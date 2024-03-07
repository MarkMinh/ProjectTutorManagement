/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.RequestDetail;
import Models.TutorRating;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DetailReceived extends HttpServlet {

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
            out.println("<title>Servlet DetailReceived</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailReceived at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("uid");
        request.setAttribute("uid", username);
        User uS = DAO.INSTANCE.getUser(username);
        request.setAttribute("uS", uS);
        ArrayList<String> listSub = DAO.INSTANCE.getSubjectByTutor(username);
        request.setAttribute("listSub", listSub);
        String rid = request.getParameter("rid");
        request.setAttribute("rid", rid);
        try {
            int ridS = Integer.parseInt(rid);
            RequestDetail rd = DAO.INSTANCE.getRequestDetailById(ridS);
            request.setAttribute("rd", rd);
        } catch (Exception e) {
        }
        int numRate = DAO.INSTANCE.getCountRating(username);
        request.setAttribute("numRate", numRate);
        double rateScore = DAO.INSTANCE.getRating(username);
        request.setAttribute("rateScore", rateScore);

        ArrayList<TutorRating> listRating = DAO.INSTANCE.getFeedBack(username);
        request.setAttribute("listRating", listRating);

        ArrayList<String> listStdRate = DAO.INSTANCE.getStudentNameByTutor(username);
        request.setAttribute("listStdRate", listStdRate);

        request.setAttribute("dao", DAO.INSTANCE);
        request.getRequestDispatcher("Views/DetailReceived.jsp").forward(request, response);
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
        String tutorName = request.getParameter("uid");
        request.setAttribute("uid", tutorName);
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");
        try {
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("comment");
            if (DAO.INSTANCE.getFeedBack(tutorName, user.getUsername()) == null) {
                DAO.INSTANCE.insertFeedBack(tutorName, user.getUsername(), rating, comment);
            }else{
                DAO.INSTANCE.updateFeedBack(tutorName, user.getUsername(), rating, comment);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        doGet(request, response);
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
