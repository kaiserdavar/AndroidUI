package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class SwipeLoadingStack extends BaseStack<SwipeLoadingStack, SwipeRefreshLayout> {

    public static SwipeLoadingStack create(Context context) {
        return new SwipeLoadingStack(context);
    }

    public SwipeLoadingStack(SwipeRefreshLayout view) {
        super(view);
    }

    public SwipeLoadingStack(Context context) {
        super(context);
    }

    @Override
    protected SwipeRefreshLayout onGetMainView(Context context) {
        return new SwipeRefreshLayout(context);
    }

    public SwipeLoadingStack onLoading(SwipeRefreshLayout.OnRefreshListener listener) {
        view.setOnRefreshListener(listener);
        return this;
    }

    public SwipeLoadingStack loading(LiveData<Boolean> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::loading);
        return this;
    }
    public SwipeLoadingStack loading(boolean loading) {
        view.setRefreshing(loading);
        return this;
    }

    public SwipeLoadingStack large() {
        view.setSize(SwipeRefreshLayout.LARGE);
        return this;
    }

    public SwipeLoadingStack progressColors(int... colors) {
        view.setColorSchemeColors(colors);
        return this;
    }
    public SwipeLoadingStack progressBackgroundColor(@ColorInt int color) {
        view.setProgressBackgroundColorSchemeColor(color);
        return this;
    }
    public SwipeLoadingStack progressBackgroundColorRes(@ColorRes int resId) {
        view.setProgressBackgroundColorSchemeResource(resId);
        return this;
    }
    public SwipeLoadingStack progressOffset(boolean scale, int startDp, int endDp) {
        view.setProgressViewOffset(scale, px(startDp), px(endDp));
        return this;
    }

    public SwipeLoadingStack progressRestingPosition(boolean scale, int dp) {
        view.setProgressViewEndTarget(scale, px(dp));
        return this;
    }
    public SwipeLoadingStack progressPositionToStartLoading(int dp) {
        view.setSlingshotDistance(px(dp));
        return this;
    }
    public SwipeLoadingStack swipeAmountToStartLoading(int dp) {
        view.setDistanceToTriggerSync(dp);
        return this;
    }


}
