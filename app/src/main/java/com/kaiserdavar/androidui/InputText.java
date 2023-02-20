package com.kaiserdavar.androidui;

import android.content.Context;

import androidx.appcompat.widget.AppCompatEditText;

import com.kaiserdavar.androidui.style.TextStyle;
import com.kaiserdavar.androidui.style.VueStyle;

public class InputText extends com.kaiserdavar.androidui.BaseInputText<InputText, AppCompatEditText> {
    public static VueStyle defaultStyle;

    public static InputText create(Context context) {
        return new InputText(context);
    }

    public InputText(AppCompatEditText view) {
        super(view);
    }

    public InputText(Context context) {
        super(context);
    }

    @Override
    protected AppCompatEditText onGetMainView(Context context) {
        return new AppCompatEditText(context);
    }

    @Override
    protected void onInit() {
        if (defaultStyle != null) {
            t.style(defaultStyle);
        }
    }

}
