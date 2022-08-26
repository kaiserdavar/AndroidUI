package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.appcompat.widget.LinearLayoutCompat;

public class HStack extends BaseHStack<HStack, LinearLayoutCompat> {

    public static HStack create(Context context) {
        return new HStack(context);
    }

    public HStack(LinearLayoutCompat view) {
        super(view);
    }

    public HStack(Context context) {
        super(context);
    }

}
