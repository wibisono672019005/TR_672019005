package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailBarang extends AppCompatActivity {

    ImageView img_detailbarang, img_tambahbarang, img_kurangbarang;
    TextView txt_detailnama, txt_detailharga, txt_detaildeskripsi, txt_jumlahbarang;
    Button btnTambahKeranjang;
    Toolbar toolbar;


    Barang barang = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        toolbar = findViewById(R.id.toolbar_detailbarang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txt_jumlahbarang = findViewById(R.id.txt_jumlahbarang);

        img_detailbarang = findViewById(R.id.img_detailbarang);
        img_tambahbarang = findViewById(R.id.img_tambahbarang);
        img_kurangbarang = findViewById(R.id.img_kurangbarang);
        txt_detailnama = findViewById(R.id.txt_detailnama);
        txt_detailharga = findViewById(R.id.txt_detailharga);
        txt_detaildeskripsi = findViewById(R.id.txt_detaildeskripsi);


    }


}