package com.kaiserdavar.androidui.stack;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kaiserdavar.androidui.EmptyView;
import com.kaiserdavar.androidui.Vue;

public class RStackAdapter extends RecyclerView.Adapter<RStackAdapter.ViewHolder> {

    private OnItemTypeListener onItemTypeListener;
    private OnChildListener onChildListener;
    private OnBindListener onBindListener;
    private OnItemCountListener onItemCountListener;

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
        return new ViewHolder(EmptyView.create(null));
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

    public RStackAdapter onItemCount(OnItemCountListener listener) {
        onItemCountListener = listener;
        return this;
    }

    public RStackAdapter onItemType(OnItemTypeListener listener) {
        onItemTypeListener = listener;
        return this;
    }

    public RStackAdapter onChild(OnChildListener listener) {
        onChildListener = listener;
        return this;
    }

    public RStackAdapter onBind(OnBindListener listener) {
        onBindListener = listener;
        return this;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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

    public interface OnItemTypeListener {
        int onItemType(int position);
    }

    public interface OnChildListener {
        Vue onChild(int viewType);
    }

    public interface OnBindListener {
        void onBind(ViewHolder holder, int position, int viewType);
    }

    public interface OnItemCountListener {
        int onItemCount();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
