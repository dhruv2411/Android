package com.itextpdf.text.pdf.parser.clipper;

import java.lang.Comparable;
import java.lang.Number;
import java.math.BigInteger;
import java.util.Comparator;

public abstract class Point<T extends Number & Comparable<T>> {
    private static final NumberComparator NUMBER_COMPARATOR = new NumberComparator();
    protected T x;
    protected T y;
    protected T z;

    public static class DoublePoint extends Point<Double> {
        public DoublePoint() {
            this(0.0d, 0.0d);
        }

        public DoublePoint(double d, double d2) {
            this(d, d2, 0.0d);
        }

        public DoublePoint(double d, double d2, double d3) {
            super(Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3));
        }

        public DoublePoint(DoublePoint doublePoint) {
            super(doublePoint);
        }

        public double getX() {
            return ((Double) this.x).doubleValue();
        }

        public double getY() {
            return ((Double) this.y).doubleValue();
        }

        public double getZ() {
            return ((Double) this.z).doubleValue();
        }
    }

    public static class LongPoint extends Point<Long> {
        public static double getDeltaX(LongPoint longPoint, LongPoint longPoint2) {
            if (longPoint.getY() == longPoint2.getY()) {
                return -3.4E38d;
            }
            return ((double) (longPoint2.getX() - longPoint.getX())) / ((double) (longPoint2.getY() - longPoint.getY()));
        }

        public LongPoint() {
            this(0, 0);
        }

        public LongPoint(long j, long j2) {
            this(j, j2, 0);
        }

        public LongPoint(double d, double d2) {
            this((long) d, (long) d2);
        }

        public LongPoint(long j, long j2, long j3) {
            super(Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3));
        }

        public LongPoint(LongPoint longPoint) {
            super(longPoint);
        }

        public long getX() {
            return ((Long) this.x).longValue();
        }

        public long getY() {
            return ((Long) this.y).longValue();
        }

        public long getZ() {
            return ((Long) this.z).longValue();
        }
    }

    private static class NumberComparator<T extends Number & Comparable<T>> implements Comparator<T> {
        private NumberComparator() {
        }

        public int compare(T t, T t2) throws ClassCastException {
            return ((Comparable) t).compareTo(t2);
        }
    }

    static boolean arePointsClose(Point<? extends Number> point, Point<? extends Number> point2, double d) {
        double doubleValue = point.x.doubleValue() - point2.x.doubleValue();
        double doubleValue2 = point.y.doubleValue() - point2.y.doubleValue();
        return (doubleValue * doubleValue) + (doubleValue2 * doubleValue2) <= d;
    }

    static double distanceFromLineSqrd(Point<? extends Number> point, Point<? extends Number> point2, Point<? extends Number> point3) {
        double doubleValue = point2.y.doubleValue() - point3.y.doubleValue();
        double doubleValue2 = point3.x.doubleValue() - point2.x.doubleValue();
        double doubleValue3 = ((point.x.doubleValue() * doubleValue) + (point.y.doubleValue() * doubleValue2)) - ((point2.x.doubleValue() * doubleValue) + (point2.y.doubleValue() * doubleValue2));
        return (doubleValue3 * doubleValue3) / ((doubleValue * doubleValue) + (doubleValue2 * doubleValue2));
    }

    static DoublePoint getUnitNormal(LongPoint longPoint, LongPoint longPoint2) {
        double longValue = (double) (((Long) longPoint2.x).longValue() - ((Long) longPoint.x).longValue());
        double longValue2 = (double) (((Long) longPoint2.y).longValue() - ((Long) longPoint.y).longValue());
        if (longValue == 0.0d && longValue2 == 0.0d) {
            return new DoublePoint();
        }
        double sqrt = 1.0d / Math.sqrt((longValue * longValue) + (longValue2 * longValue2));
        return new DoublePoint(longValue2 * sqrt, -(longValue * sqrt));
    }

    protected static boolean isPt2BetweenPt1AndPt3(LongPoint longPoint, LongPoint longPoint2, LongPoint longPoint3) {
        if (longPoint.equals(longPoint3) || longPoint.equals(longPoint2) || longPoint3.equals(longPoint2)) {
            return false;
        }
        if (longPoint.x != longPoint3.x) {
            if ((((Long) longPoint2.x).longValue() > ((Long) longPoint.x).longValue()) == (((Long) longPoint2.x).longValue() < ((Long) longPoint3.x).longValue())) {
                return true;
            }
            return false;
        }
        if ((((Long) longPoint2.y).longValue() > ((Long) longPoint.y).longValue()) == (((Long) longPoint2.y).longValue() < ((Long) longPoint3.y).longValue())) {
            return true;
        }
        return false;
    }

    protected static boolean slopesEqual(LongPoint longPoint, LongPoint longPoint2, LongPoint longPoint3, boolean z2) {
        if (z2) {
            return BigInteger.valueOf(longPoint.getY() - longPoint2.getY()).multiply(BigInteger.valueOf(longPoint2.getX() - longPoint3.getX())).equals(BigInteger.valueOf(longPoint.getX() - longPoint2.getX()).multiply(BigInteger.valueOf(longPoint2.getY() - longPoint3.getY())));
        }
        return ((longPoint.getY() - longPoint2.getY()) * (longPoint2.getX() - longPoint3.getX())) - ((longPoint.getX() - longPoint2.getX()) * (longPoint2.getY() - longPoint3.getY())) == 0;
    }

    protected static boolean slopesEqual(LongPoint longPoint, LongPoint longPoint2, LongPoint longPoint3, LongPoint longPoint4, boolean z2) {
        if (z2) {
            return BigInteger.valueOf(longPoint.getY() - longPoint2.getY()).multiply(BigInteger.valueOf(longPoint3.getX() - longPoint4.getX())).equals(BigInteger.valueOf(longPoint.getX() - longPoint2.getX()).multiply(BigInteger.valueOf(longPoint3.getY() - longPoint4.getY())));
        }
        return ((longPoint.getY() - longPoint2.getY()) * (longPoint3.getX() - longPoint4.getX())) - ((longPoint.getX() - longPoint2.getX()) * (longPoint3.getY() - longPoint4.getY())) == 0;
    }

    static boolean slopesNearCollinear(LongPoint longPoint, LongPoint longPoint2, LongPoint longPoint3, double d) {
        if (Math.abs(((Long) longPoint.x).longValue() - ((Long) longPoint2.x).longValue()) > Math.abs(((Long) longPoint.y).longValue() - ((Long) longPoint2.y).longValue())) {
            if ((((Long) longPoint.x).longValue() > ((Long) longPoint2.x).longValue()) != (((Long) longPoint.x).longValue() < ((Long) longPoint3.x).longValue())) {
                if ((((Long) longPoint2.x).longValue() > ((Long) longPoint.x).longValue()) == (((Long) longPoint2.x).longValue() < ((Long) longPoint3.x).longValue())) {
                    if (distanceFromLineSqrd(longPoint2, longPoint, longPoint3) < d) {
                        return true;
                    }
                    return false;
                } else if (distanceFromLineSqrd(longPoint3, longPoint, longPoint2) < d) {
                    return true;
                } else {
                    return false;
                }
            } else if (distanceFromLineSqrd(longPoint, longPoint2, longPoint3) < d) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((((Long) longPoint.y).longValue() > ((Long) longPoint2.y).longValue()) != (((Long) longPoint.y).longValue() < ((Long) longPoint3.y).longValue())) {
                if ((((Long) longPoint2.y).longValue() > ((Long) longPoint.y).longValue()) == (((Long) longPoint2.y).longValue() < ((Long) longPoint3.y).longValue())) {
                    if (distanceFromLineSqrd(longPoint2, longPoint, longPoint3) < d) {
                        return true;
                    }
                    return false;
                } else if (distanceFromLineSqrd(longPoint3, longPoint, longPoint2) < d) {
                    return true;
                } else {
                    return false;
                }
            } else if (distanceFromLineSqrd(longPoint, longPoint2, longPoint3) < d) {
                return true;
            } else {
                return false;
            }
        }
    }

    protected Point(Point<T> point) {
        this(point.x, point.y, point.z);
    }

    protected Point(T t, T t2, T t3) {
        this.x = t;
        this.y = t2;
        this.z = t3;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Point)) {
            return false;
        }
        Point point = (Point) obj;
        if (NUMBER_COMPARATOR.compare(this.x, point.x) == 0 && NUMBER_COMPARATOR.compare(this.y, point.y) == 0) {
            return true;
        }
        return false;
    }

    public void set(Point<T> point) {
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
    }

    public void setX(T t) {
        this.x = t;
    }

    public void setY(T t) {
        this.y = t;
    }

    public void setZ(T t) {
        this.z = t;
    }

    public String toString() {
        return "Point [x=" + this.x + ", y=" + this.y + ", z=" + this.z + "]";
    }
}
