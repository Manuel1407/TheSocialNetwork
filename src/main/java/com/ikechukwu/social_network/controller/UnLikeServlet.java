package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.LikeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UnLikeServlet", value = "/unlikepost")
public class UnLikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("userid");
        int postId = Integer.parseInt(request.getParameter("postid"));

        LikeDAO likeDAO = new LikeDAO();
        try {
            likeDAO.unLike(userId, postId);
            RequestDispatcher rd = request.getRequestDispatcher("./home");
            rd.forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
