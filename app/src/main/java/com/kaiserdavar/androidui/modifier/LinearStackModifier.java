package com.kaiserdavar.androidui.modifier;

public interface LinearStackModifier<T> {
    int getGravity();
    float getWeight();

    T align(int gravity);
    T weight(float weight);
}
