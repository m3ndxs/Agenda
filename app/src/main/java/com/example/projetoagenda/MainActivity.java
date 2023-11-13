package com.example.projetoagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FragmentDescription fragmentDescription;
    private FragmentTimePicker fragmentTimePicker;
    private FragmentDatePicker fragmentDatePicker;

    private FragmentAppointments fragmentAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentDescription = new FragmentDescription();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, fragmentDescription)
                .commit();

        fragmentTimePicker = new FragmentTimePicker();
        fragmentDatePicker = new FragmentDatePicker();

        fragmentAppointments = new FragmentAppointments();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainerAppointments, fragmentAppointments )
                    .commit();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new FragmentDescription())
                .commit();

        // Obtem a descrição do CompromissoFragment e imprime no Log
        obterDescricaoDoFragment();

    }

    private void obterDescricaoDoFragment() {
        FragmentDescription compromissoFragment = (FragmentDescription) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

        if (compromissoFragment != null) {
            String descricao = compromissoFragment.getDescription();
            Log.d("MainActivity", "Descrição obtida da CompromissoFragment: " + descricao);
        }
    }

    public String getDescriptionFromFragment() {
        //Log.d("getDescriptionFromFragment", "Obtendo descrição do fragmento");
        return fragmentDescription.getDescription();
    }

    public void mostraDialogoTimePicker(View v) {
        fragmentTimePicker.show(getSupportFragmentManager(), "timePicker");
    }

    public void mostraDialogoDatePicker(View v) {
        fragmentDatePicker.show(getSupportFragmentManager(), "datePicker");
    }

    public void onOkButtonPressed(View v) {
        String descricao = getDescriptionFromFragment();
        String data = getDataFromDatePicker();
        String hora = getTimeFromTimePicker();

        //Toast.makeText(this, "Descrição: " + descricao + "\nData: " + data + "\nHora: " + hora, Toast.LENGTH_LONG).show();
        Log.d("Data", "Data: " + hora);
        Log.d("Hora", "Hora: " + data);
        Log.d("Descricao", "Descricao: " + descricao);
    }

    private String getDataFromDatePicker() {
        return fragmentDatePicker.getSelectedDate();
    }

    private String getTimeFromTimePicker() {
        return fragmentTimePicker.getSelectedTime();
    }
}