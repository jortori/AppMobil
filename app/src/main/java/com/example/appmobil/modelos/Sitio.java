package com.example.appmobil.modelos;

public class Sitio {
    String id_sitio, cuno, nombre_sitio, direccion_sitio, latitud_sitio, longitud_sitio;

    public Sitio() {
    }

    public Sitio(String id_sitio, String cuno, String nombre_sitio, String direccion_sitio, String latitud_sitio, String longitud_sitio) {
        this.id_sitio = id_sitio;
        this.cuno = cuno;
        this.nombre_sitio = nombre_sitio;
        this.direccion_sitio = direccion_sitio;
        this.latitud_sitio = latitud_sitio;
        this.longitud_sitio = longitud_sitio;
    }

    public String getId_sitio() {
        return id_sitio;
    }

    public void setId_sitio(String id_sitio) {
        this.id_sitio = id_sitio;
    }

    public String getCuno() {
        return cuno;
    }

    public void setCuno(String cuno) {
        this.cuno = cuno;
    }

    public String getNombre_sitio() {
        return nombre_sitio;
    }

    public void setNombre_sitio(String nombre_sitio) {
        this.nombre_sitio = nombre_sitio;
    }

    public String getDireccion_sitio() {
        return direccion_sitio;
    }

    public void setDireccion_sitio(String direccion_sitio) {
        this.direccion_sitio = direccion_sitio;
    }

    public String getLatitud_sitio() {
        return latitud_sitio;
    }

    public void setLatitud_sitio(String latitud_sitio) {
        this.latitud_sitio = latitud_sitio;
    }

    public String getLongitud_sitio() {
        return longitud_sitio;
    }

    public void setLongitud_sitio(String longitud_sitio) {
        this.longitud_sitio = longitud_sitio;
    }

    @Override
    public String toString() {
        return "Sitio{" +
                "id_sitio='" + id_sitio + '\'' +
                ", cuno='" + cuno + '\'' +
                ", nombre_sitio='" + nombre_sitio + '\'' +
                ", direccion_sitio='" + direccion_sitio + '\'' +
                ", latitud_sitio='" + latitud_sitio + '\'' +
                ", longitud_sitio='" + longitud_sitio + '\'' +
                '}';
    }
}
