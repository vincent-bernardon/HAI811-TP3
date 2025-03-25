package com.example.tp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

public class FragmentConnexion extends Fragment {
    private BD bd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_connexion, container, false);


        bd = BDutils.getBDInstance(getActivity());

        EditText editTextLogin = view.findViewById(R.id.login);
        EditText editTextPassword = view.findViewById(R.id.mdp);
        Button buttonConnexion = view.findViewById(R.id.buttonConnexion);


        buttonConnexion.setOnClickListener(v -> {
            String login = editTextLogin.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread(() -> {
                //vérification dans la BD
                Utilisateur utilisateur = bd.utilisateurDAO().getUtilisateur(login, password);
                if (utilisateur != null) {
                    Bundle args = new Bundle();
                    args.putString("mail", utilisateur.getMail());
                    FragmentPlanning fragmentPlanning = new FragmentPlanning();
                    fragmentPlanning.setArguments(args);

                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, fragmentPlanning);
                    transaction.addToBackStack(null);
                    transaction.commit();

                } else {
                    Toast.makeText(getActivity(), "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                }
            }).start();
        });


        return view;
    }
}