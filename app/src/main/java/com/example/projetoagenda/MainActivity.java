package com.example.projetoagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private String dateSelection;
    private String timeSelection;
    private EditText textDescription;
    private AppointmentDB appointmentDB;
    private TextView textViewAppointments;

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

        Button buttonToday = findViewById(R.id.buttonToday);
        Button buttonOtherDate = findViewById(R.id.buttonOtherDate);
        Button buttonDeleteAppointment = findViewById(R.id.buttonDelete);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = textDescription.getText().toString();

                createAppointment(dateSelection, timeSelection, description);
            }
        });

        buttonToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppointment(getDateToday());
            }
        });

        buttonOtherDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        buttonDeleteAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAppointments();
            }
        });
    }

    public void showDialogTimePicker(View v) {
        DialogFragment newFragment = new FragmentTimePicker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDialogDatePicker(View v) {
        FragmentDatePicker newFragment = new FragmentDatePicker();

        newFragment.setDateSelectionHandler(date -> {
            setDateSelection(date);
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        dateSelection = dateFormat.format(selectedDate.getTime());

                        showAppointment(dateSelection);
                    }
                },
                year, month, dayOfMonth);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


    private void createAppointment(String date, String time, String description) {

        if(date != null && time != null) {
            Appointment appointment = new Appointment(date, time, description);
            appointmentDB.addAppointment(appointment);
        } else {
            Log.e("CreateAppointment", "Appointment not created. Date or time is null.");
        }
    }

    void showAppointment(String date) {
        String clauseWhere = AppointmentDBSchema.AppointmentTbl.Cols.DATE + " = ?";
        String[] argsWhere = new String[]{date};
        String appointment = appointmentDB.listAppointment(clauseWhere, argsWhere);

        if (!appointment.isEmpty()) {
            textViewAppointments.setText(appointment);
        } else {
            textViewAppointments.setText("Sem compromisso marcado.");
        }
    }

    private String getDateToday(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateToday = dateFormat.format(calendar.getTime());

        return dateToday;
    }

    private void deleteAppointments(){
        if(appointmentDB != null){
            appointmentDB.deleteAppointments();
            showAppointment(getDateToday());
        }
    }
    public void instanceDB() {
        if (appointmentDB == null) {
            appointmentDB = new AppointmentDB(this);
        }
    }
}