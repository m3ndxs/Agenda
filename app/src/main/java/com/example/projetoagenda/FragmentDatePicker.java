package com.example.projetoagenda;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.text.format.DateFormat;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class FragmentDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(requireContext(), this, ano, mes, dia);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        selectedYear = year;
        selectedMonth = month;
        selectedDay = day;

        Log.d("DataHora", "Ano: " + String.valueOf(year));
        Log.d("DataHora", "Mês: " + String.valueOf(month + 1));
        Log.d("DataHora", "Dia: " + String.valueOf(day));
    }

    public String getSelectedDate() {
        return String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
    }
}