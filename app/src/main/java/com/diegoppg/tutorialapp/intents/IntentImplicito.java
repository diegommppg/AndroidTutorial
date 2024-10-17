package com.diegoppg.tutorialapp.intents;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

        //Intent implicito Navegador
        ImageButton imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent abrir navegador
  /*              String url = "https://www.flaticon.es/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
*/
             
/*
                //Intent crear alarma

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "Alarma clase")
                        .putExtra(AlarmClock.EXTRA_HOUR, 7)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 10);
                startActivity(intent);
*/




                Intent intent2 = getIntent();

                intent2.putExtra("Prueba", "De vuelta");
                setResult(Activity.RESULT_OK,intent2); //Indicamos con RESULT_OK que todo ha salido correctamente
                finish(); //acabamos la actividad y volvemos a MainActivity




            }
        });



    }
}