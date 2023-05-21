package com.example.mascheap.modelo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import database.models.entities.BaseEntity;
import database.models.entities.User;

public class Producto extends BaseEntity {
    private int cantidad;
    private String nombre;
    private String descripcion;
    private String precio;
    private String marca;
    private String catergoria;

    public Producto(String nombre,int cantidad,String descripcion, String precio, String marca, String catergoria) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.marca = marca;
        this.catergoria = catergoria;
        this.cantidad= cantidad;
        this.nombre = nombre;
    }

    public Producto(){}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCatergoria() {
        return catergoria;
    }

    public void setCatergoria(String catergoria) {
        this.catergoria = catergoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
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
        List<BaseEntity> productos = new ArrayList<>();
        for (QueryDocumentSnapshot document : task.getResult()) {
            Producto producto = document.toObject(Producto.class);
            producto.setId(document.getId());
            productos.add(producto);
        }
        return productos;
    }

    @Override
    public BaseEntity Document(DocumentSnapshot document) {
        return document.toObject(Producto.class);
    }
}
