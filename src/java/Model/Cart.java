/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Cart implements Serializable {

    private ArrayList<LineBook> listLine;
    private double totalPrice;

    public Cart() {
        listLine = new ArrayList<LineBook>();
    }

    public Cart(ArrayList<LineBook> listLine) {
        this.listLine = listLine;
    }

    public double getTotalPrice() {
        totalPrice = 0;
        for (LineBook lBook : listLine) {
            totalPrice += lBook.getLineBookPrice();
        }
        return totalPrice;
    }

    public void setTotalPrice() {
        totalPrice = 0;
        for (LineBook lBook : listLine) {
            totalPrice += lBook.getLineBookPrice();
        }
    }

    public ArrayList<LineBook> getListLine() {
        return listLine;
    }

    public void setListLine(ArrayList<LineBook> listLine) {
        this.listLine = listLine;
    }

    public void addBookToCart(Book book, int numBook) {
        for (LineBook lBook : listLine) {
            if (book.getId().equals(lBook.getBook().getId())) {
                lBook.setNumBook(numBook);
                return;
            }
        }
        LineBook lineBook = new LineBook(book, numBook);
        listLine.add(lineBook);
    }

    public void deleteBookInCart(String idBook) {
//        for (LineBook lBook : listLine) {
//            if (idBook.equals(lBook.getBook().getId())) {
//                listLine.remove(lBook);
//            }
//        }
        for (int i = 0; i < listLine.size(); i++) {
            LineBook lBook = listLine.get(i);
            if (idBook.equals(lBook.getBook().getId())) {
                listLine.remove(lBook);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        System.out.println(cart.getListLine().size());
        Book book = new Book();
        int numBook = 4;
        cart.addBookToCart(book, numBook);
        System.out.println(cart.getListLine().size());
    }

}
