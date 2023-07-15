package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.PostDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
/*
Servlet to create new post
 */
@WebServlet(name = "CreatePostServlet", value = "/newpost")
public class CreatePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String messageBody = request.getParameter("messagebody");

        HttpSession session = request.getSession();
        PostDAO postDAO = new PostDAO();

        try {
            postDAO.createPost((Integer) session.getAttribute("userid"), messageBody);
            RequestDispatcher rd = request.getRequestDispatcher("./home");
            rd.forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
