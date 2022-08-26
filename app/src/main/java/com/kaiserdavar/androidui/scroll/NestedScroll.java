package com.kaiserdavar.androidui.scroll;

import android.content.Context;
import android.view.View;

import androidx.core.widget.NestedScrollView;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.Vue;

public class NestedScroll extends BaseVue<NestedScroll, NestedScrollView> {

    public static NestedScroll create(Context context) {
        return new NestedScroll(context);
    }

    public NestedScroll(NestedScrollView view) {
        super(view);
    }

    public NestedScroll(Context context) {
        super(context);
    }

    @Override
    protected NestedScrollView onGetMainView(Context context) {
        NestedScrollView v = new NestedScrollView(context);
        return v;
    }

    public NestedScroll child(View child) {
        view.removeAllViews();
        view.addView(child);
        return this;
    }

    public NestedScroll child(Vue child) {
        view.removeAllViews();
        view.addView(child.createView());
        return this;
    }

    public NestedScroll clear() {
        view.removeAllViews();
        return this;
    }

    public NestedScroll fill() {
        view.setFillViewport(true);
        return this;
    }

    public NestedScroll smoothScrolling(boolean enabled) {
        view.setSmoothScrollingEnabled(enabled);
        return this;
    }
    public NestedScroll nestedScrolling(boolean enabled) {
        view.setNestedScrollingEnabled(enabled);
        return this;
    }
    public NestedScroll onScroll(NestedScrollView.OnScrollChangeListener listener) {
        view.setOnScrollChangeListener(listener);
        return this;
    }
}
