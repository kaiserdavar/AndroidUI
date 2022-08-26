package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.ColorStateList;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;

public class TabSelector extends com.kaiserdavar.androidui.BaseVue<TabSelector, TabLayout> implements
        TabLayout.OnTabSelectedListener {

    private OnTabListener onSelectListener;
    private OnTabListener onUnselectListener;
    private OnTabListener onReselectListener;

    public TabSelector(TabLayout view) {
        super(view);
    }

    public TabSelector(Context context) {
        super(context);
    }

    public static TabSelector create(Context context) {
        return new TabSelector(context);
    }

    @Override
    protected TabLayout onGetMainView(Context context) {
        TabLayout v = new TabLayout(context);
        v.addOnTabSelectedListener(this);
        return v;
    }

    public TabSelector tab(CharSequence text) {
        com.kaiserdavar.androidui.TabItem tab = com.kaiserdavar.androidui.TabItem.create(view.newTab()).text(text);
        view.addTab(tab.getView());
        return this;
    }

    public TabSelector tab(@StringRes int textRes) {
        com.kaiserdavar.androidui.TabItem tab = com.kaiserdavar.androidui.TabItem.create(view.newTab()).text(textRes);
        view.addTab(tab.getView());
        return this;
    }

    public TabSelector tab(@StringRes int textRes, @DrawableRes int icon) {
        com.kaiserdavar.androidui.TabItem tab = com.kaiserdavar.androidui.TabItem.create(view.newTab()).text(textRes).icon(icon);
        view.addTab(tab.getView());
        return this;
    }

    public TabSelector tab(com.kaiserdavar.androidui.OnTabItemListener listener) {
        com.kaiserdavar.androidui.TabItem tab = com.kaiserdavar.androidui.TabItem.create(view.newTab());
        listener.onTab(tab);
        view.addTab(tab.getView());
        return this;
    }

    public TabSelector tab(int position, boolean selected, com.kaiserdavar.androidui.OnTabItemListener listener) {
        com.kaiserdavar.androidui.TabItem tab = com.kaiserdavar.androidui.TabItem.create(view.newTab());
        listener.onTab(tab);
        view.addTab(tab.getView(), position, selected);
        return this;
    }

    public TabSelector gravity(int gravity) {
        view.setTabGravity(gravity);
        return this;
    }
    public TabSelector mode(int mode) {
        view.setTabMode(mode);
        return this;
    }
    public TabSelector textColor(ColorStateList colorStateList) {
        view.setTabTextColors(colorStateList);
        return this;
    }
    public TabSelector textColor(int normalColor, int selectedColor) {
        view.setTabTextColors(normalColor, selectedColor);
        return this;
    }
    public TabSelector textColorsRes(int normalColorRes, int selectedColorRes) {
        int normalColor = ContextCompat.getColor(view.getContext(), normalColorRes);
        int selectedColor = ContextCompat.getColor(view.getContext(), selectedColorRes);
        view.setTabTextColors(normalColor, selectedColor);
        return this;
    }
    public TabSelector iconTint(ColorStateList colorStateList) {
        view.setTabIconTint(colorStateList);
        return this;
    }
    public TabSelector indicatorBackground(@DrawableRes int resId) {
        view.setSelectedTabIndicator(resId);
        return this;
    }
    public TabSelector indicatorGravity(int gravity) {
        view.setSelectedTabIndicatorGravity(gravity);
        return this;
    }
    public TabSelector linearIndicator() {
        view.setTabIndicatorAnimationMode(TabLayout.INDICATOR_ANIMATION_MODE_LINEAR);
        return this;
    }
    public TabSelector elasticIndicator() {
        view.setTabIndicatorAnimationMode(TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC);
        return this;
    }
    public TabSelector fullWidthIndicator(boolean full) {
        view.setTabIndicatorFullWidth(full);
        return this;
    }

    public TabSelector onSelect(OnTabListener listener) {
        onSelectListener = listener;
        return this;
    }

    public TabSelector onUnselect(OnTabListener listener) {
        onUnselectListener = listener;
        return this;
    }

    public TabSelector onReselect(OnTabListener listener) {
        onReselectListener = listener;
        return this;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (onSelectListener != null)
            onSelectListener.onTab(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if (onUnselectListener != null)
            onUnselectListener.onTab(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if (onReselectListener != null)
            onReselectListener.onTab(tab);
    }

    public interface OnTabListener {
        void onTab(TabLayout.Tab tab);
    }
}
