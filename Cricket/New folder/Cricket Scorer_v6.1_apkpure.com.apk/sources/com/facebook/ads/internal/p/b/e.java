package com.facebook.ads.internal.p.b;

import android.text.TextUtils;
import com.facebook.ads.internal.p.b.a.b;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;

class e extends k {
    private final h a;
    private final b b;
    private b c;

    public e(h hVar, b bVar) {
        super(hVar, bVar);
        this.b = bVar;
        this.a = hVar;
    }

    private void a(OutputStream outputStream, long j) {
        byte[] bArr = new byte[8192];
        while (true) {
            int a2 = a(bArr, j, bArr.length);
            if (a2 != -1) {
                outputStream.write(bArr, 0, a2);
                j += (long) a2;
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    private boolean a(d dVar) {
        int a2 = this.a.a();
        return !(a2 > 0) || !dVar.c || ((float) dVar.b) <= ((float) this.b.a()) + (((float) a2) * 0.2f);
    }

    private String b(d dVar) {
        String c2 = this.a.c();
        boolean z = !TextUtils.isEmpty(c2);
        int a2 = this.b.d() ? this.b.a() : this.a.a();
        boolean z2 = a2 >= 0;
        long j = dVar.c ? ((long) a2) - dVar.b : (long) a2;
        boolean z3 = z2 && dVar.c;
        StringBuilder sb = new StringBuilder();
        sb.append(dVar.c ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n");
        sb.append("Accept-Ranges: bytes\n");
        sb.append(z2 ? String.format("Content-Length: %d\n", new Object[]{Long.valueOf(j)}) : "");
        sb.append(z3 ? String.format("Content-Range: bytes %d-%d/%d\n", new Object[]{Long.valueOf(dVar.b), Integer.valueOf(a2 - 1), Integer.valueOf(a2)}) : "");
        sb.append(z ? String.format("Content-Type: %s\n", new Object[]{c2}) : "");
        sb.append("\n");
        return sb.toString();
    }

    private void b(OutputStream outputStream, long j) {
        try {
            h hVar = new h(this.a);
            hVar.a((int) j);
            byte[] bArr = new byte[8192];
            while (true) {
                int a2 = hVar.a(bArr);
                if (a2 != -1) {
                    outputStream.write(bArr, 0, a2);
                } else {
                    outputStream.flush();
                    return;
                }
            }
        } finally {
            this.a.b();
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        if (this.c != null) {
            this.c.a(this.b.a, this.a.a, i);
        }
    }

    public void a(b bVar) {
        this.c = bVar;
    }

    public void a(d dVar, Socket socket) {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(b(dVar).getBytes("UTF-8"));
        long j = dVar.b;
        if (a(dVar)) {
            a((OutputStream) bufferedOutputStream, j);
        } else {
            b(bufferedOutputStream, j);
        }
    }
}
