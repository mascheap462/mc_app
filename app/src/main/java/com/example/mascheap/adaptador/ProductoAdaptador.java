package com.example.mascheap.adaptador;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.BuscarDetalleFragment;
import com.example.mascheap.R;
import com.example.mascheap.helpers.DownloadImageFromInternet;
import com.example.mascheap.modelo.Carrito;
import com.example.mascheap.modelo.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallback;

public class ProductoAdaptador extends RecyclerView.Adapter<ProductoAdaptador.ViewHolder> {

    private final ArrayList<Producto> productos;
    private final Context context;

    public ProductoAdaptador(ArrayList<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductoAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.producto_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdaptador.ViewHolder holder, int position) {

        Producto producto = productos.get(position);
        holder.nombre.setText(producto.getNombre());
        holder.cantidad.setText(producto.getCantidad());
        double precio = producto.getSupermercados().stream().mapToDouble(m -> m.getPrecio()).min().orElseThrow(NoSuchElementException::new);
        holder.precio.setText(Double.toString(precio) + "€");
        holder.marca.setText(producto.getMarca());
        holder.categoria.setText(producto.getCategoria());

        new DownloadImageFromInternet(holder.url).execute(producto.getUrl());

        holder.itemView.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            BuscarDetalleFragment buscarDetalleFragment = BuscarDetalleFragment.newInstance(producto);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentoContenido, buscarDetalleFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        if (productos == null) {
            return 0;
        }
        return productos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        TextView nombre, cantidad, precio, marca, categoria;
        ImageView url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtview_nombre);
            cantidad = itemView.findViewById(R.id.txtview_cantidad);
            precio = itemView.findViewById(R.id.txtview_precio);
            marca = itemView.findViewById(R.id.txtview_marca);
            categoria = itemView.findViewById(R.id.txtview_categoria);
            url = itemView.findViewById(R.id.imageView);
        }
    }
}

