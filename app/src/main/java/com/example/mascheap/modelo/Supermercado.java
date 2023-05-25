package com.example.mascheap.modelo;

public enum Supermercado {
    MERCADONA("https://www.mercadona.es/favicon.ico"),
    CORTEINGLES("https://cdn.grupoelcorteingles.es/statics/front-msh2-eci-non-food/assets/stylesheets/favicons/store/favicon.ico"),
    CARREFOUR("https://www.carrefour.es/dist/rendering/home-front/img/favicon.ico"),
    DIA("https://d2q79iu7y748jz.cloudfront.net/s/_squarelogo/256x256/d6c98e47b8ebac811c1d366698bc4767"),
    ALCAMPO("https://www.alcampo.es/_ui/desktop/theme-alcampo/images/favicon.ico"),
    LIDL("https://d2q79iu7y748jz.cloudfront.net/s/_squarelogo/256x256/86b0d3232c67f7b05fb9453a14ebda09");
    private final String url;

    Supermercado(String envUrl) {
        this.url = envUrl;
    }

    public String getUrl() {
        return url;
    }

}
