package com.itextpdf.text.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.languages.ArabicLigaturizer;
import java.util.ArrayList;
import java.util.Iterator;

public class BidiLine {
    protected static final IntHashtable mirrorChars = new IntHashtable();
    protected int arabicOptions;
    protected ArrayList<PdfChunk> chunks = new ArrayList<>();
    protected int currentChar = 0;
    protected PdfChunk[] detailChunks = new PdfChunk[this.pieceSize];
    protected int[] indexChars = new int[this.pieceSize];
    protected int indexChunk = 0;
    protected int indexChunkChar = 0;
    protected boolean isWordSplit = false;
    protected byte[] orderLevels = new byte[this.pieceSize];
    protected int pieceSize = 256;
    protected int runDirection;
    protected boolean shortStore;
    protected int storedCurrentChar = 0;
    protected PdfChunk[] storedDetailChunks = new PdfChunk[0];
    protected int[] storedIndexChars = new int[0];
    protected int storedIndexChunk = 0;
    protected int storedIndexChunkChar = 0;
    protected byte[] storedOrderLevels = new byte[0];
    protected int storedRunDirection;
    protected char[] storedText = new char[0];
    protected int storedTotalTextLength = 0;
    protected char[] text = new char[this.pieceSize];
    protected int totalTextLength = 0;

    public static boolean isWS(char c) {
        return c <= ' ';
    }

    static {
        mirrorChars.put(40, 41);
        mirrorChars.put(41, 40);
        mirrorChars.put(60, 62);
        mirrorChars.put(62, 60);
        mirrorChars.put(91, 93);
        mirrorChars.put(93, 91);
        mirrorChars.put(123, 125);
        mirrorChars.put(125, 123);
        mirrorChars.put(171, 187);
        mirrorChars.put(187, 171);
        mirrorChars.put(8249, 8250);
        mirrorChars.put(8250, 8249);
        mirrorChars.put(8261, 8262);
        mirrorChars.put(8262, 8261);
        mirrorChars.put(8317, 8318);
        mirrorChars.put(8318, 8317);
        mirrorChars.put(8333, 8334);
        mirrorChars.put(8334, 8333);
        mirrorChars.put(8712, 8715);
        mirrorChars.put(8713, 8716);
        mirrorChars.put(8714, 8717);
        mirrorChars.put(8715, 8712);
        mirrorChars.put(8716, 8713);
        mirrorChars.put(8717, 8714);
        mirrorChars.put(8725, 10741);
        mirrorChars.put(8764, 8765);
        mirrorChars.put(8765, 8764);
        mirrorChars.put(8771, 8909);
        mirrorChars.put(8786, 8787);
        mirrorChars.put(8787, 8786);
        mirrorChars.put(8788, 8789);
        mirrorChars.put(8789, 8788);
        mirrorChars.put(8804, 8805);
        mirrorChars.put(8805, 8804);
        mirrorChars.put(8806, 8807);
        mirrorChars.put(8807, 8806);
        mirrorChars.put(8808, 8809);
        mirrorChars.put(8809, 8808);
        mirrorChars.put(8810, 8811);
        mirrorChars.put(8811, 8810);
        mirrorChars.put(8814, 8815);
        mirrorChars.put(8815, 8814);
        mirrorChars.put(8816, 8817);
        mirrorChars.put(8817, 8816);
        mirrorChars.put(8818, 8819);
        mirrorChars.put(8819, 8818);
        mirrorChars.put(8820, 8821);
        mirrorChars.put(8821, 8820);
        mirrorChars.put(8822, 8823);
        mirrorChars.put(8823, 8822);
        mirrorChars.put(8824, 8825);
        mirrorChars.put(8825, 8824);
        mirrorChars.put(8826, 8827);
        mirrorChars.put(8827, 8826);
        mirrorChars.put(8828, 8829);
        mirrorChars.put(8829, 8828);
        mirrorChars.put(8830, 8831);
        mirrorChars.put(8831, 8830);
        mirrorChars.put(8832, 8833);
        mirrorChars.put(8833, 8832);
        mirrorChars.put(8834, 8835);
        mirrorChars.put(8835, 8834);
        mirrorChars.put(8836, 8837);
        mirrorChars.put(8837, 8836);
        mirrorChars.put(8838, 8839);
        mirrorChars.put(8839, 8838);
        mirrorChars.put(8840, 8841);
        mirrorChars.put(8841, 8840);
        mirrorChars.put(8842, 8843);
        mirrorChars.put(8843, 8842);
        mirrorChars.put(8847, 8848);
        mirrorChars.put(8848, 8847);
        mirrorChars.put(8849, 8850);
        mirrorChars.put(8850, 8849);
        mirrorChars.put(8856, 10680);
        mirrorChars.put(8866, 8867);
        mirrorChars.put(8867, 8866);
        mirrorChars.put(8870, 10974);
        mirrorChars.put(8872, 10980);
        mirrorChars.put(8873, 10979);
        mirrorChars.put(8875, 10981);
        mirrorChars.put(8880, 8881);
        mirrorChars.put(8881, 8880);
        mirrorChars.put(8882, 8883);
        mirrorChars.put(8883, 8882);
        mirrorChars.put(8884, 8885);
        mirrorChars.put(8885, 8884);
        mirrorChars.put(8886, 8887);
        mirrorChars.put(8887, 8886);
        mirrorChars.put(8905, 8906);
        mirrorChars.put(8906, 8905);
        mirrorChars.put(8907, 8908);
        mirrorChars.put(8908, 8907);
        mirrorChars.put(8909, 8771);
        mirrorChars.put(8912, 8913);
        mirrorChars.put(8913, 8912);
        mirrorChars.put(8918, 8919);
        mirrorChars.put(8919, 8918);
        mirrorChars.put(8920, 8921);
        mirrorChars.put(8921, 8920);
        mirrorChars.put(8922, 8923);
        mirrorChars.put(8923, 8922);
        mirrorChars.put(8924, 8925);
        mirrorChars.put(8925, 8924);
        mirrorChars.put(8926, 8927);
        mirrorChars.put(8927, 8926);
        mirrorChars.put(8928, 8929);
        mirrorChars.put(8929, 8928);
        mirrorChars.put(8930, 8931);
        mirrorChars.put(8931, 8930);
        mirrorChars.put(8932, 8933);
        mirrorChars.put(8933, 8932);
        mirrorChars.put(8934, 8935);
        mirrorChars.put(8935, 8934);
        mirrorChars.put(8936, 8937);
        mirrorChars.put(8937, 8936);
        mirrorChars.put(8938, 8939);
        mirrorChars.put(8939, 8938);
        mirrorChars.put(8940, 8941);
        mirrorChars.put(8941, 8940);
        mirrorChars.put(8944, 8945);
        mirrorChars.put(8945, 8944);
        mirrorChars.put(8946, 8954);
        mirrorChars.put(8947, 8955);
        mirrorChars.put(8948, 8956);
        mirrorChars.put(8950, 8957);
        mirrorChars.put(8951, 8958);
        mirrorChars.put(8954, 8946);
        mirrorChars.put(8955, 8947);
        mirrorChars.put(8956, 8948);
        mirrorChars.put(8957, 8950);
        mirrorChars.put(8958, 8951);
        mirrorChars.put(8968, 8969);
        mirrorChars.put(8969, 8968);
        mirrorChars.put(8970, 8971);
        mirrorChars.put(8971, 8970);
        mirrorChars.put(9001, 9002);
        mirrorChars.put(9002, 9001);
        mirrorChars.put(10088, 10089);
        mirrorChars.put(10089, 10088);
        mirrorChars.put(10090, 10091);
        mirrorChars.put(10091, 10090);
        mirrorChars.put(10092, 10093);
        mirrorChars.put(10093, 10092);
        mirrorChars.put(10094, 10095);
        mirrorChars.put(10095, 10094);
        mirrorChars.put(10096, 10097);
        mirrorChars.put(10097, 10096);
        mirrorChars.put(10098, 10099);
        mirrorChars.put(10099, 10098);
        mirrorChars.put(10100, 10101);
        mirrorChars.put(10101, 10100);
        mirrorChars.put(10197, 10198);
        mirrorChars.put(10198, 10197);
        mirrorChars.put(10205, 10206);
        mirrorChars.put(10206, 10205);
        mirrorChars.put(10210, 10211);
        mirrorChars.put(10211, 10210);
        mirrorChars.put(10212, 10213);
        mirrorChars.put(10213, 10212);
        mirrorChars.put(10214, 10215);
        mirrorChars.put(10215, 10214);
        mirrorChars.put(10216, 10217);
        mirrorChars.put(10217, 10216);
        mirrorChars.put(10218, 10219);
        mirrorChars.put(10219, 10218);
        mirrorChars.put(10627, 10628);
        mirrorChars.put(10628, 10627);
        mirrorChars.put(10629, 10630);
        mirrorChars.put(10630, 10629);
        mirrorChars.put(10631, 10632);
        mirrorChars.put(10632, 10631);
        mirrorChars.put(10633, 10634);
        mirrorChars.put(10634, 10633);
        mirrorChars.put(10635, 10636);
        mirrorChars.put(10636, 10635);
        mirrorChars.put(10637, 10640);
        mirrorChars.put(10638, 10639);
        mirrorChars.put(10639, 10638);
        mirrorChars.put(10640, 10637);
        mirrorChars.put(10641, 10642);
        mirrorChars.put(10642, 10641);
        mirrorChars.put(10643, 10644);
        mirrorChars.put(10644, 10643);
        mirrorChars.put(10645, 10646);
        mirrorChars.put(10646, 10645);
        mirrorChars.put(10647, 10648);
        mirrorChars.put(10648, 10647);
        mirrorChars.put(10680, 8856);
        mirrorChars.put(10688, 10689);
        mirrorChars.put(10689, 10688);
        mirrorChars.put(10692, 10693);
        mirrorChars.put(10693, 10692);
        mirrorChars.put(10703, 10704);
        mirrorChars.put(10704, 10703);
        mirrorChars.put(10705, 10706);
        mirrorChars.put(10706, 10705);
        mirrorChars.put(10708, 10709);
        mirrorChars.put(10709, 10708);
        mirrorChars.put(10712, 10713);
        mirrorChars.put(10713, 10712);
        mirrorChars.put(10714, 10715);
        mirrorChars.put(10715, 10714);
        mirrorChars.put(10741, 8725);
        mirrorChars.put(10744, 10745);
        mirrorChars.put(10745, 10744);
        mirrorChars.put(10748, 10749);
        mirrorChars.put(10749, 10748);
        mirrorChars.put(10795, 10796);
        mirrorChars.put(10796, 10795);
        mirrorChars.put(10797, 10796);
        mirrorChars.put(10798, 10797);
        mirrorChars.put(10804, 10805);
        mirrorChars.put(10805, 10804);
        mirrorChars.put(10812, 10813);
        mirrorChars.put(10813, 10812);
        mirrorChars.put(10852, 10853);
        mirrorChars.put(10853, 10852);
        mirrorChars.put(10873, 10874);
        mirrorChars.put(10874, 10873);
        mirrorChars.put(10877, 10878);
        mirrorChars.put(10878, 10877);
        mirrorChars.put(10879, 10880);
        mirrorChars.put(10880, 10879);
        mirrorChars.put(10881, 10882);
        mirrorChars.put(10882, 10881);
        mirrorChars.put(10883, 10884);
        mirrorChars.put(10884, 10883);
        mirrorChars.put(10891, 10892);
        mirrorChars.put(10892, 10891);
        mirrorChars.put(10897, 10898);
        mirrorChars.put(10898, 10897);
        mirrorChars.put(10899, 10900);
        mirrorChars.put(10900, 10899);
        mirrorChars.put(10901, 10902);
        mirrorChars.put(10902, 10901);
        mirrorChars.put(10903, 10904);
        mirrorChars.put(10904, 10903);
        mirrorChars.put(10905, 10906);
        mirrorChars.put(10906, 10905);
        mirrorChars.put(10907, 10908);
        mirrorChars.put(10908, 10907);
        mirrorChars.put(10913, 10914);
        mirrorChars.put(10914, 10913);
        mirrorChars.put(10918, 10919);
        mirrorChars.put(10919, 10918);
        mirrorChars.put(10920, 10921);
        mirrorChars.put(10921, 10920);
        mirrorChars.put(10922, 10923);
        mirrorChars.put(10923, 10922);
        mirrorChars.put(10924, 10925);
        mirrorChars.put(10925, 10924);
        mirrorChars.put(10927, 10928);
        mirrorChars.put(10928, 10927);
        mirrorChars.put(10931, 10932);
        mirrorChars.put(10932, 10931);
        mirrorChars.put(10939, 10940);
        mirrorChars.put(10940, 10939);
        mirrorChars.put(10941, 10942);
        mirrorChars.put(10942, 10941);
        mirrorChars.put(10943, 10944);
        mirrorChars.put(10944, 10943);
        mirrorChars.put(10945, 10946);
        mirrorChars.put(10946, 10945);
        mirrorChars.put(10947, 10948);
        mirrorChars.put(10948, 10947);
        mirrorChars.put(10949, 10950);
        mirrorChars.put(10950, 10949);
        mirrorChars.put(10957, 10958);
        mirrorChars.put(10958, 10957);
        mirrorChars.put(10959, 10960);
        mirrorChars.put(10960, 10959);
        mirrorChars.put(10961, 10962);
        mirrorChars.put(10962, 10961);
        mirrorChars.put(10963, 10964);
        mirrorChars.put(10964, 10963);
        mirrorChars.put(10965, 10966);
        mirrorChars.put(10966, 10965);
        mirrorChars.put(10974, 8870);
        mirrorChars.put(10979, 8873);
        mirrorChars.put(10980, 8872);
        mirrorChars.put(10981, 8875);
        mirrorChars.put(10988, 10989);
        mirrorChars.put(10989, 10988);
        mirrorChars.put(10999, 11000);
        mirrorChars.put(11000, 10999);
        mirrorChars.put(11001, 11002);
        mirrorChars.put(11002, 11001);
        mirrorChars.put(12296, 12297);
        mirrorChars.put(12297, 12296);
        mirrorChars.put(12298, 12299);
        mirrorChars.put(12299, 12298);
        mirrorChars.put(12300, 12301);
        mirrorChars.put(12301, 12300);
        mirrorChars.put(12302, 12303);
        mirrorChars.put(12303, 12302);
        mirrorChars.put(12304, 12305);
        mirrorChars.put(12305, 12304);
        mirrorChars.put(12308, 12309);
        mirrorChars.put(12309, 12308);
        mirrorChars.put(12310, 12311);
        mirrorChars.put(12311, 12310);
        mirrorChars.put(12312, 12313);
        mirrorChars.put(12313, 12312);
        mirrorChars.put(12314, 12315);
        mirrorChars.put(12315, 12314);
        mirrorChars.put(65288, 65289);
        mirrorChars.put(65289, 65288);
        mirrorChars.put(65308, 65310);
        mirrorChars.put(65310, 65308);
        mirrorChars.put(65339, 65341);
        mirrorChars.put(65341, 65339);
        mirrorChars.put(65371, 65373);
        mirrorChars.put(65373, 65371);
        mirrorChars.put(65375, 65376);
        mirrorChars.put(65376, 65375);
        mirrorChars.put(65378, 65379);
        mirrorChars.put(65379, 65378);
    }

    public BidiLine() {
    }

    public BidiLine(BidiLine bidiLine) {
        this.runDirection = bidiLine.runDirection;
        this.pieceSize = bidiLine.pieceSize;
        this.text = (char[]) bidiLine.text.clone();
        this.detailChunks = (PdfChunk[]) bidiLine.detailChunks.clone();
        this.totalTextLength = bidiLine.totalTextLength;
        this.orderLevels = (byte[]) bidiLine.orderLevels.clone();
        this.indexChars = (int[]) bidiLine.indexChars.clone();
        this.chunks = new ArrayList<>(bidiLine.chunks);
        this.indexChunk = bidiLine.indexChunk;
        this.indexChunkChar = bidiLine.indexChunkChar;
        this.currentChar = bidiLine.currentChar;
        this.storedRunDirection = bidiLine.storedRunDirection;
        this.storedText = (char[]) bidiLine.storedText.clone();
        this.storedDetailChunks = (PdfChunk[]) bidiLine.storedDetailChunks.clone();
        this.storedTotalTextLength = bidiLine.storedTotalTextLength;
        this.storedOrderLevels = (byte[]) bidiLine.storedOrderLevels.clone();
        this.storedIndexChars = (int[]) bidiLine.storedIndexChars.clone();
        this.storedIndexChunk = bidiLine.storedIndexChunk;
        this.storedIndexChunkChar = bidiLine.storedIndexChunkChar;
        this.storedCurrentChar = bidiLine.storedCurrentChar;
        this.shortStore = bidiLine.shortStore;
        this.arabicOptions = bidiLine.arabicOptions;
    }

    public boolean isEmpty() {
        return this.currentChar >= this.totalTextLength && this.indexChunk >= this.chunks.size();
    }

    public void clearChunks() {
        this.chunks.clear();
        this.totalTextLength = 0;
        this.currentChar = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0063, code lost:
        r11.indexChunkChar++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006a, code lost:
        if (r11.indexChunkChar < r6) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006c, code lost:
        r11.indexChunkChar = 0;
        r11.indexChunk++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0075, code lost:
        if (r11.totalTextLength != 0) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0077, code lost:
        r11.detailChunks[0] = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007b, code lost:
        r1 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getParagraph(int r12) {
        /*
            r11 = this;
            r11.runDirection = r12
            r0 = 0
            r11.currentChar = r0
            r11.totalTextLength = r0
            r1 = r0
        L_0x0008:
            int r2 = r11.indexChunk
            java.util.ArrayList<com.itextpdf.text.pdf.PdfChunk> r3 = r11.chunks
            int r3 = r3.size()
            r4 = 1
            if (r2 >= r3) goto L_0x0087
            java.util.ArrayList<com.itextpdf.text.pdf.PdfChunk> r2 = r11.chunks
            int r3 = r11.indexChunk
            java.lang.Object r2 = r2.get(r3)
            com.itextpdf.text.pdf.PdfChunk r2 = (com.itextpdf.text.pdf.PdfChunk) r2
            com.itextpdf.text.pdf.PdfFont r3 = r2.font()
            com.itextpdf.text.pdf.BaseFont r3 = r3.getFont()
            java.lang.String r5 = r2.toString()
            int r6 = r5.length()
        L_0x002d:
            int r7 = r11.indexChunkChar
            if (r7 >= r6) goto L_0x007c
            int r7 = r11.indexChunkChar
            char r7 = r5.charAt(r7)
            int r8 = r3.getUnicodeEquivalent(r7)
            char r8 = (char) r8
            r9 = 10
            r10 = 13
            if (r8 == r10) goto L_0x004e
            if (r8 != r9) goto L_0x0045
            goto L_0x004e
        L_0x0045:
            r11.addPiece(r7, r2)
            int r7 = r11.indexChunkChar
            int r7 = r7 + r4
            r11.indexChunkChar = r7
            goto L_0x002d
        L_0x004e:
            if (r8 != r10) goto L_0x0063
            int r1 = r11.indexChunkChar
            int r1 = r1 + r4
            if (r1 >= r6) goto L_0x0063
            int r1 = r11.indexChunkChar
            int r1 = r1 + r4
            char r1 = r5.charAt(r1)
            if (r1 != r9) goto L_0x0063
            int r1 = r11.indexChunkChar
            int r1 = r1 + r4
            r11.indexChunkChar = r1
        L_0x0063:
            int r1 = r11.indexChunkChar
            int r1 = r1 + r4
            r11.indexChunkChar = r1
            int r1 = r11.indexChunkChar
            if (r1 < r6) goto L_0x0073
            r11.indexChunkChar = r0
            int r1 = r11.indexChunk
            int r1 = r1 + r4
            r11.indexChunk = r1
        L_0x0073:
            int r1 = r11.totalTextLength
            if (r1 != 0) goto L_0x007b
            com.itextpdf.text.pdf.PdfChunk[] r1 = r11.detailChunks
            r1[r0] = r2
        L_0x007b:
            r1 = r4
        L_0x007c:
            if (r1 == 0) goto L_0x007f
            goto L_0x0087
        L_0x007f:
            r11.indexChunkChar = r0
            int r2 = r11.indexChunk
            int r2 = r2 + r4
            r11.indexChunk = r2
            goto L_0x0008
        L_0x0087:
            int r2 = r11.totalTextLength
            if (r2 != 0) goto L_0x008c
            return r1
        L_0x008c:
            int r1 = r11.totalTextLength
            int r1 = r1 - r4
            int r1 = r11.trimRight(r0, r1)
            int r1 = r1 + r4
            r11.totalTextLength = r1
            int r1 = r11.totalTextLength
            if (r1 != 0) goto L_0x009b
            return r4
        L_0x009b:
            r1 = 2
            r2 = 3
            if (r12 == r1) goto L_0x00a1
            if (r12 != r2) goto L_0x00e8
        L_0x00a1:
            byte[] r1 = r11.orderLevels
            int r1 = r1.length
            int r3 = r11.totalTextLength
            if (r1 >= r3) goto L_0x00b4
            int r1 = r11.pieceSize
            byte[] r1 = new byte[r1]
            r11.orderLevels = r1
            int r1 = r11.pieceSize
            int[] r1 = new int[r1]
            r11.indexChars = r1
        L_0x00b4:
            char[] r1 = r11.text
            int r3 = r11.totalTextLength
            int r5 = r11.arabicOptions
            com.itextpdf.text.pdf.languages.ArabicLigaturizer.processNumbers(r1, r0, r3, r5)
            com.itextpdf.text.pdf.BidiOrder r1 = new com.itextpdf.text.pdf.BidiOrder
            char[] r3 = r11.text
            int r5 = r11.totalTextLength
            if (r12 != r2) goto L_0x00c7
            r12 = r4
            goto L_0x00c8
        L_0x00c7:
            r12 = r0
        L_0x00c8:
            byte r12 = (byte) r12
            r1.<init>(r3, r0, r5, r12)
            byte[] r12 = r1.getLevels()
            r1 = r0
        L_0x00d1:
            int r2 = r11.totalTextLength
            if (r1 >= r2) goto L_0x00e2
            byte[] r2 = r11.orderLevels
            byte r3 = r12[r1]
            r2[r1] = r3
            int[] r2 = r11.indexChars
            r2[r1] = r1
            int r1 = r1 + 1
            goto L_0x00d1
        L_0x00e2:
            r11.doArabicShapping()
            r11.mirrorGlyphs()
        L_0x00e8:
            int r12 = r11.totalTextLength
            int r12 = r12 - r4
            int r12 = r11.trimRightEx(r0, r12)
            int r12 = r12 + r4
            r11.totalTextLength = r12
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.BidiLine.getParagraph(int):boolean");
    }

    public void addChunk(PdfChunk pdfChunk) {
        this.chunks.add(pdfChunk);
    }

    public void addChunks(ArrayList<PdfChunk> arrayList) {
        this.chunks.addAll(arrayList);
    }

    public void addPiece(char c, PdfChunk pdfChunk) {
        if (this.totalTextLength >= this.pieceSize) {
            char[] cArr = this.text;
            PdfChunk[] pdfChunkArr = this.detailChunks;
            this.pieceSize *= 2;
            this.text = new char[this.pieceSize];
            this.detailChunks = new PdfChunk[this.pieceSize];
            System.arraycopy(cArr, 0, this.text, 0, this.totalTextLength);
            System.arraycopy(pdfChunkArr, 0, this.detailChunks, 0, this.totalTextLength);
        }
        this.text[this.totalTextLength] = c;
        PdfChunk[] pdfChunkArr2 = this.detailChunks;
        int i = this.totalTextLength;
        this.totalTextLength = i + 1;
        pdfChunkArr2[i] = pdfChunk;
    }

    public void save() {
        boolean z = true;
        if (this.indexChunk > 0) {
            if (this.indexChunk < this.chunks.size()) {
                while (true) {
                    this.indexChunk--;
                    if (this.indexChunk < 0) {
                        break;
                    }
                    this.chunks.remove(this.indexChunk);
                }
            } else {
                this.chunks.clear();
            }
            this.indexChunk = 0;
        }
        this.storedRunDirection = this.runDirection;
        this.storedTotalTextLength = this.totalTextLength;
        this.storedIndexChunk = this.indexChunk;
        this.storedIndexChunkChar = this.indexChunkChar;
        this.storedCurrentChar = this.currentChar;
        if (this.currentChar >= this.totalTextLength) {
            z = false;
        }
        this.shortStore = z;
        if (!this.shortStore) {
            if (this.storedText.length < this.totalTextLength) {
                this.storedText = new char[this.totalTextLength];
                this.storedDetailChunks = new PdfChunk[this.totalTextLength];
            }
            System.arraycopy(this.text, 0, this.storedText, 0, this.totalTextLength);
            System.arraycopy(this.detailChunks, 0, this.storedDetailChunks, 0, this.totalTextLength);
        }
        if (this.runDirection == 2 || this.runDirection == 3) {
            if (this.storedOrderLevels.length < this.totalTextLength) {
                this.storedOrderLevels = new byte[this.totalTextLength];
                this.storedIndexChars = new int[this.totalTextLength];
            }
            System.arraycopy(this.orderLevels, this.currentChar, this.storedOrderLevels, this.currentChar, this.totalTextLength - this.currentChar);
            System.arraycopy(this.indexChars, this.currentChar, this.storedIndexChars, this.currentChar, this.totalTextLength - this.currentChar);
        }
    }

    public void restore() {
        this.runDirection = this.storedRunDirection;
        this.totalTextLength = this.storedTotalTextLength;
        this.indexChunk = this.storedIndexChunk;
        this.indexChunkChar = this.storedIndexChunkChar;
        this.currentChar = this.storedCurrentChar;
        if (!this.shortStore) {
            System.arraycopy(this.storedText, 0, this.text, 0, this.totalTextLength);
            System.arraycopy(this.storedDetailChunks, 0, this.detailChunks, 0, this.totalTextLength);
        }
        if (this.runDirection == 2 || this.runDirection == 3) {
            System.arraycopy(this.storedOrderLevels, this.currentChar, this.orderLevels, this.currentChar, this.totalTextLength - this.currentChar);
            System.arraycopy(this.storedIndexChars, this.currentChar, this.indexChars, this.currentChar, this.totalTextLength - this.currentChar);
        }
    }

    public void mirrorGlyphs() {
        int i;
        for (int i2 = 0; i2 < this.totalTextLength; i2++) {
            if ((this.orderLevels[i2] & 1) == 1 && (i = mirrorChars.get(this.text[i2])) != 0) {
                this.text[i2] = (char) i;
            }
        }
    }

    public void doArabicShapping() {
        char c;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i < this.totalTextLength && ((c = this.text[i]) < 1536 || c > 1791)) {
                if (i != i2) {
                    this.text[i2] = this.text[i];
                    this.detailChunks[i2] = this.detailChunks[i];
                    this.orderLevels[i2] = this.orderLevels[i];
                }
                i++;
                i2++;
            } else if (i >= this.totalTextLength) {
                this.totalTextLength = i2;
                return;
            } else {
                int i3 = i + 1;
                while (i3 < this.totalTextLength && (r1 = this.text[i3]) >= 1536 && r1 <= 1791) {
                    i3++;
                }
                int i4 = i3 - i;
                int arabic_shape = ArabicLigaturizer.arabic_shape(this.text, i, i4, this.text, i2, i4, this.arabicOptions);
                if (i != i2) {
                    int i5 = 0;
                    while (i5 < arabic_shape) {
                        this.detailChunks[i2] = this.detailChunks[i];
                        this.orderLevels[i2] = this.orderLevels[i];
                        i5++;
                        i2++;
                        i++;
                    }
                } else {
                    i2 += arabic_shape;
                }
                i = i3;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0281  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.itextpdf.text.pdf.PdfLine processLine(float r25, float r26, int r27, int r28, int r29, float r30, float r31, float r32) {
        /*
            r24 = this;
            r0 = r24
            r4 = r26
            r1 = r28
            r5 = 0
            r0.isWordSplit = r5
            r6 = r29
            r0.arabicOptions = r6
            r24.save()
            r6 = 1
            r7 = 3
            if (r1 != r7) goto L_0x0016
            r8 = r6
            goto L_0x0017
        L_0x0016:
            r8 = r5
        L_0x0017:
            int r7 = r0.currentChar
            int r9 = r0.totalTextLength
            r10 = 0
            if (r7 < r9) goto L_0x0048
            boolean r1 = r0.getParagraph(r1)
            if (r1 != 0) goto L_0x0025
            return r10
        L_0x0025:
            int r1 = r0.totalTextLength
            if (r1 != 0) goto L_0x0048
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            com.itextpdf.text.pdf.PdfChunk r1 = new com.itextpdf.text.pdf.PdfChunk
            java.lang.String r2 = ""
            com.itextpdf.text.pdf.PdfChunk[] r3 = r0.detailChunks
            r3 = r3[r5]
            r1.<init>((java.lang.String) r2, (com.itextpdf.text.pdf.PdfChunk) r3)
            r7.add(r1)
            com.itextpdf.text.pdf.PdfLine r9 = new com.itextpdf.text.pdf.PdfLine
            r2 = 0
            r3 = 0
            r6 = 1
            r1 = r9
            r5 = r27
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x0048:
            int r1 = r0.currentChar
            if (r1 == 0) goto L_0x0057
            int r1 = r0.currentChar
            int r7 = r0.totalTextLength
            int r7 = r7 - r6
            int r1 = r0.trimLeftEx(r1, r7)
            r0.currentChar = r1
        L_0x0057:
            int r1 = r0.currentChar
            r17 = r4
            r11 = r5
            r14 = r10
            r15 = r14
            r12 = 2143289344(0x7fc00000, float:NaN)
            r13 = 2143289344(0x7fc00000, float:NaN)
            r18 = -1
        L_0x0064:
            int r9 = r0.currentChar
            int r10 = r0.totalTextLength
            r19 = 0
            if (r9 >= r10) goto L_0x0295
            com.itextpdf.text.pdf.PdfChunk[] r9 = r0.detailChunks
            int r10 = r0.currentChar
            r9 = r9[r10]
            boolean r10 = r9.isImage()
            if (r10 == 0) goto L_0x00b8
            int r10 = (r30 > r31 ? 1 : (r30 == r31 ? 0 : -1))
            if (r10 >= 0) goto L_0x00b8
            com.itextpdf.text.Image r10 = r9.getImage()
            boolean r11 = r10.isScaleToFitHeight()
            if (r11 == 0) goto L_0x00b8
            r11 = 1073741824(0x40000000, float:2.0)
            float r11 = r11 * r32
            float r11 = r31 + r11
            float r16 = r10.getScaledHeight()
            float r16 = r11 - r16
            float r20 = r9.getImageOffsetY()
            float r16 = r16 - r20
            float r20 = r10.getSpacingBefore()
            float r16 = r16 - r20
            int r16 = (r16 > r30 ? 1 : (r16 == r30 ? 0 : -1))
            if (r16 >= 0) goto L_0x00b8
            float r16 = r9.getImageOffsetY()
            float r11 = r11 - r16
            float r16 = r10.getSpacingBefore()
            float r11 = r11 - r16
            float r11 = r11 - r30
            float r10 = r10.getScaledHeight()
            float r11 = r11 / r10
            r9.setImageScalePercentage(r11)
        L_0x00b8:
            char[] r10 = r0.text
            int r11 = r0.currentChar
            boolean r10 = com.itextpdf.text.Utilities.isSurrogatePair((char[]) r10, (int) r11)
            if (r10 == 0) goto L_0x00cf
            char[] r11 = r0.text
            int r7 = r0.currentChar
            int r7 = com.itextpdf.text.Utilities.convertToUtf32((char[]) r11, (int) r7)
            int r7 = r9.getUnicodeEquivalent(r7)
            goto L_0x00d9
        L_0x00cf:
            char[] r7 = r0.text
            int r11 = r0.currentChar
            char r7 = r7[r11]
            int r7 = r9.getUnicodeEquivalent(r7)
        L_0x00d9:
            boolean r11 = com.itextpdf.text.pdf.PdfChunk.noPrint(r7)
            if (r11 == 0) goto L_0x00e6
            r23 = r1
            r3 = r6
            r21 = r8
            goto L_0x0286
        L_0x00e6:
            if (r10 == 0) goto L_0x00ed
            float r11 = r9.getCharWidth(r7)
            goto L_0x0103
        L_0x00ed:
            boolean r11 = r9.isImage()
            if (r11 == 0) goto L_0x00f8
            float r11 = r9.getImageWidth()
            goto L_0x0103
        L_0x00f8:
            char[] r11 = r0.text
            int r5 = r0.currentChar
            char r5 = r11[r5]
            float r5 = r9.getCharWidth(r5)
            r11 = r5
        L_0x0103:
            float r5 = r17 - r11
            int r5 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r5 >= 0) goto L_0x0127
            if (r15 != 0) goto L_0x0127
            boolean r5 = r9.isImage()
            if (r5 == 0) goto L_0x0127
            com.itextpdf.text.Image r5 = r9.getImage()
            boolean r16 = r5.isScaleToFitLineWhenOverflow()
            if (r16 == 0) goto L_0x0127
            float r5 = r5.getWidth()
            float r5 = r17 / r5
            r9.setImageScalePercentage(r5)
            r5 = r17
            goto L_0x0128
        L_0x0127:
            r5 = r11
        L_0x0128:
            boolean r11 = r9.isTab()
            if (r11 == 0) goto L_0x01e9
            java.lang.String r5 = "TABSETTINGS"
            boolean r5 = r9.isAttribute(r5)
            if (r5 == 0) goto L_0x019a
            int r5 = r0.currentChar
            if (r14 == 0) goto L_0x0151
            float r7 = r4 - r17
            float r11 = r14.getPosition(r13, r7, r12)
            float r7 = r7 - r13
            float r7 = r7 + r11
            float r7 = r4 - r7
            int r16 = (r7 > r19 ? 1 : (r7 == r19 ? 0 : -1))
            if (r16 >= 0) goto L_0x014c
            float r11 = r11 + r7
            r17 = r19
            goto L_0x014e
        L_0x014c:
            r17 = r7
        L_0x014e:
            r14.setPosition(r11)
        L_0x0151:
            float r7 = r4 - r17
            com.itextpdf.text.TabStop r11 = com.itextpdf.text.pdf.PdfChunk.getTabStop(r9, r7)
            float r14 = r11.getPosition()
            int r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r14 <= 0) goto L_0x016a
            r23 = r1
            r3 = r6
            r21 = r8
            r9 = r12
            r1 = r13
            r2 = r15
            r6 = 0
            goto L_0x02a1
        L_0x016a:
            r9.setTabStop(r11)
            if (r8 != 0) goto L_0x018c
            com.itextpdf.text.TabStop$Alignment r12 = r11.getAlignment()
            com.itextpdf.text.TabStop$Alignment r13 = com.itextpdf.text.TabStop.Alignment.LEFT
            if (r12 != r13) goto L_0x018c
            float r7 = r11.getPosition()
            float r17 = r4 - r7
            r23 = r1
            r18 = r5
            r21 = r8
            r22 = r9
            r6 = 0
            r9 = 2143289344(0x7fc00000, float:NaN)
            r13 = 2143289344(0x7fc00000, float:NaN)
            goto L_0x0278
        L_0x018c:
            r23 = r1
            r18 = r5
            r13 = r7
            r21 = r8
            r22 = r9
            r6 = r11
            r9 = 2143289344(0x7fc00000, float:NaN)
            goto L_0x0278
        L_0x019a:
            java.lang.String r5 = "TAB"
            java.lang.Object r5 = r9.getAttribute(r5)
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            r7 = r5[r6]
            java.lang.Float r7 = (java.lang.Float) r7
            float r7 = r7.floatValue()
            r11 = 2
            r5 = r5[r11]
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x01d2
            float r5 = r4 - r17
            int r5 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x01d2
            com.itextpdf.text.pdf.PdfLine r9 = new com.itextpdf.text.pdf.PdfLine
            r2 = 0
            r7 = 1
            int r3 = r0.currentChar
            int r3 = r3 - r6
            java.util.ArrayList r10 = r0.createArrayOfPdfChunks(r1, r3)
            r1 = r9
            r3 = r4
            r4 = r17
            r5 = r27
            r6 = r7
            r7 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x01d2:
            com.itextpdf.text.pdf.PdfChunk[] r5 = r0.detailChunks
            int r11 = r0.currentChar
            r5 = r5[r11]
            r11 = r25
            r5.adjustLeft(r11)
            float r17 = r4 - r7
        L_0x01df:
            r23 = r1
            r21 = r8
            r22 = r9
            r9 = r12
            r6 = r14
            goto L_0x0278
        L_0x01e9:
            r11 = r25
            boolean r16 = r9.isSeparator()
            if (r16 == 0) goto L_0x021f
            java.lang.String r5 = "SEPARATOR"
            java.lang.Object r5 = r9.getAttribute(r5)
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            r7 = 0
            r15 = r5[r7]
            com.itextpdf.text.pdf.draw.DrawInterface r15 = (com.itextpdf.text.pdf.draw.DrawInterface) r15
            r5 = r5[r6]
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x01df
            boolean r5 = r15 instanceof com.itextpdf.text.pdf.draw.LineSeparator
            if (r5 == 0) goto L_0x01df
            com.itextpdf.text.pdf.draw.LineSeparator r15 = (com.itextpdf.text.pdf.draw.LineSeparator) r15
            float r5 = r15.getPercentage()
            float r5 = r5 * r4
            r7 = 1120403456(0x42c80000, float:100.0)
            float r5 = r5 / r7
            float r17 = r17 - r5
            int r5 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r5 >= 0) goto L_0x01df
            r17 = r19
            goto L_0x01df
        L_0x021f:
            int r6 = r0.currentChar
            int r2 = r0.totalTextLength
            char[] r3 = r0.text
            r21 = r8
            com.itextpdf.text.pdf.PdfChunk[] r8 = r0.detailChunks
            r11 = r9
            r22 = r9
            r9 = r12
            r12 = r1
            r23 = r1
            r1 = r13
            r13 = r6
            r6 = r14
            r14 = r2
            r2 = r15
            r15 = r3
            r16 = r8
            boolean r3 = r11.isExtSplitCharacter(r12, r13, r14, r15, r16)
            if (r3 == 0) goto L_0x0249
            char r8 = (char) r7
            boolean r8 = java.lang.Character.isWhitespace(r8)
            if (r8 == 0) goto L_0x0249
            int r8 = r0.currentChar
            r18 = r8
        L_0x0249:
            float r5 = r17 - r5
            int r8 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r8 >= 0) goto L_0x0253
            r5 = r18
            r3 = 1
            goto L_0x02a1
        L_0x0253:
            if (r6 == 0) goto L_0x026d
            com.itextpdf.text.TabStop$Alignment r2 = r6.getAlignment()
            com.itextpdf.text.TabStop$Alignment r8 = com.itextpdf.text.TabStop.Alignment.ANCHOR
            if (r2 != r8) goto L_0x026d
            boolean r2 = java.lang.Float.isNaN(r9)
            if (r2 == 0) goto L_0x026d
            char r2 = r6.getAnchorChar()
            char r7 = (char) r7
            if (r2 != r7) goto L_0x026d
            float r2 = r4 - r17
            r9 = r2
        L_0x026d:
            if (r3 == 0) goto L_0x0275
            int r2 = r0.currentChar
            r13 = r1
            r18 = r2
            goto L_0x0276
        L_0x0275:
            r13 = r1
        L_0x0276:
            r17 = r5
        L_0x0278:
            if (r10 == 0) goto L_0x0281
            int r1 = r0.currentChar
            r3 = 1
            int r1 = r1 + r3
            r0.currentChar = r1
            goto L_0x0282
        L_0x0281:
            r3 = 1
        L_0x0282:
            r14 = r6
            r12 = r9
            r15 = r22
        L_0x0286:
            int r1 = r0.currentChar
            int r1 = r1 + r3
            r0.currentChar = r1
            r6 = r3
            r11 = r10
            r8 = r21
            r1 = r23
            r5 = 0
            r10 = 0
            goto L_0x0064
        L_0x0295:
            r23 = r1
            r3 = r6
            r21 = r8
            r9 = r12
            r1 = r13
            r6 = r14
            r2 = r15
            r10 = r11
            r5 = r18
        L_0x02a1:
            if (r2 != 0) goto L_0x02c9
            int r1 = r0.currentChar
            int r1 = r1 + r3
            r0.currentChar = r1
            if (r10 == 0) goto L_0x02af
            int r1 = r0.currentChar
            int r1 = r1 + r3
            r0.currentChar = r1
        L_0x02af:
            com.itextpdf.text.pdf.PdfLine r9 = new com.itextpdf.text.pdf.PdfLine
            r2 = 0
            r5 = 0
            r6 = 0
            int r1 = r0.currentChar
            int r1 = r1 - r3
            int r7 = r0.currentChar
            int r7 = r7 - r3
            java.util.ArrayList r7 = r0.createArrayOfPdfChunks(r1, r7)
            r1 = r9
            r3 = r4
            r4 = r5
            r5 = r27
            r8 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x02c9:
            if (r6 == 0) goto L_0x02ea
            float r3 = r4 - r17
            float r3 = r6.getPosition(r1, r3, r9)
            float r7 = r3 - r1
            float r7 = r17 - r7
            int r8 = (r7 > r19 ? 1 : (r7 == r19 ? 0 : -1))
            if (r8 >= 0) goto L_0x02db
            float r3 = r3 + r7
            goto L_0x02dd
        L_0x02db:
            r19 = r7
        L_0x02dd:
            if (r21 != 0) goto L_0x02e3
            r6.setPosition(r3)
            goto L_0x02ec
        L_0x02e3:
            float r3 = r4 - r19
            float r3 = r3 - r1
            r6.setPosition(r3)
            goto L_0x02ec
        L_0x02ea:
            r19 = r17
        L_0x02ec:
            int r1 = r0.currentChar
            int r3 = r0.totalTextLength
            if (r1 < r3) goto L_0x030c
            com.itextpdf.text.pdf.PdfLine r9 = new com.itextpdf.text.pdf.PdfLine
            r2 = 0
            r6 = 1
            int r1 = r0.totalTextLength
            r3 = 1
            int r1 = r1 - r3
            r3 = r23
            java.util.ArrayList r7 = r0.createArrayOfPdfChunks(r3, r1)
            r1 = r9
            r3 = r4
            r4 = r19
            r5 = r27
            r8 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x030c:
            r3 = r23
            int r1 = r0.currentChar
            r6 = 1
            int r1 = r1 - r6
            int r1 = r0.trimRightEx(r3, r1)
            if (r1 >= r3) goto L_0x0331
            com.itextpdf.text.pdf.PdfLine r9 = new com.itextpdf.text.pdf.PdfLine
            r2 = 0
            r7 = 0
            int r1 = r0.currentChar
            int r1 = r1 - r6
            java.util.ArrayList r8 = r0.createArrayOfPdfChunks(r3, r1)
            r1 = r9
            r3 = r4
            r4 = r19
            r5 = r27
            r6 = r7
            r7 = r8
            r8 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x0331:
            int r6 = r0.currentChar
            r7 = 1
            int r6 = r6 - r7
            if (r1 != r6) goto L_0x03ae
            java.lang.String r6 = "HYPHENATION"
            java.lang.Object r6 = r2.getAttribute(r6)
            com.itextpdf.text.pdf.HyphenationEvent r6 = (com.itextpdf.text.pdf.HyphenationEvent) r6
            if (r6 == 0) goto L_0x03ae
            int[] r8 = r0.getWord(r3, r1)
            if (r8 == 0) goto L_0x03ae
            r9 = 0
            r10 = r8[r9]
            int r11 = r0.currentChar
            int r11 = r11 - r7
            float r10 = r0.getWidth(r10, r11)
            float r10 = r19 + r10
            java.lang.String r11 = new java.lang.String
            char[] r12 = r0.text
            r13 = r8[r9]
            r14 = r8[r7]
            r7 = r8[r9]
            int r14 = r14 - r7
            r11.<init>(r12, r13, r14)
            com.itextpdf.text.pdf.PdfFont r7 = r2.font()
            com.itextpdf.text.pdf.BaseFont r7 = r7.getFont()
            com.itextpdf.text.pdf.PdfFont r9 = r2.font()
            float r9 = r9.size()
            java.lang.String r7 = r6.getHyphenatedWordPre(r11, r7, r9, r10)
            java.lang.String r6 = r6.getHyphenatedWordPost()
            int r9 = r7.length()
            if (r9 <= 0) goto L_0x03ae
            com.itextpdf.text.pdf.PdfChunk r1 = new com.itextpdf.text.pdf.PdfChunk
            r1.<init>((java.lang.String) r7, (com.itextpdf.text.pdf.PdfChunk) r2)
            r5 = 1
            r9 = r8[r5]
            int r6 = r6.length()
            int r9 = r9 - r6
            r0.currentChar = r9
            com.itextpdf.text.pdf.PdfLine r9 = new com.itextpdf.text.pdf.PdfLine
            r6 = 0
            float r2 = r2.width(r7)
            float r7 = r10 - r2
            r10 = 0
            r2 = 0
            r2 = r8[r2]
            int r2 = r2 - r5
            java.util.ArrayList r8 = r0.createArrayOfPdfChunks(r3, r2, r1)
            r1 = r9
            r2 = r6
            r3 = r4
            r4 = r7
            r5 = r27
            r6 = r10
            r7 = r8
            r8 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x03ae:
            r2 = -1
            if (r5 != r2) goto L_0x03b4
            r6 = 1
            r0.isWordSplit = r6
        L_0x03b4:
            if (r5 == r2) goto L_0x03e0
            if (r5 < r1) goto L_0x03b9
            goto L_0x03e0
        L_0x03b9:
            int r1 = r5 + 1
            r0.currentChar = r1
            int r1 = r0.trimRightEx(r3, r5)
            if (r1 >= r3) goto L_0x03c7
            int r1 = r0.currentChar
            r2 = 1
            int r1 = r1 - r2
        L_0x03c7:
            com.itextpdf.text.pdf.PdfLine r9 = new com.itextpdf.text.pdf.PdfLine
            r2 = 0
            float r5 = r0.getWidth(r3, r1, r4)
            float r5 = r4 - r5
            r6 = 0
            java.util.ArrayList r7 = r0.createArrayOfPdfChunks(r3, r1)
            r1 = r9
            r3 = r4
            r4 = r5
            r5 = r27
            r8 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        L_0x03e0:
            com.itextpdf.text.pdf.PdfLine r9 = new com.itextpdf.text.pdf.PdfLine
            r2 = 0
            int r5 = r1 + 1
            int r6 = r0.currentChar
            r7 = 1
            int r6 = r6 - r7
            float r5 = r0.getWidth(r5, r6, r4)
            float r5 = r19 + r5
            r6 = 0
            java.util.ArrayList r7 = r0.createArrayOfPdfChunks(r3, r1)
            r1 = r9
            r3 = r4
            r4 = r5
            r5 = r27
            r8 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.BidiLine.processLine(float, float, int, int, int, float, float, float):com.itextpdf.text.pdf.PdfLine");
    }

    public boolean isWordSplit() {
        return this.isWordSplit;
    }

    public float getWidth(int i, int i2) {
        return getWidth(i, i2, 0.0f);
    }

    public float getWidth(int i, int i2, float f) {
        float f2;
        float f3 = Float.NaN;
        float f4 = Float.NaN;
        float f5 = 0.0f;
        TabStop tabStop = null;
        while (i <= i2) {
            boolean isSurrogatePair = Utilities.isSurrogatePair(this.text, i);
            if (this.detailChunks[i].isTab() && this.detailChunks[i].isAttribute(Chunk.TABSETTINGS)) {
                if (tabStop != null) {
                    float position = tabStop.getPosition(f3, f5, f4);
                    f5 = (f5 - f3) + position;
                    tabStop.setPosition(position);
                }
                TabStop tabStop2 = this.detailChunks[i].getTabStop();
                if (tabStop2 == null) {
                    tabStop = PdfChunk.getTabStop(this.detailChunks[i], f5);
                    f4 = Float.NaN;
                    f3 = f5;
                } else {
                    if (this.runDirection == 3) {
                        f2 = f - tabStop2.getPosition();
                    } else {
                        f2 = tabStop2.getPosition();
                    }
                    f3 = Float.NaN;
                    f4 = Float.NaN;
                    f5 = f2;
                    tabStop = null;
                }
            } else if (isSurrogatePair) {
                f5 += this.detailChunks[i].getCharWidth(Utilities.convertToUtf32(this.text, i));
                i++;
            } else {
                char c = this.text[i];
                PdfChunk pdfChunk = this.detailChunks[i];
                if (!PdfChunk.noPrint(pdfChunk.getUnicodeEquivalent(c))) {
                    if (tabStop != null && tabStop.getAlignment() != TabStop.Alignment.ANCHOR && Float.isNaN(f4) && tabStop.getAnchorChar() == ((char) pdfChunk.getUnicodeEquivalent(c))) {
                        f4 = f5;
                    }
                    f5 += this.detailChunks[i].getCharWidth(c);
                }
            }
            i++;
        }
        if (tabStop == null) {
            return f5;
        }
        float position2 = tabStop.getPosition(f3, f5, f4);
        float f6 = (f5 - f3) + position2;
        tabStop.setPosition(position2);
        return f6;
    }

    public ArrayList<PdfChunk> createArrayOfPdfChunks(int i, int i2) {
        return createArrayOfPdfChunks(i, i2, (PdfChunk) null);
    }

    public ArrayList<PdfChunk> createArrayOfPdfChunks(int i, int i2, PdfChunk pdfChunk) {
        boolean z = this.runDirection == 2 || this.runDirection == 3;
        if (z) {
            reorder(i, i2);
        }
        ArrayList<PdfChunk> arrayList = new ArrayList<>();
        PdfChunk pdfChunk2 = this.detailChunks[i];
        StringBuffer stringBuffer = new StringBuffer();
        while (i <= i2) {
            int i3 = z ? this.indexChars[i] : i;
            char c = this.text[i3];
            PdfChunk pdfChunk3 = this.detailChunks[i3];
            if (!PdfChunk.noPrint(pdfChunk3.getUnicodeEquivalent(c))) {
                if (pdfChunk3.isImage() || pdfChunk3.isSeparator() || pdfChunk3.isTab()) {
                    if (stringBuffer.length() > 0) {
                        arrayList.add(new PdfChunk(stringBuffer.toString(), pdfChunk2));
                        stringBuffer = new StringBuffer();
                    }
                    arrayList.add(pdfChunk3);
                } else if (pdfChunk3 == pdfChunk2) {
                    stringBuffer.append(c);
                } else {
                    if (stringBuffer.length() > 0) {
                        arrayList.add(new PdfChunk(stringBuffer.toString(), pdfChunk2));
                        stringBuffer = new StringBuffer();
                    }
                    if (!pdfChunk3.isImage() && !pdfChunk3.isSeparator() && !pdfChunk3.isTab()) {
                        stringBuffer.append(c);
                    }
                    pdfChunk2 = pdfChunk3;
                }
            }
            i++;
        }
        if (stringBuffer.length() > 0) {
            arrayList.add(new PdfChunk(stringBuffer.toString(), pdfChunk2));
        }
        if (pdfChunk != null) {
            arrayList.add(pdfChunk);
        }
        return arrayList;
    }

    public int[] getWord(int i, int i2) {
        int i3 = i2;
        while (i3 < this.totalTextLength && (Character.isLetter(this.text[i3]) || Character.isDigit(this.text[i3]))) {
            i3++;
        }
        if (i3 == i2) {
            return null;
        }
        while (i2 >= i && (Character.isLetter(this.text[i2]) || Character.isDigit(this.text[i2]))) {
            i2--;
        }
        return new int[]{i2 + 1, i3};
    }

    public int trimRight(int i, int i2) {
        while (i2 >= i && isWS((char) this.detailChunks[i2].getUnicodeEquivalent(this.text[i2]))) {
            i2--;
        }
        return i2;
    }

    public int trimLeft(int i, int i2) {
        while (i <= i2 && isWS((char) this.detailChunks[i].getUnicodeEquivalent(this.text[i]))) {
            i++;
        }
        return i;
    }

    public int trimRightEx(int i, int i2) {
        while (i2 >= i) {
            char unicodeEquivalent = (char) this.detailChunks[i2].getUnicodeEquivalent(this.text[i2]);
            if (!isWS(unicodeEquivalent) && !PdfChunk.noPrint(unicodeEquivalent) && (!this.detailChunks[i2].isTab() || !this.detailChunks[i2].isAttribute(Chunk.TABSETTINGS) || !((Boolean) ((Object[]) this.detailChunks[i2].getAttribute(Chunk.TAB))[1]).booleanValue())) {
                break;
            }
            i2--;
        }
        return i2;
    }

    public int trimLeftEx(int i, int i2) {
        while (i <= i2) {
            char unicodeEquivalent = (char) this.detailChunks[i].getUnicodeEquivalent(this.text[i]);
            if (!isWS(unicodeEquivalent) && !PdfChunk.noPrint(unicodeEquivalent) && (!this.detailChunks[i].isTab() || !this.detailChunks[i].isAttribute(Chunk.TABSETTINGS) || !((Boolean) ((Object[]) this.detailChunks[i].getAttribute(Chunk.TAB))[1]).booleanValue())) {
                break;
            }
            i++;
        }
        return i;
    }

    public void reorder(int i, int i2) {
        byte b = this.orderLevels[i];
        byte b2 = b;
        byte b3 = b2;
        byte b4 = b3;
        for (int i3 = i + 1; i3 <= i2; i3++) {
            byte b5 = this.orderLevels[i3];
            if (b5 > b) {
                b = b5;
            } else if (b5 < b3) {
                b3 = b5;
            }
            b4 = (byte) (b4 & b5);
            b2 = (byte) (b2 | b5);
        }
        if ((b2 & 1) != 0) {
            if ((b4 & 1) == 1) {
                flip(i, i2 + 1);
                return;
            }
            byte b6 = (byte) (1 | b3);
            while (b >= b6) {
                int i4 = i;
                while (true) {
                    if (i4 <= i2 && this.orderLevels[i4] < b) {
                        i4++;
                    } else if (i4 > i2) {
                        break;
                    } else {
                        int i5 = i4 + 1;
                        while (i5 <= i2 && this.orderLevels[i5] >= b) {
                            i5++;
                        }
                        flip(i4, i5);
                        i4 = i5 + 1;
                    }
                }
                b = (byte) (b - 1);
            }
        }
    }

    public void flip(int i, int i2) {
        int i3 = (i + i2) / 2;
        int i4 = i2 - 1;
        while (i < i3) {
            int i5 = this.indexChars[i];
            this.indexChars[i] = this.indexChars[i4];
            this.indexChars[i4] = i5;
            i++;
            i4--;
        }
    }

    public static String processLTR(String str, int i, int i2) {
        BidiLine bidiLine = new BidiLine();
        bidiLine.addChunk(new PdfChunk(new Chunk(str), (PdfAction) null));
        bidiLine.arabicOptions = i2;
        bidiLine.getParagraph(i);
        ArrayList<PdfChunk> createArrayOfPdfChunks = bidiLine.createArrayOfPdfChunks(0, bidiLine.totalTextLength - 1);
        StringBuilder sb = new StringBuilder();
        Iterator<PdfChunk> it = createArrayOfPdfChunks.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }
}
