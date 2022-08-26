package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.core.view.ViewCompat;

public class Divider extends BaseVue<Divider, View> {

    public static Divider create(Context context) {
        return new Divider(context).horizontal().defaultBackground();
    }

    public Divider(View view) {
        super(view);
    }

    public Divider(Context context) {
        super(context);
    }

    @Override
    protected View onGetMainView(Context context) {
        return new View(context);
    }

    public Divider horizontal() {
        return fullWidth().height(1);
    }

    public Divider vertical() {
        return fullHeight().width(1);
    }

    public Divider defaultBackground() {
        int[] attrs = { android.R.attr.listDivider };
        TypedArray ta = view.getContext().obtainStyledAttributes(attrs);
        Drawable divider = ta.getDrawable(0);
        ta.recycle();
        ViewCompat.setBackground(view, divider);
        return this;
    }

}
