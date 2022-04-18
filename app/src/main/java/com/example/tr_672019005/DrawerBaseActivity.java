package com.example.tr_672019005;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DrawerBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    private TextView txt_menunama, txt_menuemail;
    private FirebaseUser firebaseUser;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_base, null);
        FrameLayout container = drawerLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(drawerLayout);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar_contentLayout);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.navigationView);
        view = navigationView.getHeaderView(0);
        txt_menunama = (TextView) view.findViewById(R.id.txt_menunama);
        txt_menuemail = (TextView) view.findViewById(R.id.txt_menuemail);

        if (firebaseUser != null) {
            txt_menunama.setText(firebaseUser.getDisplayName());
            txt_menuemail.setText(firebaseUser.getEmail());
        } else {
            txt_menunama.setText("Login Gagal!");
        }

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_drawer_open, R.string.menu_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.nav_home:
                startActivity(new Intent(this, MenuActivity.class));
                overridePendingTransition(0, 0);
                break;

            case R.id.nav_pesanan:
                startActivity(new Intent(this, PesananActivity.class));
                overridePendingTransition(0, 0);
                break;

            case R.id.nav_keranjang:
                startActivity(new Intent(this, KeranjangActivity.class));
                overridePendingTransition(0, 0);
                break;
        }

        return false;
    }

    protected void allocateActivityTitle(String titleString) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleString);
        }
    }
}