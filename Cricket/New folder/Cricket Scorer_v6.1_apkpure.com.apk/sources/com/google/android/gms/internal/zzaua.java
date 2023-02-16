package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.zzac;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Locale;

class zzaua extends zzauh {
    static final Pair<String, Long> zzbsX = new Pair<>("", 0L);
    /* access modifiers changed from: private */
    public SharedPreferences zzagD;
    public final zzc zzbsY = new zzc("health_monitor", zzKn().zzpz());
    public final zzb zzbsZ = new zzb("last_upload", 0);
    public final zzb zzbta = new zzb("last_upload_attempt", 0);
    public final zzb zzbtb = new zzb("backoff", 0);
    public final zzb zzbtc = new zzb("last_delete_stale", 0);
    public final zzb zzbtd = new zzb("midnight_offset", 0);
    private String zzbte;
    private boolean zzbtf;
    private long zzbtg;
    private String zzbth;
    private long zzbti;
    private final Object zzbtj = new Object();
    private SecureRandom zzbtk;
    public final zzb zzbtl = new zzb("time_before_start", 10000);
    public final zzb zzbtm = new zzb("session_timeout", 1800000);
    public final zza zzbtn = new zza("start_new_session", true);
    public final zzb zzbto = new zzb("last_pause_time", 0);
    public final zzb zzbtp = new zzb("time_active", 0);
    public boolean zzbtq;

    public final class zza {
        private final String zzAX;
        private boolean zzayS;
        private final boolean zzbtr;
        private boolean zzbts;

        public zza(String str, boolean z) {
            zzac.zzdr(str);
            this.zzAX = str;
            this.zzbtr = z;
        }

        @WorkerThread
        private void zzMp() {
            if (!this.zzbts) {
                this.zzbts = true;
                this.zzayS = zzaua.this.zzagD.getBoolean(this.zzAX, this.zzbtr);
            }
        }

        @WorkerThread
        public boolean get() {
            zzMp();
            return this.zzayS;
        }

        @WorkerThread
        public void set(boolean z) {
            SharedPreferences.Editor edit = zzaua.this.zzagD.edit();
            edit.putBoolean(this.zzAX, z);
            edit.apply();
            this.zzayS = z;
        }
    }

    public final class zzb {
        private final String zzAX;
        private long zzadd;
        private boolean zzbts;
        private final long zzbtu;

        public zzb(String str, long j) {
            zzac.zzdr(str);
            this.zzAX = str;
            this.zzbtu = j;
        }

        @WorkerThread
        private void zzMp() {
            if (!this.zzbts) {
                this.zzbts = true;
                this.zzadd = zzaua.this.zzagD.getLong(this.zzAX, this.zzbtu);
            }
        }

        @WorkerThread
        public long get() {
            zzMp();
            return this.zzadd;
        }

        @WorkerThread
        public void set(long j) {
            SharedPreferences.Editor edit = zzaua.this.zzagD.edit();
            edit.putLong(this.zzAX, j);
            edit.apply();
            this.zzadd = j;
        }
    }

    public final class zzc {
        private final long zzagH;
        final String zzbtv;
        private final String zzbtw;
        private final String zzbtx;

        private zzc(String str, long j) {
            zzac.zzdr(str);
            zzac.zzax(j > 0);
            this.zzbtv = String.valueOf(str).concat(":start");
            this.zzbtw = String.valueOf(str).concat(":count");
            this.zzbtx = String.valueOf(str).concat(":value");
            this.zzagH = j;
        }

        @WorkerThread
        private void zzqk() {
            zzaua.this.zzmR();
            long currentTimeMillis = zzaua.this.zznR().currentTimeMillis();
            SharedPreferences.Editor edit = zzaua.this.zzagD.edit();
            edit.remove(this.zzbtw);
            edit.remove(this.zzbtx);
            edit.putLong(this.zzbtv, currentTimeMillis);
            edit.apply();
        }

        @WorkerThread
        private long zzql() {
            zzaua.this.zzmR();
            long zzqn = zzqn();
            if (zzqn != 0) {
                return Math.abs(zzqn - zzaua.this.zznR().currentTimeMillis());
            }
            zzqk();
            return 0;
        }

        @WorkerThread
        private long zzqn() {
            return zzaua.this.zzMj().getLong(this.zzbtv, 0);
        }

        @WorkerThread
        public void zzcc(String str) {
            zzk(str, 1);
        }

        @WorkerThread
        public void zzk(String str, long j) {
            zzaua.this.zzmR();
            if (zzqn() == 0) {
                zzqk();
            }
            if (str == null) {
                str = "";
            }
            long j2 = zzaua.this.zzagD.getLong(this.zzbtw, 0);
            if (j2 <= 0) {
                SharedPreferences.Editor edit = zzaua.this.zzagD.edit();
                edit.putString(this.zzbtx, str);
                edit.putLong(this.zzbtw, j);
                edit.apply();
                return;
            }
            long j3 = j2 + j;
            boolean z = (zzaua.this.zzMg().nextLong() & Long.MAX_VALUE) < (Long.MAX_VALUE / j3) * j;
            SharedPreferences.Editor edit2 = zzaua.this.zzagD.edit();
            if (z) {
                edit2.putString(this.zzbtx, str);
            }
            edit2.putLong(this.zzbtw, j3);
            edit2.apply();
        }

        @WorkerThread
        public Pair<String, Long> zzqm() {
            zzaua.this.zzmR();
            long zzql = zzql();
            if (zzql < this.zzagH) {
                return null;
            }
            if (zzql > this.zzagH * 2) {
                zzqk();
                return null;
            }
            String string = zzaua.this.zzMj().getString(this.zzbtx, (String) null);
            long j = zzaua.this.zzMj().getLong(this.zzbtw, 0);
            zzqk();
            return (string == null || j <= 0) ? zzaua.zzbsX : new Pair<>(string, Long.valueOf(j));
        }
    }

    zzaua(zzaue zzaue) {
        super(zzaue);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public SecureRandom zzMg() {
        zzmR();
        if (this.zzbtk == null) {
            this.zzbtk = new SecureRandom();
        }
        return this.zzbtk;
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public SharedPreferences zzMj() {
        zzmR();
        zzob();
        return this.zzagD;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void setMeasurementEnabled(boolean z) {
        zzmR();
        zzKl().zzMe().zzj("Setting measurementEnabled", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzMj().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public String zzKq() {
        zzmR();
        try {
            return com.google.firebase.iid.zzc.zzabL().getId();
        } catch (IllegalStateException unused) {
            zzKl().zzMa().log("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public String zzMh() {
        byte[] bArr = new byte[16];
        zzMg().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public long zzMi() {
        zzob();
        zzmR();
        long j = this.zzbtd.get();
        if (j != 0) {
            return j;
        }
        long nextInt = (long) (1 + zzMg().nextInt(86400000));
        this.zzbtd.set(nextInt);
        return nextInt;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public String zzMk() {
        zzmR();
        return zzMj().getString("gmp_app_id", (String) null);
    }

    /* access modifiers changed from: package-private */
    public String zzMl() {
        synchronized (this.zzbtj) {
            if (Math.abs(zznR().elapsedRealtime() - this.zzbti) >= 1000) {
                return null;
            }
            String str = this.zzbth;
            return str;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public Boolean zzMm() {
        zzmR();
        if (!zzMj().contains("use_service")) {
            return null;
        }
        return Boolean.valueOf(zzMj().getBoolean("use_service", false));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzMn() {
        zzmR();
        zzKl().zzMe().log("Clearing collection preferences.");
        boolean contains = zzMj().contains("measurement_enabled");
        boolean z = true;
        if (contains) {
            z = zzaL(true);
        }
        SharedPreferences.Editor edit = zzMj().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            setMeasurementEnabled(z);
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public String zzMo() {
        zzmR();
        String string = zzMj().getString("previous_os_version", (String) null);
        String zzLS = zzKc().zzLS();
        if (!TextUtils.isEmpty(zzLS) && !zzLS.equals(string)) {
            SharedPreferences.Editor edit = zzMj().edit();
            edit.putString("previous_os_version", zzLS);
            edit.apply();
        }
        return string;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzaK(boolean z) {
        zzmR();
        zzKl().zzMe().zzj("Setting useService", Boolean.valueOf(z));
        SharedPreferences.Editor edit = zzMj().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzaL(boolean z) {
        zzmR();
        return zzMj().getBoolean("measurement_enabled", z);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @NonNull
    public Pair<String, Boolean> zzfG(String str) {
        zzmR();
        long elapsedRealtime = zznR().elapsedRealtime();
        if (this.zzbte != null && elapsedRealtime < this.zzbtg) {
            return new Pair<>(this.zzbte, Boolean.valueOf(this.zzbtf));
        }
        this.zzbtg = elapsedRealtime + zzKn().zzfm(str);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            this.zzbte = advertisingIdInfo.getId();
            if (this.zzbte == null) {
                this.zzbte = "";
            }
            this.zzbtf = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Throwable th) {
            zzKl().zzMd().zzj("Unable to get advertising id", th);
            this.zzbte = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzbte, Boolean.valueOf(this.zzbtf));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public String zzfH(String str) {
        zzmR();
        String str2 = (String) zzfG(str).first;
        MessageDigest zzch = zzaut.zzch("MD5");
        if (zzch == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzch.digest(str2.getBytes()))});
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zzfI(String str) {
        zzmR();
        SharedPreferences.Editor edit = zzMj().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public void zzfJ(String str) {
        synchronized (this.zzbtj) {
            this.zzbth = str;
            this.zzbti = zznR().elapsedRealtime();
        }
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        this.zzagD = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzbtq = this.zzagD.getBoolean("has_been_opened", false);
        if (!this.zzbtq) {
            SharedPreferences.Editor edit = this.zzagD.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
    }
}
