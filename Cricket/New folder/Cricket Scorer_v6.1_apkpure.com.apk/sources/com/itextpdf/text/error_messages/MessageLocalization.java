package com.itextpdf.text.error_messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

public final class MessageLocalization {
    private static final String BASE_PATH = "com/itextpdf/text/l10n/error/";
    private static HashMap<String, String> currentLanguage;
    private static HashMap<String, String> defaultLanguage;

    static {
        defaultLanguage = new HashMap<>();
        try {
            defaultLanguage = getLanguageMessages("en", (String) null);
        } catch (Exception unused) {
        }
        if (defaultLanguage == null) {
            defaultLanguage = new HashMap<>();
        }
    }

    private MessageLocalization() {
    }

    public static String getMessage(String str) {
        return getMessage(str, true);
    }

    public static String getMessage(String str, boolean z) {
        String str2;
        String str3;
        HashMap<String, String> hashMap = currentLanguage;
        if (hashMap != null && (str3 = hashMap.get(str)) != null) {
            return str3;
        }
        if (z && (str2 = defaultLanguage.get(str)) != null) {
            return str2;
        }
        return "No message found for " + str;
    }

    public static String getComposedMessage(String str, int i) {
        return getComposedMessage(str, String.valueOf(i), null, null, null);
    }

    public static String getComposedMessage(String str, Object... objArr) {
        String message = getMessage(str);
        if (objArr != null) {
            int i = 1;
            for (Object obj : objArr) {
                if (obj != null) {
                    message = message.replace("{" + i + "}", obj.toString());
                }
                i++;
            }
        }
        return message;
    }

    public static boolean setLanguage(String str, String str2) throws IOException {
        HashMap<String, String> languageMessages = getLanguageMessages(str, str2);
        if (languageMessages == null) {
            return false;
        }
        currentLanguage = languageMessages;
        return true;
    }

    public static void setMessages(Reader reader) throws IOException {
        currentLanguage = readLanguageStream(reader);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00bf A[SYNTHETIC, Splitter:B:45:0x00bf] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.HashMap<java.lang.String, java.lang.String> getLanguageMessages(java.lang.String r4, java.lang.String r5) throws java.io.IOException {
        /*
            if (r4 != 0) goto L_0x000a
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "The language cannot be null."
            r4.<init>(r5)
            throw r4
        L_0x000a:
            r0 = 0
            if (r5 == 0) goto L_0x002a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0027 }
            r1.<init>()     // Catch:{ all -> 0x0027 }
            r1.append(r4)     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = "_"
            r1.append(r2)     // Catch:{ all -> 0x0027 }
            r1.append(r5)     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = ".lng"
            r1.append(r2)     // Catch:{ all -> 0x0027 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0027 }
            goto L_0x003b
        L_0x0027:
            r4 = move-exception
            goto L_0x00bd
        L_0x002a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0027 }
            r1.<init>()     // Catch:{ all -> 0x0027 }
            r1.append(r4)     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = ".lng"
            r1.append(r2)     // Catch:{ all -> 0x0027 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0027 }
        L_0x003b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0027 }
            r2.<init>()     // Catch:{ all -> 0x0027 }
            java.lang.String r3 = "com/itextpdf/text/l10n/error/"
            r2.append(r3)     // Catch:{ all -> 0x0027 }
            r2.append(r1)     // Catch:{ all -> 0x0027 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0027 }
            com.itextpdf.text.error_messages.MessageLocalization r2 = new com.itextpdf.text.error_messages.MessageLocalization     // Catch:{ all -> 0x0027 }
            r2.<init>()     // Catch:{ all -> 0x0027 }
            java.lang.Class r2 = r2.getClass()     // Catch:{ all -> 0x0027 }
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch:{ all -> 0x0027 }
            java.io.InputStream r1 = com.itextpdf.text.io.StreamUtil.getResourceStream(r1, r2)     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x006c
            java.util.HashMap r4 = readLanguageStream((java.io.InputStream) r1)     // Catch:{ all -> 0x0069 }
            if (r1 == 0) goto L_0x0068
            r1.close()     // Catch:{ Exception -> 0x0068 }
        L_0x0068:
            return r4
        L_0x0069:
            r4 = move-exception
            r0 = r1
            goto L_0x00bd
        L_0x006c:
            if (r5 != 0) goto L_0x0074
            if (r1 == 0) goto L_0x0073
            r1.close()     // Catch:{ Exception -> 0x0073 }
        L_0x0073:
            return r0
        L_0x0074:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0069 }
            r5.<init>()     // Catch:{ all -> 0x0069 }
            r5.append(r4)     // Catch:{ all -> 0x0069 }
            java.lang.String r4 = ".lng"
            r5.append(r4)     // Catch:{ all -> 0x0069 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0069 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0069 }
            r5.<init>()     // Catch:{ all -> 0x0069 }
            java.lang.String r2 = "com/itextpdf/text/l10n/error/"
            r5.append(r2)     // Catch:{ all -> 0x0069 }
            r5.append(r4)     // Catch:{ all -> 0x0069 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0069 }
            com.itextpdf.text.error_messages.MessageLocalization r5 = new com.itextpdf.text.error_messages.MessageLocalization     // Catch:{ all -> 0x0069 }
            r5.<init>()     // Catch:{ all -> 0x0069 }
            java.lang.Class r5 = r5.getClass()     // Catch:{ all -> 0x0069 }
            java.lang.ClassLoader r5 = r5.getClassLoader()     // Catch:{ all -> 0x0069 }
            java.io.InputStream r4 = com.itextpdf.text.io.StreamUtil.getResourceStream(r4, r5)     // Catch:{ all -> 0x0069 }
            if (r4 == 0) goto L_0x00b7
            java.util.HashMap r5 = readLanguageStream((java.io.InputStream) r4)     // Catch:{ all -> 0x00b3 }
            if (r4 == 0) goto L_0x00b2
            r4.close()     // Catch:{ Exception -> 0x00b2 }
        L_0x00b2:
            return r5
        L_0x00b3:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L_0x00bd
        L_0x00b7:
            if (r4 == 0) goto L_0x00bc
            r4.close()     // Catch:{ Exception -> 0x00bc }
        L_0x00bc:
            return r0
        L_0x00bd:
            if (r0 == 0) goto L_0x00c2
            r0.close()     // Catch:{ Exception -> 0x00c2 }
        L_0x00c2:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.error_messages.MessageLocalization.getLanguageMessages(java.lang.String, java.lang.String):java.util.HashMap");
    }

    private static HashMap<String, String> readLanguageStream(InputStream inputStream) throws IOException {
        return readLanguageStream((Reader) new InputStreamReader(inputStream, "UTF-8"));
    }

    private static HashMap<String, String> readLanguageStream(Reader reader) throws IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return hashMap;
            }
            int indexOf = readLine.indexOf(61);
            if (indexOf >= 0) {
                String trim = readLine.substring(0, indexOf).trim();
                if (!trim.startsWith("#")) {
                    hashMap.put(trim, readLine.substring(indexOf + 1));
                }
            }
        }
    }
}
