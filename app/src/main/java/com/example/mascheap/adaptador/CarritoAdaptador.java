package com.example.mascheap.adaptador;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.CarritosFragment;
import com.example.mascheap.R;
import com.example.mascheap.helpers.DownloadImageFromInternet;
import com.example.mascheap.modelo.Carrito;
import com.example.mascheap.modelo.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallback;

public class CarritoAdaptador extends RecyclerView.Adapter<CarritoAdaptador.ViewHolder> {

    private final ArrayList<Producto> productos;
    private final Context context;

    public CarritoAdaptador(ArrayList<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }


    @NonNull
    @Override
    public CarritoAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.carrito_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoAdaptador.ViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.nombre.setText(producto.getNombre());
        holder.cantidad.setText(producto.getCantidad());
        double precio = producto.getSupermercados().stream().mapToDouble(m -> m.getPrecio()).min().orElseThrow(NoSuchElementException::new);
        holder.precio.setText(Double.toString(precio) + "€");
        holder.marca.setText(producto.getMarca());
        holder.categoria.setText(producto.getCategoria());
        holder.descripcion.setText(producto.getDescripcion());

        new DownloadImageFromInternet(holder.url).execute(producto.getUrl());

        holder.delete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Eliminar producto");
            builder.setMessage("¿Estás seguro de que deseas eliminar este producto?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser user = auth.getCurrentUser();
                    MasCheapFirestore.getInstance().GetById((FirestoreCallback<Carrito>) listaCompra -> {
                        if (listaCompra == null) {
                            listaCompra = new Carrito(user.getEmail(), new ArrayList<Producto>());
                        }

                        Optional<Producto> existeProducto = listaCompra.getProductos()
                                .stream()
                                .filter(f -> f.getId().contains(producto.getId()))
                                .findFirst();

                        if (existeProducto.isPresent()) {
                            productos.remove(producto);
                            listaCompra.setProductos(productos);
                            MasCheapFirestore.getInstance().Update(listaCompra);
                            AppCompatActivity activity = (AppCompatActivity) v.getContext();
                            activity.getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragmentoContenido, new CarritosFragment())
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }, new Carrito(), user.getEmail());
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // No hacer nada, simplemente cerrar el diálogo
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        if (productos == null) {
            return 0;
        }
        return productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        TextView nombre, cantidad, precio, marca, categoria, descripcion;
        ImageView url;

        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtview_nombre);
            cantidad = itemView.findViewById(R.id.txtview_cantidad);
            precio = itemView.findViewById(R.id.txtview_precio);
            marca = itemView.findViewById(R.id.txtview_marca);
            categoria = itemView.findViewById(R.id.txtview_categoria);
            descripcion = itemView.findViewById(R.id.txtview_descripcion);
            url = itemView.findViewById(R.id.imageView);
            delete = itemView.findViewById(R.id.deleteProduct);
        }
    }
}
