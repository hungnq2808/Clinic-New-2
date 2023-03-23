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
public class Patient {
    private int id;
    private String patientName;
    private String patientPhoneNumber;
    private String patientEmail;
    private boolean patientGender;
    private String patientAddress;
    private Date patientDob;

    public Patient() {
    }

    public Patient(String patientName, String patientPhoneNumber, String patientEmail, boolean patientGender, String patientAddress, Date patientDob) {
        this.patientName = patientName;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientEmail = patientEmail;
        this.patientGender = patientGender;
        this.patientAddress = patientAddress;
        this.patientDob = patientDob;
    }

    public Patient(int id, String patientName, String patientPhoneNumber, String patientEmail, boolean patientGender, String patientAddress, Date patientDob) {
        this.id = id;
        this.patientName = patientName;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientEmail = patientEmail;
        this.patientGender = patientGender;
        this.patientAddress = patientAddress;
        this.patientDob = patientDob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public boolean isPatientGender() {
        return patientGender;
    }

    public void setPatientGender(boolean patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public Date getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(Date patientDob) {
        this.patientDob = patientDob;
    }

    
    
}
