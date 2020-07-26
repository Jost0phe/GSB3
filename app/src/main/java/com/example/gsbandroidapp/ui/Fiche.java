package com.example.gsbandroidapp.ui;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Fiche {
    String ficheId;
    String ficheUserEmail;
    String etp;
    String km;
    String nui;
    String rep;

    public Fiche(){

    }

    public Fiche(String ficheId, String etp, String km, String nui, String rep) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        this.ficheUserEmail = user.getEmail();
        this.ficheId = ficheId;
        this.etp = etp;
        this.km = km;
        this.nui = nui;
        this.rep = rep;
    }
    public String getFicheUserEmail() { return ficheUserEmail; }

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
