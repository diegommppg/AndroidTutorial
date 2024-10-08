package com.diegoppg.tutorialapp.intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.diegoppg.tutorialapp.R;

public class IntentExplicito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intent_explicito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        String texto = data.getStringExtra("Prueba");
                        Log.d("PRUEBA", texto);
                    }
                }
        );

        /*
        ActivityResultLauncher<Intent> launcherDatosUsuario = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if(result.getResultCode() == Activity.RESULT_OK)  {
                            Bundle extras = result.getData().getExtras();
                            //actualPass = extras.getString("newPass");
                            //actualUser = extras.getString("newUser");
                        }
                    }
                });
*/
        //launcherDatosUsuario.launch(intent);

        Button btnComprar = findViewById(R.id.buttonComprar);
        EditText editTextComprar = findViewById(R.id.editTextComida);
        EditText editTextBebida = findViewById(R.id.editTextBebida);

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntentExplicito.this, IntentImplicito.class);
                //Add parameters
                i.putExtra("Prueba", "Hamburguesa");
                i.putExtra("Comida", editTextComprar.getText().toString());
                i.putExtra("Bebida", editTextBebida.getText().toString());

                //startActivity(i);

                activityResultLauncher.launch(i);
            }
        });


    }
}