/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;


import IODB.BookDB;
import IODB.UserCommentDB;
import IODB.UserRatingDB;
import Model.Book;
import Model.Cart;
import Model.UserComment;
import Model.UserRating;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.nio.ch.IOUtil;

/**
 *
 * @author Asus
 */
@WebServlet(name = "BookDetailControl", urlPatterns = {"/BookDetailControl"})
public class BookDetailControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookDetail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String bookId = request.getParameter("bookId");
        if (bookId != null) {
            Book book = BookDB.getBookById(bookId);
            session.setAttribute("selectingBook", book);
        }
        Book selectingBook = (Book) session.getAttribute("selectingBook");
        ArrayList<UserRating> listUserRating = UserRatingDB.getListUserRating(selectingBook.getId());
        selectingBook.setListUserRating(listUserRating);
        
        ArrayList<UserComment> listUserComment = UserCommentDB.getListUserComment(selectingBook.getId());
        selectingBook.setListUserComment(listUserComment);

        session.setAttribute("selectingBook", selectingBook);
        
        String url = "/BookDetail.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        Book book = (Book) session.getAttribute("selectingBook");
//        boolean ex = false;
//        String url = "";
//        int numBook = 0;
//        try {
//            numBook = Integer.parseInt(request.getParameter("numBook"));
//            if (numBook <= 0) {
//                ex = true;
//            } else {
//                cart.addBookToCart(book, numBook);
//                session.setAttribute("cart", cart);
//                url = "/CartControl";
//                response.sendRedirect(request.getContextPath() + url);
//                return;
//            }
//        } catch (Exception e) {
//            ex = true;
//        }
//        if (ex == true) {
//            url = "/BookDetail.jsp";
//            request.setAttribute("ex", ex);
//        }

//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(request, response);
        int numBook = Integer.parseInt(request.getParameter("numBook"));
        cart.addBookToCart(book, numBook);
        session.setAttribute("cart", cart);
        String url = "/CartControl";
        response.sendRedirect(request.getContextPath() + url);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
