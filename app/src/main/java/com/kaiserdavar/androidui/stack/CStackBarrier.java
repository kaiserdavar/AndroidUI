package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.constraintlayout.widget.Barrier;

import com.kaiserdavar.androidui.BaseVue;

public class CStackBarrier extends BaseVue<CStackBarrier, Barrier> {

    public static CStackBarrier create(Context context) {
        return new CStackBarrier(context);
    }

    public CStackBarrier(Barrier view) {
        super(view);
    }

    public CStackBarrier(Context context) {
        super(context);
    }

    @Override
    protected Barrier onGetMainView(Context context) {
        return new Barrier(context);
    }

    public CStackBarrier refs(int... ids) {
        view.setReferencedIds(ids);
        return this;
    }

}
