package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.itextpdf.text.xml.xmp.XmpBasicProperties;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser<DashManifest> {
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final String TAG = "MpdParser";
    private final String contentId;
    private final XmlPullParserFactory xmlParserFactory;

    /* access modifiers changed from: protected */
    public void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    public DashManifestParser() {
        this((String) null);
    }

    public DashManifestParser(String str) {
        this.contentId = str;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            newPullParser.setInput(inputStream, (String) null);
            if (newPullParser.next() == 2) {
                if ("MPD".equals(newPullParser.getName())) {
                    return parseMediaPresentationDescription(newPullParser, uri.toString());
                }
            }
            throw new ParserException("inputStream does not contain a valid media presentation description");
        } catch (XmlPullParserException e) {
            throw new ParserException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0168 A[LOOP:0: B:20:0x0063->B:63:0x0168, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x012c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.DashManifest parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser r33, java.lang.String r34) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r32 = this;
            r0 = r33
            java.lang.String r1 = "availabilityStartTime"
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r5 = parseDateTime(r0, r1, r2)
            java.lang.String r1 = "mediaPresentationDuration"
            long r7 = parseDuration(r0, r1, r2)
            java.lang.String r1 = "minBufferTime"
            long r9 = parseDuration(r0, r1, r2)
            java.lang.String r1 = "type"
            r4 = 0
            java.lang.String r1 = r0.getAttributeValue(r4, r1)
            r11 = 0
            if (r1 == 0) goto L_0x002d
            java.lang.String r13 = "dynamic"
            boolean r1 = r1.equals(r13)
            if (r1 == 0) goto L_0x002d
            r1 = 1
            goto L_0x002e
        L_0x002d:
            r1 = r11
        L_0x002e:
            if (r1 == 0) goto L_0x0037
            java.lang.String r13 = "minimumUpdatePeriod"
            long r13 = parseDuration(r0, r13, r2)
            goto L_0x0038
        L_0x0037:
            r13 = r2
        L_0x0038:
            if (r1 == 0) goto L_0x0041
            java.lang.String r15 = "timeShiftBufferDepth"
            long r15 = parseDuration(r0, r15, r2)
            goto L_0x0042
        L_0x0041:
            r15 = r2
        L_0x0042:
            if (r1 == 0) goto L_0x004b
            java.lang.String r4 = "suggestedPresentationDelay"
            long r17 = parseDuration(r0, r4, r2)
            goto L_0x004d
        L_0x004b:
            r17 = r2
        L_0x004d:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r1 == 0) goto L_0x0057
            r19 = r2
            goto L_0x0059
        L_0x0057:
            r19 = 0
        L_0x0059:
            r12 = r34
            r2 = r19
            r20 = 0
            r21 = 0
            r19 = r11
        L_0x0063:
            r33.next()
            r24 = r15
            java.lang.String r15 = "BaseURL"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x008a
            if (r11 != 0) goto L_0x007e
            java.lang.String r11 = parseBaseUrl(r0, r12)
            r15 = r32
            r12 = r11
            r29 = r13
            r11 = 1
            goto L_0x0124
        L_0x007e:
            r15 = r32
            r27 = r2
            r26 = r11
            r31 = r12
            r29 = r13
            goto L_0x011e
        L_0x008a:
            java.lang.String r15 = "UTCTiming"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x009e
            com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement r15 = r32.parseUtcTiming(r33)
            r29 = r13
            r21 = r15
        L_0x009a:
            r15 = r32
            goto L_0x0124
        L_0x009e:
            java.lang.String r15 = "Location"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x00b3
            java.lang.String r15 = r33.nextText()
            android.net.Uri r15 = android.net.Uri.parse(r15)
            r29 = r13
            r20 = r15
            goto L_0x009a
        L_0x00b3:
            java.lang.String r15 = "Period"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x007e
            if (r19 != 0) goto L_0x007e
            r15 = r32
            r26 = r11
            android.util.Pair r11 = r15.parsePeriod(r0, r12, r2)
            r27 = r2
            java.lang.Object r2 = r11.first
            com.google.android.exoplayer2.source.dash.manifest.Period r2 = (com.google.android.exoplayer2.source.dash.manifest.Period) r2
            r31 = r12
            r29 = r13
            long r12 = r2.startMs
            r22 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r12 > r22 ? 1 : (r12 == r22 ? 0 : -1))
            if (r3 != 0) goto L_0x0100
            if (r1 == 0) goto L_0x00e5
            r11 = r26
            r2 = r27
            r12 = r31
            r19 = 1
            goto L_0x0124
        L_0x00e5:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unable to determine start of period "
            r1.append(r2)
            int r2 = r4.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0100:
            java.lang.Object r3 = r11.second
            java.lang.Long r3 = (java.lang.Long) r3
            long r11 = r3.longValue()
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r3 != 0) goto L_0x0117
            r27 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x011b
        L_0x0117:
            long r13 = r2.startMs
            long r27 = r13 + r11
        L_0x011b:
            r4.add(r2)
        L_0x011e:
            r11 = r26
            r2 = r27
            r12 = r31
        L_0x0124:
            java.lang.String r13 = "MPD"
            boolean r13 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r0, r13)
            if (r13 == 0) goto L_0x0168
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r0 != 0) goto L_0x0145
            int r0 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x013b
            r7 = r2
            goto L_0x0145
        L_0x013b:
            if (r1 != 0) goto L_0x0145
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "Unable to determine duration of static manifest."
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0145:
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L_0x0153
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "No periods found."
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0153:
            r22 = r4
            r4 = r15
            r11 = r1
            r12 = r29
            r14 = r24
            r16 = r17
            r18 = r21
            r19 = r20
            r20 = r22
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = r4.buildMediaPresentationDescription(r5, r7, r9, r11, r12, r14, r16, r18, r19, r20)
            return r0
        L_0x0168:
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r15 = r24
            r13 = r29
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser, java.lang.String):com.google.android.exoplayer2.source.dash.manifest.DashManifest");
    }

    /* access modifiers changed from: protected */
    public DashManifest buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, long j6, UtcTimingElement utcTimingElement, Uri uri, List<Period> list) {
        return new DashManifest(j, j2, j3, z, j4, j5, j6, utcTimingElement, uri, list);
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue((String) null, "schemeIdUri"), xmlPullParser.getAttributeValue((String) null, FirebaseAnalytics.Param.VALUE));
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    /* access modifiers changed from: protected */
    public Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, String str, long j) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, TtmlNode.ATTR_ID);
        long parseDuration = parseDuration(xmlPullParser, TtmlNode.START, j);
        long parseDuration2 = parseDuration(xmlPullParser, "duration", C.TIME_UNSET);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        SegmentBase segmentBase = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, XmpBasicProperties.BASEURL)) {
                if (!z) {
                    str = parseBaseUrl(xmlPullParser, str);
                    z = true;
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "AdaptationSet")) {
                arrayList.add(parseAdaptationSet(xmlPullParser, str, segmentBase));
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                segmentBase = parseSegmentBase(xmlPullParser, (SegmentBase.SingleSegmentBase) null);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                segmentBase = parseSegmentList(xmlPullParser, (SegmentBase.SegmentList) null);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                segmentBase = parseSegmentTemplate(xmlPullParser, (SegmentBase.SegmentTemplate) null);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Period"));
        return Pair.create(buildPeriod(attributeValue, parseDuration, arrayList), Long.valueOf(parseDuration2));
    }

    /* access modifiers changed from: protected */
    public Period buildPeriod(String str, long j, List<AdaptationSet> list) {
        return new Period(str, j, list);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01e1 A[LOOP:0: B:1:0x005c->B:52:0x01e1, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01b3 A[EDGE_INSN: B:53:0x01b3->B:46:0x01b3 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.AdaptationSet parseAdaptationSet(org.xmlpull.v1.XmlPullParser r36, java.lang.String r37, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r38) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r35 = this;
            r14 = r35
            r15 = r36
            java.lang.String r0 = "id"
            r1 = -1
            int r13 = parseInt(r15, r0, r1)
            int r0 = r35.parseContentType(r36)
            java.lang.String r2 = "mimeType"
            r12 = 0
            java.lang.String r16 = r15.getAttributeValue(r12, r2)
            java.lang.String r2 = "codecs"
            java.lang.String r17 = r15.getAttributeValue(r12, r2)
            java.lang.String r2 = "width"
            int r18 = parseInt(r15, r2, r1)
            java.lang.String r2 = "height"
            int r19 = parseInt(r15, r2, r1)
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r20 = parseFrameRate(r15, r2)
            java.lang.String r2 = "audioSamplingRate"
            int r21 = parseInt(r15, r2, r1)
            java.lang.String r2 = "lang"
            java.lang.String r2 = r15.getAttributeValue(r12, r2)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r22 = 0
            r7 = r37
            r26 = r38
            r5 = r0
            r25 = r1
            r6 = r2
            r23 = r22
            r24 = r23
        L_0x005c:
            r36.next()
            java.lang.String r0 = "BaseURL"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x0090
            if (r23 != 0) goto L_0x007e
            java.lang.String r0 = parseBaseUrl(r15, r7)
            r1 = 1
            r7 = r0
            r23 = r1
        L_0x0071:
            r2 = r8
            r30 = r9
            r4 = r10
            r32 = r11
            r33 = r12
            r34 = r13
            r1 = r15
            goto L_0x01ab
        L_0x007e:
            r3 = r5
            r27 = r6
            r28 = r7
            r2 = r8
            r30 = r9
            r4 = r10
            r32 = r11
            r33 = r12
            r34 = r13
            r1 = r15
            goto L_0x01a6
        L_0x0090:
            java.lang.String r0 = "ContentProtection"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x00a2
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r0 = r35.parseContentProtection(r36)
            if (r0 == 0) goto L_0x007e
            r11.add(r0)
            goto L_0x007e
        L_0x00a2:
            java.lang.String r0 = "ContentComponent"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x00be
            java.lang.String r0 = "lang"
            java.lang.String r0 = r15.getAttributeValue(r12, r0)
            java.lang.String r6 = checkLanguageConsistency(r6, r0)
            int r0 = r35.parseContentType(r36)
            int r0 = checkContentTypeConsistency(r5, r0)
            r5 = r0
            goto L_0x0071
        L_0x00be:
            java.lang.String r0 = "Role"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x00cd
            int r0 = r35.parseRole(r36)
            r24 = r24 | r0
            goto L_0x0071
        L_0x00cd:
            java.lang.String r0 = "AudioChannelConfiguration"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x00da
            int r25 = r35.parseAudioChannelConfiguration(r36)
            goto L_0x0071
        L_0x00da:
            java.lang.String r0 = "Accessibility"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x00ea
            com.google.android.exoplayer2.source.dash.manifest.SchemeValuePair r0 = r35.parseAccessibility(r36)
            r9.add(r0)
            goto L_0x007e
        L_0x00ea:
            java.lang.String r0 = "Representation"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r15, r0)
            if (r0 == 0) goto L_0x013a
            r0 = r14
            r1 = r15
            r2 = r7
            r3 = r16
            r4 = r17
            r15 = r5
            r5 = r18
            r27 = r6
            r6 = r19
            r28 = r7
            r7 = r20
            r29 = r8
            r8 = r25
            r30 = r9
            r9 = r21
            r31 = r10
            r10 = r27
            r32 = r11
            r11 = r24
            r33 = r12
            r12 = r30
            r34 = r13
            r13 = r26
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r0 = r0.parseRepresentation(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            com.google.android.exoplayer2.Format r1 = r0.format
            int r1 = r14.getContentType(r1)
            int r1 = checkContentTypeConsistency(r15, r1)
            r2 = r29
            r2.add(r0)
            r5 = r1
            r6 = r27
            r7 = r28
            r4 = r31
            r1 = r36
            goto L_0x01ab
        L_0x013a:
            r15 = r5
            r27 = r6
            r28 = r7
            r2 = r8
            r30 = r9
            r31 = r10
            r32 = r11
            r33 = r12
            r34 = r13
            java.lang.String r0 = "SegmentBase"
            r3 = r15
            r1 = r36
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x0167
            r0 = r26
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = r14.parseSegmentBase(r1, r0)
        L_0x015d:
            r26 = r0
            r5 = r3
            r6 = r27
            r7 = r28
            r4 = r31
            goto L_0x01ab
        L_0x0167:
            java.lang.String r0 = "SegmentList"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x0178
            r0 = r26
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = r14.parseSegmentList(r1, r0)
            goto L_0x015d
        L_0x0178:
            java.lang.String r0 = "SegmentTemplate"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x0189
            r0 = r26
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = r14.parseSegmentTemplate(r1, r0)
            goto L_0x015d
        L_0x0189:
            java.lang.String r0 = "InbandEventStream"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r1, r0)
            if (r0 == 0) goto L_0x019b
            com.google.android.exoplayer2.source.dash.manifest.SchemeValuePair r0 = r35.parseInbandEventStream(r36)
            r4 = r31
            r4.add(r0)
            goto L_0x01a6
        L_0x019b:
            r4 = r31
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r36)
            if (r0 == 0) goto L_0x01a6
            r35.parseAdaptationSetChild(r36)
        L_0x01a6:
            r5 = r3
            r6 = r27
            r7 = r28
        L_0x01ab:
            java.lang.String r0 = "AdaptationSet"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r1, r0)
            if (r0 == 0) goto L_0x01e1
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r2.size()
            r0.<init>(r1)
            r1 = r22
        L_0x01be:
            int r3 = r2.size()
            if (r1 >= r3) goto L_0x01d8
            java.lang.Object r3 = r2.get(r1)
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r3 = (com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo) r3
            java.lang.String r6 = r14.contentId
            r8 = r32
            com.google.android.exoplayer2.source.dash.manifest.Representation r3 = r14.buildRepresentation(r3, r6, r8, r4)
            r0.add(r3)
            int r1 = r1 + 1
            goto L_0x01be
        L_0x01d8:
            r9 = r30
            r3 = r34
            com.google.android.exoplayer2.source.dash.manifest.AdaptationSet r0 = r14.buildAdaptationSet(r3, r5, r0, r9)
            return r0
        L_0x01e1:
            r15 = r1
            r8 = r2
            r10 = r4
            r9 = r30
            r11 = r32
            r12 = r33
            r13 = r34
            goto L_0x005c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseAdaptationSet(org.xmlpull.v1.XmlPullParser, java.lang.String, com.google.android.exoplayer2.source.dash.manifest.SegmentBase):com.google.android.exoplayer2.source.dash.manifest.AdaptationSet");
    }

    /* access modifiers changed from: protected */
    public AdaptationSet buildAdaptationSet(int i, int i2, List<Representation> list, List<SchemeValuePair> list2) {
        return new AdaptationSet(i, i2, list, list2);
    }

    /* access modifiers changed from: protected */
    public int parseContentType(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if (MimeTypes.BASE_TYPE_AUDIO.equals(attributeValue)) {
            return 1;
        }
        if (MimeTypes.BASE_TYPE_VIDEO.equals(attributeValue)) {
            return 2;
        }
        if (MimeTypes.BASE_TYPE_TEXT.equals(attributeValue)) {
            return 3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int getContentType(Format format) {
        String str = format.sampleMimeType;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (MimeTypes.isVideo(str)) {
            return 2;
        }
        if (MimeTypes.isAudio(str)) {
            return 1;
        }
        if (mimeTypeIsRawText(str)) {
            return 3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public DrmInitData.SchemeData parseContentProtection(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean equals = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95".equals(xmlPullParser.getAttributeValue((String) null, "schemeIdUri"));
        byte[] bArr = null;
        UUID uuid = null;
        boolean z = false;
        do {
            xmlPullParser.next();
            if (bArr == null && XmlPullParserUtil.isStartTag(xmlPullParser, "cenc:pssh") && xmlPullParser.next() == 4) {
                bArr = Base64.decode(xmlPullParser.getText(), 0);
                uuid = PsshAtomUtil.parseUuid(bArr);
                if (uuid == null) {
                    Log.w(TAG, "Skipping malformed cenc:pssh data");
                    bArr = null;
                }
            } else if (bArr == null && equals && XmlPullParserUtil.isStartTag(xmlPullParser, "mspr:pro") && xmlPullParser.next() == 4) {
                bArr = PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(xmlPullParser.getText(), 0));
                uuid = C.PLAYREADY_UUID;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "widevine:license")) {
                String attributeValue = xmlPullParser.getAttributeValue((String) null, "robustness_level");
                z = attributeValue != null && attributeValue.startsWith("HW");
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "ContentProtection"));
        if (bArr != null) {
            return new DrmInitData.SchemeData(uuid, MimeTypes.VIDEO_MP4, bArr, z);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public SchemeValuePair parseInbandEventStream(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return parseSchemeValuePair(xmlPullParser, "InbandEventStream");
    }

    /* access modifiers changed from: protected */
    public SchemeValuePair parseAccessibility(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return parseSchemeValuePair(xmlPullParser, "Accessibility");
    }

    /* access modifiers changed from: protected */
    public int parseRole(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", (String) null);
        String parseString2 = parseString(xmlPullParser, FirebaseAnalytics.Param.VALUE, (String) null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Role"));
        return (!"urn:mpeg:dash:role:2011".equals(parseString) || !"main".equals(parseString2)) ? 0 : 1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00fa A[LOOP:0: B:1:0x0051->B:34:0x00fa, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d6 A[EDGE_INSN: B:35:0x00d6->B:29:0x00d6 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo parseRepresentation(org.xmlpull.v1.XmlPullParser r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, int r26, int r27, float r28, int r29, int r30, java.lang.String r31, int r32, java.util.List<com.google.android.exoplayer2.source.dash.manifest.SchemeValuePair> r33, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r34) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r21 = this;
            r13 = r21
            r0 = r22
            java.lang.String r1 = "id"
            r2 = 0
            java.lang.String r1 = r0.getAttributeValue(r2, r1)
            java.lang.String r2 = "bandwidth"
            r3 = -1
            int r8 = parseInt(r0, r2, r3)
            java.lang.String r2 = "mimeType"
            r3 = r24
            java.lang.String r2 = parseString(r0, r2, r3)
            java.lang.String r3 = "codecs"
            r4 = r25
            java.lang.String r12 = parseString(r0, r3, r4)
            java.lang.String r3 = "width"
            r4 = r26
            int r3 = parseInt(r0, r3, r4)
            java.lang.String r4 = "height"
            r5 = r27
            int r4 = parseInt(r0, r4, r5)
            r5 = r28
            float r5 = parseFrameRate(r0, r5)
            java.lang.String r6 = "audioSamplingRate"
            r7 = r30
            int r7 = parseInt(r0, r6, r7)
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            r6 = 0
            r9 = r23
            r11 = r29
            r10 = r34
        L_0x0051:
            r22.next()
            r20 = r11
            java.lang.String r11 = "BaseURL"
            boolean r11 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r11)
            if (r11 == 0) goto L_0x0069
            if (r6 != 0) goto L_0x00c8
            java.lang.String r6 = parseBaseUrl(r0, r9)
            r9 = 1
            r16 = r6
            r6 = r9
            goto L_0x00ca
        L_0x0069:
            java.lang.String r11 = "AudioChannelConfiguration"
            boolean r11 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r11)
            if (r11 == 0) goto L_0x007a
            int r11 = r21.parseAudioChannelConfiguration(r22)
            r16 = r9
            r17 = r10
            goto L_0x00ce
        L_0x007a:
            java.lang.String r11 = "SegmentBase"
            boolean r11 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r11)
            if (r11 == 0) goto L_0x0089
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r10 = r13.parseSegmentBase(r0, r10)
            goto L_0x00c8
        L_0x0089:
            java.lang.String r11 = "SegmentList"
            boolean r11 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r11)
            if (r11 == 0) goto L_0x0098
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r10 = r13.parseSegmentList(r0, r10)
            goto L_0x00c8
        L_0x0098:
            java.lang.String r11 = "SegmentTemplate"
            boolean r11 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r11)
            if (r11 == 0) goto L_0x00a7
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r10 = r13.parseSegmentTemplate(r0, r10)
            goto L_0x00c8
        L_0x00a7:
            java.lang.String r11 = "ContentProtection"
            boolean r11 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r11)
            if (r11 == 0) goto L_0x00b9
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r11 = r21.parseContentProtection(r22)
            if (r11 == 0) goto L_0x00c8
            r15.add(r11)
            goto L_0x00c8
        L_0x00b9:
            java.lang.String r11 = "InbandEventStream"
            boolean r11 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r11)
            if (r11 == 0) goto L_0x00c8
            com.google.android.exoplayer2.source.dash.manifest.SchemeValuePair r11 = r21.parseInbandEventStream(r22)
            r14.add(r11)
        L_0x00c8:
            r16 = r9
        L_0x00ca:
            r17 = r10
            r11 = r20
        L_0x00ce:
            java.lang.String r9 = "Representation"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r0, r9)
            if (r9 == 0) goto L_0x00fa
            r0 = r13
            r6 = r11
            r9 = r31
            r10 = r32
            r11 = r33
            com.google.android.exoplayer2.Format r0 = r0.buildFormat(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            if (r17 == 0) goto L_0x00e5
            goto L_0x00ec
        L_0x00e5:
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r1 = new com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase
            r1.<init>()
            r17 = r1
        L_0x00ec:
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r1 = new com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo
            r9 = r14
            r14 = r1
            r10 = r15
            r15 = r0
            r18 = r10
            r19 = r9
            r14.<init>(r15, r16, r17, r18, r19)
            return r1
        L_0x00fa:
            r9 = r16
            r10 = r17
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseRepresentation(org.xmlpull.v1.XmlPullParser, java.lang.String, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, int, java.util.List, com.google.android.exoplayer2.source.dash.manifest.SegmentBase):com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo");
    }

    /* access modifiers changed from: protected */
    public Format buildFormat(String str, String str2, int i, int i2, float f, int i3, int i4, int i5, String str3, int i6, List<SchemeValuePair> list, String str4) {
        int parseCea708AccessibilityChannel;
        String str5 = str2;
        String str6 = str4;
        String sampleMimeType = getSampleMimeType(str5, str6);
        if (sampleMimeType != null) {
            if (MimeTypes.isVideo(sampleMimeType)) {
                return Format.createVideoContainerFormat(str, str5, sampleMimeType, str6, i5, i, i2, f, (List<byte[]>) null, i6);
            }
            if (MimeTypes.isAudio(sampleMimeType)) {
                return Format.createAudioContainerFormat(str, str5, sampleMimeType, str6, i5, i3, i4, (List<byte[]>) null, i6, str3);
            }
            if (mimeTypeIsRawText(sampleMimeType)) {
                if (MimeTypes.APPLICATION_CEA608.equals(sampleMimeType)) {
                    parseCea708AccessibilityChannel = parseCea608AccessibilityChannel(list);
                } else {
                    parseCea708AccessibilityChannel = MimeTypes.APPLICATION_CEA708.equals(sampleMimeType) ? parseCea708AccessibilityChannel(list) : -1;
                }
                return Format.createTextContainerFormat(str, str5, sampleMimeType, str6, i5, i6, str3, parseCea708AccessibilityChannel);
            }
        }
        return Format.createContainerFormat(str, str5, sampleMimeType, str6, i5, i6, str3);
    }

    /* access modifiers changed from: protected */
    public Representation buildRepresentation(RepresentationInfo representationInfo, String str, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<SchemeValuePair> arrayList2) {
        Format format = representationInfo.format;
        ArrayList<DrmInitData.SchemeData> arrayList3 = representationInfo.drmSchemeDatas;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            format = format.copyWithDrmInitData(new DrmInitData((List<DrmInitData.SchemeData>) arrayList3));
        }
        ArrayList<SchemeValuePair> arrayList4 = representationInfo.inbandEventStreams;
        arrayList4.addAll(arrayList2);
        return Representation.newInstance(str, -1, format, representationInfo.baseUrl, representationInfo.segmentBase, arrayList4);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, SegmentBase.SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j;
        long j2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SingleSegmentBase singleSegmentBase2 = singleSegmentBase;
        long parseLong = parseLong(xmlPullParser2, "timescale", singleSegmentBase2 != null ? singleSegmentBase2.timescale : 1);
        long j3 = 0;
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", singleSegmentBase2 != null ? singleSegmentBase2.presentationTimeOffset : 0);
        long j4 = singleSegmentBase2 != null ? singleSegmentBase2.indexStart : 0;
        if (singleSegmentBase2 != null) {
            j3 = singleSegmentBase2.indexLength;
        }
        RangedUri rangedUri = null;
        String attributeValue = xmlPullParser2.getAttributeValue((String) null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            j2 = Long.parseLong(split[0]);
            j = (Long.parseLong(split[1]) - j2) + 1;
        } else {
            j = j3;
            j2 = j4;
        }
        if (singleSegmentBase2 != null) {
            rangedUri = singleSegmentBase2.initialization;
        }
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentBase"));
        return buildSingleSegmentBase(rangedUri, parseLong, parseLong2, j2, j);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
        return new SegmentBase.SingleSegmentBase(rangedUri, j, j2, j3, j4);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentList parseSegmentList(XmlPullParser xmlPullParser, SegmentBase.SegmentList segmentList) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentList segmentList2 = segmentList;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentList2 != null ? segmentList2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentList2 != null ? segmentList2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, "duration", segmentList2 != null ? segmentList2.duration : C.TIME_UNSET);
        int parseInt = parseInt(xmlPullParser2, "startNumber", segmentList2 != null ? segmentList2.startNumber : 1);
        List list = null;
        RangedUri rangedUri = null;
        List<SegmentBase.SegmentTimelineElement> list2 = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list2 = parseSegmentTimeline(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentURL")) {
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(parseSegmentUrl(xmlPullParser));
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentList"));
        if (segmentList2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentList2.initialization;
            }
            if (list2 == null) {
                list2 = segmentList2.segmentTimeline;
            }
            if (list == null) {
                list = segmentList2.mediaSegments;
            }
        }
        return buildSegmentList(rangedUri, parseLong, parseLong2, parseInt, parseLong3, list2, list);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentList buildSegmentList(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentBase.SegmentTimelineElement> list, List<RangedUri> list2) {
        return new SegmentBase.SegmentList(rangedUri, j, j2, i, j3, list, list2);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, SegmentBase.SegmentTemplate segmentTemplate) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentTemplate segmentTemplate2 = segmentTemplate;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentTemplate2 != null ? segmentTemplate2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentTemplate2 != null ? segmentTemplate2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, "duration", segmentTemplate2 != null ? segmentTemplate2.duration : C.TIME_UNSET);
        int parseInt = parseInt(xmlPullParser2, "startNumber", segmentTemplate2 != null ? segmentTemplate2.startNumber : 1);
        RangedUri rangedUri = null;
        UrlTemplate parseUrlTemplate = parseUrlTemplate(xmlPullParser2, "media", segmentTemplate2 != null ? segmentTemplate2.mediaTemplate : null);
        UrlTemplate parseUrlTemplate2 = parseUrlTemplate(xmlPullParser2, "initialization", segmentTemplate2 != null ? segmentTemplate2.initializationTemplate : null);
        List<SegmentBase.SegmentTimelineElement> list = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list = parseSegmentTimeline(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentTemplate"));
        if (segmentTemplate2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentTemplate2.initialization;
            }
            if (list == null) {
                list = segmentTemplate2.segmentTimeline;
            }
        }
        return buildSegmentTemplate(rangedUri, parseLong, parseLong2, parseInt, parseLong3, list, parseUrlTemplate2, parseUrlTemplate);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentBase.SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2) {
        return new SegmentBase.SegmentTemplate(rangedUri, j, j2, i, j3, list, urlTemplate, urlTemplate2);
    }

    /* access modifiers changed from: protected */
    public List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "S")) {
                j = parseLong(xmlPullParser, "t", j);
                long parseLong = parseLong(xmlPullParser, "d", C.TIME_UNSET);
                int i = 0;
                int parseInt = 1 + parseInt(xmlPullParser, "r", 0);
                while (i < parseInt) {
                    arrayList.add(buildSegmentTimelineElement(j, parseLong));
                    i++;
                    j += parseLong;
                }
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long j, long j2) {
        return new SegmentBase.SegmentTimelineElement(j, j2);
    }

    /* access modifiers changed from: protected */
    public UrlTemplate parseUrlTemplate(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue != null ? UrlTemplate.compile(attributeValue) : urlTemplate;
    }

    /* access modifiers changed from: protected */
    public RangedUri parseInitialization(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "sourceURL", "range");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseSegmentUrl(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "media", "mediaRange");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue((String) null, str2);
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split("-");
            j2 = Long.parseLong(split[0]);
            if (split.length == 2) {
                j = (Long.parseLong(split[1]) - j2) + 1;
                return buildRangedUri(attributeValue, j2, j);
            }
        } else {
            j2 = 0;
        }
        j = -1;
        return buildRangedUri(attributeValue, j2, j);
    }

    /* access modifiers changed from: protected */
    public RangedUri buildRangedUri(String str, long j, long j2) {
        return new RangedUri(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public int parseAudioChannelConfiguration(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = -1;
        if ("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(parseString(xmlPullParser, "schemeIdUri", (String) null))) {
            i = parseInt(xmlPullParser, FirebaseAnalytics.Param.VALUE, -1);
        }
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    private static String getSampleMimeType(String str, String str2) {
        if (MimeTypes.isAudio(str)) {
            return MimeTypes.getAudioMediaMimeType(str2);
        }
        if (MimeTypes.isVideo(str)) {
            return MimeTypes.getVideoMediaMimeType(str2);
        }
        if (mimeTypeIsRawText(str)) {
            return str;
        }
        if (!MimeTypes.APPLICATION_MP4.equals(str)) {
            if (MimeTypes.APPLICATION_RAWCC.equals(str) && str2 != null) {
                if (str2.contains("cea708")) {
                    return MimeTypes.APPLICATION_CEA708;
                }
                if (str2.contains("eia608") || str2.contains("cea608")) {
                    return MimeTypes.APPLICATION_CEA608;
                }
            }
            return null;
        } else if ("stpp".equals(str2)) {
            return MimeTypes.APPLICATION_TTML;
        } else {
            if ("wvtt".equals(str2)) {
                return MimeTypes.APPLICATION_MP4VTT;
            }
        }
        return null;
    }

    private static boolean mimeTypeIsRawText(String str) {
        return MimeTypes.isText(str) || MimeTypes.APPLICATION_TTML.equals(str) || MimeTypes.APPLICATION_MP4VTT.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str) || MimeTypes.APPLICATION_CEA608.equals(str);
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Assertions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.checkState(i == i2);
        return i;
    }

    protected static SchemeValuePair parseSchemeValuePair(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", (String) null);
        String parseString2 = parseString(xmlPullParser, FirebaseAnalytics.Param.VALUE, (String) null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return new SchemeValuePair(parseString, parseString2);
    }

    protected static int parseCea608AccessibilityChannel(List<SchemeValuePair> list) {
        for (int i = 0; i < list.size(); i++) {
            SchemeValuePair schemeValuePair = list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(schemeValuePair.schemeIdUri) && schemeValuePair.value != null) {
                Matcher matcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(schemeValuePair.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-608 channel number from: " + schemeValuePair.value);
            }
        }
        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List<SchemeValuePair> list) {
        for (int i = 0; i < list.size(); i++) {
            SchemeValuePair schemeValuePair = list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(schemeValuePair.schemeIdUri) && schemeValuePair.value != null) {
                Matcher matcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(schemeValuePair.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w(TAG, "Unable to parse CEA-708 service block number from: " + schemeValuePair.value);
            }
        }
        return -1;
    }

    protected static float parseFrameRate(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        String group = matcher.group(2);
        return !TextUtils.isEmpty(group) ? ((float) parseInt) / ((float) Integer.parseInt(group)) : (float) parseInt;
    }

    protected static long parseDuration(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDuration(attributeValue);
    }

    protected static long parseDateTime(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDateTime(attributeValue);
    }

    protected static String parseBaseUrl(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return UriUtil.resolve(str, xmlPullParser.getText());
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    private static final class RepresentationInfo {
        public final String baseUrl;
        public final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        public final Format format;
        public final ArrayList<SchemeValuePair> inbandEventStreams;
        public final SegmentBase segmentBase;

        public RepresentationInfo(Format format2, String str, SegmentBase segmentBase2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<SchemeValuePair> arrayList2) {
            this.format = format2;
            this.baseUrl = str;
            this.segmentBase = segmentBase2;
            this.drmSchemeDatas = arrayList;
            this.inbandEventStreams = arrayList2;
        }
    }
}
