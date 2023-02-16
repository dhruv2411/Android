package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzs;
import java.io.UnsupportedEncodingException;
import java.lang.Character;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@zzme
public class zzdi {
    public static int zzH(String str) {
        byte[] bArr;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            bArr = str.getBytes();
        }
        return zzs.zza(bArr, 0, bArr.length, 0);
    }

    @Nullable
    public static String[] zzI(@Nullable String str) {
        return zzd(str, false);
    }

    private static boolean zza(Character.UnicodeBlock unicodeBlock) {
        return unicodeBlock == Character.UnicodeBlock.BOPOMOFO || unicodeBlock == Character.UnicodeBlock.BOPOMOFO_EXTENDED || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || unicodeBlock == Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS || unicodeBlock == Character.UnicodeBlock.HANGUL_JAMO || unicodeBlock == Character.UnicodeBlock.HANGUL_SYLLABLES || unicodeBlock == Character.UnicodeBlock.HIRAGANA || unicodeBlock == Character.UnicodeBlock.KATAKANA || unicodeBlock == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0064, code lost:
        if (r4 == false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0071, code lost:
        if (r4 == false) goto L_0x0073;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[] zzd(@android.support.annotation.Nullable java.lang.String r11, boolean r12) {
        /*
            if (r11 != 0) goto L_0x0004
            r11 = 0
            return r11
        L_0x0004:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            char[] r1 = r11.toCharArray()
            int r11 = r11.length()
            r2 = 0
            r3 = r2
            r4 = r3
            r5 = r4
        L_0x0015:
            if (r3 >= r11) goto L_0x0077
            int r6 = java.lang.Character.codePointAt(r1, r3)
            int r7 = java.lang.Character.charCount(r6)
            boolean r8 = zzo(r6)
            r9 = 1
            if (r8 == 0) goto L_0x003c
            if (r4 == 0) goto L_0x0032
            java.lang.String r4 = new java.lang.String
            int r6 = r3 - r5
            r4.<init>(r1, r5, r6)
            r0.add(r4)
        L_0x0032:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r1, r3, r7)
        L_0x0037:
            r0.add(r4)
            r4 = r2
            goto L_0x0075
        L_0x003c:
            boolean r8 = java.lang.Character.isLetterOrDigit(r6)
            if (r8 != 0) goto L_0x0071
            int r8 = java.lang.Character.getType(r6)
            r10 = 6
            if (r8 == r10) goto L_0x0071
            int r8 = java.lang.Character.getType(r6)
            r10 = 8
            if (r8 != r10) goto L_0x0052
            goto L_0x0071
        L_0x0052:
            if (r12 == 0) goto L_0x0067
            int r8 = java.lang.Character.charCount(r6)
            if (r8 != r9) goto L_0x0067
            char[] r6 = java.lang.Character.toChars(r6)
            char r6 = r6[r2]
            r8 = 39
            if (r6 != r8) goto L_0x0067
            if (r4 != 0) goto L_0x0074
            goto L_0x0073
        L_0x0067:
            if (r4 == 0) goto L_0x0075
            java.lang.String r4 = new java.lang.String
            int r6 = r3 - r5
            r4.<init>(r1, r5, r6)
            goto L_0x0037
        L_0x0071:
            if (r4 != 0) goto L_0x0074
        L_0x0073:
            r5 = r3
        L_0x0074:
            r4 = r9
        L_0x0075:
            int r3 = r3 + r7
            goto L_0x0015
        L_0x0077:
            if (r4 == 0) goto L_0x0082
            java.lang.String r11 = new java.lang.String
            int r3 = r3 - r5
            r11.<init>(r1, r5, r3)
            r0.add(r11)
        L_0x0082:
            int r11 = r0.size()
            java.lang.String[] r11 = new java.lang.String[r11]
            java.lang.Object[] r11 = r0.toArray(r11)
            java.lang.String[] r11 = (java.lang.String[]) r11
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdi.zzd(java.lang.String, boolean):java.lang.String[]");
    }

    static boolean zzo(int i) {
        if (Character.isLetter(i)) {
            return zza(Character.UnicodeBlock.of(i)) || zzq(i);
        }
        return false;
    }

    public static byte[] zzp(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(i);
        return allocate.array();
    }

    private static boolean zzq(int i) {
        if (i < 65382 || i > 65437) {
            return i >= 65441 && i <= 65500;
        }
        return true;
    }
}
