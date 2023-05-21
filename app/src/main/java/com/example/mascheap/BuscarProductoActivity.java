package com.example.mascheap;

import static com.example.mascheap.R.id.recycle_producto;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.adaptador.ProductoAdaptador;
import com.example.mascheap.modelo.Producto;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallbackList;

public class BuscarProductoActivity extends AppCompatActivity {
    private RecyclerView productoRV;
    private ArrayList<Producto> productos;// =new ArrayList<>();;
    ProductoAdaptador productoAdapter;
    SearchView buscador_view;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);
        productoRV = findViewById(recycle_producto);
        productoRV.setLayoutManager(new LinearLayoutManager(this));
        buscador_view = findViewById(R.id.buscador);

        MasCheapFirestore.getInstance().GetAll((FirestoreCallbackList<Producto>) list -> {
            productos = (ArrayList<Producto>) list;
            productoAdapter = new ProductoAdaptador(productos, this);
            productoRV.setAdapter(productoAdapter);
            productoAdapter.notifyDataSetChanged();
        }, new Producto());

        this.setTitle("Buscar producto");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        buscador_view();

    }

    private void buscador_view() {
        buscador_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                textSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                textSearch(s);
                return false;
            }
        });
    }

    protected void textSearch(String s) {
        ArrayList<Producto> productosFiltrados = (ArrayList<Producto>) productos
                .stream()
                .filter(f -> f.getNombre().toLowerCase(Locale.ROOT)
                        .contains(s.toLowerCase(Locale.ROOT)) )
                .collect(Collectors.toList());
        productoAdapter = new ProductoAdaptador(productosFiltrados, this);
        productoRV.setAdapter(productoAdapter);
       // productoAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}