package com.itextpdf.text;

import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgWMF extends Image {
    ImgWMF(Image image) {
        super(image);
    }

    public ImgWMF(URL url) throws BadElementException, IOException {
        super(url);
        processParameters();
    }

    public ImgWMF(String str) throws BadElementException, MalformedURLException, IOException {
        this(Utilities.toURL(str));
    }

    public ImgWMF(byte[] bArr) throws BadElementException, IOException {
        super((URL) null);
        this.rawData = bArr;
        this.originalData = bArr;
        processParameters();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processParameters() throws com.itextpdf.text.BadElementException, java.io.IOException {
        /*
            r8 = this;
            r0 = 35
            r8.type = r0
            r0 = 6
            r8.originalType = r0
            r0 = 0
            byte[] r1 = r8.rawData     // Catch:{ all -> 0x0093 }
            if (r1 != 0) goto L_0x0021
            java.net.URL r1 = r8.url     // Catch:{ all -> 0x0093 }
            java.io.InputStream r1 = r1.openStream()     // Catch:{ all -> 0x0093 }
            java.net.URL r0 = r8.url     // Catch:{ all -> 0x001c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x001c }
        L_0x0018:
            r7 = r1
            r1 = r0
            r0 = r7
            goto L_0x002b
        L_0x001c:
            r0 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L_0x0094
        L_0x0021:
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0093 }
            byte[] r2 = r8.rawData     // Catch:{ all -> 0x0093 }
            r1.<init>(r2)     // Catch:{ all -> 0x0093 }
            java.lang.String r0 = "Byte array"
            goto L_0x0018
        L_0x002b:
            com.itextpdf.text.pdf.codec.wmf.InputMeta r2 = new com.itextpdf.text.pdf.codec.wmf.InputMeta     // Catch:{ all -> 0x0093 }
            r2.<init>(r0)     // Catch:{ all -> 0x0093 }
            int r3 = r2.readInt()     // Catch:{ all -> 0x0093 }
            r4 = -1698247209(0xffffffff9ac6cdd7, float:-8.222343E-23)
            if (r3 == r4) goto L_0x004b
            com.itextpdf.text.BadElementException r2 = new com.itextpdf.text.BadElementException     // Catch:{ all -> 0x0093 }
            java.lang.String r3 = "1.is.not.a.valid.placeable.windows.metafile"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0093 }
            r5 = 0
            r4[r5] = r1     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x0093 }
            r2.<init>((java.lang.String) r1)     // Catch:{ all -> 0x0093 }
            throw r2     // Catch:{ all -> 0x0093 }
        L_0x004b:
            r2.readWord()     // Catch:{ all -> 0x0093 }
            int r1 = r2.readShort()     // Catch:{ all -> 0x0093 }
            int r3 = r2.readShort()     // Catch:{ all -> 0x0093 }
            int r4 = r2.readShort()     // Catch:{ all -> 0x0093 }
            int r5 = r2.readShort()     // Catch:{ all -> 0x0093 }
            int r2 = r2.readWord()     // Catch:{ all -> 0x0093 }
            r6 = 72
            r8.dpiX = r6     // Catch:{ all -> 0x0093 }
            r8.dpiY = r6     // Catch:{ all -> 0x0093 }
            int r5 = r5 - r3
            float r3 = (float) r5     // Catch:{ all -> 0x0093 }
            float r2 = (float) r2     // Catch:{ all -> 0x0093 }
            float r3 = r3 / r2
            r5 = 1116733440(0x42900000, float:72.0)
            float r3 = r3 * r5
            r8.scaledHeight = r3     // Catch:{ all -> 0x0093 }
            float r3 = r8.scaledHeight     // Catch:{ all -> 0x0093 }
            r8.setTop(r3)     // Catch:{ all -> 0x0093 }
            int r4 = r4 - r1
            float r1 = (float) r4     // Catch:{ all -> 0x0093 }
            float r1 = r1 / r2
            float r1 = r1 * r5
            r8.scaledWidth = r1     // Catch:{ all -> 0x0093 }
            float r1 = r8.scaledWidth     // Catch:{ all -> 0x0093 }
            r8.setRight(r1)     // Catch:{ all -> 0x0093 }
            if (r0 == 0) goto L_0x0086
            r0.close()
        L_0x0086:
            float r0 = r8.getWidth()
            r8.plainWidth = r0
            float r0 = r8.getHeight()
            r8.plainHeight = r0
            return
        L_0x0093:
            r1 = move-exception
        L_0x0094:
            if (r0 == 0) goto L_0x0099
            r0.close()
        L_0x0099:
            float r0 = r8.getWidth()
            r8.plainWidth = r0
            float r0 = r8.getHeight()
            r8.plainHeight = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.ImgWMF.processParameters():void");
    }

    public void readWMF(PdfTemplate pdfTemplate) throws IOException, DocumentException {
        InputStream byteArrayInputStream;
        setTemplateData(pdfTemplate);
        pdfTemplate.setWidth(getWidth());
        pdfTemplate.setHeight(getHeight());
        InputStream inputStream = null;
        try {
            if (this.rawData == null) {
                byteArrayInputStream = this.url.openStream();
            } else {
                byteArrayInputStream = new ByteArrayInputStream(this.rawData);
            }
            inputStream = byteArrayInputStream;
            new MetaDo(inputStream, pdfTemplate).readAll();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
