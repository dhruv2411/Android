package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.q.a.a;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class c {
    private static final String a = "c";

    @Nullable
    public static b a(Context context, com.facebook.ads.internal.m.c cVar, String str, Uri uri, Map<String, String> map) {
        if (uri == null) {
            return null;
        }
        String authority = uri.getAuthority();
        String queryParameter = uri.getQueryParameter("video_url");
        if (!TextUtils.isEmpty(uri.getQueryParameter("data"))) {
            try {
                JSONObject jSONObject = new JSONObject(uri.getQueryParameter("data"));
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    map.put(next, jSONObject.getString(next));
                }
            } catch (JSONException e) {
                Log.w(a, "Unable to parse json data in AdActionFactory.", e);
            }
        }
        l a2 = l.a(cVar, a.a());
        char c = 65535;
        int hashCode = authority.hashCode();
        if (hashCode != -1458789996) {
            if (hashCode != 109770977) {
                if (hashCode == 1546100943 && authority.equals("open_link")) {
                    c = 1;
                }
            } else if (authority.equals("store")) {
                c = 0;
            }
        } else if (authority.equals("passthrough")) {
            c = 2;
        }
        switch (c) {
            case 0:
                if (queryParameter != null) {
                    return null;
                }
                return new f(context, cVar, str, uri, map, a2);
            case 1:
                return new i(context, cVar, str, uri, map, a2);
            case 2:
                return new j(context, cVar, str, uri, map);
            default:
                return new k(context, cVar, str, uri);
        }
    }

    public static boolean a(String str) {
        return "store".equalsIgnoreCase(str) || "open_link".equalsIgnoreCase(str);
    }
}
