<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 06/05/2021
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Post</title>
</head>
<body>
<% String post_id= request.getParameter("post_id");
    String message = request.getParameter("message");
%>
<div>
    <form action="${pageContext.request.contextPath}/editPost" method="POST">
        <textarea class="form-control" name="message" ><%=message%></textarea>
        <input type="hidden" name="post_id" value=<%=post_id%>>
        <button type="submit" class="">Update</button>
    </form>
</div>
</body>
</html>
