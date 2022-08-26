package com.kaiserdavar.androidui.container;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.kaiserdavar.androidui.EmptyView;
import com.kaiserdavar.androidui.Vue;

public class PopupContainer extends PopupWindow {

    private Context context;

    public PopupContainer(Context context) {
        super(context);
        this.context = context;
        setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    protected Vue onContent() {
        return EmptyView.create(context);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        setContentView(onContent().createView());
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void showAsDropDown(View anchor) {
        setContentView(onContent().createView());
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        setContentView(onContent().createView());
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        setContentView(onContent().createView());
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }
}
