# Student Voting System

## Overview
The **Student Voting System** is a Java-based application designed to manage and automate student elections. The system allows administrators to manage candidates, register students, collect votes, and generate election reports efficiently.

This project uses:
- Java
- MySQL Database
- JDBC
- Apache POI (for Excel report generation)
- Visual Studio Code


# Features

## Admin Features
- Admin login system
- Add, update, and delete candidates
- Manage allowed students
- View election results
- Export election reports to Excel

## Student Features
- Student voting access
- Secure one-time voting    
- View available candidates

## System Features
- Database connectivity using JDBC
- Automatic vote counting
- Excel report generation using Apache POI
- Organized DAO and Service architecture


# Project Structure

```text
StudentVotingSystem/
│
├── src/                 # Source code files
├── bin/                 # Compiled class files
├── lib/                 # External libraries and dependencies
├── README.md            # Project documentation
└── Election_Report.xlsx # Generated election report
```


# Main Classes

| Class Name | Description |
|---|---|
| `Main.java` | Main entry point of the application |
| `AdminDAO.java` | Handles admin database operations |
| `StudentDAO.java` | Handles student database operations |
| `CandidateDAO.java` | Handles candidate database operations |
| `VoteService.java` | Manages voting process |
| `ResultService.java` | Calculates and displays results |
| `ExportService.java` | Exports election reports to Excel |
| `DBConnection.java` | Handles database connection |


# Requirements

Before running the system, install the following:

- Java JDK 8 or higher
- MySQL Server
- Visual Studio Code or any Java IDE
- JDBC Driver

---

# Database Setup

## Create Database

```sql
CREATE DATABASE student_voting_system;
```

## Example Tables

### Admin Table

```sql
CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
);
```

### Students Table

```sql
CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `student_number` varchar(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `year_level` int(11) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `has_voted` tinyint(1) DEFAULT 0
);
```

### Candidates Table

```sql
CREATE TABLE candidates (
    candidate_id INT AUTO_INCREMENT PRIMARY KEY,
    candidate_name VARCHAR(100) NOT NULL,
    position VARCHAR(50) NOT NULL,
    votes INT DEFAULT 0
);
```

### Election Settings Table

```sql
CREATE TABLE `election_settings` (
  `id` int(11) NOT NULL,
  `status` varchar(20) DEFAULT NULL
);
```

### Votes Table

```sql
CREATE TABLE `votes` (
  `vote_id` int(11) NOT NULL,
  `student_number` varchar(20) DEFAULT NULL,
  `candidate_id` int(11) DEFAULT NULL,
  `vote_date` timestamp NOT NULL DEFAULT current_timestamp()
);
```


# How to Run the Project

## 1. Open in Visual Studio Code

Open the project folder in VS Code.

## 2. Add Required Libraries

Ensure all `.jar` files inside the `lib` folder are included in the project dependencies.

## 3. Configure Database Connection

Update the database credentials inside:

```java
DBConnection.java
```

Example:

```java
String url = "jdbc:mysql://localhost:3306/student_voting_system";
String user = "root";
String password = "";
```

## 4. Run the Program

Run:

```text
Main.java
```



