package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
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

public class DetailCariBarang extends AppCompatActivity {

    ImageView img_detailbarang, img_tambahbarang, img_kurangbarang;
    TextView txt_detailnama, txt_detailharga, txt_detaildeskripsi, txt_jumlahbarang;
    int totalbarang = 1;
    int totalharga = 0;
    Button btnTambahKeranjang;
    Toolbar toolbar;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    ModelPencarian modelPencarian = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cari_barang);

        toolbar = findViewById(R.id.toolbar_detailbarang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ModelPencarian) {
            modelPencarian = (ModelPencarian) object;
        }

        txt_jumlahbarang = findViewById(R.id.txt_jumlahbarang);
        img_detailbarang = findViewById(R.id.img_detailbarang);
        img_tambahbarang = findViewById(R.id.img_tambahbarang);
        img_kurangbarang = findViewById(R.id.img_kurangbarang);
        txt_detailnama = findViewById(R.id.txt_detailnama);
        txt_detailharga = findViewById(R.id.txt_detailharga);
        txt_detaildeskripsi = findViewById(R.id.txt_detaildeskripsi);

        if (modelPencarian != null) {
            Glide.with(getApplicationContext()).load(modelPencarian.getImgbarang()).into(img_detailbarang);
            txt_detailnama.setText(modelPencarian.getNamabarang());
            txt_detailharga.setText(String.valueOf(modelPencarian.getHargabarang()));
            txt_detaildeskripsi.setText(modelPencarian.getDeskripsibarang());

            totalharga = (modelPencarian.getHargabarang()) * totalbarang;

        }

        img_tambahbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalbarang > 1) {
                    totalbarang--;
                    txt_jumlahbarang.setText(String.valueOf(totalbarang));
                    totalharga = (modelPencarian.getHargabarang()) * totalbarang;
                }
            }
        });

        img_kurangbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalbarang < 100) {
                    totalbarang++;
                    txt_jumlahbarang.setText(String.valueOf(totalbarang));
                    totalharga = (modelPencarian.getHargabarang()) * totalbarang;
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

        SimpleDateFormat currentTime = new SimpleDateFormat("dd MM, yyyy HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> keranjangMap = new HashMap<>();

        keranjangMap.put("namabarang", modelPencarian.getNamabarang());
        keranjangMap.put("hargabarang", txt_detailharga.getText().toString());
        keranjangMap.put("currentTime", saveCurrentTime);
        keranjangMap.put("totalbarang", txt_jumlahbarang.getText().toString());
        keranjangMap.put("totalharga", totalharga);
        keranjangMap.put("imgbarang", modelPencarian.getImgbarang());

        firebaseFirestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Keranjang").add(keranjangMap)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailCariBarang.this, "Berhasil ditambahkan ke Keranjang", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
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