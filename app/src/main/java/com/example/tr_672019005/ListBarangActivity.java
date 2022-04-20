package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.MenuItem;
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
    RecyclerView.LayoutManager layoutManager;
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
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        modelBarangArrayList = new ArrayList<>();
        adapterBarang = new AdapterBarang(this, modelBarangArrayList);
        recyclerView.setAdapter(adapterBarang);

        //Mendapatkan Type "aksesoris"
        if (type != null && type.equalsIgnoreCase("aksesoris")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "aksesoris").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //Mendapatkan Type "kandang"
        if (type != null && type.equalsIgnoreCase("kandang")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "kandang").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //Mendapatkan Type "makanankucing"
        if (type != null && type.equalsIgnoreCase("makanankucing")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "makanankucing").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //Mendapatkan Type "makanananjing"
        if (type != null && type.equalsIgnoreCase("makanananjing")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "makanananjing").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //Mendapatkan Type "grooming"
        if (type != null && type.equalsIgnoreCase("grooming")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "grooming").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //Mendapatkan Type "mainan"
        if (type != null && type.equalsIgnoreCase("mainan")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "mainan").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //Mendapatkan Type "susu"
        if (type != null && type.equalsIgnoreCase("susu")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "susu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //Mendapatkan Type "obatvitamin"
        if (type != null && type.equalsIgnoreCase("obatvitamin")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "obatvitamin").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        //Mendapatkan Type "shampo"
        if (type != null && type.equalsIgnoreCase("shampo")) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", "shampo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

    //Toolbar Kembali
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}