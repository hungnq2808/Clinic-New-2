/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Appointment;
import model.ServiceAppointments;

/**
 *
 * @author Quang Anh
 */
public class ServiceAppointmentDAO extends DBContext {

    public void createServiceAppointment(ServiceAppointments sa) {
        String sql = "INSERT INTO [dbo].[service_appointments]\n"
                + "           ([appointments_id]\n"
                + "           ,[service_id])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, sa.getAppointment().getId());
            st.setInt(2, sa.getService().getId());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public ArrayList<ServiceAppointments> getAllServiceAppointmentByAppointment(Appointment a) {
        String sql = "SELECT [id]\n"
                + "      ,[appointments_id]\n"
                + "      ,[service_id]\n"
                + "  FROM [dbo].[service_appointments]\n"
                + "  where appointments_id = ?";
        ArrayList<ServiceAppointments> ls = new ArrayList<>();
        AppointmentDAO ad = new AppointmentDAO();
        ServiceDAO sd = new ServiceDAO();
        ServiceAppointments sa;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                sa = new ServiceAppointments();
                sa.setId(rs.getInt("id"));
                sa.setAppointment(ad.getAppointmentByID(rs.getInt("appointments_id")));
                sa.setService(sd.getServiceById(rs.getInt("service_id")));
                ls.add(sa);
            }
            return ls;
        } catch (Exception e) {
        }
        return null;
    }

    public ServiceAppointments getServiceAppointmentById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[appointments_id]\n"
                + "      ,[service_id]\n"
                + "  FROM [dbo].[service_appointments]\n"
                + "  where id = ?";
        AppointmentDAO ad = new AppointmentDAO();
        ServiceDAO sd = new ServiceDAO();
        ServiceAppointments sa;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                sa = new ServiceAppointments();
                sa.setId(rs.getInt("id"));
                sa.setAppointment(ad.getAppointmentByID(rs.getInt("appointments_id")));
                sa.setService(sd.getServiceById(rs.getInt("service_id")));
                return sa;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public void deleteServiceAppointmentById(int id) {
        String sql = "DELETE FROM [dbo].[service_appointments]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteAllServiceAppointmentByAppointment(Appointment a) {
        String sql = "DELETE FROM [dbo].[service_appointments]\n"
                + "      WHERE appointments_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a.getId());
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        AppointmentDAO ad = new AppointmentDAO();
        ServiceAppointmentDAO sad = new ServiceAppointmentDAO();
//        ArrayList<ServiceAppointments> ls = sad.getAllServiceAppointmentByAppointment(ad.getAppointmentByID(2284022));
//        for (ServiceAppointments l : ls) {
//            System.out.println(l.getService().getPrice());
//        }
        System.out.println(sad.getServiceAppointmentById(20).getAppointment().getId());
    }
}
