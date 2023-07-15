package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.CommentDAO;

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
Servlet to insert new comment on a post
 */
@WebServlet(name = "CommentServlet", value = "/newcomment")
public class CommentServlet extends HttpServlet {

    CommentDAO commentDAO;

    public CommentServlet(final CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commentBody = request.getParameter("commentbody");
        //"2"
        int postId = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();

        try {
            commentDAO.insertComment((Integer) session.getAttribute("userid"), postId, commentBody);
            /*
            request dispatch to the home servlet to update the post/homepage to reflect comment
            that was added
             */
            RequestDispatcher rd = request.getRequestDispatcher("./home");
            rd.forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
