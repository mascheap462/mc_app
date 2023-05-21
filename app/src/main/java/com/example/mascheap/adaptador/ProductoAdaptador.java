package com.example.mascheap.adaptador;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.mascheap.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.content.Context;

import com.example.mascheap.modelo.Producto;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallbackList;

public class ProductoAdaptador extends RecyclerView.Adapter <ProductoAdaptador.ViewHolder>{

    private ArrayList<Producto> productos;
    private Context context;

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
        holder.cantidad.setText(Integer.toString(producto.getCantidad()));
        //holder.cantidad.setText("1");
    }

    @Override
    public int getItemCount() {
        if(productos == null){
            return 0;
        }
        return productos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        TextView nombre,cantidad;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtview_nombre);
            cantidad = itemView.findViewById(R.id.txtview_cantidad);
        }
    }
}

