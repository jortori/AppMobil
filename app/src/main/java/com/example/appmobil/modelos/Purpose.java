package com.example.appmobil.modelos;

public class Purpose {
    String id_purpose, purpose;

    public Purpose(String id_purpose, String purpose) {
        this.id_purpose = id_purpose;
        this.purpose = purpose;
    }

    public String getId_purpose() {
        return id_purpose;
    }

    public void setId_purpose(String id_purpose) {
        this.id_purpose = id_purpose;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "Purpose{" +
                "id_purpose='" + id_purpose + '\'' +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
