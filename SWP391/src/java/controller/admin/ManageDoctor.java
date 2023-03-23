/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import configs.EncodeData;
import configs.Validate;
import dal.AccountPermisionDAO;
import dal.AppointmentDAO;
import dal.LocationDAO;
import dal.PermisionDAO;
import dal.SpecializationDAO;
import dal.UserAccountDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.*;

/**
 *
 * @author admin
 */
@MultipartConfig(maxFileSize = 16177216)
public class ManageDoctor extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        UserAccountDAO doctordao = new UserAccountDAO();
        String action = request.getParameter("action");
        List<UserAccount> doctorlist = null;
        AppointmentDAO appointmentdao = new AppointmentDAO();
        SpecializationDAO specdao = new SpecializationDAO();
        LocationDAO locationdao = new LocationDAO();
        String url = null;
        String alert = null;
        String message = null;
        List<Specialization> specialitylist = specdao.getAllSpecialization();

        try {
            if (action.equals("all")) {
                url = "doctormanage?action=all";
                doctorlist = doctordao.getAllDoctor();
            }
            if (action.equals("filter")) {
                String gender = request.getParameter("gender");
                String speciality = request.getParameter("speciality");
                request.setAttribute("gender", gender);
                request.setAttribute("speciality1", speciality);
                if (gender.equals("all") && speciality.equals("all")) {
                    response.sendRedirect("doctormanage?action=all");
                } else if (gender.equals("all")) {
                    doctorlist = doctordao.searchBySpecialization(speciality);
                } else if (speciality.equals("all")) {
                    doctorlist = doctordao.searchByGender(gender);
                } else {
                    doctorlist = doctordao.searchByFilter(gender, speciality);
                }
                url = "doctormanage?action=filter&gender=" + gender + "&speciality=" + speciality;
            }
            if (action.equals("search")) {
                String text = request.getParameter("txt");
                doctorlist = doctordao.searchByName(text);
                url = "doctormanage?action=search&txt=" + text;
            }
            if (action.equals("add")) {
                request.setAttribute("speciality", specialitylist);
                PermisionDAO pd = new PermisionDAO();
                LocationDAO ld = new LocationDAO();
                request.setAttribute("lsld", ld.getAllLocation());
                request.setAttribute("lsper", pd.getAll());
                request.getRequestDispatcher("admin/adddoctor.jsp").forward(request, response);
            }
            if (action.equals("submit")) {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                String phone = request.getParameter("phone");
                String rgender = request.getParameter("gender");
                String address = request.getParameter("address");
                String img = request.getParameter("img");
                String locationid_raw = request.getParameter("locationid");
                
                LocationDAO ld = new LocationDAO();
                
                Location l = ld.getLocationById(Integer.parseInt(locationid_raw));
                Specialization specialization = specdao.getSpecializationById(Integer.parseInt(request.getParameter("speciality")));
                Date dob = Date.valueOf(request.getParameter("dob"));
                String description = request.getParameter("description");
                boolean status = true;
                String enpassword = EncodeData.enCode(password);
                boolean gender = Boolean.parseBoolean(rgender);
                String fullname = Validate.capitalizeFirstLetter(name);
                if (doctordao.isExist(email)) {
                    doctorlist = doctordao.getAllDoctor();
                    request.setAttribute("doctorlist", doctorlist);
                    request.setAttribute("speciality", specialitylist);
                    request.setAttribute("email", email);
                    request.setAttribute("password", password);
                    request.setAttribute("repassword", repassword);
                    request.setAttribute("name", name);
                    request.setAttribute("gender", rgender.equals("true"));
                    request.setAttribute("phone", phone);
                    request.setAttribute("error", "Email đã tồn tại trên hệ thống!");
                    request.getRequestDispatcher("admin/adddoctor.jsp").forward(request, response);
                } else {
                    UserAccount doctor = new UserAccount(name, email, password, phone, gender, address, dob, img, l, specialization);
                    doctordao.createAccount(doctor);
                    
                    AccountPermisionDAO apd = new AccountPermisionDAO();
                    UserAccount uan = doctordao.loginUserAccount(email, password);
                    
                    PermisionDAO perdao = new PermisionDAO();
                    Permision permision = perdao.getPermisionByID(Integer.parseInt(request.getParameter("perid")));
                    
                    apd.addPermisionForAccount(uan, permision);
                    request.getRequestDispatcher("doctormanage?action=all").forward(request, response);
                }
            }
            if (action.equals("detail")) {
                int doctor_id = Integer.parseInt(request.getParameter("id"));
                UserAccount doctor = doctordao.getUserAccountById(doctor_id);
                List<model.Appointment> appointmentlist = appointmentdao.GetListAppointmentByDoctorId(doctor);
                doctor = doctordao.getUserAccountById(doctor_id);
                request.setAttribute("speciality", specialitylist);
                request.setAttribute("appointment", appointmentlist);
                request.setAttribute("doctor", doctor);
                request.getRequestDispatcher("admin/doctordetail.jsp").forward(request, response);
            }
            if (action.equals("update_image")) {
                int doctor_id = Integer.parseInt(request.getParameter("id"));
                Part image = request.getPart("image");
                if (image != null) {
                    try {
                        doctordao.UpdateImage(doctor_id, image);
                    } catch (SQLException e) {
                    }
                }
                alert = "success";
                message = "Cập nhật ảnh thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("doctormanage?action=detail&id=" + doctor_id).forward(request, response);
            }
            if (action.equals("update_info")) {
                int doctor_id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
                String phone = request.getParameter("phone");
                Date DOB = Date.valueOf(request.getParameter("DOB"));
                String description = request.getParameter("description");
                int speciality = Integer.parseInt(request.getParameter("speciality"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                doctordao.DoctorUpdate(doctor_id, name, gender, phone, DOB, description, speciality, status);
                alert = "success";
                message = "Cập nhật thông tin thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("doctormanage?action=detail&id=" + doctor_id).forward(request, response);
            }
            if (doctorlist != null) {
                int page, numperpage = 8;
                int type = 0;
                int size = doctorlist.size();
                int num = (size % 8 == 0 ? (size / 8) : ((size / 8)) + 1);
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start, end;
                start = (page - 1) * numperpage;
                end = Math.min(page * numperpage, size);
                List<UserAccount> doctors = doctordao.getListByPage(doctorlist, start, end);
                request.setAttribute("type", type);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("url", url);
                request.setAttribute("doctor", doctors);
                request.setAttribute("speciality", specialitylist);
                request.getRequestDispatcher("admin/doctor.jsp").forward(request, response);
            }
        } catch (IOException | SQLException | ServletException e) {
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageDoctor.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageDoctor.class.getName()).log(Level.SEVERE, null, ex);
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
