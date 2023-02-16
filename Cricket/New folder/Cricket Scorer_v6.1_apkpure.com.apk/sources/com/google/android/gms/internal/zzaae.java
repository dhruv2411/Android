package com.google.android.gms.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.internal.zzaaz;

public abstract class zzaae extends zzabe implements DialogInterface.OnCancelListener {
    protected boolean mStarted;
    /* access modifiers changed from: private */
    public ConnectionResult zzaAa;
    /* access modifiers changed from: private */
    public int zzaAb;
    private final Handler zzaAc;
    protected boolean zzazZ;
    protected final GoogleApiAvailability zzazn;

    private class zza implements Runnable {
        private zza() {
        }

        @MainThread
        public void run() {
            if (zzaae.this.mStarted) {
                if (zzaae.this.zzaAa.hasResolution()) {
                    zzaae.this.zzaCR.startActivityForResult(GoogleApiActivity.zzb(zzaae.this.getActivity(), zzaae.this.zzaAa.getResolution(), zzaae.this.zzaAb, false), 1);
                } else if (zzaae.this.zzazn.isUserResolvableError(zzaae.this.zzaAa.getErrorCode())) {
                    zzaae.this.zzazn.zza(zzaae.this.getActivity(), zzaae.this.zzaCR, zzaae.this.zzaAa.getErrorCode(), 2, zzaae.this);
                } else if (zzaae.this.zzaAa.getErrorCode() == 18) {
                    final Dialog zza = zzaae.this.zzazn.zza(zzaae.this.getActivity(), (DialogInterface.OnCancelListener) zzaae.this);
                    zzaae.this.zzazn.zza(zzaae.this.getActivity().getApplicationContext(), (zzaaz.zza) new zzaaz.zza() {
                        public void zzvE() {
                            zzaae.this.zzvD();
                            if (zza.isShowing()) {
                                zza.dismiss();
                            }
                        }
                    });
                } else {
                    zzaae.this.zza(zzaae.this.zzaAa, zzaae.this.zzaAb);
                }
            }
        }
    }

    protected zzaae(zzabf zzabf) {
        this(zzabf, GoogleApiAvailability.getInstance());
    }

    zzaae(zzabf zzabf, GoogleApiAvailability googleApiAvailability) {
        super(zzabf);
        this.zzaAb = -1;
        this.zzaAc = new Handler(Looper.getMainLooper());
        this.zzazn = googleApiAvailability;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r3, int r4, android.content.Intent r5) {
        /*
            r2 = this;
            r0 = 1
            r1 = 0
            switch(r3) {
                case 1: goto L_0x0021;
                case 2: goto L_0x0006;
                default: goto L_0x0005;
            }
        L_0x0005:
            goto L_0x0039
        L_0x0006:
            com.google.android.gms.common.GoogleApiAvailability r3 = r2.zzazn
            android.app.Activity r4 = r2.getActivity()
            int r3 = r3.isGooglePlayServicesAvailable(r4)
            if (r3 != 0) goto L_0x0013
            goto L_0x0014
        L_0x0013:
            r0 = r1
        L_0x0014:
            com.google.android.gms.common.ConnectionResult r4 = r2.zzaAa
            int r4 = r4.getErrorCode()
            r5 = 18
            if (r4 != r5) goto L_0x003a
            if (r3 != r5) goto L_0x003a
            return
        L_0x0021:
            r3 = -1
            if (r4 != r3) goto L_0x0025
            goto L_0x003a
        L_0x0025:
            if (r4 != 0) goto L_0x0039
            r3 = 13
            if (r5 == 0) goto L_0x0031
            java.lang.String r4 = "<<ResolutionFailureErrorDetail>>"
            int r3 = r5.getIntExtra(r4, r3)
        L_0x0031:
            com.google.android.gms.common.ConnectionResult r4 = new com.google.android.gms.common.ConnectionResult
            r5 = 0
            r4.<init>(r3, r5)
            r2.zzaAa = r4
        L_0x0039:
            r0 = r1
        L_0x003a:
            if (r0 == 0) goto L_0x0040
            r2.zzvD()
            return
        L_0x0040:
            com.google.android.gms.common.ConnectionResult r3 = r2.zzaAa
            int r4 = r2.zzaAb
            r2.zza(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaae.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, (PendingIntent) null), this.zzaAb);
        zzvD();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzazZ = bundle.getBoolean("resolving_error", false);
            if (this.zzazZ) {
                this.zzaAb = bundle.getInt("failed_client_id", -1);
                this.zzaAa = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.zzazZ);
        if (this.zzazZ) {
            bundle.putInt("failed_client_id", this.zzaAb);
            bundle.putInt("failed_status", this.zzaAa.getErrorCode());
            bundle.putParcelable("failed_resolution", this.zzaAa.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(ConnectionResult connectionResult, int i);

    public void zzb(ConnectionResult connectionResult, int i) {
        if (!this.zzazZ) {
            this.zzazZ = true;
            this.zzaAb = i;
            this.zzaAa = connectionResult;
            this.zzaAc.post(new zza());
        }
    }

    /* access modifiers changed from: protected */
    public void zzvD() {
        this.zzaAb = -1;
        this.zzazZ = false;
        this.zzaAa = null;
        zzvx();
    }

    /* access modifiers changed from: protected */
    public abstract void zzvx();
}
