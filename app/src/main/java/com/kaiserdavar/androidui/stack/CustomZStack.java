package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.widget.FrameLayout;

public abstract class CustomZStack<T> extends BaseZStack<T, FrameLayout> {

    public CustomZStack(FrameLayout view) {
        super(view);
    }

    public CustomZStack(Context context) {
        super(context);
    }

    @Override
    protected FrameLayout onGetMainView(Context context) {
        return new FrameLayout(context);
    }

}
