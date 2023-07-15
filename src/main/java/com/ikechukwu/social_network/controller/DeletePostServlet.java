package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.PostDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
/*
Servlet to delete post
 */
@WebServlet(name = "DeletePostServlet", value = "/deletepost")
public class DeletePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int postId = Integer.parseInt(request.getParameter("id"));
        PostDAO postDAO = new PostDAO();
        try {
            postDAO.deletePost(postId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //request dispatch to profile servlet to update posts/profile page to reflect change
        RequestDispatcher rd = request.getRequestDispatcher("./profile");
        rd.forward(request, response);
    }
}
