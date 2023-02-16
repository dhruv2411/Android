package apps.shehryar.com.cricketscroingappPro.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentRecyclerView extends BaseFragment {
    RecyclerView.LayoutManager layoutManager;
    @BindView(2131755586)
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;

    public RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView2) {
        this.recyclerView = recyclerView2;
    }

    public RecyclerView.Adapter getRecyclerViewAdapter() {
        return this.recyclerViewAdapter;
    }

    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        this.recyclerViewAdapter = adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return this.layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager2) {
        this.layoutManager = layoutManager2;
    }

    public static FragmentRecyclerView newInstance(RecyclerView.Adapter adapter, RecyclerView.LayoutManager layoutManager2) {
        FragmentRecyclerView fragmentRecyclerView = new FragmentRecyclerView();
        fragmentRecyclerView.setLayoutManager(layoutManager2);
        fragmentRecyclerView.setRecyclerViewAdapter(adapter);
        return fragmentRecyclerView;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_recyclerview, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setAdapter(this.recyclerViewAdapter);
        return inflate;
    }
}
