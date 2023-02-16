package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

public interface AdaptiveMediaSourceEventListener {
    void onDownstreamFormatChanged(int i, Format format, int i2, Object obj, long j);

    void onLoadCanceled(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5);

    void onLoadCompleted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5);

    void onLoadError(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z);

    void onLoadStarted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3);

    void onUpstreamDiscarded(int i, long j, long j2);

    public static final class EventDispatcher {
        private final Handler handler;
        /* access modifiers changed from: private */
        public final AdaptiveMediaSourceEventListener listener;
        private final long mediaTimeOffsetMs;

        public EventDispatcher(Handler handler2, AdaptiveMediaSourceEventListener adaptiveMediaSourceEventListener) {
            this(handler2, adaptiveMediaSourceEventListener, 0);
        }

        public EventDispatcher(Handler handler2, AdaptiveMediaSourceEventListener adaptiveMediaSourceEventListener, long j) {
            this.handler = adaptiveMediaSourceEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = adaptiveMediaSourceEventListener;
            this.mediaTimeOffsetMs = j;
        }

        public EventDispatcher copyWithMediaTimeOffsetMs(long j) {
            return new EventDispatcher(this.handler, this.listener, j);
        }

        public void loadStarted(DataSpec dataSpec, int i, long j) {
            loadStarted(dataSpec, i, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET, j);
        }

        public void loadStarted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3) {
            if (this.listener != null) {
                Handler handler2 = this.handler;
                final DataSpec dataSpec2 = dataSpec;
                final int i4 = i;
                final int i5 = i2;
                final Format format2 = format;
                final int i6 = i3;
                final Object obj2 = obj;
                final long j4 = j;
                final long j5 = j2;
                AnonymousClass1 r14 = r0;
                final long j6 = j3;
                AnonymousClass1 r0 = new Runnable() {
                    public void run() {
                        EventDispatcher.this.listener.onLoadStarted(dataSpec2, i4, i5, format2, i6, obj2, EventDispatcher.this.adjustMediaTime(j4), EventDispatcher.this.adjustMediaTime(j5), j6);
                    }
                };
                handler2.post(r14);
            }
        }

        public void loadCompleted(DataSpec dataSpec, int i, long j, long j2, long j3) {
            loadCompleted(dataSpec, i, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET, j, j2, j3);
        }

        public void loadCompleted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
            if (this.listener != null) {
                final DataSpec dataSpec2 = dataSpec;
                final int i4 = i;
                final int i5 = i2;
                final Format format2 = format;
                final int i6 = i3;
                final Object obj2 = obj;
                final long j6 = j;
                final long j7 = j2;
                AnonymousClass2 r18 = r0;
                final long j8 = j3;
                final long j9 = j4;
                final long j10 = j5;
                AnonymousClass2 r0 = new Runnable(this) {
                    final /* synthetic */ EventDispatcher this$0;

                    {
                        this.this$0 = r4;
                    }

                    public void run() {
                        AdaptiveMediaSourceEventListener access$100 = this.this$0.listener;
                        DataSpec dataSpec = dataSpec2;
                        int i = i4;
                        int i2 = i5;
                        Format format = format2;
                        int i3 = i6;
                        Object obj = obj2;
                        long access$000 = this.this$0.adjustMediaTime(j6);
                        long access$0002 = this.this$0.adjustMediaTime(j7);
                        long j = j8;
                        long j2 = j;
                        access$100.onLoadCompleted(dataSpec, i, i2, format, i3, obj, access$000, access$0002, j2, j9, j10);
                    }
                };
                this.handler.post(r18);
            }
        }

        public void loadCanceled(DataSpec dataSpec, int i, long j, long j2, long j3) {
            loadCanceled(dataSpec, i, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET, j, j2, j3);
        }

        public void loadCanceled(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
            if (this.listener != null) {
                final DataSpec dataSpec2 = dataSpec;
                final int i4 = i;
                final int i5 = i2;
                final Format format2 = format;
                final int i6 = i3;
                final Object obj2 = obj;
                final long j6 = j;
                final long j7 = j2;
                AnonymousClass3 r18 = r0;
                final long j8 = j3;
                final long j9 = j4;
                final long j10 = j5;
                AnonymousClass3 r0 = new Runnable(this) {
                    final /* synthetic */ EventDispatcher this$0;

                    {
                        this.this$0 = r4;
                    }

                    public void run() {
                        AdaptiveMediaSourceEventListener access$100 = this.this$0.listener;
                        DataSpec dataSpec = dataSpec2;
                        int i = i4;
                        int i2 = i5;
                        Format format = format2;
                        int i3 = i6;
                        Object obj = obj2;
                        long access$000 = this.this$0.adjustMediaTime(j6);
                        long access$0002 = this.this$0.adjustMediaTime(j7);
                        long j = j8;
                        long j2 = j;
                        access$100.onLoadCanceled(dataSpec, i, i2, format, i3, obj, access$000, access$0002, j2, j9, j10);
                    }
                };
                this.handler.post(r18);
            }
        }

        public void loadError(DataSpec dataSpec, int i, long j, long j2, long j3, IOException iOException, boolean z) {
            loadError(dataSpec, i, -1, (Format) null, 0, (Object) null, C.TIME_UNSET, C.TIME_UNSET, j, j2, j3, iOException, z);
        }

        public void loadError(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
            if (this.listener != null) {
                final DataSpec dataSpec2 = dataSpec;
                final int i4 = i;
                final int i5 = i2;
                final Format format2 = format;
                final int i6 = i3;
                final Object obj2 = obj;
                final long j6 = j;
                final long j7 = j2;
                AnonymousClass4 r20 = r0;
                final long j8 = j3;
                final long j9 = j4;
                final long j10 = j5;
                final IOException iOException2 = iOException;
                final boolean z2 = z;
                AnonymousClass4 r0 = new Runnable(this) {
                    final /* synthetic */ EventDispatcher this$0;

                    {
                        this.this$0 = r4;
                    }

                    public void run() {
                        AdaptiveMediaSourceEventListener access$100 = this.this$0.listener;
                        DataSpec dataSpec = dataSpec2;
                        int i = i4;
                        int i2 = i5;
                        Format format = format2;
                        int i3 = i6;
                        Object obj = obj2;
                        long access$000 = this.this$0.adjustMediaTime(j6);
                        long access$0002 = this.this$0.adjustMediaTime(j7);
                        long j = j8;
                        long j2 = j9;
                        long j3 = j10;
                        long j4 = j3;
                        access$100.onLoadError(dataSpec, i, i2, format, i3, obj, access$000, access$0002, j, j2, j4, iOException2, z2);
                    }
                };
                this.handler.post(r20);
            }
        }

        public void upstreamDiscarded(int i, long j, long j2) {
            if (this.listener != null) {
                final int i2 = i;
                final long j3 = j;
                final long j4 = j2;
                this.handler.post(new Runnable() {
                    public void run() {
                        EventDispatcher.this.listener.onUpstreamDiscarded(i2, EventDispatcher.this.adjustMediaTime(j3), EventDispatcher.this.adjustMediaTime(j4));
                    }
                });
            }
        }

        public void downstreamFormatChanged(int i, Format format, int i2, Object obj, long j) {
            if (this.listener != null) {
                final int i3 = i;
                final Format format2 = format;
                final int i4 = i2;
                final Object obj2 = obj;
                final long j2 = j;
                this.handler.post(new Runnable() {
                    public void run() {
                        EventDispatcher.this.listener.onDownstreamFormatChanged(i3, format2, i4, obj2, EventDispatcher.this.adjustMediaTime(j2));
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public long adjustMediaTime(long j) {
            long usToMs = C.usToMs(j);
            if (usToMs == C.TIME_UNSET) {
                return C.TIME_UNSET;
            }
            return this.mediaTimeOffsetMs + usToMs;
        }
    }
}
