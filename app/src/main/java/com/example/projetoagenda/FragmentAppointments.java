package com.example.projetoagenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentAppointments extends Fragment {

    private ScrollView scrollView;
    private TextView textViewAppointments;
    private Button buttonToday;
    private Button buttonOtherDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        scrollView = view.findViewById(R.id.scrollView);
        textViewAppointments = view.findViewById(R.id.textViewAppointments);
        buttonToday = view.findViewById(R.id.buttonToday);
        buttonOtherDate = view.findViewById(R.id.buttonOtherDate);

        // Aqui você pode adicionar funcionalidade aos botões, como definir os listeners
        buttonToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para exibir compromissos do dia corrente
                // ...
            }
        });

        buttonOtherDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para abrir o fragmento de diálogo e atualizar os compromissos
            }
        });

        return view;
    }
}

