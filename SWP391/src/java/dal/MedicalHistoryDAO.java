/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.MedicalHistory;
import model.Patient;

/**
 *
 * @author Quang Anh
 */
public class MedicalHistoryDAO extends DBContext {

    public MedicalHistory getMHById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[disease]\n"
                + "      ,[date]\n"
                + "      ,[note]\n"
                + "      ,[patient_id]\n"
                + "      ,[reexam_medical]\n"
                + "  FROM [dbo].[medical_history]\n"
                + "  where id = ?";
        MedicalHistory mh;
        PatientDAO pd = new PatientDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                mh = new MedicalHistory();
                mh.setId(rs.getInt("id"));
                mh.setDisease(rs.getString("disease"));
                mh.setDate(rs.getString("date"));
                mh.setNote(rs.getString("note"));
                mh.setPaient(pd.getPatientById(rs.getInt("patient_id")));
                mh.setReexamMedical(rs.getBoolean("reexam_medical"));
                return mh;
            }
        } catch (Exception e) {
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
}
