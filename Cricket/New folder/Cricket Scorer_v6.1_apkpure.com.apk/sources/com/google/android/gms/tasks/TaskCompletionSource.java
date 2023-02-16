package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult> {
    private final zzh<TResult> zzbNC = new zzh<>();

    @NonNull
    public Task<TResult> getTask() {
        return this.zzbNC;
    }

    public void setException(@NonNull Exception exc) {
        this.zzbNC.setException(exc);
    }

    public void setResult(TResult tresult) {
        this.zzbNC.setResult(tresult);
    }

    public boolean trySetException(@NonNull Exception exc) {
        return this.zzbNC.trySetException(exc);
    }

    public boolean trySetResult(TResult tresult) {
        return this.zzbNC.trySetResult(tresult);
    }
}
