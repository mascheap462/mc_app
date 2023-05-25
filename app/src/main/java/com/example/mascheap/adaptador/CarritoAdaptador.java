package com.example.mascheap.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.R;
import com.example.mascheap.modelo.Carrito;

import java.util.ArrayList;

public class CarritoAdaptador extends RecyclerView.Adapter<CarritoAdaptador.ViewHolder> {

    private final ArrayList<Carrito> lineas;
    private final Context context;

    public CarritoAdaptador(ArrayList<Carrito> lineas, Context context) {
        this.lineas = lineas;
        this.context = context;
    }


    @NonNull
    @Override
    public CarritoAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.carrito_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoAdaptador.ViewHolder holder, int position) {
        Carrito linea = lineas.get(position);
        holder.cantidad.setText(linea.getCantidad());
        holder.nombre.setText(linea.getNombre());
    }

    @Override
    public int getItemCount() {
        if (lineas == null) {
            return 0;
        }
        return lineas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cantidad, nombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cantidad = itemView.findViewById(R.id.textView);
            nombre = itemView.findViewById(R.id.textView2);
        }
    }
}
