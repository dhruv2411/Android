package apps.shehryar.com.cricketscroingappPro.Match;

import java.io.Serializable;

public class ResumeMatch implements Serializable {
    public static final long serialVersionUID = 42;
    int batFacing;
    String bowlerName;
    int innings;
    String overScore;

    public ResumeMatch() {
    }

    public ResumeMatch(int i, String str, int i2, String str2) {
        this.innings = i;
        this.bowlerName = str;
        this.batFacing = i2;
        this.overScore = str2;
    }

    public int getInnings() {
        return this.innings;
    }

    public void setInnings(int i) {
        this.innings = i;
    }

    public String getBowlerName() {
        return this.bowlerName;
    }

    public void setBowlerName(String str) {
        this.bowlerName = str;
    }

    public int getBatFacing() {
        return this.batFacing;
    }

    public void setBatFacing(int i) {
        this.batFacing = i;
    }

    public String getOverScore() {
        return this.overScore;
    }

    public void setOverScore(String str) {
        this.overScore = str;
    }
}
