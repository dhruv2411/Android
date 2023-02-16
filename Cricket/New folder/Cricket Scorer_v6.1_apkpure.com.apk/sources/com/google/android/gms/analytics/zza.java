package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzrt;
import com.google.android.gms.internal.zzrx;
import com.google.android.gms.internal.zzsc;
import java.util.ListIterator;

public class zza extends zzg<zza> {
    private final zzsc zzabn;
    private boolean zzabo;

    public zza(zzsc zzsc) {
        super(zzsc.zznU(), zzsc.zznR());
        this.zzabn = zzsc;
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzabo = z;
    }

    /* access modifiers changed from: protected */
    public void zza(zze zze) {
        zzrt zzrt = (zzrt) zze.zzb(zzrt.class);
        if (TextUtils.isEmpty(zzrt.zzmy())) {
            zzrt.setClientId(this.zzabn.zzoi().zzoQ());
        }
        if (this.zzabo && TextUtils.isEmpty(zzrt.zznv())) {
            zzrx zzoh = this.zzabn.zzoh();
            zzrt.zzbF(zzoh.zznG());
            zzrt.zzS(zzoh.zznw());
        }
    }

    public void zzbo(String str) {
        zzac.zzdr(str);
        zzbp(str);
        zzmO().add(new zzb(this.zzabn, str));
    }

    public void zzbp(String str) {
        Uri zzbq = zzb.zzbq(str);
        ListIterator<zzi> listIterator = zzmO().listIterator();
        while (listIterator.hasNext()) {
            if (zzbq.equals(listIterator.next().zzmr())) {
                listIterator.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public zzsc zzmn() {
        return this.zzabn;
    }

    public zze zzmo() {
        zze zzmC = zzmN().zzmC();
        zzmC.zza((zzf) this.zzabn.zznZ().zzoy());
        zzmC.zza((zzf) this.zzabn.zzoa().zzpB());
        zzd(zzmC);
        return zzmC;
    }
}
