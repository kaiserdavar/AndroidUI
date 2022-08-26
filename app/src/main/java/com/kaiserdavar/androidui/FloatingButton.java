package com.kaiserdavar.androidui;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FloatingButton extends com.kaiserdavar.androidui.BaseVue<FloatingButton, FloatingActionButton> {

    public static FloatingButton create(Context context) {
        return new FloatingButton(context);
    }

    public FloatingButton(FloatingActionButton view) {
        super(view);
    }

    public FloatingButton(Context context) {
        super(context);
    }

    @Override
    protected FloatingActionButton onGetMainView(Context context) {
        return new FloatingActionButton(context);
    }

    public FloatingButton image(@DrawableRes int resId) {
        view.setImageResource(resId);
        return this;
    }
    public FloatingButton image(Drawable d) {
        view.setImageDrawable(d);
        return this;
    }

}
