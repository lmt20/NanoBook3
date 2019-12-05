/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IODB;

import Model.UserComment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class UserCommentDB {

    public static int insert(UserComment userComment) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "Insert into UserComment(idBook, idUser, comment) values(?, ?, ?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(userComment.getIdBook()));
            ps.setInt(2, Integer.parseInt(userComment.getIdUser()));
            ps.setString(3, userComment.getComment());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static int update(UserComment userComment) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update usercomment set comment = ? where idBook=? and idUser = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(2, Integer.parseInt(userComment.getIdBook()));
            ps.setInt(3, Integer.parseInt(userComment.getIdUser()));
            ps.setString(1, userComment.getComment());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<UserComment> getListUserComment(String idBook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<UserComment> listUserComment = new ArrayList<UserComment>();
        String query = "SELECT * FROM usercomment WHERE idBook=?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(idBook));
            rs = ps.executeQuery();

            while (rs.next()) {
                UserComment userComment = new UserComment();
                userComment.setIdBook(rs.getInt("idBook")+"");
                userComment.setIdUser(rs.getInt("idUser")+"");
                userComment.setComment(rs.getString("comment"));
                listUserComment.add(userComment);
            }
            return listUserComment;
        } catch (SQLException e) {
            e.printStackTrace();
            return listUserComment;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
