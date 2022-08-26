package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.widget.FrameLayout;

public class ZStack extends BaseZStack<ZStack, FrameLayout> {

    public static ZStack create(Context context) {
        return new ZStack(context);
    }

    public ZStack(FrameLayout view) {
        super(view);
    }

    public ZStack(Context context) {
        super(context);
    }

}
