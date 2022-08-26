package com.kaiserdavar.androidui.container;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kaiserdavar.androidui.R;
import com.kaiserdavar.androidui.Vue;
import com.kaiserdavar.androidui.stack.ZStack;

import java.util.List;

public class VueActivity extends AppCompatActivity {

    private ZStack root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vue vue = onContent(savedInstanceState);
        if (vue == null)
            vue = onContent();
        if (vue != null) {
            setContentView(vue.createView());
            return;
        }
        root = ZStack.create(this)
                .id(R.id.container)
                .fullSize();
        setContentView(root.createView());
        if (savedInstanceState == null) {
            Container container = onInitialContainer();
            if (container != null) {
                addContainer(container);
            }
        }
    }

    protected Vue onContent() {
        return null;
    }
    protected Vue onContent(Bundle data) {
        return null;
    }
    protected Container onInitialContainer() {
        return null;
    }

    protected void publishMessage(String destinationTag, String tag, Bundle bundle) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(destinationTag);
        if (fragment instanceof Container) {
            ((Container) fragment).onMessage(tag, bundle);
        }
    }
    protected void broadcastMessage(String tag, Bundle bundle) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof Container) {
                ((Container) fragment).onMessage(tag, bundle);
            }
        }
    }

    protected void addContainer(Container container) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ft.add(R.id.container, container);
        ft.commit();
    }
    protected void replaceContainer(Container container) {
        FragmentTransaction ft = getFragmentTransaction();
        if (ft == null)
            return;
        ft.replace(R.id.container, container);
        ft.commit();
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
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }
    protected void dismissTo(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    protected void dismissAll() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    protected ContainerTransition getAnimation(boolean back) {
        ViewGroup rootView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content))
                .getChildAt(0);
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

    protected FragmentTransaction getFragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }

}
