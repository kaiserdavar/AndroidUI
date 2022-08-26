package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.widget.RadioGroup;

public abstract class CustomRadioStack<T> extends BaseRadioStack<T, RadioGroup> {

    public CustomRadioStack(RadioGroup view) {
        super(view);
    }

    public CustomRadioStack(Context context) {
        super(context);
    }

    @Override
    protected RadioGroup onGetMainView(Context context) {
        return new RadioGroup(context);
    }

}
