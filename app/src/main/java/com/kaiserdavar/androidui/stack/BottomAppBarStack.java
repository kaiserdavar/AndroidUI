package com.kaiserdavar.androidui.stack;

import android.content.Context;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.kaiserdavar.androidui.Vue;

public class BottomAppBarStack extends BaseStack<BottomAppBarStack, BottomAppBar> {

    public static BottomAppBarStack create(Context context) {
        return new BottomAppBarStack(context);
    }

    public BottomAppBarStack(BottomAppBar view) {
        super(view);
    }

    public BottomAppBarStack(Context context) {
        super(context);
    }

    @Override
    protected BottomAppBar onGetMainView(Context context) {
        return new BottomAppBar(context);
    }

    @Override
    protected void onAddChild(Vue child) {
        BottomAppBar.LayoutParams params = new BottomAppBar.LayoutParams(child.getMarginLp());
        params.gravity = child.getGravity() != -1 ? child.getGravity() : mGravity;
        view.addView(child.view(), params);
    }

    public BottomAppBarStack gravity(int gravity) {
        mGravity = gravity;
        return this;
    }

}
