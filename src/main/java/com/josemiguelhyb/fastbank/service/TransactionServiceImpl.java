package com.josemiguelhyb.fastbank.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.josemiguelhyb.fastbank.model.Account;
import com.josemiguelhyb.fastbank.model.Transaction;
import com.josemiguelhyb.fastbank.model.TransactionType;
import com.josemiguelhyb.fastbank.repository.AccountRepository;
import com.josemiguelhyb.fastbank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionRepository transactionRepository;
	private final AccountRepository accountRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
		this.transactionRepository = transactionRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	@Transactional
	public Transaction deposit(Long accountId, BigDecimal amount) {
		// 1. Buscar la cuoenta por ID
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
		
		// 2. Actualizar el saldo de la cuenta
		BigDecimal nuevoSaldo = account.getBalance().add(amount); // conversión explícita
		account.setBalance(nuevoSaldo);
		accountRepository.save(account); 
		
		// 3 - Crear una nueva transacción (depósito = solo toAccount)
		Transaction transaction = new Transaction();
		transaction.setAccount(account); // depósito a esa cuenta
		transaction.setAmount(amount);
		transaction.setType(TransactionType.DEPOSIT);
		transaction.setCreatedAt(LocalDateTime.now());
		
		// 4 - Guardar y devolver la transacción
		return transactionRepository.save(transaction);
	}
	
	// Otra forma mas sencilla
	/**@Override
    @Transactional
    public Transaction deposit(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction(null, account, amount, TransactionType.DEPOSIT);
        return transactionRepository.save(transaction);
    }**/
		

	@Override
	@Transactional
	public Transaction withdraw(Long accountId, BigDecimal amount) {
		// Buscar la cuenta
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
		
		// Verificar saldo suficiente
		// compareTo devuelve: -1 si es menor, 0 si es igual, 1 si es mayor
		if(account.getBalance().compareTo(amount) < 0) {
			throw new RuntimeException("Saldo insuficiente");
		}
		
		// 3 - Restar el monto al saldo actual
		BigDecimal nuevoSaldo = account.getBalance().subtract(amount);
		account.setBalance(nuevoSaldo);
		accountRepository.save(account);
		
		// 4 - Registrar la transacción
		//Transaction transaction = new Transaction(account, null, amount, TransactionType.WITHDRAW);
		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setAmount(amount);
		transaction.setType(TransactionType.WITHDRAW);
		transaction.setCreatedAt(LocalDateTime.now());
				
		return transactionRepository.save(transaction);
	}

	@Override
	@Transactional
	public Transaction transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
		// 1. Buscar las cuentas
		Account from = accountRepository.findById(fromAccountId)
				.orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));
		
		Account to = accountRepository.findById(toAccountId)
				.orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));
		
		// 2. Comprobar el saldo
		// comparteTo devuelve: -1 si es menor, 0 si es igual, 1 si es mayor
		if(from.getBalance().compareTo(amount) < 0) {
			throw new RuntimeException("Saldo insuficiente");
		}
		
		// 3. Actualizar los saltos de ambas cuentas
		from.setBalance(from.getBalance().subtract(amount)); // restamos de la cuenta origen
		to.setBalance(to.getBalance().add(amount)); // suammos a la cuenta destino
		
		// 4. Guardar las cuentas actualizadas en la base de datos
		accountRepository.save(from);
		accountRepository.save(to);
		
		// 5. Registrar la transacción de salida
		Transaction outgoing = new Transaction();
		outgoing.setAccount(from); // dinero sale de esta cuenta
		outgoing.setAmount(amount.negate()); // opcional: poner negativo para salidas
		outgoing.setType(TransactionType.TRANSFER);
		outgoing.setCreatedAt(LocalDateTime.now());
		transactionRepository.save(outgoing);
		
		// 6. Registrar transacción de entrada
		Transaction incoming = new Transaction();
		incoming.setAccount(to);
		incoming.setAmount(amount);
		incoming.setType(TransactionType.TRANSFER);
		incoming.setCreatedAt(LocalDateTime.now());		
		transactionRepository.save(incoming);	
		
		return outgoing; //o incoming, según lo que quieras devolver
	}

	@Override
	public List<Transaction> getTransactionsByAccount(Account accountId) {
		// Buscamos todas las transacciones donde la cuenta sea origen o destino
		return transactionRepository.findByAccount(accountId);
	}
}
