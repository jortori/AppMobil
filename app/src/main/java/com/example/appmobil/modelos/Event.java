package com.example.appmobil.modelos;

public class Event {
    String id, Evento;

    public Event() {
    }

    public Event(String id, String evento) {
        this.id = id;
        Evento = evento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvento() {
        return Evento;
    }

    public void setEvento(String evento) {
        Evento = evento;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", Evento='" + Evento + '\'' +
                '}';
    }
}
