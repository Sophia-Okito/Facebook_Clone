package com.sophia.Facebook_Clone.controller;

import com.sophia.Facebook_Clone.DAO.UserDAO;
import com.sophia.Facebook_Clone.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUpServlet", value = "/signUp")
public class SignUpServlet extends HttpServlet {
    UserDAO userDAO =  new UserDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request,response);
    }

    // method to handle the signup route post request
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean createUser;
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String gender = request.getParameter("gender");

        try {
            User newUser = new User(firstName, lastName, email, password, gender, dateOfBirth);
            createUser = userDAO.createNewUser(newUser);
            if (createUser) {
                HttpSession session = request.getSession();
                User getUserDetails = userDAO.getUserByEmail(email);
                // store the object in the request session
                session.setAttribute("userID", getUserDetails.getUserID());
                session.setAttribute("userFirstName", getUserDetails.getFirstName());

                response.sendRedirect("index.jsp");
            } else {
                out.println("A User With That Email Already Exist");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                request.setAttribute("error_message", "A User With That Email Already Exist");

                // forward the request to the
                requestDispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();

            // forward the request to the
        }
    }
}
