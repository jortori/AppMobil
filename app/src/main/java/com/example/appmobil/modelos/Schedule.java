package com.example.appmobil.modelos;

public class Schedule {
    String usuario, cliente, actividad, fecha, comentario;

    public Schedule() {
    }

    public Schedule(String usuario, String cliente, String actividad, String fecha, String comentario) {
        this.usuario = usuario;
        this.cliente = cliente;
        this.actividad = actividad;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "usuario='" + usuario + '\'' +
                ", cliente='" + cliente + '\'' +
                ", actividad='" + actividad + '\'' +
                ", fecha='" + fecha + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
