package com.kaiserdavar.androidui.container;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

import com.kaiserdavar.androidui.R;
import com.kaiserdavar.androidui.EmptyView;
import com.kaiserdavar.androidui.Vue;

import java.io.Serializable;
import java.util.ArrayList;

public class Container extends Fragment {

    private final Bundle args = new Bundle();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onContent().createView();
    }

    protected Vue onContent() {
        return EmptyView.create(getContext());
    }

    protected void onMessage(String tag, Bundle bundle) {

    }

    protected void publishMessage(String destinationTag, String tag, Bundle bundle) {
        if (getActivity() instanceof VueActivity) {
            VueActivity activity = (VueActivity) getActivity();
            activity.publishMessage(destinationTag, tag, bundle);
        }
    }
    protected void broadcastMessage(String tag, Bundle bundle) {
        if (getActivity() instanceof VueActivity) {
            VueActivity activity = (VueActivity) getActivity();
            activity.broadcastMessage(tag, bundle);
        }
    }

    protected void navigate(Container container) {
        navigate(container, null);
    }
    protected void navigate(Container container, String tag) {
        navigate(container, getAnimation(false), tag);
    }
    protected void navigate(Container container, ContainerTransition anim, String tag) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ft.setCustomAnimations(anim.enter, anim.exit, anim.popEnter, anim.popExit);
        ft.addToBackStack(container.getClass().getSimpleName());
        ft.replace(R.id.container, container, tag);
        ft.commit();
    }

    protected void present(Container container) {
        present(container, null);
    }
    protected void present(Container container, String tag) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ft.setCustomAnimations(R.anim.sliding_up_animator, 0, 0, R.anim.sliding_down_animator);
        ft.addToBackStack(container.getClass().getSimpleName());
        ft.add(R.id.container, container, tag);
        ft.commit();
    }
    protected void dismiss() {
        FragmentManager fm = getManager();
        if (fm == null)
            return;
        fm.popBackStack();
    }
    protected void dismissTo(String tag) {
        FragmentManager fm = getManager();
        if (fm == null)
            return;
        fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    protected void dismissAll() {
        FragmentManager fm = getManager();
        if (fm == null)
            return;
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public Container arg(String key, int value) {
        args.putInt(key, value);
        setArguments(args);
        return this;
    }
    public Container arg(String key, int[] value) {
        args.putIntArray(key, value);
        setArguments(args);
        return this;
    }
    public Container arg(String key, float value) {
        args.putFloat(key, value);
        setArguments(args);
        return this;
    }
    public Container arg(String key, double value) {
        args.putDouble(key, value);
        setArguments(args);
        return this;
    }
    public Container arg(String key, String value) {
        args.putString(key, value);
        setArguments(args);
        return this;
    }
    public Container arg(String key, String[] value) {
        args.putStringArray(key, value);
        setArguments(args);
        return this;
    }
    public Container arg(String key, ArrayList<String> value) {
        args.putStringArrayList(key, value);
        setArguments(args);
        return this;
    }
    public Container arg(String key, Serializable value) {
        args.putSerializable(key, value);
        setArguments(args);
        return this;
    }
    public Container arg(String key, Parcelable value) {
        args.putParcelable(key, value);
        setArguments(args);
        return this;
    }



    protected void addFragment(@IdRes int containerRes, Fragment fragment) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ft.add(containerRes, fragment);
        ft.commit();
    }

    protected void removeFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ft.remove(fragment);
        ft.commit();
    }

    protected Fragment getFragment(@IdRes int containerRes) {
        if (getActivity() == null)
            return null;
        return getActivity().getSupportFragmentManager().findFragmentById(containerRes);
    }

    protected void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

    protected void replaceFragment(@IdRes int containerRes, Fragment fragment) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ft.replace(containerRes, fragment);
        ft.commit();
    }

    protected void fadeFragment(@IdRes int containerRes, Fragment fragment) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(containerRes, fragment);
        ft.commit();
    }


    protected void backToFragment(@IdRes int containerRes, Fragment fragment, String backStackName) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ContainerTransition anim = getAnimation(true);
        ft.setCustomAnimations(anim.enter, anim.exit, anim.popEnter, anim.popExit);

        if (backStackName != null)
            ft.addToBackStack(backStackName);
        ft.replace(containerRes, fragment);
        ft.commit();
    }

    protected ContainerTransition getAnimation(boolean back) {
        if (getActivity() == null)
            return null;
        ViewGroup rootView = (ViewGroup) ((ViewGroup) getActivity()
                .findViewById(android.R.id.content)).getChildAt(0);
        boolean isRtl = false;
        if (rootView != null)
            isRtl = rootView.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
        ContainerTransition anim = new ContainerTransition();
        if (isRtl && back) {
            anim.enter = R.anim.slide_in;
            anim.exit = R.anim.slide_out;
            anim.popEnter = R.anim.slide_in_rev;
            anim.popExit = R.anim.slide_out_rev;
        } else {
            anim.enter = R.anim.slide_in_rev;
            anim.exit = R.anim.slide_out_rev;
            anim.popEnter = R.anim.slide_in;
            anim.popExit = R.anim.slide_out;
        }
        return anim;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getFragment(Class<T> tClass) {
        if (getActivity() == null)
            return null;
        return (T) getActivity().getSupportFragmentManager().findFragmentByTag(tClass.getSimpleName());
    }

    protected void removeAllFragments() {
        if (getActivity() == null)
            return;
        getActivity().getSupportFragmentManager().popBackStack(null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    protected FragmentManager getManager() {
        if (getActivity() == null)
            return null;
        return getActivity().getSupportFragmentManager();
    }
    protected FragmentTransaction getFragmentTransaction() {
        if (getActivity() == null)
            return null;
        return getActivity().getSupportFragmentManager().beginTransaction();
    }

    protected Context context() {
        return getContext();
    }

    public LifecycleOwner vlo() {
        return getViewLifecycleOwner();
    }

    public @ColorInt int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(requireContext(), resId);
    }

    protected int px(float dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics()
        );
    }
}
