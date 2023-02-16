package com.google.android.gms.internal;

import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzy;
import com.google.android.gms.common.zzg;
import com.google.android.gms.common.zzh;
import com.google.android.gms.internal.zzatt;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class zzauf extends zzatt.zza {
    /* access modifiers changed from: private */
    public final zzaue zzbqc;
    private Boolean zzbuJ;
    @Nullable
    private String zzbuK;

    public zzauf(zzaue zzaue) {
        this(zzaue, (String) null);
    }

    public zzauf(zzaue zzaue, @Nullable String str) {
        zzac.zzw(zzaue);
        this.zzbqc = zzaue;
        this.zzbuK = str;
    }

    @BinderThread
    private void zzb(zzatd zzatd, boolean z) {
        zzac.zzw(zzatd);
        zzm(zzatd.packageName, z);
        this.zzbqc.zzKh().zzga(zzatd.zzbqL);
    }

    @BinderThread
    private void zzm(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            this.zzbqc.zzKl().zzLY().log("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        try {
            zzn(str, z);
        } catch (SecurityException e) {
            this.zzbqc.zzKl().zzLY().zzj("Measurement Service called with invalid calling package. appId", zzatx.zzfE(str));
            throw e;
        }
    }

    @BinderThread
    public List<zzauq> zza(final zzatd zzatd, boolean z) {
        zzb(zzatd, false);
        try {
            List<zzaus> list = (List) this.zzbqc.zzKk().zzd(new Callable<List<zzaus>>() {
                /* renamed from: zzMN */
                public List<zzaus> call() throws Exception {
                    zzauf.this.zzbqc.zzML();
                    return zzauf.this.zzbqc.zzKg().zzft(zzatd.packageName);
                }
            }).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzaus zzaus : list) {
                if (z || !zzaut.zzgd(zzaus.mName)) {
                    arrayList.add(new zzauq(zzaus));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzbqc.zzKl().zzLY().zze("Failed to get user attributes. appId", zzatx.zzfE(zzatd.packageName), e);
            return null;
        }
    }

    @BinderThread
    public List<zzatg> zza(final String str, final String str2, final zzatd zzatd) {
        zzb(zzatd, false);
        try {
            return (List) this.zzbqc.zzKk().zzd(new Callable<List<zzatg>>() {
                /* renamed from: zzMN */
                public List<zzatg> call() throws Exception {
                    zzauf.this.zzbqc.zzML();
                    return zzauf.this.zzbqc.zzKg().zzl(zzatd.packageName, str, str2);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzbqc.zzKl().zzLY().zzj("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public List<zzauq> zza(final String str, final String str2, final String str3, boolean z) {
        zzm(str, true);
        try {
            List<zzaus> list = (List) this.zzbqc.zzKk().zzd(new Callable<List<zzaus>>() {
                /* renamed from: zzMN */
                public List<zzaus> call() throws Exception {
                    zzauf.this.zzbqc.zzML();
                    return zzauf.this.zzbqc.zzKg().zzk(str, str2, str3);
                }
            }).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzaus zzaus : list) {
                if (z || !zzaut.zzgd(zzaus.mName)) {
                    arrayList.add(new zzauq(zzaus));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzbqc.zzKl().zzLY().zze("Failed to get user attributes. appId", zzatx.zzfE(str), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public List<zzauq> zza(final String str, final String str2, boolean z, final zzatd zzatd) {
        zzb(zzatd, false);
        try {
            List<zzaus> list = (List) this.zzbqc.zzKk().zzd(new Callable<List<zzaus>>() {
                /* renamed from: zzMN */
                public List<zzaus> call() throws Exception {
                    zzauf.this.zzbqc.zzML();
                    return zzauf.this.zzbqc.zzKg().zzk(zzatd.packageName, str, str2);
                }
            }).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzaus zzaus : list) {
                if (z || !zzaut.zzgd(zzaus.mName)) {
                    arrayList.add(new zzauq(zzaus));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzbqc.zzKl().zzLY().zze("Failed to get user attributes. appId", zzatx.zzfE(zzatd.packageName), e);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public void zza(long j, String str, String str2, String str3) {
        final String str4 = str2;
        final String str5 = str3;
        final String str6 = str;
        final long j2 = j;
        this.zzbqc.zzKk().zzm(new Runnable() {
            public void run() {
                if (str4 == null) {
                    zzauf.this.zzbqc.zzKe().zza(str5, (AppMeasurement.zzf) null);
                    return;
                }
                AppMeasurement.zzf zzf = new AppMeasurement.zzf();
                zzf.zzbqf = str6;
                zzf.zzbqg = str4;
                zzf.zzbqh = j2;
                zzauf.this.zzbqc.zzKe().zza(str5, zzf);
            }
        });
    }

    @BinderThread
    public void zza(final zzatd zzatd) {
        zzb(zzatd, false);
        this.zzbqc.zzKk().zzm(new Runnable() {
            public void run() {
                zzauf.this.zzbqc.zzML();
                zzauf.this.zzbqc.zze(zzatd);
            }
        });
    }

    @BinderThread
    public void zza(zzatg zzatg, final zzatd zzatd) {
        zzaud zzKk;
        Runnable r1;
        zzac.zzw(zzatg);
        zzac.zzw(zzatg.zzbqW);
        zzb(zzatd, false);
        final zzatg zzatg2 = new zzatg(zzatg);
        zzatg2.packageName = zzatd.packageName;
        if (zzatg.zzbqW.getValue() == null) {
            zzKk = this.zzbqc.zzKk();
            r1 = new Runnable() {
                public void run() {
                    zzauf.this.zzbqc.zzML();
                    zzauf.this.zzbqc.zzc(zzatg2, zzatd);
                }
            };
        } else {
            zzKk = this.zzbqc.zzKk();
            r1 = new Runnable() {
                public void run() {
                    zzauf.this.zzbqc.zzML();
                    zzauf.this.zzbqc.zzb(zzatg2, zzatd);
                }
            };
        }
        zzKk.zzm(r1);
    }

    @BinderThread
    public void zza(final zzatq zzatq, final zzatd zzatd) {
        zzac.zzw(zzatq);
        zzb(zzatd, false);
        this.zzbqc.zzKk().zzm(new Runnable() {
            public void run() {
                zzauf.this.zzbqc.zzML();
                zzauf.this.zzbqc.zzb(zzatq, zzatd);
            }
        });
    }

    @BinderThread
    public void zza(final zzatq zzatq, final String str, String str2) {
        zzac.zzw(zzatq);
        zzac.zzdr(str);
        zzm(str, true);
        this.zzbqc.zzKk().zzm(new Runnable() {
            public void run() {
                zzauf.this.zzbqc.zzML();
                zzauf.this.zzbqc.zzb(zzatq, str);
            }
        });
    }

    @BinderThread
    public void zza(final zzauq zzauq, final zzatd zzatd) {
        zzaud zzKk;
        Runnable r1;
        zzac.zzw(zzauq);
        zzb(zzatd, false);
        if (zzauq.getValue() == null) {
            zzKk = this.zzbqc.zzKk();
            r1 = new Runnable() {
                public void run() {
                    zzauf.this.zzbqc.zzML();
                    zzauf.this.zzbqc.zzc(zzauq, zzatd);
                }
            };
        } else {
            zzKk = this.zzbqc.zzKk();
            r1 = new Runnable() {
                public void run() {
                    zzauf.this.zzbqc.zzML();
                    zzauf.this.zzbqc.zzb(zzauq, zzatd);
                }
            };
        }
        zzKk.zzm(r1);
    }

    @BinderThread
    public byte[] zza(final zzatq zzatq, final String str) {
        zzac.zzdr(str);
        zzac.zzw(zzatq);
        zzm(str, true);
        this.zzbqc.zzKl().zzMd().zzj("Log and bundle. event", zzatq.name);
        long nanoTime = this.zzbqc.zznR().nanoTime() / C.MICROS_PER_SECOND;
        try {
            byte[] bArr = (byte[]) this.zzbqc.zzKk().zze(new Callable<byte[]>() {
                /* renamed from: zzMO */
                public byte[] call() throws Exception {
                    zzauf.this.zzbqc.zzML();
                    return zzauf.this.zzbqc.zza(zzatq, str);
                }
            }).get();
            if (bArr == null) {
                this.zzbqc.zzKl().zzLY().zzj("Log and bundle returned null. appId", zzatx.zzfE(str));
                bArr = new byte[0];
            }
            this.zzbqc.zzKl().zzMd().zzd("Log and bundle processed. event, size, time_ms", zzatq.name, Integer.valueOf(bArr.length), Long.valueOf((this.zzbqc.zznR().nanoTime() / C.MICROS_PER_SECOND) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzbqc.zzKl().zzLY().zzd("Failed to log and bundle. appId, event, error", zzatx.zzfE(str), zzatq.name, e);
            return null;
        }
    }

    @BinderThread
    public void zzb(final zzatd zzatd) {
        zzb(zzatd, false);
        this.zzbqc.zzKk().zzm(new Runnable() {
            public void run() {
                zzauf.this.zzbqc.zzML();
                zzauf.this.zzbqc.zzd(zzatd);
            }
        });
    }

    @BinderThread
    public void zzb(zzatg zzatg) {
        zzaud zzKk;
        Runnable r1;
        zzac.zzw(zzatg);
        zzac.zzw(zzatg.zzbqW);
        zzm(zzatg.packageName, true);
        final zzatg zzatg2 = new zzatg(zzatg);
        if (zzatg.zzbqW.getValue() == null) {
            zzKk = this.zzbqc.zzKk();
            r1 = new Runnable() {
                public void run() {
                    zzauf.this.zzbqc.zzML();
                    zzauf.this.zzbqc.zze(zzatg2);
                }
            };
        } else {
            zzKk = this.zzbqc.zzKk();
            r1 = new Runnable() {
                public void run() {
                    zzauf.this.zzbqc.zzML();
                    zzauf.this.zzbqc.zzd(zzatg2);
                }
            };
        }
        zzKk.zzm(r1);
    }

    @BinderThread
    public String zzc(zzatd zzatd) {
        zzb(zzatd, false);
        return this.zzbqc.zzfP(zzatd.packageName);
    }

    @BinderThread
    public List<zzatg> zzn(final String str, final String str2, final String str3) {
        zzm(str, true);
        try {
            return (List) this.zzbqc.zzKk().zzd(new Callable<List<zzatg>>() {
                /* renamed from: zzMN */
                public List<zzatg> call() throws Exception {
                    zzauf.this.zzbqc.zzML();
                    return zzauf.this.zzbqc.zzKg().zzl(str, str2, str3);
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzbqc.zzKl().zzLY().zzj("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    /* access modifiers changed from: protected */
    public void zzn(String str, boolean z) throws SecurityException {
        if (z) {
            if (this.zzbuJ == null) {
                this.zzbuJ = Boolean.valueOf("com.google.android.gms".equals(this.zzbuK) || zzy.zzf(this.zzbqc.getContext(), Binder.getCallingUid()) || zzh.zzaN(this.zzbqc.getContext()).zza(this.zzbqc.getContext().getPackageManager(), Binder.getCallingUid()));
            }
            if (this.zzbuJ.booleanValue()) {
                return;
            }
        }
        if (this.zzbuK == null && zzg.zzc(this.zzbqc.getContext(), Binder.getCallingUid(), str)) {
            this.zzbuK = str;
        }
        if (!str.equals(this.zzbuK)) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
        }
    }
}
