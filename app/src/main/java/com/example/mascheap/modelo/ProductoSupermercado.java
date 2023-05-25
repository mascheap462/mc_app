package com.example.mascheap.modelo;

import com.google.firebase.firestore.Exclude;

public class ProductoSupermercado {
    private double precio;
    private String nombre;
    public ProductoSupermercado(double precio, String nombre) {
        this.precio = precio;
        this.nombre = nombre;
    }

    public ProductoSupermercado() {
    }

    public double getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    @Exclude
    public String getUrl() {
        return Supermercado.valueOf(nombre).getUrl();
    }
}
