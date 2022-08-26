package com.kaiserdavar.androidui.container;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.kaiserdavar.androidui.Vue;

public class ToastContainer extends Toast {

    public static ToastContainer create(Context context) {
        return new ToastContainer(context);
    }

    public ToastContainer(Context context) {
        super(context);
        Vue vue = onContent();
        if (vue != null)
            setView(vue.createView());
    }

    protected Vue onContent() {
        return null;
    }

    public ToastContainer duration(int duration) {
        setDuration(duration);
        return this;
    }
    public ToastContainer shortDuration() {
        setDuration(LENGTH_SHORT);
        return this;
    }
    public ToastContainer longDuration() {
        setDuration(LENGTH_LONG);
        return this;
    }
    public ToastContainer gravity(int gravity) {
        setGravity(gravity, 0, 0);
        return this;
    }
    public ToastContainer gravity(int gravity, int xOffset, int yOffset) {
        setGravity(gravity, xOffset, yOffset);
        return this;
    }
    public ToastContainer text(@StringRes int resId) {
        setText(resId);
        return this;
    }
    public ToastContainer text(CharSequence text) {
        setText(text);
        return this;
    }
    public ToastContainer margin(int horizontalDp, int verticalDp) {
        setMargin(horizontalDp, verticalDp);
        return this;
    }

}
