package com.kaiserdavar.androidui.style;

import android.content.Context;
import android.util.TypedValue;

import androidx.annotation.DrawableRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.kaiserdavar.androidui.OnBackgroundListener;
import com.kaiserdavar.androidui.Vue;
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

    public VueStyle margin(int dp) {
        checkMargin();
        margin[0] = dp;
        margin[1] = dp;
        margin[2] = dp;
        margin[3] = dp;
        return this;
    }
    public VueStyle marginHorizontal(int dp) {
        checkMargin();
        margin[0] = dp;
        margin[2] = dp;
        return this;
    }
    public VueStyle marginVertical(int dp) {
        checkMargin();
        margin[1] = dp;
        margin[3] = dp;
        return this;
    }
    public VueStyle marginTop(int dp) {
        checkMargin();
        margin[1] = dp;
        return this;
    }
    public VueStyle marginBottom(int dp) {
        checkMargin();
        margin[3] = dp;
        return this;
    }
    public VueStyle marginStart(int dp) {
        checkMargin();
        margin[0] = dp;
        return this;
    }
    public VueStyle marginEnd(int dp) {
        checkMargin();
        margin[2] = dp;
        return this;
    }


    public VueStyle padding(int dp) {
        checkPadding();
        padding[0] = dp;
        padding[1] = dp;
        padding[2] = dp;
        padding[3] = dp;
        return this;
    }
    public VueStyle paddingHorizontal(int dp) {
        checkPadding();
        padding[0] = dp;
        padding[2] = dp;
        return this;
    }
    public VueStyle paddingVertical(int dp) {
        checkPadding();
        padding[1] = dp;
        padding[3] = dp;
        return this;
    }
    public VueStyle paddingTop(int dp) {
        checkPadding();
        padding[1] = dp;
        return this;
    }
    public VueStyle paddingBottom(int dp) {
        checkPadding();
        padding[3] = dp;
        return this;
    }
    public VueStyle paddingStart(int dp) {
        checkPadding();
        padding[0] = dp;
        return this;
    }
    public VueStyle paddingEnd(int dp) {
        checkPadding();
        padding[2] = dp;
        return this;
    }

    private void checkMargin() {
        if (margin == null)
            margin = new Integer[4];
    }
    private void checkPadding() {
        if (padding == null)
            padding = new Integer[4];
    }

}
