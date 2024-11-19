package com.diegoppg.tutorialapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ListadoComida extends AppCompatActivity {


    String comidaNames[] = {"Hamburguesa", "Ensalada", "Patatas", "Helado",
            "Pizza", "Cerveza"};

    int comidaImageIds[] = {R.drawable.hamburguesa,
            R.drawable.ensalada,
            R.drawable.patatas,
            R.drawable.helado,
            R.drawable.pizza,
            R.drawable.cerveza};

    ArrayList<HashMap<String, Object>> listaComida = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_comida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        for(int i = 0; i < comidaNames.length; i++) {
            // creating an Object of HashMap class
            HashMap<String, Object> map = new HashMap<>();

            // Data entry in HashMap
            map.put("nombreComida", comidaNames[i]);
            map.put("imagenComida", comidaImageIds[i]);

            // adding the HashMap to the ArrayList
            listaComida.add(map);
        }

        //Creamos arrays con datos
        String[] from = {"nombreComida", "imagenComida"};
        int to[] = {R.id.textViewComida, R.id.imageViewComida};

        //Creamos adaptador
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listaComida, R.layout.item_comida, from, to);

        //Recupera listView y seteamos adaptador
        ListView vistaComida = findViewById(R.id.listadoComida);
        vistaComida.setAdapter(simpleAdapter);



        //Shared preferences
        ejemploSharedGet();
        ejemploSharedAdd();

        ejemploSharedList();
    }


    private void ejemploSharedAdd(){
        SharedPreferences sharedpreferences = getSharedPreferences("comida", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("Calorias", 120);
        editor.putString("Nombre", "Ramiro");
        editor.apply();
    }

    private void ejemploSharedGet(){
        SharedPreferences sharedpreferences = getSharedPreferences("comida", Context.MODE_PRIVATE);
        int calorias = sharedpreferences.getInt("Calorias", 0);

        Log.d("comidaShared", "Calorias: "+calorias);
        Toast.makeText(this, "Calorias: "+calorias, Toast.LENGTH_SHORT).show();
    }

    private void ejemploSharedList(){
        Set<String> listNumbers = new HashSet<String>();

        for (int i = 0; i < 10; i++) {
            listNumbers.add("Number " + i);
        }

        SharedPreferences myScores = getSharedPreferences("numeros", Context.MODE_PRIVATE);
        SharedPreferences.Editor scoreEditor = myScores.edit();

        //Retrieve the values
        Set<String> set = myScores.getStringSet("numero", null);

        if (set != null) {
            for (String s : set) {
                Log.d("numeros", s);
            }
        }

        //Add data
        scoreEditor.putStringSet("numero", listNumbers);
        scoreEditor.apply();
    }

}