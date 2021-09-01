package com.example.ecommercepa.uteq.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommercepa.R;
import com.example.ecommercepa.uteq.model.PharmacyModel;
import com.example.ecommercepa.uteq.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterPharmacy extends RecyclerView.Adapter<ListAdapterPharmacy.PharmacyViewHolder> implements  View.OnClickListener{

    private List<PharmacyModel> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;

    public ListAdapterPharmacy(List<PharmacyModel> mData, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public ListAdapterPharmacy.PharmacyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_list_product,null,false);
        View view = mInflater.inflate(R.layout.element_list_pharm, parent,false);
        view.setOnClickListener(this);
        return new ListAdapterPharmacy.PharmacyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterPharmacy.PharmacyViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getName());
        holder.address.setText(mData.get(position).getAddress());
        holder.phone.setText(mData.get(position).getPhone());
        holder.hoursoperation.setText(mData.get(position).getHoursoperation());
        Glide.with(context).load("https://as.com/diarioas/imagenes/2021/01/31/actualidad/1612128752_898054_1612128997_noticia_normal_recorte1.jpg").into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class PharmacyViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView pharmacy_id, user_id, name,address,phone,hoursoperation,dateupdate;


        PharmacyViewHolder (View itemView){
            super(itemView);
            photo = itemView.findViewById(R.id.PharmImageView);
            name = itemView.findViewById(R.id.pharmname);
            address = itemView.findViewById(R.id.addresspharm);
            phone = itemView.findViewById(R.id.phonepharm);
            hoursoperation = itemView.findViewById(R.id.operationpharm);


        }
    }

    public void filter(ArrayList<PharmacyModel> filterPpharmacy){
        this.mData = filterPpharmacy;
        notifyDataSetChanged();
    }
}
