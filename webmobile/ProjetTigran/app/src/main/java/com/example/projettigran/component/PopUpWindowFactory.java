package com.example.projettigran.component;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

import java.util.Objects;

public class PopUpWindowFactory {

    private static PopUpWindowFactory INSTANCE;

    public static PopUpWindowFactory create() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new PopUpWindowFactory();
        }
        return INSTANCE;
    }

    private PopUpWindowFactory() {
    }

    public PopupWindow manufactor(final LinearLayout location,
                                  final View view) {
        final PopupWindow popUp = new PopupWindow(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        popUp.showAtLocation(location, Gravity.CENTER, 10, 10);
        return popUp;
    }

}
