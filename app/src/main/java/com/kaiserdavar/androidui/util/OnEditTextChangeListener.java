package com.kaiserdavar.androidui.util;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class OnEditTextChangeListener implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public abstract void onTextChanged(CharSequence charSequence, int i, int i1, int i2);

    @Override
    public void afterTextChanged(Editable editable) { }
}
