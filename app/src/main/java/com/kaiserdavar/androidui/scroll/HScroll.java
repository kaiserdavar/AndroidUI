package com.kaiserdavar.androidui.scroll;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.Vue;

public class HScroll extends BaseVue<HScroll, HorizontalScrollView> {

    public static HScroll create(Context context) {
        return new HScroll(context);
    }

    public HScroll(HorizontalScrollView view) {
        super(view);
    }

    public HScroll(Context context) {
        super(context);
    }

    @Override
    protected HorizontalScrollView onGetMainView(Context context) {
        HorizontalScrollView v = new HorizontalScrollView(context);
        return v;
    }

    public HScroll child(View child) {
        view.removeAllViews();
        view.addView(child);
        return this;
    }

    public HScroll child(Vue child) {
        view.removeAllViews();
        view.addView(child.createView());
        return this;
    }

    public HScroll clear() {
        view.removeAllViews();
        return this;
    }

    public HScroll fill() {
        view.setFillViewport(true);
        return this;
    }
    public HScroll smoothScrolling(boolean enabled) {
        view.setSmoothScrollingEnabled(enabled);
        return this;
    }
    public HScroll nestedScrolling(boolean enabled) {
        view.setNestedScrollingEnabled(enabled);
        return this;
    }
    public HScroll onScroll(View.OnScrollChangeListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setOnScrollChangeListener(listener);
        }
        return this;
    }
}
