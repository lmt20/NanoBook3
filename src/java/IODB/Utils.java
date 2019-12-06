/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IODB;

import Model.Book;
import Model.Catagory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.http.Cookie;

/**
 *
 * @author Asus
 */
public class Utils {

    public static String dropAllTable = "Drop table book,bookcatagory,catagory,linebook,user,usercomment,userrating;";
    public static String createTableUserQuery = "CREATE  TABLE IF NOT EXISTS User(\n"
            + "	idUser int not null auto_increment,\n"
            + "    username varchar(150) not null,\n"
            + "    password varchar(150) not null,\n"
            + "    name varchar(150) not null,\n"
            + "    address varchar(150) not null,\n"
            + "    numberPhone varchar(150) not null,\n"
            + "    primary key (idUser)\n"
            + ");";

    public static String createTableInvoiceQuery = "CREATE  TABLE IF NOT EXISTS Invoice(\n"
            + "	idInvoice int not null,\n"
            + "    idUser int not null,\n"
            + "    paymentMethod varchar(150) not null,\n"
            + "    transportMethod varchar(150) not null,\n"
            + "    discount double not null,\n"
            + "    dayPayment Date not null,\n"
            + "    nameUserReceive varchar(150) not null,\n"
            + "    numberPhoneUserReceive varchar(150) not null,\n"
            + "    addressUserReceive varchar(150) not null,\n"
            + "    primary key (idInvoice)\n"
            + ");";
    public static String createTableLineBookQuery = "CREATE  TABLE IF NOT EXISTS LineBook(\n"
            + "	idBook int not null,\n"
            + "    numBook int not null,\n"
            + "    idInvoice int not null\n"
            + ");";
    public static String createTableBookQuery = "CREATE  TABLE IF NOT EXISTS Book(\n"
            + "	idBook int not null,\n"
            + "    name varchar(150) not null,\n"
            + "    author varchar(150) not null,\n"
            + "    price double not null,\n"
            + "    description varchar(5150) not null,\n"
            + "    image varchar(150) not null,\n"
            + "    primary key (idBook)\n"
            + ");";
    public static String createTableCatagoryQuery = "CREATE  TABLE IF NOT EXISTS Catagory(\n"
            + "	idCatagory int not null,\n"
            + "    name varchar(150) not null,\n"
            + "    primary key (idCatagory)\n"
            + ");";
    public static String createTableBookCatagoryQuery = "CREATE  TABLE IF NOT EXISTS BookCatagory(\n"
            + "	idBook int not null,\n"
            + "	idCatagory int not null\n"
            + ");";
    public static String createTableUserCommentQuery = "CREATE  TABLE IF NOT EXISTS UserComment(\n"
            + "	idBook int not null,\n"
            + "    idUser int not null,\n"
            + "    comment varchar(150) not null\n"
            + ");";
    public static String createTableUserRatingQuery = "CREATE  TABLE IF NOT EXISTS UserRating(\n"
            + "	idBook int not null,\n"
            + "    idUser int not null,\n"
            + "    rate int not null\n"
            + ");";

    private static ArrayList<Book> listBook = new ArrayList<Book>();
    private static String pathDirData = "C:\\Users\\Asus\\Desktop\\Data\\Data_NanoBook";
    private static String pathImageData = "C:\\Users\\Asus\\Desktop\\ProjectLTW\\NanoBook_Project3_Final\\web\\Data";
    private static String descriptionBooksFile = "C:\\Users\\Asus\\Desktop\\Data\\DescriptionBooks.txt";

    public static ArrayList<Catagory> initialCatagorys() {
        ArrayList<Catagory> listCatagory = new ArrayList<>();
        File file = new File(pathDirData);
        File[] children = file.listFiles();
        int countId = 1;
        for (File child : children) {
            int idCatagory = countId;
            countId += 1;
            String name = child.getName();
            Catagory catagory = new Catagory(idCatagory + "", name);
            listCatagory.add(catagory);
        }
        return listCatagory;
    }

    private void fetchChild(File file) {
//        System.out.println(file.getAbsolutePath());
        if (file.isFile()) {
//            String absPath = file.getAbsolutePath();
            String st = file.getName();
            String[] data = st.trim().split("\\+");
            double price = Double.parseDouble(data[3].trim().split("\\.")[0]);
            String catagoryName = file.getParentFile().getName();
            Catagory catagory = new Catagory();
            ArrayList<Catagory> lCatagory = initialCatagorys();
            for (Catagory c : lCatagory) {
                if (c.getName().equals(catagoryName)) {
                    catagory = c;
                    break;
                }
            }
            boolean existBook = false;
            for (Book book : listBook) {
                if (book.getId().equals(data[0])) {
                    Book tempBook = book;
                    ArrayList<Catagory> listCatagory = book.getCatagory();
                    listCatagory.add(catagory);
                    book.setCatagory(listCatagory);
//                    book.setImage(absPath);
                    listBook.remove(tempBook);
                    listBook.add(book);

                    existBook = true;
                    break;
                }
            }
            if (existBook == false) {
                ArrayList<Catagory> listCatagory = new ArrayList<>();
                listCatagory.add(catagory);
                Book book = new Book(data[0], data[1], data[2], listCatagory, price, "");
//                book.setImage(absPath);
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

    public static String readFileText() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(descriptionBooksFile)),
                StandardCharsets.UTF_8);//đưa về chuẩn utf-8
        return content;
    }

    public static ArrayList<Book> initialBooks() throws IOException {
//        Add description
        ArrayList<Book> listBooks = getListBookDirectly();
        String descriptions = readFileText();
        String[] idAndDescriptions = descriptions.trim().split("00000");
        for (String idAndDescription : idAndDescriptions) {
            String[] d = idAndDescription.trim().split("\\|");
            String id = d[0];
            String description = d[1];
            for (Book book : listBooks) {
                if (book.getId().equals(id)) {
                    book.setDescription(description);
                    break;
                }
            }
        }
        for (Book book : listBooks) {
            book.setImage("Data\\" + book.getId() + ".png");
        }

        return listBooks;
    }

    public static ArrayList<Book> getListBookDirectly() {
        listBook = new ArrayList<Book>();
        Utils example = new Utils();
        File dir = new File(pathDirData);
        example.fetchChild(dir);
        return listBook;
    }

    public static String getCookieValue(Cookie[] cookies, String cookieName) {
        String cookieValue = "";
        Cookie cookie;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                }
            }
        }
        return cookieValue;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Book> listBooks = initialBooks();
        for (Book book : listBooks) {
            System.out.println(book.getId() + book.getDescription());
        }
    }

}
