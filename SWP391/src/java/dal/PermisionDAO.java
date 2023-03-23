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
import model.Permision;

/**
 *
 * @author Quang Anh
 */
public class PermisionDAO extends DBContext {

    public Permision getPermisionByID(int ID) {
        String sql = "SELECT [id]\n"
                + "      ,[permision]\n"
                + "  FROM [dbo].[permision]\n"
                + "  where id = ?";

        Permision per;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                per = new Permision();
                per.setId(rs.getInt("id"));
                per.setPermision(rs.getString("permision"));
                return per;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Permision> getAll() {
        ArrayList<Permision> ls = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[permision]\n"
                + "  FROM [dbo].[permision]";
        
        Permision p;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p = new Permision();
                p.setId(rs.getInt("id"));
                p.setPermision(rs.getString("permision"));
                ls.add(p);
            }
            return ls;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        PermisionDAO perd = new PermisionDAO();
        ArrayList<Permision> ls = perd.getAll();
        for (Permision l : ls) {
            System.out.println(l.getPermision());
        }
    }
}
