package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.api.Releasable;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@zzme
public abstract class zzis implements Releasable {
    protected Context mContext;
    protected String zzIA;
    protected WeakReference<zzqw> zzIB;

    public zzis(zzqw zzqw) {
        this.mContext = zzqw.getContext();
        this.zzIA = zzw.zzcM().zzu(this.mContext, zzqw.zzly().zzba);
        this.zzIB = new WeakReference<>(zzqw);
    }

    /* access modifiers changed from: private */
    public void zza(String str, Map<String, String> map) {
        zzqw zzqw = (zzqw) this.zzIB.get();
        if (zzqw != null) {
            zzqw.zza(str, (Map<String, ?>) map);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zzaf(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.String r0 = "internal"
            int r1 = r3.hashCode()
            switch(r1) {
                case -1396664534: goto L_0x0067;
                case -1347010958: goto L_0x005d;
                case -918817863: goto L_0x0053;
                case -659376217: goto L_0x0049;
                case -642208130: goto L_0x003f;
                case -354048396: goto L_0x0034;
                case -32082395: goto L_0x0029;
                case 96784904: goto L_0x001f;
                case 580119100: goto L_0x0015;
                case 725497484: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0071
        L_0x000b:
            java.lang.String r1 = "noCacheDir"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 4
            goto L_0x0072
        L_0x0015:
            java.lang.String r1 = "expireFailed"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 5
            goto L_0x0072
        L_0x001f:
            java.lang.String r1 = "error"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 0
            goto L_0x0072
        L_0x0029:
            java.lang.String r1 = "externalAbort"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 9
            goto L_0x0072
        L_0x0034:
            java.lang.String r1 = "sizeExceeded"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 8
            goto L_0x0072
        L_0x003f:
            java.lang.String r1 = "playerFailed"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 1
            goto L_0x0072
        L_0x0049:
            java.lang.String r1 = "contentLengthMissing"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 3
            goto L_0x0072
        L_0x0053:
            java.lang.String r1 = "downloadTimeout"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 7
            goto L_0x0072
        L_0x005d:
            java.lang.String r1 = "inProgress"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 2
            goto L_0x0072
        L_0x0067:
            java.lang.String r1 = "badUrl"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0071
            r3 = 6
            goto L_0x0072
        L_0x0071:
            r3 = -1
        L_0x0072:
            switch(r3) {
                case 0: goto L_0x007f;
                case 1: goto L_0x007f;
                case 2: goto L_0x007f;
                case 3: goto L_0x007f;
                case 4: goto L_0x007c;
                case 5: goto L_0x007c;
                case 6: goto L_0x0079;
                case 7: goto L_0x0079;
                case 8: goto L_0x0076;
                case 9: goto L_0x0076;
                default: goto L_0x0075;
            }
        L_0x0075:
            return r0
        L_0x0076:
            java.lang.String r0 = "policy"
            return r0
        L_0x0079:
            java.lang.String r0 = "network"
            return r0
        L_0x007c:
            java.lang.String r0 = "io"
            return r0
        L_0x007f:
            java.lang.String r0 = "internal"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzis.zzaf(java.lang.String):java.lang.String");
    }

    public abstract void abort();

    public void release() {
    }

    /* access modifiers changed from: protected */
    public void zza(final String str, final String str2, final int i) {
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("event", "precacheComplete");
                hashMap.put(HtmlTags.SRC, str);
                hashMap.put("cachedSrc", str2);
                hashMap.put("totalBytes", Integer.toString(i));
                zzis.this.zza("onPrecacheEvent", (Map<String, String>) hashMap);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zza(String str, String str2, int i, int i2, boolean z) {
        final String str3 = str;
        final String str4 = str2;
        final int i3 = i;
        final int i4 = i2;
        final boolean z2 = z;
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("event", "precacheProgress");
                hashMap.put(HtmlTags.SRC, str3);
                hashMap.put("cachedSrc", str4);
                hashMap.put("bytesLoaded", Integer.toString(i3));
                hashMap.put("totalBytes", Integer.toString(i4));
                hashMap.put("cacheReady", z2 ? "1" : "0");
                zzis.this.zza("onPrecacheEvent", (Map<String, String>) hashMap);
            }
        });
    }

    public void zza(String str, String str2, String str3, @Nullable String str4) {
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final String str8 = str4;
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("event", "precacheCanceled");
                hashMap.put(HtmlTags.SRC, str5);
                if (!TextUtils.isEmpty(str6)) {
                    hashMap.put("cachedSrc", str6);
                }
                hashMap.put(DublinCoreProperties.TYPE, zzis.this.zzaf(str7));
                hashMap.put("reason", str7);
                if (!TextUtils.isEmpty(str8)) {
                    hashMap.put(SettingsJsonConstants.PROMPT_MESSAGE_KEY, str8);
                }
                zzis.this.zza("onPrecacheEvent", (Map<String, String>) hashMap);
            }
        });
    }

    public abstract boolean zzad(String str);

    /* access modifiers changed from: protected */
    public String zzae(String str) {
        return zzel.zzeT().zzbe(str);
    }
}
