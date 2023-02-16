package com.itextpdf.text.pdf.codec;

import android.support.v4.view.MotionEventCompat;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.ImgRaw;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.BidiOrder;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfString;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class BmpImage {
    private static final int BI_BITFIELDS = 3;
    private static final int BI_RGB = 0;
    private static final int BI_RLE4 = 2;
    private static final int BI_RLE8 = 1;
    private static final int LCS_CALIBRATED_RGB = 0;
    private static final int LCS_CMYK = 2;
    private static final int LCS_sRGB = 1;
    private static final int VERSION_2_1_BIT = 0;
    private static final int VERSION_2_24_BIT = 3;
    private static final int VERSION_2_4_BIT = 1;
    private static final int VERSION_2_8_BIT = 2;
    private static final int VERSION_3_1_BIT = 4;
    private static final int VERSION_3_24_BIT = 7;
    private static final int VERSION_3_4_BIT = 5;
    private static final int VERSION_3_8_BIT = 6;
    private static final int VERSION_3_NT_16_BIT = 8;
    private static final int VERSION_3_NT_32_BIT = 9;
    private static final int VERSION_4_16_BIT = 13;
    private static final int VERSION_4_1_BIT = 10;
    private static final int VERSION_4_24_BIT = 14;
    private static final int VERSION_4_32_BIT = 15;
    private static final int VERSION_4_4_BIT = 11;
    private static final int VERSION_4_8_BIT = 12;
    private int alphaMask;
    private long bitmapFileSize;
    private long bitmapOffset;
    private int bitsPerPixel;
    private int blueMask;
    private long compression;
    private int greenMask;
    int height;
    private long imageSize;
    private int imageType;
    private InputStream inputStream;
    private boolean isBottomUp;
    private int numBands;
    private byte[] palette;
    public HashMap<String, Object> properties = new HashMap<>();
    private int redMask;
    int width;
    private long xPelsPerMeter;
    private long yPelsPerMeter;

    private int findMask(int i) {
        for (int i2 = 0; i2 < 32 && (i & 1) != 1; i2++) {
            i >>>= 1;
        }
        return i;
    }

    private int findShift(int i) {
        int i2 = 0;
        while (i2 < 32 && (i & 1) != 1) {
            i >>>= 1;
            i2++;
        }
        return i2;
    }

    BmpImage(InputStream inputStream2, boolean z, int i) throws IOException {
        this.bitmapFileSize = (long) i;
        this.bitmapOffset = 0;
        process(inputStream2, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.itextpdf.text.Image getImage(java.net.URL r2) throws java.io.IOException {
        /*
            java.io.InputStream r0 = r2.openStream()     // Catch:{ all -> 0x0013 }
            com.itextpdf.text.Image r1 = getImage((java.io.InputStream) r0)     // Catch:{ all -> 0x0011 }
            r1.setUrl(r2)     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x0010
            r0.close()
        L_0x0010:
            return r1
        L_0x0011:
            r2 = move-exception
            goto L_0x0015
        L_0x0013:
            r2 = move-exception
            r0 = 0
        L_0x0015:
            if (r0 == 0) goto L_0x001a
            r0.close()
        L_0x001a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.BmpImage.getImage(java.net.URL):com.itextpdf.text.Image");
    }

    public static Image getImage(InputStream inputStream2) throws IOException {
        return getImage(inputStream2, false, 0);
    }

    public static Image getImage(InputStream inputStream2, boolean z, int i) throws IOException {
        BmpImage bmpImage = new BmpImage(inputStream2, z, i);
        try {
            Image image = bmpImage.getImage();
            image.setDpi((int) ((((double) bmpImage.xPelsPerMeter) * 0.0254d) + 0.5d), (int) ((((double) bmpImage.yPelsPerMeter) * 0.0254d) + 0.5d));
            image.setOriginalType(4);
            return image;
        } catch (BadElementException e) {
            throw new ExceptionConverter(e);
        }
    }

    public static Image getImage(String str) throws IOException {
        return getImage(Utilities.toURL(str));
    }

    public static Image getImage(byte[] bArr) throws IOException {
        Image image = getImage((InputStream) new ByteArrayInputStream(bArr));
        image.setOriginalData(bArr);
        return image;
    }

    /* access modifiers changed from: protected */
    public void process(InputStream inputStream2, boolean z) throws IOException {
        int i;
        int i2;
        InputStream inputStream3 = inputStream2;
        if (z || (inputStream3 instanceof BufferedInputStream)) {
            this.inputStream = inputStream3;
        } else {
            this.inputStream = new BufferedInputStream(inputStream3);
        }
        if (!z) {
            if (readUnsignedByte(this.inputStream) == 66 && readUnsignedByte(this.inputStream) == 77) {
                this.bitmapFileSize = readDWord(this.inputStream);
                readWord(this.inputStream);
                readWord(this.inputStream);
                this.bitmapOffset = readDWord(this.inputStream);
            } else {
                throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.magic.value.for.bmp.file", new Object[0]));
            }
        }
        long readDWord = readDWord(this.inputStream);
        if (readDWord == 12) {
            this.width = readWord(this.inputStream);
            this.height = readWord(this.inputStream);
        } else {
            this.width = readLong(this.inputStream);
            this.height = readLong(this.inputStream);
        }
        int readWord = readWord(this.inputStream);
        this.bitsPerPixel = readWord(this.inputStream);
        this.properties.put("color_planes", Integer.valueOf(readWord));
        this.properties.put("bits_per_pixel", Integer.valueOf(this.bitsPerPixel));
        this.numBands = 3;
        if (this.bitmapOffset == 0) {
            this.bitmapOffset = readDWord;
        }
        if (readDWord == 12) {
            this.properties.put("bmp_version", "BMP v. 2.x");
            if (this.bitsPerPixel == 1) {
                this.imageType = 0;
            } else if (this.bitsPerPixel == 4) {
                this.imageType = 1;
            } else if (this.bitsPerPixel == 8) {
                this.imageType = 2;
            } else if (this.bitsPerPixel == 24) {
                this.imageType = 3;
            }
            int i3 = ((int) (((this.bitmapOffset - 14) - readDWord) / 3)) * 3;
            if (this.bitmapOffset == readDWord) {
                switch (this.imageType) {
                    case 0:
                        i3 = 6;
                        break;
                    case 1:
                        i3 = 48;
                        break;
                    case 2:
                        i3 = 768;
                        break;
                    case 3:
                        i3 = 0;
                        break;
                }
                this.bitmapOffset = readDWord + ((long) i3);
            }
            readPalette(i3);
        } else {
            this.compression = readDWord(this.inputStream);
            this.imageSize = readDWord(this.inputStream);
            this.xPelsPerMeter = (long) readLong(this.inputStream);
            this.yPelsPerMeter = (long) readLong(this.inputStream);
            long readDWord2 = readDWord(this.inputStream);
            long readDWord3 = readDWord(this.inputStream);
            switch ((int) this.compression) {
                case 0:
                    this.properties.put("compression", "BI_RGB");
                    break;
                case 1:
                    this.properties.put("compression", "BI_RLE8");
                    break;
                case 2:
                    this.properties.put("compression", "BI_RLE4");
                    break;
                case 3:
                    this.properties.put("compression", "BI_BITFIELDS");
                    break;
            }
            this.properties.put("x_pixels_per_meter", Long.valueOf(this.xPelsPerMeter));
            this.properties.put("y_pixels_per_meter", Long.valueOf(this.yPelsPerMeter));
            this.properties.put("colors_used", Long.valueOf(readDWord2));
            this.properties.put("colors_important", Long.valueOf(readDWord3));
            if (readDWord == 40 || readDWord == 52 || readDWord == 56) {
                switch ((int) this.compression) {
                    case 0:
                    case 1:
                    case 2:
                        if (this.bitsPerPixel == 1) {
                            this.imageType = 4;
                        } else if (this.bitsPerPixel == 4) {
                            this.imageType = 5;
                        } else if (this.bitsPerPixel == 8) {
                            this.imageType = 6;
                        } else if (this.bitsPerPixel == 24) {
                            this.imageType = 7;
                        } else if (this.bitsPerPixel == 16) {
                            this.imageType = 8;
                            this.redMask = 31744;
                            this.greenMask = 992;
                            this.blueMask = 31;
                            this.properties.put("red_mask", Integer.valueOf(this.redMask));
                            this.properties.put("green_mask", Integer.valueOf(this.greenMask));
                            this.properties.put("blue_mask", Integer.valueOf(this.blueMask));
                        } else if (this.bitsPerPixel == 32) {
                            this.imageType = 9;
                            this.redMask = 16711680;
                            this.greenMask = MotionEventCompat.ACTION_POINTER_INDEX_MASK;
                            this.blueMask = 255;
                            this.properties.put("red_mask", Integer.valueOf(this.redMask));
                            this.properties.put("green_mask", Integer.valueOf(this.greenMask));
                            this.properties.put("blue_mask", Integer.valueOf(this.blueMask));
                        }
                        if (readDWord >= 52) {
                            this.redMask = (int) readDWord(this.inputStream);
                            this.greenMask = (int) readDWord(this.inputStream);
                            this.blueMask = (int) readDWord(this.inputStream);
                            this.properties.put("red_mask", Integer.valueOf(this.redMask));
                            this.properties.put("green_mask", Integer.valueOf(this.greenMask));
                            this.properties.put("blue_mask", Integer.valueOf(this.blueMask));
                        }
                        if (readDWord == 56) {
                            this.alphaMask = (int) readDWord(this.inputStream);
                            this.properties.put("alpha_mask", Integer.valueOf(this.alphaMask));
                        }
                        int i4 = ((int) (((this.bitmapOffset - 14) - readDWord) / 4)) * 4;
                        if (this.bitmapOffset == readDWord) {
                            switch (this.imageType) {
                                case 4:
                                    if (readDWord2 == 0) {
                                        readDWord2 = 2;
                                    }
                                    i4 = ((int) readDWord2) * 4;
                                    break;
                                case 5:
                                    if (readDWord2 == 0) {
                                        readDWord2 = 16;
                                    }
                                    i4 = ((int) readDWord2) * 4;
                                    break;
                                case 6:
                                    if (readDWord2 == 0) {
                                        readDWord2 = 256;
                                    }
                                    i4 = ((int) readDWord2) * 4;
                                    break;
                                default:
                                    i4 = 0;
                                    break;
                            }
                            this.bitmapOffset = readDWord + ((long) i4);
                        }
                        readPalette(i4);
                        this.properties.put("bmp_version", "BMP v. 3.x");
                        break;
                    case 3:
                        if (this.bitsPerPixel == 16) {
                            this.imageType = 8;
                        } else if (this.bitsPerPixel == 32) {
                            this.imageType = 9;
                        }
                        this.redMask = (int) readDWord(this.inputStream);
                        this.greenMask = (int) readDWord(this.inputStream);
                        this.blueMask = (int) readDWord(this.inputStream);
                        if (readDWord == 56) {
                            this.alphaMask = (int) readDWord(this.inputStream);
                            this.properties.put("alpha_mask", Integer.valueOf(this.alphaMask));
                        }
                        this.properties.put("red_mask", Integer.valueOf(this.redMask));
                        this.properties.put("green_mask", Integer.valueOf(this.greenMask));
                        this.properties.put("blue_mask", Integer.valueOf(this.blueMask));
                        if (readDWord2 != 0) {
                            readPalette(((int) readDWord2) * 4);
                        }
                        this.properties.put("bmp_version", "BMP v. 3.x NT");
                        break;
                    default:
                        throw new RuntimeException("Invalid compression specified in BMP file.");
                }
            } else if (readDWord == 108) {
                this.properties.put("bmp_version", "BMP v. 4.x");
                this.redMask = (int) readDWord(this.inputStream);
                this.greenMask = (int) readDWord(this.inputStream);
                this.blueMask = (int) readDWord(this.inputStream);
                this.alphaMask = (int) readDWord(this.inputStream);
                long readDWord4 = readDWord(this.inputStream);
                int readLong = readLong(this.inputStream);
                int readLong2 = readLong(this.inputStream);
                int readLong3 = readLong(this.inputStream);
                int readLong4 = readLong(this.inputStream);
                int readLong5 = readLong(this.inputStream);
                int readLong6 = readLong(this.inputStream);
                int readLong7 = readLong(this.inputStream);
                int readLong8 = readLong(this.inputStream);
                int readLong9 = readLong(this.inputStream);
                int i5 = readLong5;
                int i6 = readLong7;
                long readDWord5 = readDWord(this.inputStream);
                long readDWord6 = readDWord(this.inputStream);
                long readDWord7 = readDWord(this.inputStream);
                if (this.bitsPerPixel == 1) {
                    this.imageType = 10;
                } else if (this.bitsPerPixel == 4) {
                    this.imageType = 11;
                } else if (this.bitsPerPixel == 8) {
                    this.imageType = 12;
                } else if (this.bitsPerPixel == 16) {
                    this.imageType = 13;
                    if (((int) this.compression) == 0) {
                        this.redMask = 31744;
                        this.greenMask = 992;
                        this.blueMask = 31;
                    }
                } else if (this.bitsPerPixel == 24) {
                    this.imageType = 14;
                } else if (this.bitsPerPixel == 32) {
                    this.imageType = 15;
                    if (((int) this.compression) == 0) {
                        this.redMask = 16711680;
                        this.greenMask = MotionEventCompat.ACTION_POINTER_INDEX_MASK;
                        this.blueMask = 255;
                    }
                }
                this.properties.put("red_mask", Integer.valueOf(this.redMask));
                this.properties.put("green_mask", Integer.valueOf(this.greenMask));
                this.properties.put("blue_mask", Integer.valueOf(this.blueMask));
                this.properties.put("alpha_mask", Integer.valueOf(this.alphaMask));
                int i7 = ((int) (((this.bitmapOffset - 14) - readDWord) / 4)) * 4;
                if (this.bitmapOffset == readDWord) {
                    switch (this.imageType) {
                        case 10:
                            if (readDWord2 == 0) {
                                readDWord2 = 2;
                            }
                            i7 = ((int) readDWord2) * 4;
                            break;
                        case 11:
                            if (readDWord2 == 0) {
                                readDWord2 = 16;
                            }
                            i7 = ((int) readDWord2) * 4;
                            break;
                        case 12:
                            if (readDWord2 == 0) {
                                readDWord2 = 256;
                            }
                            i7 = ((int) readDWord2) * 4;
                            break;
                        default:
                            i7 = 0;
                            break;
                    }
                    this.bitmapOffset = readDWord + ((long) i7);
                }
                readPalette(i7);
                switch ((int) readDWord4) {
                    case 0:
                        this.properties.put("color_space", "LCS_CALIBRATED_RGB");
                        this.properties.put("redX", Integer.valueOf(readLong));
                        this.properties.put("redY", Integer.valueOf(readLong2));
                        this.properties.put("redZ", Integer.valueOf(readLong3));
                        this.properties.put("greenX", Integer.valueOf(readLong4));
                        this.properties.put("greenY", Integer.valueOf(i5));
                        this.properties.put("greenZ", Integer.valueOf(readLong6));
                        this.properties.put("blueX", Integer.valueOf(i6));
                        this.properties.put("blueY", Integer.valueOf(readLong8));
                        this.properties.put("blueZ", Integer.valueOf(readLong9));
                        this.properties.put("gamma_red", Long.valueOf(readDWord5));
                        this.properties.put("gamma_green", Long.valueOf(readDWord6));
                        this.properties.put("gamma_blue", Long.valueOf(readDWord7));
                        throw new RuntimeException("Not implemented yet.");
                    case 1:
                        this.properties.put("color_space", "LCS_sRGB");
                        break;
                    case 2:
                        this.properties.put("color_space", "LCS_CMYK");
                        throw new RuntimeException("Not implemented yet.");
                }
            } else {
                this.properties.put("bmp_version", "BMP v. 5.x");
                throw new RuntimeException("BMP version 5 not implemented yet.");
            }
        }
        if (this.height > 0) {
            i2 = 1;
            this.isBottomUp = true;
            i = 0;
        } else {
            i2 = 1;
            i = 0;
            this.isBottomUp = false;
            this.height = Math.abs(this.height);
        }
        if (this.bitsPerPixel != i2) {
            if (this.bitsPerPixel == 4 || this.bitsPerPixel == 8) {
                i2 = 1;
            } else if (this.bitsPerPixel == 16) {
                this.numBands = 3;
                return;
            } else {
                int i8 = 3;
                if (this.bitsPerPixel == 32) {
                    if (this.alphaMask != 0) {
                        i8 = 4;
                    }
                    this.numBands = i8;
                    return;
                }
                this.numBands = 3;
                return;
            }
        }
        this.numBands = i2;
        if (this.imageType == 0 || this.imageType == i2 || this.imageType == 2) {
            int length = this.palette.length / 3;
            if (length > 256) {
                length = 256;
            }
            byte[] bArr = new byte[length];
            byte[] bArr2 = new byte[length];
            byte[] bArr3 = new byte[length];
            while (i < length) {
                int i9 = 3 * i;
                bArr3[i] = this.palette[i9];
                bArr2[i] = this.palette[i9 + 1];
                bArr[i] = this.palette[i9 + 2];
                i++;
            }
            return;
        }
        int length2 = this.palette.length / 4;
        if (length2 > 256) {
            length2 = 256;
        }
        byte[] bArr4 = new byte[length2];
        byte[] bArr5 = new byte[length2];
        byte[] bArr6 = new byte[length2];
        while (i < length2) {
            int i10 = 4 * i;
            bArr6[i] = this.palette[i10];
            bArr5[i] = this.palette[i10 + 1];
            bArr4[i] = this.palette[i10 + 2];
            i++;
        }
    }

    private byte[] getPalette(int i) {
        if (this.palette == null) {
            return null;
        }
        byte[] bArr = new byte[((this.palette.length / i) * 3)];
        int length = this.palette.length / i;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * i;
            int i4 = i2 * 3;
            int i5 = i3 + 1;
            bArr[i4 + 2] = this.palette[i3];
            bArr[i4 + 1] = this.palette[i5];
            bArr[i4] = this.palette[i5 + 1];
        }
        return bArr;
    }

    private Image getImage() throws IOException, BadElementException {
        switch (this.imageType) {
            case 0:
                return read1Bit(3);
            case 1:
                return read4Bit(3);
            case 2:
                return read8Bit(3);
            case 3:
                byte[] bArr = new byte[(this.width * this.height * 3)];
                read24Bit(bArr);
                return new ImgRaw(this.width, this.height, 3, 8, bArr);
            case 4:
                return read1Bit(4);
            case 5:
                int i = (int) this.compression;
                if (i == 0) {
                    return read4Bit(4);
                }
                if (i == 2) {
                    return readRLE4();
                }
                throw new RuntimeException("Invalid compression specified for BMP file.");
            case 6:
                switch ((int) this.compression) {
                    case 0:
                        return read8Bit(4);
                    case 1:
                        return readRLE8();
                    default:
                        throw new RuntimeException("Invalid compression specified for BMP file.");
                }
            case 7:
                byte[] bArr2 = new byte[(this.width * this.height * 3)];
                read24Bit(bArr2);
                return new ImgRaw(this.width, this.height, 3, 8, bArr2);
            case 8:
                return read1632Bit(false);
            case 9:
                return read1632Bit(true);
            case 10:
                return read1Bit(4);
            case 11:
                int i2 = (int) this.compression;
                if (i2 == 0) {
                    return read4Bit(4);
                }
                if (i2 == 2) {
                    return readRLE4();
                }
                throw new RuntimeException("Invalid compression specified for BMP file.");
            case 12:
                switch ((int) this.compression) {
                    case 0:
                        return read8Bit(4);
                    case 1:
                        return readRLE8();
                    default:
                        throw new RuntimeException("Invalid compression specified for BMP file.");
                }
            case 13:
                return read1632Bit(false);
            case 14:
                byte[] bArr3 = new byte[(this.width * this.height * 3)];
                read24Bit(bArr3);
                return new ImgRaw(this.width, this.height, 3, 8, bArr3);
            case 15:
                return read1632Bit(true);
            default:
                return null;
        }
    }

    private Image indexedModel(byte[] bArr, int i, int i2) throws BadElementException {
        ImgRaw imgRaw = new ImgRaw(this.width, this.height, 1, i, bArr);
        PdfArray pdfArray = new PdfArray();
        pdfArray.add((PdfObject) PdfName.INDEXED);
        pdfArray.add((PdfObject) PdfName.DEVICERGB);
        byte[] palette2 = getPalette(i2);
        pdfArray.add((PdfObject) new PdfNumber((palette2.length / 3) - 1));
        pdfArray.add((PdfObject) new PdfString(palette2));
        PdfDictionary pdfDictionary = new PdfDictionary();
        pdfDictionary.put(PdfName.COLORSPACE, pdfArray);
        imgRaw.setAdditional(pdfDictionary);
        return imgRaw;
    }

    private void readPalette(int i) throws IOException {
        if (i != 0) {
            this.palette = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = this.inputStream.read(this.palette, i2, i - i2);
                if (read < 0) {
                    throw new RuntimeException(MessageLocalization.getComposedMessage("incomplete.palette", new Object[0]));
                }
                i2 += read;
            }
            this.properties.put("palette", this.palette);
        }
    }

    private Image read1Bit(int i) throws IOException, BadElementException {
        byte[] bArr = new byte[(((this.width + 7) / 8) * this.height)];
        int ceil = (int) Math.ceil(((double) this.width) / 8.0d);
        int i2 = ceil % 4;
        int i3 = 0;
        int i4 = (i2 != 0 ? 4 - i2 : 0) + ceil;
        int i5 = this.height * i4;
        byte[] bArr2 = new byte[i5];
        int i6 = 0;
        while (i6 < i5) {
            i6 += this.inputStream.read(bArr2, i6, i5 - i6);
        }
        if (this.isBottomUp) {
            while (i3 < this.height) {
                int i7 = i3 + 1;
                System.arraycopy(bArr2, i5 - (i7 * i4), bArr, i3 * ceil, ceil);
                i3 = i7;
            }
        } else {
            while (i3 < this.height) {
                System.arraycopy(bArr2, i3 * i4, bArr, i3 * ceil, ceil);
                i3++;
            }
        }
        return indexedModel(bArr, 1, i);
    }

    private Image read4Bit(int i) throws IOException, BadElementException {
        byte[] bArr = new byte[(((this.width + 1) / 2) * this.height)];
        int ceil = (int) Math.ceil(((double) this.width) / 2.0d);
        int i2 = ceil % 4;
        int i3 = 0;
        int i4 = (i2 != 0 ? 4 - i2 : 0) + ceil;
        int i5 = this.height * i4;
        byte[] bArr2 = new byte[i5];
        int i6 = 0;
        while (i6 < i5) {
            i6 += this.inputStream.read(bArr2, i6, i5 - i6);
        }
        if (this.isBottomUp) {
            while (i3 < this.height) {
                int i7 = i3 + 1;
                System.arraycopy(bArr2, i5 - (i7 * i4), bArr, i3 * ceil, ceil);
                i3 = i7;
            }
        } else {
            while (i3 < this.height) {
                System.arraycopy(bArr2, i3 * i4, bArr, i3 * ceil, ceil);
                i3++;
            }
        }
        return indexedModel(bArr, 4, i);
    }

    private Image read8Bit(int i) throws IOException, BadElementException {
        byte[] bArr = new byte[(this.width * this.height)];
        int i2 = this.width * 8;
        int i3 = 0;
        int ceil = i2 % 32 != 0 ? (int) Math.ceil(((double) ((((i2 / 32) + 1) * 32) - i2)) / 8.0d) : 0;
        int i4 = (this.width + ceil) * this.height;
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        while (i5 < i4) {
            i5 += this.inputStream.read(bArr2, i5, i4 - i5);
        }
        if (this.isBottomUp) {
            while (i3 < this.height) {
                int i6 = i3 + 1;
                System.arraycopy(bArr2, i4 - ((this.width + ceil) * i6), bArr, i3 * this.width, this.width);
                i3 = i6;
            }
        } else {
            while (i3 < this.height) {
                System.arraycopy(bArr2, (this.width + ceil) * i3, bArr, this.width * i3, this.width);
                i3++;
            }
        }
        return indexedModel(bArr, 8, i);
    }

    private void read24Bit(byte[] bArr) {
        int i = this.width * 24;
        int ceil = i % 32 != 0 ? (int) Math.ceil(((double) ((((i / 32) + 1) * 32) - i)) / 8.0d) : 0;
        int i2 = (((this.width * 3) + 3) / 4) * 4 * this.height;
        byte[] bArr2 = new byte[i2];
        int i3 = 0;
        while (i3 < i2) {
            try {
                int read = this.inputStream.read(bArr2, i3, i2 - i3);
                if (read < 0) {
                    break;
                }
                i3 += read;
            } catch (IOException e) {
                throw new ExceptionConverter(e);
            }
        }
        if (this.isBottomUp) {
            int i4 = ((this.width * this.height) * 3) - 1;
            int i5 = -ceil;
            int i6 = 0;
            while (i6 < this.height) {
                i6++;
                int i7 = (i4 - ((this.width * i6) * 3)) + 1;
                int i8 = i5 + ceil;
                for (int i9 = 0; i9 < this.width; i9++) {
                    int i10 = i8 + 1;
                    bArr[i7 + 2] = bArr2[i8];
                    int i11 = i10 + 1;
                    bArr[i7 + 1] = bArr2[i10];
                    i8 = i11 + 1;
                    bArr[i7] = bArr2[i11];
                    i7 += 3;
                }
                i5 = i8;
            }
            return;
        }
        int i12 = -ceil;
        int i13 = 0;
        int i14 = 0;
        while (i13 < this.height) {
            int i15 = i12 + ceil;
            for (int i16 = 0; i16 < this.width; i16++) {
                int i17 = i15 + 1;
                bArr[i14 + 2] = bArr2[i15];
                int i18 = i17 + 1;
                bArr[i14 + 1] = bArr2[i17];
                i15 = i18 + 1;
                bArr[i14] = bArr2[i18];
                i14 += 3;
            }
            i13++;
            i12 = i15;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.itextpdf.text.Image read1632Bit(boolean r23) throws java.io.IOException, com.itextpdf.text.BadElementException {
        /*
            r22 = this;
            r0 = r22
            int r2 = r0.redMask
            int r2 = r0.findMask(r2)
            int r3 = r0.redMask
            int r3 = r0.findShift(r3)
            int r4 = r2 + 1
            int r5 = r0.greenMask
            int r5 = r0.findMask(r5)
            int r6 = r0.greenMask
            int r6 = r0.findShift(r6)
            int r7 = r5 + 1
            int r8 = r0.blueMask
            int r8 = r0.findMask(r8)
            int r9 = r0.blueMask
            int r9 = r0.findShift(r9)
            int r10 = r8 + 1
            int r11 = r0.width
            int r12 = r0.height
            int r11 = r11 * r12
            int r11 = r11 * 3
            byte[] r11 = new byte[r11]
            if (r23 != 0) goto L_0x0050
            int r13 = r0.width
            int r13 = r13 * 16
            int r14 = r13 % 32
            if (r14 == 0) goto L_0x0050
            int r14 = r13 / 32
            int r14 = r14 + 1
            int r14 = r14 * 32
            int r14 = r14 - r13
            double r13 = (double) r14
            r15 = 4620693217682128896(0x4020000000000000, double:8.0)
            double r13 = r13 / r15
            double r13 = java.lang.Math.ceil(r13)
            int r13 = (int) r13
            goto L_0x0051
        L_0x0050:
            r13 = 0
        L_0x0051:
            long r14 = r0.imageSize
            int r14 = (int) r14
            if (r14 != 0) goto L_0x005a
            long r14 = r0.bitmapFileSize
            long r14 = r0.bitmapOffset
        L_0x005a:
            boolean r14 = r0.isBottomUp
            if (r14 == 0) goto L_0x00cc
            int r14 = r0.height
            int r14 = r14 + -1
        L_0x0062:
            if (r14 < 0) goto L_0x0120
            int r15 = r0.width
            int r15 = r15 * 3
            int r15 = r15 * r14
            r16 = r15
            r15 = 0
        L_0x006c:
            int r12 = r0.width
            if (r15 >= r12) goto L_0x00b4
            if (r23 == 0) goto L_0x007c
            java.io.InputStream r12 = r0.inputStream
            r18 = r13
            long r12 = r0.readDWord(r12)
            int r12 = (int) r12
            goto L_0x0084
        L_0x007c:
            r18 = r13
            java.io.InputStream r12 = r0.inputStream
            int r12 = r0.readWord(r12)
        L_0x0084:
            int r13 = r16 + 1
            int r17 = r12 >>> r3
            r19 = r3
            r3 = r17 & r2
            int r3 = r3 * 256
            int r3 = r3 / r4
            byte r3 = (byte) r3
            r11[r16] = r3
            int r3 = r13 + 1
            int r16 = r12 >>> r6
            r20 = r6
            r6 = r16 & r5
            int r6 = r6 * 256
            int r6 = r6 / r7
            byte r6 = (byte) r6
            r11[r13] = r6
            int r16 = r3 + 1
            int r6 = r12 >>> r9
            r6 = r6 & r8
            int r6 = r6 * 256
            int r6 = r6 / r10
            byte r6 = (byte) r6
            r11[r3] = r6
            int r15 = r15 + 1
            r13 = r18
            r3 = r19
            r6 = r20
            goto L_0x006c
        L_0x00b4:
            r19 = r3
            r20 = r6
            r12 = r13
            r3 = 0
        L_0x00ba:
            if (r3 >= r12) goto L_0x00c4
            java.io.InputStream r6 = r0.inputStream
            r6.read()
            int r3 = r3 + 1
            goto L_0x00ba
        L_0x00c4:
            int r14 = r14 + -1
            r13 = r12
            r3 = r19
            r6 = r20
            goto L_0x0062
        L_0x00cc:
            r19 = r3
            r20 = r6
            r12 = r13
            r3 = 0
            r6 = 0
        L_0x00d3:
            int r13 = r0.height
            if (r3 >= r13) goto L_0x0120
            r13 = r6
            r6 = 0
        L_0x00d9:
            int r14 = r0.width
            if (r6 >= r14) goto L_0x0111
            if (r23 == 0) goto L_0x00e7
            java.io.InputStream r14 = r0.inputStream
            long r14 = r0.readDWord(r14)
            int r14 = (int) r14
            goto L_0x00ed
        L_0x00e7:
            java.io.InputStream r14 = r0.inputStream
            int r14 = r0.readWord(r14)
        L_0x00ed:
            int r15 = r13 + 1
            int r16 = r14 >>> r19
            r1 = r16 & r2
            int r1 = r1 * 256
            int r1 = r1 / r4
            byte r1 = (byte) r1
            r11[r13] = r1
            int r1 = r15 + 1
            int r13 = r14 >>> r20
            r13 = r13 & r5
            int r13 = r13 * 256
            int r13 = r13 / r7
            byte r13 = (byte) r13
            r11[r15] = r13
            int r13 = r1 + 1
            int r14 = r14 >>> r9
            r14 = r14 & r8
            int r14 = r14 * 256
            int r14 = r14 / r10
            byte r14 = (byte) r14
            r11[r1] = r14
            int r6 = r6 + 1
            goto L_0x00d9
        L_0x0111:
            r1 = 0
        L_0x0112:
            if (r1 >= r12) goto L_0x011c
            java.io.InputStream r6 = r0.inputStream
            r6.read()
            int r1 = r1 + 1
            goto L_0x0112
        L_0x011c:
            int r3 = r3 + 1
            r6 = r13
            goto L_0x00d3
        L_0x0120:
            com.itextpdf.text.ImgRaw r1 = new com.itextpdf.text.ImgRaw
            int r13 = r0.width
            int r14 = r0.height
            r15 = 3
            r16 = 8
            r12 = r1
            r17 = r11
            r12.<init>(r13, r14, r15, r16, r17)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.BmpImage.read1632Bit(boolean):com.itextpdf.text.Image");
    }

    private Image readRLE8() throws IOException, BadElementException {
        int i = (int) this.imageSize;
        if (i == 0) {
            i = (int) (this.bitmapFileSize - this.bitmapOffset);
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        while (i3 < i) {
            i3 += this.inputStream.read(bArr, i3, i - i3);
        }
        byte[] decodeRLE = decodeRLE(true, bArr);
        int i4 = this.width * this.height;
        if (this.isBottomUp) {
            byte[] bArr2 = new byte[decodeRLE.length];
            int i5 = this.width;
            while (i2 < this.height) {
                int i6 = i2 + 1;
                System.arraycopy(decodeRLE, i4 - (i6 * i5), bArr2, i2 * i5, i5);
                i2 = i6;
            }
            decodeRLE = bArr2;
        }
        return indexedModel(decodeRLE, 8, 4);
    }

    private Image readRLE4() throws IOException, BadElementException {
        int i = (int) this.imageSize;
        if (i == 0) {
            i = (int) (this.bitmapFileSize - this.bitmapOffset);
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            i2 += this.inputStream.read(bArr, i2, i - i2);
        }
        byte[] decodeRLE = decodeRLE(false, bArr);
        if (this.isBottomUp) {
            byte[] bArr2 = new byte[(this.width * this.height)];
            int i3 = 0;
            for (int i4 = this.height - 1; i4 >= 0; i4--) {
                int i5 = this.width * i4;
                int i6 = this.width + i3;
                while (i3 != i6) {
                    bArr2[i3] = decodeRLE[i5];
                    i3++;
                    i5++;
                }
            }
            decodeRLE = bArr2;
        }
        int i7 = (this.width + 1) / 2;
        byte[] bArr3 = new byte[(this.height * i7)];
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i8 < this.height) {
            int i11 = i9;
            for (int i12 = 0; i12 < this.width; i12++) {
                if ((i12 & 1) == 0) {
                    bArr3[(i12 / 2) + i10] = (byte) (decodeRLE[i11] << 4);
                    i11++;
                } else {
                    int i13 = (i12 / 2) + i10;
                    bArr3[i13] = (byte) (((byte) (decodeRLE[i11] & BidiOrder.B)) | bArr3[i13]);
                    i11++;
                }
            }
            i10 += i7;
            i8++;
            i9 = i11;
        }
        return indexedModel(bArr3, 4, 4);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] decodeRLE(boolean r17, byte[] r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            int r2 = r0.width
            int r3 = r0.height
            int r2 = r2 * r3
            byte[] r2 = new byte[r2]
            r3 = 0
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x0010:
            int r8 = r0.height     // Catch:{ RuntimeException -> 0x00d0 }
            if (r4 >= r8) goto L_0x00d0
            int r8 = r1.length     // Catch:{ RuntimeException -> 0x00d0 }
            if (r5 >= r8) goto L_0x00d0
            int r8 = r5 + 1
            byte r5 = r1[r5]     // Catch:{ RuntimeException -> 0x00d0 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            r9 = 1
            if (r5 == 0) goto L_0x0053
            int r10 = r8 + 1
            byte r8 = r1[r8]     // Catch:{ RuntimeException -> 0x00d0 }
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r17 == 0) goto L_0x0037
            r9 = r7
            r7 = r5
        L_0x002a:
            if (r7 == 0) goto L_0x0035
            int r11 = r9 + 1
            byte r12 = (byte) r8     // Catch:{ RuntimeException -> 0x00d0 }
            r2[r9] = r12     // Catch:{ RuntimeException -> 0x00d0 }
            int r7 = r7 + -1
            r9 = r11
            goto L_0x002a
        L_0x0035:
            r11 = r9
            goto L_0x004f
        L_0x0037:
            r11 = r7
            r7 = r3
        L_0x0039:
            if (r7 >= r5) goto L_0x004f
            int r12 = r11 + 1
            r13 = r7 & 1
            if (r13 != r9) goto L_0x0044
            r13 = r8 & 15
            goto L_0x0048
        L_0x0044:
            int r13 = r8 >>> 4
            r13 = r13 & 15
        L_0x0048:
            byte r13 = (byte) r13     // Catch:{ RuntimeException -> 0x00d0 }
            r2[r11] = r13     // Catch:{ RuntimeException -> 0x00d0 }
            int r7 = r7 + 1
            r11 = r12
            goto L_0x0039
        L_0x004f:
            int r6 = r6 + r5
            r5 = r10
            r7 = r11
            goto L_0x0010
        L_0x0053:
            int r5 = r8 + 1
            byte r8 = r1[r8]     // Catch:{ RuntimeException -> 0x00d0 }
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r8 != r9) goto L_0x005d
            goto L_0x00d0
        L_0x005d:
            if (r8 == 0) goto L_0x00c7
            r10 = 2
            if (r8 == r10) goto L_0x00b3
            if (r17 == 0) goto L_0x0078
            r11 = r5
            r5 = r8
        L_0x0066:
            if (r5 == 0) goto L_0x009c
            int r12 = r7 + 1
            int r13 = r11 + 1
            byte r11 = r1[r11]     // Catch:{ RuntimeException -> 0x00d0 }
            r11 = r11 & 255(0xff, float:3.57E-43)
            byte r11 = (byte) r11     // Catch:{ RuntimeException -> 0x00d0 }
            r2[r7] = r11     // Catch:{ RuntimeException -> 0x00d0 }
            int r5 = r5 + -1
            r7 = r12
            r11 = r13
            goto L_0x0066
        L_0x0078:
            r12 = r3
            r11 = r5
            r5 = r12
        L_0x007b:
            if (r5 >= r8) goto L_0x009c
            r13 = r5 & 1
            if (r13 != 0) goto L_0x008a
            int r12 = r11 + 1
            byte r11 = r1[r11]     // Catch:{ RuntimeException -> 0x00d0 }
            r11 = r11 & 255(0xff, float:3.57E-43)
            r15 = r12
            r12 = r11
            r11 = r15
        L_0x008a:
            int r14 = r7 + 1
            if (r13 != r9) goto L_0x0091
            r13 = r12 & 15
            goto L_0x0095
        L_0x0091:
            int r13 = r12 >>> 4
            r13 = r13 & 15
        L_0x0095:
            byte r13 = (byte) r13     // Catch:{ RuntimeException -> 0x00d0 }
            r2[r7] = r13     // Catch:{ RuntimeException -> 0x00d0 }
            int r5 = r5 + 1
            r7 = r14
            goto L_0x007b
        L_0x009c:
            int r6 = r6 + r8
            if (r17 == 0) goto L_0x00a6
            r5 = r8 & 1
            if (r5 != r9) goto L_0x00ad
            int r11 = r11 + 1
            goto L_0x00ad
        L_0x00a6:
            r5 = r8 & 3
            if (r5 == r9) goto L_0x00b0
            if (r5 != r10) goto L_0x00ad
            goto L_0x00b0
        L_0x00ad:
            r5 = r11
            goto L_0x0010
        L_0x00b0:
            int r11 = r11 + 1
            goto L_0x00ad
        L_0x00b3:
            int r7 = r5 + 1
            byte r5 = r1[r5]     // Catch:{ RuntimeException -> 0x00d0 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r6 = r6 + r5
            int r5 = r7 + 1
            byte r7 = r1[r7]     // Catch:{ RuntimeException -> 0x00d0 }
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r4 = r4 + r7
            int r7 = r0.width     // Catch:{ RuntimeException -> 0x00d0 }
            int r7 = r7 * r4
            int r7 = r7 + r6
            goto L_0x0010
        L_0x00c7:
            int r4 = r4 + 1
            int r6 = r0.width     // Catch:{ RuntimeException -> 0x00d0 }
            int r6 = r6 * r4
            r7 = r6
            r6 = r3
            goto L_0x0010
        L_0x00d0:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.codec.BmpImage.decodeRLE(boolean, byte[]):byte[]");
    }

    private int readUnsignedByte(InputStream inputStream2) throws IOException {
        return inputStream2.read() & 255;
    }

    private int readUnsignedShort(InputStream inputStream2) throws IOException {
        return ((readUnsignedByte(inputStream2) << 8) | readUnsignedByte(inputStream2)) & 65535;
    }

    private int readShort(InputStream inputStream2) throws IOException {
        return (readUnsignedByte(inputStream2) << 8) | readUnsignedByte(inputStream2);
    }

    private int readWord(InputStream inputStream2) throws IOException {
        return readUnsignedShort(inputStream2);
    }

    private long readUnsignedInt(InputStream inputStream2) throws IOException {
        int readUnsignedByte = readUnsignedByte(inputStream2);
        int readUnsignedByte2 = readUnsignedByte(inputStream2);
        return ((long) ((readUnsignedByte(inputStream2) << 24) | (readUnsignedByte(inputStream2) << 16) | (readUnsignedByte2 << 8) | readUnsignedByte)) & -1;
    }

    private int readInt(InputStream inputStream2) throws IOException {
        int readUnsignedByte = readUnsignedByte(inputStream2);
        int readUnsignedByte2 = readUnsignedByte(inputStream2);
        return (readUnsignedByte(inputStream2) << 24) | (readUnsignedByte(inputStream2) << 16) | (readUnsignedByte2 << 8) | readUnsignedByte;
    }

    private long readDWord(InputStream inputStream2) throws IOException {
        return readUnsignedInt(inputStream2);
    }

    private int readLong(InputStream inputStream2) throws IOException {
        return readInt(inputStream2);
    }
}
