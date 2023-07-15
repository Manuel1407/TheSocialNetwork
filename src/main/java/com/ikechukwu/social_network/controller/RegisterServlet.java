package com.ikechukwu.social_network.controller;

import com.ikechukwu.social_network.dao.UserDAO;
import com.ikechukwu.social_network.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
/*
Servlet to handle new user registration
 */
@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");
        String gender = request.getParameter("gender");
        //"1990-01-01"
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        if (!password.equals(confirmpassword)) {
            PrintWriter writer = response.getWriter();
            response.setContentType("text/html");
            writer.println("password mismatch. check password again");
        } else {
            User user = new User(firstName, lastName, email, password, gender, birthday);
            UserDAO userDAO = new UserDAO();
            String success = "";
            success = userDAO.register(user);
            if (success.equals("Registration Successful.")) {
                HttpSession session = request.getSession();
                try {
                    session.setAttribute("userid", userDAO.getUserByEmail(email).getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                RequestDispatcher rd = request.getRequestDispatcher("./home");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/view/register.jsp");
                rd.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }
}
