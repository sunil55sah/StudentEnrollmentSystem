<%@ page import="com.enrollment.model.Student, java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
    Student editStudent = (Student) request.getAttribute("student");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Students</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-5">
    <h2>Student Management</h2>

    <form action="student" method="post" class="mb-4">
        <input type="hidden" name="action" value="<%= (editStudent != null) ? "update" : "add" %>"/>
        <% if (editStudent != null) { %>
            <input type="hidden" name="id" value="<%= editStudent.getId() %>"/>
        <% } %>
        <div class="mb-3">
            <label>Username</label>
            <input type="text" name="username" class="form-control" required value="<%= (editStudent != null) ? editStudent.getUsername() : "" %>"/>
        </div>
        <div class="mb-3">
            <label>Password</label>
            <input type="password" name="password" class="form-control" required value="<%= (editStudent != null) ? editStudent.getPassword() : "" %>"/>
        </div>
        <div class="mb-3">
            <label>First Name</label>
            <input type="text" name="first_name" class="form-control" required value="<%= (editStudent != null) ? editStudent.getFirstName() : "" %>"/>
        </div>
        <div class="mb-3">
            <label>Last Name</label>
            <input type="text" name="last_name" class="form-control" required value="<%= (editStudent != null) ? editStudent.getLastName() : "" %>"/>
        </div>
        <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required value="<%= (editStudent != null) ? editStudent.getEmail() : "" %>"/>
        </div>
        <button type="submit" class="btn btn-success">
            <%= (editStudent != null) ? "Update Student" : "Add Student" %>
        </button>
    </form>

    <h4>All Students</h4>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Student s : students) { %>
        <tr>
            <td><%= s.getUsername() %></td>
            <td><%= s.getFirstName() %></td>
            <td><%= s.getLastName() %></td>
            <td><%= s.getEmail() %></td>
            <td>
                <a href="student?action=edit&id=<%= s.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                <form action="student" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="<%= s.getId() %>"/>
                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Delete student?');">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
