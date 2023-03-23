/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.Medical_historyDAO;
import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Medical_history;
import model.Patient;

/**
 *
 * @author User
 */
public class AddMedicalHistory extends HttpServlet {

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
            out.println("<title>Servlet ListDoctor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListDoctor at " + request.getContextPath() + "</h1>");
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
        Medical_historyDAO mh = new Medical_historyDAO();
        PatientDAO pDao = new PatientDAO();

        List<Patient> ls = new ArrayList<>();
        try {
            ls = pDao.getAllPatient();

            request.setAttribute("list", ls);
            request.getRequestDispatcher("addMedical.jsp").forward(request, response);
        } catch (Exception e) {
        }

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
        Medical_historyDAO mh = new Medical_historyDAO();
        PatientDAO pDao = new PatientDAO();
//        List<Patients> ls = new ArrayList<>();
//        try {
//            ls = pDao.getAllPatients();
//            
//            request.setAttribute("list", ls);
//            request.getRequestDispatcher("addMedical.jsp").forward(request, response);
//        } catch (Exception e) {
//        }

        String disease = request.getParameter("disease");
        String date = request.getParameter("date");
        String note = request.getParameter("note");
        String patient_id_raw = request.getParameter("pid");
        String reexam_medical_raw = request.getParameter("reexam");

        int pid;

        try {

            pid = Integer.parseInt(patient_id_raw);
//            String mess = "sucess";
//            request.setAttribute("mess", mess);
//            request.getRequestDispatcher("addMedical.jsp").forward(request, response);
            boolean reexam = reexam_medical_raw.compareToIgnoreCase("") == 0 ? true : false;

//            String mess = "sucess";
//            request.setAttribute("mess", mess);
//            request.getRequestDispatcher("addMedical.jsp").forward(request, response);
            Medical_history m = new Medical_history(disease, date, note, pDao.getPatientById(pid), reexam);
            mh.insert(m);
            request.getRequestDispatcher("appoinmentDoctor").forward(request, response);
            //request.getRequestDispatcher("medicalHistory").forward(request, response);
        } catch (Exception e) {

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
