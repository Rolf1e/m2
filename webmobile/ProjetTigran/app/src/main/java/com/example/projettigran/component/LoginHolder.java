package com.example.projettigran.component;

import java.util.Objects;

public class LoginHolder {

    private static LoginHolder INSTANCE;

    private boolean logged;

    private LoginHolder(boolean logged) {
        this.logged = logged;
    }

    public static LoginHolder getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new LoginHolder(false);
        }
        return INSTANCE;
    }

    public void setLogged(final boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }
}
