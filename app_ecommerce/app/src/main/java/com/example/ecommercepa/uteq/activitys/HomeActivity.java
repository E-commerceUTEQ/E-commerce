package com.example.ecommercepa.uteq.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecommercepa.R;

public class HomeActivity extends AppCompatActivity {

    private Button btnLogin, btnSearch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goLogin();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goSearch();
            }
        });
    }

    private void init(){
        btnLogin = findViewById(R.id.btnLogin);
        btnSearch = findViewById(R.id.btnSearch);
    }

    private void goLogin(){
        Intent i = new Intent(this, MainActivity.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        Toast.makeText(HomeActivity.this, "Login", Toast.LENGTH_LONG).show();
    }
    private void goSearch(){
        Intent i = new Intent(this, DrugfinderActivity.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        Toast.makeText(HomeActivity.this, "Pharmaceutical products search engine", Toast.LENGTH_LONG).show();
    }
}
