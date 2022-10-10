package com.kaiserdavar.androidui;

import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

public interface Vue {
    View view();
    View createView();
    Vue findChild(int id);
    Vue findChildDeep(int id);
    Vue getChild(int index);
    ViewGroup.MarginLayoutParams getMarginLp();
    ConstraintLayout.LayoutParams getConstraintLp();
    boolean hasChild();
    int getGravity();
    float getWeight();
}
