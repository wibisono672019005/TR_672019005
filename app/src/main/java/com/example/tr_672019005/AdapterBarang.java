package com.example.tr_672019005;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;

public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.MyViewHolder> {

    Context context;
    ArrayList<Barang> barangArrayList;

    public AdapterBarang(Context context, ArrayList<Barang> barangArrayList) {
        this.context = context;
        this.barangArrayList = barangArrayList;
    }

    @NonNull
    @Override
    public AdapterBarang.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBarang.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Barang barang = barangArrayList.get(position);

        Glide.with(context).load(barang.getImgbarang()).into(holder.img_gambarbarang);
        holder.txt_namabarang.setText(barang.getNamabarang());
        holder.txt_hargabarang.setText(String.valueOf(barang.getHargabarang()));
        holder.txt_deskripsibarang.setText(barang.getDeskripsibarang());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailBarang.class);
                intent.putExtra("detail", barangArrayList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return barangArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_namabarang, txt_hargabarang, txt_deskripsibarang;
        ImageView img_gambarbarang;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_namabarang = itemView.findViewById(R.id.txt_namabarang);
            txt_hargabarang = itemView.findViewById(R.id.txt_hargabarang);
            txt_deskripsibarang = itemView.findViewById(R.id.txt_deskripsibarang);
            img_gambarbarang = itemView.findViewById(R.id.img_gambarbarang);
        }
    }
}
