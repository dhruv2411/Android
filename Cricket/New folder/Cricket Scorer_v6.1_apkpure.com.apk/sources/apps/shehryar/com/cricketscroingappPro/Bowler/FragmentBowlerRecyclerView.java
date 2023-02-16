package apps.shehryar.com.cricketscroingappPro.Bowler;

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
import apps.shehryar.com.cricketscroingappPro.Bowler.Dialog_Custom_ListView_Adapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import java.util.ArrayList;

public class FragmentBowlerRecyclerView extends Fragment {
    RecyclerView bowlerRecyclerView;
    ArrayList<Bowler> bowlersArrayList;
    Dialog_Custom_ListView_Adapter.LinearLayoutClickLister clickLister;
    Match match;
    ArrayList<Over> overs;

    public FragmentBowlerRecyclerView(Dialog_Custom_ListView_Adapter.LinearLayoutClickLister linearLayoutClickLister) {
        this.clickLister = linearLayoutClickLister;
    }

    public FragmentBowlerRecyclerView() {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recycler_view_fragment, viewGroup, false);
        this.bowlersArrayList = (ArrayList) getArguments().getSerializable("bowlers");
        this.overs = (ArrayList) getArguments().getSerializable(DBHelper.TABLE_OVERS);
        if (this.overs == null) {
            this.overs = new ArrayList<>();
        }
        this.match = (Match) getArguments().getSerializable(DBHelper.TABLE_MATCH);
        if (this.match == null) {
            this.match = new Match();
        }
        this.bowlerRecyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        this.bowlerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.bowlerRecyclerView.setItemAnimator(new DefaultItemAnimator());
        this.bowlerRecyclerView.setNestedScrollingEnabled(false);
        this.bowlerRecyclerView.setItemViewCacheSize(20);
        this.bowlerRecyclerView.setDrawingCacheEnabled(true);
        this.bowlerRecyclerView.setDrawingCacheQuality(1048576);
        this.bowlerRecyclerView.setAdapter(new Dialog_Custom_ListView_Adapter(getActivity(), this.bowlersArrayList, this.clickLister, this.overs, this.match));
        return inflate;
    }
}
