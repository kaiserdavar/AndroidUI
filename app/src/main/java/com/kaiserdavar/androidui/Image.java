package com.kaiserdavar.androidui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

public class Image extends com.kaiserdavar.androidui.BaseImage<Image, ImageView> {

    public Image(ImageView view) {
        super(view);
    }

    public Image(Context context) {
        super(context);
    }

    public static Image create(Context context) {
        return new Image(context);
    }

    public static Image create(Context context, @DrawableRes int imageResId) {
        Image v = new Image(context);
        if (imageResId != 0)
            v.imageRes(imageResId);
        return v;
    }

    public static Image create(Context context, Drawable imageDrawable) {
        Image v = new Image(context);
        if (imageDrawable != null)
            v.imageDrawable(imageDrawable);
        return v;
    }

    public static Image create(Context context, String imageUrl) {
        Image v = new Image(context);
        if (imageUrl != null)
            v.imageUrl(imageUrl);
        return v;
    }

    @Override
    protected ImageView onGetMainView(Context context) {
        return new ImageView(context);
    }

}
