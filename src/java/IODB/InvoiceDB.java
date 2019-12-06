/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IODB;

import Model.Cart;
import Model.Invoice;
import Model.LineBook;
import Model.UserReceive;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class InvoiceDB {

    public static int insert(Invoice invoice) {
        ArrayList<LineBook> listLineBook = invoice.getCart().getListLine();
        for(LineBook lineBook: listLineBook){
            lineBook.setIdInvoice(invoice.getId());
            LineBookDB.insert(lineBook);
        }
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into invoice (idInvoice, paymentMethod, transportMethod, discount"
                + ", dayPayment, nameUserReceive, numberPhoneUserReceive, addressUserReceive, idUser)"
                + " values(?,?,?,?,?,?,?,?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(invoice.getId()));
            ps.setString(2, invoice.getPaymentMethod());
            ps.setString(3, invoice.getTransportMethod());
            ps.setDouble(4, invoice.getDiscount());
            ps.setDate(5, (Date) invoice.getDayPayment());
            ps.setString(6, invoice.getUserReceive().getName());
            ps.setString(7, invoice.getUserReceive().getNumberPhone());
            ps.setString(8, invoice.getUserReceive().getAddress());
            ps.setInt(9, Integer.parseInt(invoice.getIdUser()));
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static Invoice getInvoiceById(String idInvoice) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM Invoice WHERE idInvoice=?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(idInvoice));
            rs = ps.executeQuery();
            Invoice invoice = null;
            if (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt("idInvoice") + "");
                ArrayList <LineBook> listLine = LineBookDB.getListLineBookOfInvoice(rs.getInt("idInvoice") + "");
                Cart cart = new Cart(listLine);
                invoice.setCart(cart);
                invoice.setPaymentMethod(rs.getString("paymentMethod"));
                invoice.setTransportMethod(rs.getString("transportMethod"));
                invoice.setDiscount(rs.getDouble("discount"));
                invoice.setDayPayment(rs.getDate("dayPayment"));
                UserReceive userReceive = new UserReceive();
                userReceive.setName(rs.getString("nameUserReceive"));
                userReceive.setNumberPhone(rs.getString("numberPhoneUserReceive"));
                userReceive.setAddress(rs.getString("addressUserReceive"));
                invoice.setUserReceive(userReceive);
                invoice.setIdUser(rs.getInt("idUser") + "");
            }
            return invoice;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static ArrayList<Invoice> getListInvoiceOfUser(String idUser) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Invoice> listInvoice = new ArrayList<Invoice>();
        String query = "SELECT * FROM Invoice WHERE idUser=?;";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(idUser));
            rs = ps.executeQuery();

            while (rs.next()) {
                Invoice invoice = getInvoiceById(rs.getInt("idInvoice") + "");
                listInvoice.add(0,invoice);
            }
            return listInvoice;
        } catch (SQLException e) {
            e.printStackTrace();
            return listInvoice;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
