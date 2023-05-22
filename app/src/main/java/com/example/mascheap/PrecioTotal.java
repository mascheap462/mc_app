package com.example.mascheap;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.adapter.Adaptador;
import com.example.mascheap.model.Modelo;

import java.util.ArrayList;
import java.util.Locale;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallbackList;
import database.models.entities.IBaseEntity;

public class PrecioTotal extends AppCompatActivity {
    private RecyclerView mProducto;
    private ArrayList<Modelo> productos;
    String cant, name;
    int total;
    Adaptador pAdapter;
    Modelo model;
    //SearchView buscador_view;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProducto = findViewById(R.id.myRecycler);
        mProducto.setLayoutManager(new LinearLayoutManager(this));
        //buscador_view = findViewById(R.id.buscador);

        MasCheapFirestore.getInstance().GetAll((FirestoreCallbackList) list -> {
            //productos = (ArrayList<Modelo>) list;
            model = new Modelo(cant, name);
            mProducto.setAdapter(pAdapter);
            pAdapter.notifyDataSetChanged();
        }, (IBaseEntity) new Modelo(cant, name));

        this.setTitle("Lista compra");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //buscador_view();

    }

    protected void sumarPrecios(String recy) {
        ArrayList<Modelo> productosF = (ArrayList<Modelo>) productos;
                /*.stream()
                .filter(f -> f.getCantidad().contains(recy.toLowerCase(Locale.ROOT)));*/
        model = new Modelo(cant, name);
        mProducto.setAdapter(pAdapter);
        for(int i = 0; i < 10; i++){
            total = Integer.parseInt(model.getCantidad());
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
