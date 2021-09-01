package com.example.ecommercepa.uteq.model;

import java.io.Serializable;

public class PharmacyModel implements Serializable {

    private String pharmacy_id;
    private String user_id;
    private String name;
    private String address;
    private String phone;
    private String hoursoperation;
    private String dateupdate;

    public PharmacyModel(String pharmacy_id, String user_id, String name, String address, String phone, String hoursoperation, String dateupdate) {
        this.pharmacy_id = pharmacy_id;
        this.user_id = user_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.hoursoperation = hoursoperation;
        this.dateupdate = dateupdate;
    }

    public String getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(String pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoursoperation() {
        return hoursoperation;
    }

    public void setHoursoperation(String hoursoperation) {
        this.hoursoperation = hoursoperation;
    }

    public String getDateupdate() {
        return dateupdate;
    }

    public void setDateupdate(String dateupdate) {
        this.dateupdate = dateupdate;
    }


}
