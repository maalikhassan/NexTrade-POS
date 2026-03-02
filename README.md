# NexTrade POS System
### Global Essential Supply Chain

![Java](https://img.shields.io/badge/Java-17-orange.svg)
![JavaFX](https://img.shields.io/badge/JavaFX-19-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.x-red.svg)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow.svg)

A modern, feature-rich Point of Sale (POS) system built with JavaFX and MySQL, designed for retail and supply chain management operations. NexTrade POS implements a clean, layered architecture with a focus on maintainability and scalability.

## 📋 Table of Contents
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Database Setup](#-database-setup)
- [Usage](#-usage)
- [Development Status](#-development-status)
- [Upcoming Features](#-upcoming-features)
- [Contributing](#-contributing)

## ✨ Features

### Currently Implemented

#### Customer Management
- ✅ Add, update, and delete customer records
- ✅ Comprehensive customer information tracking (ID, title, name, DOB, salary, address, city, province, postal code)
- ✅ Search functionality for quick customer lookup
- ✅ Dynamic city/province selection with cascading ComboBoxes
- ✅ TableView display with complete customer data
- ✅ Real-time data loading and refresh

#### Item/Inventory Management
- ✅ Complete CRUD operations for inventory items
- ✅ Item details tracking (code, description, pack size, unit price, quantity on hand)
- ✅ Search items by code
- ✅ Real-time stock quantity management
- ✅ TableView display for inventory overview

#### Order Management (Partial)
- ✅ Customer search by ID for order placement
- ✅ Item search and selection for orders
- ✅ Shopping cart structure (OrderDetailDto)
- ✅ Order data model with order details list
- ⏳ **Order placement with transaction management** (In Progress)
- ⏳ **Transaction rollback logic** (Pending)

#### UI/UX
- ✅ Modern JavaFX interface
- ✅ JFoenix Material Design components
- ✅ Custom application icon and branding
- ✅ Responsive form layouts
- ✅ Intuitive navigation between modules

## 🛠 Tech Stack

### Core Technologies
- **Java 17** - Programming Language
- **JavaFX 19** - UI Framework
- **Maven** - Build & Dependency Management
- **MySQL** - Database Management System

### Libraries & Frameworks
- **JFoenix 9.0.10** - Material Design UI Components
- **Lombok 1.18.42** - Boilerplate Code Reduction
- **MySQL Connector/J 9.5.0** - JDBC Driver
- **JasperReports** (Planned) - Report Generation

### Design Patterns
- **MVC (Model-View-Controller)** - UI Pattern
- **Repository Pattern** - Data Access Layer
- **Service Layer Pattern** - Business Logic Separation
- **Singleton Pattern** - Database Connection Management
- **DTO (Data Transfer Object)** - Data Transfer

## 🏗 Architecture

NexTrade POS follows a clean **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────────────┐
│          Presentation Layer (View)          │
│         (FXML + Controllers)                │
├─────────────────────────────────────────────┤
│           Controller Layer                  │
│   (CustomerFormController, etc.)            │
├─────────────────────────────────────────────┤
│           Service Layer                     │
│   (Business Logic + Validation)             │
│   (CustomerServiceImpl, etc.)               │
├─────────────────────────────────────────────┤
│         Repository Layer                    │
│   (Data Access + SQL Queries)               │
│   (CustomerRepositoryImpl, etc.)            │
├─────────────────────────────────────────────┤
│          Database Layer                     │
│         (MySQL Database)                    │
└─────────────────────────────────────────────┘
```

### Layer Responsibilities

#### **Controller Layer**
- Handles user interactions and UI events
- Binds data between View and Service layers
- Manages TableView and form components
- Delegates business logic to Service layer

#### **Service Layer**
- Implements business logic and validation rules
- Transforms data between DTOs and database entities
- Coordinates multiple repository operations
- Error handling and business rule enforcement

#### **Repository Layer**
- Direct database communication via JDBC
- SQL query execution
- ResultSet to DTO mapping
- Transaction management (planned)

#### **Database Connection**
- Singleton pattern for connection management
- Connection pooling ready architecture
- Configuration: `jdbc:mysql://localhost:3306/Thogakade`

## 📁 Project Structure

```
NexTrade-POS/
├── src/
│   ├── main/
│   │   ├── java/edu/icet/
│   │   │   ├── Main.java                    # Entry point
│   │   │   ├── Starter.java                 # JavaFX Application launcher
│   │   │   ├── controller/                  # UI Controllers
│   │   │   │   ├── DashController.java
│   │   │   │   ├── HomeController.java
│   │   │   │   ├── customer/
│   │   │   │   │   └── CustomerFormController.java
│   │   │   │   ├── item/
│   │   │   │   │   └── ItemFormController.java
│   │   │   │   └── order/
│   │   │   │       └── OrderFormController.java
│   │   │   ├── db/
│   │   │   │   └── DBConnection.java        # Singleton DB connection
│   │   │   ├── model/
│   │   │   │   ├── dto/                     # Data Transfer Objects
│   │   │   │   │   ├── CustomerDto.java
│   │   │   │   │   ├── ItemDto.java
│   │   │   │   │   ├── OrderDto.java
│   │   │   │   │   └── OrderDetailDto.java
│   │   │   │   └── entity/                  # Database entities (planned)
│   │   │   ├── repository/                  # Data Access Layer
│   │   │   │   ├── customer/
│   │   │   │   │   ├── CustomerRepository.java
│   │   │   │   │   └── CustomerRepositoryImpl.java
│   │   │   │   ├── item/
│   │   │   │   │   ├── ItemRepository.java
│   │   │   │   │   └── ItemRepositoryImpl.java
│   │   │   │   └── order/
│   │   │   │       ├── OrderRepository.java
│   │   │   │       └── OrderRepositoryImpl.java
│   │   │   ├── service/                     # Business Logic Layer
│   │   │   │   ├── customer/
│   │   │   │   │   ├── CustomerFormService.java
│   │   │   │   │   └── CustomerServiceImpl.java
│   │   │   │   ├── item/
│   │   │   │   │   ├── ItemFormService.java
│   │   │   │   │   └── ItemServiceImpl.java
│   │   │   │   └── order/
│   │   │   │       ├── OrderFormService.java
│   │   │   │       └── OrderServiceImpl.java
│   │   │   └── util/                        # Utility classes
│   │   └── resources/
│   │       ├── assets/                      # Images, icons, etc.
│   │       │   └── nextradelogobgrem.png
│   │       ├── css/                         # Stylesheets
│   │       └── view/                        # FXML files
│   │           ├── CustomerForm.fxml
│   │           ├── Dash.fxml
│   │           ├── Home.fxml
│   │           ├── ItemForm.fxml
│   │           └── OrderForm.fxml
│   └── test/
│       └── java/                            # Test classes
├── pom.xml                                  # Maven configuration
└── README.md
```

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java Development Kit (JDK) 17** or higher
- **Maven 3.x** or higher
- **MySQL Server 8.0** or higher
- **IDE** (IntelliJ IDEA, Eclipse, or NetBeans recommended)
- **JavaFX SDK** (automatically managed by Maven)

## 🚀 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/NexTrade-POS.git
cd NexTrade-POS
```

### 2. Configure Database Connection

Update the database credentials in [DBConnection.java](src/main/java/edu/icet/db/DBConnection.java):

```java
connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/Thogakade", 
    "your_username", 
    "your_password"
);
```

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn javafx:run
```

Or run the `Main.java` class directly from your IDE.

## 🗄 Database Setup

### Create Database
```sql
CREATE DATABASE Thogakade;
USE Thogakade;
```

### Create Tables

#### Customer Table
```sql
CREATE TABLE customer (
    CustId VARCHAR(10) PRIMARY KEY,
    CustTitle VARCHAR(10),
    CustName VARCHAR(100) NOT NULL,
    DOB DATE,
    salary DOUBLE,
    CustAddress TEXT,
    City VARCHAR(50),
    Province VARCHAR(50),
    PostalCode VARCHAR(10)
);
```

#### Item Table
```sql
CREATE TABLE item (
    ItemCode VARCHAR(10) PRIMARY KEY,
    Description VARCHAR(200) NOT NULL,
    PackSize VARCHAR(50),
    UnitPrice DOUBLE NOT NULL,
    QtyOnHand INT NOT NULL
);
```

#### Orders Table (For future implementation)
```sql
CREATE TABLE orders (
    OrderId VARCHAR(10) PRIMARY KEY,
    OrderDate DATE NOT NULL,
    CustomerId VARCHAR(10),
    FOREIGN KEY (CustomerId) REFERENCES customer(CustId)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
```

#### Order Details Table (For future implementation)
```sql
CREATE TABLE order_detail (
    OrderId VARCHAR(10),
    ItemCode VARCHAR(10),
    Qty INT NOT NULL,
    UnitPrice DOUBLE NOT NULL,
    Discount DOUBLE DEFAULT 0,
    Total DOUBLE NOT NULL,
    PRIMARY KEY (OrderId, ItemCode),
    FOREIGN KEY (OrderId) REFERENCES orders(OrderId)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (ItemCode) REFERENCES item(ItemCode)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);
```

## 💻 Usage

### Customer Management
1. Navigate to **Customer Form** from the dashboard
2. Fill in customer details (ID, title, name, DOB, salary, address, etc.)
3. Click **Add** to create a new customer
4. Select a customer from the table to **Update** or **Delete**
5. Use the search functionality to find specific customers

### Item Management
1. Navigate to **Item Form** from the dashboard
2. Enter item details (code, description, pack size, unit price, quantity)
3. Click **Add** to add new inventory items
4. Select items from the table to **Update** or **Delete**
5. Use search to quickly locate items by code

### Order Processing (In Development)
1. Navigate to **Order Form**
2. Enter customer ID to load customer details
3. Search and add items to the cart
4. Adjust quantities and apply discounts
5. **Place Order** (with transaction management - Coming Soon)

## 📊 Development Status

### ✅ Completed Modules
- [x] Project architecture and structure
- [x] Database connection management (Singleton pattern)
- [x] Customer CRUD operations
- [x] Item/Inventory CRUD operations
- [x] Order data models (DTO layer)
- [x] Basic order form UI and item search
- [x] Repository layer implementation
- [x] Service layer implementation
- [x] Controller layer implementation

### 🚧 In Progress
- [ ] **Order Placement with Transaction Management**
  - Order creation with multiple order details
  - Automatic stock reduction
  - Transaction begin/commit/rollback logic
  - Concurrent order handling
  - Data consistency guarantees

### 📅 Planned Features
- [ ] **Advanced Transaction Management**
  - ACID compliance for order operations
  - Rollback on failure scenarios
  - Optimistic/Pessimistic locking
  - Connection pooling implementation

- [ ] **JasperReports Integration**
  - Sales reports (daily, weekly, monthly)
  - Inventory reports (stock levels, low stock alerts)
  - Customer purchase history
  - Invoice generation
  - Custom report templates
  - PDF export functionality

## 🚀 Upcoming Features

### Phase 1: Transaction Management (Priority)
```java
// Planned implementation in OrderRepositoryImpl
public boolean placeOrder(OrderDto order) throws SQLException {
    Connection connection = DBConnection.getInstance().getConnection();
    
    try {
        connection.setAutoCommit(false);  // Start transaction
        
        // 1. Insert order
        insertOrder(order, connection);
        
        // 2. Insert order details
        for (OrderDetailDto detail : order.getOrderDetails()) {
            insertOrderDetail(detail, order.getOrderId(), connection);
            
            // 3. Update stock quantity
            updateItemStock(detail.getItemCode(), detail.getQty(), connection);
        }
        
        connection.commit();  // Commit transaction
        return true;
        
    } catch (SQLException e) {
        connection.rollback();  // Rollback on error
        throw e;
    } finally {
        connection.setAutoCommit(true);
    }
}
```

### Phase 2: Reporting Module
- **JasperReports Integration**
  - Sales summary reports
  - Inventory status reports
  - Customer analytics
  - Revenue analysis
  - Download reports as PDF/Excel

### Phase 3: Enhancements
- User authentication and authorization
- Multi-user support with role-based access
- Real-time inventory alerts (low stock warnings)
- Barcode scanning integration
- Receipt printing
- Payment gateway integration
- Dashboard analytics with charts
- Backup and restore functionality
- Audit trail logging
- Multi-branch support

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Style Guidelines
- Follow Java naming conventions
- Use Lombok annotations to reduce boilerplate
- Maintain layered architecture separation
- Add JavaDoc comments for public methods
- Write unit tests for new features

## 📝 License

This project is developed as part of academic coursework at ICET (Institute of Computer Engineering Technology).

## 👥 Authors

- **Developer** - Initial work and ongoing development

## 🙏 Acknowledgments

- Institute of Computer Engineering Technology (ICET)
- JavaFX community for excellent documentation
- JFoenix for Material Design components
- Open source community

## 📞 Support

For questions, issues, or suggestions:
- Open an issue in the repository
- Contact: [Your contact information]

---

**Note:** This project is currently under active development. The order placement with transaction management and JasperReports integration are scheduled for completion in the upcoming development cycle.

**Last Updated:** March 2026
**Version:** 1.0-SNAPSHOT
**Status:** 🚧 In Development
