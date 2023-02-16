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
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerHelper;
import apps.shehryar.com.cricketscroingappPro.Bowler.Custom_YourBowl_Details_Adapter;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import java.util.ArrayList;

public class Fragment_Tournament_bowling_list extends Fragment {
    ArrayList<Bowler> bowler;
    ExpandedListView bowlinglist;
    Context context;
    DBHelper dbHelper;
    ArrayList<Match> matches;
    ArrayList<String> player_names;
    String team_name;
    ArrayList<Bowler> topBowlers;

    public Fragment_Tournament_bowling_list() {
    }

    @SuppressLint({"ValidFragment"})
    public Fragment_Tournament_bowling_list(Context context2, ArrayList<Bowler> arrayList) {
        this.context = context2;
        this.topBowlers = arrayList;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_tournament_bowling_list, viewGroup, false);
        inflate.findViewById(R.id.progressBar).setVisibility(8);
        this.bowlinglist = (ExpandedListView) inflate.findViewById(R.id.bowlerslistview);
        try {
            BowlerHelper.sortBowlerOnWickets(this.topBowlers);
            this.bowlinglist.setAdapter(new Custom_YourBowl_Details_Adapter(getContext(), R.layout.custom_yourbat_details_adapter, this.topBowlers, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }
}
