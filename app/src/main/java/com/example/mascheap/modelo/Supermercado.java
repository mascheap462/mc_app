package com.example.mascheap.modelo;

public enum Supermercado {
    MERCADONA("https://www.mercadona.es/favicon.ico"),
    CORTEINGLES("https://cdn.grupoelcorteingles.es/statics/front-msh2-eci-non-food/assets/stylesheets/favicons/store/favicon.ico"),
    CARREFOUR("https://www.carrefour.es/dist/rendering/home-front/img/favicon.ico"),
    DIA("https://www.dia.es/home-front/assets/images/logo_dia.svg"),
    ALCAMPO("https://www.alcampo.es/_ui/desktop/theme-alcampo/images/favicon.ico"),
    LIDL("https://www.lidl.es/imgs/lidl-logo-svg.svg");
    private final String url;

    Supermercado(String envUrl) {
        this.url = envUrl;
    }

    public String getUrl() {
        return url;
    }

}
