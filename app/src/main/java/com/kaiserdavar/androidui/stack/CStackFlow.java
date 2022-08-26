package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.constraintlayout.helper.widget.Flow;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.Vue;

import java.util.List;

public class CStackFlow extends BaseVue<CStackFlow, Flow> {

    public Vue[] vues;

    public static CStackFlow create(Context context) {
        return new CStackFlow(context);
    }

    public CStackFlow(Flow view) {
        super(view);
    }

    public CStackFlow(Context context) {
        super(context);
    }

    @Override
    protected Flow onGetMainView(Context context) {
        return new Flow(context);
    }

    @Override
    public CStackFlow horizontalChain(int style) {
        view.setHorizontalStyle(style);
        return this;
    }
    @Override
    public CStackFlow verticalChain(int style) {
        view.setVerticalStyle(style);
        return this;
    }
    public CStackFlow horizontalBias(float bias) {
        view.setHorizontalBias(bias);
        return this;
    }
    public CStackFlow verticalBias(float bias) {
        view.setVerticalBias(bias);
        return this;
    }
    public CStackFlow horizontalGap(int dp) {
        view.setHorizontalGap(px(dp));
        return this;
    }
    public CStackFlow verticalGap(int dp) {
        view.setVerticalGap(px(dp));
        return this;
    }
    public CStackFlow horizontalAlign(int align) {
        view.setHorizontalAlign(align);
        return this;
    }
    public CStackFlow verticalAlign(int align) {
        view.setVerticalAlign(align);
        return this;
    }
    public CStackFlow orientation(int orientation) {
        view.setOrientation(orientation);
        return this;
    }
    public CStackFlow horizontal() {
        view.setOrientation(Flow.HORIZONTAL);
        return this;
    }
    public CStackFlow vertical() {
        view.setOrientation(Flow.VERTICAL);
        return this;
    }
    public CStackFlow wrapMode(int mode) {
        view.setWrapMode(mode);
        return this;
    }
    public CStackFlow wrapNone() {
        view.setWrapMode(Flow.WRAP_NONE);
        return this;
    }
    public CStackFlow wrapChain() {
        view.setWrapMode(Flow.WRAP_CHAIN);
        return this;
    }
    public CStackFlow wrapAligned() {
        view.setWrapMode(Flow.WRAP_ALIGNED);
        return this;
    }
    public CStackFlow maxElementsWrap(int max) {
        view.setMaxElementsWrap(max);
        return this;
    }
    public CStackFlow refs(int... ids) {
        view.setReferencedIds(ids);
        return this;
    }
    public CStackFlow childRefs(Vue... vues) {
        this.vues = vues;
        int[] ids = new int[vues.length];
        for (int i = 0; i < vues.length; i++) {
            ids[i] = vues[i].view().getId();
        }
        view.setReferencedIds(ids);
        return this;
    }
    public CStackFlow childRefs(List<Vue> vues) {
        return childRefs(vues.toArray(new Vue[0]));
    }
}
