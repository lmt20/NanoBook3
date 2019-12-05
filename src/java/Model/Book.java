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
public class Book implements Serializable {

    private String id;
    private String name;
    private String author;
    private ArrayList<Catagory> catagory;
    private double price;
    private String description;
    private ArrayList<UserRating> listUserRating;
    private ArrayList<UserComment> listUserComment;
    private double finalRating;
    private String image;

    public Book() {
    }

    public Book(String id, String name, String author, ArrayList<Catagory> catagory, double price, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.catagory = catagory;
        this.price = price;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<UserRating> getListUserRating() {
        return listUserRating;
    }

    public void setListUserRating(ArrayList<UserRating> listUserRating) {
        this.listUserRating = listUserRating;
    }

//    public void addUserRating(UserRating userRating) {
//        if (listUserRating == null) {
//            listUserRating = new ArrayList<UserRating>();
//        }
//        for (UserRating uRating : listUserRating) {
//            if (uRating.getUser().getUsername().equals(userRating.getUser().getUsername())) {
//                listUserRating.remove(uRating);
//                break;
//            }
//        }
//        listUserRating.add(0,userRating);
//    }

    public ArrayList<UserComment> getListUserComment() {
        return listUserComment;
    }

    public void setListUserComment(ArrayList<UserComment> listUserComment) {
        this.listUserComment = listUserComment;
    }

    public void addUserComment(UserComment userComment) {
        if (listUserComment == null) {
            listUserComment = new ArrayList<UserComment>();
        }
//        for (UserComment uComment : listUserComment) {
//            if (uComment.getUser().getUsername().equals(userComment.getUser().getUsername())) {
//                listUserComment.remove(uComment);
//                break;
//            }
//        }
        listUserComment.add(0, userComment);
    }

    public double getFinalRating() {
        if (listUserRating == null) {
            listUserRating = new ArrayList<UserRating>();
        }
        finalRating = 0;
        for (UserRating uRating : listUserRating) {
            finalRating += uRating.getRate();
        }
        if (!listUserRating.isEmpty()) {
            finalRating /= listUserRating.size();
        }
        return finalRating;
    }

    public void setFinalRating(double finalRating) {
        this.finalRating = finalRating;
    }

    public ArrayList<Catagory> getCatagory() {
        return catagory;
    }

    public void setCatagory(ArrayList<Catagory> catagory) {
        this.catagory = catagory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
