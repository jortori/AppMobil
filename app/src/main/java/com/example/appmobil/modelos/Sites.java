package com.example.appmobil.modelos;

public class Sites {
    String id_site, site;

    public Sites() {
    }

    public Sites(String id_site, String site) {
        this.id_site = id_site;
        this.site = site;
    }

    public String getId_site() {
        return id_site;
    }

    public void setId_site(String id_site) {
        this.id_site = id_site;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Sites{" +
                "id_site='" + id_site + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
