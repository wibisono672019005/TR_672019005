package com.example.tr_672019005;

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

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Barang> barangArrayList;
    AdapterBarang adapterBarang;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

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

        db = FirebaseFirestore.getInstance();
        barangArrayList = new ArrayList<Barang>();
        adapterBarang = new AdapterBarang(MenuActivity.this, barangArrayList);

        recyclerView.setAdapter(adapterBarang);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DetailBarang.class));
            }
        });

        EventChangeListener();

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