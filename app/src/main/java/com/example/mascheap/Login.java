package com.example.mascheap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText editTextUser, editTextPassword;
    CheckBox mostrarPassword;
    Button buttonLogin;
    FirebaseAuth mAuth;

    ProgressBar barraProgreso;

    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editTextUser = findViewById(R.id.usuario);
        editTextPassword = findViewById(R.id.password);
        mostrarPassword = findViewById(R.id.checkbox);
        buttonLogin = findViewById(R.id.btn_login);
        barraProgreso = findViewById(R.id.barraProgreso);
        textView = findViewById(R.id.crearCuenta);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);
                finish();
            }
        });

        //Mostrar Contraseña
        mostrarPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barraProgreso.setVisibility(View.VISIBLE);

                String email, password;

                email = String.valueOf(editTextUser.getText());
                password = String.valueOf(editTextPassword.getText());

                if (editTextUser.getText().toString().trim().length() < 8) {
                    editTextUser.setError("La longitud del email debe tener al menos 8 caracteres");
                }

                if (editTextPassword.getText().toString().trim().length() < 8) {
                    //layoutPassword.setPasswordVisibilityToggleEnabled(false);
                    editTextPassword.setError("La contraseña debe tener al menos 8 caracteres");

                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Debe introducir su correo electrónico", Toast.LENGTH_LONG).show();
                    barraProgreso.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Debe introducir una contraseña", Toast.LENGTH_LONG).show();
                    barraProgreso.setVisibility(View.GONE);
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                barraProgreso.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Ha iniciado sesión de forma exitosa",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Fallo de autenticación.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });


    }

}