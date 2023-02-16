package com.itextpdf.text.pdf.parser;

import java.util.Arrays;

public class Vector {
    public static final int I1 = 0;
    public static final int I2 = 1;
    public static final int I3 = 2;
    private final float[] vals = {0.0f, 0.0f, 0.0f};

    public Vector(float f, float f2, float f3) {
        this.vals[0] = f;
        this.vals[1] = f2;
        this.vals[2] = f3;
    }

    public float get(int i) {
        return this.vals[i];
    }

    public Vector cross(Matrix matrix) {
        return new Vector((this.vals[0] * matrix.get(0)) + (this.vals[1] * matrix.get(3)) + (this.vals[2] * matrix.get(6)), (this.vals[0] * matrix.get(1)) + (this.vals[1] * matrix.get(4)) + (this.vals[2] * matrix.get(7)), (this.vals[0] * matrix.get(2)) + (this.vals[1] * matrix.get(5)) + (this.vals[2] * matrix.get(8)));
    }

    public Vector subtract(Vector vector) {
        return new Vector(this.vals[0] - vector.vals[0], this.vals[1] - vector.vals[1], this.vals[2] - vector.vals[2]);
    }

    public Vector cross(Vector vector) {
        return new Vector((this.vals[1] * vector.vals[2]) - (this.vals[2] * vector.vals[1]), (this.vals[2] * vector.vals[0]) - (this.vals[0] * vector.vals[2]), (this.vals[0] * vector.vals[1]) - (this.vals[1] * vector.vals[0]));
    }

    public Vector normalize() {
        float length = length();
        return new Vector(this.vals[0] / length, this.vals[1] / length, this.vals[2] / length);
    }

    public Vector multiply(float f) {
        return new Vector(this.vals[0] * f, this.vals[1] * f, this.vals[2] * f);
    }

    public float dot(Vector vector) {
        return (this.vals[0] * vector.vals[0]) + (this.vals[1] * vector.vals[1]) + (this.vals[2] * vector.vals[2]);
    }

    public float length() {
        return (float) Math.sqrt((double) lengthSquared());
    }

    public float lengthSquared() {
        return (this.vals[0] * this.vals[0]) + (this.vals[1] * this.vals[1]) + (this.vals[2] * this.vals[2]);
    }

    public String toString() {
        return this.vals[0] + "," + this.vals[1] + "," + this.vals[2];
    }

    public int hashCode() {
        return 31 + Arrays.hashCode(this.vals);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && Arrays.equals(this.vals, ((Vector) obj).vals);
    }
}
