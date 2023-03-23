/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Location;

/**
 *
 * @author Quang Anh
 */
public class LocationDAO extends DBContext {

    public Location getLocationById(int id) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[locations] l\n"
                + "  where l.id = ?";
        Location l;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                l = new Location();
                l.setId(rs.getInt("id"));
                l.setName(rs.getString("name"));
                return l;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void addLocation(Location s) {
        String sql = "INSERT INTO [dbo].[locations]\n"
                + "           ([name])\n"
                + "     VALUES (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, s.getName());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public ArrayList<Location> getAllLocation() {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[locations]";
        Location l;
        ArrayList<Location> ls = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                l = new Location();
                l.setId(rs.getInt("id"));
                l.setName(rs.getString("name"));
                ls.add(l);
            }
            return ls;
        } catch (Exception e) {
        }

        return null;
    }

    public static void main(String[] args) {
        LocationDAO ld = new LocationDAO();
        System.out.println(ld.getLocationById(1).getName());

    }
}
