/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
}
