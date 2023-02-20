package com.kaiserdavar.androidui;

import android.content.Context;
import android.view.SurfaceView;

public class Surface extends BaseVue<Surface, SurfaceView> {
    public Surface(SurfaceView view) {
        super(view);
    }

    public Surface(Context context) {
        super(context);
    }

    public static Surface create(Context context) {
        return new Surface(context);
    }

    @Override
    protected SurfaceView onGetMainView(Context context) {
        return new SurfaceView(context);
    }

}
