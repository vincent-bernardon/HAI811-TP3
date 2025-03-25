package com.example.tp3;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class SelectExo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.select_exo); //c'est la vue
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.selectexo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonExo1 = findViewById(R.id.button_exo1);
        buttonExo1.setOnClickListener(v -> {
            Intent intent = new Intent(SelectExo.this, Ex1.class);
            startActivity(intent);
        });

        Button buttonExo2 = findViewById(R.id.button_exo2);
        buttonExo2.setOnClickListener(v -> {
            Intent intent = new Intent(SelectExo.this, Ex2.class);
            startActivity(intent);
        });

//        Button buttonExo3 = findViewById(R.id.button_exo3);
//        buttonExo3.setOnClickListener(v -> {
//            Intent intent = new Intent(SelectExo.this, Ex3.class);
//            startActivity(intent);
//        });
//
//        Button buttonExo4 = findViewById(R.id.button_exo4);
//        buttonExo4.setOnClickListener(v -> {
//            Intent intent = new Intent(SelectExo.this, Ex4.class);
//            startActivity(intent);
//        });
//
//        Button buttonExo5 = findViewById(R.id.button_exo5);
//        buttonExo5.setOnClickListener(v -> {
//            Intent intent = new Intent(SelectExo.this, Ex5.class);
//            startActivity(intent);
//        });
//
//        Button buttonExo6 = findViewById(R.id.button_exo6);
//        buttonExo6.setOnClickListener(v -> {
//            Intent intent = new Intent(SelectExo.this, Ex6.class);
//            startActivity(intent);
//        });
//
//        Button buttonExo7 = findViewById(R.id.button_exo7);
//        buttonExo7.setOnClickListener(v -> {
//            Intent intent = new Intent(SelectExo.this, Ex7.class);
//            startActivity(intent);
//        });
//
//        Button buttonExo8 = findViewById(R.id.button_exo8);
//        buttonExo8.setOnClickListener(v -> {
//            Intent intent = new Intent(SelectExo.this, Ex8.class);
//            startActivity(intent);
//        });
//
//        Button buttonExo9 = findViewById(R.id.button_exo9);
//        buttonExo9.setOnClickListener(v -> {
//            Intent intent = new Intent(SelectExo.this, Ex9.class);
//            startActivity(intent);
//        });

    }
}
