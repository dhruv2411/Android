package apps.shehryar.com.cricketscroingappPro.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Adapters.AdapterListItemNavigation;
import apps.shehryar.com.cricketscroingappPro.Listeners.OnListItemClickListener;
import apps.shehryar.com.cricketscroingappPro.Model.NavigationItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

public class FragmentNavigationDrawer extends BaseFragment {
    public static final String NAVIGATION_ITEMS = "navigation_items";
    ArrayList<NavigationItem> navigationItems;
    OnListItemClickListener onListItemClickListener;
    @BindView(2131755562)
    RecyclerView rvNavigation;

    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof OnListItemClickListener) {
            this.onListItemClickListener = (OnListItemClickListener) getActivity();
        }
    }

    public static FragmentNavigationDrawer newInstance(ArrayList<NavigationItem> arrayList) {
        FragmentNavigationDrawer fragmentNavigationDrawer = new FragmentNavigationDrawer();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NAVIGATION_ITEMS, arrayList);
        fragmentNavigationDrawer.setArguments(bundle);
        return fragmentNavigationDrawer;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.navigationItems = (ArrayList) getArguments().getSerializable(NAVIGATION_ITEMS);
        }
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_navigation_drawer, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        return inflate;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        AdapterListItemNavigation adapterListItemNavigation = new AdapterListItemNavigation(this.navigationItems, this.onListItemClickListener);
        this.rvNavigation.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rvNavigation.setAdapter(adapterListItemNavigation);
    }
}
