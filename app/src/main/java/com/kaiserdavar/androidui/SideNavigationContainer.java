package com.kaiserdavar.androidui;

import android.content.Context;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.kaiserdavar.androidui.stack.BaseStack;

public class SideNavigationContainer extends BaseStack<SideNavigationContainer, DrawerLayout> {

    public NavigationView navigationView;

    public static SideNavigationContainer create(Context context) {
        return new SideNavigationContainer(context);
    }

    public SideNavigationContainer(DrawerLayout view) {
        super(view);
    }

    public SideNavigationContainer(Context context) {
        super(context);
    }

    @Override
    protected DrawerLayout onGetMainView(Context context) {
        return new DrawerLayout(context);
    }

    @Override
    protected void onAddChild(com.kaiserdavar.androidui.Vue child) {
        if (child.view() instanceof NavigationView) {
            DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(child.marginLp());
            params.gravity = child.getGravity() != -1 ? child.getGravity() : mGravity;
            view.addView(child.view(), params);
        } else {
            super.onAddChild(child);
        }
    }

    public void open() {
        view.open();
    }
    public void open(int gravity) {
        view.openDrawer(gravity);
    }
    public void close() {
        view.close();
    }
    public void close(int gravity) {
        view.closeDrawer(gravity);
    }
    public void toggle() {
        if (view.isOpen())
            view.close();
        else
            view.open();
    }

    public SideNavigationContainer lockMode(int mode) {
        view.setDrawerLockMode(mode);
        return this;
    }
    public SideNavigationContainer shadow(@DrawableRes int resId, int gravity) {
        view.setDrawerShadow(resId, gravity);
        return this;
    }
    public SideNavigationContainer title(int gravity, CharSequence title) {
        view.setDrawerTitle(gravity, title);
        return this;
    }
    public SideNavigationContainer statusBarBackground(@DrawableRes int resId) {
        view.setStatusBarBackground(resId);
        return this;
    }
    public SideNavigationContainer scrimColor(@ColorInt int color) {
        view.setScrimColor(color);
        return this;
    }

}
