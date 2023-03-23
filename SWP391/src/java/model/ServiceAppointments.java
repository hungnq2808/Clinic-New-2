/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Quang Anh
 */
public class ServiceAppointments {
    private int id;
    private Appointment appointment;
    private Service service;

    public ServiceAppointments() {
    }

    public ServiceAppointments(Appointment appointment, Service service) {
        this.appointment = appointment;
        this.service = service;
    }

    
    
    public ServiceAppointments(int id, Appointment appointment, Service service) {
        this.id = id;
        this.appointment = appointment;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
    
}
