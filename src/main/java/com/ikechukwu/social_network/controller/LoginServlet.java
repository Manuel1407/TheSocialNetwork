package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.UserDAO;

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
Servlet to process user login
 */
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        Boolean success = false;
        success = userDAO.login(email, password);
        if (success) {
            HttpSession session = request.getSession();
            try {
                session.setAttribute("userid", userDAO.getUserByEmail(email).getId());
                session.setAttribute("firstname", userDAO.getUserByEmail(email).getFirstName());
                session.setAttribute("lastname", userDAO.getUserByEmail(email).getLastName());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            //request dispatch to home servlet to populate home page if login is successful
            RequestDispatcher rd = request.getRequestDispatcher("./home");
            rd.forward(request, response);

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
