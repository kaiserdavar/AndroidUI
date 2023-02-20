package com.kaiserdavar.androidui.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.kaiserdavar.androidui.style.ColorSelector;

import java.util.ArrayList;
import java.util.List;

public class Shaper {

    private Context mContext;
    private int mBackgroundColor;
    private int mShadowColor;
    private int mStrokeColor;
    private int mRippleColor;
    private int mStrokeWidth;
    private int mStrokeDashWidth;
    private int mStrokeDashGap;
    private int mShadowRadius;
    private int mShadowOffsetX;
    private int mShadowOffsetY;
    private GradientDrawable.Orientation mGradientOrientation;
    private int mGradientType;
    private float mGradientRadius;
    private int[] mGradientColors;
    private final int[] mCorners;
    private final int[] mPadding;
    private int mGravity = Gravity.NO_GRAVITY;
    private int mWidth;
    private int mHeight;
    private boolean withRippleEffect;
    private boolean setAsMask;
    private ShapeDrawable mShapeDrawable;
    private int mShape;
    private ColorStateList mBackgroundColorStateList;
    private ColorStateList mStrokeColorStateList;
    private ColorStateList mRippleColorStateList;
    private final List<Shaper> mLayers;

    public Shaper() {
        this(null);
    }
    public Shaper(Context context) {
        if (context != null)
            mContext = context.getApplicationContext();
        this.mBackgroundColor = Color.WHITE;
        this.mGradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
        this.mGradientType = GradientDrawable.LINEAR_GRADIENT;
        this.mShadowColor = Color.parseColor("#505f5f5f");
        this.mRippleColor = Color.parseColor("#27000000");
        this.mShape = GradientDrawable.RECTANGLE;
        this.mLayers = new ArrayList<>();
        this.mPadding = new int[4];
        this.mCorners = new int[4];
    }

    public Drawable generate() {
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(mShape);
        if (mBackgroundColorStateList != null)
            gd.setColor(mBackgroundColorStateList);
        else
            gd.setColor(mBackgroundColor);
        if (mStrokeWidth > 0) {
            if (mStrokeDashWidth > 0 && mStrokeDashGap > 0) {
                if (mStrokeColorStateList != null)
                    gd.setStroke(mStrokeWidth, mStrokeColorStateList, mStrokeDashWidth, mStrokeDashGap);
                else
                    gd.setStroke(mStrokeWidth, mStrokeColor, mStrokeDashWidth, mStrokeDashGap);
            } else {
                if (mStrokeColorStateList != null)
                    gd.setStroke(mStrokeWidth, mStrokeColorStateList);
                else
                    gd.setStroke(mStrokeWidth, mStrokeColor);
            }
        }
        if (mGradientColors != null) {
            gd.setColors(mGradientColors);
            gd.setGradientType(mGradientType);
            gd.setGradientRadius(mGradientRadius);
            gd.setOrientation(mGradientOrientation);
        }

        float[] outerRadius = {mCorners[0], mCorners[0],
                mCorners[1], mCorners[1],
                mCorners[2], mCorners[2],
                mCorners[3], mCorners[3]};
        gd.setCornerRadii(outerRadius);

        List<Drawable> layers = new ArrayList<>();

        ShapeDrawable shadowDrawable = null;
        if (mShadowRadius > 0) {
            shadowDrawable = new ShapeDrawable();
            shadowDrawable.getPaint().setColor(mBackgroundColor);
            shadowDrawable.getPaint().setAntiAlias(true);
            shadowDrawable.getPaint().setShadowLayer(mShadowRadius, mShadowOffsetX, mShadowOffsetY, mShadowColor);

            int extraCorner = mStrokeWidth / 2;
            float[] radii = {mCorners[0] + extraCorner, mCorners[0] + extraCorner,
                    mCorners[1] + extraCorner, mCorners[1] + extraCorner,
                    mCorners[2] + extraCorner, mCorners[2] + extraCorner,
                    mCorners[3] + extraCorner, mCorners[3] + extraCorner};

            if (mShape == GradientDrawable.RECTANGLE)
                shadowDrawable.setShape(new RoundRectShape(radii, null, null));
            else if (mShape == GradientDrawable.OVAL)
                shadowDrawable.setShape(new OvalShape());
            mShapeDrawable = shadowDrawable;
            layers.add(mShapeDrawable);
        }
        layers.add(gd);
        if (mLayers != null) {
            for (int i = 0; i < mLayers.size(); i++) {
                layers.add(mLayers.get(i).generate());
            }
        }

        LayerDrawable drawable = new LayerDrawable(layers.toArray(new Drawable[0]));
        int[] inset = new int[4];
        inset[0] = mPadding[0] - mShadowOffsetX;
        inset[1] = mPadding[1] - mShadowOffsetY;
        inset[2] = mPadding[2] + mShadowOffsetX;
        inset[3] = mPadding[3] + mShadowOffsetY;
        drawable.setLayerInset(0, inset[0], inset[1], inset[2], inset[3]);
        if (shadowDrawable != null) {
            drawable.setLayerInset(1, inset[0], inset[1], inset[2], inset[3]);
        }
        if (mWidth > 0 || mHeight > 0) {
            //drawable.setBounds(0, 0, mWidth, mHeight);
            gd.setSize(mWidth, mHeight);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (int i = 0; i < layers.size() - mLayers.size(); i++) {
                    drawable.setLayerGravity(i, mGravity);
                    if (mWidth > 0)
                        drawable.setLayerWidth(i, mWidth);
                    if (mHeight > 0)
                        drawable.setLayerHeight(i, mHeight);
                }
            }
        }

        if (withRippleEffect) {
            //int[][] states = new int[][] {new int[] { android.R.attr.state_enabled}};
            //int[] colors = new int[] {mRippleColor};
            //ColorStateList colorStates = new ColorStateList(states, colors);
            ColorStateList colorStateList;
            if (mRippleColorStateList != null)
                colorStateList = mRippleColorStateList;
            else
                colorStateList = ColorSelector.create(null).enabled(mRippleColor).getColor();

            if (setAsMask) {
                return new RippleDrawable(colorStateList, null, drawable);
            } else {
                return new RippleDrawable(colorStateList, drawable, null);
            }
        }
        return drawable;
    }

    public void setAsBackgroundOf(View view) {
        Drawable shadow = generate();
        checkLayerType(view);
        ViewCompat.setBackground(view, shadow);
    }

    private void checkLayerType(View view) {
        if (Build.VERSION.SDK_INT < 28 && mShadowRadius > 0)
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, mShapeDrawable.getPaint());
    }

    private void setBackgroundColor(int color) {
        mBackgroundColorStateList = null;
        mBackgroundColor = color;
    }
    private void setStrokeColor(int color) {
        mStrokeColorStateList = null;
        mStrokeColor = color;
    }
    private void setRippleColor(int color) {
        mRippleColorStateList = null;
        mRippleColor = color;
    }

    public int getShadowRadius() {
        return mShadowRadius;
    }

    // Builder methods

    public Shaper backgroundColor(@ColorInt int color) {
        setBackgroundColor(color);
        return this;
    }

    public Shaper backgroundColor(Context context, @ColorRes int color) {
        setBackgroundColor(ContextCompat.getColor(context, color));
        return this;
    }

    public Shaper backgroundColorRes(@ColorRes int resId) {
        setBackgroundColor(ContextCompat.getColor(mContext, resId));
        return this;
    }

    public Shaper backgroundColor(ColorSelector selector) {
        mBackgroundColorStateList = selector.getColor();
        return this;
    }

    public Shaper backgroundColor(ColorStateList colorStateList) {
        mBackgroundColorStateList = colorStateList;
        return this;
    }

    public Shaper noBackground() {
        setBackgroundColor(Color.TRANSPARENT);
        return this;
    }

    public Shaper shadowRadius(int dp) {
        mShadowRadius = px(dp);
        padding(dp + (dp / 2));
        return this;
    }

    public Shaper shadowColor(@ColorInt int color) {
        mShadowColor = color;
        return this;
    }

    public Shaper shadowColor(Context context, @ColorRes int color) {
        mShadowColor = ContextCompat.getColor(context, color);
        return this;
    }

    public Shaper shadowColorRes(@ColorRes int resId) {
        mShadowColor = ContextCompat.getColor(mContext, resId);
        return this;
    }

    public Shaper shadowOffset(int dxDp, int dyDp) {
        mShadowOffsetX = px(dxDp);
        mShadowOffsetY = px(dyDp);
        return this;
    }

    public Shaper strokeColor(@ColorInt int color) {
        setStrokeColor(color);
        return this;
    }

    public Shaper strokeColor(Context context, @ColorRes int color) {
        setStrokeColor(ContextCompat.getColor(context, color));
        return this;
    }

    public Shaper strokeColorRes(@ColorRes int resId) {
        setStrokeColor(ContextCompat.getColor(mContext, resId));
        return this;
    }

    public Shaper strokeColor(ColorSelector selector) {
        mStrokeColorStateList = selector.getColor();
        return this;
    }

    public Shaper strokeColor(ColorStateList colorStateList) {
        mStrokeColorStateList = colorStateList;
        return this;
    }

    public Shaper strokeWidth(int dp) {
        mStrokeWidth = px(dp);
        return this;
    }
    public Shaper strokeDashWidth(int dp) {
        mStrokeDashWidth = px(dp);
        return this;
    }
    public Shaper strokeDashGap(int dp) {
        mStrokeDashGap = px(dp);
        return this;
    }

    public Shaper gradientColors(int... colors) {
        mGradientColors = colors;
        return this;
    }
    public Shaper gradientOrientation(GradientDrawable.Orientation orientation) {
        mGradientOrientation = orientation;
        return this;
    }
    public Shaper linearGradient() {
        mGradientType = GradientDrawable.LINEAR_GRADIENT;
        return this;
    }
    public Shaper radialGradient() {
        mGradientType = GradientDrawable.RADIAL_GRADIENT;
        return this;
    }
    public Shaper sweepGradient() {
        mGradientType = GradientDrawable.SWEEP_GRADIENT;
        return this;
    }
    public Shaper gradientType(int type) {
        mGradientType = type;
        return this;
    }
    public Shaper gradientRadius(int radius) {
        mGradientRadius = px(radius);
        return this;
    }

    public Shaper rippleEffect() {
        this.withRippleEffect = true;
        return this;
    }

    public Shaper rippleEffect(boolean asMask) {
        this.withRippleEffect = true;
        this.setAsMask = asMask;
        return this;
    }

    public Shaper rippleColor(@ColorInt int color) {
        this.withRippleEffect = true;
        setRippleColor(color);
        return this;
    }

    public Shaper rippleColor(Context context, @ColorRes int color) {
        setRippleColor(ContextCompat.getColor(context, color));
        return this;
    }

    public Shaper rippleColorRes(@ColorRes int resId) {
        setRippleColor(ContextCompat.getColor(mContext, resId));
        return this;
    }

    public Shaper rippleColor(ColorSelector selector) {
        mRippleColorStateList = selector.getColor();
        return this;
    }

    public Shaper rippleColor(ColorStateList colorStateList) {
        mRippleColorStateList = colorStateList;
        return this;
    }

    public Shaper size(int widthDp, int heightDp) {
        mWidth = px(widthDp);
        mHeight = px(heightDp);
        return this;
    }
    public Shaper width(int widthDp) {
        mWidth = px(widthDp);
        return this;
    }
    public Shaper height(int heightDp) {
        mHeight = px(heightDp);
        return this;
    }

    public Shaper gravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    public Shaper layer(Shaper shaper) {
        mLayers.add(shaper);
        return this;
    }

    public Shaper rectangle() {
        this.mShape = GradientDrawable.RECTANGLE;
        return this;
    }
    public Shaper oval() {
        this.mShape = GradientDrawable.OVAL;
        return this;
    }
    public Shaper line() {
        this.mShape = GradientDrawable.LINE;
        return this;
    }

    public Shaper cornerTop(int dp) {
        int cornerValue = px(dp);
        mCorners[0] = cornerValue;
        mCorners[1] = cornerValue;
        return this;
    }
    public Shaper cornerBottom(int dp) {
        int cornerValue = px(dp);
        mCorners[2] = cornerValue;
        mCorners[3] = cornerValue;
        return this;
    }
    public Shaper cornerLeft(int dp) {
        int cornerValue = px(dp);
        mCorners[0] = cornerValue;
        mCorners[3] = cornerValue;
        return this;
    }
    public Shaper cornerRight(int dp) {
        int cornerValue = px(dp);
        mCorners[1] = cornerValue;
        mCorners[2] = cornerValue;
        return this;
    }
    public Shaper cornerTopLeft(int dp) {
        int cornerValue = px(dp);
        mCorners[0] = cornerValue;
        return this;
    }
    public Shaper cornerTopRight(int dp) {
        int cornerValue = px(dp);
        mCorners[1] = cornerValue;
        return this;
    }
    public Shaper cornerBottomRight(int dp) {
        int cornerValue = px(dp);
        mCorners[2] = cornerValue;
        return this;
    }
    public Shaper cornerBottomLeft(int dp) {
        int cornerValue = px(dp);
        mCorners[3] = cornerValue;
        return this;
    }
    public Shaper corner(int dp) {
        int c = px(dp);
        mCorners[0] = c;
        mCorners[1] = c;
        mCorners[2] = c;
        mCorners[3] = c;
        return this;
    }
    public Shaper corner(int topLeft, int topRight, int bottomRight, int bottomLeft) {
        mCorners[0] = px(topLeft);
        mCorners[1] = px(topRight);
        mCorners[2] = px(bottomRight);
        mCorners[3] = px(bottomLeft);
        return this;
    }

    public Shaper paddingTop(int dp) {
        mPadding[1] = px(dp);
        return this;
    }
    public Shaper paddingBottom(int dp) {
        mPadding[3] = px(dp);
        return this;
    }
    public Shaper paddingLeft(int dp) {
        mPadding[0] = px(dp);
        return this;
    }
    public Shaper paddingRight(int dp) {
        mPadding[2] = px(dp);
        return this;
    }
    public Shaper padding(int dp) {
        int p = px(dp);
        mPadding[0] = p;
        mPadding[1] = p;
        mPadding[2] = p;
        mPadding[3] = p;
        return this;
    }
    public Shaper padding(int left, int top, int right, int bottom) {
        mPadding[0] = px(left);
        mPadding[1] = px(top);
        mPadding[2] = px(right);
        mPadding[3] = px(bottom);
        return this;
    }

    private int px(float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                Resources.getSystem().getDisplayMetrics()
        );
    }


}
