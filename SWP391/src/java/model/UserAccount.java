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
public class UserAccount {
//    id int IDENTITY(1,1) primary key,
//    first_name varchar(50),
//    last_name varchar(50),
//    email varchar(254) not null,
//    age tinyint,
//    phone varchar(11) not null unique,
//    gender bit,
//    address nvarchar(100),
//    dob date,
//	img nvarchar(1000),
//	location_id int foreign key references dbo.locations (id),
//	specialization_id int foreign key references specializations (id),
//	user_account_id int foreign key references account(id),
    private int id;
    private String name,email,password;
    private String phone;
    private boolean gender;
    private String address;
    private Date dob;
    private String img;
    private Location location;
    private Specialization specialization;

    public UserAccount() {
    }

    public UserAccount(String name, String email, String password, String phone, boolean gender, String address, Date dob, String img, Location location, Specialization specialization) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.img = img;
        this.location = location;
        this.specialization = specialization;
    }

    public UserAccount(int id, String name, String email, String password, String phone, boolean gender, String address, Date dob, String img, Location location, Specialization specialization) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.img = img;
        this.location = location;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "UserAccount{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + ", gender=" + gender + ", address=" + address + ", dob=" + dob + ", img=" + img + ", location=" + location + ", specialization=" + specialization + '}';
    }

    
    
}
