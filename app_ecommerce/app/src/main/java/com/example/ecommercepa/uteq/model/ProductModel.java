package com.example.ecommercepa.uteq.model;

public class ProductModel {

    private String product_id;
    private String name;
    private String laboratory;
    private String certification;
    private String photo;
    private String price;
    private String pharmacy_id;
    private String userfarm;
    private String namefarm;
    private String addressfarm;
    private String phonefarm;
    private String housfarm;
    private String dateupdatefarm;

    public ProductModel(String product_id, String name, String laboratory, String certification, String photo, String price, String pharmacy_id, String userfarm, String namefarm, String addressfarm, String phonefarm, String housfarm, String dateupdatefarm) {
        this.product_id = product_id;
        this.name = name;
        this.laboratory = laboratory;
        this.certification = certification;
        this.photo = photo;
        this.price = price;
        this.pharmacy_id = pharmacy_id;
        this.userfarm = userfarm;
        this.namefarm = namefarm;
        this.addressfarm = addressfarm;
        this.phonefarm = phonefarm;
        this.housfarm = housfarm;
        this.dateupdatefarm = dateupdatefarm;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPharmacy_id() {
        return pharmacy_id;
    }

    public void setPharmacy_id(String pharmacy_id) {
        this.pharmacy_id = pharmacy_id;
    }

    public String getUserfarm() {
        return userfarm;
    }

    public void setUserfarm(String userfarm) {
        this.userfarm = userfarm;
    }

    public String getNamefarm() {
        return namefarm;
    }

    public void setNamefarm(String namefarm) {
        this.namefarm = namefarm;
    }

    public String getAddressfarm() {
        return addressfarm;
    }

    public void setAddressfarm(String addressfarm) {
        this.addressfarm = addressfarm;
    }

    public String getPhonefarm() {
        return phonefarm;
    }

    public void setPhonefarm(String phonefarm) {
        this.phonefarm = phonefarm;
    }

    public String getHousfarm() {
        return housfarm;
    }

    public void setHousfarm(String housfarm) {
        this.housfarm = housfarm;
    }

    public String getDateupdatefarm() {
        return dateupdatefarm;
    }

    public void setDateupdatefarm(String dateupdatefarm) {
        this.dateupdatefarm = dateupdatefarm;
    }
}
