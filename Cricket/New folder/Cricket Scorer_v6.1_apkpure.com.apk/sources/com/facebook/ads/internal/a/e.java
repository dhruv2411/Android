package com.facebook.ads.internal.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.facebook.ads.internal.m.c;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;

public class e {

    public interface a {
        d a();

        Collection<String> b();

        String c();
    }

    public static Collection<String> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.optString(i));
        }
        return hashSet;
    }

    public static boolean a(Context context, a aVar, c cVar) {
        Collection<String> b;
        boolean z;
        d a2 = aVar.a();
        if (a2 == null || a2 == d.NONE || (b = aVar.b()) == null || b.isEmpty()) {
            return false;
        }
        Iterator<String> it = b.iterator();
        while (true) {
            if (it.hasNext()) {
                if (a(context, it.next())) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z == (a2 == d.INSTALLED)) {
            String c = aVar.c();
            if (!TextUtils.isEmpty(c)) {
                cVar.b(c, (Map<String, String>) null);
            }
            return true;
        }
        return false;
    }

    public static boolean a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            return false;
        }
    }
}
