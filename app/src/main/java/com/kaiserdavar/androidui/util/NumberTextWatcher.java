package com.kaiserdavar.androidui.util;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberTextWatcher implements TextWatcher {

    private EditText editText;
    private NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

    public NumberTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String str;
        String cleanString;
        long parsed;

        str = s.toString();
        if (TextUtils.isEmpty(str)) {
            parsed = -1;
            onNumber(null);
            onFormattedNumber("");
        } else {
            editText.removeTextChangedListener(this);
            cleanString = s.toString().replaceAll("[$,٬.]", "");

            Number n = null;
            try {
                n = numberFormat.parse(cleanString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (n != null)
                parsed = n.longValue();
            else
                parsed = 0;

            onNumber(parsed);
            String formatted = numberFormat.format(parsed);
            onFormattedNumber(formatted);

            editText.setText(formatted);
            editText.setSelection(formatted.length());
            editText.addTextChangedListener(this);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void onNumber(Long number) {

    }
    public void onFormattedNumber(String formattedNumber) {

    }
}
