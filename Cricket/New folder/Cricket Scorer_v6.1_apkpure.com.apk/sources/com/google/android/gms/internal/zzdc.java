package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzme
public class zzdc {
    private final Object zzrJ = new Object();
    private int zzxE;
    private List<zzdb> zzxF = new LinkedList();

    public boolean zza(zzdb zzdb) {
        synchronized (this.zzrJ) {
            return this.zzxF.contains(zzdb);
        }
    }

    public boolean zzb(zzdb zzdb) {
        synchronized (this.zzrJ) {
            Iterator<zzdb> it = this.zzxF.iterator();
            while (it.hasNext()) {
                zzdb next = it.next();
                if (!zzgd.zzCi.get().booleanValue() || zzw.zzcQ().zzkg()) {
                    if (zzgd.zzCk.get().booleanValue() && !zzw.zzcQ().zzkh() && zzdb != next && next.zzec().equals(zzdb.zzec())) {
                        it.remove();
                        return true;
                    }
                } else if (zzdb != next && next.zzea().equals(zzdb.zzea())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public void zzc(zzdb zzdb) {
        synchronized (this.zzrJ) {
            if (this.zzxF.size() >= 10) {
                int size = this.zzxF.size();
                StringBuilder sb = new StringBuilder(41);
                sb.append("Queue is full, current size = ");
                sb.append(size);
                zzpk.zzbf(sb.toString());
                this.zzxF.remove(0);
            }
            int i = this.zzxE;
            this.zzxE = i + 1;
            zzdb.zzn(i);
            this.zzxF.add(zzdb);
        }
    }

    @Nullable
    public zzdb zzei() {
        synchronized (this.zzrJ) {
            zzdb zzdb = null;
            if (this.zzxF.size() == 0) {
                zzpk.zzbf("Queue empty");
                return null;
            }
            int i = 0;
            if (this.zzxF.size() >= 2) {
                int i2 = Integer.MIN_VALUE;
                int i3 = 0;
                for (zzdb next : this.zzxF) {
                    int score = next.getScore();
                    if (score > i2) {
                        i = i3;
                        zzdb = next;
                        i2 = score;
                    }
                    i3++;
                }
                this.zzxF.remove(i);
                return zzdb;
            }
            zzdb zzdb2 = this.zzxF.get(0);
            zzdb2.zzed();
            return zzdb2;
        }
    }
}
