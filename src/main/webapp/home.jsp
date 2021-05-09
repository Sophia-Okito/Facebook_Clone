<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 04/05/2021
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<!-- Icon used in this project are from https://www.iconfinder.com -->
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8" />
    <title>Facebook</title>
    <link rel="stylesheet" href="CSS/bootstrap.min.css" />
    <link rel="stylesheet" href="CSS/style.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>

    <style>
        .useridcss{
            background: #020800;
            border-radius: 100%;
            width: 35px;
            padding: 5px;
            color: #fff;
        }

        .btn a {
            color: #fff!important;
        }

        .navmenu {
            margin-top: -20px;
        }

        .avatar {
            border-radius: 100%;
            width: 150px;
            height: 150px;
        }



    </style>
</head>
<body>
<%HttpSession httpSession = request.getSession();%>


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


<section class="container mt-3">
    <div class="row">
        <div class="col-lg-3 d-md-block">
            <div class="shadow p-3 mb-5 bg-body rounded text-center mr-4">
                <img class="avatar mb-3" src="images/flower1.jpeg" alt=""/>
                <h4 class="my-1"><%=httpSession.getAttribute("username")%>  </h4>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="shadow p-3 mb-5 bg-body rounded">
                <div class="row">
                    <div class="col-lg-1">
                        <img class="post-avatar" src="" alt=""/>
                    </div>
                    <form action="/post" method="post">
                        <div class="col-lg-11">
                            <h5 style="text-align: left">Create New Post</h5>
                            <input type="text" name="message" id="" class="py-3 px-2 form-control"/>
                            <button type="submit" class="btn btn-primary mt-1 btn w-100">Post</button>
                        </div>
                    </form>
                </div>
            </div>

            <h4 class="mb-4">Posts</h4>



            <%
                String driverName = "com.mysql.jdbc.Driver";
                String connectionUrl = "jdbc:mysql://localhost:3306/";
                String dbName = "Facebook";
                String userId = "root";
                String password = "";


                try {
                    Class.forName(driverName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;

            %>

            <%
                try{
                    connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
                    statement=connection.createStatement();
                    String sql ="SELECT  posts.postID,posts.totalLikes, users.userID, message, date, deleted, users.firstName, users.lastName, message FROM posts inner join users on posts.userID = users.userID where posts.deleted='false' order by postID DESC ";
                    resultSet = statement.executeQuery(sql);
                    while(resultSet.next()){
                        String option = "Like";
            %>
            <div class="post-post shadow-none p-3 mb-5 bg-light rounded">
                <div class="row mt-3 mb-5">
                    <div class="col-lg-2">
                        <div class="">@<%=resultSet.getString("firstName") %></div>
                    </div>
                    <div class="col-lg-10">
                        <div class="post-body"><%=resultSet.getString("message") %></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-3">
                        <form action="comment.jsp" method="Post">
                            <input type="hidden" name="message" value="<%= resultSet.getString("message") %>">
                            <input type="hidden" name="post_id" value="<%= resultSet.getInt("postID") %>">
                            <input type="hidden" name="user_id" value="<%= resultSet.getInt("userID") %>">
                            <button type="submit" value="" class="btn btn-success btn-sm button">Comment </button>
                        </form>
                    </div>
                    <%
                        int userID = (int) httpSession.getAttribute("userID");
                        if (resultSet.getInt("userID") == userID) {%>
                    <div class="col-lg-3">
                        <form action="${pageContext.request.contextPath}/editPost.jsp" method="Post">
                            <input type="hidden" name="message" value="<%= resultSet.getString("message") %>">
                            <input type="hidden" name="post_id" value="<%= resultSet.getInt("postID") %>">
                            <button type="submit" value="" class="btn btn-success btn-sm button">Edit </button>
                        </form>
                    </div>
                    <%}%>
                    <div class="col-lg-3">
                        <form action="/like" method="POST">
                            <input type="hidden" name="post_id" value="<%= resultSet.getInt("postID") %>">
                            <button type="submit" value="" class="btn btn-success btn-sm button">Like <%=resultSet.getInt("totalLikes")%></button>
                        </form>
                    </div>
                    <%if (resultSet.getInt("userID") == userID) {%>
                    <div class="col-lg-3" style="color: #fff;">
                        <span class="btn btn-danger"><%=String.format("<a href='/delete?post_id=%d'>Delete</a>",resultSet.getInt("postID"))%></span>
                    </div>
                    <%}%>
                </div>
            </div>
            <%
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </div>
    </div>
</section>
<section class="container">
    <div class="row">
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                <li class="page-item active"><a class="page-link" href="/home.jsp">1</a></li>
                <li class="page-item"><a class="page-link" href="/home.jsp">Next</a></li>
            </ul>
        </nav>
    </div>
</section>
<footer class="text-center mt-5 mb-5"> - 2021</footer>
</body>
</html>
