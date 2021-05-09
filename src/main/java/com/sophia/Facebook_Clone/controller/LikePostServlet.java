package com.sophia.Facebook_Clone.controller;

import com.sophia.Facebook_Clone.DAO.PostDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LikeServlet", value = "/like")
public class LikePostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int postID = Integer.parseInt(request.getParameter("post_id"));
        System.out.println(postID);
        HttpSession session =  request.getSession();
        int userID = (int) session.getAttribute("userID");
        System.out.println(userID);
        try {
            PostDAO.likeOrUnlikePost(postID, userID);
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        } finally {
            response.sendRedirect("home.jsp");
        }

    }
}
