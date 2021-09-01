package com.example.ecommercepa.uteq.interfaces;


import com.example.ecommercepa.uteq.model.PharmacyModel;

public interface iCommunicates_Fragments {
    //esta interface se encarga de realizar la comunicacion entre la lista de personas y el detalle
    public void SendPharmacy(PharmacyModel pharmacyModel); //se transportara un objeto de tipo usermodel
    //(En la clase pharmacyModel se implementa Serializable para poder transportar un objeteo a otro)

}
