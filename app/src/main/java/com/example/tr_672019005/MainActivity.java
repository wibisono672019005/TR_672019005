package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextView txt_namapengguna;
    private Button btnKeluar, btnMenuActivity;
    private FirebaseUser firebaseUser;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_namapengguna = findViewById(R.id.txt_namapengguna);
        btnKeluar = findViewById(R.id.btnKeluar);
        btnMenuActivity = findViewById(R.id.btnMenuActivity);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            txt_namapengguna.setText(firebaseUser.getDisplayName());
        } else {
            txt_namapengguna.setText("Login Gagal!");
        }

        btnKeluar.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), MasukActivity.class));
            finish();
        });

        btnMenuActivity.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        });
    }

}