package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzah;
import com.google.android.gms.internal.zzai;
import java.io.UnsupportedEncodingException;
import java.util.Set;

class zzbg extends zzam {
    private static final String ID = zzah.JOINER.toString();
    private static final String zzbGf = zzai.ARG0.toString();
    private static final String zzbGx = zzai.ITEM_SEPARATOR.toString();
    private static final String zzbGy = zzai.KEY_VALUE_SEPARATOR.toString();
    private static final String zzbGz = zzai.ESCAPE.toString();

    private enum zza {
        NONE,
        URL,
        BACKSLASH
    }

    public zzbg() {
        super(ID, zzbGf);
    }

    private String zza(String str, zza zza2, Set<Character> set) {
        switch (zza2) {
            case URL:
                try {
                    return zzdp.zzhD(str);
                } catch (UnsupportedEncodingException e) {
                    zzbo.zzb("Joiner: unsupported encoding", e);
                    return str;
                }
            case BACKSLASH:
                String replace = str.replace("\\", "\\\\");
                for (Character ch : set) {
                    String ch2 = ch.toString();
                    String valueOf = String.valueOf(ch2);
                    replace = replace.replace(ch2, valueOf.length() != 0 ? "\\".concat(valueOf) : new String("\\"));
                }
                return replace;
            default:
                return str;
        }
    }

    private void zza(StringBuilder sb, String str, zza zza2, Set<Character> set) {
        sb.append(zza(str, zza2, set));
    }

    private void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    public boolean zzQb() {
        return true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzak.zza zzZ(java.util.Map<java.lang.String, com.google.android.gms.internal.zzak.zza> r10) {
        /*
            r9 = this;
            java.lang.String r0 = zzbGf
            java.lang.Object r0 = r10.get(r0)
            com.google.android.gms.internal.zzak$zza r0 = (com.google.android.gms.internal.zzak.zza) r0
            if (r0 != 0) goto L_0x000f
            com.google.android.gms.internal.zzak$zza r10 = com.google.android.gms.tagmanager.zzdl.zzRR()
            return r10
        L_0x000f:
            java.lang.String r1 = zzbGx
            java.lang.Object r1 = r10.get(r1)
            com.google.android.gms.internal.zzak$zza r1 = (com.google.android.gms.internal.zzak.zza) r1
            if (r1 == 0) goto L_0x001e
            java.lang.String r1 = com.google.android.gms.tagmanager.zzdl.zze(r1)
            goto L_0x0020
        L_0x001e:
            java.lang.String r1 = ""
        L_0x0020:
            java.lang.String r2 = zzbGy
            java.lang.Object r2 = r10.get(r2)
            com.google.android.gms.internal.zzak$zza r2 = (com.google.android.gms.internal.zzak.zza) r2
            if (r2 == 0) goto L_0x002f
            java.lang.String r2 = com.google.android.gms.tagmanager.zzdl.zze(r2)
            goto L_0x0031
        L_0x002f:
            java.lang.String r2 = "="
        L_0x0031:
            com.google.android.gms.tagmanager.zzbg$zza r3 = com.google.android.gms.tagmanager.zzbg.zza.NONE
            java.lang.String r4 = zzbGz
            java.lang.Object r10 = r10.get(r4)
            com.google.android.gms.internal.zzak$zza r10 = (com.google.android.gms.internal.zzak.zza) r10
            r4 = 0
            if (r10 == 0) goto L_0x008a
            java.lang.String r10 = com.google.android.gms.tagmanager.zzdl.zze(r10)
            java.lang.String r3 = "url"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x004d
            com.google.android.gms.tagmanager.zzbg$zza r3 = com.google.android.gms.tagmanager.zzbg.zza.URL
            goto L_0x008a
        L_0x004d:
            java.lang.String r3 = "backslash"
            boolean r3 = r3.equals(r10)
            if (r3 == 0) goto L_0x006c
            com.google.android.gms.tagmanager.zzbg$zza r3 = com.google.android.gms.tagmanager.zzbg.zza.BACKSLASH
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            r9.zza(r4, r1)
            r9.zza(r4, r2)
            r10 = 92
            java.lang.Character r10 = java.lang.Character.valueOf(r10)
            r4.remove(r10)
            goto L_0x008a
        L_0x006c:
            java.lang.String r0 = "Joiner: unsupported escape type: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r1 = r10.length()
            if (r1 == 0) goto L_0x007d
            java.lang.String r10 = r0.concat(r10)
            goto L_0x0082
        L_0x007d:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r0)
        L_0x0082:
            com.google.android.gms.tagmanager.zzbo.e(r10)
            com.google.android.gms.internal.zzak$zza r10 = com.google.android.gms.tagmanager.zzdl.zzRR()
            return r10
        L_0x008a:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            int r5 = r0.type
            r6 = 0
            switch(r5) {
                case 2: goto L_0x00c3;
                case 3: goto L_0x009d;
                default: goto L_0x0095;
            }
        L_0x0095:
            java.lang.String r0 = com.google.android.gms.tagmanager.zzdl.zze(r0)
            r9.zza(r10, r0, r3, r4)
            goto L_0x00dd
        L_0x009d:
            com.google.android.gms.internal.zzak$zza[] r5 = r0.zzlv
            int r5 = r5.length
            if (r6 >= r5) goto L_0x00dd
            if (r6 <= 0) goto L_0x00a7
            r10.append(r1)
        L_0x00a7:
            com.google.android.gms.internal.zzak$zza[] r5 = r0.zzlv
            r5 = r5[r6]
            java.lang.String r5 = com.google.android.gms.tagmanager.zzdl.zze(r5)
            com.google.android.gms.internal.zzak$zza[] r7 = r0.zzlw
            r7 = r7[r6]
            java.lang.String r7 = com.google.android.gms.tagmanager.zzdl.zze(r7)
            r9.zza(r10, r5, r3, r4)
            r10.append(r2)
            r9.zza(r10, r7, r3, r4)
            int r6 = r6 + 1
            goto L_0x009d
        L_0x00c3:
            com.google.android.gms.internal.zzak$zza[] r0 = r0.zzlu
            r2 = 1
            int r5 = r0.length
            r7 = r2
            r2 = r6
        L_0x00c9:
            if (r2 >= r5) goto L_0x00dd
            r8 = r0[r2]
            if (r7 != 0) goto L_0x00d2
            r10.append(r1)
        L_0x00d2:
            java.lang.String r7 = com.google.android.gms.tagmanager.zzdl.zze(r8)
            r9.zza(r10, r7, r3, r4)
            int r2 = r2 + 1
            r7 = r6
            goto L_0x00c9
        L_0x00dd:
            java.lang.String r10 = r10.toString()
            com.google.android.gms.internal.zzak$zza r10 = com.google.android.gms.tagmanager.zzdl.zzR(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzbg.zzZ(java.util.Map):com.google.android.gms.internal.zzak$zza");
    }
}
