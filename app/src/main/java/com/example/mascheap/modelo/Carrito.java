package com.example.mascheap.modelo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import database.models.entities.BaseEntity;

public class Carrito extends BaseEntity {
    String cantidad, nombre;

    public Carrito(String cantidad, String nombre) {
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public Carrito(){}

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

    @Override
    public List<BaseEntity> QueryDocument(Task<QuerySnapshot> task) {
        List<BaseEntity> lineas = new ArrayList<>();
        for (QueryDocumentSnapshot document : task.getResult()) {
            Carrito linea = document.toObject(Carrito.class);
            linea.setId(document.getId());
            lineas.add(linea);
        }
        return lineas;
    }

    @Override
    public BaseEntity Document(DocumentSnapshot document) {
        return document.toObject(Carrito.class);
    }
}
