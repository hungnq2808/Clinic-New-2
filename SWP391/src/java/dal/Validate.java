/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Quang Anh
 */
public class Validate {
    public boolean IsDateBookingOk(String date) {
        Date currentDate = new java.util.Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateget = (Date) dateFormat.parse(date);
            if (dateget.compareTo(currentDate) >= 0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        Validate v = new Validate();
        System.out.println(v.IsDateBookingOk("2023-03-19"));
    }
}
