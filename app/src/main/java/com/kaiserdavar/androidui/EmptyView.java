package com.kaiserdavar.androidui;

import android.content.Context;
import android.view.View;

public class EmptyView extends BaseVue<EmptyView, View> {
    public EmptyView(View view) {
        super(view);
    }

    public EmptyView(Context context) {
        super(context);
    }

    public static EmptyView create(Context context) {
        return new EmptyView(context);
    }

    @Override
    protected View onGetMainView(Context context) {
        return new View(context);
    }

}
