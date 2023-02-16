package apps.shehryar.com.cricketscroingappPro.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Adapters.AdapterSuggestionListItem;
import apps.shehryar.com.cricketscroingappPro.Listeners.OnListItemClickListener;
import apps.shehryar.com.cricketscroingappPro.Model.SuggestionListItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

public class FragmentListDialog extends BaseDialogFragment implements OnListItemClickListener {
    public static final String ON_LIST_ITEM_CLICK_LISTENER = "on_list_item_click_listener";
    public static final String SUGGESTION_LIST_ITEMS = "suggestion_list_item";
    public static final String TITLE = "title";
    FragmentRecyclerView fragmentRecyclerView;
    OnListItemClickListener onListItemClickListener;
    ArrayList suggestionListItems;
    String title;
    @BindView(2131755552)
    TextView tvTitle;

    public void onListItemClicked(int i) {
    }

    public static FragmentListDialog newInstance(String str, ArrayList<SuggestionListItem> arrayList, OnListItemClickListener onListItemClickListener2) {
        FragmentListDialog fragmentListDialog = new FragmentListDialog();
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        bundle.putSerializable(SUGGESTION_LIST_ITEMS, arrayList);
        bundle.putSerializable(ON_LIST_ITEM_CLICK_LISTENER, onListItemClickListener2);
        fragmentListDialog.setArguments(bundle);
        return fragmentListDialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.title = getArguments().getString("title");
            this.suggestionListItems = (ArrayList) getArguments().getSerializable(SUGGESTION_LIST_ITEMS);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_list_dialog, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.tvTitle.setText(this.title);
        this.fragmentRecyclerView = FragmentRecyclerView.newInstance(new AdapterSuggestionListItem(getActivity(), this.suggestionListItems, this), new LinearLayoutManager(getActivity()));
        getFragmentManager().beginTransaction().replace(R.id.ll_contianer, this.fragmentRecyclerView).commit();
        return inflate;
    }
}
