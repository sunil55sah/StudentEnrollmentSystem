<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.enrollment.model.Course" %>
<%@ page import="com.enrollment.dao.impl.CourseDAOImpl" %>

<%
    String idParam = request.getParameter("id");
    int courseId = 0;
    Course course = null;

    try {
        courseId = Integer.parseInt(idParam);
        CourseDAOImpl dao = new CourseDAOImpl();
        course = dao.getCourseById(courseId);

        if (course == null) {
            response.sendRedirect("adminDashboard.jsp?error=notfound");
            return;
        }
    } catch (Exception e) {
        response.sendRedirect("adminDashboard.jsp?error=invalid");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Course</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-5">
    <h2>Edit Course</h2>
    <form action="updateCourse" method="post">
        <!-- Hidden course ID -->
        <input type="hidden" name="courseId" value="<%= course.getId() %>" />

        <div class="mb-3">
            <label>Course Name</label>
            <input type="text" name="courseName" class="form-control" value="<%= course.getCourseName() %>" required />
        </div>
        <div class="mb-3">
            <label>Instructor</label>
            <input type="text" name="instructor" class="form-control" value="<%= course.getInstructor() %>" required />
        </div>
        <div class="mb-3">
            <label>Description</label>
            <textarea name="description" class="form-control" required><%= course.getDescription() %></textarea>
        </div>

        <button type="submit" class="btn btn-primary">Update Course</button>
        <a href="adminDashboard.jsp" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
