/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Book;
import Model.Cart;
import Model.LineBook;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
@WebServlet(name = "CartControl", urlPatterns = {"/CartControl"})
public class CartControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void editNumBookInCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        String idBook = request.getParameter("idBook");
        String url = "/Cart.jsp";
        boolean exeptionNumEdit = false;

        try {
            int numBook = Integer.parseInt(request.getParameter("numBook"));
            if (numBook < 0) {
                exeptionNumEdit = true;
            } else if (numBook == 0) {
                request.setAttribute("idBook", idBook);
                deleteBookInCart(request, response);
                return;
//                url = "/DeleteBookCart";
            } else {
                for (LineBook lBook : cart.getListLine()) {
                    if (lBook.getBook().getId().equals(idBook)) {
                        lBook.setNumBook(numBook);
                    }
                }
            }
        } catch (Exception e) {
            exeptionNumEdit = true;
        }
        if (cart.getListLine().isEmpty()) {
            session.setAttribute("isEmpty", true);
        } else {
            session.setAttribute("isEmpty", false);
        }

        request.setAttribute("exeptionNumEdit", exeptionNumEdit);
        request.setAttribute("cart", cart);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void deleteBookInCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String idBook = "";
        try {
            idBook = request.getParameter("idBook");
        } catch (Exception e) {
            idBook = (String) request.getAttribute("idBook");
        }

        for (LineBook lBook : cart.getListLine()) {
            if (lBook.getBook().getId().equals(idBook)) {
                cart.deleteBookInCart(idBook);
                break;
            }
        }
        if (cart.getListLine().isEmpty()) {
            session.setAttribute("isEmpty", true);
        } else {
            session.setAttribute("isEmpty", false);
        }
        request.setAttribute("cart", cart);
        String url = "/Cart.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        if (cart.getListLine().isEmpty()) {
            session.setAttribute("isEmpty", true);
        } else {
            session.setAttribute("isEmpty", false);
        }
        String url = "/Cart.jsp";
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
        String typeControl = (String) request.getParameter("typeControl");
        if(typeControl == null){
            typeControl = "";
        }
        if (typeControl.equals("edit")) {
            editNumBookInCart(request, response);
        } else if (typeControl.equals("delete")) {
            deleteBookInCart(request, response);
        } else {
            String url = "";
            HttpSession session = request.getSession();
            if ((boolean) session.getAttribute("isEmpty") == true) {
                session.setAttribute("errorNoItemsInCartToPayment", true);
                url = "/CartControl";
            } else {
                if (session.getAttribute("user") != null) {
                    url = "/Payment";
                    response.sendRedirect(request.getContextPath()+url);
                    return;
                } else {
                    boolean loginToPayment = true;
                    session.setAttribute("loginToPayment", loginToPayment);
                    url = "/Login";
                    response.sendRedirect(request.getContextPath()+url);
                    return;
                }
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

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
