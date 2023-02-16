package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class FragmentTeamRanking extends Fragment {
    TeamRankingListItemAdapter adapter;
    ArrayList<Team> allTeams;
    ArrayList<Long> allTeamsIds;
    RecyclerView batsmenRankingRecyclerView;
    ProgressDialog loading = null;
    int matchesType;
    TextView tvName;
    TextView tvTitle;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_batsmen_rankings, viewGroup, false);
        this.batsmenRankingRecyclerView = (RecyclerView) inflate.findViewById(R.id.batsmen_ranking_recyclerView);
        this.tvTitle = (TextView) inflate.findViewById(R.id.ranking_fragment_title);
        this.tvName = (TextView) inflate.findViewById(R.id.ranking_fragment_name);
        FontProvider.setSeguUIFont(getActivity(), this.tvTitle);
        this.tvTitle.setText("TEAMS RANKING");
        this.tvName.setText("Team");
        this.allTeams = new ArrayList<>();
        this.allTeamsIds = (ArrayList) getArguments().getSerializable("allTeamsIds");
        this.matchesType = getArguments().getInt("matchType");
        new LoadTeamRanking().execute(new Void[0]);
        return inflate;
    }

    public class LoadTeamRanking extends AsyncTask<Void, ArrayList<Team>, Void> {
        public LoadTeamRanking() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            try {
                FragmentTeamRanking.this.loading = new ProgressDialog(FragmentTeamRanking.this.getActivity());
                FragmentTeamRanking.this.loading.setCancelable(true);
                FragmentTeamRanking.this.loading.setMessage("Ranking Teams. Please Wait.");
                FragmentTeamRanking.this.loading.setProgressStyle(0);
                FragmentTeamRanking.this.loading.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            try {
                FragmentTeamRanking.this.allTeams = new DBHelper(FragmentTeamRanking.this.getActivity()).get_All_Teams_Stats(FragmentTeamRanking.this.matchesType, (String) null);
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("exception loading teams", e.toString());
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            if (FragmentTeamRanking.this.allTeams == null) {
                FragmentTeamRanking.this.allTeams = new ArrayList<>();
            }
            int i = 0;
            while (i < FragmentTeamRanking.this.allTeams.size()) {
                i++;
                FragmentTeamRanking.this.allTeams.get(i).setRanking(i);
            }
            FragmentTeamRanking.this.loading.dismiss();
            try {
                FragmentTeamRanking.this.adapter = new TeamRankingListItemAdapter(FragmentTeamRanking.this.getActivity(), FragmentTeamRanking.this.allTeams);
                FragmentTeamRanking.this.batsmenRankingRecyclerView.setLayoutManager(new LinearLayoutManager(FragmentTeamRanking.this.getActivity()));
                FragmentTeamRanking.this.batsmenRankingRecyclerView.setItemAnimator(new DefaultItemAnimator());
                FragmentTeamRanking.this.batsmenRankingRecyclerView.setItemViewCacheSize(20);
                FragmentTeamRanking.this.batsmenRankingRecyclerView.setDrawingCacheEnabled(true);
                FragmentTeamRanking.this.batsmenRankingRecyclerView.setDrawingCacheQuality(1048576);
                FragmentTeamRanking.this.batsmenRankingRecyclerView.setAdapter(FragmentTeamRanking.this.adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
