<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Student Course Enrollment</title>
    <link rel="stylesheet" href="css/login.css"> <!-- Reuse login.css -->
</head>
<body>
    <h2>Student Registration</h2>
    <form method="post" action="register">
        <label>Name:</label>
        <input type="text" name="name" required><br><br>

        <label>Email:</label>
        <input type="email" name="email" required><br><br>

        <label>Password:</label>
        <input type="password" name="password" required><br><br>

        <input type="submit" value="Register"><br><br>

        <a href="login.jsp">Already have an account?</a>
    </form>
</body>
</html>
