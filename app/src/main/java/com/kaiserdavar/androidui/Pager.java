package com.kaiserdavar.androidui;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Px;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.kaiserdavar.androidui.stack.RStackAdapter;

public class Pager extends BaseVue<Pager, ViewPager2> {

    private RecyclerView.Adapter adapter;
    private RStackAdapter.OnItemTypeListener onItemTypeListener;
    private RStackAdapter.OnChildListener onChildListener;
    private RStackAdapter.OnBindListener onBindListener;
    private RStackAdapter.OnItemCountListener onItemCountListener;

    private boolean isChanging;

    public static Pager create(Context context) {
        return new Pager(context);
    }

    public Pager(ViewPager2 view) {
        super(view);
    }

    public Pager(Context context) {
        super(context);
    }

    @Override
    protected ViewPager2 onGetMainView(Context context) {
        return new ViewPager2(context);
    }

    @SuppressWarnings("rawtypes")
    public Pager adapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        view.setAdapter(adapter);
        return this;
    }

    public Pager onItemCount(RStackAdapter.OnItemCountListener listener) {
        onItemCountListener = listener;
        return this;
    }

    public Pager onItemType(RStackAdapter.OnItemTypeListener listener) {
        onItemTypeListener = listener;
        return this;
    }

    public Pager onChild(RStackAdapter.OnChildListener listener) {
        onChildListener = listener;
        return this;
    }

    public Pager onBind(RStackAdapter.OnBindListener listener) {
        onBindListener = listener;
        return this;
    }

    public Pager load() {
        return load(-1);
    }

    @SuppressLint("NotifyDataSetChanged")
    public Pager load(int position) {
        if (adapter == null) {
            adapter = new RStackAdapter()
                    .onItemType(onItemTypeListener)
                    .onItemCount(onItemCountListener)
                    .onChild(onChildListener)
                    .onBind(onBindListener);
            view.setAdapter(adapter);
        } else if (position >= 0) {
            adapter.notifyItemChanged(position);
        } else {
            adapter.notifyDataSetChanged();
        }
        return this;
    }

    public Pager currentPage(MutableLiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::currentPage);
        view.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                changePage(position, liveData);
            }
        });
        return this;
    }
    public Pager currentPage(int page) {
        changePage(page, null);
        return this;
    }

    public Pager direction(int layoutDirection) {
        view.setLayoutDirection(layoutDirection);
        return this;
    }

    public Pager orientation(int orientation) {
        view.setOrientation(orientation);
        return this;
    }
    public Pager horizontal() {
        view.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        return this;
    }

    public Pager vertical() {
        view.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        return this;
    }

    public Pager offScreenPages(int count) {
        view.setOffscreenPageLimit(count);
        return this;
    }

    public Pager transformer(ViewPager2.PageTransformer transformer) {
        view.setPageTransformer(transformer);
        return this;
    }

    public Pager enableUserInput() {
        view.setUserInputEnabled(true);
        return this;
    }
    public Pager disableUserInput() {
        view.setUserInputEnabled(false);
        return this;
    }

    public Pager onPage(ViewPager2.OnPageChangeCallback listener) {
        view.registerOnPageChangeCallback(listener);
        return this;
    }
    public Pager onPageScroll(OnPageScrollListener listener) {
        view.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                listener.onPageScroll(position, positionOffset, positionOffsetPixels);
            }
        });
        return this;
    }
    public Pager onPageSelected(OnPageListener listener) {
        view.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                listener.onPage(position);
            }
        });
        return this;
    }

    private void changePage(int page, MutableLiveData<Integer> liveData) {
        if (isChanging)
            return;
        isChanging = true;
        if (liveData != null)
            liveData.setValue(page);
        else
            view.setCurrentItem(page);
        isChanging = false;
    }

    public interface OnPageScrollListener {
        void onPageScroll(int position, float positionOffset, @Px int positionOffsetPixels);
    }
    public interface OnPageListener {
        void onPage(int position);
    }
}
