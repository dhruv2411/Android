package com.facebook.ads.internal.view.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.q.a.k;
import com.facebook.ads.internal.q.a.u;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.r.a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class a extends com.facebook.ads.internal.q.c.a {
    private static final String a = "a";
    /* access modifiers changed from: private */
    public final WeakReference<b> b;
    private final AtomicBoolean c = new AtomicBoolean();
    private final AtomicBoolean d = new AtomicBoolean(true);
    @Nullable
    private WeakReference<d> e;
    @Nullable
    private com.facebook.ads.internal.r.a f;
    /* access modifiers changed from: private */
    public u g = new u();
    private a.C0006a h;
    /* access modifiers changed from: private */
    public boolean i = true;
    private boolean j;

    /* renamed from: com.facebook.ads.internal.view.b.a$a  reason: collision with other inner class name */
    static class C0010a {
        private final String a = C0010a.class.getSimpleName();
        private final WeakReference<a> b;
        private final WeakReference<b> c;
        private final WeakReference<com.facebook.ads.internal.r.a> d;
        private final WeakReference<AtomicBoolean> e;
        private final WeakReference<AtomicBoolean> f;
        private final boolean g;

        C0010a(a aVar, b bVar, com.facebook.ads.internal.r.a aVar2, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, boolean z) {
            this.b = new WeakReference<>(aVar);
            this.c = new WeakReference<>(bVar);
            this.d = new WeakReference<>(aVar2);
            this.e = new WeakReference<>(atomicBoolean);
            this.f = new WeakReference<>(atomicBoolean2);
            this.g = z;
        }

        @JavascriptInterface
        public void alert(String str) {
            Log.e(this.a, str);
        }

        @JavascriptInterface
        public String getAnalogInfo() {
            return k.a(com.facebook.ads.internal.g.a.a());
        }

        @JavascriptInterface
        public void onMainAssetLoaded() {
            if (this.b.get() != null && this.e.get() != null && this.f.get() != null && this.g && ((AtomicBoolean) this.f.get()).get()) {
                ((AtomicBoolean) this.e.get()).set(true);
                if (((a) this.b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new e(this.d));
                }
            }
        }

        @JavascriptInterface
        public void onPageInitialized() {
            a aVar = (a) this.b.get();
            if (aVar != null && !aVar.c()) {
                b bVar = (b) this.c.get();
                if (bVar != null) {
                    bVar.a();
                }
                if (!this.g && ((a) this.b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new e(this.d));
                }
            }
        }
    }

    public interface b {
        void a();

        void a(int i);

        void a(@Nullable WebResourceError webResourceError);

        void a(String str, Map<String, String> map);

        void b();
    }

    public static class c implements b {
        public void a() {
        }

        public void a(int i) {
        }

        public void a(@Nullable WebResourceError webResourceError) {
        }

        public void a(String str, Map<String, String> map) {
        }

        public void b() {
        }
    }

    public interface d {
        void b();
    }

    static class e implements Runnable {
        private final WeakReference<com.facebook.ads.internal.r.a> a;

        e(com.facebook.ads.internal.r.a aVar) {
            this.a = new WeakReference<>(aVar);
        }

        e(WeakReference<com.facebook.ads.internal.r.a> weakReference) {
            this.a = weakReference;
        }

        public void run() {
            com.facebook.ads.internal.r.a aVar = (com.facebook.ads.internal.r.a) this.a.get();
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    static class f extends WebChromeClient {
        f() {
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }
    }

    static class g extends WebViewClient {
        private final WeakReference<b> a;
        private final WeakReference<com.facebook.ads.internal.r.a> b;
        private final WeakReference<u> c;
        private final WeakReference<AtomicBoolean> d;
        private final WeakReference<a> e;
        /* access modifiers changed from: private */
        public boolean f = false;

        g(WeakReference<b> weakReference, WeakReference<com.facebook.ads.internal.r.a> weakReference2, WeakReference<u> weakReference3, WeakReference<AtomicBoolean> weakReference4, WeakReference<a> weakReference5) {
            this.a = weakReference;
            this.b = weakReference2;
            this.c = weakReference3;
            this.d = weakReference4;
            this.e = weakReference5;
        }

        /* access modifiers changed from: private */
        public void a(@Nullable WebResourceError webResourceError) {
            if (this.a.get() != null) {
                ((b) this.a.get()).a(webResourceError);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (!(this.e.get() == null || this.d.get() == null || ((AtomicBoolean) this.d.get()).get())) {
                ((a) this.e.get()).e();
            }
            this.f = true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!g.this.f) {
                        g.this.a((WebResourceError) null);
                    }
                }
            }, 2000);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            this.f = true;
            a(webResourceError);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            HashMap hashMap = new HashMap();
            if (this.b.get() != null) {
                ((com.facebook.ads.internal.r.a) this.b.get()).a((Map<String, String>) hashMap);
            }
            if (this.c.get() != null) {
                hashMap.put("touch", k.a(((u) this.c.get()).e()));
            }
            if (this.a.get() == null) {
                return true;
            }
            ((b) this.a.get()).a(str, hashMap);
            return true;
        }
    }

    public a(Context context, WeakReference<b> weakReference, int i2) {
        super(context);
        this.j = com.facebook.ads.internal.l.a.v(context);
        this.b = weakReference;
        this.h = new a.C0006a() {
            public void a() {
                if (a.this.i || !a.this.g.b()) {
                    a.this.g.a();
                }
                if (a.this.b.get() != null) {
                    ((b) a.this.b.get()).b();
                }
            }
        };
        this.f = new com.facebook.ads.internal.r.a(this, i2, this.h);
        setWebChromeClient(a());
        setWebViewClient(b());
        getSettings().setSupportZoom(false);
        getSettings().setCacheMode(1);
        addJavascriptInterface(new C0010a(this, (b) weakReference.get(), this.f, this.c, this.d, this.j), "AdControl");
    }

    private boolean d() {
        return !this.j || this.c.get();
    }

    /* access modifiers changed from: private */
    public void e() {
        this.c.set(true);
        new Handler(Looper.getMainLooper()).post(new e(this.f));
        if (this.e != null && this.e.get() != null) {
            ((d) this.e.get()).b();
        }
    }

    /* access modifiers changed from: protected */
    public WebChromeClient a() {
        return new f();
    }

    public void a(int i2, int i3) {
        if (this.f != null) {
            this.f.a(i2);
            this.f.b(i3);
        }
    }

    /* access modifiers changed from: protected */
    public WebViewClient b() {
        return new g(this.b, new WeakReference(this.f), new WeakReference(this.g), new WeakReference(this.d), new WeakReference(this));
    }

    public void destroy() {
        if (this.f != null) {
            this.f.c();
            this.f = null;
        }
        x.b(this);
        this.h = null;
        this.g = null;
        com.facebook.ads.internal.q.c.b.a(this);
        super.destroy();
    }

    public Map<String, String> getTouchData() {
        return this.g.e();
    }

    public u getTouchDataRecorder() {
        return this.g;
    }

    public com.facebook.ads.internal.r.a getViewabilityChecker() {
        return this.f;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.g.a(motionEvent, this, this);
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (this.b.get() != null) {
            ((b) this.b.get()).a(i2);
        }
        if (this.f != null) {
            if (i2 == 0 && d()) {
                this.f.a();
            } else if (i2 == 8) {
                this.f.c();
            }
        }
    }

    public void setCheckAssetsByJavascriptBridge(boolean z) {
        this.d.set(z);
    }

    public void setLogMultipleImpressions(boolean z) {
        this.i = z;
    }

    public void setOnAssetsLoadedListener(d dVar) {
        this.e = new WeakReference<>(dVar);
    }

    public void setWaitForAssetsToLoad(boolean z) {
        this.j = z;
    }
}
