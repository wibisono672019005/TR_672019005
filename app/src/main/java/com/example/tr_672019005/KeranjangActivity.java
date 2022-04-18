package com.example.tr_672019005;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.tr_672019005.databinding.ActivityKeranjangBinding;
import com.example.tr_672019005.databinding.ActivityPesananBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class KeranjangActivity extends DrawerBaseActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;

    ActivityKeranjangBinding activityKeranjangBinding;
    AdapterKeranjang adapterKeranjang;
    RecyclerView recyclerViewKeranjang;
    List<ModelKeranjang> modelKeranjangList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityKeranjangBinding = ActivityKeranjangBinding.inflate(getLayoutInflater());
        setContentView(activityKeranjangBinding.getRoot());
        allocateActivityTitle("Keranjang");

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerViewKeranjang = findViewById(R.id.recyclerViewKeranjang);
        recyclerViewKeranjang.setLayoutManager(new LinearLayoutManager(this));

        modelKeranjangList = new ArrayList<ModelKeranjang>();
        adapterKeranjang = new AdapterKeranjang(KeranjangActivity.this, (ArrayList<ModelKeranjang>) modelKeranjangList);
        recyclerViewKeranjang.setAdapter(adapterKeranjang);

        db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                ModelKeranjang modelKeranjang = documentSnapshot.toObject(ModelKeranjang.class);
                                modelKeranjangList.add(modelKeranjang);
                                adapterKeranjang.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }
}