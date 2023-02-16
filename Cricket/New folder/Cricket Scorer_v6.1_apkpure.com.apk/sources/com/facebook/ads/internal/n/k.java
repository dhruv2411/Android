package com.facebook.ads.internal.n;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import com.facebook.ads.internal.settings.AdInternalSettings;
import org.json.JSONObject;

public class k {
    private Typeface a = Typeface.DEFAULT;
    private int b = -1;
    private int c = ViewCompat.MEASURED_STATE_MASK;
    private int d = -11643291;
    private int e = 0;
    private int f = -12420889;
    private int g = -12420889;
    private boolean h = AdInternalSettings.isVideoAutoplay();
    private boolean i = AdInternalSettings.isVideoAutoplayOnMobile();

    public k() {
    }

    public k(JSONObject jSONObject) {
        int parseColor = jSONObject.getBoolean("background_transparent") ? 0 : Color.parseColor(jSONObject.getString("background_color"));
        int parseColor2 = Color.parseColor(jSONObject.getString("title_text_color"));
        int parseColor3 = Color.parseColor(jSONObject.getString("description_text_color"));
        int parseColor4 = jSONObject.getBoolean("button_transparent") ? 0 : Color.parseColor(jSONObject.getString("button_color"));
        int parseColor5 = jSONObject.getBoolean("button_border_transparent") ? 0 : Color.parseColor(jSONObject.getString("button_border_color"));
        int parseColor6 = Color.parseColor(jSONObject.getString("button_text_color"));
        Typeface create = Typeface.create(jSONObject.getString("android_typeface"), 0);
        this.b = parseColor;
        this.c = parseColor2;
        this.d = parseColor3;
        this.e = parseColor4;
        this.g = parseColor5;
        this.f = parseColor6;
        this.a = create;
    }

    public Typeface a() {
        return this.a;
    }

    public void a(int i2) {
        this.b = i2;
    }

    public void a(Typeface typeface) {
        this.a = typeface;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public int b() {
        return this.b;
    }

    public void b(int i2) {
        this.c = i2;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public int c() {
        return this.c;
    }

    public void c(int i2) {
        this.d = i2;
    }

    public int d() {
        return this.d;
    }

    public void d(int i2) {
        this.e = i2;
    }

    public int e() {
        return this.e;
    }

    public void e(int i2) {
        this.f = i2;
    }

    public int f() {
        return this.f;
    }

    public void f(int i2) {
        this.g = i2;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return 16;
    }

    public int i() {
        return 10;
    }

    public boolean j() {
        return this.h;
    }

    public boolean k() {
        return this.i;
    }
}
