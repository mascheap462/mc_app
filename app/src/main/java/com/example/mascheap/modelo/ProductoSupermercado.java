package com.example.mascheap.modelo;

import com.google.firebase.firestore.Exclude;

public class ProductoSupermercado {
    private double precio;
    private Supermercado Nombre;
    public ProductoSupermercado(double precio, Supermercado nombre) {
        this.precio = precio;
        Nombre = nombre;
    }

    public ProductoSupermercado() {
    }

    public double getPrecio() {
        return precio;
    }

    public Supermercado getNombre() {
        return Nombre;
    }

    @Exclude
    public String getUrl() {
        return Nombre.getUrl();
    }
}
