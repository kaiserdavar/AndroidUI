package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.Vue;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseStack<T, M extends ViewGroup> extends BaseVue<T, M> {

    public BaseStack(M view) {
        super(view);
    }

    public BaseStack(Context context) {
        super(context);
    }

    @Override
    protected void onInit() {
        children = new ArrayList<>();
    }

    public T child(View child) {
        view.addView(child);
        return t;
    }

    public T child(Vue child) {
        addChild(child);
        return t;
    }

    public T child(Vue... children) {
        for (Vue child : children) {
            addChild(child);
        }
        return t;
    }

    public T child(List<Vue> children) {
        for (Vue child : children) {
            addChild(child);
        }
        return t;
    }

    public T child(OnStackChildListener listener) {
        List<Vue> children = new ArrayList<>();
        listener.onChild(children);
        for (Vue child : children) {
            addChild(child);
        }
        return t;
    }

    protected final void addChild(Vue child) {
        if (child != null) {
            onAddChild(child);
            children.add(child);
        }
    }

    protected void onAddChild(Vue child) {
        view.addView(child.view(), child.marginLp());
    }

    public T clear() {
        if (hasChild())
            children = new ArrayList<>();
        view.removeAllViews();
        return t;
    }

    public T removeChild(int id) {
        Vue vue = findChild(id);
        if (vue != null) {
            children.remove(vue);
            view.removeView(vue.view());
        }
        return t;
    }

    public T removeChild(Vue vue) {
        if (vue != null) {
            children.remove(vue);
            view.removeView(vue.view());
        }
        return t;
    }

}
