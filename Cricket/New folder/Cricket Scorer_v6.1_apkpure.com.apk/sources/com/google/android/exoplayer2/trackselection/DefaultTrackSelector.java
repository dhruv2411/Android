package com.google.android.exoplayer2.trackselection;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultTrackSelector extends MappingTrackSelector {
    private static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;
    private static final int[] NO_TRACKS = new int[0];
    private static final int WITHIN_RENDERER_CAPABILITIES_BONUS = 1000;
    private final TrackSelection.Factory adaptiveTrackSelectionFactory;
    private final AtomicReference<Parameters> paramsReference;

    private static int compareFormatValues(int i, int i2) {
        if (i == -1) {
            return i2 == -1 ? 0 : -1;
        }
        if (i2 == -1) {
            return 1;
        }
        return i - i2;
    }

    protected static boolean isSupported(int i, boolean z) {
        int i2 = i & 3;
        return i2 == 3 || (z && i2 == 2);
    }

    public static final class Parameters {
        public final boolean allowMixedMimeAdaptiveness;
        public final boolean allowNonSeamlessAdaptiveness;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        public final int maxVideoBitrate;
        public final int maxVideoHeight;
        public final int maxVideoWidth;
        public final boolean orientationMayChange;
        public final String preferredAudioLanguage;
        public final String preferredTextLanguage;
        public final int viewportHeight;
        public final int viewportWidth;

        public Parameters() {
            this((String) null, (String) null, false, true, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        public Parameters(String str, String str2, boolean z, boolean z2, int i, int i2, int i3, boolean z3, boolean z4, int i4, int i5, boolean z5) {
            this.preferredAudioLanguage = str;
            this.preferredTextLanguage = str2;
            this.allowMixedMimeAdaptiveness = z;
            this.allowNonSeamlessAdaptiveness = z2;
            this.maxVideoWidth = i;
            this.maxVideoHeight = i2;
            this.maxVideoBitrate = i3;
            this.exceedVideoConstraintsIfNecessary = z3;
            this.exceedRendererCapabilitiesIfNecessary = z4;
            this.viewportWidth = i4;
            this.viewportHeight = i5;
            this.orientationMayChange = z5;
        }

        public Parameters withPreferredAudioLanguage(String str) {
            String normalizeLanguageCode = Util.normalizeLanguageCode(str);
            if (TextUtils.equals(normalizeLanguageCode, this.preferredAudioLanguage)) {
                return this;
            }
            return new Parameters(normalizeLanguageCode, this.preferredTextLanguage, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.orientationMayChange);
        }

        public Parameters withPreferredTextLanguage(String str) {
            String normalizeLanguageCode = Util.normalizeLanguageCode(str);
            if (TextUtils.equals(normalizeLanguageCode, this.preferredTextLanguage)) {
                return this;
            }
            return new Parameters(this.preferredAudioLanguage, normalizeLanguageCode, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.orientationMayChange);
        }

        public Parameters withAllowMixedMimeAdaptiveness(boolean z) {
            if (z == this.allowMixedMimeAdaptiveness) {
                return this;
            }
            return new Parameters(this.preferredAudioLanguage, this.preferredTextLanguage, z, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.orientationMayChange);
        }

        public Parameters withAllowNonSeamlessAdaptiveness(boolean z) {
            if (z == this.allowNonSeamlessAdaptiveness) {
                return this;
            }
            return new Parameters(this.preferredAudioLanguage, this.preferredTextLanguage, this.allowMixedMimeAdaptiveness, z, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.orientationMayChange);
        }

        public Parameters withMaxVideoSize(int i, int i2) {
            int i3;
            int i4 = i;
            if (i4 == this.maxVideoWidth) {
                i3 = i2;
                if (i3 == this.maxVideoHeight) {
                    return this;
                }
            } else {
                i3 = i2;
            }
            return new Parameters(this.preferredAudioLanguage, this.preferredTextLanguage, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, i4, i3, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.orientationMayChange);
        }

        public Parameters withMaxVideoBitrate(int i) {
            if (i == this.maxVideoBitrate) {
                return this;
            }
            return new Parameters(this.preferredAudioLanguage, this.preferredTextLanguage, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, i, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.orientationMayChange);
        }

        public Parameters withMaxVideoSizeSd() {
            return withMaxVideoSize(1279, 719);
        }

        public Parameters withoutVideoSizeConstraints() {
            return withMaxVideoSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        public Parameters withExceedVideoConstraintsIfNecessary(boolean z) {
            if (z == this.exceedVideoConstraintsIfNecessary) {
                return this;
            }
            return new Parameters(this.preferredAudioLanguage, this.preferredTextLanguage, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, z, this.exceedRendererCapabilitiesIfNecessary, this.viewportWidth, this.viewportHeight, this.orientationMayChange);
        }

        public Parameters withExceedRendererCapabilitiesIfNecessary(boolean z) {
            if (z == this.exceedRendererCapabilitiesIfNecessary) {
                return this;
            }
            return new Parameters(this.preferredAudioLanguage, this.preferredTextLanguage, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, z, this.viewportWidth, this.viewportHeight, this.orientationMayChange);
        }

        public Parameters withViewportSize(int i, int i2, boolean z) {
            boolean z2;
            int i3;
            int i4 = i;
            if (i4 == this.viewportWidth) {
                i3 = i2;
                if (i3 == this.viewportHeight) {
                    z2 = z;
                    if (z2 == this.orientationMayChange) {
                        return this;
                    }
                    return new Parameters(this.preferredAudioLanguage, this.preferredTextLanguage, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, i4, i3, z2);
                }
            } else {
                i3 = i2;
            }
            z2 = z;
            return new Parameters(this.preferredAudioLanguage, this.preferredTextLanguage, this.allowMixedMimeAdaptiveness, this.allowNonSeamlessAdaptiveness, this.maxVideoWidth, this.maxVideoHeight, this.maxVideoBitrate, this.exceedVideoConstraintsIfNecessary, this.exceedRendererCapabilitiesIfNecessary, i4, i3, z2);
        }

        public Parameters withViewportSizeFromContext(Context context, boolean z) {
            Point physicalDisplaySize = Util.getPhysicalDisplaySize(context);
            return withViewportSize(physicalDisplaySize.x, physicalDisplaySize.y, z);
        }

        public Parameters withoutViewportSizeConstraints() {
            return withViewportSize(Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Parameters parameters = (Parameters) obj;
            if (this.allowMixedMimeAdaptiveness == parameters.allowMixedMimeAdaptiveness && this.allowNonSeamlessAdaptiveness == parameters.allowNonSeamlessAdaptiveness && this.maxVideoWidth == parameters.maxVideoWidth && this.maxVideoHeight == parameters.maxVideoHeight && this.exceedVideoConstraintsIfNecessary == parameters.exceedVideoConstraintsIfNecessary && this.exceedRendererCapabilitiesIfNecessary == parameters.exceedRendererCapabilitiesIfNecessary && this.orientationMayChange == parameters.orientationMayChange && this.viewportWidth == parameters.viewportWidth && this.viewportHeight == parameters.viewportHeight && this.maxVideoBitrate == parameters.maxVideoBitrate && TextUtils.equals(this.preferredAudioLanguage, parameters.preferredAudioLanguage) && TextUtils.equals(this.preferredTextLanguage, parameters.preferredTextLanguage)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (31 * ((((((((((((((((((((this.preferredAudioLanguage.hashCode() * 31) + this.preferredTextLanguage.hashCode()) * 31) + (this.allowMixedMimeAdaptiveness ? 1 : 0)) * 31) + (this.allowNonSeamlessAdaptiveness ? 1 : 0)) * 31) + this.maxVideoWidth) * 31) + this.maxVideoHeight) * 31) + this.maxVideoBitrate) * 31) + (this.exceedVideoConstraintsIfNecessary ? 1 : 0)) * 31) + (this.exceedRendererCapabilitiesIfNecessary ? 1 : 0)) * 31) + (this.orientationMayChange ? 1 : 0)) * 31) + this.viewportWidth)) + this.viewportHeight;
        }
    }

    public DefaultTrackSelector() {
        this((TrackSelection.Factory) null);
    }

    public DefaultTrackSelector(BandwidthMeter bandwidthMeter) {
        this((TrackSelection.Factory) new AdaptiveTrackSelection.Factory(bandwidthMeter));
    }

    public DefaultTrackSelector(TrackSelection.Factory factory) {
        this.adaptiveTrackSelectionFactory = factory;
        this.paramsReference = new AtomicReference<>(new Parameters());
    }

    public void setParameters(Parameters parameters) {
        Assertions.checkNotNull(parameters);
        if (!this.paramsReference.getAndSet(parameters).equals(parameters)) {
            invalidate();
        }
    }

    public Parameters getParameters() {
        return this.paramsReference.get();
    }

    /* access modifiers changed from: protected */
    public TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr) throws ExoPlaybackException {
        Parameters parameters;
        Parameters parameters2;
        int i;
        RendererCapabilities[] rendererCapabilitiesArr2;
        TrackSelection[] trackSelectionArr;
        int i2;
        DefaultTrackSelector defaultTrackSelector = this;
        RendererCapabilities[] rendererCapabilitiesArr3 = rendererCapabilitiesArr;
        int length = rendererCapabilitiesArr3.length;
        TrackSelection[] trackSelectionArr2 = new TrackSelection[length];
        Parameters parameters3 = defaultTrackSelector.paramsReference.get();
        boolean z = false;
        int i3 = 0;
        boolean z2 = false;
        while (true) {
            boolean z3 = true;
            if (i3 >= length) {
                break;
            }
            if (2 == rendererCapabilitiesArr3[i3].getTrackType()) {
                if (!z) {
                    trackSelectionArr = trackSelectionArr2;
                    i = i3;
                    parameters2 = parameters3;
                    i2 = length;
                    rendererCapabilitiesArr2 = rendererCapabilitiesArr;
                    trackSelectionArr[i] = defaultTrackSelector.selectVideoTrack(rendererCapabilitiesArr3[i3], trackGroupArrayArr[i3], iArr[i3], parameters3.maxVideoWidth, parameters3.maxVideoHeight, parameters3.maxVideoBitrate, parameters3.allowNonSeamlessAdaptiveness, parameters3.allowMixedMimeAdaptiveness, parameters3.viewportWidth, parameters3.viewportHeight, parameters3.orientationMayChange, defaultTrackSelector.adaptiveTrackSelectionFactory, parameters3.exceedVideoConstraintsIfNecessary, parameters3.exceedRendererCapabilitiesIfNecessary);
                    z = trackSelectionArr[i] != null;
                } else {
                    i = i3;
                    parameters2 = parameters3;
                    trackSelectionArr = trackSelectionArr2;
                    i2 = length;
                    rendererCapabilitiesArr2 = rendererCapabilitiesArr3;
                }
                if (trackGroupArrayArr[i].length <= 0) {
                    z3 = false;
                }
                z2 |= z3;
            } else {
                i = i3;
                parameters2 = parameters3;
                trackSelectionArr = trackSelectionArr2;
                i2 = length;
                rendererCapabilitiesArr2 = rendererCapabilitiesArr3;
            }
            i3 = i + 1;
            length = i2;
            trackSelectionArr2 = trackSelectionArr;
            rendererCapabilitiesArr3 = rendererCapabilitiesArr2;
            parameters3 = parameters2;
            defaultTrackSelector = this;
        }
        Parameters parameters4 = parameters3;
        TrackSelection[] trackSelectionArr3 = trackSelectionArr2;
        int i4 = length;
        RendererCapabilities[] rendererCapabilitiesArr4 = rendererCapabilitiesArr3;
        boolean z4 = false;
        int i5 = 0;
        boolean z5 = false;
        while (i5 < i4) {
            switch (rendererCapabilitiesArr4[i5].getTrackType()) {
                case 1:
                    parameters = parameters4;
                    if (z4) {
                        break;
                    } else {
                        trackSelectionArr3[i5] = selectAudioTrack(trackGroupArrayArr[i5], iArr[i5], parameters.preferredAudioLanguage, parameters.exceedRendererCapabilitiesIfNecessary, parameters.allowMixedMimeAdaptiveness, z2 ? null : this.adaptiveTrackSelectionFactory);
                        z4 = trackSelectionArr3[i5] != null;
                        break;
                    }
                case 3:
                    if (!z5) {
                        Parameters parameters5 = parameters4;
                        parameters = parameters5;
                        trackSelectionArr3[i5] = selectTextTrack(trackGroupArrayArr[i5], iArr[i5], parameters5.preferredTextLanguage, parameters5.preferredAudioLanguage, parameters5.exceedRendererCapabilitiesIfNecessary);
                        z5 = trackSelectionArr3[i5] != null;
                        break;
                    }
                case 2:
                    parameters = parameters4;
                    break;
                default:
                    parameters = parameters4;
                    trackSelectionArr3[i5] = selectOtherTrack(rendererCapabilitiesArr4[i5].getTrackType(), trackGroupArrayArr[i5], iArr[i5], parameters.exceedRendererCapabilitiesIfNecessary);
                    break;
            }
            i5++;
            parameters4 = parameters;
        }
        return trackSelectionArr3;
    }

    /* access modifiers changed from: protected */
    public TrackSelection selectVideoTrack(RendererCapabilities rendererCapabilities, TrackGroupArray trackGroupArray, int[][] iArr, int i, int i2, int i3, boolean z, boolean z2, int i4, int i5, boolean z3, TrackSelection.Factory factory, boolean z4, boolean z5) throws ExoPlaybackException {
        TrackSelection selectAdaptiveVideoTrack = factory != null ? selectAdaptiveVideoTrack(rendererCapabilities, trackGroupArray, iArr, i, i2, i3, z, z2, i4, i5, z3, factory) : null;
        return selectAdaptiveVideoTrack == null ? selectFixedVideoTrack(trackGroupArray, iArr, i, i2, i3, i4, i5, z3, z4, z5) : selectAdaptiveVideoTrack;
    }

    private static TrackSelection selectAdaptiveVideoTrack(RendererCapabilities rendererCapabilities, TrackGroupArray trackGroupArray, int[][] iArr, int i, int i2, int i3, boolean z, boolean z2, int i4, int i5, boolean z3, TrackSelection.Factory factory) throws ExoPlaybackException {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        int i6 = z ? 12 : 8;
        boolean z4 = z2 && (rendererCapabilities.supportsMixedMimeTypeAdaptation() & i6) != 0;
        for (int i7 = 0; i7 < trackGroupArray2.length; i7++) {
            TrackGroup trackGroup = trackGroupArray2.get(i7);
            int[] adaptiveVideoTracksForGroup = getAdaptiveVideoTracksForGroup(trackGroup, iArr[i7], z4, i6, i, i2, i3, i4, i5, z3);
            if (adaptiveVideoTracksForGroup.length > 0) {
                return factory.createTrackSelection(trackGroup, adaptiveVideoTracksForGroup);
            }
            TrackSelection.Factory factory2 = factory;
        }
        return null;
    }

    private static int[] getAdaptiveVideoTracksForGroup(TrackGroup trackGroup, int[] iArr, boolean z, int i, int i2, int i3, int i4, int i5, int i6, boolean z2) {
        String str;
        int adaptiveVideoTrackCountForMimeType;
        TrackGroup trackGroup2 = trackGroup;
        if (trackGroup2.length < 2) {
            return NO_TRACKS;
        }
        List<Integer> viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup2, i5, i6, z2);
        if (viewportFilteredTrackIndices.size() < 2) {
            return NO_TRACKS;
        }
        if (!z) {
            HashSet hashSet = new HashSet();
            String str2 = null;
            int i7 = 0;
            for (int i8 = 0; i8 < viewportFilteredTrackIndices.size(); i8++) {
                String str3 = trackGroup2.getFormat(viewportFilteredTrackIndices.get(i8).intValue()).sampleMimeType;
                if (hashSet.add(str3) && (adaptiveVideoTrackCountForMimeType = getAdaptiveVideoTrackCountForMimeType(trackGroup2, iArr, i, str3, i2, i3, i4, viewportFilteredTrackIndices)) > i7) {
                    i7 = adaptiveVideoTrackCountForMimeType;
                    str2 = str3;
                }
            }
            str = str2;
        } else {
            str = null;
        }
        filterAdaptiveVideoTrackCountForMimeType(trackGroup2, iArr, i, str, i2, i3, i4, viewportFilteredTrackIndices);
        return viewportFilteredTrackIndices.size() < 2 ? NO_TRACKS : Util.toArray(viewportFilteredTrackIndices);
    }

    private static int getAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, List<Integer> list) {
        int i5 = 0;
        for (int i6 = 0; i6 < list.size(); i6++) {
            int intValue = list.get(i6).intValue();
            if (isSupportedAdaptiveVideoTrack(trackGroup.getFormat(intValue), str, iArr[intValue], i, i2, i3, i4)) {
                i5++;
            }
        }
        return i5;
    }

    private static void filterAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, List<Integer> list) {
        List<Integer> list2 = list;
        for (int size = list.size() - 1; size >= 0; size--) {
            int intValue = list2.get(size).intValue();
            if (!isSupportedAdaptiveVideoTrack(trackGroup.getFormat(intValue), str, iArr[intValue], i, i2, i3, i4)) {
                list2.remove(size);
            }
        }
    }

    private static boolean isSupportedAdaptiveVideoTrack(Format format, String str, int i, int i2, int i3, int i4, int i5) {
        if (!isSupported(i, false) || (i & i2) == 0) {
            return false;
        }
        if (str != null && !Util.areEqual(format.sampleMimeType, str)) {
            return false;
        }
        if (format.width != -1 && format.width > i3) {
            return false;
        }
        if (format.height != -1 && format.height > i4) {
            return false;
        }
        if (format.bitrate == -1 || format.bitrate <= i5) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0048, code lost:
        if (r3.width <= r25) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        if (r3.height <= r26) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0064, code lost:
        if (r3.bitrate <= r27) goto L_0x0069;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.trackselection.TrackSelection selectFixedVideoTrack(com.google.android.exoplayer2.source.TrackGroupArray r23, int[][] r24, int r25, int r26, int r27, int r28, int r29, boolean r30, boolean r31, boolean r32) {
        /*
            r0 = r23
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = -1
            r9 = -1
        L_0x0008:
            int r10 = r0.length
            if (r4 >= r10) goto L_0x00e3
            com.google.android.exoplayer2.source.TrackGroup r10 = r0.get(r4)
            r11 = r28
            r12 = r29
            r13 = r30
            java.util.List r14 = getViewportFilteredTrackIndices(r10, r11, r12, r13)
            r15 = r24[r4]
            r1 = r9
            r9 = r8
            r8 = r7
            r7 = r5
            r5 = 0
        L_0x0021:
            int r3 = r10.length
            if (r5 >= r3) goto L_0x00d4
            r3 = r15[r5]
            r2 = r32
            boolean r3 = isSupported(r3, r2)
            if (r3 == 0) goto L_0x00c6
            com.google.android.exoplayer2.Format r3 = r10.getFormat(r5)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
            boolean r0 = r14.contains(r0)
            r18 = 1
            if (r0 == 0) goto L_0x006c
            int r0 = r3.width
            r2 = -1
            if (r0 == r2) goto L_0x004b
            int r0 = r3.width
            r2 = r25
            if (r0 > r2) goto L_0x006c
            goto L_0x004d
        L_0x004b:
            r2 = r25
        L_0x004d:
            int r0 = r3.height
            r2 = -1
            if (r0 == r2) goto L_0x0059
            int r0 = r3.height
            r2 = r26
            if (r0 > r2) goto L_0x006c
            goto L_0x005b
        L_0x0059:
            r2 = r26
        L_0x005b:
            int r0 = r3.bitrate
            r2 = -1
            if (r0 == r2) goto L_0x0067
            int r0 = r3.bitrate
            r2 = r27
            if (r0 > r2) goto L_0x006e
            goto L_0x0069
        L_0x0067:
            r2 = r27
        L_0x0069:
            r0 = r18
            goto L_0x006f
        L_0x006c:
            r2 = r27
        L_0x006e:
            r0 = 0
        L_0x006f:
            if (r0 != 0) goto L_0x0074
            if (r31 != 0) goto L_0x0074
            goto L_0x00c6
        L_0x0074:
            if (r0 == 0) goto L_0x007d
            r19 = 2
            r20 = r6
            r2 = r19
            goto L_0x0081
        L_0x007d:
            r20 = r6
            r2 = r18
        L_0x0081:
            r6 = r15[r5]
            r21 = r7
            r7 = 0
            boolean r6 = isSupported(r6, r7)
            if (r6 == 0) goto L_0x008e
            int r2 = r2 + 1000
        L_0x008e:
            if (r2 <= r8) goto L_0x0093
            r17 = r18
            goto L_0x0095
        L_0x0093:
            r17 = r7
        L_0x0095:
            if (r2 != r8) goto L_0x00ba
            int r7 = r3.getPixelCount()
            if (r7 == r9) goto L_0x00a6
            int r7 = r3.getPixelCount()
            int r7 = compareFormatValues(r7, r9)
            goto L_0x00ac
        L_0x00a6:
            int r7 = r3.bitrate
            int r7 = compareFormatValues(r7, r1)
        L_0x00ac:
            if (r6 == 0) goto L_0x00b3
            if (r0 == 0) goto L_0x00b3
            if (r7 <= 0) goto L_0x00b8
            goto L_0x00b5
        L_0x00b3:
            if (r7 >= 0) goto L_0x00b8
        L_0x00b5:
            r17 = r18
            goto L_0x00ba
        L_0x00b8:
            r17 = 0
        L_0x00ba:
            if (r17 == 0) goto L_0x00ca
            int r1 = r3.bitrate
            int r9 = r3.getPixelCount()
            r8 = r2
            r6 = r5
            r7 = r10
            goto L_0x00ce
        L_0x00c6:
            r20 = r6
            r21 = r7
        L_0x00ca:
            r6 = r20
            r7 = r21
        L_0x00ce:
            int r5 = r5 + 1
            r0 = r23
            goto L_0x0021
        L_0x00d4:
            r20 = r6
            r21 = r7
            int r4 = r4 + 1
            r7 = r8
            r8 = r9
            r5 = r21
            r0 = r23
            r9 = r1
            goto L_0x0008
        L_0x00e3:
            if (r5 != 0) goto L_0x00e8
            r16 = 0
            goto L_0x00ef
        L_0x00e8:
            com.google.android.exoplayer2.trackselection.FixedTrackSelection r1 = new com.google.android.exoplayer2.trackselection.FixedTrackSelection
            r1.<init>(r5, r6)
            r16 = r1
        L_0x00ef:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.selectFixedVideoTrack(com.google.android.exoplayer2.source.TrackGroupArray, int[][], int, int, int, int, int, boolean, boolean, boolean):com.google.android.exoplayer2.trackselection.TrackSelection");
    }

    /* access modifiers changed from: protected */
    public TrackSelection selectAudioTrack(TrackGroupArray trackGroupArray, int[][] iArr, String str, boolean z, boolean z2, TrackSelection.Factory factory) {
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        TrackSelection.Factory factory2 = factory;
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        int i4 = -1;
        while (i < trackGroupArray2.length) {
            TrackGroup trackGroup = trackGroupArray2.get(i);
            int[] iArr2 = iArr[i];
            int i5 = i2;
            int i6 = i4;
            int i7 = i3;
            for (int i8 = 0; i8 < trackGroup.length; i8++) {
                if (isSupported(iArr2[i8], z)) {
                    int audioTrackScore = getAudioTrackScore(iArr2[i8], str, trackGroup.getFormat(i8));
                    if (audioTrackScore > i5) {
                        i7 = i;
                        i6 = i8;
                        i5 = audioTrackScore;
                    }
                } else {
                    String str2 = str;
                }
            }
            String str3 = str;
            boolean z3 = z;
            i++;
            i3 = i7;
            i4 = i6;
            i2 = i5;
        }
        if (i3 == -1) {
            return null;
        }
        TrackGroup trackGroup2 = trackGroupArray2.get(i3);
        if (factory2 != null) {
            int[] adaptiveAudioTracks = getAdaptiveAudioTracks(trackGroup2, iArr[i3], z2);
            if (adaptiveAudioTracks.length > 0) {
                return factory2.createTrackSelection(trackGroup2, adaptiveAudioTracks);
            }
        }
        return new FixedTrackSelection(trackGroup2, i4);
    }

    private static int getAudioTrackScore(int i, String str, Format format) {
        int i2 = 1;
        boolean z = (format.selectionFlags & 1) != 0;
        if (formatHasLanguage(format, str)) {
            i2 = z ? 4 : 3;
        } else if (z) {
            i2 = 2;
        }
        return isSupported(i, false) ? i2 + 1000 : i2;
    }

    private static int[] getAdaptiveAudioTracks(TrackGroup trackGroup, int[] iArr, boolean z) {
        int adaptiveAudioTrackCount;
        HashSet hashSet = new HashSet();
        AudioConfigurationTuple audioConfigurationTuple = null;
        int i = 0;
        for (int i2 = 0; i2 < trackGroup.length; i2++) {
            Format format = trackGroup.getFormat(i2);
            AudioConfigurationTuple audioConfigurationTuple2 = new AudioConfigurationTuple(format.channelCount, format.sampleRate, z ? null : format.sampleMimeType);
            if (hashSet.add(audioConfigurationTuple2) && (adaptiveAudioTrackCount = getAdaptiveAudioTrackCount(trackGroup, iArr, audioConfigurationTuple2)) > i) {
                i = adaptiveAudioTrackCount;
                audioConfigurationTuple = audioConfigurationTuple2;
            }
        }
        if (i <= 1) {
            return NO_TRACKS;
        }
        int[] iArr2 = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 < trackGroup.length; i4++) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i4), iArr[i4], audioConfigurationTuple)) {
                iArr2[i3] = i4;
                i3++;
            }
        }
        return iArr2;
    }

    private static int getAdaptiveAudioTrackCount(TrackGroup trackGroup, int[] iArr, AudioConfigurationTuple audioConfigurationTuple) {
        int i = 0;
        for (int i2 = 0; i2 < trackGroup.length; i2++) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i2), iArr[i2], audioConfigurationTuple)) {
                i++;
            }
        }
        return i;
    }

    private static boolean isSupportedAdaptiveAudioTrack(Format format, int i, AudioConfigurationTuple audioConfigurationTuple) {
        if (!isSupported(i, false) || format.channelCount != audioConfigurationTuple.channelCount || format.sampleRate != audioConfigurationTuple.sampleRate) {
            return false;
        }
        if (audioConfigurationTuple.mimeType == null || TextUtils.equals(audioConfigurationTuple.mimeType, format.sampleMimeType)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0075 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.trackselection.TrackSelection selectTextTrack(com.google.android.exoplayer2.source.TrackGroupArray r19, int[][] r20, java.lang.String r21, java.lang.String r22, boolean r23) {
        /*
            r18 = this;
            r0 = r19
            r1 = 0
            r2 = 0
            r4 = r1
            r3 = r2
            r5 = r3
            r6 = r5
        L_0x0008:
            int r7 = r0.length
            if (r3 >= r7) goto L_0x0084
            com.google.android.exoplayer2.source.TrackGroup r7 = r0.get(r3)
            r8 = r20[r3]
            r9 = r6
            r6 = r5
            r5 = r4
            r4 = r2
        L_0x0016:
            int r10 = r7.length
            if (r4 >= r10) goto L_0x0078
            r10 = r8[r4]
            r11 = r23
            boolean r10 = isSupported(r10, r11)
            if (r10 == 0) goto L_0x0071
            com.google.android.exoplayer2.Format r10 = r7.getFormat(r4)
            int r12 = r10.selectionFlags
            r13 = 1
            r12 = r12 & r13
            if (r12 == 0) goto L_0x0030
            r12 = r13
            goto L_0x0031
        L_0x0030:
            r12 = r2
        L_0x0031:
            int r14 = r10.selectionFlags
            r15 = 2
            r14 = r14 & r15
            if (r14 == 0) goto L_0x003c
            r14 = r21
            r16 = r13
            goto L_0x0040
        L_0x003c:
            r14 = r21
            r16 = r2
        L_0x0040:
            boolean r17 = formatHasLanguage(r10, r14)
            if (r17 == 0) goto L_0x0052
            if (r12 == 0) goto L_0x004c
            r13 = 6
        L_0x0049:
            r12 = r22
            goto L_0x0061
        L_0x004c:
            if (r16 != 0) goto L_0x0050
            r13 = 5
            goto L_0x0049
        L_0x0050:
            r13 = 4
            goto L_0x0049
        L_0x0052:
            if (r12 == 0) goto L_0x0056
            r13 = 3
            goto L_0x0049
        L_0x0056:
            if (r16 == 0) goto L_0x0073
            r12 = r22
            boolean r10 = formatHasLanguage(r10, r12)
            if (r10 == 0) goto L_0x0061
            r13 = r15
        L_0x0061:
            r10 = r8[r4]
            boolean r10 = isSupported(r10, r2)
            if (r10 == 0) goto L_0x006b
            int r13 = r13 + 1000
        L_0x006b:
            if (r13 <= r9) goto L_0x0075
            r6 = r4
            r5 = r7
            r9 = r13
            goto L_0x0075
        L_0x0071:
            r14 = r21
        L_0x0073:
            r12 = r22
        L_0x0075:
            int r4 = r4 + 1
            goto L_0x0016
        L_0x0078:
            r14 = r21
            r12 = r22
            r11 = r23
            int r3 = r3 + 1
            r4 = r5
            r5 = r6
            r6 = r9
            goto L_0x0008
        L_0x0084:
            if (r4 != 0) goto L_0x0087
            goto L_0x008c
        L_0x0087:
            com.google.android.exoplayer2.trackselection.FixedTrackSelection r1 = new com.google.android.exoplayer2.trackselection.FixedTrackSelection
            r1.<init>(r4, r5)
        L_0x008c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.selectTextTrack(com.google.android.exoplayer2.source.TrackGroupArray, int[][], java.lang.String, java.lang.String, boolean):com.google.android.exoplayer2.trackselection.TrackSelection");
    }

    /* access modifiers changed from: protected */
    public TrackSelection selectOtherTrack(int i, TrackGroupArray trackGroupArray, int[][] iArr, boolean z) {
        TrackGroup trackGroup = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < trackGroupArray.length) {
            TrackGroup trackGroup2 = trackGroupArray.get(i2);
            int[] iArr2 = iArr[i2];
            int i5 = i4;
            int i6 = i3;
            TrackGroup trackGroup3 = trackGroup;
            for (int i7 = 0; i7 < trackGroup2.length; i7++) {
                if (isSupported(iArr2[i7], z)) {
                    int i8 = 1;
                    if ((trackGroup2.getFormat(i7).selectionFlags & 1) != 0) {
                        i8 = 2;
                    }
                    if (isSupported(iArr2[i7], false)) {
                        i8 += 1000;
                    }
                    if (i8 > i5) {
                        i6 = i7;
                        trackGroup3 = trackGroup2;
                        i5 = i8;
                    }
                }
            }
            i2++;
            trackGroup = trackGroup3;
            i3 = i6;
            i4 = i5;
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i3);
    }

    protected static boolean formatHasLanguage(Format format, String str) {
        return str != null && TextUtils.equals(str, Util.normalizeLanguageCode(format.language));
    }

    private static List<Integer> getViewportFilteredTrackIndices(TrackGroup trackGroup, int i, int i2, boolean z) {
        ArrayList arrayList = new ArrayList(trackGroup.length);
        for (int i3 = 0; i3 < trackGroup.length; i3++) {
            arrayList.add(Integer.valueOf(i3));
        }
        if (i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE) {
            return arrayList;
        }
        int i4 = Integer.MAX_VALUE;
        for (int i5 = 0; i5 < trackGroup.length; i5++) {
            Format format = trackGroup.getFormat(i5);
            if (format.width > 0 && format.height > 0) {
                Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z, i, i2, format.width, format.height);
                int i6 = format.width * format.height;
                if (format.width >= ((int) (((float) maxVideoSizeInViewport.x) * FRACTION_TO_CONSIDER_FULLSCREEN)) && format.height >= ((int) (((float) maxVideoSizeInViewport.y) * FRACTION_TO_CONSIDER_FULLSCREEN)) && i6 < i4) {
                    i4 = i6;
                }
            }
        }
        if (i4 != Integer.MAX_VALUE) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                int pixelCount = trackGroup.getFormat(((Integer) arrayList.get(size)).intValue()).getPixelCount();
                if (pixelCount == -1 || pixelCount > i4) {
                    arrayList.remove(size);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r1 != r3) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto L_0x000f
            r3 = 0
            r0 = 1
            if (r6 <= r7) goto L_0x0008
            r1 = r0
            goto L_0x0009
        L_0x0008:
            r1 = r3
        L_0x0009:
            if (r4 <= r5) goto L_0x000c
            r3 = r0
        L_0x000c:
            if (r1 == r3) goto L_0x000f
            goto L_0x0012
        L_0x000f:
            r2 = r5
            r5 = r4
            r4 = r2
        L_0x0012:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L_0x0022
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = com.google.android.exoplayer2.util.Util.ceilDivide((int) r0, (int) r6)
            r3.<init>(r5, r4)
            return r3
        L_0x0022:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = com.google.android.exoplayer2.util.Util.ceilDivide((int) r3, (int) r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }

    private static final class AudioConfigurationTuple {
        public final int channelCount;
        public final String mimeType;
        public final int sampleRate;

        public AudioConfigurationTuple(int i, int i2, String str) {
            this.channelCount = i;
            this.sampleRate = i2;
            this.mimeType = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AudioConfigurationTuple audioConfigurationTuple = (AudioConfigurationTuple) obj;
            if (this.channelCount == audioConfigurationTuple.channelCount && this.sampleRate == audioConfigurationTuple.sampleRate && TextUtils.equals(this.mimeType, audioConfigurationTuple.mimeType)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (31 * ((this.channelCount * 31) + this.sampleRate)) + (this.mimeType != null ? this.mimeType.hashCode() : 0);
        }
    }
}
