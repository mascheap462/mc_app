package com.example.mascheap;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

import com.example.mascheap.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    /*Button btn_agregar1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
    Button buttonCerrarSesion;
    TextView textView;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // !!!IMPORTANTE!!! Todo lo que tiene que ver con login esta desactivado para poder hacer pruebas

        //auth = FirebaseAuth.getInstance();
        buttonCerrarSesion = findViewById(R.id.cerrarSesion);
        textView = findViewById(R.id.datosUsuario);
        //user = auth.getCurrentUser();

//        if (user == null) {
//            Intent intent = new Intent(getApplicationContext(), Login.class);
//            startActivity(intent);
//            finish();
//        } else {
//            textView.setText("prueba");
//        }
//
//        MasCheapFirestore.getInstance()
//                .GetAll((FirestoreCallbackList<User>) users -> Log.d(TAG, users.toString()), new User());
//
//        MasCheapFirestore.getInstance()
//                .GetById((FirestoreCallback<User>) user ->
//                        {
//                            Log.d(TAG, user.email);
//                            Log.d(TAG, user.username);
//                        },
//                        new User(),
//                        "6b0c9e74-e717-4045-b9a3-409fd748dbba");

        // Este boton de cerrar de sesion se tiene que trasladar a otra pantalla
        buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });

        // Boton del menu de navegacion - Arreglar

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()) {

                case R.id.principal:
                    replaceFragment(new PrincipalFragment());
                    break;
                case R.id.buscar:
                    replaceFragment(new BuscarFragment());
                    break;
                case R.id.ofertas:
                    replaceFragment(new OfertasFragment());
                    break;
                case R.id.carritos:
                    replaceFragment(new CarritosFragment());
                    break;
            }

            return true;
        });


        /* La visibilidad de la barra inferior de navegación dependerá de tener una sesió iniciada
        * o no. Aunque también puede que para manejarlo lo tengamos en otra Actividad, pero la verdad
        * es que no tengo ni idea de cómo se hacen aplicaciones Android y estoy un poco harto
        * así que por ahora está así. */
        // binding.bottomNavigationView.setVisibility(View.VISIBLE);

    }


    // Metodo para cambiar de fragmento usando el menu inferior
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linearLayout, fragment);
        fragmentTransaction.commit();
    }

    // Comentario prueba stash

}