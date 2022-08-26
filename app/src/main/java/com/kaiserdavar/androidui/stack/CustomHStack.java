package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.appcompat.widget.LinearLayoutCompat;

public abstract class CustomHStack<T> extends BaseHStack<T, LinearLayoutCompat> {

    public CustomHStack(LinearLayoutCompat view) {
        super(view);
    }

    public CustomHStack(Context context) {
        super(context);
    }

    @Override
    protected LinearLayoutCompat onGetMainView(Context context) {
        LinearLayoutCompat v = new LinearLayoutCompat(context);
        v.setOrientation(LinearLayoutCompat.HORIZONTAL);
        v.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_MIDDLE);
        return v;
    }

}
