package com.kaiserdavar.androidui;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.google.android.material.tabs.TabLayout;

public class TabItem {

    private TabLayout.Tab mTab;

    public static TabItem create(TabLayout.Tab tab) {
        return new TabItem(tab);
    }

    public TabItem(TabLayout.Tab tab) {
        mTab = tab;
    }

    public TabItem text(CharSequence text) {
        mTab.setText(text);
        return this;
    }

    public TabItem text(@StringRes int resId) {
        mTab.setText(resId);
        return this;
    }

    public TabItem icon(Drawable d) {
        mTab.setIcon(d);
        return this;
    }

    public TabItem icon(@DrawableRes int resId) {
        mTab.setIcon(resId);
        return this;
    }

    public TabItem id(int id) {
        mTab.setId(id);
        return this;
    }

    public TabItem view(View view) {
        mTab.setCustomView(view);
        return this;
    }

    public TabItem view(int resId) {
        mTab.setId(resId);
        return this;
    }

    public TabItem tag(Object tag) {
        mTab.setTag(tag);
        return this;
    }

    public TabItem labelVisibility(int mode) {
        mTab.setTabLabelVisibility(mode);
        return this;
    }

    public TabItem select() {
        mTab.select();
        return this;
    }

    public TabLayout.Tab getView() {
        return mTab;
    }

}
