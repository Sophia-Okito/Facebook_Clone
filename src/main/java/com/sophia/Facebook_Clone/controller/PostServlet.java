package com.sophia.Facebook_Clone.controller;

import com.sophia.Facebook_Clone.DAO.PostDAO;
import com.sophia.Facebook_Clone.model.Post;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PostServlet", value = "/post")
public class PostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = request.getParameter("message");
        HttpSession session =  request.getSession();
        int userID = (int) session.getAttribute("userID");
        Post post = new Post(userID, message);
        boolean createPost = PostDAO.createPost(post);
        if(createPost){
            System.out.println("Post Created");
        } else {
            System.out.println("Unable to create your post");
        }
        response.sendRedirect("home.jsp");
    }
}
