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

import com.bumptech.glide.Glide;
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
    int totalbarang = 1;
    int totalharga = 0;
    Button btnTambahKeranjang;
    Toolbar toolbar;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    Barang barang = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        toolbar = findViewById(R.id.toolbar_detailbarang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Barang) {
            barang = (Barang) object;
        }

        txt_jumlahbarang = findViewById(R.id.txt_jumlahbarang);

        img_detailbarang = findViewById(R.id.img_detailbarang);
        img_tambahbarang = findViewById(R.id.img_tambahbarang);
        img_kurangbarang = findViewById(R.id.img_kurangbarang);
        txt_detailnama = findViewById(R.id.txt_detailnama);
        txt_detailharga = findViewById(R.id.txt_detailharga);
        txt_detaildeskripsi = findViewById(R.id.txt_detaildeskripsi);

        if (barang != null) {
            Glide.with(getApplicationContext()).load(barang.getImgbarang()).into(img_detailbarang);
            txt_detailnama.setText(barang.getNamabarang());
            txt_detailharga.setText(barang.getHargabarang());
            txt_detaildeskripsi.setText(barang.getDeskripsibarang());

            totalharga = barang.getHargabarang() * totalbarang;

        }

        img_tambahbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalbarang > 1) {
                    totalbarang--;
                    txt_jumlahbarang.setText(String.valueOf(totalbarang));
                }
            }
        });

        img_kurangbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalbarang < 10) {
                    totalbarang++;
                    txt_jumlahbarang.setText(String.valueOf(totalbarang));
                }
            }
        });

        btnTambahKeranjang = findViewById(R.id.btnTambahKeranjang);
        btnTambahKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tambahkeranjang();
            }
        });
    }

    private void tambahkeranjang() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> keranjang = new HashMap<>();

        keranjang.put("namabarang", barang.getNamabarang());
        keranjang.put("hargabarang", txt_detailharga.getText().toString());
        keranjang.put("currentDate", saveCurrentDate);
        keranjang.put("currentTime", saveCurrentTime);
        keranjang.put("totalbarang", txt_jumlahbarang.getText().toString());
        keranjang.put("totalharga", totalharga);


    }
}