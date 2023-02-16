package apps.shehryar.com.cricketscroingappPro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Match.PreviousMatchesListAdapter;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class Fragment_Tournament_Matches extends Fragment {
    Activity activity;
    Context context;
    DBHelper dbHelper;
    int index;
    Intent intent;
    ListView listView;
    ArrayList<Match> matches;
    ExpandedListView matcheslist;
    TextView noMatches;
    RecyclerView recyclerView;
    ArrayList<Team> team1scores;
    ArrayList<Team> team1stats;
    ArrayList<Team> team2scores;
    ArrayList<Team> team2stats;
    ArrayList<Team> teamNames;

    public Fragment_Tournament_Matches() {
    }

    @SuppressLint({"ValidFragment"})
    public Fragment_Tournament_Matches(Context context2, Activity activity2, ArrayList<Match> arrayList, ArrayList<Team> arrayList2, ArrayList<Team> arrayList3, ArrayList<Team> arrayList4, ArrayList<Team> arrayList5, ArrayList<Team> arrayList6) {
        this.context = context2;
        this.activity = activity2;
        this.activity = activity2;
        this.matches = arrayList;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_your_matches_list, viewGroup, false);
        inflate.findViewById(R.id.progressBar).setVisibility(8);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.batsmanrecyclerview);
        this.noMatches = (TextView) inflate.findViewById(R.id.no_matches_text);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (this.matches == null) {
            this.noMatches.setVisibility(8);
        } else if (this.matches.isEmpty()) {
            this.noMatches.setVisibility(0);
        }
        try {
            this.recyclerView.setAdapter(new PreviousMatchesListAdapter(getContext(), this.activity, this.matches));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }
}
