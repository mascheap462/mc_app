package com.example.mascheap.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.example.mascheap.model.Modelo;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Adaptador extends FirestoreRecyclerAdapter<Modelo, Adaptador.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adaptador(@NonNull FirestoreRecyclerOptions<Modelo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Modelo model) {
        holder.cantidad.setText(model.getCantidad());
        holder.nombre.setText(model.getNombre());
    }

    //Este método es para conectar la clase adaptador con el diseño
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_single, parent, false);
        return new ViewHolder(v);
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
