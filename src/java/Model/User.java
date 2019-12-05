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
public class User implements Serializable{
    private String id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String numberPhone;
    private ArrayList<Invoice> transactionHistory;

    public User() {
    }

    public User(String id, String username, String password, String name, String address, String numberPhone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.numberPhone = numberPhone;
    }
    public void addTransaction(Invoice invoice){
        transactionHistory.add(invoice);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public ArrayList<Invoice> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(ArrayList<Invoice> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
    
    
    
}
