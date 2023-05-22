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
    private String cantidad;
    private String nombre;
    private String descripcion;
    private String precio;
    private String marca;
    private String categoria;
    private String url;

    public Producto(String nombre,String cantidad,String descripcion, String precio, String marca, String categoria, String url) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.marca = marca;
        this.categoria = categoria;
        this.cantidad= cantidad;
        this.nombre = nombre;
        this.url = url;
    }

    public Producto(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public String getCategoria() {
        return categoria;
    }

    public void setCatergoria(String categoria) {
        this.categoria = categoria;
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
