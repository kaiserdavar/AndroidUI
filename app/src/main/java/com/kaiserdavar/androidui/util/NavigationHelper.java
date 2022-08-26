package com.kaiserdavar.androidui.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kaiserdavar.androidui.R;

public class NavigationHelper {

    public static void addFragment(@Nullable Activity activity, @IdRes int containerRes, Fragment fragment) {
        if (activity == null)
            return;
        FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
        ft.add(containerRes, fragment);
        ft.commit();
    }

    public static void removeFragment(@Nullable Activity activity, Fragment fragment) {
        if (activity == null)
            return;
        FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    public static Fragment getFragment(@Nullable Activity activity, @IdRes int containerRes) {
        if (activity == null)
            return null;
        return ((AppCompatActivity)activity).getSupportFragmentManager()
                .findFragmentById(containerRes);
    }


    public static void replaceFragment(@Nullable Activity activity, Fragment fragment) {
        if (activity == null)
            return;
        FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

    public static void replaceFragment(@Nullable Activity activity, @IdRes int containerRes, Fragment fragment) {
        if (activity == null)
            return;
        FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
        ft.replace(containerRes, fragment);
        ft.commit();
    }

    public static void switchFragment(@Nullable Activity activity, @IdRes int containerRes, Fragment fragment) {
        if (activity == null)
            return;
        FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(containerRes, fragment);
        ft.commit();
    }

    public static void showFragment(@Nullable Activity activity, @IdRes int containerRes, Fragment fragment) {
        if (activity == null)
            return;
        FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
        int inAnim = R.anim.sliding_up_animator;
        int outAnim = R.anim.sliding_down_animator;
        ft.setCustomAnimations(inAnim, 0, 0, outAnim);

        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.add(containerRes, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }

    public static void goToFragment(@Nullable Activity activity, Fragment fragment) {
        goToFragment(activity, R.id.container, fragment, fragment.getClass().getSimpleName());
    }

    public static void goToFragment(@Nullable Activity activity, @IdRes int containerRes, Fragment fragment, String backStackName) {
        if (activity == null)
            return;

        FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
        setAnimation(activity, ft, false);

        if (backStackName != null)
            ft.addToBackStack(backStackName);
        ft.replace(containerRes, fragment);
        ft.commit();

    }

    public static void backToFragment(@Nullable Activity activity, @IdRes int containerRes, Fragment fragment, String backStackName) {
        if (activity == null)
            return;

        FragmentTransaction ft = ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction();
        setAnimation(activity, ft, true);

        if (backStackName != null)
            ft.addToBackStack(backStackName);
        ft.replace(containerRes, fragment);
        ft.commit();

        //getFragment(activity, GoalSettingsFragment.class);
    }

    public static void setAnimation(@Nullable Activity activity, FragmentTransaction ft, boolean back) {
        if (activity == null)
            return;
        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity
                .findViewById(android.R.id.content)).getChildAt(0);

        boolean isRtl = false;
        if (rootView != null)
            isRtl = rootView.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        /*boolean isRtl = activity.getResources().getConfiguration()
                .getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;*/

        if (isRtl && back)
            ft.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in_rev, R.anim.slide_out_rev);
        else
            ft.setCustomAnimations(R.anim.slide_in_rev, R.anim.slide_out_rev, R.anim.slide_in, R.anim.slide_out);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFragment(@Nullable Activity activity, Class<T> tClass) {
        return (T) ((AppCompatActivity)activity).getSupportFragmentManager().findFragmentByTag(
                        tClass.getSimpleName());
    }

    public static void removeAllFragments(@Nullable Activity activity) {
        if (activity == null)
            return;
        ((AppCompatActivity)activity).getSupportFragmentManager()
                .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}