package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.n.g;
import com.facebook.ads.internal.n.l;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.itextpdf.text.html.HtmlTags;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class x extends b {
    private final y c;
    private l d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private View j;
    @Nullable
    private g k;
    private List<View> l;
    private a m = a.ALL;

    public enum a {
        ALL("all"),
        NONE("none"),
        MANUAL("manual");
        
        private final String d;

        private a(String str) {
            this.d = str;
        }

        public String toString() {
            return this.d;
        }
    }

    public x(Context context, c cVar, com.facebook.ads.internal.r.a aVar, y yVar) {
        super(context, cVar, aVar);
        this.c = yVar;
    }

    private String b(View view) {
        try {
            return c(view).toString();
        } catch (JSONException unused) {
            return "Json exception";
        }
    }

    private JSONObject c(View view) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt(TtmlNode.ATTR_ID, Integer.valueOf(view.getId()));
        jSONObject.putOpt(HtmlTags.CLASS, view.getClass());
        boolean z = true;
        jSONObject.putOpt("origin", String.format("{x:%d, y:%d}", new Object[]{Integer.valueOf(view.getTop()), Integer.valueOf(view.getLeft())}));
        jSONObject.putOpt(HtmlTags.SIZE, String.format("{h:%d, w:%d}", new Object[]{Integer.valueOf(view.getHeight()), Integer.valueOf(view.getWidth())}));
        if (this.l == null || !this.l.contains(view)) {
            z = false;
        }
        jSONObject.putOpt("clickable", Boolean.valueOf(z));
        String str = "unknown";
        if (view instanceof Button) {
            str = "button";
        } else if (view instanceof TextView) {
            str = MimeTypes.BASE_TYPE_TEXT;
        } else if (view instanceof ImageView) {
            str = "image";
        } else if (view instanceof MediaView) {
            str = "mediaview";
        } else if (view instanceof ViewGroup) {
            str = "viewgroup";
        }
        jSONObject.putOpt(DublinCoreProperties.TYPE, str);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                jSONArray.put(c(viewGroup.getChildAt(i2)));
            }
            jSONObject.putOpt("list", jSONArray);
        }
        return jSONObject;
    }

    private String d(View view) {
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return "";
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            createBitmap.setDensity(view.getResources().getDisplayMetrics().densityDpi);
            view.draw(new Canvas(createBitmap));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(Bitmap.CompressFormat.JPEG, this.c.h(), byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception unused) {
            return "";
        }
    }

    public void a(View view) {
        this.j = view;
    }

    public void a(a aVar) {
        this.m = aVar;
    }

    public void a(g gVar) {
        this.k = gVar;
    }

    public void a(l lVar) {
        this.d = lVar;
    }

    public void a(List<View> list) {
        this.l = list;
    }

    /* access modifiers changed from: protected */
    public void a(Map<String, String> map) {
        if (this.c != null) {
            if (this.d != null) {
                map.put("nti", String.valueOf(this.d.c()));
            }
            if (this.e) {
                map.put("nhs", Boolean.TRUE.toString());
            }
            if (this.f) {
                map.put("nmv", Boolean.TRUE.toString());
            }
            if (this.g) {
                map.put("nmvap", Boolean.TRUE.toString());
            }
            if (this.j != null && this.c.e()) {
                map.put(Promotion.ACTION_VIEW, b(this.j));
            }
            if (this.j != null && this.c.d()) {
                map.put("snapshot", d(this.j));
            }
            if (this.h) {
                map.put("niv", Boolean.TRUE.toString());
            }
            if (this.m != null) {
                map.put("precache_media", this.m.toString());
            }
            if (this.i) {
                map.put("ucvr", Boolean.TRUE.toString());
            }
            if (this.k != null) {
                map.put("namw", String.valueOf((int) (((float) this.k.getWidth()) / com.facebook.ads.internal.q.a.x.b)));
                map.put("namh", String.valueOf((int) (((float) this.k.getHeight()) / com.facebook.ads.internal.q.a.x.b)));
            }
            this.c.a(map);
        }
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public void c(boolean z) {
        this.g = z;
    }

    public void d(boolean z) {
        this.h = z;
    }

    public void e(boolean z) {
        this.i = z;
    }
}
