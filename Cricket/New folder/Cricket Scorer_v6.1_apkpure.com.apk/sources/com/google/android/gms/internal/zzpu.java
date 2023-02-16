package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzaa;
import java.util.ArrayList;
import java.util.List;

@zzme
public class zzpu {
    private final String[] zzYb;
    private final double[] zzYc;
    private final double[] zzYd;
    private final int[] zzYe;
    private int zzYf;

    public static class zza {
        public final int count;
        public final String name;
        public final double zzYg;
        public final double zzYh;
        public final double zzYi;

        public zza(String str, double d, double d2, double d3, int i) {
            this.name = str;
            this.zzYh = d;
            this.zzYg = d2;
            this.zzYi = d3;
            this.count = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zzaa.equal(this.name, zza.name) && this.zzYg == zza.zzYg && this.zzYh == zza.zzYh && this.count == zza.count && Double.compare(this.zzYi, zza.zzYi) == 0;
        }

        public int hashCode() {
            return zzaa.hashCode(this.name, Double.valueOf(this.zzYg), Double.valueOf(this.zzYh), Double.valueOf(this.zzYi), Integer.valueOf(this.count));
        }

        public String toString() {
            return zzaa.zzv(this).zzg("name", this.name).zzg("minBound", Double.valueOf(this.zzYh)).zzg("maxBound", Double.valueOf(this.zzYg)).zzg("percent", Double.valueOf(this.zzYi)).zzg("count", Integer.valueOf(this.count)).toString();
        }
    }

    public static class zzb {
        /* access modifiers changed from: private */
        public final List<String> zzYj = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Double> zzYk = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Double> zzYl = new ArrayList();

        public zzb zza(String str, double d, double d2) {
            int i = 0;
            while (i < this.zzYj.size()) {
                double doubleValue = this.zzYl.get(i).doubleValue();
                double doubleValue2 = this.zzYk.get(i).doubleValue();
                if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                    break;
                }
                i++;
            }
            this.zzYj.add(i, str);
            this.zzYl.add(i, Double.valueOf(d));
            this.zzYk.add(i, Double.valueOf(d2));
            return this;
        }

        public zzpu zzla() {
            return new zzpu(this);
        }
    }

    private zzpu(zzb zzb2) {
        int size = zzb2.zzYk.size();
        this.zzYb = (String[]) zzb2.zzYj.toArray(new String[size]);
        this.zzYc = zzn(zzb2.zzYk);
        this.zzYd = zzn(zzb2.zzYl);
        this.zzYe = new int[size];
        this.zzYf = 0;
    }

    private double[] zzn(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = list.get(i).doubleValue();
        }
        return dArr;
    }

    public List<zza> getBuckets() {
        ArrayList arrayList = new ArrayList(this.zzYb.length);
        for (int i = 0; i < this.zzYb.length; i++) {
            arrayList.add(new zza(this.zzYb[i], this.zzYd[i], this.zzYc[i], ((double) this.zzYe[i]) / ((double) this.zzYf), this.zzYe[i]));
        }
        return arrayList;
    }

    public void zza(double d) {
        this.zzYf++;
        int i = 0;
        while (i < this.zzYd.length) {
            if (this.zzYd[i] <= d && d < this.zzYc[i]) {
                int[] iArr = this.zzYe;
                iArr[i] = iArr[i] + 1;
            }
            if (d >= this.zzYd[i]) {
                i++;
            } else {
                return;
            }
        }
    }
}
