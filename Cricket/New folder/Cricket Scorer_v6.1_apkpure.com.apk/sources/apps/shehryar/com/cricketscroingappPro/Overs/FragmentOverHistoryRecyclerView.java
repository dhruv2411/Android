package apps.shehryar.com.cricketscroingappPro.Overs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.CustomOverHistoryListViewAdapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import java.util.ArrayList;

public class FragmentOverHistoryRecyclerView extends Fragment {
    ArrayList<Over> oversArrayList;
    RecyclerView recyclerView;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recycler_view_fragment, viewGroup, false);
        this.oversArrayList = (ArrayList) getArguments().getSerializable(DBHelper.TABLE_OVERS);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setItemViewCacheSize(20);
        this.recyclerView.setDrawingCacheEnabled(true);
        this.recyclerView.setDrawingCacheQuality(524288);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.recyclerView.setAdapter(new CustomOverHistoryListViewAdapter(getActivity(), this.oversArrayList));
    }
}
