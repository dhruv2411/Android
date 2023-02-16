package com.itextpdf.xmp.impl;

import com.itextpdf.xmp.XMPDateTime;
import com.itextpdf.xmp.XMPException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class ISO8601Converter {
    private ISO8601Converter() {
    }

    public static XMPDateTime parse(String str) throws XMPException {
        return parse(str, new XMPDateTimeImpl());
    }

    /* JADX WARNING: Removed duplicated region for block: B:129:0x021b  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0223 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.itextpdf.xmp.XMPDateTime parse(java.lang.String r11, com.itextpdf.xmp.XMPDateTime r12) throws com.itextpdf.xmp.XMPException {
        /*
            if (r11 != 0) goto L_0x000b
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Parameter must not be null"
            r0 = 4
            r11.<init>(r12, r0)
            throw r11
        L_0x000b:
            int r0 = r11.length()
            if (r0 != 0) goto L_0x0012
            return r12
        L_0x0012:
            com.itextpdf.xmp.impl.ParseState r0 = new com.itextpdf.xmp.impl.ParseState
            r0.<init>(r11)
            r11 = 0
            char r1 = r0.ch(r11)
            r2 = 45
            if (r1 != r2) goto L_0x0023
            r0.skip()
        L_0x0023:
            java.lang.String r1 = "Invalid year in date string"
            r3 = 9999(0x270f, float:1.4012E-41)
            int r1 = r0.gatherInt(r1, r3)
            boolean r3 = r0.hasNext()
            r4 = 5
            if (r3 == 0) goto L_0x0040
            char r3 = r0.ch()
            if (r3 == r2) goto L_0x0040
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after year"
            r11.<init>(r12, r4)
            throw r11
        L_0x0040:
            char r3 = r0.ch(r11)
            if (r3 != r2) goto L_0x0047
            int r1 = -r1
        L_0x0047:
            r12.setYear(r1)
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x0051
            return r12
        L_0x0051:
            r0.skip()
            java.lang.String r1 = "Invalid month in date string"
            r3 = 12
            int r1 = r0.gatherInt(r1, r3)
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0070
            char r3 = r0.ch()
            if (r3 == r2) goto L_0x0070
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after month"
            r11.<init>(r12, r4)
            throw r11
        L_0x0070:
            r12.setMonth(r1)
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x007a
            return r12
        L_0x007a:
            r0.skip()
            java.lang.String r1 = "Invalid day in date string"
            r3 = 31
            int r1 = r0.gatherInt(r1, r3)
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x009b
            char r3 = r0.ch()
            r5 = 84
            if (r3 == r5) goto L_0x009b
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after day"
            r11.<init>(r12, r4)
            throw r11
        L_0x009b:
            r12.setDay(r1)
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x00a5
            return r12
        L_0x00a5:
            r0.skip()
            java.lang.String r1 = "Invalid hour in date string"
            r3 = 23
            int r1 = r0.gatherInt(r1, r3)
            r12.setHour(r1)
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x00ba
            return r12
        L_0x00ba:
            char r1 = r0.ch()
            r5 = 59
            r6 = 58
            r7 = 43
            r8 = 90
            if (r1 != r6) goto L_0x00fa
            r0.skip()
            java.lang.String r1 = "Invalid minute in date string"
            int r1 = r0.gatherInt(r1, r5)
            boolean r9 = r0.hasNext()
            if (r9 == 0) goto L_0x00f7
            char r9 = r0.ch()
            if (r9 == r6) goto L_0x00f7
            char r9 = r0.ch()
            if (r9 == r8) goto L_0x00f7
            char r9 = r0.ch()
            if (r9 == r7) goto L_0x00f7
            char r9 = r0.ch()
            if (r9 == r2) goto L_0x00f7
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after minute"
            r11.<init>(r12, r4)
            throw r11
        L_0x00f7:
            r12.setMinute(r1)
        L_0x00fa:
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x0101
            return r12
        L_0x0101:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0190
            char r1 = r0.ch()
            if (r1 != r6) goto L_0x0190
            r0.skip()
            java.lang.String r1 = "Invalid whole seconds in date string"
            int r1 = r0.gatherInt(r1, r5)
            boolean r9 = r0.hasNext()
            r10 = 46
            if (r9 == 0) goto L_0x013e
            char r9 = r0.ch()
            if (r9 == r10) goto L_0x013e
            char r9 = r0.ch()
            if (r9 == r8) goto L_0x013e
            char r9 = r0.ch()
            if (r9 == r7) goto L_0x013e
            char r9 = r0.ch()
            if (r9 == r2) goto L_0x013e
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after whole seconds"
            r11.<init>(r12, r4)
            throw r11
        L_0x013e:
            r12.setSecond(r1)
            char r1 = r0.ch()
            if (r1 != r10) goto L_0x01aa
            r0.skip()
            int r1 = r0.pos()
            java.lang.String r9 = "Invalid fractional seconds in date string"
            r10 = 999999999(0x3b9ac9ff, float:0.004723787)
            int r9 = r0.gatherInt(r9, r10)
            boolean r10 = r0.hasNext()
            if (r10 == 0) goto L_0x0177
            char r10 = r0.ch()
            if (r10 == r8) goto L_0x0177
            char r10 = r0.ch()
            if (r10 == r7) goto L_0x0177
            char r10 = r0.ch()
            if (r10 == r2) goto L_0x0177
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after fractional second"
            r11.<init>(r12, r4)
            throw r11
        L_0x0177:
            int r10 = r0.pos()
            int r10 = r10 - r1
        L_0x017c:
            r1 = 9
            if (r10 <= r1) goto L_0x0185
            int r9 = r9 / 10
            int r10 = r10 + -1
            goto L_0x017c
        L_0x0185:
            if (r10 >= r1) goto L_0x018c
            int r9 = r9 * 10
            int r10 = r10 + 1
            goto L_0x0185
        L_0x018c:
            r12.setNanoSecond(r9)
            goto L_0x01aa
        L_0x0190:
            char r1 = r0.ch()
            if (r1 == r8) goto L_0x01aa
            char r1 = r0.ch()
            if (r1 == r7) goto L_0x01aa
            char r1 = r0.ch()
            if (r1 == r2) goto L_0x01aa
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after time"
            r11.<init>(r12, r4)
            throw r11
        L_0x01aa:
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x01b1
            return r12
        L_0x01b1:
            char r1 = r0.ch()
            if (r1 != r8) goto L_0x01bb
            r0.skip()
            goto L_0x01ff
        L_0x01bb:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x01ff
            char r1 = r0.ch()
            if (r1 != r7) goto L_0x01c9
            r1 = 1
            goto L_0x01d0
        L_0x01c9:
            char r1 = r0.ch()
            if (r1 != r2) goto L_0x01f7
            r1 = -1
        L_0x01d0:
            r0.skip()
            java.lang.String r2 = "Invalid time zone hour in date string"
            int r2 = r0.gatherInt(r2, r3)
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0201
            char r11 = r0.ch()
            if (r11 != r6) goto L_0x01ef
            r0.skip()
            java.lang.String r11 = "Invalid time zone minute in date string"
            int r11 = r0.gatherInt(r11, r5)
            goto L_0x0201
        L_0x01ef:
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Invalid date string, after time zone hour"
            r11.<init>(r12, r4)
            throw r11
        L_0x01f7:
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Time zone must begin with 'Z', '+', or '-'"
            r11.<init>(r12, r4)
            throw r11
        L_0x01ff:
            r1 = r11
            r2 = r1
        L_0x0201:
            int r2 = r2 * 3600
            int r2 = r2 * 1000
            int r11 = r11 * 60
            int r11 = r11 * 1000
            int r2 = r2 + r11
            int r2 = r2 * r1
            java.util.SimpleTimeZone r11 = new java.util.SimpleTimeZone
            java.lang.String r1 = ""
            r11.<init>(r2, r1)
            r12.setTimeZone(r11)
            boolean r11 = r0.hasNext()
            if (r11 == 0) goto L_0x0223
            com.itextpdf.xmp.XMPException r11 = new com.itextpdf.xmp.XMPException
            java.lang.String r12 = "Invalid date string, extra chars at end"
            r11.<init>(r12, r4)
            throw r11
        L_0x0223:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.xmp.impl.ISO8601Converter.parse(java.lang.String, com.itextpdf.xmp.XMPDateTime):com.itextpdf.xmp.XMPDateTime");
    }

    public static String render(XMPDateTime xMPDateTime) {
        StringBuffer stringBuffer = new StringBuffer();
        if (xMPDateTime.hasDate()) {
            DecimalFormat decimalFormat = new DecimalFormat("0000", new DecimalFormatSymbols(Locale.ENGLISH));
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.getYear()));
            if (xMPDateTime.getMonth() == 0) {
                return stringBuffer.toString();
            }
            decimalFormat.applyPattern("'-'00");
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.getMonth()));
            if (xMPDateTime.getDay() == 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.getDay()));
            if (xMPDateTime.hasTime()) {
                stringBuffer.append('T');
                decimalFormat.applyPattern("00");
                stringBuffer.append(decimalFormat.format((long) xMPDateTime.getHour()));
                stringBuffer.append(':');
                stringBuffer.append(decimalFormat.format((long) xMPDateTime.getMinute()));
                if (!(xMPDateTime.getSecond() == 0 && xMPDateTime.getNanoSecond() == 0)) {
                    decimalFormat.applyPattern(":00.#########");
                    stringBuffer.append(decimalFormat.format(((double) xMPDateTime.getSecond()) + (((double) xMPDateTime.getNanoSecond()) / 1.0E9d)));
                }
                if (xMPDateTime.hasTimeZone()) {
                    int offset = xMPDateTime.getTimeZone().getOffset(xMPDateTime.getCalendar().getTimeInMillis());
                    if (offset == 0) {
                        stringBuffer.append('Z');
                    } else {
                        int i = offset / 3600000;
                        int abs = Math.abs((offset % 3600000) / 60000);
                        decimalFormat.applyPattern("+00;-00");
                        stringBuffer.append(decimalFormat.format((long) i));
                        decimalFormat.applyPattern(":00");
                        stringBuffer.append(decimalFormat.format((long) abs));
                    }
                }
            }
        }
        return stringBuffer.toString();
    }
}
