package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListBarangActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    AdapterBarang adapterBarang;
    ArrayList<ModelBarang> modelBarangArrayList;
    Toolbar toolbar_listbarang;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_barang);

        toolbar_listbarang = findViewById(R.id.toolbar_listbarang);
        setSupportActionBar(toolbar_listbarang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        firebaseFirestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.recyclerViewListBarang);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelBarangArrayList = new ArrayList<>();
        adapterBarang = new AdapterBarang(this, modelBarangArrayList);
        recyclerView.setAdapter(adapterBarang);

        //Mendapatkan Type "anjing"
        if (type != null && type.equalsIgnoreCase("anjing")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "anjing").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                        ModelBarang modelBarang = documentSnapshot.toObject(ModelBarang.class);
                        modelBarangArrayList.add(modelBarang);
                        adapterBarang.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        //Mendapatkan Type "kucing"
        if (type != null && type.equalsIgnoreCase("kucing")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "kucing").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                        ModelBarang modelBarang = documentSnapshot.toObject(ModelBarang.class);
                        modelBarangArrayList.add(modelBarang);
                        adapterBarang.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    }
}