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
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class FragmentBowlersRanking extends Fragment {
    BowlerRankingListItemAdapter adapter;
    ArrayList<Team> allTeams;
    /* access modifiers changed from: private */
    public ArrayList<Long> allteamsIds;
    RecyclerView batsmenRankingRecyclerView;
    ArrayList<Bowler> bowlers;
    ProgressDialog loading = null;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_bowlers_ranking, viewGroup, false);
        this.batsmenRankingRecyclerView = (RecyclerView) inflate.findViewById(R.id.bowler_ranking_recyclerView);
        this.bowlers = new ArrayList<>();
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
                FragmentBowlersRanking.this.loading = new ProgressDialog(FragmentBowlersRanking.this.getActivity());
                FragmentBowlersRanking.this.loading.setCancelable(true);
                FragmentBowlersRanking.this.loading.setMessage("Ranking Bowlers. Please Wait.");
                FragmentBowlersRanking.this.loading.setProgressStyle(0);
                FragmentBowlersRanking.this.loading.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            Bowler bowler;
            Iterator<Team> it = FragmentBowlersRanking.this.allTeams.iterator();
            while (it.hasNext()) {
                Team next = it.next();
                Iterator<Player> it2 = next.getPlayers().iterator();
                while (it2.hasNext()) {
                    try {
                        bowler = new DBHelper(FragmentBowlersRanking.this.getActivity()).getYourBowlerStats(next.getName(), it2.next().getName(), FragmentBowlersRanking.this.allteamsIds);
                    } catch (Exception e) {
                        e.printStackTrace();
                        bowler = null;
                    }
                    if (bowler != null) {
                        FragmentBowlersRanking.this.bowlers.add(bowler);
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            Collections.sort(FragmentBowlersRanking.this.bowlers, new Comparator<Bowler>() {
                public int compare(Bowler bowler, Bowler bowler2) {
                    return bowler2.getPoints() - bowler.getPoints();
                }
            });
            FragmentBowlersRanking.this.loading.dismiss();
            ArrayList arrayList = new ArrayList();
            Iterator<Bowler> it = FragmentBowlersRanking.this.bowlers.iterator();
            while (it.hasNext()) {
                Bowler next = it.next();
                if (next.getPoints() != 0) {
                    arrayList.add(next);
                }
            }
            int i = 0;
            while (i < arrayList.size()) {
                i++;
                ((Bowler) arrayList.get(i)).setRanking(i);
            }
            FragmentBowlersRanking.this.adapter = new BowlerRankingListItemAdapter(FragmentBowlersRanking.this.getActivity(), arrayList);
            FragmentBowlersRanking.this.batsmenRankingRecyclerView.setLayoutManager(new LinearLayoutManager(FragmentBowlersRanking.this.getActivity()));
            FragmentBowlersRanking.this.batsmenRankingRecyclerView.setItemAnimator(new DefaultItemAnimator());
            FragmentBowlersRanking.this.batsmenRankingRecyclerView.setItemViewCacheSize(20);
            FragmentBowlersRanking.this.batsmenRankingRecyclerView.setDrawingCacheEnabled(true);
            FragmentBowlersRanking.this.batsmenRankingRecyclerView.setDrawingCacheQuality(1048576);
            FragmentBowlersRanking.this.batsmenRankingRecyclerView.setAdapter(FragmentBowlersRanking.this.adapter);
        }
    }
}
