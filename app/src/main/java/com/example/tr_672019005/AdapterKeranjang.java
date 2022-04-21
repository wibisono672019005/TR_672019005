package com.example.tr_672019005;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class AdapterKeranjang extends RecyclerView.Adapter<AdapterKeranjang.ViewHolder> {

    Context context;
    ArrayList<ModelKeranjang> modelKeranjangList;
    int totalPesanan;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    public AdapterKeranjang(Context context, ArrayList<ModelKeranjang> modelKeranjangList) {
        this.context = context;
        this.modelKeranjangList = modelKeranjangList;
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
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

        Glide.with(context).load(modelKeranjang.getImgbarang()).into(holder.img_gambarbarang);
        holder.namabarang.setText(modelKeranjang.getNamabarang());
        holder.hargabarang.setText(modelKeranjang.getHargabarang());
        holder.totalbarang.setText(modelKeranjang.getTotalbarang());
        holder.totalharga.setText(String.valueOf(modelKeranjang.getTotalharga()));

        //Menghapus barang dari Keranjang
        holder.img_hapusbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("Keranjang")
                        .document(modelKeranjang.getBarangKeranjangId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    modelKeranjangList.remove(modelKeranjangList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Barang berhasil dihapus dari Keranjang.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



    }

    @Override
    public int getItemCount() {
        return modelKeranjangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namabarang, hargabarang, totalbarang, totalharga;
        ImageView img_gambarbarang, img_hapusbarang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namabarang = itemView.findViewById(R.id.txt_namabarang);
            hargabarang = itemView.findViewById(R.id.txt_hargabarang);
            totalbarang = itemView.findViewById(R.id.txt_jumlahbarang);
            totalharga = itemView.findViewById(R.id.txt_totalharga);
            img_gambarbarang = itemView.findViewById(R.id.img_gambarbarang);
            img_hapusbarang = itemView.findViewById(R.id.img_hapusbarang);
        }
    }
}
