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
import com.google.firebase.auth.UserProfileChangeRequest;

public class DaftarActivity extends AppCompatActivity {

    private TextView txtDaftar_judul, txtDaftar_bio, txtDaftar_masuk;
    private EditText editDaftar_nama, editDaftar_email, editDaftar_katasandi;
    private Button btnDaftar;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        txtDaftar_judul = findViewById(R.id.txtDaftar_judul);
        txtDaftar_bio = findViewById(R.id.txtDaftar_bio);
        txtDaftar_masuk = findViewById(R.id.txtDaftar_masuk);
        editDaftar_nama = findViewById(R.id.editDaftar_nama);
        editDaftar_email = findViewById(R.id.editDaftar_email);
        editDaftar_katasandi = findViewById(R.id.editDaftar_katasandi);
        btnDaftar = findViewById(R.id.btnDaftar);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(DaftarActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan menunggu, sedang diproses!");
        progressDialog.setCancelable(false);

        txtDaftar_masuk.setOnClickListener(view -> {
            finish();
        });
        btnDaftar.setOnClickListener(view -> {
            if (editDaftar_nama.getText().length() > 0 && editDaftar_email.getText().length() > 0 && editDaftar_katasandi.getText().length() > 0) {
                daftarAkun(
                        editDaftar_nama.getText().toString(),
                        editDaftar_email.getText().toString(),
                        editDaftar_katasandi.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void daftarAkun(String nama, String email, String katasandi) {
        //Coding untuk mendaftar Akun
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, katasandi)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            if (firebaseUser != null) {
                                UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nama)
                                        .build();
                                firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        reload();
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Registrasi akun gagal, coba lagi!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
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