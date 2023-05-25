package com.example.mascheap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.adaptador.SupermercadoAdaptador;
import com.example.mascheap.helpers.DownloadImageFromInternet;
import com.example.mascheap.modelo.Producto;
import com.example.mascheap.modelo.ProductoSupermercado;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BuscarDetalleFragment extends Fragment {
    private static Producto arg_producto;
    private View view;
    private TextView nombre, cantidad, precio, marca, categoria, descripcion;
    private ImageView url;
    private SupermercadoAdaptador supermercadoAdaptador;
    private RecyclerView supermercadoRV;

    public static BuscarDetalleFragment newInstance(Producto producto) {
        BuscarDetalleFragment fragment = new BuscarDetalleFragment();
        arg_producto = producto;
        return fragment;
    }

    private BuscarDetalleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_buscar_detalle, container, false);

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

        precio.setText(Double.toString(precioProd));
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