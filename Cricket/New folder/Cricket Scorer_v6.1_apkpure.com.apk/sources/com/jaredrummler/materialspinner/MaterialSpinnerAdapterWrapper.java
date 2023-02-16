package com.jaredrummler.materialspinner;

import android.content.Context;
import android.widget.ListAdapter;
import java.util.ArrayList;
import java.util.List;

final class MaterialSpinnerAdapterWrapper extends MaterialSpinnerBaseAdapter {
    private final ListAdapter listAdapter;

    public MaterialSpinnerAdapterWrapper(Context context, ListAdapter listAdapter2) {
        super(context);
        this.listAdapter = listAdapter2;
    }

    public int getCount() {
        return this.listAdapter.getCount() - 1;
    }

    public Object getItem(int i) {
        if (i >= getSelectedIndex()) {
            return this.listAdapter.getItem(i + 1);
        }
        return this.listAdapter.getItem(i);
    }

    public Object get(int i) {
        return this.listAdapter.getItem(i);
    }

    public List<Object> getItems() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getCount(); i++) {
            arrayList.add(getItem(i));
        }
        return arrayList;
    }
}
