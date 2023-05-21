package com.example.mascheap.adaptador;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mascheap.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
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
        holder.cantidad.setText(producto.getCantidad());
        holder.precio.setText(producto.getPrecio());
        holder.marca.setText(producto.getMarca());
        holder.categoria.setText(producto.getCategoria());
        holder.descripcion.setText(producto.getDescripcion());
        new DownloadImageFromInternet(holder.url).execute(producto.getUrl());
    }

    @Override
    public int getItemCount() {
        if(productos == null){
            return 0;
        }
        return productos.size();
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView=imageView;
          //  Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        TextView nombre,cantidad, precio, marca, categoria, descripcion;
        ImageView url;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txtview_nombre);
            cantidad = itemView.findViewById(R.id.txtview_cantidad);
            precio = itemView.findViewById(R.id.txtview_precio);
            marca = itemView.findViewById(R.id.txtview_marca);
            categoria = itemView.findViewById(R.id.txtview_categoria);
            descripcion = itemView.findViewById(R.id.txtview_descripcion);
            url = itemView.findViewById(R.id.imageView);
        }
    }
}

