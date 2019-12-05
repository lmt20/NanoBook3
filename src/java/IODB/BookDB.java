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
public class BookDB {

    public static int insert(Book book) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into book (idBook, name, author, price, description, image) values(?,?,?,?,?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(book.getId()));
            ps.setString(2, book.getName());
            ps.setString(3, book.getAuthor());
            ps.setDouble(4, book.getPrice());
            ps.setString(5, book.getDescription());
            ps.setString(6, book.getImage());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Book> getListBookOfCatagory(String idCatagory) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> listBook = new ArrayList<Book>();
        String query = "SELECT book.idBook, book.name, book.author, book.price, book.description, book.image\n"
                + "From book, bookcatagory\n"
                + "where book.idBook = bookcatagory.idBook and bookcatagory.idCatagory = ?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(idCatagory));
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("idBook") + "");
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setDescription(rs.getString("description"));
                book.setImage(rs.getString("image"));
                listBook.add(book);
            }
            return listBook;
        } catch (SQLException e) {
            e.printStackTrace();
            return listBook;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Book> getListBook() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> listBook = new ArrayList<Book>();
        String query = "SELECT * FROM book;";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("idBook") + "");
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getDouble("price"));
                book.setDescription(rs.getString("description"));
                book.setImage(rs.getString("image"));
                listBook.add(book);
            }
            return listBook;
        } catch (SQLException e) {
            e.printStackTrace();
            return listBook;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static Book getBookById(String idBook) {
        ArrayList<Book> listBook = getListBook();
        for(Book book: listBook){
            if(book.getId().equals(idBook)){
                return book;
            }
        }
        return null;
    }
}
