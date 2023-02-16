package com.google.android.exoplayer2.metadata.id3;

import android.util.Log;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.itextpdf.text.xml.xmp.XmpWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class Id3Decoder implements MetadataDecoder {
    private static final int FRAME_FLAG_V3_HAS_GROUP_IDENTIFIER = 32;
    private static final int FRAME_FLAG_V3_IS_COMPRESSED = 128;
    private static final int FRAME_FLAG_V3_IS_ENCRYPTED = 64;
    private static final int FRAME_FLAG_V4_HAS_DATA_LENGTH = 1;
    private static final int FRAME_FLAG_V4_HAS_GROUP_IDENTIFIER = 64;
    private static final int FRAME_FLAG_V4_IS_COMPRESSED = 8;
    private static final int FRAME_FLAG_V4_IS_ENCRYPTED = 4;
    private static final int FRAME_FLAG_V4_IS_UNSYNCHRONIZED = 2;
    public static final int ID3_HEADER_LENGTH = 10;
    public static final int ID3_TAG = Util.getIntegerCodeForString("ID3");
    private static final int ID3_TEXT_ENCODING_ISO_8859_1 = 0;
    private static final int ID3_TEXT_ENCODING_UTF_16 = 1;
    private static final int ID3_TEXT_ENCODING_UTF_16BE = 2;
    private static final int ID3_TEXT_ENCODING_UTF_8 = 3;
    private static final String TAG = "Id3Decoder";
    private final FramePredicate framePredicate;

    public interface FramePredicate {
        boolean evaluate(int i, int i2, int i3, int i4, int i5);
    }

    private static int delimiterLength(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    private static String getCharsetName(int i) {
        switch (i) {
            case 0:
                return "ISO-8859-1";
            case 1:
                return "UTF-16";
            case 2:
                return XmpWriter.UTF16BE;
            case 3:
                return "UTF-8";
            default:
                return "ISO-8859-1";
        }
    }

    public Id3Decoder() {
        this((FramePredicate) null);
    }

    public Id3Decoder(FramePredicate framePredicate2) {
        this.framePredicate = framePredicate2;
    }

    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        return decode(byteBuffer.array(), byteBuffer.limit());
    }

    public Metadata decode(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        Id3Header decodeHeader = decodeHeader(parsableByteArray);
        if (decodeHeader == null) {
            return null;
        }
        int position = parsableByteArray.getPosition();
        int i2 = decodeHeader.majorVersion == 2 ? 6 : 10;
        int access$100 = decodeHeader.framesSize;
        if (decodeHeader.isUnsynchronized) {
            access$100 = removeUnsynchronization(parsableByteArray, decodeHeader.framesSize);
        }
        parsableByteArray.setLimit(position + access$100);
        boolean z = false;
        if (!validateFrames(parsableByteArray, decodeHeader.majorVersion, i2, false)) {
            if (decodeHeader.majorVersion != 4 || !validateFrames(parsableByteArray, 4, i2, true)) {
                Log.w(TAG, "Failed to validate ID3 tag with majorVersion=" + decodeHeader.majorVersion);
                return null;
            }
            z = true;
        }
        while (parsableByteArray.bytesLeft() >= i2) {
            Id3Frame decodeFrame = decodeFrame(decodeHeader.majorVersion, parsableByteArray, z, i2, this.framePredicate);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static Id3Header decodeHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 10) {
            Log.w(TAG, "Data too short to be an ID3 tag");
            return null;
        }
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        if (readUnsignedInt24 != ID3_TAG) {
            Log.w(TAG, "Unexpected first three bytes of ID3 tag header: " + readUnsignedInt24);
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        boolean z = true;
        parsableByteArray.skipBytes(1);
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        int readSynchSafeInt = parsableByteArray.readSynchSafeInt();
        if (readUnsignedByte == 2) {
            if ((readUnsignedByte2 & 64) != 0) {
                Log.w(TAG, "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (readUnsignedByte == 3) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readInt = parsableByteArray.readInt();
                parsableByteArray.skipBytes(readInt);
                readSynchSafeInt -= readInt + 4;
            }
        } else if (readUnsignedByte == 4) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readSynchSafeInt2 = parsableByteArray.readSynchSafeInt();
                parsableByteArray.skipBytes(readSynchSafeInt2 - 4);
                readSynchSafeInt -= readSynchSafeInt2;
            }
            if ((readUnsignedByte2 & 16) != 0) {
                readSynchSafeInt -= 10;
            }
        } else {
            Log.w(TAG, "Skipped ID3 tag with unsupported majorVersion=" + readUnsignedByte);
            return null;
        }
        if (readUnsignedByte >= 4 || (readUnsignedByte2 & 128) == 0) {
            z = false;
        }
        return new Id3Header(readUnsignedByte, z, readSynchSafeInt);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0080, code lost:
        if ((r11 & 1) != 0) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0084, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0091, code lost:
        if ((r11 & 128) != 0) goto L_0x0082;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a7 A[SYNTHETIC, Splitter:B:50:0x00a7] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00a3 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean validateFrames(com.google.android.exoplayer2.util.ParsableByteArray r20, int r21, int r22, boolean r23) {
        /*
            r1 = r20
            r2 = r21
            int r3 = r20.getPosition()
        L_0x0008:
            int r4 = r20.bytesLeft()     // Catch:{ all -> 0x00be }
            r5 = 1
            r6 = r22
            if (r4 < r6) goto L_0x00ba
            r4 = 3
            r7 = 0
            if (r2 < r4) goto L_0x0022
            int r8 = r20.readInt()     // Catch:{ all -> 0x00be }
            long r9 = r20.readUnsignedInt()     // Catch:{ all -> 0x00be }
            int r11 = r20.readUnsignedShort()     // Catch:{ all -> 0x00be }
            goto L_0x002c
        L_0x0022:
            int r8 = r20.readUnsignedInt24()     // Catch:{ all -> 0x00be }
            int r9 = r20.readUnsignedInt24()     // Catch:{ all -> 0x00be }
            long r9 = (long) r9
            r11 = r7
        L_0x002c:
            r12 = 0
            if (r8 != 0) goto L_0x003a
            int r8 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r8 != 0) goto L_0x003a
            if (r11 != 0) goto L_0x003a
            r1.setPosition(r3)
            return r5
        L_0x003a:
            r8 = 4
            if (r2 != r8) goto L_0x0074
            if (r23 != 0) goto L_0x0074
            r14 = 8421504(0x808080, double:4.160776E-317)
            long r16 = r9 & r14
            int r14 = (r16 > r12 ? 1 : (r16 == r12 ? 0 : -1))
            if (r14 == 0) goto L_0x004c
            r1.setPosition(r3)
            return r7
        L_0x004c:
            r12 = 255(0xff, double:1.26E-321)
            long r14 = r9 & r12
            r16 = 8
            long r16 = r9 >> r16
            long r18 = r16 & r12
            r16 = 7
            long r16 = r18 << r16
            long r18 = r14 | r16
            r14 = 16
            long r14 = r9 >> r14
            long r16 = r14 & r12
            r14 = 14
            long r14 = r16 << r14
            long r16 = r18 | r14
            r14 = 24
            long r9 = r9 >> r14
            long r14 = r9 & r12
            r9 = 21
            long r9 = r14 << r9
            long r12 = r16 | r9
            goto L_0x0075
        L_0x0074:
            r12 = r9
        L_0x0075:
            if (r2 != r8) goto L_0x0086
            r4 = r11 & 64
            if (r4 == 0) goto L_0x007d
            r4 = r5
            goto L_0x007e
        L_0x007d:
            r4 = r7
        L_0x007e:
            r8 = r11 & 1
            if (r8 == 0) goto L_0x0084
        L_0x0082:
            r8 = r5
            goto L_0x0096
        L_0x0084:
            r8 = r7
            goto L_0x0096
        L_0x0086:
            if (r2 != r4) goto L_0x0094
            r4 = r11 & 32
            if (r4 == 0) goto L_0x008e
            r4 = r5
            goto L_0x008f
        L_0x008e:
            r4 = r7
        L_0x008f:
            r8 = r11 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x0084
            goto L_0x0082
        L_0x0094:
            r4 = r7
            r8 = r4
        L_0x0096:
            if (r4 == 0) goto L_0x0099
            goto L_0x009a
        L_0x0099:
            r5 = r7
        L_0x009a:
            if (r8 == 0) goto L_0x009e
            int r5 = r5 + 4
        L_0x009e:
            long r4 = (long) r5
            int r8 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r8 >= 0) goto L_0x00a7
            r1.setPosition(r3)
            return r7
        L_0x00a7:
            int r4 = r20.bytesLeft()     // Catch:{ all -> 0x00be }
            long r4 = (long) r4
            int r8 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r8 >= 0) goto L_0x00b4
            r1.setPosition(r3)
            return r7
        L_0x00b4:
            int r4 = (int) r12
            r1.skipBytes(r4)     // Catch:{ all -> 0x00be }
            goto L_0x0008
        L_0x00ba:
            r1.setPosition(r3)
            return r5
        L_0x00be:
            r0 = move-exception
            r2 = r0
            r1.setPosition(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.id3.Id3Decoder.validateFrames(com.google.android.exoplayer2.util.ParsableByteArray, int, int, boolean):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0192, code lost:
        if (r14 == 67) goto L_0x0194;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.metadata.id3.Id3Frame decodeFrame(int r20, com.google.android.exoplayer2.util.ParsableByteArray r21, boolean r22, int r23, com.google.android.exoplayer2.metadata.id3.Id3Decoder.FramePredicate r24) {
        /*
            r7 = r20
            r8 = r21
            int r9 = r21.readUnsignedByte()
            int r10 = r21.readUnsignedByte()
            int r11 = r21.readUnsignedByte()
            r12 = 3
            if (r7 < r12) goto L_0x0019
            int r1 = r21.readUnsignedByte()
            r14 = r1
            goto L_0x001a
        L_0x0019:
            r14 = 0
        L_0x001a:
            r15 = 4
            if (r7 != r15) goto L_0x003d
            int r1 = r21.readUnsignedIntToInt()
            if (r22 != 0) goto L_0x003a
            r2 = r1 & 255(0xff, float:3.57E-43)
            int r3 = r1 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 7
            r2 = r2 | r3
            int r3 = r1 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 14
            r2 = r2 | r3
            int r1 = r1 >> 24
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 21
            r1 = r1 | r2
        L_0x003a:
            r16 = r1
            goto L_0x0049
        L_0x003d:
            if (r7 != r12) goto L_0x0044
            int r1 = r21.readUnsignedIntToInt()
            goto L_0x003a
        L_0x0044:
            int r1 = r21.readUnsignedInt24()
            goto L_0x003a
        L_0x0049:
            if (r7 < r12) goto L_0x0051
            int r1 = r21.readUnsignedShort()
            r6 = r1
            goto L_0x0052
        L_0x0051:
            r6 = 0
        L_0x0052:
            r17 = 0
            if (r9 != 0) goto L_0x0068
            if (r10 != 0) goto L_0x0068
            if (r11 != 0) goto L_0x0068
            if (r14 != 0) goto L_0x0068
            if (r16 != 0) goto L_0x0068
            if (r6 != 0) goto L_0x0068
            int r1 = r21.limit()
            r8.setPosition(r1)
            return r17
        L_0x0068:
            int r1 = r21.getPosition()
            int r5 = r1 + r16
            int r1 = r21.limit()
            if (r5 <= r1) goto L_0x0083
            java.lang.String r1 = "Id3Decoder"
            java.lang.String r2 = "Frame size exceeds remaining tag data"
            android.util.Log.w(r1, r2)
            int r1 = r21.limit()
            r8.setPosition(r1)
            return r17
        L_0x0083:
            if (r24 == 0) goto L_0x0098
            r1 = r24
            r2 = r7
            r3 = r9
            r4 = r10
            r13 = r5
            r5 = r11
            r15 = r6
            r6 = r14
            boolean r1 = r1.evaluate(r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x009a
            r8.setPosition(r13)
            return r17
        L_0x0098:
            r13 = r5
            r15 = r6
        L_0x009a:
            r1 = 1
            if (r7 != r12) goto L_0x00b7
            r2 = r15 & 128(0x80, float:1.794E-43)
            if (r2 == 0) goto L_0x00a3
            r2 = r1
            goto L_0x00a4
        L_0x00a3:
            r2 = 0
        L_0x00a4:
            r3 = r15 & 64
            if (r3 == 0) goto L_0x00aa
            r3 = r1
            goto L_0x00ab
        L_0x00aa:
            r3 = 0
        L_0x00ab:
            r4 = r15 & 32
            if (r4 == 0) goto L_0x00b1
            r4 = r1
            goto L_0x00b2
        L_0x00b1:
            r4 = 0
        L_0x00b2:
            r18 = r4
            r5 = 0
            r4 = r2
            goto L_0x00ee
        L_0x00b7:
            r2 = 4
            if (r7 != r2) goto L_0x00e8
            r2 = r15 & 64
            if (r2 == 0) goto L_0x00c0
            r2 = r1
            goto L_0x00c1
        L_0x00c0:
            r2 = 0
        L_0x00c1:
            r3 = r15 & 8
            if (r3 == 0) goto L_0x00c7
            r3 = r1
            goto L_0x00c8
        L_0x00c7:
            r3 = 0
        L_0x00c8:
            r4 = r15 & 4
            if (r4 == 0) goto L_0x00ce
            r4 = r1
            goto L_0x00cf
        L_0x00ce:
            r4 = 0
        L_0x00cf:
            r5 = r15 & 2
            if (r5 == 0) goto L_0x00d5
            r5 = r1
            goto L_0x00d6
        L_0x00d5:
            r5 = 0
        L_0x00d6:
            r6 = r15 & 1
            if (r6 == 0) goto L_0x00dd
            r18 = r1
            goto L_0x00df
        L_0x00dd:
            r18 = 0
        L_0x00df:
            r19 = r18
            r18 = r2
            r2 = r3
            r3 = r4
            r4 = r19
            goto L_0x00ee
        L_0x00e8:
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r18 = 0
        L_0x00ee:
            if (r2 != 0) goto L_0x021b
            if (r3 == 0) goto L_0x00f4
            goto L_0x021b
        L_0x00f4:
            if (r18 == 0) goto L_0x00fb
            int r16 = r16 + -1
            r8.skipBytes(r1)
        L_0x00fb:
            if (r4 == 0) goto L_0x0103
            int r16 = r16 + -4
            r1 = 4
            r8.skipBytes(r1)
        L_0x0103:
            r1 = r16
            if (r5 == 0) goto L_0x010b
            int r1 = removeUnsynchronization(r8, r1)
        L_0x010b:
            r12 = r1
            r1 = 84
            r2 = 88
            r3 = 2
            if (r9 != r1) goto L_0x0121
            if (r10 != r2) goto L_0x0121
            if (r11 != r2) goto L_0x0121
            if (r7 == r3) goto L_0x011b
            if (r14 != r2) goto L_0x0121
        L_0x011b:
            com.google.android.exoplayer2.metadata.id3.TextInformationFrame r1 = decodeTxxxFrame(r8, r12)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x0121:
            if (r9 != r1) goto L_0x0131
            java.lang.String r1 = getFrameId(r7, r9, r10, r11, r14)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            com.google.android.exoplayer2.metadata.id3.TextInformationFrame r1 = decodeTextInformationFrame(r8, r12, r1)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x012d:
            r0 = move-exception
            r1 = r0
            goto L_0x0217
        L_0x0131:
            r4 = 87
            if (r9 != r4) goto L_0x0143
            if (r10 != r2) goto L_0x0143
            if (r11 != r2) goto L_0x0143
            if (r7 == r3) goto L_0x013d
            if (r14 != r2) goto L_0x0143
        L_0x013d:
            com.google.android.exoplayer2.metadata.id3.UrlLinkFrame r1 = decodeWxxxFrame(r8, r12)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x0143:
            r2 = 87
            if (r9 != r2) goto L_0x0151
            java.lang.String r1 = getFrameId(r7, r9, r10, r11, r14)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            com.google.android.exoplayer2.metadata.id3.UrlLinkFrame r1 = decodeUrlLinkFrame(r8, r12, r1)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x0151:
            r2 = 73
            r4 = 80
            if (r9 != r4) goto L_0x0167
            r5 = 82
            if (r10 != r5) goto L_0x0167
            if (r11 != r2) goto L_0x0167
            r5 = 86
            if (r14 != r5) goto L_0x0167
            com.google.android.exoplayer2.metadata.id3.PrivFrame r1 = decodePrivFrame(r8, r12)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x0167:
            r5 = 71
            r6 = 79
            if (r9 != r5) goto L_0x017f
            r5 = 69
            if (r10 != r5) goto L_0x017f
            if (r11 != r6) goto L_0x017f
            r5 = 66
            if (r14 == r5) goto L_0x0179
            if (r7 != r3) goto L_0x017f
        L_0x0179:
            com.google.android.exoplayer2.metadata.id3.GeobFrame r1 = decodeGeobFrame(r8, r12)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x017f:
            r5 = 67
            if (r7 != r3) goto L_0x018a
            if (r9 != r4) goto L_0x0199
            if (r10 != r2) goto L_0x0199
            if (r11 != r5) goto L_0x0199
            goto L_0x0194
        L_0x018a:
            r15 = 65
            if (r9 != r15) goto L_0x0199
            if (r10 != r4) goto L_0x0199
            if (r11 != r2) goto L_0x0199
            if (r14 != r5) goto L_0x0199
        L_0x0194:
            com.google.android.exoplayer2.metadata.id3.ApicFrame r1 = decodeApicFrame(r8, r12, r7)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x0199:
            if (r9 != r5) goto L_0x01ac
            if (r10 != r6) goto L_0x01ac
            r2 = 77
            if (r11 != r2) goto L_0x01ac
            r2 = 77
            if (r14 == r2) goto L_0x01a7
            if (r7 != r3) goto L_0x01ac
        L_0x01a7:
            com.google.android.exoplayer2.metadata.id3.CommentFrame r1 = decodeCommentFrame(r8, r12)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x01ac:
            if (r9 != r5) goto L_0x01c6
            r2 = 72
            if (r10 != r2) goto L_0x01c6
            r2 = 65
            if (r11 != r2) goto L_0x01c6
            if (r14 != r4) goto L_0x01c6
            r1 = r8
            r2 = r12
            r3 = r7
            r4 = r22
            r5 = r23
            r6 = r24
            com.google.android.exoplayer2.metadata.id3.ChapterFrame r1 = decodeChapterFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x01c6:
            if (r9 != r5) goto L_0x01dc
            if (r10 != r1) goto L_0x01dc
            if (r11 != r6) goto L_0x01dc
            if (r14 != r5) goto L_0x01dc
            r1 = r8
            r2 = r12
            r3 = r7
            r4 = r22
            r5 = r23
            r6 = r24
            com.google.android.exoplayer2.metadata.id3.ChapterTocFrame r1 = decodeChapterTOCFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            goto L_0x01e4
        L_0x01dc:
            java.lang.String r1 = getFrameId(r7, r9, r10, r11, r14)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            com.google.android.exoplayer2.metadata.id3.BinaryFrame r1 = decodeBinaryFrame(r8, r12, r1)     // Catch:{ UnsupportedEncodingException -> 0x020c }
        L_0x01e4:
            if (r1 != 0) goto L_0x0208
            java.lang.String r2 = "Id3Decoder"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x020c }
            r3.<init>()     // Catch:{ UnsupportedEncodingException -> 0x020c }
            java.lang.String r4 = "Failed to decode frame: id="
            r3.append(r4)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            java.lang.String r4 = getFrameId(r7, r9, r10, r11, r14)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            r3.append(r4)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            java.lang.String r4 = ", frameSize="
            r3.append(r4)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            r3.append(r12)     // Catch:{ UnsupportedEncodingException -> 0x020c }
            java.lang.String r3 = r3.toString()     // Catch:{ UnsupportedEncodingException -> 0x020c }
            android.util.Log.w(r2, r3)     // Catch:{ UnsupportedEncodingException -> 0x020c }
        L_0x0208:
            r8.setPosition(r13)
            return r1
        L_0x020c:
            java.lang.String r1 = "Id3Decoder"
            java.lang.String r2 = "Unsupported character encoding"
            android.util.Log.w(r1, r2)     // Catch:{ all -> 0x012d }
            r8.setPosition(r13)
            return r17
        L_0x0217:
            r8.setPosition(r13)
            throw r1
        L_0x021b:
            java.lang.String r1 = "Id3Decoder"
            java.lang.String r2 = "Skipping unsupported compressed or encrypted frame"
            android.util.Log.w(r1, r2)
            r8.setPosition(r13)
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.id3.Id3Decoder.decodeFrame(int, com.google.android.exoplayer2.util.ParsableByteArray, boolean, int, com.google.android.exoplayer2.metadata.id3.Id3Decoder$FramePredicate):com.google.android.exoplayer2.metadata.id3.Id3Frame");
    }

    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfEos = indexOfEos(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        return new TextInformationFrame("TXXX", str, delimiterLength < bArr.length ? new String(bArr, delimiterLength, indexOfEos(bArr, delimiterLength, readUnsignedByte) - delimiterLength, charsetName) : "");
    }

    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray parsableByteArray, int i, String str) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        return new TextInformationFrame(str, (String) null, new String(bArr, 0, indexOfEos(bArr, 0, readUnsignedByte), charsetName));
    }

    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfEos = indexOfEos(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        return new UrlLinkFrame("WXXX", str, delimiterLength < bArr.length ? new String(bArr, delimiterLength, indexOfZeroByte(bArr, delimiterLength) - delimiterLength, "ISO-8859-1") : "");
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray parsableByteArray, int i, String str) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new UrlLinkFrame(str, (String) null, new String(bArr, 0, indexOfZeroByte(bArr, 0), "ISO-8859-1"));
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        byte[] bArr;
        byte[] bArr2 = new byte[i];
        parsableByteArray.readBytes(bArr2, 0, i);
        int indexOfZeroByte = indexOfZeroByte(bArr2, 0);
        String str = new String(bArr2, 0, indexOfZeroByte, "ISO-8859-1");
        int i2 = indexOfZeroByte + 1;
        if (i2 < bArr2.length) {
            bArr = Arrays.copyOfRange(bArr2, i2, bArr2.length);
        } else {
            bArr = new byte[0];
        }
        return new PrivFrame(str, bArr);
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        String str = new String(bArr, 0, indexOfZeroByte, "ISO-8859-1");
        int i3 = indexOfZeroByte + 1;
        int indexOfEos = indexOfEos(bArr, i3, readUnsignedByte);
        String str2 = new String(bArr, i3, indexOfEos - i3, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        int indexOfEos2 = indexOfEos(bArr, delimiterLength, readUnsignedByte);
        return new GeobFrame(str, str2, new String(bArr, delimiterLength, indexOfEos2 - delimiterLength, charsetName), Arrays.copyOfRange(bArr, indexOfEos2 + delimiterLength(readUnsignedByte), bArr.length));
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray parsableByteArray, int i, int i2) throws UnsupportedEncodingException {
        String str;
        int i3;
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i4 = i - 1;
        byte[] bArr = new byte[i4];
        parsableByteArray.readBytes(bArr, 0, i4);
        if (i2 == 2) {
            String str2 = "image/" + Util.toLowerInvariant(new String(bArr, 0, 3, "ISO-8859-1"));
            if (str2.equals("image/jpg")) {
                str2 = "image/jpeg";
            }
            str = str2;
            i3 = 2;
        } else {
            i3 = indexOfZeroByte(bArr, 0);
            str = Util.toLowerInvariant(new String(bArr, 0, i3, "ISO-8859-1"));
            if (str.indexOf(47) == -1) {
                str = "image/" + str;
            }
        }
        int i5 = i3 + 2;
        int indexOfEos = indexOfEos(bArr, i5, readUnsignedByte);
        return new ApicFrame(str, new String(bArr, i5, indexOfEos - i5, charsetName), bArr[i3 + 1] & 255, Arrays.copyOfRange(bArr, indexOfEos + delimiterLength(readUnsignedByte), bArr.length));
    }

    private static CommentFrame decodeCommentFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        if (i < 4) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        byte[] bArr = new byte[3];
        parsableByteArray.readBytes(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        int i2 = i - 4;
        byte[] bArr2 = new byte[i2];
        parsableByteArray.readBytes(bArr2, 0, i2);
        int indexOfEos = indexOfEos(bArr2, 0, readUnsignedByte);
        String str2 = new String(bArr2, 0, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        return new CommentFrame(str, str2, delimiterLength < bArr2.length ? new String(bArr2, delimiterLength, indexOfEos(bArr2, delimiterLength, readUnsignedByte) - delimiterLength, charsetName) : "");
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, FramePredicate framePredicate2) throws UnsupportedEncodingException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray2.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray2.data, position);
        String str = new String(parsableByteArray2.data, position, indexOfZeroByte - position, "ISO-8859-1");
        parsableByteArray2.setPosition(indexOfZeroByte + 1);
        int readInt = parsableByteArray2.readInt();
        int readInt2 = parsableByteArray2.readInt();
        long readUnsignedInt = parsableByteArray2.readUnsignedInt();
        long j = readUnsignedInt == 4294967295L ? -1 : readUnsignedInt;
        long readUnsignedInt2 = parsableByteArray2.readUnsignedInt();
        long j2 = readUnsignedInt2 == 4294967295L ? -1 : readUnsignedInt2;
        ArrayList arrayList = new ArrayList();
        int i4 = position + i;
        while (parsableByteArray2.getPosition() < i4) {
            Id3Frame decodeFrame = decodeFrame(i2, parsableByteArray2, z, i3, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        Id3Frame[] id3FrameArr = new Id3Frame[arrayList.size()];
        arrayList.toArray(id3FrameArr);
        return new ChapterFrame(str, readInt, readInt2, j, j2, id3FrameArr);
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, FramePredicate framePredicate2) throws UnsupportedEncodingException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray2.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray2.data, position);
        String str = new String(parsableByteArray2.data, position, indexOfZeroByte - position, "ISO-8859-1");
        parsableByteArray2.setPosition(indexOfZeroByte + 1);
        int readUnsignedByte = parsableByteArray2.readUnsignedByte();
        boolean z2 = (readUnsignedByte & 2) != 0;
        boolean z3 = (readUnsignedByte & 1) != 0;
        int readUnsignedByte2 = parsableByteArray2.readUnsignedByte();
        String[] strArr = new String[readUnsignedByte2];
        for (int i4 = 0; i4 < readUnsignedByte2; i4++) {
            int position2 = parsableByteArray2.getPosition();
            int indexOfZeroByte2 = indexOfZeroByte(parsableByteArray2.data, position2);
            strArr[i4] = new String(parsableByteArray2.data, position2, indexOfZeroByte2 - position2, "ISO-8859-1");
            parsableByteArray2.setPosition(indexOfZeroByte2 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i5 = position + i;
        while (parsableByteArray2.getPosition() < i5) {
            Id3Frame decodeFrame = decodeFrame(i2, parsableByteArray2, z, i3, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        Id3Frame[] id3FrameArr = new Id3Frame[arrayList.size()];
        arrayList.toArray(id3FrameArr);
        return new ChapterTocFrame(str, z2, z3, strArr, id3FrameArr);
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray parsableByteArray, int i, String str) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new BinaryFrame(str, bArr);
    }

    private static int removeUnsynchronization(ParsableByteArray parsableByteArray, int i) {
        byte[] bArr = parsableByteArray.data;
        int position = parsableByteArray.getPosition();
        while (true) {
            int i2 = position + 1;
            if (i2 >= i) {
                return i;
            }
            if ((bArr[position] & 255) == 255 && bArr[i2] == 0) {
                System.arraycopy(bArr, position + 2, bArr, i2, (i - position) - 2);
                i--;
            }
            position = i2;
        }
    }

    private static String getFrameId(int i, int i2, int i3, int i4, int i5) {
        if (i == 2) {
            return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
        }
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    private static int indexOfEos(byte[] bArr, int i, int i2) {
        int indexOfZeroByte = indexOfZeroByte(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return indexOfZeroByte;
        }
        while (indexOfZeroByte < bArr.length - 1) {
            if (indexOfZeroByte % 2 == 0 && bArr[indexOfZeroByte + 1] == 0) {
                return indexOfZeroByte;
            }
            indexOfZeroByte = indexOfZeroByte(bArr, indexOfZeroByte + 1);
        }
        return bArr.length;
    }

    private static int indexOfZeroByte(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return bArr.length;
    }

    private static final class Id3Header {
        /* access modifiers changed from: private */
        public final int framesSize;
        /* access modifiers changed from: private */
        public final boolean isUnsynchronized;
        /* access modifiers changed from: private */
        public final int majorVersion;

        public Id3Header(int i, boolean z, int i2) {
            this.majorVersion = i;
            this.isUnsynchronized = z;
            this.framesSize = i2;
        }
    }
}
