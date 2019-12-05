/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class LineBook implements Serializable{
    private Book book;
    private int numBook;
    private double lineBookPrice;
    private String idInvoice;
    
    public LineBook() {
    }

    public LineBook(Book book, int numBook) {
        this.book = book;
        this.numBook = numBook;
        setLineBookPrice();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getNumBook() {
        return numBook;
    }

    public void setNumBook(int numBook) {
        this.numBook = numBook;
        setLineBookPrice();
    }

    public String getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(String idInvoice) {
        this.idInvoice = idInvoice;
    }
    
    public double getLineBookPrice() {
        return lineBookPrice;
    }

    public void setLineBookPrice() {
        this.lineBookPrice = numBook * book.getPrice();
    }
    
    
    
    
}
