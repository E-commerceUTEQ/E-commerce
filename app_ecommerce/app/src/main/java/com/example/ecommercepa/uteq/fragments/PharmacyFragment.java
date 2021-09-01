package com.example.ecommercepa.uteq.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecommercepa.R;
import com.example.ecommercepa.uteq.activitys.menu;

public class PharmacyFragment extends Fragment {

    private CardView btnAddPharmacy,btnSearchPharmacy;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_pharmacy, container, false);

        btnAddPharmacy =  (CardView) view.findViewById(R.id.btnAddPharmacy);
        btnSearchPharmacy =  (CardView) view.findViewById(R.id.btnSearchPharmacy);

        btnAddPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new AddPharmacyFragment());
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Add", Toast.LENGTH_LONG).show();
            }
        });

        btnSearchPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            /*    SearchPharmacyFragment fr=new SearchPharmacyFragment();
                fr.setArguments(bn);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor,fr)
                        .addToBackStack(null)
                        .commit(); */
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new SearchPharmacyFragment());
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Search", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


}
