package apps.shehryar.com.cricketscroingappPro.Team;

import android.app.Activity;
import android.content.Context;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater;
import java.util.ArrayList;

public class TeamDataUpdater {
    BowlerDataUpdater bowlerDataUpdater;
    TeamViewsUpdater teamViewsUpdater;

    public TeamDataUpdater(Context context, Activity activity) {
        this.teamViewsUpdater = new TeamViewsUpdater(context, activity);
        this.bowlerDataUpdater = new BowlerDataUpdater(context, activity);
    }

    public Team changeTeamOversAndBalls(Bowler bowler, Team team, int i, int i2, int i3, boolean z) {
        team.setOverballs(i2);
        if (team.getOverballs() == i3) {
            team.setOversPlayed(1);
            team.setOverballs(0);
        }
        this.teamViewsUpdater.changeTeamOversText(team, z);
        return team;
    }

    public Team updateTeamScoreBallsAndExtras(Team team, int i, int i2, int i3, int i4) {
        team.setScore(i);
        team.setExtras(i2);
        team.setByes(i3);
        team.setLegbyes(i4);
        this.teamViewsUpdater.changeTeamScoreText(team);
        return team;
    }

    public double getTeamRunRate(ArrayList<Team> arrayList) {
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i = 0; i < arrayList.size(); i++) {
            d += (double) arrayList.get(i).getScore();
            if (arrayList.get(i).getWickets() == 10) {
                d2 += (double) arrayList.get(i).getMatchTotalOvers();
            } else {
                d2 += (double) arrayList.get(i).getOversPlayed();
                if (arrayList.get(i).getOverballs() != 6) {
                    d2 += ((double) arrayList.get(i).getOverballs()) / 6.0d;
                }
            }
        }
        return d / d2;
    }
}
