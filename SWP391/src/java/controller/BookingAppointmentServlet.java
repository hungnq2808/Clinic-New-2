/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AppointmentDAO;
import dal.PatientDAO;
import dal.ServiceAppointmentDAO;
import dal.ServiceDAO;
import dal.SlotDAO;
import dal.SpecializationDAO;
import dal.UserAccountDAO;
import dal.Validate;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Appointment;
import model.Patient;
import model.UserAccount;
import java.util.Date;
import model.ServiceAppointments;
import model.Slot;

/**
 *
 * @author Quang Anh
 */
public class BookingAppointmentServlet extends HttpServlet {

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
            out.println("<title>Servlet BookingAppointmentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingAppointmentServlet at " + request.getContextPath() + "</h1>");
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
        /*
            name, doctor id,  email, phone, date, time
         */

        SpecializationDAO spd = new SpecializationDAO();
        UserAccountDAO udd = new UserAccountDAO();
        SlotDAO sd = new SlotDAO();
        ArrayList<UserAccount> listUA = udd.getAllDoctor();
        ArrayList<Slot> listSlot = sd.getAllSlot();
        request.setAttribute("lsUA", listUA);
        request.setAttribute("lsS", listSlot);
        request.getRequestDispatcher("bookingappointment.jsp").forward(request, response);
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

        String patientName = request.getParameter("name");
        String drId_raw = request.getParameter("doctorid");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String date = request.getParameter("date");
        String slotId_raw = request.getParameter("slotid");
        String symptom = request.getParameter("symptom");
        String mess = null;
        
        Validate v = new Validate();
        if (v.IsDateBookingOk(date) == false) {
            mess = "Please choose date after today!!";
            SpecializationDAO spd = new SpecializationDAO();
            UserAccountDAO udd = new UserAccountDAO();
            SlotDAO sd = new SlotDAO();
            ArrayList<UserAccount> listUA = udd.getAllDoctor();
            ArrayList<Slot> listSlot = sd.getAllSlot();
            request.setAttribute("lsUA", listUA);
            request.setAttribute("lsS", listSlot);
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("bookingappointment.jsp").forward(request, response);
        } else {

            int drId, slotId;

            try {
                drId = Integer.parseInt(drId_raw);
                slotId = Integer.parseInt(slotId_raw);

                // check exist patient
                PatientDAO pd = new PatientDAO();
                Patient p;
                if (!pd.checkExist(patientName, phone, email)) {
                    p = new Patient();
                    p.setPatientName(patientName);
                    p.setPatientEmail(email);
                    p.setPatientPhoneNumber(phone);
                    pd.createPatientsDetails(p);
                }
                p = pd.getPatientsDetailsByOtherFeature(patientName, phone, email);
                HttpSession session = request.getSession();
                session.setAttribute("patients", p);
                // String ms = "ok";
                // check exist appointment

                AppointmentDAO ad = new AppointmentDAO();
                Appointment a;
                UserAccountDAO uad = new UserAccountDAO();
                SlotDAO sd = new SlotDAO();

                String code;
                do {
                    code = ad.randomClinicCode(8);
                    if (ad.checkExistCode(code) == false) {
                        break;
                    }
                } while (true);
                Appointment ap;
                ServiceAppointments sap;
                ServiceAppointmentDAO sad = new ServiceAppointmentDAO();
                ServiceDAO sed = new ServiceDAO();
                if (ad.checkExist(drId, date, slotId) == false) {
                    a = new Appointment();
                    a.setPd(p);
                    a.setUa(uad.getUserAccountById(drId));
                    a.setDate(java.sql.Date.valueOf(date));
                    a.setSymptom(symptom);
                    a.setSlot(sd.getSlotById(slotId));
                    a.setClinicCode(code);
                    a.setManagerAccept(false);
                    a.setDoctorAccept(false);
                    a.setIsDenided(false);
                    ad.createAnAppointment(a);

                    // tao hoa don co ban
                    ap = ad.getAppointmentByClinicCode(code);
                    sap = new ServiceAppointments();
                    sap.setAppointment(ap);
                    sap.setService(sed.getServiceById(1));
                    sad.createServiceAppointment(sap);
                    response.sendRedirect("patientprofile");
                } else {                  
                    mess = "Doctor have appointment on that day or slot please choose another!!";
                    SpecializationDAO spd = new SpecializationDAO();
                    UserAccountDAO udd = new UserAccountDAO();
                    ArrayList<UserAccount> listUA = udd.getAllDoctor();
                    ArrayList<Slot> listSlot = sd.getAllSlot();
                    request.setAttribute("lsUA", listUA);
                    request.setAttribute("lsS", listSlot);
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("bookingappointment.jsp").forward(request, response);
                }
            } catch (Exception e) {
            }
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
