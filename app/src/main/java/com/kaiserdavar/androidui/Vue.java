package com.kaiserdavar.androidui;

import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;

public interface Vue {
    View view();
    View createView();
    ViewGroup.MarginLayoutParams marginLp();
    ConstraintLayout.LayoutParams constraintLp();
    int getGravity();
    float getWeight();
    Vue findChild(int id);
    Vue findChildDeep(int id);
    Vue getChild(int index);
    boolean hasChild();
}
