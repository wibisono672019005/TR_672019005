package com.example.tr_672019005;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterPesanan extends RecyclerView.Adapter<AdapterPesanan.ViewHolder> {

    Context context;
    ArrayList<ModelPesanan> modelPesananArrayList;

    public AdapterPesanan(Context context, ArrayList<ModelPesanan> modelPesananArrayList) {
        this.context = context;
        this.modelPesananArrayList = modelPesananArrayList;
    }

    @NonNull
    @Override
    public AdapterPesanan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesanan_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelPesanan modelPesanan = modelPesananArrayList.get(position);

        Glide.with(context).load(modelPesanan.getImgbarang()).into(holder.img_gambarbarang);
        holder.namabarang.setText(modelPesanan.getNamabarang());
        holder.hargabarang.setText(modelPesanan.getHargabarang());
        holder.totalbarang.setText(modelPesanan.getTotalbarang());
        holder.totalharga.setText(String.valueOf(modelPesanan.getTotalharga()));
        holder.currentTime.setText(modelPesanan.getCurrentTime());
    }

    @Override
    public int getItemCount() {
        return modelPesananArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namabarang, hargabarang, totalbarang, totalharga, currentTime;
        ImageView img_gambarbarang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namabarang = itemView.findViewById(R.id.txt_namabarang);
            hargabarang = itemView.findViewById(R.id.txt_hargabarang);
            totalbarang = itemView.findViewById(R.id.txt_jumlahbarang);
            totalharga = itemView.findViewById(R.id.txt_totalharga);
            img_gambarbarang = itemView.findViewById(R.id.img_gambarbarang);
            currentTime = itemView.findViewById(R.id.txt_currentTime);
        }
    }
}
