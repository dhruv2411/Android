package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzac;
import java.util.UUID;

public class zztg extends zzsa {
    /* access modifiers changed from: private */
    public SharedPreferences zzagD;
    private long zzagE;
    private long zzagF = -1;
    private final zza zzagG = new zza("monitoring", zznT().zzpz());

    public final class zza {
        private final String mName;
        private final long zzagH;

        private zza(String str, long j) {
            zzac.zzdr(str);
            zzac.zzax(j > 0);
            this.mName = str;
            this.zzagH = j;
        }

        private void zzqk() {
            long currentTimeMillis = zztg.this.zznR().currentTimeMillis();
            SharedPreferences.Editor edit = zztg.this.zzagD.edit();
            edit.remove(zzqp());
            edit.remove(zzqq());
            edit.putLong(zzqo(), currentTimeMillis);
            edit.commit();
        }

        private long zzql() {
            long zzqn = zzqn();
            if (zzqn == 0) {
                return 0;
            }
            return Math.abs(zzqn - zztg.this.zznR().currentTimeMillis());
        }

        private long zzqn() {
            return zztg.this.zzagD.getLong(zzqo(), 0);
        }

        private String zzqo() {
            return String.valueOf(this.mName).concat(":start");
        }

        private String zzqp() {
            return String.valueOf(this.mName).concat(":count");
        }

        public void zzcc(String str) {
            if (zzqn() == 0) {
                zzqk();
            }
            if (str == null) {
                str = "";
            }
            synchronized (this) {
                long j = zztg.this.zzagD.getLong(zzqp(), 0);
                if (j <= 0) {
                    SharedPreferences.Editor edit = zztg.this.zzagD.edit();
                    edit.putString(zzqq(), str);
                    edit.putLong(zzqp(), 1);
                    edit.apply();
                    return;
                }
                long j2 = j + 1;
                boolean z = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / j2;
                SharedPreferences.Editor edit2 = zztg.this.zzagD.edit();
                if (z) {
                    edit2.putString(zzqq(), str);
                }
                edit2.putLong(zzqp(), j2);
                edit2.apply();
            }
        }

        public Pair<String, Long> zzqm() {
            long zzql = zzql();
            if (zzql < this.zzagH) {
                return null;
            }
            if (zzql > this.zzagH * 2) {
                zzqk();
                return null;
            }
            String string = zztg.this.zzagD.getString(zzqq(), (String) null);
            long j = zztg.this.zzagD.getLong(zzqp(), 0);
            zzqk();
            if (string == null || j <= 0) {
                return null;
            }
            return new Pair<>(string, Long.valueOf(j));
        }

        /* access modifiers changed from: protected */
        public String zzqq() {
            return String.valueOf(this.mName).concat(":value");
        }
    }

    protected zztg(zzsc zzsc) {
        super(zzsc);
    }

    public void zzcb(String str) {
        zzmR();
        zzob();
        SharedPreferences.Editor edit = this.zzagD.edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("installation_campaign");
        } else {
            edit.putString("installation_campaign", str);
        }
        if (!edit.commit()) {
            zzbS("Failed to commit campaign data");
        }
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        this.zzagD = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }

    public long zzqe() {
        zzmR();
        zzob();
        if (this.zzagE == 0) {
            long j = this.zzagD.getLong("first_run", 0);
            if (j == 0) {
                j = zznR().currentTimeMillis();
                SharedPreferences.Editor edit = this.zzagD.edit();
                edit.putLong("first_run", j);
                if (!edit.commit()) {
                    zzbS("Failed to commit first run time");
                }
            }
            this.zzagE = j;
        }
        return this.zzagE;
    }

    public zztj zzqf() {
        return new zztj(zznR(), zzqe());
    }

    public long zzqg() {
        zzmR();
        zzob();
        if (this.zzagF == -1) {
            this.zzagF = this.zzagD.getLong("last_dispatch", 0);
        }
        return this.zzagF;
    }

    public void zzqh() {
        zzmR();
        zzob();
        long currentTimeMillis = zznR().currentTimeMillis();
        SharedPreferences.Editor edit = this.zzagD.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.zzagF = currentTimeMillis;
    }

    public String zzqi() {
        zzmR();
        zzob();
        String string = this.zzagD.getString("installation_campaign", (String) null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    public zza zzqj() {
        return this.zzagG;
    }
}
