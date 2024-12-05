package com.diegoppg.tutorialapp;

import static com.diegoppg.tutorialapp.controladores.FirebasePato.*;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diegoppg.tutorialapp.controladores.FirebasePato;
import com.diegoppg.tutorialapp.controladores.FirebasePerro;
import com.diegoppg.tutorialapp.modelo.Pato;
import com.diegoppg.tutorialapp.modelo.Perro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityRecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton button = findViewById(R.id.floatingActionButtonPato);
        button.setOnClickListener(v -> {

            NuevoPatoFragment nuevoPatoFragment = new NuevoPatoFragment();
            nuevoPatoFragment.show(getSupportFragmentManager(), "nuevoPato");


        });


        listarPatos().thenAccept(patos -> {
            if (patos != null) {
                //Creamos adaptador personalizadp
                //String[] patos = {"Donald", "Lucas", "Daisy", "Hank", "Hugo"};
                AdaptadorPato adapter = new AdaptadorPato(patos);

                //Seteamos adaptador
                RecyclerView recyclerView = findViewById(R.id.recyclerPato);

                // Nuestro RecyclerView usará un linear layout manager
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(adapter);
            }
        }).exceptionally(e -> {
            Log.d("PRUEBA", "Failed to retrieve tortillas: " + e.getMessage());
            return null;
        });








    }
}