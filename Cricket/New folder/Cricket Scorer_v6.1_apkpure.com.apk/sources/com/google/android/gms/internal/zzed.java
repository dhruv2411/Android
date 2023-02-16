package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@zzme
public final class zzed {
    private Bundle mExtras;
    private Location zzcV;
    private boolean zzsS;
    private long zzzj;
    private int zzzk;
    private List<String> zzzl;
    private boolean zzzm;
    private int zzzn;
    private String zzzo;
    private zzfp zzzp;
    private String zzzq;
    private Bundle zzzr;
    private Bundle zzzs;
    private List<String> zzzt;
    private String zzzu;
    private String zzzv;
    private boolean zzzw;

    public zzed() {
        this.zzzj = -1;
        this.mExtras = new Bundle();
        this.zzzk = -1;
        this.zzzl = new ArrayList();
        this.zzzm = false;
        this.zzzn = -1;
        this.zzsS = false;
        this.zzzo = null;
        this.zzzp = null;
        this.zzcV = null;
        this.zzzq = null;
        this.zzzr = new Bundle();
        this.zzzs = new Bundle();
        this.zzzt = new ArrayList();
        this.zzzu = null;
        this.zzzv = null;
        this.zzzw = false;
    }

    public zzed(zzec zzec) {
        this.zzzj = zzec.zzyT;
        this.mExtras = zzec.extras;
        this.zzzk = zzec.zzyU;
        this.zzzl = zzec.zzyV;
        this.zzzm = zzec.zzyW;
        this.zzzn = zzec.zzyX;
        this.zzsS = zzec.zzyY;
        this.zzzo = zzec.zzyZ;
        this.zzzp = zzec.zzza;
        this.zzcV = zzec.zzzb;
        this.zzzq = zzec.zzzc;
        this.zzzr = zzec.zzzd;
        this.zzzs = zzec.zzze;
        this.zzzt = zzec.zzzf;
        this.zzzu = zzec.zzzg;
        this.zzzv = zzec.zzzh;
    }

    public zzed zza(@Nullable Location location) {
        this.zzcV = location;
        return this;
    }

    public zzec zzeC() {
        long j = this.zzzj;
        Bundle bundle = this.mExtras;
        int i = this.zzzk;
        List<String> list = this.zzzl;
        boolean z = this.zzzm;
        int i2 = this.zzzn;
        boolean z2 = this.zzsS;
        String str = this.zzzo;
        zzfp zzfp = this.zzzp;
        Location location = this.zzcV;
        String str2 = this.zzzq;
        Bundle bundle2 = this.zzzr;
        Bundle bundle3 = this.zzzs;
        return new zzec(7, j, bundle, i, list, z, i2, z2, str, zzfp, location, str2, bundle2, bundle3, this.zzzt, this.zzzu, this.zzzv, false);
    }
}
