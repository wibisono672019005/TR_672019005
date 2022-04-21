package com.example.tr_672019005;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tr_672019005.databinding.ActivityTentangBinding;


public class TentangActivity extends DrawerBaseActivity {

    ActivityTentangBinding activityTentangBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTentangBinding = ActivityTentangBinding.inflate(getLayoutInflater());
        setContentView(activityTentangBinding.getRoot());
        allocateActivityTitle("Tentang");
    }
}