/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.Medical_historyDAO;
import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Medical_history;
import model.Patient;

/**
 *
 * @author User
 */
public class UpdateMedicalHistory extends HttpServlet {

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

        //String id_raw = request.getParameter("mhid");
        int mhid = 1;
        String disease = request.getParameter("disease");
        String date = request.getParameter("date");
        String note = request.getParameter("note");       
        String reexam_medical_raw = request.getParameter("reexam");


        try {

            //id = Integer.parseInt(id_raw);
            Medical_history medical= mh.getMedicalByID(mhid);
            
            boolean reexam = Boolean.parseBoolean(reexam_medical_raw);
            Medical_history m = new Medical_history(mhid,disease, date, note, medical.getPatient_id(), reexam);

            //Medical_history m = mh.getMedicalByID(pid);
            
            mh.update(m);
            

            //request.getRequestDispatcher("UpdateMedicalHistory.jsp").forward(request, response);
            
            response.sendRedirect("medicalHistory?pid=1002");
        } catch (Exception e) {

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
        //request.getRequestDispatcher("UpdateMedicalHistory.jsp").forward(request, response);
        processRequest(request, response);
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
