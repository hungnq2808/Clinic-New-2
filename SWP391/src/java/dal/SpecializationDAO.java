/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Specialization;

/**
 *
 * @author Quang Anh
 */
public class SpecializationDAO extends DBContext {

    public Specialization getSpecializationById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[specializations]\n"
                + "  where id = ?";
        Specialization spe;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                spe = new Specialization();
                spe.setId(rs.getInt("id"));
                spe.setName(rs.getString("name"));
                return spe;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Specialization> getAllSpecialization() {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[specializations]";
        ArrayList<Specialization> list = new ArrayList<>();
        Specialization s;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                s = new Specialization();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void addSpecialization(Specialization s) {
        String sql = "INSERT INTO [dbo].[specializations]\n"
                + "           ([name])\n"
                + "     VALUES (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getName());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public static void main(String[] args) {
        SpecializationDAO sd = new SpecializationDAO();
        Specialization s = new Specialization("Gan");
        System.out.println(sd.getSpecializationById(1).getName());
//        sd.addSpecialization(s);
//        
//        ArrayList<Specialization> ls = sd.getAllSpecialization();
//        for (Specialization l : ls) {
//            System.out.println(l.getName());
//        }
    }
}
