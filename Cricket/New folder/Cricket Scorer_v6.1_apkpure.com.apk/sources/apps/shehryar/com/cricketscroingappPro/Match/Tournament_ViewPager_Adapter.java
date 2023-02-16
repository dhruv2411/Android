package apps.shehryar.com.cricketscroingappPro.Match;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Fragment_Tournament_Batsmen_Stats;
import apps.shehryar.com.cricketscroingappPro.Fragment_Tournament_Matches;
import apps.shehryar.com.cricketscroingappPro.Fragment_Tournament_bowling_list;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Team.Team_players;
import apps.shehryar.com.cricketscroingappPro.Tournament.FragmentPointsTable;
import apps.shehryar.com.cricketscroingappPro.Tournament.FragmentTournamentStats;
import apps.shehryar.com.cricketscroingappPro.Tournament.Tournament;
import java.util.ArrayList;

public class Tournament_ViewPager_Adapter extends FragmentStatePagerAdapter {
    Activity activity;
    ArrayList<Team> allTeamsstats;
    Context context;
    ArrayList<Match> matches;
    ArrayList<String> playernames;
    ArrayList<Team> team1scores;
    ArrayList<Team> team1stats;
    ArrayList<Team> team2scores;
    ArrayList<Team> team2stats;
    ArrayList<Team> teamNames;
    ArrayList<Team_players> teams;
    ArrayList<Bowler> topBowlers;
    ArrayList<Batsman> topScorer;
    String tournamentName;
    Tournament tournamentStats;

    public int getCount() {
        return 5;
    }

    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return "Matches";
            case 1:
                return "Stats";
            case 2:
                return "Batsmen";
            case 3:
                return "Bowlers";
            case 4:
                return "Points Table";
            default:
                return null;
        }
    }

    public Tournament_ViewPager_Adapter(FragmentManager fragmentManager, Activity activity2, Context context2, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<Team_players> arrayList3, ArrayList<Batsman> arrayList4) {
        super(fragmentManager);
        this.context = context2;
        this.playernames = arrayList;
        this.activity = activity2;
        this.topScorer = arrayList4;
        this.teams = arrayList3;
    }

    public Tournament_ViewPager_Adapter(FragmentManager fragmentManager, Activity activity2, Context context2, String str, ArrayList<Match> arrayList, ArrayList<Team> arrayList2, ArrayList<Team> arrayList3, ArrayList<Team> arrayList4, ArrayList<Team> arrayList5, ArrayList<Team> arrayList6, ArrayList<String> arrayList7, ArrayList<Batsman> arrayList8, ArrayList<Bowler> arrayList9, ArrayList<Team> arrayList10, Tournament tournament) {
        super(fragmentManager);
        this.context = context2;
        this.playernames = arrayList7;
        this.activity = activity2;
        this.topScorer = arrayList8;
        this.teamNames = arrayList2;
        this.team1scores = arrayList3;
        this.team2scores = arrayList4;
        this.team1stats = arrayList5;
        this.team2stats = arrayList6;
        this.matches = arrayList;
        this.topBowlers = arrayList9;
        this.allTeamsstats = arrayList10;
        this.tournamentStats = tournament;
        this.tournamentName = str;
    }

    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new Fragment_Tournament_Matches(this.context, this.activity, this.matches, this.teamNames, this.team1scores, this.team2scores, this.team1stats, this.team2stats);
            case 1:
                return FragmentTournamentStats.newInstance(this.tournamentStats);
            case 2:
                return new Fragment_Tournament_Batsmen_Stats(this.context, this.topScorer);
            case 3:
                return new Fragment_Tournament_bowling_list(this.context, this.topBowlers);
            case 4:
                return FragmentPointsTable.newInstance(this.tournamentName);
            default:
                return null;
        }
    }
}
