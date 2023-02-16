package com.jaredrummler.materialspinner;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class MaterialSpinner extends TextView {
    /* access modifiers changed from: private */
    public MaterialSpinnerBaseAdapter adapter;
    private int arrowColor;
    private int arrowColorDisabled;
    private Drawable arrowDrawable;
    private int backgroundColor;
    private int backgroundSelector;
    /* access modifiers changed from: private */
    public boolean hideArrow;
    private int hintColor;
    /* access modifiers changed from: private */
    public String hintText;
    private ListView listView;
    /* access modifiers changed from: private */
    public boolean nothingSelected;
    /* access modifiers changed from: private */
    public OnItemSelectedListener onItemSelectedListener;
    /* access modifiers changed from: private */
    public OnNothingSelectedListener onNothingSelectedListener;
    private PopupWindow popupWindow;
    private int popupWindowHeight;
    private int popupWindowMaxHeight;
    /* access modifiers changed from: private */
    public int selectedIndex;
    /* access modifiers changed from: private */
    public int textColor;

    public interface OnItemSelectedListener<T> {
        void onItemSelected(MaterialSpinner materialSpinner, int i, long j, T t);
    }

    public interface OnNothingSelectedListener {
        void onNothingSelected(MaterialSpinner materialSpinner);
    }

    public MaterialSpinner(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public MaterialSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public MaterialSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void init(Context context, AttributeSet attributeSet) {
        String str;
        int i;
        int i2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialSpinner);
        int defaultColor = getTextColors().getDefaultColor();
        boolean isRtl = Utils.isRtl(context);
        try {
            this.backgroundColor = obtainStyledAttributes.getColor(R.styleable.MaterialSpinner_ms_background_color, -1);
            this.backgroundSelector = obtainStyledAttributes.getResourceId(R.styleable.MaterialSpinner_ms_background_selector, 0);
            this.textColor = obtainStyledAttributes.getColor(R.styleable.MaterialSpinner_ms_text_color, defaultColor);
            this.hintColor = obtainStyledAttributes.getColor(R.styleable.MaterialSpinner_ms_hint_color, defaultColor);
            this.arrowColor = obtainStyledAttributes.getColor(R.styleable.MaterialSpinner_ms_arrow_tint, this.textColor);
            this.hideArrow = obtainStyledAttributes.getBoolean(R.styleable.MaterialSpinner_ms_hide_arrow, false);
            if (obtainStyledAttributes.getString(R.styleable.MaterialSpinner_ms_hint) == null) {
                str = "";
            } else {
                str = obtainStyledAttributes.getString(R.styleable.MaterialSpinner_ms_hint);
            }
            this.hintText = str;
            this.popupWindowMaxHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MaterialSpinner_ms_dropdown_max_height, 0);
            this.popupWindowHeight = obtainStyledAttributes.getLayoutDimension(R.styleable.MaterialSpinner_ms_dropdown_height, -2);
            this.arrowColorDisabled = Utils.lighter(this.arrowColor, 0.8f);
            obtainStyledAttributes.recycle();
            this.nothingSelected = true;
            Resources resources = getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.ms__padding_top);
            if (isRtl) {
                i = resources.getDimensionPixelSize(R.dimen.ms__padding_left);
                i2 = dimensionPixelSize;
            } else {
                i2 = resources.getDimensionPixelSize(R.dimen.ms__padding_left);
                i = dimensionPixelSize;
            }
            setGravity(8388627);
            setClickable(true);
            setPadding(i2, dimensionPixelSize, i, dimensionPixelSize);
            setBackgroundResource(R.drawable.ms__selector);
            if (Build.VERSION.SDK_INT >= 17 && isRtl) {
                setLayoutDirection(1);
                setTextDirection(4);
            }
            if (!this.hideArrow) {
                this.arrowDrawable = Utils.getDrawable(context, R.drawable.ms__arrow).mutate();
                this.arrowDrawable.setColorFilter(this.arrowColor, PorterDuff.Mode.SRC_IN);
                if (isRtl) {
                    setCompoundDrawablesWithIntrinsicBounds(this.arrowDrawable, (Drawable) null, (Drawable) null, (Drawable) null);
                } else {
                    setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.arrowDrawable, (Drawable) null);
                }
            }
            this.listView = new ListView(context);
            this.listView.setId(getId());
            this.listView.setDivider((Drawable) null);
            this.listView.setItemsCanFocus(true);
            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    if (i >= MaterialSpinner.this.selectedIndex && i < MaterialSpinner.this.adapter.getCount() && MaterialSpinner.this.adapter.getItems().size() != 1 && TextUtils.isEmpty(MaterialSpinner.this.hintText)) {
                        i++;
                    }
                    int i2 = i;
                    int unused = MaterialSpinner.this.selectedIndex = i2;
                    boolean unused2 = MaterialSpinner.this.nothingSelected = false;
                    Object obj = MaterialSpinner.this.adapter.get(i2);
                    MaterialSpinner.this.adapter.notifyItemSelected(i2);
                    MaterialSpinner.this.setTextColor(MaterialSpinner.this.textColor);
                    MaterialSpinner.this.setText(obj.toString());
                    MaterialSpinner.this.collapse();
                    if (MaterialSpinner.this.onItemSelectedListener != null) {
                        MaterialSpinner.this.onItemSelectedListener.onItemSelected(MaterialSpinner.this, i2, j, obj);
                    }
                }
            });
            this.popupWindow = new PopupWindow(context);
            this.popupWindow.setContentView(this.listView);
            this.popupWindow.setOutsideTouchable(true);
            this.popupWindow.setFocusable(true);
            if (Build.VERSION.SDK_INT >= 21) {
                this.popupWindow.setElevation(16.0f);
                this.popupWindow.setBackgroundDrawable(Utils.getDrawable(context, R.drawable.ms__drawable));
            } else {
                this.popupWindow.setBackgroundDrawable(Utils.getDrawable(context, R.drawable.ms__drop_down_shadow));
            }
            if (this.backgroundColor != -1) {
                setBackgroundColor(this.backgroundColor);
            } else if (this.backgroundSelector != 0) {
                setBackgroundResource(this.backgroundSelector);
            }
            if (this.textColor != defaultColor) {
                setTextColor(this.textColor);
            }
            this.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                public void onDismiss() {
                    if (MaterialSpinner.this.nothingSelected && MaterialSpinner.this.onNothingSelectedListener != null) {
                        MaterialSpinner.this.onNothingSelectedListener.onNothingSelected(MaterialSpinner.this);
                    }
                    if (!MaterialSpinner.this.hideArrow) {
                        MaterialSpinner.this.animateArrow(false);
                    }
                }
            });
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        this.popupWindow.setWidth(View.MeasureSpec.getSize(i));
        this.popupWindow.setHeight(calculatePopupWindowHeight());
        if (this.adapter != null) {
            CharSequence text = getText();
            String charSequence = text.toString();
            for (int i3 = 0; i3 < this.adapter.getCount(); i3++) {
                String itemText = this.adapter.getItemText(i3);
                if (itemText.length() > charSequence.length()) {
                    charSequence = itemText;
                }
            }
            setText(charSequence);
            super.onMeasure(i, i2);
            setText(text);
            return;
        }
        super.onMeasure(i, i2);
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && isEnabled() && isClickable()) {
            if (!this.popupWindow.isShowing()) {
                expand();
            } else {
                collapse();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        Drawable background = getBackground();
        if (background instanceof StateListDrawable) {
            try {
                Method declaredMethod = StateListDrawable.class.getDeclaredMethod("getStateDrawable", new Class[]{Integer.TYPE});
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                int[] iArr = {Utils.darker(i, 0.85f), i};
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    ((ColorDrawable) declaredMethod.invoke(background, new Object[]{Integer.valueOf(i2)})).setColor(iArr[i2]);
                }
            } catch (Exception e) {
                Log.e("MaterialSpinner", "Error setting background color", e);
            }
        } else if (background != null) {
            background.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        }
        this.popupWindow.getBackground().setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }

    public void setTextColor(int i) {
        this.textColor = i;
        super.setTextColor(i);
    }

    public void setHintColor(int i) {
        this.hintColor = i;
        super.setTextColor(i);
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("state", super.onSaveInstanceState());
        bundle.putInt("selected_index", this.selectedIndex);
        bundle.putBoolean("nothing_selected", this.nothingSelected);
        if (this.popupWindow != null) {
            bundle.putBoolean("is_popup_showing", this.popupWindow.isShowing());
            collapse();
        } else {
            bundle.putBoolean("is_popup_showing", false);
        }
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.selectedIndex = bundle.getInt("selected_index");
            this.nothingSelected = bundle.getBoolean("nothing_selected");
            if (this.adapter != null) {
                if (!this.nothingSelected || TextUtils.isEmpty(this.hintText)) {
                    setTextColor(this.textColor);
                    setText(this.adapter.get(this.selectedIndex).toString());
                } else {
                    setHintColor(this.hintColor);
                    setText(this.hintText);
                }
                this.adapter.notifyItemSelected(this.selectedIndex);
            }
            if (bundle.getBoolean("is_popup_showing") && this.popupWindow != null) {
                post(new Runnable() {
                    public void run() {
                        MaterialSpinner.this.expand();
                    }
                });
            }
            parcelable = bundle.getParcelable("state");
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.arrowDrawable != null) {
            this.arrowDrawable.setColorFilter(z ? this.arrowColor : this.arrowColorDisabled, PorterDuff.Mode.SRC_IN);
        }
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public void setSelectedIndex(int i) {
        if (this.adapter == null) {
            return;
        }
        if (i < 0 || i > this.adapter.getCount()) {
            throw new IllegalArgumentException("Position must be lower than adapter count!");
        }
        this.adapter.notifyItemSelected(i);
        this.selectedIndex = i;
        setText(this.adapter.get(i).toString());
    }

    public void setOnItemSelectedListener(@Nullable OnItemSelectedListener onItemSelectedListener2) {
        this.onItemSelectedListener = onItemSelectedListener2;
    }

    public void setOnNothingSelectedListener(@Nullable OnNothingSelectedListener onNothingSelectedListener2) {
        this.onNothingSelectedListener = onNothingSelectedListener2;
    }

    public <T> void setItems(@NonNull T... tArr) {
        setItems(Arrays.asList(tArr));
    }

    public <T> void setItems(@NonNull List<T> list) {
        this.adapter = new MaterialSpinnerAdapter(getContext(), list).setBackgroundSelector(this.backgroundSelector).setTextColor(this.textColor);
        setAdapterInternal(this.adapter);
    }

    public <T> List<T> getItems() {
        if (this.adapter == null) {
            return null;
        }
        return this.adapter.getItems();
    }

    public void setAdapter(@NonNull ListAdapter listAdapter) {
        this.adapter = new MaterialSpinnerAdapterWrapper(getContext(), listAdapter).setBackgroundSelector(this.backgroundSelector).setTextColor(this.textColor);
        setAdapterInternal(this.adapter);
    }

    public <T> void setAdapter(MaterialSpinnerAdapter<T> materialSpinnerAdapter) {
        this.adapter = materialSpinnerAdapter;
        this.adapter.setTextColor(this.textColor);
        this.adapter.setBackgroundSelector(this.backgroundSelector);
        setAdapterInternal(materialSpinnerAdapter);
    }

    private void setAdapterInternal(@NonNull MaterialSpinnerBaseAdapter materialSpinnerBaseAdapter) {
        materialSpinnerBaseAdapter.setHintEnabled(!TextUtils.isEmpty(this.hintText));
        this.listView.setAdapter(materialSpinnerBaseAdapter);
        if (this.selectedIndex >= materialSpinnerBaseAdapter.getCount()) {
            this.selectedIndex = 0;
        }
        if (materialSpinnerBaseAdapter.getItems().size() <= 0) {
            setText("");
        } else if (!this.nothingSelected || TextUtils.isEmpty(this.hintText)) {
            setTextColor(this.textColor);
            setText(materialSpinnerBaseAdapter.get(this.selectedIndex).toString());
        } else {
            setText(this.hintText);
            setHintColor(this.hintColor);
        }
    }

    public void expand() {
        if (!this.hideArrow) {
            animateArrow(true);
        }
        this.nothingSelected = true;
        this.popupWindow.showAsDropDown(this);
    }

    public void collapse() {
        if (!this.hideArrow) {
            animateArrow(false);
        }
        this.popupWindow.dismiss();
    }

    public void setArrowColor(@ColorInt int i) {
        this.arrowColor = i;
        this.arrowColorDisabled = Utils.lighter(this.arrowColor, 0.8f);
        if (this.arrowDrawable != null) {
            this.arrowDrawable.setColorFilter(this.arrowColor, PorterDuff.Mode.SRC_IN);
        }
    }

    /* access modifiers changed from: private */
    public void animateArrow(boolean z) {
        int i = 10000;
        int i2 = z ? 0 : 10000;
        if (!z) {
            i = 0;
        }
        ObjectAnimator.ofInt(this.arrowDrawable, FirebaseAnalytics.Param.LEVEL, new int[]{i2, i}).start();
    }

    public void setDropdownMaxHeight(int i) {
        this.popupWindowMaxHeight = i;
        this.popupWindow.setHeight(calculatePopupWindowHeight());
    }

    public void setDropdownHeight(int i) {
        this.popupWindowHeight = i;
        this.popupWindow.setHeight(calculatePopupWindowHeight());
    }

    private int calculatePopupWindowHeight() {
        if (this.adapter == null) {
            return -2;
        }
        float dimension = getResources().getDimension(R.dimen.ms__item_height);
        float count = ((float) this.adapter.getCount()) * dimension;
        if (this.popupWindowMaxHeight > 0 && count > ((float) this.popupWindowMaxHeight)) {
            return this.popupWindowMaxHeight;
        }
        if (this.popupWindowHeight == -1 || this.popupWindowHeight == -2 || ((float) this.popupWindowHeight) > count) {
            return (count == 0.0f && this.adapter.getItems().size() == 1) ? (int) dimension : (int) count;
        }
        return this.popupWindowHeight;
    }

    public PopupWindow getPopupWindow() {
        return this.popupWindow;
    }

    public ListView getListView() {
        return this.listView;
    }
}
