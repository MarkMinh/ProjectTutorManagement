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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import java.io.File;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50) // 50 MB
public class EditImgControl extends HttpServlet {

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
            out.println("<title>Servlet EditImgControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditImgControl at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("Views/EditAvatar.jsp");
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
            Part part = request.getPart("image");
            String realPath = "F:/ProjectPRJ/PRJ301_SE1761_FA23_HE171380/web/img";
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            if (fileName == null) {
                HttpSession ses = request.getSession();
                User user = (User) ses.getAttribute("user");
                fileName = user.getImg();
            }
            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }
            
            part.write(realPath + File.separator + fileName);
            HttpSession ses = request.getSession();
            User user = (User) ses.getAttribute("user");
            user.setImg(fileName);
            
            ArrayList<Province> listProvince = DAO.INSTANCE.getAllProvince();
   
        if (ses.getAttribute("user") != null) {
   
            ArrayList<District> listDistrict = DAO.INSTANCE.getAllDistrictByProvinceId(user.getProvinceId());
            ArrayList<Ward> listWard = DAO.INSTANCE.getAllWardByDistrictId(user.getDistrictId());
            request.setAttribute("listProvince", listProvince);
            request.setAttribute("listDistrict", listDistrict);
            request.setAttribute("listWard", listWard);
        }
            
            DAO.INSTANCE.UpdateAvatar(fileName, user.getUsername());

            request.getRequestDispatcher("Views/Edit.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("loi anh" + e);
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
