package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.PostDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
Servlet to edit post
 */
@WebServlet(name = "EditPostServlet", value = "/editpost")
public class EditPostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String messageBody = request.getParameter("messagebody");
        int postId = Integer.parseInt(request.getParameter("postid"));
        PostDAO postDAO = new PostDAO();
        postDAO.updatePost(messageBody, postId);

        //send to profile servlet to update posts/profile page to reflect change
        response.sendRedirect("./profile");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
