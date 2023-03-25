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
import java.sql.SQLException;
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PatientDAO patientdao = new PatientDAO();
        List<Patient> patientlist = null;
        String url = null;
        String alert = null;
        String message = null;
        String action = request.getParameter("action");

        try {
            if (action.equals("all")) {
                url = "patient?action=all";
                patientlist = patientdao.getAllPatient();
            }

            if (action.equals("search")) {
                String search = request.getParameter("search");
                url = "patient?action=search&search=" + search;
                patientlist = patientdao.searchByName(search);
            }

            if (action.equals("detail")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Patient patient = patientdao.getPatientById(pid);
                request.setAttribute("patient", patient);
                request.getRequestDispatcher("admin/patientdetail.jsp").forward(request, response);
            }

            if (action.equals("update_patient")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                String phone = request.getParameter("phone");
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String address = request.getParameter("address");
                boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
                Date dob = Date.valueOf(request.getParameter("DOB"));
                Patient p = new Patient(pid, name, phone, email, gender, address, dob);
                boolean check = patientdao.updatePatient(p);
                if (check==true) {
                    alert = "success";
                    message = "Cập nhật thông tin thành công";
                    request.setAttribute("alert", alert);
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("patient?action=all").forward(request, response);
                } else {
                    alert = "failed";
                    message = "Cập nhật thông tin thất bại";
                    request.setAttribute("alert", alert);
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("patient?action=detail&pid="+pid).forward(request, response);
                }
            }

            if (patientlist != null) {
                int page, numperpage = 8;
                int size = patientlist.size();
                int num = (size % 8 == 0 ? (size / 8) : ((size / 8)) + 1);//so trang
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<Patient> patientDetails = patientdao.getListByPage(patientlist, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("url", url);
                request.setAttribute("list", patientlist);
                request.setAttribute("patientDetails", patientDetails);
                request.getRequestDispatcher("admin/patient.jsp").forward(request, response);
            }

        } catch (IOException | ServletException e) {
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
