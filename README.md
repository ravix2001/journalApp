# Journal App

A secure and user-friendly journal application built with Spring Boot and MongoDB. This application allows users to create, update, and delete journal entries while ensuring role-based access control and secure authentication.

---

## Key Features

- **User Management**: 
  - Create, update, and delete user accounts.
  - Role-based access control (Admin/User).
  
- **Journal Management**:
  - Create, update, delete, and retrieve journal entries.
  - Each user can manage their own journal entries securely.

- **Security**:
  - Spring Security integration with BCrypt password encoding.
  - Stateless authentication using HTTP Basic Auth.

- **Database**:
  - MongoDB integration for storing user and journal data.
  - Transaction management for consistent data operations.

- **RESTful API**:
  - Exposes endpoints for user and journal management.
  - Admin-specific endpoints for managing all users.

---

## Setup and Run

### Prerequisites
1. **Java**: Ensure Java is installed.
2. **Maven**: Ensure Maven is installed or use the provided Maven wrapper (`mvnw` or `mvnw.cmd`).
3. **MongoDB**: Set up a MongoDB instance (local or cloud).

### Steps to Set Up
1. Clone the repository:
   ```bash
   git clone https://github.com/ravix2001/journalApp.git
   cd journalApp
   ```

2. Configure MongoDB:
   - Update the MongoDB URI in `src/main/resources/application.yml`:
     ```yml
     spring:
       data:
         mongodb:
           uri: mongodb+srv://<username>:<password>@<cluster-url>/<database-name>
     ```

3. Build the project:
   ```bash
   ./mvnw clean package
   ```

4. Run the application:
   ```bash
   java -jar target/journalApp-0.0.1-SNAPSHOT.jar
   ```

5. Access the application:
   - Health Check: [http://localhost:8080/public/health-check](http://localhost:8080/public/health-check)

---

## Example/Demo

### Create a User
- Endpoint: `POST /public/create-user`
- Request Body:
  ```json
  {
    "username": "john_doe",
    "password": "securepassword"
  }
  ```

### Create a Journal Entry
- Endpoint: `POST /journal`
- Request Body:
  ```json
  {
    "title": "My First Entry",
    "content": "Today was a great day!"
  }
  ```

### Retrieve All Journal Entries
- Endpoint: `GET /journal`

---

## Testing

### Run Unit Tests
1. Ensure MongoDB is running.
2. Execute the following command:
   ```bash
   ./mvnw test
   ```

### Key Test Classes
- `UserServiceTests`: Tests for user-related operations.
- `UserDetailsServiceImplTests`: Tests for user authentication and loading.
- `JournalAppApplicationTests`: Ensures the application context loads correctly.

---

Feel free to contribute or raise issues for improvements!