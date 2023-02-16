package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import java.util.ArrayList;

public class FragmentBatsmanHistoryRecyclerView extends Fragment {
    ArrayList<Batsman> batsmanArrayList;
    private Match match;
    RecyclerView recyclerView;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recycler_view_fragment, viewGroup, false);
        this.batsmanArrayList = (ArrayList) getArguments().getSerializable("batsmen");
        this.match = (Match) getArguments().getSerializable(DBHelper.TABLE_MATCH);
        Log.i("batsman arrayList size", this.batsmanArrayList.size() + "");
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setItemViewCacheSize(20);
        this.recyclerView.setDrawingCacheEnabled(true);
        this.recyclerView.setDrawingCacheQuality(1048576);
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.recyclerView.setAdapter(new CustomBatHistoryAdapter(getActivity(), this.batsmanArrayList, this.match));
    }
}
