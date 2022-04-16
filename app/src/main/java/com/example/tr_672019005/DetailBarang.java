package com.example.tr_672019005;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailBarang extends AppCompatActivity {

    ImageView img_detailbarang, img_tambahbarang, img_kurangbarang;
    TextView txt_detailnama, txt_detailharga, txt_detaildeskripsi;
    Button btnTambahKeranjang;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        img_detailbarang = findViewById(R.id.img_detailbarang);
        img_tambahbarang = findViewById(R.id.img_tambahbarang);
        img_kurangbarang = findViewById(R.id.img_kurangbarang);
        txt_detailnama = findViewById(R.id.txt_detailnama);
        txt_detailharga = findViewById(R.id.txt_detailharga);
        txt_detaildeskripsi = findViewById(R.id.txt_detaildeskripsi);
        btnTambahKeranjang = findViewById(R.id.btnTambahKeranjang);

    }
}