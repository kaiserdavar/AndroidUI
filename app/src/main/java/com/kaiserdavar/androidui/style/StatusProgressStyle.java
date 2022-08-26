package com.kaiserdavar.androidui.style;

import androidx.annotation.DrawableRes;

public class StatusProgressStyle extends VueStyle {

    public TextStyle titleStyle;
    public TextStyle buttonStyle;
    public int status;
    public CharSequence loadingText;
    public CharSequence emptyText;
    public CharSequence errorText;
    public CharSequence retryText;
    public @DrawableRes int emptyImageRes;
    public @DrawableRes int errorImageRes;
    public int imageGravity;
}
