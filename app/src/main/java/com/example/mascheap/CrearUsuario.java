package com.example.mascheap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//import database.models.Product;


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
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true); //funcionalidad de hacer click y retroceder
        //mFirebase = FirebaseFirestore.getInstance();//Apuntamos a la BD

       /* Product p = new Product("Nuevo");
        p.Add();

        User user = new User( "test","test");
        user.Add();
        user.username = "nuevo nombre";
        user.Update();
       
            user.GetAll();
        for (User user1 : user.users) {
            
        }*/

        //User user1 = user.findByID("1");

    }

    private void postUsuario(String name, String surname, String age) {
        Map<String, Object > map = new HashMap<>();
        map.put("nombre", nombre);//le pasamos como se va a llamar los campos en la base datos
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

    //metodo para retroceder

    @Override
    public boolean onSupportNavigateUp() {
      onBackPressed();
      return false;
    }
}