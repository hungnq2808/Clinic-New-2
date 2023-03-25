/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.ServiceDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Service;

/**
 *
 * @author Admin
 */
public class ManageService extends HttpServlet {

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
        ServiceDAO servicedao = new ServiceDAO();
        String action = request.getParameter("action");
        String url = null;
        String alert = null;
        String message = null;
        List<Service> servicelist = null;
        try {
            if (action.equals("all")) {
                servicelist = servicedao.getAllServices();
                url = "servicemanage?action=all";
            }

            if (action.equals("search")) {
                String txt = request.getParameter("txt");
                servicelist = servicedao.getSearch(txt);
                url = "servicemanage?action=search&txt=" + txt;
            }

            if (action.equals("update_status")) {
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                int sid = Integer.parseInt(request.getParameter("id"));
                boolean check = servicedao.updateStatus(status, sid);
                if (check) {
                    alert = "success";
                    message = "Cập nhật thông tin thành công";
                    request.setAttribute("alert", alert);
                    request.setAttribute("message", message);
                    response.sendRedirect("servicemanage?action=all");
                } else {
                    alert = "fail";
                    message = "Cập nhật thông tin thất bại";
                    request.setAttribute("alert", alert);
                    request.setAttribute("message", message);
                    response.sendRedirect("servicemanage?action=all");
                }

            }

            if (action.equals("detail")) {
                int service_id = Integer.parseInt(request.getParameter("id"));
                Service service = new Service();
                service = servicedao.getServiceById(service_id);
                request.setAttribute("service", service);
                request.getRequestDispatcher("admin/servicedetail.jsp").forward(request, response);
            }

            if (action.equals("update_info")) {
                String name = request.getParameter("name");
                int price = Integer.parseInt(request.getParameter("price"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                int sid = Integer.parseInt(request.getParameter("id"));
                Service s = new Service(sid, name, price, status);
                servicedao.updateService(s);
                alert = "success";
                message = "Cập nhật thông tin thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("servicemanage?action=detail&id=" + sid).forward(request, response);
            }

            if (action.equals("add")) {
                request.getRequestDispatcher("admin/addservice.jsp").forward(request, response);
            }

            if (action.equals("submit")) {
                String name = request.getParameter("name");
                int price = Integer.parseInt(request.getParameter("price"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                Service s = new Service(name, price, status);
                
                servicedao.addNewService(s);
                alert = "success";
                message = "Thêm mới thành công";
                request.setAttribute("alert", alert);
                request.setAttribute("message", message);
                request.getRequestDispatcher("servicemanage?action=all").forward(request, response);
            }

            if (servicelist != null) {
                int page, numperpage = 8;
                int size = servicelist.size();
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
                List<Service> service = servicedao.getListByPage(servicelist, start, end);
                request.setAttribute("page", page);
                request.setAttribute("num", num);
                request.setAttribute("url", url);
                request.setAttribute("service", service);
                request.getRequestDispatcher("admin/service.jsp").forward(request, response);
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
