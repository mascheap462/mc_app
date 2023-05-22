package com.example.mascheap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.adaptador.CarritoAdaptador;
import com.example.mascheap.adaptador.ProductoAdaptador;
import com.example.mascheap.modelo.Carrito;
import com.example.mascheap.modelo.Producto;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallbackList;


public class CarritosFragment extends Fragment {

    private RecyclerView carritoRV;
    private ArrayList<Carrito> lineas;// =new ArrayList<>();;
    private CarritoAdaptador carritoAdapter;
    private View view;

    public CarritosFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_carritos, container, false);

        carritoRV = view.findViewById(R.id.recycle_carrito);
        carritoRV.setLayoutManager(new LinearLayoutManager(requireContext()));

        MasCheapFirestore.getInstance().GetAll((FirestoreCallbackList<Carrito>) list -> {
            lineas = (ArrayList<Carrito>) list;
            carritoAdapter = new CarritoAdaptador(lineas, requireContext());
            carritoRV.setAdapter(carritoAdapter);
            /*ArrayList<Carrito> lineas2= (ArrayList<Carrito>) lineas
                    .stream()
                    .reduce(r->r.getCantidad().)
                    .collect(Collectors.toList());*/
            carritoAdapter.notifyDataSetChanged();
        }, new Carrito());

        return view;
    }
}