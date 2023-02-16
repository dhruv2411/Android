package com.itextpdf.text.pdf;

import android.support.v4.app.FrameMetricsAggregator;
import android.support.v7.widget.helper.ItemTouchHelper;
import apps.shehryar.com.cricketscroingappPro.BuildConfig;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.pdf.codec.CCITTG4Encoder;
import com.itextpdf.text.pdf.codec.TIFFConstants;
import com.itextpdf.text.pdf.codec.wmf.MetaDo;
import com.itextpdf.xmp.XMPError;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;

public class BarcodePDF417 {
    protected static final int ABSOLUTE_MAX_TEXT_SIZE = 5420;
    protected static final int AL = 28;
    protected static final int ALPHA = 65536;
    protected static final int AS = 27;
    protected static final int BYTESHIFT = 913;
    protected static final int BYTE_MODE = 901;
    protected static final int BYTE_MODE_6 = 924;
    private static final int[][] CLUSTERS;
    private static final int[][] ERROR_LEVEL = {new int[]{27, 917}, new int[]{MetaDo.META_SETTEXTJUSTIFICATION, 568, 723, 809}, new int[]{Jpeg.M_APPD, 308, 436, TIFFConstants.TIFFTAG_PLANARCONFIG, 646, 653, 428, 379}, new int[]{TIFFConstants.TIFFTAG_ORIENTATION, 562, 232, 755, 599, MetaDo.META_SETWINDOWEXT, 801, 132, MetaDo.META_RESTOREDC, 116, 442, 428, MetaDo.META_RESTOREDC, 42, 176, 65}, new int[]{361, 575, MACRO_LAST_SEGMENT, MetaDo.META_SETVIEWPORTORG, 176, 586, 640, TIFFConstants.TIFFTAG_HALFTONEHINTS, 536, 742, 677, 742, 687, TIFFConstants.TIFFTAG_PLANARCONFIG, 193, TIFFConstants.TIFFTAG_JPEGLOSSLESSPREDICTORS, TIFFConstants.TIFFTAG_STRIPOFFSETS, 494, 263, 147, 593, 800, 571, TIFFConstants.TIFFTAG_COLORMAP, 803, 133, 231, 390, 685, TIFFConstants.TIFFTAG_SUBIFD, 63, 410}, new int[]{539, 422, 6, 93, 862, 771, 453, 106, 610, TIFFConstants.TIFFTAG_YPOSITION, 107, MetaDo.META_CREATEPATTERNBRUSH, 733, 877, 381, 612, 723, 476, 462, 172, 430, 609, 858, 822, 543, 376, FrameMetricsAggregator.EVERY_DURATION, 400, 672, MetaDo.META_CREATEPENINDIRECT, TIFFConstants.TIFFTAG_YRESOLUTION, 184, 440, 35, TIFFConstants.TIFFTAG_JPEGQTABLES, 31, 460, 594, 225, 535, TIFFConstants.TIFFTAG_JPEGLOSSLESSPREDICTORS, 352, 605, 158, 651, XMPError.BADXML, 488, 502, 648, 733, 717, 83, 404, 97, TIFFConstants.TIFFTAG_MINSAMPLEVALUE, 771, 840, 629, 4, 381, 843, 623, 264, 543}, new int[]{521, 310, 864, 547, 858, 580, TIFFConstants.TIFFTAG_RESOLUTIONUNIT, 379, 53, 779, 897, 444, 400, 925, 749, 415, 822, 93, 217, 208, MACRO_SEGMENT_ID, 244, 583, 620, 246, 148, 447, 631, TIFFConstants.TIFFTAG_GROUP3OPTIONS, 908, 490, 704, 516, 258, 457, 907, 594, 723, 674, TIFFConstants.TIFFTAG_GROUP3OPTIONS, TIFFConstants.TIFFTAG_MODEL, 96, 684, 432, 686, 606, 860, 569, 193, 219, TsExtractor.TS_STREAM_TYPE_AC3, 186, 236, TIFFConstants.TIFFTAG_YPOSITION, PsExtractor.AUDIO_STREAM, 775, TIFFConstants.TIFFTAG_ROWSPERSTRIP, 173, 40, 379, 712, 463, 646, 776, 171, 491, TIFFConstants.TIFFTAG_PAGENUMBER, MetaDo.META_CREATEFONTINDIRECT, 156, 732, 95, TIFFConstants.TIFFTAG_IMAGEDESCRIPTION, 447, 90, 507, 48, 228, 821, 808, 898, 784, 663, 627, 378, 382, 262, 380, 602, 754, TIFFConstants.TIFFTAG_DOTRANGE, 89, 614, 87, 432, 670, 616, 157, 374, 242, 726, SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT, TIFFConstants.TIFFTAG_DOCUMENTNAME, 375, 898, 845, 454, 354, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 814, 587, MetaDo.META_POLYGON, 34, 211, TIFFConstants.TIFFTAG_SUBIFD, 539, TIFFConstants.TIFFTAG_PAGENUMBER, 827, 865, 37, TIFFConstants.TIFFTAG_JPEGLOSSLESSPREDICTORS, 834, TIFFConstants.TIFFTAG_ARTIST, 550, 86, 801, 4, 108, 539}, new int[]{MetaDo.META_SETWINDOWEXT, 894, 75, 766, 882, 857, 74, XMPError.BADSTREAM, 82, 586, 708, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 905, 786, TsExtractor.TS_STREAM_TYPE_DTS, 720, 858, 194, 311, BYTESHIFT, 275, 190, 375, 850, 438, 733, 194, TIFFConstants.TIFFTAG_MINSAMPLEVALUE, XMPError.BADXML, TIFFConstants.TIFFTAG_MINSAMPLEVALUE, 828, 757, 710, 814, 919, 89, 68, 569, 11, XMPError.BADSTREAM, 796, 605, 540, BYTESHIFT, 801, 700, 799, BuildConfig.VERSION_CODE, 439, 418, 592, 668, 353, 859, 370, 694, TIFFConstants.TIFFTAG_TILEBYTECOUNTS, PsExtractor.VIDEO_STREAM_MASK, 216, 257, TIFFConstants.TIFFTAG_PLANARCONFIG, 549, 209, 884, TIFFConstants.TIFFTAG_ARTIST, 70, 329, 793, 490, TIFFConstants.TIFFTAG_ORIENTATION, 877, 162, 749, 812, 684, 461, TIFFConstants.TIFFTAG_NUMBEROFINKS, 376, 849, 521, 307, TIFFConstants.TIFFTAG_GRAYRESPONSECURVE, 803, 712, 19, 358, 399, 908, 103, FrameMetricsAggregator.EVERY_DURATION, 51, 8, TIFFConstants.TIFFTAG_JPEGLOSSLESSPREDICTORS, 225, TIFFConstants.TIFFTAG_FREEBYTECOUNTS, 470, 637, 731, 66, 255, 917, TIFFConstants.TIFFTAG_DOCUMENTNAME, 463, 830, 730, 433, 848, 585, 136, 538, 906, 90, 2, TIFFConstants.TIFFTAG_GRAYRESPONSEUNIT, 743, 199, 655, 903, 329, 49, 802, 580, 355, 588, 188, 462, 10, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 628, TIFFConstants.TIFFTAG_COLORMAP, 479, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 739, 71, 263, TIFFConstants.TIFFTAG_WHITEPOINT, 374, 601, PsExtractor.AUDIO_STREAM, 605, 142, 673, 687, 234, 722, 384, 177, 752, 607, 640, 455, 193, 689, 707, MetaDo.META_POLYLINE, 641, 48, 60, 732, 621, 895, MetaDo.META_OFFSETCLIPRGN, MetaDo.META_SETRELABS, 852, 655, 309, 697, 755, 756, 60, 231, 773, 434, 421, 726, 528, 503, 118, 49, 795, 32, 144, 500, Jpeg.M_APPE, 836, 394, TIFFConstants.TIFFTAG_MINSAMPLEVALUE, 566, TIFFConstants.TIFFTAG_PRIMARYCHROMATICITIES, 9, 647, 550, 73, 914, 342, 126, 32, 681, 331, 792, 620, 60, 609, 441, 180, 791, 893, 754, 605, 383, 228, 749, 760, 213, 54, TIFFConstants.TIFFTAG_PAGENUMBER, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 54, 834, MetaDo.META_PAINTREGION, MACRO_LAST_SEGMENT, 191, 910, 532, 609, 829, PsExtractor.PRIVATE_STREAM_1, 20, 167, 29, 872, 449, 83, 402, 41, 656, MetaDo.META_CREATEPATTERNBRUSH, 579, 481, 173, 404, 251, 688, 95, 497, 555, 642, 543, 307, 159, BYTE_MODE_6, 558, 648, 55, 497, 10}, new int[]{352, 77, 373, 504, 35, 599, 428, 207, 409, 574, 118, 498, TIFFConstants.TIFFTAG_PAGENAME, 380, 350, 492, 197, TIFFConstants.TIFFTAG_CELLLENGTH, 920, 155, 914, MetaDo.META_PAINTREGION, 229, 643, 294, 871, TIFFConstants.TIFFTAG_DATETIME, 88, 87, 193, 352, 781, 846, 75, TIFFConstants.TIFFTAG_CLEANFAXDATA, TIFFConstants.TIFFTAG_JPEGDCTABLES, 435, 543, XMPError.BADXMP, Element.WRITABLE_DIRECT, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, 408, 390, 644, 102, 476, 499, TIFFConstants.TIFFTAG_GRAYRESPONSEUNIT, 632, 545, 37, 858, 916, MetaDo.META_FILLREGION, 41, 542, TIFFConstants.TIFFTAG_FREEBYTECOUNTS, 122, TIFFConstants.TIFFTAG_MODEL, 383, 800, 485, 98, 752, 472, 761, 107, 784, 860, 658, 741, TIFFConstants.TIFFTAG_GRAYRESPONSEUNIT, XMPError.BADSTREAM, 681, 407, 855, 85, 99, 62, 482, 180, 20, TIFFConstants.TIFFTAG_PAGENUMBER, 451, 593, BYTESHIFT, 142, 808, 684, TIFFConstants.TIFFTAG_YPOSITION, 536, MetaDo.META_SETMAPPERFLAGS, 76, 653, 899, 729, 567, 744, 390, 513, PsExtractor.AUDIO_STREAM, 516, 258, PsExtractor.VIDEO_STREAM_MASK, TIFFConstants.TIFFTAG_JPEGPOINTTRANSFORM, 794, 395, 768, 848, 51, 610, 384, 168, 190, 826, TIFFConstants.TIFFTAG_CONSECUTIVEBADFAXLINES, 596, 786, 303, 570, 381, 415, 641, 156, Jpeg.M_APPD, 151, 429, 531, 207, 676, 710, 89, 168, 304, 402, 40, 708, 575, 162, 864, 229, 65, 861, 841, 512, 164, 477, 221, 92, 358, 785, TIFFConstants.TIFFTAG_FREEOFFSETS, 357, 850, 836, 827, 736, 707, 94, 8, 494, 114, 521, 2, 499, 851, 543, 152, 729, 771, 95, 248, 361, 578, TIFFConstants.TIFFTAG_TILELENGTH, 856, 797, TIFFConstants.TIFFTAG_FREEBYTECOUNTS, 51, 684, 466, 533, 820, 669, 45, NUMERIC_MODE, 452, 167, 342, 244, 173, 35, 463, 651, 51, 699, 591, 452, 578, 37, 124, MetaDo.META_INVERTREGION, TIFFConstants.TIFFTAG_INKSET, MetaDo.META_FILLREGION, 43, 427, 119, 662, 777, 475, 850, MetaDo.META_CREATEBRUSHINDIRECT, 364, 578, 911, TIFFConstants.TIFFTAG_YRESOLUTION, 711, 472, 420, 245, TIFFConstants.TIFFTAG_FREEOFFSETS, 594, 394, FrameMetricsAggregator.EVERY_DURATION, TIFFConstants.TIFFTAG_CLEANFAXDATA, 589, 777, 699, 688, 43, 408, 842, 383, 721, 521, 560, 644, 714, 559, 62, 145, 873, 663, 713, 159, 672, 729, 624, 59, 193, 417, 158, 209, 563, MetaDo.META_SELECTPALETTE, 343, 693, 109, 608, 563, 365, 181, 772, 677, 310, 248, 353, 708, 410, 579, 870, 617, 841, 632, 860, TIFFConstants.TIFFTAG_FREEBYTECOUNTS, 536, 35, 777, 618, 586, 424, 833, 77, 597, 346, TIFFConstants.TIFFTAG_DOCUMENTNAME, 757, 632, 695, 751, 331, MetaDo.META_CREATEPALETTE, 184, 45, 787, 680, 18, 66, 407, 369, 54, 492, 228, 613, 830, MACRO_LAST_SEGMENT, 437, TIFFConstants.TIFFTAG_JPEGQTABLES, 644, 905, 789, 420, TIFFConstants.TIFFTAG_SOFTWARE, 441, 207, 300, 892, 827, 141, 537, 381, 662, 513, 56, 252, TIFFConstants.TIFFTAG_SMAXSAMPLEVALUE, 242, 797, 838, 837, 720, 224, 307, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, 425, 216, 548, 249, TIFFConstants.TIFFTAG_HALFTONEHINTS, 881, 699, 535, 673, 782, 210, 815, 905, 303, 843, MACRO_LAST_SEGMENT, TIFFConstants.TIFFTAG_MAXSAMPLEVALUE, 73, 469, 791, 660, 162, 498, 308, 155, 422, 907, 817, 187, 62, 16, 425, 535, TIFFConstants.TIFFTAG_DOTRANGE, TIFFConstants.TIFFTAG_XPOSITION, 437, 375, TIFFConstants.TIFFTAG_STRIPOFFSETS, 610, TIFFConstants.TIFFTAG_RESOLUTIONUNIT, 183, 923, 116, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, TIFFConstants.TIFFTAG_SUBIFD, 5, 39, 923, 311, 424, 242, 749, TIFFConstants.TIFFTAG_HALFTONEHINTS, 54, 669, TIFFConstants.TIFFTAG_HOSTCOMPUTER, 342, MetaDo.META_PAINTREGION, 534, 105, 667, 488, 640, 672, 576, 540, TIFFConstants.TIFFTAG_HOSTCOMPUTER, 486, 721, 610, 46, 656, 447, 171, 616, 464, 190, 531, TIFFConstants.TIFFTAG_PAGENUMBER, TIFFConstants.TIFFTAG_HALFTONEHINTS, MetaDo.META_CREATEPENINDIRECT, 752, 533, 175, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 14, 381, 433, 717, 45, 111, 20, 596, TIFFConstants.TIFFTAG_PLANARCONFIG, 736, TsExtractor.TS_STREAM_TYPE_DTS, 646, 411, 877, 669, 141, 919, 45, 780, 407, 164, TIFFConstants.TIFFTAG_INKSET, 899, 165, 726, SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT, TIFFConstants.TIFFTAG_TILEBYTECOUNTS, 498, 655, 357, 752, 768, 223, 849, 647, 63, 310, 863, 251, 366, 304, TIFFConstants.TIFFTAG_XRESOLUTION, 738, 675, 410, 389, 244, 31, 121, 303, 263}};
    protected static final int ISBYTE = 1048576;
    protected static final int LL = 27;
    protected static final int LOWER = 131072;
    protected static final int MACRO_LAST_SEGMENT = 922;
    protected static final int MACRO_SEGMENT_ID = 928;
    protected static final int MAX_DATA_CODEWORDS = 926;
    protected static final int MIXED = 262144;
    private static final String MIXED_SET = "0123456789&\r\t,:#-.$/+%*=^";
    protected static final int ML = 28;
    protected static final int MOD = 929;
    protected static final int NUMERIC_MODE = 902;
    protected static final int PAL = 29;
    public static final int PDF417_AUTO_ERROR_LEVEL = 0;
    public static final int PDF417_FIXED_COLUMNS = 2;
    public static final int PDF417_FIXED_RECTANGLE = 1;
    public static final int PDF417_FIXED_ROWS = 4;
    public static final int PDF417_FORCE_BINARY = 32;
    public static final int PDF417_INVERT_BITMAP = 128;
    public static final int PDF417_USE_ASPECT_RATIO = 0;
    public static final int PDF417_USE_ERROR_LEVEL = 16;
    public static final int PDF417_USE_MACRO = 256;
    public static final int PDF417_USE_RAW_CODEWORDS = 64;
    protected static final int PL = 25;
    protected static final int PS = 29;
    protected static final int PUNCTUATION = 524288;
    private static final String PUNCTUATION_SET = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'";
    protected static final int SPACE = 26;
    protected static final int START_CODE_SIZE = 17;
    protected static final int START_PATTERN = 130728;
    protected static final int STOP_PATTERN = 260649;
    protected static final int STOP_SIZE = 18;
    protected static final int TEXT_MODE = 900;
    private float aspectRatio;
    private int bitColumns;
    protected int bitPtr;
    private int codeColumns;
    private int codeRows;
    private int[] codewords = new int[MACRO_SEGMENT_ID];
    protected int cwPtr;
    private int errorLevel;
    private int lenCodewords;
    private String macroFileId;
    private int macroIndex;
    private int macroSegmentCount = 0;
    private int macroSegmentId = -1;
    private int options;
    private byte[] outBits;
    protected SegmentList segmentList;
    private byte[] text;
    private float yHeight;

    protected static int maxPossibleErrorLevel(int i) {
        int i2 = 8;
        int i3 = 512;
        while (i2 > 0) {
            if (i >= i3) {
                return i2;
            }
            i2--;
            i3 >>= 1;
        }
        return 0;
    }

    public BarcodePDF417() {
        setDefaultParameters();
    }

    public void setMacroSegmentId(int i) {
        this.macroSegmentId = i;
    }

    public void setMacroSegmentCount(int i) {
        this.macroSegmentCount = i;
    }

    public void setMacroFileId(String str) {
        this.macroFileId = str;
    }

    /* access modifiers changed from: protected */
    public boolean checkSegmentType(Segment segment, char c) {
        return segment != null && segment.type == c;
    }

    /* access modifiers changed from: protected */
    public int getSegmentLength(Segment segment) {
        if (segment == null) {
            return 0;
        }
        return segment.end - segment.start;
    }

    public void setDefaultParameters() {
        this.options = 0;
        this.outBits = null;
        this.text = new byte[0];
        this.yHeight = 3.0f;
        this.aspectRatio = 0.5f;
    }

    /* access modifiers changed from: protected */
    public void outCodeword17(int i) {
        int i2 = this.bitPtr / 8;
        int i3 = this.bitPtr - (i2 * 8);
        byte[] bArr = this.outBits;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (bArr[i2] | (i >> (9 + i3)));
        byte[] bArr2 = this.outBits;
        int i5 = i4 + 1;
        int i6 = 1 + i3;
        bArr2[i4] = (byte) ((i >> i6) | bArr2[i4]);
        byte[] bArr3 = this.outBits;
        bArr3[i5] = (byte) (((i << 8) >> i6) | bArr3[i5]);
        this.bitPtr += 17;
    }

    /* access modifiers changed from: protected */
    public void outCodeword18(int i) {
        int i2 = this.bitPtr / 8;
        int i3 = this.bitPtr - (i2 * 8);
        byte[] bArr = this.outBits;
        int i4 = i2 + 1;
        bArr[i2] = (byte) (bArr[i2] | (i >> (10 + i3)));
        byte[] bArr2 = this.outBits;
        int i5 = i4 + 1;
        int i6 = 2 + i3;
        bArr2[i4] = (byte) (bArr2[i4] | (i >> i6));
        byte[] bArr3 = this.outBits;
        bArr3[i5] = (byte) (((i << 8) >> i6) | bArr3[i5]);
        if (i3 == 7) {
            byte[] bArr4 = this.outBits;
            int i7 = i5 + 1;
            bArr4[i7] = (byte) (bArr4[i7] | 128);
        }
        this.bitPtr += 18;
    }

    /* access modifiers changed from: protected */
    public void outCodeword(int i) {
        outCodeword17(i);
    }

    /* access modifiers changed from: protected */
    public void outStopPattern() {
        outCodeword18(STOP_PATTERN);
    }

    /* access modifiers changed from: protected */
    public void outStartPattern() {
        outCodeword17(START_PATTERN);
    }

    /* access modifiers changed from: protected */
    public void outPaintCode() {
        int i;
        int i2;
        this.bitColumns = (17 * (this.codeColumns + 3)) + 18;
        this.outBits = new byte[((((this.bitColumns - 1) / 8) + 1) * this.codeRows)];
        int i3 = 0;
        int i4 = 0;
        while (i3 < this.codeRows) {
            this.bitPtr = (((this.bitColumns - 1) / 8) + 1) * 8 * i3;
            int i5 = i3 % 3;
            int[] iArr = CLUSTERS[i5];
            outStartPattern();
            switch (i5) {
                case 0:
                    i = ((i3 / 3) * 30) + ((this.codeRows - 1) / 3);
                    break;
                case 1:
                    i = ((i3 / 3) * 30) + (this.errorLevel * 3) + ((this.codeRows - 1) % 3);
                    break;
                default:
                    i = (((i3 / 3) * 30) + this.codeColumns) - 1;
                    break;
            }
            outCodeword(iArr[i]);
            int i6 = i4;
            int i7 = 0;
            while (i7 < this.codeColumns) {
                outCodeword(iArr[this.codewords[i6]]);
                i7++;
                i6++;
            }
            switch (i5) {
                case 0:
                    i2 = ((30 * (i3 / 3)) + this.codeColumns) - 1;
                    break;
                case 1:
                    i2 = (30 * (i3 / 3)) + ((this.codeRows - 1) / 3);
                    break;
                default:
                    i2 = (30 * (i3 / 3)) + (this.errorLevel * 3) + ((this.codeRows - 1) % 3);
                    break;
            }
            outCodeword(iArr[i2]);
            outStopPattern();
            i3++;
            i4 = i6;
        }
        if ((this.options & 128) != 0) {
            for (int i8 = 0; i8 < this.outBits.length; i8++) {
                byte[] bArr = this.outBits;
                bArr[i8] = (byte) (bArr[i8] ^ 255);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void calculateErrorCorrection(int i) {
        if (this.errorLevel < 0 || this.errorLevel > 8) {
            this.errorLevel = 0;
        }
        int[] iArr = ERROR_LEVEL[this.errorLevel];
        int i2 = 2 << this.errorLevel;
        for (int i3 = 0; i3 < i2; i3++) {
            this.codewords[i + i3] = 0;
        }
        int i4 = i2 - 1;
        for (int i5 = 0; i5 < this.lenCodewords; i5++) {
            int i6 = this.codewords[i5] + this.codewords[i];
            int i7 = 0;
            while (i7 <= i4) {
                int i8 = i + i7;
                this.codewords[i8] = ((i7 == i4 ? 0 : this.codewords[i8 + 1]) + (929 - ((iArr[i4 - i7] * i6) % MOD))) % MOD;
                i7++;
            }
        }
        for (int i9 = 0; i9 < i2; i9++) {
            int i10 = i + i9;
            this.codewords[i10] = (929 - this.codewords[i10]) % MOD;
        }
    }

    private static int getTextTypeAndValue(byte[] bArr, int i, int i2) {
        if (i2 >= i) {
            return 0;
        }
        char c = (char) (bArr[i2] & 255);
        if (c >= 'A' && c <= 'Z') {
            return (0 + c) - 65;
        }
        if (c >= 'a' && c <= 'z') {
            return (0 + c) - 97;
        }
        if (c == ' ') {
            return 458778;
        }
        int indexOf = MIXED_SET.indexOf(c);
        int indexOf2 = PUNCTUATION_SET.indexOf(c);
        if (indexOf < 0 && indexOf2 < 0) {
            return 0 + c;
        }
        if (indexOf == indexOf2) {
            return 786432 + indexOf;
        }
        return indexOf >= 0 ? 262144 + indexOf : 524288 + indexOf2;
    }

    /* access modifiers changed from: protected */
    public int getTextTypeAndValue(int i, int i2) {
        return getTextTypeAndValue(this.text, i, i2);
    }

    private void textCompaction(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        byte[] bArr2 = bArr;
        int[] iArr = new int[10840];
        int i11 = i2 + i;
        int i12 = 0;
        int i13 = i;
        int i14 = 0;
        int i15 = 0;
        int i16 = 65536;
        while (i13 < i11) {
            int textTypeAndValue = getTextTypeAndValue(bArr2, i11, i13);
            if ((textTypeAndValue & i16) != 0) {
                i10 = i14 + 1;
                iArr[i14] = textTypeAndValue & 255;
            } else if ((1048576 & textTypeAndValue) != 0) {
                if ((i14 & 1) != 0) {
                    i9 = i14 + 1;
                    iArr[i14] = 29;
                    if ((i16 & 524288) != 0) {
                        i16 = 65536;
                    }
                } else {
                    i9 = i14;
                }
                int i17 = i9 + 1;
                iArr[i9] = BYTESHIFT;
                i10 = i17 + 1;
                iArr[i17] = textTypeAndValue & 255;
                i15 += 2;
            } else {
                if (i16 != 65536) {
                    if (i16 != 131072) {
                        if (i16 != 262144) {
                            if (i16 == 524288) {
                                iArr[i14] = 29;
                                i13--;
                                i14++;
                            }
                        } else if ((textTypeAndValue & 131072) != 0) {
                            int i18 = i14 + 1;
                            iArr[i14] = 27;
                            i7 = i18 + 1;
                            iArr[i18] = textTypeAndValue & 255;
                        } else if ((textTypeAndValue & 65536) != 0) {
                            int i19 = i14 + 1;
                            iArr[i14] = 28;
                            i14 = i19 + 1;
                            iArr[i19] = textTypeAndValue & 255;
                        } else if ((getTextTypeAndValue(bArr2, i11, i13 + 1) & getTextTypeAndValue(bArr2, i11, i13 + 2) & 524288) != 0) {
                            int i20 = i14 + 1;
                            iArr[i14] = 25;
                            i5 = i20 + 1;
                            iArr[i20] = textTypeAndValue & 255;
                            i16 = 524288;
                        } else {
                            int i21 = i14 + 1;
                            iArr[i14] = 29;
                            i14 = i21 + 1;
                            iArr[i21] = textTypeAndValue & 255;
                        }
                        i16 = 65536;
                    } else if ((textTypeAndValue & 65536) != 0) {
                        if ((getTextTypeAndValue(bArr2, i11, i13 + 1) & getTextTypeAndValue(bArr2, i11, i13 + 2) & 65536) != 0) {
                            int i22 = i14 + 1;
                            iArr[i14] = 28;
                            int i23 = i22 + 1;
                            iArr[i22] = 28;
                            i16 = 65536;
                            i8 = i23;
                        } else {
                            i8 = i14 + 1;
                            iArr[i14] = 27;
                        }
                        i14 = i8 + 1;
                        iArr[i8] = textTypeAndValue & 255;
                    } else if ((textTypeAndValue & 262144) != 0) {
                        int i24 = i14 + 1;
                        iArr[i14] = 28;
                        i6 = i24 + 1;
                        iArr[i24] = textTypeAndValue & 255;
                        i16 = 262144;
                    } else if ((getTextTypeAndValue(bArr2, i11, i13 + 1) & getTextTypeAndValue(bArr2, i11, i13 + 2) & 524288) != 0) {
                        int i25 = i14 + 1;
                        iArr[i14] = 28;
                        int i26 = i25 + 1;
                        iArr[i25] = 25;
                        i4 = i26 + 1;
                        iArr[i26] = textTypeAndValue & 255;
                        i5 = i4;
                        i16 = 524288;
                    } else {
                        int i27 = i14 + 1;
                        iArr[i14] = 29;
                        i14 = i27 + 1;
                        iArr[i27] = textTypeAndValue & 255;
                    }
                    i13++;
                } else if ((textTypeAndValue & 131072) != 0) {
                    int i28 = i14 + 1;
                    iArr[i14] = 27;
                    i7 = i28 + 1;
                    iArr[i28] = textTypeAndValue & 255;
                } else if ((textTypeAndValue & 262144) != 0) {
                    int i29 = i14 + 1;
                    iArr[i14] = 28;
                    i6 = i29 + 1;
                    iArr[i29] = textTypeAndValue & 255;
                    i16 = 262144;
                    i13++;
                } else if ((getTextTypeAndValue(bArr2, i11, i13 + 1) & getTextTypeAndValue(bArr2, i11, i13 + 2) & 524288) != 0) {
                    int i30 = i14 + 1;
                    iArr[i14] = 28;
                    int i31 = i30 + 1;
                    iArr[i30] = 25;
                    i4 = i31 + 1;
                    iArr[i31] = textTypeAndValue & 255;
                    i5 = i4;
                    i16 = 524288;
                    i13++;
                } else {
                    int i32 = i14 + 1;
                    iArr[i14] = 29;
                    i14 = i32 + 1;
                    iArr[i32] = textTypeAndValue & 255;
                    i13++;
                }
                i16 = 131072;
                i13++;
            }
            i14 = i10;
            i13++;
        }
        if ((i14 & 1) != 0) {
            i3 = i14 + 1;
            iArr[i14] = 29;
        } else {
            i3 = i14;
        }
        if (((i15 + i3) / 2) + this.cwPtr > MAX_DATA_CODEWORDS) {
            throw new IndexOutOfBoundsException(MessageLocalization.getComposedMessage("the.text.is.too.big", new Object[0]));
        }
        while (i12 < i3) {
            int i33 = i12 + 1;
            int i34 = iArr[i12];
            if (i34 >= 30) {
                int[] iArr2 = this.codewords;
                int i35 = this.cwPtr;
                this.cwPtr = i35 + 1;
                iArr2[i35] = i34;
                int[] iArr3 = this.codewords;
                int i36 = this.cwPtr;
                this.cwPtr = i36 + 1;
                iArr3[i36] = iArr[i33];
                i12 = i33 + 1;
            } else {
                int[] iArr4 = this.codewords;
                int i37 = this.cwPtr;
                this.cwPtr = i37 + 1;
                iArr4[i37] = (i34 * 30) + iArr[i33];
                i12 = i33 + 1;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void textCompaction(int i, int i2) {
        textCompaction(this.text, i, i2);
    }

    /* access modifiers changed from: protected */
    public void basicNumberCompaction(int i, int i2) {
        basicNumberCompaction(this.text, i, i2);
    }

    private void basicNumberCompaction(byte[] bArr, int i, int i2) {
        int i3 = this.cwPtr;
        int i4 = i2 / 3;
        this.cwPtr += i4 + 1;
        for (int i5 = 0; i5 <= i4; i5++) {
            this.codewords[i3 + i5] = 0;
        }
        int i6 = i3 + i4;
        this.codewords[i6] = 1;
        int i7 = i2 + i;
        while (i < i7) {
            for (int i8 = i4; i8 >= 0; i8--) {
                int[] iArr = this.codewords;
                int i9 = i3 + i8;
                iArr[i9] = iArr[i9] * 10;
            }
            int[] iArr2 = this.codewords;
            iArr2[i6] = iArr2[i6] + (bArr[i] - 48);
            for (int i10 = i4; i10 > 0; i10--) {
                int[] iArr3 = this.codewords;
                int i11 = i3 + i10;
                int i12 = i11 - 1;
                iArr3[i12] = iArr3[i12] + (this.codewords[i11] / TEXT_MODE);
                int[] iArr4 = this.codewords;
                iArr4[i11] = iArr4[i11] % TEXT_MODE;
            }
            i++;
        }
    }

    private void numberCompaction(byte[] bArr, int i, int i2) {
        int i3 = (i2 / 44) * 15;
        int i4 = i2 % 44;
        if (i4 != 0) {
            i3 = i3 + (i4 / 3) + 1;
        }
        if (i3 + this.cwPtr > MAX_DATA_CODEWORDS) {
            throw new IndexOutOfBoundsException(MessageLocalization.getComposedMessage("the.text.is.too.big", new Object[0]));
        }
        int i5 = i2 + i;
        while (i < i5) {
            int i6 = i5 - i;
            if (i6 >= 44) {
                i6 = 44;
            }
            basicNumberCompaction(bArr, i, i6);
            i += 44;
        }
    }

    /* access modifiers changed from: protected */
    public void numberCompaction(int i, int i2) {
        numberCompaction(this.text, i, i2);
    }

    /* access modifiers changed from: protected */
    public void byteCompaction6(int i) {
        int i2 = this.cwPtr;
        this.cwPtr += 5;
        for (int i3 = 0; i3 <= 4; i3++) {
            this.codewords[i2 + i3] = 0;
        }
        int i4 = 6 + i;
        while (i < i4) {
            for (int i5 = 4; i5 >= 0; i5--) {
                int[] iArr = this.codewords;
                int i6 = i2 + i5;
                iArr[i6] = iArr[i6] * 256;
            }
            int[] iArr2 = this.codewords;
            int i7 = i2 + 4;
            iArr2[i7] = iArr2[i7] + (this.text[i] & 255);
            for (int i8 = 4; i8 > 0; i8--) {
                int[] iArr3 = this.codewords;
                int i9 = i2 + i8;
                int i10 = i9 - 1;
                iArr3[i10] = iArr3[i10] + (this.codewords[i9] / TEXT_MODE);
                int[] iArr4 = this.codewords;
                iArr4[i9] = iArr4[i9] % TEXT_MODE;
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public void byteCompaction(int i, int i2) {
        if (((i2 / 6) * 5) + (i2 % 6) + this.cwPtr > MAX_DATA_CODEWORDS) {
            throw new IndexOutOfBoundsException(MessageLocalization.getComposedMessage("the.text.is.too.big", new Object[0]));
        }
        int i3 = i2 + i;
        while (i < i3) {
            int i4 = i3 - i;
            if (i4 >= 44) {
                i4 = 6;
            }
            if (i4 < 6) {
                for (int i5 = 0; i5 < i4; i5++) {
                    int[] iArr = this.codewords;
                    int i6 = this.cwPtr;
                    this.cwPtr = i6 + 1;
                    iArr[i6] = this.text[i + i5] & 255;
                }
            } else {
                byteCompaction6(i);
            }
            i += 6;
        }
    }

    /* access modifiers changed from: package-private */
    public void breakString() {
        boolean z;
        boolean z2;
        char c = ' ';
        int length = this.text.length;
        if ((this.options & 32) != 0) {
            this.segmentList.add('B', 0, length);
            return;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            char c2 = (char) (this.text[i4] & 255);
            if (c2 < '0' || c2 > '9') {
                if (i >= 13) {
                    if (i2 != i3) {
                        char c3 = (char) (this.text[i2] & 255);
                        boolean z3 = (c3 >= ' ' && c3 < 127) || c3 == 13 || c3 == 10 || c3 == 9;
                        int i5 = i2;
                        while (i2 < i3) {
                            char c4 = (char) (this.text[i2] & 255);
                            boolean z4 = (c4 >= ' ' && c4 < 127) || c4 == 13 || c4 == 10 || c4 == 9;
                            if (z4 != z3) {
                                this.segmentList.add(z3 ? 'T' : 'B', i5, i2);
                                i5 = i2;
                                z3 = z4;
                            }
                            i2++;
                        }
                        this.segmentList.add(z3 ? 'T' : 'B', i5, i3);
                    }
                    this.segmentList.add('N', i3, i4);
                    i2 = i4;
                }
                i = 0;
            } else {
                if (i == 0) {
                    i3 = i4;
                }
                i++;
            }
        }
        if (i < 13) {
            i3 = length;
        }
        if (i2 != i3) {
            char c5 = (char) (this.text[i2] & 255);
            boolean z5 = (c5 >= ' ' && c5 < 127) || c5 == 13 || c5 == 10 || c5 == 9;
            int i6 = i2;
            while (i2 < i3) {
                char c6 = (char) (this.text[i2] & 255);
                boolean z6 = (c6 >= c && c6 < 127) || c6 == 13 || c6 == 10 || c6 == 9;
                if (z6 != z5) {
                    this.segmentList.add(z5 ? 'T' : 'B', i6, i2);
                    i6 = i2;
                    z5 = z6;
                }
                i2++;
                c = ' ';
            }
            this.segmentList.add(z5 ? 'T' : 'B', i6, i3);
        }
        if (i >= 13) {
            this.segmentList.add('N', i3, length);
        }
        int i7 = 0;
        while (i7 < this.segmentList.size()) {
            Segment segment = this.segmentList.get(i7);
            Segment segment2 = this.segmentList.get(i7 - 1);
            Segment segment3 = this.segmentList.get(i7 + 1);
            if (checkSegmentType(segment, 'B') && getSegmentLength(segment) == 1 && checkSegmentType(segment2, 'T') && checkSegmentType(segment3, 'T') && getSegmentLength(segment2) + getSegmentLength(segment3) >= 3) {
                segment2.end = segment3.end;
                this.segmentList.remove(i7);
                this.segmentList.remove(i7);
                i7 = -1;
            }
            i7++;
        }
        int i8 = 0;
        while (i8 < this.segmentList.size()) {
            Segment segment4 = this.segmentList.get(i8);
            int i9 = i8 - 1;
            Segment segment5 = this.segmentList.get(i9);
            Segment segment6 = this.segmentList.get(i8 + 1);
            if (checkSegmentType(segment4, 'T') && getSegmentLength(segment4) >= 5) {
                if ((!checkSegmentType(segment5, 'B') || getSegmentLength(segment5) != 1) && !checkSegmentType(segment5, 'T')) {
                    z2 = false;
                } else {
                    segment4.start = segment5.start;
                    this.segmentList.remove(i9);
                    i8--;
                    z2 = true;
                }
                if ((checkSegmentType(segment6, 'B') && getSegmentLength(segment6) == 1) || checkSegmentType(segment6, 'T')) {
                    segment4.end = segment6.end;
                    this.segmentList.remove(i8 + 1);
                    z2 = true;
                }
                if (z2) {
                    i8 = -1;
                }
            }
            i8++;
        }
        int i10 = 0;
        while (i10 < this.segmentList.size()) {
            Segment segment7 = this.segmentList.get(i10);
            int i11 = i10 - 1;
            Segment segment8 = this.segmentList.get(i11);
            Segment segment9 = this.segmentList.get(i10 + 1);
            if (checkSegmentType(segment7, 'B')) {
                if ((!checkSegmentType(segment8, 'T') || getSegmentLength(segment8) >= 5) && !checkSegmentType(segment8, 'B')) {
                    z = false;
                } else {
                    segment7.start = segment8.start;
                    this.segmentList.remove(i11);
                    i10--;
                    z = true;
                }
                if ((checkSegmentType(segment9, 'T') && getSegmentLength(segment9) < 5) || checkSegmentType(segment9, 'B')) {
                    segment7.end = segment9.end;
                    this.segmentList.remove(i10 + 1);
                    z = true;
                }
                if (z) {
                    i10 = -1;
                }
            }
            i10++;
        }
        if (this.segmentList.size() == 1) {
            Segment segment10 = this.segmentList.get(0);
            if (segment10.type == 'T' && getSegmentLength(segment10) >= 8) {
                int i12 = segment10.start;
                while (i12 < segment10.end && (r3 = (char) (this.text[i12] & 255)) >= '0' && r3 <= '9') {
                    i12++;
                }
                if (i12 == segment10.end) {
                    segment10.type = 'N';
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void assemble() {
        if (this.segmentList.size() != 0) {
            this.cwPtr = 1;
            for (int i = 0; i < this.segmentList.size(); i++) {
                Segment segment = this.segmentList.get(i);
                char c = segment.type;
                if (c == 'B') {
                    int[] iArr = this.codewords;
                    int i2 = this.cwPtr;
                    this.cwPtr = i2 + 1;
                    iArr[i2] = getSegmentLength(segment) % 6 != 0 ? BYTE_MODE : BYTE_MODE_6;
                    byteCompaction(segment.start, getSegmentLength(segment));
                } else if (c == 'N') {
                    int[] iArr2 = this.codewords;
                    int i3 = this.cwPtr;
                    this.cwPtr = i3 + 1;
                    iArr2[i3] = NUMERIC_MODE;
                    numberCompaction(segment.start, getSegmentLength(segment));
                } else if (c == 'T') {
                    if (i != 0) {
                        int[] iArr3 = this.codewords;
                        int i4 = this.cwPtr;
                        this.cwPtr = i4 + 1;
                        iArr3[i4] = TEXT_MODE;
                    }
                    textCompaction(segment.start, getSegmentLength(segment));
                }
            }
            if ((this.options & 256) != 0) {
                macroCodes();
            }
        }
    }

    private void macroCodes() {
        if (this.macroSegmentId < 0) {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("macrosegmentid.must.be.gt.eq.0", new Object[0]));
        } else if (this.macroSegmentId >= this.macroSegmentCount) {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("macrosegmentid.must.be.lt.macrosemgentcount", new Object[0]));
        } else if (this.macroSegmentCount < 1) {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("macrosemgentcount.must.be.gt.0", new Object[0]));
        } else {
            this.macroIndex = this.cwPtr;
            int[] iArr = this.codewords;
            int i = this.cwPtr;
            this.cwPtr = i + 1;
            iArr[i] = MACRO_SEGMENT_ID;
            append(this.macroSegmentId, 5);
            if (this.macroFileId != null) {
                append(this.macroFileId);
            }
            if (this.macroSegmentId >= this.macroSegmentCount - 1) {
                int[] iArr2 = this.codewords;
                int i2 = this.cwPtr;
                this.cwPtr = i2 + 1;
                iArr2[i2] = MACRO_LAST_SEGMENT;
            }
        }
    }

    private void append(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer(i2 + 1);
        stringBuffer.append(Integer.toString(i));
        for (int length = stringBuffer.length(); length < i2; length++) {
            stringBuffer.insert(0, "0");
        }
        byte[] convertToBytes = PdfEncodings.convertToBytes(stringBuffer.toString(), "cp437");
        numberCompaction(convertToBytes, 0, convertToBytes.length);
    }

    private void append(String str) {
        byte[] convertToBytes = PdfEncodings.convertToBytes(str, "cp437");
        textCompaction(convertToBytes, 0, convertToBytes.length);
    }

    /* access modifiers changed from: protected */
    public void dumpList() {
        if (this.segmentList.size() != 0) {
            for (int i = 0; i < this.segmentList.size(); i++) {
                Segment segment = this.segmentList.get(i);
                int segmentLength = getSegmentLength(segment);
                char[] cArr = new char[segmentLength];
                for (int i2 = 0; i2 < segmentLength; i2++) {
                    cArr[i2] = (char) (this.text[segment.start + i2] & 255);
                    if (cArr[i2] == 13) {
                        cArr[i2] = 10;
                    }
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(segment.type);
                stringBuffer.append(cArr);
                System.out.println(stringBuffer.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getMaxSquare() {
        if (this.codeColumns > 21) {
            this.codeColumns = 29;
            this.codeRows = 32;
            return MACRO_SEGMENT_ID;
        }
        this.codeColumns = 16;
        this.codeRows = 58;
        return MACRO_SEGMENT_ID;
    }

    public void paintCode() {
        int i;
        boolean z;
        if ((this.options & 64) != 0) {
            if (this.lenCodewords > MAX_DATA_CODEWORDS || this.lenCodewords < 1 || this.lenCodewords != this.codewords[0]) {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.codeword.size", new Object[0]));
            }
        } else if (this.text == null) {
            throw new NullPointerException(MessageLocalization.getComposedMessage("text.cannot.be.null", new Object[0]));
        } else if (this.text.length > ABSOLUTE_MAX_TEXT_SIZE) {
            throw new IndexOutOfBoundsException(MessageLocalization.getComposedMessage("the.text.is.too.big", new Object[0]));
        } else {
            this.segmentList = new SegmentList();
            breakString();
            assemble();
            this.segmentList = null;
            int[] iArr = this.codewords;
            int i2 = this.cwPtr;
            this.lenCodewords = i2;
            iArr[0] = i2;
        }
        int maxPossibleErrorLevel = maxPossibleErrorLevel(928 - this.lenCodewords);
        if ((this.options & 16) == 0) {
            if (this.lenCodewords < 41) {
                this.errorLevel = 2;
            } else if (this.lenCodewords < 161) {
                this.errorLevel = 3;
            } else if (this.lenCodewords < 321) {
                this.errorLevel = 4;
            } else {
                this.errorLevel = 5;
            }
        }
        if (this.errorLevel < 0) {
            this.errorLevel = 0;
        } else if (this.errorLevel > maxPossibleErrorLevel) {
            this.errorLevel = maxPossibleErrorLevel;
        }
        if (this.codeColumns < 1) {
            this.codeColumns = 1;
        } else if (this.codeColumns > 30) {
            this.codeColumns = 30;
        }
        if (this.codeRows < 3) {
            this.codeRows = 3;
        } else if (this.codeRows > 90) {
            this.codeRows = 90;
        }
        int i3 = 2 << this.errorLevel;
        boolean z2 = (4 & this.options) == 0;
        int i4 = this.lenCodewords + i3;
        if ((this.options & 1) != 0) {
            int i5 = this.codeColumns * this.codeRows;
            if (i5 > MACRO_SEGMENT_ID) {
                i5 = getMaxSquare();
            }
            if (i5 < this.lenCodewords + i3) {
                i5 = this.lenCodewords + i3;
                z = false;
            } else {
                z = true;
            }
            i = i5;
        } else if ((this.options & 6) == 0) {
            if (((double) this.aspectRatio) < 0.001d) {
                this.aspectRatio = 0.001f;
            } else if (this.aspectRatio > 1000.0f) {
                this.aspectRatio = 1000.0f;
            }
            double d = (double) ((73.0f * this.aspectRatio) - 4.0f);
            i = i4;
            this.codeColumns = (int) ((((-d) + Math.sqrt((d * d) + ((double) (((68.0f * this.aspectRatio) * ((float) (this.lenCodewords + i3))) * this.yHeight)))) / ((double) (34.0f * this.aspectRatio))) + 0.5d);
            if (this.codeColumns < 1) {
                this.codeColumns = 1;
            } else if (this.codeColumns > 30) {
                this.codeColumns = 30;
            }
            z = false;
            z2 = true;
        } else {
            i = i4;
            z = false;
        }
        if (!z) {
            if (z2) {
                int i6 = i - 1;
                this.codeRows = (i6 / this.codeColumns) + 1;
                if (this.codeRows < 3) {
                    this.codeRows = 3;
                } else if (this.codeRows > 90) {
                    this.codeRows = 90;
                    this.codeColumns = (i6 / 90) + 1;
                }
            } else {
                int i7 = i - 1;
                this.codeColumns = (i7 / this.codeRows) + 1;
                if (this.codeColumns > 30) {
                    this.codeColumns = 30;
                    this.codeRows = (i7 / 30) + 1;
                }
            }
            i = this.codeRows * this.codeColumns;
        }
        int i8 = i;
        if (i8 > MACRO_SEGMENT_ID) {
            i8 = getMaxSquare();
        }
        this.errorLevel = maxPossibleErrorLevel(i8 - this.lenCodewords);
        int i9 = (i8 - (2 << this.errorLevel)) - this.lenCodewords;
        if ((this.options & 256) == 0) {
            this.cwPtr = this.lenCodewords;
            while (true) {
                int i10 = i9 - 1;
                if (i9 == 0) {
                    break;
                }
                int[] iArr2 = this.codewords;
                int i11 = this.cwPtr;
                this.cwPtr = i11 + 1;
                iArr2[i11] = TEXT_MODE;
                i9 = i10;
            }
        } else {
            System.arraycopy(this.codewords, this.macroIndex, this.codewords, this.macroIndex + i9, i9);
            this.cwPtr = this.lenCodewords + i9;
            while (true) {
                int i12 = i9 - 1;
                if (i9 == 0) {
                    break;
                }
                int[] iArr3 = this.codewords;
                int i13 = this.macroIndex;
                this.macroIndex = i13 + 1;
                iArr3[i13] = TEXT_MODE;
                i9 = i12;
            }
        }
        int[] iArr4 = this.codewords;
        int i14 = this.cwPtr;
        this.lenCodewords = i14;
        iArr4[0] = i14;
        calculateErrorCorrection(this.lenCodewords);
        this.lenCodewords = i8;
        outPaintCode();
    }

    public Image getImage() throws BadElementException {
        paintCode();
        return Image.getInstance(this.bitColumns, this.codeRows, false, 256, (this.options & 128) == 0 ? 0 : 1, CCITTG4Encoder.compress(this.outBits, this.bitColumns, this.codeRows), (int[]) null);
    }

    public byte[] getOutBits() {
        return this.outBits;
    }

    public int getBitColumns() {
        return this.bitColumns;
    }

    public int getCodeRows() {
        return this.codeRows;
    }

    public void setCodeRows(int i) {
        this.codeRows = i;
    }

    public int getCodeColumns() {
        return this.codeColumns;
    }

    public void setCodeColumns(int i) {
        this.codeColumns = i;
    }

    public int[] getCodewords() {
        return this.codewords;
    }

    public int getLenCodewords() {
        return this.lenCodewords;
    }

    public void setLenCodewords(int i) {
        this.lenCodewords = i;
    }

    public int getErrorLevel() {
        return this.errorLevel;
    }

    public void setErrorLevel(int i) {
        this.errorLevel = i;
    }

    public byte[] getText() {
        return this.text;
    }

    public void setText(byte[] bArr) {
        this.text = bArr;
    }

    public void setText(String str) {
        this.text = PdfEncodings.convertToBytes(str, "cp437");
    }

    public int getOptions() {
        return this.options;
    }

    public void setOptions(int i) {
        this.options = i;
    }

    public float getAspectRatio() {
        return this.aspectRatio;
    }

    public void setAspectRatio(float f) {
        this.aspectRatio = f;
    }

    public float getYHeight() {
        return this.yHeight;
    }

    public void setYHeight(float f) {
        this.yHeight = f;
    }

    static {
        int[] iArr = new int[MOD];
        // fill-array-data instruction
        iArr[0] = 120256;
        iArr[1] = 125680;
        iArr[2] = 128380;
        iArr[3] = 120032;
        iArr[4] = 125560;
        iArr[5] = 128318;
        iArr[6] = 108736;
        iArr[7] = 119920;
        iArr[8] = 108640;
        iArr[9] = 86080;
        iArr[10] = 108592;
        iArr[11] = 86048;
        iArr[12] = 110016;
        iArr[13] = 120560;
        iArr[14] = 125820;
        iArr[15] = 109792;
        iArr[16] = 120440;
        iArr[17] = 125758;
        iArr[18] = 88256;
        iArr[19] = 109680;
        iArr[20] = 88160;
        iArr[21] = 89536;
        iArr[22] = 110320;
        iArr[23] = 120700;
        iArr[24] = 89312;
        iArr[25] = 110200;
        iArr[26] = 120638;
        iArr[27] = 89200;
        iArr[28] = 110140;
        iArr[29] = 89840;
        iArr[30] = 110460;
        iArr[31] = 89720;
        iArr[32] = 110398;
        iArr[33] = 89980;
        iArr[34] = 128506;
        iArr[35] = 119520;
        iArr[36] = 125304;
        iArr[37] = 128190;
        iArr[38] = 107712;
        iArr[39] = 119408;
        iArr[40] = 125244;
        iArr[41] = 107616;
        iArr[42] = 119352;
        iArr[43] = 84032;
        iArr[44] = 107568;
        iArr[45] = 119324;
        iArr[46] = 84000;
        iArr[47] = 107544;
        iArr[48] = 83984;
        iArr[49] = 108256;
        iArr[50] = 119672;
        iArr[51] = 125374;
        iArr[52] = 85184;
        iArr[53] = 108144;
        iArr[54] = 119612;
        iArr[55] = 85088;
        iArr[56] = 108088;
        iArr[57] = 119582;
        iArr[58] = 85040;
        iArr[59] = 108060;
        iArr[60] = 85728;
        iArr[61] = 108408;
        iArr[62] = 119742;
        iArr[63] = 85616;
        iArr[64] = 108348;
        iArr[65] = 85560;
        iArr[66] = 108318;
        iArr[67] = 85880;
        iArr[68] = 108478;
        iArr[69] = 85820;
        iArr[70] = 85790;
        iArr[71] = 107200;
        iArr[72] = 119152;
        iArr[73] = 125116;
        iArr[74] = 107104;
        iArr[75] = 119096;
        iArr[76] = 125086;
        iArr[77] = 83008;
        iArr[78] = 107056;
        iArr[79] = 119068;
        iArr[80] = 82976;
        iArr[81] = 107032;
        iArr[82] = 82960;
        iArr[83] = 82952;
        iArr[84] = 83648;
        iArr[85] = 107376;
        iArr[86] = 119228;
        iArr[87] = 83552;
        iArr[88] = 107320;
        iArr[89] = 119198;
        iArr[90] = 83504;
        iArr[91] = 107292;
        iArr[92] = 83480;
        iArr[93] = 83468;
        iArr[94] = 83824;
        iArr[95] = 107452;
        iArr[96] = 83768;
        iArr[97] = 107422;
        iArr[98] = 83740;
        iArr[99] = 83900;
        iArr[100] = 106848;
        iArr[101] = 118968;
        iArr[102] = 125022;
        iArr[103] = 82496;
        iArr[104] = 106800;
        iArr[105] = 118940;
        iArr[106] = 82464;
        iArr[107] = 106776;
        iArr[108] = 118926;
        iArr[109] = 82448;
        iArr[110] = 106764;
        iArr[111] = 82440;
        iArr[112] = 106758;
        iArr[113] = 82784;
        iArr[114] = 106936;
        iArr[115] = 119006;
        iArr[116] = 82736;
        iArr[117] = 106908;
        iArr[118] = 82712;
        iArr[119] = 106894;
        iArr[120] = 82700;
        iArr[121] = 82694;
        iArr[122] = 106974;
        iArr[123] = 82830;
        iArr[124] = 82240;
        iArr[125] = 106672;
        iArr[126] = 118876;
        iArr[127] = 82208;
        iArr[128] = 106648;
        iArr[129] = 118862;
        iArr[130] = 82192;
        iArr[131] = 106636;
        iArr[132] = 82184;
        iArr[133] = 106630;
        iArr[134] = 82180;
        iArr[135] = 82352;
        iArr[136] = 82328;
        iArr[137] = 82316;
        iArr[138] = 82080;
        iArr[139] = 118830;
        iArr[140] = 106572;
        iArr[141] = 106566;
        iArr[142] = 82050;
        iArr[143] = 117472;
        iArr[144] = 124280;
        iArr[145] = 127678;
        iArr[146] = 103616;
        iArr[147] = 117360;
        iArr[148] = 124220;
        iArr[149] = 103520;
        iArr[150] = 117304;
        iArr[151] = 124190;
        iArr[152] = 75840;
        iArr[153] = 103472;
        iArr[154] = 75808;
        iArr[155] = 104160;
        iArr[156] = 117624;
        iArr[157] = 124350;
        iArr[158] = 76992;
        iArr[159] = 104048;
        iArr[160] = 117564;
        iArr[161] = 76896;
        iArr[162] = 103992;
        iArr[163] = 76848;
        iArr[164] = 76824;
        iArr[165] = 77536;
        iArr[166] = 104312;
        iArr[167] = 117694;
        iArr[168] = 77424;
        iArr[169] = 104252;
        iArr[170] = 77368;
        iArr[171] = 77340;
        iArr[172] = 77688;
        iArr[173] = 104382;
        iArr[174] = 77628;
        iArr[175] = 77758;
        iArr[176] = 121536;
        iArr[177] = 126320;
        iArr[178] = 128700;
        iArr[179] = 121440;
        iArr[180] = 126264;
        iArr[181] = 128670;
        iArr[182] = 111680;
        iArr[183] = 121392;
        iArr[184] = 126236;
        iArr[185] = 111648;
        iArr[186] = 121368;
        iArr[187] = 126222;
        iArr[188] = 111632;
        iArr[189] = 121356;
        iArr[190] = 103104;
        iArr[191] = 117104;
        iArr[192] = 124092;
        iArr[193] = 112320;
        iArr[194] = 103008;
        iArr[195] = 117048;
        iArr[196] = 124062;
        iArr[197] = 112224;
        iArr[198] = 121656;
        iArr[199] = 126366;
        iArr[200] = 93248;
        iArr[201] = 74784;
        iArr[202] = 102936;
        iArr[203] = 117006;
        iArr[204] = 93216;
        iArr[205] = 112152;
        iArr[206] = 93200;
        iArr[207] = 75456;
        iArr[208] = 103280;
        iArr[209] = 117180;
        iArr[210] = 93888;
        iArr[211] = 75360;
        iArr[212] = 103224;
        iArr[213] = 117150;
        iArr[214] = 93792;
        iArr[215] = 112440;
        iArr[216] = 121758;
        iArr[217] = 93744;
        iArr[218] = 75288;
        iArr[219] = 93720;
        iArr[220] = 75632;
        iArr[221] = 103356;
        iArr[222] = 94064;
        iArr[223] = 75576;
        iArr[224] = 103326;
        iArr[225] = 94008;
        iArr[226] = 112542;
        iArr[227] = 93980;
        iArr[228] = 75708;
        iArr[229] = 94140;
        iArr[230] = 75678;
        iArr[231] = 94110;
        iArr[232] = 121184;
        iArr[233] = 126136;
        iArr[234] = 128606;
        iArr[235] = 111168;
        iArr[236] = 121136;
        iArr[237] = 126108;
        iArr[238] = 111136;
        iArr[239] = 121112;
        iArr[240] = 126094;
        iArr[241] = 111120;
        iArr[242] = 121100;
        iArr[243] = 111112;
        iArr[244] = 111108;
        iArr[245] = 102752;
        iArr[246] = 116920;
        iArr[247] = 123998;
        iArr[248] = 111456;
        iArr[249] = 102704;
        iArr[250] = 116892;
        iArr[251] = 91712;
        iArr[252] = 74272;
        iArr[253] = 121244;
        iArr[254] = 116878;
        iArr[255] = 91680;
        iArr[256] = 74256;
        iArr[257] = 102668;
        iArr[258] = 91664;
        iArr[259] = 111372;
        iArr[260] = 102662;
        iArr[261] = 74244;
        iArr[262] = 74592;
        iArr[263] = 102840;
        iArr[264] = 116958;
        iArr[265] = 92000;
        iArr[266] = 74544;
        iArr[267] = 102812;
        iArr[268] = 91952;
        iArr[269] = 111516;
        iArr[270] = 102798;
        iArr[271] = 91928;
        iArr[272] = 74508;
        iArr[273] = 74502;
        iArr[274] = 74680;
        iArr[275] = 102878;
        iArr[276] = 92088;
        iArr[277] = 74652;
        iArr[278] = 92060;
        iArr[279] = 74638;
        iArr[280] = 92046;
        iArr[281] = 92126;
        iArr[282] = 110912;
        iArr[283] = 121008;
        iArr[284] = 126044;
        iArr[285] = 110880;
        iArr[286] = 120984;
        iArr[287] = 126030;
        iArr[288] = 110864;
        iArr[289] = 120972;
        iArr[290] = 110856;
        iArr[291] = 120966;
        iArr[292] = 110852;
        iArr[293] = 110850;
        iArr[294] = 74048;
        iArr[295] = 102576;
        iArr[296] = 116828;
        iArr[297] = 90944;
        iArr[298] = 74016;
        iArr[299] = 102552;
        iArr[300] = 116814;
        iArr[301] = 90912;
        iArr[302] = 111000;
        iArr[303] = 121038;
        iArr[304] = 90896;
        iArr[305] = 73992;
        iArr[306] = 102534;
        iArr[307] = 90888;
        iArr[308] = 110982;
        iArr[309] = 90884;
        iArr[310] = 74160;
        iArr[311] = 102620;
        iArr[312] = 91056;
        iArr[313] = 74136;
        iArr[314] = 102606;
        iArr[315] = 91032;
        iArr[316] = 111054;
        iArr[317] = 91020;
        iArr[318] = 74118;
        iArr[319] = 91014;
        iArr[320] = 91100;
        iArr[321] = 91086;
        iArr[322] = 110752;
        iArr[323] = 120920;
        iArr[324] = 125998;
        iArr[325] = 110736;
        iArr[326] = 120908;
        iArr[327] = 110728;
        iArr[328] = 120902;
        iArr[329] = 110724;
        iArr[330] = 110722;
        iArr[331] = 73888;
        iArr[332] = 102488;
        iArr[333] = 116782;
        iArr[334] = 90528;
        iArr[335] = 73872;
        iArr[336] = 102476;
        iArr[337] = 90512;
        iArr[338] = 110796;
        iArr[339] = 102470;
        iArr[340] = 90504;
        iArr[341] = 73860;
        iArr[342] = 90500;
        iArr[343] = 73858;
        iArr[344] = 73944;
        iArr[345] = 90584;
        iArr[346] = 90572;
        iArr[347] = 90566;
        iArr[348] = 120876;
        iArr[349] = 120870;
        iArr[350] = 110658;
        iArr[351] = 102444;
        iArr[352] = 73800;
        iArr[353] = 90312;
        iArr[354] = 90308;
        iArr[355] = 90306;
        iArr[356] = 101056;
        iArr[357] = 116080;
        iArr[358] = 123580;
        iArr[359] = 100960;
        iArr[360] = 116024;
        iArr[361] = 70720;
        iArr[362] = 100912;
        iArr[363] = 115996;
        iArr[364] = 70688;
        iArr[365] = 100888;
        iArr[366] = 70672;
        iArr[367] = 70664;
        iArr[368] = 71360;
        iArr[369] = 101232;
        iArr[370] = 116156;
        iArr[371] = 71264;
        iArr[372] = 101176;
        iArr[373] = 116126;
        iArr[374] = 71216;
        iArr[375] = 101148;
        iArr[376] = 71192;
        iArr[377] = 71180;
        iArr[378] = 71536;
        iArr[379] = 101308;
        iArr[380] = 71480;
        iArr[381] = 101278;
        iArr[382] = 71452;
        iArr[383] = 71612;
        iArr[384] = 71582;
        iArr[385] = 118112;
        iArr[386] = 124600;
        iArr[387] = 127838;
        iArr[388] = 105024;
        iArr[389] = 118064;
        iArr[390] = 124572;
        iArr[391] = 104992;
        iArr[392] = 118040;
        iArr[393] = 124558;
        iArr[394] = 104976;
        iArr[395] = 118028;
        iArr[396] = 104968;
        iArr[397] = 118022;
        iArr[398] = 100704;
        iArr[399] = 115896;
        iArr[400] = 123486;
        iArr[401] = 105312;
        iArr[402] = 100656;
        iArr[403] = 115868;
        iArr[404] = 79424;
        iArr[405] = 70176;
        iArr[406] = 118172;
        iArr[407] = 115854;
        iArr[408] = 79392;
        iArr[409] = 105240;
        iArr[410] = 100620;
        iArr[411] = 79376;
        iArr[412] = 70152;
        iArr[413] = 79368;
        iArr[414] = 70496;
        iArr[415] = 100792;
        iArr[416] = 115934;
        iArr[417] = 79712;
        iArr[418] = 70448;
        iArr[419] = 118238;
        iArr[420] = 79664;
        iArr[421] = 105372;
        iArr[422] = 100750;
        iArr[423] = 79640;
        iArr[424] = 70412;
        iArr[425] = 79628;
        iArr[426] = 70584;
        iArr[427] = 100830;
        iArr[428] = 79800;
        iArr[429] = 70556;
        iArr[430] = 79772;
        iArr[431] = 70542;
        iArr[432] = 70622;
        iArr[433] = 79838;
        iArr[434] = 122176;
        iArr[435] = 126640;
        iArr[436] = 128860;
        iArr[437] = 122144;
        iArr[438] = 126616;
        iArr[439] = 128846;
        iArr[440] = 122128;
        iArr[441] = 126604;
        iArr[442] = 122120;
        iArr[443] = 126598;
        iArr[444] = 122116;
        iArr[445] = 104768;
        iArr[446] = 117936;
        iArr[447] = 124508;
        iArr[448] = 113472;
        iArr[449] = 104736;
        iArr[450] = 126684;
        iArr[451] = 124494;
        iArr[452] = 113440;
        iArr[453] = 122264;
        iArr[454] = 126670;
        iArr[455] = 113424;
        iArr[456] = 104712;
        iArr[457] = 117894;
        iArr[458] = 113416;
        iArr[459] = 122246;
        iArr[460] = 104706;
        iArr[461] = 69952;
        iArr[462] = 100528;
        iArr[463] = 115804;
        iArr[464] = 78656;
        iArr[465] = 69920;
        iArr[466] = 100504;
        iArr[467] = 115790;
        iArr[468] = 96064;
        iArr[469] = 78624;
        iArr[470] = 104856;
        iArr[471] = 117966;
        iArr[472] = 96032;
        iArr[473] = 113560;
        iArr[474] = 122318;
        iArr[475] = 100486;
        iArr[476] = 96016;
        iArr[477] = 78600;
        iArr[478] = 104838;
        iArr[479] = 96008;
        iArr[480] = 69890;
        iArr[481] = 70064;
        iArr[482] = 100572;
        iArr[483] = 78768;
        iArr[484] = 70040;
        iArr[485] = 100558;
        iArr[486] = 96176;
        iArr[487] = 78744;
        iArr[488] = 104910;
        iArr[489] = 96152;
        iArr[490] = 113614;
        iArr[491] = 70022;
        iArr[492] = 78726;
        iArr[493] = 70108;
        iArr[494] = 78812;
        iArr[495] = 70094;
        iArr[496] = 96220;
        iArr[497] = 78798;
        iArr[498] = 122016;
        iArr[499] = 126552;
        iArr[500] = 128814;
        iArr[501] = 122000;
        iArr[502] = 126540;
        iArr[503] = 121992;
        iArr[504] = 126534;
        iArr[505] = 121988;
        iArr[506] = 121986;
        iArr[507] = 104608;
        iArr[508] = 117848;
        iArr[509] = 124462;
        iArr[510] = 113056;
        iArr[511] = 104592;
        iArr[512] = 126574;
        iArr[513] = 113040;
        iArr[514] = 122060;
        iArr[515] = 117830;
        iArr[516] = 113032;
        iArr[517] = 104580;
        iArr[518] = 113028;
        iArr[519] = 104578;
        iArr[520] = 113026;
        iArr[521] = 69792;
        iArr[522] = 100440;
        iArr[523] = 115758;
        iArr[524] = 78240;
        iArr[525] = 69776;
        iArr[526] = 100428;
        iArr[527] = 95136;
        iArr[528] = 78224;
        iArr[529] = 104652;
        iArr[530] = 100422;
        iArr[531] = 95120;
        iArr[532] = 113100;
        iArr[533] = 69764;
        iArr[534] = 95112;
        iArr[535] = 78212;
        iArr[536] = 69762;
        iArr[537] = 78210;
        iArr[538] = 69848;
        iArr[539] = 100462;
        iArr[540] = 78296;
        iArr[541] = 69836;
        iArr[542] = 95192;
        iArr[543] = 78284;
        iArr[544] = 69830;
        iArr[545] = 95180;
        iArr[546] = 78278;
        iArr[547] = 69870;
        iArr[548] = 95214;
        iArr[549] = 121936;
        iArr[550] = 126508;
        iArr[551] = 121928;
        iArr[552] = 126502;
        iArr[553] = 121924;
        iArr[554] = 121922;
        iArr[555] = 104528;
        iArr[556] = 117804;
        iArr[557] = 112848;
        iArr[558] = 104520;
        iArr[559] = 117798;
        iArr[560] = 112840;
        iArr[561] = 121958;
        iArr[562] = 112836;
        iArr[563] = 104514;
        iArr[564] = 112834;
        iArr[565] = 69712;
        iArr[566] = 100396;
        iArr[567] = 78032;
        iArr[568] = 69704;
        iArr[569] = 100390;
        iArr[570] = 94672;
        iArr[571] = 78024;
        iArr[572] = 104550;
        iArr[573] = 94664;
        iArr[574] = 112870;
        iArr[575] = 69698;
        iArr[576] = 94660;
        iArr[577] = 78018;
        iArr[578] = 94658;
        iArr[579] = 78060;
        iArr[580] = 94700;
        iArr[581] = 94694;
        iArr[582] = 126486;
        iArr[583] = 121890;
        iArr[584] = 117782;
        iArr[585] = 104484;
        iArr[586] = 104482;
        iArr[587] = 69672;
        iArr[588] = 77928;
        iArr[589] = 94440;
        iArr[590] = 69666;
        iArr[591] = 77922;
        iArr[592] = 99680;
        iArr[593] = 68160;
        iArr[594] = 99632;
        iArr[595] = 68128;
        iArr[596] = 99608;
        iArr[597] = 115342;
        iArr[598] = 68112;
        iArr[599] = 99596;
        iArr[600] = 68104;
        iArr[601] = 99590;
        iArr[602] = 68448;
        iArr[603] = 99768;
        iArr[604] = 115422;
        iArr[605] = 68400;
        iArr[606] = 99740;
        iArr[607] = 68376;
        iArr[608] = 99726;
        iArr[609] = 68364;
        iArr[610] = 68358;
        iArr[611] = 68536;
        iArr[612] = 99806;
        iArr[613] = 68508;
        iArr[614] = 68494;
        iArr[615] = 68574;
        iArr[616] = 101696;
        iArr[617] = 116400;
        iArr[618] = 123740;
        iArr[619] = 101664;
        iArr[620] = 116376;
        iArr[621] = 101648;
        iArr[622] = 116364;
        iArr[623] = 101640;
        iArr[624] = 116358;
        iArr[625] = 101636;
        iArr[626] = 67904;
        iArr[627] = 99504;
        iArr[628] = 115292;
        iArr[629] = 72512;
        iArr[630] = 67872;
        iArr[631] = 116444;
        iArr[632] = 115278;
        iArr[633] = 72480;
        iArr[634] = 101784;
        iArr[635] = 116430;
        iArr[636] = 72464;
        iArr[637] = 67848;
        iArr[638] = 99462;
        iArr[639] = 72456;
        iArr[640] = 101766;
        iArr[641] = 67842;
        iArr[642] = 68016;
        iArr[643] = 99548;
        iArr[644] = 72624;
        iArr[645] = 67992;
        iArr[646] = 99534;
        iArr[647] = 72600;
        iArr[648] = 101838;
        iArr[649] = 72588;
        iArr[650] = 67974;
        iArr[651] = 68060;
        iArr[652] = 72668;
        iArr[653] = 68046;
        iArr[654] = 72654;
        iArr[655] = 118432;
        iArr[656] = 124760;
        iArr[657] = 127918;
        iArr[658] = 118416;
        iArr[659] = 124748;
        iArr[660] = 118408;
        iArr[661] = 124742;
        iArr[662] = 118404;
        iArr[663] = 118402;
        iArr[664] = 101536;
        iArr[665] = 116312;
        iArr[666] = 105888;
        iArr[667] = 101520;
        iArr[668] = 116300;
        iArr[669] = 105872;
        iArr[670] = 118476;
        iArr[671] = 116294;
        iArr[672] = 105864;
        iArr[673] = 101508;
        iArr[674] = 105860;
        iArr[675] = 101506;
        iArr[676] = 105858;
        iArr[677] = 67744;
        iArr[678] = 99416;
        iArr[679] = 72096;
        iArr[680] = 67728;
        iArr[681] = 116334;
        iArr[682] = 80800;
        iArr[683] = 72080;
        iArr[684] = 101580;
        iArr[685] = 99398;
        iArr[686] = 80784;
        iArr[687] = 105932;
        iArr[688] = 67716;
        iArr[689] = 80776;
        iArr[690] = 72068;
        iArr[691] = 67714;
        iArr[692] = 72066;
        iArr[693] = 67800;
        iArr[694] = 99438;
        iArr[695] = 72152;
        iArr[696] = 67788;
        iArr[697] = 80856;
        iArr[698] = 72140;
        iArr[699] = 67782;
        iArr[700] = 80844;
        iArr[701] = 72134;
        iArr[702] = 67822;
        iArr[703] = 72174;
        iArr[704] = 80878;
        iArr[705] = 126800;
        iArr[706] = 128940;
        iArr[707] = 126792;
        iArr[708] = 128934;
        iArr[709] = 126788;
        iArr[710] = 126786;
        iArr[711] = 118352;
        iArr[712] = 124716;
        iArr[713] = 122576;
        iArr[714] = 126828;
        iArr[715] = 124710;
        iArr[716] = 122568;
        iArr[717] = 126822;
        iArr[718] = 122564;
        iArr[719] = 118338;
        iArr[720] = 122562;
        iArr[721] = 101456;
        iArr[722] = 116268;
        iArr[723] = 105680;
        iArr[724] = 101448;
        iArr[725] = 116262;
        iArr[726] = 114128;
        iArr[727] = 105672;
        iArr[728] = 118374;
        iArr[729] = 114120;
        iArr[730] = 122598;
        iArr[731] = 101442;
        iArr[732] = 114116;
        iArr[733] = 105666;
        iArr[734] = 114114;
        iArr[735] = 67664;
        iArr[736] = 99372;
        iArr[737] = 71888;
        iArr[738] = 67656;
        iArr[739] = 99366;
        iArr[740] = 80336;
        iArr[741] = 71880;
        iArr[742] = 101478;
        iArr[743] = 97232;
        iArr[744] = 80328;
        iArr[745] = 105702;
        iArr[746] = 67650;
        iArr[747] = 97224;
        iArr[748] = 114150;
        iArr[749] = 71874;
        iArr[750] = 97220;
        iArr[751] = 67692;
        iArr[752] = 71916;
        iArr[753] = 67686;
        iArr[754] = 80364;
        iArr[755] = 71910;
        iArr[756] = 97260;
        iArr[757] = 80358;
        iArr[758] = 97254;
        iArr[759] = 126760;
        iArr[760] = 128918;
        iArr[761] = 126756;
        iArr[762] = 126754;
        iArr[763] = 118312;
        iArr[764] = 124694;
        iArr[765] = 122472;
        iArr[766] = 126774;
        iArr[767] = 122468;
        iArr[768] = 118306;
        iArr[769] = 122466;
        iArr[770] = 101416;
        iArr[771] = 116246;
        iArr[772] = 105576;
        iArr[773] = 101412;
        iArr[774] = 113896;
        iArr[775] = 105572;
        iArr[776] = 101410;
        iArr[777] = 113892;
        iArr[778] = 105570;
        iArr[779] = 113890;
        iArr[780] = 67624;
        iArr[781] = 99350;
        iArr[782] = 71784;
        iArr[783] = 101430;
        iArr[784] = 80104;
        iArr[785] = 71780;
        iArr[786] = 67618;
        iArr[787] = 96744;
        iArr[788] = 80100;
        iArr[789] = 71778;
        iArr[790] = 96740;
        iArr[791] = 80098;
        iArr[792] = 96738;
        iArr[793] = 71798;
        iArr[794] = 96758;
        iArr[795] = 126738;
        iArr[796] = 122420;
        iArr[797] = 122418;
        iArr[798] = 105524;
        iArr[799] = 113780;
        iArr[800] = 113778;
        iArr[801] = 71732;
        iArr[802] = 79988;
        iArr[803] = 96500;
        iArr[804] = 96498;
        iArr[805] = 66880;
        iArr[806] = 66848;
        iArr[807] = 98968;
        iArr[808] = 66832;
        iArr[809] = 66824;
        iArr[810] = 66820;
        iArr[811] = 66992;
        iArr[812] = 66968;
        iArr[813] = 66956;
        iArr[814] = 66950;
        iArr[815] = 67036;
        iArr[816] = 67022;
        iArr[817] = 100000;
        iArr[818] = 99984;
        iArr[819] = 115532;
        iArr[820] = 99976;
        iArr[821] = 115526;
        iArr[822] = 99972;
        iArr[823] = 99970;
        iArr[824] = 66720;
        iArr[825] = 98904;
        iArr[826] = 69024;
        iArr[827] = 100056;
        iArr[828] = 98892;
        iArr[829] = 69008;
        iArr[830] = 100044;
        iArr[831] = 69000;
        iArr[832] = 100038;
        iArr[833] = 68996;
        iArr[834] = 66690;
        iArr[835] = 68994;
        iArr[836] = 66776;
        iArr[837] = 98926;
        iArr[838] = 69080;
        iArr[839] = 100078;
        iArr[840] = 69068;
        iArr[841] = 66758;
        iArr[842] = 69062;
        iArr[843] = 66798;
        iArr[844] = 69102;
        iArr[845] = 116560;
        iArr[846] = 116552;
        iArr[847] = 116548;
        iArr[848] = 116546;
        iArr[849] = 99920;
        iArr[850] = 102096;
        iArr[851] = 116588;
        iArr[852] = 115494;
        iArr[853] = 102088;
        iArr[854] = 116582;
        iArr[855] = 102084;
        iArr[856] = 99906;
        iArr[857] = 102082;
        iArr[858] = 66640;
        iArr[859] = 68816;
        iArr[860] = 66632;
        iArr[861] = 98854;
        iArr[862] = 73168;
        iArr[863] = 68808;
        iArr[864] = 66628;
        iArr[865] = 73160;
        iArr[866] = 68804;
        iArr[867] = 66626;
        iArr[868] = 73156;
        iArr[869] = 68802;
        iArr[870] = 66668;
        iArr[871] = 68844;
        iArr[872] = 66662;
        iArr[873] = 73196;
        iArr[874] = 68838;
        iArr[875] = 73190;
        iArr[876] = 124840;
        iArr[877] = 124836;
        iArr[878] = 124834;
        iArr[879] = 116520;
        iArr[880] = 118632;
        iArr[881] = 124854;
        iArr[882] = 118628;
        iArr[883] = 116514;
        iArr[884] = 118626;
        iArr[885] = 99880;
        iArr[886] = 115478;
        iArr[887] = 101992;
        iArr[888] = 116534;
        iArr[889] = 106216;
        iArr[890] = 101988;
        iArr[891] = 99874;
        iArr[892] = 106212;
        iArr[893] = 101986;
        iArr[894] = 106210;
        iArr[895] = 66600;
        iArr[896] = 98838;
        iArr[897] = 68712;
        iArr[898] = 99894;
        iArr[899] = 72936;
        iArr[900] = 68708;
        iArr[901] = 66594;
        iArr[902] = 81384;
        iArr[903] = 72932;
        iArr[904] = 68706;
        iArr[905] = 81380;
        iArr[906] = 72930;
        iArr[907] = 66614;
        iArr[908] = 68726;
        iArr[909] = 72950;
        iArr[910] = 81398;
        iArr[911] = 128980;
        iArr[912] = 128978;
        iArr[913] = 124820;
        iArr[914] = 126900;
        iArr[915] = 124818;
        iArr[916] = 126898;
        iArr[917] = 116500;
        iArr[918] = 118580;
        iArr[919] = 116498;
        iArr[920] = 122740;
        iArr[921] = 118578;
        iArr[922] = 122738;
        iArr[923] = 99860;
        iArr[924] = 101940;
        iArr[925] = 99858;
        iArr[926] = 106100;
        iArr[927] = 101938;
        iArr[928] = 114420;
        int[] iArr2 = new int[MOD];
        // fill-array-data instruction
        iArr2[0] = 128352;
        iArr2[1] = 129720;
        iArr2[2] = 125504;
        iArr2[3] = 128304;
        iArr2[4] = 129692;
        iArr2[5] = 125472;
        iArr2[6] = 128280;
        iArr2[7] = 129678;
        iArr2[8] = 125456;
        iArr2[9] = 128268;
        iArr2[10] = 125448;
        iArr2[11] = 128262;
        iArr2[12] = 125444;
        iArr2[13] = 125792;
        iArr2[14] = 128440;
        iArr2[15] = 129758;
        iArr2[16] = 120384;
        iArr2[17] = 125744;
        iArr2[18] = 128412;
        iArr2[19] = 120352;
        iArr2[20] = 125720;
        iArr2[21] = 128398;
        iArr2[22] = 120336;
        iArr2[23] = 125708;
        iArr2[24] = 120328;
        iArr2[25] = 125702;
        iArr2[26] = 120324;
        iArr2[27] = 120672;
        iArr2[28] = 125880;
        iArr2[29] = 128478;
        iArr2[30] = 110144;
        iArr2[31] = 120624;
        iArr2[32] = 125852;
        iArr2[33] = 110112;
        iArr2[34] = 120600;
        iArr2[35] = 125838;
        iArr2[36] = 110096;
        iArr2[37] = 120588;
        iArr2[38] = 110088;
        iArr2[39] = 120582;
        iArr2[40] = 110084;
        iArr2[41] = 110432;
        iArr2[42] = 120760;
        iArr2[43] = 125918;
        iArr2[44] = 89664;
        iArr2[45] = 110384;
        iArr2[46] = 120732;
        iArr2[47] = 89632;
        iArr2[48] = 110360;
        iArr2[49] = 120718;
        iArr2[50] = 89616;
        iArr2[51] = 110348;
        iArr2[52] = 89608;
        iArr2[53] = 110342;
        iArr2[54] = 89952;
        iArr2[55] = 110520;
        iArr2[56] = 120798;
        iArr2[57] = 89904;
        iArr2[58] = 110492;
        iArr2[59] = 89880;
        iArr2[60] = 110478;
        iArr2[61] = 89868;
        iArr2[62] = 90040;
        iArr2[63] = 110558;
        iArr2[64] = 90012;
        iArr2[65] = 89998;
        iArr2[66] = 125248;
        iArr2[67] = 128176;
        iArr2[68] = 129628;
        iArr2[69] = 125216;
        iArr2[70] = 128152;
        iArr2[71] = 129614;
        iArr2[72] = 125200;
        iArr2[73] = 128140;
        iArr2[74] = 125192;
        iArr2[75] = 128134;
        iArr2[76] = 125188;
        iArr2[77] = 125186;
        iArr2[78] = 119616;
        iArr2[79] = 125360;
        iArr2[80] = 128220;
        iArr2[81] = 119584;
        iArr2[82] = 125336;
        iArr2[83] = 128206;
        iArr2[84] = 119568;
        iArr2[85] = 125324;
        iArr2[86] = 119560;
        iArr2[87] = 125318;
        iArr2[88] = 119556;
        iArr2[89] = 119554;
        iArr2[90] = 108352;
        iArr2[91] = 119728;
        iArr2[92] = 125404;
        iArr2[93] = 108320;
        iArr2[94] = 119704;
        iArr2[95] = 125390;
        iArr2[96] = 108304;
        iArr2[97] = 119692;
        iArr2[98] = 108296;
        iArr2[99] = 119686;
        iArr2[100] = 108292;
        iArr2[101] = 108290;
        iArr2[102] = 85824;
        iArr2[103] = 108464;
        iArr2[104] = 119772;
        iArr2[105] = 85792;
        iArr2[106] = 108440;
        iArr2[107] = 119758;
        iArr2[108] = 85776;
        iArr2[109] = 108428;
        iArr2[110] = 85768;
        iArr2[111] = 108422;
        iArr2[112] = 85764;
        iArr2[113] = 85936;
        iArr2[114] = 108508;
        iArr2[115] = 85912;
        iArr2[116] = 108494;
        iArr2[117] = 85900;
        iArr2[118] = 85894;
        iArr2[119] = 85980;
        iArr2[120] = 85966;
        iArr2[121] = 125088;
        iArr2[122] = 128088;
        iArr2[123] = 129582;
        iArr2[124] = 125072;
        iArr2[125] = 128076;
        iArr2[126] = 125064;
        iArr2[127] = 128070;
        iArr2[128] = 125060;
        iArr2[129] = 125058;
        iArr2[130] = 119200;
        iArr2[131] = 125144;
        iArr2[132] = 128110;
        iArr2[133] = 119184;
        iArr2[134] = 125132;
        iArr2[135] = 119176;
        iArr2[136] = 125126;
        iArr2[137] = 119172;
        iArr2[138] = 119170;
        iArr2[139] = 107424;
        iArr2[140] = 119256;
        iArr2[141] = 125166;
        iArr2[142] = 107408;
        iArr2[143] = 119244;
        iArr2[144] = 107400;
        iArr2[145] = 119238;
        iArr2[146] = 107396;
        iArr2[147] = 107394;
        iArr2[148] = 83872;
        iArr2[149] = 107480;
        iArr2[150] = 119278;
        iArr2[151] = 83856;
        iArr2[152] = 107468;
        iArr2[153] = 83848;
        iArr2[154] = 107462;
        iArr2[155] = 83844;
        iArr2[156] = 83842;
        iArr2[157] = 83928;
        iArr2[158] = 107502;
        iArr2[159] = 83916;
        iArr2[160] = 83910;
        iArr2[161] = 83950;
        iArr2[162] = 125008;
        iArr2[163] = 128044;
        iArr2[164] = 125000;
        iArr2[165] = 128038;
        iArr2[166] = 124996;
        iArr2[167] = 124994;
        iArr2[168] = 118992;
        iArr2[169] = 125036;
        iArr2[170] = 118984;
        iArr2[171] = 125030;
        iArr2[172] = 118980;
        iArr2[173] = 118978;
        iArr2[174] = 106960;
        iArr2[175] = 119020;
        iArr2[176] = 106952;
        iArr2[177] = 119014;
        iArr2[178] = 106948;
        iArr2[179] = 106946;
        iArr2[180] = 82896;
        iArr2[181] = 106988;
        iArr2[182] = 82888;
        iArr2[183] = 106982;
        iArr2[184] = 82884;
        iArr2[185] = 82882;
        iArr2[186] = 82924;
        iArr2[187] = 82918;
        iArr2[188] = 124968;
        iArr2[189] = 128022;
        iArr2[190] = 124964;
        iArr2[191] = 124962;
        iArr2[192] = 118888;
        iArr2[193] = 124982;
        iArr2[194] = 118884;
        iArr2[195] = 118882;
        iArr2[196] = 106728;
        iArr2[197] = 118902;
        iArr2[198] = 106724;
        iArr2[199] = 106722;
        iArr2[200] = 82408;
        iArr2[201] = 106742;
        iArr2[202] = 82404;
        iArr2[203] = 82402;
        iArr2[204] = 124948;
        iArr2[205] = 124946;
        iArr2[206] = 118836;
        iArr2[207] = 118834;
        iArr2[208] = 106612;
        iArr2[209] = 106610;
        iArr2[210] = 124224;
        iArr2[211] = 127664;
        iArr2[212] = 129372;
        iArr2[213] = 124192;
        iArr2[214] = 127640;
        iArr2[215] = 129358;
        iArr2[216] = 124176;
        iArr2[217] = 127628;
        iArr2[218] = 124168;
        iArr2[219] = 127622;
        iArr2[220] = 124164;
        iArr2[221] = 124162;
        iArr2[222] = 117568;
        iArr2[223] = 124336;
        iArr2[224] = 127708;
        iArr2[225] = 117536;
        iArr2[226] = 124312;
        iArr2[227] = 127694;
        iArr2[228] = 117520;
        iArr2[229] = 124300;
        iArr2[230] = 117512;
        iArr2[231] = 124294;
        iArr2[232] = 117508;
        iArr2[233] = 117506;
        iArr2[234] = 104256;
        iArr2[235] = 117680;
        iArr2[236] = 124380;
        iArr2[237] = 104224;
        iArr2[238] = 117656;
        iArr2[239] = 124366;
        iArr2[240] = 104208;
        iArr2[241] = 117644;
        iArr2[242] = 104200;
        iArr2[243] = 117638;
        iArr2[244] = 104196;
        iArr2[245] = 104194;
        iArr2[246] = 77632;
        iArr2[247] = 104368;
        iArr2[248] = 117724;
        iArr2[249] = 77600;
        iArr2[250] = 104344;
        iArr2[251] = 117710;
        iArr2[252] = 77584;
        iArr2[253] = 104332;
        iArr2[254] = 77576;
        iArr2[255] = 104326;
        iArr2[256] = 77572;
        iArr2[257] = 77744;
        iArr2[258] = 104412;
        iArr2[259] = 77720;
        iArr2[260] = 104398;
        iArr2[261] = 77708;
        iArr2[262] = 77702;
        iArr2[263] = 77788;
        iArr2[264] = 77774;
        iArr2[265] = 128672;
        iArr2[266] = 129880;
        iArr2[267] = 93168;
        iArr2[268] = 128656;
        iArr2[269] = 129868;
        iArr2[270] = 92664;
        iArr2[271] = 128648;
        iArr2[272] = 129862;
        iArr2[273] = 92412;
        iArr2[274] = 128644;
        iArr2[275] = 128642;
        iArr2[276] = 124064;
        iArr2[277] = 127576;
        iArr2[278] = 129326;
        iArr2[279] = 126368;
        iArr2[280] = 124048;
        iArr2[281] = 129902;
        iArr2[282] = 126352;
        iArr2[283] = 128716;
        iArr2[284] = 127558;
        iArr2[285] = 126344;
        iArr2[286] = 124036;
        iArr2[287] = 126340;
        iArr2[288] = 124034;
        iArr2[289] = 126338;
        iArr2[290] = 117152;
        iArr2[291] = 124120;
        iArr2[292] = 127598;
        iArr2[293] = 121760;
        iArr2[294] = 117136;
        iArr2[295] = 124108;
        iArr2[296] = 121744;
        iArr2[297] = 126412;
        iArr2[298] = 124102;
        iArr2[299] = 121736;
        iArr2[300] = 117124;
        iArr2[301] = 121732;
        iArr2[302] = 117122;
        iArr2[303] = 121730;
        iArr2[304] = 103328;
        iArr2[305] = 117208;
        iArr2[306] = 124142;
        iArr2[307] = 112544;
        iArr2[308] = 103312;
        iArr2[309] = 117196;
        iArr2[310] = 112528;
        iArr2[311] = 121804;
        iArr2[312] = 117190;
        iArr2[313] = 112520;
        iArr2[314] = 103300;
        iArr2[315] = 112516;
        iArr2[316] = 103298;
        iArr2[317] = 112514;
        iArr2[318] = 75680;
        iArr2[319] = 103384;
        iArr2[320] = 117230;
        iArr2[321] = 94112;
        iArr2[322] = 75664;
        iArr2[323] = 103372;
        iArr2[324] = 94096;
        iArr2[325] = 112588;
        iArr2[326] = 103366;
        iArr2[327] = 94088;
        iArr2[328] = 75652;
        iArr2[329] = 94084;
        iArr2[330] = 75650;
        iArr2[331] = 75736;
        iArr2[332] = 103406;
        iArr2[333] = 94168;
        iArr2[334] = 75724;
        iArr2[335] = 94156;
        iArr2[336] = 75718;
        iArr2[337] = 94150;
        iArr2[338] = 75758;
        iArr2[339] = 128592;
        iArr2[340] = 129836;
        iArr2[341] = 91640;
        iArr2[342] = 128584;
        iArr2[343] = 129830;
        iArr2[344] = 91388;
        iArr2[345] = 128580;
        iArr2[346] = 91262;
        iArr2[347] = 128578;
        iArr2[348] = 123984;
        iArr2[349] = 127532;
        iArr2[350] = 126160;
        iArr2[351] = 123976;
        iArr2[352] = 127526;
        iArr2[353] = 126152;
        iArr2[354] = 128614;
        iArr2[355] = 126148;
        iArr2[356] = 123970;
        iArr2[357] = 126146;
        iArr2[358] = 116944;
        iArr2[359] = 124012;
        iArr2[360] = 121296;
        iArr2[361] = 116936;
        iArr2[362] = 124006;
        iArr2[363] = 121288;
        iArr2[364] = 126182;
        iArr2[365] = 121284;
        iArr2[366] = 116930;
        iArr2[367] = 121282;
        iArr2[368] = 102864;
        iArr2[369] = 116972;
        iArr2[370] = 111568;
        iArr2[371] = 102856;
        iArr2[372] = 116966;
        iArr2[373] = 111560;
        iArr2[374] = 121318;
        iArr2[375] = 111556;
        iArr2[376] = 102850;
        iArr2[377] = 111554;
        iArr2[378] = 74704;
        iArr2[379] = 102892;
        iArr2[380] = 92112;
        iArr2[381] = 74696;
        iArr2[382] = 102886;
        iArr2[383] = 92104;
        iArr2[384] = 111590;
        iArr2[385] = 92100;
        iArr2[386] = 74690;
        iArr2[387] = 92098;
        iArr2[388] = 74732;
        iArr2[389] = 92140;
        iArr2[390] = 74726;
        iArr2[391] = 92134;
        iArr2[392] = 128552;
        iArr2[393] = 129814;
        iArr2[394] = 90876;
        iArr2[395] = 128548;
        iArr2[396] = 90750;
        iArr2[397] = 128546;
        iArr2[398] = 123944;
        iArr2[399] = 127510;
        iArr2[400] = 126056;
        iArr2[401] = 128566;
        iArr2[402] = 126052;
        iArr2[403] = 123938;
        iArr2[404] = 126050;
        iArr2[405] = 116840;
        iArr2[406] = 123958;
        iArr2[407] = 121064;
        iArr2[408] = 116836;
        iArr2[409] = 121060;
        iArr2[410] = 116834;
        iArr2[411] = 121058;
        iArr2[412] = 102632;
        iArr2[413] = 116854;
        iArr2[414] = 111080;
        iArr2[415] = 121078;
        iArr2[416] = 111076;
        iArr2[417] = 102626;
        iArr2[418] = 111074;
        iArr2[419] = 74216;
        iArr2[420] = 102646;
        iArr2[421] = 91112;
        iArr2[422] = 74212;
        iArr2[423] = 91108;
        iArr2[424] = 74210;
        iArr2[425] = 91106;
        iArr2[426] = 74230;
        iArr2[427] = 91126;
        iArr2[428] = 128532;
        iArr2[429] = 90494;
        iArr2[430] = 128530;
        iArr2[431] = 123924;
        iArr2[432] = 126004;
        iArr2[433] = 123922;
        iArr2[434] = 126002;
        iArr2[435] = 116788;
        iArr2[436] = 120948;
        iArr2[437] = 116786;
        iArr2[438] = 120946;
        iArr2[439] = 102516;
        iArr2[440] = 110836;
        iArr2[441] = 102514;
        iArr2[442] = 110834;
        iArr2[443] = 73972;
        iArr2[444] = 90612;
        iArr2[445] = 73970;
        iArr2[446] = 90610;
        iArr2[447] = 128522;
        iArr2[448] = 123914;
        iArr2[449] = 125978;
        iArr2[450] = 116762;
        iArr2[451] = 120890;
        iArr2[452] = 102458;
        iArr2[453] = 110714;
        iArr2[454] = 123552;
        iArr2[455] = 127320;
        iArr2[456] = 129198;
        iArr2[457] = 123536;
        iArr2[458] = 127308;
        iArr2[459] = 123528;
        iArr2[460] = 127302;
        iArr2[461] = 123524;
        iArr2[462] = 123522;
        iArr2[463] = 116128;
        iArr2[464] = 123608;
        iArr2[465] = 127342;
        iArr2[466] = 116112;
        iArr2[467] = 123596;
        iArr2[468] = 116104;
        iArr2[469] = 123590;
        iArr2[470] = 116100;
        iArr2[471] = 116098;
        iArr2[472] = 101280;
        iArr2[473] = 116184;
        iArr2[474] = 123630;
        iArr2[475] = 101264;
        iArr2[476] = 116172;
        iArr2[477] = 101256;
        iArr2[478] = 116166;
        iArr2[479] = 101252;
        iArr2[480] = 101250;
        iArr2[481] = 71584;
        iArr2[482] = 101336;
        iArr2[483] = 116206;
        iArr2[484] = 71568;
        iArr2[485] = 101324;
        iArr2[486] = 71560;
        iArr2[487] = 101318;
        iArr2[488] = 71556;
        iArr2[489] = 71554;
        iArr2[490] = 71640;
        iArr2[491] = 101358;
        iArr2[492] = 71628;
        iArr2[493] = 71622;
        iArr2[494] = 71662;
        iArr2[495] = 127824;
        iArr2[496] = 129452;
        iArr2[497] = 79352;
        iArr2[498] = 127816;
        iArr2[499] = 129446;
        iArr2[500] = 79100;
        iArr2[501] = 127812;
        iArr2[502] = 78974;
        iArr2[503] = 127810;
        iArr2[504] = 123472;
        iArr2[505] = 127276;
        iArr2[506] = 124624;
        iArr2[507] = 123464;
        iArr2[508] = 127270;
        iArr2[509] = 124616;
        iArr2[510] = 127846;
        iArr2[511] = 124612;
        iArr2[512] = 123458;
        iArr2[513] = 124610;
        iArr2[514] = 115920;
        iArr2[515] = 123500;
        iArr2[516] = 118224;
        iArr2[517] = 115912;
        iArr2[518] = 123494;
        iArr2[519] = 118216;
        iArr2[520] = 124646;
        iArr2[521] = 118212;
        iArr2[522] = 115906;
        iArr2[523] = 118210;
        iArr2[524] = 100816;
        iArr2[525] = 115948;
        iArr2[526] = 105424;
        iArr2[527] = 100808;
        iArr2[528] = 115942;
        iArr2[529] = 105416;
        iArr2[530] = 118246;
        iArr2[531] = 105412;
        iArr2[532] = 100802;
        iArr2[533] = 105410;
        iArr2[534] = 70608;
        iArr2[535] = 100844;
        iArr2[536] = 79824;
        iArr2[537] = 70600;
        iArr2[538] = 100838;
        iArr2[539] = 79816;
        iArr2[540] = 105446;
        iArr2[541] = 79812;
        iArr2[542] = 70594;
        iArr2[543] = 79810;
        iArr2[544] = 70636;
        iArr2[545] = 79852;
        iArr2[546] = 70630;
        iArr2[547] = 79846;
        iArr2[548] = 129960;
        iArr2[549] = 95728;
        iArr2[550] = 113404;
        iArr2[551] = 129956;
        iArr2[552] = 95480;
        iArr2[553] = 113278;
        iArr2[554] = 129954;
        iArr2[555] = 95356;
        iArr2[556] = 95294;
        iArr2[557] = 127784;
        iArr2[558] = 129430;
        iArr2[559] = 78588;
        iArr2[560] = 128872;
        iArr2[561] = 129974;
        iArr2[562] = 95996;
        iArr2[563] = 78462;
        iArr2[564] = 128868;
        iArr2[565] = 127778;
        iArr2[566] = 95870;
        iArr2[567] = 128866;
        iArr2[568] = 123432;
        iArr2[569] = 127254;
        iArr2[570] = 124520;
        iArr2[571] = 123428;
        iArr2[572] = 126696;
        iArr2[573] = 128886;
        iArr2[574] = 123426;
        iArr2[575] = 126692;
        iArr2[576] = 124514;
        iArr2[577] = 126690;
        iArr2[578] = 115816;
        iArr2[579] = 123446;
        iArr2[580] = 117992;
        iArr2[581] = 115812;
        iArr2[582] = 122344;
        iArr2[583] = 117988;
        iArr2[584] = 115810;
        iArr2[585] = 122340;
        iArr2[586] = 117986;
        iArr2[587] = 122338;
        iArr2[588] = 100584;
        iArr2[589] = 115830;
        iArr2[590] = 104936;
        iArr2[591] = 100580;
        iArr2[592] = 113640;
        iArr2[593] = 104932;
        iArr2[594] = 100578;
        iArr2[595] = 113636;
        iArr2[596] = 104930;
        iArr2[597] = 113634;
        iArr2[598] = 70120;
        iArr2[599] = 100598;
        iArr2[600] = 78824;
        iArr2[601] = 70116;
        iArr2[602] = 96232;
        iArr2[603] = 78820;
        iArr2[604] = 70114;
        iArr2[605] = 96228;
        iArr2[606] = 78818;
        iArr2[607] = 96226;
        iArr2[608] = 70134;
        iArr2[609] = 78838;
        iArr2[610] = 129940;
        iArr2[611] = 94968;
        iArr2[612] = 113022;
        iArr2[613] = 129938;
        iArr2[614] = 94844;
        iArr2[615] = 94782;
        iArr2[616] = 127764;
        iArr2[617] = 78206;
        iArr2[618] = 128820;
        iArr2[619] = 127762;
        iArr2[620] = 95102;
        iArr2[621] = 128818;
        iArr2[622] = 123412;
        iArr2[623] = 124468;
        iArr2[624] = 123410;
        iArr2[625] = 126580;
        iArr2[626] = 124466;
        iArr2[627] = 126578;
        iArr2[628] = 115764;
        iArr2[629] = 117876;
        iArr2[630] = 115762;
        iArr2[631] = 122100;
        iArr2[632] = 117874;
        iArr2[633] = 122098;
        iArr2[634] = 100468;
        iArr2[635] = 104692;
        iArr2[636] = 100466;
        iArr2[637] = 113140;
        iArr2[638] = 104690;
        iArr2[639] = 113138;
        iArr2[640] = 69876;
        iArr2[641] = 78324;
        iArr2[642] = 69874;
        iArr2[643] = 95220;
        iArr2[644] = 78322;
        iArr2[645] = 95218;
        iArr2[646] = 129930;
        iArr2[647] = 94588;
        iArr2[648] = 94526;
        iArr2[649] = 127754;
        iArr2[650] = 128794;
        iArr2[651] = 123402;
        iArr2[652] = 124442;
        iArr2[653] = 126522;
        iArr2[654] = 115738;
        iArr2[655] = 117818;
        iArr2[656] = 121978;
        iArr2[657] = 100410;
        iArr2[658] = 104570;
        iArr2[659] = 112890;
        iArr2[660] = 69754;
        iArr2[661] = 78074;
        iArr2[662] = 94714;
        iArr2[663] = 94398;
        iArr2[664] = 123216;
        iArr2[665] = 127148;
        iArr2[666] = 123208;
        iArr2[667] = 127142;
        iArr2[668] = 123204;
        iArr2[669] = 123202;
        iArr2[670] = 115408;
        iArr2[671] = 123244;
        iArr2[672] = 115400;
        iArr2[673] = 123238;
        iArr2[674] = 115396;
        iArr2[675] = 115394;
        iArr2[676] = 99792;
        iArr2[677] = 115436;
        iArr2[678] = 99784;
        iArr2[679] = 115430;
        iArr2[680] = 99780;
        iArr2[681] = 99778;
        iArr2[682] = 68560;
        iArr2[683] = 99820;
        iArr2[684] = 68552;
        iArr2[685] = 99814;
        iArr2[686] = 68548;
        iArr2[687] = 68546;
        iArr2[688] = 68588;
        iArr2[689] = 68582;
        iArr2[690] = 127400;
        iArr2[691] = 129238;
        iArr2[692] = 72444;
        iArr2[693] = 127396;
        iArr2[694] = 72318;
        iArr2[695] = 127394;
        iArr2[696] = 123176;
        iArr2[697] = 127126;
        iArr2[698] = 123752;
        iArr2[699] = 123172;
        iArr2[700] = 123748;
        iArr2[701] = 123170;
        iArr2[702] = 123746;
        iArr2[703] = 115304;
        iArr2[704] = 123190;
        iArr2[705] = 116456;
        iArr2[706] = 115300;
        iArr2[707] = 116452;
        iArr2[708] = 115298;
        iArr2[709] = 116450;
        iArr2[710] = 99560;
        iArr2[711] = 115318;
        iArr2[712] = 101864;
        iArr2[713] = 99556;
        iArr2[714] = 101860;
        iArr2[715] = 99554;
        iArr2[716] = 101858;
        iArr2[717] = 68072;
        iArr2[718] = 99574;
        iArr2[719] = 72680;
        iArr2[720] = 68068;
        iArr2[721] = 72676;
        iArr2[722] = 68066;
        iArr2[723] = 72674;
        iArr2[724] = 68086;
        iArr2[725] = 72694;
        iArr2[726] = 129492;
        iArr2[727] = 80632;
        iArr2[728] = 105854;
        iArr2[729] = 129490;
        iArr2[730] = 80508;
        iArr2[731] = 80446;
        iArr2[732] = 127380;
        iArr2[733] = 72062;
        iArr2[734] = 127924;
        iArr2[735] = 127378;
        iArr2[736] = 80766;
        iArr2[737] = 127922;
        iArr2[738] = 123156;
        iArr2[739] = 123700;
        iArr2[740] = 123154;
        iArr2[741] = 124788;
        iArr2[742] = 123698;
        iArr2[743] = 124786;
        iArr2[744] = 115252;
        iArr2[745] = 116340;
        iArr2[746] = 115250;
        iArr2[747] = 118516;
        iArr2[748] = 116338;
        iArr2[749] = 118514;
        iArr2[750] = 99444;
        iArr2[751] = 101620;
        iArr2[752] = 99442;
        iArr2[753] = 105972;
        iArr2[754] = 101618;
        iArr2[755] = 105970;
        iArr2[756] = 67828;
        iArr2[757] = 72180;
        iArr2[758] = 67826;
        iArr2[759] = 80884;
        iArr2[760] = 72178;
        iArr2[761] = 80882;
        iArr2[762] = 97008;
        iArr2[763] = 114044;
        iArr2[764] = 96888;
        iArr2[765] = 113982;
        iArr2[766] = 96828;
        iArr2[767] = 96798;
        iArr2[768] = 129482;
        iArr2[769] = 80252;
        iArr2[770] = 130010;
        iArr2[771] = 97148;
        iArr2[772] = 80190;
        iArr2[773] = 97086;
        iArr2[774] = 127370;
        iArr2[775] = 127898;
        iArr2[776] = 128954;
        iArr2[777] = 123146;
        iArr2[778] = 123674;
        iArr2[779] = 124730;
        iArr2[780] = 126842;
        iArr2[781] = 115226;
        iArr2[782] = 116282;
        iArr2[783] = 118394;
        iArr2[784] = 122618;
        iArr2[785] = 99386;
        iArr2[786] = 101498;
        iArr2[787] = 105722;
        iArr2[788] = 114170;
        iArr2[789] = 67706;
        iArr2[790] = 71930;
        iArr2[791] = 80378;
        iArr2[792] = 96632;
        iArr2[793] = 113854;
        iArr2[794] = 96572;
        iArr2[795] = 96542;
        iArr2[796] = 80062;
        iArr2[797] = 96702;
        iArr2[798] = 96444;
        iArr2[799] = 96414;
        iArr2[800] = 96350;
        iArr2[801] = 123048;
        iArr2[802] = 123044;
        iArr2[803] = 123042;
        iArr2[804] = 115048;
        iArr2[805] = 123062;
        iArr2[806] = 115044;
        iArr2[807] = 115042;
        iArr2[808] = 99048;
        iArr2[809] = 115062;
        iArr2[810] = 99044;
        iArr2[811] = 99042;
        iArr2[812] = 67048;
        iArr2[813] = 99062;
        iArr2[814] = 67044;
        iArr2[815] = 67042;
        iArr2[816] = 67062;
        iArr2[817] = 127188;
        iArr2[818] = 68990;
        iArr2[819] = 127186;
        iArr2[820] = 123028;
        iArr2[821] = 123316;
        iArr2[822] = 123026;
        iArr2[823] = 123314;
        iArr2[824] = 114996;
        iArr2[825] = 115572;
        iArr2[826] = 114994;
        iArr2[827] = 115570;
        iArr2[828] = 98932;
        iArr2[829] = 100084;
        iArr2[830] = 98930;
        iArr2[831] = 100082;
        iArr2[832] = 66804;
        iArr2[833] = 69108;
        iArr2[834] = 66802;
        iArr2[835] = 69106;
        iArr2[836] = 129258;
        iArr2[837] = 73084;
        iArr2[838] = 73022;
        iArr2[839] = 127178;
        iArr2[840] = 127450;
        iArr2[841] = 123018;
        iArr2[842] = 123290;
        iArr2[843] = 123834;
        iArr2[844] = 114970;
        iArr2[845] = 115514;
        iArr2[846] = 116602;
        iArr2[847] = 98874;
        iArr2[848] = 99962;
        iArr2[849] = 102138;
        iArr2[850] = 66682;
        iArr2[851] = 68858;
        iArr2[852] = 73210;
        iArr2[853] = 81272;
        iArr2[854] = 106174;
        iArr2[855] = 81212;
        iArr2[856] = 81182;
        iArr2[857] = 72894;
        iArr2[858] = 81342;
        iArr2[859] = 97648;
        iArr2[860] = 114364;
        iArr2[861] = 97592;
        iArr2[862] = 114334;
        iArr2[863] = 97564;
        iArr2[864] = 97550;
        iArr2[865] = 81084;
        iArr2[866] = 97724;
        iArr2[867] = 81054;
        iArr2[868] = 97694;
        iArr2[869] = 97464;
        iArr2[870] = 114270;
        iArr2[871] = 97436;
        iArr2[872] = 97422;
        iArr2[873] = 80990;
        iArr2[874] = 97502;
        iArr2[875] = 97372;
        iArr2[876] = 97358;
        iArr2[877] = 97326;
        iArr2[878] = 114868;
        iArr2[879] = 114866;
        iArr2[880] = 98676;
        iArr2[881] = 98674;
        iArr2[882] = 66292;
        iArr2[883] = 66290;
        iArr2[884] = 123098;
        iArr2[885] = 114842;
        iArr2[886] = 115130;
        iArr2[887] = 98618;
        iArr2[888] = 99194;
        iArr2[889] = 66170;
        iArr2[890] = 67322;
        iArr2[891] = 69310;
        iArr2[892] = 73404;
        iArr2[893] = 73374;
        iArr2[894] = 81592;
        iArr2[895] = 106334;
        iArr2[896] = 81564;
        iArr2[897] = 81550;
        iArr2[898] = 73310;
        iArr2[899] = 81630;
        iArr2[900] = 97968;
        iArr2[901] = 114524;
        iArr2[902] = 97944;
        iArr2[903] = 114510;
        iArr2[904] = 97932;
        iArr2[905] = 97926;
        iArr2[906] = 81500;
        iArr2[907] = 98012;
        iArr2[908] = 81486;
        iArr2[909] = 97998;
        iArr2[910] = 97880;
        iArr2[911] = 114478;
        iArr2[912] = 97868;
        iArr2[913] = 97862;
        iArr2[914] = 81454;
        iArr2[915] = 97902;
        iArr2[916] = 97836;
        iArr2[917] = 97830;
        iArr2[918] = 69470;
        iArr2[919] = 73564;
        iArr2[920] = 73550;
        iArr2[921] = 81752;
        iArr2[922] = 106414;
        iArr2[923] = 81740;
        iArr2[924] = 81734;
        iArr2[925] = 73518;
        iArr2[926] = 81774;
        iArr2[927] = 81708;
        iArr2[928] = 81702;
        int[] iArr3 = new int[MOD];
        // fill-array-data instruction
        iArr3[0] = 109536;
        iArr3[1] = 120312;
        iArr3[2] = 86976;
        iArr3[3] = 109040;
        iArr3[4] = 120060;
        iArr3[5] = 86496;
        iArr3[6] = 108792;
        iArr3[7] = 119934;
        iArr3[8] = 86256;
        iArr3[9] = 108668;
        iArr3[10] = 86136;
        iArr3[11] = 129744;
        iArr3[12] = 89056;
        iArr3[13] = 110072;
        iArr3[14] = 129736;
        iArr3[15] = 88560;
        iArr3[16] = 109820;
        iArr3[17] = 129732;
        iArr3[18] = 88312;
        iArr3[19] = 109694;
        iArr3[20] = 129730;
        iArr3[21] = 88188;
        iArr3[22] = 128464;
        iArr3[23] = 129772;
        iArr3[24] = 89592;
        iArr3[25] = 128456;
        iArr3[26] = 129766;
        iArr3[27] = 89340;
        iArr3[28] = 128452;
        iArr3[29] = 89214;
        iArr3[30] = 128450;
        iArr3[31] = 125904;
        iArr3[32] = 128492;
        iArr3[33] = 125896;
        iArr3[34] = 128486;
        iArr3[35] = 125892;
        iArr3[36] = 125890;
        iArr3[37] = 120784;
        iArr3[38] = 125932;
        iArr3[39] = 120776;
        iArr3[40] = 125926;
        iArr3[41] = 120772;
        iArr3[42] = 120770;
        iArr3[43] = 110544;
        iArr3[44] = 120812;
        iArr3[45] = 110536;
        iArr3[46] = 120806;
        iArr3[47] = 110532;
        iArr3[48] = 84928;
        iArr3[49] = 108016;
        iArr3[50] = 119548;
        iArr3[51] = 84448;
        iArr3[52] = 107768;
        iArr3[53] = 119422;
        iArr3[54] = 84208;
        iArr3[55] = 107644;
        iArr3[56] = 84088;
        iArr3[57] = 107582;
        iArr3[58] = 84028;
        iArr3[59] = 129640;
        iArr3[60] = 85488;
        iArr3[61] = 108284;
        iArr3[62] = 129636;
        iArr3[63] = 85240;
        iArr3[64] = 108158;
        iArr3[65] = 129634;
        iArr3[66] = 85116;
        iArr3[67] = 85054;
        iArr3[68] = 128232;
        iArr3[69] = 129654;
        iArr3[70] = 85756;
        iArr3[71] = 128228;
        iArr3[72] = 85630;
        iArr3[73] = 128226;
        iArr3[74] = 125416;
        iArr3[75] = 128246;
        iArr3[76] = 125412;
        iArr3[77] = 125410;
        iArr3[78] = 119784;
        iArr3[79] = 125430;
        iArr3[80] = 119780;
        iArr3[81] = 119778;
        iArr3[82] = 108520;
        iArr3[83] = 119798;
        iArr3[84] = 108516;
        iArr3[85] = 108514;
        iArr3[86] = 83424;
        iArr3[87] = 107256;
        iArr3[88] = 119166;
        iArr3[89] = 83184;
        iArr3[90] = 107132;
        iArr3[91] = 83064;
        iArr3[92] = 107070;
        iArr3[93] = 83004;
        iArr3[94] = 82974;
        iArr3[95] = 129588;
        iArr3[96] = 83704;
        iArr3[97] = 107390;
        iArr3[98] = 129586;
        iArr3[99] = 83580;
        iArr3[100] = 83518;
        iArr3[101] = 128116;
        iArr3[102] = 83838;
        iArr3[103] = 128114;
        iArr3[104] = 125172;
        iArr3[105] = 125170;
        iArr3[106] = 119284;
        iArr3[107] = 119282;
        iArr3[108] = 107508;
        iArr3[109] = 107506;
        iArr3[110] = 82672;
        iArr3[111] = 106876;
        iArr3[112] = 82552;
        iArr3[113] = 106814;
        iArr3[114] = 82492;
        iArr3[115] = 82462;
        iArr3[116] = 129562;
        iArr3[117] = 82812;
        iArr3[118] = 82750;
        iArr3[119] = 128058;
        iArr3[120] = 125050;
        iArr3[121] = 119034;
        iArr3[122] = 82296;
        iArr3[123] = 106686;
        iArr3[124] = 82236;
        iArr3[125] = 82206;
        iArr3[126] = 82366;
        iArr3[127] = 82108;
        iArr3[128] = 82078;
        iArr3[129] = 76736;
        iArr3[130] = 103920;
        iArr3[131] = 117500;
        iArr3[132] = 76256;
        iArr3[133] = 103672;
        iArr3[134] = 117374;
        iArr3[135] = 76016;
        iArr3[136] = 103548;
        iArr3[137] = 75896;
        iArr3[138] = 103486;
        iArr3[139] = 75836;
        iArr3[140] = 129384;
        iArr3[141] = 77296;
        iArr3[142] = 104188;
        iArr3[143] = 129380;
        iArr3[144] = 77048;
        iArr3[145] = 104062;
        iArr3[146] = 129378;
        iArr3[147] = 76924;
        iArr3[148] = 76862;
        iArr3[149] = 127720;
        iArr3[150] = 129398;
        iArr3[151] = 77564;
        iArr3[152] = 127716;
        iArr3[153] = 77438;
        iArr3[154] = 127714;
        iArr3[155] = 124392;
        iArr3[156] = 127734;
        iArr3[157] = 124388;
        iArr3[158] = 124386;
        iArr3[159] = 117736;
        iArr3[160] = 124406;
        iArr3[161] = 117732;
        iArr3[162] = 117730;
        iArr3[163] = 104424;
        iArr3[164] = 117750;
        iArr3[165] = 104420;
        iArr3[166] = 104418;
        iArr3[167] = 112096;
        iArr3[168] = 121592;
        iArr3[169] = 126334;
        iArr3[170] = 92608;
        iArr3[171] = 111856;
        iArr3[172] = 121468;
        iArr3[173] = 92384;
        iArr3[174] = 111736;
        iArr3[175] = 121406;
        iArr3[176] = 92272;
        iArr3[177] = 111676;
        iArr3[178] = 92216;
        iArr3[179] = 111646;
        iArr3[180] = 92188;
        iArr3[181] = 75232;
        iArr3[182] = 103160;
        iArr3[183] = 117118;
        iArr3[184] = 93664;
        iArr3[185] = 74992;
        iArr3[186] = 103036;
        iArr3[187] = 93424;
        iArr3[188] = 112252;
        iArr3[189] = 102974;
        iArr3[190] = 93304;
        iArr3[191] = 74812;
        iArr3[192] = 93244;
        iArr3[193] = 74782;
        iArr3[194] = 93214;
        iArr3[195] = 129332;
        iArr3[196] = 75512;
        iArr3[197] = 103294;
        iArr3[198] = 129908;
        iArr3[199] = 129330;
        iArr3[200] = 93944;
        iArr3[201] = 75388;
        iArr3[202] = 129906;
        iArr3[203] = 93820;
        iArr3[204] = 75326;
        iArr3[205] = 93758;
        iArr3[206] = 127604;
        iArr3[207] = 75646;
        iArr3[208] = 128756;
        iArr3[209] = 127602;
        iArr3[210] = 94078;
        iArr3[211] = 128754;
        iArr3[212] = 124148;
        iArr3[213] = 126452;
        iArr3[214] = 124146;
        iArr3[215] = 126450;
        iArr3[216] = 117236;
        iArr3[217] = 121844;
        iArr3[218] = 117234;
        iArr3[219] = 121842;
        iArr3[220] = 103412;
        iArr3[221] = 103410;
        iArr3[222] = 91584;
        iArr3[223] = 111344;
        iArr3[224] = 121212;
        iArr3[225] = 91360;
        iArr3[226] = 111224;
        iArr3[227] = 121150;
        iArr3[228] = 91248;
        iArr3[229] = 111164;
        iArr3[230] = 91192;
        iArr3[231] = 111134;
        iArr3[232] = 91164;
        iArr3[233] = 91150;
        iArr3[234] = 74480;
        iArr3[235] = 102780;
        iArr3[236] = 91888;
        iArr3[237] = 74360;
        iArr3[238] = 102718;
        iArr3[239] = 91768;
        iArr3[240] = 111422;
        iArr3[241] = 91708;
        iArr3[242] = 74270;
        iArr3[243] = 91678;
        iArr3[244] = 129306;
        iArr3[245] = 74620;
        iArr3[246] = 129850;
        iArr3[247] = 92028;
        iArr3[248] = 74558;
        iArr3[249] = 91966;
        iArr3[250] = 127546;
        iArr3[251] = 128634;
        iArr3[252] = 124026;
        iArr3[253] = 126202;
        iArr3[254] = 116986;
        iArr3[255] = 121338;
        iArr3[256] = 102906;
        iArr3[257] = 90848;
        iArr3[258] = 110968;
        iArr3[259] = 121022;
        iArr3[260] = 90736;
        iArr3[261] = 110908;
        iArr3[262] = 90680;
        iArr3[263] = 110878;
        iArr3[264] = 90652;
        iArr3[265] = 90638;
        iArr3[266] = 74104;
        iArr3[267] = 102590;
        iArr3[268] = 91000;
        iArr3[269] = 74044;
        iArr3[270] = 90940;
        iArr3[271] = 74014;
        iArr3[272] = 90910;
        iArr3[273] = 74174;
        iArr3[274] = 91070;
        iArr3[275] = 90480;
        iArr3[276] = 110780;
        iArr3[277] = 90424;
        iArr3[278] = 110750;
        iArr3[279] = 90396;
        iArr3[280] = 90382;
        iArr3[281] = 73916;
        iArr3[282] = 90556;
        iArr3[283] = 73886;
        iArr3[284] = 90526;
        iArr3[285] = 90296;
        iArr3[286] = 110686;
        iArr3[287] = 90268;
        iArr3[288] = 90254;
        iArr3[289] = 73822;
        iArr3[290] = 90334;
        iArr3[291] = 90204;
        iArr3[292] = 90190;
        iArr3[293] = 71136;
        iArr3[294] = 101112;
        iArr3[295] = 116094;
        iArr3[296] = 70896;
        iArr3[297] = 100988;
        iArr3[298] = 70776;
        iArr3[299] = 100926;
        iArr3[300] = 70716;
        iArr3[301] = 70686;
        iArr3[302] = 129204;
        iArr3[303] = 71416;
        iArr3[304] = 101246;
        iArr3[305] = 129202;
        iArr3[306] = 71292;
        iArr3[307] = 71230;
        iArr3[308] = 127348;
        iArr3[309] = 71550;
        iArr3[310] = 127346;
        iArr3[311] = 123636;
        iArr3[312] = 123634;
        iArr3[313] = 116212;
        iArr3[314] = 116210;
        iArr3[315] = 101364;
        iArr3[316] = 101362;
        iArr3[317] = 79296;
        iArr3[318] = 105200;
        iArr3[319] = 118140;
        iArr3[320] = 79072;
        iArr3[321] = 105080;
        iArr3[322] = 118078;
        iArr3[323] = 78960;
        iArr3[324] = 105020;
        iArr3[325] = 78904;
        iArr3[326] = 104990;
        iArr3[327] = 78876;
        iArr3[328] = 78862;
        iArr3[329] = 70384;
        iArr3[330] = 100732;
        iArr3[331] = 79600;
        iArr3[332] = 70264;
        iArr3[333] = 100670;
        iArr3[334] = 79480;
        iArr3[335] = 105278;
        iArr3[336] = 79420;
        iArr3[337] = 70174;
        iArr3[338] = 79390;
        iArr3[339] = 129178;
        iArr3[340] = 70524;
        iArr3[341] = 129466;
        iArr3[342] = 79740;
        iArr3[343] = 70462;
        iArr3[344] = 79678;
        iArr3[345] = 127290;
        iArr3[346] = 127866;
        iArr3[347] = 123514;
        iArr3[348] = 124666;
        iArr3[349] = 115962;
        iArr3[350] = 118266;
        iArr3[351] = 100858;
        iArr3[352] = 113376;
        iArr3[353] = 122232;
        iArr3[354] = 126654;
        iArr3[355] = 95424;
        iArr3[356] = 113264;
        iArr3[357] = 122172;
        iArr3[358] = 95328;
        iArr3[359] = 113208;
        iArr3[360] = 122142;
        iArr3[361] = 95280;
        iArr3[362] = 113180;
        iArr3[363] = 95256;
        iArr3[364] = 113166;
        iArr3[365] = 95244;
        iArr3[366] = 78560;
        iArr3[367] = 104824;
        iArr3[368] = 117950;
        iArr3[369] = 95968;
        iArr3[370] = 78448;
        iArr3[371] = 104764;
        iArr3[372] = 95856;
        iArr3[373] = 113468;
        iArr3[374] = 104734;
        iArr3[375] = 95800;
        iArr3[376] = 78364;
        iArr3[377] = 95772;
        iArr3[378] = 78350;
        iArr3[379] = 95758;
        iArr3[380] = 70008;
        iArr3[381] = 100542;
        iArr3[382] = 78712;
        iArr3[383] = 69948;
        iArr3[384] = 96120;
        iArr3[385] = 78652;
        iArr3[386] = 69918;
        iArr3[387] = 96060;
        iArr3[388] = 78622;
        iArr3[389] = 96030;
        iArr3[390] = 70078;
        iArr3[391] = 78782;
        iArr3[392] = 96190;
        iArr3[393] = 94912;
        iArr3[394] = 113008;
        iArr3[395] = 122044;
        iArr3[396] = 94816;
        iArr3[397] = 112952;
        iArr3[398] = 122014;
        iArr3[399] = 94768;
        iArr3[400] = 112924;
        iArr3[401] = 94744;
        iArr3[402] = 112910;
        iArr3[403] = 94732;
        iArr3[404] = 94726;
        iArr3[405] = 78192;
        iArr3[406] = 104636;
        iArr3[407] = 95088;
        iArr3[408] = 78136;
        iArr3[409] = 104606;
        iArr3[410] = 95032;
        iArr3[411] = 113054;
        iArr3[412] = 95004;
        iArr3[413] = 78094;
        iArr3[414] = 94990;
        iArr3[415] = 69820;
        iArr3[416] = 78268;
        iArr3[417] = 69790;
        iArr3[418] = 95164;
        iArr3[419] = 78238;
        iArr3[420] = 95134;
        iArr3[421] = 94560;
        iArr3[422] = 112824;
        iArr3[423] = 121950;
        iArr3[424] = 94512;
        iArr3[425] = 112796;
        iArr3[426] = 94488;
        iArr3[427] = 112782;
        iArr3[428] = 94476;
        iArr3[429] = 94470;
        iArr3[430] = 78008;
        iArr3[431] = 104542;
        iArr3[432] = 94648;
        iArr3[433] = 77980;
        iArr3[434] = 94620;
        iArr3[435] = 77966;
        iArr3[436] = 94606;
        iArr3[437] = 69726;
        iArr3[438] = 78046;
        iArr3[439] = 94686;
        iArr3[440] = 94384;
        iArr3[441] = 112732;
        iArr3[442] = 94360;
        iArr3[443] = 112718;
        iArr3[444] = 94348;
        iArr3[445] = 94342;
        iArr3[446] = 77916;
        iArr3[447] = 94428;
        iArr3[448] = 77902;
        iArr3[449] = 94414;
        iArr3[450] = 94296;
        iArr3[451] = 112686;
        iArr3[452] = 94284;
        iArr3[453] = 94278;
        iArr3[454] = 77870;
        iArr3[455] = 94318;
        iArr3[456] = 94252;
        iArr3[457] = 94246;
        iArr3[458] = 68336;
        iArr3[459] = 99708;
        iArr3[460] = 68216;
        iArr3[461] = 99646;
        iArr3[462] = 68156;
        iArr3[463] = 68126;
        iArr3[464] = 68476;
        iArr3[465] = 68414;
        iArr3[466] = 127162;
        iArr3[467] = 123258;
        iArr3[468] = 115450;
        iArr3[469] = 99834;
        iArr3[470] = 72416;
        iArr3[471] = 101752;
        iArr3[472] = 116414;
        iArr3[473] = 72304;
        iArr3[474] = 101692;
        iArr3[475] = 72248;
        iArr3[476] = 101662;
        iArr3[477] = 72220;
        iArr3[478] = 72206;
        iArr3[479] = 67960;
        iArr3[480] = 99518;
        iArr3[481] = 72568;
        iArr3[482] = 67900;
        iArr3[483] = 72508;
        iArr3[484] = 67870;
        iArr3[485] = 72478;
        iArr3[486] = 68030;
        iArr3[487] = 72638;
        iArr3[488] = 80576;
        iArr3[489] = 105840;
        iArr3[490] = 118460;
        iArr3[491] = 80480;
        iArr3[492] = 105784;
        iArr3[493] = 118430;
        iArr3[494] = 80432;
        iArr3[495] = 105756;
        iArr3[496] = 80408;
        iArr3[497] = 105742;
        iArr3[498] = 80396;
        iArr3[499] = 80390;
        iArr3[500] = 72048;
        iArr3[501] = 101564;
        iArr3[502] = 80752;
        iArr3[503] = 71992;
        iArr3[504] = 101534;
        iArr3[505] = 80696;
        iArr3[506] = 71964;
        iArr3[507] = 80668;
        iArr3[508] = 71950;
        iArr3[509] = 80654;
        iArr3[510] = 67772;
        iArr3[511] = 72124;
        iArr3[512] = 67742;
        iArr3[513] = 80828;
        iArr3[514] = 72094;
        iArr3[515] = 80798;
        iArr3[516] = 114016;
        iArr3[517] = 122552;
        iArr3[518] = 126814;
        iArr3[519] = 96832;
        iArr3[520] = 113968;
        iArr3[521] = 122524;
        iArr3[522] = 96800;
        iArr3[523] = 113944;
        iArr3[524] = 122510;
        iArr3[525] = 96784;
        iArr3[526] = 113932;
        iArr3[527] = 96776;
        iArr3[528] = 113926;
        iArr3[529] = 96772;
        iArr3[530] = 80224;
        iArr3[531] = 105656;
        iArr3[532] = 118366;
        iArr3[533] = 97120;
        iArr3[534] = 80176;
        iArr3[535] = 105628;
        iArr3[536] = 97072;
        iArr3[537] = 114076;
        iArr3[538] = 105614;
        iArr3[539] = 97048;
        iArr3[540] = 80140;
        iArr3[541] = 97036;
        iArr3[542] = 80134;
        iArr3[543] = 97030;
        iArr3[544] = 71864;
        iArr3[545] = 101470;
        iArr3[546] = 80312;
        iArr3[547] = 71836;
        iArr3[548] = 97208;
        iArr3[549] = 80284;
        iArr3[550] = 71822;
        iArr3[551] = 97180;
        iArr3[552] = 80270;
        iArr3[553] = 97166;
        iArr3[554] = 67678;
        iArr3[555] = 71902;
        iArr3[556] = 80350;
        iArr3[557] = 97246;
        iArr3[558] = 96576;
        iArr3[559] = 113840;
        iArr3[560] = 122460;
        iArr3[561] = 96544;
        iArr3[562] = 113816;
        iArr3[563] = 122446;
        iArr3[564] = 96528;
        iArr3[565] = 113804;
        iArr3[566] = 96520;
        iArr3[567] = 113798;
        iArr3[568] = 96516;
        iArr3[569] = 96514;
        iArr3[570] = 80048;
        iArr3[571] = 105564;
        iArr3[572] = 96688;
        iArr3[573] = 80024;
        iArr3[574] = 105550;
        iArr3[575] = 96664;
        iArr3[576] = 113870;
        iArr3[577] = 96652;
        iArr3[578] = 80006;
        iArr3[579] = 96646;
        iArr3[580] = 71772;
        iArr3[581] = 80092;
        iArr3[582] = 71758;
        iArr3[583] = 96732;
        iArr3[584] = 80078;
        iArr3[585] = 96718;
        iArr3[586] = 96416;
        iArr3[587] = 113752;
        iArr3[588] = 122414;
        iArr3[589] = 96400;
        iArr3[590] = 113740;
        iArr3[591] = 96392;
        iArr3[592] = 113734;
        iArr3[593] = 96388;
        iArr3[594] = 96386;
        iArr3[595] = 79960;
        iArr3[596] = 105518;
        iArr3[597] = 96472;
        iArr3[598] = 79948;
        iArr3[599] = 96460;
        iArr3[600] = 79942;
        iArr3[601] = 96454;
        iArr3[602] = 71726;
        iArr3[603] = 79982;
        iArr3[604] = 96494;
        iArr3[605] = 96336;
        iArr3[606] = 113708;
        iArr3[607] = 96328;
        iArr3[608] = 113702;
        iArr3[609] = 96324;
        iArr3[610] = 96322;
        iArr3[611] = 79916;
        iArr3[612] = 96364;
        iArr3[613] = 79910;
        iArr3[614] = 96358;
        iArr3[615] = 96296;
        iArr3[616] = 113686;
        iArr3[617] = 96292;
        iArr3[618] = 96290;
        iArr3[619] = 79894;
        iArr3[620] = 96310;
        iArr3[621] = 66936;
        iArr3[622] = 99006;
        iArr3[623] = 66876;
        iArr3[624] = 66846;
        iArr3[625] = 67006;
        iArr3[626] = 68976;
        iArr3[627] = 100028;
        iArr3[628] = 68920;
        iArr3[629] = 99998;
        iArr3[630] = 68892;
        iArr3[631] = 68878;
        iArr3[632] = 66748;
        iArr3[633] = 69052;
        iArr3[634] = 66718;
        iArr3[635] = 69022;
        iArr3[636] = 73056;
        iArr3[637] = 102072;
        iArr3[638] = 116574;
        iArr3[639] = 73008;
        iArr3[640] = 102044;
        iArr3[641] = 72984;
        iArr3[642] = 102030;
        iArr3[643] = 72972;
        iArr3[644] = 72966;
        iArr3[645] = 68792;
        iArr3[646] = 99934;
        iArr3[647] = 73144;
        iArr3[648] = 68764;
        iArr3[649] = 73116;
        iArr3[650] = 68750;
        iArr3[651] = 73102;
        iArr3[652] = 66654;
        iArr3[653] = 68830;
        iArr3[654] = 73182;
        iArr3[655] = 81216;
        iArr3[656] = 106160;
        iArr3[657] = 118620;
        iArr3[658] = 81184;
        iArr3[659] = 106136;
        iArr3[660] = 118606;
        iArr3[661] = 81168;
        iArr3[662] = 106124;
        iArr3[663] = 81160;
        iArr3[664] = 106118;
        iArr3[665] = 81156;
        iArr3[666] = 81154;
        iArr3[667] = 72880;
        iArr3[668] = 101980;
        iArr3[669] = 81328;
        iArr3[670] = 72856;
        iArr3[671] = 101966;
        iArr3[672] = 81304;
        iArr3[673] = 106190;
        iArr3[674] = 81292;
        iArr3[675] = 72838;
        iArr3[676] = 81286;
        iArr3[677] = 68700;
        iArr3[678] = 72924;
        iArr3[679] = 68686;
        iArr3[680] = 81372;
        iArr3[681] = 72910;
        iArr3[682] = 81358;
        iArr3[683] = 114336;
        iArr3[684] = 122712;
        iArr3[685] = 126894;
        iArr3[686] = 114320;
        iArr3[687] = 122700;
        iArr3[688] = 114312;
        iArr3[689] = 122694;
        iArr3[690] = 114308;
        iArr3[691] = 114306;
        iArr3[692] = 81056;
        iArr3[693] = 106072;
        iArr3[694] = 118574;
        iArr3[695] = 97696;
        iArr3[696] = 81040;
        iArr3[697] = 106060;
        iArr3[698] = 97680;
        iArr3[699] = 114380;
        iArr3[700] = 106054;
        iArr3[701] = 97672;
        iArr3[702] = 81028;
        iArr3[703] = 97668;
        iArr3[704] = 81026;
        iArr3[705] = 97666;
        iArr3[706] = 72792;
        iArr3[707] = 101934;
        iArr3[708] = 81112;
        iArr3[709] = 72780;
        iArr3[710] = 97752;
        iArr3[711] = 81100;
        iArr3[712] = 72774;
        iArr3[713] = 97740;
        iArr3[714] = 81094;
        iArr3[715] = 97734;
        iArr3[716] = 68654;
        iArr3[717] = 72814;
        iArr3[718] = 81134;
        iArr3[719] = 97774;
        iArr3[720] = 114256;
        iArr3[721] = 122668;
        iArr3[722] = 114248;
        iArr3[723] = 122662;
        iArr3[724] = 114244;
        iArr3[725] = 114242;
        iArr3[726] = 80976;
        iArr3[727] = 106028;
        iArr3[728] = 97488;
        iArr3[729] = 80968;
        iArr3[730] = 106022;
        iArr3[731] = 97480;
        iArr3[732] = 114278;
        iArr3[733] = 97476;
        iArr3[734] = 80962;
        iArr3[735] = 97474;
        iArr3[736] = 72748;
        iArr3[737] = 81004;
        iArr3[738] = 72742;
        iArr3[739] = 97516;
        iArr3[740] = 80998;
        iArr3[741] = 97510;
        iArr3[742] = 114216;
        iArr3[743] = 122646;
        iArr3[744] = 114212;
        iArr3[745] = 114210;
        iArr3[746] = 80936;
        iArr3[747] = 106006;
        iArr3[748] = 97384;
        iArr3[749] = 80932;
        iArr3[750] = 97380;
        iArr3[751] = 80930;
        iArr3[752] = 97378;
        iArr3[753] = 72726;
        iArr3[754] = 80950;
        iArr3[755] = 97398;
        iArr3[756] = 114196;
        iArr3[757] = 114194;
        iArr3[758] = 80916;
        iArr3[759] = 97332;
        iArr3[760] = 80914;
        iArr3[761] = 97330;
        iArr3[762] = 66236;
        iArr3[763] = 66206;
        iArr3[764] = 67256;
        iArr3[765] = 99166;
        iArr3[766] = 67228;
        iArr3[767] = 67214;
        iArr3[768] = 66142;
        iArr3[769] = 67294;
        iArr3[770] = 69296;
        iArr3[771] = 100188;
        iArr3[772] = 69272;
        iArr3[773] = 100174;
        iArr3[774] = 69260;
        iArr3[775] = 69254;
        iArr3[776] = 67164;
        iArr3[777] = 69340;
        iArr3[778] = 67150;
        iArr3[779] = 69326;
        iArr3[780] = 73376;
        iArr3[781] = 102232;
        iArr3[782] = 116654;
        iArr3[783] = 73360;
        iArr3[784] = 102220;
        iArr3[785] = 73352;
        iArr3[786] = 102214;
        iArr3[787] = 73348;
        iArr3[788] = 73346;
        iArr3[789] = 69208;
        iArr3[790] = 100142;
        iArr3[791] = 73432;
        iArr3[792] = 102254;
        iArr3[793] = 73420;
        iArr3[794] = 69190;
        iArr3[795] = 73414;
        iArr3[796] = 67118;
        iArr3[797] = 69230;
        iArr3[798] = 73454;
        iArr3[799] = 106320;
        iArr3[800] = 118700;
        iArr3[801] = 106312;
        iArr3[802] = 118694;
        iArr3[803] = 106308;
        iArr3[804] = 106306;
        iArr3[805] = 73296;
        iArr3[806] = 102188;
        iArr3[807] = 81616;
        iArr3[808] = 106348;
        iArr3[809] = 102182;
        iArr3[810] = 81608;
        iArr3[811] = 73284;
        iArr3[812] = 81604;
        iArr3[813] = 73282;
        iArr3[814] = 81602;
        iArr3[815] = 69164;
        iArr3[816] = 73324;
        iArr3[817] = 69158;
        iArr3[818] = 81644;
        iArr3[819] = 73318;
        iArr3[820] = 81638;
        iArr3[821] = 122792;
        iArr3[822] = 126934;
        iArr3[823] = 122788;
        iArr3[824] = 122786;
        iArr3[825] = 106280;
        iArr3[826] = 118678;
        iArr3[827] = 114536;
        iArr3[828] = 106276;
        iArr3[829] = 114532;
        iArr3[830] = 106274;
        iArr3[831] = 114530;
        iArr3[832] = 73256;
        iArr3[833] = 102166;
        iArr3[834] = 81512;
        iArr3[835] = 73252;
        iArr3[836] = 98024;
        iArr3[837] = 81508;
        iArr3[838] = 73250;
        iArr3[839] = 98020;
        iArr3[840] = 81506;
        iArr3[841] = 98018;
        iArr3[842] = 69142;
        iArr3[843] = 73270;
        iArr3[844] = 81526;
        iArr3[845] = 98038;
        iArr3[846] = 122772;
        iArr3[847] = 122770;
        iArr3[848] = 106260;
        iArr3[849] = 114484;
        iArr3[850] = 106258;
        iArr3[851] = 114482;
        iArr3[852] = 73236;
        iArr3[853] = 81460;
        iArr3[854] = 73234;
        iArr3[855] = 97908;
        iArr3[856] = 81458;
        iArr3[857] = 97906;
        iArr3[858] = 122762;
        iArr3[859] = 106250;
        iArr3[860] = 114458;
        iArr3[861] = 73226;
        iArr3[862] = 81434;
        iArr3[863] = 97850;
        iArr3[864] = 66396;
        iArr3[865] = 66382;
        iArr3[866] = 67416;
        iArr3[867] = 99246;
        iArr3[868] = 67404;
        iArr3[869] = 67398;
        iArr3[870] = 66350;
        iArr3[871] = 67438;
        iArr3[872] = 69456;
        iArr3[873] = 100268;
        iArr3[874] = 69448;
        iArr3[875] = 100262;
        iArr3[876] = 69444;
        iArr3[877] = 69442;
        iArr3[878] = 67372;
        iArr3[879] = 69484;
        iArr3[880] = 67366;
        iArr3[881] = 69478;
        iArr3[882] = 102312;
        iArr3[883] = 116694;
        iArr3[884] = 102308;
        iArr3[885] = 102306;
        iArr3[886] = 69416;
        iArr3[887] = 100246;
        iArr3[888] = 73576;
        iArr3[889] = 102326;
        iArr3[890] = 73572;
        iArr3[891] = 69410;
        iArr3[892] = 73570;
        iArr3[893] = 67350;
        iArr3[894] = 69430;
        iArr3[895] = 73590;
        iArr3[896] = 118740;
        iArr3[897] = 118738;
        iArr3[898] = 102292;
        iArr3[899] = 106420;
        iArr3[900] = 102290;
        iArr3[901] = 106418;
        iArr3[902] = 69396;
        iArr3[903] = 73524;
        iArr3[904] = 69394;
        iArr3[905] = 81780;
        iArr3[906] = 73522;
        iArr3[907] = 81778;
        iArr3[908] = 118730;
        iArr3[909] = 102282;
        iArr3[910] = 106394;
        iArr3[911] = 69386;
        iArr3[912] = 73498;
        iArr3[913] = 81722;
        iArr3[914] = 66476;
        iArr3[915] = 66470;
        iArr3[916] = 67496;
        iArr3[917] = 99286;
        iArr3[918] = 67492;
        iArr3[919] = 67490;
        iArr3[920] = 66454;
        iArr3[921] = 67510;
        iArr3[922] = 100308;
        iArr3[923] = 100306;
        iArr3[924] = 67476;
        iArr3[925] = 69556;
        iArr3[926] = 67474;
        iArr3[927] = 69554;
        iArr3[928] = 116714;
        CLUSTERS = new int[][]{iArr, iArr2, iArr3};
    }

    protected static class Segment {
        public int end;
        public int start;
        public char type;

        public Segment(char c, int i, int i2) {
            this.type = c;
            this.start = i;
            this.end = i2;
        }
    }

    protected static class SegmentList {
        protected ArrayList<Segment> list = new ArrayList<>();

        protected SegmentList() {
        }

        public void add(char c, int i, int i2) {
            this.list.add(new Segment(c, i, i2));
        }

        public Segment get(int i) {
            if (i < 0 || i >= this.list.size()) {
                return null;
            }
            return this.list.get(i);
        }

        public void remove(int i) {
            if (i >= 0 && i < this.list.size()) {
                this.list.remove(i);
            }
        }

        public int size() {
            return this.list.size();
        }
    }

    public void placeBarcode(PdfContentByte pdfContentByte, BaseColor baseColor, float f, float f2) {
        paintCode();
        int i = (this.bitColumns + 7) / 8;
        pdfContentByte.setColorFill(baseColor);
        for (int i2 = 0; i2 < this.codeRows; i2++) {
            int i3 = i2 * i;
            for (int i4 = 0; i4 < this.bitColumns; i4++) {
                if ((((this.outBits[(i4 / 8) + i3] & 255) << (i4 % 8)) & 128) != 0) {
                    pdfContentByte.rectangle(((float) i4) * f2, ((float) ((this.codeRows - i2) - 1)) * f, f2, f);
                }
            }
        }
        pdfContentByte.fill();
    }

    public Rectangle getBarcodeSize() {
        paintCode();
        return new Rectangle(0.0f, 0.0f, (float) this.bitColumns, (float) this.codeRows);
    }
}
