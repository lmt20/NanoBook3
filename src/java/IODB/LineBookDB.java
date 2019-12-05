/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IODB;

import Model.Book;
import Model.Invoice;
import Model.LineBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class LineBookDB {

    public static int insert(LineBook lineBook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "Insert into LineBook(idBook, numBook, idInvoice) values(?, ?, ?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(lineBook.getBook().getId()));
            ps.setInt(2, lineBook.getNumBook());
            ps.setInt(3, Integer.parseInt(lineBook.getIdInvoice()));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(LineBook lineBook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update linebook set numBook = ? where idBook=? and idInvoice = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(2, Integer.parseInt(lineBook.getBook().getId()));
            ps.setInt(1, lineBook.getNumBook());
            ps.setInt(3, Integer.parseInt(lineBook.getIdInvoice()));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int delete(LineBook lineBook) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "delete from linebook where idBook = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(lineBook.getBook().getId()));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<LineBook> getListLineBookOfInvoice(String idInvoice) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<LineBook> listLineBook = new ArrayList<LineBook>();
        String query = "Select * from linebook where idInvoice=?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(idInvoice));
            rs = ps.executeQuery();
            while (rs.next()) {
                LineBook lineBook = new LineBook();
                Book book = BookDB.getBookById(rs.getInt("idBook")+"");
                lineBook.setBook(book);
                lineBook.setNumBook(rs.getInt("numBook"));
                lineBook.setIdInvoice(rs.getInt("idInvoice")+"");
                listLineBook.add(lineBook);
            }
            return listLineBook;
        } catch (SQLException e) {
            e.printStackTrace();
            return listLineBook;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
