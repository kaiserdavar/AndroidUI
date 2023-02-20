package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.widget.FrameLayout;

import com.kaiserdavar.androidui.Vue;

public abstract class BaseZStack<T, M extends FrameLayout> extends BaseStack<T, M> {

    public BaseZStack(M view) {
        super(view);
    }

    public BaseZStack(Context context) {
        super(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected M onGetMainView(Context context) {
        return (M) new FrameLayout(context);
    }

    @Override
    protected void onAddChild(Vue child) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(child.marginLp());
        params.gravity = child.getGravity() != -1 ? child.getGravity() : mGravity;
        view.addView(child.view(), params);
    }

    public T gravity(int gravity) {
        mGravity = gravity;
        return t;
    }

}
