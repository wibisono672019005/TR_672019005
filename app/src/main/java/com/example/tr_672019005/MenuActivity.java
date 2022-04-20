package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tr_672019005.databinding.ActivityMenuBinding;
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

public class MenuActivity extends DrawerBaseActivity {

    ActivityMenuBinding activityMenuBinding;
    RecyclerView recyclerView, kategoriRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ModelBarang> modelBarangArrayList;
    AdapterBarang adapterBarang;
    FirebaseFirestore db;
    TextView txt_listkategori;
    ProgressBar progressBar;

    List<ModelKategoriBarang> modelKategoriBarangList;
    AdapterKategori adapterKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMenuBinding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(activityMenuBinding.getRoot());
        allocateActivityTitle("Home");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVisibility(View.GONE);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        modelBarangArrayList = new ArrayList<ModelBarang>();
        adapterBarang = new AdapterBarang(MenuActivity.this, modelBarangArrayList);
        recyclerView.setAdapter(adapterBarang);

        db = FirebaseFirestore.getInstance();

        EventChangeListener();

        kategoriRecyclerView = findViewById(R.id.kategoriRecyclerView);

        kategoriRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        modelKategoriBarangList = new ArrayList<ModelKategoriBarang>();
        adapterKategori = new AdapterKategori(MenuActivity.this, modelKategoriBarangList);
        kategoriRecyclerView.setAdapter(adapterKategori);
        db.collection("Kategori")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ModelKategoriBarang modelKategoriBarang = document.toObject(ModelKategoriBarang.class);
                                modelKategoriBarangList.add(modelKategoriBarang);
                                adapterKategori.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(MenuActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        txt_listkategori = findViewById(R.id.txt_listkategori);
        txt_listkategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlistkategori = new Intent(MenuActivity.this, ListKategoriActivity.class);
                startActivity(intentlistkategori);
            }
        });

    }

    private void EventChangeListener() {

        db.collection("Barang").orderBy("namabarang", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("FireStore Error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                modelBarangArrayList.add(dc.getDocument().toObject(ModelBarang.class));
                            }
                            adapterBarang.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }

                    }
                });

    }
}