package com.example.projetoagenda;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class FragmentTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        mainActivity =(MainActivity) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hora, minuto,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mainActivity.setTimeSelection(hourOfDay + ":" + minute);
    }


}