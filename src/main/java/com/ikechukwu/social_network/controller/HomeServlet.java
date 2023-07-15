package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.PostDAO;
import com.ikechukwu.social_network.model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/*
Servlet to populate home page feed
 */
@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostDAO postDAO = new PostDAO();
        try {
            ArrayList<Post> allPosts = postDAO.getAllPost();
            request.setAttribute("allPosts", allPosts);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PostDAO postDAO = new PostDAO();
        try {
            ArrayList<Post> allPosts = postDAO.getAllPost();
            request.setAttribute("allPosts", allPosts);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.forward(request, response);
    }
}
