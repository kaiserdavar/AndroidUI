package com.kaiserdavar.androidui.container;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kaiserdavar.androidui.EmptyView;
import com.kaiserdavar.androidui.Vue;

import java.io.Serializable;
import java.util.ArrayList;

public class SheetContainer extends BottomSheetDialogFragment {

    private Bundle args = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        onDialog(dialog);
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = dialog.getBehavior();
        onBehavior(bottomSheetBehavior);
        return dialog;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onContent().createView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (view.getParent() instanceof View) {
            ((View) view.getParent()).setBackgroundResource(android.R.color.transparent);
        }
    }

    protected Vue onContent() {
        return EmptyView.create(getContext());
    }
    protected void onBehavior(BottomSheetBehavior<FrameLayout> behavior) {

    }
    protected void onDialog(BottomSheetDialog dialog) {

    }

    protected void onMessage(String tag, Bundle bundle) {

    }

    protected void publishMessage(String destinationTag, String tag, Bundle bundle) {
        if (getActivity() instanceof VueActivity) {
            VueActivity activity = (VueActivity) getActivity();
            activity.publishMessage(destinationTag, tag, bundle);
        }
    }
    protected void broadcastMessage(String tag, Bundle bundle) {
        if (getActivity() instanceof VueActivity) {
            VueActivity activity = (VueActivity) getActivity();
            activity.broadcastMessage(tag, bundle);
        }
    }

    public SheetContainer arg(String key, int value) {
        args.putInt(key, value);
        setArguments(args);
        return this;
    }
    public SheetContainer arg(String key, int[] value) {
        args.putIntArray(key, value);
        setArguments(args);
        return this;
    }
    public SheetContainer arg(String key, float value) {
        args.putFloat(key, value);
        setArguments(args);
        return this;
    }
    public SheetContainer arg(String key, double value) {
        args.putDouble(key, value);
        setArguments(args);
        return this;
    }
    public SheetContainer arg(String key, String value) {
        args.putString(key, value);
        setArguments(args);
        return this;
    }
    public SheetContainer arg(String key, String[] value) {
        args.putStringArray(key, value);
        setArguments(args);
        return this;
    }
    public SheetContainer arg(String key, ArrayList<String> value) {
        args.putStringArrayList(key, value);
        setArguments(args);
        return this;
    }
    public SheetContainer arg(String key, Serializable value) {
        args.putSerializable(key, value);
        setArguments(args);
        return this;
    }
    public SheetContainer arg(String key, Parcelable value) {
        args.putParcelable(key, value);
        setArguments(args);
        return this;
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

    protected Context context() {
        return getContext();
    }

    public LifecycleOwner vlo() {
        return getViewLifecycleOwner();
    }

    protected int px(float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics()
        );
    }

}
