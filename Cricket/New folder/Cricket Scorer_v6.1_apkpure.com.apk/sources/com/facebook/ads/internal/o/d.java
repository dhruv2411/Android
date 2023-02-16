package com.facebook.ads.internal.o;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.l.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.Locale;

public class d {
    public static String a() {
        String urlPrefix = AdInternalSettings.getUrlPrefix();
        if (TextUtils.isEmpty(urlPrefix)) {
            return "https://graph.facebook.com/network_ads_common";
        }
        return String.format("https://graph.%s.facebook.com/network_ads_common", new Object[]{urlPrefix});
    }

    public static String a(Context context) {
        String urlPrefix = AdInternalSettings.getUrlPrefix();
        String format = TextUtils.isEmpty(urlPrefix) ? "https://www.facebook.com/adnw_logging/" : String.format(Locale.US, "https://www.%s.facebook.com/adnw_logging/", new Object[]{urlPrefix});
        String u = a.u(context);
        return TextUtils.isEmpty(u) ? format : format.replace("www", u);
    }
}
