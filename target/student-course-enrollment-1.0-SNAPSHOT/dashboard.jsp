<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

    <!-- ✅ Display Success/Error Message -->
    <c:if test="${not empty sessionScope.msg}">
        <div class="alert alert-info alert-dismissible fade show" role="alert">
            ${sessionScope.msg}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <%
            session.removeAttribute("msg");
        %>
    </c:if>

    <div class="mb-4">
        <h2>Welcome, ${student.firstName} ${student.lastName}</h2>
        <p>Email: ${student.email}</p>
        <a href="logout" class="btn btn-danger btn-sm">Logout</a>
    </div>

    <!-- Show Add New Course button for admin only -->
    <c:if test="${student.email == 'admin@gmail.com'}">
        <a href="addCourse.jsp" class="btn btn-success mb-3">Add New Course</a>
    </c:if>

    <h3>Available Courses</h3>
    <c:if test="${not empty courses}">
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>Course Name</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="course" items="${courses}">
                    <tr>
                        <td>${course.courseName}</td>
                        <td>${course.description}</td>
                        <td>
                            <form action="enroll" method="post" style="display: inline;">
                                <input type="hidden" name="courseId" value="${course.id}">
                                <button type="submit" class="btn btn-primary btn-sm">Enroll</button>
                            </form>

                            <!-- Admin delete course button -->
                            <c:if test="${student.email == 'admin@gmail.com'}">
                                <form action="deleteCourse" method="post" style="display: inline;" onsubmit="return confirm('Are you sure you want to delete this course?');">
                                    <input type="hidden" name="courseId" value="${course.id}">
                                    <button type="submit" class="btn btn-danger btn-sm">Delete Course</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty courses}">
        <p>No courses available at the moment.</p>
    </c:if>

    <h3>Your Enrollments</h3>
    <c:if test="${not empty enrollments}">
        <table class="table table-bordered table-hover">
            <thead class="table-secondary">
                <tr>
                    <th>Course Name</th>
                    <th>Enrollment Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="enroll" items="${enrollments}">
                    <tr>
                        <td>${enroll.courseName}</td>
                        <td>${enroll.enrollmentDate}</td>
                        <td>
                            <form action="deleteEnrollment" method="post" style="display: inline;">
                                <input type="hidden" name="courseId" value="${enroll.courseId}">
                                <button type="submit" class="btn btn-warning btn-sm">Unenroll</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty enrollments}">
        <p>You haven't enrolled in any courses yet.</p>
    </c:if>

</body>
</html>
