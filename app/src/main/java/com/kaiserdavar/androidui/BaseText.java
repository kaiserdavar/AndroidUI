package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.TextViewCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kaiserdavar.androidui.style.ColorSelector;
import com.kaiserdavar.androidui.style.TextStyle;
import com.kaiserdavar.androidui.style.VueStyle;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class BaseText<T, M extends TextView> extends BaseVue<T, M> {

    public BaseText(M view) {
        super(view);
    }

    public BaseText(Context context) {
        super(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected M onGetMainView(Context context) {
        return (M) new TextView(context);
    }

    @Override
    public T style(VueStyle vueStyle) {
        super.style(vueStyle);
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
                letterSpacing(style.letterSpacing);
            if (style.lineSpacing != null)
                lineSpacing(style.lineSpacing);
            if (style.drawablePadding != null)
                drawablePadding(style.drawablePadding);
        }
        return t;
    }

    public T text(LiveData<CharSequence> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::text);
        return t;
    }
    public T text(MutableLiveData<CharSequence> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::text);
        return t;
    }
    public T text(CharSequence text) {
        view.setText(text);
        return t;
    }
    public T text(@StringRes int resId) {
        view.setText(resId);
        return t;
    }

    public T textColor(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::textColor);
        return t;
    }
    public T textColor(int color) {
        view.setTextColor(color);
        return t;
    }
    public T textColor(ColorSelector selector) {
        return textColor(selector.getColor());
    }
    public T textColor(ColorStateList colors) {
        view.setTextColor(colors);
        return t;
    }

    public T textColorRes(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::textColorRes);
        return t;
    }
    public T textColorRes(@ColorRes int resId) {
        view.setTextColor(ContextCompat.getColor(view.getContext(), resId));
        return t;
    }

    public T textSize(LiveData<Float> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::textSize);
        return t;
    }
    public T textSize(float size) {
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        return t;
    }
    public T textSize(int unit, float size) {
        view.setTextSize(unit, size);
        return t;
    }

    public T font(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::font);
        return t;
    }
    public T font(@FontRes int fontResId) {
        view.setTypeface(ResourcesCompat.getFont(view.getContext(), fontResId));
        return t;
    }

    public T textAlign(int gravity) {
        view.setGravity(gravity);
        return t;
    }
    public T textDirection(int textDirection) {
        view.setTextDirection(textDirection);
        return t;
    }
    public T lines(int count) {
        view.setLines(count);
        return t;
    }
    public T lines(int min, int max) {
        view.setMinLines(min);
        view.setMaxLines(max);
        return t;
    }
    public T letterSpacing(float space) {
        view.setLetterSpacing(space);
        return t;
    }
    public T lineSpacing(int dp) {
        view.setLineSpacing(px(dp), 1.0f);
        return t;
    }
    public T ems(int ems) {
        view.setEms(ems);
        return t;
    }
    public T ems(int min, int max) {
        view.setMinEms(min);
        view.setMaxEms(max);
        return t;
    }
    public T ellipsize(TextUtils.TruncateAt where) {
        view.setEllipsize(where);
        return t;
    }
    public T allCaps() {
        view.setAllCaps(true);
        return t;
    }
    public T allCaps(boolean allCaps) {
        view.setAllCaps(allCaps);
        return t;
    }
    public T error(CharSequence text) {
        view.setError(text);
        return t;
    }
    public T hint(CharSequence text) {
        view.setHint(text);
        return t;
    }
    public T hint(@StringRes int textRes) {
        view.setHint(textRes);
        return t;
    }
    public T hintColor(@ColorInt int color) {
        view.setHintTextColor(ColorStateList.valueOf(color));
        return t;
    }
    public T hintColor(ColorSelector selector) {
        view.setHintTextColor(selector.getColor());
        return t;
    }
    public T hintColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setHintTextColor(ColorStateList.valueOf(color));
        return t;
    }
    public T linkColor(@ColorInt int color) {
        view.setLinkTextColor(ColorStateList.valueOf(color));
        return t;
    }
    public T linkColor(ColorSelector selector) {
        view.setLinkTextColor(selector.getColor());
        return t;
    }
    public T linkColorRes(@ColorRes int colorRes) {
        int color = ContextCompat.getColor(view.getContext(), colorRes);
        view.setLinkTextColor(color);
        return t;
    }
    public T linkMask(int linkifyMask) {
        view.setAutoLinkMask(linkifyMask);
        return t;
    }

    public T imeOptions(int editorInfoAction) {
        //set single line
        view.setImeOptions(editorInfoAction);
        return t;
    }
    public T imeActionLabel(CharSequence label, int actionId) {
        view.setImeActionLabel(label, actionId);
        return t;
    }

    public T scrollHorizontally() {
        view.setHorizontallyScrolling(true);
        return t;
    }
    public T textLocale(Locale locale) {
        view.setTextLocale(locale);
        return t;
    }
    public T justify() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            view.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        return t;
    }
    public T shadow(float radius, int dx, int dy, int color) {
        view.setShadowLayer(radius, dx, dy, color);
        return t;
    }
    public T movementMethod(MovementMethod method) {
        view.setMovementMethod(method);
        return t;
    }
    public T span(int startIndex, int endIndex, @ColorRes int colorRes, OnClickListener listener) {
        if (view.getText().length() <= endIndex)
            return t;
        Spannable span = new SpannableString(view.getText());
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NotNull View widget) {
                listener.onClick();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ContextCompat.getColor(view.getContext(), colorRes));
            }
        }, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setText(span);
        return t;
    }

    public T span(Object span, int startIndex, int endIndex) {
        SpannableString string = new SpannableString(view.getText());
        string.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(string);
        return t;
    }

    public T inputType(int inputType) {
        view.setInputType(inputType);
        return t;
    }
    public T textInput() {
        view.setInputType(InputType.TYPE_CLASS_TEXT);
        return t;
    }
    public T numberInput() {
        view.setInputType(InputType.TYPE_CLASS_NUMBER);
        return t;
    }
    public T phoneInput() {
        view.setInputType(InputType.TYPE_CLASS_PHONE);
        return t;
    }
    public T dateTimeInput() {
        view.setInputType(InputType.TYPE_CLASS_DATETIME);
        return t;
    }
    public T dateInput() {
        view.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
        return t;
    }
    public T timeInput() {
        view.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_TIME);
        return t;
    }
    public T emailInput() {
        view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        return t;
    }
    public T textPasswordInput() {
        view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        return t;
    }
    public T textMultilineInput() {
        view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        return t;
    }
    public T numberPasswordInput() {
        view.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        return t;
    }
    public T numberDecimalInput() {
        view.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        return t;
    }
    public T numberSignedInput() {
        view.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
        return t;
    }


    public T maxLength(int max) {
        addFilter(new InputFilter.LengthFilter(max));
        return t;
    }
    public T inputFilter(InputFilter filter) {
        addFilter(filter);
        return t;
    }


    protected void addFilter(InputFilter filter) {
        List<InputFilter> filterList = new ArrayList<>();
        if (view.getFilters() != null) {
            filterList.addAll(Arrays.asList(view.getFilters()));
        }
        filterList.add(filter);
        view.setFilters(filterList.toArray(new InputFilter[0]));
    }

    public T drawableTop(@DrawableRes int resId) {
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, resId, 0, 0);
        return t;
    }
    public T drawableBottom(@DrawableRes int resId) {
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, resId);
        return t;
    }
    public T drawableStart(@DrawableRes int resId) {
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(resId, 0, 0, 0);
        return t;
    }
    public T drawableEnd(@DrawableRes int resId) {
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, resId, 0);
        return t;
    }
    public T noDrawable() {
        view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
        return t;
    }
    public T drawablePadding(int paddingDp) {
        view.setCompoundDrawablePadding(px(paddingDp));
        return t;
    }
    public T drawableTintMode(PorterDuff.Mode tintMode) {
        TextViewCompat.setCompoundDrawableTintMode(view, tintMode);
        return t;
    }
    public T drawableTint(ColorSelector selector) {
        TextViewCompat.setCompoundDrawableTintList(view, selector.getColor());
        return t;
    }
}
