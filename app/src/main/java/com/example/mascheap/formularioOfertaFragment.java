package com.example.mascheap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class formularioOfertaFragment extends DialogFragment {
    private EditText producto, precio, supermercado;

    public Dialog onCreateDialog(Bundle saveInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_formulario_oferta, null);

        builder.setView(view)
                .setTitle("Aportar Oferta")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Accion cancelada", Toast.LENGTH_LONG).show();
                    }
                })
                .setPositiveButton("Ok", null);  // Set null para evitar el cierre automático

        producto = view.findViewById(R.id.nombreOferta);
        precio = view.findViewById(R.id.precioOferta);
        supermercado = view.findViewById(R.id.supermercadoOferta);

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nombreProducto = producto.getText().toString();
                        String precioOferta = precio.getText().toString();
                        String nombreSupermercado = supermercado.getText().toString();
                        if (nombreProducto.isEmpty() || precioOferta.isEmpty() || nombreSupermercado.isEmpty()) {
                            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_LONG).show();
                        } else {
                            // Realizar la acción deseada, ya que los campos no están vacíos
                            Toast.makeText(getActivity(), "¡Gracias por su aportación!", Toast.LENGTH_LONG).show();
                            dialog.dismiss();  // Cerrar el diálogo manualmente después de la acción deseada
                        }
                    }
                });
            }
        });

        return dialog;
    }
}