package com.kaiserdavar.androidui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

public class BaseImage<T, M extends ImageView> extends BaseVue<T, M> {

    private OnGlideRequestListener onGlideRequestListener;
    private Transformation<Bitmap> scaleTransformation;
    private Transformation<Bitmap> cornerTransformation;

    private int corner;
    private boolean round;

    public BaseImage(M view) {
        super(view);
    }

    public BaseImage(Context context) {
        super(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected M onGetMainView(Context context) {
        return (M) new ImageView(context);
    }

    public T scaleType(ImageView.ScaleType scaleType) {
        view.setScaleType(scaleType);
        return t;
    }

    public T imageRes(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        return imageRes(liveData, false, lifecycleOwner);
    }
    public T imageRes(LiveData<Integer> liveData, boolean loadWithGlide, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, resId -> imageRes(resId, loadWithGlide));
        return t;
    }
    public T imageRes(@DrawableRes int resId) {
        return imageRes(resId, false);
    }
    public T imageRes(@DrawableRes int resId, boolean loadWithGlide) {
        if (loadWithGlide) {
            RequestBuilder<Drawable> requestBuilder = Glide.with(view).load(resId);
            loadImage(requestBuilder);
        } else {
            if (corner > 0 || round) {
                Bitmap src = BitmapFactory.decodeResource(view.getResources(), resId);
                RoundedBitmapDrawable roundedDrawable = getRoundedImage(src);
                view.setImageDrawable(roundedDrawable);
            } else {
                view.setImageResource(resId);
            }
        }
        return t;
    }
    public T imageDrawable(LiveData<Drawable> liveData, LifecycleOwner lifecycleOwner) {
        return imageDrawable(liveData, false, lifecycleOwner);
    }
    public T imageDrawable(LiveData<Drawable> liveData, boolean loadWithGlide, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, drawable -> imageDrawable(drawable, loadWithGlide));
        return t;
    }
    public T imageDrawable(Drawable drawable) {
        return imageDrawable(drawable, false);
    }
    public T imageDrawable(Drawable drawable, boolean loadWithGlide) {
        if (loadWithGlide) {
            RequestBuilder<Drawable> requestBuilder = Glide.with(view).load(drawable);
            loadImage(requestBuilder);
        } else {
            if (corner > 0 || round) {
                Bitmap src = drawableToBitmap(drawable);
                RoundedBitmapDrawable roundedDrawable = getRoundedImage(src);
                view.setImageDrawable(roundedDrawable);
            } else {
                view.setImageDrawable(drawable);
            }
        }
        return t;
    }
    public T gifRes(@DrawableRes int resId, int loopCount) {
        RequestBuilder<GifDrawable> requestBuilder = Glide.with(view).asGif().load(resId);
        requestBuilder.listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                        Target<GifDrawable> target,
                                        boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model,
                                           Target<GifDrawable> target,
                                           DataSource dataSource,
                                           boolean isFirstResource) {
                resource.setLoopCount(loopCount);
                return false;
            }
        }).into(view);
        return t;
    }

    public T imageFile(LiveData<File> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::imageFile);
        return t;
    }
    public T imageFile(File file) {
        RequestBuilder<Drawable> requestBuilder = Glide.with(view).load(file);
        loadImage(requestBuilder);
        return t;
    }

    public T imageUri(LiveData<Uri> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::imageUri);
        return t;
    }
    public T imageUri(Uri uri) {
        RequestBuilder<Drawable> requestBuilder = Glide.with(view).load(uri);
        loadImage(requestBuilder);
        return t;
    }

    public T imageUrl(LiveData<String> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::imageUrl);
        return t;
    }
    public T imageUrl(String url) {
        RequestBuilder<Drawable> requestBuilder = Glide.with(view).load(url);
        loadImage(requestBuilder);
        return t;
    }

    public T onLoad(OnGlideRequestListener listener) {
        this.onGlideRequestListener = listener;
        return t;
    }

    public T corner(int dp) {
        corner = px(dp);
        cornerTransformation = new RoundedCorners(px(dp));
        return t;
    }
    public T round() {
        round = true;
        cornerTransformation = new CircleCrop();
        return t;
    }
    public T centerCrop() {
        scaleTransformation = new CenterCrop();
        return scaleType(ImageView.ScaleType.CENTER_CROP);
    }
    public T centerInside() {
        scaleTransformation = new CenterInside();
        return scaleType(ImageView.ScaleType.CENTER_INSIDE);
    }
    public T fitCenter() {
        scaleTransformation = new FitCenter();
        return scaleType(ImageView.ScaleType.FIT_CENTER);
    }
    public T fitStart() {
        return scaleType(ImageView.ScaleType.FIT_START);
    }
    public T fitEnd() {
        return scaleType(ImageView.ScaleType.FIT_END);
    }
    public T fitXY() {
        return scaleType(ImageView.ScaleType.FIT_XY);
    }
    public T matrix() {
        return scaleType(ImageView.ScaleType.MATRIX);
    }

    private void loadImage(RequestBuilder<Drawable> requestBuilder) {
        if (scaleTransformation != null && cornerTransformation != null)
            requestBuilder = requestBuilder.transform(scaleTransformation, cornerTransformation);
        else if (scaleTransformation != null) {
            requestBuilder = requestBuilder.transform(scaleTransformation);
        } else if (cornerTransformation != null) {
            requestBuilder = requestBuilder.transform(cornerTransformation);
        }
        if (onGlideRequestListener != null)
            onGlideRequestListener.onRequest(requestBuilder);
        requestBuilder.into(view);
    }

    private RoundedBitmapDrawable getRoundedImage(Bitmap src) {
        Resources res = view.getResources();
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(res, src);
        if (round)
            roundedDrawable.setCircular(true);
        else
            roundedDrawable.setCornerRadius(corner);
        return roundedDrawable;
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            // Single color bitmap will be created of 1x1 pixel
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public interface OnGlideRequestListener {
        void onRequest(RequestBuilder<Drawable> request);
    }
}
