package com.diegoppg.tutorialapp.controladores;

import android.util.Log;

import androidx.annotation.NonNull;

import com.diegoppg.tutorialapp.modelo.Pato;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FirebasePato {

    public static void addPato(Pato pato){
        String TAG = "FirebasePato";

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new pato
        Map<String, Object> patoNuevo = new HashMap<>();
        patoNuevo.put("nombre", pato.getNombre());
        patoNuevo.put("edad", pato.getEdad());


        // Add a new document with a generated ID
        db.collection("patos")
                .add(patoNuevo)
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

    public static CompletableFuture<List<Pato>> listarPatos(){
        String TAG = "FirebasePato";

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //Create CompletableFuture de perros
        CompletableFuture<List<Pato>> futurePatos = new CompletableFuture<>();

        db.collection("patos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Pato> patos = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                //Crear objeto Perro
                                Pato newPato = document.toObject(Pato.class);

                                //Añadir al ArrayList
                                patos.add(newPato);
                            }

                            futurePatos.complete(patos);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            futurePatos.completeExceptionally(new Exception("Error listPerros", task.getException()));
                        }
                    }
                });

        return futurePatos;
    }


}
