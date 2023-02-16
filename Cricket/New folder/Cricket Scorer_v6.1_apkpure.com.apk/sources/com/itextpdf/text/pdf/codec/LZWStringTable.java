package com.itextpdf.text.pdf.codec;

import java.io.PrintStream;

public class LZWStringTable {
    private static final short HASHSIZE = 9973;
    private static final short HASHSTEP = 2039;
    private static final short HASH_FREE = -1;
    private static final int MAXBITS = 12;
    private static final int MAXSTR = 4096;
    private static final short NEXT_FIRST = -1;
    private static final int RES_CODES = 2;
    short numStrings_;
    byte[] strChr_ = new byte[4096];
    short[] strHsh_ = new short[9973];
    int[] strLen_ = new int[4096];
    short[] strNxt_ = new short[4096];

    public int AddCharString(short s, byte b) {
        if (this.numStrings_ >= 4096) {
            return 65535;
        }
        int Hash = Hash(s, b);
        while (this.strHsh_[Hash] != -1) {
            Hash = (Hash + 2039) % 9973;
        }
        this.strHsh_[Hash] = this.numStrings_;
        this.strChr_[this.numStrings_] = b;
        if (s == -1) {
            this.strNxt_[this.numStrings_] = -1;
            this.strLen_[this.numStrings_] = 1;
        } else {
            this.strNxt_[this.numStrings_] = s;
            this.strLen_[this.numStrings_] = this.strLen_[s] + 1;
        }
        short s2 = this.numStrings_;
        this.numStrings_ = (short) (s2 + 1);
        return s2;
    }

    public short FindCharString(short s, byte b) {
        if (s == -1) {
            return (short) (b & 255);
        }
        int Hash = Hash(s, b);
        while (true) {
            short s2 = this.strHsh_[Hash];
            if (s2 == -1) {
                return -1;
            }
            if (this.strNxt_[s2] == s && this.strChr_[s2] == b) {
                return (short) s2;
            }
            Hash = (Hash + 2039) % 9973;
        }
    }

    public void ClearTable(int i) {
        this.numStrings_ = 0;
        for (int i2 = 0; i2 < 9973; i2++) {
            this.strHsh_[i2] = -1;
        }
        int i3 = (1 << i) + 2;
        for (int i4 = 0; i4 < i3; i4++) {
            AddCharString(-1, (byte) i4);
        }
    }

    public static int Hash(short s, byte b) {
        return ((s ^ ((short) (b << 8))) & 65535) % HASHSIZE;
    }

    public int expandCode(byte[] bArr, int i, short s, int i2) {
        if (i == -2 && i2 == 1) {
            i2 = 0;
        }
        if (s == -1 || i2 == this.strLen_[s]) {
            return 0;
        }
        int i3 = this.strLen_[s] - i2;
        int length = bArr.length - i;
        if (length > i3) {
            length = i3;
        }
        int i4 = i3 - length;
        int i5 = i + length;
        while (i5 > i && s != -1) {
            i4--;
            if (i4 < 0) {
                i5--;
                bArr[i5] = this.strChr_[s];
            }
            s = this.strNxt_[s];
        }
        return i3 > length ? -length : length;
    }

    public void dump(PrintStream printStream) {
        for (int i = 258; i < this.numStrings_; i++) {
            printStream.println(" strNxt_[" + i + "] = " + this.strNxt_[i] + " strChr_ " + Integer.toHexString(this.strChr_[i] & 255) + " strLen_ " + Integer.toHexString(this.strLen_[i]));
        }
    }
}
