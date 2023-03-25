/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.AppointmentDAO;
import dal.Medical_historyDAO;
import dal.PatientDAO;
import dal.ServiceAppointmentDAO;
import dal.ServiceDAO;
import dal.SlotDAO;
import dal.SpecializationDAO;
import dal.UserAccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Appointment;
import model.Patient;
import model.ServiceAppointments;
import model.Slot;
import model.UserAccount;

/**
 *
 * @author User
 */
public class Doctorbooking extends HttpServlet {

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
            out.println("<title>Servlet Doctorbooking</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Doctorbooking at " + request.getContextPath() + "</h1>");
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
        PatientDAO pdao = new PatientDAO();
        UserAccountDAO udao = new UserAccountDAO();
        SlotDAO sdao = new SlotDAO();
        AppointmentDAO adao = new AppointmentDAO();
        ArrayList<Slot> listSlot = sdao.getAllSlot();
        request.setAttribute("lsS", listSlot);
        request.getRequestDispatcher("doctorbookingappoinment.jsp").forward(request, response);
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
        PatientDAO pdao = new PatientDAO();
        HttpSession session = request.getSession();
        UserAccountDAO udao = new UserAccountDAO();
        SlotDAO sdao = new SlotDAO();
        AppointmentDAO adao = new AppointmentDAO();
        String paId_raw = request.getParameter("pid");
        UserAccount ua = (UserAccount)session.getAttribute("account");
        

        Date date = Date.valueOf(request.getParameter("date"));
        String slotid_raw = request.getParameter("slotid");
        String symptom = request.getParameter("symptom");

        int paId, slotid;
        String code;
        code = adao.randomClinicCode(8);
        
        paId = 1002;

        

        slotid = Integer.parseInt(slotid_raw);

        Appointment app = new Appointment(pdao.getPatientById(paId), ua, date, symptom, sdao.getSlotById(slotid), code, false, true, true);
        adao.createAnAppointment(app);
        response.sendRedirect("appointmentDoctor.jsp");

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
