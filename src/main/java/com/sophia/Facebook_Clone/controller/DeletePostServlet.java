package com.sophia.Facebook_Clone.controller;

import com.sophia.Facebook_Clone.DAO.PostDAO;
import com.sophia.Facebook_Clone.model.Comment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postID =Integer.parseInt(request.getParameter("post_id"));
        boolean deletePost = PostDAO.deletePost(postID);
        response.sendRedirect("home.jsp");
    }
}
