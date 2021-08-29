package com.example.ecommercepa.uteq.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommercepa.R;
import com.example.ecommercepa.uteq.Utilities.Utilities;
import com.example.ecommercepa.uteq.adapters.ListAdapterProducts;
import com.example.ecommercepa.uteq.model.ProductModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrugfinderActivity  extends AppCompatActivity {

    private String URL = "https://becommercee.herokuapp.com/";
    private RequestQueue requestQueue;

    private EditText etSearchProduct;
    private RecyclerView rvListProduct;
    private ListAdapterProducts adapter;
    private List<ProductModel> listproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugfinder);
        //getSupportActionBar().hide();

        etSearchProduct = findViewById(R.id.etBuscador);
        etSearchProduct.addTextChangedListener(new TextWatcher() {
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

        rvListProduct = findViewById(R.id.rvListProduct);
        rvListProduct.setLayoutManager(new GridLayoutManager(this, 1));


      /*  listproduct = new ArrayList<>();
        listproduct.add(new ProductModel("1","Paracetamol","xx","xx","xx","xx"
                ,"ff","ff","ff","ff","gg","ww"));

        listproduct.add(new ProductModel("2","Migradoxina","asds","sdsd","ss","ss"
                ,"ss","ss","ss","ss","ss","ss"));

        listproduct.add(new ProductModel("3","Rold","d","d","d","d"
                ,"d","d","d","d","d","d"));

        adapter = new ListAdapterProducts(listproduct,getApplicationContext());
        rvListProduct.setAdapter(adapter); */
        getdata();
    }

    public void filterp(String text){
            ArrayList<ProductModel> filterlistproduct = new ArrayList<>();

            for(ProductModel productModel : listproduct){
                if(productModel.getName().toLowerCase().contains(text.toLowerCase())){
                    filterlistproduct.add(productModel);
                }
            }
            adapter.filter(filterlistproduct);
    }

    private void getdata() {

        listproduct = new ArrayList<>();

        StringRequest request = new StringRequest(
                Request.Method.GET,
                URL +"webresources/product/allregisteredproduct",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int size = response.length();
                        boolean band = false;
                        response = Utilities.fixEncoding(response);
                          Log.d("Respuesta", response);
                      //  JSONObject json_transform = null;
                        try {
                            if (size > 0)
                            {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    listproduct.add(new ProductModel(object.getString("product_id"),
                                            object.getString("name"),object.getString("laboratory"),object.getString("certification"),
                                            object.getString("photo"),object.getString("price"),object.getString("pharmacy_id"),
                                            object.getString("userfarm"),object.getString("namefarm"),object.getString("addressfarm"),
                                            object.getString("phonefarm"),object.getString("housfarm"),object.getString("dateupdatefarm")));
                                }
                                adapter = new ListAdapterProducts(listproduct,getApplicationContext());
                                rvListProduct.setAdapter(adapter);
                            }
                        } catch (Exception e) {
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
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("Accept", "application/json");
                return params;
            }
        };
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        } else {
            requestQueue.add(request);
        }
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
        Toast.makeText(DrugfinderActivity.this, "Login", Toast.LENGTH_LONG).show();
    }
}
