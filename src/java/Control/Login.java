/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import IO.IOUser;
import IO.IOUtils;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void loginValidate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        HttpSession session = request.getSession();
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String url = "";
        if (IOUser.checkValidateUser(username, password) != null) {
            User user = IOUser.checkValidateUser(username, password);
            request.setAttribute("errorLogin", false);
            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(60 * 60 * 24 * 365 * 2);
            usernameCookie.setPath("/");
            response.addCookie(usernameCookie);

            Cookie passwordCookie = new Cookie("password", password);
            passwordCookie.setMaxAge(60 * 60 * 24 * 365 * 2);
            passwordCookie.setPath("/");
            response.addCookie(passwordCookie);
//            
            session.setAttribute("user", user);
            if (session.getAttribute("loginToPayment") != null && (boolean) session.getAttribute("loginToPayment") == true) {
                url = "/Payment";
            } else {
                url = "/index.jsp";
            }
        } else {
            request.setAttribute("errorLogin", true);
            url = "/Login.jsp";
        }
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        String url = "";
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            url = "/UserDetailControl";
        } else {
            Cookie[] cookies = request.getCookies();
            String username = IOUtils.getCookieValue(cookies, "username");
            String password = IOUtils.getCookieValue(cookies, "password");
            if (IOUser.checkValidateUser(username, password) != null) {
                User user = IOUser.checkValidateUser(username, password);
                session.setAttribute("user", user);
            }
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                if (session.getAttribute("loginToPayment") != null && (boolean) session.getAttribute("loginToPayment") == true) {
                    url = "/Payment";
                    session.setAttribute("loginToPayment", false);
                } else if (session.getAttribute("loginToComment") != null && (boolean) session.getAttribute("loginToComment") == true) {
                    url = "/BookDetail";
                    session.setAttribute("loginToComment", false);
                    response.sendRedirect(request.getContextPath() + url);
                    return;
                } else if (session.getAttribute("loginToRate") != null && (boolean) session.getAttribute("loginToRate") == true) {
                    url = "/BookDetail";
                    session.setAttribute("loginToRate", false);
                    response.sendRedirect(request.getContextPath() + url);
                    return;
                } else {
                    url = "/UserDetailControl";
                }
            } else {
                url = "/Login.jsp";
            }
        }

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        loginValidate(request, response);
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
