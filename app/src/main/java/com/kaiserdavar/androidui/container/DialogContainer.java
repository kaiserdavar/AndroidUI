package com.kaiserdavar.androidui.container;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;

import com.kaiserdavar.androidui.EmptyView;
import com.kaiserdavar.androidui.Vue;

public class DialogContainer extends DialogFragment {

    private int rootWidth;
    private int rootHeight;
    private boolean useRootSize;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Vue vue = onContent();
        rootWidth = vue.marginLp().width;
        rootHeight = vue.marginLp().height;
        return vue.createView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (view.getParent() instanceof View) {
            ((View) view.getParent()).setBackgroundResource(android.R.color.transparent);
        }
        Dialog dialog = getDialog();
        if (dialog != null) {
            init(dialog);
            onDialog(dialog);
        }
    }

    protected Vue onContent() {
        return EmptyView.create(getContext());
    }
    protected void onDialog(Dialog dialog) {

    }
    protected void useRootSize(boolean useRootSize) {
        this.useRootSize = useRootSize;
    }

    public void show(Context context) {
        if (context instanceof AppCompatActivity) {
            show(((AppCompatActivity)context).getSupportFragmentManager(), getClass().getSimpleName());
        }
    }

    public void show(Context context, String tag) {
        if (context instanceof AppCompatActivity) {
            show(((AppCompatActivity)context).getSupportFragmentManager(), tag);
        }
    }

    private void init(Dialog dialog) {
        int width, height;
        if (useRootSize) {
            width = rootWidth;
            height = rootHeight;
        } else {
            width = getCalculatedWidth(dialog);
            height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        dialog.getWindow().setLayout(width, height);
    }

    private int getCalculatedWidth(Dialog dialog) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        dialog.getWindow().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float percent;
        int dpWidth = (int) (displaymetrics.widthPixels / (displaymetrics.densityDpi / 160f));
        if (dpWidth < 500)
            percent = 0.9f;
        else if (dpWidth < 700)
            percent = 0.8f;
        else if (dpWidth < 900)
            percent = 0.7f;
        else
            percent = 0.5f;
        return (int) (displaymetrics.widthPixels * percent);
    }

    protected Context context() {
        return getContext();
    }

    public LifecycleOwner vlo() {
        return getViewLifecycleOwner();
    }

    public @ColorInt int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(requireContext(), resId);
    }

    protected int px(float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics()
        );
    }
}
