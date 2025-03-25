// FragmentFormulaire.java
package com.example.tp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentFormulaire extends Fragment {
    private UtilisateurDAO utilisateurDAO;
    private BD bd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_formulaire, container, false);

        bd = BDutils.getBDInstance(getActivity().getApplicationContext());
        utilisateurDAO = bd.utilisateurDAO();

        EditText login = view.findViewById(R.id.login);
        EditText pswd = view.findViewById(R.id.pswd);
        EditText nom = view.findViewById(R.id.nom);
        EditText dateNaissance = view.findViewById(R.id.date_naissance);
        EditText numTelephone = view.findViewById(R.id.num_telephone);
        EditText mail = view.findViewById(R.id.mail);
        CheckBox sport = view.findViewById(R.id.sport);
        CheckBox musique = view.findViewById(R.id.musique);
        CheckBox cinema = view.findViewById(R.id.cinema);
        Button submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(v -> {
            String email = mail.getText().toString();
            String password = pswd.getText().toString();
            String loginText = login.getText().toString();

            if (!email.contains("@")) {
                Toast.makeText(getActivity(), "Email invalide", Toast.LENGTH_LONG).show();
                return;
            }

            if (password.length() <= 6) {
                Toast.makeText(getActivity(), "Le mot de passe doit contenir plus de 6 caractères", Toast.LENGTH_LONG).show();
                return;
            }

            if (!Character.isLetter(loginText.charAt(0)) || loginText.length() > 10) {
                Toast.makeText(getActivity(), "Le login doit commencer par une lettre et ne pas dépasser 10 caractères", Toast.LENGTH_LONG).show();
                return;
            }

            // on dois utiliser des thread pour les requetes de la base de données sinon l'application va crasher
            new Thread(() -> { // ici on utilise un thread car isEmailInUsed et isLoginInUsed sont des requetes de la base de données
                boolean emailInUse = isEmailInUsed(email);
                boolean loginInUse = isLoginInUsed(loginText);
                getActivity().runOnUiThread(() -> {
                    if(loginInUse) {
                        Toast.makeText(getActivity(), "Login déjà utilisé", Toast.LENGTH_SHORT).show();
                    } else if (emailInUse) {
                        Toast.makeText(getActivity(), "Email déjà utilisé", Toast.LENGTH_SHORT).show();
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putString("login", login.getText().toString());
                        bundle.putString("pswd", pswd.getText().toString());
                        bundle.putString("nom", nom.getText().toString());
                        bundle.putString("date_naissance", dateNaissance.getText().toString());
                        bundle.putString("num_telephone", numTelephone.getText().toString());
                        bundle.putString("mail", email);
                        bundle.putString("interets", (sport.isChecked() ? "Sport " : "") +
                                (musique.isChecked() ? "Musique " : "") +
                                (cinema.isChecked() ? "Cinéma" : ""));

                        new Thread(()->enregistrerUtilisateur(bundle)).start();// ici on utilise un thread car enregistrerUtilisateur est une requete de la base de données

                        //envoi des données à l'activité pour l'affichage pour EX1 et donc détecter si on vien de EX1 ou EX2 pour faire des chose différente
//                        ((Ex1) getActivity()).envoyerDonnees(bundle);

                        //EX2 allez vers la page de connexion
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new FragmentConnexion());
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }).start();
        });

        return view;
    }

    private boolean isEmailInUsed(String email) {
        return utilisateurDAO.isEmailInUsed(email) > 0;
    }
    private boolean isLoginInUsed(String login) {
        return utilisateurDAO.isLoginInUsed(login) > 0;
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
        bd.utilisateurDAO().insert(utilisateur);
    }
}