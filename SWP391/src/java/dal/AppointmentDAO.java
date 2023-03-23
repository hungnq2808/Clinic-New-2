package dal;

import context.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Appointment;
import model.Patient;
import model.Slot;
import model.Statistic;
import model.UserAccount;

/**
 *
 * @author Quang Anh
 */
public class AppointmentDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;
    PatientDAO pd = new PatientDAO();
        UserAccountDAO uad = new UserAccountDAO();
        SlotDAO sd = new SlotDAO();

    public ArrayList<Appointment> getAllAppointment() {
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[clinic_code]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]";
        ArrayList<Appointment> ls = new ArrayList<>();
        
        Appointment a;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                a = new Appointment();
                a.setId(rs.getInt("id"));
                a.setPd(pd.getPatientById(rs.getInt("patient_id")));
                a.setUa(uad.getUserAccountById(rs.getInt("user_detail_id")));
                a.setDate(rs.getDate("date"));
                a.setSymptom(rs.getString("symptom"));
                a.setSlot(sd.getSlotById(rs.getInt("booking_slot_id")));
                a.setClinicCode(rs.getString("clinic_code"));
                a.setManagerAccept(rs.getBoolean("manager_accept"));
                a.setDoctorAccept(rs.getBoolean("doctor_accept"));
                a.setIsDenided(rs.getBoolean("is_denided"));
                ls.add(a);
            }
            return ls;
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean createAnAppointment(Appointment a) {
        String sql = "INSERT INTO [dbo].[appointments]\n"
                + "           ([patient_id]\n"
                + "           ,[user_detail_id]\n"
                + "           ,[date]\n"
                + "           ,[symptom]\n"
                + "           ,[booking_slot_id]\n"
                + "           ,[clinic_code]\n"
                + "           ,[manager_accept]\n"
                + "           ,[doctor_accept]\n"
                + "           ,[is_denided])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, a.getPd().getId());
            st.setInt(2, a.getUa().getId());
            st.setDate(3, a.getDate());
            st.setString(4, a.getSymptom());
            st.setInt(5, a.getSlot().getId());
            st.setString(6, a.getClinicCode());
            st.setBoolean(7, a.isManagerAccept());
            st.setBoolean(8, a.isDoctorAccept());
            st.setBoolean(9, a.isIsDenided());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    public ArrayList<Appointment> getAppointmentManagerAcc() {
        ArrayList<Appointment> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[clinic_code]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]\n"
                + "  where manager_accept = 0";
        try {
            
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Appointment a = new Appointment();
                a.setId(rs.getInt("id"));
                Patient p = pd.getPatientById(rs.getInt("patient_id"));
                a.setPd(p);
                UserAccount u = uad.getUserAccountById(rs.getInt("user_detail_id"));
                a.setUa(u);
                a.setDate(rs.getDate("date"));
                a.setSymptom(rs.getString("symptom"));
                Slot s = sd.getSlotById(rs.getInt("booking_slot_id"));
                a.setSlot(s);
                a.setClinicCode(rs.getString("clinic_code"));
                a.setManagerAccept(rs.getBoolean("manager_accept"));
                a.setDoctorAccept(rs.getBoolean("doctor_accept"));
                a.setIsDenided(rs.getBoolean("is_denided"));

                list.add(a);

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    public ArrayList<Appointment> getAppointmentDenied(boolean deni) {
        ArrayList<Appointment> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]\n"
                + "  where doctor_accept =  ?";
        try {
           
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, deni);
            rs = ps.executeQuery();
            while (rs.next()) {
                Appointment a = new Appointment();
                a.setId(rs.getInt("id"));
                Patient p = pd.getPatientById(rs.getInt("patient_id"));
                a.setPd(p);
                UserAccount u = uad.getUserAccountById(rs.getInt("user_detail_id"));
                a.setUa(u);
                a.setDate(rs.getDate("date"));
                a.setSymptom(rs.getString("symptom"));
                Slot s = sd.getSlotById(rs.getInt("booking_slot_id"));
                a.setSlot(s);
                a.setManagerAccept(rs.getBoolean("manager_accept"));
                a.setDoctorAccept(rs.getBoolean("doctor_accept"));
                a.setIsDenided(rs.getBoolean("is_denided"));

                list.add(a);

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public Appointment getAppointmentByID(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[clinic_code]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]\n"
                + "  where id = ?";
        Appointment a;
        PatientDAO pd = new PatientDAO();
        UserAccountDAO uad = new UserAccountDAO();
        SlotDAO sd = new SlotDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                a = new Appointment();
                a.setId(rs.getInt("id"));
                a.setPd(pd.getPatientById(rs.getInt("patient_id")));
                a.setUa(uad.getUserAccountById(rs.getInt("user_detail_id")));
                a.setDate(rs.getDate("date"));
                a.setSymptom(rs.getString("symptom"));
                a.setSlot(sd.getSlotById(rs.getInt("booking_slot_id")));
                a.setClinicCode(rs.getString("clinic_code"));
                a.setManagerAccept(rs.getBoolean("manager_accept"));
                a.setDoctorAccept(rs.getBoolean("doctor_accept"));
                a.setIsDenided(rs.getBoolean("is_denided"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
    
    public ArrayList<Appointment> getAppointmentByService() {
        ArrayList<Appointment> list = new ArrayList<>();
        String sql = "select appointments.id , patients.patient_name  , user_account.name ,appointments.date ,appointments.symptom, slot.date_schedule ,appointments.manager_accept\n"
                + "                                         from  appointments inner join service_appointments on appointments.id = service_appointments.appointments_id\n"
                + "										 inner join patients on appointments.patient_id = patients.id                  \n"
                + "                                     inner join user_account on appointments.user_detail_id = user_account.id					\n"
                + "                                   inner join slot on appointments.booking_slot_id = slot.id";
        try {
            
            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                Appointment a = new Appointment();
                a.setId(rs.getInt("id"));
                Patient p = pd.getPatientById(rs.getInt("patient_id"));
                a.setPd(p);
                UserAccount u = uad.getUserAccountById(rs.getInt("user_detail_id"));
                a.setUa(u);
                a.setDate(rs.getDate("date"));
                a.setSymptom(rs.getString("symptom"));
                Slot s = sd.getSlotById(rs.getInt("booking_slot_id"));
                a.setSlot(s);
                a.setClinicCode(rs.getString("clinic_code"));
                a.setManagerAccept(rs.getBoolean("manager_accept"));
                a.setDoctorAccept(rs.getBoolean("doctor_accept"));
                a.setIsDenided(rs.getBoolean("is_denided"));

                list.add(a);

            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    // random code 
    public static String randomClinicCode(int n) {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public boolean checkExist(int userid, String date, int slotId) {
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[clinic_code]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]\n"
                + "  where user_detail_id= ? and [date] = ? and booking_slot_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userid);
            st.setString(2, date);
            st.setInt(3, slotId);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean checkExistCode(String code) {
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[clinic_code]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]\n"
                + "  where clinic_code = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public Appointment getAppointmentByOther(int patientId, String date, int slotId) {
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[clinic_code]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]\n"
                + "  where patient_id = ? and [date] like ? and booking_slot_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, patientId);
            st.setString(2, date);
            st.setInt(3, slotId);
            rs = st.executeQuery();
            PatientDAO pd = new PatientDAO();
            UserAccountDAO uad = new UserAccountDAO();
            SlotDAO sd = new SlotDAO();
            Appointment a;
            if (rs.next()) {
                a = new Appointment();
                a.setId(rs.getInt("id"));
                a.setPd(pd.getPatientById(rs.getInt("patient_id")));
                a.setUa(uad.getUserAccountById(rs.getInt("user_detail_id")));
                a.setDate(rs.getDate("date"));
                a.setSymptom(rs.getString("symptom"));
                a.setSlot(sd.getSlotById(rs.getInt("booking_slot_id")));
                a.setClinicCode(rs.getString("clinic_code"));
                a.setManagerAccept(rs.getBoolean("manager_accept"));
                a.setDoctorAccept(rs.getBoolean("doctor_accept"));
                a.setIsDenided(rs.getBoolean("is_denided"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Appointment> getAppointmnetByPatient(Patient p) {
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[clinic_code]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]\n"
                + "  where patient_id = ?\n"
                + "  order by date desc";
        ArrayList<Appointment> ls = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, p.getId());
            rs = st.executeQuery();
            PatientDAO pd = new PatientDAO();
            UserAccountDAO uad = new UserAccountDAO();
            SlotDAO sd = new SlotDAO();
            Appointment a;
            while (rs.next()) {
                a = new Appointment();
                a.setId(rs.getInt("id"));
                a.setPd(pd.getPatientById(rs.getInt("patient_id")));
                a.setUa(uad.getUserAccountById(rs.getInt("user_detail_id")));
                a.setDate(rs.getDate("date"));
                a.setSymptom(rs.getString("symptom"));
                a.setSlot(sd.getSlotById(rs.getInt("booking_slot_id")));
                a.setClinicCode(rs.getString("clinic_code"));
                a.setManagerAccept(rs.getBoolean("manager_accept"));
                a.setDoctorAccept(rs.getBoolean("doctor_accept"));
                a.setIsDenided(rs.getBoolean("is_denided"));
                ls.add(a);
            }
            return ls;
        } catch (SQLException e) {
        }
        return null;
    }

    public Appointment getAppointmentByClinicCode(String code) throws SQLException {
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[clinic_code]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]\n"
                + "  where clinic_code = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            rs = st.executeQuery();
            PatientDAO pd = new PatientDAO();
            UserAccountDAO uad = new UserAccountDAO();
            SlotDAO sd = new SlotDAO();
            Appointment a;
            if (rs.next()) {
                a = new Appointment();
                a.setId(rs.getInt("id"));
                a.setPd(pd.getPatientById(rs.getInt("patient_id")));
                a.setUa(uad.getUserAccountById(rs.getInt("user_detail_id")));
                a.setDate(rs.getDate("date"));
                a.setSymptom(rs.getString("symptom"));
                a.setSlot(sd.getSlotById(rs.getInt("booking_slot_id")));
                a.setClinicCode(rs.getString("clinic_code"));
                a.setManagerAccept(rs.getBoolean("manager_accept"));
                a.setDoctorAccept(rs.getBoolean("doctor_accept"));
                a.setIsDenided(rs.getBoolean("is_denided"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void deleteAllAppointmentByPatients(Patient p) {
        String sql = "DELETE FROM [dbo].[appointments]\n"
                + "      WHERE patient_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<Appointment> GetListAppointmentByDoctorId(UserAccount ua) {
        String sql = "SELECT [id]\n"
                + "      ,[patient_id]\n"
                + "      ,[user_detail_id]\n"
                + "      ,[date]\n"
                + "      ,[symptom]\n"
                + "      ,[booking_slot_id]\n"
                + "      ,[clinic_code]\n"
                + "      ,[manager_accept]\n"
                + "      ,[doctor_accept]\n"
                + "      ,[is_denided]\n"
                + "  FROM [dbo].[appointments]\n"
                + "  where user_detail_id = ?\n"
                + "  order by date desc";
        ArrayList<Appointment> ls = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ua.getId());
            rs = st.executeQuery();
            PatientDAO pd = new PatientDAO();
            UserAccountDAO uad = new UserAccountDAO();
            SlotDAO sd = new SlotDAO();
            Appointment a;
            while (rs.next()) {
                a = new Appointment();
                a.setId(rs.getInt("id"));
                a.setPd(pd.getPatientById(rs.getInt("patient_id")));
                a.setUa(uad.getUserAccountById(rs.getInt("user_detail_id")));
                a.setDate(rs.getDate("date"));
                a.setSymptom(rs.getString("symptom"));
                a.setSlot(sd.getSlotById(rs.getInt("booking_slot_id")));
                a.setClinicCode(rs.getString("clinic_code"));
                a.setManagerAccept(rs.getBoolean("manager_accept"));
                a.setDoctorAccept(rs.getBoolean("doctor_accept"));
                a.setIsDenided(rs.getBoolean("is_denided"));
                ls.add(a);
            }
            return ls;
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Appointment> getAppointmentListByDate(String type) {
        List<Appointment> list = new ArrayList<>();
        PatientDAO pd = new PatientDAO();
        UserAccountDAO userdao = new UserAccountDAO();
        SlotDAO slotdao = new SlotDAO();
        String month = "SELECT * FROM appointments as ap WHERE MONTH(ap.date) = MONTH(CURRENT_TIMESTAMP)";
        String today = "SELECT * FROM appointments as ap WHERE DAY(ap.date) = DAY(CURRENT_TIMESTAMP) AND MONTH(ap.date) = MONTH(CURRENT_TIMESTAMP) AND YEAR(ap.date) = YEAR(CURRENT_TIMESTAMP)";
        String day7 = "SELECT * FROM appointments as ap WHERE ap.date BETWEEN DATEADD(day,-6,CURRENT_TIMESTAMP) AND CURRENT_TIMESTAMP";
        String day14 = "SELECT * FROM appointments as ap WHERE ap.date BETWEEN DATEADD(day,-13,CURRENT_TIMESTAMP) AND CURRENT_TIMESTAMP";
        try {
            if (type.equals("today")) {
                ps = connection.prepareStatement(today);
            }
            if (type.equals("7day")) {
                ps = connection.prepareStatement(day7);
            }
            if (type.equals("14day")) {
                ps = connection.prepareStatement(day14);
            }

            if (type.equals("month")) {
                ps = connection.prepareStatement(month);
            }
            rs = ps.executeQuery();
            int id;
            while (rs.next()) {
                id = rs.getInt("id");
                Patient patient = pd.getPatientById(rs.getInt("patient_id"));
                UserAccount ua = userdao.getUserAccountById(rs.getInt("user_detail_id"));
                Date date = rs.getDate("date");
                String symptom = rs.getString("symptom");
                Slot slot = slotdao.getSlotById(rs.getInt("booking_slot_id"));
                list.add(new Appointment(id,patient, ua, date, symptom, slot, today, true, true, true));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Statistic> getDataLast7Day(String type) {
        List<Statistic> list = new ArrayList<>();
        String day7 = "Select p.day , coalesce(count(u.id), 0) as count from \n"
                + "(Select CURRENT_TIMESTAMP as day\n"
                + "union\n"
                + "Select DATEADD(day,-6,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-5,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-4,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-3,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-2,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-1,CURRENT_TIMESTAMP))as p\n"
                + "left join appointments as u on (day(p.day) = day(u.date) and MONTH(p.day) = MONTH(u.date) and YEAR(p.day) = YEAR(u.date)) group by p.day order by p.day asc";

        String day14 = "Select p.day , coalesce(count(u.id), 0) as count from \n"
                + "(Select CURRENT_TIMESTAMP as day\n"
                + "union\n"
                + "Select DATEADD(day,-14,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-13,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-12,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-11,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-10,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-9,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-8,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-7,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-6,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-5,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-4,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-3,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-2,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-1,CURRENT_TIMESTAMP))as p\n"
                + "left join appointments as u on (day(p.day) = day(u.date) and MONTH(p.day) = MONTH(u.date) and YEAR(p.day) = YEAR(u.date)) group by p.day order by p.day asc";

        String day3 = "Select p.day , coalesce(count(u.id), 0) as count from \n"
                + "(Select CURRENT_TIMESTAMP as day\n"
                + "union\n"
                + "Select DATEADD(day,-2,CURRENT_TIMESTAMP)\n"
                + "union\n"
                + "Select DATEADD(day,-1,CURRENT_TIMESTAMP))as p\n"
                + "left join appointments as u on (day(p.day) = day(u.date) and MONTH(p.day) = MONTH(u.date) and YEAR(p.day) = YEAR(u.date)) group by p.day order by p.day asc";
        try {
            PreparedStatement ps = null;
            if (type.equals("7day")) {
                ps = connection.prepareStatement(day7);
            }
            if (type.equals("14day")) {
                ps = connection.prepareStatement(day14);
            }
            if (type.equals("3day")) {
                ps = connection.prepareStatement(day3);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Statistic(rs.getDate(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public int getFeeOfServiceByAppointmentId(int id) {
        int fee = 0;
        String sql = "SELECT ap.id, ap.patient_id, ap.date, ser.name, ser.price FROM appointments as ap \n"
                + "                 INNER JOIN service_appointments as aps ON ap.id = aps.appointments_id\n"
                + "                INNER JOIN services as ser ON aps.service_id = ser.id \n"
                + "                WHERE ap.id = " + id;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                fee += rs.getInt(5);
            }
            return fee;
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fee;
    }

    public int getFeeOfMedicinesByAppointmentId(int id) {
        int fee = 0;
        String sql = "SELECT ap.id, ap.patient_id, ap.date,med.name, pre.quantity, med.price FROM appointments as ap \n"
                + "INNER JOIN prescriptions as pre ON ap.id = pre.appointment_id \n"
                + "INNER JOIN medicines as med ON pre.medicine_id = med.id \n"
                + "WHERE ap.id = " + id;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int price = rs.getInt(7);
                int quantity = rs.getInt(6);
                fee += price * quantity;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fee;
    }

    public int SumFee(String type) {
        return sumMedicineFee(type) + sumServiceFee(type);
    }

    public int sumMedicineFee(String type) {
        int sum = 0;
        List<Appointment> list = getAppointmentListByDate(type);
        for (Appointment appoint : list) {
            sum += getFeeOfMedicinesByAppointmentId(appoint.getId());
        }
        return sum;
    }

    public int sumServiceFee(String type) {
        int sum = 0;
        List<Appointment> list = getAppointmentListByDate(type);
        for (Appointment appoint : list) {
            sum += getFeeOfServiceByAppointmentId(appoint.getId());
        }
        return sum;
    }

    public int CountPatient() {
        int count = 0;
        String sql = "select count(*) from patients";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return count;
    }

    public int CountAppointment() {
        int count = 0;
        String sql = "select count(*) from appointments";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return count;
    }

    public void updateStatusDoctor(Appointment a) throws Exception {

        String sql = "UPDATE [dbo].[appointments]\n"
                + "   SET [doctor_accept] = ?\n"
                + " WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);

            ps.setBoolean(1, a.isDoctorAccept());
            ps.setInt(2, a.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
        }

    }
    public void updateStatus(Appointment a) throws Exception {

        String sql = "UPDATE [dbo].[appointments]\n"
                + "   SET [manager_accept] = ?\n"
                + " WHERE id = ?";
        try {
            
            ps = connection.prepareStatement(sql);

            ps.setBoolean(1, a.isManagerAccept());
            ps.setInt(2, a.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
        }

    }
    public void updateUserInApp(Appointment a, UserAccount u) throws Exception {

        String sql = "UPDATE [dbo].[appointments]\n"
                + "   SET [user_detail_id] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, u.getId());
            st.setInt(2, a.getId());
            st.executeUpdate();
        } catch (SQLException e) {
        }

    }
    public static void main(String[] args) {
        AppointmentDAO ad = new AppointmentDAO();
        PatientDAO pd = new PatientDAO();
        UserAccountDAO uad = new UserAccountDAO();
        SlotDAO sd = new SlotDAO();
 //       Appointment a = new Appointment(pd.getPatientsById(1), uad.getUserAccountById(1), "2023-07-12", "dau dit", sd.getSlotById(7), "new", false, false, false);
        //  ad.createAnAppointment(a);
   //     ArrayList<Appointment> ls = ad.getAppointmnetByPatient(pd.getPatientsById(5));
//        for (Appointment l : ls) {
//            System.out.println(l.getSymptom());
//        }
       // System.out.println(ls);
//        int fee = ad.sumServiceFee("month");
//        System.out.println(fee);
        AppointmentDAO dao = new AppointmentDAO();
        System.out.println(dao.sumServiceFee("month"));
        List<Statistic> ls = dao.getDataLast7Day("day7");
        for (Statistic l : ls) {
            System.out.println(l.getCount());
        }
        
        System.out.println();
    }
}
