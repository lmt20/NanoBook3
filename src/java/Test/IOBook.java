/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import IO.IOUtils;
import Model.Book;
import Model.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class IOBook {

    public static ArrayList<Book> getListBook() {
        ArrayList<Book> listBook = (ArrayList<Book>) IOUtils.readFromFile("C:\\Users\\Asus\\Desktop\\ProjectLTW\\NanoBook\\web\\WEB-INF\\ListBook.txt");
        return listBook;
    }

    public static Book getBookById(String id) throws FileNotFoundException, IOException {
        ArrayList<Book> listBook = getListBook();
        for (Book book : listBook) {
            if (id.equals(book.getId())) {
                return book;
            }
        }
        return new Book();
    }

    public static ArrayList<String> getListCatagory() {
        String fileName = "C:\\Users\\Asus\\Desktop\\ProjectLTW\\NanoBook\\web\\WEB-INF\\ListCatagory.txt";
        ArrayList<String> listCatagory = (ArrayList<String>) IOUtils.readFromFile(fileName);
        return listCatagory;
    }

    public static ArrayList<Book> getListBookCatagory(String catagory) {
        ArrayList<Book> listBook = getListBook();
        ArrayList<Book> listBookByCatagory = new ArrayList<>();
        for (Book book : listBook) {
            ArrayList<String> listCatagory = book.getCatagory();
            for (String cata : listCatagory) {
                if (cata.equals(catagory)) {
                    listBookByCatagory.add(book);
                }
            }
        }
        return listBookByCatagory;
    }

    public static void main(String[] args) throws IOException {
        IOObject.initialFileListBook();
        IOObject.initialFileListCatagory();
        IOObject.initialDescriptionBooks();
        ArrayList<Book> listBook = getListBook();
        System.out.println(listBook.size());
    }
}

class IOObject{

    private static String fileName = "C:\\Users\\Asus\\Desktop\\ProjectLTW\\NanoBook\\web\\WEB-INF\\ListBook.txt";

    public static void initialFileListBook() {
        ArrayList<Book> listBooks = RecursiveFileExample.getListBookDirectly();
        IOUtils.writeToFile(listBooks, fileName);
    }

    public static void initialFileListCatagory() {
        ArrayList<String> listCatagory = new ArrayList<String>();
        File file = new File("C:\\Users\\Asus\\Desktop\\Data_Project_LTWeb");
        File[] children = file.listFiles();
        for (File child : children) {
            listCatagory.add(child.getName());
        }
        String fileName = "C:\\Users\\Asus\\Desktop\\ProjectLTW\\NanoBook\\web\\WEB-INF\\ListCatagory.txt";
        IOUtils.writeToFile(listCatagory, fileName);
    }
    
    public static String readFileText() throws IOException {
        String fileName = "C:\\Users\\Asus\\Desktop\\Data\\DescriptionBooks.txt";
        String content = new String(Files.readAllBytes(Paths.get(fileName)),
                StandardCharsets.UTF_8);//đưa về chuẩn utf-8
        return content;
    }
    
    
    
    public static void initialDescriptionBooks() throws IOException {
        ArrayList<Book> listBooks = IOBook.getListBook();
        String descriptions = readFileText();
        String[] idAndDescriptions = descriptions.trim().split("00000");
        for(String idAndDescription: idAndDescriptions ){
            String[] d = idAndDescription.trim().split("\\|");
            String id = d[0];
            String description = d[1];
            for(Book book: listBooks){
                if(book.getId().equals(id)){
                    book.setDescription(description);
                    break;
                }
            }
        }
        IOUtils.writeToFile(listBooks, fileName);
    }
}

class RecursiveFileExample {

    private static String result = "";
    private static ArrayList<Book> listBook = new ArrayList<Book>();

    private void fetchChild(File file) {
//        System.out.println(file.getAbsolutePath());
        if (file.isFile()) {
            result += file.getName() + "\n";
            String st = file.getName();
            String[] data = st.trim().split("\\+");
            double price = Double.parseDouble(data[3].trim().split("\\.")[0]);
            String catagory = file.getParentFile().getName();
            boolean existBook = false;
            for (Book book : listBook) {
                if (book.getId().equals(data[0])) {
                    Book tempBook = book;
                    ArrayList<String> listCatagory = book.getCatagory();
                    listCatagory.add(catagory);
                    book.setCatagory(listCatagory);

                    listBook.remove(tempBook);
                    listBook.add(book);

                    existBook = true;
                    break;
                }
            }
            if (existBook == false) {
                ArrayList<String> listCatagory = new ArrayList<>();
                listCatagory.add(catagory);
                Book book = new Book(data[0], data[1], data[2], listCatagory, price, "");
                listBook.add(book);
            }
        }

        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File child : children) {
                // Đệ quy (Recursive)
                this.fetchChild(child);
            }
        }
    }

    public static ArrayList<Book> getListBookDirectly() {
        result = "";
        listBook = new ArrayList<Book>();
        RecursiveFileExample example = new RecursiveFileExample();
        File dir = new File("C:\\Users\\Asus\\Desktop\\Data_Project_LTWeb");
        example.fetchChild(dir);
        return listBook;
    }
}
