package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class zzav extends Thread implements zzau {
    private static zzav zzbGq;
    private volatile boolean mClosed = false;
    /* access modifiers changed from: private */
    public final Context mContext;
    private volatile boolean zzNA = false;
    private final LinkedBlockingQueue<Runnable> zzbGp = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */
    public volatile zzaw zzbGr;

    private zzav(Context context) {
        super("GAThread");
        this.mContext = context != null ? context.getApplicationContext() : context;
        start();
    }

    static zzav zzca(Context context) {
        if (zzbGq == null) {
            zzbGq = new zzav(context);
        }
        return zzbGq;
    }

    private String zzg(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public void run() {
        while (true) {
            boolean z = this.mClosed;
            try {
                Runnable take = this.zzbGp.take();
                if (!this.zzNA) {
                    take.run();
                }
            } catch (InterruptedException e) {
                zzbo.zzbg(e.toString());
            } catch (Throwable th) {
                String valueOf = String.valueOf(zzg(th));
                zzbo.e(valueOf.length() != 0 ? "Error on Google TagManager Thread: ".concat(valueOf) : new String("Error on Google TagManager Thread: "));
                zzbo.e("Google TagManager is shutting down.");
                this.zzNA = true;
            }
        }
    }

    public void zzhj(String str) {
        zzp(str, System.currentTimeMillis());
    }

    public void zzp(Runnable runnable) {
        this.zzbGp.add(runnable);
    }

    /* access modifiers changed from: package-private */
    public void zzp(String str, long j) {
        final long j2 = j;
        final String str2 = str;
        zzp(new Runnable() {
            public void run() {
                if (zzav.this.zzbGr == null) {
                    zzdc zzRy = zzdc.zzRy();
                    zzRy.zza(zzav.this.mContext, this);
                    zzaw unused = zzav.this.zzbGr = zzRy.zzRB();
                }
                zzav.this.zzbGr.zzg(j2, str2);
            }
        });
    }
}
