package apps.shehryar.com.cricketscroingappPro;

import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import java.io.Serializable;

public class LastBall implements Serializable {
    public static final long serialVersionUID = 42;
    Batsman batsman;
    Bowler bowler;
    private int byescore;
    private FallOfWickets fallOfWickets;
    private boolean isLastBallOfOver = false;
    private boolean isbye;
    private boolean islegbye;
    private boolean isno;
    public boolean iswicket;
    private boolean iswide;
    private int legbyescore;
    private int noscore;
    private Over over;
    private int score;
    private Wicket wicket;
    private int widescore;

    public Wicket getWicket() {
        return this.wicket;
    }

    public void setWicket(Wicket wicket2) {
        this.wicket = wicket2;
    }

    public FallOfWickets getFallOfWickets() {
        return this.fallOfWickets;
    }

    public void setFallOfWickets(FallOfWickets fallOfWickets2) {
        this.fallOfWickets = fallOfWickets2;
    }

    public boolean iswicket() {
        return this.iswicket;
    }

    public void setIswicket(boolean z) {
        this.iswicket = z;
    }

    public int getByescore() {
        return this.byescore;
    }

    public void setByescore(int i) {
        this.byescore = i;
    }

    public int getLegbyescore() {
        return this.legbyescore;
    }

    public void setLegbyescore(int i) {
        this.legbyescore = i;
    }

    public boolean isbye() {
        return this.isbye;
    }

    public void setIsbye(boolean z) {
        this.isbye = z;
    }

    public boolean islegbye() {
        return this.islegbye;
    }

    public void setIslegbye(boolean z) {
        this.islegbye = z;
    }

    public Over getOver() {
        return this.over;
    }

    public void setOver(Over over2) {
        this.over = over2;
    }

    public int getWidescore() {
        return this.widescore;
    }

    public void setWidescore(int i) {
        this.widescore = i;
    }

    public int getNoscore() {
        return this.noscore;
    }

    public void setNoscore(int i) {
        this.noscore = i;
    }

    public Batsman getBatsman() {
        return this.batsman;
    }

    public void setBatsman(Batsman batsman2) {
        this.batsman = batsman2;
    }

    public Bowler getBowler() {
        return this.bowler;
    }

    public void setBowler(Bowler bowler2) {
        this.bowler = bowler2;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public boolean iswide() {
        return this.iswide;
    }

    public void setIswide(boolean z) {
        this.iswide = z;
    }

    public boolean isno() {
        return this.isno;
    }

    public void setIsno(boolean z) {
        this.isno = z;
    }

    public boolean isLastBallOfOver() {
        return this.isLastBallOfOver;
    }

    public void setLastBallOfOver(boolean z) {
        this.isLastBallOfOver = z;
    }
}
