package com.example.ecommercepa.uteq.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.example.ecommercepa.R;
import com.example.ecommercepa.uteq.activitys.menu;

public class AddPharmacyFragment extends Fragment {

    private String URL = "https://becommercee.herokuapp.com/";
    private RequestQueue requestQueue;

    // variables para mantener sesion
    private SharedPreferences preferences;
    private String user_id, name, last_name, email, address, role, imguser, registrationdate,
            dateupdate, birthdaydate, user_token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_addpharmacy, container, false);
        init();

        return view;
    }

    private void init(){
        preferences = this.getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    public void sessionuser() {
        user_id = preferences.getString("user_id", null);
        name = preferences.getString("name", null);
        last_name = preferences.getString("last_name", null);
        email = preferences.getString("email", null);
        address = preferences.getString("address", null);
        role = preferences.getString("role", null);
        imguser = preferences.getString("imguser", null);
        dateupdate= preferences.getString("dateupdate",null);
        registrationdate= preferences.getString("registrationdate",null);
        birthdaydate= preferences.getString("birthdaydate",null);
        user_token= preferences.getString("user_token",null);
    }




}
