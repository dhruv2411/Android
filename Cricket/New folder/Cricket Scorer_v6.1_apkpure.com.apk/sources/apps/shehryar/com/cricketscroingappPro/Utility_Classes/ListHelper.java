package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListHelper {
    public static void sortBatsmanOnPoints(ArrayList<Batsman> arrayList) {
        Collections.sort(arrayList, new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getPoints() - batsman.getPoints();
            }
        });
    }

    public static void sortBatsmanOnScores(ArrayList<Batsman> arrayList) {
        Collections.sort(arrayList, new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getTotalScore() - batsman.getTotalScore();
            }
        });
    }

    public static void sortTeamsOnPoints(ArrayList<Team> arrayList) {
        Collections.sort(arrayList, new Comparator<Team>() {
            public int compare(Team team, Team team2) {
                if (team2.getPoints() == team.getPoints()) {
                    return Double.compare(team2.getNet_Run_Rate(), team.getNet_Run_Rate());
                }
                return team2.getPoints() - team.getPoints();
            }
        });
    }
}
