package com.example.tp3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

public class Ex1 extends AppCompatActivity {
    private BD bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        bd = Room.databaseBuilder(getApplicationContext(),
                BD.class, "bd").build();
    }

    public void envoyerDonnees(Bundle bundle) {
        FragmentAffichage fragmentAffichage = (FragmentAffichage) getSupportFragmentManager().findFragmentById(R.id.fragmentaffichage);
        if (fragmentAffichage != null) {
            fragmentAffichage.afficherDonnees(bundle);
        }
    }

    public void enregistrerUtilisateur(Bundle bundle) {
        String login = bundle.getString("login");
        String pswd = bundle.getString("pswd");
        String nom = bundle.getString("nom");
        String dateNaissance = bundle.getString("date_naissance");
        String numTelephone = bundle.getString("num_telephone");
        String mail = bundle.getString("mail");
        String interets = bundle.getString("interets");

        // Créer un objet Utilisateur
        Utilisateur utilisateur = new Utilisateur(login, pswd, nom, dateNaissance, numTelephone, mail, interets);

        // Insérer dans la base de données
        new Thread(() -> {
            bd.utilisateurDAO().insert(utilisateur);
        }).start();
    }
}
