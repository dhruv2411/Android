package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzac;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzaaa extends zzaae {
    private final SparseArray<zza> zzazN = new SparseArray<>();

    private class zza implements GoogleApiClient.OnConnectionFailedListener {
        public final int zzazO;
        public final GoogleApiClient zzazP;
        public final GoogleApiClient.OnConnectionFailedListener zzazQ;

        public zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zzazO = i;
            this.zzazP = googleApiClient;
            this.zzazQ = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.append(str).append("GoogleApiClient #").print(this.zzazO);
            printWriter.println(":");
            this.zzazP.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(27 + String.valueOf(valueOf).length());
            sb.append("beginFailureResolution for ");
            sb.append(valueOf);
            Log.d("AutoManageHelper", sb.toString());
            zzaaa.this.zzb(connectionResult, this.zzazO);
        }

        public void zzvy() {
            this.zzazP.unregisterConnectionFailedListener(this);
            this.zzazP.disconnect();
        }
    }

    private zzaaa(zzabf zzabf) {
        super(zzabf);
        this.zzaCR.zza("AutoManageHelper", (zzabe) this);
    }

    public static zzaaa zza(zzabd zzabd) {
        zzabf zzc = zzc(zzabd);
        zzaaa zzaaa = (zzaaa) zzc.zza("AutoManageHelper", zzaaa.class);
        return zzaaa != null ? zzaaa : new zzaaa(zzc);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zzazN.size(); i++) {
            this.zzazN.valueAt(i).dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zzazN);
        StringBuilder sb = new StringBuilder(14 + String.valueOf(valueOf).length());
        sb.append("onStart ");
        sb.append(z);
        sb.append(" ");
        sb.append(valueOf);
        Log.d("AutoManageHelper", sb.toString());
        if (!this.zzazZ) {
            for (int i = 0; i < this.zzazN.size(); i++) {
                this.zzazN.valueAt(i).zzazP.connect();
            }
        }
    }

    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.zzazN.size(); i++) {
            this.zzazN.valueAt(i).zzazP.disconnect();
        }
    }

    public void zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzac.zzb(googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        boolean z = this.zzazN.indexOfKey(i) < 0;
        StringBuilder sb = new StringBuilder(54);
        sb.append("Already managing a GoogleApiClient with id ");
        sb.append(i);
        zzac.zza(z, (Object) sb.toString());
        boolean z2 = this.mStarted;
        boolean z3 = this.zzazZ;
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("starting AutoManage for client ");
        sb2.append(i);
        sb2.append(" ");
        sb2.append(z2);
        sb2.append(" ");
        sb2.append(z3);
        Log.d("AutoManageHelper", sb2.toString());
        this.zzazN.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && !this.zzazZ) {
            String valueOf = String.valueOf(googleApiClient);
            StringBuilder sb3 = new StringBuilder(11 + String.valueOf(valueOf).length());
            sb3.append("connecting ");
            sb3.append(valueOf);
            Log.d("AutoManageHelper", sb3.toString());
            googleApiClient.connect();
        }
    }

    /* access modifiers changed from: protected */
    public void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zza2 = this.zzazN.get(i);
        if (zza2 != null) {
            zzcA(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zza2.zzazQ;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    public void zzcA(int i) {
        zza zza2 = this.zzazN.get(i);
        this.zzazN.remove(i);
        if (zza2 != null) {
            zza2.zzvy();
        }
    }

    /* access modifiers changed from: protected */
    public void zzvx() {
        for (int i = 0; i < this.zzazN.size(); i++) {
            this.zzazN.valueAt(i).zzazP.connect();
        }
    }
}
