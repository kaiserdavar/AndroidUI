package com.kaiserdavar.androidui.util;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

import android.util.Log;

/**
 * Created by Ghaisar Davar on 10/17/2016.
 */

public class StopWatch {
    public static final String TAG = "StopWatch";

    private static long time;

    public static void start() {
        time = System.nanoTime();
    }

    public static String stop() {
        long endTime = System.nanoTime();
        long diff = endTime - time;

        long duration;
        String exp;
        if (diff < 1000) {
            exp = diff + " ns";
        } else if (diff < 1000000) {
            duration = NANOSECONDS.toMicros(diff);
            exp = duration + " \u00b5" + "s";
        } else if (diff < 1000000000) {
            duration = NANOSECONDS.toMillis(diff);
            exp = duration + " ms";
        } else {
            duration = NANOSECONDS.toSeconds(diff);
            exp = duration + " S";
        }
        Log.i(TAG, exp);
        return exp;
    }

}
