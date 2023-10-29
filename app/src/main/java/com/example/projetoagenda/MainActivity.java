package com.example.projetoagenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private FragmentDescription fragmentDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentDescription = new FragmentDescription();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, fragmentDescription)
                .commit();
    }
    public String getDescriptionFromFragment() {
        return fragmentDescription.getDescription();
    }
    public void mostraDialogoTimePicker(View v) {
        DialogFragment newFragment = new FragmentTimePicker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    public void mostraDialogoDatePicker(View v) {
        DialogFragment newFragment = new FragmentDatePicker();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}