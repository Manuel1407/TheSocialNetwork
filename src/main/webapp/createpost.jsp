<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%--form for creating posts--%>
<form action="./newpost" method="post">
    <textarea rows="4" cols="100" name="messagebody" placeholder="Share your thoughts..."></textarea>
    <p><input type="submit" value="Post" style="padding: 5px 10px;"></p>
</form>
<hr>
</body>
</html>
