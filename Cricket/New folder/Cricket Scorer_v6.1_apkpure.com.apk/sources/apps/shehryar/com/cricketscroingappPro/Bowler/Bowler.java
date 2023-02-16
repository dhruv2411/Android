package apps.shehryar.com.cricketscroingappPro.Bowler;

import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Tournament.TournamentEntity;
import io.fabric.sdk.android.services.common.IdManager;
import java.io.Serializable;
import java.util.ArrayList;

public class Bowler extends Player implements Serializable, TournamentEntity {
    public static final long serialVersionUID = 42;
    double average;
    private int balls;
    String best;
    private long bowlerID;
    private int bowlerovers;
    private int dots;
    String eco;
    public int fiveFor;
    public int fourFor;
    private int fours;
    private int maidens;
    int matches;
    private String name;
    private int noballs;
    private int nos;
    private String rightorleft;
    private int score;
    int singleMatchBalls;
    int singleMatchEco;
    int singleMatchMaidens;
    int singleMatchOvers;
    int singleMatchRuns;
    int singleMatchWickets;
    private int sixes;
    private String statsHeading;
    private int statsType;
    String teamName;
    private ArrayList<String> teamNames;
    String teamoppname;
    public int threeFor;
    private int totalscore;
    private int totalwickets;
    private int wickets;
    private int wides;

    public ArrayList<String> getTeamNames() {
        return this.teamNames;
    }

    public void setTeamNames(ArrayList<String> arrayList) {
        this.teamNames = arrayList;
    }

    public Bowler() {
    }

    public Bowler(Bowler bowler) {
        this.bowlerID = bowler.getBowlerID();
        this.balls = bowler.getBalls();
        this.best = bowler.getBest();
        this.bowlerovers = bowler.getBowlerovers();
        this.dots = bowler.getDots();
        this.eco = bowler.getEco();
        this.fiveFor = bowler.getFiveFor();
        this.fourFor = bowler.getFourFor();
        this.wickets = bowler.getWickets();
        this.totalwickets = bowler.getTotalwickets();
        this.statsType = bowler.getStatsType();
        this.name = bowler.getName();
        setImage(bowler.getImage());
        setTeamNames(bowler.getTeamNames());
    }

    public int getDots() {
        return this.dots;
    }

    public void setDots(int i) {
        this.dots += i;
    }

    public void resetDots() {
        this.dots = 0;
    }

    public int getFours() {
        return this.fours;
    }

    public void setFours(int i) {
        this.fours += i;
    }

    public void resetFours() {
        this.fours = 0;
    }

    public int getSixes() {
        return this.sixes;
    }

    public void setSixes(int i) {
        this.sixes += i;
    }

    public void resetSixes() {
        this.sixes = 0;
    }

    public int getNos() {
        return this.nos;
    }

    public void setNos(int i) {
        this.nos += i;
    }

    public void resetNos() {
        this.nos = 0;
    }

    public int getThreeFor() {
        return this.threeFor;
    }

    public void setThreeFor(int i) {
        this.threeFor += i;
    }

    public int getFourFor() {
        return this.fourFor;
    }

    public void setFourFor(int i) {
        this.fourFor += i;
    }

    public int getFiveFor() {
        return this.fiveFor;
    }

    public void setFiveFor(int i) {
        this.fiveFor += i;
    }

    public int getSingleMatchBalls() {
        return this.singleMatchBalls;
    }

    public void setSingleMatchBalls(int i) {
        this.singleMatchBalls = i;
    }

    public int getSingleMatchOvers() {
        return this.singleMatchOvers;
    }

    public void setSingleMatchOvers(int i) {
        this.singleMatchOvers = i;
    }

    public int getSingleMatchRuns() {
        return this.singleMatchRuns;
    }

    public void setSingleMatchRuns(int i) {
        this.singleMatchRuns = i;
    }

    public int getSingleMatchWickets() {
        return this.singleMatchWickets;
    }

    public void setSingleMatchWickets(int i) {
        this.singleMatchWickets = i;
    }

    public int getSingleMatchMaidens() {
        return this.singleMatchMaidens;
    }

    public void setSingleMatchMaidens(int i) {
        this.singleMatchMaidens = i;
    }

    public String getSingleMatchEco() {
        float singleMatchRuns2 = ((float) getSingleMatchRuns()) / (((((float) getSingleMatchOvers()) * 6.0f) + ((float) getSingleMatchBalls())) / 6.0f);
        return Float.isNaN(singleMatchRuns2) ? IdManager.DEFAULT_VERSION_NAME : String.format("%.1f", new Object[]{Float.valueOf(singleMatchRuns2)});
    }

    public void setSingleMatchEco(int i) {
        this.singleMatchEco = i;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String str) {
        this.teamName = str;
        super.setTeamName(str);
    }

    public String getEco() {
        getTotalscore();
        getBowlerovers();
        getBalls();
        float totalscore2 = ((float) getTotalscore()) / (((((float) getBowlerovers()) * 6.0f) + ((float) getBalls())) / 6.0f);
        return Float.isNaN(totalscore2) ? IdManager.DEFAULT_VERSION_NAME : String.format("%.1f", new Object[]{Float.valueOf(totalscore2)});
    }

    public void setEco(String str) {
        this.eco = str;
    }

    public String getAverage() {
        if (Double.isNaN(this.average)) {
            return IdManager.DEFAULT_VERSION_NAME;
        }
        return String.format("%.1f", new Object[]{Double.valueOf(this.average)});
    }

    public void setAverage(double d) {
        this.average = d;
    }

    public String getBest() {
        return this.best;
    }

    public void setBest(String str) {
        this.best = str;
    }

    public int getMatches() {
        return this.matches;
    }

    public void setMatches(int i) {
        this.matches = i;
    }

    public long getBowlerID() {
        return this.bowlerID;
    }

    public void setBowlerID(long j) {
        this.bowlerID = j;
    }

    public int getBalls() {
        return this.balls;
    }

    public void setBalls(int i) {
        this.balls += i;
    }

    public void resetBalls() {
        this.balls = 0;
    }

    public int getMaidens() {
        return this.maidens;
    }

    public void setMaidens(int i) {
        this.maidens += i;
    }

    public int getBowlerovers() {
        return this.bowlerovers;
    }

    public void setBowlerovers(int i) {
        this.bowlerovers += i;
    }

    public String getName() {
        return this.name;
    }

    public String getStats(int i) {
        return getWickets() + "";
    }

    public void setStatsType(int i) {
        this.statsType = i;
    }

    public int getStatsType() {
        return this.statsType;
    }

    public String getImgUrl() {
        return getImage();
    }

    public void setHeading(String str) {
        this.statsHeading = str;
    }

    public String getHeading() {
        return this.statsHeading;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getNoballs() {
        return this.noballs;
    }

    public void setNoballs(int i) {
        this.noballs = i;
    }

    public String getRightorleft() {
        return this.rightorleft;
    }

    public void setRightorleft(String str) {
        this.rightorleft = str;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public int getTotalscore() {
        return this.totalscore;
    }

    public void setTotalscore(int i) {
        this.totalscore += i;
    }

    public int getTotalwickets() {
        return this.totalwickets;
    }

    public void setTotalwickets(int i) {
        this.totalwickets = i;
    }

    public int getWickets() {
        return this.wickets;
    }

    public void setWickets(int i) {
        this.wickets += i;
    }

    public int getWides() {
        return this.wides;
    }

    public void setWides(int i) {
        this.wides += i;
    }

    public void resetWides() {
        this.wides = 0;
    }
}
