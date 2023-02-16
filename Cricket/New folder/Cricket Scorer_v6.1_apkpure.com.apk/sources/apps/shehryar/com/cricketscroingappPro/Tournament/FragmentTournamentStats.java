package apps.shehryar.com.cricketscroingappPro.Tournament;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Constants;
import java.util.ArrayList;

public class FragmentTournamentStats extends Fragment {
    RecyclerView recyclerView;
    Tournament tournament;
    ArrayList<TournamentEntity> tournamentEntities;

    public static FragmentTournamentStats newInstance(Tournament tournament2) {
        FragmentTournamentStats fragmentTournamentStats = new FragmentTournamentStats();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.PASSED_TOURNAMENT, tournament2);
        fragmentTournamentStats.setArguments(bundle);
        return fragmentTournamentStats;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragemnt_tournament_stats, viewGroup, false);
        this.tournament = (Tournament) getArguments().getSerializable(Constants.PASSED_TOURNAMENT);
        this.tournamentEntities = getTournamentEntities();
        inititalizeViews(inflate);
        loadData();
        return inflate;
    }

    private void inititalizeViews(View view) {
        this.recyclerView = (RecyclerView) view.findViewById(R.id.tournament_stats_recyclerview);
    }

    public void loadData() {
        TournamentStatsAdapter tournamentStatsAdapter = new TournamentStatsAdapter(getActivity(), this.tournamentEntities);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(tournamentStatsAdapter);
    }

    public ArrayList<TournamentEntity> getTournamentEntities() {
        this.tournamentEntities = new ArrayList<>();
        if (this.tournament.getTopScorer() != null) {
            addTournamentEntity("Most Runs", 5, new Batsman(this.tournament.getTopScorer()), this.tournamentEntities);
        }
        if (this.tournament.getTopBowler() != null) {
            addTournamentEntity("Most Wickets", 6, new Bowler(this.tournament.getTopBowler()), this.tournamentEntities);
        }
        if (this.tournament.getMostSixesBatsman() != null) {
            addTournamentEntity("Most Sixes", 3, new Batsman(this.tournament.getMostSixesBatsman()), this.tournamentEntities);
        }
        if (this.tournament.getMostFoursBatsman() != null) {
            addTournamentEntity("Most Fours", 2, new Batsman(this.tournament.getMostFoursBatsman()), this.tournamentEntities);
        }
        if (this.tournament.getHighestScorerTeam() != null) {
            addTournamentEntity("Highest Total", 9, new Team(this.tournament.getHighestScorerTeam()), this.tournamentEntities);
        }
        if (this.tournament.getLowestScorerTeam() != null) {
            addTournamentEntity("Lowest Total", 10, new Team(this.tournament.getLowestScorerTeam()), this.tournamentEntities);
        }
        if (this.tournament.getHighestChaseTeam() != null) {
            addTournamentEntity("Highest Chase", 11, new Team(this.tournament.getHighestChaseTeam()), this.tournamentEntities);
        }
        if (this.tournament.getHighestDefendTeam() != null) {
            addTournamentEntity("Lowest Defend", 12, new Team(this.tournament.getHighestDefendTeam()), this.tournamentEntities);
        }
        GeneralTournamentEntity generalTournamentEntity = new GeneralTournamentEntity("", this.tournament.getTotalSixes() + "", 13, "Total Sixes", (String) null);
        GeneralTournamentEntity generalTournamentEntity2 = new GeneralTournamentEntity("", this.tournament.getTotalFours() + "", 14, "Total Fours", (String) null);
        this.tournamentEntities.add(generalTournamentEntity);
        this.tournamentEntities.add(generalTournamentEntity2);
        return this.tournamentEntities;
    }

    public void addTournamentEntity(String str, int i, TournamentEntity tournamentEntity, ArrayList<TournamentEntity> arrayList) {
        if (tournamentEntity != null) {
            tournamentEntity.setHeading(str);
            tournamentEntity.setStatsType(i);
            arrayList.add(tournamentEntity);
        }
    }
}
