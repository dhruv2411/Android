package apps.shehryar.com.cricketscroingappPro.Batsman;

import apps.shehryar.com.cricketscroingappPro.Player.Player;
import java.io.Serializable;

public class Batsman_Details extends Player implements Serializable {
    public static final long serialVersionUID = 42;
    int balls;
    String date;
    int dots;
    int doubles;
    int fifties;
    int fours;
    int hundreds = 0;
    String isout;
    int score;
    int singles;
    int sixes;
    long teamid;
    long teamoppid;
    String teamoppname;
    int thirties;
    int triples;
    String venue;

    public String getIsout() {
        return this.isout;
    }

    public void setIsout(String str) {
        this.isout = str;
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

    public long getTeamoppid() {
        return this.teamoppid;
    }

    public void setTeamoppid(long j) {
        this.teamoppid = j;
    }

    public long getTeamid() {
        return this.teamid;
    }

    public void setTeamid(long j) {
        this.teamid = j;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public int getBalls() {
        return this.balls;
    }

    public void setBalls(int i) {
        this.balls = i;
    }

    public String getTeamoppname() {
        return this.teamoppname;
    }

    public void setTeamoppname(String str) {
        this.teamoppname = str;
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

    public int getDots() {
        return this.dots;
    }

    public void setDots(int i) {
        this.dots = i;
    }

    public int getSingles() {
        return this.singles;
    }

    public void setSingles(int i) {
        this.singles = i;
    }

    public int getDoubles() {
        return this.doubles;
    }

    public void setDoubles(int i) {
        this.doubles = i;
    }

    public int getTriples() {
        return this.triples;
    }

    public void setTriples(int i) {
        this.triples = i;
    }

    public int getFours() {
        return this.fours;
    }

    public void setFours(int i) {
        this.fours = i;
    }

    public int getSixes() {
        return this.sixes;
    }

    public void setSixes(int i) {
        this.sixes = i;
    }
}
