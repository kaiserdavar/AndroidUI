package com.kaiserdavar.androidui.style;

import com.kaiserdavar.androidui.OnObjectListener;

public class ActionButtonStyle extends VueStyle {

    public static ActionButtonStyle create() {
        return new ActionButtonStyle();
    }

    public VueStyle titleStyle;
    public VueStyle progressStyle;
    public VueStyle imageStyle;

    public ActionButtonStyle titleStyle(OnObjectListener<TextStyle> listener) {
        TextStyle textStyle = new TextStyle();
        listener.onObject(textStyle);
        this.titleStyle = textStyle;
        return this;
    }
    public ActionButtonStyle progressStyle(OnObjectListener<VueStyle> listener) {
        VueStyle style = new VueStyle();
        listener.onObject(style);
        this.progressStyle = style;
        return this;
    }
    public ActionButtonStyle imageStyle(OnObjectListener<VueStyle> listener) {
        VueStyle style = new VueStyle();
        listener.onObject(style);
        this.imageStyle = style;
        return this;
    }

}
