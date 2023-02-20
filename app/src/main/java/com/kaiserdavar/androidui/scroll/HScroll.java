package com.kaiserdavar.androidui.scroll;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.OnShaperListener;
import com.kaiserdavar.androidui.Vue;
import com.kaiserdavar.androidui.util.Shaper;

import java.util.ArrayList;

public class HScroll extends BaseScroll<HScroll, HorizontalScrollView> {

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

    @Override
    public HScroll fill() {
        view.setFillViewport(true);
        return this;
    }
    @Override
    public HScroll smoothScrolling(boolean enabled) {
        view.setSmoothScrollingEnabled(enabled);
        return this;
    }
    @Override
    public HScroll scroll(int value) {
        view.setScrollX(value);
        return this;
    }
    @Override
    public HScroll showBar() {
        view.setHorizontalScrollBarEnabled(true);
        return this;
    }
    @Override
    public HScroll hideBar() {
        view.setHorizontalScrollBarEnabled(false);
        return this;
    }
    @Override
    public HScroll thumbShape(OnShaperListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Shaper shaper = new Shaper(context());
            listener.onShaper(shaper);
            view.setHorizontalScrollbarThumbDrawable(shaper.generate());
        }
        return this;
    }
    @Override
    public HScroll trackShape(OnShaperListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Shaper shaper = new Shaper(context());
            listener.onShaper(shaper);
            view.setHorizontalScrollbarTrackDrawable(shaper.generate());
        }
        return this;
    }


    public HScroll onScroll(View.OnScrollChangeListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setOnScrollChangeListener(listener);
        }
        return this;
    }

}
