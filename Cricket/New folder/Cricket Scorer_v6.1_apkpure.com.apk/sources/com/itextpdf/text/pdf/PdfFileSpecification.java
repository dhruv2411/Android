package com.itextpdf.text.pdf;

import com.itextpdf.text.pdf.collection.PdfCollectionItem;
import java.io.IOException;
import java.io.OutputStream;

public class PdfFileSpecification extends PdfDictionary {
    protected PdfIndirectReference ref;
    protected PdfWriter writer;

    public PdfFileSpecification() {
        super(PdfName.FILESPEC);
    }

    public static PdfFileSpecification url(PdfWriter pdfWriter, String str) {
        PdfFileSpecification pdfFileSpecification = new PdfFileSpecification();
        pdfFileSpecification.writer = pdfWriter;
        pdfFileSpecification.put(PdfName.FS, PdfName.URL);
        pdfFileSpecification.put(PdfName.F, new PdfString(str));
        return pdfFileSpecification;
    }

    public static PdfFileSpecification fileEmbedded(PdfWriter pdfWriter, String str, String str2, byte[] bArr) throws IOException {
        return fileEmbedded(pdfWriter, str, str2, bArr, 9);
    }

    public static PdfFileSpecification fileEmbedded(PdfWriter pdfWriter, String str, String str2, byte[] bArr, int i) throws IOException {
        return fileEmbedded(pdfWriter, str, str2, bArr, (String) null, (PdfDictionary) null, i);
    }

    public static PdfFileSpecification fileEmbedded(PdfWriter pdfWriter, String str, String str2, byte[] bArr, boolean z) throws IOException {
        return fileEmbedded(pdfWriter, str, str2, bArr, (String) null, (PdfDictionary) null, z ? 9 : 0);
    }

    public static PdfFileSpecification fileEmbedded(PdfWriter pdfWriter, String str, String str2, byte[] bArr, boolean z, String str3, PdfDictionary pdfDictionary) throws IOException {
        return fileEmbedded(pdfWriter, str, str2, bArr, str3, pdfDictionary, z ? 9 : 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0110 A[SYNTHETIC, Splitter:B:53:0x0110] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.itextpdf.text.pdf.PdfFileSpecification fileEmbedded(com.itextpdf.text.pdf.PdfWriter r4, java.lang.String r5, java.lang.String r6, byte[] r7, java.lang.String r8, com.itextpdf.text.pdf.PdfDictionary r9, int r10) throws java.io.IOException {
        /*
            com.itextpdf.text.pdf.PdfFileSpecification r0 = new com.itextpdf.text.pdf.PdfFileSpecification
            r0.<init>()
            r0.writer = r4
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.F
            com.itextpdf.text.pdf.PdfString r2 = new com.itextpdf.text.pdf.PdfString
            r2.<init>((java.lang.String) r6)
            r0.put(r1, r2)
            r1 = 0
            r0.setUnicodeFileName(r6, r1)
            r6 = 0
            if (r7 != 0) goto L_0x0082
            com.itextpdf.text.pdf.PdfIndirectReference r2 = r4.getPdfIndirectReference()     // Catch:{ all -> 0x007e }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x007e }
            r3.<init>(r5)     // Catch:{ all -> 0x007e }
            boolean r3 = r3.canRead()     // Catch:{ all -> 0x007e }
            if (r3 == 0) goto L_0x002e
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x007e }
            r1.<init>(r5)     // Catch:{ all -> 0x007e }
            r6 = r1
            goto L_0x0076
        L_0x002e:
            java.lang.String r3 = "file:/"
            boolean r3 = r5.startsWith(r3)     // Catch:{ all -> 0x007e }
            if (r3 != 0) goto L_0x006c
            java.lang.String r3 = "http://"
            boolean r3 = r5.startsWith(r3)     // Catch:{ all -> 0x007e }
            if (r3 != 0) goto L_0x006c
            java.lang.String r3 = "https://"
            boolean r3 = r5.startsWith(r3)     // Catch:{ all -> 0x007e }
            if (r3 != 0) goto L_0x006c
            java.lang.String r3 = "jar:"
            boolean r3 = r5.startsWith(r3)     // Catch:{ all -> 0x007e }
            if (r3 == 0) goto L_0x004f
            goto L_0x006c
        L_0x004f:
            java.io.InputStream r3 = com.itextpdf.text.io.StreamUtil.getResourceStream(r5)     // Catch:{ all -> 0x007e }
            if (r3 != 0) goto L_0x006a
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0066 }
            java.lang.String r6 = "1.not.found.as.file.or.resource"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0066 }
            r7[r1] = r5     // Catch:{ all -> 0x0066 }
            java.lang.String r5 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r6, (java.lang.Object[]) r7)     // Catch:{ all -> 0x0066 }
            r4.<init>(r5)     // Catch:{ all -> 0x0066 }
            throw r4     // Catch:{ all -> 0x0066 }
        L_0x0066:
            r4 = move-exception
            r1 = r3
            goto L_0x010e
        L_0x006a:
            r6 = r3
            goto L_0x0076
        L_0x006c:
            java.net.URL r1 = new java.net.URL     // Catch:{ all -> 0x007e }
            r1.<init>(r5)     // Catch:{ all -> 0x007e }
            java.io.InputStream r5 = r1.openStream()     // Catch:{ all -> 0x007e }
            r6 = r5
        L_0x0076:
            com.itextpdf.text.pdf.PdfEFStream r5 = new com.itextpdf.text.pdf.PdfEFStream     // Catch:{ all -> 0x007e }
            r5.<init>(r6, r4)     // Catch:{ all -> 0x007e }
            r1 = r6
            r6 = r2
            goto L_0x0088
        L_0x007e:
            r4 = move-exception
            r1 = r6
            goto L_0x010e
        L_0x0082:
            com.itextpdf.text.pdf.PdfEFStream r5 = new com.itextpdf.text.pdf.PdfEFStream     // Catch:{ all -> 0x007e }
            r5.<init>(r7)     // Catch:{ all -> 0x007e }
            r1 = r6
        L_0x0088:
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.TYPE     // Catch:{ all -> 0x010d }
            com.itextpdf.text.pdf.PdfName r3 = com.itextpdf.text.pdf.PdfName.EMBEDDEDFILE     // Catch:{ all -> 0x010d }
            r5.put(r2, r3)     // Catch:{ all -> 0x010d }
            r5.flateCompress(r10)     // Catch:{ all -> 0x010d }
            com.itextpdf.text.pdf.PdfDictionary r10 = new com.itextpdf.text.pdf.PdfDictionary     // Catch:{ all -> 0x010d }
            r10.<init>()     // Catch:{ all -> 0x010d }
            if (r9 == 0) goto L_0x009c
            r10.merge(r9)     // Catch:{ all -> 0x010d }
        L_0x009c:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.MODDATE     // Catch:{ all -> 0x010d }
            boolean r9 = r10.contains(r9)     // Catch:{ all -> 0x010d }
            if (r9 != 0) goto L_0x00ae
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.MODDATE     // Catch:{ all -> 0x010d }
            com.itextpdf.text.pdf.PdfDate r2 = new com.itextpdf.text.pdf.PdfDate     // Catch:{ all -> 0x010d }
            r2.<init>()     // Catch:{ all -> 0x010d }
            r10.put(r9, r2)     // Catch:{ all -> 0x010d }
        L_0x00ae:
            if (r7 != 0) goto L_0x00b6
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.PARAMS     // Catch:{ all -> 0x010d }
            r5.put(r9, r6)     // Catch:{ all -> 0x010d }
            goto L_0x00c9
        L_0x00b6:
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.SIZE     // Catch:{ all -> 0x010d }
            com.itextpdf.text.pdf.PdfNumber r2 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ all -> 0x010d }
            int r3 = r5.getRawLength()     // Catch:{ all -> 0x010d }
            r2.<init>((int) r3)     // Catch:{ all -> 0x010d }
            r10.put(r9, r2)     // Catch:{ all -> 0x010d }
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.PARAMS     // Catch:{ all -> 0x010d }
            r5.put(r9, r10)     // Catch:{ all -> 0x010d }
        L_0x00c9:
            if (r8 == 0) goto L_0x00d5
            com.itextpdf.text.pdf.PdfName r9 = com.itextpdf.text.pdf.PdfName.SUBTYPE     // Catch:{ all -> 0x010d }
            com.itextpdf.text.pdf.PdfName r2 = new com.itextpdf.text.pdf.PdfName     // Catch:{ all -> 0x010d }
            r2.<init>((java.lang.String) r8)     // Catch:{ all -> 0x010d }
            r5.put(r9, r2)     // Catch:{ all -> 0x010d }
        L_0x00d5:
            com.itextpdf.text.pdf.PdfIndirectObject r8 = r4.addToBody(r5)     // Catch:{ all -> 0x010d }
            com.itextpdf.text.pdf.PdfIndirectReference r8 = r8.getIndirectReference()     // Catch:{ all -> 0x010d }
            if (r7 != 0) goto L_0x00f3
            r5.writeLength()     // Catch:{ all -> 0x010d }
            com.itextpdf.text.pdf.PdfName r7 = com.itextpdf.text.pdf.PdfName.SIZE     // Catch:{ all -> 0x010d }
            com.itextpdf.text.pdf.PdfNumber r9 = new com.itextpdf.text.pdf.PdfNumber     // Catch:{ all -> 0x010d }
            int r5 = r5.getRawLength()     // Catch:{ all -> 0x010d }
            r9.<init>((int) r5)     // Catch:{ all -> 0x010d }
            r10.put(r7, r9)     // Catch:{ all -> 0x010d }
            r4.addToBody((com.itextpdf.text.pdf.PdfObject) r10, (com.itextpdf.text.pdf.PdfIndirectReference) r6)     // Catch:{ all -> 0x010d }
        L_0x00f3:
            if (r1 == 0) goto L_0x00f8
            r1.close()     // Catch:{ Exception -> 0x00f8 }
        L_0x00f8:
            com.itextpdf.text.pdf.PdfDictionary r4 = new com.itextpdf.text.pdf.PdfDictionary
            r4.<init>()
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.F
            r4.put(r5, r8)
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.UF
            r4.put(r5, r8)
            com.itextpdf.text.pdf.PdfName r5 = com.itextpdf.text.pdf.PdfName.EF
            r0.put(r5, r4)
            return r0
        L_0x010d:
            r4 = move-exception
        L_0x010e:
            if (r1 == 0) goto L_0x0113
            r1.close()     // Catch:{ Exception -> 0x0113 }
        L_0x0113:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.PdfFileSpecification.fileEmbedded(com.itextpdf.text.pdf.PdfWriter, java.lang.String, java.lang.String, byte[], java.lang.String, com.itextpdf.text.pdf.PdfDictionary, int):com.itextpdf.text.pdf.PdfFileSpecification");
    }

    public static PdfFileSpecification fileExtern(PdfWriter pdfWriter, String str) {
        PdfFileSpecification pdfFileSpecification = new PdfFileSpecification();
        pdfFileSpecification.writer = pdfWriter;
        pdfFileSpecification.put(PdfName.F, new PdfString(str));
        pdfFileSpecification.setUnicodeFileName(str, false);
        return pdfFileSpecification;
    }

    public PdfIndirectReference getReference() throws IOException {
        if (this.ref != null) {
            return this.ref;
        }
        this.ref = this.writer.addToBody(this).getIndirectReference();
        return this.ref;
    }

    public void setMultiByteFileName(byte[] bArr) {
        put(PdfName.F, new PdfString(bArr).setHexWriting(true));
    }

    public void setUnicodeFileName(String str, boolean z) {
        put(PdfName.UF, new PdfString(str, z ? PdfObject.TEXT_UNICODE : PdfObject.TEXT_PDFDOCENCODING));
    }

    public void setVolatile(boolean z) {
        put(PdfName.V, new PdfBoolean(z));
    }

    public void addDescription(String str, boolean z) {
        put(PdfName.DESC, new PdfString(str, z ? PdfObject.TEXT_UNICODE : PdfObject.TEXT_PDFDOCENCODING));
    }

    public void addCollectionItem(PdfCollectionItem pdfCollectionItem) {
        put(PdfName.CI, pdfCollectionItem);
    }

    public void toPdf(PdfWriter pdfWriter, OutputStream outputStream) throws IOException {
        PdfWriter.checkPdfIsoConformance(pdfWriter, 10, this);
        super.toPdf(pdfWriter, outputStream);
    }
}
