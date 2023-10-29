package com.example.projetoagenda;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentDescription extends Fragment {
    private EditText editTextDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        return view;
    }

    public String getDescription() {
        return editTextDescription.getText().toString();
    }
}