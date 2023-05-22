package com.example.mascheap.model;

import com.example.mascheap.R;

import java.util.ArrayList;

public class Modelo {
    String cantidad, nombre;
    public Modelo(){}

    public Modelo(String cantidad, String nombre) {
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
