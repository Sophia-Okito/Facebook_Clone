package com.sophia.Facebook_Clone.controller;

import com.sophia.Facebook_Clone.DAO.UserDAO;
import com.sophia.Facebook_Clone.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogInServlet", value = "/login")
public class LogInServlet extends HttpServlet {
    // handles the login route get request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    // handles the login route post request
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (Validation.checkUser(email, password)) {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByEmail(email);
            request.getSession().invalidate();
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(50 * 60);
            session.setAttribute("userID", user.getUserID());
            session.setAttribute("username", user.getFirstName() + " " + user.getLastName());
            RequestDispatcher rs = request.getRequestDispatcher("/home.jsp");
            rs.forward(request, response);
        }
        else {
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("error_message", "Username or Password incorrect");
            rs.forward(request, response);
        }
    }

}
