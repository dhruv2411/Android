package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.Window;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzh;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzcy;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzjj;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjr;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzkx;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmk;
import com.google.android.gms.internal.zzmn;
import com.google.android.gms.internal.zznd;
import com.google.android.gms.internal.zzoo;
import com.google.android.gms.internal.zzor;
import com.google.android.gms.internal.zzot;
import com.google.android.gms.internal.zzov;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpj;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzqx;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzm extends zzc implements zzih, zzin.zza {
    protected transient boolean zztB = false;
    private int zztC = -1;
    /* access modifiers changed from: private */
    public boolean zztD;
    /* access modifiers changed from: private */
    public float zztE;
    private final zzov zztF;

    @zzme
    private class zza extends zzpj {
        private final int zztH;

        public zza(int i) {
            this.zztH = i;
        }

        public void onStop() {
        }

        public void zzco() {
            zzn zzn = new zzn(zzm.this.zzss.zztK, zzm.this.zzcl(), zzm.this.zztD, zzm.this.zztE, zzm.this.zzss.zztK ? this.zztH : -1);
            int requestedOrientation = zzm.this.zzss.zzvs.zzNH.getRequestedOrientation();
            if (requestedOrientation == -1) {
                requestedOrientation = zzm.this.zzss.zzvs.orientation;
            }
            final AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(zzm.this, zzm.this, zzm.this, zzm.this.zzss.zzvs.zzNH, requestedOrientation, zzm.this.zzss.zzvn, zzm.this.zzss.zzvs.zzSs, zzn);
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzw.zzcK().zza(zzm.this.zzss.zzqn, adOverlayInfoParcel);
                }
            });
        }
    }

    public zzm(Context context, zzeg zzeg, String str, zzka zzka, zzqh zzqh, zze zze) {
        super(context, zzeg, str, zzka, zzqh, zze);
        this.zztF = zzw.zzdl().zzjT() ? new zzov(context, str) : null;
    }

    static zzpb.zza zzc(zzpb.zza zza2) {
        zzpb.zza zza3 = zza2;
        try {
            String jSONObject = zznd.zzb(zza3.zzWm).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, zza3.zzTi.zzvl);
            zzjq zzjq = new zzjq(jSONObject, (String) null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), (String) null, (String) null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), (String) null, Collections.emptyList(), Collections.emptyList(), (String) null, (String) null, (String) null, (List<String>) null, (String) null, Collections.emptyList());
            zzmn zzmn = zza3.zzWm;
            zzjr zzjr = new zzjr(Collections.singletonList(zzjq), zzgd.zzDM.get().longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzmn.zzKI, zzmn.zzKJ, "", -1, 0, 1, (String) null, 0, -1, -1, false);
            zzmk zzmk = zza3.zzTi;
            String str = zzmn.zzNJ;
            String str2 = zzmn.body;
            List emptyList = Collections.emptyList();
            List emptyList2 = Collections.emptyList();
            long j = zzmn.zzSm;
            long j2 = zzmn.zzSo;
            List emptyList3 = Collections.emptyList();
            long j3 = zzmn.zzKL;
            int i = zzmn.orientation;
            String str3 = zzmn.zzSq;
            long j4 = zzmn.zzSr;
            String str4 = zzmn.zzSs;
            boolean z = zzmn.zzSt;
            String str5 = zzmn.zzSu;
            boolean z2 = zzmn.zzSw;
            boolean z3 = zzmn.zzzB;
            boolean z4 = zzmn.zzRG;
            boolean z5 = zzmn.zzSx;
            boolean z6 = zzmn.zzSy;
            String str6 = zzmn.zzSB;
            boolean z7 = zzmn.zzzC;
            boolean z8 = zzmn.zzzD;
            boolean z9 = z8;
            zzmn zzmn2 = new zzmn(zzmk, str, str2, emptyList, emptyList2, j, true, j2, emptyList3, j3, i, str3, j4, str4, z, str5, (String) null, z2, z3, z4, z5, z6, str6, z7, z9, (zzoo) null, Collections.emptyList(), Collections.emptyList(), zzmn.zzSF, zzmn.zzSG, zzmn.zzRV, zzmn.zzRW, zzmn.zzKI, zzmn.zzKJ, zzmn.zzSH, (zzor) null, zzmn.zzSJ, zzmn.zzSK, zzmn.zzSh);
            zzpb.zza zza4 = zza2;
            return new zzpb.zza(zza4.zzTi, zzmn2, zzjr, zza4.zzvr, zza4.errorCode, zza4.zzWg, zza4.zzWh, (JSONObject) null);
        } catch (JSONException e) {
            zzpb.zza zza5 = zza3;
            zzpk.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return zza5;
        }
    }

    private void zzc(Bundle bundle) {
        zzw.zzcM().zzb(this.zzss.zzqn, this.zzss.zzvn.zzba, "gmob-apps", bundle, false);
    }

    public void showInterstitial() {
        String str;
        zzac.zzdj("showInterstitial must be called on the main UI thread.");
        if (this.zzss.zzvs == null) {
            str = "The interstitial has not loaded.";
        } else {
            if (zzgd.zzDx.get().booleanValue()) {
                String packageName = (this.zzss.zzqn.getApplicationContext() != null ? this.zzss.zzqn.getApplicationContext() : this.zzss.zzqn).getPackageName();
                if (!this.zztB) {
                    zzpk.zzbh("It is not recommended to show an interstitial before onAdLoaded completes.");
                    Bundle bundle = new Bundle();
                    bundle.putString("appid", packageName);
                    bundle.putString("action", "show_interstitial_before_load_finish");
                    zzc(bundle);
                }
                if (!zzw.zzcM().zzP(this.zzss.zzqn)) {
                    zzpk.zzbh("It is not recommended to show an interstitial when app is not in foreground.");
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("appid", packageName);
                    bundle2.putString("action", "show_interstitial_app_not_in_foreground");
                    zzc(bundle2);
                }
            }
            if (!this.zzss.zzdr()) {
                if (this.zzss.zzvs.zzSn && this.zzss.zzvs.zzLj != null) {
                    try {
                        this.zzss.zzvs.zzLj.showInterstitial();
                        return;
                    } catch (RemoteException e) {
                        zzpk.zzc("Could not show interstitial.", e);
                        zzcm();
                        return;
                    }
                } else if (this.zzss.zzvs.zzNH == null) {
                    str = "The interstitial failed to load.";
                } else if (this.zzss.zzvs.zzNH.zzlz()) {
                    str = "The interstitial is already showing.";
                } else {
                    this.zzss.zzvs.zzNH.zzK(true);
                    if (this.zzss.zzvs.zzWa != null) {
                        this.zzsu.zza(this.zzss.zzvr, this.zzss.zzvs);
                    }
                    zzt.zzzg();
                    final zzpb zzpb = this.zzss.zzvs;
                    if (zzpb.zzdD()) {
                        new zzcy(this.zzss.zzqn, zzpb.zzNH.getView()).zza((zzcy.zzb) zzpb.zzNH);
                    } else {
                        zzpb.zzNH.zzlv().zza((zzqx.zzc) new zzqx.zzc() {
                            public void zzcf() {
                                new zzcy(zzm.this.zzss.zzqn, zzpb.zzNH.getView()).zza((zzcy.zzb) zzpb.zzNH);
                            }
                        });
                    }
                    Bitmap zzQ = this.zzss.zztK ? zzw.zzcM().zzQ(this.zzss.zzqn) : null;
                    this.zztC = zzw.zzdh().zzb(zzQ);
                    if (!zzgd.zzEa.get().booleanValue() || zzQ == null) {
                        zzn zzn = new zzn(this.zzss.zztK, zzcl(), false, 0.0f, -1);
                        int requestedOrientation = this.zzss.zzvs.zzNH.getRequestedOrientation();
                        if (requestedOrientation == -1) {
                            requestedOrientation = this.zzss.zzvs.orientation;
                        }
                        zzw.zzcK().zza(this.zzss.zzqn, new AdOverlayInfoParcel(this, this, this, this.zzss.zzvs.zzNH, requestedOrientation, this.zzss.zzvn, this.zzss.zzvs.zzSs, zzn));
                        return;
                    }
                    new zza(this.zztC).zziP();
                    return;
                }
            } else {
                return;
            }
        }
        zzpk.zzbh(str);
    }

    /* access modifiers changed from: protected */
    public zzqw zza(zzpb.zza zza2, @Nullable zzf zzf, @Nullable zzot zzot) {
        zzqw zza3 = zzw.zzcN().zza(this.zzss.zzqn, this.zzss.zzvr, false, false, this.zzss.zzvm, this.zzss.zzvn, this.zzsn, this, this.zzsv);
        zza3.zzlv().zza(this, (zzh) null, this, this, zzgd.zzCv.get().booleanValue(), this, this, zzf, (zzkx) null, zzot);
        zza((zzjj) zza3);
        zza3.zzbj(zza2.zzTi.zzRL);
        zzin.zza(zza3, (zzin.zza) this);
        return zza3;
    }

    public void zza(zzpb.zza zza2, zzgl zzgl) {
        if (!zzgd.zzDc.get().booleanValue()) {
            super.zza(zza2, zzgl);
        } else if (zza2.errorCode != -2) {
            super.zza(zza2, zzgl);
        } else {
            Bundle bundle = zza2.zzTi.zzRy.zzzd.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
            boolean z = bundle == null || !bundle.containsKey("gw");
            boolean z2 = true ^ zza2.zzWm.zzSn;
            if (z && z2) {
                this.zzss.zzvt = zzc(zza2);
            }
            super.zza(this.zzss.zzvt, zzgl);
        }
    }

    public void zza(boolean z, float f) {
        this.zztD = z;
        this.zztE = f;
    }

    public boolean zza(zzec zzec, zzgl zzgl) {
        if (this.zzss.zzvs == null) {
            return super.zza(zzec, zzgl);
        }
        zzpk.zzbh("An interstitial is already loading. Aborting.");
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzec zzec, zzpb zzpb, boolean z) {
        if (this.zzss.zzdq() && zzpb.zzNH != null) {
            zzw.zzcO().zzl(zzpb.zzNH);
        }
        return this.zzsr.zzcy();
    }

    public boolean zza(@Nullable zzpb zzpb, zzpb zzpb2) {
        if (!super.zza(zzpb, zzpb2)) {
            return false;
        }
        if (this.zzss.zzdq() || this.zzss.zzvN == null || zzpb2.zzWa == null) {
            return true;
        }
        this.zzsu.zza(this.zzss.zzvr, zzpb2, this.zzss.zzvN);
        return true;
    }

    public void zzb(zzoo zzoo) {
        if (this.zzss.zzvs != null) {
            if (this.zzss.zzvs.zzSE != null) {
                zzw.zzcM().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, this.zzss.zzvs.zzSE);
            }
            if (this.zzss.zzvs.zzSC != null) {
                zzoo = this.zzss.zzvs.zzSC;
            }
        }
        zza(zzoo);
    }

    /* access modifiers changed from: protected */
    public void zzbG() {
        zzcm();
        super.zzbG();
    }

    /* access modifiers changed from: protected */
    public void zzbJ() {
        super.zzbJ();
        this.zztB = true;
    }

    public void zzbN() {
        super.zzbN();
        if (zzw.zzdl().zzjT()) {
            this.zztF.zzC(false);
        }
    }

    public void zzbO() {
        zzqx zzlv;
        recordImpression();
        super.zzbO();
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzNH == null || (zzlv = this.zzss.zzvs.zzNH.zzlv()) == null)) {
            zzlv.zzlT();
        }
        if (zzw.zzdl().zzjT()) {
            zzw.zzdl().zza(this.zzss.zzqn, this.zzss.zzvl, zzw.zzdl().zzC(this.zzss.zzqn));
            this.zztF.zzC(true);
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzcl() {
        Window window;
        if (!(this.zzss.zzqn instanceof Activity) || (window = ((Activity) this.zzss.zzqn).getWindow()) == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, (Point) null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        return (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) ? false : true;
    }

    public void zzcm() {
        zzw.zzdh().zzb(Integer.valueOf(this.zztC));
        if (this.zzss.zzdq()) {
            this.zzss.zzdn();
            this.zzss.zzvs = null;
            this.zzss.zztK = false;
            this.zztB = false;
        }
    }

    public void zzcn() {
        if (!(this.zzss.zzvs == null || this.zzss.zzvs.zzWf == null)) {
            zzw.zzcM().zza(this.zzss.zzqn, this.zzss.zzvn.zzba, this.zzss.zzvs.zzWf);
        }
        zzbK();
    }

    public void zzg(boolean z) {
        this.zzss.zztK = z;
    }
}
