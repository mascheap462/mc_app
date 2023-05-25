package com.example.mascheap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AjustesFragment extends Fragment {

    // Declaracion de Firebase
    FirebaseAuth auth;
    FirebaseUser user;

    TextView txtCorreo;
    Button btnCerrarSesion;

    // Metodo newInstace
//    public static AjustesFragment newInstance(String param1, String param2) {
//        AjustesFragment fragment = new AjustesFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    // Constructor vacio
    public AjustesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Asignacion de valores de Firebase
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Obtengo la vista padre
        View view = inflater.inflate(R.layout.fragment_ajustes, container, false);

        txtCorreo = view.findViewById(R.id.txtCorreoUsuario);
        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(requireContext(), Login.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        // Le asigno al capo de correo el del usuario actual
        txtCorreo.setText(user.getEmail());
        return view;

    }


}