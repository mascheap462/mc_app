package com.example.mascheap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class formularioOfertaFragment extends Fragment {
    private EditText producto, precio, supermercado;

    /*public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_formulario_oferta, null);

        builder.setView(view)
                .setTitle("Aportar Oferta")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        producto = view.findViewById(R.id.nombreOferta);
        precio = view.findViewById(R.id.precioOferta);
        supermercado = view.findViewById(R.id.supermercadoOferta);

        return builder.create();
    }*/

}