package com.example.appmobil.modelos;

public class Product {
    String pano20, ds18, sos1, pres, lob, tipo;
    int qyhnd, qyor;

    public Product() {
    }

    public Product(String pano20, String ds18, String sos1, String pres, String lob, String tipo, int qyhnd, int qyor) {
        this.pano20 = pano20;
        this.ds18 = ds18;
        this.sos1 = sos1;
        this.pres = pres;
        this.lob = lob;
        this.tipo = tipo;
        this.qyhnd = qyhnd;
        this.qyor = qyor;
    }

    public String getPano20() { return pano20;    }
    public void setPano20(String pano20) { this.pano20 = pano20;    }
    public String getDs18() { return ds18;}
    public void setDs18(String ds18) { this.ds18 = ds18; }
    public String getSos1() { return sos1; }
    public void setSos1(String sos1) { this.sos1 = sos1; }
    public String getPres() { return pres; }
    public void setPres(String pres) { this.pres = pres; }

    public String getLob() {
        return lob;
    }

    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQyhnd() {
        return qyhnd;
    }

    public void setQyhnd(int qyhnd) {
        this.qyhnd = qyhnd;
    }

    public int getQyor() {
        return qyor;
    }

    public void setQyor(int qyor) {
        this.qyor = qyor;
    }

    @Override
    public String toString() {
        return "Products{" +
                "pano20='" + pano20 + '\'' +
                ", ds18='" + ds18 + '\'' +
                ", sos1='" + sos1 + '\'' +
                ", pres='" + pres + '\'' +
                ", lob='" + lob + '\'' +
                ", tipo='" + tipo + '\'' +
                ", qyhnd=" + qyhnd +
                ", qyor=" + qyor +
                '}';
    }
}

