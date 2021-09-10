package com.ulco.canard;

import com.ulco.canard.comportement.cancanement.Cancan;
import com.ulco.canard.comportement.vol.NePasVoler;

public class PrototypeCanard extends Canard {

    public PrototypeCanard() {
        this.comportementVol = new NePasVoler();
        this.comportementCancan = new Cancan();
    }

    @Override
    public void afficher() {
        System.out.println("Je suis un prototype de canard");
    }
}
