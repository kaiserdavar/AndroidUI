package com.kaiserdavar.androidui.style;

import java.util.ArrayList;
import java.util.List;

public class ViewState {
    boolean pressed;
    boolean selected;
    boolean enabled;
    boolean disabled;
    boolean checked;
    boolean focused;
    boolean windowFocused;
    boolean activated;
    boolean hovered;
    boolean dragHovered;

    public ViewState pressed() { pressed = true; return this; }
    public ViewState selected() { selected = true; return this; }
    public ViewState enabled() { enabled = true; return this; }
    public ViewState disabled() { disabled = true; return this; }
    public ViewState checked() { checked = true; return this; }
    public ViewState focused() { focused = true; return this; }
    public ViewState windowFocused() { windowFocused = true; return this; }
    public ViewState activated() { activated = true; return this; }
    public ViewState hovered() { hovered = true; return this; }
    public ViewState dragHovered() { dragHovered = true; return this; }

    public List<Integer> getStates() {
        List<Integer> states = new ArrayList<>();
        if (pressed) states.add(android.R.attr.state_pressed);
        if (selected) states.add(android.R.attr.state_selected);
        if (enabled) states.add(android.R.attr.state_enabled);
        if (disabled) states.add(-android.R.attr.state_enabled);
        if (checked) states.add(android.R.attr.state_checked);
        if (focused) states.add(android.R.attr.state_focused);
        if (windowFocused) states.add(android.R.attr.state_window_focused);
        if (activated) states.add(android.R.attr.state_activated);
        if (hovered) states.add(android.R.attr.state_hovered);
        if (dragHovered) states.add(android.R.attr.state_drag_hovered);
        return states;
    }

    public int[] getStateArray() {
        List<Integer> stateList = getStates();
        int[] stateArray = new int[stateList.size()];
        for (int i = 0; i < stateArray.length; i++)
            stateArray[i] = stateList.get(i);
        return stateArray;
    }
}
