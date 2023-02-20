package com.kaiserdavar.androidui.modifier;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

public interface LayoutModifier<T> {

    T width(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T width(int dp);
    T height(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T height(int dp);
    T size(int widthDp, int heightDp);
    T fullWidth();
    T fullHeight();
    T fullSize();
    T fullWidth(float percent);
    T fullHeight(float percent);
    T fullWidth(int minWidthDp, int maxWidthDp);
    T fullHeight(int minHeightDp, int maxHeightDp);
    T minWidth(int dp);
    T minHeight(int dp);
    T widthPercent(int percent);
    T heightPercent(int percent);

    T margin(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T margin(int dp);
    T marginHorizontal(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T marginHorizontal(int dp);
    T marginVertical(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T marginVertical(int dp);
    T marginTop(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T marginTop(int dp);
    T marginBottom(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T marginBottom(int dp);
    T marginStart(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T marginStart(int dp);
    T marginEnd(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T marginEnd(int dp);

    T padding(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T padding(int dp);
    T paddingHorizontal(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T paddingHorizontal(int dp);
    T paddingVertical(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T paddingVertical(int dp);
    T paddingTop(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T paddingTop(int dp);
    T paddingBottom(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T paddingBottom(int dp);
    T paddingStart(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T paddingStart(int dp);
    T paddingEnd(LiveData<Integer> liveData, LifecycleOwner lifecycleOwner);
    T paddingEnd(int dp);
    
}
