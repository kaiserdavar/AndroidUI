package com.kaiserdavar.androidui.style;

import android.content.res.ColorStateList;
import android.util.TypedValue;

import androidx.annotation.ColorRes;
import androidx.annotation.FontRes;
import androidx.annotation.StringRes;

public class TextStyle extends VueStyle {

    public CharSequence text;
    public Integer textRes;
    public Float textSize;
    public int textSizeUnit = TypedValue.COMPLEX_UNIT_SP;
    public Integer textColor;
    public Integer textColorRes;
    public ColorStateList textColorStateList;
    public Integer fontRes;
    public Integer gravity;
    public Integer lineSpacing;
    public Float letterSpacing;
    public Integer drawablePadding;

    public static TextStyle create() {
        return new TextStyle();
    }

    public TextStyle text(CharSequence text) {
        this.text = text;
        return this;
    }

    public TextStyle text(@StringRes int resId) {
        this.textRes = resId;
        return this;
    }

    public TextStyle textColor(int color) {
        this.textColor = color;
        return this;
    }

    public TextStyle textColor(ColorStateList colors) {
        this.textColorStateList = colors;
        return this;
    }

    public TextStyle textColorRes(@ColorRes int resId) {
        this.textColorRes = resId;
        return this;
    }

    public TextStyle textSize(float size) {
        this.textSizeUnit = TypedValue.COMPLEX_UNIT_SP;
        this.textSize = size;
        return this;
    }

    public TextStyle textSize(int unit, float size) {
        this.textSizeUnit = unit;
        this.textSize = size;
        return this;
    }

    public TextStyle font(@FontRes int fontResId) {
        this.fontRes = fontResId;
        return this;
    }

    public TextStyle textAlign(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public TextStyle lineSpacing(int lineSpacing) {
        this.lineSpacing = lineSpacing;
        return this;
    }
    public TextStyle letterSpacing(float letterSpacing) {
        this.letterSpacing = letterSpacing;
        return this;
    }
    public TextStyle drawablePadding(int paddingDp) {
        this.drawablePadding = paddingDp;
        return this;
    }
}
