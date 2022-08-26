package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.MenuRes;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.kaiserdavar.androidui.stack.BaseStack;

public class SideNav extends BaseStack<SideNav, DrawerLayout> {

    private NavigationView navigationView;

    public static SideNav create(Context context) {
        return new SideNav(context);
    }

    public SideNav(DrawerLayout view) {
        super(view);
    }

    public SideNav(Context context) {
        super(context);
    }

    @Override
    protected DrawerLayout onGetMainView(Context context) {
        return new DrawerLayout(context);
    }

    @Override
    protected void onInit() {
        super.onInit();
        navigationView = new NavigationView(view.getContext());
        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = GravityCompat.START;
        view.addView(navigationView, params);
    }

    @Override
    protected void onAddChild(Vue child) {
        super.onAddChild(child);
        navigationView.bringToFront();
    }

    public SideNav menu(OnCreateMenuListener listener) {
        listener.onCreate(navigationView.getMenu());
        return this;
    }

    public SideNav menuRes(@MenuRes int resId) {
        navigationView.inflateMenu(resId);
        return this;
    }

    public SideNav header(Vue vue) {
        navigationView.addHeaderView(vue.createView());
        return this;
    }

    public SideNav onSelect(NavigationView.OnNavigationItemSelectedListener listener) {
        navigationView.setNavigationItemSelectedListener(listener);
        return this;
    }

    public SideNav gravity(int gravity) {
        ((DrawerLayout.LayoutParams) navigationView.getLayoutParams()).gravity = gravity;
        return this;
    }

    public SideNav itemBackground(@DrawableRes int resId) {
        navigationView.setItemBackgroundResource(resId);
        return this;
    }
    public SideNav itemHorizontalPadding(int dp) {
        navigationView.setItemHorizontalPadding(px(dp));
        return this;
    }
    public SideNav itemVerticalPadding(int dp) {
        navigationView.setItemVerticalPadding(px(dp));
        return this;
    }
    public SideNav iconPadding(int dp) {
        navigationView.setItemIconPadding(px(dp));
        return this;
    }
    public SideNav iconSize(int dp) {
        navigationView.setItemIconSize(px(dp));
        return this;
    }
    public SideNav iconTint(ColorStateList colorStateList) {
        navigationView.setItemIconTintList(colorStateList);
        return this;
    }
    public SideNav itemMaxLines(int lines) {
        navigationView.setItemMaxLines(lines);
        return this;
    }
    public SideNav itemTextAppearance(@StyleRes int resId) {
        navigationView.setItemTextAppearance(resId);
        return this;
    }
    public SideNav itemTextColor(@ColorInt int color) {
        navigationView.setItemTextColor(ColorStateList.valueOf(color));
        return this;
    }
    public SideNav itemTextColor(ColorStateList colorStateList) {
        navigationView.setItemTextColor(colorStateList);
        return this;
    }
    public SideNav itemTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        navigationView.setItemTextColor(ColorStateList.valueOf(color));
        return this;
    }
    public SideNav checkedItem(int itemId) {
        navigationView.setCheckedItem(itemId);
        return this;
    }







    public SideNav lockMode(int mode) {
        view.setDrawerLockMode(mode);
        return this;
    }
    public SideNav shadow(@DrawableRes int resId, int gravity) {
        view.setDrawerShadow(resId, gravity);
        return this;
    }
    public SideNav title(int gravity, CharSequence title) {
        view.setDrawerTitle(gravity, title);
        return this;
    }
    public SideNav statusBarBackground(@DrawableRes int resId) {
        view.setStatusBarBackground(resId);
        return this;
    }
    public SideNav scrimColor(@ColorInt int color) {
        view.setScrimColor(color);
        return this;
    }

}
