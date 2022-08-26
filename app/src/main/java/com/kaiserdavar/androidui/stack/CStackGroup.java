package com.kaiserdavar.androidui.stack;

import android.content.Context;

import androidx.constraintlayout.widget.Group;

import com.kaiserdavar.androidui.BaseVue;
import com.kaiserdavar.androidui.Vue;

public class CStackGroup extends BaseVue<CStackGroup, Group> {

    public Vue[] vues;

    public static CStackGroup create(Context context) {
        return new CStackGroup(context);
    }

    public CStackGroup(Group view) {
        super(view);
    }

    public CStackGroup(Context context) {
        super(context);
    }

    @Override
    protected Group onGetMainView(Context context) {
        return new Group(context);
    }

    public CStackGroup refs(int... ids) {
        view.setReferencedIds(ids);
        return this;
    }
    public CStackGroup childRefs(Vue... vues) {
        this.vues = vues;
        int[] ids = new int[vues.length];
        for (int i = 0; i < vues.length; i++) {
            ids[i] = vues[i].view().getId();
        }
        view.setReferencedIds(ids);
        return this;
    }

}
