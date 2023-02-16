package com.facebook.ads.internal.h;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.itextpdf.text.xml.xmp.DublinCoreProperties;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class a {
    private final String a;
    private final JSONObject b;
    private final Map<e, List<String>> c = new HashMap();

    public a(String str, JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        this.a = str;
        this.b = jSONObject;
        if (jSONArray != null && jSONArray.length() != 0) {
            for (e put : e.values()) {
                this.c.put(put, new LinkedList());
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString(DublinCoreProperties.TYPE);
                    String string2 = jSONObject2.getString("url");
                    e valueOf = e.valueOf(string.toUpperCase(Locale.US));
                    if (valueOf != null && !TextUtils.isEmpty(string2)) {
                        this.c.get(valueOf).add(string2);
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    public String a() {
        return this.a;
    }

    public List<String> a(e eVar) {
        return this.c.get(eVar);
    }

    public JSONObject b() {
        return this.b;
    }
}
