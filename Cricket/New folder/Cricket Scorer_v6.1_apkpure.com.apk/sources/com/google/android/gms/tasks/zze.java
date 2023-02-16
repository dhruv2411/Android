package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult> implements zzf<TResult> {
    private final Executor zzbFM;
    /* access modifiers changed from: private */
    public OnSuccessListener<? super TResult> zzbNy;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    public zze(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzbFM = executor;
        this.zzbNy = onSuccessListener;
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            this.zzbNy = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzrJ) {
                if (this.zzbNy != null) {
                    this.zzbFM.execute(new Runnable() {
                        public void run() {
                            synchronized (zze.this.zzrJ) {
                                if (zze.this.zzbNy != null) {
                                    zze.this.zzbNy.onSuccess(task.getResult());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
