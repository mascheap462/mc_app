package com.example.mascheap.modelo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import database.models.entities.BaseEntity;

public class Carrito extends BaseEntity {
    String user;
    ArrayList<Producto> productos;
    public Carrito(String user, ArrayList<Producto> productos) {
        this.user = user;
        this.productos = productos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Carrito() {
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
