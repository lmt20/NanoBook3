/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import Model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class IOUser {
    
    private static String fileName = "C:\\Users\\Asus\\Desktop\\ProjectLTW\\NanoBook_Project2\\web\\WEB-INF\\ListUser.txt";

    private static ArrayList<User> getListUser() {
        ArrayList<User> listUser = new ArrayList<User>();
        try {
            listUser = (ArrayList<User>) IOUtils.readFromFile(fileName);
        } catch (Exception e) {
        }
        return listUser;
    }

    public static User checkValidateUser(String username, String password) throws FileNotFoundException, IOException {
        ArrayList<User> listUser = getListUser();
        for (User user : listUser) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static boolean checkExistUser(String username) {
        ArrayList<User> listUser = getListUser();
        for (User user : listUser) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static void addUser(User user) {
        ArrayList<User> listUser = getListUser();
        listUser.add(user);
        IOUtils.writeToFile(listUser, fileName);
    }

    public static void updateUser(User user) {
        ArrayList<User> listUser = getListUser();
        for (User u : listUser) {
            if (user.getUsername().equals(u.getUsername())) {
                listUser.remove(u);
                break;
            }
        }
        listUser.add(user);
        IOUtils.writeToFile(listUser, fileName);
    }

    public static void main(String[] args) {
        ArrayList<User> listUser = new ArrayList<>();
        IOUtils.writeToFile(listUser, fileName);
    }
}
