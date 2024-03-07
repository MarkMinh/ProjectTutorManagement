/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import DAL.MaHoa;
import Models.Notification;

import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class LoginControl extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginControl at " + request.getContextPath() + "</h1>");
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
//        Cookie arr[] = request.getCookies();
//        if (arr != null) {
//            for (Cookie o : arr) {
//                if (o.getName().equals("userC")) {
//                    request.setAttribute("username", o.getValue());
//                }
//                if (o.getName().equals("passC")) {
//                    request.setAttribute("password", o.getValue());
//                }
//            }
//        }

        request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
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
        String u = request.getParameter("user");
        String p = request.getParameter("pass");
        String r = request.getParameter("rem");

        Cookie cu = new Cookie("cuser", u);
        Cookie cp = new Cookie("cpass", p);
        Cookie cr = new Cookie("crem", r);
        
        p = MaHoa.toSHA1(p);
        User user = DAO.INSTANCE.login(u, p);

        // luu acc tren cookie
        if (r != null) {
            cu.setMaxAge(60 * 60 * 24);
            cp.setMaxAge(60 * 60 * 24);
            cr.setMaxAge(60 * 60 * 24);
        } else {
            cu.setMaxAge(0);
            cp.setMaxAge(0);
            cr.setMaxAge(0);
        }
        response.addCookie(cu);   // luu user va pass len chrome
        response.addCookie(cp);
        response.addCookie(cr);
        if (user == null) {
            request.setAttribute("msg", "Wrong username or password");
            request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
        } else {

            HttpSession ses = request.getSession();
            ses.setAttribute("user", user);

            User us = (User) ses.getAttribute("user");
            if (user.getRollNo() == 0) {
                response.sendRedirect("AdminControl");
                return;
            }
            ArrayList<Notification> listNoti = DAO.INSTANCE.getAllNotification(us.getUsername());
            ses.setAttribute("listNoti", listNoti);

            response.sendRedirect("HomeControl");
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
