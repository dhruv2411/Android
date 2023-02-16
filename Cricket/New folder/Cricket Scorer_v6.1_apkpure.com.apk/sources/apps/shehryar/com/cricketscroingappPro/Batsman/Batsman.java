package apps.shehryar.com.cricketscroingappPro.Batsman;

import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Tournament.TournamentEntity;
import com.itextpdf.text.pdf.PdfBoolean;
import io.fabric.sdk.android.services.common.IdManager;
import java.io.Serializable;
import java.util.ArrayList;

public class Batsman extends Player implements Serializable, TournamentEntity {
    public static final long serialVersionUID = 42;
    public ArrayList<Integer> allscoreslist = new ArrayList<>();
    double average;
    private int ballsfaced;
    long batsmanID;
    int best;
    private int dots;
    private int doubles;
    int fifties;
    private int fives;
    private int fours;
    int hundreds = 0;
    int innings;
    boolean lastactivity;
    Match match;
    int matches;
    long matchid;
    private String name;
    private int noOfNotOuts;
    String out = PdfBoolean.FALSE;
    private String outType;
    private int score;
    int singleMatchBalls;
    int singleMatchFours;
    int singleMatchRuns;
    float singleMatchSR;
    int singleMatchSixes;
    private int singles;
    private int sixs;
    int sr;
    private int statsType;
    private float strikerate;
    ArrayList<String> teamNames;
    String team_Name;
    int thirties;
    private int threes;
    private int totalScore;
    private String type;
    public String viewsHeading = null;

    public Batsman() {
    }

    public Batsman(Batsman batsman) {
        setBatsmanID(batsman.getBatsmanID());
        setName(batsman.getName());
        setTotalScore(batsman.getTotalScore());
        setBallsfaced(batsman.getBallsfaced());
        setTeamName(batsman.getTeamName());
        setSingles(batsman.getSingles());
        setDoubles(batsman.getDoubles());
        setThrees(batsman.getThrees());
        setFours(batsman.getFours());
        setFives(batsman.getFives());
        setSixs(batsman.getSixs());
        setImage(batsman.getImage());
        setSingleMatchRuns(batsman.getSingleMatchRuns());
        setSingleMatchFours(batsman.getSingleMatchFours());
        setSingleMatchSixes(batsman.getSingleMatchSixes());
        setSingleMatchBalls(batsman.getSingleMatchBalls());
        setTeamNames(batsman.getTeamNames());
    }

    public Match getMatch() {
        return this.match;
    }

    public void setMatch(Match match2) {
        this.match = match2;
    }

    public int getSingleMatchRuns() {
        return this.singleMatchRuns;
    }

    public void setSingleMatchRuns(int i) {
        this.singleMatchRuns = i;
    }

    public int getSingleMatchBalls() {
        return this.singleMatchBalls;
    }

    public void setSingleMatchBalls(int i) {
        this.singleMatchBalls = i;
    }

    public int getSingleMatchFours() {
        return this.singleMatchFours;
    }

    public void setSingleMatchFours(int i) {
        this.singleMatchFours = i;
    }

    public ArrayList<String> getTeamNames() {
        return this.teamNames;
    }

    public void setTeamNames(ArrayList<String> arrayList) {
        this.teamNames = arrayList;
    }

    public int getSingleMatchSixes() {
        return this.singleMatchSixes;
    }

    public void setSingleMatchSixes(int i) {
        this.singleMatchSixes = i;
    }

    public String getSingleMatchSR() {
        if (this.singleMatchBalls == 0) {
            return IdManager.DEFAULT_VERSION_NAME;
        }
        float singleMatchRuns2 = (((float) getSingleMatchRuns()) / ((float) getSingleMatchBalls())) * 100.0f;
        return Float.isNaN(singleMatchRuns2) ? IdManager.DEFAULT_VERSION_NAME : String.format("%.1f", new Object[]{Float.valueOf(singleMatchRuns2)});
    }

    public void setSingleMatchSR(float f) {
        this.singleMatchSR = f;
    }

    public int getInnings() {
        return this.innings;
    }

    public void setInnings(int i) {
        this.innings = i;
    }

    public void incrementInnings() {
        this.innings++;
    }

    public int getNoOfNotOuts() {
        return this.noOfNotOuts;
    }

    public void setNoOfNotOuts(int i) {
        this.noOfNotOuts = i;
    }

    public int getThirties() {
        return this.thirties;
    }

    public void setThirties(int i) {
        this.thirties += i;
    }

    public int getFifties() {
        return this.fifties;
    }

    public void setFifties(int i) {
        this.fifties += i;
    }

    public int getHundreds() {
        return this.hundreds;
    }

    public void setHundreds(int i) {
        this.hundreds += i;
    }

    public String getTeam_Name() {
        return this.team_Name;
    }

    public void setTeam_Name(String str) {
        this.team_Name = str;
        setTeamName(str);
    }

    public long getMatchid() {
        return this.matchid;
    }

    public void setMatchid(long j) {
        this.matchid = j;
    }

    public String getAverage() {
        return Double.isNaN(this.average) ? IdManager.DEFAULT_VERSION_NAME : String.format("%.1f", new Object[]{Double.valueOf(this.average)});
    }

    public void setAverage(double d) {
        this.average = d;
    }

    public int getSr() {
        return this.sr;
    }

    public void setSr(int i) {
        this.sr = i;
    }

    public int getMatches() {
        return this.matches;
    }

    public void setMatches(int i) {
        this.matches = i;
    }

    public void incrementMatches() {
        this.matches++;
    }

    public long getBatsmanID() {
        return this.batsmanID;
    }

    public void setBatsmanID(long j) {
        this.batsmanID = j;
    }

    public int getBest() {
        return this.best;
    }

    public void setBest(int i) {
        this.best = i;
    }

    public String getStrikerate() {
        if (this.ballsfaced == 0) {
            return IdManager.DEFAULT_VERSION_NAME;
        }
        float totalScore2 = (((float) getTotalScore()) / ((float) getBallsfaced())) * 100.0f;
        return Float.isNaN(totalScore2) ? IdManager.DEFAULT_VERSION_NAME : String.format("%.1f", new Object[]{Float.valueOf(totalScore2)});
    }

    public boolean isLastactivity() {
        return this.lastactivity;
    }

    public void setLastactivity(boolean z) {
        this.lastactivity = z;
    }

    public String isOut() {
        return this.out;
    }

    public void setOut(String str) {
        this.out = str;
    }

    public void setBallsfaced(int i) {
        this.ballsfaced += i;
    }

    public int getFives() {
        return this.fives;
    }

    public void setFives(int i) {
        this.fives += i;
    }

    public int getThrees() {
        return this.threes;
    }

    public void setThrees(int i) {
        this.threes += i;
    }

    public int getDots() {
        return this.dots;
    }

    public void setDots(int i) {
        this.dots += i;
    }

    public int getBallsfaced() {
        return this.ballsfaced;
    }

    public String getOutType() {
        return this.outType;
    }

    public void setOutType(String str) {
        this.outType = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getTotalScore() {
        return this.totalScore;
    }

    public void setTotalScore(int i) {
        this.totalScore += i;
    }

    public int getSixs() {
        return this.sixs;
    }

    public void setSixs(int i) {
        this.sixs += i;
    }

    public int getSingles() {
        return this.singles;
    }

    public void setSingles(int i) {
        this.singles += i;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public int getDoubles() {
        return this.doubles;
    }

    public void setDoubles(int i) {
        this.doubles += i;
    }

    public String getName() {
        return this.name;
    }

    public String getStats(int i) {
        switch (i) {
            case 2:
                return getFours() + "";
            case 3:
                return getSixs() + "";
            case 5:
                return getTotalScore() + "";
            case 7:
            case 8:
                return getTotalScore() + " (" + getBallsfaced() + ")";
            default:
                return getTotalScore() + "";
        }
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
        this.viewsHeading = str;
    }

    public String getHeading() {
        return this.viewsHeading;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getFours() {
        return this.fours;
    }

    public void setFours(int i) {
        this.fours += i;
    }
}
