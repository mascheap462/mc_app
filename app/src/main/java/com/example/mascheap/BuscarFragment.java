package com.example.mascheap;

import static com.example.mascheap.R.id.recycle_producto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.adaptador.ProductoAdaptador;
import com.example.mascheap.modelo.Producto;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallbackList;

public class BuscarFragment extends Fragment {
    private RecyclerView productoRV;
    private ArrayList<Producto> productos;// =new ArrayList<>();;
    ProductoAdaptador productoAdapter;
    SearchView buscador_view;
    private View view;

    public BuscarFragment() {
        // Required empty public constructor
    }

//    public static BuscarFragment newInstance(String param1, String param2) {
//        BuscarFragment fragment = new BuscarFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_buscar, container, false);

        productoRV = view.findViewById(R.id.recycle_producto);
        productoRV.setLayoutManager(new LinearLayoutManager(requireContext()));
        buscador_view = view.findViewById(R.id.buscador);

        MasCheapFirestore.getInstance().GetAll((FirestoreCallbackList<Producto>) list -> {
            productos = (ArrayList<Producto>) list;
            productoAdapter = new ProductoAdaptador(productos, requireContext());
            productoRV.setAdapter(productoAdapter);
            productoAdapter.notifyDataSetChanged();
        }, new Producto());

        buscador_view();

        return view;
    }

    private void buscador_view() {
        buscador_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                textSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                textSearch(s);
                return false;
            }
        });
    }

    protected void textSearch(String s) {
        ArrayList<Producto> productosFiltrados = (ArrayList<Producto>) productos
                .stream()
                .filter(f -> f.getNombre().toLowerCase(Locale.ROOT)
                        .contains(s.toLowerCase(Locale.ROOT))
                        || f.getCategoria().toLowerCase(Locale.ROOT)
                        .contains(s.toLowerCase(Locale.ROOT))
                        || f.getMarca().toLowerCase(Locale.ROOT)
                        .contains(s.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
        productoAdapter = new ProductoAdaptador(productosFiltrados, requireContext());
        productoRV.setAdapter(productoAdapter);
    }

}