package com.facebook.ads.internal.k;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.facebook.ads.internal.q.d.b;

public class a {
    /* access modifiers changed from: private */
    public final Context a;
    private final String b;
    private final String c;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public Messenger e;
    private final ServiceConnection f = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean unused = a.this.d = true;
            Messenger unused2 = a.this.e = new Messenger(iBinder);
            Message obtain = Message.obtain((Handler) null, 1);
            obtain.setData(a.this.b());
            try {
                a.this.e.send(obtain);
            } catch (RemoteException e) {
                com.facebook.ads.internal.q.d.a.a(a.this.a, "generic", b.m, (Exception) e);
            }
            a.this.a.unbindService(this);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            try {
                a.this.a.unbindService(this);
            } catch (IllegalArgumentException unused) {
            }
            Messenger unused2 = a.this.e = null;
            boolean unused3 = a.this.d = false;
        }
    };

    public a(Context context, String str, String str2) {
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    /* access modifiers changed from: private */
    public Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putInt("PARAM_PROTOCOL_VERSION", 1);
        bundle.putString("PARAM_AN_UUID", this.c);
        bundle.putString("PARAM_REQUEST_ID", this.b);
        return bundle;
    }

    public void a() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.audiencenetwork.AudienceNetworkService");
        try {
            if (!this.a.bindService(intent, this.f, 1)) {
                this.a.unbindService(this.f);
            }
        } catch (Exception e2) {
            com.facebook.ads.internal.q.d.a.a(this.a, "generic", b.n, e2);
        }
    }
}
