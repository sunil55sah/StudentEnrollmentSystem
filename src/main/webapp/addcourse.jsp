<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Course</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-5">
    <h2>Add New Course</h2>
    <form action="addcourse" method="post">
        <div class="mb-3">
            <label>Course Name</label>
            <input type="text" name="courseName" class="form-control" required />
        </div>
        <div class="mb-3">
            <label>Instructor</label>
            <input type="text" name="instructor" class="form-control" required />
        </div>
        <div class="mb-3">
            <label>Description</label>
            <textarea name="description" class="form-control" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add Course</button>
        <a href="adminDashboard.jsp" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
