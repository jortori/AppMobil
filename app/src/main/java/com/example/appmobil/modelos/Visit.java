package com.example.appmobil.modelos;

public class Visit {
    String latitud, longitud, direccion, usuario, fecha, cliente, comentario;

    public Visit() {
    }

    public Visit( String latitud, String longitud, String direccion, String usuario, String fecha, String cliente, String comentario) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.usuario = usuario;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comentario = comentario;
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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Visit{" +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", direccion='" + direccion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", fecha='" + fecha + '\'' +
                ", cliente='" + cliente + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
