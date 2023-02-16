package com.itextpdf.text.pdf;

import com.itextpdf.text.DocWriter;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.io.RandomAccessSourceFactory;
import java.io.IOException;

public class PRTokeniser {
    static final String EMPTY = "";
    public static final boolean[] delims = {true, true, false, false, false, false, false, false, false, false, true, true, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false, false, true, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private final RandomAccessFileOrArray file;
    protected int generation;
    protected boolean hexString;
    private final StringBuilder outBuf = new StringBuilder();
    protected int reference;
    protected String stringValue;
    protected TokenType type;

    public enum TokenType {
        NUMBER,
        STRING,
        NAME,
        COMMENT,
        START_ARRAY,
        END_ARRAY,
        START_DIC,
        END_DIC,
        REF,
        OTHER,
        ENDOFFILE
    }

    public static int getHex(int i) {
        if (i >= 48 && i <= 57) {
            return i - 48;
        }
        if (i >= 65 && i <= 70) {
            return (i - 65) + 10;
        }
        if (i < 97 || i > 102) {
            return -1;
        }
        return (i - 97) + 10;
    }

    public static final boolean isDelimiter(int i) {
        return i == 40 || i == 41 || i == 60 || i == 62 || i == 91 || i == 93 || i == 47 || i == 37;
    }

    public static final boolean isWhitespace(int i, boolean z) {
        return (z && i == 0) || i == 9 || i == 10 || i == 12 || i == 13 || i == 32;
    }

    public PRTokeniser(RandomAccessFileOrArray randomAccessFileOrArray) {
        this.file = randomAccessFileOrArray;
    }

    public void seek(long j) throws IOException {
        this.file.seek(j);
    }

    public long getFilePointer() throws IOException {
        return this.file.getFilePointer();
    }

    public void close() throws IOException {
        this.file.close();
    }

    public long length() throws IOException {
        return this.file.length();
    }

    public int read() throws IOException {
        return this.file.read();
    }

    public RandomAccessFileOrArray getSafeFile() {
        return new RandomAccessFileOrArray(this.file);
    }

    public RandomAccessFileOrArray getFile() {
        return this.file;
    }

    public String readString(int i) throws IOException {
        int read;
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i2 = i - 1;
            if (i > 0 && (read = read()) != -1) {
                sb.append((char) read);
                i = i2;
            }
        }
        return sb.toString();
    }

    public static final boolean isWhitespace(int i) {
        return isWhitespace(i, true);
    }

    public static final boolean isDelimiterWhitespace(int i) {
        return delims[i + 1];
    }

    public TokenType getTokenType() {
        return this.type;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public int getReference() {
        return this.reference;
    }

    public int getGeneration() {
        return this.generation;
    }

    public void backOnePosition(int i) {
        if (i != -1) {
            this.file.pushBack((byte) i);
        }
    }

    public void throwError(String str) throws IOException {
        throw new InvalidPdfException(MessageLocalization.getComposedMessage("1.at.file.pointer.2", str, String.valueOf(this.file.getFilePointer())));
    }

    public int getHeaderOffset() throws IOException {
        String readString = readString(1024);
        int indexOf = readString.indexOf("%PDF-");
        if (indexOf >= 0 || (indexOf = readString.indexOf("%FDF-")) >= 0) {
            return indexOf;
        }
        throw new InvalidPdfException(MessageLocalization.getComposedMessage("pdf.header.not.found", new Object[0]));
    }

    public char checkPdfHeader() throws IOException {
        this.file.seek(0);
        String readString = readString(1024);
        if (readString.indexOf("%PDF-") == 0) {
            return readString.charAt(7);
        }
        throw new InvalidPdfException(MessageLocalization.getComposedMessage("pdf.header.not.found", new Object[0]));
    }

    public void checkFdfHeader() throws IOException {
        this.file.seek(0);
        if (readString(1024).indexOf("%FDF-") != 0) {
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("fdf.header.not.found", new Object[0]));
        }
    }

    public long getStartxref() throws IOException {
        long j = (long) 1024;
        long length = this.file.length() - j;
        long j2 = 1;
        if (length >= 1) {
            j2 = length;
        }
        while (j2 > 0) {
            this.file.seek(j2);
            int lastIndexOf = readString(1024).lastIndexOf("startxref");
            if (lastIndexOf >= 0) {
                return j2 + ((long) lastIndexOf);
            }
            j2 = (j2 - j) + 9;
        }
        throw new InvalidPdfException(MessageLocalization.getComposedMessage("pdf.startxref.not.found", new Object[0]));
    }

    public void nextValidToken() throws IOException {
        String str = null;
        int i = 0;
        long j = 0;
        String str2 = null;
        while (nextToken()) {
            if (this.type != TokenType.COMMENT) {
                switch (i) {
                    case 0:
                        if (this.type == TokenType.NUMBER) {
                            j = this.file.getFilePointer();
                            str = this.stringValue;
                            i++;
                            break;
                        } else {
                            return;
                        }
                    case 1:
                        if (this.type == TokenType.NUMBER) {
                            str2 = this.stringValue;
                            i++;
                            break;
                        } else {
                            this.file.seek(j);
                            this.type = TokenType.NUMBER;
                            this.stringValue = str;
                            return;
                        }
                    default:
                        if (this.type != TokenType.OTHER || !this.stringValue.equals("R")) {
                            this.file.seek(j);
                            this.type = TokenType.NUMBER;
                            this.stringValue = str;
                            return;
                        }
                        this.type = TokenType.REF;
                        this.reference = Integer.parseInt(str);
                        this.generation = Integer.parseInt(str2);
                        return;
                }
            }
        }
        if (i == 1) {
            this.type = TokenType.NUMBER;
        }
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public boolean nextToken() throws java.io.IOException {
        /*
            r11 = this;
        L_0x0000:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            r1 = -1
            if (r0 == r1) goto L_0x000f
            boolean r2 = isWhitespace(r0)
            if (r2 != 0) goto L_0x0000
        L_0x000f:
            r2 = 0
            if (r0 != r1) goto L_0x0017
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = com.itextpdf.text.pdf.PRTokeniser.TokenType.ENDOFFILE
            r11.type = r0
            return r2
        L_0x0017:
            java.lang.StringBuilder r3 = r11.outBuf
            r3.setLength(r2)
            java.lang.String r3 = ""
            r11.stringValue = r3
            r3 = 37
            r4 = 13
            r5 = 10
            r6 = 1
            if (r0 == r3) goto L_0x026f
            r3 = 40
            r7 = 48
            if (r0 == r3) goto L_0x01a1
            r3 = 47
            if (r0 == r3) goto L_0x0163
            r3 = 60
            r4 = 62
            if (r0 == r3) goto L_0x00ed
            if (r0 == r4) goto L_0x00d4
            r3 = 91
            if (r0 == r3) goto L_0x00ce
            r3 = 93
            if (r0 == r3) goto L_0x00c8
            java.lang.StringBuilder r3 = r11.outBuf
            r3.setLength(r2)
            r3 = 57
            r4 = 46
            r5 = 45
            if (r0 == r5) goto L_0x0074
            r8 = 43
            if (r0 == r8) goto L_0x0074
            if (r0 == r4) goto L_0x0074
            if (r0 < r7) goto L_0x005b
            if (r0 > r3) goto L_0x005b
            goto L_0x0074
        L_0x005b:
            com.itextpdf.text.pdf.PRTokeniser$TokenType r2 = com.itextpdf.text.pdf.PRTokeniser.TokenType.OTHER
            r11.type = r2
        L_0x005f:
            java.lang.StringBuilder r2 = r11.outBuf
            char r0 = (char) r0
            r2.append(r0)
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            boolean[] r2 = delims
            int r3 = r0 + 1
            boolean r2 = r2[r3]
            if (r2 == 0) goto L_0x005f
            goto L_0x00c1
        L_0x0074:
            com.itextpdf.text.pdf.PRTokeniser$TokenType r8 = com.itextpdf.text.pdf.PRTokeniser.TokenType.NUMBER
            r11.type = r8
            if (r0 != r5) goto L_0x008d
            r0 = r2
        L_0x007b:
            int r0 = r0 + r6
            com.itextpdf.text.pdf.RandomAccessFileOrArray r8 = r11.file
            int r8 = r8.read()
            if (r8 == r5) goto L_0x007b
            java.lang.StringBuilder r9 = r11.outBuf
            r9.append(r5)
            r5 = r0
            r0 = r8
            r8 = r2
            goto L_0x009b
        L_0x008d:
            java.lang.StringBuilder r5 = r11.outBuf
            char r0 = (char) r0
            r5.append(r0)
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            r5 = r2
            r8 = r5
        L_0x009b:
            if (r0 == r1) goto L_0x00b3
            if (r0 < r7) goto L_0x00a1
            if (r0 <= r3) goto L_0x00a3
        L_0x00a1:
            if (r0 != r4) goto L_0x00b3
        L_0x00a3:
            if (r0 != r4) goto L_0x00a6
            r8 = r6
        L_0x00a6:
            java.lang.StringBuilder r9 = r11.outBuf
            char r0 = (char) r0
            r9.append(r0)
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            goto L_0x009b
        L_0x00b3:
            if (r5 <= r6) goto L_0x00c1
            if (r8 != 0) goto L_0x00c1
            java.lang.StringBuilder r3 = r11.outBuf
            r3.setLength(r2)
            java.lang.StringBuilder r2 = r11.outBuf
            r2.append(r7)
        L_0x00c1:
            if (r0 == r1) goto L_0x027f
            r11.backOnePosition(r0)
            goto L_0x027f
        L_0x00c8:
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = com.itextpdf.text.pdf.PRTokeniser.TokenType.END_ARRAY
            r11.type = r0
            goto L_0x027f
        L_0x00ce:
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = com.itextpdf.text.pdf.PRTokeniser.TokenType.START_ARRAY
            r11.type = r0
            goto L_0x027f
        L_0x00d4:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            if (r0 == r4) goto L_0x00e7
            java.lang.String r0 = "greaterthan.not.expected"
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r1)
            r11.throwError(r0)
        L_0x00e7:
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = com.itextpdf.text.pdf.PRTokeniser.TokenType.END_DIC
            r11.type = r0
            goto L_0x027f
        L_0x00ed:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            if (r0 != r3) goto L_0x00fb
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = com.itextpdf.text.pdf.PRTokeniser.TokenType.START_DIC
            r11.type = r0
            goto L_0x027f
        L_0x00fb:
            java.lang.StringBuilder r1 = r11.outBuf
            r1.setLength(r2)
            com.itextpdf.text.pdf.PRTokeniser$TokenType r1 = com.itextpdf.text.pdf.PRTokeniser.TokenType.STRING
            r11.type = r1
            r11.hexString = r6
            r1 = r2
        L_0x0107:
            boolean r3 = isWhitespace(r0)
            if (r3 == 0) goto L_0x0114
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            goto L_0x0107
        L_0x0114:
            if (r0 != r4) goto L_0x0117
            goto L_0x0142
        L_0x0117:
            int r0 = getHex(r0)
            if (r0 >= 0) goto L_0x011e
            goto L_0x0142
        L_0x011e:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r1 = r11.file
            int r1 = r1.read()
        L_0x0124:
            boolean r3 = isWhitespace(r1)
            if (r3 == 0) goto L_0x0131
            com.itextpdf.text.pdf.RandomAccessFileOrArray r1 = r11.file
            int r1 = r1.read()
            goto L_0x0124
        L_0x0131:
            if (r1 != r4) goto L_0x013c
            int r3 = r0 << 4
            java.lang.StringBuilder r4 = r11.outBuf
            char r3 = (char) r3
            r4.append(r3)
            goto L_0x0142
        L_0x013c:
            int r1 = getHex(r1)
            if (r1 >= 0) goto L_0x0153
        L_0x0142:
            if (r0 < 0) goto L_0x0146
            if (r1 >= 0) goto L_0x027f
        L_0x0146:
            java.lang.String r0 = "error.reading.string"
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r1)
            r11.throwError(r0)
            goto L_0x027f
        L_0x0153:
            int r0 = r0 << 4
            int r0 = r0 + r1
            java.lang.StringBuilder r3 = r11.outBuf
            char r0 = (char) r0
            r3.append(r0)
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            goto L_0x0107
        L_0x0163:
            java.lang.StringBuilder r0 = r11.outBuf
            r0.setLength(r2)
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = com.itextpdf.text.pdf.PRTokeniser.TokenType.NAME
            r11.type = r0
        L_0x016c:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            boolean[] r1 = delims
            int r2 = r0 + 1
            boolean r1 = r1[r2]
            if (r1 == 0) goto L_0x017f
            r11.backOnePosition(r0)
            goto L_0x027f
        L_0x017f:
            r1 = 35
            if (r0 != r1) goto L_0x019a
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            int r0 = getHex(r0)
            int r0 = r0 << 4
            com.itextpdf.text.pdf.RandomAccessFileOrArray r1 = r11.file
            int r1 = r1.read()
            int r1 = getHex(r1)
            int r0 = r0 + r1
        L_0x019a:
            java.lang.StringBuilder r1 = r11.outBuf
            char r0 = (char) r0
            r1.append(r0)
            goto L_0x016c
        L_0x01a1:
            java.lang.StringBuilder r0 = r11.outBuf
            r0.setLength(r2)
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = com.itextpdf.text.pdf.PRTokeniser.TokenType.STRING
            r11.type = r0
            r11.hexString = r2
            r0 = r2
        L_0x01ad:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r8 = r11.file
            int r8 = r8.read()
            if (r8 != r1) goto L_0x01b7
            goto L_0x0259
        L_0x01b7:
            if (r8 != r3) goto L_0x01bd
            int r0 = r0 + 1
            goto L_0x0257
        L_0x01bd:
            r9 = 41
            if (r8 != r9) goto L_0x01c5
            int r0 = r0 + -1
            goto L_0x0257
        L_0x01c5:
            r9 = 92
            if (r8 != r9) goto L_0x0246
            com.itextpdf.text.pdf.RandomAccessFileOrArray r8 = r11.file
            int r8 = r8.read()
            if (r8 == r5) goto L_0x023a
            if (r8 == r4) goto L_0x022f
            if (r8 == r9) goto L_0x022c
            r9 = 98
            if (r8 == r9) goto L_0x022a
            r9 = 102(0x66, float:1.43E-43)
            if (r8 == r9) goto L_0x0227
            r9 = 110(0x6e, float:1.54E-43)
            if (r8 == r9) goto L_0x0224
            r9 = 114(0x72, float:1.6E-43)
            if (r8 == r9) goto L_0x0221
            r9 = 116(0x74, float:1.63E-43)
            if (r8 == r9) goto L_0x021e
            switch(r8) {
                case 40: goto L_0x022c;
                case 41: goto L_0x022c;
                default: goto L_0x01ec;
            }
        L_0x01ec:
            if (r8 < r7) goto L_0x022c
            r9 = 55
            if (r8 <= r9) goto L_0x01f3
            goto L_0x022c
        L_0x01f3:
            int r8 = r8 + -48
            com.itextpdf.text.pdf.RandomAccessFileOrArray r10 = r11.file
            int r10 = r10.read()
            if (r10 < r7) goto L_0x021a
            if (r10 <= r9) goto L_0x0200
            goto L_0x021a
        L_0x0200:
            int r8 = r8 << 3
            int r8 = r8 + r10
            int r8 = r8 - r7
            com.itextpdf.text.pdf.RandomAccessFileOrArray r10 = r11.file
            int r10 = r10.read()
            if (r10 < r7) goto L_0x0216
            if (r10 <= r9) goto L_0x020f
            goto L_0x0216
        L_0x020f:
            int r8 = r8 << 3
            int r8 = r8 + r10
            int r8 = r8 - r7
            r8 = r8 & 255(0xff, float:3.57E-43)
            goto L_0x022c
        L_0x0216:
            r11.backOnePosition(r10)
            goto L_0x022c
        L_0x021a:
            r11.backOnePosition(r10)
            goto L_0x022c
        L_0x021e:
            r8 = 9
            goto L_0x022c
        L_0x0221:
            r8 = r2
            r9 = r4
            goto L_0x023c
        L_0x0224:
            r8 = r2
            r9 = r5
            goto L_0x023c
        L_0x0227:
            r8 = 12
            goto L_0x022c
        L_0x022a:
            r8 = 8
        L_0x022c:
            r9 = r8
            r8 = r2
            goto L_0x023c
        L_0x022f:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r8 = r11.file
            int r8 = r8.read()
            if (r8 == r5) goto L_0x023a
            r11.backOnePosition(r8)
        L_0x023a:
            r9 = r8
            r8 = r6
        L_0x023c:
            if (r8 == 0) goto L_0x0240
            goto L_0x01ad
        L_0x0240:
            if (r9 >= 0) goto L_0x0244
            r8 = r9
            goto L_0x0259
        L_0x0244:
            r8 = r9
            goto L_0x0257
        L_0x0246:
            if (r8 != r4) goto L_0x0257
            com.itextpdf.text.pdf.RandomAccessFileOrArray r8 = r11.file
            int r8 = r8.read()
            if (r8 >= 0) goto L_0x0251
            goto L_0x0259
        L_0x0251:
            if (r8 == r5) goto L_0x0257
            r11.backOnePosition(r8)
            r8 = r5
        L_0x0257:
            if (r0 != r1) goto L_0x0267
        L_0x0259:
            if (r8 != r1) goto L_0x027f
            java.lang.String r0 = "error.reading.string"
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r0, (java.lang.Object[]) r1)
            r11.throwError(r0)
            goto L_0x027f
        L_0x0267:
            java.lang.StringBuilder r9 = r11.outBuf
            char r8 = (char) r8
            r9.append(r8)
            goto L_0x01ad
        L_0x026f:
            com.itextpdf.text.pdf.PRTokeniser$TokenType r0 = com.itextpdf.text.pdf.PRTokeniser.TokenType.COMMENT
            r11.type = r0
        L_0x0273:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r0 = r11.file
            int r0 = r0.read()
            if (r0 == r1) goto L_0x027f
            if (r0 == r4) goto L_0x027f
            if (r0 != r5) goto L_0x0273
        L_0x027f:
            java.lang.StringBuilder r0 = r11.outBuf
            if (r0 == 0) goto L_0x028b
            java.lang.StringBuilder r0 = r11.outBuf
            java.lang.String r0 = r0.toString()
            r11.stringValue = r0
        L_0x028b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PRTokeniser.nextToken():boolean");
    }

    public long longValue() {
        return Long.parseLong(this.stringValue);
    }

    public int intValue() {
        return Integer.parseInt(this.stringValue);
    }

    public boolean readLineSegment(byte[] bArr) throws IOException {
        return readLineSegment(bArr, true);
    }

    public boolean readLineSegment(byte[] bArr, boolean z) throws IOException {
        boolean z2;
        int i;
        int i2;
        int read;
        int length = bArr.length;
        if (length > 0) {
            do {
                read = read();
            } while (isWhitespace(read, z));
            z2 = false;
            i = read;
            i2 = 0;
        } else {
            z2 = false;
            i2 = 0;
            i = -1;
        }
        while (!z2 && i2 < length) {
            if (!(i == -1 || i == 10)) {
                if (i != 13) {
                    bArr[i2] = (byte) i;
                    i2++;
                    if (z2 || length <= i2) {
                        break;
                    }
                    i = read();
                } else {
                    long filePointer = getFilePointer();
                    if (read() != 10) {
                        seek(filePointer);
                    }
                }
            }
            z2 = true;
            i = read();
        }
        if (i2 >= length) {
            boolean z3 = false;
            while (!z3) {
                i = read();
                if (!(i == -1 || i == 10)) {
                    if (i == 13) {
                        long filePointer2 = getFilePointer();
                        if (read() != 10) {
                            seek(filePointer2);
                        }
                    }
                }
                z3 = true;
            }
        }
        if (i == -1 && i2 == 0) {
            return false;
        }
        if (i2 + 2 <= length) {
            bArr[i2] = DocWriter.SPACE;
            bArr[i2 + 1] = 88;
        }
        return true;
    }

    public static long[] checkObjectStart(byte[] bArr) {
        try {
            PRTokeniser pRTokeniser = new PRTokeniser(new RandomAccessFileOrArray(new RandomAccessSourceFactory().createSource(bArr)));
            if (pRTokeniser.nextToken()) {
                if (pRTokeniser.getTokenType() == TokenType.NUMBER) {
                    int intValue = pRTokeniser.intValue();
                    if (pRTokeniser.nextToken()) {
                        if (pRTokeniser.getTokenType() == TokenType.NUMBER) {
                            int intValue2 = pRTokeniser.intValue();
                            if (!pRTokeniser.nextToken() || !pRTokeniser.getStringValue().equals("obj")) {
                                return null;
                            }
                            return new long[]{(long) intValue, (long) intValue2};
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean isHexString() {
        return this.hexString;
    }
}
