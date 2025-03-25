package com.example.tp3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

public class FragmentPlanning extends Fragment {
    private BD bd;
    private String selectDate = "";
    private String mail;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_planning, container, false);

        if (getArguments() != null) {
            mail = getArguments().getString("mail");
        }


        bd = BDutils.getBDInstance(getActivity().getApplicationContext());
        TextView date = view.findViewById(R.id.date);
        Button buttonDate = view.findViewById(R.id.buttonDate);
        EditText et_08_10 = view.findViewById(R.id.et_08_10);
        EditText et_10_12 = view.findViewById(R.id.et_10_12);
        EditText et_14_16 = view.findViewById(R.id.et_14_16);
        EditText et_16_18 = view.findViewById(R.id.et_16_18);
        Button buttonSave = view.findViewById(R.id.save);

        buttonDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (DatePicker view1, int year, int month, int dayOfMonth) -> {
                selectDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                date.setText("Date sélectionnée: " + selectDate);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        buttonSave.setOnClickListener(v -> {

            if (selectDate.isEmpty()) {
                Toast.makeText(getActivity(), "Veuillez sélectionner une date", Toast.LENGTH_SHORT).show();
                return;
            }

            String planning08_10 = et_08_10.getText().toString();
            String planning10_12 = et_10_12.getText().toString();
            String planning14_16 = et_14_16.getText().toString();
            String planning16_18 = et_16_18.getText().toString();

            if (planning08_10.isEmpty() || planning10_12.isEmpty() || planning14_16.isEmpty() || planning16_18.isEmpty()) {
                Toast.makeText(getActivity(), "Veuillez remplir tous les créneaux", Toast.LENGTH_SHORT).show();
                return;
            }

            Planning planning = new Planning(selectDate,planning08_10, planning10_12, planning14_16, planning16_18,mail);
            new Thread(() -> {
                bd.planingDAO().insert(planning);
                Intent intent = new Intent(getActivity(), SynthesePlanningActivity.class);
                intent.putExtra("mail", mail); // Passer l'email de l'utilisateur à l'activité de synthèse
                startActivity(intent);
            }).start();

        });



        return view;
    }
}