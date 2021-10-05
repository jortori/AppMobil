package com.example.appmobil.modelos;

public class Contact {
    String cuno, profesion, firstname, secondname, lastname1, lastname2, cargo, phone, cel, email;

    public Contact() {
    }

    public Contact(String cuno, String profesion, String firstname, String secondname, String lastname1, String lastname2, String cargo, String phone, String cel, String email) {
        this.cuno = cuno;
        this.profesion = profesion;
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname1 = lastname1;
        this.lastname2 = lastname2;
        this.cargo = cargo;
        this.phone = phone;
        this.cel = cel;
        this.email = email;
    }

    public String getCuno() {
        return cuno;
    }

    public void setCuno(String cuno) {
        this.cuno = cuno;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getLastname1() {
        return lastname1;
    }

    public void setLastname1(String lastname1) {
        this.lastname1 = lastname1;
    }

    public String getLastname2() {
        return lastname2;
    }

    public void setLastname2(String lastname2) {
        this.lastname2 = lastname2;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "cuno='" + cuno + '\'' +
                ", profesion='" + profesion + '\'' +
                ", firstname='" + firstname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", lastname1='" + lastname1 + '\'' +
                ", lastname2='" + lastname2 + '\'' +
                ", cargo='" + cargo + '\'' +
                ", phone='" + phone + '\'' +
                ", cel='" + cel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
