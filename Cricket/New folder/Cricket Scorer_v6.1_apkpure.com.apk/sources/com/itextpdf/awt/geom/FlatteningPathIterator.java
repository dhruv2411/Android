package com.itextpdf.awt.geom;

import com.itextpdf.awt.geom.misc.Messages;
import java.util.NoSuchElementException;

public class FlatteningPathIterator implements PathIterator {
    private static final int BUFFER_CAPACITY = 16;
    private static final int BUFFER_LIMIT = 16;
    private static final int BUFFER_SIZE = 16;
    double[] buf;
    boolean bufEmpty;
    int bufIndex;
    int bufLimit;
    int bufSize;
    int bufSubdiv;
    int bufType;
    double[] coords;
    double flatness;
    double flatness2;
    PathIterator p;
    double px;
    double py;

    public FlatteningPathIterator(PathIterator pathIterator, double d) {
        this(pathIterator, d, 16);
    }

    public FlatteningPathIterator(PathIterator pathIterator, double d, int i) {
        this.bufEmpty = true;
        this.coords = new double[6];
        if (d < 0.0d) {
            throw new IllegalArgumentException(Messages.getString("awt.206"));
        } else if (i < 0) {
            throw new IllegalArgumentException(Messages.getString("awt.207"));
        } else if (pathIterator == null) {
            throw new NullPointerException(Messages.getString("awt.208"));
        } else {
            this.p = pathIterator;
            this.flatness = d;
            this.flatness2 = d * d;
            this.bufLimit = i;
            this.bufSize = Math.min(this.bufLimit, 16);
            this.buf = new double[this.bufSize];
            this.bufIndex = this.bufSize;
        }
    }

    public double getFlatness() {
        return this.flatness;
    }

    public int getRecursionLimit() {
        return this.bufLimit;
    }

    public int getWindingRule() {
        return this.p.getWindingRule();
    }

    public boolean isDone() {
        return this.bufEmpty && this.p.isDone();
    }

    /* access modifiers changed from: package-private */
    public void evaluate() {
        if (this.bufEmpty) {
            this.bufType = this.p.currentSegment(this.coords);
        }
        boolean z = false;
        switch (this.bufType) {
            case 0:
            case 1:
                this.px = this.coords[0];
                this.py = this.coords[1];
                return;
            case 2:
                if (this.bufEmpty) {
                    this.bufIndex -= 6;
                    this.buf[this.bufIndex + 0] = this.px;
                    this.buf[this.bufIndex + 1] = this.py;
                    System.arraycopy(this.coords, 0, this.buf, this.bufIndex + 2, 4);
                    this.bufSubdiv = 0;
                }
                while (this.bufSubdiv < this.bufLimit && QuadCurve2D.getFlatnessSq(this.buf, this.bufIndex) >= this.flatness2) {
                    if (this.bufIndex <= 4) {
                        double[] dArr = new double[(this.bufSize + 16)];
                        System.arraycopy(this.buf, this.bufIndex, dArr, this.bufIndex + 16, this.bufSize - this.bufIndex);
                        this.buf = dArr;
                        this.bufSize += 16;
                        this.bufIndex += 16;
                    }
                    QuadCurve2D.subdivide(this.buf, this.bufIndex, this.buf, this.bufIndex - 4, this.buf, this.bufIndex);
                    this.bufIndex -= 4;
                    this.bufSubdiv++;
                }
                this.bufIndex += 4;
                this.px = this.buf[this.bufIndex];
                this.py = this.buf[this.bufIndex + 1];
                if (this.bufIndex == this.bufSize - 2) {
                    z = true;
                }
                this.bufEmpty = z;
                if (this.bufEmpty) {
                    this.bufIndex = this.bufSize;
                    this.bufType = 1;
                    return;
                }
                return;
            case 3:
                if (this.bufEmpty) {
                    this.bufIndex -= 8;
                    this.buf[this.bufIndex + 0] = this.px;
                    this.buf[this.bufIndex + 1] = this.py;
                    System.arraycopy(this.coords, 0, this.buf, this.bufIndex + 2, 6);
                    this.bufSubdiv = 0;
                }
                while (this.bufSubdiv < this.bufLimit && CubicCurve2D.getFlatnessSq(this.buf, this.bufIndex) >= this.flatness2) {
                    if (this.bufIndex <= 6) {
                        double[] dArr2 = new double[(this.bufSize + 16)];
                        System.arraycopy(this.buf, this.bufIndex, dArr2, this.bufIndex + 16, this.bufSize - this.bufIndex);
                        this.buf = dArr2;
                        this.bufSize += 16;
                        this.bufIndex += 16;
                    }
                    CubicCurve2D.subdivide(this.buf, this.bufIndex, this.buf, this.bufIndex - 6, this.buf, this.bufIndex);
                    this.bufIndex -= 6;
                    this.bufSubdiv++;
                }
                this.bufIndex += 6;
                this.px = this.buf[this.bufIndex];
                this.py = this.buf[this.bufIndex + 1];
                if (this.bufIndex == this.bufSize - 2) {
                    z = true;
                }
                this.bufEmpty = z;
                if (this.bufEmpty) {
                    this.bufIndex = this.bufSize;
                    this.bufType = 1;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void next() {
        if (this.bufEmpty) {
            this.p.next();
        }
    }

    public int currentSegment(float[] fArr) {
        if (isDone()) {
            throw new NoSuchElementException(Messages.getString("awt.4Bx"));
        }
        evaluate();
        int i = this.bufType;
        if (i == 4) {
            return i;
        }
        fArr[0] = (float) this.px;
        fArr[1] = (float) this.py;
        if (i != 0) {
            return 1;
        }
        return i;
    }

    public int currentSegment(double[] dArr) {
        if (isDone()) {
            throw new NoSuchElementException(Messages.getString("awt.4B"));
        }
        evaluate();
        int i = this.bufType;
        if (i == 4) {
            return i;
        }
        dArr[0] = this.px;
        dArr[1] = this.py;
        if (i != 0) {
            return 1;
        }
        return i;
    }
}
