package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.widget.RadioGroup;

import com.kaiserdavar.androidui.Vue;

public class BaseRadioStack<T, M extends RadioGroup> extends BaseStack<T, M> {

    public BaseRadioStack(M view) {
        super(view);
    }

    public BaseRadioStack(Context context) {
        super(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected M onGetMainView(Context context) {
        return (M) new RadioGroup(context);
    }

    @Override
    protected void onAddChild(Vue child) {
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(child.getMarginLp());
        if (child.getWeight() != 0)
            params.weight = child.getWeight();
        params.gravity = child.getGravity();
        view.addView(child.view(), params);
    }

    public T gravity(int gravity) {
        view.setGravity(gravity);
        return t;
    }

    public T horizontal() {
        view.setOrientation(RadioGroup.HORIZONTAL);
        return t;
    }
    public T vertical() {
        view.setOrientation(RadioGroup.VERTICAL);
        return t;
    }

    public T onToggle(RadioGroup.OnCheckedChangeListener listener) {
        view.setOnCheckedChangeListener(listener);
        return t;
    }

}
