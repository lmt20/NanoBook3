/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import IO.IOUserTransaction;
import Model.Cart;
import Model.Invoice;
import Model.User;
import Model.UserReceive;
import Model.UserTransaction;
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

/**
 *
 * @author Asus
 */
@WebServlet(name = "InvoiceControl", urlPatterns = {"/InvoiceControl"})
public class InvoiceControl extends HttpServlet {

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
            out.println("<title>Servlet Invoice</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Invoice at " + request.getContextPath() + "</h1>");
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
//        HttpSession session = request.getSession();
        HttpSession session = request.getSession();
        User user = new User();
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
        }
        UserTransaction userTransaction = IOUserTransaction.getUserTransaction(user);
        ArrayList<Invoice> listInvoice = userTransaction.getListInvoice();
        String idInvoice = (String) request.getParameter("idInvoice");
        Invoice selectedInvoice = new Invoice();
        for (Invoice iv : listInvoice) {
            if (iv.getId().equals(idInvoice)) {
                selectedInvoice = iv;
                break;
            }
        }
        request.setAttribute("selectedInvoice", selectedInvoice);
        request.setAttribute("userTransaction", userTransaction);
        request.setAttribute("listInvoice", listInvoice);
        String url = "/UserDetail.jsp";
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
        if (request.getParameter("completeInvoice") != null) {
            HttpSession session = request.getSession();

            session.removeAttribute("cart");
            Invoice invoice = (Invoice) session.getAttribute("invoice");
            User user = (User) session.getAttribute("user");
            UserTransaction userTransaction = IOUserTransaction.getUserTransaction(user);
            userTransaction.addInvoice(invoice);
            IOUserTransaction.addUserTransaction(userTransaction);
            session.removeAttribute("invoice");

            String url = "/UserDetailControl";
            response.sendRedirect(request.getContextPath() + url);
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//            dispatcher.forward(request, response);
        } else {

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            String name = request.getParameter("name");
            String numberPhone = request.getParameter("numberPhone");
            String address = request.getParameter("address");
            String discount = request.getParameter("discount");
            String idTransportMethod = request.getParameter("transportMethod");
            String transportMethod = "";
            if (idTransportMethod.equals("1")) {
                transportMethod = "Giao Hàng Tiêu Chuẩn";
            } else if (idTransportMethod.equals("2")) {
                transportMethod = "Giao Hàng Nhanh";
            }

            String paymentMethod = "";
            String idPaymentMethod = request.getParameter("paymentMethod");
            if (idPaymentMethod.equals("1")) {
                paymentMethod = "Thanh Toán Khi Nhận Hàng";
            } else if (idPaymentMethod.equals("2")) {
                paymentMethod = "Ví Điện Tử";
            } else if (idPaymentMethod.equals("3")) {
                paymentMethod = "Thẻ Tín Dụng/Ghi Nợ";
            }

            UserReceive userReceive = new UserReceive(name, numberPhone, address);
            Invoice invoice = new Invoice(userReceive, cart, transportMethod, paymentMethod, Double.parseDouble(discount));
            session.setAttribute("invoice", invoice);

            String url = "/Invoice.jsp";
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
