package com.kaiserdavar.androidui;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.constraintlayout.utils.widget.ImageFilterView;

public class FilterImage extends BaseImage<FilterImage, ImageFilterView> {

    public FilterImage(ImageFilterView view) {
        super(view);
    }

    public FilterImage(Context context) {
        super(context);
    }

    public static FilterImage create(Context context) {
        return new FilterImage(context);
    }

    public static FilterImage create(Context context, @DrawableRes int imageResId) {
        FilterImage v = new FilterImage(context);
        if (imageResId != 0)
            v.imageRes(imageResId);
        return v;
    }

    public static FilterImage create(Context context, Drawable imageDrawable) {
        FilterImage v = new FilterImage(context);
        if (imageDrawable != null)
            v.imageDrawable(imageDrawable);
        return v;
    }

    public static FilterImage create(Context context, String imageUrl) {
        FilterImage v = new FilterImage(context);
        if (imageUrl != null)
            v.imageUrl(imageUrl);
        return v;
    }

    @Override
    protected ImageFilterView onGetMainView(Context context) {
        return new ImageFilterView(context);
    }

    public FilterImage altImage(@DrawableRes int resId) {
        view.setAltImageResource(resId);
        return this;
    }

    public FilterImage brightness(float value) {
        view.setBrightness(value);
        return this;
    }
    public FilterImage saturation(float value) {
        view.setSaturation(value);
        return this;
    }
    public FilterImage contrast(float value) {
        view.setContrast(value);
        return this;
    }
    public FilterImage warmth(float value) {
        view.setWarmth(value);
        return this;
    }
    public FilterImage crossFade(float value) {
        view.setCrossfade(value);
        return this;
    }
    public FilterImage panX(float value) {
        view.setImagePanX(value);
        return this;
    }
    public FilterImage panY(float value) {
        view.setImagePanY(value);
        return this;
    }
    public FilterImage zoom(float value) {
        view.setImageZoom(value);
        return this;
    }
    public FilterImage rotation(float value) {
        view.setImageRotate(value);
        return this;
    }
    public FilterImage round(float value) {
        view.setRound(value);
        return this;
    }
}
