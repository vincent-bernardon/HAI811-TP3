package com.example.tp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentAffichage extends Fragment {
    private TextView affichage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_affichage, container, false);
        affichage = view.findViewById(R.id.affichage);
        return view;
    }

    public void afficherDonnees(Bundle bundle) {
        if (bundle != null) {
            String result = "Login: " + bundle.getString("login") + "\n" +
                    "Nom: " + bundle.getString("nom") + "\n" +
                    "Date de naissance: " + bundle.getString("date_naissance") + "\n" +
                    "Téléphone: " + bundle.getString("num_telephone") + "\n" +
                    "Mail: " + bundle.getString("mail") + "\n" +
                    "Intérêts: " + bundle.getString("interets");
            affichage.setText(result);
        }
    }
}
