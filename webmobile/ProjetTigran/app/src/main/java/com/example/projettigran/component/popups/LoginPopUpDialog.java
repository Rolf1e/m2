package com.example.projettigran.component.popups;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.projettigran.R;
import com.example.projettigran.component.LoginHolder;
import com.example.projettigran.component.activities.Activities;

public class LoginPopUpDialog extends AppCompatDialogFragment {

    private static final String PASSWORD = "perec";

    private final LoginHolder loginHolder;

    public LoginPopUpDialog() {
        this.loginHolder = LoginHolder.getInstance();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.popup_login, null);
        builder.setTitle("Login")
                .setView(view)
                .setPositiveButton("Login", (dialog, which) -> checkLogin(view));


        return builder.create();
    }

    private void checkLogin(View view) {
        final EditText passwordEditText = view.findViewById(R.id.password_editText);
        final String password = Activities.extractText(passwordEditText);
        loginHolder.setLogged(PASSWORD.equals(password));
    }

}
