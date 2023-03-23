/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Medicine;

/**
 *
 * @author FOR GAMER
 */
public class MedicineDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext dbc = new DBContext();
    Connection connection = null;

    public List<Medicine> getAllMedicine() throws SQLException, Exception {
        List<Medicine> list = new ArrayList<>();
        String GET_ALL_MEDICINE = "SELECT * FROM [medicines]";
        try {
            ps = connection.prepareStatement(GET_ALL_MEDICINE);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                Date expiry_date = rs.getDate(7);
                Medicine medicine = new Medicine(id,
                        name,
                        price,
                        expiry_date);
                list.add(medicine);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public boolean deleteMedicine(int id) throws SQLException, Exception {
        String DELETE_PATIENT_BY_ID = "delete from medicines where id=?";
        try {
            ps = connection.prepareStatement(DELETE_PATIENT_BY_ID);
            ps.setInt(1, id);
            if (ps.executeUpdate() >= 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    public List<Medicine> getListByPage(List<Medicine> list,
            int start, int end) {
        ArrayList<Medicine> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public int CountMedicines() {
        int count = 0;
        String sql = "select count(*) from medicines";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return count;
    }

    public Medicine addMedcine(String name, Float price, String description, int quantity, Date expiry_date) throws SQLException, Exception {
        String ADD_MEDICINE = "INSERT INTO medicines (name, price, description, quantity, expiry_date)\n"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(ADD_MEDICINE);
            ps.setString(1, name);
            ps.setFloat(2, price);
            ps.setString(3, description);
            ps.setInt(4, quantity);
            ps.setDate(5, expiry_date);
            rs = ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public Medicine getMedicineById(int id) throws SQLException, Exception {
        String GET_MEDICINE_QUERY = "select * from medicines where id = ?";
        try {

            ps = connection.prepareStatement(GET_MEDICINE_QUERY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                return new Medicine(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDate(7));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public void updateMedicine(String name, Float price, String description, int quantity, Date expiry_date, int id) throws SQLException, Exception {
        String UPDATE_MEDICINE_QUERY = "update medicines SET name = ?,price = ?, description = ?, quantity = ?, expiry_date = ? where id = ?";
        try {
            ps = connection.prepareStatement(UPDATE_MEDICINE_QUERY);
            ps.setString(1, name);
            ps.setFloat(2, price);
            ps.setString(3, description);
            ps.setInt(4, quantity);
            ps.setDate(5, expiry_date);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<Medicine> searchMedicine(String search) throws SQLException, Exception {
        String SEARCH_PATIENTS_QUERY = "select * from [medicines] where [name] like '%" + search + "%'";
        List<Medicine> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(SEARCH_PATIENTS_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                Date expiry_date = rs.getDate(7);
                Medicine medicine = new Medicine(id,
                        name,
                        price,
                        expiry_date);
                list.add(medicine);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        MedicineDAO patient = new MedicineDAO();
        System.out.println(patient.deleteMedicine(9));
        // System.out.println(patient.addPatient("0912123456", "123@gam.a", "Ha", "Ha",
        // new Date(2014, 02, 03), true, "123 Ha Ha"));
        // System.out.println(patient.deletePatient(1));
//         System.out.println(patient.getPatientById(3));
    }
}
