package com.itextpdf.text;

import com.itextpdf.text.api.Indentable;
import com.itextpdf.text.api.Spaceable;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.ICC_Profile;
import com.itextpdf.text.pdf.PRIndirectReference;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfIndirectReference;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfOCG;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.codec.CCITTG4Encoder;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import com.itextpdf.text.pdf.interfaces.IAlternateDescription;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public abstract class Image extends Rectangle implements Indentable, Spaceable, IAccessibleElement, IAlternateDescription {
    public static final int AX = 0;
    public static final int AY = 1;
    public static final int BX = 2;
    public static final int BY = 3;
    public static final int CX = 4;
    public static final int CY = 5;
    public static final int DEFAULT = 0;
    public static final int DX = 6;
    public static final int DY = 7;
    public static final int LEFT = 0;
    public static final int MIDDLE = 1;
    public static final int ORIGINAL_BMP = 4;
    public static final int ORIGINAL_GIF = 3;
    public static final int ORIGINAL_JBIG2 = 9;
    public static final int ORIGINAL_JPEG = 1;
    public static final int ORIGINAL_JPEG2000 = 8;
    public static final int ORIGINAL_NONE = 0;
    public static final int ORIGINAL_PNG = 2;
    public static final int ORIGINAL_PS = 7;
    public static final int ORIGINAL_TIFF = 5;
    public static final int ORIGINAL_WMF = 6;
    public static final int RIGHT = 2;
    public static final int TEXTWRAP = 4;
    public static final int UNDERLYING = 8;
    static long serialId;
    private float XYRatio = 0.0f;
    protected float absoluteX = Float.NaN;
    protected float absoluteY = Float.NaN;
    protected HashMap<PdfName, PdfObject> accessibleAttributes = null;
    private PdfDictionary additional = null;
    protected int alignment;
    protected String alt;
    protected Annotation annotation = null;
    protected int bpc = 1;
    protected int colorspace = -1;
    protected int colortransform = 1;
    protected int compressionLevel = -1;
    protected boolean deflated = false;
    private PdfIndirectReference directReference;
    protected int dpiX = 0;
    protected int dpiY = 0;
    private AccessibleElementId id = null;
    protected Image imageMask;
    protected float indentationLeft = 0.0f;
    protected float indentationRight = 0.0f;
    private float initialRotation;
    protected boolean interpolation;
    protected boolean invert = false;
    protected PdfOCG layer;
    protected boolean mask = false;
    protected Long mySerialId = getSerialId();
    protected byte[] originalData;
    protected int originalType = 0;
    protected float paddingTop;
    protected float plainHeight;
    protected float plainWidth;
    protected ICC_Profile profile = null;
    protected byte[] rawData;
    protected PdfName role = PdfName.FIGURE;
    protected float rotationRadians;
    protected boolean scaleToFitHeight = true;
    protected boolean scaleToFitLineWhenOverflow;
    protected float scaledHeight;
    protected float scaledWidth;
    private boolean smask;
    protected float spacingAfter;
    protected float spacingBefore;
    protected PdfTemplate[] template = new PdfTemplate[1];
    protected int[] transparency;
    protected int type;
    protected URL url;
    private float widthPercentage = 100.0f;

    public boolean isInline() {
        return true;
    }

    public boolean isNestable() {
        return true;
    }

    public Image(URL url2) {
        super(0.0f, 0.0f);
        this.url = url2;
        this.alignment = 0;
        this.rotationRadians = 0.0f;
    }

    public static Image getInstance(URL url2) throws BadElementException, MalformedURLException, IOException {
        return getInstance(url2, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:128:0x0176 A[Catch:{ all -> 0x016e, all -> 0x0192 }] */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0106 A[SYNTHETIC, Splitter:B:82:0x0106] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0115 A[SYNTHETIC, Splitter:B:90:0x0115] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.itextpdf.text.Image getInstance(java.net.URL r18, boolean r19) throws com.itextpdf.text.BadElementException, java.net.MalformedURLException, java.io.IOException {
        /*
            r1 = r18
            r2 = r19
            com.itextpdf.text.io.RandomAccessSourceFactory r3 = new com.itextpdf.text.io.RandomAccessSourceFactory
            r3.<init>()
            java.io.InputStream r5 = r18.openStream()     // Catch:{ all -> 0x0192 }
            int r6 = r5.read()     // Catch:{ all -> 0x018f }
            int r7 = r5.read()     // Catch:{ all -> 0x018f }
            int r8 = r5.read()     // Catch:{ all -> 0x018f }
            int r9 = r5.read()     // Catch:{ all -> 0x018f }
            int r10 = r5.read()     // Catch:{ all -> 0x018f }
            int r11 = r5.read()     // Catch:{ all -> 0x018f }
            int r12 = r5.read()     // Catch:{ all -> 0x018f }
            int r13 = r5.read()     // Catch:{ all -> 0x018f }
            r5.close()     // Catch:{ all -> 0x018f }
            r5 = 71
            r14 = 73
            r15 = 1
            if (r6 != r5) goto L_0x0047
            if (r7 != r14) goto L_0x0047
            r5 = 70
            if (r8 != r5) goto L_0x0047
            com.itextpdf.text.pdf.codec.GifImage r2 = new com.itextpdf.text.pdf.codec.GifImage     // Catch:{ all -> 0x0192 }
            r2.<init>((java.net.URL) r1)     // Catch:{ all -> 0x0192 }
            com.itextpdf.text.Image r1 = r2.getImage(r15)     // Catch:{ all -> 0x0192 }
            return r1
        L_0x0047:
            r5 = 255(0xff, float:3.57E-43)
            if (r6 != r5) goto L_0x0055
            r4 = 216(0xd8, float:3.03E-43)
            if (r7 != r4) goto L_0x0055
            com.itextpdf.text.Jpeg r2 = new com.itextpdf.text.Jpeg     // Catch:{ all -> 0x0192 }
            r2.<init>((java.net.URL) r1)     // Catch:{ all -> 0x0192 }
            return r2
        L_0x0055:
            if (r6 != 0) goto L_0x0065
            if (r7 != 0) goto L_0x0065
            if (r8 != 0) goto L_0x0065
            r4 = 12
            if (r9 != r4) goto L_0x0065
            com.itextpdf.text.Jpeg2000 r2 = new com.itextpdf.text.Jpeg2000     // Catch:{ all -> 0x0192 }
            r2.<init>((java.net.URL) r1)     // Catch:{ all -> 0x0192 }
            return r2
        L_0x0065:
            if (r6 != r5) goto L_0x0077
            r4 = 79
            if (r7 != r4) goto L_0x0077
            if (r8 != r5) goto L_0x0077
            r4 = 81
            if (r9 != r4) goto L_0x0077
            com.itextpdf.text.Jpeg2000 r2 = new com.itextpdf.text.Jpeg2000     // Catch:{ all -> 0x0192 }
            r2.<init>((java.net.URL) r1)     // Catch:{ all -> 0x0192 }
            return r2
        L_0x0077:
            int[] r4 = com.itextpdf.text.pdf.codec.PngImage.PNGID     // Catch:{ all -> 0x0192 }
            r5 = 0
            r4 = r4[r5]     // Catch:{ all -> 0x0192 }
            if (r6 != r4) goto L_0x0099
            int[] r4 = com.itextpdf.text.pdf.codec.PngImage.PNGID     // Catch:{ all -> 0x0192 }
            r4 = r4[r15]     // Catch:{ all -> 0x0192 }
            if (r7 != r4) goto L_0x0099
            int[] r4 = com.itextpdf.text.pdf.codec.PngImage.PNGID     // Catch:{ all -> 0x0192 }
            r16 = 2
            r4 = r4[r16]     // Catch:{ all -> 0x0192 }
            if (r8 != r4) goto L_0x0099
            int[] r4 = com.itextpdf.text.pdf.codec.PngImage.PNGID     // Catch:{ all -> 0x0192 }
            r16 = 3
            r4 = r4[r16]     // Catch:{ all -> 0x0192 }
            if (r9 != r4) goto L_0x0099
            com.itextpdf.text.Image r1 = com.itextpdf.text.pdf.codec.PngImage.getImage((java.net.URL) r18)     // Catch:{ all -> 0x0192 }
            return r1
        L_0x0099:
            r4 = 215(0xd7, float:3.01E-43)
            if (r6 != r4) goto L_0x00a7
            r4 = 205(0xcd, float:2.87E-43)
            if (r7 != r4) goto L_0x00a7
            com.itextpdf.text.ImgWMF r2 = new com.itextpdf.text.ImgWMF     // Catch:{ all -> 0x0192 }
            r2.<init>((java.net.URL) r1)     // Catch:{ all -> 0x0192 }
            return r2
        L_0x00a7:
            r4 = 66
            r5 = 77
            if (r6 != r4) goto L_0x00b4
            if (r7 != r5) goto L_0x00b4
            com.itextpdf.text.Image r1 = com.itextpdf.text.pdf.codec.BmpImage.getImage((java.net.URL) r18)     // Catch:{ all -> 0x0192 }
            return r1
        L_0x00b4:
            r4 = 42
            if (r6 != r5) goto L_0x00be
            if (r7 != r5) goto L_0x00be
            if (r8 != 0) goto L_0x00be
            if (r9 == r4) goto L_0x00c6
        L_0x00be:
            if (r6 != r14) goto L_0x011c
            if (r7 != r14) goto L_0x011c
            if (r8 != r4) goto L_0x011c
            if (r9 != 0) goto L_0x011c
        L_0x00c6:
            java.lang.String r4 = r18.getProtocol()     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
            java.lang.String r5 = "file"
            boolean r4 = r4.equals(r5)     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
            if (r4 == 0) goto L_0x00e5
            java.lang.String r4 = r18.getFile()     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
            java.lang.String r4 = com.itextpdf.text.Utilities.unEscapeURL(r4)     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
            com.itextpdf.text.pdf.RandomAccessFileOrArray r5 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
            com.itextpdf.text.io.RandomAccessSource r3 = r3.createBestSource((java.lang.String) r4)     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
            r5.<init>((com.itextpdf.text.io.RandomAccessSource) r3)     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
            r4 = r5
            goto L_0x00ee
        L_0x00e5:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r4 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
            com.itextpdf.text.io.RandomAccessSource r3 = r3.createSource((java.net.URL) r1)     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
            r4.<init>((com.itextpdf.text.io.RandomAccessSource) r3)     // Catch:{ RuntimeException -> 0x0101, all -> 0x00fd }
        L_0x00ee:
            com.itextpdf.text.Image r3 = com.itextpdf.text.pdf.codec.TiffImage.getTiffImage(r4, r15)     // Catch:{ RuntimeException -> 0x00fa }
            r3.url = r1     // Catch:{ RuntimeException -> 0x00fa }
            if (r4 == 0) goto L_0x00f9
            r4.close()     // Catch:{ all -> 0x0192 }
        L_0x00f9:
            return r3
        L_0x00fa:
            r0 = move-exception
            r3 = r0
            goto L_0x0104
        L_0x00fd:
            r0 = move-exception
            r1 = r0
            r4 = 0
            goto L_0x0116
        L_0x0101:
            r0 = move-exception
            r3 = r0
            r4 = 0
        L_0x0104:
            if (r2 == 0) goto L_0x0115
            com.itextpdf.text.Image r2 = com.itextpdf.text.pdf.codec.TiffImage.getTiffImage((com.itextpdf.text.pdf.RandomAccessFileOrArray) r4, (boolean) r2, (int) r15)     // Catch:{ all -> 0x0112 }
            r2.url = r1     // Catch:{ all -> 0x0112 }
            if (r4 == 0) goto L_0x0111
            r4.close()     // Catch:{ all -> 0x0192 }
        L_0x0111:
            return r2
        L_0x0112:
            r0 = move-exception
            r1 = r0
            goto L_0x0116
        L_0x0115:
            throw r3     // Catch:{ all -> 0x0112 }
        L_0x0116:
            if (r4 == 0) goto L_0x011b
            r4.close()     // Catch:{ all -> 0x0192 }
        L_0x011b:
            throw r1     // Catch:{ all -> 0x0192 }
        L_0x011c:
            r2 = 151(0x97, float:2.12E-43)
            if (r6 != r2) goto L_0x017a
            r2 = 74
            if (r7 != r2) goto L_0x017a
            r2 = 66
            if (r8 != r2) goto L_0x017a
            r2 = 50
            if (r9 != r2) goto L_0x017a
            r2 = 13
            if (r10 != r2) goto L_0x017a
            r2 = 10
            if (r11 != r2) goto L_0x017a
            r4 = 26
            if (r12 != r4) goto L_0x017a
            if (r13 != r2) goto L_0x017a
            java.lang.String r2 = r18.getProtocol()     // Catch:{ all -> 0x0171 }
            java.lang.String r4 = "file"
            boolean r2 = r2.equals(r4)     // Catch:{ all -> 0x0171 }
            if (r2 == 0) goto L_0x0158
            java.lang.String r2 = r18.getFile()     // Catch:{ all -> 0x0171 }
            java.lang.String r2 = com.itextpdf.text.Utilities.unEscapeURL(r2)     // Catch:{ all -> 0x0171 }
            com.itextpdf.text.pdf.RandomAccessFileOrArray r4 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x0171 }
            com.itextpdf.text.io.RandomAccessSource r2 = r3.createBestSource((java.lang.String) r2)     // Catch:{ all -> 0x0171 }
            r4.<init>((com.itextpdf.text.io.RandomAccessSource) r2)     // Catch:{ all -> 0x0171 }
            goto L_0x0162
        L_0x0158:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r2 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x0171 }
            com.itextpdf.text.io.RandomAccessSource r3 = r3.createSource((java.net.URL) r1)     // Catch:{ all -> 0x0171 }
            r2.<init>((com.itextpdf.text.io.RandomAccessSource) r3)     // Catch:{ all -> 0x0171 }
            r4 = r2
        L_0x0162:
            com.itextpdf.text.Image r2 = com.itextpdf.text.pdf.codec.JBIG2Image.getJbig2Image(r4, r15)     // Catch:{ all -> 0x016e }
            r2.url = r1     // Catch:{ all -> 0x016e }
            if (r4 == 0) goto L_0x016d
            r4.close()     // Catch:{ all -> 0x0192 }
        L_0x016d:
            return r2
        L_0x016e:
            r0 = move-exception
            r1 = r0
            goto L_0x0174
        L_0x0171:
            r0 = move-exception
            r1 = r0
            r4 = 0
        L_0x0174:
            if (r4 == 0) goto L_0x0179
            r4.close()     // Catch:{ all -> 0x0192 }
        L_0x0179:
            throw r1     // Catch:{ all -> 0x0192 }
        L_0x017a:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0192 }
            java.lang.String r3 = "unknown.image.format"
            java.lang.Object[] r4 = new java.lang.Object[r15]     // Catch:{ all -> 0x0192 }
            java.lang.String r1 = r18.toString()     // Catch:{ all -> 0x0192 }
            r5 = 0
            r4[r5] = r1     // Catch:{ all -> 0x0192 }
            java.lang.String r1 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x0192 }
            r2.<init>(r1)     // Catch:{ all -> 0x0192 }
            throw r2     // Catch:{ all -> 0x0192 }
        L_0x018f:
            r0 = move-exception
            r1 = r0
            goto L_0x0195
        L_0x0192:
            r0 = move-exception
            r1 = r0
            r5 = 0
        L_0x0195:
            if (r5 == 0) goto L_0x019a
            r5.close()
        L_0x019a:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.Image.getInstance(java.net.URL, boolean):com.itextpdf.text.Image");
    }

    public static Image getInstance(String str) throws BadElementException, MalformedURLException, IOException {
        return getInstance(Utilities.toURL(str));
    }

    public static Image getInstance(String str, boolean z) throws IOException, BadElementException {
        return getInstance(Utilities.toURL(str), z);
    }

    public static Image getInstance(byte[] bArr) throws BadElementException, MalformedURLException, IOException {
        return getInstance(bArr, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.itextpdf.text.pdf.RandomAccessFileOrArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.ByteArrayInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.itextpdf.text.pdf.RandomAccessFileOrArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.io.ByteArrayInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.itextpdf.text.pdf.RandomAccessFileOrArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.io.ByteArrayInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.io.ByteArrayInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: com.itextpdf.text.pdf.RandomAccessFileOrArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.itextpdf.text.pdf.RandomAccessFileOrArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.itextpdf.text.pdf.RandomAccessFileOrArray} */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0153 A[SYNTHETIC, Splitter:B:129:0x0153] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x00d7 A[SYNTHETIC, Splitter:B:80:0x00d7] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x00ec A[SYNTHETIC, Splitter:B:89:0x00ec] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.itextpdf.text.Image getInstance(byte[] r12, boolean r13) throws com.itextpdf.text.BadElementException, java.net.MalformedURLException, java.io.IOException {
        /*
            com.itextpdf.text.io.RandomAccessSourceFactory r0 = new com.itextpdf.text.io.RandomAccessSourceFactory
            r0.<init>()
            r1 = 0
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x016d }
            r2.<init>(r12)     // Catch:{ all -> 0x016d }
            int r3 = r2.read()     // Catch:{ all -> 0x016a }
            int r4 = r2.read()     // Catch:{ all -> 0x016a }
            int r5 = r2.read()     // Catch:{ all -> 0x016a }
            int r6 = r2.read()     // Catch:{ all -> 0x016a }
            r2.close()     // Catch:{ all -> 0x016a }
            r2 = 71
            r7 = 73
            r8 = 1
            if (r3 != r2) goto L_0x0035
            if (r4 != r7) goto L_0x0035
            r2 = 70
            if (r5 != r2) goto L_0x0035
            com.itextpdf.text.pdf.codec.GifImage r13 = new com.itextpdf.text.pdf.codec.GifImage     // Catch:{ all -> 0x016d }
            r13.<init>((byte[]) r12)     // Catch:{ all -> 0x016d }
            com.itextpdf.text.Image r12 = r13.getImage(r8)     // Catch:{ all -> 0x016d }
            return r12
        L_0x0035:
            r2 = 255(0xff, float:3.57E-43)
            if (r3 != r2) goto L_0x0043
            r9 = 216(0xd8, float:3.03E-43)
            if (r4 != r9) goto L_0x0043
            com.itextpdf.text.Jpeg r13 = new com.itextpdf.text.Jpeg     // Catch:{ all -> 0x016d }
            r13.<init>((byte[]) r12)     // Catch:{ all -> 0x016d }
            return r13
        L_0x0043:
            if (r3 != 0) goto L_0x0053
            if (r4 != 0) goto L_0x0053
            if (r5 != 0) goto L_0x0053
            r9 = 12
            if (r6 != r9) goto L_0x0053
            com.itextpdf.text.Jpeg2000 r13 = new com.itextpdf.text.Jpeg2000     // Catch:{ all -> 0x016d }
            r13.<init>((byte[]) r12)     // Catch:{ all -> 0x016d }
            return r13
        L_0x0053:
            if (r3 != r2) goto L_0x0065
            r9 = 79
            if (r4 != r9) goto L_0x0065
            if (r5 != r2) goto L_0x0065
            r2 = 81
            if (r6 != r2) goto L_0x0065
            com.itextpdf.text.Jpeg2000 r13 = new com.itextpdf.text.Jpeg2000     // Catch:{ all -> 0x016d }
            r13.<init>((byte[]) r12)     // Catch:{ all -> 0x016d }
            return r13
        L_0x0065:
            int[] r2 = com.itextpdf.text.pdf.codec.PngImage.PNGID     // Catch:{ all -> 0x016d }
            r9 = 0
            r2 = r2[r9]     // Catch:{ all -> 0x016d }
            if (r3 != r2) goto L_0x0085
            int[] r2 = com.itextpdf.text.pdf.codec.PngImage.PNGID     // Catch:{ all -> 0x016d }
            r2 = r2[r8]     // Catch:{ all -> 0x016d }
            if (r4 != r2) goto L_0x0085
            int[] r2 = com.itextpdf.text.pdf.codec.PngImage.PNGID     // Catch:{ all -> 0x016d }
            r10 = 2
            r2 = r2[r10]     // Catch:{ all -> 0x016d }
            if (r5 != r2) goto L_0x0085
            int[] r2 = com.itextpdf.text.pdf.codec.PngImage.PNGID     // Catch:{ all -> 0x016d }
            r10 = 3
            r2 = r2[r10]     // Catch:{ all -> 0x016d }
            if (r6 != r2) goto L_0x0085
            com.itextpdf.text.Image r12 = com.itextpdf.text.pdf.codec.PngImage.getImage((byte[]) r12)     // Catch:{ all -> 0x016d }
            return r12
        L_0x0085:
            r2 = 215(0xd7, float:3.01E-43)
            if (r3 != r2) goto L_0x0093
            r2 = 205(0xcd, float:2.87E-43)
            if (r4 != r2) goto L_0x0093
            com.itextpdf.text.ImgWMF r13 = new com.itextpdf.text.ImgWMF     // Catch:{ all -> 0x016d }
            r13.<init>((byte[]) r12)     // Catch:{ all -> 0x016d }
            return r13
        L_0x0093:
            r2 = 66
            r10 = 77
            if (r3 != r2) goto L_0x00a0
            if (r4 != r10) goto L_0x00a0
            com.itextpdf.text.Image r12 = com.itextpdf.text.pdf.codec.BmpImage.getImage((byte[]) r12)     // Catch:{ all -> 0x016d }
            return r12
        L_0x00a0:
            r11 = 42
            if (r3 != r10) goto L_0x00aa
            if (r4 != r10) goto L_0x00aa
            if (r5 != 0) goto L_0x00aa
            if (r6 == r11) goto L_0x00b2
        L_0x00aa:
            if (r3 != r7) goto L_0x00f3
            if (r4 != r7) goto L_0x00f3
            if (r5 != r11) goto L_0x00f3
            if (r6 != 0) goto L_0x00f3
        L_0x00b2:
            com.itextpdf.text.pdf.RandomAccessFileOrArray r2 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ RuntimeException -> 0x00d3, all -> 0x00d0 }
            com.itextpdf.text.io.RandomAccessSource r0 = r0.createSource((byte[]) r12)     // Catch:{ RuntimeException -> 0x00d3, all -> 0x00d0 }
            r2.<init>((com.itextpdf.text.io.RandomAccessSource) r0)     // Catch:{ RuntimeException -> 0x00d3, all -> 0x00d0 }
            com.itextpdf.text.Image r0 = com.itextpdf.text.pdf.codec.TiffImage.getTiffImage(r2, r8)     // Catch:{ RuntimeException -> 0x00ce }
            byte[] r3 = r0.getOriginalData()     // Catch:{ RuntimeException -> 0x00ce }
            if (r3 != 0) goto L_0x00c8
            r0.setOriginalData(r12)     // Catch:{ RuntimeException -> 0x00ce }
        L_0x00c8:
            if (r2 == 0) goto L_0x00cd
            r2.close()     // Catch:{ all -> 0x016d }
        L_0x00cd:
            return r0
        L_0x00ce:
            r0 = move-exception
            goto L_0x00d5
        L_0x00d0:
            r12 = move-exception
            r2 = r1
            goto L_0x00ed
        L_0x00d3:
            r0 = move-exception
            r2 = r1
        L_0x00d5:
            if (r13 == 0) goto L_0x00ec
            com.itextpdf.text.Image r13 = com.itextpdf.text.pdf.codec.TiffImage.getTiffImage((com.itextpdf.text.pdf.RandomAccessFileOrArray) r2, (boolean) r13, (int) r8)     // Catch:{ all -> 0x00ea }
            byte[] r0 = r13.getOriginalData()     // Catch:{ all -> 0x00ea }
            if (r0 != 0) goto L_0x00e4
            r13.setOriginalData(r12)     // Catch:{ all -> 0x00ea }
        L_0x00e4:
            if (r2 == 0) goto L_0x00e9
            r2.close()     // Catch:{ all -> 0x016d }
        L_0x00e9:
            return r13
        L_0x00ea:
            r12 = move-exception
            goto L_0x00ed
        L_0x00ec:
            throw r0     // Catch:{ all -> 0x00ea }
        L_0x00ed:
            if (r2 == 0) goto L_0x00f2
            r2.close()     // Catch:{ all -> 0x016d }
        L_0x00f2:
            throw r12     // Catch:{ all -> 0x016d }
        L_0x00f3:
            r13 = 151(0x97, float:2.12E-43)
            if (r3 != r13) goto L_0x015c
            r13 = 74
            if (r4 != r13) goto L_0x015c
            if (r5 != r2) goto L_0x015c
            r13 = 50
            if (r6 != r13) goto L_0x015c
            java.io.ByteArrayInputStream r13 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x016d }
            r13.<init>(r12)     // Catch:{ all -> 0x016d }
            r2 = 4
            r13.skip(r2)     // Catch:{ all -> 0x0159 }
            int r2 = r13.read()     // Catch:{ all -> 0x0159 }
            int r3 = r13.read()     // Catch:{ all -> 0x0159 }
            int r4 = r13.read()     // Catch:{ all -> 0x0159 }
            int r5 = r13.read()     // Catch:{ all -> 0x0159 }
            r13.close()     // Catch:{ all -> 0x0159 }
            r6 = 13
            if (r2 != r6) goto L_0x0157
            r2 = 10
            if (r3 != r2) goto L_0x0157
            r3 = 26
            if (r4 != r3) goto L_0x0157
            if (r5 != r2) goto L_0x0157
            com.itextpdf.text.pdf.RandomAccessFileOrArray r2 = new com.itextpdf.text.pdf.RandomAccessFileOrArray     // Catch:{ all -> 0x0150 }
            com.itextpdf.text.io.RandomAccessSource r0 = r0.createSource((byte[]) r12)     // Catch:{ all -> 0x0150 }
            r2.<init>((com.itextpdf.text.io.RandomAccessSource) r0)     // Catch:{ all -> 0x0150 }
            com.itextpdf.text.Image r0 = com.itextpdf.text.pdf.codec.JBIG2Image.getJbig2Image(r2, r8)     // Catch:{ all -> 0x014d }
            byte[] r1 = r0.getOriginalData()     // Catch:{ all -> 0x014d }
            if (r1 != 0) goto L_0x0142
            r0.setOriginalData(r12)     // Catch:{ all -> 0x014d }
        L_0x0142:
            if (r2 == 0) goto L_0x0147
            r2.close()     // Catch:{ all -> 0x0159 }
        L_0x0147:
            if (r13 == 0) goto L_0x014c
            r13.close()
        L_0x014c:
            return r0
        L_0x014d:
            r12 = move-exception
            r1 = r2
            goto L_0x0151
        L_0x0150:
            r12 = move-exception
        L_0x0151:
            if (r1 == 0) goto L_0x0156
            r1.close()     // Catch:{ all -> 0x0159 }
        L_0x0156:
            throw r12     // Catch:{ all -> 0x0159 }
        L_0x0157:
            r1 = r13
            goto L_0x015c
        L_0x0159:
            r12 = move-exception
            r1 = r13
            goto L_0x016e
        L_0x015c:
            java.io.IOException r12 = new java.io.IOException     // Catch:{ all -> 0x016d }
            java.lang.String r13 = "the.byte.array.is.not.a.recognized.imageformat"
            java.lang.Object[] r0 = new java.lang.Object[r9]     // Catch:{ all -> 0x016d }
            java.lang.String r13 = com.itextpdf.text.error_messages.MessageLocalization.getComposedMessage((java.lang.String) r13, (java.lang.Object[]) r0)     // Catch:{ all -> 0x016d }
            r12.<init>(r13)     // Catch:{ all -> 0x016d }
            throw r12     // Catch:{ all -> 0x016d }
        L_0x016a:
            r12 = move-exception
            r1 = r2
            goto L_0x016e
        L_0x016d:
            r12 = move-exception
        L_0x016e:
            if (r1 == 0) goto L_0x0173
            r1.close()
        L_0x0173:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.Image.getInstance(byte[], boolean):com.itextpdf.text.Image");
    }

    public static Image getInstance(int i, int i2, int i3, int i4, byte[] bArr) throws BadElementException {
        return getInstance(i, i2, i3, i4, bArr, (int[]) null);
    }

    public static Image getInstance(int i, int i2, byte[] bArr, byte[] bArr2) {
        return new ImgJBIG2(i, i2, bArr, bArr2);
    }

    public static Image getInstance(int i, int i2, boolean z, int i3, int i4, byte[] bArr) throws BadElementException {
        return getInstance(i, i2, z, i3, i4, bArr, (int[]) null);
    }

    public static Image getInstance(int i, int i2, boolean z, int i3, int i4, byte[] bArr, int[] iArr) throws BadElementException {
        if (iArr == null || iArr.length == 2) {
            ImgCCITT imgCCITT = new ImgCCITT(i, i2, z, i3, i4, bArr);
            imgCCITT.transparency = iArr;
            return imgCCITT;
        }
        throw new BadElementException(MessageLocalization.getComposedMessage("transparency.length.must.be.equal.to.2.with.ccitt.images", new Object[0]));
    }

    public static Image getInstance(int i, int i2, int i3, int i4, byte[] bArr, int[] iArr) throws BadElementException {
        if (iArr != null && iArr.length != i3 * 2) {
            throw new BadElementException(MessageLocalization.getComposedMessage("transparency.length.must.be.equal.to.componentes.2", new Object[0]));
        } else if (i3 == 1 && i4 == 1) {
            return getInstance(i, i2, false, 256, 1, CCITTG4Encoder.compress(bArr, i, i2), iArr);
        } else {
            ImgRaw imgRaw = new ImgRaw(i, i2, i3, i4, bArr);
            imgRaw.transparency = iArr;
            return imgRaw;
        }
    }

    public static Image getInstance(PdfTemplate pdfTemplate) throws BadElementException {
        return new ImgTemplate(pdfTemplate);
    }

    public PdfIndirectReference getDirectReference() {
        return this.directReference;
    }

    public void setDirectReference(PdfIndirectReference pdfIndirectReference) {
        this.directReference = pdfIndirectReference;
    }

    public static Image getInstance(PRIndirectReference pRIndirectReference) throws BadElementException {
        Image image;
        PdfDictionary pdfDictionary = (PdfDictionary) PdfReader.getPdfObjectRelease((PdfObject) pRIndirectReference);
        int intValue = ((PdfNumber) PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.WIDTH))).intValue();
        int intValue2 = ((PdfNumber) PdfReader.getPdfObjectRelease(pdfDictionary.get(PdfName.HEIGHT))).intValue();
        PdfObject pdfObject = pdfDictionary.get(PdfName.SMASK);
        if (pdfObject == null || !pdfObject.isIndirect()) {
            PdfObject pdfObject2 = pdfDictionary.get(PdfName.MASK);
            image = (pdfObject2 == null || !pdfObject2.isIndirect() || !(PdfReader.getPdfObjectRelease(pdfObject2) instanceof PdfDictionary)) ? null : getInstance((PRIndirectReference) pdfObject2);
        } else {
            image = getInstance((PRIndirectReference) pdfObject);
        }
        ImgRaw imgRaw = new ImgRaw(intValue, intValue2, 1, 1, (byte[]) null);
        imgRaw.imageMask = image;
        imgRaw.directReference = pRIndirectReference;
        return imgRaw;
    }

    protected Image(Image image) {
        super((Rectangle) image);
        this.type = image.type;
        this.url = image.url;
        this.rawData = image.rawData;
        this.bpc = image.bpc;
        this.template = image.template;
        this.alignment = image.alignment;
        this.alt = image.alt;
        this.absoluteX = image.absoluteX;
        this.absoluteY = image.absoluteY;
        this.plainWidth = image.plainWidth;
        this.plainHeight = image.plainHeight;
        this.scaledWidth = image.scaledWidth;
        this.scaledHeight = image.scaledHeight;
        this.mySerialId = image.mySerialId;
        this.directReference = image.directReference;
        this.rotationRadians = image.rotationRadians;
        this.initialRotation = image.initialRotation;
        this.indentationLeft = image.indentationLeft;
        this.indentationRight = image.indentationRight;
        this.spacingBefore = image.spacingBefore;
        this.spacingAfter = image.spacingAfter;
        this.widthPercentage = image.widthPercentage;
        this.scaleToFitLineWhenOverflow = image.scaleToFitLineWhenOverflow;
        this.scaleToFitHeight = image.scaleToFitHeight;
        this.annotation = image.annotation;
        this.layer = image.layer;
        this.interpolation = image.interpolation;
        this.originalType = image.originalType;
        this.originalData = image.originalData;
        this.deflated = image.deflated;
        this.dpiX = image.dpiX;
        this.dpiY = image.dpiY;
        this.XYRatio = image.XYRatio;
        this.colorspace = image.colorspace;
        this.invert = image.invert;
        this.profile = image.profile;
        this.additional = image.additional;
        this.mask = image.mask;
        this.imageMask = image.imageMask;
        this.smask = image.smask;
        this.transparency = image.transparency;
        this.role = image.role;
        if (image.accessibleAttributes != null) {
            this.accessibleAttributes = new HashMap<>(image.accessibleAttributes);
        }
        setId(image.getId());
    }

    public static Image getInstance(Image image) {
        if (image == null) {
            return null;
        }
        try {
            return (Image) image.getClass().getDeclaredConstructor(new Class[]{Image.class}).newInstance(new Object[]{image});
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public int type() {
        return this.type;
    }

    public boolean isJpeg() {
        return this.type == 32;
    }

    public boolean isImgRaw() {
        return this.type == 34;
    }

    public boolean isImgTemplate() {
        return this.type == 35;
    }

    public URL getUrl() {
        return this.url;
    }

    public void setUrl(URL url2) {
        this.url = url2;
    }

    public byte[] getRawData() {
        return this.rawData;
    }

    public int getBpc() {
        return this.bpc;
    }

    public PdfTemplate getTemplateData() {
        return this.template[0];
    }

    public void setTemplateData(PdfTemplate pdfTemplate) {
        this.template[0] = pdfTemplate;
    }

    public int getAlignment() {
        return this.alignment;
    }

    public void setAlignment(int i) {
        this.alignment = i;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String str) {
        this.alt = str;
        setAccessibleAttribute(PdfName.ALT, new PdfString(str));
    }

    public void setAbsolutePosition(float f, float f2) {
        this.absoluteX = f;
        this.absoluteY = f2;
    }

    public boolean hasAbsoluteX() {
        return !Float.isNaN(this.absoluteX);
    }

    public float getAbsoluteX() {
        return this.absoluteX;
    }

    public boolean hasAbsoluteY() {
        return !Float.isNaN(this.absoluteY);
    }

    public float getAbsoluteY() {
        return this.absoluteY;
    }

    public float getScaledWidth() {
        return this.scaledWidth;
    }

    public float getScaledHeight() {
        return this.scaledHeight;
    }

    public float getPlainWidth() {
        return this.plainWidth;
    }

    public float getPlainHeight() {
        return this.plainHeight;
    }

    public void scaleAbsolute(Rectangle rectangle) {
        scaleAbsolute(rectangle.getWidth(), rectangle.getHeight());
    }

    public void scaleAbsolute(float f, float f2) {
        this.plainWidth = f;
        this.plainHeight = f2;
        float[] matrix = matrix();
        this.scaledWidth = matrix[6] - matrix[4];
        this.scaledHeight = matrix[7] - matrix[5];
        setWidthPercentage(0.0f);
    }

    public void scaleAbsoluteWidth(float f) {
        this.plainWidth = f;
        float[] matrix = matrix();
        this.scaledWidth = matrix[6] - matrix[4];
        this.scaledHeight = matrix[7] - matrix[5];
        setWidthPercentage(0.0f);
    }

    public void scaleAbsoluteHeight(float f) {
        this.plainHeight = f;
        float[] matrix = matrix();
        this.scaledWidth = matrix[6] - matrix[4];
        this.scaledHeight = matrix[7] - matrix[5];
        setWidthPercentage(0.0f);
    }

    public void scalePercent(float f) {
        scalePercent(f, f);
    }

    public void scalePercent(float f, float f2) {
        this.plainWidth = (getWidth() * f) / 100.0f;
        this.plainHeight = (getHeight() * f2) / 100.0f;
        float[] matrix = matrix();
        this.scaledWidth = matrix[6] - matrix[4];
        this.scaledHeight = matrix[7] - matrix[5];
        setWidthPercentage(0.0f);
    }

    public void scaleToFit(Rectangle rectangle) {
        scaleToFit(rectangle.getWidth(), rectangle.getHeight());
    }

    public void scaleToFit(float f, float f2) {
        scalePercent(100.0f);
        float scaledWidth2 = (f * 100.0f) / getScaledWidth();
        float scaledHeight2 = (f2 * 100.0f) / getScaledHeight();
        if (scaledWidth2 >= scaledHeight2) {
            scaledWidth2 = scaledHeight2;
        }
        scalePercent(scaledWidth2);
        setWidthPercentage(0.0f);
    }

    public float[] matrix() {
        return matrix(1.0f);
    }

    public float[] matrix(float f) {
        float[] fArr = new float[8];
        float cos = (float) Math.cos((double) this.rotationRadians);
        float sin = (float) Math.sin((double) this.rotationRadians);
        fArr[0] = this.plainWidth * cos * f;
        fArr[1] = this.plainWidth * sin * f;
        fArr[2] = (-this.plainHeight) * sin * f;
        fArr[3] = this.plainHeight * cos * f;
        if (((double) this.rotationRadians) < 1.5707963267948966d) {
            fArr[4] = fArr[2];
            fArr[5] = 0.0f;
            fArr[6] = fArr[0];
            fArr[7] = fArr[1] + fArr[3];
        } else if (((double) this.rotationRadians) < 3.141592653589793d) {
            fArr[4] = fArr[0] + fArr[2];
            fArr[5] = fArr[3];
            fArr[6] = 0.0f;
            fArr[7] = fArr[1];
        } else if (((double) this.rotationRadians) < 4.71238898038469d) {
            fArr[4] = fArr[0];
            fArr[5] = fArr[1] + fArr[3];
            fArr[6] = fArr[2];
            fArr[7] = 0.0f;
        } else {
            fArr[4] = 0.0f;
            fArr[5] = fArr[1];
            fArr[6] = fArr[0] + fArr[2];
            fArr[7] = fArr[3];
        }
        return fArr;
    }

    protected static synchronized Long getSerialId() {
        Long valueOf;
        synchronized (Image.class) {
            serialId++;
            valueOf = Long.valueOf(serialId);
        }
        return valueOf;
    }

    public Long getMySerialId() {
        return this.mySerialId;
    }

    public float getImageRotation() {
        float f = (float) (((double) (this.rotationRadians - this.initialRotation)) % 6.283185307179586d);
        return f < 0.0f ? (float) (((double) f) + 6.283185307179586d) : f;
    }

    public void setRotation(float f) {
        this.rotationRadians = (float) (((double) (f + this.initialRotation)) % 6.283185307179586d);
        if (this.rotationRadians < 0.0f) {
            this.rotationRadians = (float) (((double) this.rotationRadians) + 6.283185307179586d);
        }
        float[] matrix = matrix();
        this.scaledWidth = matrix[6] - matrix[4];
        this.scaledHeight = matrix[7] - matrix[5];
    }

    public void setRotationDegrees(float f) {
        setRotation((f / 180.0f) * ((float) 3.141592653589793d));
    }

    public float getInitialRotation() {
        return this.initialRotation;
    }

    public void setInitialRotation(float f) {
        this.initialRotation = f;
        setRotation(this.rotationRadians - this.initialRotation);
    }

    public float getIndentationLeft() {
        return this.indentationLeft;
    }

    public void setIndentationLeft(float f) {
        this.indentationLeft = f;
    }

    public float getIndentationRight() {
        return this.indentationRight;
    }

    public void setIndentationRight(float f) {
        this.indentationRight = f;
    }

    public float getSpacingBefore() {
        return this.spacingBefore;
    }

    public void setSpacingBefore(float f) {
        this.spacingBefore = f;
    }

    public float getSpacingAfter() {
        return this.spacingAfter;
    }

    public void setSpacingAfter(float f) {
        this.spacingAfter = f;
    }

    public float getPaddingTop() {
        return this.paddingTop;
    }

    public void setPaddingTop(float f) {
        this.paddingTop = f;
    }

    public float getWidthPercentage() {
        return this.widthPercentage;
    }

    public void setWidthPercentage(float f) {
        this.widthPercentage = f;
    }

    public boolean isScaleToFitLineWhenOverflow() {
        return this.scaleToFitLineWhenOverflow;
    }

    public void setScaleToFitLineWhenOverflow(boolean z) {
        this.scaleToFitLineWhenOverflow = z;
    }

    public boolean isScaleToFitHeight() {
        return this.scaleToFitHeight;
    }

    public void setScaleToFitHeight(boolean z) {
        this.scaleToFitHeight = z;
    }

    public void setAnnotation(Annotation annotation2) {
        this.annotation = annotation2;
    }

    public Annotation getAnnotation() {
        return this.annotation;
    }

    public PdfOCG getLayer() {
        return this.layer;
    }

    public void setLayer(PdfOCG pdfOCG) {
        this.layer = pdfOCG;
    }

    public boolean isInterpolation() {
        return this.interpolation;
    }

    public void setInterpolation(boolean z) {
        this.interpolation = z;
    }

    public int getOriginalType() {
        return this.originalType;
    }

    public void setOriginalType(int i) {
        this.originalType = i;
    }

    public byte[] getOriginalData() {
        return this.originalData;
    }

    public void setOriginalData(byte[] bArr) {
        this.originalData = bArr;
    }

    public boolean isDeflated() {
        return this.deflated;
    }

    public void setDeflated(boolean z) {
        this.deflated = z;
    }

    public int getDpiX() {
        return this.dpiX;
    }

    public int getDpiY() {
        return this.dpiY;
    }

    public void setDpi(int i, int i2) {
        this.dpiX = i;
        this.dpiY = i2;
    }

    public float getXYRatio() {
        return this.XYRatio;
    }

    public void setXYRatio(float f) {
        this.XYRatio = f;
    }

    public int getColorspace() {
        return this.colorspace;
    }

    public void setColorTransform(int i) {
        this.colortransform = i;
    }

    public int getColorTransform() {
        return this.colortransform;
    }

    public boolean isInverted() {
        return this.invert;
    }

    public void setInverted(boolean z) {
        this.invert = z;
    }

    public void tagICC(ICC_Profile iCC_Profile) {
        this.profile = iCC_Profile;
    }

    public boolean hasICCProfile() {
        return this.profile != null;
    }

    public ICC_Profile getICCProfile() {
        return this.profile;
    }

    public PdfDictionary getAdditional() {
        return this.additional;
    }

    public void setAdditional(PdfDictionary pdfDictionary) {
        this.additional = pdfDictionary;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.itextpdf.text.pdf.PdfArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.itextpdf.text.pdf.PdfObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.itextpdf.text.pdf.PdfArray} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.itextpdf.text.pdf.PdfArray} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void simplifyColorspace() {
        /*
            r3 = this;
            com.itextpdf.text.pdf.PdfDictionary r0 = r3.additional
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            com.itextpdf.text.pdf.PdfDictionary r0 = r3.additional
            com.itextpdf.text.pdf.PdfName r1 = com.itextpdf.text.pdf.PdfName.COLORSPACE
            com.itextpdf.text.pdf.PdfArray r0 = r0.getAsArray(r1)
            if (r0 != 0) goto L_0x0010
            return
        L_0x0010:
            com.itextpdf.text.pdf.PdfObject r1 = r3.simplifyColorspace(r0)
            boolean r2 = r1.isName()
            if (r2 == 0) goto L_0x001c
            r0 = r1
            goto L_0x003e
        L_0x001c:
            r1 = 0
            com.itextpdf.text.pdf.PdfName r1 = r0.getAsName(r1)
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.INDEXED
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x003e
            int r1 = r0.size()
            r2 = 2
            if (r1 < r2) goto L_0x003e
            r1 = 1
            com.itextpdf.text.pdf.PdfArray r2 = r0.getAsArray(r1)
            if (r2 == 0) goto L_0x003e
            com.itextpdf.text.pdf.PdfObject r2 = r3.simplifyColorspace(r2)
            r0.set(r1, r2)
        L_0x003e:
            com.itextpdf.text.pdf.PdfDictionary r1 = r3.additional
            com.itextpdf.text.pdf.PdfName r2 = com.itextpdf.text.pdf.PdfName.COLORSPACE
            r1.put(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.Image.simplifyColorspace():void");
    }

    private PdfObject simplifyColorspace(PdfArray pdfArray) {
        if (pdfArray == null) {
            return pdfArray;
        }
        PdfName asName = pdfArray.getAsName(0);
        if (PdfName.CALGRAY.equals(asName)) {
            return PdfName.DEVICEGRAY;
        }
        return PdfName.CALRGB.equals(asName) ? PdfName.DEVICERGB : pdfArray;
    }

    public boolean isMask() {
        return this.mask;
    }

    public void makeMask() throws DocumentException {
        if (!isMaskCandidate()) {
            throw new DocumentException(MessageLocalization.getComposedMessage("this.image.can.not.be.an.image.mask", new Object[0]));
        }
        this.mask = true;
    }

    public boolean isMaskCandidate() {
        if ((this.type != 34 || this.bpc <= 255) && this.colorspace != 1) {
            return false;
        }
        return true;
    }

    public Image getImageMask() {
        return this.imageMask;
    }

    public void setImageMask(Image image) throws DocumentException {
        boolean z = false;
        if (this.mask) {
            throw new DocumentException(MessageLocalization.getComposedMessage("an.image.mask.cannot.contain.another.image.mask", new Object[0]));
        } else if (!image.mask) {
            throw new DocumentException(MessageLocalization.getComposedMessage("the.image.mask.is.not.a.mask.did.you.do.makemask", new Object[0]));
        } else {
            this.imageMask = image;
            if (image.bpc > 1 && image.bpc <= 8) {
                z = true;
            }
            this.smask = z;
        }
    }

    public boolean isSmask() {
        return this.smask;
    }

    public void setSmask(boolean z) {
        this.smask = z;
    }

    public int[] getTransparency() {
        return this.transparency;
    }

    public void setTransparency(int[] iArr) {
        this.transparency = iArr;
    }

    public int getCompressionLevel() {
        return this.compressionLevel;
    }

    public void setCompressionLevel(int i) {
        if (i < 0 || i > 9) {
            this.compressionLevel = -1;
        } else {
            this.compressionLevel = i;
        }
    }

    public PdfObject getAccessibleAttribute(PdfName pdfName) {
        if (this.accessibleAttributes != null) {
            return this.accessibleAttributes.get(pdfName);
        }
        return null;
    }

    public void setAccessibleAttribute(PdfName pdfName, PdfObject pdfObject) {
        if (this.accessibleAttributes == null) {
            this.accessibleAttributes = new HashMap<>();
        }
        this.accessibleAttributes.put(pdfName, pdfObject);
    }

    public HashMap<PdfName, PdfObject> getAccessibleAttributes() {
        return this.accessibleAttributes;
    }

    public PdfName getRole() {
        return this.role;
    }

    public void setRole(PdfName pdfName) {
        this.role = pdfName;
    }

    public AccessibleElementId getId() {
        if (this.id == null) {
            this.id = new AccessibleElementId();
        }
        return this.id;
    }

    public void setId(AccessibleElementId accessibleElementId) {
        this.id = accessibleElementId;
    }
}
