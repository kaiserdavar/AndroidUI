package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.cardview.widget.CardView;

import com.kaiserdavar.androidui.Vue;

public class Card extends BaseStack<Card, CardView> {

    public static Card create(Context context) {
        return new Card(context);
    }

    public Card(CardView view) {
        super(view);
    }

    public Card(Context context) {
        super(context);
    }

    @Override
    protected CardView onGetMainView(Context context) {
        CardView v = new CardView(context);
        return v;
    }

    @Override
    protected void onAddChild(Vue child) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(child.getMarginLp());
        params.gravity = child.getGravity() != -1 ? child.getGravity() : mGravity;
        view.addView(child.view(), params);
    }

    public Card gravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    public Card cardElevation(float elevation) {
        view.setCardElevation(elevation);
        return this;
    }

    public Card cardRadius(float radius) {
        view.setRadius(radius);
        return this;
    }

    public Card preventCornerOverlap(boolean overlap) {
        view.setPreventCornerOverlap(overlap);
        return this;
    }

}
