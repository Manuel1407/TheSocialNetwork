package com.ikechukwu.social_network.dao;

import com.ikechukwu.social_network.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class LikeDAO {
    boolean updated = false;

    public boolean like(int userId, int postId) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO likes(userid, postid) " +
                "SELECT ?, ? FROM DUAL WHERE NOT EXISTS (SELECT * FROM likes WHERE userid = ? AND postid = ?);");
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, postId);
        preparedStatement.setInt(3, userId);
        preparedStatement.setInt(4, postId);

        updated = preparedStatement.executeUpdate() > 0;

        if(updated) {
            preparedStatement = connection.prepareStatement("UPDATE post SET numoflikes = numoflikes + 1 where id = ?;");
            preparedStatement.setInt(1, postId);
            updated = preparedStatement.executeUpdate() > 0;

        }
        return updated;
    }


    public boolean unLike(int userId, int postId) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM likes WHERE userid = ? AND postid = ?;");
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, postId);

        updated = preparedStatement.executeUpdate() > 0;

        if(updated) {
            preparedStatement = connection.prepareStatement("UPDATE post SET numoflikes = numoflikes - 1 where id = ?;");
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();
            updated = preparedStatement.executeUpdate() > 0;
        }
        return updated;
    }
}
