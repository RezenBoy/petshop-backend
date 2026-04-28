# Bowlfull Buddies

A full-stack pet shop web application built with Spring Boot (backend) and a Node.js-based frontend, connected to a MySQL database.

---

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Project Setup](#project-setup)
3. [Database Configuration](#database-configuration)
4. [Backend Setup](#backend-setup)
5. [Frontend Setup](#frontend-setup)
6. [Connecting Frontend to Backend](#connecting-frontend-to-backend)
7. [Running the Application](#running-the-application)
8. [Common Errors and Fixes](#common-errors-and-fixes)
9. [Pro Tips](#pro-tips)

---

## Prerequisites

Ensure the following tools are installed on your system before proceeding.

**Java (JDK 17)**

```bash
java -version
```

**Apache Maven**

```bash
mvn -v
```

**Node.js and npm**

```bash
node -v
npm -v
```

**MySQL**

Install MySQL Server and MySQL Workbench from the official MySQL website.

---

## Project Setup

Clone both repositories to your local machine.

```bash
git clone https://github.com/RezenBoy/petshop-backend.git
git clone https://github.com/RezenBoy/petshop-frontend.git
```

---

## Database Configuration

Open MySQL Workbench or any MySQL client and execute the following command to create the application database.

```sql
CREATE DATABASE bowlfull_buddies;
```

---

## Backend Setup

### Step 1: Navigate to the backend directory

```bash
cd petshop-backend
```

### Step 2: Fix PowerShell execution policy (Windows only, run once)

```powershell
Set-ExecutionPolicy RemoteSigned
```

When prompted, type `Y` and press Enter.

### Step 3: Configure database connection

Open the file located at:

```
src/main/resources/application.properties
```

Update the following properties with your local MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bowlfull_buddies
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Step 4: Build the project

Using the Maven wrapper (recommended):

```bash
.\mvnw clean compile
```

Or using the globally installed Maven:

```bash
mvn clean compile
```

### Step 5: Start the backend server

```bash
.\mvnw spring-boot:run
```

The backend will be available at:

```
http://localhost:8080
```

---

## Frontend Setup

### Step 1: Navigate to the frontend directory

```bash
cd ../petshop-frontend
```

### Step 2: Install dependencies

```bash
npm install
```

### Step 3: Start the frontend server

```bash
npm start
```

The frontend will be available at:

```
http://localhost:3000
```

---

## Connecting Frontend to Backend

All API calls from the frontend should target the backend base URL:

```
http://localhost:8080
```

Example usage:

```javascript
fetch("http://localhost:8080/api/products")
```

---

## Running the Application

Use the following sequence to start both servers correctly. Always start the backend before the frontend.

```bash
# Start the backend
cd petshop-backend
.\mvnw clean compile
.\mvnw spring-boot:run

# Start the frontend (in a separate terminal)
cd ../petshop-frontend
npm install
npm start
```

Once both servers are running, open your browser and navigate to:

```
http://localhost:3000
```

---

## Common Errors and Fixes

**`mvn` not recognized**

Use the Maven wrapper bundled with the project instead of the global Maven installation.

```bash
.\mvnw clean compile
```

**Unresolved compilation problem**

This typically indicates a missing Lombok dependency or a stale build cache. To resolve:

- Add the Lombok dependency to `pom.xml` if not already present
- Restart your IDE
- Run a clean build: `.\mvnw clean compile`

**Unknown database error**

The database has not been created yet. Connect to MySQL and run:

```sql
CREATE DATABASE bowlfull_buddies;
```

**Port already in use**

If port 8080 is occupied, change the backend port in `application.properties`:

```properties
server.port=8081
```

**CORS error**

If the frontend cannot reach the backend due to cross-origin restrictions, annotate the relevant controller(s) with:

```java
@CrossOrigin(origins = "http://localhost:3000")
```

---

## Pro Tips

- Always start the backend server before the frontend
- Use the Maven wrapper (`.\mvnw`) rather than the global `mvn` command for consistent builds
- Ensure MySQL is running before starting the backend
- Use Postman or a similar API client to test backend endpoints independently before integrating with the frontend

---

## Final Checklist

- [ ] Java 17 installed
- [ ] Maven available (global or via wrapper)
- [ ] MySQL server running
- [ ] `bowlfull_buddies` database created
- [ ] `application.properties` updated with correct credentials
- [ ] Backend running on port 8080
- [ ] Frontend running on port 3000
