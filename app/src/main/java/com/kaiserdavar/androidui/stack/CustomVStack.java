package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.appcompat.widget.LinearLayoutCompat;

public abstract class CustomVStack<T> extends BaseVStack<T, LinearLayoutCompat> {

    public CustomVStack(LinearLayoutCompat view) {
        super(view);
    }

    public CustomVStack(Context context) {
        super(context);
    }

    @Override
    protected LinearLayoutCompat onGetMainView(Context context) {
        LinearLayoutCompat v = new LinearLayoutCompat(context);
        v.setOrientation(LinearLayoutCompat.VERTICAL);
        v.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_MIDDLE);
        return v;
    }

}
