package com.example.projetoagenda;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.function.Consumer;

public class FragmentDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private Consumer<String> dateSelectionHandler;
    private Consumer<String> appointmentShowHandler;

    public void setDateSelectionHandler(Consumer<String> handler) {
        this.dateSelectionHandler = handler;
    }

    public void setAppointmentShowHandler(Consumer<String> handler) {
        this.appointmentShowHandler = handler;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String dateFormatted = String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);
        Log.d("FragmentDatePicker", "Data formatada: " + dateFormatted);

        if (dateSelectionHandler != null) {
            dateSelectionHandler.accept(dateFormatted);
        }

        if (appointmentShowHandler != null) {
            appointmentShowHandler.accept(dateFormatted);
        }
    }
}