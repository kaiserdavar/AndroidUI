package com.kaiserdavar.androidui;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.kaiserdavar.androidui.stack.BaseVStack;
import com.kaiserdavar.androidui.stack.ZStack;
import com.kaiserdavar.androidui.style.StatusProgressStyle;
import com.kaiserdavar.androidui.style.VueStyle;

public class StatusProgress extends BaseVStack<StatusProgress, LinearLayoutCompat> {

    public static StatusProgressStyle defaultStyle;

    public static final int STATUS_LOADING = 0;
    public static final int STATUS_EMPTY = 2;
    public static final int STATUS_ERROR = -1;
    public static final int STATUS_DONE = 1;

    private Text mTextVue;
    private ZStack mProgressStack;
    private Progress mProgressVue;
    private Button mRetryButton;

    private int mStatus;
    private CharSequence loadingText = "Loading";
    private CharSequence emptyText = "No item";
    private CharSequence errorText = "Error";
    private CharSequence retryText = "Retry";
    private @DrawableRes int emptyImageRes;
    private @DrawableRes int errorImageRes;
    private int imageGravity = Gravity.TOP;
    private long loadingDelay;
    private Handler mHandler;

    public static StatusProgress create(Context context) {
        return new StatusProgress(context);
    }

    @Override
    protected LinearLayoutCompat onGetMainView(Context context) {
        LinearLayoutCompat v = new LinearLayoutCompat(context);
        v.setOrientation(LinearLayoutCompat.VERTICAL);
        v.setShowDividers(LinearLayoutCompat.SHOW_DIVIDER_MIDDLE);
        return v;
    }

    public StatusProgress(Context context) {
        super(context);
        mHandler = new Handler();
        mProgressStack = ZStack.create(context());
        Vue customProgress = onGetProgressVue();
        if (customProgress != null) {
            mProgressStack.child(customProgress);
        } else {
            mProgressVue = Progress.create(context());
            mProgressStack.child(mProgressVue);
        }
        mTextVue = Text.create(context())
                .textAlign(Gravity.CENTER)
                .drawablePadding(16);
        mRetryButton = Button.create(context())
                .text(retryText)
                .paddingHorizontal(24)
                .paddingVertical(8);
        gravity(Gravity.CENTER);
        child(mProgressStack);
        child(mTextVue);
        child(mRetryButton);
        if (defaultStyle != null)
            style(defaultStyle);
        setStatus(mStatus);
    }

    protected Vue onGetProgressVue() {
        return null;
    }

    @Override
    public StatusProgress style(VueStyle vueStyle) {
        super.style(vueStyle);
        if (vueStyle instanceof StatusProgressStyle) {
            StatusProgressStyle style = (StatusProgressStyle) vueStyle;
            mTextVue.style(style.titleStyle);
            mRetryButton.style(style.buttonStyle);
            loadingText = style.loadingText;
            emptyText = style.emptyText;
            errorText = style.errorText;
            retryText = style.retryText;
            emptyImageRes = style.emptyImageRes;
            errorImageRes = style.errorImageRes;
            imageGravity = style.imageGravity;
            mStatus = style.status;
        }
        return this;
    }

    public StatusProgress progressVue(Vue vue) {
        mProgressVue = null;
        mProgressStack.clear().child(vue);
        return this;
    }

    public StatusProgress loadingText(CharSequence text) {
        loadingText = text;
        return this;
    }

    public StatusProgress emptyText(CharSequence text) {
        emptyText = text;
        return this;
    }

    public StatusProgress errorText(CharSequence text) {
        errorText = text;
        return this;
    }

    public StatusProgress retryText(CharSequence text) {
        retryText = text;
        return this;
    }

    public StatusProgress loadingText(int resId) {
        loadingText = view.getContext().getString(resId);
        return this;
    }

    public StatusProgress emptyText(int resId) {
        emptyText = view.getContext().getString(resId);
        return this;
    }

    public StatusProgress errorText(int resId) {
        errorText = view.getContext().getString(resId);
        return this;
    }

    public StatusProgress retryText(int resId) {
        retryText = view.getContext().getString(resId);
        return this;
    }

    public StatusProgress loadingText(LiveData<String> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, s -> loadingText = s);
        return this;
    }

    public StatusProgress emptyText(LiveData<String> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, s -> emptyText = s);
        return this;
    }

    public StatusProgress errorText(LiveData<String> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, s -> errorText = s);
        return this;
    }

    public StatusProgress retryText(LiveData<String> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, s -> retryText = s);
        return this;
    }

    public StatusProgress emptyImage(@DrawableRes int emptyImage) {
        emptyImageRes = emptyImage;
        return this;
    }

    public StatusProgress errorImage(@DrawableRes int errorImage) {
        errorImageRes = errorImage;
        return this;
    }

    public StatusProgress imageGravity(int gravity) {
        imageGravity = gravity;
        return this;
    }

    public StatusProgress textColor(@ColorRes int colorRes) {
        mTextVue.textColorRes(colorRes);
        return this;
    }

    public StatusProgress textSize(float size) {
        mTextVue.textSize(size);
        return this;
    }

    public StatusProgress font(@FontRes int fontResId) {
        mTextVue.font(fontResId);
        mRetryButton.font(fontResId);
        return this;
    }

    public StatusProgress progressColor(int color) {
        if (mProgressVue != null)
            mProgressVue.progressColor(color);
        return this;
    }

    public StatusProgress loadingDelay(long delay) {
        this.loadingDelay = delay;
        return this;
    }

    public StatusProgress onRetryClick(OnClickListener listener) {
        mRetryButton.onClick(listener);
        return this;
    }

    public StatusProgress mainTitle(com.kaiserdavar.androidui.OnWidgetListener<Text> listener) {
        listener.onWidget(mTextVue);
        return this;
    }

    public StatusProgress retryButton(com.kaiserdavar.androidui.OnWidgetListener<Button> listener) {
        listener.onWidget(mRetryButton);
        return this;
    }

    public StatusProgress status(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::status);
        return this;
    }
    public StatusProgress status(int status) {
        if (status == mStatus)
            return this;
        mStatus = status;
        if (loadingDelay > 0 && status == STATUS_LOADING) {
            setStatusLoadingDelay();
        } else {
            setStatus(status);
        }
        return this;
    }

    private void setStatusLoadingDelay() {
        setStatus(STATUS_DONE);
        mHandler.postDelayed(() -> {
            if (mStatus == STATUS_LOADING && !mProgressStack.isVisible())
                setStatus(STATUS_LOADING);
        }, loadingDelay);
    }

    private void setStatus(int status) {
        CharSequence text = null;
        int imageRes = 0;
        switch (status) {
            case STATUS_LOADING:
                text = loadingText;
                imageRes = 0;
                break;
            case STATUS_EMPTY:
                text = emptyText;
                imageRes = emptyImageRes;
                break;
            case STATUS_ERROR:
                text = errorText;
                imageRes = errorImageRes;
                break;
            case STATUS_DONE:
                text = "";
                imageRes = 0;
                break;
        }

        if (text != null)
            mTextVue.text(text);

        if (imageRes != 0) {
            switch (imageGravity) {
                case Gravity.TOP:
                    mTextVue.drawableTop(imageRes);
                    break;
                case Gravity.BOTTOM:
                    mTextVue.drawableBottom(imageRes);
                    break;
                case Gravity.START:
                    mTextVue.drawableStart(imageRes);
                    break;
                case Gravity.END:
                    mTextVue.drawableEnd(imageRes);
            }
        } else {
            mTextVue.noDrawable();
        }

        visibility(status != STATUS_DONE);
        mTextVue.visibility(text != null || imageRes != 0);
        mProgressStack.visibility(status == STATUS_LOADING);
        mRetryButton.visibility(status == STATUS_ERROR).text(retryText);

        int i = 0;
        for (Vue vue : children) {
            if (vue.view().getVisibility() == View.VISIBLE) {
                int m = i > 0 ? 16 : 0;
                ((ViewGroup.MarginLayoutParams) vue.view().getLayoutParams()).topMargin = px(m);
                i++;
            }
        }
    }

}
