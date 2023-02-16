package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzaa {

    public static final class zza {
        private final Object zzYG;
        private final List<String> zzaGv;

        private zza(Object obj) {
            this.zzYG = zzac.zzw(obj);
            this.zzaGv = new ArrayList();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append(this.zzYG.getClass().getSimpleName());
            sb.append('{');
            int size = this.zzaGv.size();
            for (int i = 0; i < size; i++) {
                sb.append(this.zzaGv.get(i));
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append('}');
            return sb.toString();
        }

        public zza zzg(String str, Object obj) {
            List<String> list = this.zzaGv;
            String str2 = (String) zzac.zzw(str);
            String valueOf = String.valueOf(String.valueOf(obj));
            StringBuilder sb = new StringBuilder(1 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
            sb.append(str2);
            sb.append("=");
            sb.append(valueOf);
            list.add(sb.toString());
            return this;
        }
    }

    public static boolean equal(@Nullable Object obj, @Nullable Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static zza zzv(Object obj) {
        return new zza(obj);
    }
}
