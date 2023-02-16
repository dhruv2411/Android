package com.google.android.gms.internal;

import android.content.res.Resources;
import com.google.android.gms.internal.zzsn;

abstract class zzso<T extends zzsn> extends zzrz {
    zza<T> zzaeY;

    public interface zza<U extends zzsn> {
        void zzd(String str, int i);

        void zze(String str, boolean z);

        U zzoV();

        void zzp(String str, String str2);

        void zzq(String str, String str2);
    }

    public zzso(zzsc zzsc, zza<T> zza2) {
        super(zzsc);
        this.zzaeY = zza2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c7, code lost:
        r2 = "Error parsing int configuration value";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d0, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d1, code lost:
        zze("Error parsing tracker configuration file", r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d0 A[ExcHandler: IOException | XmlPullParserException (r5v3 'e' java.lang.Object A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private T zza(android.content.res.XmlResourceParser r5) {
        /*
            r4 = this;
            r5.next()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            int r0 = r5.getEventType()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
        L_0x0007:
            r1 = 1
            if (r0 == r1) goto L_0x00d6
            int r0 = r5.getEventType()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            r1 = 2
            if (r0 != r1) goto L_0x00ca
            java.lang.String r0 = r5.getName()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r0 = r0.toLowerCase()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r1 = "screenname"
            boolean r1 = r0.equals(r1)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            r2 = 0
            if (r1 == 0) goto L_0x0043
            java.lang.String r0 = "name"
            java.lang.String r0 = r5.getAttributeValue(r2, r0)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r1 = r5.nextText()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r1 = r1.trim()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r2 != 0) goto L_0x00ca
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r2 != 0) goto L_0x00ca
            com.google.android.gms.internal.zzso$zza<T> r2 = r4.zzaeY     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            r2.zzp(r0, r1)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            goto L_0x00ca
        L_0x0043:
            java.lang.String r1 = "string"
            boolean r1 = r0.equals(r1)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r1 == 0) goto L_0x0067
            java.lang.String r0 = "name"
            java.lang.String r0 = r5.getAttributeValue(r2, r0)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r1 = r5.nextText()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r1 = r1.trim()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r2 != 0) goto L_0x00ca
            if (r1 == 0) goto L_0x00ca
            com.google.android.gms.internal.zzso$zza<T> r2 = r4.zzaeY     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            r2.zzq(r0, r1)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            goto L_0x00ca
        L_0x0067:
            java.lang.String r1 = "bool"
            boolean r1 = r0.equals(r1)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r1 == 0) goto L_0x009a
            java.lang.String r0 = "name"
            java.lang.String r0 = r5.getAttributeValue(r2, r0)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r1 = r5.nextText()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r1 = r1.trim()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r2 != 0) goto L_0x00ca
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r2 != 0) goto L_0x00ca
            boolean r2 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ NumberFormatException -> 0x0093, IOException | XmlPullParserException -> 0x00d0 }
            com.google.android.gms.internal.zzso$zza<T> r3 = r4.zzaeY     // Catch:{ NumberFormatException -> 0x0093, IOException | XmlPullParserException -> 0x00d0 }
            r3.zze(r0, r2)     // Catch:{ NumberFormatException -> 0x0093, IOException | XmlPullParserException -> 0x00d0 }
            goto L_0x00ca
        L_0x0093:
            r0 = move-exception
            java.lang.String r2 = "Error parsing bool configuration value"
        L_0x0096:
            r4.zzc(r2, r1, r0)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            goto L_0x00ca
        L_0x009a:
            java.lang.String r1 = "integer"
            boolean r0 = r0.equals(r1)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r0 == 0) goto L_0x00ca
            java.lang.String r0 = "name"
            java.lang.String r0 = r5.getAttributeValue(r2, r0)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r1 = r5.nextText()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            java.lang.String r1 = r1.trim()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r2 != 0) goto L_0x00ca
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            if (r2 != 0) goto L_0x00ca
            int r2 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x00c6, IOException | XmlPullParserException -> 0x00d0 }
            com.google.android.gms.internal.zzso$zza<T> r3 = r4.zzaeY     // Catch:{ NumberFormatException -> 0x00c6, IOException | XmlPullParserException -> 0x00d0 }
            r3.zzd(r0, r2)     // Catch:{ NumberFormatException -> 0x00c6, IOException | XmlPullParserException -> 0x00d0 }
            goto L_0x00ca
        L_0x00c6:
            r0 = move-exception
            java.lang.String r2 = "Error parsing int configuration value"
            goto L_0x0096
        L_0x00ca:
            int r0 = r5.next()     // Catch:{ IOException | XmlPullParserException -> 0x00d0 }
            goto L_0x0007
        L_0x00d0:
            r5 = move-exception
            java.lang.String r0 = "Error parsing tracker configuration file"
            r4.zze(r0, r5)
        L_0x00d6:
            com.google.android.gms.internal.zzso$zza<T> r5 = r4.zzaeY
            com.google.android.gms.internal.zzsn r5 = r5.zzoV()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzso.zza(android.content.res.XmlResourceParser):com.google.android.gms.internal.zzsn");
    }

    public T zzaI(int i) {
        try {
            return zza(zznQ().zzod().getResources().getXml(i));
        } catch (Resources.NotFoundException e) {
            zzd("inflate() called with unknown resourceId", e);
            return null;
        }
    }
}
