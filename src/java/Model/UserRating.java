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
public class UserRating implements Serializable{
    private String idBook;
    private String idUser;
    private int rate;

    public UserRating() {
    }

    public UserRating(String idBook, String idUser, int rate) {
        this.idBook = idBook;
        this.idUser = idUser;
        this.rate = rate;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

}
