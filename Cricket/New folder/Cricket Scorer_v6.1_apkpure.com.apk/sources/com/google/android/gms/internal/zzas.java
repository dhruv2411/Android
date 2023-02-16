package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag;
import java.util.LinkedList;

public abstract class zzas implements zzar {
    protected MotionEvent zzpF;
    protected LinkedList<MotionEvent> zzpG = new LinkedList<>();
    protected long zzpH = 0;
    protected long zzpI = 0;
    protected long zzpJ = 0;
    protected long zzpK = 0;
    protected long zzpL = 0;
    protected long zzpM = 0;
    protected long zzpN = 0;
    private boolean zzpO = false;
    protected boolean zzpP = false;
    protected DisplayMetrics zzpQ;

    protected zzas(Context context) {
        try {
            zzao.zzO();
            this.zzpQ = context.getResources().getDisplayMetrics();
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0015 A[Catch:{ UnsupportedEncodingException | NoSuchAlgorithmException -> 0x0034, Throwable -> 0x0032 }] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001b A[Catch:{ UnsupportedEncodingException | NoSuchAlgorithmException -> 0x0034, Throwable -> 0x0032 }] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x000e A[SYNTHETIC, Splitter:B:9:0x000e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String zza(android.content.Context r2, java.lang.String r3, boolean r4, android.view.View r5, byte[] r6) {
        /*
            r1 = this;
            if (r6 == 0) goto L_0x000a
            int r0 = r6.length
            if (r0 <= 0) goto L_0x000a
            com.google.android.gms.internal.zzae$zza r6 = com.google.android.gms.internal.zzae.zza.zzc(r6)     // Catch:{ zzbxs -> 0x000a }
            goto L_0x000b
        L_0x000a:
            r6 = 0
        L_0x000b:
            r0 = 1
            if (r4 == 0) goto L_0x0015
            com.google.android.gms.internal.zzag$zza r2 = r1.zza((android.content.Context) r2, (android.view.View) r5)     // Catch:{ UnsupportedEncodingException | NoSuchAlgorithmException -> 0x0034, Throwable -> 0x0032 }
            r1.zzpO = r0     // Catch:{ UnsupportedEncodingException | NoSuchAlgorithmException -> 0x0034, Throwable -> 0x0032 }
            goto L_0x0019
        L_0x0015:
            com.google.android.gms.internal.zzag$zza r2 = r1.zza((android.content.Context) r2, (com.google.android.gms.internal.zzae.zza) r6)     // Catch:{ UnsupportedEncodingException | NoSuchAlgorithmException -> 0x0034, Throwable -> 0x0032 }
        L_0x0019:
            if (r2 == 0) goto L_0x002c
            int r5 = r2.zzaeT()     // Catch:{ UnsupportedEncodingException | NoSuchAlgorithmException -> 0x0034, Throwable -> 0x0032 }
            if (r5 != 0) goto L_0x0022
            goto L_0x002c
        L_0x0022:
            boolean r4 = zzb((boolean) r4)     // Catch:{ UnsupportedEncodingException | NoSuchAlgorithmException -> 0x0034, Throwable -> 0x0032 }
            r4 = r4 ^ r0
            java.lang.String r2 = com.google.android.gms.internal.zzao.zza((com.google.android.gms.internal.zzag.zza) r2, (java.lang.String) r3, (boolean) r4)     // Catch:{ UnsupportedEncodingException | NoSuchAlgorithmException -> 0x0034, Throwable -> 0x0032 }
            return r2
        L_0x002c:
            r2 = 5
            java.lang.String r2 = java.lang.Integer.toString(r2)     // Catch:{ UnsupportedEncodingException | NoSuchAlgorithmException -> 0x0034, Throwable -> 0x0032 }
            return r2
        L_0x0032:
            r2 = 3
            goto L_0x0035
        L_0x0034:
            r2 = 7
        L_0x0035:
            java.lang.String r2 = java.lang.Integer.toString(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzas.zza(android.content.Context, java.lang.String, boolean, android.view.View, byte[]):java.lang.String");
    }

    private static boolean zza(zzbe zzbe) {
        return (zzbe == null || zzbe.zzcf == null || zzbe.zzqM == null) ? false : true;
    }

    private boolean zzb(zzbe zzbe) {
        return (this.zzpQ == null || zzbe == null || zzbe.zzcd == null || zzbe.zzqN == null) ? false : true;
    }

    private static boolean zzb(boolean z) {
        if (!zzgd.zzDP.get().booleanValue()) {
            return true;
        }
        return zzgd.zzDV.get().booleanValue() && z;
    }

    /* access modifiers changed from: protected */
    public abstract long zza(StackTraceElement[] stackTraceElementArr) throws zzba;

    /* access modifiers changed from: protected */
    public abstract zzag.zza zza(Context context, View view);

    /* access modifiers changed from: protected */
    public abstract zzag.zza zza(Context context, zzae.zza zza);

    public String zza(Context context, String str, View view) {
        return zza(context, str, true, view, (byte[]) null);
    }

    public String zza(Context context, byte[] bArr) {
        if (!zzbf.zzbc() || !zzgd.zzDU.get().booleanValue()) {
            return zza(context, (String) null, false, (View) null, bArr);
        }
        throw new IllegalStateException("The caller must not be called from the UI thread.");
    }

    public void zza(int i, int i2, int i3) {
        MotionEvent motionEvent;
        if (this.zzpF != null) {
            this.zzpF.recycle();
        }
        if (this.zzpQ != null) {
            motionEvent = MotionEvent.obtain(0, (long) i3, 1, this.zzpQ.density * ((float) i), this.zzpQ.density * ((float) i2), 0.0f, 0.0f, 0, 0.0f, 0.0f, 0, 0);
        } else {
            motionEvent = null;
        }
        this.zzpF = motionEvent;
        this.zzpP = false;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(android.view.MotionEvent r11) {
        /*
            r10 = this;
            boolean r0 = r10.zzpO
            if (r0 == 0) goto L_0x0035
            r0 = 0
            r10.zzpK = r0
            r10.zzpJ = r0
            r10.zzpI = r0
            r10.zzpH = r0
            r10.zzpL = r0
            r10.zzpN = r0
            r10.zzpM = r0
            java.util.LinkedList<android.view.MotionEvent> r0 = r10.zzpG
            java.util.Iterator r0 = r0.iterator()
        L_0x001a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x002a
            java.lang.Object r1 = r0.next()
            android.view.MotionEvent r1 = (android.view.MotionEvent) r1
            r1.recycle()
            goto L_0x001a
        L_0x002a:
            java.util.LinkedList<android.view.MotionEvent> r0 = r10.zzpG
            r0.clear()
            r0 = 0
            r10.zzpF = r0
            r0 = 0
            r10.zzpO = r0
        L_0x0035:
            int r0 = r11.getAction()
            r1 = 1
            r2 = 1
            switch(r0) {
                case 0: goto L_0x00c5;
                case 1: goto L_0x008e;
                case 2: goto L_0x0049;
                case 3: goto L_0x0041;
                default: goto L_0x003f;
            }
        L_0x003f:
            goto L_0x00cb
        L_0x0041:
            long r4 = r10.zzpK
            long r6 = r4 + r2
            r10.zzpK = r6
            goto L_0x00cb
        L_0x0049:
            long r2 = r10.zzpI
            int r0 = r11.getHistorySize()
            int r0 = r0 + r1
            long r4 = (long) r0
            long r6 = r2 + r4
            r10.zzpI = r6
            com.google.android.gms.internal.zzbe r11 = r10.zzb((android.view.MotionEvent) r11)     // Catch:{ zzba -> 0x00cb }
            boolean r0 = zza((com.google.android.gms.internal.zzbe) r11)     // Catch:{ zzba -> 0x00cb }
            if (r0 == 0) goto L_0x0073
            long r2 = r10.zzpM     // Catch:{ zzba -> 0x00cb }
            java.lang.Long r0 = r11.zzcf     // Catch:{ zzba -> 0x00cb }
            long r4 = r0.longValue()     // Catch:{ zzba -> 0x00cb }
            java.lang.Long r0 = r11.zzqM     // Catch:{ zzba -> 0x00cb }
            long r6 = r0.longValue()     // Catch:{ zzba -> 0x00cb }
            long r8 = r4 + r6
            long r4 = r2 + r8
            r10.zzpM = r4     // Catch:{ zzba -> 0x00cb }
        L_0x0073:
            boolean r0 = r10.zzb((com.google.android.gms.internal.zzbe) r11)     // Catch:{ zzba -> 0x00cb }
            if (r0 == 0) goto L_0x00cb
            long r2 = r10.zzpN     // Catch:{ zzba -> 0x00cb }
            java.lang.Long r0 = r11.zzcd     // Catch:{ zzba -> 0x00cb }
            long r4 = r0.longValue()     // Catch:{ zzba -> 0x00cb }
            java.lang.Long r11 = r11.zzqN     // Catch:{ zzba -> 0x00cb }
            long r6 = r11.longValue()     // Catch:{ zzba -> 0x00cb }
            long r8 = r4 + r6
            long r4 = r2 + r8
            r10.zzpN = r4     // Catch:{ zzba -> 0x00cb }
            goto L_0x00cb
        L_0x008e:
            android.view.MotionEvent r11 = android.view.MotionEvent.obtain(r11)
            r10.zzpF = r11
            java.util.LinkedList<android.view.MotionEvent> r11 = r10.zzpG
            android.view.MotionEvent r0 = r10.zzpF
            r11.add(r0)
            java.util.LinkedList<android.view.MotionEvent> r11 = r10.zzpG
            int r11 = r11.size()
            r0 = 6
            if (r11 <= r0) goto L_0x00af
            java.util.LinkedList<android.view.MotionEvent> r11 = r10.zzpG
            java.lang.Object r11 = r11.remove()
            android.view.MotionEvent r11 = (android.view.MotionEvent) r11
            r11.recycle()
        L_0x00af:
            long r4 = r10.zzpJ
            long r6 = r4 + r2
            r10.zzpJ = r6
            java.lang.Throwable r11 = new java.lang.Throwable     // Catch:{ zzba -> 0x00cb }
            r11.<init>()     // Catch:{ zzba -> 0x00cb }
            java.lang.StackTraceElement[] r11 = r11.getStackTrace()     // Catch:{ zzba -> 0x00cb }
            long r2 = r10.zza((java.lang.StackTraceElement[]) r11)     // Catch:{ zzba -> 0x00cb }
            r10.zzpL = r2     // Catch:{ zzba -> 0x00cb }
            goto L_0x00cb
        L_0x00c5:
            long r4 = r10.zzpH
            long r6 = r4 + r2
            r10.zzpH = r6
        L_0x00cb:
            r10.zzpP = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzas.zza(android.view.MotionEvent):void");
    }

    /* access modifiers changed from: protected */
    public abstract zzbe zzb(MotionEvent motionEvent) throws zzba;

    public String zzb(Context context) {
        if (!zzbf.zzbc() || !zzgd.zzDU.get().booleanValue()) {
            return zza(context, (String) null, false, (View) null, (byte[]) null);
        }
        throw new IllegalStateException("The caller must not be called from the UI thread.");
    }

    public String zzb(Context context, String str) {
        return zza(context, str, (View) null);
    }
}
