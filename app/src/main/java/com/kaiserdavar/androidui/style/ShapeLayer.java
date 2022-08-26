package com.kaiserdavar.androidui.style;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import com.kaiserdavar.androidui.OnShaperListener;
import com.kaiserdavar.androidui.util.Shaper;

import java.util.ArrayList;
import java.util.List;

public class ShapeLayer {

    private final List<Shaper> shaperList = new ArrayList<>();

    public static ShapeLayer create() {
        return new ShapeLayer();
    }

    public ShapeLayer layer(OnShaperListener listener) {
        Shaper shaper = new Shaper();
        listener.onShaper(shaper);
        shaperList.add(shaper);
        return this;
    }
    public ShapeLayer layer(Shaper shaper) {
        shaperList.add(shaper);
        return this;
    }

    public LayerDrawable getDrawable() {
        Drawable[] drawables = new Drawable[shaperList.size()];
        for (int i = 0; i < drawables.length; i++) {
            drawables[i] = shaperList.get(i).generate();
        }
        return new LayerDrawable(drawables);
    }
}
