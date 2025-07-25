# Journal App

A secure and user-friendly journal application built with Spring Boot and MongoDB. This application allows users to create, update, and delete journal entries, manage their accounts, and leverage features like JWT authentication, Google OAuth, and scheduled tasks.

---

### Deployed Application
- Base URL: **[https://journal-psi-eight.vercel.app/](https://journal-psi-eight.vercel.app/)**

---

## Key Features

- **User Management**
  - Signup, login (JWT-based), and Google OAuth login
  - Update and delete user accounts
  - Role-based access control (Admin/User)
- **Journal Management**
  - Create, update, delete, and retrieve journal entries (per user)
  - Only authenticated users can access their own entries
- **Security**
  - JWT authentication for all protected endpoints
  - Passwords securely hashed with BCrypt
  - Stateless session management
- **Admin Features**
  - Admin endpoints for user management and cache clearing
- **Scheduled Tasks**
  - Weekly email reminders (for users with sentiment analysis enabled)
  - Weekly app cache clearing
- **Weather Integration**
  - Personalized greeting with current weather info
- **API Documentation**
  - Swagger/OpenAPI available at [https://journalapp-latest.onrender.com/swagger-ui/index.html](https://journalapp-latest.onrender.com/swagger-ui/index.html)

---

## Setup and Run

### Prerequisites

- **Java 21**
- **Maven**
- **MongoDB** (local or Atlas)

### Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ravix2001/journalApp.git
   cd journalApp
   ```

2. **Configure MongoDB:**
   - Update the MongoDB URI in `src/main/resources/application.yml`:
     ```yml
     spring:
       data:
         mongodb:
           uri: mongodb+srv://<username>:<password>@<cluster-url>/<database-name>
     ```

3. **Build the project:**
   ```bash
   ./mvnw clean package
   ```

4. **Run the application:**
   ```bash
   java -jar target/journalApp-0.0.1-SNAPSHOT.jar
   ```

5. **Access the application:**
   - Health Check: [https://journalapp-latest.onrender.com/health-check](https://journalapp-latest.onrender.com/health-check)

---

## Example/Demo

### Create a User
- Endpoint: `POST /signup`
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