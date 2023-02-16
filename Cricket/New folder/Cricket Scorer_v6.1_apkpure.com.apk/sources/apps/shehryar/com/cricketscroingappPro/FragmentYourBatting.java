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
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHelper;
import apps.shehryar.com.cricketscroingappPro.Batsman.Custom_YourBat_Details_Adapter;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class FragmentYourBatting extends Fragment {
    ArrayList<Long> allTeamIds;
    ArrayList<Batsman> batsmen;
    ExpandedListView battinglist;
    Context context;
    DBHelper dbHelper;
    ArrayList<Match> matches;
    boolean otherTeam;
    ArrayList<String> playerNames;
    String teamName;
    Team yourTeam;

    public FragmentYourBatting() {
    }

    @SuppressLint({"ValidFragment"})
    public FragmentYourBatting(Context context2, ArrayList<String> arrayList, String str, Team team, ArrayList<Long> arrayList2) {
        this.context = context2;
        this.playerNames = arrayList;
        this.teamName = str;
        this.yourTeam = team;
        this.allTeamIds = arrayList2;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_your_batting_list, viewGroup, false);
        this.battinglist = (ExpandedListView) inflate.findViewById(R.id.batsmenlistview);
        this.dbHelper = new DBHelper(getContext());
        new LoadData().execute(new Void[0]);
        return inflate;
    }

    class LoadData extends AsyncTask<Void, Void, Void> {
        LoadData() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            FragmentYourBatting.this.batsmen = new ArrayList<>();
            try {
                if (FragmentYourBatting.this.teamName == null || FragmentYourBatting.this.teamName.isEmpty() || FragmentYourBatting.this.playerNames == null) {
                    return null;
                }
                for (int i = 0; i < FragmentYourBatting.this.playerNames.size(); i++) {
                    FragmentYourBatting.this.batsmen.add(FragmentYourBatting.this.dbHelper.getYourBatsmanStats(FragmentYourBatting.this.teamName, FragmentYourBatting.this.playerNames.get(i), FragmentYourBatting.this.allTeamIds));
                }
                return null;
            } catch (NullPointerException unused) {
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            try {
                if (FragmentYourBatting.this.batsmen != null && FragmentYourBatting.this.batsmen.size() > 0) {
                    BatsmanHelper.sortBatsmanOnScores(FragmentYourBatting.this.batsmen);
                    FragmentYourBatting.this.battinglist.setAdapter(new Custom_YourBat_Details_Adapter(FragmentYourBatting.this.getContext(), R.layout.custom_yourbat_details_adapter, FragmentYourBatting.this.batsmen, FragmentYourBatting.this.teamName, FragmentYourBatting.this.yourTeam, FragmentYourBatting.this.allTeamIds));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
