package apps.shehryar.com.cricketscroingappPro;

import java.io.Serializable;

public class Score implements Serializable {
    public static final long serialVersionUID = 42;
    private int dots;
    private int doubles;
    private int fives;
    private int fours;
    private int singles;
    private int sixes;
    private int triples;

    public int getDots() {
        return this.dots;
    }

    public void setDots(int i) {
        this.dots += i;
    }

    public int getSingles() {
        return this.singles;
    }

    public void setSingles(int i) {
        this.singles += i;
    }

    public int getDoubles() {
        return this.doubles;
    }

    public void setDoubles(int i) {
        this.doubles += i;
    }

    public int getTriples() {
        return this.triples;
    }

    public void setTriples(int i) {
        this.triples += i;
    }

    public int getFours() {
        return this.fours;
    }

    public void setFours(int i) {
        this.fours += i;
    }

    public int getFives() {
        return this.fives;
    }

    public void setFives(int i) {
        this.fives += i;
    }

    public int getSixes() {
        return this.sixes;
    }

    public void setSixes(int i) {
        this.sixes += i;
    }

    public void clearDots() {
        this.dots = 0;
    }

    public void clearSingles() {
        this.singles = 0;
    }

    public void clearDoubles() {
        this.doubles = 0;
    }

    public void clearTriples() {
        this.triples = 0;
    }

    public void clearFours() {
        this.fours = 0;
    }

    public void clearFives() {
        this.fives = 0;
    }

    public void clearSixes() {
        this.sixes = 0;
    }
}
