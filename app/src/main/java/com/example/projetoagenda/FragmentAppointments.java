package com.example.projetoagenda;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FragmentAppointments extends Fragment {

    private AppointmentDB appointmentDB;
    private ScrollView scrollView;
    private TextView textViewAppointments;
    private Button buttonToday;
    private Button buttonOtherDate;
    private Calendar selectedDate = Calendar.getInstance();
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        Context context = getActivity();

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

            }
        });

        return view;
    }

    /*private String getDateToday(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return String.format(Locale.getDefault(), "%02d/%02d/%d", day, month, year);
    }

    void showAppointment(String date) {

    }*/

    public void exibirCompromissos(String data) {
        Log.d("MainActivity", "showAppointment called with date: " + data);
        List<String> compromissosDoDia = obterCompromissosDoDiaDoBanco(data);

        if (compromissosDoDia.isEmpty()) {
            textViewAppointments.setText("Sem compromissos marcados");
        } else {
            // Adiciona uma quebra de linha se o TextView já contiver texto
            if (!textViewAppointments.getText().toString().isEmpty()) {
                textViewAppointments.append("\n");
            }

            // Concatena os compromissos obtidos do banco ao TextView
            for (String compromisso : compromissosDoDia) {
                textViewAppointments.append(compromisso + "\n");
            }
        }
    }

    private List<String> obterCompromissosDoDiaDoBanco(String data) {
        if (appointmentDB != null) {
            String clauseWhere = AppointmentDBSchema.AppointmentTbl.Cols.DATE + " = ?";
            String[] argsWhere = new String[]{data};

            return appointmentDB.listAppointments(clauseWhere, argsWhere);
        }

        return Collections.emptyList();
    }
}

