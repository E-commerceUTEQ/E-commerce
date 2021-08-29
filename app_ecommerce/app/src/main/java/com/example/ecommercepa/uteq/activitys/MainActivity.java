package com.example.ecommercepa.uteq.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommercepa.R;
import com.example.ecommercepa.uteq.Utilities.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String URL = "https://becommercee.herokuapp.com/";
    private RequestQueue requestQueue;

    private Button btnLogin;
    private EditText edittextuser,edittexpassword;

    // variables para mantener sesion
    private SharedPreferences preferences;
    private String user_id, name, last_name, email, address, role, imguser, registrationdate,
            dateupdate, birthdaydate, user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
      /*  if(validatesesion()){
            Toast.makeText(MainActivity.this, "Active session", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Session not active", Toast.LENGTH_LONG).show();
        } */

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String loginjson = "{\n" +
                        "   \"email\":\""+edittextuser.getText()+"\",\n" +
                        "   \"password\":\""+edittexpassword.getText()+"\"\n" +
                        "}";
                Log.d("JSONUSER",loginjson);
                Sesion(loginjson);
            }
        });
    }

    private void init(){
        btnLogin = findViewById(R.id.btnLogin);
        edittextuser =  (EditText) findViewById(R.id.edittextuser);
        edittexpassword =  (EditText)findViewById(R.id.edittexpassword);
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
    }

    private void goMenu(){
        Intent i = new Intent(this, menu.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


    private void Sesion(String datajson){

        //Obtención de datos del web service utilzando Volley
        // requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.POST,URL+"webresources/users/logIn",
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
                                    Log.d("response",response);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("user_id",json_transform.getJSONObject("data").getString("user_id"));
                                    editor.putString("name",json_transform.getJSONObject("data").getString("name"));
                                    editor.putString("last_name",json_transform.getJSONObject("data").getString("last_name"));
                                    editor.putString("email",json_transform.getJSONObject("data").getString("email"));
                                    editor.putString("address",json_transform.getJSONObject("data").getString("address"));
                                    editor.putString("role",json_transform.getJSONObject("data").getString("role"));
                                    editor.putString("imguser",json_transform.getJSONObject("data").getString("imguser"));
                                    editor.putString("registrationdate",json_transform.getJSONObject("data").getString("registrationdate"));
                                    editor.putString("dateupdate",json_transform.getJSONObject("data").getString("dateupdate"));
                                    editor.putString("birthdaydate",json_transform.getJSONObject("data").getString("birthdaydate"));
                                    editor.putString("user_token",json_transform.getJSONObject("data").getString("user_token"));
                                    editor.commit();
                                    goMenu();
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Incorrect credentials", Toast.LENGTH_LONG).show();
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
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        } else {
            requestQueue.add(request);
        }
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

    // Se controla la pulsación del botón atrás
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Would you like to return to Home?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            goHome();
                            finish();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void goHome(){
        Intent i = new Intent(this, HomeActivity.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_LONG).show();
    }

    private boolean validatesesion(){
        sessionuser();
        if (user_id != null && email != null) {
            Log.d("iduser", user_id);
            Log.d("email", email);
            goMenu();
            return true;
        }else{
            return false;
        }
    }
}