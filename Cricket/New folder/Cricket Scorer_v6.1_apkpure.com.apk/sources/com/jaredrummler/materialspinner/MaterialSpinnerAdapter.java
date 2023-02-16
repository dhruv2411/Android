package com.jaredrummler.materialspinner;

import android.content.Context;
import java.util.List;

public class MaterialSpinnerAdapter<T> extends MaterialSpinnerBaseAdapter {
    private final List<T> items;

    public MaterialSpinnerAdapter(Context context, List<T> list) {
        super(context);
        this.items = list;
    }

    public int getCount() {
        int size = this.items.size();
        return (size == 1 || isHintEnabled()) ? size : size - 1;
    }

    public T getItem(int i) {
        if (isHintEnabled()) {
            return this.items.get(i);
        }
        if (i < getSelectedIndex() || this.items.size() == 1) {
            return this.items.get(i);
        }
        return this.items.get(i + 1);
    }

    public T get(int i) {
        return this.items.get(i);
    }

    public List<T> getItems() {
        return this.items;
    }
}
