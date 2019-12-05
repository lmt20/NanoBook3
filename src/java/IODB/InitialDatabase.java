/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IODB;

import Model.Book;
import Model.Catagory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class InitialDatabase {

    public static void createTable(String query) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.executeUpdate();

        } catch (Exception e) {
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void createAllTable() {
        createTable(Utils.dropAllTable);
        createTable(Utils.createTableBookCatagoryQuery);
        createTable(Utils.createTableBookQuery);
        createTable(Utils.createTableCatagoryQuery);
        createTable(Utils.createTableInvoiceQuery);
        createTable(Utils.createTableLineBookQuery);
        createTable(Utils.createTableUserCommentQuery);
        createTable(Utils.createTableUserQuery);
        createTable(Utils.createTableUserRatingQuery);
    }

    public static String pathDirData = "C:\\Users\\Asus\\Desktop\\Data\\Data_NanoBook";

    public static void initialCatagoryDatabase() {
        ArrayList<Catagory> listCatagory = Utils.initialCatagorys();
        for (Catagory catagory : listCatagory) {
            CatagoryDB.insert(catagory);
        }
    }

    public static void initialBookDatabase() throws IOException {
        ArrayList<Book> listBook = Utils.initialBooks();
        for (Book book : listBook) {
            BookDB.insert(book);
        }
    }

    public static void initialBookCatagoryDatabase() throws IOException {
        ArrayList<Book> listBook = Utils.initialBooks();
        for (Book book : listBook) {
            for (Catagory catagory : book.getCatagory()) {
                BookCatagoryDB.insert(book.getId(), catagory.getId());
            }
        }
    }

}
