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
public class UserComment implements Serializable {

    private String idBook;
    private String idUser;
    private String comment;

    public UserComment() {
    }

    public UserComment(String idBook, String idUser, String comment) {
        this.idBook = idBook;
        this.idUser = idUser;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    

}
