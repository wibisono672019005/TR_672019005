package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView, kategoriRecyclerView;
    ArrayList<Barang> barangArrayList;
    AdapterBarang adapterBarang;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    List<KategoriBarang> kategoriBarangList;
    AdapterKategori adapterKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        progressDialog = new ProgressDialog(MenuActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan menunggu, sedang diproses!");
        progressDialog.setCancelable(false);
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        barangArrayList = new ArrayList<Barang>();
        adapterBarang = new AdapterBarang(MenuActivity.this, barangArrayList);
        recyclerView.setAdapter(adapterBarang);

        db = FirebaseFirestore.getInstance();

        EventChangeListener();

        kategoriRecyclerView = findViewById(R.id.kategoriRecyclerView);

        kategoriRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        kategoriBarangList = new ArrayList<KategoriBarang>();
        adapterKategori = new AdapterKategori(MenuActivity.this, kategoriBarangList);
        kategoriRecyclerView.setAdapter(adapterKategori);
        db.collection("Kategori")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                KategoriBarang kategoriBarang = document.toObject(KategoriBarang.class);
                                kategoriBarangList.add(kategoriBarang);
                                adapterKategori.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(MenuActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void EventChangeListener() {

        db.collection("Barang").orderBy("namabarang", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Log.e("FireStore Error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                barangArrayList.add(dc.getDocument().toObject(Barang.class));
                            }
                            adapterBarang.notifyDataSetChanged();
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }

                    }
                });

    }
}