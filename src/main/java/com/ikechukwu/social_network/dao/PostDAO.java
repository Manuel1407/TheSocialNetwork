package com.ikechukwu.social_network.dao;

import model.Post;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostDAO {
    public boolean createPost(int userId, String messageBody) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO post(userid, messagebody) VALUES (?,?);")) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, messageBody);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public ArrayList<Post> getAllPost() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM post ORDER BY id DESC;");
        return getPosts(preparedStatement);
    }

    public ArrayList<Post> getUserPost(int userId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM post WHERE userid = ? ORDER BY id DESC;");
        preparedStatement.setInt(1, userId);
        return getPosts(preparedStatement);
    }

    private ArrayList<Post> getPosts(final PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Post> array = new ArrayList<>();
        while(resultSet.next()) {
            Post post = new Post();
            post.setId(resultSet.getInt("id"));
            post.setUserId(resultSet.getInt("userid"));
            post.setMessageBody(resultSet.getString("messagebody"));
            post.setNumOfComments(resultSet.getInt("numofcomments"));
            post.setNumOfLikes(resultSet.getInt("numoflikes"));

            array.add(post);
        }
        return array;
    }

    public boolean deletePost(int postId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM post WHERE id = ?;");
        preparedStatement.setInt(1, postId);
        return preparedStatement.executeUpdate() > 0;

    }

    public Post getPost(String body) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM post WHERE messagebody = ?;");
        preparedStatement.setString(1, body);
        ResultSet resultSet = preparedStatement.executeQuery();
        Post post = new Post();
        if(resultSet.next()) {
            post.setId(resultSet.getInt("id"));
            post.setUserId(resultSet.getInt("userid"));
            post.setMessageBody(resultSet.getString("messagebody"));
            post.setNumOfComments(resultSet.getInt("numofcomments"));
            post.setNumOfLikes(resultSet.getInt("numoflikes"));
        }
        return post;
    }

    public String updatePost(String messageBody, int postId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE post SET messagebody = ? WHERE id = ?;");
            preparedStatement.setString(1, messageBody);
            preparedStatement.setInt(2, postId);
            preparedStatement.execute();
            return "Post Update Successful.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Post Update Failed.";
        }
    }
}
