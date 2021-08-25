package com.example.ecommercepa.uteq.activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
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
import com.google.android.material.navigation.NavigationView;

public class menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String URL = "https://bsmarthome.herokuapp.com/webresources";
    private RequestQueue requestQueue;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    //variable del fragment detallehome
 //   private DetailHomeFragment detailHomeFragment;
    //variable del fragment detalleNotifications
 //   private DetailNotificationFragment detailNotificationFragment;
    // variables para mantener sesion
    private SharedPreferences preferences;
    private String user_id, name, last_name, email, address, type, imguser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        init();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);
        // añadiendo imagenes full color
        navigationView.setItemIconTintList(null);
        // para añadir datos del usuario al menu encabezado
        View hView = navigationView.getHeaderView(0);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();//cargar fragment principal en la actividad
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new HomeFragment());
        fragmentTransaction.commit();


    }

    private void init() {
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
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
        }
        return false;
    }
}
