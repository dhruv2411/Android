package com.crashlytics.android.answers;

import io.fabric.sdk.android.services.concurrency.internal.RetryState;

class RetryManager {
    private static final long NANOSECONDS_IN_MS = 1000000;
    long lastRetry;
    private RetryState retryState;

    public RetryManager(RetryState retryState2) {
        if (retryState2 == null) {
            throw new NullPointerException("retryState must not be null");
        }
        this.retryState = retryState2;
    }

    public boolean canRetry(long j) {
        return j - this.lastRetry >= 1000000 * this.retryState.getRetryDelay();
    }

    public void recordRetry(long j) {
        this.lastRetry = j;
        this.retryState = this.retryState.nextRetryState();
    }

    public void reset() {
        this.lastRetry = 0;
        this.retryState = this.retryState.initialRetryState();
    }
}
