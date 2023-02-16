package com.itextpdf.text.pdf;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.xmp.XMPError;

public class Barcode128 extends Barcode {
    private static final byte[][] BARS = {new byte[]{2, 1, 2, 2, 2, 2}, new byte[]{2, 2, 2, 1, 2, 2}, new byte[]{2, 2, 2, 2, 2, 1}, new byte[]{1, 2, 1, 2, 2, 3}, new byte[]{1, 2, 1, 3, 2, 2}, new byte[]{1, 3, 1, 2, 2, 2}, new byte[]{1, 2, 2, 2, 1, 3}, new byte[]{1, 2, 2, 3, 1, 2}, new byte[]{1, 3, 2, 2, 1, 2}, new byte[]{2, 2, 1, 2, 1, 3}, new byte[]{2, 2, 1, 3, 1, 2}, new byte[]{2, 3, 1, 2, 1, 2}, new byte[]{1, 1, 2, 2, 3, 2}, new byte[]{1, 2, 2, 1, 3, 2}, new byte[]{1, 2, 2, 2, 3, 1}, new byte[]{1, 1, 3, 2, 2, 2}, new byte[]{1, 2, 3, 1, 2, 2}, new byte[]{1, 2, 3, 2, 2, 1}, new byte[]{2, 2, 3, 2, 1, 1}, new byte[]{2, 2, 1, 1, 3, 2}, new byte[]{2, 2, 1, 2, 3, 1}, new byte[]{2, 1, 3, 2, 1, 2}, new byte[]{2, 2, 3, 1, 1, 2}, new byte[]{3, 1, 2, 1, 3, 1}, new byte[]{3, 1, 1, 2, 2, 2}, new byte[]{3, 2, 1, 1, 2, 2}, new byte[]{3, 2, 1, 2, 2, 1}, new byte[]{3, 1, 2, 2, 1, 2}, new byte[]{3, 2, 2, 1, 1, 2}, new byte[]{3, 2, 2, 2, 1, 1}, new byte[]{2, 1, 2, 1, 2, 3}, new byte[]{2, 1, 2, 3, 2, 1}, new byte[]{2, 3, 2, 1, 2, 1}, new byte[]{1, 1, 1, 3, 2, 3}, new byte[]{1, 3, 1, 1, 2, 3}, new byte[]{1, 3, 1, 3, 2, 1}, new byte[]{1, 1, 2, 3, 1, 3}, new byte[]{1, 3, 2, 1, 1, 3}, new byte[]{1, 3, 2, 3, 1, 1}, new byte[]{2, 1, 1, 3, 1, 3}, new byte[]{2, 3, 1, 1, 1, 3}, new byte[]{2, 3, 1, 3, 1, 1}, new byte[]{1, 1, 2, 1, 3, 3}, new byte[]{1, 1, 2, 3, 3, 1}, new byte[]{1, 3, 2, 1, 3, 1}, new byte[]{1, 1, 3, 1, 2, 3}, new byte[]{1, 1, 3, 3, 2, 1}, new byte[]{1, 3, 3, 1, 2, 1}, new byte[]{3, 1, 3, 1, 2, 1}, new byte[]{2, 1, 1, 3, 3, 1}, new byte[]{2, 3, 1, 1, 3, 1}, new byte[]{2, 1, 3, 1, 1, 3}, new byte[]{2, 1, 3, 3, 1, 1}, new byte[]{2, 1, 3, 1, 3, 1}, new byte[]{3, 1, 1, 1, 2, 3}, new byte[]{3, 1, 1, 3, 2, 1}, new byte[]{3, 3, 1, 1, 2, 1}, new byte[]{3, 1, 2, 1, 1, 3}, new byte[]{3, 1, 2, 3, 1, 1}, new byte[]{3, 3, 2, 1, 1, 1}, new byte[]{3, 1, 4, 1, 1, 1}, new byte[]{2, 2, 1, 4, 1, 1}, new byte[]{4, 3, 1, 1, 1, 1}, new byte[]{1, 1, 1, 2, 2, 4}, new byte[]{1, 1, 1, 4, 2, 2}, new byte[]{1, 2, 1, 1, 2, 4}, new byte[]{1, 2, 1, 4, 2, 1}, new byte[]{1, 4, 1, 1, 2, 2}, new byte[]{1, 4, 1, 2, 2, 1}, new byte[]{1, 1, 2, 2, 1, 4}, new byte[]{1, 1, 2, 4, 1, 2}, new byte[]{1, 2, 2, 1, 1, 4}, new byte[]{1, 2, 2, 4, 1, 1}, new byte[]{1, 4, 2, 1, 1, 2}, new byte[]{1, 4, 2, 2, 1, 1}, new byte[]{2, 4, 1, 2, 1, 1}, new byte[]{2, 2, 1, 1, 1, 4}, new byte[]{4, 1, 3, 1, 1, 1}, new byte[]{2, 4, 1, 1, 1, 2}, new byte[]{1, 3, 4, 1, 1, 1}, new byte[]{1, 1, 1, 2, 4, 2}, new byte[]{1, 2, 1, 1, 4, 2}, new byte[]{1, 2, 1, 2, 4, 1}, new byte[]{1, 1, 4, 2, 1, 2}, new byte[]{1, 2, 4, 1, 1, 2}, new byte[]{1, 2, 4, 2, 1, 1}, new byte[]{4, 1, 1, 2, 1, 2}, new byte[]{4, 2, 1, 1, 1, 2}, new byte[]{4, 2, 1, 2, 1, 1}, new byte[]{2, 1, 2, 1, 4, 1}, new byte[]{2, 1, 4, 1, 2, 1}, new byte[]{4, 1, 2, 1, 2, 1}, new byte[]{1, 1, 1, 1, 4, 3}, new byte[]{1, 1, 1, 3, 4, 1}, new byte[]{1, 3, 1, 1, 4, 1}, new byte[]{1, 1, 4, 1, 1, 3}, new byte[]{1, 1, 4, 3, 1, 1}, new byte[]{4, 1, 1, 1, 1, 3}, new byte[]{4, 1, 1, 3, 1, 1}, new byte[]{1, 1, 3, 1, 4, 1}, new byte[]{1, 1, 4, 1, 3, 1}, new byte[]{3, 1, 1, 1, 4, 1}, new byte[]{4, 1, 1, 1, 3, 1}, new byte[]{2, 1, 1, 4, 1, 2}, new byte[]{2, 1, 1, 2, 1, 4}, new byte[]{2, 1, 1, 2, 3, 2}};
    private static final byte[] BARS_STOP = {2, 3, 3, 1, 1, 1, 2};
    public static final char CODE_A = 'È';
    public static final char CODE_AB_TO_C = 'c';
    public static final char CODE_AC_TO_B = 'd';
    public static final char CODE_BC_TO_A = 'e';
    public static final char CODE_C = 'Ç';
    public static final char DEL = 'Ã';
    public static final char FNC1 = 'Ê';
    public static final char FNC1_INDEX = 'f';
    public static final char FNC2 = 'Å';
    public static final char FNC3 = 'Ä';
    public static final char FNC4 = 'È';
    public static final char SHIFT = 'Æ';
    public static final char STARTA = 'Ë';
    public static final char STARTB = 'Ì';
    public static final char STARTC = 'Í';
    public static final char START_A = 'g';
    public static final char START_B = 'h';
    public static final char START_C = 'i';
    private static final IntHashtable ais = new IntHashtable();
    private Barcode128CodeSet codeSet = Barcode128CodeSet.AUTO;

    static {
        ais.put(0, 20);
        ais.put(1, 16);
        ais.put(2, 16);
        ais.put(10, -1);
        ais.put(11, 9);
        ais.put(12, 8);
        ais.put(13, 8);
        ais.put(15, 8);
        ais.put(17, 8);
        ais.put(20, 4);
        ais.put(21, -1);
        ais.put(22, -1);
        ais.put(23, -1);
        ais.put(PsExtractor.VIDEO_STREAM_MASK, -1);
        ais.put(241, -1);
        ais.put(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, -1);
        ais.put(251, -1);
        ais.put(252, -1);
        ais.put(30, -1);
        for (int i = 3100; i < 3700; i++) {
            ais.put(i, 10);
        }
        ais.put(37, -1);
        for (int i2 = 3900; i2 < 3940; i2++) {
            ais.put(i2, -1);
        }
        ais.put(400, -1);
        ais.put(401, -1);
        ais.put(402, 20);
        ais.put(403, -1);
        for (int i3 = 410; i3 < 416; i3++) {
            ais.put(i3, 16);
        }
        ais.put(420, -1);
        ais.put(421, -1);
        ais.put(422, 6);
        ais.put(423, -1);
        ais.put(424, 6);
        ais.put(425, 6);
        ais.put(426, 6);
        ais.put(7001, 17);
        ais.put(7002, -1);
        for (int i4 = 7030; i4 < 7040; i4++) {
            ais.put(i4, -1);
        }
        ais.put(8001, 18);
        ais.put(8002, -1);
        ais.put(8003, -1);
        ais.put(8004, -1);
        ais.put(8005, 10);
        ais.put(8006, 22);
        ais.put(8007, -1);
        ais.put(8008, -1);
        ais.put(8018, 22);
        ais.put(8020, -1);
        ais.put(8100, 10);
        ais.put(8101, 14);
        ais.put(8102, 6);
        for (int i5 = 90; i5 < 100; i5++) {
            ais.put(i5, -1);
        }
    }

    public Barcode128() {
        try {
            this.x = 0.8f;
            this.font = BaseFont.createFont("Helvetica", "winansi", false);
            this.size = 8.0f;
            this.baseline = this.size;
            this.barHeight = this.size * 3.0f;
            this.textAlignment = 1;
            this.codeType = 9;
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public enum Barcode128CodeSet {
        A,
        B,
        C,
        AUTO;

        public char getStartSymbol() {
            switch (this) {
                case A:
                    return Barcode128.START_A;
                case B:
                    return Barcode128.START_B;
                case C:
                    return Barcode128.START_C;
                default:
                    return Barcode128.START_B;
            }
        }
    }

    public void setCodeSet(Barcode128CodeSet barcode128CodeSet) {
        this.codeSet = barcode128CodeSet;
    }

    public Barcode128CodeSet getCodeSet() {
        return this.codeSet;
    }

    public static String removeFNC1(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= ' ' && charAt <= '~') {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    public static String getHumanReadableUCCEAN(String str) {
        String substring;
        StringBuffer stringBuffer = new StringBuffer();
        String valueOf = String.valueOf(FNC1);
        while (true) {
            try {
                if (str.startsWith(valueOf)) {
                    str = str.substring(1);
                } else {
                    int i = 2;
                    int i2 = 0;
                    while (true) {
                        if (i >= 5) {
                            break;
                        } else if (str.length() < i) {
                            break;
                        } else {
                            i2 = ais.get(Integer.parseInt(str.substring(0, i)));
                            if (i2 != 0) {
                                break;
                            }
                            i++;
                        }
                    }
                    i = 0;
                    if (i == 0) {
                        break;
                    }
                    stringBuffer.append('(');
                    stringBuffer.append(str.substring(0, i));
                    stringBuffer.append(')');
                    substring = str.substring(i);
                    if (i2 > 0) {
                        int i3 = i2 - i;
                        try {
                            if (substring.length() <= i3) {
                                break;
                            }
                            stringBuffer.append(removeFNC1(substring.substring(0, i3)));
                            str = substring.substring(i3);
                        } catch (Exception unused) {
                        }
                    } else {
                        int indexOf = substring.indexOf(XMPError.BADRDF);
                        if (indexOf < 0) {
                            break;
                        }
                        stringBuffer.append(substring.substring(0, indexOf));
                        str = substring.substring(indexOf + 1);
                    }
                }
            } catch (Exception unused2) {
            }
        }
        stringBuffer.append(removeFNC1(str));
        return stringBuffer.toString();
        str = substring;
        stringBuffer.append(removeFNC1(str));
        return stringBuffer.toString();
    }

    static boolean isNextDigits(String str, int i, int i2) {
        int length = str.length();
        loop0:
        while (i < length && i2 > 0) {
            if (str.charAt(i) == 202) {
                i++;
            } else {
                int min = Math.min(2, i2);
                if (i + min > length) {
                    return false;
                }
                while (true) {
                    int i3 = min - 1;
                    if (min <= 0) {
                        continue;
                        break;
                    }
                    int i4 = i + 1;
                    char charAt = str.charAt(i);
                    if (charAt < '0' || charAt > '9') {
                        return false;
                    }
                    i2--;
                    i = i4;
                    min = i3;
                }
                return false;
            }
        }
        if (i2 == 0) {
            return true;
        }
        return false;
    }

    static String getPackedRawDigits(String str, int i, int i2) {
        StringBuilder sb = new StringBuilder("");
        int i3 = i;
        while (i2 > 0) {
            if (str.charAt(i3) == 202) {
                sb.append(FNC1_INDEX);
                i3++;
            } else {
                i2 -= 2;
                int i4 = i3 + 1;
                sb.append((char) (((str.charAt(i3) - '0') * 10) + (str.charAt(i4) - '0')));
                i3 = i4 + 1;
            }
        }
        return ((char) (i3 - i)) + sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0250, code lost:
        r3 = r1;
        r7 = r10;
        r1 = START_A;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x029e, code lost:
        r3 = r1;
        r1 = START_C;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x02b9, code lost:
        r7 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x02e0, code lost:
        r3 = r1;
        r7 = r10;
        r1 = START_B;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x030f, code lost:
        if (r2 == com.itextpdf.text.pdf.Barcode128.Barcode128CodeSet.AUTO) goto L_0x0327;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0315, code lost:
        if (r1 == r19.getStartSymbol()) goto L_0x0327;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0326, code lost:
        throw new java.lang.RuntimeException(com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage("there.are.illegal.characters.for.barcode.128.in.1", r0));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getRawText(java.lang.String r17, boolean r18, com.itextpdf.text.pdf.Barcode128.Barcode128CodeSet r19) {
        /*
            r0 = r17
            r2 = r19
            java.lang.String r3 = ""
            int r4 = r17.length()
            r5 = 102(0x66, float:1.43E-43)
            if (r4 != 0) goto L_0x0033
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            char r2 = r19.getStartSymbol()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            if (r18 == 0) goto L_0x0032
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r5)
            java.lang.String r0 = r1.toString()
        L_0x0032:
            return r0
        L_0x0033:
            r6 = 0
            r7 = r6
        L_0x0035:
            r8 = 202(0xca, float:2.83E-43)
            r9 = 1
            if (r7 >= r4) goto L_0x0057
            char r10 = r0.charAt(r7)
            r11 = 127(0x7f, float:1.78E-43)
            if (r10 <= r11) goto L_0x0054
            if (r10 == r8) goto L_0x0054
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "there.are.illegal.characters.for.barcode.128.in.1"
            java.lang.Object[] r3 = new java.lang.Object[r9]
            r3[r6] = r0
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)
            r1.<init>(r0)
            throw r1
        L_0x0054:
            int r7 = r7 + 1
            goto L_0x0035
        L_0x0057:
            char r7 = r0.charAt(r6)
            com.itextpdf.text.pdf.Barcode128$Barcode128CodeSet r10 = com.itextpdf.text.pdf.Barcode128.Barcode128CodeSet.AUTO
            r11 = 103(0x67, float:1.44E-43)
            r12 = 105(0x69, float:1.47E-43)
            r13 = 104(0x68, float:1.46E-43)
            r14 = 2
            r15 = 32
            if (r2 == r10) goto L_0x006c
            com.itextpdf.text.pdf.Barcode128$Barcode128CodeSet r10 = com.itextpdf.text.pdf.Barcode128.Barcode128CodeSet.C
            if (r2 != r10) goto L_0x00b2
        L_0x006c:
            boolean r10 = isNextDigits(r0, r6, r14)
            if (r10 == 0) goto L_0x00b2
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r3)
            r7.append(r12)
            java.lang.String r3 = r7.toString()
            if (r18 == 0) goto L_0x0092
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r5)
            java.lang.String r3 = r1.toString()
        L_0x0092:
            java.lang.String r1 = getPackedRawDigits(r0, r6, r14)
            char r7 = r1.charAt(r6)
            int r7 = r7 + r6
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r3)
            java.lang.String r1 = r1.substring(r9)
            r10.append(r1)
            java.lang.String r1 = r10.toString()
            r3 = r1
            r1 = r12
            goto L_0x0130
        L_0x00b2:
            if (r7 >= r15) goto L_0x00ea
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r3)
            r10.append(r11)
            java.lang.String r3 = r10.toString()
            if (r18 == 0) goto L_0x00d4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r5)
            java.lang.String r3 = r1.toString()
        L_0x00d4:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            int r7 = r7 + 64
            char r3 = (char) r7
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r3 = r1
            r7 = r9
            r1 = r11
            goto L_0x0130
        L_0x00ea:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r3)
            r10.append(r13)
            java.lang.String r3 = r10.toString()
            if (r18 == 0) goto L_0x010a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r5)
            java.lang.String r3 = r1.toString()
        L_0x010a:
            if (r7 != r8) goto L_0x011c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            goto L_0x012d
        L_0x011c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            int r7 = r7 - r15
            char r3 = (char) r7
            r1.append(r3)
            java.lang.String r1 = r1.toString()
        L_0x012d:
            r3 = r1
            r7 = r9
            r1 = r13
        L_0x0130:
            com.itextpdf.text.pdf.Barcode128$Barcode128CodeSet r10 = com.itextpdf.text.pdf.Barcode128.Barcode128CodeSet.AUTO
            if (r2 == r10) goto L_0x014a
            char r10 = r19.getStartSymbol()
            if (r1 == r10) goto L_0x014a
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "there.are.illegal.characters.for.barcode.128.in.1"
            java.lang.Object[] r3 = new java.lang.Object[r9]
            r3[r6] = r0
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)
            r1.<init>(r0)
            throw r1
        L_0x014a:
            if (r7 >= r4) goto L_0x032f
            r10 = 99
            r11 = 100
            r12 = 101(0x65, float:1.42E-43)
            r13 = 4
            switch(r1) {
                case 103: goto L_0x0269;
                case 104: goto L_0x01dd;
                case 105: goto L_0x0158;
                default: goto L_0x0156;
            }
        L_0x0156:
            goto L_0x030d
        L_0x0158:
            boolean r10 = isNextDigits(r0, r7, r14)
            if (r10 == 0) goto L_0x017c
            java.lang.String r10 = getPackedRawDigits(r0, r7, r14)
            char r11 = r10.charAt(r6)
            int r7 = r7 + r11
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r3)
            java.lang.String r3 = r10.substring(r9)
            r11.append(r3)
            java.lang.String r3 = r11.toString()
            goto L_0x030d
        L_0x017c:
            int r10 = r7 + 1
            char r7 = r0.charAt(r7)
            if (r7 != r8) goto L_0x0195
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r3)
            r7.append(r5)
            java.lang.String r3 = r7.toString()
            goto L_0x02b9
        L_0x0195:
            if (r7 >= r15) goto L_0x01ba
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            int r7 = r7 + 64
            char r1 = (char) r7
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            goto L_0x0250
        L_0x01ba:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            int r7 = r7 + -32
            char r1 = (char) r7
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            goto L_0x02e0
        L_0x01dd:
            com.itextpdf.text.pdf.Barcode128$Barcode128CodeSet r11 = com.itextpdf.text.pdf.Barcode128.Barcode128CodeSet.AUTO
            if (r2 != r11) goto L_0x0214
            boolean r11 = isNextDigits(r0, r7, r13)
            if (r11 == 0) goto L_0x0214
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = getPackedRawDigits(r0, r7, r13)
            char r10 = r3.charAt(r6)
            int r7 = r7 + r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r1)
            java.lang.String r1 = r3.substring(r9)
            r10.append(r1)
            java.lang.String r1 = r10.toString()
            goto L_0x029e
        L_0x0214:
            int r10 = r7 + 1
            char r7 = r0.charAt(r7)
            if (r7 != r8) goto L_0x022d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r3)
            r7.append(r5)
            java.lang.String r3 = r7.toString()
            goto L_0x02b9
        L_0x022d:
            if (r7 >= r15) goto L_0x0256
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r12)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            int r7 = r7 + 64
            char r1 = (char) r7
            r3.append(r1)
            java.lang.String r1 = r3.toString()
        L_0x0250:
            r3 = r1
            r7 = r10
            r1 = 103(0x67, float:1.44E-43)
            goto L_0x030d
        L_0x0256:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r3)
            int r7 = r7 + -32
            char r3 = (char) r7
            r11.append(r3)
            java.lang.String r3 = r11.toString()
            goto L_0x02b9
        L_0x0269:
            com.itextpdf.text.pdf.Barcode128$Barcode128CodeSet r12 = com.itextpdf.text.pdf.Barcode128.Barcode128CodeSet.AUTO
            if (r2 != r12) goto L_0x02a2
            boolean r12 = isNextDigits(r0, r7, r13)
            if (r12 == 0) goto L_0x02a2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = getPackedRawDigits(r0, r7, r13)
            char r10 = r3.charAt(r6)
            int r7 = r7 + r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r1)
            java.lang.String r1 = r3.substring(r9)
            r10.append(r1)
            java.lang.String r1 = r10.toString()
        L_0x029e:
            r3 = r1
            r1 = 105(0x69, float:1.47E-43)
            goto L_0x030d
        L_0x02a2:
            int r10 = r7 + 1
            char r7 = r0.charAt(r7)
            if (r7 != r8) goto L_0x02bb
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r3)
            r7.append(r5)
            java.lang.String r3 = r7.toString()
        L_0x02b9:
            r7 = r10
            goto L_0x030d
        L_0x02bb:
            r12 = 95
            if (r7 <= r12) goto L_0x02e5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            int r7 = r7 + -32
            char r1 = (char) r7
            r3.append(r1)
            java.lang.String r1 = r3.toString()
        L_0x02e0:
            r3 = r1
            r7 = r10
            r1 = 104(0x68, float:1.46E-43)
            goto L_0x030d
        L_0x02e5:
            if (r7 >= r15) goto L_0x02fa
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r3)
            int r7 = r7 + 64
            char r3 = (char) r7
            r11.append(r3)
            java.lang.String r3 = r11.toString()
            goto L_0x02b9
        L_0x02fa:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r3)
            int r7 = r7 + -32
            char r3 = (char) r7
            r11.append(r3)
            java.lang.String r3 = r11.toString()
            goto L_0x02b9
        L_0x030d:
            com.itextpdf.text.pdf.Barcode128$Barcode128CodeSet r10 = com.itextpdf.text.pdf.Barcode128.Barcode128CodeSet.AUTO
            if (r2 == r10) goto L_0x0327
            char r10 = r19.getStartSymbol()
            if (r1 == r10) goto L_0x0327
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "there.are.illegal.characters.for.barcode.128.in.1"
            java.lang.Object[] r3 = new java.lang.Object[r9]
            r3[r6] = r0
            java.lang.String r0 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)
            r1.<init>(r0)
            throw r1
        L_0x0327:
            r11 = 103(0x67, float:1.44E-43)
            r12 = 105(0x69, float:1.47E-43)
            r13 = 104(0x68, float:1.46E-43)
            goto L_0x014a
        L_0x032f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.Barcode128.getRawText(java.lang.String, boolean, com.itextpdf.text.pdf.Barcode128$Barcode128CodeSet):java.lang.String");
    }

    public static String getRawText(String str, boolean z) {
        return getRawText(str, z, Barcode128CodeSet.AUTO);
    }

    public static byte[] getBarsCode128Raw(String str) {
        int indexOf = str.indexOf(65535);
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        int charAt = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            charAt += str.charAt(i) * i;
        }
        String str2 = str + ((char) (charAt % 103));
        byte[] bArr = new byte[(((str2.length() + 1) * 6) + 7)];
        int i2 = 0;
        while (i2 < str2.length()) {
            System.arraycopy(BARS[str2.charAt(i2)], 0, bArr, i2 * 6, 6);
            i2++;
        }
        System.arraycopy(BARS_STOP, 0, bArr, i2 * 6, 7);
        return bArr;
    }

    public Rectangle getBarcodeSize() {
        float f;
        String str;
        float f2;
        String str2;
        boolean z = true;
        float f3 = 0.0f;
        if (this.font != null) {
            if (this.baseline > 0.0f) {
                f2 = this.baseline - this.font.getFontDescriptor(3, this.size);
            } else {
                f2 = (-this.baseline) + this.size;
            }
            f3 = f2;
            if (this.codeType == 11) {
                int indexOf = this.code.indexOf(65535);
                if (indexOf < 0) {
                    str2 = "";
                } else {
                    str2 = this.code.substring(indexOf + 1);
                }
            } else if (this.codeType == 10) {
                str2 = getHumanReadableUCCEAN(this.code);
            } else {
                str2 = removeFNC1(this.code);
            }
            BaseFont baseFont = this.font;
            if (this.altText != null) {
                str2 = this.altText;
            }
            f = baseFont.getWidthPoint(str2, this.size);
        } else {
            f = 0.0f;
        }
        if (this.codeType == 11) {
            int indexOf2 = this.code.indexOf(65535);
            if (indexOf2 >= 0) {
                str = this.code.substring(0, indexOf2);
            } else {
                str = this.code;
            }
        } else {
            String str3 = this.code;
            if (this.codeType != 10) {
                z = false;
            }
            str = getRawText(str3, z, this.codeSet);
        }
        return new Rectangle(Math.max((((float) ((str.length() + 2) * 11)) * this.x) + (2.0f * this.x), f), this.barHeight + f3);
    }

    public Rectangle placeBarcode(PdfContentByte pdfContentByte, BaseColor baseColor, BaseColor baseColor2) {
        String str;
        float f;
        String str2;
        float f2;
        float f3;
        float f4;
        float f5;
        boolean z = true;
        if (this.codeType == 11) {
            int indexOf = this.code.indexOf(65535);
            if (indexOf < 0) {
                str = "";
            } else {
                str = this.code.substring(indexOf + 1);
            }
        } else if (this.codeType == 10) {
            str = getHumanReadableUCCEAN(this.code);
        } else {
            str = removeFNC1(this.code);
        }
        float f6 = 0.0f;
        if (this.font != null) {
            BaseFont baseFont = this.font;
            if (this.altText != null) {
                str = this.altText;
            }
            f = baseFont.getWidthPoint(str, this.size);
        } else {
            f = 0.0f;
        }
        if (this.codeType == 11) {
            int indexOf2 = this.code.indexOf(65535);
            if (indexOf2 >= 0) {
                str2 = this.code.substring(0, indexOf2);
            } else {
                str2 = this.code;
            }
        } else {
            str2 = getRawText(this.code, this.codeType == 10, this.codeSet);
        }
        float length = (((float) ((str2.length() + 2) * 11)) * this.x) + (this.x * 2.0f);
        int i = this.textAlignment;
        if (i != 0) {
            if (i != 2) {
                if (f > length) {
                    f3 = (f - length) / 2.0f;
                } else {
                    f5 = (length - f) / 2.0f;
                    f2 = f5;
                    f3 = 0.0f;
                }
            } else if (f > length) {
                f3 = f - length;
            } else {
                f5 = length - f;
                f2 = f5;
                f3 = 0.0f;
            }
            f2 = 0.0f;
        } else {
            f3 = 0.0f;
            f2 = 0.0f;
        }
        if (this.font == null) {
            f4 = 0.0f;
        } else if (this.baseline <= 0.0f) {
            f4 = this.barHeight - this.baseline;
        } else {
            float f7 = -this.font.getFontDescriptor(3, this.size);
            float f8 = f7;
            f6 = this.baseline + f7;
            f4 = f8;
        }
        byte[] barsCode128Raw = getBarsCode128Raw(str2);
        if (baseColor != null) {
            pdfContentByte.setColorFill(baseColor);
        }
        for (byte b : barsCode128Raw) {
            float f9 = ((float) b) * this.x;
            if (z) {
                pdfContentByte.rectangle(f3, f6, f9 - this.inkSpreading, this.barHeight);
            }
            z = !z;
            f3 += f9;
        }
        pdfContentByte.fill();
        if (this.font != null) {
            if (baseColor2 != null) {
                pdfContentByte.setColorFill(baseColor2);
            }
            pdfContentByte.beginText();
            pdfContentByte.setFontAndSize(this.font, this.size);
            pdfContentByte.setTextMatrix(f2, f4);
            pdfContentByte.showText(str);
            pdfContentByte.endText();
        }
        return getBarcodeSize();
    }

    public void setCode(String str) {
        if (getCodeType() != 10 || !str.startsWith("(")) {
            super.setCode(str);
            return;
        }
        StringBuilder sb = new StringBuilder("");
        int i = 0;
        while (i >= 0) {
            int indexOf = str.indexOf(41, i);
            if (indexOf < 0) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("badly.formed.ucc.string.1", str));
            }
            String substring = str.substring(i + 1, indexOf);
            if (substring.length() < 2) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("ai.too.short.1", substring));
            }
            int parseInt = Integer.parseInt(substring);
            int i2 = ais.get(parseInt);
            if (i2 == 0) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("ai.not.found.1", substring));
            }
            String valueOf = String.valueOf(parseInt);
            if (valueOf.length() == 1) {
                valueOf = "0" + valueOf;
            }
            int indexOf2 = str.indexOf(40, indexOf);
            int length = indexOf2 < 0 ? str.length() : indexOf2;
            sb.append(valueOf);
            sb.append(str.substring(indexOf + 1, length));
            if (i2 < 0) {
                if (indexOf2 >= 0) {
                    sb.append(FNC1);
                }
            } else if (((length - indexOf) - 1) + valueOf.length() != i2) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.ai.length.1", valueOf));
            }
            i = indexOf2;
        }
        super.setCode(sb.toString());
    }
}
