package com.example.projetoagenda;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FragmentAppointments extends Fragment {

    private ScrollView scrollView;
    private TextView textViewAppointments;
    private Button buttonToday;
    private Button buttonOtherDate;
    private Calendar selectedDate = Calendar.getInstance();
    private String dataSelecionada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        scrollView = view.findViewById(R.id.scrollView);
        textViewAppointments = view.findViewById(R.id.textViewAppointments);
        buttonToday = view.findViewById(R.id.buttonToday);
        buttonOtherDate = view.findViewById(R.id.buttonOtherDate);



        buttonToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String dataAtual = dateFormat.format(calendar.getTime());

                exibirCompromissos(dataAtual);
            }
        });

        buttonOtherDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatePicker();
            }
        });

        return view;
    }

    private void mostrarDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate.set(Calendar.YEAR, year);
                        selectedDate.set(Calendar.MONTH, month);
                        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        dataSelecionada = dateFormat.format(selectedDate.getTime());

                        exibirCompromissos(dataSelecionada);
                    }
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    public void exibirCompromissos(String data) {
        String compromissosDoDia = obterCompromissosDoDia(data);

        if (compromissosDoDia.isEmpty()) {
            textViewAppointments.setText("Sem compromissos marcados");
        } else {
            // Adiciona uma quebra de linha se o TextView já contiver texto
            if (!textViewAppointments.getText().toString().isEmpty()) {
                textViewAppointments.append("\n");
            }
            textViewAppointments.append(compromissosDoDia);
        }
    }

    private String obterCompromissosDoDia(String data) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dataAtual = dateFormat.format(calendar.getTime());

        if (data.equals(dataAtual)) {
            return "Compromissos para " + data + ":\n1. Reunião às 10:00\n2. Almoço às 12:30";
        } else {
            return "Sem compromissos marcados";
        }
    }

    public void obterDescricaoDoFragment() {
    }
}

