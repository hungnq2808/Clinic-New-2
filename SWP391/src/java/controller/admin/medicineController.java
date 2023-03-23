/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.MedicineDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Medicine;

/**
 *
 * @author FOR GAMER
 */

public class medicineController extends HttpServlet {

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
            }
            if (action.equalsIgnoreCase("load")) {
                MedicineDAO dao = new MedicineDAO();
                List<Medicine> listAllMedicine;
                listAllMedicine = dao.getAllMedicine();
                request.setAttribute("listAllMedicine", listAllMedicine);
                request.getRequestDispatcher("medicine.jsp").forward(request, response);
            }
            if (action.equalsIgnoreCase("detail")) {
                String pid = request.getParameter("pid");
                MedicineDAO dao = new MedicineDAO();
                Medicine medicine = dao.getMedicineById(Integer.parseInt(pid));
                request.setAttribute("medicine", medicine);
                request.getRequestDispatcher("medicinedetail.jsp").forward(request, response);
            }
            if (action.equalsIgnoreCase("add")) {
                request.getRequestDispatcher("medicinedetail.jsp").forward(request, response);
                MedicineDAO dao = new MedicineDAO();
                Float price = Float.parseFloat(request.getParameter("price"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                Date expiry_date = Date.valueOf(request.getParameter("expiry_date"));
                dao.addMedcine(name, price, description, quantity, expiry_date);
            }
            if (action.equalsIgnoreCase("update")) {
                MedicineDAO dao = new MedicineDAO();
                int pid = Integer.parseInt(request.getParameter("pid"));
                Float price = Float.parseFloat(request.getParameter("price"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                Date expiry_date = Date.valueOf(request.getParameter("expiry_date"));
                dao.updateMedicine(name, price, description, quantity, expiry_date, pid);
                request.getRequestDispatcher("medicine?action=detail&pid=" + pid).forward(request, response);
            }
            if (action.equalsIgnoreCase("disable")){
                MedicineDAO dao = new MedicineDAO();
                String pid = request.getParameter("pid");
                dao.deleteMedicine(Integer.parseInt(pid));
                response.sendRedirect("medicine");
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
