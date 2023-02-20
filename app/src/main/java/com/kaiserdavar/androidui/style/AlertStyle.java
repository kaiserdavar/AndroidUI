package com.kaiserdavar.androidui.style;

import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;

import com.kaiserdavar.androidui.OnObjectListener;
import com.kaiserdavar.androidui.Text;

public class AlertStyle extends VueStyle {

    public static AlertStyle create() {
        return new AlertStyle();
    }

    public VueStyle titleStyle;
    public VueStyle messageStyle;
    public VueStyle inputTextStyle;
    public VueStyle listItemStyle;
    public @DrawableRes Integer icon;
    public VueStyle positiveButtonStyle;
    public VueStyle positiveDestructiveButtonStyle;
    public VueStyle negativeButtonStyle;
    public VueStyle customButtonStyle;

    public AlertStyle titleStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.titleStyle = textStyle;
        return this;
    }
    public AlertStyle messageStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.messageStyle = textStyle;
        return this;
    }
    public AlertStyle inputTextStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.inputTextStyle = textStyle;
        return this;
    }
    public AlertStyle listItemStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.listItemStyle = textStyle;
        return this;
    }

    public AlertStyle positiveButtonStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.positiveButtonStyle = textStyle;
        return this;
    }
    public AlertStyle positiveDestructiveButtonStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.positiveDestructiveButtonStyle = textStyle;
        return this;
    }
    public AlertStyle negativeButtonStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.negativeButtonStyle = textStyle;
        return this;
    }
    public AlertStyle customButtonStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.customButtonStyle = textStyle;
        return this;
    }

}
