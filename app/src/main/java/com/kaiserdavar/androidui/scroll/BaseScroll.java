package com.kaiserdavar.androidui.scroll;

import android.content.Context;
import android.os.Build;
import android.view.ScrollCaptureCallback;
import android.view.View;
import android.view.ViewGroup;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.OnShaperListener;
import com.kaiserdavar.androidui.Vue;

import java.util.ArrayList;

public abstract class BaseScroll<T, M extends ViewGroup> extends BaseVue<T, M> {

    public BaseScroll(M view) {
        super(view);
    }

    public BaseScroll(Context context) {
        super(context);
    }

    @Override
    protected void onInit() {
        children = new ArrayList<>();
    }

    public T child(View child) {
        view.removeAllViews();
        view.addView(child);
        return t;
    }

    public T child(Vue child) {
        view.removeAllViews();
        view.addView(child.createView());
        children.add(child);
        return t;
    }

    public T clear() {
        if (hasChild())
            children = new ArrayList<>();
        view.removeAllViews();
        return t;
    }

    public abstract T fill();
    public abstract T smoothScrolling(boolean enabled);
    public abstract T scroll(int value);
    public abstract T showBar();
    public abstract T hideBar();
    public abstract T thumbShape(OnShaperListener listener);
    public abstract T trackShape(OnShaperListener listener);

    public T nestedScrolling(boolean enabled) {
        view.setNestedScrollingEnabled(enabled);
        return t;
    }
    public T barInsideOverlay() {
        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        return t;
    }
    public T barInsideInset() {
        view.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        return t;
    }
    public T barOutsideOverlay() {
        view.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        return t;
    }
    public T barOutsideInset() {
        view.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);
        return t;
    }
    public T barStyle(int style) {
        view.setScrollBarStyle(style);
        return t;
    }
    public T barSize(int dp) {
        view.setScrollBarSize(px(dp));
        return t;
    }
    public T scrollContainer(boolean isContainer) {
        view.setScrollContainer(isContainer);
        return t;
    }
    public T overScrollAlways() {
        view.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        return t;
    }
    public T overScrollIfContentScrolls() {
        view.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        return t;
    }
    public T overScrollNever() {
        view.setOverScrollMode(View.OVER_SCROLL_NEVER);
        return t;
    }
    public T scrollIndicators(int indicators) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setScrollIndicators(indicators);
        }
        return t;
    }
    public T onScrollCapture(ScrollCaptureCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            view.setScrollCaptureCallback(callback);
        }
        return t;
    }
    public T scrollCaptureHint(int hint) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            view.setScrollCaptureHint(hint);
        }
        return t;
    }
}
