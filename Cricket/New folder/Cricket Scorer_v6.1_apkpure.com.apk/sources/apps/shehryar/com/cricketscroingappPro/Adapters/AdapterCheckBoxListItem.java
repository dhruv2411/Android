package apps.shehryar.com.cricketscroingappPro.Adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import apps.shehryar.com.CheckBoxListItem;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Listeners.CheckBoxListItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.util.ArrayList;

public class AdapterCheckBoxListItem extends RecyclerView.Adapter<CheckBoxViewHolder> {
    CheckBoxListItemClickListener checkBoxListItemClickListener;
    ArrayList<CheckBoxListItem> checkBoxListItems;
    Context context;

    public class CheckBoxViewHolder_ViewBinding implements Unbinder {
        private CheckBoxViewHolder target;

        @UiThread
        public CheckBoxViewHolder_ViewBinding(CheckBoxViewHolder checkBoxViewHolder, View view) {
            this.target = checkBoxViewHolder;
            checkBoxViewHolder.checkBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.checkBox, "field 'checkBox'", CheckBox.class);
        }

        @CallSuper
        public void unbind() {
            CheckBoxViewHolder checkBoxViewHolder = this.target;
            if (checkBoxViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            checkBoxViewHolder.checkBox = null;
        }
    }

    public AdapterCheckBoxListItem(Context context2, ArrayList<CheckBoxListItem> arrayList, CheckBoxListItemClickListener checkBoxListItemClickListener2) {
        this.context = context2;
        this.checkBoxListItems = arrayList;
        this.checkBoxListItemClickListener = checkBoxListItemClickListener2;
    }

    public CheckBoxViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CheckBoxViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_checkbox, viewGroup, false));
    }

    public void onBindViewHolder(CheckBoxViewHolder checkBoxViewHolder, final int i) {
        checkBoxViewHolder.checkBox.setText(this.checkBoxListItems.get(i).getCheckBoxText());
        checkBoxViewHolder.checkBox.setChecked(this.checkBoxListItems.get(i).isChecked());
        checkBoxViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AdapterCheckBoxListItem.this.checkBoxListItemClickListener.onCheckBoxClicked(i, z, AdapterCheckBoxListItem.this.checkBoxListItems.get(i).getCheckBoxListItem());
            }
        });
    }

    public int getItemCount() {
        return this.checkBoxListItems.size();
    }

    public class CheckBoxViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131755603)
        CheckBox checkBox;

        public CheckBoxViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
