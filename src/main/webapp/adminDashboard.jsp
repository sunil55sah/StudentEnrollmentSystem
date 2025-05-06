<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.enrollment.model.Course" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    com.enrollment.model.Admin admin = (com.enrollment.model.Admin) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Course Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
</head>
<body>
<div class="container mt-5">
    <h2>Welcome, Admin</h2>
    <hr>

    <!-- Add Course Button -->
    <a href="addcourse.jsp" class="btn btn-success mb-3">Add New Course</a>

    <!-- Course Table -->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td>${course.courseName}</td>
                <td>${course.instructor}</td>
                <td>${course.description}</td>
                <td>
                    <!-- Edit Course -->
                    <form action="editCourse.jsp" method="get" style="display:inline;">
                        <input type="hidden" name="id" value="${course.id}" />
                        <button type="submit" class="btn btn-primary btn-sm">Edit</button>
                    </form>

                    <!-- Delete Course -->
                    <form action="deleteCourse" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this course?');">
                        <input type="hidden" name="courseId" value="${course.id}" />
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Logout -->
    <a href="logout" class="btn btn-secondary">Logout</a>
</div>
</body>
</html>