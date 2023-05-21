package com.example.mascheap;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mascheap.modelo.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import database.models.MasCheapFirestore;
import database.models.callbacks.FirestoreCallback;
import database.models.callbacks.FirestoreCallbackList;
import database.models.entities.User;

public class MainActivity extends AppCompatActivity {
   Button btn_buscar;

    FirebaseAuth auth;
    Button button, buttonCargar;
    TextView textView;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.cerrarSesion);
        textView = findViewById(R.id.datosUsuario);
        buttonCargar = findViewById(R.id.btn_add);
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }

        buttonCargar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ArrayList<Producto> productos = new ArrayList<Producto>(){
                    {
                        add(new Producto("Leche sin lactosa", "1 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "0.90", "Hacendado", "Lacteos","https://prod-mercadona.imgix.net/images/4c383f76349faee5a3c079c48d3dcb44.jpg?fit=crop&h=1300&w=1300"));
                        add(new Producto("Leche semidesnatada", "1 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "0.88", "Milbona", "Lacteos","https://es.openfoodfacts.org/images/products/20037321/front_es.29.full.jpg"));
                        add( new Producto("Leche entera", "1 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "1.50", "Puleva", "Lacteos","https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202205/03/00120912100052____13__600x600.jpg"));
                        add( new Producto("Macarrones vegetales", "1.5 kg", "eget duis at tellus at urna condimentum mattis pellentesque id", "1.10", "Gallo", "Pastas","https://www.pastasgallo.es/wp-content/uploads/2020/12/GALLO-Nature-Multivegetales-Helices-Remolacha-Curcuma-Espirulina-400g-1.png"));
                        add( new Producto("Macarrones ", "1 kg", "eget duis at tellus at urna condimentum mattis pellentesque id", "0.75", "Carrefour", "Pastas","https://static.carrefour.es/hd_510x_/img_pim_food/226607_00_1.jpg"));
                        add( new Producto("Cocacola", "1.5 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "1.60", "Cocacola", "Refresco","https://c-pi.niceshops.com/upload/image/product/large/default/45789_98fd2e0a.1024x1024.jpg"));
                        add( new Producto("Fanta naranja", "1.5 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "1.50", "Fanta", "Refresco","https://s4d-images.telepizza.es/Products/Original/Botella_2L__Fanta_Naranja-914.jpg"));
                        add( new Producto("Cola", "2 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "0.70", "Freeway", "Refresco","https://static-product-catalog.lidlplus.com/images/ES/ES_0002458/1_v4_big.jpg"));
                        add( new Producto("Casera naranja", "1.5 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "1.36", "Casera", "Refresco","https://yourspanishcorner.com/5482-large_default/la-casera-sabor-naranja-15-l.jpg"));
                        add( new Producto("Arroz", "1 Kg", "eget duis at tellus at urna condimentum mattis pellentesque id", "1", "Sos", "Legumbres","https://static.carrefour.es/hd_510x_/img_pim_food/002277_00_1.jpg"));
                        add( new Producto("Arroz Bomba", "1 Kg", "eget duis at tellus at urna condimentum mattis pellentesque id", "5", "Bayo", "Legumbres","https://www.comergrup.com/img/fotografias/2711004100.jpg"));
                        add( new Producto("Arroz Bomba", "1 Kg", "eget duis at tellus at urna condimentum mattis pellentesque id", "1", "Hacendado", "Legumbres","https://prod-mercadona.imgix.net/images/696d7c325a27f1bcc0b2244e7e3ecb2c.jpg?fit=crop&h=600&w=600"));
                        add( new Producto("Arroz", "1 Kg", "eget duis at tellus at urna condimentum mattis pellentesque id", "1.5", "Brillante", "Legumbres","https://www.hechoenandalucia.net/1040-large_default/arroz-vaporizado-brillante-.jpg"));

                    }
                };
                for(Producto p : productos){
                    MasCheapFirestore.getInstance().Add(p);
                }
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });
        btn_buscar = findViewById(R.id.btn_buscar);
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BuscarProductoActivity.class));
            }
        });


    }
}