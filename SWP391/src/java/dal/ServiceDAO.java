/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Service;

/**
 *
 * @author Quang Anh
 */
public class ServiceDAO extends DBContext {

    public Service getServiceById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[services]\n"
                + "  where id = ?";

        Service s;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                s = new Service();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPrice(rs.getInt("price"));
                s.setStatus(rs.getBoolean("status"));
                return s;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public ArrayList<Service> getAllServiceStillServe() {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[services]\n"
                + "  where [status] = 1";
        Service s;
        ArrayList<Service> ls = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                s = new Service();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPrice(rs.getInt("price"));
                s.setStatus(rs.getBoolean("status"));
                ls.add(s);
            }
            return ls;
        } catch (Exception e) {
        }

        return null;
    }

    public ArrayList<Service> getAllServices() {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[services]\n";
        Service s;
        ArrayList<Service> ls = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                s = new Service();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPrice(rs.getInt("price"));
                s.setStatus(rs.getBoolean("status"));
                ls.add(s);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Service> getSearch(String txt) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[services]\n"
                + "WHERE [name] LIKE '%?%'";
        Service s;
        ArrayList<Service> ls = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, txt);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                s = new Service();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setPrice(rs.getInt("price"));
                s.setStatus(rs.getBoolean("status"));
                ls.add(s);
            }
            return ls;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateService(Service s) {
        String sql = "UPDATE [dbo].[services]\n"
                + "   SET [name] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setInt(2, s.getPrice());
            ps.setBoolean(3, s.isStatus());
            ps.setInt(4, s.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateStatus(boolean status, int id) {
        String sql = "UPDATE [dbo].[services]\n"
                + "      ,[status] = ?\n"
                + " WHERE [id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean addNewService(Service s) {
        String sql = "INSERT INTO [dbo].[services]\n"
                + "           ([name]\n"
                + "           ,[price]\n"
                + "           ,[status])\n"
                + "     VALUES(\n"
                + "           ?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, s.getName());
            ps.setInt(2, s.getPrice());
            ps.setBoolean(3, s.isStatus());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public List<Service> getListByPage(List<Service> list,
            int start, int end) {
        ArrayList<Service> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
}
