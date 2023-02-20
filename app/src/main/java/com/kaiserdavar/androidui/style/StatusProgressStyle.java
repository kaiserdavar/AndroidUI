package com.kaiserdavar.androidui.style;

import androidx.annotation.DrawableRes;

import com.kaiserdavar.androidui.OnObjectListener;

public class StatusProgressStyle extends VueStyle {

    public static StatusProgressStyle create() {
        return new StatusProgressStyle();
    }

    public VueStyle titleStyle;
    public VueStyle buttonStyle;
    public int status;
    public CharSequence loadingText;
    public CharSequence emptyText;
    public CharSequence errorText;
    public CharSequence retryText;
    public @DrawableRes int emptyImageRes;
    public @DrawableRes int errorImageRes;
    public int imageGravity;

    public StatusProgressStyle titleStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.titleStyle = textStyle;
        return this;
    }
    public StatusProgressStyle buttonStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.buttonStyle = textStyle;
        return this;
    }

    public StatusProgressStyle status(int status) {
        this.status = status;
        return this;
    }
    public StatusProgressStyle loadingText(CharSequence text) {
        this.loadingText = text;
        return this;
    }
    public StatusProgressStyle emptyText(CharSequence text) {
        this.emptyText = text;
        return this;
    }
    public StatusProgressStyle errorText(CharSequence text) {
        this.errorText = text;
        return this;
    }
    public StatusProgressStyle retryText(CharSequence text) {
        this.retryText = text;
        return this;
    }
    public StatusProgressStyle emptyImageRes(@DrawableRes int drawableRes) {
        this.emptyImageRes = drawableRes;
        return this;
    }
    public StatusProgressStyle errorImageRes(@DrawableRes int drawableRes) {
        this.errorImageRes = drawableRes;
        return this;
    }
    public StatusProgressStyle imageGravity(int gravity) {
        this.imageGravity = gravity;
        return this;
    }
}
