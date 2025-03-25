package com.example.tp3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentMenu extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_menu, container, false);

        //si on appuis sur nouvelle inscripoitn, alros on affiche le formulaire d'inscription dans le fragemnt
        view.findViewById(R.id.nouvelle_inscription).setOnClickListener(v -> {
            FragmentFormulaire fragmentFormulaire = new FragmentFormulaire();
            Bundle bundle = new Bundle();
            bundle.putInt("id", 2);
            fragmentFormulaire.setArguments(bundle);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragmentFormulaire);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        view.findViewById(R.id.connexion).setOnClickListener(v->{
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new FragmentConnexion());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }
}