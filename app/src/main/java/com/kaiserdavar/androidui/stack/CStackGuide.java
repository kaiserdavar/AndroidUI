package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;

import com.kaiserdavar.androidui.BaseVue;

public class CStackGuide extends BaseVue<CStackGuide, Guideline> {

    public static CStackGuide create(Context context) {
        return new CStackGuide(context);
    }

    public CStackGuide(Guideline view) {
        super(view);
    }

    public CStackGuide(Context context) {
        super(context);
    }

    @Override
    protected Guideline onGetMainView(Context context) {
        return new Guideline(context);
    }

    public CStackGuide horizontal() {
        constraintLp.orientation = ConstraintSet.HORIZONTAL_GUIDELINE;
        return this;
    }
    public CStackGuide vertical() {
        constraintLp.orientation = ConstraintSet.VERTICAL_GUIDELINE;
        return this;
    }
    public CStackGuide begin(int dp) {
        constraintLp.guideBegin = px(dp);
        return this;
    }
    public CStackGuide end(int dp) {
        constraintLp.guideEnd = px(dp);
        return this;
    }
    public CStackGuide percent(float value) {
        constraintLp.guidePercent = value;
        return this;
    }


}
