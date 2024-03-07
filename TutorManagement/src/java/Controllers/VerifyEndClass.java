/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.Classes;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.time.LocalDateTime.now;

/**
 *
 * @author ADMIN
 */
public class VerifyEndClass extends HttpServlet {

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
            out.println("<title>Servlet VerifyEndClass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyEndClass at " + request.getContextPath() + "</h1>");
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
        try {
            int cid = Integer.parseInt(request.getParameter("cid"));
            Classes c = DAO.INSTANCE.getClassById(cid);
            HttpSession ses = request.getSession();
            User user = (User) ses.getAttribute("user");
            request.setAttribute("c", c);
            request.setAttribute("cid", cid);

        } catch (Exception e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("Views/VerifyEndClass.jsp").forward(request, response);
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

        try {
            int cid = Integer.parseInt(request.getParameter("cid"));
            Classes c = DAO.INSTANCE.getClassById(cid);
            HttpSession ses = request.getSession();
            User user = (User) ses.getAttribute("user");
            DAO.INSTANCE.updateClass(now().toString(), "Ended", cid);
            if (user.getRollNo() == 0) {
                response.sendRedirect("AdminControl");
                return;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        response.sendRedirect("HomeControl");
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
