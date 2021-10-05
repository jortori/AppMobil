package com.example.appmobil.modelos;

public class Customer {
    String Cuno, Cunm, Phone;
    int Credit;

    public Customer() {
    }

    public Customer(String cuno, String cunm, String phone, int credit) {
        Cuno = cuno;
        Cunm = cunm;
        Phone = phone;
        Credit = credit;
    }

    public String getCuno() {
        return Cuno;
    }

    public void setCuno(String cuno) {
        Cuno = cuno;
    }

    public String getCunm() {
        return Cunm;
    }

    public void setCunm(String cunm) {
        Cunm = cunm;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int credit) {
        Credit = credit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Cuno='" + Cuno + '\'' +
                ", Cunm='" + Cunm + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Credit=" + Credit +
                '}';
    }
}
