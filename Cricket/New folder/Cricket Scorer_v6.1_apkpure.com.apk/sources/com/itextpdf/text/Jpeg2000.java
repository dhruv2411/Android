package com.itextpdf.text;

import com.itextpdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Jpeg2000 extends Image {
    public static final int JP2_BPCC = 1651532643;
    public static final int JP2_COLR = 1668246642;
    public static final int JP2_DBTL = 1685348972;
    public static final int JP2_FTYP = 1718909296;
    public static final int JP2_IHDR = 1768449138;
    public static final int JP2_JP = 1783636000;
    public static final int JP2_JP2 = 1785737760;
    public static final int JP2_JP2C = 1785737827;
    public static final int JP2_JP2H = 1785737832;
    public static final int JP2_URL = 1970433056;
    public static final int JPIP_JPIP = 1785751920;
    int boxLength;
    int boxType;
    byte[] bpcBoxData;
    ArrayList<ColorSpecBox> colorSpecBoxes;
    InputStream inp;
    boolean isJp2;
    int numOfComps;

    Jpeg2000(Image image) {
        super(image);
        this.colorSpecBoxes = null;
        this.isJp2 = false;
        if (image instanceof Jpeg2000) {
            Jpeg2000 jpeg2000 = (Jpeg2000) image;
            this.numOfComps = jpeg2000.numOfComps;
            if (this.colorSpecBoxes != null) {
                this.colorSpecBoxes = (ArrayList) jpeg2000.colorSpecBoxes.clone();
            }
            this.isJp2 = jpeg2000.isJp2;
            if (this.bpcBoxData != null) {
                this.bpcBoxData = (byte[]) jpeg2000.bpcBoxData.clone();
            }
        }
    }

    public Jpeg2000(URL url) throws BadElementException, IOException {
        super(url);
        this.colorSpecBoxes = null;
        this.isJp2 = false;
        processParameters();
    }

    public Jpeg2000(byte[] bArr) throws BadElementException, IOException {
        super((URL) null);
        this.colorSpecBoxes = null;
        this.isJp2 = false;
        this.rawData = bArr;
        this.originalData = bArr;
        processParameters();
    }

    public Jpeg2000(byte[] bArr, float f, float f2) throws BadElementException, IOException {
        this(bArr);
        this.scaledWidth = f;
        this.scaledHeight = f2;
    }

    private int cio_read(int i) throws IOException {
        int i2 = 0;
        for (int i3 = i - 1; i3 >= 0; i3--) {
            i2 += this.inp.read() << (i3 << 3);
        }
        return i2;
    }

    public void jp2_read_boxhdr() throws IOException {
        this.boxLength = cio_read(4);
        this.boxType = cio_read(4);
        if (this.boxLength == 1) {
            if (cio_read(4) != 0) {
                throw new IOException(MessageLocalization.getComposedMessage("cannot.handle.box.sizes.higher.than.2.32", new Object[0]));
            }
            this.boxLength = cio_read(4);
            if (this.boxLength == 0) {
                throw new IOException(MessageLocalization.getComposedMessage("unsupported.box.size.eq.eq.0", new Object[0]));
            }
        } else if (this.boxLength == 0) {
            throw new ZeroBoxSizeException(MessageLocalization.getComposedMessage("unsupported.box.size.eq.eq.0", new Object[0]));
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:39|(1:41)|42|43|44|45|46) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x0132 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processParameters() throws java.io.IOException {
        /*
            r9 = this;
            r0 = 33
            r9.type = r0
            r0 = 8
            r9.originalType = r0
            r1 = 0
            r9.inp = r1
            byte[] r2 = r9.rawData     // Catch:{ all -> 0x019a }
            if (r2 != 0) goto L_0x0018
            java.net.URL r2 = r9.url     // Catch:{ all -> 0x019a }
            java.io.InputStream r2 = r2.openStream()     // Catch:{ all -> 0x019a }
            r9.inp = r2     // Catch:{ all -> 0x019a }
            goto L_0x0021
        L_0x0018:
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x019a }
            byte[] r3 = r9.rawData     // Catch:{ all -> 0x019a }
            r2.<init>(r3)     // Catch:{ all -> 0x019a }
            r9.inp = r2     // Catch:{ all -> 0x019a }
        L_0x0021:
            r2 = 4
            int r3 = r9.cio_read(r2)     // Catch:{ all -> 0x019a }
            r9.boxLength = r3     // Catch:{ all -> 0x019a }
            int r3 = r9.boxLength     // Catch:{ all -> 0x019a }
            r4 = 12
            r5 = 2
            r6 = 0
            if (r3 != r4) goto L_0x0137
            r3 = 1
            r9.isJp2 = r3     // Catch:{ all -> 0x019a }
            int r4 = r9.cio_read(r2)     // Catch:{ all -> 0x019a }
            r9.boxType = r4     // Catch:{ all -> 0x019a }
            r4 = 1783636000(0x6a502020, float:6.290207E25)
            int r7 = r9.boxType     // Catch:{ all -> 0x019a }
            if (r4 == r7) goto L_0x004e
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x019a }
            java.lang.String r2 = "expected.jp.marker"
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ all -> 0x019a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x019a }
            r0.<init>(r2)     // Catch:{ all -> 0x019a }
            throw r0     // Catch:{ all -> 0x019a }
        L_0x004e:
            r4 = 218793738(0xd0a870a, float:4.268708E-31)
            int r7 = r9.cio_read(r2)     // Catch:{ all -> 0x019a }
            if (r4 == r7) goto L_0x0065
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x019a }
            java.lang.String r2 = "error.with.jp.marker"
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ all -> 0x019a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x019a }
            r0.<init>(r2)     // Catch:{ all -> 0x019a }
            throw r0     // Catch:{ all -> 0x019a }
        L_0x0065:
            r9.jp2_read_boxhdr()     // Catch:{ all -> 0x019a }
            r4 = 1718909296(0x66747970, float:2.8862439E23)
            int r7 = r9.boxType     // Catch:{ all -> 0x019a }
            if (r4 == r7) goto L_0x007d
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x019a }
            java.lang.String r2 = "expected.ftyp.marker"
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ all -> 0x019a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x019a }
            r0.<init>(r2)     // Catch:{ all -> 0x019a }
            throw r0     // Catch:{ all -> 0x019a }
        L_0x007d:
            java.io.InputStream r4 = r9.inp     // Catch:{ all -> 0x019a }
            int r7 = r9.boxLength     // Catch:{ all -> 0x019a }
            int r7 = r7 - r0
            com.itextpdf.text.Utilities.skip(r4, r7)     // Catch:{ all -> 0x019a }
            r9.jp2_read_boxhdr()     // Catch:{ all -> 0x019a }
        L_0x0088:
            int r4 = r9.boxType     // Catch:{ all -> 0x019a }
            r7 = 1785737832(0x6a703268, float:7.259506E25)
            if (r7 == r4) goto L_0x00af
            int r4 = r9.boxType     // Catch:{ all -> 0x019a }
            r8 = 1785737827(0x6a703263, float:7.2595035E25)
            if (r4 != r8) goto L_0x00a4
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x019a }
            java.lang.String r2 = "expected.jp2h.marker"
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ all -> 0x019a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x019a }
            r0.<init>(r2)     // Catch:{ all -> 0x019a }
            throw r0     // Catch:{ all -> 0x019a }
        L_0x00a4:
            java.io.InputStream r4 = r9.inp     // Catch:{ all -> 0x019a }
            int r8 = r9.boxLength     // Catch:{ all -> 0x019a }
            int r8 = r8 - r0
            com.itextpdf.text.Utilities.skip(r4, r8)     // Catch:{ all -> 0x019a }
            r9.jp2_read_boxhdr()     // Catch:{ all -> 0x019a }
        L_0x00af:
            int r4 = r9.boxType     // Catch:{ all -> 0x019a }
            if (r7 != r4) goto L_0x0088
            r9.jp2_read_boxhdr()     // Catch:{ all -> 0x019a }
            r4 = 1768449138(0x69686472, float:1.7559071E25)
            int r7 = r9.boxType     // Catch:{ all -> 0x019a }
            if (r4 == r7) goto L_0x00cb
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x019a }
            java.lang.String r2 = "expected.ihdr.marker"
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ all -> 0x019a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x019a }
            r0.<init>(r2)     // Catch:{ all -> 0x019a }
            throw r0     // Catch:{ all -> 0x019a }
        L_0x00cb:
            int r4 = r9.cio_read(r2)     // Catch:{ all -> 0x019a }
            float r4 = (float) r4     // Catch:{ all -> 0x019a }
            r9.scaledHeight = r4     // Catch:{ all -> 0x019a }
            float r4 = r9.scaledHeight     // Catch:{ all -> 0x019a }
            r9.setTop(r4)     // Catch:{ all -> 0x019a }
            int r2 = r9.cio_read(r2)     // Catch:{ all -> 0x019a }
            float r2 = (float) r2     // Catch:{ all -> 0x019a }
            r9.scaledWidth = r2     // Catch:{ all -> 0x019a }
            float r2 = r9.scaledWidth     // Catch:{ all -> 0x019a }
            r9.setRight(r2)     // Catch:{ all -> 0x019a }
            int r2 = r9.cio_read(r5)     // Catch:{ all -> 0x019a }
            r9.numOfComps = r2     // Catch:{ all -> 0x019a }
            r2 = -1
            r9.bpc = r2     // Catch:{ all -> 0x019a }
            int r2 = r9.cio_read(r3)     // Catch:{ all -> 0x019a }
            r9.bpc = r2     // Catch:{ all -> 0x019a }
            java.io.InputStream r2 = r9.inp     // Catch:{ all -> 0x019a }
            r3 = 3
            com.itextpdf.text.Utilities.skip(r2, r3)     // Catch:{ all -> 0x019a }
            r9.jp2_read_boxhdr()     // Catch:{ all -> 0x019a }
            int r2 = r9.boxType     // Catch:{ all -> 0x019a }
            r3 = 1651532643(0x62706363, float:1.10859504E21)
            if (r2 != r3) goto L_0x0114
            int r2 = r9.boxLength     // Catch:{ all -> 0x019a }
            int r2 = r2 - r0
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x019a }
            r9.bpcBoxData = r2     // Catch:{ all -> 0x019a }
            java.io.InputStream r2 = r9.inp     // Catch:{ all -> 0x019a }
            byte[] r3 = r9.bpcBoxData     // Catch:{ all -> 0x019a }
            int r4 = r9.boxLength     // Catch:{ all -> 0x019a }
            int r4 = r4 - r0
            r2.read(r3, r6, r4)     // Catch:{ all -> 0x019a }
            goto L_0x0174
        L_0x0114:
            int r0 = r9.boxType     // Catch:{ all -> 0x019a }
            r2 = 1668246642(0x636f6c72, float:4.4165861E21)
            if (r0 != r2) goto L_0x0174
        L_0x011b:
            java.util.ArrayList<com.itextpdf.text.Jpeg2000$ColorSpecBox> r0 = r9.colorSpecBoxes     // Catch:{ all -> 0x019a }
            if (r0 != 0) goto L_0x0126
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x019a }
            r0.<init>()     // Catch:{ all -> 0x019a }
            r9.colorSpecBoxes = r0     // Catch:{ all -> 0x019a }
        L_0x0126:
            java.util.ArrayList<com.itextpdf.text.Jpeg2000$ColorSpecBox> r0 = r9.colorSpecBoxes     // Catch:{ all -> 0x019a }
            com.itextpdf.text.Jpeg2000$ColorSpecBox r3 = r9.jp2_read_colr()     // Catch:{ all -> 0x019a }
            r0.add(r3)     // Catch:{ all -> 0x019a }
            r9.jp2_read_boxhdr()     // Catch:{ ZeroBoxSizeException -> 0x0132 }
        L_0x0132:
            int r0 = r9.boxType     // Catch:{ all -> 0x019a }
            if (r2 == r0) goto L_0x011b
            goto L_0x0174
        L_0x0137:
            int r3 = r9.boxLength     // Catch:{ all -> 0x019a }
            r4 = -11534511(0xffffffffff4fff51, float:-2.7647587E38)
            if (r3 != r4) goto L_0x018c
            java.io.InputStream r3 = r9.inp     // Catch:{ all -> 0x019a }
            com.itextpdf.text.Utilities.skip(r3, r2)     // Catch:{ all -> 0x019a }
            int r3 = r9.cio_read(r2)     // Catch:{ all -> 0x019a }
            int r4 = r9.cio_read(r2)     // Catch:{ all -> 0x019a }
            int r6 = r9.cio_read(r2)     // Catch:{ all -> 0x019a }
            int r2 = r9.cio_read(r2)     // Catch:{ all -> 0x019a }
            java.io.InputStream r7 = r9.inp     // Catch:{ all -> 0x019a }
            r8 = 16
            com.itextpdf.text.Utilities.skip(r7, r8)     // Catch:{ all -> 0x019a }
            int r5 = r9.cio_read(r5)     // Catch:{ all -> 0x019a }
            r9.colorspace = r5     // Catch:{ all -> 0x019a }
            r9.bpc = r0     // Catch:{ all -> 0x019a }
            int r4 = r4 - r2
            float r0 = (float) r4     // Catch:{ all -> 0x019a }
            r9.scaledHeight = r0     // Catch:{ all -> 0x019a }
            float r0 = r9.scaledHeight     // Catch:{ all -> 0x019a }
            r9.setTop(r0)     // Catch:{ all -> 0x019a }
            int r3 = r3 - r6
            float r0 = (float) r3     // Catch:{ all -> 0x019a }
            r9.scaledWidth = r0     // Catch:{ all -> 0x019a }
            float r0 = r9.scaledWidth     // Catch:{ all -> 0x019a }
            r9.setRight(r0)     // Catch:{ all -> 0x019a }
        L_0x0174:
            java.io.InputStream r0 = r9.inp
            if (r0 == 0) goto L_0x017f
            java.io.InputStream r0 = r9.inp     // Catch:{ Exception -> 0x017d }
            r0.close()     // Catch:{ Exception -> 0x017d }
        L_0x017d:
            r9.inp = r1
        L_0x017f:
            float r0 = r9.getWidth()
            r9.plainWidth = r0
            float r0 = r9.getHeight()
            r9.plainHeight = r0
            return
        L_0x018c:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x019a }
            java.lang.String r2 = "not.a.valid.jpeg2000.file"
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ all -> 0x019a }
            java.lang.String r2 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r2, (java.lang.Object[]) r3)     // Catch:{ all -> 0x019a }
            r0.<init>(r2)     // Catch:{ all -> 0x019a }
            throw r0     // Catch:{ all -> 0x019a }
        L_0x019a:
            r0 = move-exception
            java.io.InputStream r2 = r9.inp
            if (r2 == 0) goto L_0x01a6
            java.io.InputStream r2 = r9.inp     // Catch:{ Exception -> 0x01a4 }
            r2.close()     // Catch:{ Exception -> 0x01a4 }
        L_0x01a4:
            r9.inp = r1
        L_0x01a6:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.Jpeg2000.processParameters():void");
    }

    private ColorSpecBox jp2_read_colr() throws IOException {
        ColorSpecBox colorSpecBox = new ColorSpecBox();
        int i = 8;
        for (int i2 = 0; i2 < 3; i2++) {
            colorSpecBox.add(Integer.valueOf(cio_read(1)));
            i++;
        }
        if (colorSpecBox.getMeth() == 1) {
            colorSpecBox.add(Integer.valueOf(cio_read(4)));
            i += 4;
        } else {
            colorSpecBox.add(0);
        }
        if (this.boxLength - i > 0) {
            byte[] bArr = new byte[(this.boxLength - i)];
            this.inp.read(bArr, 0, this.boxLength - i);
            colorSpecBox.setColorProfile(bArr);
        }
        return colorSpecBox;
    }

    public int getNumOfComps() {
        return this.numOfComps;
    }

    public byte[] getBpcBoxData() {
        return this.bpcBoxData;
    }

    public ArrayList<ColorSpecBox> getColorSpecBoxes() {
        return this.colorSpecBoxes;
    }

    public boolean isJp2() {
        return this.isJp2;
    }

    public static class ColorSpecBox extends ArrayList<Integer> {
        private byte[] colorProfile;

        public int getMeth() {
            return ((Integer) get(0)).intValue();
        }

        public int getPrec() {
            return ((Integer) get(1)).intValue();
        }

        public int getApprox() {
            return ((Integer) get(2)).intValue();
        }

        public int getEnumCs() {
            return ((Integer) get(3)).intValue();
        }

        public byte[] getColorProfile() {
            return this.colorProfile;
        }

        /* access modifiers changed from: package-private */
        public void setColorProfile(byte[] bArr) {
            this.colorProfile = bArr;
        }
    }

    private class ZeroBoxSizeException extends IOException {
        public ZeroBoxSizeException() {
        }

        public ZeroBoxSizeException(String str) {
            super(str);
        }
    }
}
