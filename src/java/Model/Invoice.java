/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class Invoice implements Serializable {

    private String id;
    private Cart cart;
    private UserReceive userReceive;
    private String paymentMethod;
    private String transportMethod;
    private double transportFee;
    private double discount;
    

    private Date dayPayment;
    private double finalPrice;
    private String idUser;
    
    private static int numAutoIncrease = 100000;
    
    public Invoice() {
    }

    public Invoice(UserReceive userReceive, Cart cart, String transportMethod, String paymentMethod, double discount ) {
        this.cart = cart;
        this.paymentMethod = paymentMethod;
        this.discount = discount;
        this.transportMethod = transportMethod;
        this.transportFee = transportFee;
        this.userReceive = userReceive;
        this.finalPrice = getFinalPrice();
        this.transportFee = getTransportFree();
        this.dayPayment = new Date();
        this.numAutoIncrease += (int) (Math.random() * 10000);
        this.id = "MDH" + numAutoIncrease;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    
    public double getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(double transportFee) {
        this.transportFee = transportFee;
    }

    public UserReceive getUserReceive() {
        return userReceive;
    }

    public static int getNumAutoIncrease() {
        return numAutoIncrease;
    }

    public static void setNumAutoIncrease(int numAutoIncrease) {
        Invoice.numAutoIncrease = numAutoIncrease;
    }

    public void setUserReceive(UserReceive userReceive) {
        this.userReceive = userReceive;
    }

    public String getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransportMethod() {
        return transportMethod;
    }

    public void setTransportMethod(String transportMethod) {
        this.transportMethod = transportMethod;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getShippingMethod() {
        return transportMethod;
    }

    public void setShippingMethod(String transportMethod) {
        this.transportMethod = transportMethod;
    }

    public double getTransportFree() {
        transportFee = 0;

        if (transportMethod.equals("Giao Hàng Tiêu Chuẩn")) {
            transportFee = 20000;
        } else if (transportMethod.equals("Giao Hàng Nhanh")) {
            transportFee = 30000;
        }
        return transportFee;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTransportFree(double transportFee) {
        this.transportFee = transportFee;
    }

    public void setDayPayment(Date dayPayment) {
        this.dayPayment = dayPayment;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getDayPayment() {
        return dayPayment;
    }
    
    public double getFinalPrice() {
        finalPrice = cart.getTotalPrice() + transportFee - discount;
        return finalPrice;
    }

}
