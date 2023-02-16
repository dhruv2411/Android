package com.itextpdf.text.pdf.parser.clipper;

import com.itextpdf.text.pdf.parser.clipper.Clipper;
import com.itextpdf.text.pdf.parser.clipper.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ClipperOffset {
    private static final double DEFAULT_ARC_TOLERANCE = 0.25d;
    private static final double TOLERANCE = 1.0E-20d;
    private static final double TWO_PI = 6.283185307179586d;
    private final double arcTolerance;
    private double cos;
    private double delta;
    private Path destPoly;
    private Paths destPolys;
    private double inA;
    private Point.LongPoint lowest;
    private double miterLim;
    private final double miterLimit;
    private final List<Point.DoublePoint> normals;
    private final PolyNode polyNodes;
    private double sin;
    private Path srcPoly;
    private double stepsPerRad;

    private static boolean nearZero(double d) {
        return d > -1.0E-20d && d < TOLERANCE;
    }

    public ClipperOffset() {
        this(2.0d, DEFAULT_ARC_TOLERANCE);
    }

    public ClipperOffset(double d) {
        this(d, DEFAULT_ARC_TOLERANCE);
    }

    public ClipperOffset(double d, double d2) {
        this.miterLimit = d;
        this.arcTolerance = d2;
        this.lowest = new Point.LongPoint();
        this.lowest.setX(-1L);
        this.polyNodes = new PolyNode();
        this.normals = new ArrayList();
    }

    public void addPath(Path path, Clipper.JoinType joinType, Clipper.EndType endType) {
        int size = path.size() - 1;
        if (size >= 0) {
            PolyNode polyNode = new PolyNode();
            polyNode.setJoinType(joinType);
            polyNode.setEndType(endType);
            int i = 0;
            if (endType == Clipper.EndType.CLOSED_LINE || endType == Clipper.EndType.CLOSED_POLYGON) {
                while (size > 0 && path.get(0) == path.get(size)) {
                    size--;
                }
            }
            polyNode.getPolygon().add(path.get(0));
            int i2 = 0;
            for (int i3 = 1; i3 <= size; i3++) {
                if (polyNode.getPolygon().get(i) != path.get(i3)) {
                    i++;
                    polyNode.getPolygon().add(path.get(i3));
                    if (((Point.LongPoint) path.get(i3)).getY() > ((Point.LongPoint) polyNode.getPolygon().get(i2)).getY() || (((Point.LongPoint) path.get(i3)).getY() == ((Point.LongPoint) polyNode.getPolygon().get(i2)).getY() && ((Point.LongPoint) path.get(i3)).getX() < ((Point.LongPoint) polyNode.getPolygon().get(i2)).getX())) {
                        i2 = i;
                    }
                }
            }
            if (endType != Clipper.EndType.CLOSED_POLYGON || i >= 2) {
                this.polyNodes.addChild(polyNode);
                if (endType == Clipper.EndType.CLOSED_POLYGON) {
                    if (this.lowest.getX() < 0) {
                        this.lowest = new Point.LongPoint((long) (this.polyNodes.getChildCount() - 1), (long) i2);
                        return;
                    }
                    Point.LongPoint longPoint = (Point.LongPoint) this.polyNodes.getChilds().get((int) this.lowest.getX()).getPolygon().get((int) this.lowest.getY());
                    if (((Point.LongPoint) polyNode.getPolygon().get(i2)).getY() > longPoint.getY() || (((Point.LongPoint) polyNode.getPolygon().get(i2)).getY() == longPoint.getY() && ((Point.LongPoint) polyNode.getPolygon().get(i2)).getX() < longPoint.getX())) {
                        this.lowest = new Point.LongPoint((long) (this.polyNodes.getChildCount() - 1), (long) i2);
                    }
                }
            }
        }
    }

    public void addPaths(Paths paths, Clipper.JoinType joinType, Clipper.EndType endType) {
        Iterator it = paths.iterator();
        while (it.hasNext()) {
            addPath((Path) it.next(), joinType, endType);
        }
    }

    public void clear() {
        this.polyNodes.getChilds().clear();
        this.lowest.setX(-1L);
    }

    private void doMiter(int i, int i2, double d) {
        double d2 = this.delta / d;
        this.destPoly.add(new Point.LongPoint(Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i)).getX()) + ((this.normals.get(i2).getX() + this.normals.get(i).getX()) * d2)), Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i)).getY()) + ((this.normals.get(i2).getY() + this.normals.get(i).getY()) * d2))));
    }

    private void doOffset(double d) {
        double d2;
        double d3;
        int i;
        int i2;
        char c;
        double d4 = d;
        this.destPolys = new Paths();
        this.delta = d4;
        int i3 = 0;
        if (nearZero(d)) {
            while (i3 < this.polyNodes.getChildCount()) {
                PolyNode polyNode = this.polyNodes.getChilds().get(i3);
                if (polyNode.getEndType() == Clipper.EndType.CLOSED_POLYGON) {
                    this.destPolys.add(polyNode.getPolygon());
                }
                i3++;
            }
            return;
        }
        if (this.miterLimit > 2.0d) {
            this.miterLim = 2.0d / (this.miterLimit * this.miterLimit);
        } else {
            this.miterLim = 0.5d;
        }
        double d5 = 0.0d;
        int i4 = (this.arcTolerance > 0.0d ? 1 : (this.arcTolerance == 0.0d ? 0 : -1));
        double d6 = DEFAULT_ARC_TOLERANCE;
        if (i4 > 0) {
            if (this.arcTolerance > Math.abs(d) * DEFAULT_ARC_TOLERANCE) {
                d6 = DEFAULT_ARC_TOLERANCE * Math.abs(d);
            } else {
                d6 = this.arcTolerance;
            }
        }
        double acos = 3.141592653589793d / Math.acos(1.0d - (d6 / Math.abs(d)));
        double d7 = TWO_PI / acos;
        this.sin = Math.sin(d7);
        this.cos = Math.cos(d7);
        this.stepsPerRad = acos / TWO_PI;
        if (d4 < 0.0d) {
            this.sin = -this.sin;
        }
        int i5 = 0;
        while (i5 < this.polyNodes.getChildCount()) {
            PolyNode polyNode2 = this.polyNodes.getChilds().get(i5);
            this.srcPoly = polyNode2.getPolygon();
            int size = this.srcPoly.size();
            if (size == 0 || (d4 <= d5 && (size < 3 || polyNode2.getEndType() != Clipper.EndType.CLOSED_POLYGON))) {
                i = i3;
                d2 = acos;
                d3 = d5;
            } else {
                this.destPoly = new Path();
                int i6 = 1;
                if (size == 1) {
                    if (polyNode2.getJoinType() == Clipper.JoinType.ROUND) {
                        double d8 = d5;
                        double d9 = 1.0d;
                        while (((double) i6) <= acos) {
                            double d10 = d9;
                            this.destPoly.add(new Point.LongPoint(Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i3)).getX()) + (d9 * d4)), Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i3)).getY()) + (d8 * d4))));
                            d9 = (d10 * this.cos) - (this.sin * d8);
                            d8 = (d8 * this.cos) + (this.sin * d10);
                            i6++;
                            acos = acos;
                            i3 = 0;
                        }
                        d2 = acos;
                    } else {
                        d2 = acos;
                        double d11 = -1.0d;
                        double d12 = -1.0d;
                        for (int i7 = 0; i7 < 4; i7++) {
                            this.destPoly.add(new Point.LongPoint(Math.round(((double) ((Point.LongPoint) this.srcPoly.get(0)).getX()) + (d11 * d4)), Math.round(((double) ((Point.LongPoint) this.srcPoly.get(0)).getY()) + (d12 * d4))));
                            if (d11 < 0.0d) {
                                d11 = 1.0d;
                            } else if (d12 < 0.0d) {
                                d12 = 1.0d;
                            } else {
                                d11 = -1.0d;
                            }
                        }
                    }
                    this.destPolys.add(this.destPoly);
                } else {
                    d2 = acos;
                    this.normals.clear();
                    int i8 = 0;
                    while (true) {
                        i2 = size - 1;
                        if (i8 >= i2) {
                            break;
                        }
                        i8++;
                        this.normals.add(Point.getUnitNormal((Point.LongPoint) this.srcPoly.get(i8), (Point.LongPoint) this.srcPoly.get(i8)));
                    }
                    if (polyNode2.getEndType() == Clipper.EndType.CLOSED_LINE || polyNode2.getEndType() == Clipper.EndType.CLOSED_POLYGON) {
                        c = 0;
                        this.normals.add(Point.getUnitNormal((Point.LongPoint) this.srcPoly.get(i2), (Point.LongPoint) this.srcPoly.get(0)));
                    } else {
                        this.normals.add(new Point.DoublePoint(this.normals.get(size - 2)));
                        c = 0;
                    }
                    if (polyNode2.getEndType() == Clipper.EndType.CLOSED_POLYGON) {
                        int[] iArr = new int[1];
                        iArr[c] = i2;
                        for (int i9 = 0; i9 < size; i9++) {
                            offsetPoint(i9, iArr, polyNode2.getJoinType());
                        }
                        this.destPolys.add(this.destPoly);
                    } else if (polyNode2.getEndType() == Clipper.EndType.CLOSED_LINE) {
                        int[] iArr2 = {i2};
                        for (int i10 = 0; i10 < size; i10++) {
                            offsetPoint(i10, iArr2, polyNode2.getJoinType());
                        }
                        this.destPolys.add(this.destPoly);
                        this.destPoly = new Path();
                        Point.DoublePoint doublePoint = this.normals.get(i2);
                        for (int i11 = i2; i11 > 0; i11--) {
                            int i12 = i11 - 1;
                            this.normals.set(i11, new Point.DoublePoint(-this.normals.get(i12).getX(), -this.normals.get(i12).getY()));
                        }
                        this.normals.set(0, new Point.DoublePoint(-doublePoint.getX(), -doublePoint.getY(), 0.0d));
                        iArr2[0] = 0;
                        while (i2 >= 0) {
                            offsetPoint(i2, iArr2, polyNode2.getJoinType());
                            i2--;
                        }
                        this.destPolys.add(this.destPoly);
                    } else {
                        int[] iArr3 = new int[1];
                        for (int i13 = 1; i13 < i2; i13++) {
                            offsetPoint(i13, iArr3, polyNode2.getJoinType());
                        }
                        if (polyNode2.getEndType() == Clipper.EndType.OPEN_BUTT) {
                            this.destPoly.add(new Point.LongPoint(Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i2)).getX()) + (this.normals.get(i2).getX() * d4)), Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i2)).getY()) + (this.normals.get(i2).getY() * d4)), 0));
                            this.destPoly.add(new Point.LongPoint(Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i2)).getX()) - (this.normals.get(i2).getX() * d4)), Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i2)).getY()) - (this.normals.get(i2).getY() * d4)), 0));
                        } else {
                            iArr3[0] = size - 2;
                            this.inA = 0.0d;
                            this.normals.set(i2, new Point.DoublePoint(-this.normals.get(i2).getX(), -this.normals.get(i2).getY()));
                            if (polyNode2.getEndType() == Clipper.EndType.OPEN_SQUARE) {
                                doSquare(i2, iArr3[0], true);
                            } else {
                                doRound(i2, iArr3[0]);
                            }
                        }
                        for (int i14 = i2; i14 > 0; i14--) {
                            int i15 = i14 - 1;
                            this.normals.set(i14, new Point.DoublePoint(-this.normals.get(i15).getX(), -this.normals.get(i15).getY()));
                        }
                        this.normals.set(0, new Point.DoublePoint(-this.normals.get(1).getX(), -this.normals.get(1).getY()));
                        iArr3[0] = i2;
                        for (int i16 = iArr3[0] - 1; i16 > 0; i16--) {
                            offsetPoint(i16, iArr3, polyNode2.getJoinType());
                        }
                        if (polyNode2.getEndType() == Clipper.EndType.OPEN_BUTT) {
                            i = 0;
                            this.destPoly.add(new Point.LongPoint(Math.round(((double) ((Point.LongPoint) this.srcPoly.get(0)).getX()) - (this.normals.get(0).getX() * d4)), Math.round(((double) ((Point.LongPoint) this.srcPoly.get(0)).getY()) - (this.normals.get(0).getY() * d4))));
                            this.destPoly.add(new Point.LongPoint(Math.round(((double) ((Point.LongPoint) this.srcPoly.get(0)).getX()) + (this.normals.get(0).getX() * d4)), Math.round(((double) ((Point.LongPoint) this.srcPoly.get(0)).getY()) + (this.normals.get(0).getY() * d4))));
                            d3 = 0.0d;
                        } else {
                            i = 0;
                            iArr3[0] = 1;
                            d3 = 0.0d;
                            this.inA = 0.0d;
                            if (polyNode2.getEndType() == Clipper.EndType.OPEN_SQUARE) {
                                doSquare(0, 1, true);
                            } else {
                                doRound(0, 1);
                            }
                        }
                        this.destPolys.add(this.destPoly);
                    }
                }
                i = 0;
                d3 = 0.0d;
            }
            i5++;
            i3 = i;
            d5 = d3;
            acos = d2;
        }
    }

    private void doRound(int i, int i2) {
        int max = Math.max((int) Math.round(this.stepsPerRad * Math.abs(Math.atan2(this.inA, (this.normals.get(i2).getX() * this.normals.get(i).getX()) + (this.normals.get(i2).getY() * this.normals.get(i).getY())))), 1);
        double x = this.normals.get(i2).getX();
        double y = this.normals.get(i2).getY();
        int i3 = 0;
        while (i3 < max) {
            this.destPoly.add(new Point.LongPoint(Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i)).getX()) + (this.delta * x)), Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i)).getY()) + (this.delta * y))));
            double d = (this.cos * x) - (this.sin * y);
            y = (y * this.cos) + (x * this.sin);
            i3++;
            x = d;
        }
        this.destPoly.add(new Point.LongPoint(Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i)).getX()) + (this.normals.get(i).getX() * this.delta)), Math.round(((double) ((Point.LongPoint) this.srcPoly.get(i)).getY()) + (this.normals.get(i).getY() * this.delta))));
    }

    private void doSquare(int i, int i2, boolean z) {
        int i3 = i;
        int i4 = i2;
        double x = this.normals.get(i4).getX();
        double y = this.normals.get(i4).getY();
        double x2 = this.normals.get(i3).getX();
        double y2 = this.normals.get(i3).getY();
        double x3 = (double) ((Point.LongPoint) this.srcPoly.get(i3)).getX();
        double y3 = (double) ((Point.LongPoint) this.srcPoly.get(i3)).getY();
        double d = x2;
        double tan = Math.tan(Math.atan2(this.inA, (x * x2) + (y * y2)) / 4.0d);
        double d2 = y2;
        double d3 = 0.0d;
        this.destPoly.add(new Point.LongPoint(Math.round((this.delta * (x - (z ? y * tan : 0.0d))) + x3), Math.round((this.delta * (y + (z ? x * tan : 0.0d))) + y3), 0));
        Path path = this.destPoly;
        long round = Math.round(x3 + (this.delta * (d + (z ? d2 * tan : 0.0d))));
        double d4 = this.delta;
        if (z) {
            d3 = d * tan;
        }
        path.add(new Point.LongPoint(round, Math.round(y3 + (d4 * (d2 - d3))), 0));
    }

    public void execute(Paths paths, double d) {
        Paths paths2 = paths;
        double d2 = d;
        paths.clear();
        fixOrientations();
        doOffset(d2);
        DefaultClipper defaultClipper = new DefaultClipper(1);
        defaultClipper.addPaths(this.destPolys, Clipper.PolyType.SUBJECT, true);
        if (d2 > 0.0d) {
            defaultClipper.execute(Clipper.ClipType.UNION, paths2, Clipper.PolyFillType.POSITIVE, Clipper.PolyFillType.POSITIVE);
            return;
        }
        LongRect bounds = this.destPolys.getBounds();
        Path path = new Path(4);
        path.add(new Point.LongPoint(bounds.left - 10, bounds.bottom + 10, 0));
        path.add(new Point.LongPoint(bounds.right + 10, bounds.bottom + 10, 0));
        path.add(new Point.LongPoint(bounds.right + 10, bounds.top - 10, 0));
        path.add(new Point.LongPoint(bounds.left - 10, bounds.top - 10, 0));
        defaultClipper.addPath(path, Clipper.PolyType.SUBJECT, true);
        defaultClipper.execute(Clipper.ClipType.UNION, paths2, Clipper.PolyFillType.NEGATIVE, Clipper.PolyFillType.NEGATIVE);
        if (paths.size() > 0) {
            paths2.remove(0);
        }
    }

    public void execute(PolyTree polyTree, double d) {
        PolyTree polyTree2 = polyTree;
        double d2 = d;
        polyTree.Clear();
        fixOrientations();
        doOffset(d2);
        DefaultClipper defaultClipper = new DefaultClipper(1);
        defaultClipper.addPaths(this.destPolys, Clipper.PolyType.SUBJECT, true);
        if (d2 > 0.0d) {
            defaultClipper.execute(Clipper.ClipType.UNION, polyTree2, Clipper.PolyFillType.POSITIVE, Clipper.PolyFillType.POSITIVE);
            return;
        }
        LongRect bounds = this.destPolys.getBounds();
        Path path = new Path(4);
        path.add(new Point.LongPoint(bounds.left - 10, bounds.bottom + 10, 0));
        path.add(new Point.LongPoint(bounds.right + 10, bounds.bottom + 10, 0));
        path.add(new Point.LongPoint(bounds.right + 10, bounds.top - 10, 0));
        path.add(new Point.LongPoint(bounds.left - 10, bounds.top - 10, 0));
        defaultClipper.addPath(path, Clipper.PolyType.SUBJECT, true);
        defaultClipper.execute(Clipper.ClipType.UNION, polyTree2, Clipper.PolyFillType.NEGATIVE, Clipper.PolyFillType.NEGATIVE);
        if (polyTree.getChildCount() != 1 || polyTree.getChilds().get(0).getChildCount() <= 0) {
            polyTree.Clear();
            return;
        }
        PolyNode polyNode = polyTree.getChilds().get(0);
        polyTree.getChilds().set(0, polyNode.getChilds().get(0));
        polyTree.getChilds().get(0).setParent(polyTree2);
        for (int i = 1; i < polyNode.getChildCount(); i++) {
            polyTree2.addChild(polyNode.getChilds().get(i));
        }
    }

    private void fixOrientations() {
        int i = 0;
        if (this.lowest.getX() < 0 || this.polyNodes.childs.get((int) this.lowest.getX()).getPolygon().orientation()) {
            while (i < this.polyNodes.getChildCount()) {
                PolyNode polyNode = this.polyNodes.childs.get(i);
                if (polyNode.getEndType() == Clipper.EndType.CLOSED_LINE && !polyNode.getPolygon().orientation()) {
                    Collections.reverse(polyNode.getPolygon());
                }
                i++;
            }
            return;
        }
        while (i < this.polyNodes.getChildCount()) {
            PolyNode polyNode2 = this.polyNodes.childs.get(i);
            if (polyNode2.getEndType() == Clipper.EndType.CLOSED_POLYGON || (polyNode2.getEndType() == Clipper.EndType.CLOSED_LINE && polyNode2.getPolygon().orientation())) {
                Collections.reverse(polyNode2.getPolygon());
            }
            i++;
        }
    }

    private void offsetPoint(int i, int[] iArr, Clipper.JoinType joinType) {
        long j;
        int i2;
        char c;
        int i3 = i;
        int i4 = iArr[0];
        double x = this.normals.get(i4).getX();
        double y = this.normals.get(i4).getY();
        double y2 = this.normals.get(i3).getY();
        double x2 = this.normals.get(i3).getX();
        long x3 = ((Point.LongPoint) this.srcPoly.get(i3)).getX();
        int i5 = i4;
        long y3 = ((Point.LongPoint) this.srcPoly.get(i3)).getY();
        this.inA = (x * y2) - (x2 * y);
        long j2 = y3;
        if (Math.abs(this.inA * this.delta) >= 1.0d) {
            j = j2;
            if (this.inA > 1.0d) {
                this.inA = 1.0d;
            } else if (this.inA < -1.0d) {
                this.inA = -1.0d;
            }
        } else if ((x * x2) + (y2 * y) > 0.0d) {
            this.destPoly.add(new Point.LongPoint(Math.round(((double) x3) + (x * this.delta)), Math.round(((double) j2) + (y * this.delta)), 0));
            return;
        } else {
            j = j2;
        }
        if (this.inA * this.delta >= 0.0d) {
            double d = y2;
            i2 = i;
            switch (joinType) {
                case MITER:
                    int i6 = i5;
                    c = 0;
                    double d2 = 1.0d + (x2 * x) + (d * y);
                    if (d2 < this.miterLim) {
                        doSquare(i2, i6, false);
                        break;
                    } else {
                        doMiter(i2, i6, d2);
                        break;
                    }
                case BEVEL:
                    c = 0;
                    doSquare(i2, i5, false);
                    break;
                case ROUND:
                    doRound(i2, i5);
                    break;
            }
        } else {
            double d3 = (double) x3;
            double d4 = (double) j;
            double d5 = y2;
            this.destPoly.add(new Point.LongPoint(Math.round((x * this.delta) + d3), Math.round((y * this.delta) + d4)));
            i2 = i;
            this.destPoly.add(this.srcPoly.get(i2));
            this.destPoly.add(new Point.LongPoint(Math.round(d3 + (x2 * this.delta)), Math.round(d4 + (this.delta * d5))));
        }
        c = 0;
        iArr[c] = i2;
    }
}
