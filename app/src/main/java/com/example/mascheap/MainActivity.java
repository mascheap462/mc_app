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

import java.util.List;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallback;
import database.models.callbacks.FirestoreCallbackList;
import database.models.entities.User;

public class MainActivity extends AppCompatActivity {
   Button btn_buscar;

    FirebaseAuth auth;
    Button button;
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
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }
       /* private CourseRVAdapter courseRVAdapter;
        courseRV.setAdapter(courseRVAdapter);
        MasCheapFirestore.getInstance()
                .GetAll(new FirestoreCallbackList() {
                    @Override
                    public void onCallback(List users) {
                        Log.d(TAG, users.toString());
                        courseRVAdapter = new CourseRVAdapter(users, this);
                        courseRVAdapter.notifyDataSetChanged();
                    }
                }, new User()) ;
*/
        MasCheapFirestore.getInstance()
                .GetById((FirestoreCallback<User>) user ->
                        {
                            Log.d(TAG, user.email);
                            Log.d(TAG, user.username);
/*
                            userRVAdapter = new userRVAdapter(user, this);
                            userRVAdapter.notifyDataSetChanged();*/
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
        btn_buscar = findViewById(R.id.btn_buscar);
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BuscarProductoActivity.class));
            }
        });


    }
}