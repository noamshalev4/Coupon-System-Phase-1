# 🎟️ Coupon System - Phase 1

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![MySQL](https://img.shields.io/badge/MySQL-Database-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg)]()

A comprehensive **Java-based coupon management system** designed to handle multi-tier coupon operations for administrators, companies, and customers. This enterprise-grade application provides robust coupon lifecycle management with secure authentication and role-based access control.

## 🌟 Features

### 👨‍💼 Administrator Features
- **Company Management**: Create, update, delete, and view company profiles
- **Customer Management**: Full CRUD operations for customer accounts
- **System Oversight**: Complete administrative control over the platform

### 🏢 Company Features
- **Coupon Creation**: Design and launch promotional coupons
- **Inventory Management**: Track coupon availability and usage
- **Category-based Filtering**: Organize coupons by business categories
- **Price-based Queries**: Filter coupons by price ranges
- **Real-time Updates**: Modify coupon details and availability

### 👥 Customer Features
- **Coupon Discovery**: Browse available coupons across all companies
- **Purchase System**: Secure coupon acquisition with validation
- **Personal Collection**: View purchased coupon history
- **Advanced Filtering**: Search by category, price, and availability
- **Purchase Protection**: Prevents duplicate purchases and expired coupon acquisition

## 🏗️ Architecture

### **Design Patterns**
- **DAO (Data Access Object)**: Clean separation between business logic and data persistence
- **Singleton Pattern**: Efficient database connection management
- **Service Layer**: Business logic encapsulation
- **Factory Pattern**: Client service instantiation

### **Technology Stack**
- **Backend**: Java 21
- **Database**: MySQL with JDBC
- **Connection Management**: Custom Connection Pool
- **Architecture**: Multi-layered enterprise application

## 📁 Project Structure

```
Coupon-System-Phase-1/
├── 📄 README.md
├── 📄 LICENSE (MIT)
├── 📄 Coupon-System-Phase-1.iml
└── JhonCoupons/
    ├── 📄 JhonCoupons.iml
    └── src/
        ├── 🚀 App.java                    # Application entry point
        ├── 🧪 Testing.java                # Comprehensive test suite
        ├── beans/                         # Data models
        │   ├── 📊 Category.java          # Coupon categories enum
        │   ├── 🏢 Company.java           # Company entity
        │   ├── 🎟️ Coupon.java            # Coupon entity
        │   └── 👤 Customer.java          # Customer entity
        ├── dao/                          # Data Access Layer
        │   ├── Companies/
        │   │   ├── 📋 CompaniesDAO.java
        │   │   └── 🔗 CompaniesDBDAO.java
        │   ├── Coupons/
        │   │   ├── 📋 CouponsDAO.java
        │   │   └── 🔗 CouponsDBDAO.java
        │   └── Customers/
        │       ├── 📋 CustomersDAO.java
        │       └── 🔗 CustomersDBDAO.java
        ├── db/
        │   └── 🏊 ConnectionPool.java     # Database connection management
        ├── exceptions/                    # Custom exception handling
        │   ├── ⚠️ CompanyAlreadyExistException.java
        │   ├── ⚠️ EmailAlreadyExistException.java
        │   ├── ⚠️ ExpiredCouponException.java
        │   ├── ⚠️ InvalidUserInfoException.java
        │   ├── ⚠️ TitleAlreadyExistException.java
        │   └── ⚠️ UnablePurchaseException.java
        └── services/                      # Business logic layer
            ├── 👨‍💼 AdminService.java        # Administrator operations
            ├── 🔐 ClientService.java       # Base client service
            ├── 📊 ClientType.java          # User role enumeration
            ├── 🏢 CompanyService.java      # Company operations
            ├── 🎯 CouponCondition.java     # Coupon validation states
            ├── ⏰ CouponExpirationDailyJob.java # Automated cleanup
            ├── 👥 CustomerService.java     # Customer operations
            └── 🔑 LoginManager.java        # Authentication management
```

## 🚀 Getting Started

### Prerequisites
- **Java Development Kit (JDK) 21** or higher
- **MySQL Server** (8.0 recommended)
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code
- **Maven** (optional, for dependency management)

### Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/noamshalev4/Coupon-System-Phase-1.git
   cd Coupon-System-Phase-1
   ```

2. **Database Configuration**
   ```sql
   -- Create database
   CREATE DATABASE jhon_CouponsDB;
   
   -- Update connection details in ConnectionPool.java
   -- Default: localhost:3306, user: root, password: 1234
   ```

3. **Database Schema Setup**
   Create the required tables:
   - `companies` - Company information
   - `customers` - Customer profiles
   - `coupons` - Coupon details
   - `categories` - Coupon categories
   - `customers_vs_coupons` - Purchase history

4. **Run the Application**
   ```bash
   # Compile and run
   javac -cp . JhonCoupons/src/App.java
   java -cp . App
   ```

## 💡 Usage Examples

### Administrator Login
```java
AdminService admin = (AdminService) LoginManager.getInstance()
    .login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
```

### Company Operations
```java
CompanyService company = (CompanyService) LoginManager.getInstance()
    .login("company@email.com", "password", ClientType.COMPANY);

// Create new coupon
Coupon coupon = new Coupon(companyId, Category.FOOD, 
    "50% Off Pizza", "Half price on all pizzas", 
    startDate, endDate, 100, 25.0, "🍕");
company.addCoupon(coupon);
```

### Customer Operations
```java
CustomerService customer = (CustomerService) LoginManager.getInstance()
    .login("customer@email.com", "password", ClientType.CUSTOMER);

// Purchase coupon
Coupon coupon = customer.getOneCoupon(couponId);
customer.purchaseCoupon(coupon, customerId);
```

## 🔐 Security Features

- **Role-based Access Control**: Separate permissions for Admin, Company, and Customer roles
- **Input Validation**: Comprehensive validation for all user inputs  
- **Purchase Protection**: Prevents duplicate purchases and expired coupon acquisition
- **Connection Security**: Secure database connection management
- **Exception Handling**: Robust error handling and user feedback

## ⚡ Performance Features

- **Connection Pooling**: Efficient database connection management (20 concurrent connections)
- **Automated Cleanup**: Daily job removes expired coupons automatically
- **Optimized Queries**: Efficient SQL queries with prepared statements
- **Thread Safety**: Synchronized connection pool operations

## 🧪 Testing

The application includes a comprehensive test suite (`Testing.java`) covering:
- ✅ User authentication across all roles
- ✅ CRUD operations for all entities  
- ✅ Coupon purchase workflows
- ✅ Business rule validation
- ✅ Exception handling scenarios

Run tests:
```bash
java -cp . Testing
```

## 🛠️ Available Categories

The system supports 10 coupon categories:
- 🍔 **FOOD** - Restaurants and dining
- 👗 **FASHION** - Clothing and accessories  
- 🎬 **CINEMA** - Movies and entertainment
- 💆 **SPA** - Wellness and relaxation
- 💻 **TECH** - Electronics and gadgets
- 🪑 **FURNITURE** - Home and office furniture
- ⚽ **SPORT** - Sports and fitness
- 🎨 **ART** - Arts and crafts
- 🛠️ **CRAFT** - DIY and handmade items
- 🏢 **OFFICE** - Business and office supplies

## 🚧 Future Enhancements (Phase 2)

- 🌐 **RESTful API**: Spring Boot REST endpoints
- 🎨 **Web Interface**: Angular/React frontend
- 🔐 **JWT Authentication**: Token-based security
- 📧 **Email Notifications**: Automated email system
- 📊 **Analytics Dashboard**: Business intelligence
- 🏪 **Multi-tenant Support**: SaaS capabilities

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Noam Shalev**
- GitHub: [@noamshalev4](https://github.com/noamshalev4)
- LinkedIn: [Connect with me](https://www.linkedin.com/in/noam-shalev-701b75335/)

## 🙏 Acknowledgments

- Java community for excellent documentation
- MySQL for robust database management
- IntelliJ IDEA for development environment
- Open source community for inspiration

---

<div align="center">

**⭐ Star this repository if you find it helpful!**

Made with ❤️ by [Noam Shalev](https://github.com/noamshalev4)

</div>