package com.kaiserdavar.androidui;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.widget.ProgressBar;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.kaiserdavar.androidui.util.Shaper;

public class HProgress extends BaseVue<HProgress, ProgressBar> {

    private Drawable backDrawable;
    private Drawable p1Drawable;
    private Drawable p2Drawable;

    public static HProgress create(Context context) {
        return new HProgress(context);
    }

    public HProgress(ProgressBar view) {
        super(view);
    }

    public HProgress(Context context) {
        super(context);
    }

    @Override
    protected ProgressBar onGetMainView(Context context) {
        return new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
    }

    public HProgress progress(LiveData<Integer> colorLiveData, LifecycleOwner lifecycleOwner) {
        colorLiveData.observe(lifecycleOwner, this::progress);
        return this;
    }
    public HProgress progress(int progress) {
        view.setProgress(progress);
        return this;
    }

    public HProgress secondProgress(LiveData<Integer> colorLiveData, LifecycleOwner lifecycleOwner) {
        colorLiveData.observe(lifecycleOwner, this::secondProgress);
        return this;
    }
    public HProgress secondProgress(int progress) {
        view.setSecondaryProgress(progress);
        return this;
    }

    public HProgress max(LiveData<Integer> colorLiveData, LifecycleOwner lifecycleOwner) {
        colorLiveData.observe(lifecycleOwner, this::max);
        return this;
    }
    public HProgress max(int max) {
        view.setMax(max);
        return this;
    }

    public HProgress indeterminate(boolean indeterminate) {
        view.setIndeterminate(indeterminate);
        return this;
    }

    public HProgress progressDrawable(Drawable d) {
        view.setProgressDrawable(d);
        return this;
    }

    public HProgress progressBackgroundShape(OnBackgroundListener listener) {
        Shaper back = new Shaper();
        listener.onBackground(back);
        backDrawable = back.generate();
        updateProgressDrawable();
        return this;
    }
    public HProgress progressShape(OnBackgroundListener listener) {
        Shaper back = new Shaper();
        listener.onBackground(back);
        p1Drawable = new ClipDrawable(back.generate(), Gravity.LEFT, ClipDrawable.HORIZONTAL);
        updateProgressDrawable();
        return this;
    }
    public HProgress secondProgressShape(OnBackgroundListener listener) {
        Shaper back = new Shaper();
        listener.onBackground(back);
        p2Drawable = new ClipDrawable(back.generate(), Gravity.LEFT, ClipDrawable.HORIZONTAL);
        updateProgressDrawable();
        return this;
    }

    private void updateProgressDrawable() {
        LayerDrawable layer = new LayerDrawable(new Drawable[]{backDrawable, p2Drawable, p1Drawable});
        layer.setId(0, android.R.id.background);
        layer.setId(1, android.R.id.secondaryProgress);
        layer.setId(2, android.R.id.progress);
        view.setProgressDrawable(layer);
    }


}
