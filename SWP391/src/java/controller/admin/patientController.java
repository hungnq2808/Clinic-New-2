/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Patient;

/**
 *
 * @author dinhp
 */
public class patientController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String page_raw = request.getParameter("pageid");
        try {
            if (action == null) {
                action = "load";
            } else if (page_raw == null || page_raw.equals("0")) {
                page_raw = "1";
            }
//            Load detail from patient
            if (action.equalsIgnoreCase("load")) {
                PatientDAO dao = new PatientDAO();
                List<Patient> listAllPatient;
                listAllPatient = dao.getAllPatient();
                request.setAttribute("listAllPatient", listAllPatient);
                request.getRequestDispatcher("patient.jsp").forward(request, response);
            }

//              Delete patient
            if (action.equalsIgnoreCase("disable")) {
                String pid = request.getParameter("pid");
                PatientDAO dao = new PatientDAO();
                dao.deletePatientsById(Integer.parseInt(pid));
                response.sendRedirect("patient");
            }

//              Detail patient
            if (action.equalsIgnoreCase("detail")) {
                String pid = request.getParameter("pid");
                PatientDAO dao = new PatientDAO();
                Patient patient = dao.getPatientById(Integer.parseInt(pid));
                request.setAttribute("patient", patient);
                request.getRequestDispatcher("patientdetail.jsp").forward(request, response);
            }
//                      Add user
            if (action.equalsIgnoreCase("add")) {
                request.getRequestDispatcher("patientdetail.jsp").forward(request, response);
                PatientDAO dao = new PatientDAO();
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
                Date dob = Date.valueOf(request.getParameter("dob"));
                String address = request.getParameter("address");
                
                dao.createPatientsDetails(new Patient(firstName+ " " + lastName, phone, email, gender, address, dob));
                request.getRequestDispatcher("patientdetail.jsp").forward(request, response);
            }
//          Update patient
            if (action.equalsIgnoreCase("update")) {
                PatientDAO dao = new PatientDAO();
                int pid = Integer.parseInt(request.getParameter("pid"));
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
                Date dob = Date.valueOf(request.getParameter("dob"));
                String address = request.getParameter("address");
                dao.updatePatient(new Patient(pid,firstName + " " +lastName, phone, email,gender,address,dob));
                request.getRequestDispatcher("patient?action=detail&pid=" + pid).forward(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
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
        processRequest(request, response);

        response.setContentType("text/html;charset=UTF-8");
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
        response.setContentType("text/html;charset=UTF-8");

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
