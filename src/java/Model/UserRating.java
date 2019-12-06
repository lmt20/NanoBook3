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
public class UserRating implements Serializable {

    private Book book;
    private User user;
    private int rate;

    public UserRating() {
    }

    public UserRating(Book book, User user, int rate) {
        this.book = book;
        this.user = user;
        this.rate = rate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    
    

}
