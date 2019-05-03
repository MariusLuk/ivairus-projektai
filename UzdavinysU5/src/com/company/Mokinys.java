package com.company;

public class Mokinys {
    String mokinioVardas;
    int chemijosIvertis;
    int matematikosIvertis;
    int fizikosIvertis;
    double visuMokinioPazymiuVidurkis;

    public Mokinys(String mokinioVardas, int chemijosIvertis, int matematikosIvertis, int fizikosIvertis) {
        this.mokinioVardas = mokinioVardas;
        this.chemijosIvertis = chemijosIvertis;
        this.matematikosIvertis = matematikosIvertis;
        this.fizikosIvertis = fizikosIvertis;
    }

    public double grazinkMokinioPazymiuVidurki(){
        return (this.chemijosIvertis + this.matematikosIvertis + this.fizikosIvertis)/3.0;
    }
}
