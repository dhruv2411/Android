package com.itextpdf.text.pdf.qrcode;

public final class ErrorCorrectionLevel {
    private static final ErrorCorrectionLevel[] FOR_BITS = {M, L, H, Q};
    public static final ErrorCorrectionLevel H = new ErrorCorrectionLevel(3, 2, "H");
    public static final ErrorCorrectionLevel L = new ErrorCorrectionLevel(0, 1, "L");
    public static final ErrorCorrectionLevel M = new ErrorCorrectionLevel(1, 0, "M");
    public static final ErrorCorrectionLevel Q = new ErrorCorrectionLevel(2, 3, "Q");
    private final int bits;
    private final String name;
    private final int ordinal;

    private ErrorCorrectionLevel(int i, int i2, String str) {
        this.ordinal = i;
        this.bits = i2;
        this.name = str;
    }

    public int ordinal() {
        return this.ordinal;
    }

    public int getBits() {
        return this.bits;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public static ErrorCorrectionLevel forBits(int i) {
        if (i >= 0 && i < FOR_BITS.length) {
            return FOR_BITS[i];
        }
        throw new IllegalArgumentException();
    }
}
