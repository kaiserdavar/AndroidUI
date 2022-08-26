package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.constraintlayout.helper.widget.Layer;

import com.kaiserdavar.androidui.BaseVue;

public class CStackLayer extends BaseVue<CStackLayer, Layer> {

    public static CStackLayer create(Context context) {
        return new CStackLayer(context);
    }

    public CStackLayer(Layer view) {
        super(view);
    }

    public CStackLayer(Context context) {
        super(context);
    }

    @Override
    protected Layer onGetMainView(Context context) {
        return new Layer(context);
    }

    public CStackLayer refs(int... ids) {
        view.setReferencedIds(ids);
        return this;
    }

    public CStackLayer rotation(float value) {
        view.setRotation(value);
        return this;
    }
    public CStackLayer translationX(float value) {
        view.setTranslationX(value);
        return this;
    }
    public CStackLayer translationY(float value) {
        view.setTranslationY(value);
        return this;
    }
    public CStackLayer pivotX(float value) {
        view.setPivotX(value);
        return this;
    }
    public CStackLayer pivotY(float value) {
        view.setPivotY(value);
        return this;
    }
    public CStackLayer scaleX(float value) {
        view.setScaleX(value);
        return this;
    }
    public CStackLayer scaleY(float value) {
        view.setScaleY(value);
        return this;
    }

}
