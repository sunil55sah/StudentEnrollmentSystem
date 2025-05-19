## ## Student Course Enrollment System

A full-stack Java web application for managing students, courses, and enrollments. Built using **Servlets**, **JSP**, **JDBC**, and **MySQL**, with **Bootstrap** for styling.
## # Project is successfully **hosted on Railway using Docker**:  
 [Click to open Student Course Enrollment System](https://studentenrollmentsystem-production.up.railway.app/login.jsp)
---

##  Features

## #  Student
- Register and Login
- View available courses
- Enroll in courses
- View enrolled courses
- Delete enrollment

## #  Admin
- Login
- Add/Edit/Delete courses
- View all courses

---

# #  Tech Stack

- **Frontend**: HTML, CSS, JavaScript, Bootstrap  
- **Backend**: Java (Servlets), JSP  
- **Database**: MySQL  
- **Tools**: Maven, Tomcat Server, JDBC  
- **Deployment**: Docker + Railway  

---

## # Project Structure
StudentEnrollmentSystem/
├── src/
│ └── com/enrollment/
│ ├── model/
│ ├── dao/
│ ├── dao/impl/
│ └── servlet/
├── src/main/webapp/
│ ├── JSPs (login.jsp, register.jsp, dashboard.jsp, adminDashboard.jsp)
│ └── CSS, JS, images
├── pom.xml
├── Dockerfile
└── README.md

---

## # Live Demo

## # Project is successfully **hosted on Railway using Docker**:  
 [Click to open Student Course Enrollment System](https://studentenrollmentsystem-production.up.railway.app/login.jsp)

---
Docker + Railway Deployment Steps
## # Dockerfile
A multi-stage Dockerfile is included:

## # Docker + Railway Deployment (Short Steps)
1. Push the project to GitHub with a working Dockerfile.

2. Create a new Railway project → choose Deploy from GitHub Repo.

3. Add a MySQL service in Railway → It auto-generates DB credentials.

4. Set environment variables in StudentEnrollmentSystem service:


   ![Students and Administrators ](https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/images/Students%20and%20Administrators%20-%20Sign%20in%20here.png)
   ![AdminLogin](https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/images/AdminLogin.png)
   ![Student Registration](https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/images/Students%20and%20Administrators%20-%20Sign%20in%20here.png)
    ![AddCourses](https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/images/AddCourses.png)
   ![Admin Dashboard](https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/images/Admin%20Dashboard.png)
   ![Welcome dashboard](https://github.com/sunil55sah/StudentEnrollmentSystem/blob/main/images/Welcome%20dashboard.png)
  
