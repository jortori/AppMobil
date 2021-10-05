package com.example.appmobil.modelos;

public class Profesion {
    String id, profesion;

    public Profesion() {
    }

    public Profesion(String id, String profesion) {
        this.id = id;
        this.profesion = profesion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public String toString() {
        return "Profesion{" +
                "id='" + id + '\'' +
                ", profesion='" + profesion + '\'' +
                '}';
    }
}
