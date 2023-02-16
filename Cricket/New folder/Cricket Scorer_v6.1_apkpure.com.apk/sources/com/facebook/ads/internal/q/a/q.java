package com.facebook.ads.internal.q.a;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.StringTokenizer;
import org.json.JSONArray;

public class q {
    public static final String a(String str) {
        StringBuilder sb;
        String substring;
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, " ", true);
            if (str.length() > 90 && (str.length() > 93 || !str.endsWith("..."))) {
                int i = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    int length = stringTokenizer.nextToken().length() + i;
                    if (length < 90) {
                        i = length;
                    }
                }
                if (i == 0) {
                    sb = new StringBuilder();
                    substring = str.substring(0, 90);
                } else {
                    sb = new StringBuilder();
                    substring = str.substring(0, i);
                }
                sb.append(substring);
                sb.append("...");
                return sb.toString();
            }
        }
        return str;
    }

    public static final String a(Throwable th) {
        if (th == null) {
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public static final String a(StackTraceElement[] stackTraceElementArr) {
        JSONArray jSONArray = new JSONArray();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            jSONArray.put(stackTraceElement.toString());
        }
        return jSONArray.toString();
    }
}
