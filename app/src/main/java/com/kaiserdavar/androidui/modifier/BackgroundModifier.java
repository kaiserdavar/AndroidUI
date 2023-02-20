package com.kaiserdavar.androidui.modifier;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.kaiserdavar.androidui.OnShaperListener;
import com.kaiserdavar.androidui.style.Color;
import com.kaiserdavar.androidui.style.ColorSelector;
import com.kaiserdavar.androidui.style.ShapeSelector;
import com.kaiserdavar.androidui.util.Shaper;

interface BackgroundModifier<T> {

    T background(LiveData<Shaper> liveData, LifecycleOwner lifecycleOwner);
    T background(Shaper shaper);
    T background(OnShaperListener listener);
    T background(@DrawableRes int resource);
    T background(Drawable drawable);
    T backgroundColor(@ColorInt int color);
    T background(ShapeSelector selector);
    T selectableItemBackground();
    T backgroundByAttr(@AttrRes int attr);
    T backgroundTint(PorterDuff.Mode tintMode, ColorSelector selector);
    T backgroundTint(ColorStateList colorStateList);
    T noBackground();

    T foreground(LiveData<Shaper> liveData, LifecycleOwner lifecycleOwner);
    T foreground(Shaper shaper);
    T foreground(OnShaperListener listener);
    T foreground(@DrawableRes int resource);
    T foreground(Drawable drawable);
    T foregroundColor(@ColorInt int color);
    T foregroundGravity(int gravity);
    T selectableItemForeground();
    T foregroundByAttr(@AttrRes int attr);
    T foregroundTint(PorterDuff.Mode tintMode, ColorSelector selector);
    T foregroundTint(ColorStateList colorStateList);
    T noForeground();

}
