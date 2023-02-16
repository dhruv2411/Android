package com.facebook.ads.internal.p.b;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.ads.internal.p.b.a.g;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class f {
    private final Object a;
    private final ExecutorService b;
    private final Map<String, g> c;
    private final ServerSocket d;
    private final int e;
    private final Thread f;
    private final c g;
    private boolean h;

    public static final class a {
        private File a;
        private com.facebook.ads.internal.p.b.a.c b = new com.facebook.ads.internal.p.b.a.f();
        private com.facebook.ads.internal.p.b.a.a c = new g(67108864);

        public a(Context context) {
            this.a = o.a(context);
        }

        /* access modifiers changed from: private */
        public c a() {
            return new c(this.a, this.b, this.c);
        }
    }

    private class b implements Callable<Boolean> {
        private b() {
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(f.this.c());
        }
    }

    private class c implements Callable<Boolean> {
        private final String b;

        public c(String str) {
            this.b = str;
        }

        /* renamed from: a */
        public Boolean call() {
            return Boolean.valueOf(f.this.c(this.b));
        }
    }

    private final class d implements Runnable {
        private final Socket b;

        public d(Socket socket) {
            this.b = socket;
        }

        public void run() {
            f.this.a(this.b);
        }
    }

    private final class e implements Runnable {
        private final CountDownLatch b;

        public e(CountDownLatch countDownLatch) {
            this.b = countDownLatch;
        }

        public void run() {
            this.b.countDown();
            f.this.e();
        }
    }

    public f(Context context) {
        this(new a(context).a());
    }

    private f(c cVar) {
        this.a = new Object();
        this.b = Executors.newFixedThreadPool(8);
        this.c = new ConcurrentHashMap();
        this.g = (c) j.a(cVar);
        try {
            this.d = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.e = this.d.getLocalPort();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.f = new Thread(new e(countDownLatch));
            this.f.start();
            countDownLatch.await();
            Log.i("ProxyCache", "Proxy cache server started. Ping it...");
            b();
        } catch (IOException | InterruptedException e2) {
            this.b.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e2);
        }
    }

    private void a(Throwable th) {
        Log.e("ProxyCache", "HttpProxyCacheServer error", th);
    }

    /* access modifiers changed from: private */
    public void a(Socket socket) {
        String str;
        StringBuilder sb;
        try {
            d a2 = d.a(socket.getInputStream());
            Log.i("ProxyCache", "Request to cache proxy:" + a2);
            String c2 = m.c(a2.a);
            if ("ping".equals(c2)) {
                b(socket);
            } else {
                e(c2).a(a2, socket);
            }
            c(socket);
            str = "ProxyCache";
            sb = new StringBuilder();
        } catch (SocketException unused) {
            Log.d("ProxyCache", "Closing socket... Socket is closed by client.");
            c(socket);
            str = "ProxyCache";
            sb = new StringBuilder();
        } catch (l | IOException e2) {
            a((Throwable) new l("Error processing request", e2));
            c(socket);
            str = "ProxyCache";
            sb = new StringBuilder();
        } catch (Throwable th) {
            c(socket);
            Log.d("ProxyCache", "Opened connections: " + f());
            throw th;
        }
        sb.append("Opened connections: ");
        sb.append(f());
        Log.d(str, sb.toString());
    }

    private void b() {
        int i = 300;
        int i2 = 0;
        while (i2 < 3) {
            try {
                long j = (long) i;
                this.h = ((Boolean) this.b.submit(new b()).get(j, TimeUnit.MILLISECONDS)).booleanValue();
                if (!this.h) {
                    SystemClock.sleep(j);
                    i2++;
                    i *= 2;
                } else {
                    return;
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                Log.e("ProxyCache", "Error pinging server [attempt: " + i2 + ", timeout: " + i + "]. ", e2);
            }
        }
        Log.e("ProxyCache", "Shutdown server... Error pinging server [attempts: " + i2 + ", max timeout: " + (i / 2) + "].");
        a();
    }

    private void b(Socket socket) {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
        outputStream.write("ping ok".getBytes());
    }

    private void c(Socket socket) {
        d(socket);
        e(socket);
        f(socket);
    }

    /* access modifiers changed from: private */
    public boolean c() {
        h hVar = new h(d("ping"));
        try {
            byte[] bytes = "ping ok".getBytes();
            hVar.a(0);
            byte[] bArr = new byte[bytes.length];
            hVar.a(bArr);
            boolean equals = Arrays.equals(bytes, bArr);
            Log.d("ProxyCache", "Ping response: `" + new String(bArr) + "`, pinged? " + equals);
            return equals;
        } catch (l e2) {
            Log.e("ProxyCache", "Error reading ping response", e2);
            return false;
        } finally {
            hVar.b();
        }
    }

    /* access modifiers changed from: private */
    public boolean c(String str) {
        h hVar = new h(d(str));
        try {
            hVar.a(0);
            do {
            } while (hVar.a(new byte[8192]) != -1);
            hVar.b();
            return true;
        } catch (l e2) {
            Log.e("ProxyCache", "Error reading url", e2);
            hVar.b();
            return false;
        } catch (Throwable th) {
            hVar.b();
            throw th;
        }
    }

    private String d(String str) {
        return String.format("http://%s:%d/%s", new Object[]{"127.0.0.1", Integer.valueOf(this.e), m.b(str)});
    }

    private void d() {
        synchronized (this.a) {
            for (g a2 : this.c.values()) {
                a2.a();
            }
            this.c.clear();
        }
    }

    private void d(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException unused) {
            Log.d("ProxyCache", "Releasing input stream... Socket is closed by client.");
        } catch (IOException e2) {
            a((Throwable) new l("Error closing socket input stream", e2));
        }
    }

    private g e(String str) {
        g gVar;
        synchronized (this.a) {
            gVar = this.c.get(str);
            if (gVar == null) {
                gVar = new g(str, this.g);
                this.c.put(str, gVar);
            }
        }
        return gVar;
    }

    /* access modifiers changed from: private */
    public void e() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = this.d.accept();
                Log.d("ProxyCache", "Accept new socket " + accept);
                this.b.submit(new d(accept));
            } catch (IOException e2) {
                a((Throwable) new l("Error during waiting connection", e2));
                return;
            }
        }
    }

    private void e(Socket socket) {
        try {
            if (socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (IOException e2) {
            a((Throwable) new l("Error closing socket output stream", e2));
        }
    }

    private int f() {
        int i;
        synchronized (this.a) {
            i = 0;
            for (g b2 : this.c.values()) {
                i += b2.b();
            }
        }
        return i;
    }

    private void f(Socket socket) {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e2) {
            a((Throwable) new l("Error closing socket", e2));
        }
    }

    public void a() {
        Log.i("ProxyCache", "Shutdown proxy server");
        d();
        this.f.interrupt();
        try {
            if (!this.d.isClosed()) {
                this.d.close();
            }
        } catch (IOException e2) {
            a((Throwable) new l("Error shutting down proxy server", e2));
        }
    }

    public boolean a(String str) {
        int i = 300;
        int i2 = 0;
        while (i2 < 3) {
            try {
                if (((Boolean) this.b.submit(new c(str)).get()).booleanValue()) {
                    return true;
                }
                SystemClock.sleep((long) i);
                i2++;
                i *= 2;
            } catch (InterruptedException | ExecutionException e2) {
                Log.e("ProxyCache", "Error precaching url [attempt: " + i2 + ", url: " + str + "]. ", e2);
            }
        }
        Log.e("ProxyCache", "Shutdown server... Error precaching url [attempts: " + i2 + ", url: " + str + "].");
        a();
        return false;
    }

    public String b(String str) {
        if (!this.h) {
            Log.e("ProxyCache", "Proxy server isn't pinged. Caching doesn't work.");
        }
        return this.h ? d(str) : str;
    }
}
