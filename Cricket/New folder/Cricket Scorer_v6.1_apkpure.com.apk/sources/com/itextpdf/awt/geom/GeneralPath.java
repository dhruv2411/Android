package com.itextpdf.awt.geom;

import com.itextpdf.awt.geom.Point2D;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.awt.geom.gl.Crossing;
import com.itextpdf.awt.geom.misc.Messages;
import java.util.NoSuchElementException;

public final class GeneralPath implements Shape, Cloneable {
    private static final int BUFFER_CAPACITY = 10;
    private static final int BUFFER_SIZE = 10;
    public static final int WIND_EVEN_ODD = 0;
    public static final int WIND_NON_ZERO = 1;
    static int[] pointShift = {2, 2, 4, 6, 0};
    int pointSize;
    float[] points;
    int rule;
    int typeSize;
    byte[] types;

    class Iterator implements PathIterator {
        GeneralPath p;
        int pointIndex;
        AffineTransform t;
        int typeIndex;

        Iterator(GeneralPath generalPath, GeneralPath generalPath2) {
            this(generalPath2, (AffineTransform) null);
        }

        Iterator(GeneralPath generalPath, AffineTransform affineTransform) {
            this.p = generalPath;
            this.t = affineTransform;
        }

        public int getWindingRule() {
            return this.p.getWindingRule();
        }

        public boolean isDone() {
            return this.typeIndex >= this.p.typeSize;
        }

        public void next() {
            this.typeIndex++;
        }

        public int currentSegment(double[] dArr) {
            if (isDone()) {
                throw new NoSuchElementException(Messages.getString("awt.4B"));
            }
            byte b = this.p.types[this.typeIndex];
            int i = GeneralPath.pointShift[b];
            for (int i2 = 0; i2 < i; i2++) {
                dArr[i2] = (double) this.p.points[this.pointIndex + i2];
            }
            if (this.t != null) {
                this.t.transform(dArr, 0, dArr, 0, i / 2);
            }
            this.pointIndex += i;
            return b;
        }

        public int currentSegment(float[] fArr) {
            if (isDone()) {
                throw new NoSuchElementException(Messages.getString("awt.4B"));
            }
            byte b = this.p.types[this.typeIndex];
            int i = GeneralPath.pointShift[b];
            System.arraycopy(this.p.points, this.pointIndex, fArr, 0, i);
            if (this.t != null) {
                this.t.transform(fArr, 0, fArr, 0, i / 2);
            }
            this.pointIndex += i;
            return b;
        }
    }

    public GeneralPath() {
        this(1, 10);
    }

    public GeneralPath(int i) {
        this(i, 10);
    }

    public GeneralPath(int i, int i2) {
        setWindingRule(i);
        this.types = new byte[i2];
        this.points = new float[(i2 * 2)];
    }

    public GeneralPath(Shape shape) {
        this(1, 10);
        PathIterator pathIterator = shape.getPathIterator((AffineTransform) null);
        setWindingRule(pathIterator.getWindingRule());
        append(pathIterator, false);
    }

    public void setWindingRule(int i) {
        if (i == 0 || i == 1) {
            this.rule = i;
            return;
        }
        throw new IllegalArgumentException(Messages.getString("awt.209"));
    }

    public int getWindingRule() {
        return this.rule;
    }

    /* access modifiers changed from: package-private */
    public void checkBuf(int i, boolean z) {
        if (!z || this.typeSize != 0) {
            if (this.typeSize == this.types.length) {
                byte[] bArr = new byte[(this.typeSize + 10)];
                System.arraycopy(this.types, 0, bArr, 0, this.typeSize);
                this.types = bArr;
            }
            if (this.pointSize + i > this.points.length) {
                float[] fArr = new float[(this.pointSize + Math.max(20, i))];
                System.arraycopy(this.points, 0, fArr, 0, this.pointSize);
                this.points = fArr;
                return;
            }
            return;
        }
        throw new IllegalPathStateException(Messages.getString("awt.20A"));
    }

    public void moveTo(float f, float f2) {
        if (this.typeSize <= 0 || this.types[this.typeSize - 1] != 0) {
            checkBuf(2, false);
            byte[] bArr = this.types;
            int i = this.typeSize;
            this.typeSize = i + 1;
            bArr[i] = 0;
            float[] fArr = this.points;
            int i2 = this.pointSize;
            this.pointSize = i2 + 1;
            fArr[i2] = f;
            float[] fArr2 = this.points;
            int i3 = this.pointSize;
            this.pointSize = i3 + 1;
            fArr2[i3] = f2;
            return;
        }
        this.points[this.pointSize - 2] = f;
        this.points[this.pointSize - 1] = f2;
    }

    public void lineTo(float f, float f2) {
        checkBuf(2, true);
        byte[] bArr = this.types;
        int i = this.typeSize;
        this.typeSize = i + 1;
        bArr[i] = 1;
        float[] fArr = this.points;
        int i2 = this.pointSize;
        this.pointSize = i2 + 1;
        fArr[i2] = f;
        float[] fArr2 = this.points;
        int i3 = this.pointSize;
        this.pointSize = i3 + 1;
        fArr2[i3] = f2;
    }

    public void quadTo(float f, float f2, float f3, float f4) {
        checkBuf(4, true);
        byte[] bArr = this.types;
        int i = this.typeSize;
        this.typeSize = i + 1;
        bArr[i] = 2;
        float[] fArr = this.points;
        int i2 = this.pointSize;
        this.pointSize = i2 + 1;
        fArr[i2] = f;
        float[] fArr2 = this.points;
        int i3 = this.pointSize;
        this.pointSize = i3 + 1;
        fArr2[i3] = f2;
        float[] fArr3 = this.points;
        int i4 = this.pointSize;
        this.pointSize = i4 + 1;
        fArr3[i4] = f3;
        float[] fArr4 = this.points;
        int i5 = this.pointSize;
        this.pointSize = i5 + 1;
        fArr4[i5] = f4;
    }

    public void curveTo(float f, float f2, float f3, float f4, float f5, float f6) {
        checkBuf(6, true);
        byte[] bArr = this.types;
        int i = this.typeSize;
        this.typeSize = i + 1;
        bArr[i] = 3;
        float[] fArr = this.points;
        int i2 = this.pointSize;
        this.pointSize = i2 + 1;
        fArr[i2] = f;
        float[] fArr2 = this.points;
        int i3 = this.pointSize;
        this.pointSize = i3 + 1;
        fArr2[i3] = f2;
        float[] fArr3 = this.points;
        int i4 = this.pointSize;
        this.pointSize = i4 + 1;
        fArr3[i4] = f3;
        float[] fArr4 = this.points;
        int i5 = this.pointSize;
        this.pointSize = i5 + 1;
        fArr4[i5] = f4;
        float[] fArr5 = this.points;
        int i6 = this.pointSize;
        this.pointSize = i6 + 1;
        fArr5[i6] = f5;
        float[] fArr6 = this.points;
        int i7 = this.pointSize;
        this.pointSize = i7 + 1;
        fArr6[i7] = f6;
    }

    public void closePath() {
        if (this.typeSize == 0 || this.types[this.typeSize - 1] != 4) {
            checkBuf(0, true);
            byte[] bArr = this.types;
            int i = this.typeSize;
            this.typeSize = i + 1;
            bArr[i] = 4;
        }
    }

    public void append(Shape shape, boolean z) {
        append(shape.getPathIterator((AffineTransform) null), z);
    }

    public void append(PathIterator pathIterator, boolean z) {
        boolean z2 = z;
        while (!pathIterator.isDone()) {
            float[] fArr = new float[6];
            switch (pathIterator.currentSegment(fArr)) {
                case 0:
                    if (z2 && this.typeSize != 0) {
                        if (this.types[this.typeSize - 1] != 4 && this.points[this.pointSize - 2] == fArr[0] && this.points[this.pointSize - 1] == fArr[1]) {
                            break;
                        }
                    } else {
                        moveTo(fArr[0], fArr[1]);
                        break;
                    }
                    break;
                case 1:
                    lineTo(fArr[0], fArr[1]);
                    break;
                case 2:
                    quadTo(fArr[0], fArr[1], fArr[2], fArr[3]);
                    break;
                case 3:
                    curveTo(fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5]);
                    break;
                case 4:
                    closePath();
                    break;
            }
            pathIterator.next();
            z2 = false;
        }
    }

    public Point2D getCurrentPoint() {
        if (this.typeSize == 0) {
            return null;
        }
        int i = this.pointSize - 2;
        if (this.types[this.typeSize - 1] == 4) {
            for (int i2 = this.typeSize - 2; i2 > 0; i2--) {
                byte b = this.types[i2];
                if (b == 0) {
                    break;
                }
                i -= pointShift[b];
            }
        }
        return new Point2D.Float(this.points[i], this.points[i + 1]);
    }

    public void reset() {
        this.typeSize = 0;
        this.pointSize = 0;
    }

    public void transform(AffineTransform affineTransform) {
        affineTransform.transform(this.points, 0, this.points, 0, this.pointSize / 2);
    }

    public Shape createTransformedShape(AffineTransform affineTransform) {
        GeneralPath generalPath = (GeneralPath) clone();
        if (affineTransform != null) {
            generalPath.transform(affineTransform);
        }
        return generalPath;
    }

    public Rectangle2D getBounds2D() {
        float f;
        float f2;
        float f3;
        float f4;
        if (this.pointSize == 0) {
            f4 = 0.0f;
            f3 = 0.0f;
            f2 = 0.0f;
            f = 0.0f;
        } else {
            int i = this.pointSize - 1;
            int i2 = i - 1;
            int i3 = i2 - 1;
            f = this.points[i];
            f2 = this.points[i2];
            f4 = f2;
            f3 = f;
            while (i3 > 0) {
                int i4 = i3 - 1;
                float f5 = this.points[i3];
                int i5 = i4 - 1;
                float f6 = this.points[i4];
                if (f6 < f4) {
                    f4 = f6;
                } else if (f6 > f2) {
                    f2 = f6;
                }
                if (f5 < f3) {
                    f3 = f5;
                } else if (f5 > f) {
                    f = f5;
                }
                i3 = i5;
            }
        }
        return new Rectangle2D.Float(f4, f3, f2 - f4, f - f3);
    }

    public Rectangle getBounds() {
        return getBounds2D().getBounds();
    }

    /* access modifiers changed from: package-private */
    public boolean isInside(int i) {
        if (this.rule == 1) {
            return Crossing.isInsideNonZero(i);
        }
        return Crossing.isInsideEvenOdd(i);
    }

    public boolean contains(double d, double d2) {
        return isInside(Crossing.crossShape(this, d, d2));
    }

    public boolean contains(double d, double d2, double d3, double d4) {
        int intersectShape = Crossing.intersectShape(this, d, d2, d3, d4);
        return intersectShape != 255 && isInside(intersectShape);
    }

    public boolean intersects(double d, double d2, double d3, double d4) {
        int intersectShape = Crossing.intersectShape(this, d, d2, d3, d4);
        return intersectShape == 255 || isInside(intersectShape);
    }

    public boolean contains(Point2D point2D) {
        return contains(point2D.getX(), point2D.getY());
    }

    public boolean contains(Rectangle2D rectangle2D) {
        return contains(rectangle2D.getX(), rectangle2D.getY(), rectangle2D.getWidth(), rectangle2D.getHeight());
    }

    public boolean intersects(Rectangle2D rectangle2D) {
        return intersects(rectangle2D.getX(), rectangle2D.getY(), rectangle2D.getWidth(), rectangle2D.getHeight());
    }

    public PathIterator getPathIterator(AffineTransform affineTransform) {
        return new Iterator(this, affineTransform);
    }

    public PathIterator getPathIterator(AffineTransform affineTransform, double d) {
        return new FlatteningPathIterator(getPathIterator(affineTransform), d);
    }

    public Object clone() {
        try {
            GeneralPath generalPath = (GeneralPath) super.clone();
            generalPath.types = (byte[]) this.types.clone();
            generalPath.points = (float[]) this.points.clone();
            return generalPath;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
