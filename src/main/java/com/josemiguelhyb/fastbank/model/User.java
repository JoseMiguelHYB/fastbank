// MODELO  -> Son las entidades JPA lo que va a representar la tabla en la BD.
paquete com.josemiguelhyb.fastbank.model; com.josemiguelhyb.fastbank.model;

Importarónón  jakarta.persistencia. Columna; jakarta.persistence.Column;jakarta.persistencia. Columna; jakarta.persistence.Column;
Importarónón  jakarta.persistencia. Entidad; jakarta.persistence.Entity;jakarta.persistencia. Entidad; jakarta.persistence.Entity;
Importarónón  jakarta.persistencia. Valor generado; jakarta.persistence.GeneratedValue;jakarta.persistencia. Valor generado; jakarta.persistence.GeneratedValue;
Importarónón  jakarta.persistencia. Tipo  Yakarta persistenciaón; jakarta.persistence.GenerationType;
Importarónón  jakarta.persistencia. Id; jakarta.persistence.Id;
Importarónón  jakarta.persistencia. Mesa; jakarta.persistence.Table;

@entity
@table ( nombre =   "usuarios")(nombre =  "users")
usuarioúblicoúblico de clase pública  blica  { clase User {
	
@id 
@GeneratedValue (estrategia = GenerationType. Identidad) (Estrategia =  GenerationType. Identidad.GenerationType.IDENTITY)
Largo id   privado;    id privado; private Long id;
	
@column (nulable = falso) (nula = false)
nombre de cuerda privado;  de cuerda  privado;  private String name;
	
@column (único = verdadero, nula = falso) (único =  true, nulable= false)
correo electrónico privado de String;  electrónico privado de String; private String email;
	
@column (nulable = falso) (nula = false)
contraseña privada de String; ña privada de String; private String password;
	
	// Constructor vacío (obligatorio para JPA) // Constructor vacío (obligatorio para JPA)
Usuario público () {  público () { public User() {
} }

	públicoúblico  UserUser(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	// Getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
paquete

@id 
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}	
}
