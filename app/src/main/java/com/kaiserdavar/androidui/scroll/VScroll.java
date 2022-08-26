package com.kaiserdavar.androidui.scroll;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ScrollView;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.Vue;

public class VScroll extends BaseVue<VScroll, ScrollView> {

    public static VScroll create(Context context) {
        return new VScroll(context);
    }

    public VScroll(ScrollView view) {
        super(view);
    }

    public VScroll(Context context) {
        super(context);
    }

    @Override
    protected ScrollView onGetMainView(Context context) {
        ScrollView v = new ScrollView(context);
        return v;
    }

    public VScroll child(View child) {
        view.removeAllViews();
        view.addView(child);
        return this;
    }

    public VScroll child(Vue child) {
        view.removeAllViews();
        view.addView(child.createView());
        return this;
    }

    public VScroll clear() {
        view.removeAllViews();
        return this;
    }

    public VScroll fill() {
        view.setFillViewport(true);
        return this;
    }
    public VScroll smoothScrolling(boolean enabled) {
        view.setSmoothScrollingEnabled(enabled);
        return this;
    }
    public VScroll nestedScrolling(boolean enabled) {
        view.setNestedScrollingEnabled(enabled);
        return this;
    }
    public VScroll onScroll(View.OnScrollChangeListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setOnScrollChangeListener(listener);
        }
        return this;
    }
}
