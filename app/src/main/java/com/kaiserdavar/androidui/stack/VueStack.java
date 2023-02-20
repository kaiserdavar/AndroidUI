package com.kaiserdavar.androidui.stack;

import android.view.View;
import com.kaiserdavar.androidui.Vue;

import java.util.List;

public interface VueStack<T> {

    T child(View child);
    T child(Vue child);
    T child(Vue... children);
    T child(List<Vue> children);
    T child(OnStackChildListener listener);
    T clear();
    T removeChild(int id);
    T removeChild(Vue vue);
    void addChild(Vue child);
    void onAddChild(Vue child);
    Vue findChild(int id);
    Vue findChildDeep(int id);
    Vue getChild(int index);
    boolean hasChild();

}
