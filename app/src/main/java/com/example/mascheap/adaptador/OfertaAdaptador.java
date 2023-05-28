package com.example.mascheap.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.BuscarDetalleFragment;
import com.example.mascheap.R;
import com.example.mascheap.helpers.DownloadImageFromInternet;
import com.example.mascheap.modelo.Carrito;
import com.example.mascheap.modelo.Oferta;
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

public class OfertaAdaptador extends RecyclerView.Adapter<OfertaAdaptador.ViewHolder>{

    private final ArrayList<Oferta> ofertas;
    private final Context context;

    public OfertaAdaptador(ArrayList<Oferta> ofertas, Context context) {
        this.ofertas = ofertas;
        this.context = context;
    }

    @NonNull
    @Override
    public OfertaAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfertaAdaptador.ViewHolder(LayoutInflater.from(context).inflate(R.layout.oferta_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfertaAdaptador.ViewHolder holder, int position) {
        Oferta oferta = ofertas.get(position);
        holder.producto.setText(oferta.getProducto());
        holder.usuario.setText(oferta.getUsuario());
        holder.precio.setText(oferta.getPrecio());

        new DownloadImageFromInternet(holder.urlProducto).execute(oferta.getUrlProducto());
        new DownloadImageFromInternet(holder.iconoSuper).execute(oferta.getIconoSuper());

        /*holder.itemView.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            BuscarDetalleFragment buscarDetalleFragment = BuscarDetalleFragment.newInstance(oferta);
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentoContenido, buscarDetalleFragment)
                    .addToBackStack(null)
                    .commit();
        });
        holder.add.setOnClickListener(v -> {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            FirebaseUser user = auth.getCurrentUser();
            MasCheapFirestore.getInstance().GetById((FirestoreCallback<Carrito>) listaCompra -> {
                if(listaCompra == null)
                {
                    listaCompra = new Carrito(user.getEmail(), new ArrayList<Producto>());
                }

                Optional<Producto> existeProducto = listaCompra.getProductos()
                        .stream()
                        .filter(f -> f.getId() .contains(producto.getId()))
                        .findFirst();

                if(!existeProducto.isPresent()){
                    listaCompra.getProductos().add(producto);
                    MasCheapFirestore.getInstance().Add(listaCompra, user.getEmail());
                }
            }, new Carrito(),user.getEmail());
        });*/

    }

    @Override
    public int getItemCount() {
        if (ofertas == null) {
            return 0;
        }
        return ofertas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        TextView producto, usuario, precio;
        ImageView urlProducto, iconoSuper;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            producto = itemView.findViewById(R.id.txtview_producto);
            usuario = itemView.findViewById(R.id.txtview_usuario);
            precio = itemView.findViewById(R.id.txtview_precioOferta);
            urlProducto = itemView.findViewById(R.id.imageViewOferta);
            iconoSuper = itemView.findViewById(R.id.iconoSuper);
        }
    }
}
