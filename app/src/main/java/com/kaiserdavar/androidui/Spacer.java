package com.kaiserdavar.androidui;

import android.content.Context;
import android.widget.Space;

public class Spacer extends BaseVue<Spacer, Space> {

    public static Spacer create(Context context) {
        return new Spacer(context);
    }

    public Spacer(Space view) {
        super(view);
    }

    public Spacer(Context context) {
        super(context);
    }

    @Override
    protected Space onGetMainView(Context context) {
        return new Space(context);
    }

}
