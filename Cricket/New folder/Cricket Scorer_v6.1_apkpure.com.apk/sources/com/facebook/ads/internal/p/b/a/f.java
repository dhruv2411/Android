package com.facebook.ads.internal.p.b.a;

import android.text.TextUtils;
import com.facebook.ads.internal.p.b.m;

public class f implements c {
    private String b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || lastIndexOf <= str.lastIndexOf(47) || (lastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(lastIndexOf + 1, str.length());
    }

    public String a(String str) {
        String b = b(str);
        String d = m.d(str);
        if (TextUtils.isEmpty(b)) {
            return d;
        }
        return d + "." + b;
    }
}
