package com.example.tr_672019005;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tr_672019005.databinding.ActivityProfilBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilActivity extends DrawerBaseActivity {

    ActivityProfilBinding activityProfilBinding;
    private TextView txt_profilnama, txt_profilemail;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityProfilBinding = ActivityProfilBinding.inflate(getLayoutInflater());
        setContentView(activityProfilBinding.getRoot());
        allocateActivityTitle("Profil");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        txt_profilnama = (TextView) findViewById(R.id.txt_profilnama);
        txt_profilemail = (TextView) findViewById(R.id.txt_profilemail);

        if (firebaseUser != null) {
            txt_profilnama.setText(firebaseUser.getDisplayName());
            txt_profilemail.setText(firebaseUser.getEmail());
        } else {
            txt_profilnama.setText("Login Gagal!");
        }
    }
}