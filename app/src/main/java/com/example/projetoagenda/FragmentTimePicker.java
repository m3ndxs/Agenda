package com.example.projetoagenda;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class FragmentTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private int selectedHour;
    private int selectedMinute;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hora, minuto,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        selectedHour = hourOfDay;
        selectedMinute = minute;

        Log.d("DataHora", "Hora: " + String.valueOf(hourOfDay));
        Log.d("DataHora", "Minuto: " + String.valueOf(minute));
    }

    public String getSelectedTime() {
        return String.format("%02d:%02d", selectedHour, selectedMinute);
    }
}