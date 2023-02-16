package com.google.android.exoplayer2.extractor.mkv;

import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.itextpdf.text.DocWriter;
import com.itextpdf.text.pdf.ByteBuffer;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class MatroskaExtractor implements Extractor {
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() {
        public Extractor[] createExtractors() {
            return new Extractor[]{new MatroskaExtractor()};
        }
    };
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    private static final byte[] SUBRIP_PREFIX = {49, 10, ByteBuffer.ZERO, ByteBuffer.ZERO, 58, ByteBuffer.ZERO, ByteBuffer.ZERO, 58, ByteBuffer.ZERO, ByteBuffer.ZERO, 44, ByteBuffer.ZERO, ByteBuffer.ZERO, ByteBuffer.ZERO, DocWriter.SPACE, 45, 45, DocWriter.GT, DocWriter.SPACE, ByteBuffer.ZERO, ByteBuffer.ZERO, 58, ByteBuffer.ZERO, ByteBuffer.ZERO, 58, ByteBuffer.ZERO, ByteBuffer.ZERO, 44, ByteBuffer.ZERO, ByteBuffer.ZERO, ByteBuffer.ZERO, 10};
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE, DocWriter.SPACE};
    private static final int SUBRIP_TIMECODE_LENGTH = 12;
    private static final String TAG = "MatroskaExtractor";
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private java.nio.ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subripSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    /* access modifiers changed from: package-private */
    public int getElementType(int i) {
        switch (i) {
            case ID_TRACK_TYPE /*131*/:
            case ID_FLAG_DEFAULT /*136*/:
            case ID_BLOCK_DURATION /*155*/:
            case ID_CHANNELS /*159*/:
            case ID_PIXEL_WIDTH /*176*/:
            case ID_CUE_TIME /*179*/:
            case ID_PIXEL_HEIGHT /*186*/:
            case ID_TRACK_NUMBER /*215*/:
            case ID_TIME_CODE /*231*/:
            case ID_CUE_CLUSTER_POSITION /*241*/:
            case ID_REFERENCE_BLOCK /*251*/:
            case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
            case ID_DOC_TYPE_READ_VERSION /*17029*/:
            case ID_EBML_READ_VERSION /*17143*/:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
            case ID_CONTENT_ENCODING_ORDER /*20529*/:
            case ID_CONTENT_ENCODING_SCOPE /*20530*/:
            case ID_SEEK_POSITION /*21420*/:
            case ID_STEREO_MODE /*21432*/:
            case ID_DISPLAY_WIDTH /*21680*/:
            case ID_DISPLAY_UNIT /*21682*/:
            case ID_DISPLAY_HEIGHT /*21690*/:
            case ID_FLAG_FORCED /*21930*/:
            case ID_COLOUR_RANGE /*21945*/:
            case ID_COLOUR_TRANSFER /*21946*/:
            case ID_COLOUR_PRIMARIES /*21947*/:
            case ID_MAX_CLL /*21948*/:
            case ID_MAX_FALL /*21949*/:
            case ID_CODEC_DELAY /*22186*/:
            case ID_SEEK_PRE_ROLL /*22203*/:
            case ID_AUDIO_BIT_DEPTH /*25188*/:
            case ID_DEFAULT_DURATION /*2352003*/:
            case ID_TIMECODE_SCALE /*2807729*/:
                return 2;
            case 134:
            case ID_DOC_TYPE /*17026*/:
            case ID_LANGUAGE /*2274716*/:
                return 3;
            case ID_BLOCK_GROUP /*160*/:
            case ID_TRACK_ENTRY /*174*/:
            case ID_CUE_TRACK_POSITIONS /*183*/:
            case ID_CUE_POINT /*187*/:
            case 224:
            case ID_AUDIO /*225*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /*18407*/:
            case ID_SEEK /*19899*/:
            case ID_CONTENT_COMPRESSION /*20532*/:
            case ID_CONTENT_ENCRYPTION /*20533*/:
            case ID_COLOUR /*21936*/:
            case ID_MASTERING_METADATA /*21968*/:
            case ID_CONTENT_ENCODING /*25152*/:
            case ID_CONTENT_ENCODINGS /*28032*/:
            case ID_PROJECTION /*30320*/:
            case ID_SEEK_HEAD /*290298740*/:
            case 357149030:
            case ID_TRACKS /*374648427*/:
            case ID_SEGMENT /*408125543*/:
            case ID_EBML /*440786851*/:
            case ID_CUES /*475249515*/:
            case ID_CLUSTER /*524531317*/:
                return 1;
            case ID_BLOCK /*161*/:
            case ID_SIMPLE_BLOCK /*163*/:
            case ID_CONTENT_COMPRESSION_SETTINGS /*16981*/:
            case ID_CONTENT_ENCRYPTION_KEY_ID /*18402*/:
            case ID_SEEK_ID /*21419*/:
            case ID_CODEC_PRIVATE /*25506*/:
            case ID_PROJECTION_PRIVATE /*30322*/:
                return 4;
            case ID_SAMPLING_FREQUENCY /*181*/:
            case ID_DURATION /*17545*/:
            case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
            case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
            case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
            case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
            case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
            case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
            case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
            case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
            case ID_LUMNINANCE_MAX /*21977*/:
            case ID_LUMNINANCE_MIN /*21978*/:
                return 5;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isLevel1Element(int i) {
        return i == 357149030 || i == ID_CLUSTER || i == ID_CUES || i == ID_TRACKS;
    }

    public void release() {
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i) {
        this.segmentContentPosition = -1;
        this.timecodeScale = C.TIME_UNSET;
        this.durationTimecode = C.TIME_UNSET;
        this.durationUs = C.TIME_UNSET;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.reader = ebmlReader;
        this.reader.init(new InnerEbmlReaderOutput());
        this.seekForCuesEnabled = (i & 1) != 0 ? false : true;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(java.nio.ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subripSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return new Sniffer().sniff(extractorInput);
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        this.sampleRead = false;
        boolean z = true;
        while (z && !this.sampleRead) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == ID_BLOCK_GROUP) {
            this.sampleSeenReferenceBlock = false;
        } else if (i == ID_TRACK_ENTRY) {
            this.currentTrack = new Track();
        } else if (i == ID_CUE_POINT) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == ID_CONTENT_ENCRYPTION) {
            this.currentTrack.hasContentEncryption = true;
        } else if (i == ID_MASTERING_METADATA) {
            this.currentTrack.hasColorInfo = true;
        } else if (i == ID_CONTENT_ENCODING) {
        } else {
            if (i != ID_SEGMENT) {
                if (i == ID_CUES) {
                    this.cueTimesUs = new LongArray();
                    this.cueClusterPositions = new LongArray();
                } else if (i != ID_CLUSTER || this.sentSeekMap) {
                } else {
                    if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                        this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                        this.sentSeekMap = true;
                        return;
                    }
                    this.seekForCues = true;
                }
            } else if (this.segmentContentPosition == -1 || this.segmentContentPosition == j) {
                this.segmentContentPosition = j;
                this.segmentContentSize = j2;
            } else {
                throw new ParserException("Multiple Segment elements not supported");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void endMasterElement(int i) throws ParserException {
        if (i != ID_BLOCK_GROUP) {
            if (i == ID_TRACK_ENTRY) {
                if (isCodecSupported(this.currentTrack.codecId)) {
                    this.currentTrack.initializeOutput(this.extractorOutput, this.currentTrack.number);
                    this.tracks.put(this.currentTrack.number, this.currentTrack);
                }
                this.currentTrack = null;
            } else if (i != ID_SEEK) {
                if (i != ID_CONTENT_ENCODING) {
                    if (i != ID_CONTENT_ENCODINGS) {
                        if (i == 357149030) {
                            if (this.timecodeScale == C.TIME_UNSET) {
                                this.timecodeScale = C.MICROS_PER_SECOND;
                            }
                            if (this.durationTimecode != C.TIME_UNSET) {
                                this.durationUs = scaleTimecodeToUs(this.durationTimecode);
                            }
                        } else if (i != ID_TRACKS) {
                            if (i == ID_CUES && !this.sentSeekMap) {
                                this.extractorOutput.seekMap(buildSeekMap());
                                this.sentSeekMap = true;
                            }
                        } else if (this.tracks.size() == 0) {
                            throw new ParserException("No valid tracks were found");
                        } else {
                            this.extractorOutput.endTracks();
                        }
                    } else if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                        throw new ParserException("Combining encryption and compression is not supported");
                    }
                } else if (!this.currentTrack.hasContentEncryption) {
                } else {
                    if (this.currentTrack.encryptionKeyId == null) {
                        throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                    }
                    this.currentTrack.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.encryptionKeyId));
                }
            } else if (this.seekEntryId == -1 || this.seekEntryPosition == -1) {
                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            } else if (this.seekEntryId == ID_CUES) {
                this.cuesContentPosition = this.seekEntryPosition;
            }
        } else if (this.blockState == 2) {
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput(this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void integerElement(int i, long j) throws ParserException {
        boolean z = false;
        switch (i) {
            case ID_TRACK_TYPE /*131*/:
                this.currentTrack.type = (int) j;
                return;
            case ID_FLAG_DEFAULT /*136*/:
                Track track = this.currentTrack;
                if (j == 1) {
                    z = true;
                }
                track.flagForced = z;
                return;
            case ID_BLOCK_DURATION /*155*/:
                this.blockDurationUs = scaleTimecodeToUs(j);
                return;
            case ID_CHANNELS /*159*/:
                this.currentTrack.channelCount = (int) j;
                return;
            case ID_PIXEL_WIDTH /*176*/:
                this.currentTrack.width = (int) j;
                return;
            case ID_CUE_TIME /*179*/:
                this.cueTimesUs.add(scaleTimecodeToUs(j));
                return;
            case ID_PIXEL_HEIGHT /*186*/:
                this.currentTrack.height = (int) j;
                return;
            case ID_TRACK_NUMBER /*215*/:
                this.currentTrack.number = (int) j;
                return;
            case ID_TIME_CODE /*231*/:
                this.clusterTimecodeUs = scaleTimecodeToUs(j);
                return;
            case ID_CUE_CLUSTER_POSITION /*241*/:
                if (!this.seenClusterPositionForCurrentCuePoint) {
                    this.cueClusterPositions.add(j);
                    this.seenClusterPositionForCurrentCuePoint = true;
                    return;
                }
                return;
            case ID_REFERENCE_BLOCK /*251*/:
                this.sampleSeenReferenceBlock = true;
                return;
            case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
                if (j != 3) {
                    throw new ParserException("ContentCompAlgo " + j + " not supported");
                }
                return;
            case ID_DOC_TYPE_READ_VERSION /*17029*/:
                if (j < 1 || j > 2) {
                    throw new ParserException("DocTypeReadVersion " + j + " not supported");
                }
                return;
            case ID_EBML_READ_VERSION /*17143*/:
                if (j != 1) {
                    throw new ParserException("EBMLReadVersion " + j + " not supported");
                }
                return;
            case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
                if (j != 5) {
                    throw new ParserException("ContentEncAlgo " + j + " not supported");
                }
                return;
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
                if (j != 1) {
                    throw new ParserException("AESSettingsCipherMode " + j + " not supported");
                }
                return;
            case ID_CONTENT_ENCODING_ORDER /*20529*/:
                if (j != 0) {
                    throw new ParserException("ContentEncodingOrder " + j + " not supported");
                }
                return;
            case ID_CONTENT_ENCODING_SCOPE /*20530*/:
                if (j != 1) {
                    throw new ParserException("ContentEncodingScope " + j + " not supported");
                }
                return;
            case ID_SEEK_POSITION /*21420*/:
                this.seekEntryPosition = j + this.segmentContentPosition;
                return;
            case ID_STEREO_MODE /*21432*/:
                int i2 = (int) j;
                if (i2 == 3) {
                    this.currentTrack.stereoMode = 1;
                    return;
                } else if (i2 != 15) {
                    switch (i2) {
                        case 0:
                            this.currentTrack.stereoMode = 0;
                            return;
                        case 1:
                            this.currentTrack.stereoMode = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.currentTrack.stereoMode = 3;
                    return;
                }
            case ID_DISPLAY_WIDTH /*21680*/:
                this.currentTrack.displayWidth = (int) j;
                return;
            case ID_DISPLAY_UNIT /*21682*/:
                this.currentTrack.displayUnit = (int) j;
                return;
            case ID_DISPLAY_HEIGHT /*21690*/:
                this.currentTrack.displayHeight = (int) j;
                return;
            case ID_FLAG_FORCED /*21930*/:
                Track track2 = this.currentTrack;
                if (j == 1) {
                    z = true;
                }
                track2.flagDefault = z;
                return;
            case ID_COLOUR_RANGE /*21945*/:
                switch ((int) j) {
                    case 1:
                        this.currentTrack.colorRange = 2;
                        return;
                    case 2:
                        this.currentTrack.colorRange = 1;
                        return;
                    default:
                        return;
                }
            case ID_COLOUR_TRANSFER /*21946*/:
                int i3 = (int) j;
                if (i3 != 1) {
                    if (i3 == 16) {
                        this.currentTrack.colorTransfer = 6;
                        return;
                    } else if (i3 != 18) {
                        switch (i3) {
                            case 6:
                            case 7:
                                break;
                            default:
                                return;
                        }
                    } else {
                        this.currentTrack.colorTransfer = 7;
                        return;
                    }
                }
                this.currentTrack.colorTransfer = 3;
                return;
            case ID_COLOUR_PRIMARIES /*21947*/:
                this.currentTrack.hasColorInfo = true;
                int i4 = (int) j;
                if (i4 == 1) {
                    this.currentTrack.colorSpace = 1;
                    return;
                } else if (i4 != 9) {
                    switch (i4) {
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            this.currentTrack.colorSpace = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.currentTrack.colorSpace = 6;
                    return;
                }
            case ID_MAX_CLL /*21948*/:
                this.currentTrack.maxContentLuminance = (int) j;
                return;
            case ID_MAX_FALL /*21949*/:
                this.currentTrack.maxFrameAverageLuminance = (int) j;
                return;
            case ID_CODEC_DELAY /*22186*/:
                this.currentTrack.codecDelayNs = j;
                return;
            case ID_SEEK_PRE_ROLL /*22203*/:
                this.currentTrack.seekPreRollNs = j;
                return;
            case ID_AUDIO_BIT_DEPTH /*25188*/:
                this.currentTrack.audioBitDepth = (int) j;
                return;
            case ID_DEFAULT_DURATION /*2352003*/:
                this.currentTrack.defaultSampleDurationNs = (int) j;
                return;
            case ID_TIMECODE_SCALE /*2807729*/:
                this.timecodeScale = j;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void floatElement(int i, double d) {
        if (i == ID_SAMPLING_FREQUENCY) {
            this.currentTrack.sampleRate = (int) d;
        } else if (i != ID_DURATION) {
            switch (i) {
                case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
                    this.currentTrack.primaryRChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
                    this.currentTrack.primaryRChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
                    this.currentTrack.primaryGChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
                    this.currentTrack.primaryGChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
                    this.currentTrack.primaryBChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
                    this.currentTrack.primaryBChromaticityY = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
                    this.currentTrack.whitePointChromaticityX = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
                    this.currentTrack.whitePointChromaticityY = (float) d;
                    return;
                case ID_LUMNINANCE_MAX /*21977*/:
                    this.currentTrack.maxMasteringLuminance = (float) d;
                    return;
                case ID_LUMNINANCE_MIN /*21978*/:
                    this.currentTrack.minMasteringLuminance = (float) d;
                    return;
                default:
                    return;
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* access modifiers changed from: package-private */
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            this.currentTrack.codecId = str;
        } else if (i != ID_DOC_TYPE) {
            if (i == ID_LANGUAGE) {
                String unused = this.currentTrack.language = str;
            }
        } else if (!DOC_TYPE_WEBM.equals(str) && !DOC_TYPE_MATROSKA.equals(str)) {
            throw new ParserException("DocType " + str + " not supported");
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01e5, code lost:
        r9 = r16;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void binaryElement(int r25, int r26, com.google.android.exoplayer2.extractor.ExtractorInput r27) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r27
            r4 = 161(0xa1, float:2.26E-43)
            r5 = 163(0xa3, float:2.28E-43)
            r6 = 4
            r7 = 0
            if (r1 == r4) goto L_0x0098
            if (r1 == r5) goto L_0x0098
            r4 = 16981(0x4255, float:2.3795E-41)
            if (r1 == r4) goto L_0x0089
            r4 = 18402(0x47e2, float:2.5787E-41)
            if (r1 == r4) goto L_0x007a
            r4 = 21419(0x53ab, float:3.0014E-41)
            if (r1 == r4) goto L_0x005b
            r4 = 25506(0x63a2, float:3.5742E-41)
            if (r1 == r4) goto L_0x004c
            r4 = 30322(0x7672, float:4.249E-41)
            if (r1 == r4) goto L_0x003d
            com.google.android.exoplayer2.ParserException r2 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unexpected id: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>((java.lang.String) r1)
            throw r2
        L_0x003d:
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.projectionData = r4
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r1 = r1.projectionData
            r3.readFully(r1, r7, r2)
            goto L_0x02c2
        L_0x004c:
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.codecPrivate = r4
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r1 = r1.codecPrivate
            r3.readFully(r1, r7, r2)
            goto L_0x02c2
        L_0x005b:
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            byte[] r1 = r1.data
            java.util.Arrays.fill(r1, r7)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            byte[] r1 = r1.data
            int r6 = r6 - r2
            r3.readFully(r1, r6, r2)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            r1.setPosition(r7)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            long r1 = r1.readUnsignedInt()
            int r1 = (int) r1
            r0.seekEntryId = r1
            goto L_0x02c2
        L_0x007a:
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.encryptionKeyId = r4
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r1 = r1.encryptionKeyId
            r3.readFully(r1, r7, r2)
            goto L_0x02c2
        L_0x0089:
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.sampleStrippedBytes = r4
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r1 = r1.sampleStrippedBytes
            r3.readFully(r1, r7, r2)
            goto L_0x02c2
        L_0x0098:
            int r4 = r0.blockState
            r8 = 8
            r9 = 1
            if (r4 != 0) goto L_0x00be
            com.google.android.exoplayer2.extractor.mkv.VarintReader r4 = r0.varintReader
            long r10 = r4.readUnsignedVarint(r3, r7, r9, r8)
            int r4 = (int) r10
            r0.blockTrackNumber = r4
            com.google.android.exoplayer2.extractor.mkv.VarintReader r4 = r0.varintReader
            int r4 = r4.getLastLength()
            r0.blockTrackNumberLength = r4
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0.blockDurationUs = r10
            r0.blockState = r9
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.scratch
            r4.reset()
        L_0x00be:
            android.util.SparseArray<com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track> r4 = r0.tracks
            int r10 = r0.blockTrackNumber
            java.lang.Object r4 = r4.get(r10)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r4 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r4
            if (r4 != 0) goto L_0x00d4
            int r1 = r0.blockTrackNumberLength
            int r1 = r2 - r1
            r3.skipFully(r1)
            r0.blockState = r7
            return
        L_0x00d4:
            int r10 = r0.blockState
            if (r10 != r9) goto L_0x0290
            r10 = 3
            r0.readScratch(r3, r10)
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r0.scratch
            byte[] r11 = r11.data
            r12 = 2
            byte r11 = r11[r12]
            r13 = 6
            r11 = r11 & r13
            int r11 = r11 >> r9
            r14 = 255(0xff, float:3.57E-43)
            if (r11 != 0) goto L_0x00fe
            r0.blockLacingSampleCount = r9
            int[] r6 = r0.blockLacingSampleSizes
            int[] r6 = ensureArrayCapacity(r6, r9)
            r0.blockLacingSampleSizes = r6
            int[] r6 = r0.blockLacingSampleSizes
            int r11 = r0.blockTrackNumberLength
            int r2 = r2 - r11
            int r2 = r2 - r10
            r6[r7] = r2
            goto L_0x0229
        L_0x00fe:
            if (r1 == r5) goto L_0x0108
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Lacing only supported in SimpleBlocks."
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x0108:
            r0.readScratch(r3, r6)
            com.google.android.exoplayer2.util.ParsableByteArray r15 = r0.scratch
            byte[] r15 = r15.data
            byte r15 = r15[r10]
            r15 = r15 & r14
            int r15 = r15 + r9
            r0.blockLacingSampleCount = r15
            int[] r15 = r0.blockLacingSampleSizes
            int r5 = r0.blockLacingSampleCount
            int[] r5 = ensureArrayCapacity(r15, r5)
            r0.blockLacingSampleSizes = r5
            if (r11 != r12) goto L_0x0131
            int r5 = r0.blockTrackNumberLength
            int r2 = r2 - r5
            int r2 = r2 - r6
            int r5 = r0.blockLacingSampleCount
            int r2 = r2 / r5
            int[] r5 = r0.blockLacingSampleSizes
            int r6 = r0.blockLacingSampleCount
            java.util.Arrays.fill(r5, r7, r6, r2)
            goto L_0x0229
        L_0x0131:
            if (r11 != r9) goto L_0x016a
            r5 = r7
            r10 = r5
        L_0x0135:
            int r11 = r0.blockLacingSampleCount
            int r11 = r11 - r9
            if (r5 >= r11) goto L_0x015c
            int[] r11 = r0.blockLacingSampleSizes
            r11[r5] = r7
        L_0x013e:
            int r6 = r6 + r9
            r0.readScratch(r3, r6)
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r0.scratch
            byte[] r11 = r11.data
            int r13 = r6 + -1
            byte r11 = r11[r13]
            r11 = r11 & r14
            int[] r13 = r0.blockLacingSampleSizes
            r15 = r13[r5]
            int r15 = r15 + r11
            r13[r5] = r15
            if (r11 == r14) goto L_0x013e
            int[] r11 = r0.blockLacingSampleSizes
            r11 = r11[r5]
            int r10 = r10 + r11
            int r5 = r5 + 1
            goto L_0x0135
        L_0x015c:
            int[] r5 = r0.blockLacingSampleSizes
            int r11 = r0.blockLacingSampleCount
            int r11 = r11 - r9
            int r13 = r0.blockTrackNumberLength
            int r2 = r2 - r13
            int r2 = r2 - r6
            int r2 = r2 - r10
            r5[r11] = r2
            goto L_0x0229
        L_0x016a:
            if (r11 != r10) goto L_0x0279
            r5 = r7
            r10 = r5
        L_0x016e:
            int r11 = r0.blockLacingSampleCount
            int r11 = r11 - r9
            if (r5 >= r11) goto L_0x0219
            int[] r11 = r0.blockLacingSampleSizes
            r11[r5] = r7
            int r6 = r6 + 1
            r0.readScratch(r3, r6)
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r0.scratch
            byte[] r11 = r11.data
            int r15 = r6 + -1
            byte r11 = r11[r15]
            if (r11 != 0) goto L_0x018e
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "No valid varint length mask found"
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x018e:
            r16 = 0
            r11 = r7
        L_0x0191:
            if (r11 >= r8) goto L_0x01e3
            int r18 = 7 - r11
            int r18 = r9 << r18
            com.google.android.exoplayer2.util.ParsableByteArray r12 = r0.scratch
            byte[] r12 = r12.data
            byte r12 = r12[r15]
            r12 = r12 & r18
            if (r12 == 0) goto L_0x01dc
            int r6 = r6 + r11
            r0.readScratch(r3, r6)
            com.google.android.exoplayer2.util.ParsableByteArray r12 = r0.scratch
            byte[] r12 = r12.data
            int r16 = r15 + 1
            byte r12 = r12[r15]
            r12 = r12 & r14
            r15 = r18 ^ -1
            r12 = r12 & r15
            r19 = r10
            long r9 = (long) r12
            r22 = r9
            r9 = r16
            r16 = r22
        L_0x01ba:
            if (r9 >= r6) goto L_0x01ce
            long r16 = r16 << r8
            com.google.android.exoplayer2.util.ParsableByteArray r10 = r0.scratch
            byte[] r10 = r10.data
            int r12 = r9 + 1
            byte r9 = r10[r9]
            r9 = r9 & r14
            long r9 = (long) r9
            long r20 = r16 | r9
            r9 = r12
            r16 = r20
            goto L_0x01ba
        L_0x01ce:
            if (r5 <= 0) goto L_0x01e5
            int r11 = r11 * 7
            int r11 = r11 + r13
            r9 = 1
            long r11 = r9 << r11
            long r20 = r11 - r9
            long r9 = r16 - r20
            goto L_0x01e7
        L_0x01dc:
            r19 = r10
            int r11 = r11 + 1
            r9 = 1
            r12 = 2
            goto L_0x0191
        L_0x01e3:
            r19 = r10
        L_0x01e5:
            r9 = r16
        L_0x01e7:
            r11 = -2147483648(0xffffffff80000000, double:NaN)
            int r15 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r15 < 0) goto L_0x0211
            r11 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r15 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r15 <= 0) goto L_0x01f6
            goto L_0x0211
        L_0x01f6:
            int r9 = (int) r9
            int[] r10 = r0.blockLacingSampleSizes
            if (r5 != 0) goto L_0x01fc
            goto L_0x0203
        L_0x01fc:
            int[] r11 = r0.blockLacingSampleSizes
            int r12 = r5 + -1
            r11 = r11[r12]
            int r9 = r9 + r11
        L_0x0203:
            r10[r5] = r9
            int[] r9 = r0.blockLacingSampleSizes
            r9 = r9[r5]
            int r10 = r19 + r9
            int r5 = r5 + 1
            r9 = 1
            r12 = 2
            goto L_0x016e
        L_0x0211:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "EBML lacing sample size out of range."
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x0219:
            r19 = r10
            int[] r5 = r0.blockLacingSampleSizes
            int r9 = r0.blockLacingSampleCount
            r10 = 1
            int r9 = r9 - r10
            int r10 = r0.blockTrackNumberLength
            int r2 = r2 - r10
            int r2 = r2 - r6
            int r2 = r2 - r19
            r5[r9] = r2
        L_0x0229:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.scratch
            byte[] r2 = r2.data
            byte r2 = r2[r7]
            int r2 = r2 << r8
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r0.scratch
            byte[] r5 = r5.data
            r6 = 1
            byte r5 = r5[r6]
            r5 = r5 & r14
            r2 = r2 | r5
            long r5 = r0.clusterTimecodeUs
            long r9 = (long) r2
            long r9 = r0.scaleTimecodeToUs(r9)
            long r11 = r5 + r9
            r0.blockTimeUs = r11
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.scratch
            byte[] r2 = r2.data
            r5 = 2
            byte r2 = r2[r5]
            r2 = r2 & r8
            if (r2 != r8) goto L_0x0250
            r2 = 1
            goto L_0x0251
        L_0x0250:
            r2 = r7
        L_0x0251:
            int r6 = r4.type
            if (r6 == r5) goto L_0x0267
            r6 = 163(0xa3, float:2.28E-43)
            if (r1 != r6) goto L_0x0265
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r0.scratch
            byte[] r6 = r6.data
            byte r6 = r6[r5]
            r5 = 128(0x80, float:1.794E-43)
            r6 = r6 & r5
            if (r6 != r5) goto L_0x0265
            goto L_0x0267
        L_0x0265:
            r5 = r7
            goto L_0x0268
        L_0x0267:
            r5 = 1
        L_0x0268:
            if (r2 == 0) goto L_0x026d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x026e
        L_0x026d:
            r2 = r7
        L_0x026e:
            r2 = r2 | r5
            r0.blockFlags = r2
            r2 = 2
            r0.blockState = r2
            r0.blockLacingSampleIndex = r7
            r2 = 163(0xa3, float:2.28E-43)
            goto L_0x0291
        L_0x0279:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unexpected lacing value: "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x0290:
            r2 = r5
        L_0x0291:
            if (r1 != r2) goto L_0x02bb
        L_0x0293:
            int r1 = r0.blockLacingSampleIndex
            int r2 = r0.blockLacingSampleCount
            if (r1 >= r2) goto L_0x02b8
            int[] r1 = r0.blockLacingSampleSizes
            int r2 = r0.blockLacingSampleIndex
            r1 = r1[r2]
            r0.writeSampleData(r3, r4, r1)
            long r1 = r0.blockTimeUs
            int r5 = r0.blockLacingSampleIndex
            int r6 = r4.defaultSampleDurationNs
            int r5 = r5 * r6
            int r5 = r5 / 1000
            long r5 = (long) r5
            long r8 = r1 + r5
            r0.commitSampleToOutput(r4, r8)
            int r1 = r0.blockLacingSampleIndex
            r2 = 1
            int r1 = r1 + r2
            r0.blockLacingSampleIndex = r1
            goto L_0x0293
        L_0x02b8:
            r0.blockState = r7
            goto L_0x02c2
        L_0x02bb:
            int[] r1 = r0.blockLacingSampleSizes
            r1 = r1[r7]
            r0.writeSampleData(r3, r4, r1)
        L_0x02c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.binaryElement(int, int, com.google.android.exoplayer2.extractor.ExtractorInput):void");
    }

    private void commitSampleToOutput(Track track, long j) {
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubripSample(track);
        }
        track.output.sampleMetadata(j, this.blockFlags, this.sampleBytesWritten, 0, track.encryptionKeyId);
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                this.scratch.reset(Arrays.copyOf(this.scratch.data, Math.max(this.scratch.data.length * 2, i)), this.scratch.limit());
            }
            extractorInput.readFully(this.scratch.data, this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i) throws IOException, InterruptedException {
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            int length = SUBRIP_PREFIX.length + i;
            if (this.subripSample.capacity() < length) {
                this.subripSample.data = Arrays.copyOf(SUBRIP_PREFIX, length + i);
            }
            extractorInput.readFully(this.subripSample.data, SUBRIP_PREFIX.length, i);
            this.subripSample.setPosition(0);
            this.subripSample.setLimit(length);
            return;
        }
        TrackOutput trackOutput = track.output;
        if (!this.sampleEncodingHandled) {
            if (track.hasContentEncryption) {
                this.blockFlags &= -1073741825;
                int i2 = 128;
                if (!this.sampleSignalByteRead) {
                    extractorInput.readFully(this.scratch.data, 0, 1);
                    this.sampleBytesRead++;
                    if ((this.scratch.data[0] & 128) == 128) {
                        throw new ParserException("Extension bit is set in signal byte");
                    }
                    this.sampleSignalByte = this.scratch.data[0];
                    this.sampleSignalByteRead = true;
                }
                if ((this.sampleSignalByte & 1) == 1) {
                    boolean z = (this.sampleSignalByte & 2) == 2;
                    this.blockFlags |= 1073741824;
                    if (!this.sampleInitializationVectorRead) {
                        extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                        this.sampleBytesRead += 8;
                        this.sampleInitializationVectorRead = true;
                        byte[] bArr = this.scratch.data;
                        if (!z) {
                            i2 = 0;
                        }
                        bArr[0] = (byte) (i2 | 8);
                        this.scratch.setPosition(0);
                        trackOutput.sampleData(this.scratch, 1);
                        this.sampleBytesWritten++;
                        this.encryptionInitializationVector.setPosition(0);
                        trackOutput.sampleData(this.encryptionInitializationVector, 8);
                        this.sampleBytesWritten += 8;
                    }
                    if (z) {
                        if (!this.samplePartitionCountRead) {
                            extractorInput.readFully(this.scratch.data, 0, 1);
                            this.sampleBytesRead++;
                            this.scratch.setPosition(0);
                            this.samplePartitionCount = this.scratch.readUnsignedByte();
                            this.samplePartitionCountRead = true;
                        }
                        int i3 = this.samplePartitionCount * 4;
                        this.scratch.reset(i3);
                        extractorInput.readFully(this.scratch.data, 0, i3);
                        this.sampleBytesRead += i3;
                        short s = (short) ((this.samplePartitionCount / 2) + 1);
                        int i4 = (6 * s) + 2;
                        if (this.encryptionSubsampleDataBuffer == null || this.encryptionSubsampleDataBuffer.capacity() < i4) {
                            this.encryptionSubsampleDataBuffer = java.nio.ByteBuffer.allocate(i4);
                        }
                        this.encryptionSubsampleDataBuffer.position(0);
                        this.encryptionSubsampleDataBuffer.putShort(s);
                        int i5 = 0;
                        int i6 = 0;
                        while (i5 < this.samplePartitionCount) {
                            int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                            if (i5 % 2 == 0) {
                                this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i6));
                            } else {
                                this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i6);
                            }
                            i5++;
                            i6 = readUnsignedIntToInt;
                        }
                        int i7 = (i - this.sampleBytesRead) - i6;
                        if (this.samplePartitionCount % 2 == 1) {
                            this.encryptionSubsampleDataBuffer.putInt(i7);
                        } else {
                            this.encryptionSubsampleDataBuffer.putShort((short) i7);
                            this.encryptionSubsampleDataBuffer.putInt(0);
                        }
                        this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i4);
                        trackOutput.sampleData(this.encryptionSubsampleData, i4);
                        this.sampleBytesWritten += i4;
                    }
                }
            } else if (track.sampleStrippedBytes != null) {
                this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
            }
            this.sampleEncodingHandled = true;
        }
        int limit = i + this.sampleStrippedBytes.limit();
        if (CODEC_ID_H264.equals(track.codecId) || CODEC_ID_H265.equals(track.codecId)) {
            byte[] bArr2 = this.nalLength.data;
            bArr2[0] = 0;
            bArr2[1] = 0;
            bArr2[2] = 0;
            int i8 = track.nalUnitLengthFieldLength;
            int i9 = 4 - track.nalUnitLengthFieldLength;
            while (this.sampleBytesRead < limit) {
                if (this.sampleCurrentNalBytesRemaining == 0) {
                    readToTarget(extractorInput, bArr2, i9, i8);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                } else {
                    this.sampleCurrentNalBytesRemaining -= readToOutput(extractorInput, trackOutput, this.sampleCurrentNalBytesRemaining);
                }
            }
        } else {
            while (this.sampleBytesRead < limit) {
                readToOutput(extractorInput, trackOutput, limit - this.sampleBytesRead);
            }
        }
        if (CODEC_ID_VORBIS.equals(track.codecId)) {
            this.vorbisNumPageSamples.setPosition(0);
            trackOutput.sampleData(this.vorbisNumPageSamples, 4);
            this.sampleBytesWritten += 4;
        }
    }

    private void writeSubripSample(Track track) {
        setSubripSampleEndTimecode(this.subripSample.data, this.blockDurationUs);
        track.output.sampleData(this.subripSample, this.subripSample.limit());
        this.sampleBytesWritten += this.subripSample.limit();
    }

    private static void setSubripSampleEndTimecode(byte[] bArr, long j) {
        byte[] bArr2;
        if (j == C.TIME_UNSET) {
            bArr2 = SUBRIP_TIMECODE_EMPTY;
        } else {
            int i = (int) (j / 3600000000L);
            long j2 = j - (((long) i) * 3600000000L);
            int i2 = (int) (j2 / 60000000);
            long j3 = j2 - ((long) (60000000 * i2));
            int i3 = (int) (j3 / C.MICROS_PER_SECOND);
            bArr2 = Util.getUtf8Bytes(String.format(Locale.US, "%02d:%02d:%02d,%03d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j3 - ((long) (1000000 * i3))) / 1000))}));
        }
        System.arraycopy(bArr2, 0, bArr, 19, 12);
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
        this.sampleBytesRead += i2;
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException, InterruptedException {
        int i2;
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft > 0) {
            i2 = Math.min(i, bytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, i2);
        } else {
            i2 = trackOutput.sampleData(extractorInput, i, false);
        }
        this.sampleBytesRead += i2;
        this.sampleBytesWritten += i2;
        return i2;
    }

    private SeekMap buildSeekMap() {
        if (this.segmentContentPosition == -1 || this.durationUs == C.TIME_UNSET || this.cueTimesUs == null || this.cueTimesUs.size() == 0 || this.cueClusterPositions == null || this.cueClusterPositions.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jArr3[i2] = this.cueTimesUs.get(i2);
            jArr[i2] = this.segmentContentPosition + this.cueClusterPositions.get(i2);
        }
        while (true) {
            int i3 = size - 1;
            if (i < i3) {
                int i4 = i + 1;
                iArr[i] = (int) (jArr[i4] - jArr[i]);
                jArr2[i] = jArr3[i4] - jArr3[i];
                i = i4;
            } else {
                iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
                jArr2[i3] = this.durationUs - jArr3[i3];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        } else if (!this.sentSeekMap || this.seekPositionAfterBuildingCues == -1) {
            return false;
        } else {
            positionHolder.position = this.seekPositionAfterBuildingCues;
            this.seekPositionAfterBuildingCues = -1;
            return true;
        }
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        if (this.timecodeScale == C.TIME_UNSET) {
            throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
        }
        return Util.scaleLargeTimestamp(j, this.timecodeScale, 1000);
    }

    private static boolean isCodecSupported(String str) {
        return CODEC_ID_VP8.equals(str) || CODEC_ID_VP9.equals(str) || CODEC_ID_MPEG2.equals(str) || CODEC_ID_MPEG4_SP.equals(str) || CODEC_ID_MPEG4_ASP.equals(str) || CODEC_ID_MPEG4_AP.equals(str) || CODEC_ID_H264.equals(str) || CODEC_ID_H265.equals(str) || CODEC_ID_FOURCC.equals(str) || CODEC_ID_THEORA.equals(str) || CODEC_ID_OPUS.equals(str) || CODEC_ID_VORBIS.equals(str) || CODEC_ID_AAC.equals(str) || CODEC_ID_MP2.equals(str) || CODEC_ID_MP3.equals(str) || CODEC_ID_AC3.equals(str) || CODEC_ID_E_AC3.equals(str) || CODEC_ID_TRUEHD.equals(str) || CODEC_ID_DTS.equals(str) || CODEC_ID_DTS_EXPRESS.equals(str) || CODEC_ID_DTS_LOSSLESS.equals(str) || CODEC_ID_FLAC.equals(str) || CODEC_ID_ACM.equals(str) || CODEC_ID_PCM_INT_LIT.equals(str) || CODEC_ID_SUBRIP.equals(str) || CODEC_ID_VOBSUB.equals(str) || CODEC_ID_PGS.equals(str) || CODEC_ID_DVBSUB.equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }

    private final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        private InnerEbmlReaderOutput() {
        }

        public int getElementType(int i) {
            return MatroskaExtractor.this.getElementType(i);
        }

        public boolean isLevel1Element(int i) {
            return MatroskaExtractor.this.isLevel1Element(i);
        }

        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    private static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public byte[] encryptionKeyId;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        /* access modifiers changed from: private */
        public String language;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:113:0x0241, code lost:
            r12 = r1;
            r15 = 4096;
            r18 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:115:0x024f, code lost:
            r12 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:118:0x029d, code lost:
            r12 = r1;
            r15 = r2;
            r2 = r3;
            r18 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:127:0x02ee, code lost:
            r12 = r1;
            r2 = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:128:0x02f0, code lost:
            r15 = -1;
            r18 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:136:0x030d, code lost:
            r12 = r1;
            r15 = -1;
            r18 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:137:0x0311, code lost:
            r2 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:138:0x0312, code lost:
            r1 = r0.flagDefault | false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:139:0x0317, code lost:
            if (r0.flagForced == false) goto L_0x031a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:140:0x0319, code lost:
            r6 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:141:0x031a, code lost:
            r1 = r1 | r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:142:0x031f, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.isAudio(r12) == false) goto L_0x0342;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:143:0x0321, code lost:
            r1 = com.google.android.exoplayer2.Format.createAudioSampleFormat(java.lang.Integer.toString(r28), r12, (java.lang.String) null, -1, r15, r0.channelCount, r0.sampleRate, r18, r2, r0.drmInitData, r1 ? 1 : 0, r0.language);
            r8 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:145:0x0346, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.isVideo(r12) == false) goto L_0x03b6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:147:0x034a, code lost:
            if (r0.displayUnit != 0) goto L_0x0362;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:149:0x034e, code lost:
            if (r0.displayWidth != -1) goto L_0x0353;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:150:0x0350, code lost:
            r1 = r0.width;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:151:0x0353, code lost:
            r1 = r0.displayWidth;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:152:0x0355, code lost:
            r0.displayWidth = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:153:0x0359, code lost:
            if (r0.displayHeight != -1) goto L_0x035e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:154:0x035b, code lost:
            r1 = r0.height;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:155:0x035e, code lost:
            r1 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:156:0x0360, code lost:
            r0.displayHeight = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:157:0x0362, code lost:
            r1 = -1.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:158:0x0366, code lost:
            if (r0.displayWidth == -1) goto L_0x0379;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:160:0x036a, code lost:
            if (r0.displayHeight == -1) goto L_0x0379;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:161:0x036c, code lost:
            r1 = ((float) (r0.height * r0.displayWidth)) / ((float) (r0.width * r0.displayHeight));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:162:0x0379, code lost:
            r21 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:163:0x037d, code lost:
            if (r0.hasColorInfo == false) goto L_0x038e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:164:0x037f, code lost:
            r10 = new com.google.android.exoplayer2.video.ColorInfo(r0.colorSpace, r0.colorRange, r0.colorTransfer, getHdrStaticInfo());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:165:0x038e, code lost:
            r1 = com.google.android.exoplayer2.Format.createVideoSampleFormat(java.lang.Integer.toString(r28), r12, (java.lang.String) null, -1, r15, r0.width, r0.height, -1.0f, r2, -1, r21, r0.projectionData, r0.stereoMode, r10, r0.drmInitData);
            r8 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:167:0x03bc, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_SUBRIP.equals(r12) == false) goto L_0x03d2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:168:0x03be, code lost:
            r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(java.lang.Integer.toString(r28), r12, (java.lang.String) null, -1, r1 ? 1 : 0, r0.language, r0.drmInitData);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:170:0x03d8, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_VOBSUB.equals(r12) != false) goto L_0x03f3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:172:0x03e0, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_PGS.equals(r12) != false) goto L_0x03f3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:174:0x03e8, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_DVBSUBS.equals(r12) == false) goto L_0x03eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:176:0x03f2, code lost:
            throw new com.google.android.exoplayer2.ParserException("Unexpected MIME type.");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:0x03f3, code lost:
            r1 = com.google.android.exoplayer2.Format.createImageSampleFormat(java.lang.Integer.toString(r28), r12, (java.lang.String) null, -1, r2, r0.language, r0.drmInitData);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:178:0x0406, code lost:
            r0.output = r27.track(r0.number, r8);
            r0.output.format(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:179:0x0415, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:98:0x01c4, code lost:
            r12 = r1;
            r18 = r2;
            r15 = -1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput r27, int r28) throws com.google.android.exoplayer2.ParserException {
            /*
                r26 = this;
                r0 = r26
                java.lang.String r1 = r0.codecId
                int r2 = r1.hashCode()
                r3 = 4
                r4 = 8
                r5 = 1
                r6 = 0
                r7 = 2
                r8 = 3
                r9 = -1
                switch(r2) {
                    case -2095576542: goto L_0x0149;
                    case -2095575984: goto L_0x013f;
                    case -1985379776: goto L_0x0134;
                    case -1784763192: goto L_0x0129;
                    case -1730367663: goto L_0x011e;
                    case -1482641358: goto L_0x0113;
                    case -1482641357: goto L_0x0108;
                    case -1373388978: goto L_0x00fe;
                    case -933872740: goto L_0x00f3;
                    case -538363189: goto L_0x00e8;
                    case -538363109: goto L_0x00dd;
                    case -425012669: goto L_0x00d1;
                    case -356037306: goto L_0x00c5;
                    case 62923557: goto L_0x00b9;
                    case 62923603: goto L_0x00ad;
                    case 62927045: goto L_0x00a1;
                    case 82338133: goto L_0x0096;
                    case 82338134: goto L_0x008b;
                    case 99146302: goto L_0x007f;
                    case 444813526: goto L_0x0073;
                    case 542569478: goto L_0x0067;
                    case 725957860: goto L_0x005b;
                    case 855502857: goto L_0x0050;
                    case 1422270023: goto L_0x0044;
                    case 1809237540: goto L_0x0039;
                    case 1950749482: goto L_0x002d;
                    case 1950789798: goto L_0x0021;
                    case 1951062397: goto L_0x0015;
                    default: goto L_0x0013;
                }
            L_0x0013:
                goto L_0x0153
            L_0x0015:
                java.lang.String r2 = "A_OPUS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 11
                goto L_0x0154
            L_0x0021:
                java.lang.String r2 = "A_FLAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 21
                goto L_0x0154
            L_0x002d:
                java.lang.String r2 = "A_EAC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 16
                goto L_0x0154
            L_0x0039:
                java.lang.String r2 = "V_MPEG2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = r7
                goto L_0x0154
            L_0x0044:
                java.lang.String r2 = "S_TEXT/UTF8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 24
                goto L_0x0154
            L_0x0050:
                java.lang.String r2 = "V_MPEGH/ISO/HEVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 7
                goto L_0x0154
            L_0x005b:
                java.lang.String r2 = "A_PCM/INT/LIT"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 23
                goto L_0x0154
            L_0x0067:
                java.lang.String r2 = "A_DTS/EXPRESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 19
                goto L_0x0154
            L_0x0073:
                java.lang.String r2 = "V_THEORA"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 9
                goto L_0x0154
            L_0x007f:
                java.lang.String r2 = "S_HDMV/PGS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 26
                goto L_0x0154
            L_0x008b:
                java.lang.String r2 = "V_VP9"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = r5
                goto L_0x0154
            L_0x0096:
                java.lang.String r2 = "V_VP8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = r6
                goto L_0x0154
            L_0x00a1:
                java.lang.String r2 = "A_DTS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 18
                goto L_0x0154
            L_0x00ad:
                java.lang.String r2 = "A_AC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 15
                goto L_0x0154
            L_0x00b9:
                java.lang.String r2 = "A_AAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 12
                goto L_0x0154
            L_0x00c5:
                java.lang.String r2 = "A_DTS/LOSSLESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 20
                goto L_0x0154
            L_0x00d1:
                java.lang.String r2 = "S_VOBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 25
                goto L_0x0154
            L_0x00dd:
                java.lang.String r2 = "V_MPEG4/ISO/AVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 6
                goto L_0x0154
            L_0x00e8:
                java.lang.String r2 = "V_MPEG4/ISO/ASP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = r3
                goto L_0x0154
            L_0x00f3:
                java.lang.String r2 = "S_DVBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 27
                goto L_0x0154
            L_0x00fe:
                java.lang.String r2 = "V_MS/VFW/FOURCC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = r4
                goto L_0x0154
            L_0x0108:
                java.lang.String r2 = "A_MPEG/L3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 14
                goto L_0x0154
            L_0x0113:
                java.lang.String r2 = "A_MPEG/L2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 13
                goto L_0x0154
            L_0x011e:
                java.lang.String r2 = "A_VORBIS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 10
                goto L_0x0154
            L_0x0129:
                java.lang.String r2 = "A_TRUEHD"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 17
                goto L_0x0154
            L_0x0134:
                java.lang.String r2 = "A_MS/ACM"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 22
                goto L_0x0154
            L_0x013f:
                java.lang.String r2 = "V_MPEG4/ISO/SP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = r8
                goto L_0x0154
            L_0x0149:
                java.lang.String r2 = "V_MPEG4/ISO/AP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0153
                r1 = 5
                goto L_0x0154
            L_0x0153:
                r1 = r9
            L_0x0154:
                r2 = 4096(0x1000, float:5.74E-42)
                r10 = 0
                switch(r1) {
                    case 0: goto L_0x030b;
                    case 1: goto L_0x0308;
                    case 2: goto L_0x0305;
                    case 3: goto L_0x02f4;
                    case 4: goto L_0x02f4;
                    case 5: goto L_0x02f4;
                    case 6: goto L_0x02db;
                    case 7: goto L_0x02c7;
                    case 8: goto L_0x02a8;
                    case 9: goto L_0x02a4;
                    case 10: goto L_0x0293;
                    case 11: goto L_0x0252;
                    case 12: goto L_0x0247;
                    case 13: goto L_0x023f;
                    case 14: goto L_0x023c;
                    case 15: goto L_0x0238;
                    case 16: goto L_0x0234;
                    case 17: goto L_0x0230;
                    case 18: goto L_0x022c;
                    case 19: goto L_0x022c;
                    case 20: goto L_0x0228;
                    case 21: goto L_0x021f;
                    case 22: goto L_0x01ca;
                    case 23: goto L_0x0196;
                    case 24: goto L_0x0192;
                    case 25: goto L_0x0188;
                    case 26: goto L_0x0184;
                    case 27: goto L_0x0162;
                    default: goto L_0x015a;
                }
            L_0x015a:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Unrecognized codec identifier."
                r1.<init>((java.lang.String) r2)
                throw r1
            L_0x0162:
                java.lang.String r1 = "application/dvbsubs"
                byte[] r2 = new byte[r3]
                byte[] r3 = r0.codecPrivate
                byte r3 = r3[r6]
                r2[r6] = r3
                byte[] r3 = r0.codecPrivate
                byte r3 = r3[r5]
                r2[r5] = r3
                byte[] r3 = r0.codecPrivate
                byte r3 = r3[r7]
                r2[r7] = r3
                byte[] r3 = r0.codecPrivate
                byte r3 = r3[r8]
                r2[r8] = r3
                java.util.List r2 = java.util.Collections.singletonList(r2)
                goto L_0x024f
            L_0x0184:
                java.lang.String r1 = "application/pgs"
                goto L_0x030d
            L_0x0188:
                java.lang.String r1 = "application/vobsub"
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
                goto L_0x024f
            L_0x0192:
                java.lang.String r1 = "application/x-subrip"
                goto L_0x030d
            L_0x0196:
                java.lang.String r1 = "audio/raw"
                int r2 = r0.audioBitDepth
                int r2 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r2)
                if (r2 != 0) goto L_0x01c4
                java.lang.String r1 = "audio/x-unknown"
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Unsupported PCM bit depth: "
                r3.append(r4)
                int r4 = r0.audioBitDepth
                r3.append(r4)
                java.lang.String r4 = ". Setting mimeType to "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                android.util.Log.w(r2, r3)
                goto L_0x030d
            L_0x01c4:
                r12 = r1
                r18 = r2
                r15 = r9
                goto L_0x0311
            L_0x01ca:
                java.lang.String r1 = "audio/raw"
                com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r2.<init>((byte[]) r3)
                boolean r2 = parseMsAcmCodecPrivate(r2)
                if (r2 == 0) goto L_0x0205
                int r2 = r0.audioBitDepth
                int r2 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r2)
                if (r2 != 0) goto L_0x01c4
                java.lang.String r1 = "audio/x-unknown"
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Unsupported PCM bit depth: "
                r3.append(r4)
                int r4 = r0.audioBitDepth
                r3.append(r4)
                java.lang.String r4 = ". Setting mimeType to "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                android.util.Log.w(r2, r3)
                goto L_0x030d
            L_0x0205:
                java.lang.String r1 = "audio/x-unknown"
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Non-PCM MS/ACM is unsupported. Setting mimeType to "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                android.util.Log.w(r2, r3)
                goto L_0x030d
            L_0x021f:
                java.lang.String r1 = "audio/x-flac"
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
                goto L_0x024f
            L_0x0228:
                java.lang.String r1 = "audio/vnd.dts.hd"
                goto L_0x030d
            L_0x022c:
                java.lang.String r1 = "audio/vnd.dts"
                goto L_0x030d
            L_0x0230:
                java.lang.String r1 = "audio/true-hd"
                goto L_0x030d
            L_0x0234:
                java.lang.String r1 = "audio/eac3"
                goto L_0x030d
            L_0x0238:
                java.lang.String r1 = "audio/ac3"
                goto L_0x030d
            L_0x023c:
                java.lang.String r1 = "audio/mpeg"
                goto L_0x0241
            L_0x023f:
                java.lang.String r1 = "audio/mpeg-L2"
            L_0x0241:
                r12 = r1
                r15 = r2
                r18 = r9
                goto L_0x0311
            L_0x0247:
                java.lang.String r1 = "audio/mp4a-latm"
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
            L_0x024f:
                r12 = r1
                goto L_0x02f0
            L_0x0252:
                java.lang.String r1 = "audio/opus"
                r2 = 5760(0x1680, float:8.071E-42)
                java.util.ArrayList r3 = new java.util.ArrayList
                r3.<init>(r8)
                byte[] r11 = r0.codecPrivate
                r3.add(r11)
                java.nio.ByteBuffer r11 = java.nio.ByteBuffer.allocate(r4)
                java.nio.ByteOrder r12 = java.nio.ByteOrder.nativeOrder()
                java.nio.ByteBuffer r11 = r11.order(r12)
                long r12 = r0.codecDelayNs
                java.nio.ByteBuffer r11 = r11.putLong(r12)
                byte[] r11 = r11.array()
                r3.add(r11)
                java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r4)
                java.nio.ByteOrder r11 = java.nio.ByteOrder.nativeOrder()
                java.nio.ByteBuffer r4 = r4.order(r11)
                long r11 = r0.seekPreRollNs
                java.nio.ByteBuffer r4 = r4.putLong(r11)
                byte[] r4 = r4.array()
                r3.add(r4)
                goto L_0x029d
            L_0x0293:
                java.lang.String r1 = "audio/vorbis"
                r2 = 8192(0x2000, float:1.14794E-41)
                byte[] r3 = r0.codecPrivate
                java.util.List r3 = parseVorbisCodecPrivate(r3)
            L_0x029d:
                r12 = r1
                r15 = r2
                r2 = r3
                r18 = r9
                goto L_0x0312
            L_0x02a4:
                java.lang.String r1 = "video/x-unknown"
                goto L_0x030d
            L_0x02a8:
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r2 = r0.codecPrivate
                r1.<init>((byte[]) r2)
                java.util.List r1 = parseFourCcVc1Private(r1)
                if (r1 == 0) goto L_0x02bd
                java.lang.String r2 = "video/wvc1"
            L_0x02b7:
                r12 = r2
                r15 = r9
                r18 = r15
                r2 = r1
                goto L_0x0312
            L_0x02bd:
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.String r3 = "Unsupported FourCC. Setting mimeType to video/x-unknown"
                android.util.Log.w(r2, r3)
                java.lang.String r2 = "video/x-unknown"
                goto L_0x02b7
            L_0x02c7:
                java.lang.String r1 = "video/hevc"
                com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r2.<init>((byte[]) r3)
                com.google.android.exoplayer2.video.HevcConfig r2 = com.google.android.exoplayer2.video.HevcConfig.parse(r2)
                java.util.List<byte[]> r3 = r2.initializationData
                int r2 = r2.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r2
                goto L_0x02ee
            L_0x02db:
                java.lang.String r1 = "video/avc"
                com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r2.<init>((byte[]) r3)
                com.google.android.exoplayer2.video.AvcConfig r2 = com.google.android.exoplayer2.video.AvcConfig.parse(r2)
                java.util.List<byte[]> r3 = r2.initializationData
                int r2 = r2.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r2
            L_0x02ee:
                r12 = r1
                r2 = r3
            L_0x02f0:
                r15 = r9
                r18 = r15
                goto L_0x0312
            L_0x02f4:
                java.lang.String r1 = "video/mp4v-es"
                byte[] r2 = r0.codecPrivate
                if (r2 != 0) goto L_0x02fd
                r2 = r10
                goto L_0x024f
            L_0x02fd:
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
                goto L_0x024f
            L_0x0305:
                java.lang.String r1 = "video/mpeg2"
                goto L_0x030d
            L_0x0308:
                java.lang.String r1 = "video/x-vnd.on2.vp9"
                goto L_0x030d
            L_0x030b:
                java.lang.String r1 = "video/x-vnd.on2.vp8"
            L_0x030d:
                r12 = r1
                r15 = r9
                r18 = r15
            L_0x0311:
                r2 = r10
            L_0x0312:
                boolean r1 = r0.flagDefault
                r1 = r1 | r6
                boolean r3 = r0.flagForced
                if (r3 == 0) goto L_0x031a
                r6 = r7
            L_0x031a:
                r1 = r1 | r6
                boolean r3 = com.google.android.exoplayer2.util.MimeTypes.isAudio(r12)
                if (r3 == 0) goto L_0x0342
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                int r3 = r0.channelCount
                int r4 = r0.sampleRate
                com.google.android.exoplayer2.drm.DrmInitData r6 = r0.drmInitData
                java.lang.String r7 = r0.language
                r16 = r3
                r17 = r4
                r19 = r2
                r20 = r6
                r21 = r1
                r22 = r7
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createAudioSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
                r8 = r5
                goto L_0x0406
            L_0x0342:
                boolean r3 = com.google.android.exoplayer2.util.MimeTypes.isVideo(r12)
                if (r3 == 0) goto L_0x03b6
                int r1 = r0.displayUnit
                if (r1 != 0) goto L_0x0362
                int r1 = r0.displayWidth
                if (r1 != r9) goto L_0x0353
                int r1 = r0.width
                goto L_0x0355
            L_0x0353:
                int r1 = r0.displayWidth
            L_0x0355:
                r0.displayWidth = r1
                int r1 = r0.displayHeight
                if (r1 != r9) goto L_0x035e
                int r1 = r0.height
                goto L_0x0360
            L_0x035e:
                int r1 = r0.displayHeight
            L_0x0360:
                r0.displayHeight = r1
            L_0x0362:
                r1 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r3 = r0.displayWidth
                if (r3 == r9) goto L_0x0379
                int r3 = r0.displayHeight
                if (r3 == r9) goto L_0x0379
                int r1 = r0.height
                int r3 = r0.displayWidth
                int r1 = r1 * r3
                float r1 = (float) r1
                int r3 = r0.width
                int r4 = r0.displayHeight
                int r3 = r3 * r4
                float r3 = (float) r3
                float r1 = r1 / r3
            L_0x0379:
                r21 = r1
                boolean r1 = r0.hasColorInfo
                if (r1 == 0) goto L_0x038e
                byte[] r1 = r26.getHdrStaticInfo()
                com.google.android.exoplayer2.video.ColorInfo r10 = new com.google.android.exoplayer2.video.ColorInfo
                int r3 = r0.colorSpace
                int r4 = r0.colorRange
                int r5 = r0.colorTransfer
                r10.<init>(r3, r4, r5, r1)
            L_0x038e:
                r24 = r10
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                int r1 = r0.width
                int r3 = r0.height
                r18 = -1082130432(0xffffffffbf800000, float:-1.0)
                r20 = -1
                byte[] r4 = r0.projectionData
                int r5 = r0.stereoMode
                com.google.android.exoplayer2.drm.DrmInitData r6 = r0.drmInitData
                r16 = r1
                r17 = r3
                r19 = r2
                r22 = r4
                r23 = r5
                r25 = r6
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
                r8 = r7
                goto L_0x0406
            L_0x03b6:
                java.lang.String r3 = "application/x-subrip"
                boolean r3 = r3.equals(r12)
                if (r3 == 0) goto L_0x03d2
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                java.lang.String r2 = r0.language
                com.google.android.exoplayer2.drm.DrmInitData r3 = r0.drmInitData
                r15 = r1
                r16 = r2
                r17 = r3
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(r11, r12, r13, r14, r15, r16, r17)
                goto L_0x0406
            L_0x03d2:
                java.lang.String r1 = "application/vobsub"
                boolean r1 = r1.equals(r12)
                if (r1 != 0) goto L_0x03f3
                java.lang.String r1 = "application/pgs"
                boolean r1 = r1.equals(r12)
                if (r1 != 0) goto L_0x03f3
                java.lang.String r1 = "application/dvbsubs"
                boolean r1 = r1.equals(r12)
                if (r1 == 0) goto L_0x03eb
                goto L_0x03f3
            L_0x03eb:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Unexpected MIME type."
                r1.<init>((java.lang.String) r2)
                throw r1
            L_0x03f3:
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                java.lang.String r1 = r0.language
                com.google.android.exoplayer2.drm.DrmInitData r3 = r0.drmInitData
                r15 = r2
                r16 = r1
                r17 = r3
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createImageSampleFormat(r11, r12, r13, r14, r15, r16, r17)
            L_0x0406:
                int r2 = r0.number
                r3 = r27
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r3.track(r2, r8)
                r0.output = r2
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r0.output
                r2.format(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track.initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput, int):void");
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            java.nio.ByteBuffer wrap = java.nio.ByteBuffer.wrap(bArr);
            wrap.put((byte) 0);
            wrap.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            wrap.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            wrap.putShort((short) this.maxContentLuminance);
            wrap.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static List<byte[]> parseFourCcVc1Private(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                if (parsableByteArray.readLittleEndianUnsignedInt() != 826496599) {
                    return null;
                }
                byte[] bArr = parsableByteArray.data;
                for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                    if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length));
                    }
                }
                throw new ParserException("Failed to find FourCC VC1 initialization data");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC VC1 codec private");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] != 2) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i = 0;
                int i2 = 1;
                while (bArr[i2] == -1) {
                    i += 255;
                    i2++;
                }
                int i3 = i2 + 1;
                int i4 = i + bArr[i2];
                int i5 = 0;
                while (bArr[i3] == -1) {
                    i5 += 255;
                    i3++;
                }
                int i6 = i3 + 1;
                int i7 = i5 + bArr[i3];
                if (bArr[i6] != 1) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr2 = new byte[i4];
                System.arraycopy(bArr, i6, bArr2, 0, i4);
                int i8 = i6 + i4;
                if (bArr[i8] != 3) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                int i9 = i8 + i7;
                if (bArr[i9] != 5) {
                    throw new ParserException("Error parsing vorbis codec private");
                }
                byte[] bArr3 = new byte[(bArr.length - i9)];
                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != MatroskaExtractor.WAVE_FORMAT_EXTENSIBLE) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                    return true;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }
}
