package com.example.tp3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Ex1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);
    }

    public void envoyerDonnees(Bundle bundle) {
        FragmentAffichage fragmentAffichage = (FragmentAffichage) getSupportFragmentManager().findFragmentById(R.id.fragmentaffichage);
        if (fragmentAffichage != null) {
            fragmentAffichage.afficherDonnees(bundle);
        }
    }
}
