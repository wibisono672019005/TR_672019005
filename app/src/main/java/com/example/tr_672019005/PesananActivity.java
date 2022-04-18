package com.example.tr_672019005;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.tr_672019005.databinding.ActivityPesananBinding;

public class PesananActivity extends DrawerBaseActivity {

    ActivityPesananBinding activityPesananBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPesananBinding = ActivityPesananBinding.inflate(getLayoutInflater());
        setContentView(activityPesananBinding.getRoot());
        allocateActivityTitle("Pesanan");
    }
}