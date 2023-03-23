/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import model.AccountPermision;
import model.Location;
import model.Permision;
import model.Specialization;
import model.UserAccount;

/**
 *
 * @author Quang Anh
 */
public class UserAccountDAO extends DBContext {

    public UserAccount getUserAccountById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dob]\n"
                + "      ,[img]\n"
                + "      ,[location_id]\n"
                + "      ,[specialization_id]\n"
                + "  FROM [dbo].[user_account]\n"
                + "  where id = ?";
        LocationDAO ld = new LocationDAO();
        SpecializationDAO spd = new SpecializationDAO();
        UserAccount ud;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ud = new UserAccount();
                ud.setId(rs.getInt("id"));
                ud.setName(rs.getString("name"));
                ud.setEmail(rs.getString("email"));
                ud.setPassword(rs.getString("password"));
                ud.setPhone(rs.getString("phone"));
                ud.setGender(rs.getBoolean("gender"));
                ud.setAddress(rs.getString("address"));
                ud.setDob(rs.getDate("dob"));
                ud.setImg(rs.getString("img"));
                ud.setLocation(ld.getLocationById(rs.getInt("location_id")));
                ud.setSpecialization(spd.getSpecializationById(rs.getInt("specialization_id")));
                return ud;
            }
        } catch (Exception e) {
        }
        return null;
    }
    

    public UserAccount loginUserAccount(String email, String pass) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dob]\n"
                + "      ,[img]\n"
                + "      ,[location_id]\n"
                + "      ,[specialization_id]\n"
                + "  FROM [dbo].[user_account]\n"
                + "  where email = ? and [password] = ?";

        LocationDAO ld = new LocationDAO();
        SpecializationDAO spd = new SpecializationDAO();
        UserAccount ud;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ud = new UserAccount();
                ud.setId(rs.getInt("id"));
                ud.setName(rs.getString("name"));
                ud.setEmail(rs.getString("email"));
                ud.setPassword(rs.getString("password"));
                ud.setPhone(rs.getString("phone"));
                ud.setGender(rs.getBoolean("gender"));
                ud.setAddress(rs.getString("address"));
                ud.setDob(rs.getDate("dob"));
                ud.setImg(rs.getString("img"));
                ud.setLocation(ld.getLocationById(rs.getInt("location_id")));
                ud.setSpecialization(spd.getSpecializationById(rs.getInt("specialization_id")));
                return ud;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<UserAccount> getAllUserDetail() {
        ArrayList<UserAccount> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dob]\n"
                + "      ,[img]\n"
                + "      ,[location_id]\n"
                + "      ,[specialization_id]\n"
                + "  FROM [dbo].[user_account]";
        LocationDAO ld = new LocationDAO();
        SpecializationDAO sped = new SpecializationDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            UserAccount ud;
            while (rs.next()) {
                ud = new UserAccount();
                ud.setId(rs.getInt("id"));
                ud.setName(rs.getString("name"));
                ud.setEmail(rs.getString("email"));
                ud.setPassword(rs.getString("password"));
                ud.setPhone(rs.getString("phone"));
                ud.setGender(rs.getBoolean("gender"));
                ud.setAddress(rs.getString("address"));
                ud.setDob(rs.getDate("dob"));
                ud.setImg(rs.getString("img"));
                ud.setLocation(ld.getLocationById(rs.getInt("location_id")));
                ud.setSpecialization(sped.getSpecializationById(rs.getInt("specialization_id")));
                list.add(ud);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<UserAccount> getAllDoctor() {
        ArrayList<UserAccount> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dob]\n"
                + "      ,[img]\n"
                + "      ,[location_id]\n"
                + "      ,[specialization_id]\n"
                + "  FROM [dbo].[user_account]\n"
                + "  where specialization_id is  not null";
        LocationDAO ld = new LocationDAO();
        SpecializationDAO sped = new SpecializationDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            UserAccount ud;
            while (rs.next()) {
                ud = new UserAccount();
                ud.setId(rs.getInt("id"));
                ud.setName(rs.getString("name"));
                ud.setEmail(rs.getString("email"));
                ud.setPassword(rs.getString("password"));
                ud.setPhone(rs.getString("phone"));
                ud.setGender(rs.getBoolean("gender"));
                ud.setAddress(rs.getString("address"));
                ud.setDob(rs.getDate("dob"));
                ud.setImg(rs.getString("img"));
                ud.setLocation(ld.getLocationById(rs.getInt("location_id")));
                ud.setSpecialization(sped.getSpecializationById(rs.getInt("specialization_id")));
                list.add(ud);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean createAccount(UserAccount ua) {
        String sql = "INSERT INTO [dbo].[user_account]\n"
                + "           ([name]\n"
                + "           ,[email]\n"
                + "           ,[password]\n"
                + "           ,[phone]\n"
                + "           ,[gender]\n"
                + "           ,[address]\n"
                + "           ,[dob]\n"
                + "           ,[img]\n"
                + "           ,[location_id]\n"
                + "           ,[specialization_id])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ua.getName());
            st.setString(2, ua.getEmail());
            st.setString(3, ua.getPassword());
            st.setString(4, ua.getPhone());
            st.setBoolean(5, ua.isGender());
            st.setString(6, ua.getAddress());
            st.setDate(7, ua.getDob());
            st.setString(8, ua.getImg());
            st.setInt(9, ua.getLocation().getId());
            st.setInt(10, ua.getSpecialization().getId());
            st.executeUpdate();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public ArrayList<UserAccount> searchByLocation(int id) {
        ArrayList<UserAccount> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dob]\n"
                + "      ,[img]\n"
                + "      ,[location_id]\n"
                + "      ,[specialization_id]\n"
                + "  FROM [dbo].[user_account]\n"
                + "  where location_id = ?";
        LocationDAO ld = new LocationDAO();
        SpecializationDAO sped = new SpecializationDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            UserAccount ud;
            while (rs.next()) {
                ud = new UserAccount();
                ud.setId(rs.getInt("id"));
                ud.setName(rs.getString("name"));
                ud.setEmail(rs.getString("email"));
                ud.setPassword(rs.getString("password"));
                ud.setPhone(rs.getString("phone"));
                ud.setGender(rs.getBoolean("gender"));
                ud.setAddress(rs.getString("address"));
                ud.setDob(rs.getDate("dob"));
                ud.setImg(rs.getString("img"));
                ud.setLocation(ld.getLocationById(rs.getInt("location_id")));
                ud.setSpecialization(sped.getSpecializationById(rs.getInt("specialization_id")));
                list.add(ud);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<UserAccount> searchByGender(String gender) {
        ArrayList<UserAccount> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dob]\n"
                + "      ,[img]\n"
                + "      ,[location_id]\n"
                + "      ,[specialization_id]\n"
                + "  FROM [dbo].[user_account]\n"
                + "  where gender = ? and specialization_id is  not null";
        LocationDAO ld = new LocationDAO();
        SpecializationDAO sped = new SpecializationDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, Boolean.parseBoolean(gender));
            ResultSet rs = st.executeQuery();
            UserAccount ud;
            while (rs.next()) {
                ud = new UserAccount();
                ud.setId(rs.getInt("id"));
                ud.setName(rs.getString("name"));
                ud.setEmail(rs.getString("email"));
                ud.setPassword(rs.getString("password"));
                ud.setPhone(rs.getString("phone"));
                ud.setGender(rs.getBoolean("gender"));
                ud.setAddress(rs.getString("address"));
                ud.setDob(rs.getDate("dob"));
                ud.setImg(rs.getString("img"));
                ud.setLocation(ld.getLocationById(rs.getInt("location_id")));
                ud.setSpecialization(sped.getSpecializationById(rs.getInt("specialization_id")));
                list.add(ud);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
    
    
    

    public ArrayList<UserAccount> searchByFilter(String gender, String speciality) {
        ArrayList<UserAccount> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dob]\n"
                + "      ,[img]\n"
                + "      ,[location_id]\n"
                + "      ,[specialization_id]\n"
                + "  FROM [dbo].[user_account]\n"
                + "  where gender = ? and specialization_id = ?";
        LocationDAO ld = new LocationDAO();
        SpecializationDAO sped = new SpecializationDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, Boolean.parseBoolean(gender));
            st.setString(2, speciality);
            ResultSet rs = st.executeQuery();
            UserAccount ud;
            while (rs.next()) {
                ud = new UserAccount();
                ud.setId(rs.getInt("id"));
                ud.setName(rs.getString("name"));
                ud.setEmail(rs.getString("email"));
                ud.setPassword(rs.getString("password"));
                ud.setPhone(rs.getString("phone"));
                ud.setGender(rs.getBoolean("gender"));
                ud.setAddress(rs.getString("address"));
                ud.setDob(rs.getDate("dob"));
                ud.setImg(rs.getString("img"));
                ud.setLocation(ld.getLocationById(rs.getInt("location_id")));
                ud.setSpecialization(sped.getSpecializationById(rs.getInt("specialization_id")));
                list.add(ud);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<UserAccount> searchBySpecialization(String speciality) {
        ArrayList<UserAccount> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dob]\n"
                + "      ,[img]\n"
                + "      ,[location_id]\n"
                + "      ,[specialization_id]\n"
                + "  FROM [dbo].[user_account]\n"
                + "  where specialization_id = ?";
        LocationDAO ld = new LocationDAO();
        SpecializationDAO sped = new SpecializationDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, speciality);
            ResultSet rs = st.executeQuery();
            UserAccount ud;
            while (rs.next()) {
                ud = new UserAccount();
                ud.setId(rs.getInt("id"));
                ud.setName(rs.getString("name"));
                ud.setEmail(rs.getString("email"));
                ud.setPassword(rs.getString("password"));
                ud.setPhone(rs.getString("phone"));
                ud.setGender(rs.getBoolean("gender"));
                ud.setAddress(rs.getString("address"));
                ud.setDob(rs.getDate("dob"));
                ud.setImg(rs.getString("img"));
                ud.setLocation(ld.getLocationById(rs.getInt("location_id")));
                ud.setSpecialization(sped.getSpecializationById(rs.getInt("specialization_id")));
                list.add(ud);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<UserAccount> searchByName(String name) {
        ArrayList<UserAccount> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[password]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dob]\n"
                + "      ,[img]\n"
                + "      ,[location_id]\n"
                + "      ,[specialization_id]\n"
                + "  FROM [dbo].[user_account]\n"
                + "  where name like ?";
        LocationDAO ld = new LocationDAO();
        SpecializationDAO sped = new SpecializationDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            UserAccount ud;
            while (rs.next()) {
                ud = new UserAccount();
                ud.setId(rs.getInt("id"));
                ud.setName(rs.getString("name"));
                ud.setEmail(rs.getString("email"));
                ud.setPassword(rs.getString("password"));
                ud.setPhone(rs.getString("phone"));
                ud.setGender(rs.getBoolean("gender"));
                ud.setAddress(rs.getString("address"));
                ud.setDob(rs.getDate("dob"));
                ud.setImg(rs.getString("img"));
                ud.setLocation(ld.getLocationById(rs.getInt("location_id")));
                ud.setSpecialization(sped.getSpecializationById(rs.getInt("specialization_id")));
                list.add(ud);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    
    public void UpdateImage(int uid, Part img) throws SQLException {
        String sql = "UPDATE user_account SET img = ? WHERE (id = ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            InputStream image = img.getInputStream();
            st.setBlob(1, image);
            st.setInt(2, uid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void DoctorUpdate(int id, String name, boolean gender, String phone, Date DOB, String description, int speciality, boolean status) throws SQLException {
        String sql = "UPDATE user_account SET specialization_id = ?, name = ?, gender = ?, dob = ?, phone = ?, description = ?, status = ? WHERE (id = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ps.setInt(1, speciality);
            ps.setString(2, name);
            ps.setBoolean(3, gender);
            ps.setDate(4, DOB);
            ps.setString(5, phone);
            ps.setString(6, description);
            ps.setBoolean(7, status);
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void UpdateAccount(int uid, String name, String phone, boolean gender, int pid) throws SQLException {
        AccountPermisionDAO accperdao = new AccountPermisionDAO();
        String sql = "UPDATE [dbo].[user_account]\n"
                + "   SET [name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[gender] = ?\n"
                + " WHERE [id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setBoolean(3, gender);
            ps.setInt(4, uid);
            accperdao.UpdateRole(uid, pid);
            ps.executeUpdate();
        } catch (SQLException e) {
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public boolean isExist(String email) {
        List<UserAccount> list = getAllUserDetail();
        for (UserAccount a : list) {
            if (a.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public List<UserAccount> getListByPage(List<UserAccount> list,
            int start, int end) {
        ArrayList<UserAccount> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public int CountDoctor() {
        int count = 0;
        String sql = "select count(*) from user_account where specialization_id Is not null";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return count;
    }

    public static void main(String[] args) {
        UserAccountDAO uad = new UserAccountDAO();
        //  System.out.println(udd.getUserAccountById(1).getId());
        LocationDAO ld = new LocationDAO();
        SpecializationDAO sped = new SpecializationDAO();
        try {
            uad.UpdateAccount(2, "Quang Anh", "012345678", true, 2);
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
