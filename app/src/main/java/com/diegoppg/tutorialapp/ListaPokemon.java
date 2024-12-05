package com.diegoppg.tutorialapp;

import android.os.Bundle;
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

import com.diegoppg.tutorialapp.controladores.FirebasePerro;
import com.diegoppg.tutorialapp.modelo.Perro;

public class ListaPokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_pokemon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Recuparar elementos de strings.xml
        String [] arrayPokemon = getResources().getStringArray(R.array.pokedex);

        //Crear adaptador (unir array con vista de cada elemento)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_pokemon, R.id.textViewPokemon, arrayPokemon);

        //Asignar adaptador: unir listView con adaptador
        ListView listViewPokemon = findViewById(R.id.listViewPokemon);
        listViewPokemon.setAdapter(adapter);

        //Añadir escuchador a listView
        listViewPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {



                String value=adapter.getItem(position);
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
            }
        });

    }
}