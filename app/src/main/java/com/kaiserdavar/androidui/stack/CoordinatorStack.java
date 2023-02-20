package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.kaiserdavar.androidui.Vue;

public class CoordinatorStack extends BaseStack<CoordinatorStack, CoordinatorLayout> {

    public CoordinatorStack(CoordinatorLayout view) {
        super(view);
    }

    public CoordinatorStack(Context context) {
        super(context);
    }

    public static CoordinatorStack create(Context context) {
        return new CoordinatorStack(context);
    }

    @Override
    protected CoordinatorLayout onGetMainView(Context context) {
        return new CoordinatorLayout(context);
    }

    @Override
    protected void onAddChild(Vue child) {
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(child.marginLp());
        params.gravity = child.getGravity() != -1 ? child.getGravity() : mGravity;
        view.addView(child.view(), params);
    }

    public CoordinatorStack gravity(int gravity) {
        mGravity = gravity;
        return this;
    }

}
