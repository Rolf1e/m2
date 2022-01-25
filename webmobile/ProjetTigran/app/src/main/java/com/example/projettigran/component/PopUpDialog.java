package com.example.projettigran.component;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.projettigran.R;

public class PopUpDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.popup, null);
        builder.setTitle("A propos de ...")
                .setView(view)
                .setPositiveButton("ok", (dialog, which) -> {

                });


        return builder.create();
    }


}
