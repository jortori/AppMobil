package com.example.appmobil.modelos;


public class User {
    private String  user, nombre, password;

    public User() {
    }

    public User(String user, String nombre, String password) {
        this.user = user;
        this.nombre = nombre;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}