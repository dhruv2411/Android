package apps.shehryar.com.cricketscroingappPro.Tournament;

import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.io.Serializable;

public class Tournament implements Serializable {
    private Batsman fastestFifty;
    private Batsman fastestHundred;
    private Team highestChaseTeam;
    private Team highestDefendTeam;
    private Team highestScorerTeam;
    private Team lowestScorerTeam;
    private Batsman mostFoursBatsman;
    private Batsman mostSixesBatsman;
    private Bowler mostWicketBowler;
    private int noOfMatches;
    private Bowler topBowler;
    private Batsman topScorer;
    private int totalFours;
    private int totalScore;
    private int totalSixes;
    private int totalWickets;

    public Team getHighestChaseTeam() {
        return this.highestChaseTeam;
    }

    public void setHighestChaseTeam(Team team) {
        this.highestChaseTeam = team;
    }

    public Team getHighestDefendTeam() {
        return this.highestDefendTeam;
    }

    public void setHighestDefendTeam(Team team) {
        this.highestDefendTeam = team;
    }

    public Team getHighestScorerTeam() {
        return this.highestScorerTeam;
    }

    public void setHighestScorerTeam(Team team) {
        this.highestScorerTeam = team;
    }

    public Team getLowestScorerTeam() {
        return this.lowestScorerTeam;
    }

    public void setLowestScorerTeam(Team team) {
        this.lowestScorerTeam = team;
    }

    public int getNoOfMatches() {
        return this.noOfMatches;
    }

    public void setNoOfMatches(int i) {
        this.noOfMatches = i;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public void setTotalScore(int i) {
        this.totalScore += i;
    }

    public int getTotalWickets() {
        return this.totalWickets;
    }

    public void setTotalWickets(int i) {
        this.totalWickets += i;
    }

    public int getTotalFours() {
        return this.totalFours;
    }

    public void setTotalFours(int i) {
        this.totalFours += i;
    }

    public int getTotalSixes() {
        return this.totalSixes;
    }

    public void setTotalSixes(int i) {
        this.totalSixes += i;
    }

    public Batsman getMostSixesBatsman() {
        return this.mostSixesBatsman;
    }

    public void setMostSixesBatsman(Batsman batsman) {
        this.mostSixesBatsman = batsman;
    }

    public Bowler getMostWicketBowler() {
        return this.mostWicketBowler;
    }

    public void setMostWicketBowler(Bowler bowler) {
        this.mostWicketBowler = bowler;
    }

    public Batsman getTopScorer() {
        return this.topScorer;
    }

    public void setTopScorer(Batsman batsman) {
        this.topScorer = batsman;
    }

    public Bowler getTopBowler() {
        return this.topBowler;
    }

    public void setTopBowler(Bowler bowler) {
        this.topBowler = bowler;
    }

    public Batsman getMostFoursBatsman() {
        return this.mostFoursBatsman;
    }

    public void setMostFoursBatsman(Batsman batsman) {
        this.mostFoursBatsman = batsman;
    }

    public Batsman getFastestFifty() {
        return this.fastestFifty;
    }

    public void setFastestFifty(Batsman batsman) {
        this.fastestFifty = batsman;
    }

    public Batsman getFastestHundred() {
        return this.fastestHundred;
    }

    public void setFastestHundred(Batsman batsman) {
        this.fastestHundred = batsman;
    }

    public class Stats {
        public static final int BEST_CHASE = 11;
        public static final int BEST_DEFEND = 12;
        public static final int FASTEST_FIFTY = 7;
        public static final int FASTEST_HUNDRED = 8;
        public static final int HIGHEST_TOTAL = 9;
        public static final int LOWEST_TOTAL = 10;
        public static final int MOST_FOURS = 2;
        public static final int MOST_RUNS = 5;
        public static final int MOST_SIXES = 3;
        public static final int MOST_WICKETS = 6;
        public static final int TOTAL_FOURS = 14;
        public static final int TOTAL_SIXES = 13;

        public Stats() {
        }
    }
}
