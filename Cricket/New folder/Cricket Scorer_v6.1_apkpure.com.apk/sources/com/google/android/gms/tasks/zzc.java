package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzc<TResult> implements zzf<TResult> {
    private final Executor zzbFM;
    /* access modifiers changed from: private */
    public OnCompleteListener<TResult> zzbNu;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    public zzc(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.zzbFM = executor;
        this.zzbNu = onCompleteListener;
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            this.zzbNu = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        synchronized (this.zzrJ) {
            if (this.zzbNu != null) {
                this.zzbFM.execute(new Runnable() {
                    public void run() {
                        synchronized (zzc.this.zzrJ) {
                            if (zzc.this.zzbNu != null) {
                                zzc.this.zzbNu.onComplete(task);
                            }
                        }
                    }
                });
            }
        }
    }
}
