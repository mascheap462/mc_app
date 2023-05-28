package com.example.mascheap.modelo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import database.models.entities.BaseEntity;

public class Oferta extends BaseEntity {
    private String producto;
    private String usuario;
    private String precio;
    private String urlProducto;
    private String iconoSuper;

    public Oferta(String producto, String usuario, String precio, String urlProducto, String iconoSuper) {
        this.producto = producto;
        this.usuario = usuario;
        this.precio = precio;
        this.urlProducto = urlProducto;
        this.iconoSuper = iconoSuper;
    }

    public Oferta() {}

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUrlProducto() {
        return urlProducto;
    }

    public void setUrlProducto(String urlProducto) {
        this.urlProducto = urlProducto;
    }

    public String getIconoSuper() {
        return iconoSuper;
    }

    public void setIconoSuper(String iconoSuper) {
        this.iconoSuper = iconoSuper;
    }

    @Override
    public List<BaseEntity> QueryDocument(Task<QuerySnapshot> task) {
        List<BaseEntity> ofertas = new ArrayList<>();
        for (QueryDocumentSnapshot document : task.getResult()) {
            Oferta oferta = document.toObject(Oferta.class);
            oferta.setId(document.getId());
            ofertas.add(oferta);
        }
        return ofertas;
    }

    @Override
    public BaseEntity Document(DocumentSnapshot document) {
        return document.toObject(Oferta.class);
    }
}
