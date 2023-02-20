package com.kaiserdavar.androidui;

import android.content.Context;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatTextView;
import com.kaiserdavar.androidui.style.VueStyle;

public class Text extends BaseText<Text, AppCompatTextView> {
    public static VueStyle defaultStyle;

    public static Text create(Context context) {
        return new Text(context);
    }

    public Text(AppCompatTextView textView) {
        super(textView);
    }

    public Text(Context context) {
        super(context);
    }

    public static Text create(Context context, CharSequence text) {
        Text v = new Text(context);
        if (text != null) v.text(text);
        return v;
    }

    public static Text create(Context context, @StringRes int resId) {
        Text v = new Text(context);
        if (resId != 0) v.text(resId);
        return v;
    }

    @Override
    protected AppCompatTextView onGetMainView(Context context) {
        return new AppCompatTextView(context);
    }

    @Override
    protected void onInit() {
        if (defaultStyle != null) {
            t.style(defaultStyle);
        }
    }

}
