package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.tr_672019005.databinding.ActivityMenuBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends DrawerBaseActivity {

    ActivityMenuBinding activityMenuBinding;
    RecyclerView recyclerView, kategoriRecyclerView;
    RecyclerView.LayoutManager layoutManager, layoutManagerPencarian;
    ArrayList<ModelBarang> modelBarangArrayList;
    AdapterBarang adapterBarang;
    FirebaseFirestore firebaseFirestore;
    TextView txt_listkategori;
    ProgressBar progressBar;
    ImageSlider imageSlider;

    EditText edit_pencarian;
    RecyclerView recyclerViewPencarian;
    ArrayList<ModelPencarian> modelPencarianArrayList;
    AdapterPencarian adapterPencarian;

    List<ModelKategoriBarang> modelKategoriBarangList;
    AdapterKategori adapterKategori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMenuBinding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(activityMenuBinding.getRoot());
        allocateActivityTitle("Home");

        //PENCARIAN BARANG
        recyclerViewPencarian = findViewById(R.id.recyclerViewPencarian);
        recyclerViewPencarian.setHasFixedSize(true);
        recyclerViewPencarian.setNestedScrollingEnabled(false);
        layoutManagerPencarian = new GridLayoutManager(this, 2);
        recyclerViewPencarian.setLayoutManager(layoutManagerPencarian);
        modelPencarianArrayList = new ArrayList<ModelPencarian>();
        adapterPencarian = new AdapterPencarian(MenuActivity.this, modelPencarianArrayList);
        recyclerViewPencarian.setAdapter(adapterPencarian);

        edit_pencarian = findViewById(R.id.edit_pencarian);
        edit_pencarian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    modelPencarianArrayList.clear();
                    adapterPencarian.notifyDataSetChanged();
                } else {
                    cariBarang(editable.toString());
                }
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVisibility(View.GONE);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        modelBarangArrayList = new ArrayList<ModelBarang>();
        adapterBarang = new AdapterBarang(MenuActivity.this, modelBarangArrayList);
        recyclerView.setAdapter(adapterBarang);

        firebaseFirestore = FirebaseFirestore.getInstance();

        EventChangeListener();

        kategoriRecyclerView = findViewById(R.id.kategoriRecyclerView);

        kategoriRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        modelKategoriBarangList = new ArrayList<ModelKategoriBarang>();
        adapterKategori = new AdapterKategori(MenuActivity.this, modelKategoriBarangList);
        kategoriRecyclerView.setAdapter(adapterKategori);
        firebaseFirestore.collection("Kategori")
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

        imageSlider = findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModelArrayList = new ArrayList<>();

        firebaseFirestore.collection("Carousel").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                slideModelArrayList.add(new SlideModel(queryDocumentSnapshot.getString("url"), ScaleTypes.FIT));
                                imageSlider.setImageList(slideModelArrayList, ScaleTypes.FIT);
                            }
                        } else {
                            Toast.makeText(MenuActivity.this, "Gagal Memuat Gambar dari Database!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MenuActivity.this, "Gagal Memuat Gambar dari Database!", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void cariBarang(String type) {
        if (!type.isEmpty()) {
            firebaseFirestore.collection("Barang").whereEqualTo("type", type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                modelPencarianArrayList.clear();
                                adapterPencarian.notifyDataSetChanged();
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    ModelPencarian modelPencarian = documentSnapshot.toObject(ModelPencarian.class);
                                    modelPencarianArrayList.add(modelPencarian);
                                    adapterPencarian.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }

    private void EventChangeListener() {

        firebaseFirestore.collection("Barang").orderBy("namabarang", Query.Direction.ASCENDING)
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