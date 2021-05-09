<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Facebook - Welcome</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>
<div class="container mt-5">
    <div class="row welcome">
        <div class="col-lg-7">
            <img src="" alt="">
            <h1 class="logo pb-2">Facebook</h1>
            <h2 class="JavaFacebook mb-5">Facebook helps you connect and share <br> with friends.</h2>
        </div>
        <div class="col-lg-5">
            <%
                if (request.getAttribute("error_message") != null) {
                    out.print(request.getAttribute("error_message").toString());
                }
            %>
            <div class="shadow p-3 mb-5 bg-body rounded">
                <form class="" action="/login" method="Post">
                    <input class="form-control mb-3" type="text" name="email" value="" placeholder="Email Address">
                    <input class="form-control" type="password" name="password" value="" placeholder="Password">
                    <div class="d-grid gap-2 mt-3">
                        <button type="submit" class="btn btn-primary btn-lg">Log In</button>
                        <p class="text-center mt-2"><a href="#">Forgotten password?</a></p>
                        <div class="divider"></div>
                        <a href ="signup.jsp" class="btn btn-green btn-lg mt-3 mb-3">
                            Create New Account
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>