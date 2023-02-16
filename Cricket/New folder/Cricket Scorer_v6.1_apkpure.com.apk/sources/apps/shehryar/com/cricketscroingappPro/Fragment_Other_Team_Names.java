package apps.shehryar.com.cricketscroingappPro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Team.Other_Team_Activity;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class Fragment_Other_Team_Names extends Fragment {
    ArrayList<Long> allTeamsIds;
    Context context;
    DBHelper dbHelper;
    ArrayList<String> playerNames;
    ArrayList<Team> teamPlayers;
    ListView teamslist;

    public Fragment_Other_Team_Names() {
    }

    @SuppressLint({"ValidFragment"})
    public Fragment_Other_Team_Names(Context context2, ArrayList<String> arrayList, ArrayList<Team> arrayList2, ArrayList<Long> arrayList3) {
        this.context = context2;
        this.playerNames = arrayList;
        this.teamPlayers = arrayList2;
        this.allTeamsIds = arrayList3;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.other_team_names_layout, viewGroup, false);
        this.teamslist = (ListView) inflate.findViewById(R.id.listviewteams);
        this.dbHelper = new DBHelper(getContext());
        try {
            if (this.playerNames.isEmpty()) {
                inflate.findViewById(R.id.tvNoData).setVisibility(0);
                inflate.findViewById(R.id.tvTapInfo).setVisibility(8);
            } else {
                this.teamslist.setAdapter(new Custom_Suggestions_Adapter(getContext(), R.layout.custom_suggestions_layout, this.playerNames));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.teamslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(Fragment_Other_Team_Names.this.getContext(), Other_Team_Activity.class);
                intent.putExtra("index", i);
                intent.putStringArrayListExtra("team_names", Fragment_Other_Team_Names.this.playerNames);
                intent.putExtra("team_players", Fragment_Other_Team_Names.this.teamPlayers);
                intent.putExtra("allTeamsIds", Fragment_Other_Team_Names.this.allTeamsIds);
                Fragment_Other_Team_Names.this.startActivity(intent);
            }
        });
        return inflate;
    }
}
