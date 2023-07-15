<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login | The Social Network</title>
</head>
<body>
<jsp:include page="header.jsp" />
<%--login page with form for email and password input. If email and password pair match database user is logged in--%>
<form align="center" action="./login" method="post">
    <p><input type="text" name="email" placeholder="Email" /></p>
    <p><input type="text" name="password" placeholder="Password"/></p>
    <p><input type="submit" value="Login" /></p>
</form>

<a align="center" href="register.jsp">Create New Account</a>
</body>
</html>
