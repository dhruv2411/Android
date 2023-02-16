package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.ContainerHolder;

class zzo implements ContainerHolder {
    private boolean zzaLQ;
    private Status zzair;
    private Container zzbFc;
    private Container zzbFd;
    private zzb zzbFe;
    private zza zzbFf;
    private TagManager zzbFg;
    private final Looper zzrs;

    public interface zza {
        String zzQh();

        void zzQj();

        void zzgW(String str);
    }

    private class zzb extends Handler {
        private final ContainerHolder.ContainerAvailableListener zzbFh;

        public zzb(ContainerHolder.ContainerAvailableListener containerAvailableListener, Looper looper) {
            super(looper);
            this.zzbFh = containerAvailableListener;
        }

        public void handleMessage(Message message) {
            if (message.what != 1) {
                zzbo.e("Don't know how to handle this message.");
            } else {
                zzgY((String) message.obj);
            }
        }

        public void zzgX(String str) {
            sendMessage(obtainMessage(1, str));
        }

        /* access modifiers changed from: protected */
        public void zzgY(String str) {
            this.zzbFh.onContainerAvailable(zzo.this, str);
        }
    }

    public zzo(Status status) {
        this.zzair = status;
        this.zzrs = null;
    }

    public zzo(TagManager tagManager, Looper looper, Container container, zza zza2) {
        this.zzbFg = tagManager;
        this.zzrs = looper == null ? Looper.getMainLooper() : looper;
        this.zzbFc = container;
        this.zzbFf = zza2;
        this.zzair = Status.zzazx;
        tagManager.zza(this);
    }

    private void zzQi() {
        if (this.zzbFe != null) {
            this.zzbFe.zzgX(this.zzbFd.zzQf());
        }
    }

    public synchronized Container getContainer() {
        if (this.zzaLQ) {
            zzbo.e("ContainerHolder is released.");
            return null;
        }
        if (this.zzbFd != null) {
            this.zzbFc = this.zzbFd;
            this.zzbFd = null;
        }
        return this.zzbFc;
    }

    /* access modifiers changed from: package-private */
    public String getContainerId() {
        if (!this.zzaLQ) {
            return this.zzbFc.getContainerId();
        }
        zzbo.e("getContainerId called on a released ContainerHolder.");
        return "";
    }

    public Status getStatus() {
        return this.zzair;
    }

    public synchronized void refresh() {
        if (this.zzaLQ) {
            zzbo.e("Refreshing a released ContainerHolder.");
        } else {
            this.zzbFf.zzQj();
        }
    }

    public synchronized void release() {
        if (this.zzaLQ) {
            zzbo.e("Releasing a released ContainerHolder.");
            return;
        }
        this.zzaLQ = true;
        this.zzbFg.zzb(this);
        this.zzbFc.release();
        this.zzbFc = null;
        this.zzbFd = null;
        this.zzbFf = null;
        this.zzbFe = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setContainerAvailableListener(com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.zzaLQ     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x000c
            java.lang.String r3 = "ContainerHolder is released."
            com.google.android.gms.tagmanager.zzbo.e(r3)     // Catch:{ all -> 0x0024 }
            monitor-exit(r2)
            return
        L_0x000c:
            if (r3 != 0) goto L_0x0012
            r3 = 0
            r2.zzbFe = r3     // Catch:{ all -> 0x0024 }
            goto L_0x0022
        L_0x0012:
            com.google.android.gms.tagmanager.zzo$zzb r0 = new com.google.android.gms.tagmanager.zzo$zzb     // Catch:{ all -> 0x0024 }
            android.os.Looper r1 = r2.zzrs     // Catch:{ all -> 0x0024 }
            r0.<init>(r3, r1)     // Catch:{ all -> 0x0024 }
            r2.zzbFe = r0     // Catch:{ all -> 0x0024 }
            com.google.android.gms.tagmanager.Container r3 = r2.zzbFd     // Catch:{ all -> 0x0024 }
            if (r3 == 0) goto L_0x0022
            r2.zzQi()     // Catch:{ all -> 0x0024 }
        L_0x0022:
            monitor-exit(r2)
            return
        L_0x0024:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzo.setContainerAvailableListener(com.google.android.gms.tagmanager.ContainerHolder$ContainerAvailableListener):void");
    }

    /* access modifiers changed from: package-private */
    public String zzQh() {
        if (!this.zzaLQ) {
            return this.zzbFf.zzQh();
        }
        zzbo.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return "";
    }

    public synchronized void zza(Container container) {
        if (!this.zzaLQ) {
            if (container == null) {
                zzbo.e("Unexpected null container.");
                return;
            }
            this.zzbFd = container;
            zzQi();
        }
    }

    public synchronized void zzgU(String str) {
        if (!this.zzaLQ) {
            this.zzbFc.zzgU(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzgW(String str) {
        if (this.zzaLQ) {
            zzbo.e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzbFf.zzgW(str);
        }
    }
}
