<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 07/05/2021
  Time: 06:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit Comment</title>
</head>
<body>
<% String id= request.getParameter("id");
    String comment = request.getParameter("comment");
    String postID = request.getParameter("post_id");
%>
<div>
    <form action="${pageContext.request.contextPath}/editComment" method="POST">
        <textarea class="form-control" name="comment" ><%=comment%></textarea>
        <input type="hidden" name="id" value=<%=id%>>
        <input type="hidden" name="post_id" value=<%=postID%>>
        <button type="submit" class="">Update</button>
    </form>
</div>
</body>
</html>
