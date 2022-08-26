package com.kaiserdavar.androidui.style;

import android.content.Context;
import android.content.res.ColorStateList;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class ColorSelector {
    private Context context;
    private final List<ViewState> stateList = new ArrayList<>();
    private final List<Integer> colorList = new ArrayList<>();

    public static ColorSelector create() {
        return new ColorSelector(null);
    }
    public static ColorSelector create(Context context) {
        return new ColorSelector(context);
    }

    public ColorSelector(Context context) {
        if (context != null)
            this.context = context.getApplicationContext();
    }

    public ColorSelector normal(@ColorInt int color) {
        addState(color, new ViewState());
        return this;
    }
    public ColorSelector normalRes(@ColorRes int colorRes) {
        return normal(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector pressed(@ColorInt int color) {
        addState(color, new ViewState().pressed());
        return this;
    }
    public ColorSelector pressedRes(@ColorRes int colorRes) {
        return pressed(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector selected(@ColorInt int color) {
        addState(color, new ViewState().selected());
        return this;
    }
    public ColorSelector selectedRes(@ColorRes int colorRes) {
        return selected(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector disabled(@ColorInt int color) {
        addState(color, new ViewState().disabled());
        return this;
    }
    public ColorSelector disabledRes(@ColorRes int colorRes) {
        return disabled(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector enabled(@ColorInt int color) {
        addState(color, new ViewState().enabled());
        return this;
    }
    public ColorSelector enabledRes(@ColorRes int colorRes) {
        return enabled(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector focused(@ColorInt int color) {
        addState(color, new ViewState().focused());
        return this;
    }
    public ColorSelector focusedRes(@ColorRes int colorRes) {
        return focused(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector windowFocused(@ColorInt int color) {
        addState(color, new ViewState().windowFocused());
        return this;
    }
    public ColorSelector windowFocusedRes(@ColorRes int colorRes) {
        return windowFocused(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector checked(@ColorInt int color) {
        addState(color, new ViewState().checked());
        return this;
    }
    public ColorSelector checkedRes(@ColorRes int colorRes) {
        return checked(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector activated(@ColorInt int color) {
        addState(color, new ViewState().activated());
        return this;
    }
    public ColorSelector activatedRes(@ColorRes int colorRes) {
        return activated(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector hovered(@ColorInt int color) {
        addState(color, new ViewState().hovered());
        return this;
    }
    public ColorSelector hoveredRes(@ColorRes int colorRes) {
        return hovered(ContextCompat.getColor(context, colorRes));
    }
    public ColorSelector dragHovered(@ColorInt int color) {
        addState(color, new ViewState().dragHovered());
        return this;
    }
    public ColorSelector dragHoveredRes(@ColorRes int colorRes) {
        return dragHovered(ContextCompat.getColor(context, colorRes));
    }


    public ColorSelector composition(@ColorInt int color, OnStateListener listener) {
        ViewState state = new ViewState();
        listener.onState(state);
        addState(color, state);
        return this;
    }

    private void addState(int color, ViewState state) {
        stateList.add(state);
        colorList.add(color);
    }

    public ColorStateList getColor() {
        int[][] states = new int[stateList.size()][];
        int[] colors = new int[colorList.size()];
        for (int i = 0; i < colors.length; i++) {
            states[i] = stateList.get(i).getStateArray();
            colors[i] = colorList.get(i);
        }
        return new ColorStateList(states, colors);
    }

    public interface OnStateListener {
        void onState(ViewState state);
    }

}
