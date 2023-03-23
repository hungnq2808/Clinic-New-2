/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Quang Anh
 */
public class AccountPermision {
    private int id;
    private UserAccount ua;
    private Permision per;

    public AccountPermision() {
    }

    public AccountPermision(UserAccount ua, Permision per) {
        this.ua = ua;
        this.per = per;
    }

    public AccountPermision(int id, UserAccount ua, Permision per) {
        this.id = id;
        this.ua = ua;
        this.per = per;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAccount getUa() {
        return ua;
    }

    public void setUa(UserAccount ua) {
        this.ua = ua;
    }

    public Permision getPer() {
        return per;
    }

    public void setPer(Permision per) {
        this.per = per;
    }
    
    
}
