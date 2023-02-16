package com.itextpdf.text.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.codec.CCITTG4Encoder;
import com.itextpdf.text.pdf.qrcode.ByteMatrix;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import java.util.Map;

public class BarcodeQRCode {
    ByteMatrix bm;

    public BarcodeQRCode(String str, int i, int i2, Map<EncodeHintType, Object> map) {
        try {
            this.bm = new QRCodeWriter().encode(str, i, i2, map);
        } catch (WriterException e) {
            throw new ExceptionConverter(e);
        }
    }

    private byte[] getBitMatrix() {
        int width = this.bm.getWidth();
        int height = this.bm.getHeight();
        int i = (width + 7) / 8;
        byte[] bArr = new byte[(i * height)];
        byte[][] array = this.bm.getArray();
        for (int i2 = 0; i2 < height; i2++) {
            byte[] bArr2 = array[i2];
            for (int i3 = 0; i3 < width; i3++) {
                if (bArr2[i3] != 0) {
                    int i4 = (i * i2) + (i3 / 8);
                    bArr[i4] = (byte) (bArr[i4] | ((byte) (128 >> (i3 % 8))));
                }
            }
        }
        return bArr;
    }

    public Image getImage() throws BadElementException {
        return Image.getInstance(this.bm.getWidth(), this.bm.getHeight(), false, 256, 1, CCITTG4Encoder.compress(getBitMatrix(), this.bm.getWidth(), this.bm.getHeight()), (int[]) null);
    }
}
