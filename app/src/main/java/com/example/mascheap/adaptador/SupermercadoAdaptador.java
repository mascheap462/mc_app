package com.example.mascheap.adaptador;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.R;
import com.example.mascheap.helpers.DownloadImageFromInternet;
import com.example.mascheap.modelo.ProductoSupermercado;

import java.util.ArrayList;



public class SupermercadoAdaptador extends RecyclerView.Adapter<SupermercadoAdaptador.ViewHolder>{
    private final ArrayList<ProductoSupermercado> supermercados;
    private final Context context;

    public SupermercadoAdaptador(ArrayList<ProductoSupermercado> supermercados, Context context) {
        this.supermercados = supermercados;
        this.context = context;
    }

    @NonNull
    @Override
    public SupermercadoAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.supermercado_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SupermercadoAdaptador.ViewHolder holder, int position) {
        ProductoSupermercado supermercado = supermercados.get(position);
        if(position == 0) {
            holder.mejorPrecio.setVisibility(View.VISIBLE);
            holder.nombre.setTypeface(null, Typeface.BOLD);
            holder.nombre.setTextColor(ContextCompat.getColor(context, R.color.mc_accent));
            holder.precio.setTypeface(null, Typeface.BOLD);
            holder.precio.setTextColor(ContextCompat.getColor(context, R.color.mc_accent));
        }
        holder.nombre.setText(supermercado.getNombre().toString());
        holder.precio.setText(Double.toString(supermercado.getPrecio()) + "€");
        new DownloadImageFromInternet(holder.icono).execute(supermercado.getUrl());
    }

    @Override
    public int getItemCount() {
        if (supermercados == null) {
            return 0;
        }
        return supermercados.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,  precio;
        ImageView icono, mejorPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtview_nombre_supermercado);
            precio = itemView.findViewById(R.id.txtview_precio_supermercado);
            icono = itemView.findViewById(R.id.imageView_icono_supermercado);
            mejorPrecio = itemView.findViewById(R.id.imageViewMejorPrecio);
        }
    }

}
