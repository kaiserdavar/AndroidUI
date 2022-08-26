package com.kaiserdavar.androidui;

import android.content.Context;
import android.widget.ProgressBar;

public class Progress extends BaseVue<Progress, ProgressBar> {
    public Progress(ProgressBar view) {
        super(view);
    }

    public Progress(Context context) {
        super(context);
    }

    public static Progress create(Context context) {
        return new Progress(context);
    }

    @Override
    protected ProgressBar onGetMainView(Context context) {
        return new ProgressBar(context);
    }

    public Progress progressColor(int color) {
        view.getIndeterminateDrawable().setColorFilter(
                color,
                android.graphics.PorterDuff.Mode.SRC_IN);
        return this;
    }
}
