/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.LessonRequest;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.time.LocalDateTime.now;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class CancelRequestControl extends HttpServlet {

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
            out.println("<title>Servlet CancelRequestControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CancelRequestControl at " + request.getContextPath() + "</h1>");
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
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(9999, 11, 31, 23, 59, 59); // Năm 9999, tháng 12, ngày cuối cùng, 23 giờ 59 phút 59 giây.
//        Date endTime = calendar.getTime();
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");

        int rid;
        try {
            rid = Integer.parseInt(request.getParameter("rid"));
//            ArrayList<LessonRequest> allRequestsById = DAO.INSTANCE.getAllLessonRequestById(user.getId(), sid);
//            if (allRequestsById != null && !allRequestsById.isEmpty()) {
//                for (LessonRequest lr : allRequestsById) {
            String uRName = DAO.INSTANCE.getRequestDetailById(rid).getUsername();
            DAO.INSTANCE.updateLessonRequest("Cancelled", now().toString(), null, user.getUsername(), uRName);
//                }
//            }
        } catch (Exception e) {
            System.out.println(e);
        }

        response.sendRedirect("HomeControl");

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
        processRequest(request, response);
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
