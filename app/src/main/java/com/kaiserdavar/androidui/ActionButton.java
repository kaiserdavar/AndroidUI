package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Gravity;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.StringRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.kaiserdavar.androidui.stack.CustomZStack;
import com.kaiserdavar.androidui.style.ColorSelector;
import com.kaiserdavar.androidui.style.TextStyle;

public class ActionButton extends CustomZStack<ActionButton> {
    public static TextStyle defaultStyle;

    private com.kaiserdavar.androidui.Text mTextVue;
    private com.kaiserdavar.androidui.Image mImageVue;
    private com.kaiserdavar.androidui.Progress mProgressVue;
    private boolean isLoading;

    public static ActionButton create(Context context) {
        return new ActionButton(context);
    }

    public ActionButton(Context context) {
        super(context);
    }

    @Override
    protected void onInit() {
        super.onInit();
        gravity(Gravity.CENTER);
        mImageVue = com.kaiserdavar.androidui.Image.create(context());
        mTextVue = com.kaiserdavar.androidui.Text.create(context());
        child(mImageVue);
        child(mTextVue);
        addProgress(30);
        if (defaultStyle != null)
            mTextVue.style(defaultStyle);
    }

    private void addProgress(int size) {
        removeChild(mProgressVue);
        mProgressVue = com.kaiserdavar.androidui.Progress.create(context())
                .size(size, size)
                .progressColor(Color.WHITE)
                .gone();
        child(mProgressVue);
    }

    public ActionButton text(LiveData<CharSequence> liveData, LifecycleOwner lifecycleOwner) {
        mTextVue.text(liveData, lifecycleOwner);
        return this;
    }
    public ActionButton text(CharSequence text) {
        mTextVue.text(text);
        return this;
    }
    public ActionButton text(@StringRes int resId) {
        mTextVue.text(resId);
        return this;
    }

    public ActionButton textColorRes(LiveData<Integer> colorLiveData, LifecycleOwner lifecycleOwner) {
        mTextVue.textColorRes(colorLiveData, lifecycleOwner);
        return this;
    }
    public ActionButton textColorRes(@ColorRes int resId) {
        mTextVue.textColorRes(resId);
        return this;
    }

    public ActionButton textColor(LiveData<Integer> colorLiveData, LifecycleOwner lifecycleOwner) {
        colorLiveData.observe(lifecycleOwner, this::textColor);
        return this;
    }
    public ActionButton textColor(int color) {
        mTextVue.textColor(color);
        return this;
    }
    public ActionButton textColor(ColorSelector selector) {
        return textColor(selector.getColor());
    }
    public ActionButton textColor(ColorStateList colors) {
        mTextVue.textColor(colors);
        return this;
    }

    public ActionButton textSize(float size) {
        mTextVue.textSize(size);
        return this;
    }

    public ActionButton font(@FontRes int fontResId) {
        mTextVue.font(fontResId);
        return this;
    }

    public ActionButton textAlign(int gravity) {
        mTextVue.textAlign(gravity);
        return this;
    }

    public ActionButton imageRes(@DrawableRes int resId) {
        mImageVue.imageRes(resId);
        return this;
    }
    public ActionButton imageSize(int dp) {
        mImageVue.size(dp, dp);
        return this;
    }

    public ActionButton drawableTop(@DrawableRes int resId) {
        mTextVue.drawableTop(resId);
        return this;
    }
    public ActionButton drawableBottom(@DrawableRes int resId) {
        mTextVue.drawableBottom(resId);
        return this;
    }
    public ActionButton drawableStart(@DrawableRes int resId) {
        mTextVue.drawableStart(resId);
        return this;
    }
    public ActionButton drawableEnd(@DrawableRes int resId) {
        mTextVue.drawableEnd(resId);
        return this;
    }
    public ActionButton noDrawable() {
        mTextVue.noDrawable();
        return this;
    }
    public ActionButton drawablePadding(int dp) {
        mTextVue.drawablePadding(dp);
        return this;
    }

    public ActionButton progressColor(int color) {
        mProgressVue.progressColor(color);
        return this;
    }

    public ActionButton progressSize(int size) {
        addProgress(size);
        return this;
    }

    public ActionButton loading(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::loading);
        return this;
    }
    public ActionButton loading(boolean loading) {
        this.isLoading = loading;
        mImageVue.visibility(!loading);
        mTextVue.visibility(!loading);
        mProgressVue.visibility(loading);
        view.setEnabled(!loading);
        return this;
    }

    public boolean isLoading() {
        return isLoading;
    }

}
