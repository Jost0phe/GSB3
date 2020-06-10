package com.example.gsbandroidapp.ui;

public class Fiche {
    String ficheId;
    String etp;
    String km;
    String nui;
    String rep;

    public Fiche(){

    }

    public Fiche(String ficheId, String etp, String km, String nui, String rep) {
        this.ficheId = ficheId;
        this.etp = etp;
        this.km = km;
        this.nui = nui;
        this.rep = rep;
    }

    public String getFicheId() {
        return ficheId;
    }

    public void setFicheId(String ficheId) {
        this.ficheId = ficheId;
    }

    public String getEtp() {
        return etp;
    }

    public void setEtp(String etp) {
        this.etp = etp;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getNui() {
        return nui;
    }

    public void setNui(String nui) {
        this.nui = nui;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }
}
