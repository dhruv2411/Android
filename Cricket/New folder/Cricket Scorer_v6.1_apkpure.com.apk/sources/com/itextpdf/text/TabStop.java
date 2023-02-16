package com.itextpdf.text;

import com.itextpdf.text.pdf.draw.DrawInterface;

public class TabStop {
    protected Alignment alignment;
    protected char anchorChar;
    protected DrawInterface leader;
    protected float position;

    public enum Alignment {
        LEFT,
        RIGHT,
        CENTER,
        ANCHOR
    }

    public static TabStop newInstance(float f, float f2) {
        float round = ((float) Math.round(f * 1000.0f)) / 1000.0f;
        float round2 = ((float) Math.round(f2 * 1000.0f)) / 1000.0f;
        return new TabStop((round + round2) - (round % round2));
    }

    public TabStop(float f) {
        this(f, Alignment.LEFT);
    }

    public TabStop(float f, DrawInterface drawInterface) {
        this(f, drawInterface, Alignment.LEFT);
    }

    public TabStop(float f, Alignment alignment2) {
        this(f, (DrawInterface) null, alignment2);
    }

    public TabStop(float f, Alignment alignment2, char c) {
        this(f, (DrawInterface) null, alignment2, c);
    }

    public TabStop(float f, DrawInterface drawInterface, Alignment alignment2) {
        this(f, drawInterface, alignment2, '.');
    }

    public TabStop(float f, DrawInterface drawInterface, Alignment alignment2, char c) {
        this.alignment = Alignment.LEFT;
        this.anchorChar = '.';
        this.position = f;
        this.leader = drawInterface;
        this.alignment = alignment2;
        this.anchorChar = c;
    }

    public TabStop(TabStop tabStop) {
        this(tabStop.getPosition(), tabStop.getLeader(), tabStop.getAlignment(), tabStop.getAnchorChar());
    }

    public float getPosition() {
        return this.position;
    }

    public void setPosition(float f) {
        this.position = f;
    }

    public Alignment getAlignment() {
        return this.alignment;
    }

    public void setAlignment(Alignment alignment2) {
        this.alignment = alignment2;
    }

    public DrawInterface getLeader() {
        return this.leader;
    }

    public void setLeader(DrawInterface drawInterface) {
        this.leader = drawInterface;
    }

    public char getAnchorChar() {
        return this.anchorChar;
    }

    public void setAnchorChar(char c) {
        this.anchorChar = c;
    }

    public float getPosition(float f, float f2, float f3) {
        float f4 = this.position;
        float f5 = f2 - f;
        switch (this.alignment) {
            case RIGHT:
                return f + f5 < this.position ? this.position - f5 : f;
            case CENTER:
                float f6 = f5 / 2.0f;
                if (f + f6 < this.position) {
                    return this.position - f6;
                }
                return f;
            case ANCHOR:
                if (!Float.isNaN(f3)) {
                    if (f3 < this.position) {
                        return this.position - (f3 - f);
                    }
                    return f;
                } else if (f + f5 < this.position) {
                    return this.position - f5;
                } else {
                    return f;
                }
            default:
                return f4;
        }
    }
}
