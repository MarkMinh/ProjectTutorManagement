/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.*;
import Models.Paging;

import Models.User;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class LoadDBStd extends HttpServlet {

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
            out.println("<title>Servlet LoadDBStd</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadDBStd at " + request.getContextPath() + "</h1>");
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
        ArrayList subjects = DAO.INSTANCE.loadAllSubjects();
        request.setAttribute("subjects", subjects);

        int[] gradeLv = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        request.setAttribute("gradeLv", gradeLv);

        ArrayList<RequestDetail> listAll = DAO.INSTANCE.loadAllDetails();

        ArrayList<Province> listProvince = DAO.INSTANCE.getAllProvince();
        request.setAttribute("listProvince", listProvince);
        // paging
        int nrpp = 3;
        try {
            nrpp = (int) request.getAttribute("nrpp");
        } catch (Exception e) {
        }
        int index = -1;
        try {
            index = Integer.parseInt(request.getAttribute("index") + "");
        } catch (Exception e) {
        }
        Paging p = new Paging(nrpp, index, listAll.size());
        p.calc();
        request.setAttribute("page", p);
        request.setAttribute("nrppArr", nrppArr);

        request.setAttribute("listStd", listAll);
        request.setAttribute("dao", DAO.INSTANCE);

        request.getRequestDispatcher("Views/LoadDBStudent.jsp").forward(request, response);
    }
    int[] nrppArr = {1, 2, 3, 4, 5, 7, 10, 20, 30, 100, 500};

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

        int index = Integer.parseInt(request.getParameter("index"));
        int totalPage = Integer.parseInt(request.getParameter("totalPage"));
        if (request.getParameter("btnHome") != null) {
            index = 0;
        }
        if (request.getParameter("btnEnd") != null) {
            index = totalPage;
        }
        if (request.getParameter("btnPre") != null) {
            index -= 1;
        }
        if (request.getParameter("btnNext") != null) {
            index += 1;
        }
        for (int i = 0; i < totalPage; i++) {
            if (request.getParameter("btn" + i) != null) {
                index = i;
            }
        }
        int nrpp = Integer.parseInt(request.getParameter("nrpp"));
        request.setAttribute("nrpp", nrpp);
        request.setAttribute("index", index);
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
