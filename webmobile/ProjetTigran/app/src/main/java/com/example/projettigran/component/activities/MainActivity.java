package com.example.projettigran.component.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.projettigran.R;
import com.example.projettigran.component.popups.LoginPopUpDialog;

public class MainActivity extends AppCompatActivity implements LoginActicity {

    private boolean logged;

    public MainActivity() {
        this.logged = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void palindrome(final View view) {
        Activities.redirect(this, PalindromeActivity.class, 1);
    }

    public void register(final View view) {
        if (!logged) {
            Toast.makeText(this, "You need to be logged in !", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Activities.redirect(this, RegisterActivity.class, 1);
        }
    }

    public void login(final View view) {
        popUpWindow();
    }

    private void popUpWindow() {
        final AppCompatDialogFragment loginPopUp = LoginPopUpDialog.create(this);
        loginPopUp.show(getSupportFragmentManager(), "");
    }

    @Override
    public void setLogged(final boolean logged) {
        this.logged = logged;
    }
}