package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CStack extends BaseCStack<CStack, ConstraintLayout> {

    public static CStack create(Context context) {
        return new CStack(context);
    }

    public CStack(ConstraintLayout view) {
        super(view);
    }

    public CStack(Context context) {
        super(context);
    }

}
