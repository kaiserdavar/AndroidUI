package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.TypedValue;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kaiserdavar.androidui.style.TextStyle;
import com.kaiserdavar.androidui.style.VueStyle;
import com.kaiserdavar.androidui.util.NumberTextWatcher;
import com.kaiserdavar.androidui.util.OnEditTextChangeListener;

public class MaterialInputText extends BaseVue<MaterialInputText, TextInputLayout> {
    public static VueStyle defaultStyle;

    private TextInputEditText mEditText;
    private boolean isChanging;

    public static MaterialInputText create(Context context) {
        return new MaterialInputText(context);
    }

    public MaterialInputText(TextInputLayout view) {
        super(view);
    }

    public MaterialInputText(Context context) {
        super(context);
    }

    @Override
    protected TextInputLayout onGetMainView(Context context) {
        TextInputLayout v = new TextInputLayout(context);
        mEditText = new TextInputEditText(v.getContext());
        v.addView(mEditText);
        return v;
    }

    @Override
    protected void onInit() {
        if (defaultStyle != null) {
            t.style(defaultStyle);
        }
    }

    @Override
    public MaterialInputText style(VueStyle vueStyle) {
        if (vueStyle instanceof TextStyle) {
            TextStyle style = (TextStyle) vueStyle;
            if (style.text != null)
                text(style.text);
            if (style.textRes != null)
                text(style.textRes);
            if (style.textSize != null)
                textSize(style.textSizeUnit, style.textSize);
            if (style.textColor != null)
                textColor(style.textColor);
            if (style.textColorRes != null)
                textColorRes(style.textColorRes);
            if (style.textColorStateList != null)
                textColor(style.textColorStateList);
            if (style.fontRes != null)
                font(style.fontRes);
            if (style.gravity != null)
                textAlign(style.gravity);
            if (style.letterSpacing != null)
                mEditText.setLetterSpacing(style.letterSpacing);
            if (style.lineSpacing != null)
                mEditText.setLineSpacing(px(style.lineSpacing), 1.0f);
            if (style.drawablePadding != null)
                drawablePadding(style.drawablePadding);
            if (style.backgroundRes != null)
                mEditText.setBackgroundResource(style.backgroundRes);
            if (style.background != null)
                style.background.setAsBackgroundOf(view);
        }
        return this;
    }

    public MaterialInputText text(MutableLiveData<CharSequence> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, s -> changeText(s, null));
        mEditText.addTextChangedListener(new OnEditTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeText(charSequence, liveData);
            }
        });
        return this;
    }
    public MaterialInputText text(CharSequence text) {
        mEditText.setText(text);
        return this;
    }
    public MaterialInputText text(@StringRes int resId) {
        mEditText.setText(resId);
        return this;
    }

    public MaterialInputText textColor(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::textColor);
        return this;
    }
    public MaterialInputText textColor(int color) {
        mEditText.setTextColor(color);
        return this;
    }
    public MaterialInputText textColor(ColorStateList colors) {
        mEditText.setTextColor(colors);
        return this;
    }

    public MaterialInputText textColorRes(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::textColorRes);
        return this;
    }
    public MaterialInputText textColorRes(@ColorRes int resId) {
        mEditText.setTextColor(ContextCompat.getColor(view.getContext(), resId));
        return this;
    }

    public MaterialInputText textSize(LiveData<Float> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::textSize);
        return this;
    }
    public MaterialInputText textSize(float size) {
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return this;
    }
    public MaterialInputText textSize(int unit, float size) {
        mEditText.setTextSize(unit, size);
        return this;
    }

    public MaterialInputText font(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::font);
        return this;
    }
    public MaterialInputText font(@FontRes int fontResId) {
        view.setTypeface(ResourcesCompat.getFont(view.getContext(), fontResId));
        return this;
    }

    public MaterialInputText textAlign(int gravity) {
        mEditText.setGravity(gravity);
        return this;
    }

    public MaterialInputText textAlignment(int textAlignment) {
        mEditText.setTextAlignment(textAlignment);
        return this;
    }

    public MaterialInputText errorText(CharSequence text) {
        view.setError(text);
        return this;
    }

    public MaterialInputText errorText(int resId) {
        view.setError(view.getContext().getString(resId));
        return this;
    }

    public MaterialInputText errorIcon(@DrawableRes int resId) {
        view.setErrorIconDrawable(resId);
        return this;
    }

    public MaterialInputText errorTextColor(@ColorInt int color) {
        view.setErrorTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInputText errorTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setErrorTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInputText helperText(CharSequence text) {
        view.setHelperText(text);
        return this;
    }

    public MaterialInputText helperText(@StringRes int resId) {
        view.setHelperText(view.getContext().getString(resId));
        return this;
    }

    public MaterialInputText helperTextColor(@ColorInt int color) {
        view.setHelperTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInputText helperTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setHelperTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInputText boxMode(int boxMode) {
        view.setBoxBackgroundMode(boxMode);
        return this;
    }

    public MaterialInputText boxColor(@ColorInt int color) {
        view.setBoxBackgroundColor(color);
        return this;
    }

    public MaterialInputText strokeWidth(int width) {
        view.setBoxStrokeWidth(width);
        return this;
    }

    public MaterialInputText strokeColor(@ColorInt int color) {
        view.setBoxStrokeColor(color);
        return this;
    }

    public MaterialInputText counter(int maxLength) {
        view.setCounterEnabled(true);
        view.setCounterMaxLength(maxLength);
        return this;
    }

    public MaterialInputText counterTextColor(@ColorInt int color) {
        view.setCounterTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInputText counterTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setCounterTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInputText hint(CharSequence hint) {
        view.setHint(hint);
        return this;
    }

    public MaterialInputText hint(@StringRes int resId) {
        view.setHint(resId);
        return this;
    }

    public MaterialInputText hintTextColor(@ColorInt int color) {
        view.setHintTextColor(ColorStateList.valueOf(color));
        mEditText.setHintTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInputText hintTextColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setHintTextColor(ColorStateList.valueOf(color));
        mEditText.setHintTextColor(ColorStateList.valueOf(color));
        return this;
    }

    public MaterialInputText inputType(int inputType) {
        mEditText.setInputType(inputType);
        return this;
    }

    public MaterialInputText maxLength(int max) {
        InputFilter[] filter = new InputFilter[] {new InputFilter.LengthFilter(max)};
        mEditText.setFilters(filter);
        return this;
    }

    public MaterialInputText groupedNumberInput() {
        mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        mEditText.addTextChangedListener(new NumberTextWatcher(mEditText));
        return this;
    }

    public MaterialInputText onFocus(com.kaiserdavar.androidui.OnFocusListener listener) {
        mEditText.setOnFocusChangeListener((view1, b) -> listener.onFocus(b));
        return this;
    }

    public MaterialInputText onText(com.kaiserdavar.androidui.OnTextListener listener) {
        mEditText.addTextChangedListener(new OnEditTextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.onText(charSequence);
            }
        });
        return this;
    }

    public MaterialInputText onText(OnEditTextChangeListener listener) {
        mEditText.addTextChangedListener(listener);
        return this;
    }

    public MaterialInputText onText(TextWatcher listener) {
        mEditText.addTextChangedListener(listener);
        return this;
    }

    public MaterialInputText drawableTop(@DrawableRes int resId) {
        mEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, resId, 0, 0);
        return this;
    }
    public MaterialInputText drawableBottom(@DrawableRes int resId) {
        mEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, resId);
        return this;
    }
    public MaterialInputText drawableStart(@DrawableRes int resId) {
        mEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(resId, 0, 0, 0);
        return this;
    }
    public MaterialInputText drawableEnd(@DrawableRes int resId) {
        mEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, resId, 0);
        return this;
    }
    public MaterialInputText noDrawable() {
        mEditText.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        return this;
    }
    public MaterialInputText drawablePadding(int paddingDp) {
        mEditText.setCompoundDrawablePadding(px(paddingDp));
        return this;
    }

    private void changeText(CharSequence text, MutableLiveData<CharSequence> liveData) {
        if (isChanging)
            return;
        isChanging = true;
        if (liveData != null)
            liveData.setValue(text);
        else
            mEditText.setText(text);
        isChanging = false;
    }
}
