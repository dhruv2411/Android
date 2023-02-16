package com.itextpdf.text.pdf.parser;

import com.itextpdf.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class BezierCurve implements Shape {
    public static double curveCollinearityEpsilon = 1.0E-30d;
    public static double distanceToleranceManhattan = 0.4d;
    public static double distanceToleranceSquare = 0.025d;
    private final List<Point2D> controlPoints;

    public BezierCurve(List<Point2D> list) {
        this.controlPoints = new ArrayList(list);
    }

    public List<Point2D> getBasePoints() {
        return this.controlPoints;
    }

    public List<Point2D> getPiecewiseLinearApproximation() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.controlPoints.get(0));
        ArrayList arrayList2 = arrayList;
        recursiveApproximation(this.controlPoints.get(0).getX(), this.controlPoints.get(0).getY(), this.controlPoints.get(1).getX(), this.controlPoints.get(1).getY(), this.controlPoints.get(2).getX(), this.controlPoints.get(2).getY(), this.controlPoints.get(3).getX(), this.controlPoints.get(3).getY(), arrayList2);
        ArrayList arrayList3 = arrayList2;
        arrayList3.add(this.controlPoints.get(this.controlPoints.size() - 1));
        return arrayList3;
    }

    private void recursiveApproximation(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, List<Point2D> list) {
        double d9;
        double d10;
        List<Point2D> list2 = list;
        double d11 = (d + d3) / 2.0d;
        double d12 = (d2 + d4) / 2.0d;
        double d13 = (d3 + d5) / 2.0d;
        double d14 = (d4 + d6) / 2.0d;
        double d15 = (d5 + d7) / 2.0d;
        double d16 = (d6 + d8) / 2.0d;
        double d17 = (d11 + d13) / 2.0d;
        double d18 = (d12 + d14) / 2.0d;
        double d19 = (d13 + d15) / 2.0d;
        double d20 = (d14 + d16) / 2.0d;
        double d21 = d11;
        double d22 = (d17 + d19) / 2.0d;
        double d23 = d7 - d;
        double d24 = d8 - d2;
        double d25 = (d18 + d20) / 2.0d;
        double abs = Math.abs(((d3 - d7) * d24) - ((d4 - d8) * d23));
        double abs2 = Math.abs(((d5 - d7) * d24) - ((d6 - d8) * d23));
        if (abs > curveCollinearityEpsilon || abs2 > curveCollinearityEpsilon) {
            d9 = d22;
            d10 = d25;
            double d26 = abs + abs2;
            if (d26 * d26 <= distanceToleranceSquare * ((d23 * d23) + (d24 * d24))) {
                list2.add(new Point2D.Double(d9, d10));
                return;
            }
        } else if (Math.abs(((d + d5) - d3) - d3) + Math.abs(((d2 + d6) - d4) - d4) + Math.abs(((d3 + d7) - d5) - d5) + Math.abs(((d4 + d8) - d6) - d6) <= distanceToleranceManhattan) {
            list2.add(new Point2D.Double(d22, d25));
            return;
        } else {
            d9 = d22;
            d10 = d25;
        }
        double d27 = d9;
        double d28 = d10;
        List<Point2D> list3 = list;
        recursiveApproximation(d, d2, d21, d12, d17, d18, d27, d28, list3);
        recursiveApproximation(d27, d28, d19, d20, d15, d16, d7, d8, list3);
    }
}
