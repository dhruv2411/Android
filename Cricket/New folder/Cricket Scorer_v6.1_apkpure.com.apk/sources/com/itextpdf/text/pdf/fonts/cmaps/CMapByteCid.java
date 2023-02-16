package com.itextpdf.text.pdf.fonts.cmaps;

import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfString;
import java.util.ArrayList;

public class CMapByteCid extends AbstractCMap {
    private ArrayList<char[]> planes = new ArrayList<>();

    public CMapByteCid() {
        this.planes.add(new char[256]);
    }

    /* access modifiers changed from: package-private */
    public void addChar(PdfString pdfString, PdfObject pdfObject) {
        if (pdfObject instanceof PdfNumber) {
            encodeSequence(decodeStringToByte(pdfString), (char) ((PdfNumber) pdfObject).intValue());
        }
    }

    private void encodeSequence(byte[] bArr, char c) {
        int length = bArr.length - 1;
        int i = 0;
        char c2 = 0;
        while (i < length) {
            char[] cArr = this.planes.get(c2);
            byte b = bArr[i] & 255;
            char c3 = cArr[b];
            if (c3 == 0 || (c3 & 32768) != 0) {
                if (c3 == 0) {
                    this.planes.add(new char[256]);
                    c3 = (char) (32768 | (this.planes.size() - 1));
                    cArr[b] = c3;
                }
                c2 = c3 & BaseFont.CID_NEWLINE;
                i++;
            } else {
                throw new RuntimeException(MessageLocalization.getComposedMessage("inconsistent.mapping", new Object[0]));
            }
        }
        char[] cArr2 = this.planes.get(c2);
        byte b2 = bArr[length] & 255;
        if ((cArr2[b2] & 32768) != 0) {
            throw new RuntimeException(MessageLocalization.getComposedMessage("inconsistent.mapping", new Object[0]));
        }
        cArr2[b2] = c;
    }

    public int decodeSingle(CMapSequence cMapSequence) {
        int i = cMapSequence.off + cMapSequence.len;
        char c = 0;
        while (cMapSequence.off < i) {
            byte[] bArr = cMapSequence.seq;
            int i2 = cMapSequence.off;
            cMapSequence.off = i2 + 1;
            cMapSequence.len--;
            char c2 = this.planes.get(c)[bArr[i2] & 255];
            if ((32768 & c2) == 0) {
                return c2;
            }
            c = c2 & BaseFont.CID_NEWLINE;
        }
        return -1;
    }

    public String decodeSequence(CMapSequence cMapSequence) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int decodeSingle = decodeSingle(cMapSequence);
            if (decodeSingle < 0) {
                return sb.toString();
            }
            sb.append((char) decodeSingle);
        }
    }
}
