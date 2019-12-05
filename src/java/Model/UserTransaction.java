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
public class UserTransaction implements Serializable{
    private User user;
    private ArrayList<Invoice> listInvoice;

    public UserTransaction()  {
    }
    public void addInvoice(Invoice invoice){
        listInvoice.add(0,invoice);
    }
    public UserTransaction(User user, ArrayList<Invoice> listInvoice) {
        this.user = user;
        this.listInvoice = listInvoice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Invoice> getListInvoice() {
        return listInvoice;
    }

    public void setListInvoice(ArrayList<Invoice> listInvoice) {
        this.listInvoice = listInvoice;
    }    
}
