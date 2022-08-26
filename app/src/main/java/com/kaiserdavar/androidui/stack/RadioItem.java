package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.widget.CompoundButton;

import androidx.appcompat.widget.AppCompatRadioButton;

import com.kaiserdavar.androidui.BaseText;

public class RadioItem extends BaseText<RadioItem, AppCompatRadioButton> {

    public static RadioItem create(Context context) {
        return new RadioItem(context);
    }

    public RadioItem(AppCompatRadioButton view) {
        super(view);
    }

    public RadioItem(Context context) {
        super(context);
    }

    @Override
    protected AppCompatRadioButton onGetMainView(Context context) {
        return new AppCompatRadioButton(context);
    }
    public RadioItem onToggle(CompoundButton.OnCheckedChangeListener listener) {
        view.setOnCheckedChangeListener(listener);
        return this;
    }
}
