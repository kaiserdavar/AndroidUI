package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.appcompat.widget.LinearLayoutCompat;

public class VStack extends BaseVStack<VStack, LinearLayoutCompat> {

    public static VStack create(Context context) {
        return new VStack(context);
    }

    public VStack(LinearLayoutCompat view) {
        super(view);
    }

    public VStack(Context context) {
        super(context);
    }

}
