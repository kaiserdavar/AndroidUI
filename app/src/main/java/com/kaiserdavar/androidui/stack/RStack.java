package com.kaiserdavar.androidui.stack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.EmptyView;
import com.kaiserdavar.androidui.Vue;
import com.kaiserdavar.androidui.util.ActionLiveData;


public class RStack extends BaseVue<RStack, RecyclerView> {

    private RecyclerView.Adapter adapter;
    private OnItemTypeListener onItemTypeListener;
    private OnChildListener onChildListener;
    private OnBindListener onBindListener;
    private OnItemCountListener onItemCountListener;
    //private OnItemClickListener onItemClickListener;

    private int orientation = RecyclerView.VERTICAL;
    private boolean reverseLayout;
    private boolean isGrid;
    private int spanCount;
    private boolean layoutChanged;

    private int visibleItemCount, totalItemCount, pastVisibleItems;

    public RStack(Context context) {
        super(context);
    }

    public static RStack create(Context context) {
        return new RStack(context);
    }

    @Override
    protected RecyclerView onGetMainView(Context context) {
        return new RecyclerView(context);
    }

    public RStack horizontal() {
        orientation = RecyclerView.HORIZONTAL;
        layoutChanged = true;
        return this;
    }

    public RStack vertical() {
        orientation = RecyclerView.VERTICAL;
        layoutChanged = true;
        return this;
    }

    public RStack linear() {
        isGrid = false;
        this.spanCount = 0;
        layoutChanged = true;
        return this;
    }

    public RStack grid(int spanCount) {
        isGrid = true;
        this.spanCount = spanCount;
        layoutChanged = true;
        return this;
    }

    public RStack reverse() {
        this.reverseLayout = true;
        layoutChanged = true;
        return this;
    }

    public RStack reverse(boolean reverseLayout) {
        this.reverseLayout = reverseLayout;
        return this;
    }

    public RStack clipToPadding(boolean clipToPadding) {
        view.setClipToPadding(clipToPadding);
        return this;
    }

    public RStack nestedScrolling(boolean nestedScrolling) {
        view.setNestedScrollingEnabled(nestedScrolling);
        return this;
    }

    public RStack fixedSize(boolean fixedSize) {
        view.setHasFixedSize(fixedSize);
        return this;
    }

    public RStack itemAnimator(RecyclerView.ItemAnimator animator) {
        view.setItemAnimator(animator);
        return this;
    }

    public RStack adapter(Adapter adapter) {
        onItemTypeListener = adapter;
        onChildListener = adapter;
        onBindListener = adapter;
        onItemCountListener = adapter;
        return this;
    }

    public RStack adapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        view.setAdapter(adapter);
        return this;
    }

    public RStack onItemCount(OnItemCountListener listener) {
        onItemCountListener = listener;
        return this;
    }

    public RStack onItemType(OnItemTypeListener listener) {
        onItemTypeListener = listener;
        return this;
    }

    public RStack onChild(OnChildListener listener) {
        onChildListener = listener;
        return this;
    }

    public RStack onBind(OnBindListener listener) {
        onBindListener = listener;
        return this;
    }

    public RStack load() {
        return load(-1);
    }

    public RStack load(ActionLiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::load);
        return this;
    }

    @SuppressLint("NotifyDataSetChanged")
    public RStack load(int position) {
        if (view.getLayoutManager() == null || layoutChanged) {
            layoutChanged = false;
            if (isGrid) {
                view.setLayoutManager(new GridLayoutManager(view.getContext(),
                        spanCount, orientation, reverseLayout));
            } else {
                view.setLayoutManager(new LinearLayoutManager(view.getContext(),
                        orientation, reverseLayout));
            }
        }

        if (adapter == null) {
            adapter = new ItemAdapter();
            view.setAdapter(adapter);
        } else if (position >= 0) {
            adapter.notifyItemChanged(position);
        } else {
            adapter.notifyDataSetChanged();
        }
        return this;
    }

    public RStack inserted(ActionLiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::inserted);
        return this;
    }

    public RStack inserted(int position) {
        adapter.notifyItemInserted(position);
        return this;
    }

    public RStack inserted(int position, int count) {
        if (count == 1)
            adapter.notifyItemInserted(position);
        else if (count > 1)
            adapter.notifyItemRangeInserted(position, count);
        return this;
    }

    public RStack changed(ActionLiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::changed);
        return this;
    }

    public RStack changed(int position) {
        adapter.notifyItemChanged(position);
        return this;
    }

    public RStack changed(int position, int count) {
        if (count == 1)
            adapter.notifyItemChanged(position);
        else if (count > 1)
            adapter.notifyItemRangeChanged(position, count);
        return this;
    }

    public RStack moved(int fromPosition, int toPosition) {
        adapter.notifyItemMoved(fromPosition, toPosition);
        return this;
    }

    public RStack removed(ActionLiveData<Integer> liveData, LifecycleOwner lifecycleOwner) {
        liveData.observe(lifecycleOwner, this::removed);
        return this;
    }

    public RStack removed(int position) {
        adapter.notifyItemRemoved(position);
        return this;
    }

    public RStack removed(int position, int count) {
        if (count == 1)
            adapter.notifyItemRemoved(position);
        else if (count > 1)
            adapter.notifyItemRangeRemoved(position, count);
        return this;
    }

    public int getPositionOf(View rootView) {
        RecyclerView.ViewHolder holder = view.findContainingViewHolder(rootView);
        if (holder != null)
            return holder.getAbsoluteAdapterPosition();
        return -1;
    }

    public RStack onScrollState(OnScrollStateListener listener) {
        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                listener.onState(newState);
            }
        });
        return this;
    }
    public RStack onScroll(OnScrollListener listener) {
        view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                listener.onScroll(dx, dy);
            }
        });
        return this;
    }

    public RStack onScrollEnd(OnScrollEndListener listener) {
        onScroll((dx, dy) -> {
            if (dy <= 0)
                return;
            LinearLayoutManager layoutManager = (LinearLayoutManager) view.getLayoutManager();
            if (layoutManager == null)
                return;
            visibleItemCount = layoutManager.getChildCount();
            totalItemCount = layoutManager.getItemCount();
            pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                listener.onEnd();
            }
        });
        return this;
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

        @Override
        public int getItemViewType(int position) {
            if (onItemTypeListener != null)
                return onItemTypeListener.onItemType(position);
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (onChildListener != null) {
                return new ViewHolder(onChildListener.onChild(viewType));
            }
            return new ViewHolder(EmptyView.create(view.getContext()));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (onBindListener != null) {
                onBindListener.onBind(holder, position, getItemViewType(position));
            }
        }

        @Override
        public int getItemCount() {
            if (onItemCountListener != null)
                return onItemCountListener.onItemCount();
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public Vue vue;

            public ViewHolder(@NonNull Vue vue) {
                super(vue.createView());
                this.vue = vue;
            }

            public <T extends Vue> T find(int id) {
                Vue v = vue.findChildDeep(id);
                if (v != null) {
                    return (T) v;
                }
                return null;
            }
        }
    }

    public interface OnItemTypeListener {
        int onItemType(int position);
    }

    public interface OnChildListener {
        Vue onChild(int viewType);
    }

    public interface OnBindListener {
        void onBind(ItemAdapter.ViewHolder holder, int position, int viewType);
    }

    public interface OnItemCountListener {
        int onItemCount();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public interface OnScrollEndListener {
        void onEnd();
    }

    public interface OnScrollStateListener {
        void onState(int state);
    }
    public interface OnScrollListener {
        void onScroll(int dx, int dy);
    }

    public interface Adapter extends OnItemTypeListener, OnChildListener,
            OnBindListener, OnItemCountListener {
    }


}
