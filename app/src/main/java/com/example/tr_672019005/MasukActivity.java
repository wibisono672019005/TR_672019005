package com.example.tr_672019005;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MasukActivity extends AppCompatActivity {

    private TextView txtMasuk_judul, txtMasuk_bio, txtMasuk_daftar;
    private EditText editMasuk_email, editMasuk_katasandi;
    private Button btnMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        txtMasuk_judul = findViewById(R.id.txtMasuk_judul);
        txtMasuk_bio = findViewById(R.id.txtMasuk_bio);
        txtMasuk_daftar = findViewById(R.id.txtMasuk_daftar);
        editMasuk_email = findViewById(R.id.editMasuk_email);
        editMasuk_katasandi = findViewById(R.id.editMasuk_katasandi);
        btnMasuk = findViewById(R.id.btnMasuk);

    }
}