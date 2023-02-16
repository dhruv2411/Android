package com.itextpdf.awt.geom.gl;

import com.itextpdf.awt.geom.AffineTransform;
import com.itextpdf.awt.geom.PathIterator;
import com.itextpdf.awt.geom.Shape;

public class Crossing {
    public static final int CROSSING = 255;
    static final double DELTA = 1.0E-5d;
    static final double ROOT_DELTA = 1.0E-10d;
    static final int UNKNOWN = 254;

    public static int crossLine(double d, double d2, double d3, double d4, double d5, double d6) {
        if ((d5 < d && d5 < d3) || ((d5 > d && d5 > d3) || ((d6 > d2 && d6 > d4) || d == d3))) {
            return 0;
        }
        if ((d6 >= d2 || d6 >= d4) && ((d4 - d2) * (d5 - d)) / (d3 - d) <= d6 - d2) {
            return 0;
        }
        return d5 == d ? d < d3 ? 0 : -1 : d5 == d3 ? d < d3 ? 1 : 0 : d < d3 ? 1 : -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0063 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int intersectLine(double r14, double r16, double r18, double r20, double r22, double r24, double r26, double r28) {
        /*
            int r7 = (r26 > r14 ? 1 : (r26 == r14 ? 0 : -1))
            r8 = 0
            if (r7 >= 0) goto L_0x0009
            int r7 = (r26 > r18 ? 1 : (r26 == r18 ? 0 : -1))
            if (r7 < 0) goto L_0x0019
        L_0x0009:
            int r7 = (r22 > r14 ? 1 : (r22 == r14 ? 0 : -1))
            if (r7 <= 0) goto L_0x0011
            int r7 = (r22 > r18 ? 1 : (r22 == r18 ? 0 : -1))
            if (r7 > 0) goto L_0x0019
        L_0x0011:
            int r7 = (r24 > r16 ? 1 : (r24 == r16 ? 0 : -1))
            if (r7 <= 0) goto L_0x001a
            int r7 = (r24 > r20 ? 1 : (r24 == r20 ? 0 : -1))
            if (r7 <= 0) goto L_0x001a
        L_0x0019:
            return r8
        L_0x001a:
            int r7 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r7 >= 0) goto L_0x0023
            int r7 = (r28 > r20 ? 1 : (r28 == r20 ? 0 : -1))
            if (r7 >= 0) goto L_0x0023
            goto L_0x006c
        L_0x0023:
            int r7 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            r9 = 255(0xff, float:3.57E-43)
            if (r7 != 0) goto L_0x002a
            return r9
        L_0x002a:
            int r7 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r7 >= 0) goto L_0x0040
            int r7 = (r14 > r22 ? 1 : (r14 == r22 ? 0 : -1))
            if (r7 >= 0) goto L_0x0035
            r10 = r22
            goto L_0x0036
        L_0x0035:
            r10 = r14
        L_0x0036:
            int r7 = (r18 > r26 ? 1 : (r18 == r26 ? 0 : -1))
            if (r7 >= 0) goto L_0x003d
            r5 = r18
            goto L_0x004e
        L_0x003d:
            r5 = r26
            goto L_0x004e
        L_0x0040:
            int r7 = (r18 > r22 ? 1 : (r18 == r22 ? 0 : -1))
            if (r7 >= 0) goto L_0x0047
            r10 = r22
            goto L_0x0049
        L_0x0047:
            r10 = r18
        L_0x0049:
            int r7 = (r14 > r26 ? 1 : (r14 == r26 ? 0 : -1))
            if (r7 >= 0) goto L_0x003d
            r5 = r14
        L_0x004e:
            double r3 = r20 - r16
            double r12 = r18 - r14
            double r3 = r3 / r12
            double r10 = r10 - r14
            double r10 = r10 * r3
            double r10 = r10 + r16
            double r5 = r5 - r14
            double r3 = r3 * r5
            double r3 = r3 + r16
            int r1 = (r10 > r24 ? 1 : (r10 == r24 ? 0 : -1))
            if (r1 >= 0) goto L_0x0064
            int r1 = (r3 > r24 ? 1 : (r3 == r24 ? 0 : -1))
            if (r1 >= 0) goto L_0x0064
            return r8
        L_0x0064:
            int r1 = (r10 > r28 ? 1 : (r10 == r28 ? 0 : -1))
            if (r1 <= 0) goto L_0x00a0
            int r1 = (r3 > r28 ? 1 : (r3 == r28 ? 0 : -1))
            if (r1 <= 0) goto L_0x00a0
        L_0x006c:
            int r1 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r1 != 0) goto L_0x0071
            return r8
        L_0x0071:
            int r1 = (r22 > r14 ? 1 : (r22 == r14 ? 0 : -1))
            r2 = -1
            if (r1 != 0) goto L_0x007d
            int r1 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r1 >= 0) goto L_0x007b
            goto L_0x007c
        L_0x007b:
            r8 = r2
        L_0x007c:
            return r8
        L_0x007d:
            int r1 = (r22 > r18 ? 1 : (r22 == r18 ? 0 : -1))
            r3 = 1
            if (r1 != 0) goto L_0x0088
            int r1 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r1 >= 0) goto L_0x0087
            r8 = r3
        L_0x0087:
            return r8
        L_0x0088:
            int r1 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r1 >= 0) goto L_0x0096
            int r1 = (r14 > r22 ? 1 : (r14 == r22 ? 0 : -1))
            if (r1 >= 0) goto L_0x0095
            int r0 = (r22 > r18 ? 1 : (r22 == r18 ? 0 : -1))
            if (r0 >= 0) goto L_0x0095
            r8 = r3
        L_0x0095:
            return r8
        L_0x0096:
            int r1 = (r18 > r22 ? 1 : (r18 == r22 ? 0 : -1))
            if (r1 >= 0) goto L_0x009f
            int r1 = (r22 > r14 ? 1 : (r22 == r14 ? 0 : -1))
            if (r1 >= 0) goto L_0x009f
            r8 = r2
        L_0x009f:
            return r8
        L_0x00a0:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.awt.geom.gl.Crossing.intersectLine(double, double, double, double, double, double, double, double):int");
    }

    public static boolean isInsideEvenOdd(int i) {
        return (i & 1) != 0;
    }

    public static boolean isInsideNonZero(int i) {
        return i != 0;
    }

    public static boolean isZero(double d) {
        return -1.0E-5d < d && d < 1.0E-5d;
    }

    public static int solveQuad(double[] dArr, double[] dArr2) {
        double[] dArr3 = dArr2;
        double d = dArr[2];
        int i = 1;
        double d2 = dArr[1];
        double d3 = dArr[0];
        if (d != 0.0d) {
            double d4 = (d2 * d2) - ((4.0d * d) * d3);
            if (d4 < 0.0d) {
                return 0;
            }
            double sqrt = Math.sqrt(d4);
            double d5 = -d2;
            double d6 = d * 2.0d;
            dArr3[0] = (d5 + sqrt) / d6;
            if (sqrt != 0.0d) {
                dArr3[1] = (d5 - sqrt) / d6;
                i = 2;
            }
        } else if (d2 == 0.0d) {
            return -1;
        } else {
            dArr3[0] = (-d3) / d2;
        }
        return fixRoots(dArr3, i);
    }

    public static int solveCubic(double[] dArr, double[] dArr2) {
        double[] dArr3 = dArr2;
        double d = dArr[3];
        if (d == 0.0d) {
            return solveQuad(dArr, dArr2);
        }
        double d2 = dArr[2] / d;
        int i = 1;
        double d3 = dArr[1] / d;
        double d4 = dArr[0] / d;
        double d5 = ((d2 * d2) - (3.0d * d3)) / 9.0d;
        double d6 = (((((2.0d * d2) * d2) * d2) - ((9.0d * d2) * d3)) + (27.0d * d4)) / 54.0d;
        double d7 = d5 * d5 * d5;
        double d8 = d6 * d6;
        double d9 = (-d2) / 3.0d;
        if (d8 < d7) {
            double acos = Math.acos(d6 / Math.sqrt(d7)) / 3.0d;
            double sqrt = -2.0d * Math.sqrt(d5);
            dArr3[0] = (Math.cos(acos) * sqrt) + d9;
            dArr3[1] = (Math.cos(acos + 2.0943951023931953d) * sqrt) + d9;
            dArr3[2] = (sqrt * Math.cos(acos - 2.0943951023931953d)) + d9;
            i = 3;
        } else {
            double d10 = d8 - d7;
            double d11 = d9;
            double d12 = d10;
            double pow = Math.pow(Math.abs(d6) + Math.sqrt(d10), 0.3333333333333333d);
            if (d6 > 0.0d) {
                pow = -pow;
            }
            if (-1.0E-10d >= pow || pow >= ROOT_DELTA) {
                double d13 = pow + (d5 / pow);
                dArr3[0] = d13 + d11;
                if (-1.0E-10d < d12 && d12 < ROOT_DELTA) {
                    dArr3[1] = ((-d13) / 2.0d) + d11;
                    i = 2;
                }
            } else {
                dArr3[0] = d11;
            }
        }
        return fixRoots(dArr3, i);
    }

    static int fixRoots(double[] dArr, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i2 + 1;
            int i5 = i4;
            while (true) {
                if (i5 >= i) {
                    dArr[i3] = dArr[i2];
                    i3++;
                    break;
                } else if (isZero(dArr[i2] - dArr[i5])) {
                    break;
                } else {
                    i5++;
                }
            }
            i2 = i4;
        }
        return i3;
    }

    public static class QuadCurve {
        double Ax = (this.ax - this.Bx);
        double Ay = (this.ay - this.By);
        double Bx = (this.bx + this.bx);
        double By = (this.by + this.by);
        double ax;
        double ay;
        double bx;
        double by;

        public QuadCurve(double d, double d2, double d3, double d4, double d5, double d6) {
            this.ax = d5 - d;
            this.ay = d6 - d2;
            this.bx = d3 - d;
            this.by = d4 - d2;
        }

        /* access modifiers changed from: package-private */
        public int cross(double[] dArr, int i, double d, double d2) {
            int i2 = 0;
            int i3 = i;
            for (int i4 = 0; i4 < i3; i4++) {
                double d3 = dArr[i4];
                if (d3 >= -1.0E-5d && d3 <= 1.00001d) {
                    if (d3 < 1.0E-5d) {
                        if (d < 0.0d) {
                            if ((this.bx != 0.0d ? this.bx : this.ax - this.bx) < 0.0d) {
                                i2--;
                            }
                        }
                    } else if (d3 > 0.99999d) {
                        if (d < this.ay) {
                            if ((this.ax != this.bx ? this.ax - this.bx : this.bx) > 0.0d) {
                                i2++;
                            }
                        }
                    } else if (((this.Ay * d3) + this.By) * d3 > d2) {
                        double d4 = (d3 * this.Ax) + this.bx;
                        if (d4 <= -1.0E-5d || d4 >= 1.0E-5d) {
                            i2 += d4 > 0.0d ? 1 : -1;
                        }
                    }
                }
            }
            return i2;
        }

        /* access modifiers changed from: package-private */
        public int solvePoint(double[] dArr, double d) {
            return Crossing.solveQuad(new double[]{-d, this.Bx, this.Ax}, dArr);
        }

        /* access modifiers changed from: package-private */
        public int solveExtrem(double[] dArr) {
            int i = 0;
            if (this.Ax != 0.0d) {
                dArr[0] = (-this.Bx) / (this.Ax + this.Ax);
                i = 1;
            }
            if (this.Ay == 0.0d) {
                return i;
            }
            dArr[i] = (-this.By) / (this.Ay + this.Ay);
            return i + 1;
        }

        /* access modifiers changed from: package-private */
        public int addBound(double[] dArr, int i, double[] dArr2, int i2, double d, double d2, boolean z, int i3) {
            int i4 = i;
            int i5 = i3;
            int i6 = i2;
            for (int i7 = 0; i7 < i6; i7++) {
                double d3 = dArr2[i7];
                if (d3 > -1.0E-5d && d3 < 1.00001d) {
                    double d4 = ((this.Ax * d3) + this.Bx) * d3;
                    if (d <= d4 && d4 <= d2) {
                        int i8 = i4 + 1;
                        dArr[i4] = d3;
                        int i9 = i8 + 1;
                        dArr[i8] = d4;
                        int i10 = i9 + 1;
                        dArr[i9] = d3 * ((this.Ay * d3) + this.By);
                        i4 = i10 + 1;
                        dArr[i10] = (double) i5;
                        if (z) {
                            i5++;
                        }
                    }
                }
            }
            return i4;
        }
    }

    public static class CubicCurve {
        double Ax = ((this.ax - this.Bx) - this.Cx);
        double Ax3 = ((this.Ax + this.Ax) + this.Ax);
        double Ay = ((this.ay - this.By) - this.Cy);
        double Bx = ((((this.cx + this.cx) + this.cx) - this.Cx) - this.Cx);
        double Bx2 = (this.Bx + this.Bx);
        double By = ((((this.cy + this.cy) + this.cy) - this.Cy) - this.Cy);
        double Cx = ((this.bx + this.bx) + this.bx);
        double Cy = ((this.by + this.by) + this.by);
        double ax;
        double ay;
        double bx;
        double by;
        double cx;
        double cy;

        public CubicCurve(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
            this.ax = d7 - d;
            this.ay = d8 - d2;
            this.bx = d3 - d;
            this.by = d4 - d2;
            this.cx = d5 - d;
            this.cy = d6 - d2;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0082  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x00d3 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int cross(double[] r17, int r18, double r19, double r21) {
            /*
                r16 = this;
                r0 = r16
                r1 = 0
                r2 = r1
                r3 = r2
                r1 = r18
            L_0x0007:
                if (r2 >= r1) goto L_0x00d7
                r4 = r17[r2]
                r6 = -4691351453243840271(0xbee4f8b588e368f1, double:-1.0E-5)
                int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r8 < 0) goto L_0x00d3
                r8 = 4607182463836013682(0x3ff0000a7c5ac472, double:1.00001)
                int r10 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r10 <= 0) goto L_0x001f
                goto L_0x00d3
            L_0x001f:
                r8 = 4532020583610935537(0x3ee4f8b588e368f1, double:1.0E-5)
                int r10 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                r11 = 0
                if (r10 >= 0) goto L_0x0052
                int r4 = (r19 > r11 ? 1 : (r19 == r11 ? 0 : -1))
                if (r4 >= 0) goto L_0x00d3
                double r4 = r0.bx
                int r6 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
                if (r6 == 0) goto L_0x0037
                double r4 = r0.bx
                goto L_0x004a
            L_0x0037:
                double r4 = r0.cx
                double r6 = r0.bx
                int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r8 == 0) goto L_0x0045
                double r4 = r0.cx
                double r6 = r0.bx
            L_0x0043:
                double r4 = r4 - r6
                goto L_0x004a
            L_0x0045:
                double r4 = r0.ax
                double r6 = r0.cx
                goto L_0x0043
            L_0x004a:
                int r6 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
                if (r6 >= 0) goto L_0x00d3
                int r3 = r3 + -1
                goto L_0x00d3
            L_0x0052:
                r13 = 4607182328728024861(0x3fefffeb074a771d, double:0.99999)
                int r10 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
                if (r10 <= 0) goto L_0x0085
                double r4 = r0.ay
                int r6 = (r19 > r4 ? 1 : (r19 == r4 ? 0 : -1))
                if (r6 >= 0) goto L_0x00d3
                double r4 = r0.ax
                double r6 = r0.cx
                int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r8 == 0) goto L_0x006f
                double r4 = r0.ax
                double r6 = r0.cx
            L_0x006d:
                double r4 = r4 - r6
                goto L_0x007e
            L_0x006f:
                double r4 = r0.cx
                double r6 = r0.bx
                int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r8 == 0) goto L_0x007c
                double r4 = r0.cx
                double r6 = r0.bx
                goto L_0x006d
            L_0x007c:
                double r4 = r0.bx
            L_0x007e:
                int r6 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
                if (r6 <= 0) goto L_0x00d3
                int r3 = r3 + 1
                goto L_0x00d3
            L_0x0085:
                double r11 = r0.Ay
                double r11 = r11 * r4
                double r8 = r0.By
                double r11 = r11 + r8
                double r11 = r11 * r4
                double r8 = r0.Cy
                double r11 = r11 + r8
                double r11 = r11 * r4
                int r10 = (r11 > r21 ? 1 : (r11 == r21 ? 0 : -1))
                if (r10 <= 0) goto L_0x00d3
                double r10 = r0.Ax3
                double r10 = r10 * r4
                double r6 = r0.Bx2
                double r10 = r10 + r6
                double r10 = r10 * r4
                double r6 = r0.Cx
                double r10 = r10 + r6
                r6 = -4691351453243840271(0xbee4f8b588e368f1, double:-1.0E-5)
                int r12 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
                if (r12 <= 0) goto L_0x00c9
                r12 = 4532020583610935537(0x3ee4f8b588e368f1, double:1.0E-5)
                int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
                if (r14 >= 0) goto L_0x00c9
                double r10 = r0.Ax3
                double r6 = r0.Ax3
                double r10 = r10 + r6
                double r4 = r4 * r10
                double r6 = r0.Bx2
                double r4 = r4 + r6
                r6 = -4691351453243840271(0xbee4f8b588e368f1, double:-1.0E-5)
                int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r10 < 0) goto L_0x00d3
                int r6 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
                if (r6 <= 0) goto L_0x00c7
                goto L_0x00d3
            L_0x00c7:
                double r10 = r0.ax
            L_0x00c9:
                r4 = 0
                int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
                if (r6 <= 0) goto L_0x00d1
                r4 = 1
                goto L_0x00d2
            L_0x00d1:
                r4 = -1
            L_0x00d2:
                int r3 = r3 + r4
            L_0x00d3:
                int r2 = r2 + 1
                goto L_0x0007
            L_0x00d7:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.awt.geom.gl.Crossing.CubicCurve.cross(double[], int, double, double):int");
        }

        /* access modifiers changed from: package-private */
        public int solvePoint(double[] dArr, double d) {
            return Crossing.solveCubic(new double[]{-d, this.Cx, this.Bx, this.Ax}, dArr);
        }

        /* access modifiers changed from: package-private */
        public int solveExtremX(double[] dArr) {
            return Crossing.solveQuad(new double[]{this.Cx, this.Bx2, this.Ax3}, dArr);
        }

        /* access modifiers changed from: package-private */
        public int solveExtremY(double[] dArr) {
            return Crossing.solveQuad(new double[]{this.Cy, this.By + this.By, this.Ay + this.Ay + this.Ay}, dArr);
        }

        /* access modifiers changed from: package-private */
        public int addBound(double[] dArr, int i, double[] dArr2, int i2, double d, double d2, boolean z, int i3) {
            int i4 = i;
            int i5 = i3;
            int i6 = i2;
            for (int i7 = 0; i7 < i6; i7++) {
                double d3 = dArr2[i7];
                if (d3 > -1.0E-5d && d3 < 1.00001d) {
                    double d4 = ((((this.Ax * d3) + this.Bx) * d3) + this.Cx) * d3;
                    if (d <= d4 && d4 <= d2) {
                        int i8 = i4 + 1;
                        dArr[i4] = d3;
                        int i9 = i8 + 1;
                        dArr[i8] = d4;
                        int i10 = i9 + 1;
                        dArr[i9] = d3 * ((((this.Ay * d3) + this.By) * d3) + this.Cy);
                        i4 = i10 + 1;
                        dArr[i10] = (double) i5;
                        if (z) {
                            i5++;
                        }
                    }
                }
            }
            return i4;
        }
    }

    public static int crossQuad(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        if ((d7 < d && d7 < d3 && d7 < d5) || ((d7 > d && d7 > d3 && d7 > d5) || ((d8 > d2 && d8 > d4 && d8 > d6) || (d == d3 && d3 == d5)))) {
            return 0;
        }
        if (d8 < d2 && d8 < d4 && d8 < d6 && d7 != d && d7 != d5) {
            return d < d5 ? (d >= d7 || d7 >= d5) ? 0 : 1 : (d5 >= d7 || d7 >= d) ? 0 : -1;
        }
        QuadCurve quadCurve = r0;
        QuadCurve quadCurve2 = new QuadCurve(d, d2, d3, d4, d5, d6);
        double d9 = d8 - d2;
        double[] dArr = new double[3];
        QuadCurve quadCurve3 = quadCurve;
        return quadCurve3.cross(dArr, quadCurve3.solvePoint(dArr, d7 - d), d9, d9);
    }

    public static int crossCubic(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        if ((d9 < d && d9 < d3 && d9 < d5 && d9 < d7) || ((d9 > d && d9 > d3 && d9 > d5 && d9 > d7) || ((d10 > d2 && d10 > d4 && d10 > d6 && d10 > d8) || (d == d3 && d3 == d5 && d5 == d7)))) {
            return 0;
        }
        if (d10 < d2 && d10 < d4 && d10 < d6 && d10 < d8 && d9 != d && d9 != d7) {
            return d < d7 ? (d >= d9 || d9 >= d7) ? 0 : 1 : (d7 >= d9 || d9 >= d) ? 0 : -1;
        }
        CubicCurve cubicCurve = r0;
        CubicCurve cubicCurve2 = new CubicCurve(d, d2, d3, d4, d5, d6, d7, d8);
        double d11 = d10 - d2;
        double[] dArr = new double[3];
        CubicCurve cubicCurve3 = cubicCurve;
        return cubicCurve3.cross(dArr, cubicCurve3.solvePoint(dArr, d9 - d), d11, d11);
    }

    public static int crossPath(PathIterator pathIterator, double d, double d2) {
        double d3;
        double d4;
        double d5;
        double d6;
        double[] dArr = new double[6];
        int i = 0;
        double d7 = 0.0d;
        int i2 = 0;
        double d8 = 0.0d;
        double d9 = 0.0d;
        double d10 = 0.0d;
        while (true) {
            if (!pathIterator.isDone()) {
                switch (pathIterator.currentSegment(dArr)) {
                    case 0:
                        if (!(d8 == d7 && d9 == d10)) {
                            i2 += crossLine(d8, d9, d7, d10, d, d2);
                        }
                        d7 = dArr[0];
                        d9 = dArr[1];
                        d10 = d9;
                        d8 = d7;
                        break;
                    case 1:
                        double d11 = dArr[0];
                        double d12 = dArr[1];
                        i2 += crossLine(d8, d9, d11, d12, d, d2);
                        d8 = d11;
                        d9 = d12;
                        break;
                    case 2:
                        double d13 = dArr[0];
                        double d14 = dArr[1];
                        double d15 = dArr[2];
                        double d16 = dArr[3];
                        i2 += crossQuad(d8, d9, d13, d14, d15, d16, d, d2);
                        d8 = d15;
                        d9 = d16;
                        break;
                    case 3:
                        double d17 = dArr[0];
                        double d18 = dArr[1];
                        double d19 = dArr[2];
                        double d20 = dArr[3];
                        double d21 = dArr[4];
                        double d22 = dArr[5];
                        i2 += crossCubic(d8, d9, d17, d18, d19, d20, d21, d22, d, d2);
                        d8 = d21;
                        d9 = d22;
                        break;
                    case 4:
                        if (!(d9 == d10 && d8 == d7)) {
                            i2 += crossLine(d8, d9, d7, d10, d, d2);
                            d8 = d7;
                            d9 = d10;
                            break;
                        }
                }
                if (d == d8 && d2 == d9) {
                    d3 = d7;
                    d6 = d8;
                    d4 = d10;
                    d5 = d4;
                } else {
                    pathIterator.next();
                }
            } else {
                d3 = d7;
                d4 = d9;
                i = i2;
                d5 = d10;
                d6 = d8;
            }
        }
        return d4 != d5 ? i + crossLine(d6, d4, d3, d5, d, d2) : i;
    }

    public static int crossShape(Shape shape, double d, double d2) {
        if (!shape.getBounds2D().contains(d, d2)) {
            return 0;
        }
        return crossPath(shape.getPathIterator((AffineTransform) null), d, d2);
    }

    static void sortBound(double[] dArr, int i) {
        int i2 = 0;
        while (i2 < i - 4) {
            int i3 = i2 + 4;
            int i4 = i2;
            for (int i5 = i3; i5 < i; i5 += 4) {
                if (dArr[i4] > dArr[i5]) {
                    i4 = i5;
                }
            }
            if (i4 != i2) {
                double d = dArr[i2];
                dArr[i2] = dArr[i4];
                dArr[i4] = d;
                int i6 = i2 + 1;
                double d2 = dArr[i6];
                int i7 = i4 + 1;
                dArr[i6] = dArr[i7];
                dArr[i7] = d2;
                int i8 = i2 + 2;
                double d3 = dArr[i8];
                int i9 = i4 + 2;
                dArr[i8] = dArr[i9];
                dArr[i9] = d3;
                int i10 = i2 + 3;
                double d4 = dArr[i10];
                int i11 = i4 + 3;
                dArr[i10] = dArr[i11];
                dArr[i11] = d4;
            }
            i2 = i3;
        }
    }

    static int crossBound(double[] dArr, int i, double d, double d2) {
        if (i == 0) {
            return 0;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 2; i4 < i; i4 += 4) {
            if (dArr[i4] < d) {
                i3++;
            } else if (dArr[i4] <= d2) {
                return 255;
            } else {
                i2++;
            }
        }
        if (i2 == 0) {
            return 0;
        }
        if (i3 == 0) {
            return 254;
        }
        sortBound(dArr, i);
        boolean z = dArr[2] > d2;
        int i5 = 6;
        while (i5 < i) {
            boolean z2 = dArr[i5] > d2;
            if (z != z2 && dArr[i5 + 1] != dArr[i5 - 3]) {
                return 255;
            }
            i5 += 4;
            z = z2;
        }
        return 254;
    }

    public static int intersectQuad(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        if ((d9 < d && d9 < d3 && d9 < d5) || ((d7 > d && d7 > d3 && d7 > d5) || (d8 > d2 && d8 > d4 && d8 > d6))) {
            return 0;
        }
        if (d10 < d2 && d10 < d4 && d10 < d6 && d7 != d && d7 != d5) {
            return d < d5 ? (d >= d7 || d7 >= d5) ? 0 : 1 : (d5 >= d7 || d7 >= d) ? 0 : -1;
        }
        QuadCurve quadCurve = r0;
        QuadCurve quadCurve2 = new QuadCurve(d, d2, d3, d4, d5, d6);
        double d11 = d7 - d;
        double d12 = d8 - d2;
        double d13 = d9 - d;
        double d14 = d10 - d2;
        double[] dArr = new double[3];
        double[] dArr2 = new double[3];
        QuadCurve quadCurve3 = quadCurve;
        int solvePoint = quadCurve3.solvePoint(dArr, d11);
        int solvePoint2 = quadCurve3.solvePoint(dArr2, d13);
        if (solvePoint == 0 && solvePoint2 == 0) {
            return 0;
        }
        double[] dArr3 = new double[28];
        double d15 = d11 - 1.0E-5d;
        QuadCurve quadCurve4 = quadCurve3;
        double[] dArr4 = dArr3;
        double d16 = d13 + 1.0E-5d;
        double[] dArr5 = dArr;
        int addBound = quadCurve3.addBound(dArr3, 0, dArr, solvePoint, d15, d16, false, 0);
        double[] dArr6 = dArr4;
        double[] dArr7 = dArr2;
        QuadCurve quadCurve5 = quadCurve4;
        double d17 = d14;
        QuadCurve quadCurve6 = quadCurve5;
        int addBound2 = quadCurve5.addBound(dArr6, quadCurve4.addBound(dArr6, addBound, dArr7, solvePoint2, d15, d16, false, 1), dArr7, quadCurve5.solveExtrem(dArr7), d15, d16, true, 2);
        if (d7 < d && d < d9) {
            int i = addBound2 + 1;
            dArr4[addBound2] = 0.0d;
            int i2 = i + 1;
            dArr4[i] = 0.0d;
            int i3 = i2 + 1;
            dArr4[i2] = 0.0d;
            addBound2 = i3 + 1;
            dArr4[i3] = 4.0d;
        }
        if (d7 < d5 && d5 < d9) {
            int i4 = addBound2 + 1;
            dArr4[addBound2] = 1.0d;
            int i5 = i4 + 1;
            dArr4[i4] = quadCurve6.ax;
            int i6 = i5 + 1;
            dArr4[i5] = quadCurve6.ay;
            addBound2 = i6 + 1;
            dArr4[i6] = 5.0d;
        }
        int crossBound = crossBound(dArr4, addBound2, d12, d17);
        if (crossBound != 254) {
            return crossBound;
        }
        return quadCurve6.cross(dArr5, solvePoint, d12, d17);
    }

    public static int intersectCubic(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10, double d11, double d12) {
        if ((d11 < d && d11 < d3 && d11 < d5 && d11 < d7) || ((d9 > d && d9 > d3 && d9 > d5 && d9 > d7) || (d10 > d2 && d10 > d4 && d10 > d6 && d10 > d8))) {
            return 0;
        }
        if (d12 < d2 && d12 < d4 && d12 < d6 && d12 < d8 && d9 != d && d9 != d7) {
            return d < d7 ? (d >= d9 || d9 >= d7) ? 0 : 1 : (d7 >= d9 || d9 >= d) ? 0 : -1;
        }
        CubicCurve cubicCurve = r0;
        CubicCurve cubicCurve2 = new CubicCurve(d, d2, d3, d4, d5, d6, d7, d8);
        double d13 = d9 - d;
        double d14 = d10 - d2;
        double d15 = d11 - d;
        double d16 = d12 - d2;
        double[] dArr = new double[3];
        double[] dArr2 = new double[3];
        CubicCurve cubicCurve3 = cubicCurve;
        int solvePoint = cubicCurve3.solvePoint(dArr, d13);
        int solvePoint2 = cubicCurve3.solvePoint(dArr2, d15);
        if (solvePoint == 0 && solvePoint2 == 0) {
            return 0;
        }
        double[] dArr3 = new double[40];
        double d17 = d13 - 1.0E-5d;
        double[] dArr4 = dArr3;
        double d18 = d15 + 1.0E-5d;
        CubicCurve cubicCurve4 = cubicCurve3;
        double[] dArr5 = dArr2;
        int addBound = cubicCurve3.addBound(dArr3, 0, dArr, solvePoint, d17, d18, false, 0);
        double[] dArr6 = dArr4;
        CubicCurve cubicCurve5 = cubicCurve4;
        double[] dArr7 = dArr5;
        double[] dArr8 = dArr;
        double[] dArr9 = dArr7;
        double d19 = d16;
        CubicCurve cubicCurve6 = cubicCurve5;
        int addBound2 = cubicCurve6.addBound(dArr6, cubicCurve5.addBound(dArr6, cubicCurve4.addBound(dArr6, addBound, dArr5, solvePoint2, d17, d18, false, 1), dArr7, cubicCurve5.solveExtremX(dArr7), d17, d18, true, 2), dArr9, cubicCurve6.solveExtremY(dArr9), d17, d18, true, 4);
        if (d9 < d && d < d11) {
            int i = addBound2 + 1;
            dArr4[addBound2] = 0.0d;
            int i2 = i + 1;
            dArr4[i] = 0.0d;
            int i3 = i2 + 1;
            dArr4[i2] = 0.0d;
            addBound2 = i3 + 1;
            dArr4[i3] = 6.0d;
        }
        if (d9 < d7 && d7 < d11) {
            int i4 = addBound2 + 1;
            dArr4[addBound2] = 1.0d;
            int i5 = i4 + 1;
            dArr4[i4] = cubicCurve6.ax;
            int i6 = i5 + 1;
            dArr4[i5] = cubicCurve6.ay;
            addBound2 = i6 + 1;
            dArr4[i6] = 7.0d;
        }
        int crossBound = crossBound(dArr4, addBound2, d14, d19);
        if (crossBound != 254) {
            return crossBound;
        }
        return cubicCurve6.cross(dArr8, solvePoint, d14, d19);
    }

    public static int intersectPath(PathIterator pathIterator, double d, double d2, double d3, double d4) {
        double d5;
        int i;
        double[] dArr = new double[6];
        double d6 = d + d3;
        double d7 = d2 + d4;
        double d8 = 0.0d;
        double d9 = 0.0d;
        double d10 = 0.0d;
        double d11 = 0.0d;
        int i2 = 0;
        while (true) {
            int i3 = 255;
            if (!pathIterator.isDone()) {
                switch (pathIterator.currentSegment(dArr)) {
                    case 0:
                        i = (d8 == d11 && d9 == d10) ? 0 : intersectLine(d8, d9, d11, d10, d, d2, d6, d7);
                        d5 = dArr[0];
                        d11 = d5;
                        d10 = dArr[1];
                        i3 = 255;
                        d9 = d10;
                        break;
                    case 1:
                        double d12 = dArr[0];
                        double d13 = dArr[1];
                        i = intersectLine(d8, d9, d12, d13, d, d2, d6, d7);
                        d9 = d13;
                        i3 = 255;
                        d5 = d12;
                        break;
                    case 2:
                        double d14 = dArr[0];
                        double d15 = dArr[1];
                        d5 = dArr[2];
                        double d16 = dArr[3];
                        i = intersectQuad(d8, d9, d14, d15, d5, d16, d, d2, d6, d7);
                        d9 = d16;
                        break;
                    case 3:
                        double d17 = dArr[0];
                        double d18 = dArr[1];
                        double d19 = dArr[2];
                        double d20 = dArr[3];
                        double d21 = dArr[4];
                        double d22 = dArr[5];
                        i = intersectCubic(d8, d9, d17, d18, d19, d20, d21, d22, d, d2, d6, d7);
                        d5 = d21;
                        d9 = d22;
                        break;
                    case 4:
                        i = (d9 == d10 && d8 == d11) ? 0 : intersectLine(d8, d9, d11, d10, d, d2, d6, d7);
                        d9 = d10;
                        d5 = d11;
                        break;
                    default:
                        d5 = d8;
                        i = 0;
                        break;
                }
                i3 = 255;
                if (i == i3) {
                    return i3;
                }
                i2 += i;
                pathIterator.next();
                d8 = d5;
            } else if (d9 == d10) {
                return i2;
            } else {
                int intersectLine = intersectLine(d8, d9, d11, d10, d, d2, d6, d7);
                if (intersectLine == 255) {
                    return 255;
                }
                return i2 + intersectLine;
            }
        }
    }

    public static int intersectShape(Shape shape, double d, double d2, double d3, double d4) {
        if (!shape.getBounds2D().intersects(d, d2, d3, d4)) {
            return 0;
        }
        return intersectPath(shape.getPathIterator((AffineTransform) null), d, d2, d3, d4);
    }
}
