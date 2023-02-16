package apps.shehryar.com.cricketscroingappPro;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanSummaryRecyclerViewAdapter;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerSummaryRecyclerViewAdapter;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import java.util.ArrayList;

public class TopThreePlayersFragment extends Fragment {
    private static final String BATSMEN = "batsmen";
    private static final String BOWLER = "bowler";
    RecyclerView batsmanRecyclerView;
    ArrayList<Batsman> batsmen;
    RecyclerView bowlerRecyclerView;
    ArrayList<Bowler> bowlers;

    public static TopThreePlayersFragment newInstance(ArrayList<Batsman> arrayList, ArrayList<Bowler> arrayList2) {
        TopThreePlayersFragment topThreePlayersFragment = new TopThreePlayersFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BATSMEN, arrayList);
        bundle.putSerializable(BOWLER, arrayList2);
        topThreePlayersFragment.setArguments(bundle);
        return topThreePlayersFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_top_three_players, viewGroup, false);
        this.batsmanRecyclerView = (RecyclerView) inflate.findViewById(R.id.batsman_recycler_view_fragment);
        this.bowlerRecyclerView = (RecyclerView) inflate.findViewById(R.id.bowler_recycler_view_fragment);
        this.batsmanRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.bowlerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return inflate;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.batsmen = (ArrayList) getArguments().getSerializable(BATSMEN);
        this.bowlers = (ArrayList) getArguments().getSerializable(BOWLER);
        this.batsmen = getTopThreeBatsmen(this.batsmen);
        this.bowlers = getTopThreeBowlers(this.bowlers);
        this.batsmanRecyclerView.setAdapter(new BatsmanSummaryRecyclerViewAdapter(this.batsmen));
        this.bowlerRecyclerView.setAdapter(new BowlerSummaryRecyclerViewAdapter(this.bowlers));
    }

    private ArrayList<Batsman> getTopThreeBatsmen(ArrayList<Batsman> arrayList) {
        UtilityFunctions.sortBatsmen(arrayList);
        ArrayList<Batsman> arrayList2 = new ArrayList<>();
        if (arrayList.size() < 3) {
            return arrayList;
        }
        for (int i = 0; i < 3; i++) {
            arrayList2.add(arrayList.get(i));
        }
        return arrayList2;
    }

    private ArrayList<Bowler> getTopThreeBowlers(ArrayList<Bowler> arrayList) {
        UtilityFunctions.sortBowler(arrayList);
        ArrayList<Bowler> arrayList2 = new ArrayList<>();
        if (arrayList.size() < 3) {
            return arrayList;
        }
        for (int i = 0; i < 3; i++) {
            arrayList2.add(arrayList.get(i));
        }
        return arrayList2;
    }
}
