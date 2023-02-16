package apps.shehryar.com.cricketscroingappPro.Player;

import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Model.SuggestionListItem;
import io.fabric.sdk.android.services.common.IdManager;
import java.io.Serializable;

public class Player implements Serializable, SuggestionListItem {
    public static final int IS_ALLROUNDER = 5;
    public static final int IS_BATSMAN = 1;
    public static final int IS_BOWLER = 2;
    public static final int WICKET_KEEPER = 3;
    public static final int WICKET_KEEPER_AND_BATSMAN = 4;
    public static final long serialVersionUID = 42;
    public int balls;
    int bowlerOvers;
    private String image;
    private boolean isAlrounder;
    private boolean isBatsman;
    private boolean isBowler;
    private boolean isCaptain;
    public boolean isInPlayingXI;
    private boolean isViceCaptain;
    private boolean isWicketKeeper;
    private String name;
    private String oldName;
    private long playerId;
    private int playerType;
    private int points;
    private int ranking;
    public int score;
    private long teamId;
    private String teamName;
    public int wickets;

    public static long getSerialVersionUID() {
        return 42;
    }

    public SuggestionListItem getSuggestionListItem() {
        return this;
    }

    public Player() {
    }

    public Player(Batsman batsman) {
        setBalls(batsman.getBallsfaced());
        setScore(batsman.getTotalScore());
        setWickets(batsman.getWickets());
        setName(batsman.getName());
        setTeamName(batsman.getTeamName());
    }

    public Player(Bowler bowler) {
        setWickets(bowler.getWickets());
        setBowlerOvers(bowler.getBowlerovers());
        setBalls(bowler.getBalls());
        setName(bowler.getName());
        setTeamName(bowler.getTeamName());
    }

    public void setProfile(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.isBatsman = z;
        this.isBowler = z2;
        this.isWicketKeeper = z3;
        this.isAlrounder = z4;
        this.isCaptain = z5;
        this.isViceCaptain = z6;
    }

    public boolean isBatsman() {
        return this.isBatsman;
    }

    public void setBatsman(boolean z) {
        this.isBatsman = z;
    }

    public boolean isBowler() {
        return this.isBowler;
    }

    public void setBowler(boolean z) {
        this.isBowler = z;
    }

    public boolean isWicketKeeper() {
        return this.isWicketKeeper;
    }

    public void setWicketKeeper(boolean z) {
        this.isWicketKeeper = z;
    }

    public boolean isAlrounder() {
        return this.isAlrounder;
    }

    public void setAlrounder(boolean z) {
        this.isAlrounder = z;
    }

    public boolean isCaptain() {
        return this.isCaptain;
    }

    public void setCaptain(boolean z) {
        this.isCaptain = z;
    }

    public boolean isViceCaptain() {
        return this.isViceCaptain;
    }

    public void setViceCaptain(boolean z) {
        this.isViceCaptain = z;
    }

    public boolean isInPlayingXI() {
        return this.isInPlayingXI;
    }

    public void setInPlayingXI(boolean z) {
        this.isInPlayingXI = z;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String str) {
        this.teamName = str;
    }

    public int getRanking() {
        return this.ranking;
    }

    public void setRanking(int i) {
        this.ranking = i;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int i) {
        this.points += i;
    }

    public String getOldName() {
        return this.oldName;
    }

    public void setOldName(String str) {
        this.oldName = str;
    }

    public long getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(long j) {
        this.playerId = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getTeamId() {
        return this.teamId;
    }

    public void setTeamId(long j) {
        this.teamId = j;
    }

    public String getImage() {
        return this.image;
    }

    public String getPlayerType() {
        if (isWicketKeeper() && isBatsman()) {
            return "Wicket Keeper/Batsman";
        }
        if (isBatsman()) {
            return "Batsman";
        }
        if (isBowler()) {
            return "Bowler";
        }
        if (isWicketKeeper()) {
            return "Wicket Keeper";
        }
        if (isAlrounder()) {
            return "All Rounder";
        }
        return null;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public void setBalls(int i) {
        this.balls = i;
    }

    public int getBalls() {
        return this.balls;
    }

    public int getBowlerOvers() {
        return this.bowlerOvers;
    }

    public void setBowlerOvers(int i) {
        this.bowlerOvers = i;
    }

    public int getWickets() {
        return this.wickets;
    }

    public void setWickets(int i) {
        this.wickets = i;
    }

    public double getBattingStrikeRate() {
        if (getBalls() == 0) {
            return 0.0d;
        }
        return (((double) getScore()) / ((double) ((float) getBalls()))) * 100.0d;
    }

    public double getDoubleBowlingEco() {
        double score2 = ((double) getScore()) / (((((double) getBowlerOvers()) * 6.0d) + ((double) ((float) getBalls()))) / 6.0d);
        String format = String.format("%.1f", new Object[]{Double.valueOf(score2)});
        if (Double.isNaN(score2)) {
            format = IdManager.DEFAULT_VERSION_NAME;
        }
        try {
            return Double.parseDouble(format);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public void setPlayerType(int i) {
        switch (i) {
            case 1:
                setBatsman(true);
                setBowler(false);
                setWicketKeeper(false);
                setAlrounder(false);
                return;
            case 2:
                setBatsman(false);
                setBowler(true);
                setWicketKeeper(false);
                setAlrounder(false);
                return;
            case 3:
                setBatsman(false);
                setBowler(false);
                setWicketKeeper(true);
                setAlrounder(false);
                return;
            case 4:
                setBatsman(true);
                setBowler(false);
                setWicketKeeper(true);
                setAlrounder(false);
                return;
            case 5:
                setBatsman(true);
                setBowler(true);
                setWicketKeeper(false);
                setAlrounder(true);
                return;
            default:
                return;
        }
    }

    public String getSuggestionText() {
        return getName();
    }
}
