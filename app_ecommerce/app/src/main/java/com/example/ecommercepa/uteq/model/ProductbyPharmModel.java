package com.example.ecommercepa.uteq.model;

public class ProductbyPharmModel {

    private String product_id;
    private String name;
    private String laboratory;
    private String certification;
    private String photo;

    public ProductbyPharmModel(String product_id, String name, String laboratory, String certification, String photo) {
        this.product_id = product_id;
        this.name = name;
        this.laboratory = laboratory;
        this.certification = certification;
        this.photo = photo;
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
}
