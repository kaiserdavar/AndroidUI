package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.widget.FrameLayout;

import com.google.android.material.chip.ChipGroup;
import com.kaiserdavar.androidui.Vue;

import java.util.List;

public class ChipStack extends BaseStack<ChipStack, ChipGroup> {

    public static ChipStack create(Context context) {
        return new ChipStack(context);
    }

    public ChipStack(ChipGroup view) {
        super(view);
    }

    public ChipStack(Context context) {
        super(context);
    }

    @Override
    protected ChipGroup onGetMainView(Context context) {
        ChipGroup v = new ChipGroup(context);
        return v;
    }

    @Override
    protected void onAddChild(Vue child) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(child.getMarginLp());
        view.addView(child.view(), params);
    }

    public ChipStack singleLine() {
        view.setSingleLine(true);
        return this;
    }
    public ChipStack singleLine(boolean value) {
        view.setSingleLine(value);
        return this;
    }
    public ChipStack singleSelection(boolean value) {
        view.setSingleSelection(value);
        return this;
    }
    public ChipStack horizontalSpacing(int dp) {
        view.setChipSpacingHorizontal(px(dp));
        return this;
    }
    public ChipStack verticalSpacing(int dp) {
        view.setChipSpacingVertical(px(dp));
        return this;
    }

    public interface OnStackChipItemChildListener {
        void onChild(List<ChipItem> list);
    }
}
