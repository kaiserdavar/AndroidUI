package com.kaiserdavar.androidui.style;

import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;

import com.kaiserdavar.androidui.Toolbar;

public class ToolbarStyle extends VueStyle {

    public static ToolbarStyle create() {
        return new ToolbarStyle();
    }

    public @StyleRes Integer titleStyle;
    public @StyleRes Integer subtitleStyle;
    public @DrawableRes Integer logo;
    public @DrawableRes Integer navigationIcon;
    public @DrawableRes Integer overflowIcon;
    public Integer titleMarginTop;
    public Integer titleMarginBottom;
    public Integer titleMarginStart;
    public Integer titleMarginEnd;

    public ToolbarStyle titleStyleRes(@StyleRes int styleRes) {
        titleStyle = styleRes;
        return this;
    }
    public ToolbarStyle subtitleStyleRes(@StyleRes int styleRes) {
        subtitleStyle = styleRes;
        return this;
    }
    public ToolbarStyle logo(@DrawableRes int drawableRes) {
        logo = drawableRes;
        return this;
    }
    public ToolbarStyle navigationIcon(@DrawableRes int drawableRes) {
        navigationIcon = drawableRes;
        return this;
    }
    public ToolbarStyle overflowIcon(@DrawableRes int drawableRes) {
        overflowIcon = drawableRes;
        return this;
    }
    public ToolbarStyle titleMarginTop(int dp) {
        titleMarginTop = dp;
        return this;
    }
    public ToolbarStyle titleMarginBottom(int dp) {
        titleMarginBottom = dp;
        return this;
    }
    public ToolbarStyle titleMarginStart(int dp) {
        titleMarginStart = dp;
        return this;
    }
    public ToolbarStyle titleMarginEnd(int dp) {
        titleMarginEnd = dp;
        return this;
    }
}
