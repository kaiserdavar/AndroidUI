package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.annotation.ColorRes;

import com.google.android.material.chip.Chip;
import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.OnClickListener;

public class ChipItem extends BaseVue<ChipItem, Chip> {

    public static ChipItem create(Context context) {
        return new ChipItem(context);
    }

    public ChipItem(Chip view) {
        super(view);
    }

    public ChipItem(Context context) {
        super(context);
    }

    @Override
    protected Chip onGetMainView(Context context) {
        Chip v = new Chip(context);
        return v;
    }

    public ChipItem text(CharSequence text) {
        view.setText(text);
        return this;
    }
    public ChipItem text(int resId) {
        view.setText(resId);
        return this;
    }
    public ChipItem color(int resId) {
        view.setChipBackgroundColorResource(resId);
        return this;
    }
    public ChipItem icon(int resId) {
        view.setChipIconResource(resId);
        return this;
    }
    public ChipItem closeIcon(int resId) {
        view.setCloseIconResource(resId);
        return this;
    }
    public ChipItem checkedIcon(int resId) {
        view.setCheckedIconResource(resId);
        return this;
    }
    public ChipItem iconSize(int value) {
        view.setChipIconSize(px(value));
        return this;
    }
    public ChipItem closeIconSize(int value) {
        view.setCloseIconSize(px(value));
        return this;
    }
    public ChipItem minHeight(int value) {
        view.setChipMinHeight(px(value));
        return this;
    }
    public ChipItem textPadding(int start, int end) {
        view.setTextStartPadding(px(start));
        view.setTextEndPadding(px(end));
        return this;
    }
    public ChipItem iconPadding(int start, int end) {
        view.setIconStartPadding(px(start));
        view.setIconEndPadding(px(end));
        return this;
    }
    public ChipItem checkable() {
        view.setCheckable(true);
        return this;
    }
    public ChipItem checkable(boolean value) {
        view.setCheckable(value);
        return this;
    }
    public ChipItem checked() {
        view.setChecked(true);
        return this;
    }
    public ChipItem checked(boolean value) {
        view.setChecked(value);
        return this;
    }
    public ChipItem closeVisible() {
        view.setCloseIconVisible(true);
        return this;
    }
    public ChipItem closeVisible(boolean value) {
        view.setCloseIconVisible(value);
        return this;
    }
    public ChipItem onClose(OnClickListener listener) {
        view.setOnCloseIconClickListener(view1 -> {
            listener.onClick();
        });
        return this;
    }
    public ChipItem strokeWidth(int dp) {
        view.setChipStrokeWidth(px(dp));
        return this;
    }
    public ChipItem strokeColor(@ColorRes int resId) {
        view.setChipStrokeColorResource(resId);
        return this;
    }
}
