package com.facebook.ads.internal.view.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.d.c;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.j.b;
import com.facebook.ads.internal.q.a.x;
import com.facebook.ads.internal.q.b.e;
import java.lang.ref.WeakReference;

public class d extends AsyncTask<String, Void, Bitmap[]> {
    private static final String b = "d";
    public boolean a = false;
    private final WeakReference<Context> c;
    private final int d;
    @Nullable
    private final WeakReference<ImageView> e;
    @Nullable
    private final WeakReference<b> f;
    @Nullable
    private final WeakReference<ViewGroup> g;
    private e h;
    private int i = -1;
    private int j = -1;

    public d(ViewGroup viewGroup, int i2) {
        this.c = new WeakReference<>(viewGroup.getContext());
        this.f = null;
        this.e = null;
        this.g = new WeakReference<>(viewGroup);
        this.d = i2;
    }

    public d(ImageView imageView) {
        this.c = new WeakReference<>(imageView.getContext());
        this.f = null;
        this.e = new WeakReference<>(imageView);
        this.g = null;
        this.d = 0;
    }

    public d(b bVar) {
        this.c = new WeakReference<>(bVar.getContext());
        this.f = new WeakReference<>(bVar);
        this.e = null;
        this.g = null;
        this.d = 0;
    }

    public d a() {
        this.i = -1;
        this.j = -1;
        return this;
    }

    public d a(int i2, int i3) {
        this.i = i2;
        this.j = i3;
        return this;
    }

    public d a(e eVar) {
        this.h = eVar;
        return this;
    }

    public d a(boolean z) {
        this.a = z;
        return this;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            executeOnExecutor(THREAD_POOL_EXECUTOR, new String[]{str});
        } else if (this.h != null) {
            this.h.a(false);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Bitmap[] bitmapArr) {
        b bVar;
        ImageView imageView;
        boolean z = false;
        if (!(this.e == null || (imageView = (ImageView) this.e.get()) == null)) {
            imageView.setImageBitmap(bitmapArr[0]);
        }
        if (!(this.f == null || (bVar = (b) this.f.get()) == null)) {
            bVar.a(bitmapArr[0], bitmapArr[1]);
        }
        if (!(this.g == null || this.g.get() == null || bitmapArr[1] == null)) {
            x.a((View) this.g.get(), (Drawable) new BitmapDrawable(((Context) this.c.get()).getResources(), bitmapArr[1]));
        }
        if (this.h != null) {
            e eVar = this.h;
            if (bitmapArr[0] != null) {
                z = true;
            }
            eVar.a(z);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Bitmap[] doInBackground(String... strArr) {
        Bitmap bitmap;
        String str = strArr[0];
        Context context = (Context) this.c.get();
        Bitmap bitmap2 = null;
        if (context == null) {
            return new Bitmap[]{null, null};
        }
        try {
            bitmap = c.a(context).a(str, this.i, this.j);
            try {
                boolean z = (this.f == null || this.f.get() == null) ? false : true;
                boolean z2 = (this.g == null || this.g.get() == null) ? false : true;
                if ((z || z2) && bitmap != null && !this.a) {
                    e eVar = new e(bitmap);
                    eVar.a(this.d != 0 ? this.d : Math.round(((float) bitmap.getWidth()) / 40.0f));
                    bitmap2 = eVar.a();
                }
            } catch (Throwable th) {
                th = th;
                Log.e(b, "Error downloading image: " + str, th);
                b.a(a.a(th, (String) null));
                return new Bitmap[]{bitmap, bitmap2};
            }
        } catch (Throwable th2) {
            th = th2;
            bitmap = null;
            Log.e(b, "Error downloading image: " + str, th);
            b.a(a.a(th, (String) null));
            return new Bitmap[]{bitmap, bitmap2};
        }
        return new Bitmap[]{bitmap, bitmap2};
    }
}
