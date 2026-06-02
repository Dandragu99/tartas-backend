# 🎂 Tartas Backend - Custom Cake E-commerce API

Backend API developed with Spring Boot as part of a full-stack e-commerce project for personalized cakes.

This project was created as a personal and family-oriented application for a custom cake business idea. It was also used as my final degree project, allowing me to apply full-stack development concepts in a practical scenario.

---

## 🚀 Overview

Tartas Backend is the server-side application of a custom cake e-commerce platform. It provides a REST API that allows the frontend application to manage products, ingredients, users, orders, images and business logic related to personalized cake creation.

The backend follows a layered architecture and is connected to an SQL database using Spring Data JPA.

---

## ✨ Main Features

* RESTful API architecture.
* Product management.
* Ingredient management.
* Product customization logic.
* User and role management.
* Order management.
* Order status timeline.
* Administrator features.
* Image upload and storage system.
* SQL database integration.
* Data persistence with Spring Data JPA.
* Backend connection with Angular frontend.
* Layered architecture using controllers, services and repositories.

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Maven
* SQL database
* MySQL
* REST API
* Git / GitHub

---

## 🧩 Full-Stack Architecture

This repository contains only the backend part of the application.

The complete project is divided into:

* Frontend: Angular application.
* Backend: Spring Boot REST API.
* Database: SQL database.
* Deployment: Railway / external hosting environment.

Frontend repository:

```text
https://github.com/Dandragu99/tartas-frontend
```

---

## 📦 Main Functional Areas

### Product Management

The backend allows administrators to create, update, delete and retrieve products used in the e-commerce platform.

### Ingredient Management

The application manages different types of ingredients used for cake customization, such as bases, fillings and extras.

### Order Management

The backend handles order-related logic, including the status of each order and the timeline used to show customers the progress of their purchase.

### Image Upload

The API includes functionality for uploading and managing product images.

### Admin Features

The backend provides endpoints and logic used by the administrator dashboard in the frontend application.

---

## 🔗 API Endpoints

Some example API endpoints used by the frontend:

### Products

```bash
/api/productos-base
```

### Ingredients

```bash
/api/ingredientes
```

### Orders

```bash
/api/pedidos
```

### Users

```bash
/api/usuarios
```

### Upload

```bash
/api/upload
```

> Endpoint names may vary depending on the current backend implementation.

---

## ⚙️ Installation

Clone the repository:

```bash
git clone https://github.com/Dandragu99/tartas-backend.git
```

Enter the project folder:

```bash
cd tartas-backend
```

---

## ▶️ Run the Application

Run the project using the Maven wrapper:

```bash
./mvnw spring-boot:run
```

Or using Maven directly:

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

---

## 🗄️ Database Configuration

Configure your database connection in:

```text
src/main/resources/application.properties
```

Example configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tartas
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

> For security reasons, real credentials should not be uploaded to GitHub.

---

## 📁 Project Structure

General structure of the backend project:

```text
src/
 ├── main/
 │   ├── java/
 │   │   └── ...
 │   │       ├── controller/
 │   │       ├── service/
 │   │       ├── repository/
 │   │       ├── model/
 │   │       ├── dto/
 │   │       └── config/
 │   └── resources/
 │       └── application.properties
 └── test/
```

---

## 📸 Image Handling

* Images are uploaded through backend endpoints.
* Images are stored and served so they can be displayed from the frontend.
* This functionality is used for product images in the e-commerce catalogue and administration panel.

---

## 🔐 Security Note

The project includes user-related and administrator-related functionality. Security and authentication may continue evolving as part of future improvements.

Recommended future improvements:

* Strengthening authentication and authorization.
* Improving role-based access control.
* Adding more validation in sensitive endpoints.
* Preparing production-ready environment variables.

---

## 📌 Project Status

Project completed as a final DAM project / TFG and presented with a final grade of 8.1.

The backend is currently considered part of a portfolio project, but it may continue evolving with improvements such as stronger security, payment integration, advanced order management and production deployment adjustments.

---

## 🎯 Purpose of the Project

The main purpose of this backend was to build the server-side part of a complete full-stack application, applying knowledge of Java, Spring Boot, REST API development, database management, business logic, file upload, layered architecture and deployment preparation.

It was also designed with the idea of becoming a possible real e-commerce solution for a family custom cake business in the future.

---

## 🧠 Notes

* Designed following layered architecture: Controller → Service → Repository.
* Uses Spring Data JPA for data persistence.
* Focused on maintainability and scalability.
* Built to communicate with an Angular frontend.
* Developed as part of a full-stack portfolio project.

---

## 👨‍💻 Author

Developed by **Danut Dragu**.

* GitHub: [Dandragu99](https://github.com/Dandragu99)
* LinkedIn: [Danut Dragu](https://www.linkedin.com/in/dandragu99/)
