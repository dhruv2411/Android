package com.google.android.exoplayer2.trackselection;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;
    private final SparseBooleanArray rendererDisabledFlags = new SparseBooleanArray();
    private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides = new SparseArray<>();
    private int tunnelingAudioSessionId = 0;

    /* access modifiers changed from: protected */
    public abstract TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr) throws ExoPlaybackException;

    public static final class SelectionOverride {
        public final TrackSelection.Factory factory;
        public final int groupIndex;
        public final int length;
        public final int[] tracks;

        public SelectionOverride(TrackSelection.Factory factory2, int i, int... iArr) {
            this.factory = factory2;
            this.groupIndex = i;
            this.tracks = iArr;
            this.length = iArr.length;
        }

        public TrackSelection createTrackSelection(TrackGroupArray trackGroupArray) {
            return this.factory.createTrackSelection(trackGroupArray.get(this.groupIndex), this.tracks);
        }

        public boolean containsTrack(int i) {
            for (int i2 : this.tracks) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }
    }

    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    public final void setRendererDisabled(int i, boolean z) {
        if (this.rendererDisabledFlags.get(i) != z) {
            this.rendererDisabledFlags.put(i, z);
            invalidate();
        }
    }

    public final boolean getRendererDisabled(int i) {
        return this.rendererDisabledFlags.get(i);
    }

    public final void setSelectionOverride(int i, TrackGroupArray trackGroupArray, SelectionOverride selectionOverride) {
        Map map = this.selectionOverrides.get(i);
        if (map == null) {
            map = new HashMap();
            this.selectionOverrides.put(i, map);
        }
        if (!map.containsKey(trackGroupArray) || !Util.areEqual(map.get(trackGroupArray), selectionOverride)) {
            map.put(trackGroupArray, selectionOverride);
            invalidate();
        }
    }

    public final boolean hasSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        Map map = this.selectionOverrides.get(i);
        return map != null && map.containsKey(trackGroupArray);
    }

    public final SelectionOverride getSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        Map map = this.selectionOverrides.get(i);
        if (map != null) {
            return (SelectionOverride) map.get(trackGroupArray);
        }
        return null;
    }

    public final void clearSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        Map map = this.selectionOverrides.get(i);
        if (map != null && map.containsKey(trackGroupArray)) {
            map.remove(trackGroupArray);
            if (map.isEmpty()) {
                this.selectionOverrides.remove(i);
            }
            invalidate();
        }
    }

    public final void clearSelectionOverrides(int i) {
        Map map = this.selectionOverrides.get(i);
        if (map != null && !map.isEmpty()) {
            this.selectionOverrides.remove(i);
            invalidate();
        }
    }

    public final void clearSelectionOverrides() {
        if (this.selectionOverrides.size() != 0) {
            this.selectionOverrides.clear();
            invalidate();
        }
    }

    public void setTunnelingAudioSessionId(int i) {
        if (this.tunnelingAudioSessionId != i) {
            this.tunnelingAudioSessionId = i;
            invalidate();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: com.google.android.exoplayer2.trackselection.MappingTrackSelector$SelectionOverride} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.exoplayer2.trackselection.TrackSelectorResult selectTracks(com.google.android.exoplayer2.RendererCapabilities[] r14, com.google.android.exoplayer2.source.TrackGroupArray r15) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r13 = this;
            r0 = 0
            int r1 = r14.length
            int r1 = r1 + 1
            int[] r1 = new int[r1]
            int r2 = r14.length
            int r2 = r2 + 1
            com.google.android.exoplayer2.source.TrackGroup[][] r2 = new com.google.android.exoplayer2.source.TrackGroup[r2][]
            int r3 = r14.length
            int r3 = r3 + 1
            int[][][] r3 = new int[r3][][]
            r4 = r0
        L_0x0011:
            int r5 = r2.length
            if (r4 >= r5) goto L_0x0023
            int r5 = r15.length
            com.google.android.exoplayer2.source.TrackGroup[] r5 = new com.google.android.exoplayer2.source.TrackGroup[r5]
            r2[r4] = r5
            int r5 = r15.length
            int[][] r5 = new int[r5][]
            r3[r4] = r5
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0023:
            int[] r7 = getMixedMimeTypeAdaptationSupport(r14)
            r4 = r0
        L_0x0028:
            int r5 = r15.length
            if (r4 >= r5) goto L_0x0055
            com.google.android.exoplayer2.source.TrackGroup r5 = r15.get(r4)
            int r6 = findRenderer(r14, r5)
            int r8 = r14.length
            if (r6 != r8) goto L_0x003c
            int r8 = r5.length
            int[] r8 = new int[r8]
            goto L_0x0042
        L_0x003c:
            r8 = r14[r6]
            int[] r8 = getFormatSupport(r8, r5)
        L_0x0042:
            r9 = r1[r6]
            r10 = r2[r6]
            r10[r9] = r5
            r5 = r3[r6]
            r5[r9] = r8
            r5 = r1[r6]
            int r5 = r5 + 1
            r1[r6] = r5
            int r4 = r4 + 1
            goto L_0x0028
        L_0x0055:
            int r4 = r14.length
            com.google.android.exoplayer2.source.TrackGroupArray[] r10 = new com.google.android.exoplayer2.source.TrackGroupArray[r4]
            int r4 = r14.length
            int[] r5 = new int[r4]
            r4 = r0
        L_0x005c:
            int r6 = r14.length
            if (r4 >= r6) goto L_0x0085
            r6 = r1[r4]
            com.google.android.exoplayer2.source.TrackGroupArray r8 = new com.google.android.exoplayer2.source.TrackGroupArray
            r9 = r2[r4]
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r9, r6)
            com.google.android.exoplayer2.source.TrackGroup[] r9 = (com.google.android.exoplayer2.source.TrackGroup[]) r9
            r8.<init>(r9)
            r10[r4] = r8
            r8 = r3[r4]
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r8, r6)
            int[][] r6 = (int[][]) r6
            r3[r4] = r6
            r6 = r14[r4]
            int r6 = r6.getTrackType()
            r5[r4] = r6
            int r4 = r4 + 1
            goto L_0x005c
        L_0x0085:
            int r4 = r14.length
            r1 = r1[r4]
            com.google.android.exoplayer2.source.TrackGroupArray r9 = new com.google.android.exoplayer2.source.TrackGroupArray
            int r4 = r14.length
            r2 = r2[r4]
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r2, r1)
            com.google.android.exoplayer2.source.TrackGroup[] r1 = (com.google.android.exoplayer2.source.TrackGroup[]) r1
            r9.<init>(r1)
            com.google.android.exoplayer2.trackselection.TrackSelection[] r1 = r13.selectTracks(r14, r10, r3)
            r2 = r0
        L_0x009b:
            r11 = 0
            int r4 = r14.length
            if (r2 >= r4) goto L_0x00c9
            android.util.SparseBooleanArray r4 = r13.rendererDisabledFlags
            boolean r4 = r4.get(r2)
            if (r4 == 0) goto L_0x00aa
            r1[r2] = r11
            goto L_0x00c6
        L_0x00aa:
            r4 = r10[r2]
            android.util.SparseArray<java.util.Map<com.google.android.exoplayer2.source.TrackGroupArray, com.google.android.exoplayer2.trackselection.MappingTrackSelector$SelectionOverride>> r6 = r13.selectionOverrides
            java.lang.Object r6 = r6.get(r2)
            java.util.Map r6 = (java.util.Map) r6
            if (r6 != 0) goto L_0x00b7
            goto L_0x00be
        L_0x00b7:
            java.lang.Object r6 = r6.get(r4)
            r11 = r6
            com.google.android.exoplayer2.trackselection.MappingTrackSelector$SelectionOverride r11 = (com.google.android.exoplayer2.trackselection.MappingTrackSelector.SelectionOverride) r11
        L_0x00be:
            if (r11 == 0) goto L_0x00c6
            com.google.android.exoplayer2.trackselection.TrackSelection r4 = r11.createTrackSelection(r4)
            r1[r2] = r4
        L_0x00c6:
            int r2 = r2 + 1
            goto L_0x009b
        L_0x00c9:
            com.google.android.exoplayer2.trackselection.MappingTrackSelector$MappedTrackInfo r2 = new com.google.android.exoplayer2.trackselection.MappingTrackSelector$MappedTrackInfo
            r4 = r2
            r6 = r10
            r8 = r3
            r4.<init>(r5, r6, r7, r8, r9)
            int r4 = r14.length
            com.google.android.exoplayer2.RendererConfiguration[] r12 = new com.google.android.exoplayer2.RendererConfiguration[r4]
        L_0x00d4:
            int r4 = r14.length
            if (r0 >= r4) goto L_0x00e4
            r4 = r1[r0]
            if (r4 == 0) goto L_0x00de
            com.google.android.exoplayer2.RendererConfiguration r4 = com.google.android.exoplayer2.RendererConfiguration.DEFAULT
            goto L_0x00df
        L_0x00de:
            r4 = r11
        L_0x00df:
            r12[r0] = r4
            int r0 = r0 + 1
            goto L_0x00d4
        L_0x00e4:
            int r9 = r13.tunnelingAudioSessionId
            r4 = r14
            r5 = r10
            r6 = r3
            r7 = r12
            r8 = r1
            maybeConfigureRenderersForTunneling(r4, r5, r6, r7, r8, r9)
            com.google.android.exoplayer2.trackselection.TrackSelectorResult r14 = new com.google.android.exoplayer2.trackselection.TrackSelectorResult
            com.google.android.exoplayer2.trackselection.TrackSelectionArray r0 = new com.google.android.exoplayer2.trackselection.TrackSelectionArray
            r0.<init>(r1)
            r14.<init>(r15, r0, r2, r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.MappingTrackSelector.selectTracks(com.google.android.exoplayer2.RendererCapabilities[], com.google.android.exoplayer2.source.TrackGroupArray):com.google.android.exoplayer2.trackselection.TrackSelectorResult");
    }

    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup) throws ExoPlaybackException {
        int i = 0;
        int length = rendererCapabilitiesArr.length;
        int i2 = 0;
        while (i2 < rendererCapabilitiesArr.length) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i2];
            int i3 = length;
            for (int i4 = 0; i4 < trackGroup.length; i4++) {
                int supportsFormat = rendererCapabilities.supportsFormat(trackGroup.getFormat(i4)) & 3;
                if (supportsFormat > i) {
                    if (supportsFormat == 3) {
                        return i2;
                    }
                    i3 = i2;
                    i = supportsFormat;
                }
            }
            i2++;
            length = i3;
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) throws ExoPlaybackException {
        int[] iArr = new int[trackGroup.length];
        for (int i = 0; i < trackGroup.length; i++) {
            iArr[i] = rendererCapabilities.supportsFormat(trackGroup.getFormat(i));
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupport(RendererCapabilities[] rendererCapabilitiesArr) throws ExoPlaybackException {
        int[] iArr = new int[rendererCapabilitiesArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = rendererCapabilitiesArr[i].supportsMixedMimeTypeAdaptation();
        }
        return iArr;
    }

    private static void maybeConfigureRenderersForTunneling(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, TrackSelection[] trackSelectionArr, int i) {
        boolean z;
        if (i != 0) {
            boolean z2 = false;
            int i2 = 0;
            int i3 = -1;
            int i4 = -1;
            while (true) {
                if (i2 >= rendererCapabilitiesArr.length) {
                    z = true;
                    break;
                }
                int trackType = rendererCapabilitiesArr[i2].getTrackType();
                TrackSelection trackSelection = trackSelectionArr[i2];
                if ((trackType == 1 || trackType == 2) && trackSelection != null && rendererSupportsTunneling(iArr[i2], trackGroupArrayArr[i2], trackSelection)) {
                    if (trackType == 1) {
                        if (i3 != -1) {
                            break;
                        }
                        i3 = i2;
                    } else if (i4 != -1) {
                        break;
                    } else {
                        i4 = i2;
                    }
                }
                i2++;
            }
            z = false;
            if (!(i3 == -1 || i4 == -1)) {
                z2 = true;
            }
            if (z && z2) {
                RendererConfiguration rendererConfiguration = new RendererConfiguration(i);
                rendererConfigurationArr[i3] = rendererConfiguration;
                rendererConfigurationArr[i4] = rendererConfiguration;
            }
        }
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, TrackSelection trackSelection) {
        if (trackSelection == null) {
            return false;
        }
        int indexOf = trackGroupArray.indexOf(trackSelection.getTrackGroup());
        for (int i = 0; i < trackSelection.length(); i++) {
            if ((iArr[indexOf][trackSelection.getIndexInTrackGroup(i)] & 16) != 16) {
                return false;
            }
        }
        return true;
    }

    public static final class MappedTrackInfo {
        public static final int RENDERER_SUPPORT_EXCEEDS_CAPABILITIES_TRACKS = 2;
        public static final int RENDERER_SUPPORT_NO_TRACKS = 0;
        public static final int RENDERER_SUPPORT_PLAYABLE_TRACKS = 3;
        public static final int RENDERER_SUPPORT_UNSUPPORTED_TRACKS = 1;
        private final int[][][] formatSupport;
        public final int length;
        private final int[] mixedMimeTypeAdaptiveSupport;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray[] trackGroups;
        private final TrackGroupArray unassociatedTrackGroups;

        MappedTrackInfo(int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererTrackTypes = iArr;
            this.trackGroups = trackGroupArrayArr;
            this.formatSupport = iArr3;
            this.mixedMimeTypeAdaptiveSupport = iArr2;
            this.unassociatedTrackGroups = trackGroupArray;
            this.length = trackGroupArrayArr.length;
        }

        public TrackGroupArray getTrackGroups(int i) {
            return this.trackGroups[i];
        }

        public int getRendererSupport(int i) {
            int i2;
            int[][] iArr = this.formatSupport[i];
            int i3 = 0;
            int i4 = 0;
            while (i3 < iArr.length) {
                int i5 = i4;
                for (int i6 : iArr[i3]) {
                    switch (i6 & 3) {
                        case 2:
                            i2 = 2;
                            break;
                        case 3:
                            return 3;
                        default:
                            i2 = 1;
                            break;
                    }
                    i5 = Math.max(i5, i2);
                }
                i3++;
                i4 = i5;
            }
            return i4;
        }

        public int getTrackTypeRendererSupport(int i) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.length; i3++) {
                if (this.rendererTrackTypes[i3] == i) {
                    i2 = Math.max(i2, getRendererSupport(i3));
                }
            }
            return i2;
        }

        public int getTrackFormatSupport(int i, int i2, int i3) {
            return this.formatSupport[i][i2][i3] & 3;
        }

        public int getAdaptiveSupport(int i, int i2, boolean z) {
            int i3 = this.trackGroups[i].get(i2).length;
            int[] iArr = new int[i3];
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                int trackFormatSupport = getTrackFormatSupport(i, i2, i5);
                if (trackFormatSupport == 3 || (z && trackFormatSupport == 2)) {
                    iArr[i4] = i5;
                    i4++;
                }
            }
            return getAdaptiveSupport(i, i2, Arrays.copyOf(iArr, i4));
        }

        public int getAdaptiveSupport(int i, int i2, int[] iArr) {
            int i3 = 0;
            int i4 = 8;
            String str = null;
            int i5 = 0;
            boolean z = false;
            while (i3 < iArr.length) {
                String str2 = this.trackGroups[i].get(i2).getFormat(iArr[i3]).sampleMimeType;
                int i6 = i5 + 1;
                if (i5 == 0) {
                    str = str2;
                } else {
                    z = (!Util.areEqual(str, str2)) | z;
                }
                i4 = Math.min(i4, this.formatSupport[i][i2][i3] & 12);
                i3++;
                i5 = i6;
            }
            return z ? Math.min(i4, this.mixedMimeTypeAdaptiveSupport[i]) : i4;
        }

        public TrackGroupArray getUnassociatedTrackGroups() {
            return this.unassociatedTrackGroups;
        }
    }
}
