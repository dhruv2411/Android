package com.itextpdf.awt.geom;

import java.io.Serializable;

public class Point extends Point2D implements Serializable {
    private static final long serialVersionUID = -5276940640259749850L;
    public double x;
    public double y;

    public Point() {
        setLocation(0, 0);
    }

    public Point(int i, int i2) {
        setLocation(i, i2);
    }

    public Point(double d, double d2) {
        setLocation(d, d2);
    }

    public Point(Point point) {
        setLocation(point.x, point.y);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point point = (Point) obj;
        if (this.x == point.x && this.y == point.y) {
            return true;
        }
        return false;
    }

    public String toString() {
        return getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Point getLocation() {
        return new Point(this.x, this.y);
    }

    public void setLocation(Point point) {
        setLocation(point.x, point.y);
    }

    public void setLocation(int i, int i2) {
        setLocation((double) i, (double) i2);
    }

    public void setLocation(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public void move(int i, int i2) {
        move((double) i, (double) i2);
    }

    public void move(double d, double d2) {
        setLocation(d, d2);
    }

    public void translate(int i, int i2) {
        translate((double) i, (double) i2);
    }

    public void translate(double d, double d2) {
        this.x += d;
        this.y += d2;
    }
}
