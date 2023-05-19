package com.example.mascheap;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallback;
import database.models.callbacks.FirestoreCallbackList;
import database.models.entities.User;

public class MainActivity extends AppCompatActivity {
    /*Button btn_agregar1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se instancia boton
        btn_agregar1 = findViewById(R.id.btn1);
        //evento boton
        btn_agregar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CrearUsuario.class));//para indicar hacia donde queremos ir (Accion de navegar entre ventanas)
            }
        });*/

    FirebaseAuth auth;
    Button button, shareButton;
    TextView textView;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.cerrarSesion);
        textView = findViewById(R.id.datosUsuario);
        shareButton = findViewById(R.id.shareButton);
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }

        MasCheapFirestore.getInstance()
                .GetAll((FirestoreCallbackList<User>) users -> Log.d(TAG, users.toString()), new User());

        MasCheapFirestore.getInstance()
                .GetById((FirestoreCallback<User>) user ->
                        {
                            Log.d(TAG, user.email);
                            Log.d(TAG, user.username);
                        },
                        new User(),
                        "6b0c9e74-e717-4045-b9a3-409fd748dbba");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });


    //Metodo para compartir publicaciones
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String body = "¡Mira lo que he encontrado!";

                //enlace = nombre producto +  marca + localización + precio
                String enlace = "https://medac.instructure.com/";
                intent.putExtra(Intent.EXTRA_SUBJECT, body);
                intent.putExtra(Intent.EXTRA_TEXT, enlace);

                startActivity(Intent.createChooser(intent, "Compartir"));
            }
        });
    }
}