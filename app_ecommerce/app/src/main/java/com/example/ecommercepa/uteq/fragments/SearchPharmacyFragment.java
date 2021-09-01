package com.example.ecommercepa.uteq.fragments;

import android.app.Activity;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommercepa.R;
import com.example.ecommercepa.uteq.Utilities.Utilities;
import com.example.ecommercepa.uteq.activitys.MainActivity;
import com.example.ecommercepa.uteq.adapters.ListAdapterPharmacy;
import com.example.ecommercepa.uteq.adapters.ListAdapterProducts;
import com.example.ecommercepa.uteq.interfaces.iCommunicates_Fragments;
import com.example.ecommercepa.uteq.model.PharmacyModel;
import com.example.ecommercepa.uteq.model.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPharmacyFragment extends Fragment {

    private EditText etSearchPharmacy;
    private RecyclerView rvListPharmacy;

    private ListAdapterPharmacy adapterPharmacy;
    private List<PharmacyModel> listpharmacy;

    private String URL = "https://becommercee.herokuapp.com/";
    private RequestQueue requestQueue;

    private iCommunicates_Fragments interfacecommunicates_Fragments;

    private Activity activitys;

    // variables para mantener sesion
    private SharedPreferences preferences;
    private String user_id, name, last_name, email, address, role, imguser, registrationdate,
            dateupdate, birthdaydate, user_token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_searchpharmacy, container, false);
        etSearchPharmacy = view.findViewById(R.id.etBuscador);
        init();
        sessionuser();

        etSearchPharmacy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterp(s.toString());
            }
        });

        return view;
    }

    public void filterp(String text){
        ArrayList<PharmacyModel> filterlistpharmacy= new ArrayList<>();

        for(PharmacyModel pharmacyModel : listpharmacy){
            if(pharmacyModel.getName().toLowerCase().contains(text.toLowerCase())){
                filterlistpharmacy.add(pharmacyModel);
            }
        }
        adapterPharmacy.filter(filterlistpharmacy);
    }
    private void gologin() {
        Intent i = new Intent(getActivity(), MainActivity.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(user_id != null && email != null){

            rvListPharmacy = view.findViewById(R.id.rvListPharm);
            rvListPharmacy.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            rvListPharmacy.setHasFixedSize(true);

            listpharmacy= new ArrayList<>();

            String datajson = "{\n" +
                    "   \"user_id\":\""+user_id+"\"\n" +
                    "}";
            Log.d("user_id",datajson);

            StringRequest request = new StringRequest(
                    Request.Method.POST,URL+"webresources/pharmacy/pharmacybyuser",
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
                                            Log.d("user_id",object.get("user_id").toString());
                                            Log.d("name",object.get("name").toString());

                                            listpharmacy.add(new PharmacyModel(object.get("pharmacy_id").toString(),
                                                    object.get("user_id").toString(),
                                                    object.get("name").toString(),
                                                    object.get("address").toString(),
                                                    object.get("phone").toString(),
                                                    object.get("hoursoperation").toString(),
                                                    object.get("dateupdate").toString()));
                                        }
                                        adapterPharmacy = new ListAdapterPharmacy(listpharmacy,getActivity());
                                        rvListPharmacy.setAdapter(adapterPharmacy);

                                        adapterPharmacy.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Toast.makeText(getActivity(),"Selecciona: " +
                                                                listpharmacy.get(rvListPharmacy.getChildAdapterPosition(view)).getName(),
                                                        Toast.LENGTH_SHORT).show();
                                                interfacecommunicates_Fragments.SendPharmacy(listpharmacy.get(rvListPharmacy.getChildAdapterPosition(view)));
                                            }
                                        });
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
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Activity){
            this.activitys = (Activity) context;
            interfacecommunicates_Fragments = (iCommunicates_Fragments) this.activitys;
        }
    }
    @Override
    public void onDetach(){
        super.onDetach();
    }


}
