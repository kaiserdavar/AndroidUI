package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.kaiserdavar.androidui.style.ColorSelector;

public class MaterialInput extends com.kaiserdavar.androidui.BaseVue<MaterialInput, TextInputLayout> {

    private com.kaiserdavar.androidui.MaterialInputEditText inputEditText;

    public static MaterialInput create(Context context) {
        return new MaterialInput(context);
    }
    
    public MaterialInput(TextInputLayout view) {
        super(view);
    }

    public MaterialInput(Context context) {
        super(context);
    }

    @Override
    protected TextInputLayout onGetMainView(Context context) {
        return new TextInputLayout(context);
    }

    public MaterialInput font(@FontRes int fontResId) {
        view.setTypeface(ResourcesCompat.getFont(view.getContext(), fontResId));
        return this;
    }

    public MaterialInput errorText(CharSequence text) {
        view.setError(text);
        return this;
    }

    public MaterialInput errorText(int resId) {
        view.setError(view.getContext().getString(resId));
        return this;
    }

    public MaterialInput errorIcon(@DrawableRes int resId) {
        view.setErrorIconDrawable(resId);
        return this;
    }

    public MaterialInput errorTextColor(@ColorInt int color) {
        view.setErrorTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInput errorTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setErrorTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInput errorTextColor(ColorSelector selector) {
        view.setErrorTextColor(selector.getColor());
        return t;
    }

    public MaterialInput helperText(CharSequence text) {
        view.setHelperText(text);
        return this;
    }

    public MaterialInput helperText(@StringRes int resId) {
        view.setHelperText(view.getContext().getString(resId));
        return this;
    }

    public MaterialInput helperTextColor(@ColorInt int color) {
        view.setHelperTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInput helperTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setHelperTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInput helperTextColor(ColorSelector selector) {
        view.setHelperTextColor(selector.getColor());
        return t;
    }

    public MaterialInput boxMode(int boxMode) {
        view.setBoxBackgroundMode(boxMode);
        return this;
    }

    public MaterialInput boxColor(@ColorInt int color) {
        view.setBoxBackgroundColor(color);
        return this;
    }

    public MaterialInput strokeWidth(int width) {
        view.setBoxStrokeWidth(width);
        return this;
    }

    public MaterialInput strokeColor(@ColorInt int color) {
        view.setBoxStrokeColor(color);
        return this;
    }

    public MaterialInput counter(int maxLength) {
        view.setCounterEnabled(true);
        view.setCounterMaxLength(maxLength);
        return this;
    }

    public MaterialInput counterTextColor(@ColorInt int color) {
        view.setCounterTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInput counterTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setCounterTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInput hint(CharSequence hint) {
        view.setHint(hint);
        return this;
    }

    public MaterialInput hint(@StringRes int resId) {
        view.setHint(resId);
        return this;
    }

    public MaterialInput hintTextColor(@ColorInt int color) {
        view.setHintTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInput hintTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setHintTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInput hintTextColor(ColorSelector selector) {
        view.setHintTextColor(selector.getColor());
        return t;
    }

    public MaterialInput child(com.kaiserdavar.androidui.MaterialInputEditText inputEditText) {
        addChild(inputEditText);
        return this;
    }

    public MaterialInput child(OnInputChildListener listener) {
        com.kaiserdavar.androidui.MaterialInputEditText inputEditText = newInputEditText();
        listener.onInputChild(inputEditText);
        addChild(inputEditText);
        return this;
    }

    private void addChild(com.kaiserdavar.androidui.MaterialInputEditText inputEditText) {
        this.inputEditText = inputEditText;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(inputEditText.marginLp);
        view.addView(inputEditText.view(), params);
    }

    private com.kaiserdavar.androidui.MaterialInputEditText newInputEditText() {
        return com.kaiserdavar.androidui.MaterialInputEditText.create(view.getContext()).fullWidth();
    }

    public interface OnInputChildListener {
        void onInputChild(com.kaiserdavar.androidui.MaterialInputEditText inputEditText);
    }

}
