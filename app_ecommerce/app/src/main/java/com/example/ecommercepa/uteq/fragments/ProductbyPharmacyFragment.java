package com.example.ecommercepa.uteq.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ecommercepa.R;
import com.example.ecommercepa.uteq.Utilities.Utilities;
import com.example.ecommercepa.uteq.activitys.MainActivity;
import com.example.ecommercepa.uteq.adapters.ListAdapterPharmacy;
import com.example.ecommercepa.uteq.adapters.ListAdapterProductbyPharm;
import com.example.ecommercepa.uteq.model.PharmacyModel;
import com.example.ecommercepa.uteq.model.ProductbyPharmModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductbyPharmacyFragment extends Fragment {

    private EditText etBuscadorProdubypharm;
    private RecyclerView rvListProbyPharm;

    private String URL = "https://becommercee.herokuapp.com/";
    private RequestQueue requestQueue;

    private ListAdapterProductbyPharm listAdapterProductbyPharm;
    private List<ProductbyPharmModel> listproductbypharmacy;


    // variables para mantener sesion
    private SharedPreferences preferences;
    private String user_id, name, last_name, email, address, role, imguser, registrationdate,
            dateupdate, birthdaydate, user_token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_productbypharmacy, container, false);
        init();
        sessionuser();
        etBuscadorProdubypharm = view.findViewById(R.id.etBuscadorProdubypharm);

        etBuscadorProdubypharm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterpropharm(s.toString());
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void filterpropharm(String text){
        ArrayList<ProductbyPharmModel> filterlistprodbypharmacy= new ArrayList<>();

        for(ProductbyPharmModel pharmacyModel : listproductbypharmacy){
            if(pharmacyModel.getName().toLowerCase().contains(text.toLowerCase())){
                filterlistprodbypharmacy.add(pharmacyModel);
            }
        }
        listAdapterProductbyPharm.filter(filterlistprodbypharmacy);
    }
    private void gologin() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //Crear objeto bundler para recibir el objeto enviado por argumentos
        Bundle objecthome = getArguments();
        PharmacyModel pharmacyModel = null;
        //validacion para verificar si existen argumentos enviados para mostrar
        if(objecthome!=null) {
            pharmacyModel = (PharmacyModel) objecthome.getSerializable("object");
            String datajson = "{\n" +
                    "   \"pharmacy_id\":\""+pharmacyModel.getPharmacy_id()+"\"\n" +
                    "}";
            Log.d("jsonpharmacy_id",datajson);

            if(user_id != null && email != null){

                rvListProbyPharm = view.findViewById(R.id.rvListProbyPharm);
                rvListProbyPharm.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                rvListProbyPharm.setHasFixedSize(true);

                listproductbypharmacy= new ArrayList<>();


                StringRequest request = new StringRequest(
                        Request.Method.POST,URL+"webresources/product/productbypharm",
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
                                        if(json_transform.getString("flag").equals("true")){
                                            JSONArray jsonArray = json_transform.getJSONArray("data");
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject object = jsonArray.getJSONObject(i);
                                                listproductbypharmacy.add(new ProductbyPharmModel(object.get("product_id").toString(),
                                                        object.get("name").toString(),
                                                        object.get("laboratory").toString(),
                                                        object.get("certification").toString(),
                                                        object.get("photo").toString()));
                                            }
                                            listAdapterProductbyPharm = new ListAdapterProductbyPharm(listproductbypharmacy,getActivity());
                                             rvListProbyPharm.setAdapter(listAdapterProductbyPharm);

                                     /*   adapterPharmacy.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Toast.makeText(getActivity(),"Selecciona: " +
                                                                listpharmacy.get(rvListPharmacy.getChildAdapterPosition(view)).getName(),
                                                        Toast.LENGTH_SHORT).show();
                                                interfacecommunicates_Fragments.SendPharmacy(listpharmacy.get(rvListPharmacy.getChildAdapterPosition(view)));
                                            }
                                        }); */
                                        }
                                        else{
                                            Toast.makeText(getActivity(), "No users linked to the device", Toast.LENGTH_LONG).show();
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

            }else{
                Toast.makeText(getActivity(), "No session", Toast.LENGTH_LONG).show();
                gologin();
            }

        }

    }

    public void getdata(String datajson){

    }
}
