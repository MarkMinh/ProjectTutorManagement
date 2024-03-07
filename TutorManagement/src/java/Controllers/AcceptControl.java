/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
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
public class AcceptControl extends HttpServlet {

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
            out.println("<title>Servlet AcceptControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AcceptControl at " + request.getContextPath() + "</h1>");
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

//         public int updateLessonRequest(String status, String updateAt, String endTime, String username, String receiverName) {
        HttpSession ses = request.getSession();
        User user = (User) ses.getAttribute("user");
        request.setAttribute("uS", user);
        String sid = request.getParameter("uid");
        request.setAttribute("uid", sid);
        try {
            int rid = Integer.parseInt(request.getParameter("rid"));
            DAO.INSTANCE.deleteUserDetail(now().toString(), rid);
            request.setAttribute("rid", rid);
        } catch (Exception e) {
            System.out.println("Delete user detail fail "+ e);
        }
        
        ArrayList<String> dayList = new ArrayList<>();
        dayList.add("Monday");
        dayList.add("Tuesday");
        dayList.add("Wednesday");
        dayList.add("Thursday");
        dayList.add("Friday");
        dayList.add("Saturday");
        dayList.add("Sunday");
        request.setAttribute("dayList", dayList);
        DAO.INSTANCE.updateLessonRequest("Accepted", now().toString(), now().toString(), sid, user.getUsername());
        DAO.INSTANCE.denyAll(user.getUsername(), now().toString(), "Denied");
        request.getRequestDispatcher("Views/CreateClass.jsp").forward(request, response);
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
            String uName = request.getParameter("uid");
            double amount = Double.parseDouble(request.getParameter("moneyS"));
            String[] selectedDays = request.getParameterValues("dayW");
            ArrayList<String> listTimeSlot = DAO.INSTANCE.getTimeSlot(uName);
            if (selectedDays != null) {
                for (String s : selectedDays) {
                    int timeSlot = Integer.parseInt(request.getParameter("timeSlot" + s));
                    for (String l : listTimeSlot) {
                        if (l.contains(timeSlot + "") && l.contains(s)) {
                            request.setAttribute("ms", "The tutor's schedule clashed");
                            doGet(request, response);
                            return;
                        }
                    }

                }
            }

            int rid = Integer.parseInt(request.getParameter("rid"));
            int cId = DAO.INSTANCE.insertClass(user.getUsername(), uName, amount, now().toString(), "Unconfirmed", rid);
            if (selectedDays != null) {
                for (String s : selectedDays) {
                    int timeSlot = Integer.parseInt(request.getParameter("timeSlot" + s));
                    DAO.INSTANCE.insertSchedule(cId, s, timeSlot);
                }
            }
            DAO.INSTANCE.insertNotification(uName, "User " + user.getUsername() + " accepted your request, "
                    + "please check your class information", now().toString(), "/PRJ301_SE1761_FA23_HE171380/VerifyClass?cId=" + cId, user.getUsername());
            response.sendRedirect("HomeControl");
        } catch (Exception e) {
            System.out.println(e);
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
