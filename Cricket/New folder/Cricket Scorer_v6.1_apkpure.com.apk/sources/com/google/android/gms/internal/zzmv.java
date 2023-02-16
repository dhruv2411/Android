package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.util.zzp;
import java.io.DataInputStream;
import java.io.IOException;

@zzme
public final class zzmv extends zza {
    public static final Parcelable.Creator<zzmv> CREATOR = new zzmw();
    ParcelFileDescriptor zzSQ;
    private Parcelable zzSR;
    private boolean zzSS;

    zzmv(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzSQ = parcelFileDescriptor;
        this.zzSR = null;
        this.zzSS = true;
    }

    public zzmv(SafeParcelable safeParcelable) {
        this.zzSQ = null;
        this.zzSR = safeParcelable;
        this.zzSS = false;
    }

    /* JADX INFO: finally extract failed */
    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzSQ == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzSR.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                this.zzSQ = zzj(marshall);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        }
        zzmw.zza(this, parcel, i);
    }

    /* JADX INFO: finally extract failed */
    public <T extends SafeParcelable> T zza(Parcelable.Creator<T> creator) {
        if (this.zzSS) {
            if (this.zzSQ == null) {
                zzpk.e("File descriptor is empty, returning null.");
                return null;
            }
            DataInputStream dataInputStream = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzSQ));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr, 0, bArr.length);
                zzp.zzb(dataInputStream);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    this.zzSR = (SafeParcelable) creator.createFromParcel(obtain);
                    obtain.recycle();
                    this.zzSS = false;
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (IOException e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th2) {
                zzp.zzb(dataInputStream);
                throw th2;
            }
        }
        return (SafeParcelable) this.zzSR;
    }

    /* access modifiers changed from: protected */
    public <T> ParcelFileDescriptor zzj(final byte[] bArr) {
        final ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]);
            try {
                new Thread(new Runnable(this) {
                    /* JADX WARNING: Failed to insert additional move for type inference */
                    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r5 = this;
                            r0 = 0
                            java.io.DataOutputStream r1 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x001b, all -> 0x0016 }
                            java.io.OutputStream r2 = r3     // Catch:{ IOException -> 0x001b, all -> 0x0016 }
                            r1.<init>(r2)     // Catch:{ IOException -> 0x001b, all -> 0x0016 }
                            byte[] r0 = r5     // Catch:{ IOException -> 0x0014 }
                            int r0 = r0.length     // Catch:{ IOException -> 0x0014 }
                            r1.writeInt(r0)     // Catch:{ IOException -> 0x0014 }
                            byte[] r0 = r5     // Catch:{ IOException -> 0x0014 }
                            r1.write(r0)     // Catch:{ IOException -> 0x0014 }
                            goto L_0x0035
                        L_0x0014:
                            r0 = move-exception
                            goto L_0x001f
                        L_0x0016:
                            r1 = move-exception
                            r4 = r1
                            r1 = r0
                            r0 = r4
                            goto L_0x003a
                        L_0x001b:
                            r1 = move-exception
                            r4 = r1
                            r1 = r0
                            r0 = r4
                        L_0x001f:
                            java.lang.String r2 = "Error transporting the ad response"
                            com.google.android.gms.internal.zzpk.zzb(r2, r0)     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.internal.zzpe r2 = com.google.android.gms.ads.internal.zzw.zzcQ()     // Catch:{ all -> 0x0039 }
                            java.lang.String r3 = "LargeParcelTeleporter.pipeData.1"
                            r2.zza((java.lang.Throwable) r0, (java.lang.String) r3)     // Catch:{ all -> 0x0039 }
                            if (r1 != 0) goto L_0x0035
                            java.io.OutputStream r0 = r3
                            com.google.android.gms.common.util.zzp.zzb(r0)
                            return
                        L_0x0035:
                            com.google.android.gms.common.util.zzp.zzb(r1)
                            return
                        L_0x0039:
                            r0 = move-exception
                        L_0x003a:
                            if (r1 != 0) goto L_0x003e
                            java.io.OutputStream r1 = r3
                        L_0x003e:
                            com.google.android.gms.common.util.zzp.zzb(r1)
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzmv.AnonymousClass1.run():void");
                    }
                }).start();
                return createPipe[0];
            } catch (IOException e) {
                e = e;
                zzpk.zzb("Error transporting the ad response", e);
                zzw.zzcQ().zza((Throwable) e, "LargeParcelTeleporter.pipeData.2");
                zzp.zzb(autoCloseOutputStream);
                return null;
            }
        } catch (IOException e2) {
            e = e2;
            autoCloseOutputStream = null;
            zzpk.zzb("Error transporting the ad response", e);
            zzw.zzcQ().zza((Throwable) e, "LargeParcelTeleporter.pipeData.2");
            zzp.zzb(autoCloseOutputStream);
            return null;
        }
    }
}
