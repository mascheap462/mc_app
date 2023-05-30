package com.example.mascheap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mascheap.adaptador.CarritoAdaptador;
import com.example.mascheap.adaptador.OfertaAdaptador;
import com.example.mascheap.adaptador.ProductoAdaptador;
import com.example.mascheap.modelo.Carrito;
import com.example.mascheap.modelo.Oferta;
import com.example.mascheap.modelo.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallback;
import database.models.callbacks.FirestoreCallbackList;

public class OfertasFragment extends Fragment {

    private RecyclerView ofertaRV;
    private ArrayList<Oferta> ofertas;
    OfertaAdaptador ofertaAdapter;
    private Button aportaOferta;
    private View view;

    public OfertasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_ofertas, container, false);
        aportaOferta = view.findViewById(R.id.aportaOferta);

        ofertaRV = view.findViewById(R.id.recycle_oferta);
        ofertaRV.setLayoutManager(new LinearLayoutManager(requireContext()));

        MasCheapFirestore.getInstance().GetAll((FirestoreCallbackList<Oferta>) list -> {
            ofertas = (ArrayList<Oferta>) list;
            ofertaAdapter = new OfertaAdaptador(ofertas, getActivity());
            ofertaRV.setAdapter(ofertaAdapter);
            ofertaAdapter.notifyDataSetChanged();
        }, new Oferta());

        aportaOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formularioOfertaFragment formulario = new formularioOfertaFragment();
                formulario.show(getFragmentManager(), getString(R.string.aportaOferta));
                formulario.setTargetFragment(OfertasFragment.this, 1);
            }
        });

        return view;
    }
}