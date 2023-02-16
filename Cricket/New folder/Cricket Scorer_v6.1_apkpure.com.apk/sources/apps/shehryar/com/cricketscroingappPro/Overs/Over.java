package apps.shehryar.com.cricketscroingappPro.Overs;

import java.io.Serializable;
import java.util.ArrayList;

public class Over implements Serializable {
    public static int balls = 0;
    public static int ballscounter = 0;
    public static final long serialVersionUID = 42;
    private String bowler;
    public boolean maiden = false;
    public int overBalls;
    private int overExtraBalls;
    public int over_no = 0;
    private int overscore;
    public ArrayList<Integer> perballScore = new ArrayList<>();
    private int wickets;

    public int getOverExtraBalls() {
        return this.overExtraBalls;
    }

    public void setOverExtraBalls(int i) {
        this.overExtraBalls += i;
    }

    public void incerementOverExtraBalls() {
        this.overExtraBalls++;
    }

    public void decrementOverExtraBalls() {
        this.overExtraBalls--;
    }

    public int getOverBalls() {
        return this.overBalls;
    }

    public void incrementOverBalls() {
        this.overBalls++;
    }

    public void decrementOverBalls() {
        this.overBalls--;
    }

    public void resetOverBalls() {
        this.overBalls = 0;
    }

    public void setOverBalls(int i) {
        this.overBalls = i;
    }

    public int getOverscore() {
        return this.overscore;
    }

    public void setOverscore(int i) {
        this.overscore += i;
    }

    public static int getBallscounter() {
        return ballscounter;
    }

    public static void setBallscounter(int i) {
        ballscounter = i;
    }

    public ArrayList<Integer> getPerballScore() {
        return this.perballScore;
    }

    public void setPerballScore(ArrayList<Integer> arrayList) {
        this.perballScore = arrayList;
    }

    public int getWickets() {
        return this.wickets;
    }

    public void setWickets(int i) {
        this.wickets += i;
    }

    public static int getBalls() {
        return balls;
    }

    public static void setBalls(int i) {
        balls = i;
    }

    public String getBowler() {
        return this.bowler;
    }

    public void setBowler(String str) {
        this.bowler = str;
    }

    public int getOver_no() {
        return this.over_no;
    }

    public void setOver_no(int i) {
        this.over_no = i;
    }
}
