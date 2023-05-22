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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.adapter.Adaptador;
import com.example.mascheap.model.Modelo;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallback;
import database.models.callbacks.FirestoreCallbackList;
import database.models.entities.User;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;

    RecyclerView mRecycler;
    Adaptador mAdaptador;
    FirebaseFirestore mFirestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //esta es la linea en la que tengo que modificar el layout en el que quiero iniciar sesion
        setContentView(R.layout.activity_main);

        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.myRecycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("prueba");

        FirestoreRecyclerOptions<Modelo> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Modelo>().setQuery(query, Modelo.class).build();

        mAdaptador = new Adaptador(firestoreRecyclerOptions);
        mAdaptador.notifyDataSetChanged();
        mRecycler.setAdapter(mAdaptador);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.cerrarSesion);
        textView = findViewById(R.id.datosUsuario);
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


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdaptador.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdaptador.stopListening();
    }
}