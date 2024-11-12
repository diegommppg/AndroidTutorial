package com.diegoppg.tutorialapp.listados;

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


        //Recuparar elementos de strings.xml

        ArrayList<Perro> perros = FirebasePerro.listarPerros();

        for (Perro perro : perros) {
            Log.d("ListadoPerros", perro.toString());
        }

        String [] arrayPerro = getResources().getStringArray(R.array.pokedex);

        //Crear adaptador (unir array con vista de cada elemento)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_perro, R.id.textViewPerro, arrayPerro);

        //Asignar adaptador: unir listView con adaptador
        ListView listViewPerros = findViewById(R.id.listViewPerros);
        listViewPerros.setAdapter(adapter);


    }
}