package com.example.tp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentFormulaire extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_formulaire, container, false);

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
            Bundle bundle = new Bundle();
            bundle.putString("login", login.getText().toString());
            bundle.putString("pswd", pswd.getText().toString());
            bundle.putString("nom", nom.getText().toString());
            bundle.putString("date_naissance", dateNaissance.getText().toString());
            bundle.putString("num_telephone", numTelephone.getText().toString());
            bundle.putString("mail", mail.getText().toString());
            bundle.putString("interets", (sport.isChecked() ? "Sport " : "") +
                    (musique.isChecked() ? "Musique " : "") +
                    (cinema.isChecked() ? "Cinéma" : ""));

            ((Ex1) getActivity()).envoyerDonnees(bundle);
        });

        return view;
    }
}
