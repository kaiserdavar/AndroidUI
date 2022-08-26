package com.kaiserdavar.androidui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

public class Check extends com.kaiserdavar.androidui.BaseText<Check, AppCompatCheckBox>
        implements CompoundButton.OnCheckedChangeListener{

    private com.kaiserdavar.androidui.OnToggleListener onToggleListener;
    private MutableLiveData<Boolean> toggleLiveData;
    private boolean isChanging;

    public static Check create(Context context) {
        return new Check(context);
    }

    public Check(AppCompatCheckBox view) {
        super(view);
    }

    public Check(Context context) {
        super(context);
    }

    @Override
    protected AppCompatCheckBox onGetMainView(Context context) {
        AppCompatCheckBox v = new AppCompatCheckBox(context);
        v.setOnCheckedChangeListener(this);
        return v;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        changeState(b, toggleLiveData);
        if (onToggleListener != null) {
            onToggleListener.onToggle(b);
        }
    }

    public Check buttonDrawable(@DrawableRes int resId) {
        view.setButtonDrawable(resId);
        return this;
    }
    public Check buttonDrawable(Drawable drawable) {
        view.setButtonDrawable(drawable);
        return this;
    }

    public Check toggle(MutableLiveData<Boolean> liveData, LifecycleOwner lifecycleOwner) {
        toggleLiveData = liveData;
        liveData.observe(lifecycleOwner, this::toggle);
        return this;
    }
    public Check toggle(boolean toggle) {
        changeState(toggle, null);
        return this;
    }

    public Check onToggle(com.kaiserdavar.androidui.OnToggleListener listener) {
        this.onToggleListener = listener;
        return this;
    }

    private void changeState(boolean state, MutableLiveData<Boolean> liveData) {
        if (isChanging)
            return;
        isChanging = true;
        if (liveData != null)
            liveData.setValue(state);
        else
            view.setChecked(state);
        isChanging = false;
    }

}
