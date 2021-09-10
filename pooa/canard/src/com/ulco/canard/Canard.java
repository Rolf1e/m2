package com.ulco.canard;

import com.ulco.canard.comportement.cancanement.ComportementCancan;
import com.ulco.canard.comportement.vol.ComportementVol;

public abstract class Canard {

    protected ComportementCancan comportementCancan;
    protected ComportementVol comportementVol;

    public Canard() {

    }

    public void setComportementCancan(ComportementCancan comportementCancan) {
        this.comportementCancan = comportementCancan;
    }

    public void setComportementVol(ComportementVol comportementVol) {
        this.comportementVol = comportementVol;
    }

    public abstract void afficher();

    public void effectuerVol() {
        comportementVol.voler();
    }

    public void effectuerCancan() {
        comportementCancan.cancaner();
    }

    public void nager() {
        System.out.println("Tous les canards flottent, même les leurres !");
    }

}
