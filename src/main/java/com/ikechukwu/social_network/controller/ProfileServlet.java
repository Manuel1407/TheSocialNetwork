package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.PostDAO;
import com.ikechukwu.social_network.model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
/*
Servlet to populate user profile page
 */
@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PostDAO postDAO = new PostDAO();
        HttpSession session = request.getSession();
        try {
            ArrayList<Post> allUserPosts = postDAO.getUserPost((Integer) session.getAttribute("userid"));
            request.setAttribute("allUserPosts", allUserPosts);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
        rd.forward(request, response);
    }
}
