/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class Prescriptions {
    private int id;
    private Appointment appointment_id;
    private Medicine medicine_id;
    private String quantity;
    private String dosage;
    private String duration;

    public Prescriptions() {
    }

    public Prescriptions(int id, Appointment appointment_id, Medicine medicine_id, String quantity, String dosage, String duration) {
        this.id = id;
        this.appointment_id = appointment_id;
        this.medicine_id = medicine_id;
        this.quantity = quantity;
        this.dosage = dosage;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Appointment appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Medicine getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(Medicine medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    
}
