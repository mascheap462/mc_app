package com.example.mascheap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.adaptador.CarritoAdaptador;
import com.example.mascheap.modelo.Carrito;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallback;
import database.models.callbacks.FirestoreCallbackList;


public class CarritosFragment extends Fragment {

    private RecyclerView carritoRV;
    private Carrito carrito;// =new ArrayList<>();;
    private CarritoAdaptador carritoAdapter;
    private View view;
    FirebaseAuth auth;
    FirebaseUser user;
    // Asignacion de valores de Firebase


    public CarritosFragment() {
        // Required empty public constructor
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
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

        MasCheapFirestore.getInstance().GetById((FirestoreCallback<Carrito>) listaCompra -> {
            carrito =  listaCompra;
            if(listaCompra ==null){
                listaCompra = new Carrito(user.getEmail(), new ArrayList<>());
            }
            carritoAdapter = new CarritoAdaptador(listaCompra.getProductos(), requireContext());
            carritoRV.setAdapter(carritoAdapter);
            carritoAdapter.notifyDataSetChanged();
        }, new Carrito(),user.getEmail());

        return view;
    }
}