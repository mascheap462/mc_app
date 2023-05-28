package com.example.mascheap;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mascheap.databinding.ActivityMainBinding;
import com.example.mascheap.helpers.Semilla;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    // DECLARACIONES

    // Firebase
    FirebaseAuth auth;
    FirebaseUser user;
    // Textos
    TextView txtCorreo;
    // Botones
    ImageButton btnRegreso;
    ImageButton btnAjustes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ASIGNACIONES

        // Layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // !!!IMPORTANTE!!! Todo lo que tiene que ver con login esta desactivado para poder hacer pruebas
        // Firebase
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        // Textos
        txtCorreo = findViewById(R.id.txtCorreoUsuario);
        // Botones
        btnRegreso = findViewById(R.id.btnRegresar);
        btnAjustes = findViewById(R.id.btnAjustes);

        // Valores iniciales y visibilidad
        replaceFragment(new PrincipalFragment());

        desactivarBoton(btnRegreso);
        activarBoton(btnAjustes);
        Semilla.Execute();
        // Comprobaci'on
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        btnRegreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilizar el mismo switch para este click y el bottomNav
                desactivarBoton(btnRegreso);
                activarBoton(btnAjustes);

                switch (binding.bottomNavigationView.getSelectedItemId()) {

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
            }
        });

        btnAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new AjustesFragment());
                desactivarBoton(btnAjustes);
                activarBoton(btnRegreso);

            }
        });

        // Boton del menu de navegacion - Arreglar

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {

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
            desactivarBoton(btnRegreso);
            activarBoton(btnAjustes);
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
        fragmentTransaction.replace(R.id.fragmentoContenido, fragment);
        fragmentTransaction.commit();
    }

    private void activarBoton(ImageButton boton) {
        boton.setClickable(true);
        boton.setColorFilter(getResources().getColor(R.color.mc_hardGrey));
    }

    private void desactivarBoton(ImageButton boton) {
        boton.setClickable(false);
        boton.setColorFilter(getResources().getColor(R.color.mc_softGrey));
    }

    // Comentario prueba stash

}