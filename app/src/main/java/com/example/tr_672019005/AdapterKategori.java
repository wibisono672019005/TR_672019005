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

public class AdapterKategori extends RecyclerView.Adapter<AdapterKategori.ViewHolder> {

    Context context;
    List<ModelKategoriBarang> modelKategoriBarangList;

    public AdapterKategori(Context context, List<ModelKategoriBarang> modelKategoriBarangList) {
        this.context = context;
        this.modelKategoriBarangList = modelKategoriBarangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori_barang_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelKategoriBarang modelKategoriBarang = modelKategoriBarangList.get(position);

        Glide.with(context).load(modelKategoriBarang.getImgkategori()).into(holder.imgkategori);
        holder.namakategori.setText(modelKategoriBarang.getNamakategori());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListBarangActivity.class);
                intent.putExtra("type", modelKategoriBarang.getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelKategoriBarangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgkategori;
        TextView namakategori;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgkategori = itemView.findViewById(R.id.img_kategoribarang);
            namakategori = itemView.findViewById(R.id.txt_namakategori);
        }
    }
}
