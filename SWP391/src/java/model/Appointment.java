/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Quang Anh
 */
public class Appointment {
    private int id;
    private Patient pd;
    private UserAccount ua;
    private Date date;
    private String symptom;
    private Slot slot;
    private String clinicCode;
    private boolean managerAccept,doctorAccept,isDenided;

    public Appointment() {
    }

    public Appointment(Patient pd, UserAccount ua, Date date, String symptom, Slot slot, String clinicCode, boolean managerAccept, boolean doctorAccept, boolean isDenided) {
        this.pd = pd;
        this.ua = ua;
        this.date = date;
        this.symptom = symptom;
        this.slot = slot;
        this.clinicCode = clinicCode;
        this.managerAccept = managerAccept;
        this.doctorAccept = doctorAccept;
        this.isDenided = isDenided;
    }

    public Appointment(int id, Patient pd, UserAccount ua, Date date, String symptom, Slot slot, String clinicCode, boolean managerAccept, boolean doctorAccept, boolean isDenided) {
        this.id = id;
        this.pd = pd;
        this.ua = ua;
        this.date = date;
        this.symptom = symptom;
        this.slot = slot;
        this.clinicCode = clinicCode;
        this.managerAccept = managerAccept;
        this.doctorAccept = doctorAccept;
        this.isDenided = isDenided;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPd() {
        return pd;
    }

    public void setPd(Patient pd) {
        this.pd = pd;
    }

    public UserAccount getUa() {
        return ua;
    }

    public void setUa(UserAccount ua) {
        this.ua = ua;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public String getClinicCode() {
        return clinicCode;
    }

    public void setClinicCode(String clinicCode) {
        this.clinicCode = clinicCode;
    }

    public boolean isManagerAccept() {
        return managerAccept;
    }

    public void setManagerAccept(boolean managerAccept) {
        this.managerAccept = managerAccept;
    }

    public boolean isDoctorAccept() {
        return doctorAccept;
    }

    public void setDoctorAccept(boolean doctorAccept) {
        this.doctorAccept = doctorAccept;
    }

    public boolean isIsDenided() {
        return isDenided;
    }

    public void setIsDenided(boolean isDenided) {
        this.isDenided = isDenided;
    }
    
    
    
}
