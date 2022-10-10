package com.kaiserdavar.androidui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kaiserdavar.androidui.style.Color;
import com.kaiserdavar.androidui.style.ColorSelector;
import com.kaiserdavar.androidui.style.ShapeSelector;
import com.kaiserdavar.androidui.style.VueStyle;
import com.kaiserdavar.androidui.util.Shaper;

import java.util.List;

public abstract class BaseVue<T, M extends View> implements Vue {

    protected T t;
    protected M view;
    protected List<Vue> children;
    protected int mGravity = -1;
    protected float weight;

    public ViewGroup.MarginLayoutParams marginLp = new ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
    );
    public ConstraintLayout.LayoutParams constraintLp = new ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
    );

    public BaseVue(M view) {
        this.view = view;
        t = onGetThis();
        onInit();
    }

    public BaseVue(Context context) {
        view = onGetMainView(context);
        t = onGetThis();
        onInit();
    }

    protected void onInit() {

    }

    protected abstract M onGetMainView(Context context);
    @SuppressWarnings("unchecked")
    protected T onGetThis() {
        return (T) this;
    }

    @Override
    public M createView() {
        view.setLayoutParams(marginLp);
        return view;
    }

    public M view() {
        return view;
    }

    public Context context() {
        return view.getContext();
    }

    @Override
    public ViewGroup.MarginLayoutParams getMarginLp() {
        return marginLp;
    }

    @Override
    public ConstraintLayout.LayoutParams getConstraintLp() {
        return constraintLp;
    }

    @Override
    public int getGravity() {
        return mGravity;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    public T style(LiveData<VueStyle> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::style);
        return t;
    }
    public T style(VueStyle vueStyle) {
        if (vueStyle != null) {
            if (vueStyle.backgroundRes != null)
                background(vueStyle.backgroundRes);
            if (vueStyle.background != null)
                vueStyle.background.setAsBackgroundOf(view);
        }
        return t;
    }

    public T width(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::width);
        return t;
    }
    public T width(int dp) {
        setWidth(dp);
        return t;
    }
    public T height(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::height);
        return t;
    }
    public T height(int dp) {
        setHeight(dp);
        return t;
    }
    public T size(int widthDp, int heightDp) {
        setWidth(widthDp);
        setHeight(heightDp);
        return t;
    }
    public T fullWidth() {
        setFullWidth();
        return t;
    }
    public T fullHeight() {
        setFullHeight();
        return t;
    }
    public T fullSize() {
        setFullWidth();
        setFullHeight();
        return t;
    }
    public T fullWidth(float percent) {
        setFullWidth();
        constraintLp.matchConstraintPercentWidth = percent;
        return t;
    }
    public T fullHeight(float percent) {
        setFullHeight();
        constraintLp.matchConstraintPercentHeight = percent;
        return t;
    }
    public T fullWidth(int minWidthDp, int maxWidthDp) {
        setFullWidth();
        int min = px(minWidthDp);
        int max = px(maxWidthDp);
        constraintLp.matchConstraintMinWidth = min;
        constraintLp.matchConstraintMaxWidth = max;
        return t;
    }
    public T fullHeight(int minHeightDp, int maxHeightDp) {
        setFullHeight();
        int min = px(minHeightDp);
        int max = px(maxHeightDp);
        constraintLp.matchConstraintMinHeight = min;
        constraintLp.matchConstraintMaxHeight = max;
        return t;
    }

    private void setWidth(int dp) {
        marginLp.width = dp > 0 ? px(dp) : dp;
        constraintLp.width = marginLp.width;
    }
    private void setHeight(int dp) {
        marginLp.height = dp > 0 ? px(dp) : dp;
        constraintLp.height = marginLp.height;
    }
    private void setFullWidth() {
        marginLp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        constraintLp.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
    }
    private void setFullHeight() {
        marginLp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        constraintLp.height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT;
    }
    public T minWidth(int dp) {
        view.setMinimumWidth(px(dp));
        return t;
    }
    public T minHeight(int dp) {
        view.setMinimumHeight(px(dp));
        return t;
    }

    public T widthPercent(int percent) {
        view.post(() -> {
            View parent = ((View) view.getParent());
            int width = parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight();
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.width = (int)(((float)percent / 100) * width);
            view.requestLayout();
        });
        return t;
    }
    public T heightPercent(int percent) {
        view.post(() -> {
            View parent = ((View) view.getParent());
            int height = parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = (int)(((float)percent / 100) * height);
            view.requestLayout();
        });
        return t;
    }

    public T align(int gravity) {
        mGravity = gravity;
        return t;
    }

    public T weight(float weight) {
        this.weight = weight;
        return t;
    }

    public T margin(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::margin);
        return t;
    }
    public T margin(int dp) {
        int margin = px(dp);
        marginLp.setMargins(margin, margin, margin, margin);
        constraintLp.setMargins(margin, margin, margin, margin);
        return t;
    }
    public T marginHorizontal(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::marginHorizontal);
        return t;
    }
    public T marginHorizontal(int dp) {
        int margin = px(dp);
        marginLp.setMarginStart(margin);
        marginLp.setMarginEnd(margin);
        constraintLp.setMarginStart(margin);
        constraintLp.setMarginEnd(margin);
        return t;
    }
    public T marginVertical(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::marginVertical);
        return t;
    }
    public T marginVertical(int dp) {
        int margin = px(dp);
        marginLp.topMargin = margin;
        marginLp.bottomMargin = margin;
        constraintLp.topMargin = margin;
        constraintLp.bottomMargin = margin;
        return t;
    }
    public T marginTop(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::marginTop);
        return t;
    }
    public T marginTop(int dp) {
        int margin = px(dp);
        marginLp.topMargin = margin;
        constraintLp.topMargin = margin;
        return t;
    }
    public T marginBottom(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::marginBottom);
        return t;
    }
    public T marginBottom(int dp) {
        int margin = px(dp);
        marginLp.bottomMargin = margin;
        constraintLp.bottomMargin = margin;
        return t;
    }
    public T marginStart(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::marginStart);
        return t;
    }
    public T marginStart(int dp) {
        int margin = px(dp);
        marginLp.setMarginStart(margin);
        constraintLp.setMarginStart(margin);
        return t;
    }
    public T marginEnd(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::marginEnd);
        return t;
    }
    public T marginEnd(int dp) {
        int margin = px(dp);
        marginLp.setMarginEnd(margin);
        constraintLp.setMarginEnd(margin);
        return t;
    }

    public T padding(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::padding);
        return t;
    }
    public T padding(int dp) {
        int padding = px(dp);
        view.setPaddingRelative(padding, padding, padding, padding);
        return t;
    }
    public T paddingHorizontal(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::paddingHorizontal);
        return t;
    }
    public T paddingHorizontal(int dp) {
        int padding = px(dp);
        view.setPaddingRelative(padding, view.getPaddingTop(), padding, view.getPaddingBottom());
        return t;
    }
    public T paddingVertical(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::paddingVertical);
        return t;
    }
    public T paddingVertical(int dp) {
        int padding = px(dp);
        view.setPaddingRelative(view.getPaddingStart(), padding, view.getPaddingEnd(), padding);
        return t;
    }
    public T paddingTop(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::paddingTop);
        return t;
    }
    public T paddingTop(int dp) {
        int padding = px(dp);
        view.setPaddingRelative(view.getPaddingStart(), padding, view.getPaddingEnd(), view.getPaddingBottom());
        return t;
    }
    public T paddingBottom(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::paddingBottom);
        return t;
    }
    public T paddingBottom(int dp) {
        int padding = px(dp);
        view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), view.getPaddingEnd(), padding);
        return t;
    }
    public T paddingStart(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::paddingStart);
        return t;
    }
    public T paddingStart(int dp) {
        int padding = px(dp);
        view.setPaddingRelative(padding, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        return t;
    }
    public T paddingEnd(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::paddingEnd);
        return t;
    }
    public T paddingEnd(int dp) {
        int padding = px(dp);
        view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), padding, view.getPaddingBottom());
        return t;
    }

    public T allSidesToParent() {
        constraintLp.topToTop = ConstraintSet.PARENT_ID;
        constraintLp.bottomToBottom = ConstraintSet.PARENT_ID;
        constraintLp.startToStart = ConstraintSet.PARENT_ID;
        constraintLp.endToEnd = ConstraintSet.PARENT_ID;
        return t;
    }

    public T horizontalSidesToParent() {
        constraintLp.startToStart = ConstraintSet.PARENT_ID;
        constraintLp.endToEnd = ConstraintSet.PARENT_ID;
        return t;
    }
    public T verticalSidesToParent() {
        constraintLp.topToTop = ConstraintSet.PARENT_ID;
        constraintLp.bottomToBottom = ConstraintSet.PARENT_ID;
        return t;
    }

    public T topToTopOfParent() {
        constraintLp.topToTop = ConstraintSet.PARENT_ID;
        return t;
    }
    public T topToBottomOfParent() {
        constraintLp.topToBottom = ConstraintSet.PARENT_ID;
        return t;
    }
    public T bottomToTopOfParent() {
        constraintLp.bottomToTop = ConstraintSet.PARENT_ID;
        return t;
    }
    public T bottomToBottomOfParent() {
        constraintLp.bottomToBottom = ConstraintSet.PARENT_ID;
        return t;
    }
    public T startToStartOfParent() {
        constraintLp.startToStart = ConstraintSet.PARENT_ID;
        return t;
    }
    public T startToEndOfParent() {
        constraintLp.startToEnd = ConstraintSet.PARENT_ID;
        return t;
    }
    public T endToStartOfParent() {
        constraintLp.endToStart = ConstraintSet.PARENT_ID;
        return t;
    }
    public T endToEndOfParent() {
        constraintLp.endToEnd = ConstraintSet.PARENT_ID;
        return t;
    }

    public T topToTopOf(int id) {
        constraintLp.topToTop = id;
        return t;
    }
    public T topToBottomOf(int id) {
        constraintLp.topToBottom = id;
        return t;
    }
    public T bottomToTopOf(int id) {
        constraintLp.bottomToTop = id;
        return t;
    }
    public T bottomToBottomOf(int id) {
        constraintLp.bottomToBottom = id;
        return t;
    }
    public T startToStartOf(int id) {
        constraintLp.startToStart = id;
        return t;
    }
    public T startToEndOf(int id) {
        constraintLp.startToEnd = id;
        return t;
    }
    public T endToStartOf(int id) {
        constraintLp.endToStart = id;
        return t;
    }
    public T endToEndOf(int id) {
        constraintLp.endToEnd = id;
        return t;
    }
    public T baselineToBaselineOf(int id) {
        constraintLp.baselineToBaseline = id;
        return t;
    }
    public T baselineToTopOf(int id) {
        constraintLp.baselineToTop = id;
        return t;
    }
    public T baselineToBottomOf(int id) {
        constraintLp.baselineToBottom = id;
        return t;
    }
    public T aroundOf(int id) {
        constraintLp.circleConstraint = id;
        return t;
    }
    public T aroundRadius(int radius) {
        constraintLp.circleRadius = radius;
        return t;
    }
    public T aroundAngle(float angle) {
        constraintLp.circleAngle = angle;
        return t;
    }
    public T horizontalBias(float value) {
        constraintLp.horizontalBias = value;
        return t;
    }
    public T verticalBias(float value) {
        constraintLp.verticalBias = value;
        return t;
    }
    public T horizontalWeight(float value) {
        constraintLp.horizontalWeight = value;
        return t;
    }
    public T verticalWeight(float value) {
        constraintLp.verticalWeight = value;
        return t;
    }
    public T horizontalChain(int style) {
        constraintLp.horizontalChainStyle = style;
        return t;
    }
    public T verticalChain(int style) {
        constraintLp.verticalChainStyle = style;
        return t;
    }
    public T constrainedWidth(boolean constrained) {
        constraintLp.constrainedWidth = constrained;
        return t;
    }
    public T constrainedHeight(boolean constrained) {
        constraintLp.constrainedHeight = constrained;
        return t;
    }
    public T dimensionRatio(String ratio) {
        constraintLp.dimensionRatio = ratio;
        return t;
    }


    public T background(LiveData<Shaper> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, shadow -> {
            shadow.setAsBackgroundOf(view);
        });
        return t;
    }
    public T background(Shaper shaper) {
        shaper.setAsBackgroundOf(view);
        return t;
    }
    public T background(com.kaiserdavar.androidui.OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        shaper.setAsBackgroundOf(view);
        return t;
    }
    public T background(@DrawableRes int resource) {
        view.setBackgroundResource(resource);
        return t;
    }
    public T background(Drawable drawable) {
        ViewCompat.setBackground(view, drawable);
        return t;
    }
    public T backgroundColor(@ColorInt int color) {
        view.setBackgroundColor(color);
        return t;
    }
/*    public T backgroundSelector(OnShapeSelectorListener listener) {
        ShapeSelector selector = new ShapeSelector();
        listener.onShapeSelector(selector);
        return background(selector.getDrawable());
    }*/
    public T background(ShapeSelector selector) {
        return background(selector.getDrawable());
    }
    public T selectableItemBackground() {
        TypedValue outValue = new TypedValue();
        view.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                outValue, true);
        view.setBackgroundResource(outValue.resourceId);
        return t;
    }
    public T backgroundByAttr(@AttrRes int attr) {
        TypedValue outValue = new TypedValue();
        view.getContext().getTheme().resolveAttribute(attr, outValue, true);
        if (outValue.resourceId != 0) {
            view.setBackgroundResource(outValue.resourceId);
        } else {
            view.setBackgroundColor(outValue.data);
        }
        return t;
    }
    public T backgroundTint(PorterDuff.Mode tintMode, ColorSelector selector) {
        view.setBackgroundTintMode(tintMode);
        view.setBackgroundTintList(selector.getColor());
        return t;
    }
    public T backgroundTint(ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
        return t;
    }
    public T noBackground() {
        view.setBackgroundResource(android.R.color.transparent);
        return t;
    }



    public T foreground(LiveData<Shaper> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, shaper -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setForeground(shaper.generate());
            }
        });
        return t;
    }
    public T foreground(Shaper shaper) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setForeground(shaper.generate());
        }
        return t;
    }
    public T foreground(com.kaiserdavar.androidui.OnShaperListener listener) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Shaper shaper = new Shaper();
            listener.onShaper(shaper);
            view.setForeground(shaper.generate());
        }
        return t;
    }
    public T foreground(@DrawableRes int resource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setForeground(ContextCompat.getDrawable(context(), resource));
        }
        return t;
    }
    public T foreground(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setForeground(drawable);
        }
        return t;
    }
    public T foregroundColor(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setForeground(new ColorDrawable(color));
        }
        return t;
    }
    public T foregroundGravity(int gravity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setForegroundGravity(gravity);
        }
        return t;
    }
    public T selectableItemForeground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            TypedValue outValue = new TypedValue();
            view.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                    outValue, true);
            view.setForeground(ContextCompat.getDrawable(view.getContext(), outValue.resourceId));
        }
        return t;
    }
    public T foregroundByAttr(@AttrRes int attr) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            TypedValue outValue = new TypedValue();
            view.getContext().getTheme().resolveAttribute(attr, outValue, true);
            if (outValue.resourceId != 0) {
                view.setForeground(ContextCompat.getDrawable(context(), outValue.resourceId));
            }
        }
        return t;
    }
    public T foregroundTint(PorterDuff.Mode tintMode, ColorSelector selector) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setForegroundTintMode(tintMode);
            view.setForegroundTintList(selector.getColor());
        }
        return t;
    }
    public T foregroundTint(ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setForegroundTintList(colorStateList);
        }
        return t;
    }
    public T noForeground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setForeground(new ColorDrawable(Color.TRANSPARENT));
        }
        return t;
    }

    public T bringToFront() {
        view.bringToFront();
        return t;
    }

    public T elevation(int dp) {
        view.setElevation(px(dp));
        return t;
    }

    public T tooltipText(@StringRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            view.setTooltipText(context().getText(resId));
        }
        return t;
    }

    public T keepScreenOn(boolean keepOn) {
        view.setKeepScreenOn(keepOn);
        return t;
    }

    public T soundEffectEnabled(boolean enabled) {
        view.setSoundEffectsEnabled(enabled);
        return t;
    }

    public T shadowColorRes(@ColorRes int resId) {
        int color = ContextCompat.getColor(context(), resId);
        return shadowColor(color);
    }
    public T shadowColor(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            view.setOutlineAmbientShadowColor(color);
            view.setOutlineSpotShadowColor(color);
        }
        return t;
    }
    public T ambientShadowColor(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            view.setOutlineAmbientShadowColor(color);
        }
        return t;
    }
    public T spotShadowColor(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            view.setOutlineSpotShadowColor(color);
        }
        return t;
    }

    public T visibility(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::visibility);
        return t;
    }
    public T visibility(boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return t;
    }
    public T invisibility(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::invisibility);
        return t;
    }
    public T invisibility(boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return t;
    }
    public T visible() {
        view.setVisibility(View.VISIBLE);
        return t;
    }
    public T invisible() {
        view.setVisibility(View.INVISIBLE);
        return t;
    }
    public T gone() {
        view.setVisibility(View.GONE);
        return t;
    }
    public boolean isVisible() {
        return view.getVisibility() == View.VISIBLE;
    }

    public T selected(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::selected);
        return t;
    }
    public T selected(boolean selected) {
        view.setSelected(selected);
        return t;
    }
    public T enabled(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::enabled);
        return t;
    }
    public T disabled(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::disabled);
        return t;
    }
    public T enabled(boolean enabled) {
        view.setEnabled(enabled);
        return t;
    }
    public T disabled(boolean disabled) {
        view.setEnabled(!disabled);
        return t;
    }
    public T enable() {
        view.setEnabled(true);
        return t;
    }
    public T disable() {
        view.setEnabled(false);
        return t;
    }
    public T alpha(float value) {
        view.setAlpha(value);
        return t;
    }
    public T rotation(float value) {
        view.setRotation(value);
        return t;
    }
    public T scale(float value) {
        view.setScaleX(value);
        view.setScaleY(value);
        return t;
    }
    public T x(float value) {
        view.setX(value);
        return t;
    }
    public T y(float value) {
        view.setY(value);
        return t;
    }
    public T translationX(float dp) {
        view.setTranslationX(px(dp));
        return t;
    }
    public T translationY(float dp) {
        view.setTranslationY(px(dp));
        return t;
    }

    public T rtl() {
        view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        return t;
    }
    public T ltr() {
        view.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        return t;
    }

    public T animation(OnAnimationListener listener) {
        listener.onAnimation(view.animate());
        return t;
    }

    public void hide() {
        hide(false);
    }
    public void hide(boolean setInvisible) {
        if (!isVisible())
            return;
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(setInvisible ? View.INVISIBLE : View.GONE);
            }
        }).start();
    }
    public void show() {
        if (isVisible() && view.getAlpha() == 1.0f)
            return;
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1.0f).setListener(null).start();
    }

    public void animateWidth(int toWidthDp) {
        animateWidth(toWidthDp, null);
    }
    public void animateWidth(int toWidthDp, OnObjectListener<ValueAnimator> listener) {
        int currentWidth = view.getWidth();
        Integer toWidth = null;
        if (toWidthDp >= 0) {
            toWidth = px(toWidthDp);
        } else if (toWidthDp == ViewGroup.LayoutParams.WRAP_CONTENT) {
            int heightSpec;
            if (marginLp.height >= 0)
                heightSpec = View.MeasureSpec.makeMeasureSpec(marginLp.height, View.MeasureSpec.AT_MOST);
            else
                heightSpec = marginLp.height;

            view.measure(toWidthDp, heightSpec);
            toWidth = view.getMeasuredWidth();
        } else if (toWidthDp == ViewGroup.LayoutParams.MATCH_PARENT) {
            if (view.getParent() instanceof View) {
                View parent = (View) view.getParent();
                toWidth = parent.getMeasuredWidth() - marginLp.leftMargin - marginLp.rightMargin;
            }
        }
        if (toWidth == null || currentWidth == toWidth)
            return;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(currentWidth, toWidth);
        if (listener != null)
            listener.onObject(valueAnimator);
        valueAnimator.addUpdateListener(animator -> {
            int value = (int) animator.getAnimatedValue();
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.width = value;
            view.requestLayout();
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                /*if (toWidthDp < 0) {
                    ViewGroup.LayoutParams params = view.getLayoutParams();
                    params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    view.requestLayout();
                }*/
            }
        });
        valueAnimator.start();
    }
    public void animateHeight(int toHeightDp) {
        animateHeight(toHeightDp, null);
    }
    public void animateHeight(int toHeightDp, OnObjectListener<ValueAnimator> listener) {
        int currentHeight = view.getHeight();
        Integer toHeight = null;
        if (toHeightDp >= 0) {
            toHeight = px(toHeightDp);
        } else if (toHeightDp == ViewGroup.LayoutParams.WRAP_CONTENT) {
            int widthSpec;
            if (marginLp.width >= 0)
                widthSpec = View.MeasureSpec.makeMeasureSpec(marginLp.width, View.MeasureSpec.AT_MOST);
            else
                widthSpec = marginLp.width;

            view.measure(widthSpec, toHeightDp);
            toHeight = view.getMeasuredHeight();
        } else if (toHeightDp == ViewGroup.LayoutParams.MATCH_PARENT) {
            if (view.getParent() instanceof View) {
                View parent = (View) view.getParent();
                toHeight = parent.getMeasuredHeight() - marginLp.topMargin - marginLp.bottomMargin;
            }
        }
        if (toHeight == null || currentHeight == toHeight)
            return;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(currentHeight, toHeight);
        if (listener != null)
            listener.onObject(valueAnimator);
        valueAnimator.addUpdateListener(animator -> {
            int value = (int) animator.getAnimatedValue();
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = value;
            view.requestLayout();
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                /*if (toHeightDp < 0) {
                    ViewGroup.LayoutParams params = view.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    view.requestLayout();
                }*/
            }
        });
        valueAnimator.start();
    }

    public void animateSlide(int gravity) {
        switch (gravity) {
            case Gravity.TOP:
                view.animate().translationY(-view.getHeight()).start();
                break;
            case Gravity.BOTTOM:
                view.animate().translationY(view.getHeight()).start();
                break;
            case Gravity.START:
                int direction = view.getLayoutDirection();
                int x = direction == View.LAYOUT_DIRECTION_RTL ? view.getWidth() : -view.getWidth();
                view.animate().translationX(x).start();
                break;
            case Gravity.END:
                direction = view.getLayoutDirection();
                x = direction == View.LAYOUT_DIRECTION_RTL ? -view.getWidth() : view.getWidth();
                view.animate().translationX(x).start();
                break;
            case Gravity.NO_GRAVITY:
                view.animate().translationX(0).translationY(0).start();
                break;
        }
    }
    public void animatePosition(float xDp, float yDp) {
        view.animate().translationX(px(xDp)).translationY(px(yDp)).start();
    }
    public void animatePosition(int gravity) {
        View parent = (View) view.getParent();
        ViewPropertyAnimator animator = view.animate();
        final int absoluteGravity = Gravity.getAbsoluteGravity(gravity, view.getLayoutDirection());
        final int verticalGravity = gravity & Gravity.VERTICAL_GRAVITY_MASK;

        float midWidth = (parent.getWidth() - view.getWidth()) / 2.0f;
        float midHeight = (parent.getHeight() - view.getHeight()) / 2.0f;
        float bottom = parent.getHeight() - view.getHeight();
        float right = parent.getWidth() - view.getWidth();

        switch (absoluteGravity & Gravity.HORIZONTAL_GRAVITY_MASK) {
            case Gravity.CENTER_HORIZONTAL:
                animator.x(midWidth);
                break;
            case Gravity.RIGHT:
                animator.x(right);
                break;
            case Gravity.LEFT:
                animator.x(0);
                break;
        }
        switch (verticalGravity) {
            case Gravity.TOP:
                animator.y(0);
                break;
            case Gravity.CENTER_VERTICAL:
                animator.y(midHeight);
                break;
            case Gravity.BOTTOM:
                animator.y(bottom);
                break;
        }
        animator.start();
    }

    public void animateBackgroundColor(int... colors) {
        animateBackgroundColor(null, colors);
    }
    public void animateBackgroundColor(OnObjectListener<ValueAnimator> listener, int... colors) {
        ValueAnimator colorAnimation = ValueAnimator.ofArgb(colors);
        if (listener != null)
            listener.onObject(colorAnimation);
        colorAnimation.addUpdateListener(animator -> {
            view.setBackgroundColor((int) animator.getAnimatedValue());
        });
        colorAnimation.start();
    }
    public ValueAnimator backgroundColorAnimation(int... colors) {
        ValueAnimator colorAnimation = ValueAnimator.ofArgb(colors);
        colorAnimation.addUpdateListener(animator -> {
            view.setBackgroundColor((int) animator.getAnimatedValue());
        });
        return colorAnimation;
    }
    public void animateBackground(int duration, boolean crossFade, Drawable... drawables) {
        TransitionDrawable d = new TransitionDrawable(drawables);
        d.setCrossFadeEnabled(crossFade);
        ViewCompat.setBackground(view, d);
        d.startTransition(duration);
    }
    public TransitionDrawable backgroundAnimation(Drawable... drawables) {
        TransitionDrawable d = new TransitionDrawable(drawables);
        ViewCompat.setBackground(view, d);
        return d;
    }


    public T fitSystemWindow(boolean fit) {
        view.setFitsSystemWindows(fit);
        return t;
    }

    public T clip(Rect clipBounds) {
        view.setClipBounds(clipBounds);
        return t;
    }
    public T clipToOutline(boolean clipToOutline) {
        view.setClipToOutline(clipToOutline);
        return t;
    }


    public T id(int id) {
        view.setId(id);
        return t;
    }

    public T tag(Object tag) {
        view.setTag(tag);
        return t;
    }

    public T view(OnObjectListener<M> listener) {
        listener.onObject(view);
        return t;
    }

    public T vue(OnObjectListener<T> listener) {
        listener.onObject(t);
        return t;
    }

    public T onFocus(OnFocusListener listener) {
        view.setOnFocusChangeListener((view1, b) -> {
            listener.onFocus(b);
        });
        return t;
    }
    public T onTouch(OnTouchListener listener) {
        view.setOnTouchListener((v, event) -> listener.onTouch(event));
        return t;
    }
    public T onTouchMotion(OnTouchListener listener) {
        view.setOnGenericMotionListener((v, event) -> listener.onTouch(event));
        return t;
    }
    public T onHover(OnTouchListener listener) {
        view.setOnHoverListener((v, event) -> listener.onTouch(event));
        return t;
    }
    public T onDrag(OnDragListener listener) {
        view.setOnDragListener((v, event) -> listener.onDrag(event));
        return t;
    }
    public T onClick(OnClickListener listener) {
        view.setOnClickListener(v -> listener.onClick());
        return t;
    }
    public T onLongClick(com.kaiserdavar.androidui.OnLongClickListener listener) {
        view.setOnLongClickListener(v -> listener.onLongClick());
        return t;
    }
    public T onKey(OnEditorKeyListener listener) {
        view.setOnKeyListener((view1, keyCode, keyEvent) -> listener.onKey(keyCode));
        return t;
    }

    public T onReady(com.kaiserdavar.androidui.OnViewReadyListener<T> listener) {
        view.post(() -> listener.onReady(t));
        return t;
    }

    public <K> T onChangeOf(MutableLiveData<K> liveData, LifecycleOwner lifecycleOwner, OnChangeValueListener<T, K> listener) {
        liveData.observe(lifecycleOwner, t1 -> {
            listener.onChange(t, t1);
        });
        return t;
    }

    public T action(long delay, OnObjectListener<T> listener) {
        if (delay > 0) {
            view.postDelayed(() -> listener.onObject(t), delay);
        } else {
            view.post(() -> listener.onObject(t));
        }
        return t;
    }

    @Override
    public Vue findChild(int id) {
        if (hasChild()) {
            for (Vue vue : children) {
                if (vue.view().getId() == id)
                    return vue;
            }
        }
        return null;
    }

    @Override
    public Vue findChildDeep(int id) {
        if (view.getId() == id)
            return this;
        if (hasChild()) {
            for (Vue vue : children) {
                Vue v = vue.findChildDeep(id);
                if (v != null)
                    return v;
            }
        }
        return null;
    }

    @Override
    public Vue getChild(int index) {
        return children.get(index);
    }

    @Override
    public boolean hasChild() {
        return children != null && !children.isEmpty();
    }

    public int px(float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                view.getResources().getDisplayMetrics()
        );
    }

    public interface OnChangeValueListener<T, K> {
        void onChange(T v, K value);
    }

    public interface OnEditorKeyListener {
        boolean onKey(int keyCode);
    }
}
