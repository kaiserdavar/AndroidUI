package com.kaiserdavar.androidui.style;

import android.text.TextUtils;

import java.util.Random;

public class Color {

    public static final int WHITE = 0xFF_ffffff;
    public static final int BLACK = 0xFF_000000;
    public static final int TRANSPARENT = 0x00_000000;


    public static int format(String code) {
        if (TextUtils.isEmpty(code))
            return android.graphics.Color.WHITE;
        return android.graphics.Color.parseColor(code.startsWith("#") ? code.trim() : "#" + code.trim());
    }

    public static int adjustAlpha(int color, float factor) {
        int alpha = Math.round(android.graphics.Color.alpha(color) * factor);
        int red = android.graphics.Color.red(color);
        int green = android.graphics.Color.green(color);
        int blue = android.graphics.Color.blue(color);
        return android.graphics.Color.argb(alpha, red, green, blue);
    }

    public static int getAColor() {
        Random rnd = new Random();
        return android.graphics.Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public static boolean isDark(int color){
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = android.graphics.Color.blue(color);

        double darkness = 1 - (0.299 * red + 0.587 * green + 0.114 * blue) / 255;
        if (darkness < 0.5) {
            return false; // It's a light color
        } else {
            return true; // It's a dark color
        }
    }

}
