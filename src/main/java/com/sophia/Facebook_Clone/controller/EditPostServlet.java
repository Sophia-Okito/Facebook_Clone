package com.sophia.Facebook_Clone.controller;

import com.sophia.Facebook_Clone.DAO.PostDAO;
import com.sophia.Facebook_Clone.model.Post;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditServlet", value = "/editPost")
public class EditPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session =  request.getSession();
        int userID = (int) session.getAttribute("userID");
        String message = request.getParameter("message");
        System.out.println(message);
        int postID = Integer.parseInt(request.getParameter("post_id"));
        System.out.println(postID);
        Post post = new Post(postID, userID, message);
        boolean updatePost = PostDAO.updatePost(post);
        if (updatePost){
            System.out.println("Post Updated");
        } else {
            System.out.println("Unable to update your post");
        }
        response.sendRedirect("home.jsp");
    }
}
