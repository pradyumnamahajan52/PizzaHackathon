# PizzaHackathon

# Online Pizza Order System

This project is a console-based application designed for managing a pizza restaurant's order processing. It includes two main modules: a **Customer Module** for users to view the menu, add items to the cart, and place orders, and an **Admin Module** for the restaurant manager to manage orders. The application was developed using Core Java, JDBC, and MySQL as part of a hackathon project.

## Team Members
- **Pradyumna Mahajan**
- **Mitali Gupta** (@mitaligupta141)

## Technologies Used
- **Core Java**
- **JDBC**
- **MySQL** (Database)

## Project Structure

The project comprises two modules:
1. **Customer Module**: Allows users to register, sign in, view menus, and place orders.
2. **Admin Module**: Enables the manager to track and manage orders in the database.

## Features

### Customer Module
- **User Registration**: Register a new account with contact details and delivery address.
- **User Login**: Sign in with an email ID and password.
- **Menu Viewing**: View the pizza menu categorized into Veg and Non-Veg with various subcategories.
- **Item Selection**: Choose pizzas from the menu, view details, and select sizes (Regular, Medium, or Large).
- **Cart Management**: Add selected items to the cart, view cart contents, and place orders.

### Admin Module
- **View Orders**: Display all orders placed by customers in descending order by date.
- **Order Management**: View detailed information of individual orders and update order statuses to "Dispatched" or "Delivered."

## Setup Instructions

### Prerequisites
- **Java**: Make sure Java is installed. You can download it from [here](https://www.oracle.com/java/technologies/javase-downloads.html).
- **MySQL**: Install MySQL and set up a database for storing orders and user details.
- **JDBC Driver**: Ensure the MySQL JDBC driver is added to your classpath.

### Database Setup
1. Create a database in MySQL for the project.
2. Import the SQL schema and table definitions for storing users, orders, pizza items, and cart details (provide schema if available).
3. Update database credentials in the project configuration (e.g., `db.properties` or directly in the code if specified).

### Running the Project
1. Compile the Java code.
   ```bash
   javac -d bin src/com/pizzabite/main/Main.java
