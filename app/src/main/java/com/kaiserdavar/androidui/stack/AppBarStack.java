package com.kaiserdavar.androidui.stack;

import android.content.Context;

import com.google.android.material.appbar.AppBarLayout;
import com.kaiserdavar.androidui.Vue;

public class AppBarStack extends BaseStack<AppBarStack, AppBarLayout> {

    public static AppBarStack create(Context context) {
        return new AppBarStack(context);
    }

    public AppBarStack(AppBarLayout view) {
        super(view);
    }

    public AppBarStack(Context context) {
        super(context);
    }

    @Override
    protected AppBarLayout onGetMainView(Context context) {
        return new AppBarLayout(context);
    }

    @Override
    protected void onAddChild(Vue child) {
        AppBarLayout.LayoutParams params = new AppBarLayout.LayoutParams(child.getMarginLp());
        params.gravity = child.getGravity() != -1 ? child.getGravity() : mGravity;
        view.addView(child.view(), params);
    }

    public AppBarStack gravity(int gravity) {
        mGravity = gravity;
        return this;
    }

}
