package com.example.tp3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

public class Ex1 extends AppCompatActivity {
    private BD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        bd = BDutils.getBDInstance(getApplicationContext());

        FragmentFormulaire fragmentFormulaire = new FragmentFormulaire();
        Bundle bundle = new Bundle();
        bundle.putInt("id", 1);
        fragmentFormulaire.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentformulaire, fragmentFormulaire);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void envoyerDonnees(Bundle bundle) {
        FragmentAffichage fragmentAffichage = (FragmentAffichage) getSupportFragmentManager().findFragmentById(R.id.fragmentaffichage);
        if (fragmentAffichage != null) {
            fragmentAffichage.afficherDonnees(bundle);
        }
    }

}
