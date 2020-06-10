package com.example.gsbandroidapp.ui;

public class Fiche {
    String ficheId;
    String valeur1;
    String valeur2;

    public Fiche(){

    }

    public Fiche(String valeur1, String valeur2) {
        this.valeur1 = valeur1;
        this.valeur2 = valeur2;
    }


    public String getValeur1() {
        return valeur1;
    }

    public void setValeur1(String valeur1) {
        this.valeur1 = valeur1;
    }

    public String getValeur2() {
        return valeur2;
    }

    public void setValeur2(String valeur2) {
        this.valeur2 = valeur2;
    }
}
