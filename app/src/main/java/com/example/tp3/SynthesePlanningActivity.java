package com.example.tp3;

import android.os.Bundle;
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
                Planning dernierPlanning = plannings.get(0); //recupere le dernier planning (trié par date DESC)
                runOnUiThread(() -> afficherSynthese(dernierPlanning));
            }
        }).start();



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