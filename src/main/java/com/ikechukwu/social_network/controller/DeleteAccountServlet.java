package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
Servlet to delete user account
 */
@WebServlet(name = "DeleteAccountServlet", value = "/deleteaccount")
public class DeleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
           new UserDAO().deleteUser((Integer) request.getSession().getAttribute("userid"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //return to login page after user is deleted
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }
}
