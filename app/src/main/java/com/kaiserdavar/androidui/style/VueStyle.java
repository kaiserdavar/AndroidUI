package com.kaiserdavar.androidui.style;

import android.content.Context;
import android.util.TypedValue;

import androidx.annotation.DrawableRes;

import com.kaiserdavar.androidui.OnBackgroundListener;
import com.kaiserdavar.androidui.util.Shaper;

public class VueStyle {

    public Integer[] margin;
    public Integer[] padding;
    public Integer backgroundRes;
    public Shaper background;

    public VueStyle background(@DrawableRes int resource) {
        backgroundRes = resource;
        return this;
    }

    public VueStyle background(OnBackgroundListener listener) {
        background = new Shaper();
        listener.onBackground(background);
        return this;
    }

    public VueStyle background(int color, int cornerDp) {
        background = new Shaper()
                .backgroundColor(color)
                .corner(cornerDp);
        return this;
    }

    public VueStyle rippleBackground(int backColor, int rippleColor, int cornerDp) {
        background = new Shaper()
                .backgroundColor(backColor)
                .rippleColor(rippleColor)
                .corner(cornerDp);
        return this;
    }

    public VueStyle selectableItemBackground(Context context) {
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                outValue, true);
        backgroundRes = outValue.resourceId;
        return this;
    }

    public VueStyle noBackground() {
        backgroundRes = 0;
        return this;
    }

}
