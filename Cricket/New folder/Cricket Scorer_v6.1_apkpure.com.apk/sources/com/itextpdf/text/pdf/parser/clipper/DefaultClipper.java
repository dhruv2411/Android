package com.itextpdf.text.pdf.parser.clipper;

import com.itextpdf.text.pdf.parser.clipper.Clipper;
import com.itextpdf.text.pdf.parser.clipper.ClipperBase;
import com.itextpdf.text.pdf.parser.clipper.Edge;
import com.itextpdf.text.pdf.parser.clipper.Path;
import com.itextpdf.text.pdf.parser.clipper.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class DefaultClipper extends ClipperBase {
    private static final Logger LOGGER = Logger.getLogger(DefaultClipper.class.getName());
    private Edge activeEdges;
    private Clipper.PolyFillType clipFillType;
    private Clipper.ClipType clipType;
    private final List<Path.Join> ghostJoins;
    private final List<IntersectNode> intersectList;
    private final Comparator<IntersectNode> intersectNodeComparer;
    private final List<Path.Join> joins;
    private Path.Maxima maxima;
    protected final List<Path.OutRec> polyOuts;
    private final boolean reverseSolution;
    private ClipperBase.Scanbeam scanbeam;
    private Edge sortedEdges;
    private final boolean strictlySimple;
    private Clipper.PolyFillType subjFillType;
    private boolean usingPolyTree;
    public Clipper.ZFillCallback zFillFunction;

    private boolean doHorzSegmentsOverlap(long j, long j2, long j3, long j4) {
        if (j > j2) {
            long j5 = j;
            j = j2;
            j2 = j5;
        }
        if (j3 <= j4) {
            long j6 = j3;
            j3 = j4;
            j4 = j6;
        }
        return j < j3 && j4 < j2;
    }

    private class IntersectNode {
        Edge Edge2;
        Edge edge1;
        private Point.LongPoint pt;

        private IntersectNode() {
        }

        public Point.LongPoint getPt() {
            return this.pt;
        }

        public void setPt(Point.LongPoint longPoint) {
            this.pt = longPoint;
        }
    }

    private static void getHorzDirection(Edge edge, Clipper.Direction[] directionArr, long[] jArr, long[] jArr2) {
        if (edge.getBot().getX() < edge.getTop().getX()) {
            jArr[0] = edge.getBot().getX();
            jArr2[0] = edge.getTop().getX();
            directionArr[0] = Clipper.Direction.LEFT_TO_RIGHT;
            return;
        }
        jArr[0] = edge.getTop().getX();
        jArr2[0] = edge.getBot().getX();
        directionArr[0] = Clipper.Direction.RIGHT_TO_LEFT;
    }

    private static boolean getOverlap(long j, long j2, long j3, long j4, long[] jArr, long[] jArr2) {
        if (j < j2) {
            if (j3 < j4) {
                jArr[0] = Math.max(j, j3);
                jArr2[0] = Math.min(j2, j4);
            } else {
                jArr[0] = Math.max(j, j4);
                jArr2[0] = Math.min(j2, j3);
            }
        } else if (j3 < j4) {
            jArr[0] = Math.max(j2, j3);
            jArr2[0] = Math.min(j, j4);
        } else {
            jArr[0] = Math.max(j2, j4);
            jArr2[0] = Math.min(j, j3);
        }
        if (jArr[0] < jArr2[0]) {
            return true;
        }
        return false;
    }

    private static boolean isParam1RightOfParam2(Path.OutRec outRec, Path.OutRec outRec2) {
        do {
            outRec = outRec.firstLeft;
            if (outRec == outRec2) {
                return true;
            }
        } while (outRec != null);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x00f4 A[LOOP:0: B:1:0x001c->B:66:0x00f4, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f3 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int isPointInPolygon(com.itextpdf.text.pdf.parser.clipper.Point.LongPoint r25, com.itextpdf.text.pdf.parser.clipper.Path.OutPt r26) {
        /*
            long r0 = r25.getX()
            long r2 = r25.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r26.getPt()
            long r4 = r4.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r6 = r26.getPt()
            long r6 = r6.getY()
            r9 = r4
            r5 = 0
            r4 = r26
        L_0x001c:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r4 = r4.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r4.getPt()
            long r11 = r11.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r13 = r4.getPt()
            long r13 = r13.getY()
            int r15 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            r16 = -1
            r17 = 1
            if (r15 != 0) goto L_0x0051
            int r15 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r15 == 0) goto L_0x0050
            int r15 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r15 != 0) goto L_0x0051
            int r15 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r15 <= 0) goto L_0x0045
            r15 = r17
            goto L_0x0046
        L_0x0045:
            r15 = 0
        L_0x0046:
            int r18 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r18 >= 0) goto L_0x004d
            r8 = r17
            goto L_0x004e
        L_0x004d:
            r8 = 0
        L_0x004e:
            if (r15 != r8) goto L_0x0051
        L_0x0050:
            return r16
        L_0x0051:
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 >= 0) goto L_0x0058
            r8 = r17
            goto L_0x0059
        L_0x0058:
            r8 = 0
        L_0x0059:
            int r15 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r15 >= 0) goto L_0x0060
            r15 = r17
            goto L_0x0061
        L_0x0060:
            r15 = 0
        L_0x0061:
            if (r8 == r15) goto L_0x00e5
            int r8 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            r19 = 0
            if (r8 < 0) goto L_0x00ae
            int r8 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r8 <= 0) goto L_0x0078
            int r17 = 1 - r5
            r23 = r0
            r1 = r4
            r5 = r17
            r0 = r26
            goto L_0x00f1
        L_0x0078:
            r21 = r4
            r22 = r5
            long r4 = r9 - r0
            double r4 = (double) r4
            long r8 = r13 - r2
            double r8 = (double) r8
            double r4 = r4 * r8
            long r8 = r11 - r0
            double r8 = (double) r8
            r23 = r0
            long r0 = r6 - r2
            double r0 = (double) r0
            double r8 = r8 * r0
            double r4 = r4 - r8
            int r0 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r0 != 0) goto L_0x0092
            return r16
        L_0x0092:
            int r0 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r0 <= 0) goto L_0x0099
            r0 = r17
            goto L_0x009a
        L_0x0099:
            r0 = 0
        L_0x009a:
            int r1 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x00a1
            r1 = r17
            goto L_0x00a2
        L_0x00a1:
            r1 = 0
        L_0x00a2:
            if (r0 != r1) goto L_0x00a7
            int r5 = 1 - r22
            goto L_0x00a9
        L_0x00a7:
            r5 = r22
        L_0x00a9:
            r0 = r26
        L_0x00ab:
            r1 = r21
            goto L_0x00f1
        L_0x00ae:
            r23 = r0
            r21 = r4
            r22 = r5
            int r0 = (r11 > r23 ? 1 : (r11 == r23 ? 0 : -1))
            if (r0 <= 0) goto L_0x00eb
            long r0 = r9 - r23
            double r0 = (double) r0
            long r4 = r13 - r2
            double r4 = (double) r4
            double r0 = r0 * r4
            long r4 = r11 - r23
            double r4 = (double) r4
            long r8 = r6 - r2
            double r8 = (double) r8
            double r4 = r4 * r8
            double r0 = r0 - r4
            int r4 = (r0 > r19 ? 1 : (r0 == r19 ? 0 : -1))
            if (r4 != 0) goto L_0x00cc
            return r16
        L_0x00cc:
            int r4 = (r0 > r19 ? 1 : (r0 == r19 ? 0 : -1))
            if (r4 <= 0) goto L_0x00d3
            r0 = r17
            goto L_0x00d4
        L_0x00d3:
            r0 = 0
        L_0x00d4:
            int r1 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x00db
            r1 = r17
            goto L_0x00dc
        L_0x00db:
            r1 = 0
        L_0x00dc:
            if (r0 != r1) goto L_0x00eb
            int r17 = 1 - r22
            r0 = r26
            r5 = r17
            goto L_0x00ab
        L_0x00e5:
            r23 = r0
            r21 = r4
            r22 = r5
        L_0x00eb:
            r0 = r26
            r1 = r21
            r5 = r22
        L_0x00f1:
            if (r0 != r1) goto L_0x00f4
            return r5
        L_0x00f4:
            r4 = r1
            r9 = r11
            r6 = r13
            r0 = r23
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.parser.clipper.DefaultClipper.isPointInPolygon(com.itextpdf.text.pdf.parser.clipper.Point$LongPoint, com.itextpdf.text.pdf.parser.clipper.Path$OutPt):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0112 A[LOOP:2: B:44:0x0112->B:50:0x014c, LOOP_START, PHI: r9 
      PHI: (r9v9 com.itextpdf.text.pdf.parser.clipper.Path$OutPt) = (r9v0 com.itextpdf.text.pdf.parser.clipper.Path$OutPt), (r9v14 com.itextpdf.text.pdf.parser.clipper.Path$OutPt) binds: [B:43:0x0110, B:50:0x014c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x017d A[LOOP:3: B:58:0x017d->B:64:0x01b7, LOOP_START, PHI: r9 
      PHI: (r9v4 com.itextpdf.text.pdf.parser.clipper.Path$OutPt) = (r9v0 com.itextpdf.text.pdf.parser.clipper.Path$OutPt), (r9v8 com.itextpdf.text.pdf.parser.clipper.Path$OutPt) binds: [B:43:0x0110, B:64:0x01b7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01eb  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01ee  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01f7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean joinHorz(com.itextpdf.text.pdf.parser.clipper.Path.OutPt r7, com.itextpdf.text.pdf.parser.clipper.Path.OutPt r8, com.itextpdf.text.pdf.parser.clipper.Path.OutPt r9, com.itextpdf.text.pdf.parser.clipper.Path.OutPt r10, com.itextpdf.text.pdf.parser.clipper.Point.LongPoint r11, boolean r12) {
        /*
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r7.getPt()
            long r0 = r0.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r8 = r8.getPt()
            long r2 = r8.getX()
            int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r8 <= 0) goto L_0x0017
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r8 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.RIGHT_TO_LEFT
            goto L_0x0019
        L_0x0017:
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r8 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.LEFT_TO_RIGHT
        L_0x0019:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r9.getPt()
            long r0 = r0.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r10.getPt()
            long r2 = r10.getX()
            int r10 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r10 <= 0) goto L_0x0030
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r10 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.RIGHT_TO_LEFT
            goto L_0x0032
        L_0x0030:
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r10 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.LEFT_TO_RIGHT
        L_0x0032:
            r0 = 0
            if (r8 != r10) goto L_0x0036
            return r0
        L_0x0036:
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r1 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.LEFT_TO_RIGHT
            if (r8 != r1) goto L_0x00a5
        L_0x003a:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r1 = r7.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r1.getPt()
            long r1 = r1.getX()
            long r3 = r11.getX()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0077
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r1 = r7.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r1.getPt()
            long r1 = r1.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r7.getPt()
            long r3 = r3.getX()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0077
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r1 = r7.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r1.getPt()
            long r1 = r1.getY()
            long r3 = r11.getY()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0077
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r7 = r7.next
            goto L_0x003a
        L_0x0077:
            if (r12 == 0) goto L_0x008b
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r7.getPt()
            long r1 = r1.getX()
            long r3 = r11.getX()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x008b
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r7 = r7.next
        L_0x008b:
            r1 = r12 ^ 1
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r1 = r7.duplicate(r1)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r1.getPt()
            boolean r2 = r2.equals(r11)
            if (r2 != 0) goto L_0x010e
            r1.setPt(r11)
            r7 = r12 ^ 1
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r7 = r1.duplicate(r7)
            goto L_0x010b
        L_0x00a5:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r1 = r7.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r1.getPt()
            long r1 = r1.getX()
            long r3 = r11.getX()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x00e2
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r1 = r7.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r1.getPt()
            long r1 = r1.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r7.getPt()
            long r3 = r3.getX()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x00e2
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r1 = r7.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r1.getPt()
            long r1 = r1.getY()
            long r3 = r11.getY()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x00e2
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r7 = r7.next
            goto L_0x00a5
        L_0x00e2:
            if (r12 != 0) goto L_0x00f6
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r7.getPt()
            long r1 = r1.getX()
            long r3 = r11.getX()
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00f6
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r7 = r7.next
        L_0x00f6:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r1 = r7.duplicate(r12)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r1.getPt()
            boolean r2 = r2.equals(r11)
            if (r2 != 0) goto L_0x010e
            r1.setPt(r11)
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r7 = r1.duplicate(r12)
        L_0x010b:
            r6 = r1
            r1 = r7
            r7 = r6
        L_0x010e:
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r2 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.LEFT_TO_RIGHT
            if (r10 != r2) goto L_0x017d
        L_0x0112:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r9.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r10.getPt()
            long r2 = r10.getX()
            long r4 = r11.getX()
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 > 0) goto L_0x014f
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r9.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r10.getPt()
            long r2 = r10.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r9.getPt()
            long r4 = r10.getX()
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 < 0) goto L_0x014f
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r9.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r10.getPt()
            long r2 = r10.getY()
            long r4 = r11.getY()
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x014f
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r9 = r9.next
            goto L_0x0112
        L_0x014f:
            if (r12 == 0) goto L_0x0163
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r9.getPt()
            long r2 = r10.getX()
            long r4 = r11.getX()
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 == 0) goto L_0x0163
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r9 = r9.next
        L_0x0163:
            r10 = r12 ^ 1
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r9.duplicate(r10)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r10.getPt()
            boolean r2 = r2.equals(r11)
            if (r2 != 0) goto L_0x01e6
            r10.setPt(r11)
            r9 = r12 ^ 1
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r9 = r10.duplicate(r9)
            goto L_0x01e3
        L_0x017d:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r9.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r10.getPt()
            long r2 = r10.getX()
            long r4 = r11.getX()
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 < 0) goto L_0x01ba
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r9.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r10.getPt()
            long r2 = r10.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r9.getPt()
            long r4 = r10.getX()
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 > 0) goto L_0x01ba
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r9.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r10.getPt()
            long r2 = r10.getY()
            long r4 = r11.getY()
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x01ba
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r9 = r9.next
            goto L_0x017d
        L_0x01ba:
            if (r12 != 0) goto L_0x01ce
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r9.getPt()
            long r2 = r10.getX()
            long r4 = r11.getX()
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 == 0) goto L_0x01ce
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r9 = r9.next
        L_0x01ce:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r9.duplicate(r12)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r10.getPt()
            boolean r2 = r2.equals(r11)
            if (r2 != 0) goto L_0x01e6
            r10.setPt(r11)
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r9 = r10.duplicate(r12)
        L_0x01e3:
            r6 = r10
            r10 = r9
            r9 = r6
        L_0x01e6:
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r11 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.LEFT_TO_RIGHT
            r2 = 1
            if (r8 != r11) goto L_0x01ec
            r0 = r2
        L_0x01ec:
            if (r0 != r12) goto L_0x01f7
            r7.prev = r9
            r9.next = r7
            r1.next = r10
            r10.prev = r1
            goto L_0x01ff
        L_0x01f7:
            r7.next = r9
            r9.prev = r7
            r1.prev = r10
            r10.next = r1
        L_0x01ff:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.parser.clipper.DefaultClipper.joinHorz(com.itextpdf.text.pdf.parser.clipper.Path$OutPt, com.itextpdf.text.pdf.parser.clipper.Path$OutPt, com.itextpdf.text.pdf.parser.clipper.Path$OutPt, com.itextpdf.text.pdf.parser.clipper.Path$OutPt, com.itextpdf.text.pdf.parser.clipper.Point$LongPoint, boolean):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x026a, code lost:
        if (r10.getPt().getX() > r9.getPt().getX()) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01db, code lost:
        if (r2.getPt().getX() > r8.getPt().getX()) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0216, code lost:
        if (r9.getPt().getX() > r10.getPt().getX()) goto L_0x01df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x024c, code lost:
        if (r8.getPt().getX() > r2.getPt().getX()) goto L_0x01df;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean joinPoints(com.itextpdf.text.pdf.parser.clipper.Path.Join r22, com.itextpdf.text.pdf.parser.clipper.Path.OutRec r23, com.itextpdf.text.pdf.parser.clipper.Path.OutRec r24) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r4 = r1.outPt1
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r5 = r1.outPt2
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r6 = r1.outPt1
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r6 = r6.getPt()
            long r6 = r6.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r8 = r22.getOffPt()
            long r8 = r8.getY()
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r6 = 1
            r7 = 0
            if (r10 != 0) goto L_0x0026
            r8 = r6
            goto L_0x0027
        L_0x0026:
            r8 = r7
        L_0x0027:
            if (r8 == 0) goto L_0x00d7
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r9 = r22.getOffPt()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r1.outPt1
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r10.getPt()
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x00d7
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r9 = r22.getOffPt()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r1.outPt2
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r10.getPt()
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x00d7
            if (r2 == r3) goto L_0x004c
            return r7
        L_0x004c:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r1.outPt1
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r2.next
        L_0x0050:
            if (r2 == r4) goto L_0x0063
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r2.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r8 = r22.getOffPt()
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x0063
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r2.next
            goto L_0x0050
        L_0x0063:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r2.getPt()
            long r2 = r2.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r8 = r22.getOffPt()
            long r8 = r8.getY()
            int r10 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x0079
            r2 = r6
            goto L_0x007a
        L_0x0079:
            r2 = r7
        L_0x007a:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r1.outPt2
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r3.next
        L_0x007e:
            if (r3 == r5) goto L_0x0091
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r8 = r3.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r9 = r22.getOffPt()
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0091
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r3.next
            goto L_0x007e
        L_0x0091:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r3.getPt()
            long r8 = r3.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r22.getOffPt()
            long r10 = r3.getY()
            int r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r3 <= 0) goto L_0x00a7
            r3 = r6
            goto L_0x00a8
        L_0x00a7:
            r3 = r7
        L_0x00a8:
            if (r2 != r3) goto L_0x00ab
            return r7
        L_0x00ab:
            if (r2 == 0) goto L_0x00c2
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r4.duplicate(r7)
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r5.duplicate(r6)
            r4.prev = r5
            r5.next = r4
            r2.next = r3
            r3.prev = r2
            r1.outPt1 = r4
            r1.outPt2 = r2
            return r6
        L_0x00c2:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r4.duplicate(r6)
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r5.duplicate(r7)
            r4.next = r5
            r5.prev = r4
            r2.prev = r3
            r3.next = r2
            r1.outPt1 = r4
            r1.outPt2 = r2
            return r6
        L_0x00d7:
            if (r8 == 0) goto L_0x027a
            r2 = r4
        L_0x00da:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r2.prev
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r3.getPt()
            long r8 = r3.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r2.getPt()
            long r10 = r3.getY()
            int r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r3 != 0) goto L_0x00fb
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r2.prev
            if (r3 == r4) goto L_0x00fb
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r2.prev
            if (r3 == r5) goto L_0x00fb
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r2.prev
            goto L_0x00da
        L_0x00fb:
            r8 = r4
        L_0x00fc:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r8.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r3.getPt()
            long r3 = r3.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r9 = r8.getPt()
            long r9 = r9.getY()
            int r11 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x011d
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r8.next
            if (r3 == r2) goto L_0x011d
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r8.next
            if (r3 == r5) goto L_0x011d
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r8 = r8.next
            goto L_0x00fc
        L_0x011d:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r8.next
            if (r3 == r2) goto L_0x0279
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r8.next
            if (r3 != r5) goto L_0x0127
            goto L_0x0279
        L_0x0127:
            r9 = r5
        L_0x0128:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r9.prev
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r3.getPt()
            long r3 = r3.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r9.getPt()
            long r10 = r10.getY()
            int r12 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r12 != 0) goto L_0x0149
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r9.prev
            if (r3 == r5) goto L_0x0149
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r9.prev
            if (r3 == r8) goto L_0x0149
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r9 = r9.prev
            goto L_0x0128
        L_0x0149:
            r10 = r5
        L_0x014a:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r10.next
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r3.getPt()
            long r3 = r3.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r10.getPt()
            long r11 = r5.getY()
            int r5 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r5 != 0) goto L_0x016b
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r10.next
            if (r3 == r9) goto L_0x016b
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r10.next
            if (r3 == r2) goto L_0x016b
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r10.next
            goto L_0x014a
        L_0x016b:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r10.next
            if (r3 == r9) goto L_0x0278
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r10.next
            if (r3 != r2) goto L_0x0175
            goto L_0x0278
        L_0x0175:
            long[] r3 = new long[r6]
            long[] r4 = new long[r6]
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r2.getPt()
            long r11 = r5.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r8.getPt()
            long r13 = r5.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r9.getPt()
            long r15 = r5.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r10.getPt()
            long r17 = r5.getX()
            r19 = r3
            r20 = r4
            boolean r5 = getOverlap(r11, r13, r15, r17, r19, r20)
            if (r5 != 0) goto L_0x01a4
            return r7
        L_0x01a4:
            r11 = r3[r7]
            r3 = r4[r7]
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r2.getPt()
            long r13 = r5.getX()
            int r5 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r5 < 0) goto L_0x01e3
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r2.getPt()
            long r13 = r5.getX()
            int r5 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x01e3
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = new com.itextpdf.text.pdf.parser.clipper.Point$LongPoint
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r2.getPt()
            r3.<init>(r4)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r2.getPt()
            long r4 = r4.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r8.getPt()
            long r11 = r11.getX()
            int r13 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r13 <= 0) goto L_0x01de
            goto L_0x01df
        L_0x01de:
            r6 = r7
        L_0x01df:
            r11 = r3
            r12 = r6
            goto L_0x026e
        L_0x01e3:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r9.getPt()
            long r13 = r5.getX()
            int r5 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r5 < 0) goto L_0x0219
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r9.getPt()
            long r13 = r5.getX()
            int r5 = (r13 > r3 ? 1 : (r13 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x0219
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = new com.itextpdf.text.pdf.parser.clipper.Point$LongPoint
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r9.getPt()
            r3.<init>(r4)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r9.getPt()
            long r4 = r4.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r10.getPt()
            long r11 = r11.getX()
            int r13 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r13 <= 0) goto L_0x01de
            goto L_0x01df
        L_0x0219:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r8.getPt()
            long r13 = r5.getX()
            int r5 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r5 < 0) goto L_0x024f
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r8.getPt()
            long r11 = r5.getX()
            int r5 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x024f
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = new com.itextpdf.text.pdf.parser.clipper.Point$LongPoint
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r8.getPt()
            r3.<init>(r4)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r8.getPt()
            long r4 = r4.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r2.getPt()
            long r11 = r11.getX()
            int r13 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r13 <= 0) goto L_0x01de
            goto L_0x01df
        L_0x024f:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = new com.itextpdf.text.pdf.parser.clipper.Point$LongPoint
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r10.getPt()
            r3.<init>(r4)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r10.getPt()
            long r4 = r4.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r9.getPt()
            long r11 = r11.getX()
            int r13 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r13 <= 0) goto L_0x01de
            goto L_0x01df
        L_0x026e:
            r1.outPt1 = r2
            r1.outPt2 = r9
            r7 = r2
            boolean r1 = joinHorz(r7, r8, r9, r10, r11, r12)
            return r1
        L_0x0278:
            return r7
        L_0x0279:
            return r7
        L_0x027a:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r8 = r4.next
        L_0x027c:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r9 = r8.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r4.getPt()
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x028f
            if (r8 == r4) goto L_0x028f
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r8 = r8.next
            goto L_0x027c
        L_0x028f:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r9 = r8.getPt()
            long r9 = r9.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r4.getPt()
            long r11 = r11.getY()
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 > 0) goto L_0x02ba
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r9 = r4.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r8.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r22.getOffPt()
            boolean r12 = r0.useFullRange
            boolean r9 = com.itextpdf.text.pdf.parser.clipper.Point.slopesEqual(r9, r10, r11, r12)
            if (r9 != 0) goto L_0x02b8
            goto L_0x02ba
        L_0x02b8:
            r9 = r7
            goto L_0x02bb
        L_0x02ba:
            r9 = r6
        L_0x02bb:
            if (r9 == 0) goto L_0x02fb
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r8 = r4.prev
        L_0x02bf:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r8.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r4.getPt()
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x02d2
            if (r8 == r4) goto L_0x02d2
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r8 = r8.prev
            goto L_0x02bf
        L_0x02d2:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r8.getPt()
            long r10 = r10.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r12 = r4.getPt()
            long r12 = r12.getY()
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 > 0) goto L_0x02fa
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r4.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r8.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r12 = r22.getOffPt()
            boolean r13 = r0.useFullRange
            boolean r10 = com.itextpdf.text.pdf.parser.clipper.Point.slopesEqual(r10, r11, r12, r13)
            if (r10 != 0) goto L_0x02fb
        L_0x02fa:
            return r7
        L_0x02fb:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r5.next
        L_0x02fd:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r10.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r12 = r5.getPt()
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x0310
            if (r10 == r5) goto L_0x0310
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r10.next
            goto L_0x02fd
        L_0x0310:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r10.getPt()
            long r11 = r11.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r13 = r5.getPt()
            long r13 = r13.getY()
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 > 0) goto L_0x033b
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r11 = r5.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r12 = r10.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r13 = r22.getOffPt()
            boolean r14 = r0.useFullRange
            boolean r11 = com.itextpdf.text.pdf.parser.clipper.Point.slopesEqual(r11, r12, r13, r14)
            if (r11 != 0) goto L_0x0339
            goto L_0x033b
        L_0x0339:
            r11 = r7
            goto L_0x033c
        L_0x033b:
            r11 = r6
        L_0x033c:
            if (r11 == 0) goto L_0x037c
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r5.prev
        L_0x0340:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r12 = r10.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r13 = r5.getPt()
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x0353
            if (r10 == r5) goto L_0x0353
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r10 = r10.prev
            goto L_0x0340
        L_0x0353:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r12 = r10.getPt()
            long r12 = r12.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r14 = r5.getPt()
            long r14 = r14.getY()
            int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r16 > 0) goto L_0x037b
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r12 = r5.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r13 = r10.getPt()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r14 = r22.getOffPt()
            boolean r15 = r0.useFullRange
            boolean r12 = com.itextpdf.text.pdf.parser.clipper.Point.slopesEqual(r12, r13, r14, r15)
            if (r12 != 0) goto L_0x037c
        L_0x037b:
            return r7
        L_0x037c:
            if (r8 == r4) goto L_0x03b3
            if (r10 == r5) goto L_0x03b3
            if (r8 == r10) goto L_0x03b3
            if (r2 != r3) goto L_0x0387
            if (r9 != r11) goto L_0x0387
            goto L_0x03b3
        L_0x0387:
            if (r9 == 0) goto L_0x039e
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r4.duplicate(r7)
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r5.duplicate(r6)
            r4.prev = r5
            r5.next = r4
            r2.next = r3
            r3.prev = r2
            r1.outPt1 = r4
            r1.outPt2 = r2
            return r6
        L_0x039e:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r4.duplicate(r6)
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r5.duplicate(r7)
            r4.next = r5
            r5.prev = r4
            r2.prev = r3
            r3.next = r2
            r1.outPt1 = r4
            r1.outPt2 = r2
            return r6
        L_0x03b3:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.parser.clipper.DefaultClipper.joinPoints(com.itextpdf.text.pdf.parser.clipper.Path$Join, com.itextpdf.text.pdf.parser.clipper.Path$OutRec, com.itextpdf.text.pdf.parser.clipper.Path$OutRec):boolean");
    }

    private static Paths minkowski(Path path, Path path2, boolean z, boolean z2) {
        Path path3 = path2;
        int size = path.size();
        int size2 = path2.size();
        Paths paths = new Paths(size2);
        if (z) {
            for (int i = 0; i < size2; i++) {
                Path path4 = new Path(size);
                Iterator it = path.iterator();
                while (it.hasNext()) {
                    Point.LongPoint longPoint = (Point.LongPoint) it.next();
                    long x = ((Point.LongPoint) path3.get(i)).getX() + longPoint.getX();
                    Point.LongPoint longPoint2 = r9;
                    Point.LongPoint longPoint3 = new Point.LongPoint(x, ((Point.LongPoint) path3.get(i)).getY() + longPoint.getY(), 0);
                    path4.add(longPoint2);
                }
                paths.add(path4);
            }
        } else {
            for (int i2 = 0; i2 < size2; i2++) {
                Path path5 = new Path(size);
                Iterator it2 = path.iterator();
                while (it2.hasNext()) {
                    Point.LongPoint longPoint4 = (Point.LongPoint) it2.next();
                    long x2 = ((Point.LongPoint) path3.get(i2)).getX() - longPoint4.getX();
                    Point.LongPoint longPoint5 = r9;
                    Point.LongPoint longPoint6 = new Point.LongPoint(x2, ((Point.LongPoint) path3.get(i2)).getY() - longPoint4.getY(), 0);
                    path5.add(longPoint5);
                }
                paths.add(path5);
            }
        }
        Paths paths2 = new Paths((size2 + (z2 ? 1 : 0)) * (size + 1));
        for (int i3 = 0; i3 < (size2 - 1) + z2; i3++) {
            int i4 = 0;
            while (i4 < size) {
                Path path6 = new Path(4);
                int i5 = i3 % size2;
                int i6 = i4 % size;
                path6.add(((Path) paths.get(i5)).get(i6));
                int i7 = (i3 + 1) % size2;
                path6.add(((Path) paths.get(i7)).get(i6));
                i4++;
                int i8 = i4 % size;
                path6.add(((Path) paths.get(i7)).get(i8));
                path6.add(((Path) paths.get(i5)).get(i8));
                if (!path6.orientation()) {
                    Collections.reverse(path6);
                }
                paths2.add(path6);
            }
        }
        return paths2;
    }

    public static Paths minkowskiDiff(Path path, Path path2) {
        Paths minkowski = minkowski(path, path2, false, true);
        DefaultClipper defaultClipper = new DefaultClipper();
        defaultClipper.addPaths(minkowski, Clipper.PolyType.SUBJECT, true);
        defaultClipper.execute(Clipper.ClipType.UNION, minkowski, Clipper.PolyFillType.NON_ZERO, Clipper.PolyFillType.NON_ZERO);
        return minkowski;
    }

    public static Paths minkowskiSum(Path path, Path path2, boolean z) {
        Paths minkowski = minkowski(path, path2, true, z);
        DefaultClipper defaultClipper = new DefaultClipper();
        defaultClipper.addPaths(minkowski, Clipper.PolyType.SUBJECT, true);
        defaultClipper.execute(Clipper.ClipType.UNION, minkowski, Clipper.PolyFillType.NON_ZERO, Clipper.PolyFillType.NON_ZERO);
        return minkowski;
    }

    public static Paths minkowskiSum(Path path, Paths paths, boolean z) {
        Paths paths2 = new Paths();
        DefaultClipper defaultClipper = new DefaultClipper();
        for (int i = 0; i < paths.size(); i++) {
            defaultClipper.addPaths(minkowski(path, (Path) paths.get(i), true, z), Clipper.PolyType.SUBJECT, true);
            if (z) {
                defaultClipper.addPath(((Path) paths.get(i)).TranslatePath((Point.LongPoint) path.get(0)), Clipper.PolyType.CLIP, true);
            }
        }
        defaultClipper.execute(Clipper.ClipType.UNION, paths2, Clipper.PolyFillType.NON_ZERO, Clipper.PolyFillType.NON_ZERO);
        return paths2;
    }

    private static boolean poly2ContainsPoly1(Path.OutPt outPt, Path.OutPt outPt2) {
        Path.OutPt outPt3 = outPt;
        do {
            int isPointInPolygon = isPointInPolygon(outPt3.getPt(), outPt2);
            if (isPointInPolygon >= 0) {
                return isPointInPolygon > 0;
            }
            outPt3 = outPt3.next;
        } while (outPt3 != outPt);
        return true;
    }

    public static Paths simplifyPolygon(Path path) {
        return simplifyPolygon(path, Clipper.PolyFillType.EVEN_ODD);
    }

    public static Paths simplifyPolygon(Path path, Clipper.PolyFillType polyFillType) {
        Paths paths = new Paths();
        DefaultClipper defaultClipper = new DefaultClipper(2);
        defaultClipper.addPath(path, Clipper.PolyType.SUBJECT, true);
        defaultClipper.execute(Clipper.ClipType.UNION, paths, polyFillType, polyFillType);
        return paths;
    }

    public static Paths simplifyPolygons(Paths paths) {
        return simplifyPolygons(paths, Clipper.PolyFillType.EVEN_ODD);
    }

    public static Paths simplifyPolygons(Paths paths, Clipper.PolyFillType polyFillType) {
        Paths paths2 = new Paths();
        DefaultClipper defaultClipper = new DefaultClipper(2);
        defaultClipper.addPaths(paths, Clipper.PolyType.SUBJECT, true);
        defaultClipper.execute(Clipper.ClipType.UNION, paths2, polyFillType, polyFillType);
        return paths2;
    }

    public DefaultClipper() {
        this(0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultClipper(int i) {
        super((4 & i) != 0);
        boolean z = false;
        this.scanbeam = null;
        this.maxima = null;
        this.activeEdges = null;
        this.sortedEdges = null;
        this.intersectList = new ArrayList();
        this.intersectNodeComparer = new Comparator<IntersectNode>() {
            public int compare(IntersectNode intersectNode, IntersectNode intersectNode2) {
                long y = intersectNode2.getPt().getY() - intersectNode.getPt().getY();
                if (y > 0) {
                    return 1;
                }
                return y < 0 ? -1 : 0;
            }
        };
        this.usingPolyTree = false;
        this.polyOuts = new ArrayList();
        this.joins = new ArrayList();
        this.ghostJoins = new ArrayList();
        this.reverseSolution = (1 & i) != 0;
        this.strictlySimple = (i & 2) != 0 ? true : z;
        this.zFillFunction = null;
    }

    private void insertScanbeam(long j) {
        if (this.scanbeam == null) {
            this.scanbeam = new ClipperBase.Scanbeam();
            this.scanbeam.next = null;
            this.scanbeam.y = j;
        } else if (j > this.scanbeam.y) {
            ClipperBase.Scanbeam scanbeam2 = new ClipperBase.Scanbeam();
            scanbeam2.y = j;
            scanbeam2.next = this.scanbeam;
            this.scanbeam = scanbeam2;
        } else {
            ClipperBase.Scanbeam scanbeam3 = this.scanbeam;
            while (scanbeam3.next != null && j <= scanbeam3.next.y) {
                scanbeam3 = scanbeam3.next;
            }
            if (j != scanbeam3.y) {
                ClipperBase.Scanbeam scanbeam4 = new ClipperBase.Scanbeam();
                scanbeam4.y = j;
                scanbeam4.next = scanbeam3.next;
                scanbeam3.next = scanbeam4;
            }
        }
    }

    private void InsertMaxima(long j) {
        Path.Maxima maxima2 = new Path.Maxima();
        maxima2.X = j;
        if (this.maxima == null) {
            this.maxima = maxima2;
            this.maxima.Next = null;
            this.maxima.Prev = null;
        } else if (j < this.maxima.X) {
            maxima2.Next = this.maxima;
            maxima2.Prev = null;
            this.maxima = maxima2;
        } else {
            Path.Maxima maxima3 = this.maxima;
            while (maxima3.Next != null && j >= maxima3.Next.X) {
                maxima3 = maxima3.Next;
            }
            if (j != maxima3.X) {
                maxima2.Next = maxima3.Next;
                maxima2.Prev = maxima3;
                if (maxima3.Next != null) {
                    maxima3.Next.Prev = maxima2;
                }
                maxima3.Next = maxima2;
            }
        }
    }

    private void addEdgeToSEL(Edge edge) {
        LOGGER.entering(DefaultClipper.class.getName(), "addEdgeToSEL");
        if (this.sortedEdges == null) {
            this.sortedEdges = edge;
            edge.prevInSEL = null;
            edge.nextInSEL = null;
            return;
        }
        edge.nextInSEL = this.sortedEdges;
        edge.prevInSEL = null;
        this.sortedEdges.prevInSEL = edge;
        this.sortedEdges = edge;
    }

    private void addGhostJoin(Path.OutPt outPt, Point.LongPoint longPoint) {
        Path.Join join = new Path.Join();
        join.outPt1 = outPt;
        join.setOffPt(longPoint);
        this.ghostJoins.add(join);
    }

    private void addJoin(Path.OutPt outPt, Path.OutPt outPt2, Point.LongPoint longPoint) {
        LOGGER.entering(DefaultClipper.class.getName(), "addJoin");
        Path.Join join = new Path.Join();
        join.outPt1 = outPt;
        join.outPt2 = outPt2;
        join.setOffPt(longPoint);
        this.joins.add(join);
    }

    private void addLocalMaxPoly(Edge edge, Edge edge2, Point.LongPoint longPoint) {
        addOutPt(edge, longPoint);
        if (edge2.windDelta == 0) {
            addOutPt(edge2, longPoint);
        }
        if (edge.outIdx == edge2.outIdx) {
            edge.outIdx = -1;
            edge2.outIdx = -1;
        } else if (edge.outIdx < edge2.outIdx) {
            appendPolygon(edge, edge2);
        } else {
            appendPolygon(edge2, edge);
        }
    }

    private Path.OutPt addLocalMinPoly(Edge edge, Edge edge2, Point.LongPoint longPoint) {
        Edge edge3;
        Path.OutPt outPt;
        Edge edge4;
        LOGGER.entering(DefaultClipper.class.getName(), "addLocalMinPoly");
        if (edge2.isHorizontal() || edge.deltaX > edge2.deltaX) {
            outPt = addOutPt(edge, longPoint);
            edge2.outIdx = edge.outIdx;
            edge.side = Edge.Side.LEFT;
            edge2.side = Edge.Side.RIGHT;
            if (edge.prevInAEL == edge2) {
                edge4 = edge2.prevInAEL;
            } else {
                edge4 = edge.prevInAEL;
            }
            Edge edge5 = edge4;
            edge2 = edge;
            edge3 = edge5;
        } else {
            outPt = addOutPt(edge2, longPoint);
            edge.outIdx = edge2.outIdx;
            edge.side = Edge.Side.RIGHT;
            edge2.side = Edge.Side.LEFT;
            if (edge2.prevInAEL == edge) {
                edge3 = edge.prevInAEL;
            } else {
                edge3 = edge2.prevInAEL;
            }
        }
        if (edge3 != null && edge3.outIdx >= 0 && Edge.topX(edge3, longPoint.getY()) == Edge.topX(edge2, longPoint.getY()) && Edge.slopesEqual(edge2, edge3, this.useFullRange) && edge2.windDelta != 0 && edge3.windDelta != 0) {
            addJoin(outPt, addOutPt(edge3, longPoint), edge2.getTop());
        }
        return outPt;
    }

    private Path.OutPt addOutPt(Edge edge, Point.LongPoint longPoint) {
        LOGGER.entering(DefaultClipper.class.getName(), "addOutPt");
        boolean z = false;
        if (edge.outIdx < 0) {
            Path.OutRec createOutRec = createOutRec();
            if (edge.windDelta == 0) {
                z = true;
            }
            createOutRec.isOpen = z;
            Path.OutPt outPt = new Path.OutPt();
            createOutRec.pts = outPt;
            outPt.idx = createOutRec.Idx;
            outPt.pt = longPoint;
            outPt.next = outPt;
            outPt.prev = outPt;
            if (!createOutRec.isOpen) {
                setHoleState(edge, createOutRec);
            }
            edge.outIdx = createOutRec.Idx;
            return outPt;
        }
        Path.OutRec outRec = this.polyOuts.get(edge.outIdx);
        Path.OutPt points = outRec.getPoints();
        if (edge.side == Edge.Side.LEFT) {
            z = true;
        }
        LOGGER.finest("op=" + points.getPointCount());
        LOGGER.finest(z + " " + longPoint + " " + points.getPt());
        if (z && longPoint.equals(points.getPt())) {
            return points;
        }
        if (!z && longPoint.equals(points.prev.getPt())) {
            return points.prev;
        }
        Path.OutPt outPt2 = new Path.OutPt();
        outPt2.idx = outRec.Idx;
        outPt2.setPt(new Point.LongPoint(longPoint));
        outPt2.next = points;
        outPt2.prev = points.prev;
        outPt2.prev.next = outPt2;
        points.prev = outPt2;
        if (z) {
            outRec.setPoints(outPt2);
        }
        return outPt2;
    }

    private Path.OutPt GetLastOutPt(Edge edge) {
        Path.OutRec outRec = this.polyOuts.get(edge.outIdx);
        if (edge.side == Edge.Side.LEFT) {
            return outRec.pts;
        }
        return outRec.pts.prev;
    }

    private void appendPolygon(Edge edge, Edge edge2) {
        Path.OutRec outRec;
        Edge.Side side;
        LOGGER.entering(DefaultClipper.class.getName(), "appendPolygon");
        Path.OutRec outRec2 = this.polyOuts.get(edge.outIdx);
        Path.OutRec outRec3 = this.polyOuts.get(edge2.outIdx);
        Logger logger = LOGGER;
        logger.finest("" + edge.outIdx);
        Logger logger2 = LOGGER;
        logger2.finest("" + edge2.outIdx);
        if (isParam1RightOfParam2(outRec2, outRec3)) {
            outRec = outRec3;
        } else if (isParam1RightOfParam2(outRec3, outRec2)) {
            outRec = outRec2;
        } else {
            outRec = Path.OutPt.getLowerMostRec(outRec2, outRec3);
        }
        Path.OutPt points = outRec2.getPoints();
        Path.OutPt outPt = points.prev;
        Path.OutPt points2 = outRec3.getPoints();
        Path.OutPt outPt2 = points2.prev;
        Logger logger3 = LOGGER;
        logger3.finest("p1_lft.getPointCount() = " + points.getPointCount());
        Logger logger4 = LOGGER;
        logger4.finest("p1_rt.getPointCount() = " + outPt.getPointCount());
        Logger logger5 = LOGGER;
        logger5.finest("p2_lft.getPointCount() = " + points2.getPointCount());
        Logger logger6 = LOGGER;
        logger6.finest("p2_rt.getPointCount() = " + outPt2.getPointCount());
        if (edge.side == Edge.Side.LEFT) {
            if (edge2.side == Edge.Side.LEFT) {
                points2.reversePolyPtLinks();
                points2.next = points;
                points.prev = points2;
                outPt.next = outPt2;
                outPt2.prev = outPt;
                outRec2.setPoints(outPt2);
            } else {
                outPt2.next = points;
                points.prev = outPt2;
                points2.prev = outPt;
                outPt.next = points2;
                outRec2.setPoints(points2);
            }
            side = Edge.Side.LEFT;
        } else {
            if (edge2.side == Edge.Side.RIGHT) {
                points2.reversePolyPtLinks();
                outPt.next = outPt2;
                outPt2.prev = outPt;
                points2.next = points;
                points.prev = points2;
            } else {
                outPt.next = points2;
                points2.prev = outPt;
                points.prev = outPt2;
                outPt2.next = points;
            }
            side = Edge.Side.RIGHT;
        }
        outRec2.bottomPt = null;
        if (outRec.equals(outRec3)) {
            if (outRec3.firstLeft != outRec2) {
                outRec2.firstLeft = outRec3.firstLeft;
            }
            outRec2.isHole = outRec3.isHole;
        }
        outRec3.setPoints((Path.OutPt) null);
        outRec3.bottomPt = null;
        outRec3.firstLeft = outRec2;
        int i = edge.outIdx;
        int i2 = edge2.outIdx;
        edge.outIdx = -1;
        edge2.outIdx = -1;
        Edge edge3 = this.activeEdges;
        while (true) {
            if (edge3 == null) {
                break;
            } else if (edge3.outIdx == i2) {
                edge3.outIdx = i;
                edge3.side = side;
                break;
            } else {
                edge3 = edge3.nextInAEL;
            }
        }
        outRec3.Idx = outRec2.Idx;
    }

    private void buildIntersectList(long j) {
        boolean z;
        if (this.activeEdges != null) {
            Edge edge = this.activeEdges;
            this.sortedEdges = edge;
            while (edge != null) {
                edge.prevInSEL = edge.prevInAEL;
                edge.nextInSEL = edge.nextInAEL;
                edge.getCurrent().setX(Long.valueOf(Edge.topX(edge, j)));
                edge = edge.nextInAEL;
            }
            for (boolean z2 = true; z2 && this.sortedEdges != null; z2 = z) {
                Edge edge2 = this.sortedEdges;
                z = false;
                while (edge2.nextInSEL != null) {
                    Edge edge3 = edge2.nextInSEL;
                    Point.LongPoint[] longPointArr = new Point.LongPoint[1];
                    if (edge2.getCurrent().getX() > edge3.getCurrent().getX()) {
                        intersectPoint(edge2, edge3, longPointArr);
                        IntersectNode intersectNode = new IntersectNode();
                        intersectNode.edge1 = edge2;
                        intersectNode.Edge2 = edge3;
                        intersectNode.setPt(longPointArr[0]);
                        this.intersectList.add(intersectNode);
                        swapPositionsInSEL(edge2, edge3);
                        z = true;
                    } else {
                        edge2 = edge3;
                    }
                }
                if (edge2.prevInSEL == null) {
                    break;
                }
                edge2.prevInSEL.nextInSEL = null;
            }
            this.sortedEdges = null;
        }
    }

    private void buildResult(Paths paths) {
        paths.clear();
        for (int i = 0; i < this.polyOuts.size(); i++) {
            Path.OutRec outRec = this.polyOuts.get(i);
            if (outRec.getPoints() != null) {
                Path.OutPt outPt = outRec.getPoints().prev;
                int pointCount = outPt.getPointCount();
                LOGGER.finest("cnt = " + pointCount);
                if (pointCount >= 2) {
                    Path path = new Path(pointCount);
                    Path.OutPt outPt2 = outPt;
                    for (int i2 = 0; i2 < pointCount; i2++) {
                        path.add(outPt2.getPt());
                        outPt2 = outPt2.prev;
                    }
                    paths.add(path);
                }
            }
        }
    }

    private void buildResult2(PolyTree polyTree) {
        polyTree.Clear();
        for (int i = 0; i < this.polyOuts.size(); i++) {
            Path.OutRec outRec = this.polyOuts.get(i);
            int pointCount = outRec.getPoints() != null ? outRec.getPoints().getPointCount() : 0;
            if ((!outRec.isOpen || pointCount >= 2) && (outRec.isOpen || pointCount >= 3)) {
                outRec.fixHoleLinkage();
                PolyNode polyNode = new PolyNode();
                polyTree.getAllPolys().add(polyNode);
                outRec.polyNode = polyNode;
                Path.OutPt outPt = outRec.getPoints().prev;
                for (int i2 = 0; i2 < pointCount; i2++) {
                    polyNode.getPolygon().add(outPt.getPt());
                    outPt = outPt.prev;
                }
            }
        }
        for (int i3 = 0; i3 < this.polyOuts.size(); i3++) {
            Path.OutRec outRec2 = this.polyOuts.get(i3);
            if (outRec2.polyNode != null) {
                if (outRec2.isOpen) {
                    outRec2.polyNode.setOpen(true);
                    polyTree.addChild(outRec2.polyNode);
                } else if (outRec2.firstLeft == null || outRec2.firstLeft.polyNode == null) {
                    polyTree.addChild(outRec2.polyNode);
                } else {
                    outRec2.firstLeft.polyNode.addChild(outRec2.polyNode);
                }
            }
        }
    }

    private void copyAELToSEL() {
        Edge edge = this.activeEdges;
        this.sortedEdges = edge;
        while (edge != null) {
            edge.prevInSEL = edge.prevInAEL;
            edge.nextInSEL = edge.nextInAEL;
            edge = edge.nextInAEL;
        }
    }

    private Path.OutRec createOutRec() {
        Path.OutRec outRec = new Path.OutRec();
        outRec.Idx = -1;
        outRec.isHole = false;
        outRec.isOpen = false;
        outRec.firstLeft = null;
        outRec.setPoints((Path.OutPt) null);
        outRec.bottomPt = null;
        outRec.polyNode = null;
        this.polyOuts.add(outRec);
        outRec.Idx = this.polyOuts.size() - 1;
        return outRec;
    }

    private void deleteFromAEL(Edge edge) {
        LOGGER.entering(DefaultClipper.class.getName(), "deleteFromAEL");
        Edge edge2 = edge.prevInAEL;
        Edge edge3 = edge.nextInAEL;
        if (edge2 != null || edge3 != null || edge == this.activeEdges) {
            if (edge2 != null) {
                edge2.nextInAEL = edge3;
            } else {
                this.activeEdges = edge3;
            }
            if (edge3 != null) {
                edge3.prevInAEL = edge2;
            }
            edge.nextInAEL = null;
            edge.prevInAEL = null;
            LOGGER.exiting(DefaultClipper.class.getName(), "deleteFromAEL");
        }
    }

    private void deleteFromSEL(Edge edge) {
        LOGGER.entering(DefaultClipper.class.getName(), "deleteFromSEL");
        Edge edge2 = edge.prevInSEL;
        Edge edge3 = edge.nextInSEL;
        if (edge2 != null || edge3 != null || edge.equals(this.sortedEdges)) {
            if (edge2 != null) {
                edge2.nextInSEL = edge3;
            } else {
                this.sortedEdges = edge3;
            }
            if (edge3 != null) {
                edge3.prevInSEL = edge2;
            }
            edge.nextInSEL = null;
            edge.prevInSEL = null;
        }
    }

    private void doMaxima(Edge edge) {
        Edge maximaPair = edge.getMaximaPair();
        if (maximaPair == null) {
            if (edge.outIdx >= 0) {
                addOutPt(edge, edge.getTop());
            }
            deleteFromAEL(edge);
            return;
        }
        Edge edge2 = edge.nextInAEL;
        while (edge2 != null && edge2 != maximaPair) {
            Point.LongPoint longPoint = new Point.LongPoint(edge.getTop());
            intersectEdges(edge, edge2, longPoint);
            edge.setTop(longPoint);
            swapPositionsInAEL(edge, edge2);
            edge2 = edge.nextInAEL;
        }
        if (edge.outIdx == -1 && maximaPair.outIdx == -1) {
            deleteFromAEL(edge);
            deleteFromAEL(maximaPair);
        } else if (edge.outIdx >= 0 && maximaPair.outIdx >= 0) {
            if (edge.outIdx >= 0) {
                addLocalMaxPoly(edge, maximaPair, edge.getTop());
            }
            deleteFromAEL(edge);
            deleteFromAEL(maximaPair);
        } else if (edge.windDelta == 0) {
            if (edge.outIdx >= 0) {
                addOutPt(edge, edge.getTop());
                edge.outIdx = -1;
            }
            deleteFromAEL(edge);
            if (maximaPair.outIdx >= 0) {
                addOutPt(maximaPair, edge.getTop());
                maximaPair.outIdx = -1;
            }
            deleteFromAEL(maximaPair);
        } else {
            throw new IllegalStateException("DoMaxima error");
        }
    }

    private void doSimplePolygons() {
        int i = 0;
        while (i < this.polyOuts.size()) {
            int i2 = i + 1;
            Path.OutRec outRec = this.polyOuts.get(i);
            Path.OutPt points = outRec.getPoints();
            if (points == null || outRec.isOpen) {
                i = i2;
            } else {
                do {
                    Path.OutPt outPt = points.next;
                    while (outPt != outRec.getPoints()) {
                        if (points.getPt().equals(outPt.getPt()) && !outPt.next.equals(points) && !outPt.prev.equals(points)) {
                            Path.OutPt outPt2 = points.prev;
                            Path.OutPt outPt3 = outPt.prev;
                            points.prev = outPt3;
                            outPt3.next = points;
                            outPt.prev = outPt2;
                            outPt2.next = outPt;
                            outRec.setPoints(points);
                            Path.OutRec createOutRec = createOutRec();
                            createOutRec.setPoints(outPt);
                            updateOutPtIdxs(createOutRec);
                            if (poly2ContainsPoly1(createOutRec.getPoints(), outRec.getPoints())) {
                                createOutRec.isHole = !outRec.isHole;
                                createOutRec.firstLeft = outRec;
                                if (this.usingPolyTree) {
                                    fixupFirstLefts2(createOutRec, outRec);
                                }
                            } else if (poly2ContainsPoly1(outRec.getPoints(), createOutRec.getPoints())) {
                                createOutRec.isHole = outRec.isHole;
                                outRec.isHole = !createOutRec.isHole;
                                createOutRec.firstLeft = outRec.firstLeft;
                                outRec.firstLeft = createOutRec;
                                if (this.usingPolyTree) {
                                    fixupFirstLefts2(outRec, createOutRec);
                                }
                            } else {
                                createOutRec.isHole = outRec.isHole;
                                createOutRec.firstLeft = outRec.firstLeft;
                                if (this.usingPolyTree) {
                                    fixupFirstLefts1(outRec, createOutRec);
                                }
                            }
                            outPt = points;
                        }
                        outPt = outPt.next;
                    }
                    points = points.next;
                } while (points != outRec.getPoints());
                i = i2;
            }
        }
    }

    private boolean EdgesAdjacent(IntersectNode intersectNode) {
        return intersectNode.edge1.nextInSEL == intersectNode.Edge2 || intersectNode.edge1.prevInSEL == intersectNode.Edge2;
    }

    public boolean execute(Clipper.ClipType clipType2, Paths paths, Clipper.PolyFillType polyFillType) {
        return execute(clipType2, paths, polyFillType, polyFillType);
    }

    public boolean execute(Clipper.ClipType clipType2, PolyTree polyTree) {
        return execute(clipType2, polyTree, Clipper.PolyFillType.EVEN_ODD);
    }

    public boolean execute(Clipper.ClipType clipType2, PolyTree polyTree, Clipper.PolyFillType polyFillType) {
        return execute(clipType2, polyTree, polyFillType, polyFillType);
    }

    public boolean execute(Clipper.ClipType clipType2, Paths paths) {
        return execute(clipType2, paths, Clipper.PolyFillType.EVEN_ODD);
    }

    public boolean execute(Clipper.ClipType clipType2, Paths paths, Clipper.PolyFillType polyFillType, Clipper.PolyFillType polyFillType2) {
        boolean executeInternal;
        synchronized (this) {
            if (this.hasOpenPaths) {
                throw new IllegalStateException("Error: PolyTree struct is needed for open path clipping.");
            }
            paths.clear();
            this.subjFillType = polyFillType;
            this.clipFillType = polyFillType2;
            this.clipType = clipType2;
            this.usingPolyTree = false;
            try {
                executeInternal = executeInternal();
                if (executeInternal) {
                    buildResult(paths);
                }
            } finally {
                this.polyOuts.clear();
            }
        }
        return executeInternal;
    }

    public boolean execute(Clipper.ClipType clipType2, PolyTree polyTree, Clipper.PolyFillType polyFillType, Clipper.PolyFillType polyFillType2) {
        boolean executeInternal;
        synchronized (this) {
            this.subjFillType = polyFillType;
            this.clipFillType = polyFillType2;
            this.clipType = clipType2;
            this.usingPolyTree = true;
            try {
                executeInternal = executeInternal();
                if (executeInternal) {
                    buildResult2(polyTree);
                }
            } finally {
                this.polyOuts.clear();
            }
        }
        return executeInternal;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (r0 >= r10.polyOuts.size()) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        r2 = r10.polyOuts.get(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        if (r2.pts == null) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0055, code lost:
        if (r2.isOpen == false) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        r4 = r2.isHole ^ r10.reverseSolution;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0065, code lost:
        if (r2.area() <= 0.0d) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0069, code lost:
        if (r4 != r3) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006b, code lost:
        r2.getPoints().reversePolyPtLinks();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0072, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0075, code lost:
        joinCommonEdges();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x007e, code lost:
        if (r1 >= r10.polyOuts.size()) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0080, code lost:
        r0 = r10.polyOuts.get(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008c, code lost:
        if (r0.getPoints() != null) goto L_0x008f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0091, code lost:
        if (r0.isOpen == false) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0093, code lost:
        fixupOutPolyline(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0097, code lost:
        fixupOutPolygon(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009a, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009f, code lost:
        if (r10.strictlySimple == false) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a1, code lost:
        doSimplePolygons();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a4, code lost:
        r10.joins.clear();
        r10.ghostJoins.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ae, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean executeInternal() {
        /*
            r10 = this;
            r10.reset()     // Catch:{ all -> 0x00af }
            com.itextpdf.text.pdf.parser.clipper.ClipperBase$LocalMinima r0 = r10.currentLM     // Catch:{ all -> 0x00af }
            r1 = 0
            if (r0 != 0) goto L_0x0013
        L_0x0008:
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$Join> r0 = r10.joins
            r0.clear()
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$Join> r0 = r10.ghostJoins
            r0.clear()
            return r1
        L_0x0013:
            long r2 = r10.popScanbeam()     // Catch:{ all -> 0x00af }
        L_0x0017:
            r10.insertLocalMinimaIntoAEL(r2)     // Catch:{ all -> 0x00af }
            r10.processHorizontals()     // Catch:{ all -> 0x00af }
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$Join> r0 = r10.ghostJoins     // Catch:{ all -> 0x00af }
            r0.clear()     // Catch:{ all -> 0x00af }
            com.itextpdf.text.pdf.parser.clipper.ClipperBase$Scanbeam r0 = r10.scanbeam     // Catch:{ all -> 0x00af }
            if (r0 != 0) goto L_0x0027
            goto L_0x003d
        L_0x0027:
            long r2 = r10.popScanbeam()     // Catch:{ all -> 0x00af }
            boolean r0 = r10.processIntersections(r2)     // Catch:{ all -> 0x00af }
            if (r0 != 0) goto L_0x0032
            goto L_0x0008
        L_0x0032:
            r10.processEdgesAtTopOfScanbeam(r2)     // Catch:{ all -> 0x00af }
            com.itextpdf.text.pdf.parser.clipper.ClipperBase$Scanbeam r0 = r10.scanbeam     // Catch:{ all -> 0x00af }
            if (r0 != 0) goto L_0x0017
            com.itextpdf.text.pdf.parser.clipper.ClipperBase$LocalMinima r0 = r10.currentLM     // Catch:{ all -> 0x00af }
            if (r0 != 0) goto L_0x0017
        L_0x003d:
            r0 = r1
        L_0x003e:
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$OutRec> r2 = r10.polyOuts     // Catch:{ all -> 0x00af }
            int r2 = r2.size()     // Catch:{ all -> 0x00af }
            r3 = 1
            if (r0 >= r2) goto L_0x0075
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$OutRec> r2 = r10.polyOuts     // Catch:{ all -> 0x00af }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x00af }
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r2 = (com.itextpdf.text.pdf.parser.clipper.Path.OutRec) r2     // Catch:{ all -> 0x00af }
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r4 = r2.pts     // Catch:{ all -> 0x00af }
            if (r4 == 0) goto L_0x0072
            boolean r4 = r2.isOpen     // Catch:{ all -> 0x00af }
            if (r4 == 0) goto L_0x0058
            goto L_0x0072
        L_0x0058:
            boolean r4 = r2.isHole     // Catch:{ all -> 0x00af }
            boolean r5 = r10.reverseSolution     // Catch:{ all -> 0x00af }
            r4 = r4 ^ r5
            double r5 = r2.area()     // Catch:{ all -> 0x00af }
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0068
            goto L_0x0069
        L_0x0068:
            r3 = r1
        L_0x0069:
            if (r4 != r3) goto L_0x0072
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r2.getPoints()     // Catch:{ all -> 0x00af }
            r2.reversePolyPtLinks()     // Catch:{ all -> 0x00af }
        L_0x0072:
            int r0 = r0 + 1
            goto L_0x003e
        L_0x0075:
            r10.joinCommonEdges()     // Catch:{ all -> 0x00af }
        L_0x0078:
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$OutRec> r0 = r10.polyOuts     // Catch:{ all -> 0x00af }
            int r0 = r0.size()     // Catch:{ all -> 0x00af }
            if (r1 >= r0) goto L_0x009d
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$OutRec> r0 = r10.polyOuts     // Catch:{ all -> 0x00af }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x00af }
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r0 = (com.itextpdf.text.pdf.parser.clipper.Path.OutRec) r0     // Catch:{ all -> 0x00af }
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r0.getPoints()     // Catch:{ all -> 0x00af }
            if (r2 != 0) goto L_0x008f
            goto L_0x009a
        L_0x008f:
            boolean r2 = r0.isOpen     // Catch:{ all -> 0x00af }
            if (r2 == 0) goto L_0x0097
            r10.fixupOutPolyline(r0)     // Catch:{ all -> 0x00af }
            goto L_0x009a
        L_0x0097:
            r10.fixupOutPolygon(r0)     // Catch:{ all -> 0x00af }
        L_0x009a:
            int r1 = r1 + 1
            goto L_0x0078
        L_0x009d:
            boolean r0 = r10.strictlySimple     // Catch:{ all -> 0x00af }
            if (r0 == 0) goto L_0x00a4
            r10.doSimplePolygons()     // Catch:{ all -> 0x00af }
        L_0x00a4:
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$Join> r0 = r10.joins
            r0.clear()
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$Join> r0 = r10.ghostJoins
            r0.clear()
            return r3
        L_0x00af:
            r0 = move-exception
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$Join> r1 = r10.joins
            r1.clear()
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$Join> r1 = r10.ghostJoins
            r1.clear()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.parser.clipper.DefaultClipper.executeInternal():boolean");
    }

    private void fixupFirstLefts1(Path.OutRec outRec, Path.OutRec outRec2) {
        for (int i = 0; i < this.polyOuts.size(); i++) {
            Path.OutRec outRec3 = this.polyOuts.get(i);
            if (outRec3.getPoints() != null && outRec3.firstLeft != null && parseFirstLeft(outRec3.firstLeft).equals(outRec) && poly2ContainsPoly1(outRec3.getPoints(), outRec2.getPoints())) {
                outRec3.firstLeft = outRec2;
            }
        }
    }

    private void fixupFirstLefts2(Path.OutRec outRec, Path.OutRec outRec2) {
        for (Path.OutRec next : this.polyOuts) {
            if (next.firstLeft == outRec) {
                next.firstLeft = outRec2;
            }
        }
    }

    private boolean fixupIntersectionOrder() {
        Collections.sort(this.intersectList, this.intersectNodeComparer);
        copyAELToSEL();
        int size = this.intersectList.size();
        for (int i = 0; i < size; i++) {
            if (!EdgesAdjacent(this.intersectList.get(i))) {
                int i2 = i + 1;
                while (i2 < size && !EdgesAdjacent(this.intersectList.get(i2))) {
                    i2++;
                }
                if (i2 == size) {
                    return false;
                }
                this.intersectList.set(i, this.intersectList.get(i2));
                this.intersectList.set(i2, this.intersectList.get(i));
            }
            swapPositionsInSEL(this.intersectList.get(i).edge1, this.intersectList.get(i).Edge2);
        }
        return true;
    }

    private void fixupOutPolyline(Path.OutRec outRec) {
        Path.OutPt outPt = outRec.pts;
        Path.OutPt outPt2 = outPt.prev;
        while (outPt != outPt2) {
            outPt = outPt.next;
            if (outPt.pt.equals(outPt.prev.pt)) {
                if (outPt == outPt2) {
                    outPt2 = outPt.prev;
                }
                Path.OutPt outPt3 = outPt.prev;
                outPt3.next = outPt.next;
                outPt.next.prev = outPt3;
                outPt = outPt3;
            }
        }
        if (outPt == outPt.prev) {
            outRec.pts = null;
        }
    }

    private void fixupOutPolygon(Path.OutRec outRec) {
        outRec.bottomPt = null;
        Path.OutPt points = outRec.getPoints();
        boolean z = this.preserveCollinear || this.strictlySimple;
        loop0:
        while (true) {
            Path.OutPt outPt = null;
            while (points.prev != points && points.prev != points.next) {
                if (points.getPt().equals(points.next.getPt()) || points.getPt().equals(points.prev.getPt()) || (Point.slopesEqual(points.prev.getPt(), points.getPt(), points.next.getPt(), this.useFullRange) && (!z || !Point.isPt2BetweenPt1AndPt3(points.prev.getPt(), points.getPt(), points.next.getPt())))) {
                    points.prev.next = points.next;
                    points.next.prev = points.prev;
                    points = points.prev;
                } else if (points == outPt) {
                    outRec.setPoints(points);
                    return;
                } else {
                    if (outPt == null) {
                        outPt = points;
                    }
                    points = points.next;
                }
            }
        }
        outRec.setPoints((Path.OutPt) null);
    }

    private Path.OutRec getOutRec(int i) {
        Object obj = this.polyOuts.get(i);
        while (true) {
            Path.OutRec outRec = (Path.OutRec) obj;
            if (outRec == this.polyOuts.get(outRec.Idx)) {
                return outRec;
            }
            obj = this.polyOuts.get(outRec.Idx);
        }
    }

    private void insertEdgeIntoAEL(Edge edge, Edge edge2) {
        LOGGER.entering(DefaultClipper.class.getName(), "insertEdgeIntoAEL");
        if (this.activeEdges == null) {
            edge.prevInAEL = null;
            edge.nextInAEL = null;
            Logger logger = LOGGER;
            logger.finest("Edge " + edge.outIdx + " -> " + null);
            this.activeEdges = edge;
        } else if (edge2 != null || !Edge.doesE2InsertBeforeE1(this.activeEdges, edge)) {
            LOGGER.finest("activeEdges unchanged");
            if (edge2 == null) {
                edge2 = this.activeEdges;
            }
            while (edge2.nextInAEL != null && !Edge.doesE2InsertBeforeE1(edge2.nextInAEL, edge)) {
                edge2 = edge2.nextInAEL;
            }
            edge.nextInAEL = edge2.nextInAEL;
            if (edge2.nextInAEL != null) {
                edge2.nextInAEL.prevInAEL = edge;
            }
            edge.prevInAEL = edge2;
            edge2.nextInAEL = edge;
        } else {
            edge.prevInAEL = null;
            edge.nextInAEL = this.activeEdges;
            Logger logger2 = LOGGER;
            logger2.finest("Edge " + edge.outIdx + " -> " + edge.nextInAEL.outIdx);
            this.activeEdges.prevInAEL = edge;
            this.activeEdges = edge;
        }
    }

    private void insertLocalMinimaIntoAEL(long j) {
        LOGGER.entering(DefaultClipper.class.getName(), "insertLocalMinimaIntoAEL");
        while (this.currentLM != null && this.currentLM.y == j) {
            Edge edge = this.currentLM.leftBound;
            Edge edge2 = this.currentLM.rightBound;
            popLocalMinima();
            Path.OutPt outPt = null;
            if (edge == null) {
                insertEdgeIntoAEL(edge2, (Edge) null);
                updateWindingCount(edge2);
                if (edge2.isContributing(this.clipFillType, this.subjFillType, this.clipType)) {
                    outPt = addOutPt(edge2, edge2.getBot());
                }
            } else if (edge2 == null) {
                insertEdgeIntoAEL(edge, (Edge) null);
                updateWindingCount(edge);
                if (edge.isContributing(this.clipFillType, this.subjFillType, this.clipType)) {
                    outPt = addOutPt(edge, edge.getBot());
                }
                insertScanbeam(edge.getTop().getY());
            } else {
                insertEdgeIntoAEL(edge, (Edge) null);
                insertEdgeIntoAEL(edge2, edge);
                updateWindingCount(edge);
                edge2.windCnt = edge.windCnt;
                edge2.windCnt2 = edge.windCnt2;
                if (edge.isContributing(this.clipFillType, this.subjFillType, this.clipType)) {
                    outPt = addLocalMinPoly(edge, edge2, edge.getBot());
                }
                insertScanbeam(edge.getTop().getY());
            }
            Path.OutPt outPt2 = outPt;
            if (edge2 != null) {
                if (edge2.isHorizontal()) {
                    addEdgeToSEL(edge2);
                } else {
                    insertScanbeam(edge2.getTop().getY());
                }
            }
            if (!(edge == null || edge2 == null)) {
                if (outPt2 != null && edge2.isHorizontal() && this.ghostJoins.size() > 0 && edge2.windDelta != 0) {
                    for (int i = 0; i < this.ghostJoins.size(); i++) {
                        Path.Join join = this.ghostJoins.get(i);
                        Path.Join join2 = join;
                        if (doHorzSegmentsOverlap(join.outPt1.getPt().getX(), join.getOffPt().getX(), edge2.getBot().getX(), edge2.getTop().getX())) {
                            addJoin(join2.outPt1, outPt2, join2.getOffPt());
                        }
                    }
                }
                if (edge.outIdx >= 0 && edge.prevInAEL != null && edge.prevInAEL.getCurrent().getX() == edge.getBot().getX() && edge.prevInAEL.outIdx >= 0 && Edge.slopesEqual(edge.prevInAEL, edge, this.useFullRange) && edge.windDelta != 0 && edge.prevInAEL.windDelta != 0) {
                    addJoin(outPt2, addOutPt(edge.prevInAEL, edge.getBot()), edge.getTop());
                }
                if (edge.nextInAEL != edge2) {
                    if (edge2.outIdx >= 0 && edge2.prevInAEL.outIdx >= 0 && Edge.slopesEqual(edge2.prevInAEL, edge2, this.useFullRange) && edge2.windDelta != 0 && edge2.prevInAEL.windDelta != 0) {
                        addJoin(outPt2, addOutPt(edge2.prevInAEL, edge2.getBot()), edge2.getTop());
                    }
                    Edge edge3 = edge.nextInAEL;
                    if (edge3 != null) {
                        while (edge3 != edge2) {
                            intersectEdges(edge2, edge3, edge.getCurrent());
                            edge3 = edge3.nextInAEL;
                        }
                    }
                }
            }
        }
    }

    private void intersectEdges(Edge edge, Edge edge2, Point.LongPoint longPoint) {
        Clipper.PolyFillType polyFillType;
        Clipper.PolyFillType polyFillType2;
        Clipper.PolyFillType polyFillType3;
        Clipper.PolyFillType polyFillType4;
        int i;
        int i2;
        int i3;
        int i4;
        LOGGER.entering(DefaultClipper.class.getName(), "insersectEdges");
        int i5 = 0;
        boolean z = edge.outIdx >= 0;
        boolean z2 = edge2.outIdx >= 0;
        setZ(longPoint, edge, edge2);
        if (edge.windDelta != 0 && edge2.windDelta != 0) {
            if (edge.polyTyp != edge2.polyTyp) {
                if (!edge2.isEvenOddFillType(this.clipFillType, this.subjFillType)) {
                    edge.windCnt2 += edge2.windDelta;
                } else {
                    edge.windCnt2 = edge.windCnt2 == 0 ? 1 : 0;
                }
                if (!edge.isEvenOddFillType(this.clipFillType, this.subjFillType)) {
                    edge2.windCnt2 -= edge.windDelta;
                } else {
                    if (edge2.windCnt2 == 0) {
                        i5 = 1;
                    }
                    edge2.windCnt2 = i5;
                }
            } else if (edge.isEvenOddFillType(this.clipFillType, this.subjFillType)) {
                int i6 = edge.windCnt;
                edge.windCnt = edge2.windCnt;
                edge2.windCnt = i6;
            } else {
                if (edge.windCnt + edge2.windDelta == 0) {
                    edge.windCnt = -edge.windCnt;
                } else {
                    edge.windCnt += edge2.windDelta;
                }
                if (edge2.windCnt - edge.windDelta == 0) {
                    edge2.windCnt = -edge2.windCnt;
                } else {
                    edge2.windCnt -= edge.windDelta;
                }
            }
            if (edge.polyTyp == Clipper.PolyType.SUBJECT) {
                polyFillType2 = this.subjFillType;
                polyFillType = this.clipFillType;
            } else {
                polyFillType2 = this.clipFillType;
                polyFillType = this.subjFillType;
            }
            if (edge2.polyTyp == Clipper.PolyType.SUBJECT) {
                polyFillType4 = this.subjFillType;
                polyFillType3 = this.clipFillType;
            } else {
                polyFillType4 = this.clipFillType;
                polyFillType3 = this.subjFillType;
            }
            switch (polyFillType2) {
                case POSITIVE:
                    i = edge.windCnt;
                    break;
                case NEGATIVE:
                    i = -edge.windCnt;
                    break;
                default:
                    i = Math.abs(edge.windCnt);
                    break;
            }
            switch (polyFillType4) {
                case POSITIVE:
                    i2 = edge2.windCnt;
                    break;
                case NEGATIVE:
                    i2 = -edge2.windCnt;
                    break;
                default:
                    i2 = Math.abs(edge2.windCnt);
                    break;
            }
            if (!z || !z2) {
                if (z) {
                    if (i2 == 0 || i2 == 1) {
                        addOutPt(edge, longPoint);
                        Edge.swapSides(edge, edge2);
                        Edge.swapPolyIndexes(edge, edge2);
                    }
                } else if (z2) {
                    if (i == 0 || i == 1) {
                        addOutPt(edge2, longPoint);
                        Edge.swapSides(edge, edge2);
                        Edge.swapPolyIndexes(edge, edge2);
                    }
                } else if (i != 0 && i != 1) {
                } else {
                    if (i2 == 0 || i2 == 1) {
                        switch (polyFillType) {
                            case POSITIVE:
                                i3 = edge.windCnt2;
                                break;
                            case NEGATIVE:
                                i3 = -edge.windCnt2;
                                break;
                            default:
                                i3 = Math.abs(edge.windCnt2);
                                break;
                        }
                        switch (polyFillType3) {
                            case POSITIVE:
                                i4 = edge2.windCnt2;
                                break;
                            case NEGATIVE:
                                i4 = -edge2.windCnt2;
                                break;
                            default:
                                i4 = Math.abs(edge2.windCnt2);
                                break;
                        }
                        if (edge.polyTyp != edge2.polyTyp) {
                            addLocalMinPoly(edge, edge2, longPoint);
                        } else if (i == 1 && i2 == 1) {
                            switch (this.clipType) {
                                case INTERSECTION:
                                    if (i3 > 0 && i4 > 0) {
                                        addLocalMinPoly(edge, edge2, longPoint);
                                        return;
                                    }
                                    return;
                                case UNION:
                                    if (i3 <= 0 && i4 <= 0) {
                                        addLocalMinPoly(edge, edge2, longPoint);
                                        return;
                                    }
                                    return;
                                case DIFFERENCE:
                                    if ((edge.polyTyp == Clipper.PolyType.CLIP && i3 > 0 && i4 > 0) || (edge.polyTyp == Clipper.PolyType.SUBJECT && i3 <= 0 && i4 <= 0)) {
                                        addLocalMinPoly(edge, edge2, longPoint);
                                        return;
                                    }
                                    return;
                                case XOR:
                                    addLocalMinPoly(edge, edge2, longPoint);
                                    return;
                                default:
                                    return;
                            }
                        } else {
                            Edge.swapSides(edge, edge2);
                        }
                    }
                }
            } else if ((i == 0 || i == 1) && ((i2 == 0 || i2 == 1) && (edge.polyTyp == edge2.polyTyp || this.clipType == Clipper.ClipType.XOR))) {
                addOutPt(edge, longPoint);
                addOutPt(edge2, longPoint);
                Edge.swapSides(edge, edge2);
                Edge.swapPolyIndexes(edge, edge2);
            } else {
                addLocalMaxPoly(edge, edge2, longPoint);
            }
        } else if (edge.windDelta != 0 || edge2.windDelta != 0) {
            if (edge.polyTyp == edge2.polyTyp && edge.windDelta != edge2.windDelta && this.clipType == Clipper.ClipType.UNION) {
                if (edge.windDelta == 0) {
                    if (z2) {
                        addOutPt(edge, longPoint);
                        if (z) {
                            edge.outIdx = -1;
                        }
                    }
                } else if (z) {
                    addOutPt(edge2, longPoint);
                    if (z2) {
                        edge2.outIdx = -1;
                    }
                }
            } else if (edge.polyTyp == edge2.polyTyp) {
            } else {
                if (edge.windDelta == 0 && Math.abs(edge2.windCnt) == 1 && (this.clipType != Clipper.ClipType.UNION || edge2.windCnt2 == 0)) {
                    addOutPt(edge, longPoint);
                    if (z) {
                        edge.outIdx = -1;
                    }
                } else if (edge2.windDelta != 0 || Math.abs(edge.windCnt) != 1) {
                } else {
                    if (this.clipType != Clipper.ClipType.UNION || edge.windCnt2 == 0) {
                        addOutPt(edge2, longPoint);
                        if (z2) {
                            edge2.outIdx = -1;
                        }
                    }
                }
            }
        }
    }

    private void intersectPoint(Edge edge, Edge edge2, Point.LongPoint[] longPointArr) {
        Point.LongPoint longPoint = new Point.LongPoint();
        longPointArr[0] = longPoint;
        if (edge.deltaX == edge2.deltaX) {
            longPoint.setY(Long.valueOf(edge.getCurrent().getY()));
            longPoint.setX(Long.valueOf(Edge.topX(edge, longPoint.getY())));
            return;
        }
        if (edge.getDelta().getX() == 0) {
            longPoint.setX(Long.valueOf(edge.getBot().getX()));
            if (edge2.isHorizontal()) {
                longPoint.setY(Long.valueOf(edge2.getBot().getY()));
            } else {
                longPoint.setY(Long.valueOf(Math.round((((double) longPoint.getX()) / edge2.deltaX) + (((double) edge2.getBot().getY()) - (((double) edge2.getBot().getX()) / edge2.deltaX)))));
            }
        } else if (edge2.getDelta().getX() == 0) {
            longPoint.setX(Long.valueOf(edge2.getBot().getX()));
            if (edge.isHorizontal()) {
                longPoint.setY(Long.valueOf(edge.getBot().getY()));
            } else {
                longPoint.setY(Long.valueOf(Math.round((((double) longPoint.getX()) / edge.deltaX) + (((double) edge.getBot().getY()) - (((double) edge.getBot().getX()) / edge.deltaX)))));
            }
        } else {
            double x = ((double) edge.getBot().getX()) - (((double) edge.getBot().getY()) * edge.deltaX);
            double x2 = ((double) edge2.getBot().getX()) - (((double) edge2.getBot().getY()) * edge2.deltaX);
            double d = (x2 - x) / (edge.deltaX - edge2.deltaX);
            longPoint.setY(Long.valueOf(Math.round(d)));
            if (Math.abs(edge.deltaX) < Math.abs(edge2.deltaX)) {
                longPoint.setX(Long.valueOf(Math.round((edge.deltaX * d) + x)));
            } else {
                longPoint.setX(Long.valueOf(Math.round((edge2.deltaX * d) + x2)));
            }
        }
        if (longPoint.getY() < edge.getTop().getY() || longPoint.getY() < edge2.getTop().getY()) {
            if (edge.getTop().getY() > edge2.getTop().getY()) {
                longPoint.setY(Long.valueOf(edge.getTop().getY()));
            } else {
                longPoint.setY(Long.valueOf(edge2.getTop().getY()));
            }
            if (Math.abs(edge.deltaX) < Math.abs(edge2.deltaX)) {
                longPoint.setX(Long.valueOf(Edge.topX(edge, longPoint.getY())));
            } else {
                longPoint.setX(Long.valueOf(Edge.topX(edge2, longPoint.getY())));
            }
        }
        if (longPoint.getY() > edge.getCurrent().getY()) {
            longPoint.setY(Long.valueOf(edge.getCurrent().getY()));
            if (Math.abs(edge.deltaX) > Math.abs(edge2.deltaX)) {
                longPoint.setX(Long.valueOf(Edge.topX(edge2, longPoint.getY())));
            } else {
                longPoint.setX(Long.valueOf(Edge.topX(edge, longPoint.getY())));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x014c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void joinCommonEdges() {
        /*
            r11 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$Join> r2 = r11.joins
            int r2 = r2.size()
            if (r1 >= r2) goto L_0x0150
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$Join> r2 = r11.joins
            java.lang.Object r2 = r2.get(r1)
            com.itextpdf.text.pdf.parser.clipper.Path$Join r2 = (com.itextpdf.text.pdf.parser.clipper.Path.Join) r2
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r3 = r2.outPt1
            int r3 = r3.idx
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r3 = r11.getOutRec(r3)
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r4 = r2.outPt2
            int r4 = r4.idx
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r4 = r11.getOutRec(r4)
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r5 = r3.getPoints()
            if (r5 == 0) goto L_0x014c
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r5 = r4.getPoints()
            if (r5 != 0) goto L_0x0030
            goto L_0x014c
        L_0x0030:
            boolean r5 = r3.isOpen
            if (r5 != 0) goto L_0x014c
            boolean r5 = r4.isOpen
            if (r5 == 0) goto L_0x003a
            goto L_0x014c
        L_0x003a:
            if (r3 != r4) goto L_0x003d
            goto L_0x004b
        L_0x003d:
            boolean r5 = isParam1RightOfParam2(r3, r4)
            if (r5 == 0) goto L_0x0045
            r5 = r4
            goto L_0x0051
        L_0x0045:
            boolean r5 = isParam1RightOfParam2(r4, r3)
            if (r5 == 0) goto L_0x004d
        L_0x004b:
            r5 = r3
            goto L_0x0051
        L_0x004d:
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r5 = com.itextpdf.text.pdf.parser.clipper.Path.OutPt.getLowerMostRec(r3, r4)
        L_0x0051:
            boolean r6 = r11.joinPoints(r2, r3, r4)
            if (r6 != 0) goto L_0x0059
            goto L_0x014c
        L_0x0059:
            r6 = 0
            if (r3 != r4) goto L_0x0130
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r4 = r2.outPt1
            r3.setPoints(r4)
            r3.bottomPt = r6
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r4 = r11.createOutRec()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r5 = r2.outPt2
            r4.setPoints(r5)
            r11.updateOutPtIdxs(r4)
            boolean r5 = r11.usingPolyTree
            r6 = 1
            if (r5 == 0) goto L_0x00ac
            r5 = r0
        L_0x0075:
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$OutRec> r7 = r11.polyOuts
            int r7 = r7.size()
            int r7 = r7 - r6
            if (r5 >= r7) goto L_0x00ac
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$OutRec> r7 = r11.polyOuts
            java.lang.Object r7 = r7.get(r5)
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r7 = (com.itextpdf.text.pdf.parser.clipper.Path.OutRec) r7
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r8 = r7.getPoints()
            if (r8 == 0) goto L_0x00a9
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r8 = r7.firstLeft
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r8 = parseFirstLeft(r8)
            if (r8 != r3) goto L_0x00a9
            boolean r8 = r7.isHole
            boolean r9 = r3.isHole
            if (r8 != r9) goto L_0x009b
            goto L_0x00a9
        L_0x009b:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r8 = r7.getPoints()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r9 = r2.outPt2
            boolean r8 = poly2ContainsPoly1(r8, r9)
            if (r8 == 0) goto L_0x00a9
            r7.firstLeft = r4
        L_0x00a9:
            int r5 = r5 + 1
            goto L_0x0075
        L_0x00ac:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r4.getPoints()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r5 = r3.getPoints()
            boolean r2 = poly2ContainsPoly1(r2, r5)
            r7 = 0
            if (r2 == 0) goto L_0x00e3
            boolean r2 = r3.isHole
            r2 = r2 ^ r6
            r4.isHole = r2
            r4.firstLeft = r3
            boolean r2 = r11.usingPolyTree
            if (r2 == 0) goto L_0x00ca
            r11.fixupFirstLefts2(r4, r3)
        L_0x00ca:
            boolean r2 = r4.isHole
            boolean r3 = r11.reverseSolution
            r2 = r2 ^ r3
            double r9 = r4.area()
            int r3 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x00d8
            goto L_0x00d9
        L_0x00d8:
            r6 = r0
        L_0x00d9:
            if (r2 != r6) goto L_0x014c
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r4.getPoints()
            r2.reversePolyPtLinks()
            goto L_0x014c
        L_0x00e3:
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r3.getPoints()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r5 = r4.getPoints()
            boolean r2 = poly2ContainsPoly1(r2, r5)
            if (r2 == 0) goto L_0x0120
            boolean r2 = r3.isHole
            r4.isHole = r2
            boolean r2 = r4.isHole
            r2 = r2 ^ r6
            r3.isHole = r2
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r2 = r3.firstLeft
            r4.firstLeft = r2
            r3.firstLeft = r4
            boolean r2 = r11.usingPolyTree
            if (r2 == 0) goto L_0x0107
            r11.fixupFirstLefts2(r3, r4)
        L_0x0107:
            boolean r2 = r3.isHole
            boolean r4 = r11.reverseSolution
            r2 = r2 ^ r4
            double r4 = r3.area()
            int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0115
            goto L_0x0116
        L_0x0115:
            r6 = r0
        L_0x0116:
            if (r2 != r6) goto L_0x014c
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r3.getPoints()
            r2.reversePolyPtLinks()
            goto L_0x014c
        L_0x0120:
            boolean r2 = r3.isHole
            r4.isHole = r2
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r2 = r3.firstLeft
            r4.firstLeft = r2
            boolean r2 = r11.usingPolyTree
            if (r2 == 0) goto L_0x014c
            r11.fixupFirstLefts1(r3, r4)
            goto L_0x014c
        L_0x0130:
            r4.setPoints(r6)
            r4.bottomPt = r6
            int r2 = r3.Idx
            r4.Idx = r2
            boolean r2 = r5.isHole
            r3.isHole = r2
            if (r5 != r4) goto L_0x0143
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r2 = r4.firstLeft
            r3.firstLeft = r2
        L_0x0143:
            r4.firstLeft = r3
            boolean r2 = r11.usingPolyTree
            if (r2 == 0) goto L_0x014c
            r11.fixupFirstLefts2(r4, r3)
        L_0x014c:
            int r1 = r1 + 1
            goto L_0x0002
        L_0x0150:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.parser.clipper.DefaultClipper.joinCommonEdges():void");
    }

    private long popScanbeam() {
        LOGGER.entering(DefaultClipper.class.getName(), "popBeam");
        long j = this.scanbeam.y;
        this.scanbeam = this.scanbeam.next;
        return j;
    }

    private void processEdgesAtTopOfScanbeam(long j) {
        LOGGER.entering(DefaultClipper.class.getName(), "processEdgesAtTopOfScanbeam");
        Edge edge = this.activeEdges;
        while (edge != null) {
            double d = (double) j;
            boolean isMaxima = edge.isMaxima(d);
            if (isMaxima) {
                Edge maximaPair = edge.getMaximaPair();
                isMaxima = maximaPair == null || !maximaPair.isHorizontal();
            }
            if (isMaxima) {
                if (this.strictlySimple) {
                    InsertMaxima(edge.getTop().getX());
                }
                Edge edge2 = edge.prevInAEL;
                doMaxima(edge);
                if (edge2 == null) {
                    edge = this.activeEdges;
                } else {
                    edge = edge2.nextInAEL;
                }
            } else {
                if (!edge.isIntermediate(d) || !edge.nextInLML.isHorizontal()) {
                    edge.getCurrent().setX(Long.valueOf(Edge.topX(edge, j)));
                    edge.getCurrent().setY(Long.valueOf(j));
                } else {
                    Edge[] edgeArr = {edge};
                    updateEdgeIntoAEL(edgeArr);
                    edge = edgeArr[0];
                    if (edge.outIdx >= 0) {
                        addOutPt(edge, edge.getBot());
                    }
                    addEdgeToSEL(edge);
                }
                if (this.strictlySimple) {
                    Edge edge3 = edge.prevInAEL;
                    if (edge.outIdx >= 0 && edge.windDelta != 0 && edge3 != null && edge3.outIdx >= 0 && edge3.getCurrent().getX() == edge.getCurrent().getX() && edge3.windDelta != 0) {
                        Point.LongPoint longPoint = new Point.LongPoint(edge.getCurrent());
                        setZ(longPoint, edge3, edge);
                        addJoin(addOutPt(edge3, longPoint), addOutPt(edge, longPoint), longPoint);
                    }
                }
                edge = edge.nextInAEL;
            }
        }
        processHorizontals();
        this.maxima = null;
        Edge edge4 = this.activeEdges;
        while (edge4 != null) {
            if (edge4.isIntermediate((double) j)) {
                Path.OutPt addOutPt = edge4.outIdx >= 0 ? addOutPt(edge4, edge4.getTop()) : null;
                Edge[] edgeArr2 = {edge4};
                updateEdgeIntoAEL(edgeArr2);
                edge4 = edgeArr2[0];
                Edge edge5 = edge4.prevInAEL;
                Edge edge6 = edge4.nextInAEL;
                if (edge5 != null && edge5.getCurrent().getX() == edge4.getBot().getX() && edge5.getCurrent().getY() == edge4.getBot().getY() && addOutPt != null && edge5.outIdx >= 0 && edge5.getCurrent().getY() > edge5.getTop().getY() && Edge.slopesEqual(edge4, edge5, this.useFullRange) && edge4.windDelta != 0 && edge5.windDelta != 0) {
                    addJoin(addOutPt, addOutPt(edge5, edge4.getBot()), edge4.getTop());
                } else if (edge6 != null && edge6.getCurrent().getX() == edge4.getBot().getX() && edge6.getCurrent().getY() == edge4.getBot().getY() && addOutPt != null && edge6.outIdx >= 0 && edge6.getCurrent().getY() > edge6.getTop().getY() && Edge.slopesEqual(edge4, edge6, this.useFullRange) && edge4.windDelta != 0 && edge6.windDelta != 0) {
                    addJoin(addOutPt, addOutPt(edge6, edge4.getBot()), edge4.getTop());
                }
            }
            edge4 = edge4.nextInAEL;
        }
        LOGGER.exiting(DefaultClipper.class.getName(), "processEdgesAtTopOfScanbeam");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: boolean} */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0078, code lost:
        if (r1.X >= r7.getBot().getX()) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009e, code lost:
        if (r1.X <= r7.getTop().getX()) goto L_0x00a0;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processHorizontal(com.itextpdf.text.pdf.parser.clipper.Edge r26) {
        /*
            r25 = this;
            r9 = r25
            r0 = r26
            java.util.logging.Logger r1 = LOGGER
            java.lang.Class<com.itextpdf.text.pdf.parser.clipper.DefaultClipper> r2 = com.itextpdf.text.pdf.parser.clipper.DefaultClipper.class
            java.lang.String r2 = r2.getName()
            java.lang.String r3 = "isHorizontal"
            r1.entering(r2, r3)
            r10 = 1
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction[] r11 = new com.itextpdf.text.pdf.parser.clipper.Clipper.Direction[r10]
            long[] r12 = new long[r10]
            long[] r13 = new long[r10]
            int r1 = r0.outIdx
            r14 = 0
            if (r1 < 0) goto L_0x002d
            java.util.List<com.itextpdf.text.pdf.parser.clipper.Path$OutRec> r1 = r9.polyOuts
            int r2 = r0.outIdx
            java.lang.Object r1 = r1.get(r2)
            com.itextpdf.text.pdf.parser.clipper.Path$OutRec r1 = (com.itextpdf.text.pdf.parser.clipper.Path.OutRec) r1
            boolean r1 = r1.isOpen
            if (r1 == 0) goto L_0x002d
            r15 = r10
            goto L_0x002e
        L_0x002d:
            r15 = r14
        L_0x002e:
            getHorzDirection(r0, r11, r12, r13)
            r7 = r0
        L_0x0032:
            com.itextpdf.text.pdf.parser.clipper.Edge r1 = r7.nextInLML
            if (r1 == 0) goto L_0x0041
            com.itextpdf.text.pdf.parser.clipper.Edge r1 = r7.nextInLML
            boolean r1 = r1.isHorizontal()
            if (r1 == 0) goto L_0x0041
            com.itextpdf.text.pdf.parser.clipper.Edge r7 = r7.nextInLML
            goto L_0x0032
        L_0x0041:
            com.itextpdf.text.pdf.parser.clipper.Edge r1 = r7.nextInLML
            r2 = 0
            if (r1 != 0) goto L_0x004c
            com.itextpdf.text.pdf.parser.clipper.Edge r1 = r7.getMaximaPair()
            r8 = r1
            goto L_0x004d
        L_0x004c:
            r8 = r2
        L_0x004d:
            com.itextpdf.text.pdf.parser.clipper.Path$Maxima r1 = r9.maxima
            if (r1 == 0) goto L_0x00a1
            r3 = r11[r14]
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r4 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.LEFT_TO_RIGHT
            if (r3 != r4) goto L_0x007b
        L_0x0057:
            if (r1 == 0) goto L_0x006a
            long r3 = r1.X
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r26.getBot()
            long r5 = r5.getX()
            int r16 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r16 > 0) goto L_0x006a
            com.itextpdf.text.pdf.parser.clipper.Path$Maxima r1 = r1.Next
            goto L_0x0057
        L_0x006a:
            if (r1 == 0) goto L_0x00a1
            long r3 = r1.X
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r7.getBot()
            long r5 = r5.getX()
            int r16 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r16 < 0) goto L_0x00a1
            goto L_0x00a0
        L_0x007b:
            com.itextpdf.text.pdf.parser.clipper.Path$Maxima r3 = r1.Next
            if (r3 == 0) goto L_0x0092
            com.itextpdf.text.pdf.parser.clipper.Path$Maxima r3 = r1.Next
            long r3 = r3.X
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r26.getBot()
            long r5 = r5.getX()
            int r16 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r16 >= 0) goto L_0x0092
            com.itextpdf.text.pdf.parser.clipper.Path$Maxima r1 = r1.Next
            goto L_0x007b
        L_0x0092:
            long r3 = r1.X
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r5 = r7.getTop()
            long r5 = r5.getX()
            int r16 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r16 > 0) goto L_0x00a1
        L_0x00a0:
            r1 = r2
        L_0x00a1:
            r5 = r0
        L_0x00a2:
            if (r5 != r7) goto L_0x00a7
            r16 = r10
            goto L_0x00a9
        L_0x00a7:
            r16 = r14
        L_0x00a9:
            r0 = r11[r14]
            com.itextpdf.text.pdf.parser.clipper.Edge r0 = r5.getNextInAEL(r0)
            r6 = r0
        L_0x00b0:
            if (r6 == 0) goto L_0x0273
            if (r1 == 0) goto L_0x0120
            r0 = r11[r14]
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r3 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.LEFT_TO_RIGHT
            if (r0 != r3) goto L_0x00f3
        L_0x00ba:
            if (r1 == 0) goto L_0x00ee
            long r3 = r1.X
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r6.getCurrent()
            long r17 = r0.getX()
            int r0 = (r3 > r17 ? 1 : (r3 == r17 ? 0 : -1))
            if (r0 >= 0) goto L_0x00ee
            int r0 = r5.outIdx
            if (r0 < 0) goto L_0x00e5
            if (r15 != 0) goto L_0x00e5
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = new com.itextpdf.text.pdf.parser.clipper.Point$LongPoint
            long r3 = r1.X
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r5.getBot()
            r19 = r15
            long r14 = r10.getY()
            r0.<init>((long) r3, (long) r14)
            r9.addOutPt(r5, r0)
            goto L_0x00e7
        L_0x00e5:
            r19 = r15
        L_0x00e7:
            com.itextpdf.text.pdf.parser.clipper.Path$Maxima r1 = r1.Next
            r15 = r19
            r10 = 1
            r14 = 0
            goto L_0x00ba
        L_0x00ee:
            r19 = r15
        L_0x00f0:
            r10 = r1
            r0 = 0
            goto L_0x0124
        L_0x00f3:
            r19 = r15
        L_0x00f5:
            if (r1 == 0) goto L_0x00f0
            long r3 = r1.X
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r6.getCurrent()
            long r14 = r0.getX()
            int r0 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x00f0
            int r0 = r5.outIdx
            if (r0 < 0) goto L_0x011d
            if (r19 != 0) goto L_0x011d
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = new com.itextpdf.text.pdf.parser.clipper.Point$LongPoint
            long r3 = r1.X
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r10 = r5.getBot()
            long r14 = r10.getY()
            r0.<init>((long) r3, (long) r14)
            r9.addOutPt(r5, r0)
        L_0x011d:
            com.itextpdf.text.pdf.parser.clipper.Path$Maxima r1 = r1.Prev
            goto L_0x00f5
        L_0x0120:
            r19 = r15
            r10 = r1
            r0 = r14
        L_0x0124:
            r1 = r11[r0]
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r3 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.LEFT_TO_RIGHT
            if (r1 != r3) goto L_0x0138
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r6.getCurrent()
            long r3 = r1.getX()
            r14 = r13[r0]
            int r1 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r1 > 0) goto L_0x016f
        L_0x0138:
            r1 = r11[r0]
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r3 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.RIGHT_TO_LEFT
            if (r1 != r3) goto L_0x014d
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r6.getCurrent()
            long r3 = r1.getX()
            r14 = r12[r0]
            int r0 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r0 >= 0) goto L_0x014d
            goto L_0x016f
        L_0x014d:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r6.getCurrent()
            long r0 = r0.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r5.getTop()
            long r3 = r3.getX()
            int r14 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r14 != 0) goto L_0x0179
            com.itextpdf.text.pdf.parser.clipper.Edge r0 = r5.nextInLML
            if (r0 == 0) goto L_0x0179
            double r0 = r6.deltaX
            com.itextpdf.text.pdf.parser.clipper.Edge r3 = r5.nextInLML
            double r3 = r3.deltaX
            int r14 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r14 >= 0) goto L_0x0179
        L_0x016f:
            r17 = r7
            r1 = r10
            r23 = r12
            r24 = r13
            r10 = r5
            goto L_0x027c
        L_0x0179:
            int r0 = r5.outIdx
            if (r0 < 0) goto L_0x01ff
            if (r19 != 0) goto L_0x01ff
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r6.getCurrent()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r14 = r9.addOutPt(r5, r0)
            com.itextpdf.text.pdf.parser.clipper.Edge r0 = r9.sortedEdges
            r15 = r0
        L_0x018a:
            if (r15 == 0) goto L_0x01eb
            int r0 = r15.outIdx
            if (r0 < 0) goto L_0x01d2
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r5.getBot()
            long r1 = r0.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r5.getTop()
            long r3 = r0.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r15.getBot()
            long r17 = r0.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r15.getTop()
            long r20 = r0.getX()
            r0 = r9
            r22 = r10
            r23 = r12
            r10 = r5
            r12 = r6
            r5 = r17
            r17 = r7
            r24 = r13
            r13 = r8
            r7 = r20
            boolean r0 = r0.doHorzSegmentsOverlap(r1, r3, r5, r7)
            if (r0 == 0) goto L_0x01dd
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r0 = r9.GetLastOutPt(r15)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r15.getTop()
            r9.addJoin(r0, r14, r1)
            goto L_0x01dd
        L_0x01d2:
            r17 = r7
            r22 = r10
            r23 = r12
            r24 = r13
            r10 = r5
            r12 = r6
            r13 = r8
        L_0x01dd:
            com.itextpdf.text.pdf.parser.clipper.Edge r15 = r15.nextInSEL
            r5 = r10
            r6 = r12
            r8 = r13
            r7 = r17
            r10 = r22
            r12 = r23
            r13 = r24
            goto L_0x018a
        L_0x01eb:
            r17 = r7
            r22 = r10
            r23 = r12
            r24 = r13
            r10 = r5
            r12 = r6
            r13 = r8
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r10.getBot()
            r9.addGhostJoin(r14, r0)
            r2 = r14
            goto L_0x020a
        L_0x01ff:
            r17 = r7
            r22 = r10
            r23 = r12
            r24 = r13
            r10 = r5
            r12 = r6
            r13 = r8
        L_0x020a:
            if (r12 != r13) goto L_0x0220
            if (r16 == 0) goto L_0x0220
            int r0 = r10.outIdx
            if (r0 < 0) goto L_0x0219
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r10.getTop()
            r9.addLocalMaxPoly(r10, r13, r0)
        L_0x0219:
            r9.deleteFromAEL(r10)
            r9.deleteFromAEL(r13)
            return
        L_0x0220:
            r0 = 0
            r1 = r11[r0]
            com.itextpdf.text.pdf.parser.clipper.Clipper$Direction r0 = com.itextpdf.text.pdf.parser.clipper.Clipper.Direction.LEFT_TO_RIGHT
            if (r1 != r0) goto L_0x0241
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = new com.itextpdf.text.pdf.parser.clipper.Point$LongPoint
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r12.getCurrent()
            long r3 = r1.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r10.getCurrent()
            long r5 = r1.getY()
            r0.<init>((long) r3, (long) r5)
            r9.intersectEdges(r10, r12, r0)
        L_0x023f:
            r0 = 0
            goto L_0x025a
        L_0x0241:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = new com.itextpdf.text.pdf.parser.clipper.Point$LongPoint
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r12.getCurrent()
            long r3 = r1.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r10.getCurrent()
            long r5 = r1.getY()
            r0.<init>((long) r3, (long) r5)
            r9.intersectEdges(r12, r10, r0)
            goto L_0x023f
        L_0x025a:
            r1 = r11[r0]
            com.itextpdf.text.pdf.parser.clipper.Edge r6 = r12.getNextInAEL(r1)
            r9.swapPositionsInAEL(r10, r12)
            r5 = r10
            r8 = r13
            r7 = r17
            r15 = r19
            r1 = r22
            r12 = r23
            r13 = r24
            r10 = 1
            r14 = 0
            goto L_0x00b0
        L_0x0273:
            r10 = r5
            r17 = r7
            r23 = r12
            r24 = r13
            r19 = r15
        L_0x027c:
            r13 = r8
            com.itextpdf.text.pdf.parser.clipper.Edge r0 = r10.nextInLML
            if (r0 == 0) goto L_0x02b2
            com.itextpdf.text.pdf.parser.clipper.Edge r0 = r10.nextInLML
            boolean r0 = r0.isHorizontal()
            if (r0 != 0) goto L_0x028a
            goto L_0x02b2
        L_0x028a:
            r0 = 1
            com.itextpdf.text.pdf.parser.clipper.Edge[] r3 = new com.itextpdf.text.pdf.parser.clipper.Edge[r0]
            r0 = 0
            r3[r0] = r10
            r9.updateEdgeIntoAEL(r3)
            r5 = r3[r0]
            int r0 = r5.outIdx
            if (r0 < 0) goto L_0x02a0
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r5.getBot()
            r9.addOutPt(r5, r0)
        L_0x02a0:
            r0 = r23
            r3 = r24
            getHorzDirection(r5, r11, r0, r3)
            r12 = r0
            r8 = r13
            r7 = r17
            r15 = r19
            r10 = 1
            r14 = 0
            r13 = r3
            goto L_0x00a2
        L_0x02b2:
            int r0 = r10.outIdx
            if (r0 < 0) goto L_0x0301
            if (r2 != 0) goto L_0x0301
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r11 = r9.GetLastOutPt(r10)
            com.itextpdf.text.pdf.parser.clipper.Edge r0 = r9.sortedEdges
            r12 = r0
        L_0x02bf:
            if (r12 == 0) goto L_0x02fa
            int r0 = r12.outIdx
            if (r0 < 0) goto L_0x02f7
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r10.getBot()
            long r1 = r0.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r10.getTop()
            long r3 = r0.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r12.getBot()
            long r5 = r0.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r12.getTop()
            long r7 = r0.getX()
            r0 = r9
            boolean r0 = r0.doHorzSegmentsOverlap(r1, r3, r5, r7)
            if (r0 == 0) goto L_0x02f7
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r0 = r9.GetLastOutPt(r12)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r12.getTop()
            r9.addJoin(r0, r11, r1)
        L_0x02f7:
            com.itextpdf.text.pdf.parser.clipper.Edge r12 = r12.nextInSEL
            goto L_0x02bf
        L_0x02fa:
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r10.getTop()
            r9.addGhostJoin(r11, r0)
        L_0x0301:
            com.itextpdf.text.pdf.parser.clipper.Edge r0 = r10.nextInLML
            if (r0 == 0) goto L_0x03ee
            int r0 = r10.outIdx
            if (r0 < 0) goto L_0x03e2
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r10.getTop()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r0 = r9.addOutPt(r10, r0)
            r1 = 1
            com.itextpdf.text.pdf.parser.clipper.Edge[] r1 = new com.itextpdf.text.pdf.parser.clipper.Edge[r1]
            r2 = 0
            r1[r2] = r10
            r9.updateEdgeIntoAEL(r1)
            r1 = r1[r2]
            int r2 = r1.windDelta
            if (r2 != 0) goto L_0x0321
            return
        L_0x0321:
            com.itextpdf.text.pdf.parser.clipper.Edge r2 = r1.prevInAEL
            com.itextpdf.text.pdf.parser.clipper.Edge r3 = r1.nextInAEL
            if (r2 == 0) goto L_0x0384
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r2.getCurrent()
            long r4 = r4.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r6 = r1.getBot()
            long r6 = r6.getX()
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0384
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r2.getCurrent()
            long r4 = r4.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r6 = r1.getBot()
            long r6 = r6.getY()
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0384
            int r4 = r2.windDelta
            if (r4 == 0) goto L_0x0384
            int r4 = r2.outIdx
            if (r4 < 0) goto L_0x0384
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r4 = r2.getCurrent()
            long r4 = r4.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r6 = r2.getTop()
            long r6 = r6.getY()
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0384
            boolean r4 = r9.useFullRange
            boolean r4 = com.itextpdf.text.pdf.parser.clipper.Edge.slopesEqual(r1, r2, r4)
            if (r4 == 0) goto L_0x0384
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r3 = r1.getBot()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r9.addOutPt(r2, r3)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r1.getTop()
            r9.addJoin(r0, r2, r1)
            goto L_0x03fc
        L_0x0384:
            if (r3 == 0) goto L_0x03fc
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r3.getCurrent()
            long r4 = r2.getX()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r1.getBot()
            long r6 = r2.getX()
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x03fc
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r3.getCurrent()
            long r4 = r2.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r1.getBot()
            long r6 = r2.getY()
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x03fc
            int r2 = r3.windDelta
            if (r2 == 0) goto L_0x03fc
            int r2 = r3.outIdx
            if (r2 < 0) goto L_0x03fc
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r3.getCurrent()
            long r4 = r2.getY()
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r3.getTop()
            long r6 = r2.getY()
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x03fc
            boolean r2 = r9.useFullRange
            boolean r2 = com.itextpdf.text.pdf.parser.clipper.Edge.slopesEqual(r1, r3, r2)
            if (r2 == 0) goto L_0x03fc
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r2 = r1.getBot()
            com.itextpdf.text.pdf.parser.clipper.Path$OutPt r2 = r9.addOutPt(r3, r2)
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r1 = r1.getTop()
            r9.addJoin(r0, r2, r1)
            goto L_0x03fc
        L_0x03e2:
            r0 = 1
            com.itextpdf.text.pdf.parser.clipper.Edge[] r0 = new com.itextpdf.text.pdf.parser.clipper.Edge[r0]
            r1 = 0
            r0[r1] = r10
            r9.updateEdgeIntoAEL(r0)
            r0 = r0[r1]
            goto L_0x03fc
        L_0x03ee:
            int r0 = r10.outIdx
            if (r0 < 0) goto L_0x03f9
            com.itextpdf.text.pdf.parser.clipper.Point$LongPoint r0 = r10.getTop()
            r9.addOutPt(r10, r0)
        L_0x03f9:
            r9.deleteFromAEL(r10)
        L_0x03fc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.parser.clipper.DefaultClipper.processHorizontal(com.itextpdf.text.pdf.parser.clipper.Edge):void");
    }

    private void processHorizontals() {
        LOGGER.entering(DefaultClipper.class.getName(), "processHorizontals");
        Edge edge = this.sortedEdges;
        while (edge != null) {
            deleteFromSEL(edge);
            processHorizontal(edge);
            edge = this.sortedEdges;
        }
    }

    private boolean processIntersections(long j) {
        LOGGER.entering(DefaultClipper.class.getName(), "processIntersections");
        if (this.activeEdges == null) {
            return true;
        }
        try {
            buildIntersectList(j);
            if (this.intersectList.size() == 0) {
                return true;
            }
            if (this.intersectList.size() != 1) {
                if (!fixupIntersectionOrder()) {
                    return false;
                }
            }
            processIntersectList();
            this.sortedEdges = null;
            return true;
        } catch (Exception e) {
            this.sortedEdges = null;
            this.intersectList.clear();
            throw new IllegalStateException("ProcessIntersections error", e);
        }
    }

    private void processIntersectList() {
        for (int i = 0; i < this.intersectList.size(); i++) {
            IntersectNode intersectNode = this.intersectList.get(i);
            intersectEdges(intersectNode.edge1, intersectNode.Edge2, intersectNode.getPt());
            swapPositionsInAEL(intersectNode.edge1, intersectNode.Edge2);
        }
        this.intersectList.clear();
    }

    /* access modifiers changed from: protected */
    public void reset() {
        super.reset();
        this.scanbeam = null;
        this.maxima = null;
        this.activeEdges = null;
        this.sortedEdges = null;
        for (ClipperBase.LocalMinima localMinima = this.minimaList; localMinima != null; localMinima = localMinima.next) {
            insertScanbeam(localMinima.y);
        }
    }

    private void setHoleState(Edge edge, Path.OutRec outRec) {
        boolean z = false;
        for (Edge edge2 = edge.prevInAEL; edge2 != null; edge2 = edge2.prevInAEL) {
            if (edge2.outIdx >= 0 && edge2.windDelta != 0) {
                z = !z;
                if (outRec.firstLeft == null) {
                    outRec.firstLeft = this.polyOuts.get(edge2.outIdx);
                }
            }
        }
        if (z) {
            outRec.isHole = true;
        }
    }

    private void setZ(Point.LongPoint longPoint, Edge edge, Edge edge2) {
        if (longPoint.getZ() == 0 && this.zFillFunction != null) {
            if (longPoint.equals(edge.getBot())) {
                longPoint.setZ(Long.valueOf(edge.getBot().getZ()));
            } else if (longPoint.equals(edge.getTop())) {
                longPoint.setZ(Long.valueOf(edge.getTop().getZ()));
            } else if (longPoint.equals(edge2.getBot())) {
                longPoint.setZ(Long.valueOf(edge2.getBot().getZ()));
            } else if (longPoint.equals(edge2.getTop())) {
                longPoint.setZ(Long.valueOf(edge2.getTop().getZ()));
            } else {
                this.zFillFunction.zFill(edge.getBot(), edge.getTop(), edge2.getBot(), edge2.getTop(), longPoint);
            }
        }
    }

    private void swapPositionsInAEL(Edge edge, Edge edge2) {
        LOGGER.entering(DefaultClipper.class.getName(), "swapPositionsInAEL");
        if (edge.nextInAEL != edge.prevInAEL && edge2.nextInAEL != edge2.prevInAEL) {
            if (edge.nextInAEL == edge2) {
                Edge edge3 = edge2.nextInAEL;
                if (edge3 != null) {
                    edge3.prevInAEL = edge;
                }
                Edge edge4 = edge.prevInAEL;
                if (edge4 != null) {
                    edge4.nextInAEL = edge2;
                }
                edge2.prevInAEL = edge4;
                edge2.nextInAEL = edge;
                edge.prevInAEL = edge2;
                edge.nextInAEL = edge3;
            } else if (edge2.nextInAEL == edge) {
                Edge edge5 = edge.nextInAEL;
                if (edge5 != null) {
                    edge5.prevInAEL = edge2;
                }
                Edge edge6 = edge2.prevInAEL;
                if (edge6 != null) {
                    edge6.nextInAEL = edge;
                }
                edge.prevInAEL = edge6;
                edge.nextInAEL = edge2;
                edge2.prevInAEL = edge;
                edge2.nextInAEL = edge5;
            } else {
                Edge edge7 = edge.nextInAEL;
                Edge edge8 = edge.prevInAEL;
                edge.nextInAEL = edge2.nextInAEL;
                if (edge.nextInAEL != null) {
                    edge.nextInAEL.prevInAEL = edge;
                }
                edge.prevInAEL = edge2.prevInAEL;
                if (edge.prevInAEL != null) {
                    edge.prevInAEL.nextInAEL = edge;
                }
                edge2.nextInAEL = edge7;
                if (edge2.nextInAEL != null) {
                    edge2.nextInAEL.prevInAEL = edge2;
                }
                edge2.prevInAEL = edge8;
                if (edge2.prevInAEL != null) {
                    edge2.prevInAEL.nextInAEL = edge2;
                }
            }
            if (edge.prevInAEL == null) {
                this.activeEdges = edge;
            } else if (edge2.prevInAEL == null) {
                this.activeEdges = edge2;
            }
            LOGGER.exiting(DefaultClipper.class.getName(), "swapPositionsInAEL");
        }
    }

    private void swapPositionsInSEL(Edge edge, Edge edge2) {
        if (edge.nextInSEL != null || edge.prevInSEL != null) {
            if (edge2.nextInSEL != null || edge2.prevInSEL != null) {
                if (edge.nextInSEL == edge2) {
                    Edge edge3 = edge2.nextInSEL;
                    if (edge3 != null) {
                        edge3.prevInSEL = edge;
                    }
                    Edge edge4 = edge.prevInSEL;
                    if (edge4 != null) {
                        edge4.nextInSEL = edge2;
                    }
                    edge2.prevInSEL = edge4;
                    edge2.nextInSEL = edge;
                    edge.prevInSEL = edge2;
                    edge.nextInSEL = edge3;
                } else if (edge2.nextInSEL == edge) {
                    Edge edge5 = edge.nextInSEL;
                    if (edge5 != null) {
                        edge5.prevInSEL = edge2;
                    }
                    Edge edge6 = edge2.prevInSEL;
                    if (edge6 != null) {
                        edge6.nextInSEL = edge;
                    }
                    edge.prevInSEL = edge6;
                    edge.nextInSEL = edge2;
                    edge2.prevInSEL = edge;
                    edge2.nextInSEL = edge5;
                } else {
                    Edge edge7 = edge.nextInSEL;
                    Edge edge8 = edge.prevInSEL;
                    edge.nextInSEL = edge2.nextInSEL;
                    if (edge.nextInSEL != null) {
                        edge.nextInSEL.prevInSEL = edge;
                    }
                    edge.prevInSEL = edge2.prevInSEL;
                    if (edge.prevInSEL != null) {
                        edge.prevInSEL.nextInSEL = edge;
                    }
                    edge2.nextInSEL = edge7;
                    if (edge2.nextInSEL != null) {
                        edge2.nextInSEL.prevInSEL = edge2;
                    }
                    edge2.prevInSEL = edge8;
                    if (edge2.prevInSEL != null) {
                        edge2.prevInSEL.nextInSEL = edge2;
                    }
                }
                if (edge.prevInSEL == null) {
                    this.sortedEdges = edge;
                } else if (edge2.prevInSEL == null) {
                    this.sortedEdges = edge2;
                }
            }
        }
    }

    private void updateEdgeIntoAEL(Edge[] edgeArr) {
        Edge edge = edgeArr[0];
        if (edge.nextInLML == null) {
            throw new IllegalStateException("UpdateEdgeIntoAEL: invalid call");
        }
        Edge edge2 = edge.prevInAEL;
        Edge edge3 = edge.nextInAEL;
        edge.nextInLML.outIdx = edge.outIdx;
        if (edge2 != null) {
            edge2.nextInAEL = edge.nextInLML;
        } else {
            this.activeEdges = edge.nextInLML;
        }
        if (edge3 != null) {
            edge3.prevInAEL = edge.nextInLML;
        }
        edge.nextInLML.side = edge.side;
        edge.nextInLML.windDelta = edge.windDelta;
        edge.nextInLML.windCnt = edge.windCnt;
        edge.nextInLML.windCnt2 = edge.windCnt2;
        Edge edge4 = edge.nextInLML;
        edgeArr[0] = edge4;
        edge4.setCurrent(edge4.getBot());
        edge4.prevInAEL = edge2;
        edge4.nextInAEL = edge3;
        if (!edge4.isHorizontal()) {
            insertScanbeam(edge4.getTop().getY());
        }
    }

    private void updateOutPtIdxs(Path.OutRec outRec) {
        Path.OutPt points = outRec.getPoints();
        do {
            points.idx = outRec.Idx;
            points = points.prev;
        } while (points != outRec.getPoints());
    }

    private void updateWindingCount(Edge edge) {
        Edge edge2;
        LOGGER.entering(DefaultClipper.class.getName(), "updateWindingCount");
        Edge edge3 = edge.prevInAEL;
        while (edge3 != null && (edge3.polyTyp != edge.polyTyp || edge3.windDelta == 0)) {
            edge3 = edge3.prevInAEL;
        }
        if (edge3 == null) {
            edge.windCnt = edge.windDelta == 0 ? 1 : edge.windDelta;
            edge.windCnt2 = 0;
            edge2 = this.activeEdges;
        } else if (edge.windDelta == 0 && this.clipType != Clipper.ClipType.UNION) {
            edge.windCnt = 1;
            edge.windCnt2 = edge3.windCnt2;
            edge2 = edge3.nextInAEL;
        } else if (edge.isEvenOddFillType(this.clipFillType, this.subjFillType)) {
            if (edge.windDelta == 0) {
                boolean z = true;
                for (Edge edge4 = edge3.prevInAEL; edge4 != null; edge4 = edge4.prevInAEL) {
                    if (edge4.polyTyp == edge3.polyTyp && edge4.windDelta != 0) {
                        z = !z;
                    }
                }
                edge.windCnt = z ? 0 : 1;
            } else {
                edge.windCnt = edge.windDelta;
            }
            edge.windCnt2 = edge3.windCnt2;
            edge2 = edge3.nextInAEL;
        } else {
            if (edge3.windCnt * edge3.windDelta < 0) {
                if (Math.abs(edge3.windCnt) <= 1) {
                    edge.windCnt = edge.windDelta == 0 ? 1 : edge.windDelta;
                } else if (edge3.windDelta * edge.windDelta < 0) {
                    edge.windCnt = edge3.windCnt;
                } else {
                    edge.windCnt = edge3.windCnt + edge.windDelta;
                }
            } else if (edge.windDelta == 0) {
                edge.windCnt = edge3.windCnt < 0 ? edge3.windCnt - 1 : edge3.windCnt + 1;
            } else if (edge3.windDelta * edge.windDelta < 0) {
                edge.windCnt = edge3.windCnt;
            } else {
                edge.windCnt = edge3.windCnt + edge.windDelta;
            }
            edge.windCnt2 = edge3.windCnt2;
            edge2 = edge3.nextInAEL;
        }
        if (edge.isEvenOddAltFillType(this.clipFillType, this.subjFillType)) {
            while (edge2 != edge) {
                if (edge2.windDelta != 0) {
                    edge.windCnt2 = edge.windCnt2 == 0 ? 1 : 0;
                }
                edge2 = edge2.nextInAEL;
            }
            return;
        }
        while (edge2 != edge) {
            edge.windCnt2 += edge2.windDelta;
            edge2 = edge2.nextInAEL;
        }
    }
}
