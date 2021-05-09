<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 03/05/2021
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>FaceBook - Sign Up</title>
    <link rel="stylesheet" href="CSS/bootstrap.min.css">
    <link rel="stylesheet" href="CSS/style.css">
</head>
<body>

<div class="container mt-5">

    <div class="row signup">

        <div class="col-lg-7">

            <img src="" alt="">
            <h1 class="logo pb-2">JAVA Book</h1>
            <h2 class="JavaFacebook mb-5">Facebook helps you connect and share <br> with friends.</h2>
        </div>

        <div class="col-lg-5">
            <%
                if (request.getAttribute("error_message") != null) {
                    out.print(request.getAttribute("error_message").toString());
                }
            %>

            <div class="shadow p-3 mb-5 bg-body rounded">

                <h3 class="">Sign Up</h3>
                <p class="mb-4">It's quick and easy.</p>

                <form class="" action="/signUp" method="post">
                    <div class="flexs">
                        <input class="form-control mb-3" type="text" name="firstName" id="inputName" placeholder="First Name" required>
                        <input class="form-control mb-3" type="text" name="lastName" id="inputSurname" placeholder="Last Name" required>
                    </div>
                        <div>
                            <input class="form-control mb-3" type="email" name="email" id="inputEmail" placeholder="Email Address" required>
                        </div>
                        <div>
                            <input class="form-control" type="password" name="password" id="inputPassword" placeholder="New Password" required>
                        </div>
                    <div class="row mt-3">
                        <p class="mt-2">Date of birth</p>
                        <div class="col-lg">
                            <input type="date" name="dateOfBirth" class="form-control" id="dob" class="form-control" required>
                        </div>

                    </div>
                    <p class="mt-4">Gender</p>
                    <div class="row">

                        <div class="col-lg-6">
                            <div class="form-check">
                                <input class="form-check-input" value="male" type="radio" name="gender" id="male" >
                                <label class="form-check-label" for="male">
                                    Male
                                </label>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="gender" id="female" value="female">
                                <label class="form-check-label" for="female">
                                    Female
                                </label>
                            </div>
                        </div>
                    </div>


                    <div class="d-grid gap-2 mt-3">
<%--                        <a href="SignUpServlet" class="btn btn-green btn-lg">Sign Up</a>--%>
                        <button type="submit" class="btn btn-success">sign up</button>
                        <p class="text-center mt-3">Have an Account?</p>
                        <div class="divider"></div>
                        <a href= "/index.jsp" class="btn btn-primary btn-lg mt-3 mb-3">
                            Login </a>

                    </div>
                </form>

            </div>

        </div>

    </div>

</div>

</body>
</html>

