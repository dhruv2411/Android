package com.facebook.ads.internal.q.a;

import java.util.Set;

public class v {
    public static String a(Set<String> set, String str) {
        StringBuilder sb = new StringBuilder();
        for (String append : set) {
            sb.append(append);
            sb.append(str);
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
    }
}
