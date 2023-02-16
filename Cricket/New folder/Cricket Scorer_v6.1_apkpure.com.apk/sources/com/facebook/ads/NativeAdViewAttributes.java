package com.facebook.ads;

import android.graphics.Typeface;
import com.facebook.ads.internal.j.a;
import com.facebook.ads.internal.j.b;
import com.facebook.ads.internal.n.k;
import org.json.JSONObject;

public class NativeAdViewAttributes {
    private k a;

    public NativeAdViewAttributes() {
        this.a = new k();
    }

    NativeAdViewAttributes(k kVar) {
        this.a = kVar;
    }

    public NativeAdViewAttributes(JSONObject jSONObject) {
        try {
            this.a = new k(jSONObject);
        } catch (Exception e) {
            this.a = new k();
            b.a(a.a(e, "Error retrieving native ui configuration data"));
        }
    }

    /* access modifiers changed from: package-private */
    public k a() {
        return this.a;
    }

    public boolean getAutoplay() {
        return this.a.j();
    }

    public boolean getAutoplayOnMobile() {
        return this.a.k();
    }

    public int getBackgroundColor() {
        return this.a.b();
    }

    public int getButtonBorderColor() {
        return this.a.g();
    }

    public int getButtonColor() {
        return this.a.e();
    }

    public int getButtonTextColor() {
        return this.a.f();
    }

    public int getDescriptionTextColor() {
        return this.a.d();
    }

    public int getDescriptionTextSize() {
        return this.a.i();
    }

    public int getTitleTextColor() {
        return this.a.c();
    }

    public int getTitleTextSize() {
        return this.a.h();
    }

    public Typeface getTypeface() {
        return this.a.a();
    }

    public NativeAdViewAttributes setAutoplay(boolean z) {
        this.a.b(z);
        return this;
    }

    public NativeAdViewAttributes setAutoplayOnMobile(boolean z) {
        this.a.a(z);
        return this;
    }

    public NativeAdViewAttributes setBackgroundColor(int i) {
        this.a.a(i);
        return this;
    }

    public NativeAdViewAttributes setButtonBorderColor(int i) {
        this.a.f(i);
        return this;
    }

    public NativeAdViewAttributes setButtonColor(int i) {
        this.a.d(i);
        return this;
    }

    public NativeAdViewAttributes setButtonTextColor(int i) {
        this.a.e(i);
        return this;
    }

    public NativeAdViewAttributes setDescriptionTextColor(int i) {
        this.a.c(i);
        return this;
    }

    public NativeAdViewAttributes setTitleTextColor(int i) {
        this.a.b(i);
        return this;
    }

    public NativeAdViewAttributes setTypeface(Typeface typeface) {
        this.a.a(typeface);
        return this;
    }
}
