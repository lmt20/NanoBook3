/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IODB;

import Model.Book;
import Model.Catagory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class CatagoryDB {

    public static int insert(Catagory catagory) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into catagory (idCatagory, name) values(?, ?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(catagory.getId()));
            ps.setString(2, catagory.getName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Catagory> getListCatagory() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Catagory> listCatagory = new ArrayList<Catagory>();
        String query = "SELECT * FROM catagory;";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Catagory catagory = new Catagory();
                catagory.setId(rs.getInt("idCatagory") + "");
                catagory.setName(rs.getString("name"));
                listCatagory.add(catagory);
            }
            return listCatagory;
        } catch (SQLException e) {
            e.printStackTrace();
            return listCatagory;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Catagory> getListCatagoryOfBook(String idBook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Catagory> listCatagory = new ArrayList<Catagory>();
        String query = "SELECT catagory.idCatagory, catagory.name from catagory,"
                + " bookcatagory where bookcatagory.idBook =? and catagory.idCatagory = bookcatagory.idCatagory;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(idBook));
            rs = ps.executeQuery();
            while (rs.next()) {
                Catagory catagory = new Catagory();
                catagory.setId(rs.getInt("idCatagory") + "");
                catagory.setName(rs.getString("name"));
                listCatagory.add(catagory);
            }
            return listCatagory;
        } catch (SQLException e) {
            e.printStackTrace();
            return listCatagory;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
