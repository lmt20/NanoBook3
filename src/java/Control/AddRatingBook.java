/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import IODB.UserRatingDB;
import Model.Book;
import Model.User;
import Model.UserComment;
import Model.UserRating;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "AddRatingBook", urlPatterns = {"/AddRatingBook"})
public class AddRatingBook extends HttpServlet {

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
            out.println("<title>Servlet AddRatingBook</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRatingBook at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
        String url = "";
        if (session.getAttribute("user") == null) {
            url = "/Login";
            session.setAttribute("loginToRate", true);
            response.sendRedirect(request.getContextPath() + url);
            return;
        } else {
            User user = (User) session.getAttribute("user");
            Book selectingBook = (Book) session.getAttribute("selectingBook");
            int ratingInput = Integer.parseInt(request.getParameter("ratingInput"));
            UserRating userRating = new UserRating(selectingBook, user, ratingInput);
            ArrayList<UserRating> listUserRating = UserRatingDB.getListUserRating(selectingBook.getId());
            boolean dg = false;
            for (UserRating uRating : listUserRating) {
                if (uRating.getBook().getId().equals(userRating.getBook().getId()) &&
                        uRating.getUser().getId().equals(userRating.getUser().getId())) {
                    dg = true;
                    UserRatingDB.update(userRating);
                    break;
                }
            }
            if (dg == false) {
                UserRatingDB.insert(userRating);
            }
            url = "/BookDetailControl";
            response.sendRedirect(request.getContextPath() + url);
            return;
        }

//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
//        dispatcher.forward(request, response);
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
