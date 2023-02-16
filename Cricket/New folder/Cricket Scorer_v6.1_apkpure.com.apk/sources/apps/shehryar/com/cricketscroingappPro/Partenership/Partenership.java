package apps.shehryar.com.cricketscroingappPro.Partenership;

import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.io.Serializable;

public class Partenership implements Serializable {
    public static int allPartenershipScores = 0;
    public static final long serialVersionUID = 42;
    private Batsman batsman1 = new Batsman();
    private Batsman batsman2 = new Batsman();
    long id;
    Match match;
    Team team;
    private int totalPartenership;
    private int totalPartenershipBalls;
    private int totalPartenershipWithOutExtras;

    public static float getPartPercentage(int i, int i2) {
        return (((float) i) * 100.0f) / ((float) i2);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public Match getMatch() {
        return this.match;
    }

    public void setMatch(Match match2) {
        this.match = match2;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team2) {
        this.team = team2;
    }

    public int getTotalPartenershipWithOutExtras() {
        return this.totalPartenershipWithOutExtras;
    }

    public void setTotalPartenershipWithOutExtras(int i) {
        this.totalPartenershipWithOutExtras = i;
    }

    public static int getAllPartenershipScores() {
        return allPartenershipScores;
    }

    public static void setAllPartenershipScores(int i) {
        allPartenershipScores += i;
    }

    public int getTotalPartenershipBalls() {
        return this.totalPartenershipBalls;
    }

    public void setTotalPartenershipBalls(int i) {
        this.totalPartenershipBalls = i;
    }

    public Batsman getBatsman1() {
        return this.batsman1;
    }

    public void setBatsman1(Batsman batsman) {
        this.batsman1 = batsman;
    }

    public Batsman getBatsman2() {
        return this.batsman2;
    }

    public void setBatsman2(Batsman batsman) {
        this.batsman2 = batsman;
    }

    public int getTotalPartenership() {
        return this.totalPartenership;
    }

    public void setTotalPartenership(int i) {
        this.totalPartenership = i;
    }
}
