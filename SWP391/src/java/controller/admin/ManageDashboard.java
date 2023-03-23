package controller.admin;

import dal.AppointmentDAO;
import dal.UserAccountDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Appointment;
import model.Statistic;
import model.UserAccount;

/**
 *
 * @author Admin
 */
public class ManageDashboard extends HttpServlet {

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
        AppointmentDAO appointmentdao = new AppointmentDAO();
        UserAccountDAO doctordao = new UserAccountDAO();
        HttpSession session = request.getSession();
        try {
            String action = request.getParameter("action");
            List<Appointment> appointmentlist = appointmentdao.getAllAppointment();
            List<Appointment> appointmentstoday = appointmentdao.getAppointmentListByDate("today");
            if (action.equals("default")) {
                List<Statistic> appointment7day = appointmentdao.getDataLast7Day("7day");
                request.setAttribute("Revenue", appointmentdao.sumServiceFee("month"));
                request.setAttribute("appointment7day", appointment7day);
                session.setAttribute("atype", "7day");
                session.setAttribute("rtype", "month");
                request.setAttribute("Revenueappointment", appointmentdao.sumServiceFee("month"));
//                request.setAttribute("Revenuemedicine", appointmentdao.sumMedicineFee("month"));

            }
            if (action.contains("statistic")) {
                String atype = request.getParameter("atype");
                List<Statistic> appointment7day = appointmentdao.getDataLast7Day(atype);
                request.setAttribute("Revenue", appointmentdao.sumServiceFee("month"));
//                request.setAttribute("Revenuemedicine", appointmentdao.sumMedicineFee(rtype));
//                request.setAttribute("Revenueservice", appointmentdao.sumServiceFee(rtype));
                session.setAttribute("atype", atype);
//                session.setAttribute("rtype", rtype);
                request.setAttribute("appointment7day", appointment7day);
            }
            request.setAttribute("patient", appointmentdao.CountPatient());
            request.setAttribute("appointment", appointmentdao.CountAppointment());
            request.setAttribute("doctor", doctordao.CountDoctor());
            request.setAttribute("appointmentlist", appointmentlist);
            request.setAttribute("appointmentstoday", appointmentstoday);

            request.getRequestDispatcher("admin/dashboard.jsp").forward(request, response);
        } catch (IOException | ServletException e) {

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
