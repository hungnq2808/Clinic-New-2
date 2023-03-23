/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.AccountPermisionDAO;
import dal.PermisionDAO;
import dal.UserAccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import model.AccountPermision;
import model.Permision;
import model.UserAccount;

/**
 *
 * @author Admin
 */
@MultipartConfig(maxFileSize = 16177216)
public class ManageAccount extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        UserAccountDAO userdao = new UserAccountDAO();
        PermisionDAO roledao = new PermisionDAO();
        AccountPermisionDAO accpermisiondao = new AccountPermisionDAO();
        List<AccountPermision> accountlist = null;
        String url = null;
        String alert = null;
        String message = null;
        String action = request.getParameter("action");
        List<Permision> rolelist = roledao.getAll();
        if (action.equals("all")) {
            url = "accountmanage?action=all";
            accountlist = accpermisiondao.getAllAccountPermision();
            
        }
        if (action.equals("update")) {
            int uid = Integer.parseInt(request.getParameter("id"));
            int role_id = Integer.parseInt(request.getParameter("role_id"));
            accpermisiondao.UpdateRole(uid, role_id);
            response.sendRedirect("accountmanage?action=all");
        }
        if (action.equals("detail")) {
            int uid = Integer.parseInt(request.getParameter("id"));
            AccountPermision account = new AccountPermision();
            account = accpermisiondao.getAccountPermisionByAccount(userdao.getUserAccountById(uid));
            request.setAttribute("role", rolelist);
            request.setAttribute("account", account);
            request.setAttribute("name", account.getUa().getName());
            request.getRequestDispatcher("admin/accountdetail.jsp").forward(request, response);
        }
        if (action.equals("update_image")) {
            int uid = Integer.parseInt(request.getParameter("id"));
            Part image = request.getPart("image");
            if (image != null) {
                try {
                    userdao.UpdateImage(uid, image);
                } catch (SQLException e) {
                }
            }
            alert = "success";
            message = "Cập nhật ảnh thành công";
            request.setAttribute("alert", alert);
            request.setAttribute("message", message);
            request.getRequestDispatcher("accountmanage?action=detail&id=" + uid).forward(request, response);
        }
        if (action.equals("update_account")) {
            int uid = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
            int role_id = Integer.parseInt(request.getParameter("role_id"));
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            userdao.UpdateAccount(uid, name, phone, gender, role_id);
            alert = "success";
            message = "Cập nhật thông tin thành công";
            request.setAttribute("alert", alert);
            request.setAttribute("message", message);
            request.getRequestDispatcher("accountmanage?action=detail&id=" + uid).forward(request, response);
        }
        if (action.equals("filter")) {
            String role_id = request.getParameter("role_id");
            request.setAttribute("role_id", role_id);
            if (role_id.equals("all") ) {
                response.sendRedirect("accountmanage?action=all");
            } else{
                accountlist = accpermisiondao.searchByPermision(Integer.parseInt(role_id));
            }
        }
        if (accountlist != null) {
            int page, numperpage = 8;
            int type = 0;
            int size = accountlist.size();
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
            List<AccountPermision> account = accpermisiondao.getListByPage(accountlist, start, end);
            request.setAttribute("type", type);
            request.setAttribute("url", url);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            request.setAttribute("account", account);
            request.setAttribute("role", rolelist);
            request.getRequestDispatcher("admin/account.jsp").forward(request, response);
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
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ManageAccount.class.getName()).log(Level.SEVERE, null, ex);
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
