package com.example.mascheap;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mascheap.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Button btn_agregar1;

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
        });

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
        binding.bottomNavigationView.setVisibility(View.VISIBLE);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linearLayout, fragment);
        fragmentTransaction.commit();
    }

}