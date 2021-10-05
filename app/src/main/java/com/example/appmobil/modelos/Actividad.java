package com.example.appmobil.modelos;

public class Actividad {
    String id, Actividad;

    public Actividad() {
    }

    public Actividad(String id, String actividad) {
        this.id = id;
        Actividad = actividad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String actividad) {
        Actividad = actividad;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id='" + id + '\'' +
                ", Actividad='" + Actividad + '\'' +
                '}';
    }
}
