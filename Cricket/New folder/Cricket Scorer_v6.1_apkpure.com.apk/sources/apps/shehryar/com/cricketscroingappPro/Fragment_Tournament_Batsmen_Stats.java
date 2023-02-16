package apps.shehryar.com.cricketscroingappPro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHelper;
import apps.shehryar.com.cricketscroingappPro.Batsman.Custom_YourBat_Details_Adapter;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import java.util.ArrayList;

public class Fragment_Tournament_Batsmen_Stats extends Fragment {
    ArrayList<Batsman> batsmen;
    ExpandedListView battinglist;
    Context context;
    DBHelper dbHelper;
    ArrayList<Match> matches;
    ArrayList<Batsman> topScorers;

    public Fragment_Tournament_Batsmen_Stats() {
    }

    @SuppressLint({"ValidFragment"})
    public Fragment_Tournament_Batsmen_Stats(Context context2, ArrayList<Batsman> arrayList) {
        this.context = context2;
        this.topScorers = arrayList;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_tournament_batting_list, viewGroup, false);
        inflate.findViewById(R.id.progressBar).setVisibility(8);
        this.battinglist = (ExpandedListView) inflate.findViewById(R.id.batsmenlistview);
        try {
            BatsmanHelper.sortBatsmanOnScores(this.topScorers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.battinglist.setAdapter(new Custom_YourBat_Details_Adapter(getContext(), R.layout.custom_yourbat_details_adapter, this.topScorers, true, (ArrayList<Long>) null));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return inflate;
    }
}
