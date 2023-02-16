package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.itextpdf.text.pdf.codec.TIFFConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class AtomParsers {
    private static final String TAG = "AtomParsers";
    private static final int TYPE_cenc = Util.getIntegerCodeForString("cenc");
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_text = Util.getIntegerCodeForString(MimeTypes.BASE_TYPE_TEXT);
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z) throws ParserException {
        long j2;
        Atom.LeafAtom leafAtom2;
        Atom.ContainerAtom containerAtom2 = containerAtom;
        Atom.ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia);
        int parseHdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (parseHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(containerAtom2.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long j3 = C.TIME_UNSET;
        if (j == C.TIME_UNSET) {
            j2 = parseTkhd.duration;
            leafAtom2 = leafAtom;
        } else {
            leafAtom2 = leafAtom;
            j2 = j;
        }
        long parseMvhd = parseMvhd(leafAtom2.data);
        if (j2 != C.TIME_UNSET) {
            j3 = Util.scaleLargeTimestamp(j2, C.MICROS_PER_SECOND, parseMvhd);
        }
        long j4 = j3;
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> parseMdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData parseStsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z);
        Pair<long[], long[]> parseEdts = parseEdts(containerAtom2.getContainerAtomOfType(Atom.TYPE_edts));
        if (parseStsd.format == null) {
            return null;
        }
        int access$100 = parseTkhd.id;
        long longValue = ((Long) parseMdhd.first).longValue();
        Format format = parseStsd.format;
        int i = parseStsd.requiredSampleTransformation;
        TrackEncryptionBox[] trackEncryptionBoxArr = parseStsd.trackEncryptionBoxes;
        int i2 = parseStsd.nalUnitLengthFieldLength;
        return new Track(access$100, parseHdlr, longValue, parseMvhd, j4, format, i, trackEncryptionBoxArr, i2, (long[]) parseEdts.first, (long[]) parseEdts.second);
    }

    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox sampleSizeBox;
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr;
        long[] jArr;
        int[] iArr2;
        long[] jArr2;
        long j;
        long j2;
        int[] iArr3;
        long[] jArr3;
        boolean z2;
        int i5;
        long[] jArr4;
        long[] jArr5;
        int[] iArr4;
        int[] iArr5;
        int[] iArr6;
        long[] jArr6;
        int[] iArr7;
        ParsableByteArray parsableByteArray;
        Track track2 = track;
        Atom.ContainerAtom containerAtom2 = containerAtom;
        GaplessInfoHolder gaplessInfoHolder2 = gaplessInfoHolder;
        Atom.LeafAtom leafAtomOfType = containerAtom2.getLeafAtomOfType(Atom.TYPE_stsz);
        if (leafAtomOfType != null) {
            sampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            Atom.LeafAtom leafAtomOfType2 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stz2);
            if (leafAtomOfType2 == null) {
                throw new ParserException("Track has no sample table size information");
            }
            sampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
        }
        int sampleCount = sampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stco);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom2.getLeafAtomOfType(Atom.TYPE_co64);
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray2 = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray3 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray parsableByteArray4 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stts).data;
        Atom.LeafAtom leafAtomOfType4 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray parsableByteArray5 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        Atom.LeafAtom leafAtomOfType5 = containerAtom2.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray parsableByteArray6 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray3, parsableByteArray2, z);
        parsableByteArray4.setPosition(12);
        int readUnsignedIntToInt = parsableByteArray4.readUnsignedIntToInt() - 1;
        int readUnsignedIntToInt2 = parsableByteArray4.readUnsignedIntToInt();
        int readUnsignedIntToInt3 = parsableByteArray4.readUnsignedIntToInt();
        if (parsableByteArray6 != null) {
            parsableByteArray6.setPosition(12);
            i = parsableByteArray6.readUnsignedIntToInt();
        } else {
            i = 0;
        }
        int i6 = -1;
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            i2 = parsableByteArray5.readUnsignedIntToInt();
            if (i2 > 0) {
                i6 = parsableByteArray5.readUnsignedIntToInt() - 1;
            } else {
                parsableByteArray5 = null;
            }
        } else {
            i2 = 0;
        }
        long j3 = 0;
        if (!(sampleSizeBox.isFixedSampleSize() && MimeTypes.AUDIO_RAW.equals(track2.format.sampleMimeType) && readUnsignedIntToInt == 0 && i == 0 && i2 == 0)) {
            jArr2 = new long[sampleCount];
            iArr = new int[sampleCount];
            jArr = new long[sampleCount];
            int i7 = i2;
            iArr2 = new int[sampleCount];
            ParsableByteArray parsableByteArray7 = parsableByteArray4;
            int i8 = i6;
            long j4 = 0;
            long j5 = 0;
            int i9 = i7;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = readUnsignedIntToInt;
            int i14 = i;
            int i15 = 0;
            int i16 = 0;
            int i17 = readUnsignedIntToInt3;
            int i18 = readUnsignedIntToInt2;
            int i19 = i17;
            while (i15 < sampleCount) {
                while (i11 == 0) {
                    Assertions.checkState(chunkIterator.moveNext());
                    int i20 = i19;
                    long j6 = chunkIterator.offset;
                    i11 = chunkIterator.numSamples;
                    j4 = j6;
                    sampleCount = sampleCount;
                    i19 = i20;
                    i13 = i13;
                }
                int i21 = sampleCount;
                int i22 = i19;
                int i23 = i13;
                if (parsableByteArray6 != null) {
                    while (i12 == 0 && i14 > 0) {
                        i12 = parsableByteArray6.readUnsignedIntToInt();
                        i10 = parsableByteArray6.readInt();
                        i14--;
                    }
                    i12--;
                }
                int i24 = i10;
                jArr2[i15] = j4;
                iArr[i15] = sampleSizeBox.readNextSampleSize();
                if (iArr[i15] > i16) {
                    i16 = iArr[i15];
                }
                jArr[i15] = j5 + ((long) i24);
                iArr2[i15] = parsableByteArray5 == null ? 1 : 0;
                if (i15 == i8) {
                    iArr2[i15] = 1;
                    i9--;
                    if (i9 > 0) {
                        i8 = parsableByteArray5.readUnsignedIntToInt() - 1;
                    }
                }
                int i25 = i9;
                int i26 = i8;
                int i27 = i24;
                int i28 = i22;
                long j7 = j5 + ((long) i28);
                i18--;
                if (i18 != 0 || i23 <= 0) {
                    parsableByteArray = parsableByteArray7;
                    i13 = i23;
                } else {
                    parsableByteArray = parsableByteArray7;
                    i13 = i23 - 1;
                    i18 = parsableByteArray.readUnsignedIntToInt();
                    i28 = parsableByteArray.readUnsignedIntToInt();
                }
                ParsableByteArray parsableByteArray8 = parsableByteArray;
                i11--;
                i15++;
                j4 += (long) iArr[i15];
                sampleCount = i21;
                j5 = j7;
                i10 = i27;
                i8 = i26;
                parsableByteArray7 = parsableByteArray8;
                int i29 = i25;
                i19 = i28;
                i9 = i29;
            }
            i3 = sampleCount;
            int i30 = i13;
            Assertions.checkArgument(i12 == 0);
            while (i14 > 0) {
                Assertions.checkArgument(parsableByteArray6.readUnsignedIntToInt() == 0);
                parsableByteArray6.readInt();
                i14--;
            }
            if (i9 == 0 && i18 == 0 && i11 == 0 && i30 == 0) {
                track2 = track;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Inconsistent stbl box for track ");
                int i31 = i9;
                track2 = track;
                sb.append(track2.id);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i31);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(i18);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i11);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i30);
                Log.w(TAG, sb.toString());
            }
            j = j5;
            i4 = i16;
        } else {
            i3 = sampleCount;
            long[] jArr7 = new long[chunkIterator.length];
            int[] iArr8 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr7[chunkIterator.index] = chunkIterator.offset;
                iArr8[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results rechunk = FixedSampleSizeRechunker.rechunk(sampleSizeBox.readNextSampleSize(), jArr7, iArr8, (long) readUnsignedIntToInt3);
            jArr2 = rechunk.offsets;
            iArr = rechunk.sizes;
            int i32 = rechunk.maximumSize;
            jArr = rechunk.timestamps;
            iArr2 = rechunk.flags;
            i4 = i32;
            j = 0;
        }
        if (track2.editListDurations != null) {
            GaplessInfoHolder gaplessInfoHolder3 = gaplessInfoHolder;
            if (!gaplessInfoHolder.hasGaplessInfo()) {
                if (track2.editListDurations.length == 1 && track2.type == 1 && jArr.length >= 2) {
                    long j8 = track2.editListMediaTimes[0];
                    long scaleLargeTimestamp = j8 + Util.scaleLargeTimestamp(track2.editListDurations[0], track2.timescale, track2.movieTimescale);
                    if (jArr[0] <= j8 && j8 < jArr[1] && jArr[jArr.length - 1] < scaleLargeTimestamp && scaleLargeTimestamp <= j) {
                        long j9 = j - scaleLargeTimestamp;
                        long scaleLargeTimestamp2 = Util.scaleLargeTimestamp(j8 - jArr[0], (long) track2.format.sampleRate, track2.timescale);
                        long scaleLargeTimestamp3 = Util.scaleLargeTimestamp(j9, (long) track2.format.sampleRate, track2.timescale);
                        if (!(scaleLargeTimestamp2 == 0 && scaleLargeTimestamp3 == 0) && scaleLargeTimestamp2 <= 2147483647L && scaleLargeTimestamp3 <= 2147483647L) {
                            gaplessInfoHolder3.encoderDelay = (int) scaleLargeTimestamp2;
                            gaplessInfoHolder3.encoderPadding = (int) scaleLargeTimestamp3;
                            Util.scaleLargeTimestampsInPlace(jArr, C.MICROS_PER_SECOND, track2.timescale);
                            return new TrackSampleTable(jArr2, iArr, i4, jArr, iArr2);
                        }
                    }
                }
                if (track2.editListDurations.length == 1) {
                    char c = 0;
                    if (track2.editListDurations[0] == 0) {
                        int i33 = 0;
                        while (i33 < jArr.length) {
                            jArr[i33] = Util.scaleLargeTimestamp(jArr[i33] - track2.editListMediaTimes[c], C.MICROS_PER_SECOND, track2.timescale);
                            i33++;
                            c = 0;
                        }
                        return new TrackSampleTable(jArr2, iArr, i4, jArr, iArr2);
                    }
                }
                boolean z3 = track2.type == 1;
                int i34 = 0;
                boolean z4 = false;
                int i35 = 0;
                int i36 = 0;
                while (true) {
                    j2 = -1;
                    if (i34 >= track2.editListDurations.length) {
                        break;
                    }
                    long j10 = track2.editListMediaTimes[i34];
                    if (j10 != -1) {
                        iArr7 = iArr;
                        long scaleLargeTimestamp4 = Util.scaleLargeTimestamp(track2.editListDurations[i34], track2.timescale, track2.movieTimescale);
                        int binarySearchCeil = Util.binarySearchCeil(jArr, j10, true, true);
                        jArr6 = jArr2;
                        iArr6 = iArr2;
                        int binarySearchCeil2 = Util.binarySearchCeil(jArr, j10 + scaleLargeTimestamp4, z3, false);
                        i35 += binarySearchCeil2 - binarySearchCeil;
                        z4 |= i36 != binarySearchCeil;
                        i36 = binarySearchCeil2;
                    } else {
                        jArr6 = jArr2;
                        iArr6 = iArr2;
                        iArr7 = iArr;
                    }
                    i34++;
                    iArr = iArr7;
                    jArr2 = jArr6;
                    iArr2 = iArr6;
                }
                long[] jArr8 = jArr2;
                int[] iArr9 = iArr2;
                int[] iArr10 = iArr;
                boolean z5 = (i35 != i3) | z4;
                long[] jArr9 = z5 ? new long[i35] : jArr8;
                int[] iArr11 = z5 ? new int[i35] : iArr10;
                if (z5) {
                    i4 = 0;
                }
                int[] iArr12 = z5 ? new int[i35] : iArr9;
                long[] jArr10 = new long[i35];
                int i37 = i4;
                int i38 = 0;
                int i39 = 0;
                while (i38 < track2.editListDurations.length) {
                    long j11 = track2.editListMediaTimes[i38];
                    long j12 = track2.editListDurations[i38];
                    if (j11 != j2) {
                        long j13 = track2.timescale;
                        jArr4 = jArr10;
                        i5 = i38;
                        int binarySearchCeil3 = Util.binarySearchCeil(jArr, j11, true, true);
                        int binarySearchCeil4 = Util.binarySearchCeil(jArr, j11 + Util.scaleLargeTimestamp(j12, j13, track2.movieTimescale), z3, false);
                        if (z5) {
                            int i40 = binarySearchCeil4 - binarySearchCeil3;
                            jArr5 = jArr8;
                            System.arraycopy(jArr5, binarySearchCeil3, jArr9, i39, i40);
                            iArr4 = iArr10;
                            System.arraycopy(iArr4, binarySearchCeil3, iArr11, i39, i40);
                            z2 = z3;
                            iArr5 = iArr9;
                            System.arraycopy(iArr5, binarySearchCeil3, iArr12, i39, i40);
                        } else {
                            z2 = z3;
                            iArr4 = iArr10;
                            jArr5 = jArr8;
                            iArr5 = iArr9;
                        }
                        int i41 = i37;
                        while (binarySearchCeil3 < binarySearchCeil4) {
                            long[] jArr11 = jArr9;
                            int[] iArr13 = iArr12;
                            long j14 = j11;
                            jArr4[i39] = Util.scaleLargeTimestamp(j3, C.MICROS_PER_SECOND, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr[binarySearchCeil3] - j11, C.MICROS_PER_SECOND, track2.timescale);
                            if (z5 && iArr11[i39] > i41) {
                                i41 = iArr4[binarySearchCeil3];
                            }
                            i39++;
                            binarySearchCeil3++;
                            jArr9 = jArr11;
                            iArr12 = iArr13;
                            j11 = j14;
                        }
                        jArr3 = jArr9;
                        iArr3 = iArr12;
                        i37 = i41;
                    } else {
                        z2 = z3;
                        jArr4 = jArr10;
                        i5 = i38;
                        jArr3 = jArr9;
                        iArr3 = iArr12;
                        iArr4 = iArr10;
                        jArr5 = jArr8;
                        iArr5 = iArr9;
                    }
                    iArr9 = iArr5;
                    iArr10 = iArr4;
                    j3 += j12;
                    i38 = i5 + 1;
                    jArr8 = jArr5;
                    jArr10 = jArr4;
                    z3 = z2;
                    jArr9 = jArr3;
                    iArr12 = iArr3;
                    j2 = -1;
                }
                long[] jArr12 = jArr10;
                long[] jArr13 = jArr9;
                boolean z6 = false;
                for (int i42 = 0; i42 < iArr12.length && !z6; i42++) {
                    z6 |= (iArr12[i42] & 1) != 0;
                }
                if (z6) {
                    return new TrackSampleTable(jArr13, iArr11, i37, jArr12, iArr12);
                }
                throw new ParserException("The edited sample sequence does not contain a sync sample.");
            }
        }
        Util.scaleLargeTimestampsInPlace(jArr, C.MICROS_PER_SECOND, track2.timescale);
        return new TrackSampleTable(jArr2, iArr, i4, jArr, iArr2);
    }

    public static Metadata parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseMetaAtom(parsableByteArray, position + readInt);
            }
            parsableByteArray.skipBytes(readInt - 8);
        }
        return null;
    }

    private static Metadata parseMetaAtom(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.skipBytes(readInt - 8);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Metadata.Entry>) arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.data[position + i3] != -1) {
                z = false;
                break;
            } else {
                i3++;
            }
        }
        long j = C.TIME_UNSET;
        if (z) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt != 0) {
                j = readUnsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && readInt4 == -65536 && readInt5 == 0) {
            i2 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && readInt4 == 65536 && readInt5 == 0) {
            i2 = TIFFConstants.TIFFTAG_IMAGEDESCRIPTION;
        } else if (readInt2 == -65536 && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i2 = 180;
        }
        return new TkhdData(readInt, j, i2);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        int readInt = parsableByteArray.readInt();
        if (readInt == TYPE_soun) {
            return 1;
        }
        if (readInt == TYPE_vide) {
            return 2;
        }
        if (readInt == TYPE_text || readInt == TYPE_sbtl || readInt == TYPE_subt || readInt == TYPE_clcp) {
            return 3;
        }
        return readInt == TYPE_meta ? 4 : -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        parsableByteArray.skipBytes(i);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(readUnsignedInt), "" + ((char) (((readUnsignedShort >> 10) & 31) + 96)) + ((char) (((readUnsignedShort >> 5) & 31) + 96)) + ((char) ((readUnsignedShort & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            Assertions.checkArgument(readInt2 > 0, "childAtomSize should be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == Atom.TYPE_avc1 || readInt3 == Atom.TYPE_avc3 || readInt3 == Atom.TYPE_encv || readInt3 == Atom.TYPE_mp4v || readInt3 == Atom.TYPE_hvc1 || readInt3 == Atom.TYPE_hev1 || readInt3 == Atom.TYPE_s263 || readInt3 == Atom.TYPE_vp08 || readInt3 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray2, readInt3, position, readInt2, i, i2, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_mp4a || readInt3 == Atom.TYPE_enca || readInt3 == Atom.TYPE_ac_3 || readInt3 == Atom.TYPE_ec_3 || readInt3 == Atom.TYPE_dtsc || readInt3 == Atom.TYPE_dtse || readInt3 == Atom.TYPE_dtsh || readInt3 == Atom.TYPE_dtsl || readInt3 == Atom.TYPE_samr || readInt3 == Atom.TYPE_sawb || readInt3 == Atom.TYPE_lpcm || readInt3 == Atom.TYPE_sowt || readInt3 == Atom.TYPE__mp3 || readInt3 == Atom.TYPE_alac) {
                parseAudioSampleEntry(parsableByteArray2, readInt3, position, readInt2, i, str, z, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_TTML || readInt3 == Atom.TYPE_tx3g || readInt3 == Atom.TYPE_wvtt || readInt3 == Atom.TYPE_stpp || readInt3 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray2, readInt3, position, readInt2, i, str, drmInitData, stsdData);
            } else if (readInt3 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), MimeTypes.APPLICATION_CAMERA_MOTION, (String) null, -1, drmInitData);
            } else {
                DrmInitData drmInitData2 = drmInitData;
            }
            parsableByteArray2.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, DrmInitData drmInitData, StsdData stsdData) throws ParserException {
        String str2;
        String str3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i5 = i;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i2 + 8 + 8);
        List list = null;
        long j = Long.MAX_VALUE;
        if (i5 == Atom.TYPE_TTML) {
            str2 = MimeTypes.APPLICATION_TTML;
        } else if (i5 == Atom.TYPE_tx3g) {
            int i6 = (i3 - 8) - 8;
            byte[] bArr = new byte[i6];
            parsableByteArray2.readBytes(bArr, 0, i6);
            list = Collections.singletonList(bArr);
            str3 = MimeTypes.APPLICATION_TX3G;
            stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str3, (String) null, -1, 0, str, -1, drmInitData, j, list);
        } else if (i5 == Atom.TYPE_wvtt) {
            str2 = MimeTypes.APPLICATION_MP4VTT;
        } else if (i5 == Atom.TYPE_stpp) {
            str2 = MimeTypes.APPLICATION_TTML;
            j = 0;
        } else if (i5 == Atom.TYPE_c608) {
            str2 = MimeTypes.APPLICATION_MP4CEA608;
            stsdData2.requiredSampleTransformation = 1;
        } else {
            throw new IllegalStateException();
        }
        str3 = str2;
        stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str3, (String) null, -1, 0, str, -1, drmInitData, j, list);
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x012a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray r21, int r22, int r23, int r24, int r25, int r26, com.google.android.exoplayer2.drm.DrmInitData r27, com.google.android.exoplayer2.extractor.mp4.AtomParsers.StsdData r28, int r29) throws com.google.android.exoplayer2.ParserException {
        /*
            r0 = r21
            r1 = r23
            r2 = r24
            r3 = r28
            int r4 = r1 + 8
            int r4 = r4 + 8
            r0.setPosition(r4)
            r4 = 16
            r0.skipBytes(r4)
            int r10 = r21.readUnsignedShort()
            int r11 = r21.readUnsignedShort()
            r4 = 50
            r0.skipBytes(r4)
            int r4 = r21.getPosition()
            int r5 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_encv
            r6 = r22
            if (r6 != r5) goto L_0x0035
            r5 = r29
            int r5 = parseSampleEntryEncryptionData(r0, r1, r2, r3, r5)
            r0.setPosition(r4)
            goto L_0x0036
        L_0x0035:
            r5 = r6
        L_0x0036:
            r6 = -1
            r7 = 0
            r9 = 1065353216(0x3f800000, float:1.0)
            r17 = r6
            r6 = r7
            r13 = r6
            r16 = r13
            r15 = r9
            r7 = 0
        L_0x0042:
            int r9 = r4 - r1
            if (r9 >= r2) goto L_0x0128
            r0.setPosition(r4)
            int r9 = r21.getPosition()
            int r12 = r21.readInt()
            if (r12 != 0) goto L_0x005c
            int r14 = r21.getPosition()
            int r14 = r14 - r1
            if (r14 != r2) goto L_0x005c
            goto L_0x0128
        L_0x005c:
            if (r12 <= 0) goto L_0x0060
            r8 = 1
            goto L_0x0061
        L_0x0060:
            r8 = 0
        L_0x0061:
            java.lang.String r14 = "childAtomSize should be positive"
            com.google.android.exoplayer2.util.Assertions.checkArgument(r8, r14)
            int r8 = r21.readInt()
            int r14 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_avcC
            r1 = 3
            if (r8 != r14) goto L_0x008e
            if (r6 != 0) goto L_0x0073
            r1 = 1
            goto L_0x0074
        L_0x0073:
            r1 = 0
        L_0x0074:
            com.google.android.exoplayer2.util.Assertions.checkState(r1)
            java.lang.String r6 = "video/avc"
            int r9 = r9 + 8
            r0.setPosition(r9)
            com.google.android.exoplayer2.video.AvcConfig r1 = com.google.android.exoplayer2.video.AvcConfig.parse(r21)
            java.util.List<byte[]> r13 = r1.initializationData
            int r8 = r1.nalUnitLengthFieldLength
            r3.nalUnitLengthFieldLength = r8
            if (r7 != 0) goto L_0x0123
            float r15 = r1.pixelWidthAspectRatio
            goto L_0x0123
        L_0x008e:
            int r14 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_hvcC
            if (r8 != r14) goto L_0x00ad
            if (r6 != 0) goto L_0x0096
            r1 = 1
            goto L_0x0097
        L_0x0096:
            r1 = 0
        L_0x0097:
            com.google.android.exoplayer2.util.Assertions.checkState(r1)
            java.lang.String r6 = "video/hevc"
            int r9 = r9 + 8
            r0.setPosition(r9)
            com.google.android.exoplayer2.video.HevcConfig r1 = com.google.android.exoplayer2.video.HevcConfig.parse(r21)
            java.util.List<byte[]> r13 = r1.initializationData
            int r1 = r1.nalUnitLengthFieldLength
            r3.nalUnitLengthFieldLength = r1
            goto L_0x0123
        L_0x00ad:
            int r14 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vpcC
            if (r8 != r14) goto L_0x00c5
            if (r6 != 0) goto L_0x00b5
            r1 = 1
            goto L_0x00b6
        L_0x00b5:
            r1 = 0
        L_0x00b6:
            com.google.android.exoplayer2.util.Assertions.checkState(r1)
            int r1 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vp08
            if (r5 != r1) goto L_0x00c2
            java.lang.String r1 = "video/x-vnd.on2.vp8"
        L_0x00bf:
            r6 = r1
            goto L_0x0123
        L_0x00c2:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            goto L_0x00bf
        L_0x00c5:
            int r14 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_d263
            if (r8 != r14) goto L_0x00d4
            if (r6 != 0) goto L_0x00cd
            r1 = 1
            goto L_0x00ce
        L_0x00cd:
            r1 = 0
        L_0x00ce:
            com.google.android.exoplayer2.util.Assertions.checkState(r1)
            java.lang.String r6 = "video/3gpp"
            goto L_0x0123
        L_0x00d4:
            int r14 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_esds
            if (r8 != r14) goto L_0x00ef
            if (r6 != 0) goto L_0x00dc
            r1 = 1
            goto L_0x00dd
        L_0x00dc:
            r1 = 0
        L_0x00dd:
            com.google.android.exoplayer2.util.Assertions.checkState(r1)
            android.util.Pair r1 = parseEsdsFromParent(r0, r9)
            java.lang.Object r6 = r1.first
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r1 = r1.second
            java.util.List r13 = java.util.Collections.singletonList(r1)
            goto L_0x0123
        L_0x00ef:
            int r14 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_pasp
            if (r8 != r14) goto L_0x00f9
            float r15 = parsePaspFromParent(r0, r9)
            r7 = 1
            goto L_0x0123
        L_0x00f9:
            int r14 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_sv3d
            if (r8 != r14) goto L_0x0102
            byte[] r16 = parseProjFromParent(r0, r9, r12)
            goto L_0x0123
        L_0x0102:
            int r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_st3d
            if (r8 != r9) goto L_0x0123
            int r8 = r21.readUnsignedByte()
            r0.skipBytes(r1)
            if (r8 != 0) goto L_0x0123
            int r8 = r21.readUnsignedByte()
            switch(r8) {
                case 0: goto L_0x0121;
                case 1: goto L_0x011e;
                case 2: goto L_0x011a;
                case 3: goto L_0x0117;
                default: goto L_0x0116;
            }
        L_0x0116:
            goto L_0x0123
        L_0x0117:
            r17 = r1
            goto L_0x0123
        L_0x011a:
            r8 = 2
            r17 = r8
            goto L_0x0123
        L_0x011e:
            r17 = 1
            goto L_0x0123
        L_0x0121:
            r17 = 0
        L_0x0123:
            int r4 = r4 + r12
            r1 = r23
            goto L_0x0042
        L_0x0128:
            if (r6 != 0) goto L_0x012b
            return
        L_0x012b:
            java.lang.String r5 = java.lang.Integer.toString(r25)
            r7 = 0
            r8 = -1
            r9 = -1
            r12 = -1082130432(0xffffffffbf800000, float:-1.0)
            r18 = 0
            r14 = r26
            r19 = r27
            com.google.android.exoplayer2.Format r0 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            r3.format = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray, int, int, int, int, int, com.google.android.exoplayer2.drm.DrmInitData, com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
            return Pair.create((Object) null, (Object) null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[readUnsignedIntToInt];
        long[] jArr2 = new long[readUnsignedIntToInt];
        for (int i = 0; i < readUnsignedIntToInt; i++) {
            jArr[i] = parseFullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = parseFullAtomVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
            if (parsableByteArray.readShort() != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x01e6: MOVE  (r6v4 java.lang.String) = (r25v0 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    private static void parseAudioSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray r28, int r29, int r30, int r31, int r32, java.lang.String r33, boolean r34, com.google.android.exoplayer2.drm.DrmInitData r35, com.google.android.exoplayer2.extractor.mp4.AtomParsers.StsdData r36, int r37) {
        /*
            r0 = r28
            r1 = r30
            r2 = r31
            r14 = r33
            r13 = r35
            r12 = r36
            int r3 = r1 + 8
            r11 = 8
            int r3 = r3 + r11
            r0.setPosition(r3)
            r3 = 6
            r10 = 0
            if (r34 == 0) goto L_0x0020
            int r4 = r28.readUnsignedShort()
            r0.skipBytes(r3)
            goto L_0x0024
        L_0x0020:
            r0.skipBytes(r11)
            r4 = r10
        L_0x0024:
            r5 = 16
            r9 = 2
            r8 = 1
            if (r4 == 0) goto L_0x0046
            if (r4 != r8) goto L_0x002d
            goto L_0x0046
        L_0x002d:
            if (r4 != r9) goto L_0x0045
            r0.skipBytes(r5)
            double r3 = r28.readDouble()
            long r3 = java.lang.Math.round(r3)
            int r3 = (int) r3
            int r4 = r28.readUnsignedIntToInt()
            r5 = 20
            r0.skipBytes(r5)
            goto L_0x0057
        L_0x0045:
            return
        L_0x0046:
            int r6 = r28.readUnsignedShort()
            r0.skipBytes(r3)
            int r3 = r28.readUnsignedFixedPoint1616()
            if (r4 != r8) goto L_0x0056
            r0.skipBytes(r5)
        L_0x0056:
            r4 = r6
        L_0x0057:
            int r5 = r28.getPosition()
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_enca
            r7 = r29
            if (r7 != r6) goto L_0x006b
            r6 = r37
            int r6 = parseSampleEntryEncryptionData(r0, r1, r2, r12, r6)
            r0.setPosition(r5)
            goto L_0x006c
        L_0x006b:
            r6 = r7
        L_0x006c:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ac_3
            r15 = 0
            if (r6 != r7) goto L_0x0074
            java.lang.String r6 = "audio/ac3"
            goto L_0x00be
        L_0x0074:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ec_3
            if (r6 != r7) goto L_0x007b
            java.lang.String r6 = "audio/eac3"
            goto L_0x00be
        L_0x007b:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dtsc
            if (r6 != r7) goto L_0x0082
            java.lang.String r6 = "audio/vnd.dts"
            goto L_0x00be
        L_0x0082:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dtsh
            if (r6 == r7) goto L_0x00bc
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dtsl
            if (r6 != r7) goto L_0x008b
            goto L_0x00bc
        L_0x008b:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dtse
            if (r6 != r7) goto L_0x0092
            java.lang.String r6 = "audio/vnd.dts.hd;profile=lbr"
            goto L_0x00be
        L_0x0092:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_samr
            if (r6 != r7) goto L_0x0099
            java.lang.String r6 = "audio/3gpp"
            goto L_0x00be
        L_0x0099:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_sawb
            if (r6 != r7) goto L_0x00a0
            java.lang.String r6 = "audio/amr-wb"
            goto L_0x00be
        L_0x00a0:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_lpcm
            if (r6 == r7) goto L_0x00b9
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_sowt
            if (r6 != r7) goto L_0x00a9
            goto L_0x00b9
        L_0x00a9:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE__mp3
            if (r6 != r7) goto L_0x00b0
            java.lang.String r6 = "audio/mpeg"
            goto L_0x00be
        L_0x00b0:
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_alac
            if (r6 != r7) goto L_0x00b7
            java.lang.String r6 = "audio/alac"
            goto L_0x00be
        L_0x00b7:
            r6 = r15
            goto L_0x00be
        L_0x00b9:
            java.lang.String r6 = "audio/raw"
            goto L_0x00be
        L_0x00bc:
            java.lang.String r6 = "audio/vnd.dts.hd"
        L_0x00be:
            r17 = r3
            r16 = r4
            r7 = r5
            r5 = r15
        L_0x00c4:
            int r3 = r7 - r1
            r4 = -1
            if (r3 >= r2) goto L_0x01db
            r0.setPosition(r7)
            int r3 = r28.readInt()
            if (r3 <= 0) goto L_0x00d3
            goto L_0x00d4
        L_0x00d3:
            r8 = r10
        L_0x00d4:
            java.lang.String r9 = "childAtomSize should be positive"
            com.google.android.exoplayer2.util.Assertions.checkArgument(r8, r9)
            int r8 = r28.readInt()
            int r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_esds
            if (r8 == r9) goto L_0x0180
            if (r34 == 0) goto L_0x00e9
            int r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_wave
            if (r8 != r9) goto L_0x00e9
            goto L_0x0180
        L_0x00e9:
            int r4 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dac3
            if (r8 != r4) goto L_0x010b
            int r4 = r11 + r7
            r0.setPosition(r4)
            java.lang.String r4 = java.lang.Integer.toString(r32)
            com.google.android.exoplayer2.Format r4 = com.google.android.exoplayer2.audio.Ac3Util.parseAc3AnnexFFormat(r0, r4, r14, r13)
            r12.format = r4
        L_0x00fc:
            r24 = r5
            r25 = r6
            r5 = r7
            r6 = r10
            r20 = r11
            r2 = r12
            r18 = 1
            r19 = 2
            goto L_0x017d
        L_0x010b:
            int r4 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_dec3
            if (r8 != r4) goto L_0x011f
            int r4 = r11 + r7
            r0.setPosition(r4)
            java.lang.String r4 = java.lang.Integer.toString(r32)
            com.google.android.exoplayer2.Format r4 = com.google.android.exoplayer2.audio.Ac3Util.parseEAc3AnnexFFormat(r0, r4, r14, r13)
            r12.format = r4
            goto L_0x00fc
        L_0x011f:
            int r4 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_ddts
            if (r8 != r4) goto L_0x015c
            java.lang.String r4 = java.lang.Integer.toString(r32)
            r8 = 0
            r9 = -1
            r20 = -1
            r21 = 0
            r22 = 0
            r23 = r3
            r3 = r4
            r4 = r6
            r24 = r5
            r5 = r8
            r8 = r6
            r6 = r9
            r9 = r7
            r7 = r20
            r25 = r8
            r18 = 1
            r8 = r16
            r26 = r9
            r19 = 2
            r9 = r17
            r10 = r21
            r20 = r11
            r11 = r13
            r2 = r12
            r12 = r22
            r13 = r14
            com.google.android.exoplayer2.Format r3 = com.google.android.exoplayer2.Format.createAudioSampleFormat(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r2.format = r3
        L_0x0156:
            r3 = r23
            r5 = r26
            r6 = 0
            goto L_0x017d
        L_0x015c:
            r23 = r3
            r24 = r5
            r25 = r6
            r26 = r7
            r20 = r11
            r2 = r12
            r18 = 1
            r19 = 2
            int r3 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_alac
            if (r8 != r3) goto L_0x0156
            r3 = r23
            byte[] r4 = new byte[r3]
            r5 = r26
            r0.setPosition(r5)
            r6 = 0
            r0.readBytes(r4, r6, r3)
            goto L_0x01c8
        L_0x017d:
            r4 = r24
            goto L_0x01c8
        L_0x0180:
            r24 = r5
            r25 = r6
            r5 = r7
            r6 = r10
            r20 = r11
            r2 = r12
            r18 = 1
            r19 = 2
            int r7 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_esds
            if (r8 != r7) goto L_0x0193
            r7 = r5
            goto L_0x0197
        L_0x0193:
            int r7 = findEsdsPosition(r0, r5, r3)
        L_0x0197:
            if (r7 == r4) goto L_0x01c2
            android.util.Pair r4 = parseEsdsFromParent(r0, r7)
            java.lang.Object r7 = r4.first
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r4 = r4.second
            byte[] r4 = (byte[]) r4
            java.lang.String r8 = "audio/mp4a-latm"
            boolean r8 = r8.equals(r7)
            if (r8 == 0) goto L_0x01c6
            android.util.Pair r8 = com.google.android.exoplayer2.util.CodecSpecificDataUtil.parseAacAudioSpecificConfig(r4)
            java.lang.Object r9 = r8.first
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r17 = r9.intValue()
            java.lang.Object r8 = r8.second
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r16 = r8.intValue()
            goto L_0x01c6
        L_0x01c2:
            r4 = r24
            r7 = r25
        L_0x01c6:
            r25 = r7
        L_0x01c8:
            int r7 = r5 + r3
            r13 = r35
            r12 = r2
            r5 = r4
            r10 = r6
            r8 = r18
            r9 = r19
            r11 = r20
            r6 = r25
            r2 = r31
            goto L_0x00c4
        L_0x01db:
            r24 = r5
            r25 = r6
            r19 = r9
            r2 = r12
            com.google.android.exoplayer2.Format r0 = r2.format
            if (r0 != 0) goto L_0x021b
            r6 = r25
            if (r6 == 0) goto L_0x021b
            java.lang.String r0 = "audio/raw"
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x01f5
            r7 = r19
            goto L_0x01f6
        L_0x01f5:
            r7 = r4
        L_0x01f6:
            java.lang.String r0 = java.lang.Integer.toString(r32)
            r3 = 0
            r4 = -1
            r5 = -1
            r1 = r24
            if (r1 != 0) goto L_0x0203
            r8 = r15
            goto L_0x0208
        L_0x0203:
            java.util.List r1 = java.util.Collections.singletonList(r1)
            r8 = r1
        L_0x0208:
            r10 = 0
            r1 = r6
            r12 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r16
            r6 = r17
            r9 = r35
            r11 = r14
            com.google.android.exoplayer2.Format r0 = com.google.android.exoplayer2.Format.createAudioSampleFormat(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r12.format = r0
        L_0x021b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseAudioSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray, int, int, int, int, java.lang.String, boolean, com.google.android.exoplayer2.drm.DrmInitData, com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String str = null;
        switch (parsableByteArray.readUnsignedByte()) {
            case 32:
                str = MimeTypes.VIDEO_MP4V;
                break;
            case 33:
                str = MimeTypes.VIDEO_H264;
                break;
            case 35:
                str = MimeTypes.VIDEO_H265;
                break;
            case 64:
            case 102:
            case 103:
            case 104:
                str = MimeTypes.AUDIO_AAC;
                break;
            case 107:
                return Pair.create(MimeTypes.AUDIO_MPEG, (Object) null);
            case 165:
                str = MimeTypes.AUDIO_AC3;
                break;
            case 166:
                str = MimeTypes.AUDIO_E_AC3;
                break;
            case 169:
            case 172:
                return Pair.create(MimeTypes.AUDIO_DTS, (Object) null);
            case 170:
            case 171:
                return Pair.create(MimeTypes.AUDIO_DTS_HD, (Object) null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        return Pair.create(str, bArr);
    }

    private static int parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2, StsdData stsdData, int i3) {
        Pair<Integer, TrackEncryptionBox> parseSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (true) {
            boolean z = false;
            if (position - i >= i2) {
                return 0;
            }
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            if (readInt > 0) {
                z = true;
            }
            Assertions.checkArgument(z, "childAtomSize should be positive");
            if (parsableByteArray.readInt() != Atom.TYPE_sinf || (parseSinfFromParent = parseSinfFromParent(parsableByteArray, position, readInt)) == null) {
                position += readInt;
            } else {
                stsdData.trackEncryptionBoxes[i3] = (TrackEncryptionBox) parseSinfFromParent.second;
                return ((Integer) parseSinfFromParent.first).intValue();
            }
        }
    }

    private static Pair<Integer, TrackEncryptionBox> parseSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        boolean z = false;
        Integer num = null;
        TrackEncryptionBox trackEncryptionBox = null;
        boolean z2 = false;
        while (true) {
            boolean z3 = true;
            if (i3 - i >= i2) {
                break;
            }
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_frma) {
                num = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                if (parsableByteArray.readInt() != TYPE_cenc) {
                    z3 = false;
                }
                z2 = z3;
            } else if (readInt2 == Atom.TYPE_schi) {
                trackEncryptionBox = parseSchiFromParent(parsableByteArray, i3, readInt);
            }
            i3 += readInt;
        }
        if (!z2) {
            return null;
        }
        Assertions.checkArgument(num != null, "frma atom is mandatory");
        if (trackEncryptionBox != null) {
            z = true;
        }
        Assertions.checkArgument(z, "schi->tenc atom is mandatory");
        return Pair.create(num, trackEncryptionBox);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                parsableByteArray.skipBytes(6);
                boolean z = true;
                if (parsableByteArray.readUnsignedByte() != 1) {
                    z = false;
                }
                int readUnsignedByte = parsableByteArray.readUnsignedByte();
                byte[] bArr = new byte[16];
                parsableByteArray.readBytes(bArr, 0, bArr.length);
                return new TrackEncryptionBox(z, readUnsignedByte, bArr);
            }
            i3 += readInt;
        }
        return null;
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }

    private AtomParsers() {
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() != 1 ? false : true, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long j;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                j = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                j = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = j;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private static final class TkhdData {
        /* access modifiers changed from: private */
        public final long duration;
        /* access modifiers changed from: private */
        public final int id;
        /* access modifiers changed from: private */
        public final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    private static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize = this.data.readUnsignedIntToInt();
        private final int sampleCount = this.data.readUnsignedIntToInt();

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            return this.fixedSampleSize == 0 ? this.data.readUnsignedIntToInt() : this.fixedSampleSize;
        }

        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize = (this.data.readUnsignedIntToInt() & 255);
        private final int sampleCount = this.data.readUnsignedIntToInt();
        private int sampleIndex;

        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            if (this.fieldSize == 8) {
                return this.data.readUnsignedByte();
            }
            if (this.fieldSize == 16) {
                return this.data.readUnsignedShort();
            }
            int i = this.sampleIndex;
            this.sampleIndex = i + 1;
            if (i % 2 != 0) {
                return this.currentByte & 15;
            }
            this.currentByte = this.data.readUnsignedByte();
            return (this.currentByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
    }
}
