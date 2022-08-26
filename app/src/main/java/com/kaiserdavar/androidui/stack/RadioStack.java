package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.widget.RadioGroup;

public class RadioStack extends BaseRadioStack<RadioStack, RadioGroup> {

    public static RadioStack create(Context context) {
        return new RadioStack(context);
    }

    public RadioStack(RadioGroup view) {
        super(view);
    }

    public RadioStack(Context context) {
        super(context);
    }

}
