package com.kaiserdavar.androidui;

import android.content.Context;

import com.google.android.material.textfield.TextInputEditText;

public class MaterialInputEditText extends com.kaiserdavar.androidui.BaseInputText<MaterialInputEditText, TextInputEditText> {
    public static MaterialInputEditText create(Context context) {
        return new MaterialInputEditText(context);
    }

    public MaterialInputEditText(TextInputEditText view) {
        super(view);
    }

    public MaterialInputEditText(Context context) {
        super(context);
    }

    @Override
    protected TextInputEditText onGetMainView(Context context) {
        return new TextInputEditText(context);
    }

}
