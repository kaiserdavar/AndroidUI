package com.kaiserdavar.androidui.scroll;

import android.content.Context;
import android.os.Build;
import android.view.View;

import androidx.core.widget.NestedScrollView;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.OnShaperListener;
import com.kaiserdavar.androidui.Vue;
import com.kaiserdavar.androidui.util.Shaper;

public class NestedScroll extends BaseScroll<NestedScroll, NestedScrollView> {

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


    @Override
    public NestedScroll fill() {
        view.setFillViewport(true);
        return this;
    }
    @Override
    public NestedScroll smoothScrolling(boolean enabled) {
        view.setSmoothScrollingEnabled(enabled);
        return this;
    }
    @Override
    public NestedScroll scroll(int value) {
        view.setScrollY(value);
        return this;
    }
    @Override
    public NestedScroll showBar() {
        view.setVerticalScrollBarEnabled(true);
        return this;
    }
    @Override
    public NestedScroll hideBar() {
        view.setVerticalScrollBarEnabled(false);
        return this;
    }
    @Override
    public NestedScroll thumbShape(OnShaperListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Shaper shaper = new Shaper(context());
            listener.onShaper(shaper);
            view.setVerticalScrollbarThumbDrawable(shaper.generate());
        }
        return this;
    }
    @Override
    public NestedScroll trackShape(OnShaperListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Shaper shaper = new Shaper(context());
            listener.onShaper(shaper);
            view.setVerticalScrollbarTrackDrawable(shaper.generate());
        }
        return this;
    }


    public NestedScroll onScroll(NestedScrollView.OnScrollChangeListener listener) {
        view.setOnScrollChangeListener(listener);
        return this;
    }
}
