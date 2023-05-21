package com.example.mascheap;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AjustesFragment extends Fragment {

    // Declaracion de Firebase
    FirebaseAuth auth;
    FirebaseUser user;

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
        TextView txtCorreo = view.findViewById(R.id.txtCorreoUsuario);

        // Le asigno al capo de correo el del usuario actual
        txtCorreo.setText(user.getEmail().toString());
        return view;
    }

}