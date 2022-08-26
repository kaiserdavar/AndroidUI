package com.kaiserdavar.androidui.stack;

import android.content.Context;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.Vue;

public abstract class BaseCStack<T, M extends ConstraintLayout> extends BaseStack<T, M> {

    public BaseCStack(M view) {
        super(view);
    }

    public BaseCStack(Context context) {
        super(context);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected M onGetMainView(Context context) {
        return (M) new ConstraintLayout(context);
    }

    @Override
    protected void onAddChild(Vue child) {
        View childView = child.view();
        view.addView(childView, child.getConstraintLp());

        Vue[] vues = null;
        if (child instanceof CStackFlow)
            vues = ((CStackFlow) child).vues;
        else if (child instanceof CStackGroup)
            vues = ((CStackGroup) child).vues;
        if (vues != null) {
            for (Vue vue : vues) {
                addChild(vue);
            }
        }
    }

    public T horizontalChainChild(int chainStyle, BaseVue... children) {
        int count = children.length;
        for (int i = 0; i < count; i++) {
            BaseVue child = children[i];
            if (i > 0)
                child.startToEndOf(children[i - 1].view().getId());
            if (i < count - 1)
                child.endToStartOf(children[i + 1].view().getId());
            if (i == 0)
                child.horizontalChain(chainStyle);
            addChild(child);
        }
        return t;
    }

    public T verticalChainChild(int chainStyle, BaseVue... children) {
        int count = children.length;
        for (int i = 0; i < count; i++) {
            BaseVue child = children[i];
            if (i > 0)
                child.topToBottomOf(children[i - 1].view().getId());
            if (i < count - 1)
                child.bottomToTopOf(children[i + 1].view().getId());
            if (i == 0)
                child.verticalChain(chainStyle);
            addChild(child);
        }
        return t;
    }
    public T HorizontalLinearChild(BaseVue... children) {
        int count = children.length;
        for (int i = 0; i < count; i++) {
            BaseVue child = children[i];
            if (i > 0)
                child.startToEndOf(children[i - 1].view().getId());
            addChild(child);
        }
        return t;
    }
    public T verticalLinearChild(BaseVue... children) {
        int count = children.length;
        for (int i = 0; i < count; i++) {
            BaseVue child = children[i];
            if (i > 0)
                child.topToBottomOf(children[i - 1].view().getId());
            addChild(child);
        }
        return t;
    }

    public T constraint(ConstraintSet constraintSet) {
        constraintSet.applyTo(view);
        return t;
    }

    /*private void setLayoutParamsTo(BaseVue child) {
        int w = child.mSize[0] == ViewGroup.LayoutParams.MATCH_PARENT ? 0 : child.mSize[0];
        int h = child.mSize[1] == ViewGroup.LayoutParams.MATCH_PARENT ? 0 : child.mSize[1];
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(w, h);
        int[] m = child.mMargin;
        params.setMarginStart(m[0]);
        params.topMargin = m[1];
        params.setMarginEnd(m[2]);
        params.bottomMargin = m[3];

        params.topToTop = child.tt;
        params.topToBottom = child.tb;
        params.bottomToTop = child.bt;
        params.bottomToBottom = child.bb;
        params.startToStart = child.ss;
        params.startToEnd = child.se;
        params.endToStart = child.es;
        params.endToEnd = child.ee;

        params.horizontalBias = child.horBias;
        params.verticalBias = child.verBias;

        params.horizontalWeight = child.horWeight;
        params.verticalWeight = child.verWeight;

        params.horizontalChainStyle = child.horChain;
        params.verticalChainStyle = child.verChain;

        params.circleConstraint = child.cc;
        params.circleAngle = child.circleAngle;
        params.circleRadius = child.circleRadius;

        params.constrainedWidth = child.constrainedWidth;
        params.constrainedHeight = child.constrainedHeight;


        child.view().setLayoutParams(params);
        int[] p = child.mPadding;
        child.view().setPaddingRelative(p[0], p[1], p[2], p[3]);
    }*/

    /*private void setConstraintOf(BaseVue child) {
        ConstraintSet set = new ConstraintSet();
        set.clone(view);
        if (child.tt != 0)
            set.connect(child.view().getId(), ConstraintSet.TOP, child.tt, ConstraintSet.TOP);
        if (child.tb != 0)
            set.connect(child.view().getId(), ConstraintSet.TOP, child.tb, ConstraintSet.BOTTOM);
        if (child.bt != 0)
            set.connect(child.view().getId(), ConstraintSet.BOTTOM, child.bt, ConstraintSet.TOP);
        if (child.bb != 0)
            set.connect(child.view().getId(), ConstraintSet.BOTTOM, child.bb, ConstraintSet.BOTTOM);
        if (child.ss != 0)
            set.connect(child.view().getId(), ConstraintSet.START, child.ss, ConstraintSet.START);
        if (child.se != 0)
            set.connect(child.view().getId(), ConstraintSet.START, child.se, ConstraintSet.END);
        if (child.es != 0)
            set.connect(child.view().getId(), ConstraintSet.END, child.es, ConstraintSet.START);
        if (child.ee != 0)
            set.connect(child.view().getId(), ConstraintSet.END, child.ee, ConstraintSet.END);
        if (child.horBias > 0)
            set.setHorizontalBias(child.view().getId(), child.horBias);
        if (child.verBias > 0)
            set.setVerticalBias(child.view().getId(), child.verBias);
        set.applyTo(view);
    }*/

}
