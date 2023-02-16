package com.itextpdf.text.pdf.codec;

import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import java.io.EOFException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

public class TIFFDirectory implements Serializable {
    private static final long serialVersionUID = -168636766193675380L;
    private static final int[] sizeOfType = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};
    long IFDOffset = 8;
    Hashtable<Integer, Integer> fieldIndex = new Hashtable<>();
    TIFFField[] fields;
    boolean isBigEndian;
    long nextIFDOffset = 0;
    int numEntries;

    private static boolean isValidEndianTag(int i) {
        return i == 18761 || i == 19789;
    }

    TIFFDirectory() {
    }

    public TIFFDirectory(RandomAccessFileOrArray randomAccessFileOrArray, int i) throws IOException {
        long filePointer = randomAccessFileOrArray.getFilePointer();
        randomAccessFileOrArray.seek(0);
        int readUnsignedShort = randomAccessFileOrArray.readUnsignedShort();
        if (!isValidEndianTag(readUnsignedShort)) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.endianness.tag.not.0x4949.or.0x4d4d", new Object[0]));
        }
        this.isBigEndian = readUnsignedShort == 19789;
        if (readUnsignedShort(randomAccessFileOrArray) != 42) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.magic.number.should.be.42", new Object[0]));
        }
        long readUnsignedInt = readUnsignedInt(randomAccessFileOrArray);
        for (int i2 = 0; i2 < i; i2++) {
            if (readUnsignedInt == 0) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("directory.number.too.large", new Object[0]));
            }
            randomAccessFileOrArray.seek(readUnsignedInt);
            randomAccessFileOrArray.skip((long) (12 * readUnsignedShort(randomAccessFileOrArray)));
            readUnsignedInt = readUnsignedInt(randomAccessFileOrArray);
        }
        randomAccessFileOrArray.seek(readUnsignedInt);
        initialize(randomAccessFileOrArray);
        randomAccessFileOrArray.seek(filePointer);
    }

    public TIFFDirectory(RandomAccessFileOrArray randomAccessFileOrArray, long j, int i) throws IOException {
        long filePointer = randomAccessFileOrArray.getFilePointer();
        randomAccessFileOrArray.seek(0);
        int readUnsignedShort = randomAccessFileOrArray.readUnsignedShort();
        if (!isValidEndianTag(readUnsignedShort)) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.endianness.tag.not.0x4949.or.0x4d4d", new Object[0]));
        }
        this.isBigEndian = readUnsignedShort == 19789;
        randomAccessFileOrArray.seek(j);
        for (int i2 = 0; i2 < i; i2++) {
            randomAccessFileOrArray.seek(j + ((long) (12 * readUnsignedShort(randomAccessFileOrArray))));
            j = readUnsignedInt(randomAccessFileOrArray);
            randomAccessFileOrArray.seek(j);
        }
        initialize(randomAccessFileOrArray);
        randomAccessFileOrArray.seek(filePointer);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [byte[], char[], short[]], vars: [r12v2 ?, r12v3 ?, r12v4 ?, r12v5 ?, r12v6 ?, r12v7 ?, r12v10 ?, r12v11 ?, r12v12 ?, r12v15 ?, r12v16 ?, r12v17 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    private void initialize(com.itextpdf.text.pdf.RandomAccessFileOrArray r20) throws java.io.IOException {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            long r2 = r20.length()
            long r4 = r20.getFilePointer()
            r0.IFDOffset = r4
            int r4 = r19.readUnsignedShort(r20)
            r0.numEntries = r4
            int r4 = r0.numEntries
            com.itextpdf.text.pdf.codec.TIFFField[] r4 = new com.itextpdf.text.pdf.codec.TIFFField[r4]
            r0.fields = r4
            r6 = 0
            r7 = r6
            r8 = 0
        L_0x001e:
            int r10 = r0.numEntries
            if (r7 >= r10) goto L_0x0151
            int r10 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r10 >= 0) goto L_0x0151
            int r8 = r19.readUnsignedShort(r20)
            int r9 = r19.readUnsignedShort(r20)
            long r10 = r19.readUnsignedInt(r20)
            int r10 = (int) r10
            long r11 = r20.getFilePointer()
            r13 = 4
            long r4 = r11 + r13
            r11 = 1
            int[] r12 = sizeOfType     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0051 }
            r12 = r12[r9]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0051 }
            int r12 = r12 * r10
            r13 = 4
            if (r12 <= r13) goto L_0x004f
            long r12 = r19.readUnsignedInt(r20)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0051 }
            int r14 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r14 >= 0) goto L_0x0051
            r1.seek(r12)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0051 }
        L_0x004f:
            r12 = r11
            goto L_0x0052
        L_0x0051:
            r12 = r6
        L_0x0052:
            if (r12 == 0) goto L_0x0148
            java.util.Hashtable<java.lang.Integer, java.lang.Integer> r12 = r0.fieldIndex
            java.lang.Integer r13 = java.lang.Integer.valueOf(r8)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r7)
            r12.put(r13, r14)
            r12 = 0
            r13 = 2
            switch(r9) {
                case 1: goto L_0x0101;
                case 2: goto L_0x0101;
                case 3: goto L_0x00f2;
                case 4: goto L_0x00e4;
                case 5: goto L_0x00c2;
                case 6: goto L_0x0101;
                case 7: goto L_0x0101;
                case 8: goto L_0x00b4;
                case 9: goto L_0x00a6;
                case 10: goto L_0x0084;
                case 11: goto L_0x0076;
                case 12: goto L_0x0068;
                default: goto L_0x0066;
            }
        L_0x0066:
            goto L_0x013f
        L_0x0068:
            double[] r12 = new double[r10]
            r11 = r6
        L_0x006b:
            if (r11 >= r10) goto L_0x013f
            double r13 = r19.readDouble(r20)
            r12[r11] = r13
            int r11 = r11 + 1
            goto L_0x006b
        L_0x0076:
            float[] r12 = new float[r10]
            r11 = r6
        L_0x0079:
            if (r11 >= r10) goto L_0x013f
            float r13 = r19.readFloat(r20)
            r12[r11] = r13
            int r11 = r11 + 1
            goto L_0x0079
        L_0x0084:
            int[] r12 = new int[]{r10, r13}
            java.lang.Class<int> r13 = int.class
            java.lang.Object r12 = java.lang.reflect.Array.newInstance(r13, r12)
            int[][] r12 = (int[][]) r12
            r13 = r6
        L_0x0091:
            if (r13 >= r10) goto L_0x013f
            r14 = r12[r13]
            int r16 = r19.readInt(r20)
            r14[r6] = r16
            r14 = r12[r13]
            int r16 = r19.readInt(r20)
            r14[r11] = r16
            int r13 = r13 + 1
            goto L_0x0091
        L_0x00a6:
            int[] r12 = new int[r10]
            r11 = r6
        L_0x00a9:
            if (r11 >= r10) goto L_0x013f
            int r13 = r19.readInt(r20)
            r12[r11] = r13
            int r11 = r11 + 1
            goto L_0x00a9
        L_0x00b4:
            short[] r12 = new short[r10]
            r11 = r6
        L_0x00b7:
            if (r11 >= r10) goto L_0x013f
            short r13 = r19.readShort(r20)
            r12[r11] = r13
            int r11 = r11 + 1
            goto L_0x00b7
        L_0x00c2:
            int[] r12 = new int[]{r10, r13}
            java.lang.Class<long> r13 = long.class
            java.lang.Object r12 = java.lang.reflect.Array.newInstance(r13, r12)
            long[][] r12 = (long[][]) r12
            r13 = r6
        L_0x00cf:
            if (r13 >= r10) goto L_0x013f
            r14 = r12[r13]
            long r16 = r19.readUnsignedInt(r20)
            r14[r6] = r16
            r14 = r12[r13]
            long r16 = r19.readUnsignedInt(r20)
            r14[r11] = r16
            int r13 = r13 + 1
            goto L_0x00cf
        L_0x00e4:
            long[] r12 = new long[r10]
            r11 = r6
        L_0x00e7:
            if (r11 >= r10) goto L_0x013f
            long r13 = r19.readUnsignedInt(r20)
            r12[r11] = r13
            int r11 = r11 + 1
            goto L_0x00e7
        L_0x00f2:
            char[] r12 = new char[r10]
            r11 = r6
        L_0x00f5:
            if (r11 >= r10) goto L_0x013f
            int r13 = r19.readUnsignedShort(r20)
            char r13 = (char) r13
            r12[r11] = r13
            int r11 = r11 + 1
            goto L_0x00f5
        L_0x0101:
            byte[] r12 = new byte[r10]
            r1.readFully(r12, r6, r10)
            if (r9 != r13) goto L_0x013f
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r13 = r6
            r14 = r13
        L_0x010f:
            if (r13 >= r10) goto L_0x012b
        L_0x0111:
            if (r13 >= r10) goto L_0x011e
            int r16 = r13 + 1
            byte r13 = r12[r13]
            if (r13 == 0) goto L_0x011c
            r13 = r16
            goto L_0x0111
        L_0x011c:
            r13 = r16
        L_0x011e:
            java.lang.String r6 = new java.lang.String
            int r15 = r13 - r14
            r6.<init>(r12, r14, r15)
            r11.add(r6)
            r14 = r13
            r6 = 0
            goto L_0x010f
        L_0x012b:
            int r10 = r11.size()
            java.lang.String[] r12 = new java.lang.String[r10]
            r6 = 0
        L_0x0132:
            if (r6 >= r10) goto L_0x013f
            java.lang.Object r13 = r11.get(r6)
            java.lang.String r13 = (java.lang.String) r13
            r12[r6] = r13
            int r6 = r6 + 1
            goto L_0x0132
        L_0x013f:
            com.itextpdf.text.pdf.codec.TIFFField[] r6 = r0.fields
            com.itextpdf.text.pdf.codec.TIFFField r11 = new com.itextpdf.text.pdf.codec.TIFFField
            r11.<init>(r8, r9, r10, r12)
            r6[r7] = r11
        L_0x0148:
            r1.seek(r4)
            int r7 = r7 + 1
            r8 = r4
            r6 = 0
            goto L_0x001e
        L_0x0151:
            long r1 = r19.readUnsignedInt(r20)     // Catch:{ Exception -> 0x0158 }
            r0.nextIFDOffset = r1     // Catch:{ Exception -> 0x0158 }
            goto L_0x015c
        L_0x0158:
            r1 = 0
            r0.nextIFDOffset = r1
        L_0x015c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.TIFFDirectory.initialize(com.itextpdf.text.pdf.RandomAccessFileOrArray):void");
    }

    public int getNumEntries() {
        return this.numEntries;
    }

    public TIFFField getField(int i) {
        Integer num = this.fieldIndex.get(Integer.valueOf(i));
        if (num == null) {
            return null;
        }
        return this.fields[num.intValue()];
    }

    public boolean isTagPresent(int i) {
        return this.fieldIndex.containsKey(Integer.valueOf(i));
    }

    public int[] getTags() {
        int[] iArr = new int[this.fieldIndex.size()];
        Enumeration<Integer> keys = this.fieldIndex.keys();
        int i = 0;
        while (keys.hasMoreElements()) {
            iArr[i] = keys.nextElement().intValue();
            i++;
        }
        return iArr;
    }

    public TIFFField[] getFields() {
        return this.fields;
    }

    public byte getFieldAsByte(int i, int i2) {
        return this.fields[this.fieldIndex.get(Integer.valueOf(i)).intValue()].getAsBytes()[i2];
    }

    public byte getFieldAsByte(int i) {
        return getFieldAsByte(i, 0);
    }

    public long getFieldAsLong(int i, int i2) {
        return this.fields[this.fieldIndex.get(Integer.valueOf(i)).intValue()].getAsLong(i2);
    }

    public long getFieldAsLong(int i) {
        return getFieldAsLong(i, 0);
    }

    public float getFieldAsFloat(int i, int i2) {
        return this.fields[this.fieldIndex.get(Integer.valueOf(i)).intValue()].getAsFloat(i2);
    }

    public float getFieldAsFloat(int i) {
        return getFieldAsFloat(i, 0);
    }

    public double getFieldAsDouble(int i, int i2) {
        return this.fields[this.fieldIndex.get(Integer.valueOf(i)).intValue()].getAsDouble(i2);
    }

    public double getFieldAsDouble(int i) {
        return getFieldAsDouble(i, 0);
    }

    private short readShort(RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        if (this.isBigEndian) {
            return randomAccessFileOrArray.readShort();
        }
        return randomAccessFileOrArray.readShortLE();
    }

    private int readUnsignedShort(RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        if (this.isBigEndian) {
            return randomAccessFileOrArray.readUnsignedShort();
        }
        return randomAccessFileOrArray.readUnsignedShortLE();
    }

    private int readInt(RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        if (this.isBigEndian) {
            return randomAccessFileOrArray.readInt();
        }
        return randomAccessFileOrArray.readIntLE();
    }

    private long readUnsignedInt(RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        if (this.isBigEndian) {
            return randomAccessFileOrArray.readUnsignedInt();
        }
        return randomAccessFileOrArray.readUnsignedIntLE();
    }

    private long readLong(RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        if (this.isBigEndian) {
            return randomAccessFileOrArray.readLong();
        }
        return randomAccessFileOrArray.readLongLE();
    }

    private float readFloat(RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        if (this.isBigEndian) {
            return randomAccessFileOrArray.readFloat();
        }
        return randomAccessFileOrArray.readFloatLE();
    }

    private double readDouble(RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        if (this.isBigEndian) {
            return randomAccessFileOrArray.readDouble();
        }
        return randomAccessFileOrArray.readDoubleLE();
    }

    private static int readUnsignedShort(RandomAccessFileOrArray randomAccessFileOrArray, boolean z) throws IOException {
        if (z) {
            return randomAccessFileOrArray.readUnsignedShort();
        }
        return randomAccessFileOrArray.readUnsignedShortLE();
    }

    private static long readUnsignedInt(RandomAccessFileOrArray randomAccessFileOrArray, boolean z) throws IOException {
        if (z) {
            return randomAccessFileOrArray.readUnsignedInt();
        }
        return randomAccessFileOrArray.readUnsignedIntLE();
    }

    public static int getNumDirectories(RandomAccessFileOrArray randomAccessFileOrArray) throws IOException {
        long filePointer = randomAccessFileOrArray.getFilePointer();
        randomAccessFileOrArray.seek(0);
        int readUnsignedShort = randomAccessFileOrArray.readUnsignedShort();
        int i = 0;
        if (!isValidEndianTag(readUnsignedShort)) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.endianness.tag.not.0x4949.or.0x4d4d", new Object[0]));
        }
        boolean z = readUnsignedShort == 19789;
        if (readUnsignedShort(randomAccessFileOrArray, z) != 42) {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.magic.number.should.be.42", new Object[0]));
        }
        randomAccessFileOrArray.seek(4);
        long readUnsignedInt = readUnsignedInt(randomAccessFileOrArray, z);
        while (readUnsignedInt != 0) {
            i++;
            try {
                randomAccessFileOrArray.seek(readUnsignedInt);
                randomAccessFileOrArray.skip((long) (12 * readUnsignedShort(randomAccessFileOrArray, z)));
                readUnsignedInt = readUnsignedInt(randomAccessFileOrArray, z);
            } catch (EOFException unused) {
                i--;
            }
        }
        randomAccessFileOrArray.seek(filePointer);
        return i;
    }

    public boolean isBigEndian() {
        return this.isBigEndian;
    }

    public long getIFDOffset() {
        return this.IFDOffset;
    }

    public long getNextIFDOffset() {
        return this.nextIFDOffset;
    }
}
