/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IODB;

import Model.UserRating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class UserRatingDB {

    public static int insert(UserRating userRating) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "Insert into userrating(idBook, idUser, rate) values(?, ?, ?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(userRating.getIdBook()));
            ps.setInt(2, Integer.parseInt(userRating.getIdUser()));
            ps.setInt(3, userRating.getRate());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(UserRating userRating) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update userrating set rate = ? where idBook=? and idUser = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(2, Integer.parseInt(userRating.getIdBook()));
            ps.setInt(3, Integer.parseInt(userRating.getIdUser()));
            ps.setInt(1, userRating.getRate());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<UserRating> getListUserRating(String idBook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<UserRating> listUserRating = new ArrayList<UserRating>();
        String query = "SELECT * FROM userrating WHERE idBook=?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(idBook));
            rs = ps.executeQuery();

            while (rs.next()) {
                UserRating userRating = new UserRating();
                userRating.setIdBook(rs.getInt("idBook")+"");
                userRating.setIdUser(rs.getInt("idUser")+"");
                userRating.setRate(rs.getInt("rate"));
                listUserRating.add(userRating);
            }
            return listUserRating;
        } catch (SQLException e) {
            e.printStackTrace();
            return listUserRating;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
