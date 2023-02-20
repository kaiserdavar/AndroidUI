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
import com.kaiserdavar.androidui.style.ActionButtonStyle;
import com.kaiserdavar.androidui.style.ColorSelector;
import com.kaiserdavar.androidui.style.TextStyle;
import com.kaiserdavar.androidui.style.VueStyle;

public class ActionButton extends CustomZStack<ActionButton> {
    public static VueStyle defaultStyle;

    private Text mTextVue;
    private Image mImageVue;
    private Progress mProgressVue;
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
        mImageVue = Image.create(context());
        mTextVue = Text.create(context());
        mProgressVue = com.kaiserdavar.androidui.Progress.create(context())
                .size(30, 30)
                .progressColor(Color.WHITE)
                .gone();
        child(mImageVue);
        child(mTextVue);
        child(mProgressVue);
        if (defaultStyle != null) {
            style(defaultStyle);
            if (defaultStyle instanceof ActionButtonStyle) {
                ActionButtonStyle actionButtonStyle = (ActionButtonStyle) defaultStyle;
                if (actionButtonStyle.titleStyle != null)
                    mTextVue.style(actionButtonStyle.titleStyle);
                if (actionButtonStyle.progressStyle != null)
                    mProgressVue.style(actionButtonStyle.progressStyle);
                if (actionButtonStyle.imageStyle != null)
                    mImageVue.style(actionButtonStyle.imageStyle);
            }
        }
    }

    public ActionButton textVue(OnObjectListener<Text> listener) {
        listener.onObject(mTextVue);
        return this;
    }
    public ActionButton imageVue(OnObjectListener<Image> listener) {
        listener.onObject(mImageVue);
        return this;
    }
    public ActionButton progressVue(OnObjectListener<Progress> listener) {
        listener.onObject(mProgressVue);
        return this;
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
        mProgressVue.size(size, size);
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
