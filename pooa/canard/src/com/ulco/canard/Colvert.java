package com.ulco.canard;

import com.ulco.canard.comportement.cancanement.Cancan;
import com.ulco.canard.comportement.vol.VolerAvecDesAiles;

public class Colvert extends Canard {

    public Colvert() {
        this.comportementCancan = new Cancan();
        this.comportementVol = new VolerAvecDesAiles();
    }

    @Override
    public void afficher() {

    }
}
