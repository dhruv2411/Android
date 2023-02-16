package com.google.android.gms.tasks;

import android.app.Activity;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzabe;
import com.google.android.gms.internal.zzabf;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

final class zzh<TResult> extends Task<TResult> {
    private final zzg<TResult> zzbNE = new zzg<>();
    private boolean zzbNF;
    private TResult zzbNG;
    private Exception zzbNH;
    private final Object zzrJ = new Object();

    private static class zza extends zzabe {
        private final List<WeakReference<zzf<?>>> mListeners = new ArrayList();

        private zza(zzabf zzabf) {
            super(zzabf);
            this.zzaCR.zza("TaskOnStopCallback", (zzabe) this);
        }

        public static zza zzw(Activity activity) {
            zzabf zzs = zzs(activity);
            zza zza = (zza) zzs.zza("TaskOnStopCallback", zza.class);
            return zza == null ? new zza(zzs) : zza;
        }

        @MainThread
        public void onStop() {
            synchronized (this.mListeners) {
                for (WeakReference<zzf<?>> weakReference : this.mListeners) {
                    zzf zzf = (zzf) weakReference.get();
                    if (zzf != null) {
                        zzf.cancel();
                    }
                }
                this.mListeners.clear();
            }
        }

        public <T> void zzb(zzf<T> zzf) {
            synchronized (this.mListeners) {
                this.mListeners.add(new WeakReference(zzf));
            }
        }
    }

    zzh() {
    }

    private void zzTG() {
        zzac.zza(this.zzbNF, (Object) "Task is not yet complete");
    }

    private void zzTH() {
        zzac.zza(!this.zzbNF, (Object) "Task is already complete");
    }

    private void zzTI() {
        synchronized (this.zzrJ) {
            if (this.zzbNF) {
                this.zzbNE.zza(this);
            }
        }
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull Activity activity, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        zzc zzc = new zzc(TaskExecutors.MAIN_THREAD, onCompleteListener);
        this.zzbNE.zza(zzc);
        zza.zzw(activity).zzb(zzc);
        zzTI();
        return this;
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.zzbNE.zza(new zzc(executor, onCompleteListener));
        zzTI();
        return this;
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
        zzd zzd = new zzd(TaskExecutors.MAIN_THREAD, onFailureListener);
        this.zzbNE.zza(zzd);
        zza.zzw(activity).zzb(zzd);
        zzTI();
        return this;
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    @NonNull
    public Task<TResult> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzbNE.zza(new zzd(executor, onFailureListener));
        zzTI();
        return this;
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        zze zze = new zze(TaskExecutors.MAIN_THREAD, onSuccessListener);
        this.zzbNE.zza(zze);
        zza.zzw(activity).zzb(zze);
        zzTI();
        return this;
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.MAIN_THREAD, onSuccessListener);
    }

    @NonNull
    public Task<TResult> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzbNE.zza(new zze(executor, onSuccessListener));
        zzTI();
        return this;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWith(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation) {
        zzh zzh = new zzh();
        this.zzbNE.zza(new zza(executor, continuation, zzh));
        zzTI();
        return zzh;
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(TaskExecutors.MAIN_THREAD, continuation);
    }

    @NonNull
    public <TContinuationResult> Task<TContinuationResult> continueWithTask(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation) {
        zzh zzh = new zzh();
        this.zzbNE.zza(new zzb(executor, continuation, zzh));
        zzTI();
        return zzh;
    }

    @Nullable
    public Exception getException() {
        Exception exc;
        synchronized (this.zzrJ) {
            exc = this.zzbNH;
        }
        return exc;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.zzrJ) {
            zzTG();
            if (this.zzbNH != null) {
                throw new RuntimeExecutionException(this.zzbNH);
            }
            tresult = this.zzbNG;
        }
        return tresult;
    }

    public <X extends Throwable> TResult getResult(@NonNull Class<X> cls) throws Throwable {
        TResult tresult;
        synchronized (this.zzrJ) {
            zzTG();
            if (cls.isInstance(this.zzbNH)) {
                throw ((Throwable) cls.cast(this.zzbNH));
            } else if (this.zzbNH != null) {
                throw new RuntimeExecutionException(this.zzbNH);
            } else {
                tresult = this.zzbNG;
            }
        }
        return tresult;
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzbNF;
        }
        return z;
    }

    public boolean isSuccessful() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzbNF && this.zzbNH == null;
        }
        return z;
    }

    public void setException(@NonNull Exception exc) {
        zzac.zzb(exc, (Object) "Exception must not be null");
        synchronized (this.zzrJ) {
            zzTH();
            this.zzbNF = true;
            this.zzbNH = exc;
        }
        this.zzbNE.zza(this);
    }

    public void setResult(TResult tresult) {
        synchronized (this.zzrJ) {
            zzTH();
            this.zzbNF = true;
            this.zzbNG = tresult;
        }
        this.zzbNE.zza(this);
    }

    public boolean trySetException(@NonNull Exception exc) {
        zzac.zzb(exc, (Object) "Exception must not be null");
        synchronized (this.zzrJ) {
            if (this.zzbNF) {
                return false;
            }
            this.zzbNF = true;
            this.zzbNH = exc;
            this.zzbNE.zza(this);
            return true;
        }
    }

    public boolean trySetResult(TResult tresult) {
        synchronized (this.zzrJ) {
            if (this.zzbNF) {
                return false;
            }
            this.zzbNF = true;
            this.zzbNG = tresult;
            this.zzbNE.zza(this);
            return true;
        }
    }
}
