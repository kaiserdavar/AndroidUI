package com.kaiserdavar.androidui.container;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class ContainerArg {

    Bundle args;

    public static ContainerArg create() {
        return new ContainerArg();
    }

    public static ContainerArg create(Bundle args) {
        return new ContainerArg(args);
    }

    public ContainerArg() {
        args = new Bundle();
    }

    public ContainerArg(@NonNull Bundle args) {
        this.args = args;
    }

    public ContainerArg arg(String key, int value) {
        args.putInt(key, value);
        return this;
    }
    public ContainerArg arg(String key, int[] value) {
        args.putIntArray(key, value);
        return this;
    }
    public ContainerArg arg(String key, float value) {
        args.putFloat(key, value);
        return this;
    }
    public ContainerArg arg(String key, double value) {
        args.putDouble(key, value);
        return this;
    }
    public ContainerArg arg(String key, String value) {
        args.putString(key, value);
        return this;
    }
    public ContainerArg arg(String key, String[] value) {
        args.putStringArray(key, value);
        return this;
    }
    public ContainerArg arg(String key, ArrayList<String> value) {
        args.putStringArrayList(key, value);
        return this;
    }
    public ContainerArg arg(String key, Serializable value) {
        args.putSerializable(key, value);
        return this;
    }
    public ContainerArg arg(String key, Parcelable value) {
        args.putParcelable(key, value);
        return this;
    }

    public Bundle getBundle() {
        return args;
    }
    
}
