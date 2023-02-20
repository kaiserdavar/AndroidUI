package com.kaiserdavar.androidui.modifier;

import android.graphics.Rect;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.OnAnimationListener;
import com.kaiserdavar.androidui.OnClickListener;
import com.kaiserdavar.androidui.OnDragListener;
import com.kaiserdavar.androidui.OnFocusListener;
import com.kaiserdavar.androidui.OnObjectListener;
import com.kaiserdavar.androidui.OnTouchListener;

interface PropertyModifier<T, M> {

    T id(int id);
    T tag(Object tag);
    T fitSystemWindow(boolean fit);
    T clip(Rect clipBounds);
    T clipToOutline(boolean clipToOutline);
    T bringToFront();
    T elevation(int dp);
    T tooltipText(@StringRes int resId);
    T keepScreenOn(boolean keepOn);
    T soundEffectEnabled(boolean enabled);

    T shadowColorRes(@ColorRes int resId);
    T shadowColor(@ColorInt int color);
    T ambientShadowColor(@ColorInt int color);
    T spotShadowColor(@ColorInt int color);

    T visibility(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner);
    T visibility(boolean visible);
    T invisibility(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner);
    T invisibility(boolean visible);
    T visible();
    T invisible();
    T gone();
    boolean isVisible();
    T selected(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner);
    T selected(boolean selected);
    T enabled(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner);
    T disabled(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner);
    T enabled(boolean enabled);
    T disabled(boolean disabled);
    T enable();
    T disable();
    T alpha(float value);
    T rotation(float value);
    T scale(float value);
    T x(float value);
    T y(float value);
    T translationX(float dp);
    T translationY(float dp);
    T rtl();
    T ltr();
    T animation(OnAnimationListener listener);
    T view(OnObjectListener<M> listener);
    T vue(OnObjectListener<T> listener);
    T onFocus(OnFocusListener listener);
    T onTouch(OnTouchListener listener);
    T onTouchMotion(OnTouchListener listener);
    T onHover(OnTouchListener listener);
    T onDrag(OnDragListener listener);
    T onClick(OnClickListener listener);
    T onLongClick(com.kaiserdavar.androidui.OnLongClickListener listener);
    T onKey(BaseVue.OnEditorKeyListener listener);
    T onReady(com.kaiserdavar.androidui.OnViewReadyListener<T> listener);
    T action(long delay, OnObjectListener<T> listener);
    <K> T onChangeOf(MutableLiveData<K> liveData, 
                            LifecycleOwner lifecycleOwner, 
                            BaseVue.OnChangeValueListener<T, K> listener);

}
