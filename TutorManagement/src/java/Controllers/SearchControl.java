/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.Province;
import Models.RequestDetail;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class SearchControl extends HttpServlet {

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
            out.println("<title>Servlet SearchControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchControl at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Views/LoadDBStd.jsp").forward(request, response);
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
        ArrayList subjects = DAO.INSTANCE.loadAllSubjects();
        request.setAttribute("subjects", subjects);

        int[] gradeLv = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        request.setAttribute("gradeLv", gradeLv);

        ArrayList<RequestDetail> listAll = DAO.INSTANCE.loadAllDetails();
//
//        // filter
        ArrayList<RequestDetail> list = new ArrayList<>();
        for (RequestDetail r : listAll) {
            list.add(r);
        }

        ArrayList<Province> listProvince = DAO.INSTANCE.getAllProvince();

        request.setAttribute("listProvince", listProvince);

        if (request.getParameter("provinceS") != null) {
            if (!request.getParameter("provinceS").equals("0")) {
                String provinceS = request.getParameter("provinceS");
                list = new ArrayList<>();

                for (RequestDetail u : listAll) {
                    if (DAO.INSTANCE.getUser(u.getUsername()).getProvinceId() == Integer.parseInt(provinceS)) {
                        list.add(u);
                    }
                }
                request.setAttribute("provinceS", provinceS);
            }

        }

        if (request.getParameter("districtS") != null) {
            if (!request.getParameter("districtS").equals(0 + "")) {
                String districtS = request.getParameter("districtS");
                list = new ArrayList<>();

                for (RequestDetail u : listAll) {
                    if (DAO.INSTANCE.getUser(u.getUsername()).getDistrictId() == Integer.parseInt(districtS)) {
                        list.add(u);
                    }
                }
                request.setAttribute("districtS", districtS);
            }

        }

        if (request.getParameter("wardS") != null) {
            if (!request.getParameter("wardS").equals(0 + "")) {
                String wardS = request.getParameter("wardS");
                list = new ArrayList<>();
                for (RequestDetail u : listAll) {
                    if (DAO.INSTANCE.getUser(u.getUsername()).getWardId() == Integer.parseInt(wardS)) {
                        list.add(u);
                    }
                }
                request.setAttribute("wardS", wardS);
            }
        }

        ArrayList<RequestDetail> listBySubjects = new ArrayList<>();
        if (request.getParameter("subjectS") != null) {
            if (!request.getParameter("subjectS").equals(999 + "")) {
                String subjectS = request.getParameter("subjectS");
                request.setAttribute("subjectS", subjectS);
                for (RequestDetail u : listAll) {
                    if ((u.getSubjectId() + "").equals(subjectS)) {
                        listBySubjects.add(u);
                    }
                }
            }
        }
//        request.setAttribute("subjectS", subjectS);

        ArrayList<RequestDetail> listByGender = new ArrayList<>();
        if (request.getParameter("genderS") != null) {
            if (!request.getParameter("genderS").equals(999 + "")) {
                try {
                    int genderS = Integer.parseInt(request.getParameter("genderS"));
                    request.setAttribute("genderS", genderS);
                    for (RequestDetail u : listAll) {
                        if (u.getUserR().getGender() == (genderS)) {
                            listByGender.add(u);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }

        ArrayList<RequestDetail> listByGrade = new ArrayList<>();
        if (request.getParameter("gradeS") != null) {
            if (!request.getParameter("gradeS").equals(999 + "")) {
                String gradeS = request.getParameter("gradeS");
                request.setAttribute("gradeS", gradeS);
                for (RequestDetail u : listAll) {
                    if ((u.getGradeLevel() + "").equals(gradeS)) {
                        listByGrade.add(u);
                    }
                }
            }
        }

        ArrayList<RequestDetail> listByMoney = new ArrayList<>();
        if (request.getParameter("moneyS") != null) {
            if (!(request.getParameter("moneyS").trim().equals("")) && !(request.getParameter("moneyS").isEmpty())) {
                try {
                    double moneyS = Double.parseDouble(request.getParameter("moneyS"));
                    request.setAttribute("moneyS", moneyS);
                    for (RequestDetail u : listAll) {
                        if (u.getHourlyMinRate() <= moneyS && u.getHourlyMaxRate() >= moneyS) {
                            listByMoney.add(u);
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        ArrayList<RequestDetail> listByName = new ArrayList<>();
        if (request.getParameter("nameS") != null) {
            if (!(request.getParameter("nameS").trim().equals("")) && !(request.getParameter("nameS").isEmpty())) {
                String nameS = request.getParameter("nameS");
                request.setAttribute("nameS", nameS);
                for (RequestDetail u : listAll) {
                    if (u.getUserR().getFullName().contains(nameS)) {
                        listByName.add(u);
                    }
                }
            }
        }
        ArrayList<RequestDetail> intersection = new ArrayList<>();
        if (!listAll.isEmpty()) {
            for (RequestDetail rd : listAll) {
                intersection.add(rd);
            }
        }

        if (!list.isEmpty()) {
            intersection.retainAll(list);
        }

        if (!listBySubjects.isEmpty()) {
            intersection.retainAll(listBySubjects);
        }
        if (!listByGrade.isEmpty()) {
            intersection.retainAll(listByGrade);
        }
        if (!listByGender.isEmpty()) {
            intersection.retainAll(listByGender);
        }
        if (!listByMoney.isEmpty()) {
            intersection.retainAll(listByMoney);
        }
        if (!listByName.isEmpty()) {
            intersection.retainAll(listByName);
        }

        if ((list.isEmpty()) && (listByGender.isEmpty()) && (listByGrade.isEmpty()) && (listByMoney.isEmpty()) && (listByName.isEmpty()) && (listBySubjects.isEmpty())) {
            intersection = new ArrayList<>();
        }
        request.setAttribute("listStd", intersection);
        request.setAttribute("dao", DAO.INSTANCE);

        request.getRequestDispatcher("Views/SearchStd.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
