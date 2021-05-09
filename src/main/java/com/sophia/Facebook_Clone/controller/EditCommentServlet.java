package com.sophia.Facebook_Clone.controller;

import com.sophia.Facebook_Clone.DAO.PostDAO;
import com.sophia.Facebook_Clone.model.Comment;
import com.sophia.Facebook_Clone.model.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditCommentServlet", value = "/editComment")
public class EditCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        int userID = (int) session.getAttribute("userID");
        String comment = request.getParameter("comment");
        int id = Integer.parseInt(request.getParameter("id"));
        int postID = Integer.parseInt(request.getParameter("post_id"));
        Comment comment1 = new Comment(id, postID, userID, comment);
        boolean updateComment = PostDAO.updateComment(comment1);
        response.sendRedirect("home.jsp");
    }
}
