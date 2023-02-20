package com.kaiserdavar.androidui;

import android.content.Context;
import android.widget.CompoundButton;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class Switcher extends BaseVue<Switcher, SwitchMaterial>
        implements CompoundButton.OnCheckedChangeListener {

    private OnToggleListener onToggleListener;
    private MutableLiveData<Boolean> toggleLiveData;
    private boolean isChanging;

    public static Switcher create(Context context) {
        return new Switcher(context);
    }

    public Switcher(SwitchMaterial view) {
        super(view);
    }

    public Switcher(Context context) {
        super(context);
    }

    @Override
    protected SwitchMaterial onGetMainView(Context context) {
        SwitchMaterial v = new SwitchMaterial(context);
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

    public Switcher toggle(MutableLiveData<Boolean> liveData, LifecycleOwner lifecycleOwner) {
        toggleLiveData = liveData;
        liveData.observe(lifecycleOwner, this::toggle);
        return this;
    }
    public Switcher toggle(boolean toggle) {
        changeState(toggle, null);
        return this;
    }

    public Switcher onToggle(OnToggleListener listener) {
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
