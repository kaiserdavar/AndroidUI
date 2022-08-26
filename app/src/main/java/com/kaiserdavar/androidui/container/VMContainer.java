package com.kaiserdavar.androidui.container;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class VMContainer<T extends ViewModel> extends Container {

    protected T mViewModel;

    public void initViewModel(@NonNull Class<T> modelClass) {
        mViewModel = new ViewModelProvider(this).get(modelClass);
    }
}
