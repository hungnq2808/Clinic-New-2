/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Medical_history {
    private int id;
    private String disease;
    private String date;
    private String note;
    private Patient patient_id;
    private boolean reexam_medical;

    public Medical_history() {
    }

    public Medical_history(int id, String disease, String date, String note, Patient patient_id, boolean reexam_medical) {
        this.id = id;
        this.disease = disease;
        this.date = date;
        this.note = note;
        this.patient_id = patient_id;
        this.reexam_medical = reexam_medical;
    }

    public Medical_history(String disease, String date, String note, Patient patient_id, boolean reexam_medical) {
        this.disease = disease;
        this.date = date;
        this.note = note;
        this.patient_id = patient_id;
        this.reexam_medical = reexam_medical;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Patient getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Patient patient_id) {
        this.patient_id = patient_id;
    }

    public boolean isReexam_medical() {
        return reexam_medical;
    }

    public void setReexam_medical(boolean reexam_medical) {
        this.reexam_medical = reexam_medical;
    }

    @Override
    public String toString() {
        return "Medical_history{" + "id=" + id + ", disease=" + disease + ", date=" + date + ", note=" + note + ", patient_id=" + patient_id + ", reexam_medical=" + reexam_medical + '}';
    }
    
    
    
    
}
