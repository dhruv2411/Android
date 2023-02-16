package com.itextpdf.text.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.error_messages.MessageLocalization;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

public class PdfDeviceNColor implements ICachedColorSpace, IPdfSpecialColorSpace {
    ColorDetails[] colorantsDetails;
    PdfSpotColor[] spotColors;

    public PdfDeviceNColor(PdfSpotColor[] pdfSpotColorArr) {
        this.spotColors = pdfSpotColorArr;
    }

    public int getNumberOfColorants() {
        return this.spotColors.length;
    }

    public PdfSpotColor[] getSpotColors() {
        return this.spotColors;
    }

    public ColorDetails[] getColorantDetails(PdfWriter pdfWriter) {
        if (this.colorantsDetails == null) {
            this.colorantsDetails = new ColorDetails[this.spotColors.length];
            int i = 0;
            for (PdfSpotColor addSimple : this.spotColors) {
                this.colorantsDetails[i] = pdfWriter.addSimple((ICachedColorSpace) addSimple);
                i++;
            }
        }
        return this.colorantsDetails;
    }

    public PdfObject getPdfObject(PdfWriter pdfWriter) {
        float f;
        float f2;
        float f3;
        PdfWriter pdfWriter2 = pdfWriter;
        PdfArray pdfArray = new PdfArray((PdfObject) PdfName.DEVICEN);
        PdfArray pdfArray2 = new PdfArray();
        int i = 2;
        float[] fArr = new float[(this.spotColors.length * 2)];
        PdfDictionary pdfDictionary = new PdfDictionary();
        int length = this.spotColors.length;
        float[][] fArr2 = (float[][]) Array.newInstance(float.class, new int[]{4, length});
        String str = "";
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i2 < length) {
                PdfSpotColor pdfSpotColor = this.spotColors[i2];
                int i4 = i * i2;
                fArr[i4] = 0.0f;
                float f4 = 1.0f;
                fArr[i4 + 1] = 1.0f;
                pdfArray2.add((PdfObject) pdfSpotColor.getName());
                if (pdfDictionary.get(pdfSpotColor.getName()) != null) {
                    throw new RuntimeException(MessageLocalization.getComposedMessage("devicen.component.names.shall.be.different", new Object[0]));
                }
                if (this.colorantsDetails != null) {
                    pdfDictionary.put(pdfSpotColor.getName(), this.colorantsDetails[i2].getIndirectReference());
                } else {
                    pdfDictionary.put(pdfSpotColor.getName(), pdfSpotColor.getPdfObject(pdfWriter2));
                }
                BaseColor alternativeCS = pdfSpotColor.getAlternativeCS();
                if (alternativeCS instanceof ExtendedColor) {
                    int i5 = ((ExtendedColor) alternativeCS).type;
                    if (i5 != 7) {
                        switch (i5) {
                            case 1:
                                fArr2[0][i2] = 0.0f;
                                fArr2[1][i2] = 0.0f;
                                fArr2[i][i2] = 0.0f;
                                fArr2[3][i2] = 1.0f - ((GrayColor) alternativeCS).getGray();
                                break;
                            case 2:
                                CMYKColor cMYKColor = (CMYKColor) alternativeCS;
                                fArr2[0][i2] = cMYKColor.getCyan();
                                fArr2[1][i2] = cMYKColor.getMagenta();
                                fArr2[i][i2] = cMYKColor.getYellow();
                                fArr2[3][i2] = cMYKColor.getBlack();
                                break;
                            default:
                                throw new RuntimeException(MessageLocalization.getComposedMessage("only.rgb.gray.and.cmyk.are.supported.as.alternative.color.spaces", new Object[0]));
                        }
                    } else {
                        CMYKColor cmyk = ((LabColor) alternativeCS).toCmyk();
                        fArr2[0][i2] = cmyk.getCyan();
                        fArr2[1][i2] = cmyk.getMagenta();
                        fArr2[i][i2] = cmyk.getYellow();
                        fArr2[3][i2] = cmyk.getBlack();
                    }
                } else {
                    float red = (float) alternativeCS.getRed();
                    float green = (float) alternativeCS.getGreen();
                    float blue = (float) alternativeCS.getBlue();
                    if (red == 0.0f && green == 0.0f && blue == 0.0f) {
                        f3 = 0.0f;
                        f2 = 0.0f;
                        f = 0.0f;
                    } else {
                        float f5 = 1.0f - (red / 255.0f);
                        float f6 = 1.0f - (green / 255.0f);
                        float f7 = 1.0f - (blue / 255.0f);
                        float min = Math.min(f5, Math.min(f6, f7));
                        float f8 = 1.0f - min;
                        f2 = (f5 - min) / f8;
                        f = (f6 - min) / f8;
                        f3 = (f7 - min) / f8;
                        f4 = min;
                    }
                    fArr2[0][i2] = f2;
                    fArr2[1][i2] = f;
                    fArr2[2][i2] = f3;
                    fArr2[3][i2] = f4;
                }
                str = str + "pop ";
                i2++;
                i = 2;
            } else {
                pdfArray.add((PdfObject) pdfArray2);
                String format = String.format(Locale.US, "1.000000 %d 1 roll ", new Object[]{Integer.valueOf(length + 1)});
                pdfArray.add((PdfObject) PdfName.DEVICECMYK);
                String str2 = format + format + format + format;
                int i6 = length + 4;
                String str3 = "";
                int i7 = i6;
                while (i7 > length) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str3);
                    Locale locale = Locale.US;
                    Object[] objArr = new Object[i3];
                    objArr[0] = Integer.valueOf(i7);
                    sb.append(String.format(locale, "%d -1 roll ", objArr));
                    String sb2 = sb.toString();
                    int i8 = length;
                    while (i8 > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(sb2);
                        sb3.append(String.format(Locale.US, "%d index %f mul 1.000000 cvr exch sub mul ", new Object[]{Integer.valueOf(i8), Float.valueOf(fArr2[i6 - i7][length - i8])}));
                        sb2 = sb3.toString();
                        i8--;
                        pdfDictionary = pdfDictionary;
                    }
                    str3 = sb2 + String.format(Locale.US, "1.000000 cvr exch sub %d 1 roll ", new Object[]{Integer.valueOf(i7)});
                    i7--;
                    i3 = 1;
                    pdfDictionary = pdfDictionary;
                }
                pdfArray.add((PdfObject) PdfFunction.type4(pdfWriter2, fArr, new float[]{0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f}, "{ " + str2 + str3 + str + "}").getReference());
                PdfDictionary pdfDictionary2 = new PdfDictionary();
                pdfDictionary2.put(PdfName.SUBTYPE, PdfName.NCHANNEL);
                pdfDictionary2.put(PdfName.COLORANTS, pdfDictionary);
                pdfArray.add((PdfObject) pdfDictionary2);
                return pdfArray;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PdfDeviceNColor) && Arrays.equals(this.spotColors, ((PdfDeviceNColor) obj).spotColors);
    }

    public int hashCode() {
        return Arrays.hashCode(this.spotColors);
    }
}
