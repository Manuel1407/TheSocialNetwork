package com.ikechukwu.social_network.dao;

import com.ikechukwu.social_network.model.User;
import com.ikechukwu.social_network.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
UserDAO class contains all methods for querying and performing CRUD operations on our site and our user table in the database
 */
public class UserDAO {

    //login user
    public boolean login(String email, String password) {
        boolean result = false;
        try {
            //get connection instance from DBConnection
            Connection connection = DBConnection.getInstance().getConnection();

            //query the users table in the database with the email and password parameters passed to the method
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            //if email and password provided match the values gotten from the database then login is possible and successful

            if(resultSet.next() && resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
                    result = true;
            }
        } catch (SQLException e) {
            result = false;
        }
        return result;

    }

    //register user
    public String register(User user) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            /*
            prepared statement to insert new row into our user table in database with corresponding values gotten from user argument
             If parameters are valid user will be successfully created
             */
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(firstname, lastname, email, password, gender, birthday) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setDate(6, user.getBirthday());
            preparedStatement.execute();
            return "Registration Successful.";

        } catch (SQLException e) {
            e.printStackTrace(); //user creation/registration fails if invalid paramaters are passed
            return "Registration Failed.";
        }
    }
/*
1
2
3
4
5
6
7
 */

    //retrieve user by id
    public User getUserById(int id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        /*
        query to retrieve user from table with id parameter passed to method
         */

        //1345 OR 1=1
        //SELECT * FROM user WHERE id = 1345;
        //DROP TABLE USER

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
        preparedStatement.setInt(1, id);
        return getUser(preparedStatement);
    }

    //get user by user email
    public User getUserByEmail(String email) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        /*
        query to retrieve user from table with email parameter passed to method
         */
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
        preparedStatement.setString(1, email);
        return getUser(preparedStatement);
    }

    //preparedstatement for user query
    private User getUser(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = new User();
        if(resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("firstname"));
            user.setLastName(resultSet.getString("lastname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setGender(resultSet.getString("gender"));
            user.setBirthday(resultSet.getDate("birthday"));
        }
        return user;
    }

    //delete user from database with id matching id paramater
    public boolean deleteUser(int id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?;");
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate() > 0;
    }
}
