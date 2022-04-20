package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tr_672019005.databinding.ActivityDaftarPesanBinding;
import com.example.tr_672019005.databinding.ActivityKeranjangBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DaftarPesanActivity extends DrawerBaseActivity {

    ActivityDaftarPesanBinding activityDaftarPesanBinding;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    TextView overTotalAmount;

    AdapterPesanan adapterPesanan;
    RecyclerView recyclerViewDP;
    List<ModelPesanan> modelPesananList;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDaftarPesanBinding = ActivityDaftarPesanBinding.inflate(getLayoutInflater());
        setContentView(activityDaftarPesanBinding.getRoot());
        allocateActivityTitle("Daftar Pesan");

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerViewDP = findViewById(R.id.recyclerViewDP);
        recyclerViewDP.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDP.setVisibility(View.GONE);

        modelPesananList = new ArrayList<ModelPesanan>();
        adapterPesanan = new AdapterPesanan(DaftarPesanActivity.this, (ArrayList<ModelPesanan>) modelPesananList);
        recyclerViewDP.setAdapter(adapterPesanan);

        firebaseFirestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Transaksi").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                ModelPesanan modelPesanan = documentSnapshot.toObject(ModelPesanan.class);
                                modelPesananList.add(modelPesanan);
                                adapterPesanan.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerViewDP.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });

    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalAmount", 0);
            overTotalAmount.setText("Total Bill :" + totalBill);
        }
    };
}