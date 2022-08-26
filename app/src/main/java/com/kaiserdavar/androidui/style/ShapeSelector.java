package com.kaiserdavar.androidui.style;

import android.graphics.drawable.StateListDrawable;

import com.kaiserdavar.androidui.OnShaperListener;
import com.kaiserdavar.androidui.util.Shaper;

public class ShapeSelector {

    private final StateListDrawable stateListDrawable = new StateListDrawable();

    public static ShapeSelector create() {
        return new ShapeSelector();
    }

    public ShapeSelector normal(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        stateListDrawable.addState(new int[]{}, shaper.generate());
        return this;
    }
    public ShapeSelector pressed(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().pressed().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }
    public ShapeSelector selected(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().selected().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }
    public ShapeSelector disabled(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().disabled().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }
    public ShapeSelector enabled(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().enabled().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }
    public ShapeSelector focused(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().focused().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }
    public ShapeSelector windowFocused(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().windowFocused().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }
    public ShapeSelector checked(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().checked().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }
    public ShapeSelector activated(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().activated().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }
    public ShapeSelector hovered(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().hovered().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }
    public ShapeSelector dragHovered(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        int[] states = new ViewState().dragHovered().getStateArray();
        stateListDrawable.addState(states, shaper.generate());
        return this;
    }

    public ShapeSelector composition(OnShaperListener shapeListener, ColorSelector.OnStateListener stateListener) {
        Shaper shaper = new Shaper();
        shapeListener.onShaper(shaper);
        ViewState state = new ViewState();
        stateListener.onState(state);
        stateListDrawable.addState(state.getStateArray(), shaper.generate());
        return this;
    }

    public StateListDrawable getDrawable() {
        return stateListDrawable;
    }

}
