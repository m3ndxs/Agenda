package com.example.projetoagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
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
    }

    public String getDescriptionFromFragment() {
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

        Toast.makeText(this, "Descrição: " + descricao + "\nData: " + data + "\nHora: " + hora, Toast.LENGTH_LONG).show();
    }

    private String getDataFromDatePicker() {
        return fragmentDatePicker.getSelectedDate();
    }

    private String getTimeFromTimePicker() {
        return fragmentTimePicker.getSelectedTime();
    }
}