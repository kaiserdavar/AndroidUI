package com.kaiserdavar.androidui.container;

import android.app.Application;
import android.content.res.Resources;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;

import com.kaiserdavar.androidui.R;

public class VM extends AndroidViewModel {

    public VM(@NonNull Application application) {
        super(application);
    }

    public Resources getResources() {
        return getApplication().getResources();
    }
    public String getString(@StringRes int resId) {
        return getApplication().getString(resId);
    }
    public String getString(@StringRes int resId, Object... formatArgs) {
        return getApplication().getString(resId, formatArgs);
    }
    public @ColorInt int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(getApplication(), resId);
    }

}
