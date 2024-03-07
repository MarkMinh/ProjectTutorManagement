/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.Subject;
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

/**
 *
 * @author ADMIN
 */
public class UploadRequest extends HttpServlet {

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
            out.println("<title>Servlet UploadRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadRequest at " + request.getContextPath() + "</h1>");
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

        HttpSession ses = request.getSession();
        User u = (User) ses.getAttribute("user");

        ArrayList<Subject> subjects = DAO.INSTANCE.loadAllSubjects();

        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("Views/UploadRequest.jsp").forward(request, response);
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
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");
        try {
            int gradeLevel = Integer.parseInt(request.getParameter("gradeLevel"));
            int subjectId = Integer.parseInt(request.getParameter("subject"));
            double minRate = Double.parseDouble(request.getParameter("minRate"));
            double maxRate = Double.parseDouble(request.getParameter("maxRate"));
            String description = request.getParameter("description");
            String requirement = request.getParameter("requirement");
            int learningType = Integer.parseInt(request.getParameter("learningType"));
            if ((request.getParameter("rid") != null) && !(request.getParameter("rid").trim().isEmpty() )) {
                int rid = Integer.parseInt(request.getParameter("rid"));
                DAO.INSTANCE.updateRequestDetail(gradeLevel, minRate, maxRate, description, requirement, now().toString(),
                        subjectId, learningType, rid);
                response.sendRedirect("MyRequest");
            }else{
                DAO.INSTANCE.insertRequest(user.getUsername(), gradeLevel, minRate, maxRate, description, requirement,
                        now().toString(), now().toString(), subjectId, learningType);
                response.sendRedirect("HomeControl");
            }
            

        } catch (Exception e) {
            System.out.println("upload fail " + e);
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
