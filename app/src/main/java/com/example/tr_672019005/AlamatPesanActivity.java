package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AlamatPesanActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    //Variabel
    private GoogleMap gMap;
    private Marker selectedMarker;
    private LatLng selectedPlace;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth auth;

    private TextView txtOrderId, txtSelectedPlace;
    private Button btnPesan, btnKembali;
    Toolbar toolbar;

    private boolean isNewOrder = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat_pesan);

        toolbar = findViewById(R.id.toolbar_alamatpesan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtOrderId = findViewById(R.id.txt_orderId);
        txtSelectedPlace = findViewById(R.id.txt_selectedPlace);
        btnKembali = findViewById(R.id.btn_kembali);
        btnPesan = findViewById(R.id.btn_pesan);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KeranjangActivity.class);
                startActivity(intent);
            }
        });
        btnPesan.setOnClickListener(view -> { saveOrder(); });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

        LatLng Salatiga = new LatLng(-7.3305, 110.5084);

        selectedPlace = Salatiga;
        selectedMarker = gMap.addMarker(new MarkerOptions().position(selectedPlace));

        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(selectedPlace, 15.0f));

        gMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        selectedPlace = latLng;
        selectedMarker.setPosition(selectedPlace);
        gMap.animateCamera(CameraUpdateFactory.newLatLng(selectedPlace));

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(selectedPlace.latitude, selectedPlace.longitude, 1);
            if (addresses != null) {
                Address place = addresses.get(0);
                StringBuilder street = new StringBuilder();

                for (int i = 0; i <= place.getMaxAddressLineIndex(); i++) {
                    street.append(place.getAddressLine(i)).append("\n");
                }
                txtSelectedPlace.setText(street.toString());
            }
            else {
                Toast.makeText(this, "Could not find Address!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Error get Address!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveOrder() {

        String orderId = txtOrderId.getText().toString();
        List<ModelKeranjang> modelKeranjangList = (ArrayList<ModelKeranjang>) getIntent().getSerializableExtra("itemList");
        if (modelKeranjangList != null && modelKeranjangList.size() > 0) {
            for (ModelKeranjang modelKeranjang : modelKeranjangList) {
                String saveCurrentDate, saveCurrentTime;
                Calendar calForDate = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calForDate.getTime());

                final HashMap<String, Object> keranjangMap = new HashMap<>();
                final HashMap<String, Object> alamatMap = new HashMap<>();

                keranjangMap.put("alamatpesanan", alamatMap);
                alamatMap.put("alamat", txtSelectedPlace.getText().toString());
                alamatMap.put("lat", selectedPlace.latitude);
                alamatMap.put("lng", selectedPlace.longitude);

                keranjangMap.put("namabarang", modelKeranjang.getNamabarang());
                keranjangMap.put("hargabarang", modelKeranjang.getHargabarang());
                keranjangMap.put("currentDate", saveCurrentDate);
                keranjangMap.put("currentTime", saveCurrentTime);
                keranjangMap.put("totalbarang", modelKeranjang.getTotalbarang());
                keranjangMap.put("totalharga", modelKeranjang.getTotalharga());
                keranjangMap.put("imgbarang", modelKeranjang.getImgbarang());

                if (isNewOrder) {

                    firebaseFirestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Transaksi").add(keranjangMap)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    Toast.makeText(AlamatPesanActivity.this, "Berhasil Melakukan Pesanan di Pet Shop!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), PesananSuksesActivity.class);
                                    startActivity(intent);
                                }
                            });
                } else {
                    firebaseFirestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Transaksi").document(orderId).set(keranjangMap)
                            .addOnSuccessListener(unused -> {
                                txtSelectedPlace.setText("");

                                isNewOrder = true;
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Gagal ubah data order", Toast.LENGTH_SHORT).show();
                            });
                }
            }
        }
    }

}