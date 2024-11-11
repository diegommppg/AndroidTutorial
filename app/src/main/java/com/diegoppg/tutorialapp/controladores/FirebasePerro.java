package com.diegoppg.tutorialapp.controladores;

import android.util.Log;

import androidx.annotation.NonNull;

import com.diegoppg.tutorialapp.modelo.Perro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebasePerro {

    public static void addPerro(Perro perro){
        String TAG = "FirebasePerro";

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new peroo
        Map<String, Object> perroNuevo = new HashMap<>();
        perroNuevo.put("nombre", perro.getNombre());
        perroNuevo.put("raza", perro.getRaza());
        perroNuevo.put("edad", perro.getEdad());
        perroNuevo.put("peligroso", perro.isPeligroso());

        // Add a new document with a generated ID
        db.collection("perros")
                .add(perroNuevo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public static ArrayList<Perro> listarPerros(){
        String TAG = "FirebasePerro";

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Create ArrayList de perros
        ArrayList<Perro> perros = new ArrayList<>();

        db.collection("perros")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                //Crear objeto Perro
                                Perro newPerro = document.toObject(Perro.class);

                                //Añadir al ArrayList
                                perros.add(newPerro);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        return perros;
    }



}
