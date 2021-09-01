package com.example.ecommercepa.uteq.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.example.ecommercepa.R;
import com.example.ecommercepa.uteq.fragments.HomeFragment;
import com.example.ecommercepa.uteq.fragments.PharmacyFragment;
import com.example.ecommercepa.uteq.fragments.ProductbyPharmacyFragment;
import com.example.ecommercepa.uteq.interfaces.iCommunicates_Fragments;
import com.example.ecommercepa.uteq.model.PharmacyModel;
import com.google.android.material.navigation.NavigationView;

public class menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iCommunicates_Fragments {

    private String URL = "https://becommercee.herokuapp.com/";
    private RequestQueue requestQueue;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Menu menu;

    private ProductbyPharmacyFragment productbyPharmacyFragment;


    // variables para mantener sesion
    private SharedPreferences preferences;
    private String user_id, name, last_name, email, address, role, imguser, registrationdate,
            dateupdate, birthdaydate, user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();
        sessionuser();

        if (user_id != null && email != null) {

            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            drawerLayout = findViewById(R.id.drawerLayout);
            navigationView = findViewById(R.id.navigationView);

            navigationView.setNavigationItemSelectedListener(this);
            // añadiendo imagenes full color
            navigationView.setItemIconTintList(null);

            final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
            NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
            menu = navigationView.getMenu();
            if(!role.equals("Administrador")){
              /*  MenuItem  visivel = menu.findItem(R.id.menuother);
                MenuItem  visivel2 = menu.findItem(R.id.menuOrdersAdm);
                visivel.setVisible(false);
                visivel2.setVisible(false); */
            }

            // para añadir datos del usuario al menu encabezado
            View hView = navigationView.getHeaderView(0);
            ImageView foto = (ImageView) hView.findViewById(R.id.imageProfile);
            TextView Usuario = (TextView) hView.findViewById(R.id.viewNameUser);
            TextView RolUsuario = (TextView) hView.findViewById(R.id.viewRol);
            Usuario.setText(name + " " + last_name);
            RolUsuario.setText(role);
            Glide.with(this).load(imguser.replace('\\', '/')).into(foto);

            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            actionBarDrawerToggle.syncState();

            fragmentManager = getSupportFragmentManager();//cargar fragment principal en la actividad
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, new HomeFragment());
            fragmentTransaction.commit();

        } else {
            Toast.makeText(menu.this, "No session", Toast.LENGTH_LONG).show();
            gologin();
        }
    }

    private void init() {
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
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

    private void gologin() {
        Intent i = new Intent(this, MainActivity.class);
        // bandera para que no se creen nuevas actividades innecesarias
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void logoff() {
        preferences.edit().clear().apply();
        gologin();
        Toast.makeText(menu.this, "Closed session", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (menuItem.getItemId()) {
            case R.id.menuHome:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new HomeFragment());
                fragmentTransaction.commit();
                Toast.makeText(menu.this, "Home", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuPharmacies:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new PharmacyFragment());
                fragmentTransaction.commit();
                Toast.makeText(menu.this, "Pharmacies", Toast.LENGTH_LONG).show();
                break;
            case R.id.logOff:
                logoff();// vaciar las variables de session
                Toast.makeText(menu.this, "Log off", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    @Override
    public void SendPharmacy(PharmacyModel pharmacyModel) {
        productbyPharmacyFragment = new ProductbyPharmacyFragment();// aquí se realiza toda la lógica necesaria para poder realizar el envio
        Bundle bundleSend = new Bundle();// object bundle para transportar la información
        bundleSend.putSerializable("object", pharmacyModel);// enviar el objeto que está llegando con Serializable
        productbyPharmacyFragment.setArguments(bundleSend);
        // abrir fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, productbyPharmacyFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to exit hiHome?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            logoff();
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
}
