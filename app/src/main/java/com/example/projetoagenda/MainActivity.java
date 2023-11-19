package com.example.projetoagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private String dateSelection;
    private String timeSelection;
    private EditText textDescription;
    private AppointmentDB appointmentDB;
    private TextView textViewAppointments;

    private FragmentTimePicker fragmentTimePicker;
    private FragmentDatePicker fragmentDatePicker;


    void setDateSelection(String dateSelection) {
        this.dateSelection = dateSelection;
    }
    void setTimeSelection(String timeSelection) {
        this.timeSelection = timeSelection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanceDB();

        textViewAppointments = findViewById(R.id.textViewAppointments);
        textDescription = findViewById(R.id.textDescription);
        Button buttonOK = findViewById(R.id.btnOk);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = textDescription.getText().toString();
                Log.d("MainActivity", "Descrição capturada: " + description);
                Log.d("MainActivity", "Data selecionada: " + dateSelection);
                Log.d("MainActivity", "Hora selecionada: " + timeSelection);
                createAppointment(dateSelection, timeSelection, description);
            }
        });
    }

    public void mostraDialogoTimePicker(View v) {
        DialogFragment newFragment = new FragmentTimePicker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void mostraDialogoDatePicker(View v) {
        FragmentDatePicker newFragment = new FragmentDatePicker();

        newFragment.setDateSelectionHandler(date -> {
            setDateSelection(date);
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void createAppointment(String date, String time, String description) {
        instanceDB();

        if(date != null && time != null) {
            Appointment appointment = new Appointment(date, time, description);
            appointmentDB.addAppointment(appointment);

            Log.d("CreateAppointment", "Appointment created successfully:");
            Log.d("CreateAppointment", "Date: " + date);
            Log.d("CreateAppointment", "Time: " + time);
            Log.d("CreateAppointment", "Description: " + description);
        } else {
            Log.e("CreateAppointment", "Appointment not created. Date or time is null.");
        }
    }

    public void instanceDB() {
        if (appointmentDB == null) {
            appointmentDB = new AppointmentDB(this);
        }
    }
}