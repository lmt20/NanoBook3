/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import Model.Invoice;
import Model.User;
import Model.UserTransaction;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class IOUserTransaction {

    private static String fileName = "C:\\Users\\Asus\\Desktop\\ProjectLTW\\NanoBook_Project2\\web\\WEB-INF\\ListUserTransaction.txt";

    public static ArrayList<UserTransaction> getListUserTransaction() {
        ArrayList<UserTransaction> listUserTransaction = new ArrayList<UserTransaction>();
        try {
            listUserTransaction = (ArrayList<UserTransaction>) IOUtils.readFromFile(fileName);
        } catch (Exception e) {
        }

        return listUserTransaction;
    }

    public static UserTransaction getUserTransaction(User user) {
        ArrayList<UserTransaction> listUserTransaction = getListUserTransaction();
        for (UserTransaction userTransaction : listUserTransaction) {
            if (userTransaction.getUser().getUsername().equals(user.getUsername())) {
                return userTransaction;
            }
        }
        UserTransaction userTransaction = new UserTransaction(user, new ArrayList<>());
        listUserTransaction.add(userTransaction);
        IOUtils.writeToFile(listUserTransaction, fileName);
        return userTransaction;
    }

    public static void addUserTransaction(UserTransaction userTransaction) {
        ArrayList<UserTransaction> listUserTransaction = getListUserTransaction();
        for (UserTransaction uTransaction : listUserTransaction) {
            if (userTransaction.getUser().getUsername().equals(uTransaction.getUser().getUsername())) {
                listUserTransaction.remove(uTransaction);
                break;
            }
        }
        listUserTransaction.add(0,userTransaction);
        IOUtils.writeToFile(listUserTransaction, fileName);
    }

    public static void show() throws IOException {
        User user = IOUser.checkValidateUser("lmtbit", "truongdzai");
        UserTransaction userTransaction = IOUserTransaction.getUserTransaction(user);
        ArrayList<Invoice> listInvoice = userTransaction.getListInvoice();
        System.out.println("size:" + listInvoice.size());
        System.out.print("|");
        for (Invoice invoice : listInvoice) {
            System.out.print(invoice.getId() + " ");
        }
        System.out.print("|");

    }

    public static void showListUserTransaction() {
        ArrayList<UserTransaction> listUserTransactions = getListUserTransaction();
        ArrayList<String> listUsername = new ArrayList<>();
        for (UserTransaction userTransaction : listUserTransactions) {
            listUsername.add(userTransaction.getUser().getUsername());
        }
        System.out.println(listUsername);
    }

    public static void main(String[] args) throws IOException {
        ArrayList<UserTransaction> listUserTransaction = new ArrayList<UserTransaction>();
        IOUtils.writeToFile(listUserTransaction, fileName);
    }
}
