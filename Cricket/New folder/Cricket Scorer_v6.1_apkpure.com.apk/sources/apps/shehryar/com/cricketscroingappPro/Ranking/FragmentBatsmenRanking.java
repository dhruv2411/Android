package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class FragmentBatsmenRanking extends Fragment {
    BatsmanRankingListItemAdapter adapter;
    ArrayList<Team> allTeams;
    ArrayList<Long> allteamsIds;
    ArrayList<Batsman> batsmen;
    RecyclerView batsmenRankingRecyclerView;
    ProgressDialog loading = null;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_batsmen_rankings, viewGroup, false);
        this.batsmenRankingRecyclerView = (RecyclerView) inflate.findViewById(R.id.batsmen_ranking_recyclerView);
        this.batsmen = new ArrayList<>();
        this.allTeams = (ArrayList) getArguments().getSerializable("allTeams");
        this.allteamsIds = (ArrayList) getArguments().getSerializable("allTeamsIds");
        new LoadBatsmenRanking().execute(new Void[0]);
        return inflate;
    }

    public class LoadBatsmenRanking extends AsyncTask<Void, ArrayList<Batsman>, Void> {
        public LoadBatsmenRanking() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            try {
                FragmentBatsmenRanking.this.loading = new ProgressDialog(FragmentBatsmenRanking.this.getActivity());
                FragmentBatsmenRanking.this.loading.setCancelable(true);
                FragmentBatsmenRanking.this.loading.setMessage("Ranking Batsmen. Please Wait.");
                FragmentBatsmenRanking.this.loading.setProgressStyle(0);
                FragmentBatsmenRanking.this.loading.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            Iterator<Team> it = FragmentBatsmenRanking.this.allTeams.iterator();
            while (it.hasNext()) {
                Team next = it.next();
                Iterator<Player> it2 = next.getPlayers().iterator();
                while (it2.hasNext()) {
                    Batsman yourBatsmanStats = new DBHelper(FragmentBatsmenRanking.this.getActivity()).getYourBatsmanStats(next.getName(), it2.next().getName(), FragmentBatsmenRanking.this.allteamsIds);
                    if (yourBatsmanStats != null) {
                        FragmentBatsmenRanking.this.batsmen.add(yourBatsmanStats);
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            Collections.sort(FragmentBatsmenRanking.this.batsmen, new Comparator<Batsman>() {
                public int compare(Batsman batsman, Batsman batsman2) {
                    return batsman2.getPoints() - batsman.getPoints();
                }
            });
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < FragmentBatsmenRanking.this.batsmen.size(); i2++) {
                if (FragmentBatsmenRanking.this.batsmen.get(i2).getMatchid() > 0 && FragmentBatsmenRanking.this.batsmen.get(i2).getTotalScore() > 0) {
                    arrayList.add(FragmentBatsmenRanking.this.batsmen.get(i2));
                }
            }
            while (i < arrayList.size()) {
                i++;
                ((Batsman) arrayList.get(i)).setRanking(i);
            }
            FragmentBatsmenRanking.this.loading.dismiss();
            FragmentBatsmenRanking.this.adapter = new BatsmanRankingListItemAdapter(FragmentBatsmenRanking.this.getActivity(), arrayList);
            FragmentBatsmenRanking.this.batsmenRankingRecyclerView.setLayoutManager(new LinearLayoutManager(FragmentBatsmenRanking.this.getActivity()));
            FragmentBatsmenRanking.this.batsmenRankingRecyclerView.setItemAnimator(new DefaultItemAnimator());
            FragmentBatsmenRanking.this.batsmenRankingRecyclerView.setItemViewCacheSize(20);
            FragmentBatsmenRanking.this.batsmenRankingRecyclerView.setDrawingCacheEnabled(true);
            FragmentBatsmenRanking.this.batsmenRankingRecyclerView.setDrawingCacheQuality(1048576);
            FragmentBatsmenRanking.this.batsmenRankingRecyclerView.setAdapter(FragmentBatsmenRanking.this.adapter);
        }
    }
}
