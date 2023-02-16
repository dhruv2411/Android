package com.itextpdf.text.pdf.qrcode;

final class GF256Poly {
    private final int[] coefficients;
    private final GF256 field;

    GF256Poly(GF256 gf256, int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.field = gf256;
        int i = 1;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.coefficients = iArr;
            return;
        }
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.coefficients = gf256.getZero().coefficients;
            return;
        }
        this.coefficients = new int[(length - i)];
        System.arraycopy(iArr, i, this.coefficients, 0, this.coefficients.length);
    }

    /* access modifiers changed from: package-private */
    public int[] getCoefficients() {
        return this.coefficients;
    }

    /* access modifiers changed from: package-private */
    public int getDegree() {
        return this.coefficients.length - 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isZero() {
        return this.coefficients[0] == 0;
    }

    /* access modifiers changed from: package-private */
    public int getCoefficient(int i) {
        return this.coefficients[(this.coefficients.length - 1) - i];
    }

    /* access modifiers changed from: package-private */
    public int evaluateAt(int i) {
        if (i == 0) {
            return getCoefficient(0);
        }
        if (i == 1) {
            int i2 = 0;
            for (int addOrSubtract : this.coefficients) {
                i2 = GF256.addOrSubtract(i2, addOrSubtract);
            }
            return i2;
        }
        int i3 = this.coefficients[0];
        for (int i4 = 1; i4 < r1; i4++) {
            i3 = GF256.addOrSubtract(this.field.multiply(i, i3), this.coefficients[i4]);
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public GF256Poly addOrSubtract(GF256Poly gF256Poly) {
        if (!this.field.equals(gF256Poly.field)) {
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        } else if (isZero()) {
            return gF256Poly;
        } else {
            if (gF256Poly.isZero()) {
                return this;
            }
            int[] iArr = this.coefficients;
            int[] iArr2 = gF256Poly.coefficients;
            if (iArr.length > iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr4, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr4[i] = GF256.addOrSubtract(iArr[i - length], iArr2[i]);
            }
            return new GF256Poly(this.field, iArr4);
        }
    }

    /* access modifiers changed from: package-private */
    public GF256Poly multiply(GF256Poly gF256Poly) {
        if (!this.field.equals(gF256Poly.field)) {
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        } else if (isZero() || gF256Poly.isZero()) {
            return this.field.getZero();
        } else {
            int[] iArr = this.coefficients;
            int length = iArr.length;
            int[] iArr2 = gF256Poly.coefficients;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = GF256.addOrSubtract(iArr3[i4], this.field.multiply(i2, iArr2[i3]));
                }
            }
            return new GF256Poly(this.field, iArr3);
        }
    }

    /* access modifiers changed from: package-private */
    public GF256Poly multiply(int i) {
        if (i == 0) {
            return this.field.getZero();
        }
        if (i == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.field.multiply(this.coefficients[i2], i);
        }
        return new GF256Poly(this.field, iArr);
    }

    /* access modifiers changed from: package-private */
    public GF256Poly multiplyByMonomial(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.field.getZero();
        } else {
            int length = this.coefficients.length;
            int[] iArr = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.field.multiply(this.coefficients[i3], i2);
            }
            return new GF256Poly(this.field, iArr);
        }
    }

    /* access modifiers changed from: package-private */
    public GF256Poly[] divide(GF256Poly gF256Poly) {
        if (!this.field.equals(gF256Poly.field)) {
            throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
        } else if (gF256Poly.isZero()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            GF256Poly zero = this.field.getZero();
            int inverse = this.field.inverse(gF256Poly.getCoefficient(gF256Poly.getDegree()));
            GF256Poly gF256Poly2 = zero;
            GF256Poly gF256Poly3 = this;
            while (gF256Poly3.getDegree() >= gF256Poly.getDegree() && !gF256Poly3.isZero()) {
                int degree = gF256Poly3.getDegree() - gF256Poly.getDegree();
                int multiply = this.field.multiply(gF256Poly3.getCoefficient(gF256Poly3.getDegree()), inverse);
                GF256Poly multiplyByMonomial = gF256Poly.multiplyByMonomial(degree, multiply);
                gF256Poly2 = gF256Poly2.addOrSubtract(this.field.buildMonomial(degree, multiply));
                gF256Poly3 = gF256Poly3.addOrSubtract(multiplyByMonomial);
            }
            return new GF256Poly[]{gF256Poly2, gF256Poly3};
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(8 * getDegree());
        for (int degree = getDegree(); degree >= 0; degree--) {
            int coefficient = getCoefficient(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    stringBuffer.append(" - ");
                    coefficient = -coefficient;
                } else if (stringBuffer.length() > 0) {
                    stringBuffer.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    int log = this.field.log(coefficient);
                    if (log == 0) {
                        stringBuffer.append('1');
                    } else if (log == 1) {
                        stringBuffer.append('a');
                    } else {
                        stringBuffer.append("a^");
                        stringBuffer.append(log);
                    }
                }
                if (degree != 0) {
                    if (degree == 1) {
                        stringBuffer.append('x');
                    } else {
                        stringBuffer.append("x^");
                        stringBuffer.append(degree);
                    }
                }
            }
        }
        return stringBuffer.toString();
    }
}
