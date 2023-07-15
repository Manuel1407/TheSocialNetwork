<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h2>Welcome to your profile <c:out value="${firstName}"></c:out> <c:out value="${lastName}"></c:out></h2>
<%--from the profile page, user can navigate to the homepage, logout, or delete his/her account --%>
<div><a href="./home"><b>Home</b></a></div>
<div><a href="./logout"><b>Logout</b></a></div>
<div><a href="./deleteaccount"><b>Delete account</b></a></div>

<jsp:include page="createpost.jsp" />
<%--user profile consists of all posts by the individual user with option to edit and delete post--%>
<c:forEach var="post" items="${allUserPosts}">
    <div>
        <c:out value="${post.messageBody}"></c:out>
        <p><c:out value="${post.numOfComments}"></c:out> comments <c:out value="${post.numOfLikes}"></c:out> likes</p>

        <form action="./editpost?id=${post.id}" method="post">
            <textarea rows="1" cols="100" name="messagebody" placeholder="Edit post..."></textarea>

            <p><input type="submit" value="Edit" style="padding: 5px 10px;"></p>
        </form>

         <a href="./deletepost?id=${post.id}">Delete Post</a>
        <br>
    </div>
</c:forEach>

</body>
</html>
