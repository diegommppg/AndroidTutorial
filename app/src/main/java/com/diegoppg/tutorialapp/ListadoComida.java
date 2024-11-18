package com.diegoppg.tutorialapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListadoComida extends AppCompatActivity {


    // creating  a String type array (fruitNames)
    // which contains names of different fruits' images
    String comidaNames[] = {"Hamburguesa", "Ensalada", "Patatas", "Helado",
            "Pizza", "Cerveza"};

    // creating an Integer type array (fruitImageIds) which
    // contains IDs of different fruits' images
    int fruitImageIds[] = {R.drawable.banana,
            R.drawable.grape,
            R.drawable.guava,
            R.drawable.mango,
            R.drawable.orange,
            R.drawable.watermelon};


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


        for(int i = 0; i < fruitNames.length; i++) {
            // creating an Object of HashMap class
            HashMap<String, Object> map = new HashMap<>();

            // Data entry in HashMap
            map.put("fruitName", fruitNames[i]);
            map.put("fruitImage", fruitImageIds[i]);

            // adding the HashMap to the ArrayList
            list.add(map);

            //CREAR ADAPTDOR

        }

        //CREAR ADAPTADOR

        String[] from = {"fruitName", "fruitImage"};

        int to[] = {R.id.textViewFrutas, R.id.imageViewFrutas};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                list, R.layout.frutas_view, from, to);

        ListView vistaFrutas = findViewById(R.id.listaFrutas);
        vistaFrutas.setAdapter(simpleAdapter);




    }
}