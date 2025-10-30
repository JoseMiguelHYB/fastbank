# 🚀 FASTBANK REST API

**FastBank** es una API REST desarrollada con **Java 17 + Spring Boot**, que simula operaciones bancarias básicas:  
✅ creación de usuarios,  
✅ creación de cuentas,  
✅ depósitos, retiros y transferencias,  
✅ y operaciones concurrentes seguras (multi-hilo) usando **bloqueo pesimista** con JPA.

---

## 🧩 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA (Hibernate)**
- **Maven**
- **H2 Database (en memoria)**
- **JPA @Lock(LockModeType.PESSIMISTIC_WRITE)** → para concurrencia segura
- **Threads** → simulación de transacciones concurrentes
- **Actuator** → métricas de latencia y salud del sistema
- **API REST** completamente funcional

---

## 📦 Estructura del Proyecto
com.josemiguelhyb.fastbank
│
├── controller/ → Controladores REST
├── service/ → Lógica de negocio (transacciones seguras y concurrentes)
├── model/ → Entidades (User, Account, Transaction)
├── dto/ → Data Transfer Objects (Request / Response)
├── mapper/ → Conversión entre entidades y DTOs
└── repository/ → Repositorios JPA

## ⚙️ Cómo ejecutar el proyecto

1. Clonar el repositorio  
   ```bash
   git clone https://github.com/josemiguelhyb/fastbank.git
   cd fastbank


   mvn spring-boot:run


## Endpoints

## 👤 Usuarios
# - Crear usuario
**POST** `/api/users`

**Request Body:**
```json
{
  "name": "Juan Pérez",
  "email": "juan@example.com"
}

