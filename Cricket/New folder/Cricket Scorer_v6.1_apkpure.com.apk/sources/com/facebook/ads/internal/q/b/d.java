package com.facebook.ads.internal.q.b;

import android.graphics.Bitmap;
import android.support.v4.view.ViewCompat;
import com.itextpdf.text.pdf.BidiOrder;
import java.util.ArrayList;
import java.util.concurrent.Callable;

class d implements a {
    private static final short[] a = {512, 512, 456, 512, 328, 456, 335, 512, 405, 328, 271, 456, 388, 335, 292, 512, 454, 405, 364, 328, 298, 271, 496, 456, 420, 388, 360, 335, 312, 292, 273, 512, 482, 454, 428, 405, 383, 364, 345, 328, 312, 298, 284, 271, 259, 496, 475, 456, 437, 420, 404, 388, 374, 360, 347, 335, 323, 312, 302, 292, 282, 273, 265, 512, 497, 482, 468, 454, 441, 428, 417, 405, 394, 383, 373, 364, 354, 345, 337, 328, 320, 312, 305, 298, 291, 284, 278, 271, 265, 259, 507, 496, 485, 475, 465, 456, 446, 437, 428, 420, 412, 404, 396, 388, 381, 374, 367, 360, 354, 347, 341, 335, 329, 323, 318, 312, 307, 302, 297, 292, 287, 282, 278, 273, 269, 265, 261, 512, 505, 497, 489, 482, 475, 468, 461, 454, 447, 441, 435, 428, 422, 417, 411, 405, 399, 394, 389, 383, 378, 373, 368, 364, 359, 354, 350, 345, 341, 337, 332, 328, 324, 320, 316, 312, 309, 305, 301, 298, 294, 291, 287, 284, 281, 278, 274, 271, 268, 265, 262, 259, 257, 507, 501, 496, 491, 485, 480, 475, 470, 465, 460, 456, 451, 446, 442, 437, 433, 428, 424, 420, 416, 412, 408, 404, 400, 396, 392, 388, 385, 381, 377, 374, 370, 367, 363, 360, 357, 354, 350, 347, 344, 341, 338, 335, 332, 329, 326, 323, 320, 318, 315, 312, 310, 307, 304, 302, 299, 297, 294, 292, 289, 287, 285, 282, 280, 278, 275, 273, 271, 269, 267, 265, 263, 261, 259};
    private static final byte[] b = {9, BidiOrder.AN, BidiOrder.CS, BidiOrder.NSM, BidiOrder.NSM, BidiOrder.BN, BidiOrder.BN, BidiOrder.B, BidiOrder.B, BidiOrder.B, BidiOrder.B, BidiOrder.S, BidiOrder.S, BidiOrder.S, BidiOrder.S, BidiOrder.WS, BidiOrder.WS, BidiOrder.WS, BidiOrder.WS, BidiOrder.WS, BidiOrder.WS, BidiOrder.WS, 18, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24};

    private static class a implements Callable<Void> {
        private final int[] a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;
        private final int f;
        private final int g;

        public a(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = iArr;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = i6;
        }

        /* renamed from: a */
        public Void call() {
            d.b(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
            return null;
        }
    }

    d() {
    }

    /* access modifiers changed from: private */
    public static void b(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        int i9 = i3;
        int i10 = i6;
        int i11 = i7 - 1;
        int i12 = i8 - 1;
        int i13 = (i9 * 2) + 1;
        short s = a[i9];
        byte b2 = b[i9];
        int[] iArr2 = new int[i13];
        if (i10 == 1) {
            int i14 = (i5 * i8) / i4;
            int i15 = ((i5 + 1) * i8) / i4;
            while (i14 < i15) {
                int i16 = i7 * i14;
                int i17 = 0;
                long j = 0;
                long j2 = 0;
                long j3 = 0;
                long j4 = 0;
                long j5 = 0;
                long j6 = 0;
                while (i17 <= i9) {
                    iArr2[i17] = iArr[i16];
                    i17++;
                    long j7 = j + ((long) (((iArr[i16] >>> 16) & 255) * i17));
                    long j8 = j2 + ((long) (((iArr[i16] >>> 8) & 255) * i17));
                    long j9 = j3 + ((long) ((iArr[i16] & 255) * i17));
                    long j10 = j4 + ((long) ((iArr[i16] >>> 16) & 255));
                    long j11 = j5 + ((long) ((iArr[i16] >>> 8) & 255));
                    j6 += (long) (iArr[i16] & 255);
                    s = s;
                    j5 = j11;
                    j4 = j10;
                    j3 = j9;
                    j2 = j8;
                    j = j7;
                }
                short s2 = s;
                int i18 = i16;
                int i19 = 1;
                long j12 = 0;
                long j13 = 0;
                long j14 = 0;
                while (i19 <= i9) {
                    if (i19 <= i11) {
                        i18++;
                    }
                    iArr2[i19 + i9] = iArr[i18];
                    int i20 = (i9 + 1) - i19;
                    long j15 = j + ((long) (((iArr[i18] >>> 16) & 255) * i20));
                    long j16 = j2 + ((long) (((iArr[i18] >>> 8) & 255) * i20));
                    long j17 = j3 + ((long) ((iArr[i18] & 255) * i20));
                    long j18 = j12 + ((long) ((iArr[i18] >>> 16) & 255));
                    long j19 = j13 + ((long) ((iArr[i18] >>> 8) & 255));
                    i19++;
                    j12 = j18;
                    j14 += (long) (iArr[i18] & 255);
                    j13 = j19;
                    j3 = j17;
                    j2 = j16;
                    j = j15;
                }
                int i21 = i9 > i11 ? i11 : i9;
                long j20 = j12;
                int i22 = i21;
                int i23 = i21 + i16;
                int i24 = i16;
                int i25 = i9;
                int i26 = 0;
                while (i26 < i7) {
                    int i27 = i15;
                    int i28 = i14;
                    long j21 = j20;
                    short s3 = s2;
                    long j22 = (long) s3;
                    iArr[i24] = (int) (((long) (iArr[i24] & ViewCompat.MEASURED_STATE_MASK)) | ((((j * j22) >>> b2) & 255) << 16) | ((((j2 * j22) >>> b2) & 255) << 8) | (((j22 * j3) >>> b2) & 255));
                    int i29 = i24 + 1;
                    long j23 = j - j4;
                    long j24 = j2 - j5;
                    long j25 = j3 - j6;
                    short s4 = s3;
                    int i30 = (i25 + i13) - i9;
                    if (i30 >= i13) {
                        i30 -= i13;
                    }
                    int i31 = i29;
                    long j26 = j4 - ((long) ((iArr2[i30] >>> 16) & 255));
                    long j27 = j5 - ((long) ((iArr2[i30] >>> 8) & 255));
                    long j28 = j6 - ((long) (iArr2[i30] & 255));
                    if (i22 < i11) {
                        i23++;
                        i22++;
                    }
                    iArr2[i30] = iArr[i23];
                    long j29 = j21 + ((long) ((iArr[i23] >>> 16) & 255));
                    long j30 = j13 + ((long) ((iArr[i23] >>> 8) & 255));
                    long j31 = j14 + ((long) (iArr[i23] & 255));
                    long j32 = j23 + j29;
                    long j33 = j24 + j30;
                    long j34 = j25 + j31;
                    i25++;
                    if (i25 >= i13) {
                        i25 = 0;
                    }
                    long j35 = j32;
                    long j36 = j26 + ((long) ((iArr2[i25] >>> 16) & 255));
                    long j37 = j27 + ((long) ((iArr2[i25] >>> 8) & 255));
                    long j38 = j28 + ((long) (iArr2[i25] & 255));
                    long j39 = j29 - ((long) ((iArr2[i25] >>> 16) & 255));
                    long j40 = j30 - ((long) ((iArr2[i25] >>> 8) & 255));
                    j14 = j31 - ((long) (iArr2[i25] & 255));
                    i26++;
                    j6 = j38;
                    j13 = j40;
                    s2 = s4;
                    i24 = i31;
                    i7 = i;
                    i9 = i3;
                    j3 = j34;
                    j5 = j37;
                    j20 = j39;
                    j2 = j33;
                    j4 = j36;
                    i15 = i27;
                    i14 = i28;
                    j = j35;
                }
                int i32 = i15;
                i14++;
                s = s2;
                i7 = i;
                i9 = i3;
            }
            return;
        }
        short s5 = s;
        if (i10 == 2) {
            short s6 = s5;
            int i33 = i;
            int i34 = (i5 * i33) / i4;
            int i35 = ((i5 + 1) * i33) / i4;
            while (i34 < i35) {
                int i36 = 0;
                int i37 = i3;
                long j41 = 0;
                long j42 = 0;
                long j43 = 0;
                long j44 = 0;
                long j45 = 0;
                long j46 = 0;
                while (i36 <= i37) {
                    iArr2[i36] = iArr[i34];
                    i36++;
                    long j47 = j41 + ((long) (((iArr[i34] >>> 16) & 255) * i36));
                    long j48 = j42 + ((long) (((iArr[i34] >>> 8) & 255) * i36));
                    long j49 = j43 + ((long) ((iArr[i34] & 255) * i36));
                    long j50 = j44 + ((long) ((iArr[i34] >>> 16) & 255));
                    long j51 = j45 + ((long) ((iArr[i34] >>> 8) & 255));
                    j46 += (long) (iArr[i34] & 255);
                    i12 = i12;
                    j45 = j51;
                    j44 = j50;
                    j43 = j49;
                    j42 = j48;
                    j41 = j47;
                }
                int i38 = i12;
                int i39 = i34;
                int i40 = 1;
                long j52 = 0;
                long j53 = 0;
                long j54 = 0;
                while (i40 <= i37) {
                    int i41 = i35;
                    int i42 = i38;
                    if (i40 <= i42) {
                        i39 += i33;
                    }
                    iArr2[i40 + i37] = iArr[i39];
                    int i43 = (i37 + 1) - i40;
                    byte b3 = b2;
                    long j55 = j41 + ((long) (((iArr[i39] >>> 16) & 255) * i43));
                    long j56 = j42 + ((long) (((iArr[i39] >>> 8) & 255) * i43));
                    long j57 = j43 + ((long) ((iArr[i39] & 255) * i43));
                    long j58 = j52 + ((long) ((iArr[i39] >>> 16) & 255));
                    long j59 = j53 + ((long) ((iArr[i39] >>> 8) & 255));
                    i40++;
                    i38 = i42;
                    j52 = j58;
                    j54 += (long) (iArr[i39] & 255);
                    i35 = i41;
                    j53 = j59;
                    j43 = j57;
                    iArr2 = iArr2;
                    b2 = b3;
                    j42 = j56;
                    j41 = j55;
                }
                int i44 = i35;
                byte b4 = b2;
                int[] iArr3 = iArr2;
                int i45 = i38;
                int i46 = i37 > i45 ? i45 : i37;
                int i47 = (i46 * i33) + i34;
                long j60 = j54;
                int i48 = i34;
                long j61 = j53;
                long j62 = j52;
                int i49 = i37;
                int i50 = i46;
                int i51 = 0;
                while (i51 < i8) {
                    int i52 = i34;
                    int i53 = i51;
                    int i54 = i47;
                    long j63 = (long) s6;
                    iArr[i48] = (int) (((long) (iArr[i48] & ViewCompat.MEASURED_STATE_MASK)) | ((((j41 * j63) >>> b4) & 255) << 16) | ((((j42 * j63) >>> b4) & 255) << 8) | (((j63 * j43) >>> b4) & 255));
                    i48 += i33;
                    long j64 = j41 - j44;
                    long j65 = j42 - j45;
                    long j66 = j43 - j46;
                    int i55 = (i49 + i13) - i37;
                    if (i55 >= i13) {
                        i55 -= i13;
                    }
                    short s7 = s6;
                    long j67 = j44 - ((long) ((iArr3[i55] >>> 16) & 255));
                    long j68 = j45 - ((long) ((iArr3[i55] >>> 8) & 255));
                    long j69 = j46 - ((long) (iArr3[i55] & 255));
                    if (i50 < i45) {
                        i50++;
                        i54 += i33;
                    }
                    iArr3[i55] = iArr[i54];
                    long j70 = j62 + ((long) ((iArr[i54] >>> 16) & 255));
                    long j71 = j61 + ((long) ((iArr[i54] >>> 8) & 255));
                    long j72 = j60 + ((long) (iArr[i54] & 255));
                    long j73 = j64 + j70;
                    long j74 = j65 + j71;
                    long j75 = j66 + j72;
                    i49++;
                    if (i49 >= i13) {
                        i49 = 0;
                    }
                    long j76 = j67 + ((long) ((iArr3[i49] >>> 16) & 255));
                    long j77 = j68 + ((long) ((iArr3[i49] >>> 8) & 255));
                    long j78 = j69 + ((long) (iArr3[i49] & 255));
                    long j79 = j70 - ((long) ((iArr3[i49] >>> 16) & 255));
                    long j80 = j71 - ((long) ((iArr3[i49] >>> 8) & 255));
                    j60 = j72 - ((long) (iArr3[i49] & 255));
                    j41 = j73;
                    j46 = j78;
                    j62 = j79;
                    j44 = j76;
                    s6 = s7;
                    i8 = i2;
                    j43 = j75;
                    j61 = j80;
                    i47 = i54;
                    j45 = j77;
                    j42 = j74;
                    i34 = i52;
                    i51 = i53 + 1;
                    i37 = i3;
                }
                short s8 = s6;
                i34++;
                i12 = i45;
                i35 = i44;
                iArr2 = iArr3;
                b2 = b4;
                i8 = i2;
            }
        }
    }

    public Bitmap a(Bitmap bitmap, float f) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        try {
            int[] iArr = new int[(width * height)];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            int i = e.a;
            ArrayList arrayList = new ArrayList(i);
            ArrayList arrayList2 = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = (int) f;
                int[] iArr2 = iArr;
                int i4 = width;
                int i5 = height;
                int i6 = i3;
                int i7 = i3;
                int i8 = i;
                int i9 = i2;
                a aVar = r0;
                a aVar2 = new a(iArr2, i4, i5, i6, i8, i9, 1);
                arrayList.add(aVar);
                arrayList2.add(new a(iArr2, i4, i5, i7, i8, i9, 2));
            }
            try {
                e.b.invokeAll(arrayList);
                try {
                    e.b.invokeAll(arrayList2);
                    try {
                        return Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
                    } catch (OutOfMemoryError unused) {
                        return null;
                    }
                } catch (InterruptedException unused2) {
                    return null;
                }
            } catch (InterruptedException unused3) {
                return null;
            }
        } catch (OutOfMemoryError unused4) {
            return null;
        }
    }
}
