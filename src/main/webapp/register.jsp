<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register | The Social Network</title>
</head>
<body>
<jsp:include page="header.jsp" />
<%--register page contains form to create new user. If user details provided are valid then a new user is created--%>
<h3>Create New Social Account:</h3>
<form align="center" action="./register" method="post">
    <p><input type="text" name="firstname" placeholder="First Name" required>
        <input type="text" name="lastname" placeholder="Last Name" required></p>

    <p><input type="text" name="email" placeholder="Email" required></p>
    <p><input type="password" name="password" placeholder="Password" required></p>
    <p><input type="password" name="confirmpassword" placeholder="Re-enter Password" required></p>
    <p>
        Gender:
        <input type="radio" name="gender" value="M" checked>Male
        <input type="radio" name="gender" value="F">Female
    </p>
    <p>Date of Birth: <input type="date" name="birthday" required></p>
    <p><input type="submit" value="Register"/></p>
</form>
</body>
</html>
