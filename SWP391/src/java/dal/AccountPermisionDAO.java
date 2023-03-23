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
import model.AccountPermision;
import model.Permision;
import model.UserAccount;

/**
 *
 * @author Quang Anh
 */
public class AccountPermisionDAO extends DBContext {
    
    public List<AccountPermision> getAllAccountPermision(){
        UserAccountDAO userdao = new UserAccountDAO();
        PermisionDAO perdao = new PermisionDAO();
        List<AccountPermision> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[account_id]\n"
                + "      ,[permision_id]\n"
                + "  FROM [dbo].[account_permision]\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);;
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                UserAccount ua = userdao.getUserAccountById(rs.getInt("account_id"));
                Permision per = perdao.getPermisionByID(rs.getInt("permision_id"));
                list.add(new AccountPermision(ua, per));
               
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public AccountPermision getAccountPermisionByAccount(UserAccount ua) {
        String sql = "SELECT [id]\n"
                + "      ,[account_id]\n"
                + "      ,[permision_id]\n"
                + "  FROM [dbo].[account_permision]\n"
                + "  where account_id = ?";
        AccountPermision ap;
        UserAccountDAO uad = new UserAccountDAO();
        PermisionDAO pd = new PermisionDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ua.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ap = new AccountPermision();
                ap.setId(rs.getInt("id"));
                ap.setUa(uad.getUserAccountById(rs.getInt("account_id")));
                ap.setPer(pd.getPermisionByID(rs.getInt("permision_id")));
                return ap;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void UpdateRole(int uid, int pid) {
        String sql = "UPDATE [dbo].[account_permision]\n"
                + "   SET [permision_id] = ?\n"
                + " WHERE [account_id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ps.setInt(2, uid);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void addPermisionForAccount(UserAccount ua, Permision per) {
        String sql = "INSERT INTO [dbo].[account_permision]\n"
                + "           ([account_id]\n"
                + "           ,[permision_id])\n"
                + "     VALUES (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ua.getId());
            st.setInt(2, per.getId());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public List<AccountPermision> searchByPermision(int pid){
        List<AccountPermision> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.account_permision WHERE permision_id = " + pid;
        UserAccountDAO userdao = new UserAccountDAO();
        PermisionDAO perdao = new PermisionDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserAccount ua = userdao.getUserAccountById(rs.getInt("account_id"));
                Permision per = perdao.getPermisionByID(pid);
                list.add(new AccountPermision(ua, per));
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }

    public List<AccountPermision> getListByPage(List<AccountPermision> list,
            int start, int end) {
        List<AccountPermision> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    public static void main(String[] args) {
        UserAccountDAO uad = new UserAccountDAO();
        System.out.println(uad.loginUserAccount("admin@gmail.com", "admin123"));
    }
}
