package com.facebook.ads.internal.p.a;

import com.facebook.ads.internal.settings.AdInternalSettings;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

public class g implements r {
    private void a(Map<String, List<String>> map) {
        if (map != null) {
            for (String next : map.keySet()) {
                for (String str : map.get(next)) {
                    a(next + ":" + str);
                }
            }
        }
    }

    public void a(n nVar) {
        if (nVar != null) {
            a("=== HTTP Response ===");
            a("Receive url: " + nVar.b());
            a("Status: " + nVar.a());
            a(nVar.c());
            a("Content:\n" + nVar.e());
        }
    }

    public void a(String str) {
        System.out.println(str);
    }

    public void a(HttpURLConnection httpURLConnection, Object obj) {
        a("=== HTTP Request ===");
        a(httpURLConnection.getRequestMethod() + " " + httpURLConnection.getURL().toString());
        if (obj instanceof String) {
            a("Content: " + ((String) obj));
        }
        a((Map<String, List<String>>) httpURLConnection.getRequestProperties());
    }

    public boolean a() {
        return AdInternalSettings.isDebugBuild();
    }
}
