package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import apps.shehryar.com.CheckBoxListItem;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Adapters.AdapterCheckBoxListItem;
import apps.shehryar.com.cricketscroingappPro.Fragments.BaseDialogFragment;
import apps.shehryar.com.cricketscroingappPro.Listeners.CheckBoxListItemClickListener;
import apps.shehryar.com.cricketscroingappPro.Listeners.FragmentChooseItemListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

public class FragmentChooseItems extends BaseDialogFragment implements CheckBoxListItemClickListener {
    private static final String DIALOG_TITLE = "dialog_title";
    private static final String LIST_ITEMS = "list_items";
    @BindView(2131755554)
    public Button bCancel;
    @BindView(2131755555)
    public Button bOk;
    ArrayList<CheckBoxListItem> checkBoxListItems;
    String dialogTitle;
    FragmentChooseItemListener fragmentChooseItemListener;
    @BindView(2131755553)
    public RecyclerView rvOptions;
    ArrayList<Integer> selectedIndexes;
    @BindView(2131755552)
    public TextView tvTitle;

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentChooseItemListener) {
            this.fragmentChooseItemListener = (FragmentChooseItemListener) context;
        }
    }

    public static FragmentChooseItems newInstance(String str, ArrayList<? extends CheckBoxListItem> arrayList) {
        FragmentChooseItems fragmentChooseItems = new FragmentChooseItems();
        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_TITLE, str);
        bundle.putSerializable(LIST_ITEMS, arrayList);
        fragmentChooseItems.setArguments(bundle);
        return fragmentChooseItems;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.dialogTitle = getArguments().getString(DIALOG_TITLE);
            this.checkBoxListItems = (ArrayList) getArguments().getSerializable(LIST_ITEMS);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_choose_items, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.selectedIndexes = new ArrayList<>();
        if (this.fragmentChooseItemListener == null && (getActivity() instanceof FragmentChooseItemListener)) {
            this.fragmentChooseItemListener = (FragmentChooseItemListener) getActivity();
        }
        if (Build.VERSION.SDK_INT >= 17 && this.fragmentChooseItemListener == null && (getParentFragment() instanceof FragmentChooseItemListener)) {
            this.fragmentChooseItemListener = (FragmentChooseItemListener) getParentFragment();
        }
        this.bOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (int i = 0; i < FragmentChooseItems.this.checkBoxListItems.size(); i++) {
                    if (FragmentChooseItems.this.checkBoxListItems.get(i).getCheckBoxListItem().isChecked()) {
                        FragmentChooseItems.this.selectedIndexes.add(Integer.valueOf(i));
                    }
                }
                if (FragmentChooseItems.this.fragmentChooseItemListener != null) {
                    FragmentChooseItems.this.fragmentChooseItemListener.onItemsChosen(FragmentChooseItems.this.selectedIndexes);
                }
                FragmentChooseItems.this.dismiss();
            }
        });
        this.bCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentChooseItems.this.dismiss();
            }
        });
        this.tvTitle.setText(this.dialogTitle);
        AdapterCheckBoxListItem adapterCheckBoxListItem = new AdapterCheckBoxListItem(getActivity(), this.checkBoxListItems, this);
        this.rvOptions.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rvOptions.setAdapter(adapterCheckBoxListItem);
        return inflate;
    }

    public void onCheckBoxClicked(int i, boolean z, CheckBoxListItem checkBoxListItem) {
        this.checkBoxListItems.get(i).setChecked(z);
    }
}
