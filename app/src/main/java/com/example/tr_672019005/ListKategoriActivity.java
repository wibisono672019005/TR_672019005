package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListKategoriActivity extends AppCompatActivity {

    RecyclerView listKategoriRV;
    List<ListKategoriBarang> listKategoriBarangList;
    AdapterListKategori adapterListKategori;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kategori_activity);

        listKategoriRV = findViewById(R.id.listKategoriRV);

        db = FirebaseFirestore.getInstance();

        listKategoriRV.setLayoutManager(new LinearLayoutManager(this));
        listKategoriBarangList = new ArrayList<ListKategoriBarang>();
        adapterListKategori = new AdapterListKategori(ListKategoriActivity.this, listKategoriBarangList);
        listKategoriRV.setAdapter(adapterListKategori);
        db.collection("Kategori")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ListKategoriBarang listKategoriBarang = document.toObject(ListKategoriBarang.class);
                                listKategoriBarangList.add(listKategoriBarang);
                                adapterListKategori.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(ListKategoriActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}