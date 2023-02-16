package apps.shehryar.com.cricketscroingappPro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
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
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class Fragment_YourBowling_List extends Fragment {
    ArrayList<Long> allTeamIds;
    ArrayList<Bowler> bowler;
    ExpandedListView bowlinglist;
    Context context;
    DBHelper dbHelper;
    ArrayList<Match> matches;
    ArrayList<String> player_names;
    String team_name;
    Team yourTeam;

    public Fragment_YourBowling_List() {
    }

    @SuppressLint({"ValidFragment"})
    public Fragment_YourBowling_List(Context context2, ArrayList<String> arrayList, String str, Team team, ArrayList<Long> arrayList2) {
        this.context = context2;
        this.player_names = arrayList;
        this.team_name = str;
        this.yourTeam = team;
        this.allTeamIds = arrayList2;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_yourbowling_stats, viewGroup, false);
        this.bowlinglist = (ExpandedListView) inflate.findViewById(R.id.bowlerslistview);
        this.dbHelper = new DBHelper(getContext());
        new LoadData().execute(new Void[0]);
        return inflate;
    }

    class LoadData extends AsyncTask<Void, Void, Void> {
        LoadData() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            Fragment_YourBowling_List.this.bowler = new ArrayList<>();
            if (Fragment_YourBowling_List.this.team_name == null || Fragment_YourBowling_List.this.team_name.isEmpty() || Fragment_YourBowling_List.this.player_names == null) {
                return null;
            }
            for (int i = 0; i < Fragment_YourBowling_List.this.player_names.size(); i++) {
                try {
                    Bowler yourBowlerStats = Fragment_YourBowling_List.this.dbHelper.getYourBowlerStats(Fragment_YourBowling_List.this.team_name, Fragment_YourBowling_List.this.player_names.get(i), Fragment_YourBowling_List.this.allTeamIds);
                    if (yourBowlerStats != null) {
                        Fragment_YourBowling_List.this.bowler.add(yourBowlerStats);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            try {
                if (Fragment_YourBowling_List.this.bowler != null && Fragment_YourBowling_List.this.bowler.size() > 0) {
                    BowlerHelper.sortBowlerOnWickets(Fragment_YourBowling_List.this.bowler);
                    Fragment_YourBowling_List.this.bowlinglist.setAdapter(new Custom_YourBowl_Details_Adapter(Fragment_YourBowling_List.this.getContext(), R.layout.custom_yourbat_details_adapter, Fragment_YourBowling_List.this.bowler, Fragment_YourBowling_List.this.team_name, Fragment_YourBowling_List.this.yourTeam, Fragment_YourBowling_List.this.allTeamIds));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
