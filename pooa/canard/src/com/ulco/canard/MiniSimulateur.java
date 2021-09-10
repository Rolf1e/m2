package com.ulco.canard;

public class MiniSimulateur {
    public static void main(String[] args) {
        test(new Colvert());
        test(new PrototypeCanard());
    }

    private static void test(final Canard canard) {
        canard.effectuerVol();
        canard.effectuerCancan();
    }
}
