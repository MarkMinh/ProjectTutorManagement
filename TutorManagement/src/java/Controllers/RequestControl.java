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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.time.LocalDateTime.now;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class RequestControl extends HttpServlet {

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
            out.println("<title>Servlet RequestControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestControl at " + request.getContextPath() + "</h1>");
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
        User user = (User) ses.getAttribute("user");

        int sid;
        try {
            sid = Integer.parseInt(request.getParameter("rid"));
//            ArrayList<LessonRequest> allRequestsById = DAO.INSTANCE.getAllLessonRequestById(user.getId(), sid);
//            if (allRequestsById != null && !allRequestsById.isEmpty()) {
//                for (LessonRequest lr : allRequestsById) {
//                    DAO.INSTANCE.updateLessonRequest("Cancelled", now().toString(), now().toString(), user.getId(), sid);
//                }
//            }

            String uRName = DAO.INSTANCE.getRequestDetailById(sid).getUsername();

            DAO.INSTANCE.insertNotification(uRName, "User " + user.getUsername() + " send a request to you", now().toString(), "/PRJ301_SE1761_FA23_HE171380/DetailReceived?uid=" + user.getUsername() + "&rid=" + sid, user.getUsername());
            if (DAO.INSTANCE.getLessonRequest(user.getUsername(), uRName) == null) {
                int lrId = DAO.INSTANCE.insertLessonRequest(user.getUsername(), uRName, "pending", now().toString(), now().toString(), null);
            } else {
                int lrId = DAO.INSTANCE.updateLessonRequest("pending", now().toString(), null, user.getUsername(), uRName);
            }

//            User uReq = DAO.INSTANCE.getUser(sid);
//            Cookie[] arr = request.getCookies();
//            String txt = "";
//            if (arr != null) {
//                for (Cookie o : arr) {
//                    if (o.getName().equals("upload")) {
//                        txt += o.getValue();
//                    }
//                }
//            }
//            if (txt.isEmpty()) {
//                txt = lrId + "";
//            } else {
//                txt += "/" + lrId;
//            }
//
//            Cookie c = new Cookie("upload", txt);
//            c.setMaxAge(30 * 24 * 60 * 60);
//            response.addCookie(c);
//            List<LessonRequest> listAllRequests = new ArrayList<>();
//            listAllRequests = DAO.INSTANCE.loadRequest();
//
//            List<LessonRequest> lessonRequests = new ArrayList<>();
//            lessonRequests = DAO.INSTANCE.getRequested(listAllRequests, user.getId());
//            List<LessonRequest> lessonRequests = new ArrayList<>();
//            lessonRequests = DAO.INSTANCE.loadUserRequest(txt);
//            request.setAttribute("lessonRequests", lessonRequests);
        } catch (Exception e) {
            System.out.println(e);
        }

        response.sendRedirect("/PRJ301_SE1761_FA23_HE171380/LoadDBStd");

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
