package com.example.mascheap.helpers;

import com.example.mascheap.modelo.Carrito;
import com.example.mascheap.modelo.Oferta;
import com.example.mascheap.modelo.Producto;
import com.example.mascheap.modelo.ProductoSupermercado;
import com.example.mascheap.modelo.Supermercado;

import java.util.ArrayList;

import database.models.MasCheapFirestore;

public class Semilla {
    public static void Execute() {

        CargarProductos();
        CargarCarritoUsuarioBarbara();
        CargarOfertas();
    }

    private static void CargarCarritoUsuarioBarbara() {
        String user = "barbara123@gmail.com";

        ArrayList<Producto> productos = new ArrayList<Producto>() {
            {
                add(new Producto("Leche sin lactosa", "1 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "Hacendado", "Lacteos", "https://prod-mercadona.imgix.net/images/4c383f76349faee5a3c079c48d3dcb44.jpg?fit=crop&h=1300&w=1300",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.20, Supermercado.MERCADONA.toString()));
                        }}));
                add(new Producto("Leche semidesnatada", "1 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "Milbona", "Lacteos", "https://es.openfoodfacts.org/images/products/20037321/front_es.29.full.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(0.90, Supermercado.LIDL.toString()));
                        }}));
                add(new Producto("Leche entera", "1 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "Puleva", "Lacteos", "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202205/03/00120912100052____13__600x600.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.50, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(1.40, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(1.45, Supermercado.MERCADONA.toString()));
                            add(new ProductoSupermercado(1.65, Supermercado.DIA.toString()));
                        }}));
            }
        };

        Carrito carrito = new Carrito("barbara123@gmail.com", productos);
        MasCheapFirestore.getInstance().GetAll(list -> {
            if (list.stream().count() > 0) {
                return;
            }
            MasCheapFirestore.getInstance().Add(carrito, user);
        }, new Carrito());
    }

    private static void CargarProductos() {
        ArrayList<Producto> productos = new ArrayList<Producto>() {
            {
                add(new Producto("Leche sin lactosa", "1 L", "eget duis at tellus at urna condimentum ", "Hacendado", "Lacteos", "https://prod-mercadona.imgix.net/images/4c383f76349faee5a3c079c48d3dcb44.jpg?fit=crop&h=1300&w=1300",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.20, Supermercado.MERCADONA.toString()));
                        }}));
                add(new Producto("Leche semidesnatada", "1 L", "eget duis at tellus at urna condimentum ", "Milbona", "Lacteos", "https://es.openfoodfacts.org/images/products/20037321/front_es.29.full.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(0.90, Supermercado.LIDL.toString()));
                        }}));
                add(new Producto("Leche entera", "1 L", "eget duis at tellus at urna condimentum mattis pellentesque id", "Puleva", "Lacteos", "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/202205/03/00120912100052____13__600x600.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.50, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(1.40, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(1.45, Supermercado.MERCADONA.toString()));
                            add(new ProductoSupermercado(1.65, Supermercado.DIA.toString()));
                        }}));
                add(new Producto("Fanta naranja", "2 L", "eget duis at tellus at urna condimentum", "Fanta", "Refresco", "https://s4d-images.telepizza.es/Products/Original/Botella_2L__Fanta_Naranja-914.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.45, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(1.49, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(1.48, Supermercado.ALCAMPO.toString()));
                            add(new ProductoSupermercado(1.50, Supermercado.DIA.toString()));
                            add(new ProductoSupermercado(1.60, Supermercado.MERCADONA.toString()));
                        }}));
                add(new Producto("Macarrones vegetales", "1.5 kg", "eget duis at tellus at urna condimentum", "Gallo", "Pastas", "https://www.pastasgallo.es/wp-content/uploads/2020/12/GALLO-Nature-Multivegetales-Helices-Remolacha-Curcuma-Espirulina-400g-1.png",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.00, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(0.99, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(0.88, Supermercado.ALCAMPO.toString()));
                            add(new ProductoSupermercado(1.00, Supermercado.DIA.toString()));
                        }}));
                add(new Producto("Macarrones", "1 kg", "eget duis at tellus at urna condimentum", "Carrefour", "Pastas", "https://static.carrefour.es/hd_510x_/img_pim_food/226607_00_1.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(2.00, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(1.99, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(1.88, Supermercado.ALCAMPO.toString()));
                            add(new ProductoSupermercado(1.60, Supermercado.DIA.toString()));
                            add(new ProductoSupermercado(1.70, Supermercado.MERCADONA.toString()));
                        }}));
                add(new Producto("Cocacola", "1.5 L", "eget duis at tellus at urna condimentum", "Cocacola", "Refresco", "https://c-pi.niceshops.com/upload/image/product/large/default/45789_98fd2e0a.1024x1024.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.00, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(0.99, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(0.88, Supermercado.ALCAMPO.toString()));
                            add(new ProductoSupermercado(1.00, Supermercado.DIA.toString()));
                        }}));

                add(new Producto("Cola", "2 L", "eget duis at tellus at urna condimentum", "Freeway", "Refresco", "https://static-product-catalog.lidlplus.com/images/ES/ES_0002458/1_v4_big.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(0.69, Supermercado.LIDL.toString()));
                        }}));
                add(new Producto("Casera naranja", "1.5 L", "eget duis at tellus at urna condimentum ", "Casera", "Refresco", "https://yourspanishcorner.com/5482-large_default/la-casera-sabor-naranja-15-l.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.42, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(1.49, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(1.48, Supermercado.ALCAMPO.toString()));
                            add(new ProductoSupermercado(1.60, Supermercado.DIA.toString()));
                            add(new ProductoSupermercado(1.50, Supermercado.MERCADONA.toString()));
                        }}));
                add(new Producto("Arroz", "1 Kg", "eget duis at tellus at urna condimentum ", "Sos", "Legumbres", "https://static.carrefour.es/hd_510x_/img_pim_food/002277_00_1.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.00, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(0.97, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(0.88, Supermercado.ALCAMPO.toString()));
                            add(new ProductoSupermercado(1.00, Supermercado.DIA.toString()));
                        }}));
                add(new Producto("Arroz Bomba", "1 Kg", "eget duis at tellus at urna condimentum ", "Bayo", "Legumbres", "https://www.comergrup.com/img/fotografias/2711004100.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.30, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(0.99, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(1.08, Supermercado.ALCAMPO.toString()));
                            add(new ProductoSupermercado(1.08, Supermercado.DIA.toString()));
                            add(new ProductoSupermercado(1.05, Supermercado.MERCADONA.toString()));
                        }}));
                add(new Producto("Arroz Bomba", "1 Kg", "eget duis at tellus at urna condimentum ", "Hacendado", "Legumbres", "https://prod-mercadona.imgix.net/images/696d7c325a27f1bcc0b2244e7e3ecb2c.jpg?fit=crop&h=600&w=600",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.00, Supermercado.MERCADONA.toString()));
                        }}));
                add(new Producto("Arroz", "1 Kg", "eget duis at tellus at urna condimentum ", "Brillante", "Legumbres", "https://www.hechoenandalucia.net/1040-large_default/arroz-vaporizado-brillante-.jpg",
                        new ArrayList<ProductoSupermercado>() {{
                            add(new ProductoSupermercado(1.10, Supermercado.CARREFOUR.toString()));
                            add(new ProductoSupermercado(1.09, Supermercado.LIDL.toString()));
                            add(new ProductoSupermercado(1.30, Supermercado.ALCAMPO.toString()));
                        }}));

            }
        };

        MasCheapFirestore.getInstance().GetAll(list -> {
            if (list.stream().count() > 0) {
                return;
            }
            for (Producto p : productos) {
                MasCheapFirestore.getInstance().Add(p);
            }
        }, new Producto());

    }

    private static void CargarOfertas() {
        ArrayList<Oferta> ofertas = new ArrayList<Oferta>() {
            {
                add(new Oferta("Arroz Bomba", "Pepe Gonzalez", "0.87 €", "https://www.comergrup.com/img/fotografias/2711004100.jpg", Supermercado.CARREFOUR.toString()));
                add(new Oferta("Cola Freeway", "Juan Cano", "0.67 €", "https://static-product-catalog.lidlplus.com/images/ES/ES_0002458/1_v4_big.jpg", Supermercado.LIDL.toString()));

            }
        };
        MasCheapFirestore.getInstance().GetAll(list -> {
            if (list.stream().count() > 0) {
                return;
            }
            for (Oferta o : ofertas) {
                MasCheapFirestore.getInstance().Add(o);
            }
        }, new Oferta());
    }
}
