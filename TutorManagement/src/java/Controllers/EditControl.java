/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.District;
import Models.Province;
import Models.User;
import Models.Ward;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.time.LocalDateTime.now;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class EditControl extends HttpServlet {

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
            out.println("<title>Servlet EditControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditControl at " + request.getContextPath() + "</h1>");
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
        ArrayList<Province> listProvince = DAO.INSTANCE.getAllProvince();
        HttpSession ses = request.getSession();
        if (ses.getAttribute("user") != null) {
            User user = (User) ses.getAttribute("user");
            ArrayList<District> listDistrict = DAO.INSTANCE.getAllDistrictByProvinceId(user.getProvinceId());
            ArrayList<Ward> listWard = DAO.INSTANCE.getAllWardByDistrictId(user.getDistrictId());
            request.setAttribute("listProvince", listProvince);
            request.setAttribute("listDistrict", listDistrict);
            request.setAttribute("listWard", listWard);
        }

        request.setAttribute("dao", DAO.INSTANCE);

        request.getRequestDispatcher("Views/Edit.jsp").forward(request, response);
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
        String fullName = request.getParameter("fullName");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");

//        out.println(fullName);
//        out.println(dob);
//        out.println(email);
//        out.println(phoneNumber);
        try {
            int provinceId = Integer.parseInt(request.getParameter("province"));
            int districtId = Integer.parseInt(request.getParameter("district"));
            int wardId = Integer.parseInt(request.getParameter("ward"));
            int gender = Integer.parseInt(request.getParameter("gender"));

//            Part part = request.getPart("image");
//            String realPath = request.getServletContext().getRealPath("/uploads");
//            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//            if (fileName == null) {
//                HttpSession ses = request.getSession();
//                User user = (User) ses.getAttribute("user");
//                fileName = user.getImg();
//            }
//            if (!Files.exists(Paths.get(realPath))) {
//                Files.createDirectory(Paths.get(realPath));
//            }
            HttpSession ses = request.getSession();
            User user = (User) ses.getAttribute("user");
            if (user.getIsActive() != 1) {
                DAO.INSTANCE.Update(gender, dob, user.getEmail(), phoneNumber, user.getImg(),
                        now().toString(), fullName, provinceId, districtId, wardId, user.getUsername(), 1);
            } else {
                DAO.INSTANCE.Update(gender, dob, email, user.getPhoneNumber(), user.getImg(),
                        now().toString(), fullName, provinceId, districtId, wardId, user.getUsername(), 1);
            }
            user.setDistrictId(districtId);
            user.setWardId(wardId);
            user.setProvinceId(provinceId);
            if (user.getIsActive() != 1) {
                user.setPhoneNumber(phoneNumber);
            }

            if (user.getIsActive() == 1) {
                user.setEmail(email);
            }

            user.setDob(dob);
            user.setFullName(fullName);
            user.setGender(gender);
            user.setIsActive(1);
//            out.println(provinceId);
//            out.println(districtId);
//            out.println(wardId);
//            out.println(gender);
//            out.println(fileName);

//            
            response.sendRedirect("HomeControl");
//            part.write(realPath + "/" + fileName);
//            PrintWriter out = response.getWriter();
//            try {
//                out.println("<img src='uploads/" + fileName + "'>");
//            } catch (Exception e) {
//                System.out.println(e);
//            }

        } catch (Exception e) {
            System.out.println("UPDATE LOI " + e);
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
