package com.example.tr_672019005;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterListKategori extends RecyclerView.Adapter<AdapterListKategori.ViewHolder> {

    Context context;
    List<ModelListKategori> modelListKategoriList;

    public AdapterListKategori(Context context, List<ModelListKategori> modelListKategoriList) {
        this.context = context;
        this.modelListKategoriList = modelListKategoriList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kategori_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelListKategori modelListKategori = modelListKategoriList.get(position);

        Glide.with(context).load(modelListKategori.getImgkategori()).into(holder.imgkategori);
        holder.namakategori.setText(modelListKategori.getNamakategori());
        holder.deskripsikategori.setText(modelListKategori.getDeskripsikategori());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListBarangActivity.class);
                intent.putExtra("type", modelListKategori.getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelListKategoriList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgkategori;
        TextView namakategori, deskripsikategori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgkategori = itemView.findViewById(R.id.img_listkategori);
            namakategori = itemView.findViewById(R.id.txt_namalistkategori);
            deskripsikategori = itemView.findViewById(R.id.txt_deskripsilistkategori);
        }
    }
}
