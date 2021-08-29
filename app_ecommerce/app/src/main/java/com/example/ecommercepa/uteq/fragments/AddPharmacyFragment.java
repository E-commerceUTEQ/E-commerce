package com.example.ecommercepa.uteq.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommercepa.R;
import com.example.ecommercepa.uteq.Utilities.Utilities;
import com.example.ecommercepa.uteq.activitys.MainActivity;
import com.example.ecommercepa.uteq.activitys.menu;
import com.github.mikephil.charting.charts.BarChart;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class AddPharmacyFragment extends Fragment {

    private String URL = "https://becommercee.herokuapp.com/";
    private RequestQueue requestQueue;

    private EditText namepharm, addrespharm, phonepharm;
    private Button btnsavee;

    // variables para mantener sesion
    private SharedPreferences preferences;
    private String user_id, name, last_name, email, address, role, imguser, registrationdate,
            dateupdate, birthdaydate, user_token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_addpharmacy, container, false);
        init();
        sessionuser();
        namepharm = (EditText)view.findViewById(R.id.namepharm);
        addrespharm = (EditText)view.findViewById(R.id.addrespharm);
        phonepharm = (EditText)view.findViewById(R.id.phonepharm);
        btnsavee = (Button)view.findViewById(R.id.btnsavee);

        btnsavee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datajson = "{\n" +
                        "   \"user_id\":\""+user_id+"\",\n" +
                        "   \"name\":\""+namepharm.getText()+"\",\n" +
                        "   \"address\":\""+addrespharm.getText()+"\",\n" +
                        "   \"phone\":\""+phonepharm.getText()+"\",\n" +
                        "   \"hoursoperation\":\""+"Lunes a Domingos: 06:00 - 06:00"+"\"\n" +
                        "}";
                Log.d("data",datajson);
                addPharm(datajson);
              //  Toast.makeText(getActivity(), "Uppss!....In development process", Toast.LENGTH_LONG).show();
            }
        });

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

    private void addPharm(String datajson){

        //Obtención de datos del web service utilzando Volley
        // requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.POST,URL+"webresources/pharmacy/insertpharmacy",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int size = response.length();
                        response = Utilities.fixEncoding(response);
                        JSONObject json_transform = null;
                        try {
                            if (size > 0)
                            {
                                json_transform = new JSONObject(response);
                                if(json_transform.getString("status").equals("true")){
                                  //  Log.d("response",response);
                                    Toast.makeText(getActivity(), json_transform.getString("information"), Toast.LENGTH_LONG).show();

                                }
                                else{
                                    Toast.makeText(getActivity(), "Incorrect credentials", Toast.LENGTH_LONG).show();
                                    Log.d("response",response);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("Accept", "application/json");
                return params;
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return datajson == null ? "{}".getBytes("utf-8") : datajson.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {

                    return null;
                }
            }
        };
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(request);
        } else {
            requestQueue.add(request);
        }
    }




}
