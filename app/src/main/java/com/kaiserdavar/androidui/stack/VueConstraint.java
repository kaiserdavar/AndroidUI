package com.kaiserdavar.androidui.stack;

import androidx.constraintlayout.widget.ConstraintSet;

import com.kaiserdavar.androidui.BaseVue;

public class VueConstraint {

    private BaseVue vue;

    public int tt;
    public int tb;
    public int bt;
    public int bb;
    public int ss;
    public int se;
    public int es;
    public int ee;

    public VueConstraint(BaseVue vue) {
        this.vue = vue;
    }

    public VueConstraint topToTopOf(int id) {
        tt = id;
        return this;
    }
    public VueConstraint topToBottomOf(int id) {
        tb = id;
        return this;
    }
    public VueConstraint bottomToTopOf(int id) {
        bt = id;
        return this;
    }
    public VueConstraint bottomToBottomOf(int id) {
        bb = id;
        return this;
    }
    public VueConstraint startToStartOf(int id) {
        ss = id;
        return this;
    }
    public VueConstraint startToEndOf(int id) {
        se = id;
        return this;
    }
    public VueConstraint endToStartOf(int id) {
        es = id;
        return this;
    }
    public VueConstraint endToEndOf(int id) {
        ee = id;
        return this;
    }

    public void applyConstraint(ConstraintSet set) {
        if (tt != 0)
            set.connect(vue.view().getId(), ConstraintSet.TOP, tt, ConstraintSet.TOP);
        if (tb != 0)
            set.connect(vue.view().getId(), ConstraintSet.TOP, tb, ConstraintSet.BOTTOM);
        if (bt != 0)
            set.connect(vue.view().getId(), ConstraintSet.BOTTOM, bt, ConstraintSet.TOP);
        if (bb != 0)
            set.connect(vue.view().getId(), ConstraintSet.BOTTOM, bb, ConstraintSet.BOTTOM);
        if (ss != 0)
            set.connect(vue.view().getId(), ConstraintSet.START, ss, ConstraintSet.START);
        if (se != 0)
            set.connect(vue.view().getId(), ConstraintSet.START, se, ConstraintSet.END);
        if (es != 0)
            set.connect(vue.view().getId(), ConstraintSet.END, es, ConstraintSet.START);
        if (ee != 0)
            set.connect(vue.view().getId(), ConstraintSet.END, ee, ConstraintSet.END);
    }
    
}
