package com.example.appmobil.modelos;

public class Tracking {
    String latitud, longitud, direccion, usuario, fecha;

    public Tracking() {
    }

    public Tracking(String latitud, String longitud, String direccion, String usuario, String fecha) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", direccion='" + direccion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
