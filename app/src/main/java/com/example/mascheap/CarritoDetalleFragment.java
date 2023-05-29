package com.example.mascheap;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mascheap.adaptador.SupermercadoAdaptador;
import com.example.mascheap.helpers.DownloadImageFromInternet;
import com.example.mascheap.modelo.Carrito;
import com.example.mascheap.modelo.Oferta;
import com.example.mascheap.modelo.Producto;
import com.example.mascheap.modelo.ProductoSupermercado;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallback;

public class CarritoDetalleFragment extends Fragment {

    private static Producto arg_producto;
    private static Oferta arg_oferta;
    private View view;
    private TextView nombre, cantidad, precio, marca, categoria, descripcion;
    private ImageView url;
    private SupermercadoAdaptador supermercadoAdaptador;
    private RecyclerView supermercadoRV;
    public static CarritoDetalleFragment newInstance(Producto producto) {
        CarritoDetalleFragment fragment = new CarritoDetalleFragment();
        arg_producto = producto;
        return fragment;
    }

    public static CarritoDetalleFragment newInstance(Oferta oferta) {
        CarritoDetalleFragment fragment = new CarritoDetalleFragment();
        arg_oferta = oferta;
        return fragment;
    }

    private CarritoDetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_carrito_detalle, container, false);

        nombre = view.findViewById(R.id.txtview_nombre);
        cantidad = view.findViewById(R.id.txtview_cantidad);
        precio = view.findViewById(R.id.txtview_precio);
        marca = view.findViewById(R.id.txtview_marca);
        categoria = view.findViewById(R.id.txtview_categoria);
        descripcion = view.findViewById(R.id.txtview_descripcion);
        url = view.findViewById(R.id.imageView);

        nombre.setText(arg_producto.getNombre());
        cantidad.setText(arg_producto.getCantidad());
        double precioProd = arg_producto.getSupermercados().stream().mapToDouble(m -> m.getPrecio())
                .min().orElseThrow(NoSuchElementException::new);

        precio.setText(Double.toString(precioProd) + "€");
        marca.setText(arg_producto.getMarca());
        categoria.setText(arg_producto.getCategoria());
        descripcion.setText(arg_producto.getDescripcion());

        new DownloadImageFromInternet(url).execute(arg_producto.getUrl());

        supermercadoRV = view.findViewById(R.id.recycle_supermercado);
        supermercadoRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        supermercadoAdaptador = new SupermercadoAdaptador((ArrayList<ProductoSupermercado>) arg_producto.getSupermercados().stream()
                .sorted(Comparator.comparingDouble(ProductoSupermercado::getPrecio))
                .collect(Collectors.toList()), requireContext());

        supermercadoRV.setAdapter(supermercadoAdaptador);
        supermercadoAdaptador.notifyDataSetChanged();

        return view;
    }
}