package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.ColorStateList;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.MenuRes;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;

import com.google.android.material.navigation.NavigationView;
import com.kaiserdavar.androidui.stack.BaseStack;
import com.kaiserdavar.androidui.style.ColorSelector;

public class SideNavigation extends BaseStack<SideNavigation, NavigationView> {

    public static SideNavigation create(Context context) {
        return new SideNavigation(context);
    }

    public SideNavigation(NavigationView view) {
        super(view);
    }

    public SideNavigation(Context context) {
        super(context);
    }

    @Override
    protected NavigationView onGetMainView(Context context) {
        return new NavigationView(context);
    }

    @Override
    protected void onAddChild(Vue child) {
        NavigationView.LayoutParams params = new NavigationView.LayoutParams(child.getMarginLp());
        params.gravity = child.getGravity() != -1 ? child.getGravity() : mGravity;
        view.addView(child.view(), params);
    }

    public SideNavigation menu(CharSequence text) {
        view.getMenu().add(text);
        return this;
    }

    public SideNavigation menuRes(@MenuRes int resId) {
        view.inflateMenu(resId);
        return this;
    }

    public SideNavigation header(Vue vue) {
        view.addHeaderView(vue.createView());
        return this;
    }

    public SideNavigation itemBackground(@DrawableRes int resId) {
        view.setItemBackgroundResource(resId);
        return this;
    }
    public SideNavigation itemHorizontalPadding(int dp) {
        view.setItemHorizontalPadding(px(dp));
        return this;
    }
    public SideNavigation itemVerticalPadding(int dp) {
        view.setItemVerticalPadding(px(dp));
        return this;
    }
    public SideNavigation iconPadding(int dp) {
        view.setItemIconPadding(px(dp));
        return this;
    }
    public SideNavigation iconSize(int dp) {
        view.setItemIconSize(px(dp));
        return this;
    }
    public SideNavigation iconTint(ColorSelector selector) {
        view.setItemIconTintList(selector.getColor());
        return this;
    }
    public SideNavigation iconTint(ColorStateList colorStateList) {
        view.setItemIconTintList(colorStateList);
        return this;
    }
    public SideNavigation itemMaxLines(int lines) {
        view.setItemMaxLines(lines);
        return this;
    }
    public SideNavigation itemTextAppearance(@StyleRes int resId) {
        view.setItemTextAppearance(resId);
        return this;
    }
    public SideNavigation itemTextColor(@ColorInt int color) {
        view.setItemTextColor(ColorStateList.valueOf(color));
        return this;
    }
    public SideNavigation itemTextColor(ColorSelector selector) {
        view.setItemTextColor(selector.getColor());
        return this;
    }
    public SideNavigation itemTextColor(ColorStateList colorStateList) {
        view.setItemTextColor(colorStateList);
        return this;
    }
    public SideNavigation itemTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setItemTextColor(ColorStateList.valueOf(color));
        return this;
    }
    public SideNavigation checkedItem(int itemId) {
        view.setCheckedItem(itemId);
        return this;
    }

    public SideNavigation onSelect(NavigationView.OnNavigationItemSelectedListener listener) {
        view.setNavigationItemSelectedListener(listener);
        return this;
    }
    
}
