package com.kaiserdavar.androidui;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class MenuItem {

    private android.view.MenuItem menuItem;

    public static MenuItem create(android.view.MenuItem menuItem) {
        return new MenuItem(menuItem);
    }

    public MenuItem(android.view.MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public MenuItem title(CharSequence title) {
        menuItem.setTitle(title);
        return this;
    }
    public MenuItem title(@StringRes int resId) {
        menuItem.setTitle(resId);
        return this;
    }
    public MenuItem titleCondensed(CharSequence title) {
        menuItem.setTitleCondensed(title);
        return this;
    }

    public MenuItem icon(Drawable d) {
        menuItem.setIcon(d);
        return this;
    }

    public MenuItem icon(@DrawableRes int resId) {
        menuItem.setIcon(resId);
        return this;
    }

    public android.view.MenuItem getView() {
        return menuItem;
    }

}
