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
import com.example.ecommercepa.uteq.model.ProductbyPharmModel;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterProductbyPharm extends RecyclerView.Adapter<ListAdapterProductbyPharm.ProductbyPharmViewHolder> implements  View.OnClickListener{

    private List<ProductbyPharmModel> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;

    public ListAdapterProductbyPharm(List<ProductbyPharmModel> mData, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public ListAdapterProductbyPharm.ProductbyPharmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.element_list_productbypharm, parent,false);
        view.setOnClickListener(this);
        return new ListAdapterProductbyPharm.ProductbyPharmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterProductbyPharm.ProductbyPharmViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getName());
        holder.laboratory.setText(mData.get(position).getLaboratory());
    //    holder.certification.setText(mData.get(position).getCertification());
        if(mData.get(position).getCertification().equals("t")){
            holder.certification.setText("Medical certification is required");
            holder.certification.setTextColor(Color.RED);
        }else{
            holder.certification.setText("No medical certification is required");
            holder.certification.setTextColor(Color.GREEN);
        }
        Glide.with(context).load(mData.get(position).getPhoto()).into(holder.photo);
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

    public class ProductbyPharmViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView product_id, name, laboratory,certification;



        ProductbyPharmViewHolder (View itemView){
            super(itemView);
            photo = itemView.findViewById(R.id.productbypharmImageView);
            name = itemView.findViewById(R.id.productbypharmname);
            laboratory = itemView.findViewById(R.id.laboratoryprobypharm);
            certification = itemView.findViewById(R.id.certificateprodbyfarm);


        }
    }

    public void filter(ArrayList<ProductbyPharmModel> filterPpharmacy){
        this.mData = filterPpharmacy;
        notifyDataSetChanged();
    }


}
