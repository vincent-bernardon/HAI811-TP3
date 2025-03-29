package com.example.tp3;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class SynthesePlanningActivity extends AppCompatActivity {

    private BD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_synthese_planning);

        bd = BDutils.getBDInstance(getApplicationContext());

        String mail = getIntent().getStringExtra("mail");

        new Thread(() -> {
            List<Planning> plannings = bd.planingDAO().getPlanningsForUser(mail);
            if (plannings != null && !plannings.isEmpty()) {
                //on dois prendre le planing d'aujourd'hui ou le planing le plus récent (pas de remonter dans le temps)
                //les planing sont trie par DESC de base via le requete SQL
                // mais on peux enregistre des planing pour le futur donc il faut comparé avec la date d'aujourd'hui

                //prendre la date d'aujourd'hui au format yyyy-MM-dd
                String dateToday = DateFormat.format("yyyy-M-dd", System.currentTimeMillis()).toString();
                Planning dernierPlanning = plannings.get(0);
                boolean planningTrouve = false;
                for (Planning planning : plannings) {
                    System.out.println(planning.getDate());
                    System.out.println(dateToday);
                    if (planning.getDate().equals(dateToday)) {
                        System.out.println("planning trouvé pour aujourd'hui");
                        dernierPlanning = planning;
                        planningTrouve = true;
                        break;
                    }
                }

                //sinon on dois trouver le planing le plus récent
                if(!planningTrouve) {
                    for (Planning planning : plannings) {
                        if (planning.getDate().compareTo(dernierPlanning.getDate()) > 0) {
                            dernierPlanning = planning;
                        }
                    }
                }

                Planning finalDernierPlanning = dernierPlanning;
                runOnUiThread(() -> afficherSynthese(finalDernierPlanning));
            }
        }).start();

        //si on appuis sur go back
        findViewById(R.id.go_back).setOnClickListener(v -> {
            finish();
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void afficherSynthese(Planning dernierPlanning) {
        TextView textViewSynthese = findViewById(R.id.synthese);

        String synthese = "Date: " + dernierPlanning.getDate() + "\n\n"
                + "08h-10h: " + dernierPlanning.getPlanning_08_10() + "\n"
                + "10h-12h: " + dernierPlanning.getPlanning_10_12() + "\n"
                + "14h-16h: " + dernierPlanning.getPlanning_14_16() + "\n"
                + "16h-18h: " + dernierPlanning.getPlanning_16_18() + "\n";

        textViewSynthese.setText(synthese);
    }
}