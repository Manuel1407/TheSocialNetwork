package com.ikechukwu.social_network.dao;

import com.ikechukwu.social_network.model.Comment;
import com.ikechukwu.social_network.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO {
    public boolean insertComment(int userId, int postId, String commentBody) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comment(userid, postid, commentbody) VALUES (?,?,?);");
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, postId);
        preparedStatement.setString(3, commentBody);
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement("UPDATE post SET numofcomments = numofcomments + 1 where id = ?;");
        preparedStatement.setInt(1, postId);
        return preparedStatement.executeUpdate() > 0;
    }

    public ArrayList<Comment> getPostComment(int postId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comment WHERE postid = ? ORDER BY id DESC;");
        preparedStatement.setInt(1, postId);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Comment> array = new ArrayList<>();
        while(resultSet.next()) {
            Comment comment = new Comment();
            comment.setId(resultSet.getInt("id"));
            comment.setUserId(resultSet.getInt("userid"));
            comment.setPostId(resultSet.getInt("postid"));
            comment.setCommentBody(resultSet.getString("commentbody"));
            array.add(comment);
        }
        return array;
    }

    public ArrayList<Comment> getUserComment(int userId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comment WHERE userid = ? ORDER BY id DESC;");
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Comment> array = new ArrayList<>();
        while(resultSet.next()) {
            Comment comment = new Comment();
            comment.setId(resultSet.getInt("id"));
            comment.setUserId(resultSet.getInt("userid"));
            comment.setPostId(resultSet.getInt("postid"));
            comment.setCommentBody(resultSet.getString("commentbody"));
            array.add(comment);
        }
        return array;
    }

    public void deleteComment(int commentId) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT postid FROM comment WHERE id = ?;");
        preparedStatement.setInt(1, commentId);
        ResultSet resultSet = preparedStatement.executeQuery();

        int postId = resultSet.getInt("postid");

        preparedStatement = connection.prepareStatement("DELETE FROM comment WHERE id = ?;");
        preparedStatement.setInt(1, commentId);
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("UPDATE post SET numofcomments = numofcomments - 1 where id = ?;");
        preparedStatement.setInt(1, postId);
        preparedStatement.executeUpdate();
    }

    public String updateComment(Comment comment) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE comment SET commentbody = ? WHERE id = ?;");
            preparedStatement.setString(1, comment.getCommentBody());
            preparedStatement.setInt(2, comment.getId());
            preparedStatement.execute();
            return "Comment Update Successful.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Comment Update Failed.";
        }
    }
}
