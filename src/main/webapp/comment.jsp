<%@ page import="java.sql.*" %>
<%@ page import="com.sophia.Facebook_Clone.DAO.DBConnection" %><%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 05/05/2021
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Comment</title>
    <link rel="stylesheet" href="CSS/bootstrap.min.css" />
    <link rel="stylesheet" href="CSS/style.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<style>

    body {
        margin-right: 20%;
        margin-left: 20%;
    }
    .container {
        margin-top: 10%;
    }
     .btn {
         background-color: #2196F3;
         color: white;
         padding: 16px;
         font-size: 16px;
         border: none;
         outline: none;
     }
     .btn-delete:hover{
         background: tomato;
     }

    .dropdown {
        position: absolute;
        display: inline-block;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #f1f1f1;
        min-width: 160px;
        z-index: 1;
    }

    .dropdown-content a {
        color: black;
        padding: 12px 16px;
        text-decoration: none;
        display: block;
    }

    .dropdown-content a:hover {background-color: #ddd}

    .dropdown:hover .dropdown-content {
        display: block;
    }

    .btn:hover, .dropdown:hover .btn {
        background-color: #0b7dda;
    }
</style>
<body>



<%HttpSession httpSession = request.getSession();%>
<% String post_id= request.getParameter("post_id");
    String userID = request.getParameter("user_id");
    String message = request.getParameter("message");
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="home.jsp"><b class="javabook">Facebook</b></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <form class="d-flex w-75">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>

            <ul class="navbar-nav  mb-2 mb-lg-0 ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href=""><%=httpSession.getAttribute("username")%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/index.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="container">
  <div class="shadow p-3 mb-5 bg-white rounded text-center">
      <h4>Comments</h4>
      <div><%=userID%></div>
      <div><%=message%></div>
  </div>
    <form action="/comment" method="POST">
        <input type="text" class="form-control" name="comment">
        <input type="hidden" name="post_id" value=<%=post_id%>>
        <button type="submit" class="btn btn-primary mt-3">Comment</button>
    </form>
     <div class="container shadow p-3 mb-5 bg-white rounded">
         <%
             try{
                 Connection connect =  DBConnection.getConnection();
                 String sql2 ="SELECT * FROM comments WHERE  postID ="+ post_id;

                 PreparedStatement preparedStatement = connect.prepareStatement(sql2);
                 ResultSet resultSet = preparedStatement.executeQuery(sql2);
                 while(resultSet.next()){
         %>
         <div class="shadow p-3 mb-5 bg-white rounded mb-5">
             <div><%=resultSet.getString("userID") %></div>
             <div><%=resultSet.getString("comment") %></div>


             <div class="btn-group btn-group-sm" role="group">
                 <form action="/likeComment" method="POST">
                     <input type="hidden" name="id" value="<%= resultSet.getInt("id") %>">
                     <button type="submit" value="" class="btn btn-success btn-sm button">Like <%=resultSet.getInt("totalLikes")%></button>
                 </form>
                 <%  int id = (int) httpSession.getAttribute("userID");
                     if (resultSet.getInt("userID") == id) {%>
                 <form action="${pageContext.request.contextPath}/editComment.jsp" method="Post">
                     <input type="hidden" name="comment" value="<%= resultSet.getString("comment") %>">
                     <input type="hidden" name="id" value="<%= resultSet.getInt("id") %>">
                     <input type="hidden" name="post_id" value="<%= resultSet.getInt("postID") %>">
                     <button type="submit" value="" class="btn btn-success btn-sm button">Edit </button>
                 </form>
                 <form action="${pageContext.request.contextPath}/deleteComment" method="Get">
                     <input type="hidden" name="id" value="<%= resultSet.getInt("id") %>">
                     <button type="submit" value="" class="btn btn-success btn-sm button">Delete </button>
                 </form>
                 <%}%>
             </div>
                 </div>
             </div>
         </div>
         <%}

             } catch (Exception e) {
                 e.printStackTrace();
             }
         %>
     </div>
</div>
<div>
</div>
<footer class="text-center mb-5">
    <p>WEBSITE</p>
</footer>
</body>
</html>
