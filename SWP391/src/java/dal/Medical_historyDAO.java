/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdk.nashorn.internal.lookup.Lookup;
import model.Appointment;
import model.Medical_history;
import model.Patient;
import model.Slot;
import model.UserAccount;

/**
 *
 * @author User
 */
public class Medical_historyDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    PatientDAO pDAO = new PatientDAO();

    public void insert(Medical_history m) throws Exception {
        String sql = "INSERT INTO [dbo].[medical_history]\n"
                + "           ([disease]\n"
                + "           ,[date]\n"
                + "           ,[note]\n"
                + "           ,[patient_id]\n"
                + "           ,[reexam_medical])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);

            ps.setString(1, m.getDisease());
            ps.setString(2, m.getDate());
            ps.setString(3, m.getNote());
            ps.setInt(4, m.getPatient_id().getId());
            ps.setBoolean(5, m.isReexam_medical());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Medical_history mh) throws Exception {
        String sql = "UPDATE [dbo].[medical_history]\n"
                + "   SET [disease] = ?\n"
                + "      ,[date] = ?\n"
                + "      ,[note] = ?\n"
                //+ "      ,[patient_id] = ?\n"
                + "      ,[reexam_medical] = ?\n"
                + " WHERE id= ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, mh.getDisease());
            ps.setString(2, mh.getDate());
            ps.setString(3, mh.getNote());
            ps.setBoolean(4, mh.isReexam_medical());
            ps.setInt(5, mh.getId());
            // st.setInt(6, mh.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Medical_history> getMedicalHistory() throws Exception {
        ArrayList<Medical_history> list = new ArrayList<>();
        String sql = "select * from medical_history";
        try {
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Medical_history mh = new Medical_history();
                mh.setId(rs.getInt("id"));
                mh.setDisease(rs.getString("disease"));
                mh.setDate(rs.getString("date"));

                mh.setNote(rs.getString("note"));
                Patient p = pDAO.getPatientById(rs.getInt("patient_id"));
                mh.setPatient_id(p);
                mh.setReexam_medical(rs.getBoolean("reexam_medical"));
                list.add(mh);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Medical_history> getMedicalHistoryByPa(int pa_id) throws Exception {
        ArrayList<Medical_history> list = new ArrayList<>();
        String sql = "select * from medical_history where patient_id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, pa_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medical_history mh = new Medical_history();
                mh.setId(rs.getInt("id"));
                mh.setDisease(rs.getString("disease"));
                mh.setDate(rs.getString("date"));

                mh.setNote(rs.getString("note"));
                Patient p = pDAO.getPatientById(rs.getInt("patient_id"));
                mh.setPatient_id(p);
                mh.setReexam_medical(rs.getBoolean("reexam_medical"));
                list.add(mh);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean checkExist(int mhid) {
        String sql = "SELECT [id]\n"
                + "      ,[disease]\n"
                + "      ,[date]\n"
                + "      ,[note]\n"
                + "      ,[patient_id]\n"
                + "      ,[reexam_medical]\n"
                + "  FROM [dbo].[medical_history]\n"
                + "  where id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mhid);
            
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public Medical_history getMedicalByID(int id) throws Exception {
        String sql = "select * from medical_history where id = ?";
        try {

            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Medical_history mh = new Medical_history();
                mh.setId(rs.getInt("id"));
                mh.setDisease(rs.getString("disease"));
                mh.setDate(rs.getString("date"));

                mh.setNote(rs.getString("note"));
                Patient p = pDAO.getPatientById(rs.getInt("patient_id"));
                mh.setPatient_id(p);
                mh.setReexam_medical(rs.getBoolean("reexam_medical"));
                return mh;

            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void deleteAllMedicalHistoryByPatient(Patient p) {
        String sql = "DELETE FROM [dbo].[medical_history]\n"
                + "      WHERE patient_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, p.getId());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) throws Exception {
        Medical_historyDAO mh = new Medical_historyDAO();
        PatientDAO pdaoAO = new PatientDAO();
//        Patients p = new Patients();
//        p = pdaoAO.getPatientByID(1);
        //        Medical_history m = new Medical_history(1, "Ngu", null, "ngu", p, true);
//        mh.insert(m);
        //ArrayList<Medical_history> list = mh.getMedicalHistoryByPa(1);
//        
        //for (Medical_history medical_history : list) {
        // System.out.println(medical_history);
//        }
//        Medical_history m = new Medical_history();
//        m = mh.getMedicalByID(2);
//        System.out.println(m);
        //}
        Medical_history m = new Medical_history();

        m.setDisease("dau chym");
        m.setDate("2023-02-17");
        m.setNote("dau mong");

        m.setReexam_medical(false);
        m.setPatient_id(pdaoAO.getPatientById(3));
        mh.update(m);

        System.out.println(m);

    }
}
