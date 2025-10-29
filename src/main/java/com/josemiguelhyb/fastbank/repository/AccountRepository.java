package com.josemiguelhyb.fastbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.josemiguelhyb.fastbank.model.Account;

import jakarta.persistence.LockModeType;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // 🔒 Bloqueo pesimista (FOR UPDATE)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Optional<Account> findByIdForUpdate(@Param("id") Long id);

    // Buscar cuenta por número
    Optional<Account> findByAccountNumber(String accountNumber);

    // Buscar cuentas de un usuario
    List<Account> findByUserId(Long userId);
}
