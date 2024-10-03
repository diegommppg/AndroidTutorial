package com.diegoppg.tutorialapp.intents;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.diegoppg.tutorialapp.R;

public class IntentImplicito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intent_implicito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView textViewComida = findViewById(R.id.textViewEat);
        TextView textViewBebida = findViewById(R.id.textViewDrink);

        //Recover data
        Bundle extras = getIntent().getExtras();
        String textoPrueba = extras.getString("Prueba");
        String comida = extras.getString("Comida");
        String bebida = extras.getString("Bebida");

        textViewComida.setText(comida);
        textViewBebida.setText(bebida);
        Log.d("PRUEBA", textoPrueba);






    }
}