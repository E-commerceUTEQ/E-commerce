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
import com.example.ecommercepa.uteq.model.ProductModel;

import java.util.ArrayList;
import java.util.List;


public class ListAdapterProducts extends RecyclerView.Adapter<ListAdapterProducts.ProductViewHolder> implements  View.OnClickListener{

    private List<ProductModel> mData;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;

    public ListAdapterProducts(List<ProductModel> mData, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_list_product,null,false);
        View view = mInflater.inflate(R.layout.element_list_product, parent,false);
        view.setOnClickListener(this);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterProducts.ProductViewHolder holder, int position) {
        holder.name.setText(mData.get(position).getName());
        holder.price.setText(mData.get(position).getPrice());
        holder.namefarm.setText(mData.get(position).getNamefarm());
        holder.addressfarm.setText(mData.get(position).getAddressfarm());
        holder.phonefarm.setText(mData.get(position).getPhonefarm());
        holder.housfarm.setText(mData.get(position).getHousfarm());
        Glide.with(context).load(mData.get(position).getPhoto()).into(holder.photo);

        if(mData.get(position).getCertification().equals("t")){
            holder.certification.setText("Medical certification is required");
            holder.certification.setTextColor(Color.RED);
        }else{
            holder.certification.setText("No medical certification is required");
            holder.certification.setTextColor(Color.GREEN);
        }
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

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView product_id, name, laboratory,certification,price,pharmacy_id,userfarm,namefarm,
                addressfarm,phonefarm,housfarm,dateupdatefarm;

        ProductViewHolder (View itemView){
            super(itemView);
            photo = itemView.findViewById(R.id.ProductImageView);
            name = itemView.findViewById(R.id.productname);
            price = itemView.findViewById(R.id.priceproduct);
            namefarm = itemView.findViewById(R.id.pharmacy);
            addressfarm = itemView.findViewById(R.id.addresspharmacy);
            phonefarm = itemView.findViewById(R.id.phonepharmacy);
            housfarm = itemView.findViewById(R.id.hoursoperationpharmacy);
            certification = itemView.findViewById(R.id.certificate);

        }
    }

    public void filter(ArrayList<ProductModel> filterProducts){
        this.mData = filterProducts;
        notifyDataSetChanged();
    }
}
