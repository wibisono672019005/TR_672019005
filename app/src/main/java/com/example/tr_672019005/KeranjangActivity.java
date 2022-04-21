package com.example.tr_672019005;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tr_672019005.databinding.ActivityKeranjangBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class KeranjangActivity extends DrawerBaseActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    TextView overTotalAmount;
    Button btnPilihAlamat;

    ActivityKeranjangBinding activityKeranjangBinding;
    AdapterKeranjang adapterKeranjang;
    RecyclerView recyclerViewKeranjang;
    List<ModelKeranjang> modelKeranjangList;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityKeranjangBinding = ActivityKeranjangBinding.inflate(getLayoutInflater());
        setContentView(activityKeranjangBinding.getRoot());
        allocateActivityTitle("Keranjang");

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        btnPilihAlamat = findViewById(R.id.btnPilihAlamat);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerViewKeranjang = findViewById(R.id.recyclerViewKeranjang);
        recyclerViewKeranjang.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewKeranjang.setVisibility(View.GONE);

        overTotalAmount = findViewById(R.id.txt_totalharga);

        modelKeranjangList = new ArrayList<ModelKeranjang>();
        adapterKeranjang = new AdapterKeranjang(KeranjangActivity.this, (ArrayList<ModelKeranjang>) modelKeranjangList);
        recyclerViewKeranjang.setAdapter(adapterKeranjang);

        firebaseFirestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Keranjang").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                String barangKeranjangId = documentSnapshot.getId();
                                ModelKeranjang modelKeranjang = documentSnapshot.toObject(ModelKeranjang.class);

                                modelKeranjang.setBarangKeranjangId(barangKeranjangId);

                                modelKeranjangList.add(modelKeranjang);
                                adapterKeranjang.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerViewKeranjang.setVisibility(View.VISIBLE);
                            }
                            jumlahTotalHargaKeranjang(modelKeranjangList);
                        }
                    }
                    
                });

        btnPilihAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlamatPesanActivity.class);
                intent.putExtra("itemList", (Serializable) modelKeranjangList);
                startActivity(intent);
            }
        });
    }

    private void jumlahTotalHargaKeranjang(List<ModelKeranjang> modelKeranjangList) {
        double totalKeranjangku = 0.0;
        for (ModelKeranjang modelKeranjang : modelKeranjangList) {
            totalKeranjangku += modelKeranjang.getTotalharga();
        }

        overTotalAmount.setText("" + totalKeranjangku);
    }


}