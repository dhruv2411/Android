package apps.shehryar.com.cricketscroingappPro;

import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import java.io.Serializable;

public class FallOfWickets implements Serializable {
    public static final long serialVersionUID = 42;
    int ball;
    int batFacingCode;
    int batOutCode;
    Batsman batsman;
    String batsmanName;
    Bowler bowler;
    String bowlerName;
    long id;
    boolean isBye = false;
    boolean isNo = false;
    boolean isRunOut = false;
    boolean isWide = false;
    int overno;
    int runsScored;
    int score;
    int wicketNo;

    public boolean isRunOut() {
        return this.isRunOut;
    }

    public void setRunOut(boolean z) {
        this.isRunOut = z;
    }

    public boolean isWide() {
        return this.isWide;
    }

    public void setWide(boolean z) {
        this.isWide = z;
    }

    public boolean isNo() {
        return this.isNo;
    }

    public void setNo(boolean z) {
        this.isNo = z;
    }

    public int getRunsScored() {
        return this.runsScored;
    }

    public void setRunsScored(int i) {
        this.runsScored = i;
    }

    public boolean isBye() {
        return this.isBye;
    }

    public void setBye(boolean z) {
        this.isBye = z;
    }

    public int getBatFacingCode() {
        return this.batFacingCode;
    }

    public void setBatFacingCode(int i) {
        this.batFacingCode = i;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int getBatOutCode() {
        return this.batOutCode;
    }

    public void setBatOutCode(int i) {
        this.batOutCode = i;
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

    public int getOverno() {
        return this.overno;
    }

    public void setOverno(int i) {
        this.overno = i;
    }

    public int getBall() {
        return this.ball;
    }

    public void setBall(int i) {
        this.ball = i;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public String getBatsmanName() {
        return this.batsmanName;
    }

    public void setBatsmanName(String str) {
        this.batsmanName = str;
    }

    public String getBowlerName() {
        return this.bowlerName;
    }

    public void setBowlerName(String str) {
        this.bowlerName = str;
    }

    public int getWicketNo() {
        return this.wicketNo;
    }

    public void setWicketNo(int i) {
        this.wicketNo = i;
    }
}
