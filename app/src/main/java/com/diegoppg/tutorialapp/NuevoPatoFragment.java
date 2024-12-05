package com.diegoppg.tutorialapp;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.diegoppg.tutorialapp.controladores.FirebasePato;
import com.diegoppg.tutorialapp.modelo.Pato;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NuevoPatoFragment extends androidx.fragment.app.DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_nuevo_pato, container, false);
        EditText etNombre = v.findViewById(R.id.editTextNombre);
        EditText etEdad = v.findViewById(R.id.editTextEdad);

        v.findViewById(R.id.buttonAddPato).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Add validation
                String nombre = etNombre.getText().toString();
                String edad = etEdad.getText().toString();

                if(!nombre.isEmpty() && !edad.isEmpty()){
                    Pato pato = new Pato(nombre,
                            Integer.parseInt(edad));

                    FirebasePato.addPato(pato);

                    dismiss(); //Close dialog
                }
                else{
                    //Toast
                    //Toast.makeText(requireContext(), "Datos incompletos", Toast.LENGTH_SHORT).show();

                    //Snackbar
                    //Snackbar.make(v, "Datos incompletos", Snackbar.LENGTH_SHORT).show();


                    //Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Error");
                    builder.setMessage("Faltan datos");
                    builder.setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}