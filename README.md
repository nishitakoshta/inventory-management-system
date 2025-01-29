# Inventory Management System with Reporting and Email Notifications

## Overview

The **Inventory Management System** is a web-based application designed to manage product inventory, track stock levels, and generate reports and analytics to provide business insights. This application allows users to efficiently manage their products, sales, and inventory, offering real-time updates and insightful analytics. Additionally, users can generate monthly or yearly reports in Excel format using Apache POI.

## Features

### Core Features:
1. **Product Management:**
   - Add new products to the system.
   - Update existing product information.
   - View product details such as name, price, and quantity in stock.

2. **Sales Tracking:**
   - Record and track product sales.
   - View a history of sales transactions for any product.
   
3. **Inventory Management:**
   - Track current stock levels of all products.
   - Receive alerts when stock levels fall below a predefined threshold.

### Advanced Features:
1. **Real-Time Inventory Updates:**
   - Stock levels automatically update when products are added or sold.
   - Alerts notify users when stock levels are low.

2. **Analytics Dashboard:**
   - Visualize key metrics such as most sold products, trends in sales over time, and current stock levels.
   - View data through interactive charts and tables to get insights into product performance.

3. **Reporting:**
   - Generate detailed **Excel reports** for inventory and sales data using **Apache POI**.
   - Create custom reports for monthly or yearly data analysis.

4. **User-Friendly Interface:**
   - A clean, responsive frontend built with **Thymeleaf** for seamless interaction.
   - Easy-to-navigate dashboard to quickly access key features.

## Tech Stack

- **Backend:**
  - **Spring Boot** for building the application.
  - **MySQL** for storing and managing data.
  - **Hibernate** and **JPA** for database interactions and ORM.
  
- **Frontend:**
  - **Thymeleaf** for rendering dynamic HTML views.
  
- **Reporting:**
  - **Apache POI** for generating Excel reports.

- **Dependencies:**
  - Spring Web
  - Spring Data JPA
  - Spring Boot Starter Thymeleaf
  - Apache POI (for Excel reports)

## Installation and Setup

### Prerequisites:
- **Java 11+** (JDK)
- **MySQL** (or equivalent database)
- **Maven** (for project management and dependency handling)

### Steps to Run the Application Locally:

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/inventory-management-system.git
   cd inventory-management-system
2. **Configure the Database:**
   - Set up a MySQL database and update your application.properties with the correct database connection details.
   - Example:
     ``` bash
     spring.datasource.url=jdbc:mysql://localhost:3306/inventorymanagement
     spring.datasource.username=root
     spring.datasource.password=password
     spring.jpa.hibernate.ddl-auto=update
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
3. **Build the Application:**
   - Build the project using Gradle
     ``` bash
     ./gradlew clean build
4. **Run the Application:**
   - Start the Spring Boot Application
     ``` bash
     ./gradlew bootRun
  - Access the application via http://localhost:8085 in your browser.
### Features in Detail
1. **Product Management:**
   - **Add Products:** Users can add new products by providing details such as name, description, price, and quantity.
   - **Update Products:** Existing products can be updated with new information.
   - **Product List:** View all products in the inventory with their details.
2. **Sales Tracking:**
   - Record each sale, including the product sold, quantity, and sale price.
   - View sales history and trends for each product.
3. **Inventory Management:**
   - Track the quantity of each product in the inventory.
   - Receive notifications when stock levels drop below a certain threshold.
4. **Analytics Dashboard:**
   - View a summary of the product performance such as:
      - Most Sold Products
      - Sales Trends Over Time
      - Current Stock Levels
5. **Excel Reporting:**
   - Export reports to Excel using Apache POI.
