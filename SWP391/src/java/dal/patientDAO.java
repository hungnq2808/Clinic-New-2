/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Patient;

/**
 *
 * @author Quang Anh
 */
public class PatientDAO extends DBContext {

    public List<Patient> getAllPatient() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[patient_name]\n"
                + "      ,[patient_phone_number]\n"
                + "      ,[patient_email]\n"
                + "      ,[patient_gender]\n"
                + "      ,[patient_address]\n"
                + "      ,[dob]\n"
                + "  FROM [dbo].[patients]\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            Patient pd;
            while (rs.next()) {
                pd = new Patient();
                pd.setId(rs.getInt("id"));
                pd.setPatientName(rs.getString("patient_name"));
                pd.setPatientPhoneNumber(rs.getString("patient_phone_number"));
                pd.setPatientEmail(rs.getString("patient_email"));
                pd.setPatientGender(rs.getBoolean("patient_gender"));
                pd.setPatientAddress(rs.getString("patient_address"));
                pd.setPatientDob(rs.getDate("dob"));
                list.add(pd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Patient getPatientById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[patient_name]\n"
                + "      ,[patient_phone_number]\n"
                + "      ,[patient_email]\n"
                + "      ,[patient_gender]\n"
                + "      ,[patient_address]\n"
                + "      ,[dob]\n"
                + "  FROM [dbo].[patients]\n"
                + "  where id = ?";

        Patient pd;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                pd = new Patient();
                pd.setId(rs.getInt("id"));
                pd.setPatientName(rs.getString("patient_name"));
                pd.setPatientPhoneNumber(rs.getString("patient_phone_number"));
                pd.setPatientEmail(rs.getString("patient_email"));
                pd.setPatientGender(rs.getBoolean("patient_gender"));
                pd.setPatientAddress(rs.getString("patient_address"));
                pd.setPatientDob(rs.getDate("dob"));
                return pd;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean checkExist(String name, String phone, String email) {
        String sql = "SELECT [id]\n"
                + "      ,[patient_name]\n"
                + "      ,[patient_phone_number]\n"
                + "      ,[patient_email]\n"
                + "      ,[patient_gender]\n"
                + "      ,[patient_address]\n"
                + "      ,[dob]\n"
                + "  FROM [dbo].[patients]\n"
                + "  where patient_name = ? and patient_phone_number = ? and patient_email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, phone);
            st.setString(3, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void createPatientsDetails(Patient pd) {
        String sql = "INSERT INTO [dbo].[patients]\n"
                + "           ([patient_name]\n"
                + "           ,[patient_phone_number]\n"
                + "           ,[patient_email]\n"
                + "           ,[patient_gender]\n"
                + "           ,[patient_address]\n"
                + "           ,[dob])\n"
                + "     VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pd.getPatientName());
            st.setString(2, pd.getPatientPhoneNumber());
            st.setString(3, pd.getPatientEmail());
            st.setBoolean(4, pd.isPatientGender());
            st.setString(5, pd.getPatientAddress());
            st.setDate(6, pd.getPatientDob());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public ArrayList<Patient> searchByName(String name) {
        ArrayList<Patient> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[patient_name]\n"
                + "      ,[patient_phone_number]\n"
                + "      ,[patient_email]\n"
                + "      ,[patient_gender]\n"
                + "      ,[patient_address]\n"
                + "      ,[dob]\n"
                + "  FROM [dbo].[patients]\n"
                + "  WHERE [patient_name] LIKE ?";
        Patient p ;
        try {
            
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%"+name+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p = new Patient(rs.getInt("id"), rs.getString("patient_name"), rs.getString("patient_phone_number"), rs.getString("patient_email"), rs.getBoolean("patient_gender"), rs.getString("patient_address"), rs.getDate("dob"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

    public Patient getPatientsDetailsByOtherFeature(String name, String phone, String email) {
        String sql = "SELECT [id]\n"
                + "      ,[patient_name]\n"
                + "      ,[patient_phone_number]\n"
                + "      ,[patient_email]\n"
                + "      ,[patient_gender]\n"
                + "      ,[patient_address]\n"
                + "      ,[dob]\n"
                + "  FROM [dbo].[patients]\n"
                + "  where patient_name = ? and patient_phone_number = ? and patient_email = ?";
        Patient pd;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, phone);
            st.setString(3, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                pd = new Patient();
                pd.setId(rs.getInt("id"));
                pd.setPatientName(rs.getString("patient_name"));
                pd.setPatientPhoneNumber(rs.getString("patient_phone_number"));
                pd.setPatientEmail(rs.getString("patient_email"));
                pd.setPatientGender(rs.getBoolean("patient_gender"));
                pd.setPatientAddress(rs.getString("patient_address"));
                pd.setPatientDob(rs.getDate("dob"));
                return pd;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean updatePatient(Patient p) {
        String sql = "UPDATE [dbo].[patients]\n"
                + "   SET [patient_name] = ?\n"
                + "      ,[patient_phone_number] = ?\n"
                + "      ,[patient_email] = ?\n"
                + "      ,[patient_gender] = ?\n"
                + "      ,[patient_address] = ?\n"
                + "      ,[dob] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getPatientName());
            st.setString(2, p.getPatientPhoneNumber());
            st.setString(3, p.getPatientEmail());
            st.setBoolean(4, p.isPatientGender());
            st.setString(5, p.getPatientAddress());
            st.setDate(6, p.getPatientDob());
            st.setInt(7, p.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    public void deletePatientsById(int id) {
        String sql = "DELETE FROM [dbo].[patients]\n"
                + "      WHERE id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<Patient> getListByPage(List<Patient> list,
            int start, int end) {
        ArrayList<Patient> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

//    public static void main(String[] args) {
//        PatientDAO pd = new PatientDAO();
//        Patient p = new Patient(1,"Anh Quan", "0828065014", "quan@gmail.com", true, "ajshdqw", Date.valueOf("2002-8-13"));
//        pd.updatePatient(p);
//    }
}
