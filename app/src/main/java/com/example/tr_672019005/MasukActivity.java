package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MasukActivity extends AppCompatActivity {

    private TextView txtMasuk_judul, txtMasuk_bio, txtMasuk_daftar;
    private EditText editMasuk_email, editMasuk_katasandi;
    private Button btnMasuk;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(MasukActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan menunggu, sedang diproses!");
        progressDialog.setCancelable(false);

        //Tombol untuk Mendaftar
        txtMasuk_daftar.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), DaftarActivity.class));
        });
        //Button untuk Masuk
        btnMasuk.setOnClickListener(view -> {
            if (editMasuk_email.getText().length() > 0 && editMasuk_katasandi.getText().length() > 0) {
                masukAkun(editMasuk_email.getText().toString(), editMasuk_katasandi.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void masukAkun(String email, String katasandi) {
        //Coding untuk Masuk
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, katasandi).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    if (task.getResult().getUser() != null) {
                        reload();
                    } else {
                        Toast.makeText(getApplicationContext(), "Masuk akun gagal, coba lagi!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Masuk akun gagal, coba lagi!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        //Jika user sudah login, maka akan menjalankan reload
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}