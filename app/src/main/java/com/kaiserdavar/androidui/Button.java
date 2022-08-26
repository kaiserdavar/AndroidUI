package com.kaiserdavar.androidui;

import android.content.Context;

import com.kaiserdavar.androidui.style.TextStyle;

public class Button extends com.kaiserdavar.androidui.BaseText<Button, android.widget.Button> {
    public static TextStyle defaultStyle;

    public static Button create(Context context) {
        return new Button(context);
    }

    public Button(android.widget.Button buttonView) {
        super(buttonView);
    }

    public Button(Context context) {
        super(context);
    }

    @Override
    protected android.widget.Button onGetMainView(Context context) {
        return new android.widget.Button(context);
    }

    @Override
    protected void onInit() {
        if (defaultStyle != null) {
            t.style(defaultStyle);
        }
    }


}
