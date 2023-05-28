package com.example.mascheap.modelo;

public enum Supermercado {
    MERCADONA("https://www.mercadona.es/favicon.ico"),
    CORTEINGLES("https://cdn.grupoelcorteingles.es/statics/front-msh2-eci-non-food/assets/stylesheets/favicons/store/favicon.ico"),
    CARREFOUR("https://lh3.googleusercontent.com/u/0/drive-viewer/AFGJ81paEBZUCXcAACvitmjjijYaHScfqd91FWDr45lM6PtDtKZ7HY536TayV1qYj0Cx8_bTPdH2qyIub1WKQzpuJU4c1_j1Ig=w1165-h969"),
    DIA("https://d2q79iu7y748jz.cloudfront.net/s/_squarelogo/256x256/d6c98e47b8ebac811c1d366698bc4767"),
    ALCAMPO("https://lh3.googleusercontent.com/fife/APg5EOYwN7e0V1UNpmrwdKIeQftWsdZjefExFbM2yF49nUXgTSiVQfBI6cTI18woY7wK78FDzrvhPXFGhESq4Xz3HCD02uY_kN_sm26Lq_ZwvaqPDgRT_RASsRuh2_bXaWP-yWzFwBVPvtJq7N8fY3n3BzQ6xTG1oRsgK7-QT8mfwVgcvDgFkenBlq2uLhv9FHy8cxEVEov_QqzbZ7Pzzyh5wHyhW0Gno10SeyYUTW5tyuG0BewxrF7tSYsK9QW6XAR7wEYT7hN46WTtL_8n23TW2t4DZl4ZxX3B_ud2EdwC2BavR48c3cEnhQyLpkBhVMooyIPLLx_4vX_NiLXAbUaFxOcBpSa9D8NB9JuFrXD0Nr1CBL-QnA4qkg3vJJR_bQCodvm7lAOKInNWOBpZfJnW2pPeSxJpdldqOoGlTmgDhrqYhHhd4Z8UpmuyzOK7epdC8NrCaLV8tkFPqKsyTchBogNC_Hi0vi825b11vZ2JWSRl9I8p8ayOnqEmeM2e95J-I8qQ8UlnVUJtFjDEVgGOmx9ne1PSy5Pg4OD-TrprjyGtp0Or_UJNqmHBDexxpe_OBq9662ZLMMbrIjl7__XMtaptz_6uH4-s_FthbKjKSkH9mTgCDFnCofGOG-iMFLYbsfqXjhIoUdHto_jVxT57tdhydBOtJohbv1tm5BYTO9vo3TCdJ1PmWCzABTyvMWwAeqwY07HgFZuPGN896d9Qm6ir497CIOKr2oZ_zYn2DV32O6M0atL3xhNqv4JTWTAqo8n4eKUNQeq3QWCpjyIIu-6A-N3ebY59gFkmqD1E8iESvoG5jKlGwWI8Dc17yo-OYWm0ZDmbEBCTO3MiVDHTuC9gDfowoCrcpV44WnbaLh_IG0zpywtKssLllIp_JKdprM10VWZ2BXMNjhjmC17da-r8dTDMvMKQ1b_o_vHZ4GkAPac42q4jbMYZpcbmsXXTPMx4FIkOye03=w1165-h969"),
    LIDL("https://d2q79iu7y748jz.cloudfront.net/s/_squarelogo/256x256/86b0d3232c67f7b05fb9453a14ebda09");
    private final String url;

    Supermercado(String envUrl) {
        this.url = envUrl;
    }

    public String getUrl() {
        return url;
    }

}
