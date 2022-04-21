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

public class AdapterPencarian extends RecyclerView.Adapter<AdapterPencarian.MyViewHolder> {

    Context context;
    ArrayList<ModelPencarian> modelPencarianArrayList;

    public AdapterPencarian(Context context, ArrayList<ModelPencarian> modelPencarianArrayList) {
        this.context = context;
        this.modelPencarianArrayList = modelPencarianArrayList;
    }

    @NonNull
    @Override
    public AdapterPencarian.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_pencarian_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPencarian.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ModelPencarian modelPencarian = modelPencarianArrayList.get(position);

        Glide.with(context).load(modelPencarian.getImgbarang()).into(holder.img_gambarbarang);
        holder.txt_namabarang.setText(modelPencarian.getNamabarang());
        holder.txt_hargabarang.setText(String.valueOf(modelPencarian.getHargabarang()));
        holder.txt_deskripsibarang.setText(modelPencarian.getDeskripsibarang());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailCariBarang.class);
                intent.putExtra("detail", modelPencarianArrayList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelPencarianArrayList.size();
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
