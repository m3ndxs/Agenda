package com.example.projetoagenda;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentDescription extends Fragment {

    private EditText editTextDescricao;

    public FragmentDescription() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        editTextDescricao = view.findViewById(R.id.textDescription);
        Button buttonSalvar = view.findViewById(R.id.buttonSave);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCompromisso();
            }
        });

        return view;
    }

    public void salvarCompromisso() {
        String descricao = editTextDescricao.getText().toString();
        if (!descricao.isEmpty()) {
            Log.d("Descrição", "Compromisso: " + descricao);
        } else {
            Log.d("Descrição", "A descrição não pode ser vazia");
        }
    }

    public String getDescription() {
        return editTextDescricao.getText().toString();
    }
}