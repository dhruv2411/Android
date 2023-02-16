package apps.shehryar.com.cricketscroingappPro.Team;

import java.io.Serializable;

public class HighestTotal implements Serializable {
    public static final long serialVersionUID = 42;
    String opponent;
    int score;

    public String getOpponent() {
        return this.opponent;
    }

    public void setOpponent(String str) {
        this.opponent = str;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public HighestTotal compareHighestChased(HighestTotal highestTotal) {
        if (this != null || highestTotal == null) {
            return ((this == null || highestTotal != null) && getScore() <= highestTotal.getScore()) ? highestTotal : this;
        }
        return highestTotal;
    }

    public HighestTotal compareHighestDefended(HighestTotal highestTotal) {
        if (this != null || highestTotal == null) {
            return ((this == null || highestTotal != null) && getScore() >= highestTotal.getScore()) ? highestTotal : this;
        }
        return highestTotal;
    }
}
