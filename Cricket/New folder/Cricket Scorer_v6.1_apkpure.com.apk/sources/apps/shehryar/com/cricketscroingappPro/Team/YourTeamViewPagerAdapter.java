package apps.shehryar.com.cricketscroingappPro.Team;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import apps.shehryar.com.cricketscroingappPro.FragmentTournaments;
import apps.shehryar.com.cricketscroingappPro.FragmentYourBatting;
import apps.shehryar.com.cricketscroingappPro.FragmentYourMatches;
import apps.shehryar.com.cricketscroingappPro.Fragment_Other_Team_Names;
import apps.shehryar.com.cricketscroingappPro.Fragment_YourBowling_List;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Ranking.RankingFragment;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import java.util.ArrayList;

public class YourTeamViewPagerAdapter extends FragmentStatePagerAdapter {
    Activity activity;
    ArrayList<Long> allTeamsIds;
    ArrayList<Team> all_team_players;
    Context context;
    int matchType;
    ArrayList<Match> matches;
    ArrayList<Long> odiTeamsIds;
    ArrayList<String> playernames;
    ArrayList<String> team_names;
    ArrayList<Long> testTeamsIds;
    private ArrayList<String> tournament_names;
    Team yourTeam;

    public int getCount() {
        return 6;
    }

    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return "Matches";
            case 1:
                return "Your Batting";
            case 2:
                return "Your Bowling";
            case 3:
                return "Tournaments";
            case 4:
                return "All Teams Stats";
            case 5:
                return "Rankings";
            default:
                return null;
        }
    }

    public YourTeamViewPagerAdapter(FragmentManager fragmentManager, Activity activity2, Context context2, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        super(fragmentManager);
        this.context = context2;
        this.playernames = arrayList;
        this.team_names = arrayList2;
        this.activity = activity2;
    }

    public YourTeamViewPagerAdapter(FragmentManager fragmentManager, Activity activity2, Context context2, Team team, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<Team> arrayList3, ArrayList<Match> arrayList4, int i) {
        super(fragmentManager);
        this.context = context2;
        this.playernames = arrayList;
        this.team_names = arrayList2;
        this.all_team_players = arrayList3;
        this.yourTeam = team;
        this.activity = activity2;
        this.matches = arrayList4;
        this.matchType = i;
        try {
            this.allTeamsIds = UtilityFunctions.getTeamIds(arrayList4, i);
        } catch (Exception e) {
            e.printStackTrace();
            this.allTeamsIds = new ArrayList<>();
        }
    }

    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new FragmentYourMatches(this.context, this.activity, this.matches);
            case 1:
                return new FragmentYourBatting(this.context, this.playernames, this.yourTeam.getName(), this.yourTeam, this.allTeamsIds);
            case 2:
                return new Fragment_YourBowling_List(this.context, this.playernames, this.yourTeam.getName(), this.yourTeam, this.allTeamsIds);
            case 3:
                return new FragmentTournaments(this.context, this.activity, this.team_names, this.playernames, this.all_team_players, this.matchType);
            case 4:
                return new Fragment_Other_Team_Names(this.context, this.team_names, this.all_team_players, this.allTeamsIds);
            case 5:
                RankingFragment rankingFragment = new RankingFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("allTeams", this.all_team_players);
                bundle.putSerializable("allTeamsIds", this.allTeamsIds);
                bundle.putSerializable("matchType", Integer.valueOf(this.matchType));
                rankingFragment.setArguments(bundle);
                return rankingFragment;
            default:
                return null;
        }
    }
}
