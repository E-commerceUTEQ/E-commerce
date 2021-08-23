package com.example.ecommercepa.uteq.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ecommercepa.R;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    // variables para mantener sesion
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

              //  String loginjson = "{\n" +
                  //      "   \"email\":\""+edittextuser.getText()+"\",\n" +
                  //      "   \"password\":\""+edittexpassword.getText()+"\"\n" +
                 //       "}";
              //  Log.d("JSONUSER",loginjson);
                goMenu();
                //Sesion(loginjson);
            }
        });
    }

    private void init(){
        btnLogin = findViewById(R.id.btnLogin);
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
}