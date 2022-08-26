package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.res.ResourcesCompat;

import com.kaiserdavar.androidui.OnBackgroundListener;
import com.kaiserdavar.androidui.Vue;
import com.kaiserdavar.androidui.util.Shaper;

public abstract class BaseHStack<T, M extends LinearLayoutCompat> extends BaseStack<T, M> {

    private Integer mSpacing;

    public BaseHStack(M view) {
        super(view);
    }

    public BaseHStack(Context context) {
        super(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected M onGetMainView(Context context) {
        LinearLayoutCompat v = new LinearLayoutCompat(context);
        v.setOrientation(LinearLayoutCompat.HORIZONTAL);
        v.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_MIDDLE);
        return (M) v;
    }

    @Override
    protected void onAddChild(Vue child) {
        LinearLayoutCompat.LayoutParams params =
                new LinearLayoutCompat.LayoutParams(child.getMarginLp());
        if (child.getWeight() != 0)
            params.weight = child.getWeight();
        if (mSpacing != null && view.getChildCount() > 0)
            params.setMarginStart(px(mSpacing));
        params.gravity = child.getGravity();
        view.addView(child.view(), params);
    }

    public T gravity(int gravity) {
        view.setGravity(gravity);
        return t;
    }

    public T spacing(int dp) {
        if (mSpacing == null)
            mSpacing = 0;
        mSpacing += dp;
        int count = view.getChildCount();
        if (count > 1) {
            for (int i = 1; i < count; i++) {
                ((ViewGroup.MarginLayoutParams)view.getChildAt(i).getLayoutParams()).setMarginStart(px(mSpacing));
            }
        }
        return t;
    }

    public T divider(@DrawableRes int drawableRes) {
        Drawable d = ResourcesCompat.getDrawable(view.getResources(),
                drawableRes, view.getContext().getTheme());
        view.setDividerDrawable(d);
        return t;
    }

    public T divider(@ColorRes int colorRes, int width) {
        Drawable d = new Shaper()
                .backgroundColor(view.getContext(), colorRes)
                .size(width, 0).generate();
        view.setDividerDrawable(d);
        return t;
    }

    public T divider(OnBackgroundListener listener) {
        Shaper back = new Shaper()
                .backgroundColor(Color.LTGRAY)
                .size(1, 0);
        listener.onBackground(back);
        view.setDividerDrawable(back.generate());
        return t;
    }

    public T dividerType(@LinearLayoutCompat.DividerMode int type) {
        view.setShowDividers(type);
        return t;
    }

}
