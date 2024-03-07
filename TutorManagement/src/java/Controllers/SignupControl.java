/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import DAL.MaHoa;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.LocalTime;
import java.util.logging.Level;

/**
 *
 * @author ADMIN
 */
public class SignupControl extends HttpServlet {

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
            out.println("<title>Servlet SignupControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupControl at " + request.getContextPath() + "</h1>");
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
            int rollNo = Integer.parseInt(request.getParameter("rollNo"));
            request.setAttribute("rollNo", rollNo);
        } catch (Exception e) {
            System.out.println(e);
        }

        request.getRequestDispatcher("Views/Signup.jsp").forward(request, response);
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
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("repass");
        String email = request.getParameter("email");
        String rollNo_raw = request.getParameter("rollNo");
        String question = request.getParameter("question");

        try {
            int rollNo;
            if ("".equals(rollNo_raw) || rollNo_raw == null) {
                rollNo = 2;
            } else {
                rollNo = Integer.parseInt(rollNo_raw);
            }
            int questionType = Integer.parseInt(request.getParameter("questionType"));
            if (!pass.equals(re_pass)) {
                request.setAttribute("rollNo", rollNo);
                request.getRequestDispatcher("Views/Signup.jsp").forward(request, response);
            } else {
                User a = DAO.INSTANCE.checkAccountExist(user);
                if (a == null) { // dc sign up
                    
                    String defaultAvatar = "defaultAvatar.jpg";
                    LocalDateTime now = LocalDateTime.now();
                    pass = MaHoa.toSHA1(pass);
                    DAO.INSTANCE.signUp(user, pass, rollNo, question, email, now.toString(), now.toString(), defaultAvatar, questionType);
                    a = DAO.INSTANCE.checkAccountExist(user);
                    HttpSession ses = request.getSession();
                    ses.setAttribute("user", a);
                    response.sendRedirect("/PRJ301_SE1761_FA23_HE171380/LoginControl");
                } else {
                    request.setAttribute("ms", "Username is already exists");
                    request.setAttribute("rollNo", rollNo);
                    request.getRequestDispatcher("Views/Signup.jsp").forward(request, response);
//                    response.sendRedirect("/PRJ301_SE1761_FA23_HE171380/Views/Signup.jsp");
                }
            }
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
