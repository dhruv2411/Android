package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult> implements zzf<TResult> {
    private final Executor zzbFM;
    /* access modifiers changed from: private */
    public OnFailureListener zzbNw;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    public zzd(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzbFM = executor;
        this.zzbNw = onFailureListener;
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            this.zzbNw = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (!task.isSuccessful()) {
            synchronized (this.zzrJ) {
                if (this.zzbNw != null) {
                    this.zzbFM.execute(new Runnable() {
                        public void run() {
                            synchronized (zzd.this.zzrJ) {
                                if (zzd.this.zzbNw != null) {
                                    zzd.this.zzbNw.onFailure(task.getException());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
