package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.ColorStateList;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.MenuRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.kaiserdavar.androidui.style.ColorSelector;

public class BottomNavigation extends BaseVue<BottomNavigation, BottomNavigationView>  {

    public static BottomNavigation create(Context context) {
        return new BottomNavigation(context);
    }

    public BottomNavigation(BottomNavigationView view) {
        super(view);
    }

    public BottomNavigation(Context context) {
        super(context);
    }

    @Override
    protected BottomNavigationView onGetMainView(Context context) {
        return new BottomNavigationView(context);
    }

    public BottomNavigation menu(CharSequence text) {
        view.getMenu().add(text);
        return this;
    }

    public BottomNavigation menu(@StringRes int textRes) {
        view.getMenu().add(textRes);
        return this;
    }

    public BottomNavigation menu(@StringRes int textRes, @DrawableRes int icon) {
        view.getMenu().add(textRes).setIcon(icon);
        return this;
    }

    public BottomNavigation menuRes(@MenuRes int resId) {
        view.inflateMenu(resId);
        return this;
    }
    public BottomNavigation menu(com.kaiserdavar.androidui.OnCreateMenuListener listener) {
        listener.onCreate(view.getMenu());
        return this;
    }

    public BottomNavigation itemBackground(@DrawableRes int resId) {
        view.setItemBackgroundResource(resId);
        return this;
    }
    public BottomNavigation itemPaddingTop(int dp) {
        view.setItemPaddingTop(px(dp));
        return this;
    }
    public BottomNavigation itemPaddingBottom(int dp) {
        view.setItemPaddingBottom(px(dp));
        return this;
    }
    public BottomNavigation itemTextColor(@ColorInt int color) {
        view.setItemTextColor(ColorStateList.valueOf(color));
        return this;
    }
    public BottomNavigation itemTextColor(ColorSelector selector) {
        return itemTextColor(selector.getColor());
    }
    public BottomNavigation itemTextColor(ColorStateList colorStateList) {
        view.setItemTextColor(colorStateList);
        return this;
    }
    public BottomNavigation itemTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setItemTextColor(ColorStateList.valueOf(color));
        return this;
    }
    public BottomNavigation iconSize(int dp) {
        view.setItemIconSize(px(dp));
        return this;
    }
    public BottomNavigation iconTint(ColorSelector selector) {
        view.setItemIconTintList(selector.getColor());
        return this;
    }
    public BottomNavigation iconTint(ColorStateList colorStateList) {
        view.setItemIconTintList(colorStateList);
        return this;
    }
    public BottomNavigation selectedItem(int itemId) {
        view.setSelectedItemId(itemId);
        return this;
    }
    public BottomNavigation labelMode(int mode) {
        view.setLabelVisibilityMode(mode);
        return this;
    }
    public BottomNavigation activeTextAppearance(int resId) {
        view.setItemTextAppearanceActive(resId);
        return this;
    }
    public BottomNavigation inactiveTextAppearance(int resId) {
        view.setItemTextAppearanceInactive(resId);
        return this;
    }
    public BottomNavigation rippleColor(ColorStateList colorStateList) {
        view.setItemRippleColor(colorStateList);
        return this;
    }
    public BottomNavigation rippleColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setItemRippleColor(ColorStateList.valueOf(color));
        return this;
    }

    public BottomNavigation indicatorEnabled() {
        view.setItemActiveIndicatorEnabled(true);
        return this;
    }
    public BottomNavigation indicatorWidth(int dp) {
        view.setItemActiveIndicatorWidth(px(dp));
        return this;
    }
    public BottomNavigation indicatorHeight(int dp) {
        view.setItemActiveIndicatorHeight(px(dp));
        return this;
    }
    public BottomNavigation indicatorMargin(int dp) {
        view.setItemActiveIndicatorMarginHorizontal(px(dp));
        return this;
    }
    public BottomNavigation indicatorColor(ColorStateList colorStateList) {
        view.setItemActiveIndicatorColor(colorStateList);
        return this;
    }
    public BottomNavigation indicatorColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setItemActiveIndicatorColor(ColorStateList.valueOf(color));
        return this;
    }

    public BottomNavigation onSelect(NavigationBarView.OnItemSelectedListener listener) {
        view.setOnItemSelectedListener(listener);
        return this;
    }
    public BottomNavigation onReselect(NavigationBarView.OnItemReselectedListener listener) {
        view.setOnItemReselectedListener(listener);
        return this;
    }


}
