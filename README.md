# Railway Management System

The **Railway Management System** is a Spring Boot-based application that streamlines railway operations and provides users and administrators with an efficient platform to manage train schedules, bookings, and user authentication.

---

## Features

### Authentication and Authorization
- User registration with role-based access control (`USER`, `ADMIN`).
- Login with JWT token issuance and validation.
- Secure endpoints with role-based restrictions.

### Train Management (Admin)
- Add, update, and delete train details.
- View all train schedules.

### User Operations
- Search for trains based on availability.
- Book tickets and view booking history.

### Booking Management
- Admins can view all bookings.
- Users can view their personal bookings.

### Security
- JWT-based authentication and token validation for secure communication.
- Role-based access to APIs.

### Error Handling
- Global exception handling for user-friendly error messages.

---

## API Endpoints

### Auth Endpoints
| Method | Endpoint        | Description                |
|--------|-----------------|----------------------------|
| POST   | `/api/auth/register` | Register a new user      |
| POST   | `/api/auth/login`    | Login and receive JWT    |

### Train Endpoints (Admin)
| Method | Endpoint        | Description                |
|--------|-----------------|----------------------------|
| POST   | `/api/admin/train` | Add a new train           |
| PUT    | `/api/admin/train` | Update train details      |
| DELETE | `/api/admin/train` | Delete a train            |
| GET    | `/api/trains`      | View all train schedules  |

### User Endpoints
| Method | Endpoint             | Description                |
|--------|----------------------|----------------------------|
| GET    | `/api/users/bookings` | View user booking history  |
| POST   | `/api/bookings`       | Book a train ticket        |

### Booking Endpoints (Admin)
| Method | Endpoint            | Description               |
|--------|---------------------|---------------------------|
| GET    | `/api/admin/bookings` | View all bookings         |

---


---

## Setup and Installation

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL or any relational database
- Postman or any API testing tool (optional)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/railway-app.git

2. Navigate to the project directory:
   ````bash
   cd railway
3. Configure the database in application.properties:
properties
    ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/railwaydb
    spring.datasource.username=root
    spring.datasource.password=yourpassword
4. Build the project:
    ````bash
    mvn clean install
5. Run the application:

mvn spring-boot:run

## Technologies Used
- Backend: Spring Boot, Spring Security
- Database: MySQL
- Authentication: JWT
- Build Tool: Maven
- Additional Libraries: Lombok, JJWT



Replace placeholders like `your-username` and `your-email@example.com` with the appropriate values.


