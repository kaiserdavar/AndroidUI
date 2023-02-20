package com.kaiserdavar.androidui.scroll;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ScrollView;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.OnShaperListener;
import com.kaiserdavar.androidui.Vue;
import com.kaiserdavar.androidui.util.Shaper;

import java.util.ArrayList;

public class VScroll extends BaseScroll<VScroll, ScrollView> {

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


    @Override
    public VScroll fill() {
        view.setFillViewport(true);
        return this;
    }
    @Override
    public VScroll smoothScrolling(boolean enabled) {
        view.setSmoothScrollingEnabled(enabled);
        return this;
    }
    @Override
    public VScroll scroll(int value) {
        view.setScrollY(value);
        return this;
    }
    @Override
    public VScroll showBar() {
        view.setVerticalScrollBarEnabled(true);
        return this;
    }
    @Override
    public VScroll hideBar() {
        view.setVerticalScrollBarEnabled(false);
        return this;
    }
    @Override
    public VScroll thumbShape(OnShaperListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Shaper shaper = new Shaper(context());
            listener.onShaper(shaper);
            view.setVerticalScrollbarThumbDrawable(shaper.generate());
        }
        return this;
    }
    @Override
    public VScroll trackShape(OnShaperListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Shaper shaper = new Shaper(context());
            listener.onShaper(shaper);
            view.setVerticalScrollbarTrackDrawable(shaper.generate());
        }
        return this;
    }


    public VScroll onScroll(View.OnScrollChangeListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setOnScrollChangeListener(listener);
        }
        return this;
    }

}
