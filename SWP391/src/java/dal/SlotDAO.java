/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Slot;

/**
 *
 * @author Quang Anh
 */
public class SlotDAO extends DBContext {

    public ArrayList<Slot> getAllSlot() {
        String sql = "SELECT [id]\n"
                + "      ,[date_schedule]\n"
                + "  FROM [dbo].[slot]";
        ArrayList<Slot> ls = new ArrayList<>();
        Slot s;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                s = new Slot();
                s.setId(rs.getInt("id"));
                s.setDate_schedule(rs.getString("date_schedule"));
                ls.add(s);
            }
            return ls;
        } catch (Exception e) {
        }
        return null;
    }

    public Slot getSlotById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[date_schedule]\n"
                + "  FROM [dbo].[slot]\n"
                + "  where id =?";
        
        Slot s;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                s = new Slot();
                s.setId(rs.getInt("id"));
                s.setDate_schedule(rs.getString("date_schedule"));
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        SlotDAO sd = new SlotDAO();
//        ArrayList<Slot> ls = sd.getAllSlot();
//        System.out.println(sd.getSlotById(3).getDate_schedule());
//        for (Slot l : ls) {
//            System.out.println(l.getId() + " " + l.getDate_schedule());
//        }
        System.out.println(sd.getSlotById(2).getId());
    }
}
