package com.itextpdf.text.pdf;

public class TtfUnicodeWriter {
    protected PdfWriter writer = null;

    public TtfUnicodeWriter(PdfWriter pdfWriter) {
        this.writer = pdfWriter;
    }

    /* JADX WARNING: type inference failed for: r14v22, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeFont(com.itextpdf.text.pdf.TrueTypeFontUnicode r12, com.itextpdf.text.pdf.PdfIndirectReference r13, java.lang.Object[] r14, byte[] r15) throws com.itextpdf.text.DocumentException, java.io.IOException {
        /*
            r11 = this;
            r15 = 0
            r14 = r14[r15]
            java.util.HashMap r14 = (java.util.HashMap) r14
            boolean r0 = r12.subset
            r1 = 1
            r12.addRangeUni(r14, r1, r0)
            java.util.Collection r0 = r14.values()
            int[][] r2 = new int[r15][]
            java.lang.Object[] r0 = r0.toArray(r2)
            int[][] r0 = (int[][]) r0
            java.util.Arrays.sort(r0, r12)
            boolean r2 = r12.cff
            if (r2 == 0) goto L_0x0078
            byte[] r2 = r12.readCffFont()
            boolean r3 = r12.subset
            if (r3 != 0) goto L_0x002a
            java.util.ArrayList r3 = r12.subsetRanges
            if (r3 == 0) goto L_0x0064
        L_0x002a:
            com.itextpdf.text.pdf.CFFFontSubset r3 = new com.itextpdf.text.pdf.CFFFontSubset
            com.itextpdf.text.pdf.RandomAccessFileOrArray r4 = new com.itextpdf.text.pdf.RandomAccessFileOrArray
            r4.<init>((byte[]) r2)
            r3.<init>(r4, r14)
            java.lang.String[] r4 = r3.getNames()     // Catch:{ Exception -> 0x0040 }
            r4 = r4[r15]     // Catch:{ Exception -> 0x0040 }
            byte[] r3 = r3.Process(r4)     // Catch:{ Exception -> 0x0040 }
            r2 = r3
            goto L_0x0064
        L_0x0040:
            r0 = move-exception
            java.lang.Class<com.itextpdf.text.pdf.TtfUnicodeWriter> r3 = com.itextpdf.text.pdf.TtfUnicodeWriter.class
            com.itextpdf.text.log.Logger r3 = com.itextpdf.text.log.LoggerFactory.getLogger((java.lang.Class<?>) r3)
            java.lang.String r4 = "Issue in CFF font subsetting.Subsetting was disabled"
            r3.error(r4, r0)
            r12.setSubset(r15)
            boolean r0 = r12.subset
            r12.addRangeUni(r14, r1, r0)
            java.util.Collection r14 = r14.values()
            int[][] r15 = new int[r15][]
            java.lang.Object[] r14 = r14.toArray(r15)
            r0 = r14
            int[][] r0 = (int[][]) r0
            java.util.Arrays.sort(r0, r12)
        L_0x0064:
            com.itextpdf.text.pdf.BaseFont$StreamFont r14 = new com.itextpdf.text.pdf.BaseFont$StreamFont
            java.lang.String r15 = "CIDFontType0C"
            int r1 = r12.compressionLevel
            r14.<init>((byte[]) r2, (java.lang.String) r15, (int) r1)
            com.itextpdf.text.pdf.PdfWriter r15 = r11.writer
            com.itextpdf.text.pdf.PdfIndirectObject r14 = r15.addToBody(r14)
            com.itextpdf.text.pdf.PdfIndirectReference r14 = r14.getIndirectReference()
            goto L_0x00c0
        L_0x0078:
            boolean r2 = r12.subset
            if (r2 != 0) goto L_0x0086
            int r2 = r12.directoryOffset
            if (r2 == 0) goto L_0x0081
            goto L_0x0086
        L_0x0081:
            byte[] r14 = r12.getFullFont()
            goto L_0x00aa
        L_0x0086:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r2 = r12.rf
            monitor-enter(r2)
            com.itextpdf.text.pdf.TrueTypeFontSubSet r10 = new com.itextpdf.text.pdf.TrueTypeFontSubSet     // Catch:{ all -> 0x0101 }
            java.lang.String r4 = r12.fileName     // Catch:{ all -> 0x0101 }
            com.itextpdf.text.pdf.RandomAccessFileOrArray r5 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x0101 }
            com.itextpdf.text.pdf.RandomAccessFileOrArray r3 = r12.rf     // Catch:{ all -> 0x0101 }
            r5.<init>((com.itextpdf.text.pdf.RandomAccessFileOrArray) r3)     // Catch:{ all -> 0x0101 }
            java.util.HashSet r6 = new java.util.HashSet     // Catch:{ all -> 0x0101 }
            java.util.Set r14 = r14.keySet()     // Catch:{ all -> 0x0101 }
            r6.<init>(r14)     // Catch:{ all -> 0x0101 }
            int r7 = r12.directoryOffset     // Catch:{ all -> 0x0101 }
            r8 = 1
            r9 = 0
            r3 = r10
            r3.<init>(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0101 }
            byte[] r14 = r10.process()     // Catch:{ all -> 0x0101 }
            monitor-exit(r2)     // Catch:{ all -> 0x0101 }
        L_0x00aa:
            int[] r1 = new int[r1]
            int r2 = r14.length
            r1[r15] = r2
            com.itextpdf.text.pdf.BaseFont$StreamFont r15 = new com.itextpdf.text.pdf.BaseFont$StreamFont
            int r2 = r12.compressionLevel
            r15.<init>((byte[]) r14, (int[]) r1, (int) r2)
            com.itextpdf.text.pdf.PdfWriter r14 = r11.writer
            com.itextpdf.text.pdf.PdfIndirectObject r14 = r14.addToBody(r15)
            com.itextpdf.text.pdf.PdfIndirectReference r14 = r14.getIndirectReference()
        L_0x00c0:
            java.lang.String r15 = ""
            boolean r1 = r12.subset
            if (r1 == 0) goto L_0x00ca
            java.lang.String r15 = com.itextpdf.text.pdf.TrueTypeFontUnicode.createSubsetPrefix()
        L_0x00ca:
            r1 = 0
            com.itextpdf.text.pdf.PdfDictionary r14 = r12.getFontDescriptor(r14, r15, r1)
            com.itextpdf.text.pdf.PdfWriter r2 = r11.writer
            com.itextpdf.text.pdf.PdfIndirectObject r14 = r2.addToBody(r14)
            com.itextpdf.text.pdf.PdfIndirectReference r14 = r14.getIndirectReference()
            com.itextpdf.text.pdf.PdfDictionary r14 = r12.getCIDFontType2(r14, r15, r0)
            com.itextpdf.text.pdf.PdfWriter r2 = r11.writer
            com.itextpdf.text.pdf.PdfIndirectObject r14 = r2.addToBody(r14)
            com.itextpdf.text.pdf.PdfIndirectReference r14 = r14.getIndirectReference()
            com.itextpdf.text.pdf.PdfStream r0 = r12.getToUnicode(r0)
            if (r0 == 0) goto L_0x00f7
            com.itextpdf.text.pdf.PdfWriter r1 = r11.writer
            com.itextpdf.text.pdf.PdfIndirectObject r0 = r1.addToBody(r0)
            com.itextpdf.text.pdf.PdfIndirectReference r1 = r0.getIndirectReference()
        L_0x00f7:
            com.itextpdf.text.pdf.PdfDictionary r12 = r12.getFontBaseType(r14, r15, r1)
            com.itextpdf.text.pdf.PdfWriter r14 = r11.writer
            r14.addToBody((com.itextpdf.text.pdf.PdfObject) r12, (com.itextpdf.text.pdf.PdfIndirectReference) r13)
            return
        L_0x0101:
            r12 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0101 }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.TtfUnicodeWriter.writeFont(com.itextpdf.text.pdf.TrueTypeFontUnicode, com.itextpdf.text.pdf.PdfIndirectReference, java.lang.Object[], byte[]):void");
    }
}
