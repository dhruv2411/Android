package com.itextpdf.text;

import com.itextpdf.text.log.Level;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class FontFactoryImp implements FontProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) FontFactoryImp.class);
    private static String[] TTFamilyOrder = {"3", "1", "1033", "3", "0", "1033", "1", "0", "0", "0", "3", "0"};
    public boolean defaultEmbedding = false;
    public String defaultEncoding = "Cp1252";
    private final Hashtable<String, ArrayList<String>> fontFamilies = new Hashtable<>();
    private final Hashtable<String, String> trueTypeFonts = new Hashtable<>();

    public FontFactoryImp() {
        this.trueTypeFonts.put("Courier".toLowerCase(), "Courier");
        this.trueTypeFonts.put("Courier-Bold".toLowerCase(), "Courier-Bold");
        this.trueTypeFonts.put("Courier-Oblique".toLowerCase(), "Courier-Oblique");
        this.trueTypeFonts.put("Courier-BoldOblique".toLowerCase(), "Courier-BoldOblique");
        this.trueTypeFonts.put("Helvetica".toLowerCase(), "Helvetica");
        this.trueTypeFonts.put("Helvetica-Bold".toLowerCase(), "Helvetica-Bold");
        this.trueTypeFonts.put("Helvetica-Oblique".toLowerCase(), "Helvetica-Oblique");
        this.trueTypeFonts.put("Helvetica-BoldOblique".toLowerCase(), "Helvetica-BoldOblique");
        this.trueTypeFonts.put("Symbol".toLowerCase(), "Symbol");
        this.trueTypeFonts.put("Times-Roman".toLowerCase(), "Times-Roman");
        this.trueTypeFonts.put("Times-Bold".toLowerCase(), "Times-Bold");
        this.trueTypeFonts.put("Times-Italic".toLowerCase(), "Times-Italic");
        this.trueTypeFonts.put("Times-BoldItalic".toLowerCase(), "Times-BoldItalic");
        this.trueTypeFonts.put("ZapfDingbats".toLowerCase(), "ZapfDingbats");
        ArrayList arrayList = new ArrayList();
        arrayList.add("Courier");
        arrayList.add("Courier-Bold");
        arrayList.add("Courier-Oblique");
        arrayList.add("Courier-BoldOblique");
        this.fontFamilies.put("Courier".toLowerCase(), arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("Helvetica");
        arrayList2.add("Helvetica-Bold");
        arrayList2.add("Helvetica-Oblique");
        arrayList2.add("Helvetica-BoldOblique");
        this.fontFamilies.put("Helvetica".toLowerCase(), arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("Symbol");
        this.fontFamilies.put("Symbol".toLowerCase(), arrayList3);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add("Times-Roman");
        arrayList4.add("Times-Bold");
        arrayList4.add("Times-Italic");
        arrayList4.add("Times-BoldItalic");
        this.fontFamilies.put(FontFactory.TIMES.toLowerCase(), arrayList4);
        this.fontFamilies.put("Times-Roman".toLowerCase(), arrayList4);
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add("ZapfDingbats");
        this.fontFamilies.put("ZapfDingbats".toLowerCase(), arrayList5);
    }

    public Font getFont(String str, String str2, boolean z, float f, int i, BaseColor baseColor) {
        return getFont(str, str2, z, f, i, baseColor, true);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0066 A[Catch:{ all -> 0x0073 }, LOOP:0: B:13:0x002d->B:29:0x0066, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0062 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.Font getFont(java.lang.String r17, java.lang.String r18, boolean r19, float r20, int r21, com.itextpdf.text.BaseColor r22, boolean r23) {
        /*
            r16 = this;
            r1 = r16
            r2 = r20
            r3 = r21
            r4 = r22
            if (r17 != 0) goto L_0x0012
            com.itextpdf.text.Font r5 = new com.itextpdf.text.Font
            com.itextpdf.text.Font$FontFamily r6 = com.itextpdf.text.Font.FontFamily.UNDEFINED
            r5.<init>((com.itextpdf.text.Font.FontFamily) r6, (float) r2, (int) r3, (com.itextpdf.text.BaseColor) r4)
            return r5
        L_0x0012:
            java.lang.String r6 = r17.toLowerCase()
            java.util.Hashtable<java.lang.String, java.util.ArrayList<java.lang.String>> r7 = r1.fontFamilies
            java.lang.Object r6 = r7.get(r6)
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            if (r6 == 0) goto L_0x0077
            monitor-enter(r6)
            r7 = 0
            r8 = -1
            if (r3 != r8) goto L_0x0027
            r9 = r7
            goto L_0x0028
        L_0x0027:
            r9 = r3
        L_0x0028:
            java.util.Iterator r10 = r6.iterator()     // Catch:{ all -> 0x0073 }
            r11 = r7
        L_0x002d:
            boolean r12 = r10.hasNext()     // Catch:{ all -> 0x0073 }
            r13 = 1
            if (r12 == 0) goto L_0x0068
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x0073 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0073 }
            java.lang.String r12 = r11.toLowerCase()     // Catch:{ all -> 0x0073 }
            java.lang.String r14 = "bold"
            int r14 = r12.indexOf(r14)     // Catch:{ all -> 0x0073 }
            if (r14 == r8) goto L_0x0048
            r14 = r13
            goto L_0x0049
        L_0x0048:
            r14 = r7
        L_0x0049:
            java.lang.String r15 = "italic"
            int r15 = r12.indexOf(r15)     // Catch:{ all -> 0x0073 }
            if (r15 != r8) goto L_0x005c
            java.lang.String r15 = "oblique"
            int r12 = r12.indexOf(r15)     // Catch:{ all -> 0x0073 }
            if (r12 == r8) goto L_0x005a
            goto L_0x005c
        L_0x005a:
            r12 = r14
            goto L_0x005e
        L_0x005c:
            r12 = r14 | 2
        L_0x005e:
            r14 = r9 & 3
            if (r14 != r12) goto L_0x0066
            r5 = r11
            r11 = r12
            r7 = r13
            goto L_0x006a
        L_0x0066:
            r11 = r12
            goto L_0x002d
        L_0x0068:
            r5 = r17
        L_0x006a:
            if (r3 == r8) goto L_0x0071
            if (r7 == 0) goto L_0x0071
            r7 = r11 ^ -1
            r3 = r3 & r7
        L_0x0071:
            monitor-exit(r6)     // Catch:{ all -> 0x0073 }
            goto L_0x0079
        L_0x0073:
            r0 = move-exception
            r2 = r0
            monitor-exit(r6)     // Catch:{ all -> 0x0073 }
            throw r2
        L_0x0077:
            r5 = r17
        L_0x0079:
            r6 = r18
            r7 = r19
            r8 = r23
            com.itextpdf.text.pdf.BaseFont r5 = r1.getBaseFont(r5, r6, r7, r8)     // Catch:{ DocumentException -> 0x00a3, IOException -> 0x009b, NullPointerException -> 0x0093 }
            if (r5 != 0) goto L_0x008d
            com.itextpdf.text.Font r5 = new com.itextpdf.text.Font     // Catch:{ DocumentException -> 0x00a3, IOException -> 0x009b, NullPointerException -> 0x0093 }
            com.itextpdf.text.Font$FontFamily r6 = com.itextpdf.text.Font.FontFamily.UNDEFINED     // Catch:{ DocumentException -> 0x00a3, IOException -> 0x009b, NullPointerException -> 0x0093 }
            r5.<init>((com.itextpdf.text.Font.FontFamily) r6, (float) r2, (int) r3, (com.itextpdf.text.BaseColor) r4)     // Catch:{ DocumentException -> 0x00a3, IOException -> 0x009b, NullPointerException -> 0x0093 }
            return r5
        L_0x008d:
            com.itextpdf.text.Font r6 = new com.itextpdf.text.Font
            r6.<init>((com.itextpdf.text.pdf.BaseFont) r5, (float) r2, (int) r3, (com.itextpdf.text.BaseColor) r4)
            return r6
        L_0x0093:
            com.itextpdf.text.Font r5 = new com.itextpdf.text.Font
            com.itextpdf.text.Font$FontFamily r6 = com.itextpdf.text.Font.FontFamily.UNDEFINED
            r5.<init>((com.itextpdf.text.Font.FontFamily) r6, (float) r2, (int) r3, (com.itextpdf.text.BaseColor) r4)
            return r5
        L_0x009b:
            com.itextpdf.text.Font r5 = new com.itextpdf.text.Font
            com.itextpdf.text.Font$FontFamily r6 = com.itextpdf.text.Font.FontFamily.UNDEFINED
            r5.<init>((com.itextpdf.text.Font.FontFamily) r6, (float) r2, (int) r3, (com.itextpdf.text.BaseColor) r4)
            return r5
        L_0x00a3:
            r0 = move-exception
            r2 = r0
            com.itextpdf.text.ExceptionConverter r3 = new com.itextpdf.text.ExceptionConverter
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.FontFactoryImp.getFont(java.lang.String, java.lang.String, boolean, float, int, com.itextpdf.text.BaseColor, boolean):com.itextpdf.text.Font");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        r1 = r7.trueTypeFonts.get(r8.toLowerCase());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.pdf.BaseFont getBaseFont(java.lang.String r8, java.lang.String r9, boolean r10, boolean r11) throws java.io.IOException, com.itextpdf.text.DocumentException {
        /*
            r7 = this;
            r4 = 0
            r5 = 0
            r6 = 1
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            com.itextpdf.text.pdf.BaseFont r0 = com.itextpdf.text.pdf.BaseFont.createFont(r0, r1, r2, r3, r4, r5, r6)     // Catch:{ DocumentException -> 0x000c }
            goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            if (r0 != 0) goto L_0x0027
            java.util.Hashtable<java.lang.String, java.lang.String> r1 = r7.trueTypeFonts
            java.lang.String r8 = r8.toLowerCase()
            java.lang.Object r8 = r1.get(r8)
            r1 = r8
            java.lang.String r1 = (java.lang.String) r1
            if (r1 == 0) goto L_0x0027
            r5 = 0
            r6 = 0
            r2 = r9
            r3 = r10
            r4 = r11
            com.itextpdf.text.pdf.BaseFont r0 = com.itextpdf.text.pdf.BaseFont.createFont(r1, r2, r3, r4, r5, r6)
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.FontFactoryImp.getBaseFont(java.lang.String, java.lang.String, boolean, boolean):com.itextpdf.text.pdf.BaseFont");
    }

    public Font getFont(String str, String str2, boolean z, float f, int i) {
        return getFont(str, str2, z, f, i, (BaseColor) null);
    }

    public Font getFont(String str, String str2, boolean z, float f) {
        return getFont(str, str2, z, f, -1, (BaseColor) null);
    }

    public Font getFont(String str, String str2, boolean z) {
        return getFont(str, str2, z, -1.0f, -1, (BaseColor) null);
    }

    public Font getFont(String str, String str2, float f, int i, BaseColor baseColor) {
        return getFont(str, str2, this.defaultEmbedding, f, i, baseColor);
    }

    public Font getFont(String str, String str2, float f, int i) {
        return getFont(str, str2, this.defaultEmbedding, f, i, (BaseColor) null);
    }

    public Font getFont(String str, String str2, float f) {
        return getFont(str, str2, this.defaultEmbedding, f, -1, (BaseColor) null);
    }

    public Font getFont(String str, float f, BaseColor baseColor) {
        return getFont(str, this.defaultEncoding, this.defaultEmbedding, f, -1, baseColor);
    }

    public Font getFont(String str, String str2) {
        return getFont(str, str2, this.defaultEmbedding, -1.0f, -1, (BaseColor) null);
    }

    public Font getFont(String str, float f, int i, BaseColor baseColor) {
        return getFont(str, this.defaultEncoding, this.defaultEmbedding, f, i, baseColor);
    }

    public Font getFont(String str, float f, int i) {
        return getFont(str, this.defaultEncoding, this.defaultEmbedding, f, i, (BaseColor) null);
    }

    public Font getFont(String str, float f) {
        return getFont(str, this.defaultEncoding, this.defaultEmbedding, f, -1, (BaseColor) null);
    }

    public Font getFont(String str) {
        return getFont(str, this.defaultEncoding, this.defaultEmbedding, -1.0f, -1, (BaseColor) null);
    }

    public void registerFamily(String str, String str2, String str3) {
        ArrayList arrayList;
        boolean z;
        if (str3 != null) {
            this.trueTypeFonts.put(str2, str3);
        }
        synchronized (this.fontFamilies) {
            arrayList = this.fontFamilies.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.fontFamilies.put(str, arrayList);
            }
        }
        synchronized (arrayList) {
            if (!arrayList.contains(str2)) {
                int length = str2.length();
                int i = 0;
                while (true) {
                    if (i >= arrayList.size()) {
                        z = false;
                        break;
                    } else if (((String) arrayList.get(i)).length() >= length) {
                        arrayList.add(i, str2);
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
                if (!z) {
                    arrayList.add(str2);
                    String lowerCase = str2.toLowerCase();
                    if (lowerCase.endsWith("regular")) {
                        arrayList.add(0, str2.substring(0, lowerCase.substring(0, lowerCase.length() - 7).trim().length()));
                    }
                }
            }
        }
    }

    public void register(String str) {
        register(str, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x01a8 A[Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void register(java.lang.String r18, java.lang.String r19) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            java.lang.String r4 = r18.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r5 = ".ttf"
            boolean r4 = r4.endsWith(r5)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r5 = 0
            r6 = 3
            r7 = 1
            r8 = 0
            if (r4 != 0) goto L_0x00b2
            java.lang.String r4 = r18.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r9 = ".otf"
            boolean r4 = r4.endsWith(r9)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r4 != 0) goto L_0x00b2
            java.lang.String r4 = r18.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r9 = ".ttc,"
            int r4 = r4.indexOf(r9)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r4 <= 0) goto L_0x002e
            goto L_0x00b2
        L_0x002e:
            java.lang.String r4 = r18.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r9 = ".ttc"
            boolean r4 = r4.endsWith(r9)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r4 == 0) goto L_0x0065
            if (r19 == 0) goto L_0x0043
            com.itextpdf.text.log.Logger r3 = LOGGER     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r4 = "You can't define an alias for a true type collection."
            r3.error(r4)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
        L_0x0043:
            java.lang.String[] r3 = com.itextpdf.text.pdf.BaseFont.enumerateTTCNames((java.lang.String) r18)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r4 = r8
        L_0x0048:
            int r5 = r3.length     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r4 >= r5) goto L_0x019e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r5.<init>()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r5.append(r2)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r6 = ","
            r5.append(r6)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r5.append(r4)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r5 = r5.toString()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r1.register(r5)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r4 = r4 + 1
            goto L_0x0048
        L_0x0065:
            java.lang.String r3 = r18.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r4 = ".afm"
            boolean r3 = r3.endsWith(r4)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r3 != 0) goto L_0x007d
            java.lang.String r3 = r18.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r4 = ".pfm"
            boolean r3 = r3.endsWith(r4)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r3 == 0) goto L_0x019e
        L_0x007d:
            java.lang.String r3 = "Cp1252"
            com.itextpdf.text.pdf.BaseFont r3 = com.itextpdf.text.pdf.BaseFont.createFont(r2, r3, r8)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String[][] r4 = r3.getFullFontName()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r4 = r4[r8]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r4 = r4[r6]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String[][] r9 = r3.getFamilyFontName()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r9 = r9[r8]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r6 = r9[r6]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r6 = r6.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r3 = r3.getPostscriptFontName()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r3 = r3.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r1.registerFamily(r6, r4, r5)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.util.Hashtable<java.lang.String, java.lang.String> r5 = r1.trueTypeFonts     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r5.put(r3, r2)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.util.Hashtable<java.lang.String, java.lang.String> r3 = r1.trueTypeFonts     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r3.put(r4, r2)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            goto L_0x019e
        L_0x00b2:
            java.lang.String r4 = "Cp1252"
            java.lang.Object[] r4 = com.itextpdf.text.pdf.BaseFont.getAllFontNames(r2, r4, r5)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.util.Hashtable<java.lang.String, java.lang.String> r9 = r1.trueTypeFonts     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r10 = r4[r8]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r10 = r10.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r9.put(r10, r2)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r19 == 0) goto L_0x00db
            java.lang.String r3 = r19.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.util.Hashtable<java.lang.String, java.lang.String> r9 = r1.trueTypeFonts     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r9.put(r3, r2)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r9 = "regular"
            boolean r9 = r3.endsWith(r9)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r9 == 0) goto L_0x00db
            r1.saveCopyOfRegularFont(r3, r2)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
        L_0x00db:
            r3 = 2
            r9 = r4[r3]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String[][] r9 = (java.lang.String[][]) r9     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r10 = r9.length     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r11 = r8
        L_0x00e2:
            if (r11 >= r10) goto L_0x00ff
            r12 = r9[r11]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r12 = r12[r6]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r12 = r12.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.util.Hashtable<java.lang.String, java.lang.String> r13 = r1.trueTypeFonts     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r13.put(r12, r2)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r13 = "regular"
            boolean r13 = r12.endsWith(r13)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r13 == 0) goto L_0x00fc
            r1.saveCopyOfRegularFont(r12, r2)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
        L_0x00fc:
            int r11 = r11 + 1
            goto L_0x00e2
        L_0x00ff:
            r9 = r4[r7]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String[][] r9 = (java.lang.String[][]) r9     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r11 = r5
            r10 = r8
        L_0x0105:
            java.lang.String[] r12 = TTFamilyOrder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r12 = r12.length     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r10 >= r12) goto L_0x014a
            int r12 = r9.length     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r13 = r8
        L_0x010c:
            if (r13 >= r12) goto L_0x0147
            r14 = r9[r13]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String[] r15 = TTFamilyOrder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r15 = r15[r10]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r5 = r14[r8]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            boolean r5 = r15.equals(r5)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r5 == 0) goto L_0x0143
            java.lang.String[] r5 = TTFamilyOrder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r15 = r10 + 1
            r5 = r5[r15]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r15 = r14[r7]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            boolean r5 = r5.equals(r15)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r5 == 0) goto L_0x0143
            java.lang.String[] r5 = TTFamilyOrder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r15 = r10 + 2
            r5 = r5[r15]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r15 = r14[r3]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            boolean r5 = r5.equals(r15)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r5 == 0) goto L_0x0143
            r5 = r14[r6]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r5 = r5.toLowerCase()     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String[] r10 = TTFamilyOrder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r10 = r10.length     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r11 = r5
            goto L_0x0147
        L_0x0143:
            int r13 = r13 + 1
            r5 = 0
            goto L_0x010c
        L_0x0147:
            int r10 = r10 + r6
            r5 = 0
            goto L_0x0105
        L_0x014a:
            if (r11 == 0) goto L_0x019e
            java.lang.String r5 = ""
            r4 = r4[r3]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String[][] r4 = (java.lang.String[][]) r4     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r9 = r4.length     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r10 = r5
            r5 = r8
        L_0x0155:
            if (r5 >= r9) goto L_0x019e
            r12 = r4[r5]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r13 = r8
        L_0x015a:
            java.lang.String[] r14 = TTFamilyOrder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r14 = r14.length     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r13 >= r14) goto L_0x019a
            java.lang.String[] r14 = TTFamilyOrder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r14 = r14[r13]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r15 = r12[r8]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            boolean r14 = r14.equals(r15)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r14 == 0) goto L_0x0196
            java.lang.String[] r14 = TTFamilyOrder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r15 = r13 + 1
            r14 = r14[r15]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r15 = r12[r7]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            boolean r14 = r14.equals(r15)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r14 == 0) goto L_0x0196
            java.lang.String[] r14 = TTFamilyOrder     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            int r15 = r13 + 2
            r14 = r14[r15]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r15 = r12[r3]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            boolean r14 = r14.equals(r15)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r14 == 0) goto L_0x0196
            r14 = r12[r6]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            boolean r15 = r14.equals(r10)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r15 == 0) goto L_0x0190
            goto L_0x0196
        L_0x0190:
            r15 = 0
            r1.registerFamily(r11, r14, r15)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r10 = r14
            goto L_0x019b
        L_0x0196:
            r15 = 0
            int r13 = r13 + 3
            goto L_0x015a
        L_0x019a:
            r15 = 0
        L_0x019b:
            int r5 = r5 + 1
            goto L_0x0155
        L_0x019e:
            com.itextpdf.text.log.Logger r3 = LOGGER     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            com.itextpdf.text.log.Level r4 = com.itextpdf.text.log.Level.TRACE     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            boolean r3 = r3.isLogging(r4)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            if (r3 == 0) goto L_0x01b7
            com.itextpdf.text.log.Logger r3 = LOGGER     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r4 = "Registered %s"
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r5[r8] = r2     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            java.lang.String r2 = java.lang.String.format(r4, r5)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
            r3.trace(r2)     // Catch:{ DocumentException -> 0x01c0, IOException -> 0x01b8 }
        L_0x01b7:
            return
        L_0x01b8:
            r0 = move-exception
            r2 = r0
            com.itextpdf.text.ExceptionConverter r3 = new com.itextpdf.text.ExceptionConverter
            r3.<init>(r2)
            throw r3
        L_0x01c0:
            r0 = move-exception
            r2 = r0
            com.itextpdf.text.ExceptionConverter r3 = new com.itextpdf.text.ExceptionConverter
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.FontFactoryImp.register(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public boolean saveCopyOfRegularFont(String str, String str2) {
        String trim = str.substring(0, str.length() - 7).trim();
        if (this.trueTypeFonts.containsKey(trim)) {
            return false;
        }
        this.trueTypeFonts.put(trim, str2);
        return true;
    }

    public int registerDirectory(String str) {
        return registerDirectory(str, false);
    }

    public int registerDirectory(String str, boolean z) {
        if (LOGGER.isLogging(Level.DEBUG)) {
            LOGGER.debug(String.format("Registering directory %s, looking for fonts", new Object[]{str}));
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                if (file.isDirectory()) {
                    String[] list = file.list();
                    if (list == null) {
                        return 0;
                    }
                    int i = 0;
                    int i2 = 0;
                    while (i < list.length) {
                        try {
                            try {
                                File file2 = new File(str, list[i]);
                                if (!file2.isDirectory()) {
                                    String path = file2.getPath();
                                    String lowerCase = path.length() < 4 ? null : path.substring(path.length() - 4).toLowerCase();
                                    if (!".afm".equals(lowerCase)) {
                                        if (!".pfm".equals(lowerCase)) {
                                            if (".ttf".equals(lowerCase) || ".otf".equals(lowerCase) || ".ttc".equals(lowerCase)) {
                                                register(path, (String) null);
                                                i2++;
                                            }
                                        }
                                    }
                                    if (new File(path.substring(0, path.length() - 4) + ".pfb").exists()) {
                                        register(path, (String) null);
                                        i2++;
                                    }
                                } else if (z) {
                                    i2 += registerDirectory(file2.getAbsolutePath(), true);
                                }
                            } catch (Exception unused) {
                            }
                            i++;
                        } catch (Exception unused2) {
                            return i2;
                        }
                    }
                    return i2;
                }
            }
            return 0;
        } catch (Exception unused3) {
            return 0;
        }
    }

    public int registerDirectories() {
        String str = System.getenv("windir");
        String property = System.getProperty("file.separator");
        int i = 0;
        if (!(str == null || property == null)) {
            i = 0 + registerDirectory(str + property + "fonts");
        }
        return i + registerDirectory("/usr/share/X11/fonts", true) + registerDirectory("/usr/X/lib/X11/fonts", true) + registerDirectory("/usr/openwin/lib/X11/fonts", true) + registerDirectory("/usr/share/fonts", true) + registerDirectory("/usr/X11R6/lib/X11/fonts", true) + registerDirectory("/Library/Fonts") + registerDirectory("/System/Library/Fonts");
    }

    public Set<String> getRegisteredFonts() {
        return this.trueTypeFonts.keySet();
    }

    public Set<String> getRegisteredFamilies() {
        return this.fontFamilies.keySet();
    }

    public boolean isRegistered(String str) {
        return this.trueTypeFonts.containsKey(str.toLowerCase());
    }
}
