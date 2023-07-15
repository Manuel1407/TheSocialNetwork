<%@ page import="com.ikechukwu.social_network.dao.PostDAO" %>
<%@ page import="com.ikechukwu.social_network.model.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
<head>
    <title>The Social Network</title>
</head>
<body>
<h2>This is the Social Network</h2>
<%
    if(!session.getAttribute("managerAttribute").equals("whatever"))
		response.sendRedirect("/login.jsp");
%>
<div><a href="./profile"><b>Profile</b></a></div>
<div><a href="./logout"><b>Logout</b></a></div>
<br>
<jsp:include page="createpost.jsp" />


<form action="./newpost" method="post">
    <textarea rows="4" cols="100" name="messagebody" placeholder="Share your thoughts..."></textarea>
    <p><input type="submit" value="Post" style="padding: 5px 10px;"></p>
</form>

<%--site home page is a list of all users' posts. Posts are generated dynamically with corresponding fields for comments and likes--%>
<c:forEach var="post" items="${allPosts}">
    <div>
        <p><c:out value="${post.messageBody}"></c:out></p>

        <p><c:out value="${post.numOfComments}"></c:out> comments <c:out value="${post.numOfLikes}"></c:out> likes</p>

        <form action="./newcomment?post_id=${post.id}" method="post">
            <textarea rows="2" cols="100" name="commentbody" placeholder="Add comment..."></textarea>
            <p><input type="submit" value="Comment" style="padding: 5px 10px;"></p>
        </form>
        <p><a href="./likepost?id=${post.id}">Like</a> <a href="./unlikepost?id=${post.id}">Unlike</a></p>
    </div>
</c:forEach>

</body>
</html>
