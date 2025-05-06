# Student Course Enrollment System

A full-stack Java web application for managing students, courses, and enrollments. Built using **Servlets**, **JSP**, **JDBC**, and **MySQL**, with **Bootstrap** for styling.

---

##  Features

###  Student
- Register and Login
- View available courses
- Enroll in courses
- View enrolled courses
- Delete enrollment

###  Admin
- Login
- Add/Edit/Delete courses
- View all courses

---

##  Tech Stack

- **Frontend**: HTML, CSS, JavaScript, Bootstrap
- **Backend**: Java (Servlets), JSP
- **Database**: MySQL
- **Tools**: Maven, Tomcat Server, JDBC

---

##  Project Structure

StudentEnrollmentSystem/
├── src/
│ └── com.enrollment/
│ ├── model/
│ ├── dao/
│ ├── dao.impl/
│ └── servlet/
├── WebContent/ or src/main/webapp/
│ ├── JSPs (login.jsp, register.jsp, dashboard.jsp, adminDashboard.jsp)
│ └── CSS, JS, images
├── pom.xml
└── README.md

# How to Run the Project
1) Clone this repository:
    git clone https://github.com/sunil55sah/StudentEnrollmentSystem.git
2) Import it into Eclipse or IntelliJ as a Maven project

3) Create the database using the SQL script above

4) Update DBConnection.java with your MySQL username and password

5) Run on Apache Tomcat Server

6) Visit: http://localhost:8080/StudentEnrollmentSystem/login.jsp


  ![Student Dashboard]( https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/Registration%20.png)
  ![Student Dashboard](https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/sign%20and%20admin%20login%20page%20.png)
  ![Student Dashboard]( https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/admin%20can%20add%20course%20.png)
  ![Student Dashboard]( https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/course%20adding%20.png)
  ![Student Dashboard]( https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/course%20added%20.png)
  ![Student Dashboard]( https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/studentlogin.png)
  ![Student Dashboard]( https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/without%20enrollment.png)
  ![Student Dashboard](https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/with%20enrollement.png)
