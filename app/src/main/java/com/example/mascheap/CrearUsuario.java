package com.example.mascheap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CrearUsuario extends AppCompatActivity {
    Button btn_agregar;
    EditText nombre, apellidos, edad;
    private FirebaseFirestore mFirebase;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        this.setTitle("Crear usuario");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    private void postUsuario(String name, String surname, String age) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre); //le pasamos como se va a llamar los campos en la base datos
        map.put("apellidos", apellidos);
        map.put("edad", edad);

        mFirebase.collection("Usuario").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creación correcta", Toast.LENGTH_SHORT);
                finish();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al insertar", Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}