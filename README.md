# Online Course Management System

A robust web application for managing online courses, built with Spring Boot and modern Java technologies.

## Features

### Core Functionalities
- Dynamic course content management
- Role-based access control (Teachers/Students)
- Real-time polling system
- Interactive commenting system
- Secure file management

### User Access Levels

#### Teacher Privileges
- User management (CRUD operations)
- Course material management
- Poll creation and management 
- Comment posting
- Vote participation

#### Student Privileges
- Access course materials
- Vote in polls (with edit capability)
- Post comments
- Update personal information

#### Guest Access
- View index page only

## Technology Stack

### Frontend
- Bootstrap 5
- JSP (JavaServer Pages)
- JSTL (JavaServer Pages Standard Tag Library)

### Backend
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- H2 Database


### Prerequisites
- Java Development Kit (JDK) 17+
- IntelliJ IDEA
- Gradle

### Access the application:
Open browser: http://localhost:8089/S380F_groupProject/index

### Database Configuration
- H2 Database Settings
- Database Name: mydb
- Username: sa
- Password: password


### Default User Accounts
- Teachers
Username: keith</br>
Password: keithpw</br>
Role: ROLE_TEACHER
</br>
Username: test1</br>
Password: test1pw</br>
Role: ROLE_TEACHER


- Students
Username: smith</br>
Password: smithpw</br>
Role: ROLE_STUDENT
</br>
Username: test2</br>
Password: test2pw</br>
Role: ROLE_STUDENT
