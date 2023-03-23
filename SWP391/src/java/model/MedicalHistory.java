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
public class MedicalHistory {
    private int id;
    private String disease;
    private String date;
    private String note;
    private Patient paient;
    private boolean reexamMedical;
    

    public MedicalHistory() {
    }

    public MedicalHistory(String disease, String date, String note, Patient paient, boolean reexamMedical) {
        this.disease = disease;
        this.date = date;
        this.note = note;
        this.paient = paient;
        this.reexamMedical = reexamMedical;
    }

    public MedicalHistory(int id, String disease, String date, String note, Patient paient, boolean reexamMedical) {
        this.id = id;
        this.disease = disease;
        this.date = date;
        this.note = note;
        this.paient = paient;
        this.reexamMedical = reexamMedical;
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

    public Patient getPaient() {
        return paient;
    }

    public void setPaient(Patient paient) {
        this.paient = paient;
    }

    public boolean isReexamMedical() {
        return reexamMedical;
    }

    public void setReexamMedical(boolean reexamMedical) {
        this.reexamMedical = reexamMedical;
    }

    
}
