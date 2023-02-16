package com.itextpdf.text.pdf.codec;

import java.io.Serializable;

public class TIFFField implements Comparable<TIFFField>, Serializable {
    public static final int TIFF_ASCII = 2;
    public static final int TIFF_BYTE = 1;
    public static final int TIFF_DOUBLE = 12;
    public static final int TIFF_FLOAT = 11;
    public static final int TIFF_LONG = 4;
    public static final int TIFF_RATIONAL = 5;
    public static final int TIFF_SBYTE = 6;
    public static final int TIFF_SHORT = 3;
    public static final int TIFF_SLONG = 9;
    public static final int TIFF_SRATIONAL = 10;
    public static final int TIFF_SSHORT = 8;
    public static final int TIFF_UNDEFINED = 7;
    private static final long serialVersionUID = 9088332901412823834L;
    int count;
    Object data;
    int tag;
    int type;

    TIFFField() {
    }

    public TIFFField(int i, int i2, int i3, Object obj) {
        this.tag = i;
        this.type = i2;
        this.count = i3;
        this.data = obj;
    }

    public int getTag() {
        return this.tag;
    }

    public int getType() {
        return this.type;
    }

    public int getCount() {
        return this.count;
    }

    public byte[] getAsBytes() {
        return (byte[]) this.data;
    }

    public char[] getAsChars() {
        return (char[]) this.data;
    }

    public short[] getAsShorts() {
        return (short[]) this.data;
    }

    public int[] getAsInts() {
        return (int[]) this.data;
    }

    public long[] getAsLongs() {
        return (long[]) this.data;
    }

    public float[] getAsFloats() {
        return (float[]) this.data;
    }

    public double[] getAsDoubles() {
        return (double[]) this.data;
    }

    public int[][] getAsSRationals() {
        return (int[][]) this.data;
    }

    public long[][] getAsRationals() {
        return (long[][]) this.data;
    }

    public int getAsInt(int i) {
        int i2 = this.type;
        if (i2 != 1) {
            if (i2 == 3) {
                return ((char[]) this.data)[i] & 65535;
            }
            switch (i2) {
                case 6:
                    return ((byte[]) this.data)[i];
                case 7:
                    break;
                case 8:
                    return ((short[]) this.data)[i];
                case 9:
                    return ((int[]) this.data)[i];
                default:
                    throw new ClassCastException();
            }
        }
        return ((byte[]) this.data)[i] & 255;
    }

    public long getAsLong(int i) {
        switch (this.type) {
            case 1:
            case 7:
                return (long) (((byte[]) this.data)[i] & 255);
            case 3:
                return (long) (((char[]) this.data)[i] & 65535);
            case 4:
                return ((long[]) this.data)[i];
            case 6:
                return (long) ((byte[]) this.data)[i];
            case 8:
                return (long) ((short[]) this.data)[i];
            case 9:
                return (long) ((int[]) this.data)[i];
            default:
                throw new ClassCastException();
        }
    }

    public float getAsFloat(int i) {
        switch (this.type) {
            case 1:
                return (float) (((byte[]) this.data)[i] & 255);
            case 3:
                return (float) (((char[]) this.data)[i] & 65535);
            case 4:
                return (float) ((long[]) this.data)[i];
            case 5:
                long[] asRational = getAsRational(i);
                return (float) (((double) asRational[0]) / ((double) asRational[1]));
            case 6:
                return (float) ((byte[]) this.data)[i];
            case 8:
                return (float) ((short[]) this.data)[i];
            case 9:
                return (float) ((int[]) this.data)[i];
            case 10:
                int[] asSRational = getAsSRational(i);
                return (float) (((double) asSRational[0]) / ((double) asSRational[1]));
            case 11:
                return ((float[]) this.data)[i];
            case 12:
                return (float) ((double[]) this.data)[i];
            default:
                throw new ClassCastException();
        }
    }

    public double getAsDouble(int i) {
        switch (this.type) {
            case 1:
                return (double) (((byte[]) this.data)[i] & 255);
            case 3:
                return (double) (((char[]) this.data)[i] & 65535);
            case 4:
                return (double) ((long[]) this.data)[i];
            case 5:
                long[] asRational = getAsRational(i);
                return ((double) asRational[0]) / ((double) asRational[1]);
            case 6:
                return (double) ((byte[]) this.data)[i];
            case 8:
                return (double) ((short[]) this.data)[i];
            case 9:
                return (double) ((int[]) this.data)[i];
            case 10:
                int[] asSRational = getAsSRational(i);
                return ((double) asSRational[0]) / ((double) asSRational[1]);
            case 11:
                return (double) ((float[]) this.data)[i];
            case 12:
                return ((double[]) this.data)[i];
            default:
                throw new ClassCastException();
        }
    }

    public String getAsString(int i) {
        return ((String[]) this.data)[i];
    }

    public int[] getAsSRational(int i) {
        return ((int[][]) this.data)[i];
    }

    public long[] getAsRational(int i) {
        if (this.type == 4) {
            return getAsLongs();
        }
        return ((long[][]) this.data)[i];
    }

    public int compareTo(TIFFField tIFFField) {
        if (tIFFField == null) {
            throw new IllegalArgumentException();
        }
        int tag2 = tIFFField.getTag();
        if (this.tag < tag2) {
            return -1;
        }
        return this.tag > tag2 ? 1 : 0;
    }
}
