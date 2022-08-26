package com.kaiserdavar.androidui;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.Gravity;
import android.widget.SeekBar;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kaiserdavar.androidui.util.Shaper;

public class Seeker extends BaseVue<Seeker, SeekBar> implements SeekBar.OnSeekBarChangeListener {

    private OnProgressListener onProgressListener;
    private MutableLiveData<Integer> progressLiveData;
    private boolean isChanging;

    private Drawable backDrawable;
    private Drawable pDrawable;

    public static Seeker create(Context context) {
        return new Seeker(context);
    }

    public Seeker(SeekBar view) {
        super(view);
    }

    public Seeker(Context context) {
        super(context);
    }

    @Override
    protected SeekBar onGetMainView(Context context) {
        SeekBar v = new SeekBar(context);
        v.setOnSeekBarChangeListener(this);
        return v;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        changeProgress(i, progressLiveData);
        if (onProgressListener != null)
            onProgressListener.onProgress(i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public Seeker progress(MutableLiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        this.progressLiveData = liveData;
        liveData.observe(lifecycleOwner, this::progress);
        return this;
    }
    public Seeker progress(int progress) {
        changeProgress(progress, null);
        return this;
    }

    public Seeker max(LiveData<Integer> colorLiveData, LifecycleOwner lifecycleOwner) {
        colorLiveData.observe(lifecycleOwner, this::max);
        return this;
    }
    public Seeker max(int max) {
        view.setMax(max);
        return this;
    }

    public Seeker onProgress(OnProgressListener listener) {
        this.onProgressListener = listener;
        return this;
    }

    public Seeker progressDrawable(Drawable d) {
        view.setProgressDrawable(d);
        return this;
    }

    public Seeker progressBackgroundShape(OnBackgroundListener listener) {
        Shaper back = new Shaper();
        listener.onBackground(back);
        backDrawable = back.generate();
        updateProgressDrawable();
        return this;
    }
    public Seeker progressShape(OnBackgroundListener listener) {
        Shaper back = new Shaper();
        listener.onBackground(back);
        pDrawable = new ClipDrawable(back.generate(), Gravity.LEFT, ClipDrawable.HORIZONTAL);
        updateProgressDrawable();
        return this;
    }

    public Seeker thumbShape(OnBackgroundListener listener) {
        Shaper back = new Shaper();
        listener.onBackground(back);
        view.setThumb(back.generate());
        return this;
    }

    public Seeker thumbOffset(int offset) {
        view.setThumbOffset(offset);
        return this;
    }

    private void updateProgressDrawable() {
        LayerDrawable layer = new LayerDrawable(new Drawable[]{backDrawable, pDrawable});
        layer.setId(0, android.R.id.background);
        layer.setId(1, android.R.id.progress);
        view.setProgressDrawable(layer);
    }

    private void changeProgress(int progress, MutableLiveData<Integer> liveData) {
        if (isChanging)
            return;
        isChanging = true;
        if (liveData != null)
            liveData.setValue(progress);
        else
            view.setProgress(progress);
        isChanging = false;
    }

}
