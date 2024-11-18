package com.diegoppg.tutorialapp.listados;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.diegoppg.tutorialapp.R;
import com.diegoppg.tutorialapp.controladores.FirebasePerro;
import com.diegoppg.tutorialapp.modelo.Perro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListadoPerros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_perros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

/*
        SharedPreferences sharedpreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        int defaultValue = 5;
        int highScore = sharedpreferences.getInt("Edad", defaultValue);

        Toast.makeText(ListadoPerros.this,"Thanks: "+highScore,Toast.LENGTH_LONG).show();
        Log.d("shared", "Edad: "+highScore);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("Edad", 10);
        editor.apply();
*/


        /*
        Set<String> listOfExistingScores = new HashSet<String>();

        for (int i = 0; i < 10; i++) {
            listOfExistingScores.add("Score " + i);
        }

        SharedPreferences myScores = getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor scoreEditor = myScores.edit();

        //Retrieve the values
        Set<String> set = myScores.getStringSet("key", null);

        if (set != null) {
            for (String s : set) {
                Log.d("shared", s);
            }
        }

//Set the values

        scoreEditor.putStringSet("key", listOfExistingScores);
        scoreEditor.apply();
*/


        //Añadimos dos perros
     /*   Perro p = new Perro("Copito","Dalmata", 1, true);
        FirebasePerro.addPerro(p);
        Perro p2 = new Perro("Bigotes","Boxer", 6, false);
        FirebasePerro.addPerro(p2);
        */


        FirebasePerro.listarPerros().thenAccept(perros -> {
            if (perros != null) {

                //Crear adaptador (unir array con vista de cada elemento)
                ArrayAdapter<Perro> adapter = new ArrayAdapter<Perro>(this,
                        R.layout.item_perro, R.id.textViewPerro, perros);

                //Asignar adaptador: unir listView con adaptador
                ListView listViewPerros = findViewById(R.id.listViewPerros);
                listViewPerros.setAdapter(adapter);
            }
        }).exceptionally(e -> {
            Log.d("PRUEBA", "Failed to retrieve tortillas: " + e.getMessage());
            return null;
        });

    }
}