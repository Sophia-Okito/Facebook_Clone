package com.sophia.Facebook_Clone.controller;

import com.sophia.Facebook_Clone.DAO.PostDAO;
import com.sophia.Facebook_Clone.model.Comment;
import com.sophia.Facebook_Clone.model.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CommentServlet", value = "/comment")
public class CommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        String message = request.getParameter("comment");
        int userID = (int) session.getAttribute("userID");
        int postID =Integer.parseInt(request.getParameter("post_id"));
        Comment comment = new Comment(postID, userID, message);
        boolean createComment = PostDAO.createComment(comment);
        if(createComment){
            System.out.println("Comment Created");
            response.sendRedirect("home.jsp");
        } else {
            System.out.println("Unable to create your Comment");
        }
    }
}
