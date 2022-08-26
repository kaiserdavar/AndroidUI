package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CustomCStack<T> extends BaseCStack<T, ConstraintLayout> {

    public CustomCStack(ConstraintLayout view) {
        super(view);
    }

    public CustomCStack(Context context) {
        super(context);
    }

}
