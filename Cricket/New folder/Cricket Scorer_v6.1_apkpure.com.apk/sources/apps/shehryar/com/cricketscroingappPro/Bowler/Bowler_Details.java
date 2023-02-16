package apps.shehryar.com.cricketscroingappPro.Bowler;

import apps.shehryar.com.cricketscroingappPro.Player.Player;
import java.io.Serializable;

public class Bowler_Details extends Player implements Serializable {
    public static final long serialVersionUID = 42;
    int ball;
    String date;
    String oppname;
    long oppteamid;
    int over;
    int score;
    String venue;
    int wickets;
    long yourteamid;

    public int getOver() {
        return this.over;
    }

    public void setOver(int i) {
        this.over = i;
    }

    public int getBall() {
        return this.ball;
    }

    public void setBall(int i) {
        this.ball = i;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String str) {
        this.venue = str;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public int getWickets() {
        return this.wickets;
    }

    public void setWickets(int i) {
        this.wickets = i;
    }

    public long getYourteamid() {
        return this.yourteamid;
    }

    public void setYourteamid(long j) {
        this.yourteamid = j;
    }

    public long getOppteamid() {
        return this.oppteamid;
    }

    public void setOppteamid(long j) {
        this.oppteamid = j;
    }

    public String getOppname() {
        return this.oppname;
    }

    public void setOppname(String str) {
        this.oppname = str;
    }
}
