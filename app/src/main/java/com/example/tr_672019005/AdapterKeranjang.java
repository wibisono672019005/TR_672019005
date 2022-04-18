package com.example.tr_672019005;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterKeranjang extends RecyclerView.Adapter<AdapterKeranjang.ViewHolder> {

    Context context;
    ArrayList<ModelKeranjang> modelKeranjangList;

    public AdapterKeranjang(Context context, ArrayList<ModelKeranjang> modelKeranjangList) {
        this.context = context;
        this.modelKeranjangList = modelKeranjangList;
    }

    @NonNull
    @Override
    public AdapterKeranjang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.keranjang_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelKeranjang modelKeranjang = modelKeranjangList.get(position);

        holder.namabarang.setText(modelKeranjang.getNamabarang());
        holder.hargabarang.setText(modelKeranjang.getHargabarang());
        holder.totalbarang.setText(modelKeranjang.getTotalbarang());
        holder.totalharga.setText(String.valueOf(modelKeranjang.getTotalharga()));
    }

    @Override
    public int getItemCount() {
        return modelKeranjangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namabarang, hargabarang, totalbarang, totalharga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namabarang = itemView.findViewById(R.id.txt_namabarang);
            hargabarang = itemView.findViewById(R.id.txt_hargabarang);
            totalbarang = itemView.findViewById(R.id.txt_jumlahbarang);
            totalharga = itemView.findViewById(R.id.txt_totalharga);
        }
    }
}
