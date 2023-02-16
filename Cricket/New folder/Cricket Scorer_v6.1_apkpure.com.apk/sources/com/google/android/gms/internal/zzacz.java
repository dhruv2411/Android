package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzr;
import com.google.android.gms.internal.zzacs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class zzacz extends zzact {
    public static final Parcelable.Creator<zzacz> CREATOR = new zzada();
    private final String mClassName;
    private final zzacw zzaHf;
    private final Parcel zzaHm;
    private final int zzaHn = 2;
    private int zzaHo;
    private int zzaHp;
    private final int zzaiI;

    zzacz(int i, Parcel parcel, zzacw zzacw) {
        this.zzaiI = i;
        this.zzaHm = (Parcel) zzac.zzw(parcel);
        this.zzaHf = zzacw;
        this.mClassName = this.zzaHf == null ? null : this.zzaHf.zzyF();
        this.zzaHo = 2;
    }

    private static SparseArray<Map.Entry<String, zzacs.zza<?, ?>>> zzY(Map<String, zzacs.zza<?, ?>> map) {
        SparseArray<Map.Entry<String, zzacs.zza<?, ?>>> sparseArray = new SparseArray<>();
        for (Map.Entry next : map.entrySet()) {
            sparseArray.put(((zzacs.zza) next.getValue()).zzyx(), next);
        }
        return sparseArray;
    }

    private void zza(StringBuilder sb, int i, Object obj) {
        String str;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"");
                str = zzq.zzdy(obj.toString());
                break;
            case 8:
                sb.append("\"");
                str = zzc.zzq((byte[]) obj);
                break;
            case 9:
                sb.append("\"");
                str = zzc.zzr((byte[]) obj);
                break;
            case 10:
                zzr.zza(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                StringBuilder sb2 = new StringBuilder(26);
                sb2.append("Unknown type = ");
                sb2.append(i);
                throw new IllegalArgumentException(sb2.toString());
        }
        sb.append(str);
        sb.append("\"");
    }

    private void zza(StringBuilder sb, zzacs.zza<?, ?> zza, Parcel parcel, int i) {
        Object obj;
        switch (zza.zzyu()) {
            case 0:
                obj = Integer.valueOf(zzb.zzg(parcel, i));
                break;
            case 1:
                obj = zzb.zzk(parcel, i);
                break;
            case 2:
                obj = Long.valueOf(zzb.zzi(parcel, i));
                break;
            case 3:
                obj = Float.valueOf(zzb.zzl(parcel, i));
                break;
            case 4:
                obj = Double.valueOf(zzb.zzn(parcel, i));
                break;
            case 5:
                obj = zzb.zzp(parcel, i);
                break;
            case 6:
                obj = Boolean.valueOf(zzb.zzc(parcel, i));
                break;
            case 7:
                obj = zzb.zzq(parcel, i);
                break;
            case 8:
            case 9:
                obj = zzb.zzt(parcel, i);
                break;
            case 10:
                obj = zzr(zzb.zzs(parcel, i));
                break;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                int zzyu = zza.zzyu();
                StringBuilder sb2 = new StringBuilder(36);
                sb2.append("Unknown field out type = ");
                sb2.append(zzyu);
                throw new IllegalArgumentException(sb2.toString());
        }
        zzb(sb, zza, (Object) zza(zza, obj));
    }

    private void zza(StringBuilder sb, String str, zzacs.zza<?, ?> zza, Parcel parcel, int i) {
        sb.append("\"");
        sb.append(str);
        sb.append("\":");
        if (zza.zzyA()) {
            zza(sb, zza, parcel, i);
        } else {
            zzb(sb, zza, parcel, i);
        }
    }

    private void zza(StringBuilder sb, Map<String, zzacs.zza<?, ?>> map, Parcel parcel) {
        SparseArray<Map.Entry<String, zzacs.zza<?, ?>>> zzY = zzY(map);
        sb.append('{');
        int zzaY = zzb.zzaY(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            Map.Entry entry = zzY.get(zzb.zzdc(zzaX));
            if (entry != null) {
                if (z) {
                    sb.append(",");
                }
                zza(sb, (String) entry.getKey(), (zzacs.zza) entry.getValue(), parcel, zzaX);
                z = true;
            }
        }
        if (parcel.dataPosition() != zzaY) {
            StringBuilder sb2 = new StringBuilder(37);
            sb2.append("Overread allowed size end=");
            sb2.append(zzaY);
            throw new zzb.zza(sb2.toString(), parcel);
        }
        sb.append('}');
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0074, code lost:
        com.google.android.gms.common.util.zzb.zza(r5, (T[]) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007f, code lost:
        r6 = "]";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x011e, code lost:
        r5.append(r6);
        r6 = "\"";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0150, code lost:
        r5.append(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0153, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzb(java.lang.StringBuilder r5, com.google.android.gms.internal.zzacs.zza<?, ?> r6, android.os.Parcel r7, int r8) {
        /*
            r4 = this;
            boolean r0 = r6.zzyv()
            r1 = 0
            if (r0 == 0) goto L_0x0083
            java.lang.String r0 = "["
            r5.append(r0)
            int r0 = r6.zzyu()
            switch(r0) {
                case 0: goto L_0x0078;
                case 1: goto L_0x0070;
                case 2: goto L_0x0068;
                case 3: goto L_0x0060;
                case 4: goto L_0x0058;
                case 5: goto L_0x0053;
                case 6: goto L_0x004b;
                case 7: goto L_0x0043;
                case 8: goto L_0x003b;
                case 9: goto L_0x003b;
                case 10: goto L_0x003b;
                case 11: goto L_0x001b;
                default: goto L_0x0013;
            }
        L_0x0013:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Unknown field type out."
            r5.<init>(r6)
            throw r5
        L_0x001b:
            android.os.Parcel[] r7 = com.google.android.gms.common.internal.safeparcel.zzb.zzG(r7, r8)
            int r8 = r7.length
            r0 = r1
        L_0x0021:
            if (r0 >= r8) goto L_0x007f
            if (r0 <= 0) goto L_0x002a
            java.lang.String r2 = ","
            r5.append(r2)
        L_0x002a:
            r2 = r7[r0]
            r2.setDataPosition(r1)
            java.util.Map r2 = r6.zzyC()
            r3 = r7[r0]
            r4.zza((java.lang.StringBuilder) r5, (java.util.Map<java.lang.String, com.google.android.gms.internal.zzacs.zza<?, ?>>) r2, (android.os.Parcel) r3)
            int r0 = r0 + 1
            goto L_0x0021
        L_0x003b:
            java.lang.UnsupportedOperationException r5 = new java.lang.UnsupportedOperationException
            java.lang.String r6 = "List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported"
            r5.<init>(r6)
            throw r5
        L_0x0043:
            java.lang.String[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(r7, r8)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r5, (java.lang.String[]) r6)
            goto L_0x007f
        L_0x004b:
            boolean[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzv(r7, r8)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r5, (boolean[]) r6)
            goto L_0x007f
        L_0x0053:
            java.math.BigDecimal[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzB(r7, r8)
            goto L_0x0074
        L_0x0058:
            double[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzA(r7, r8)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r5, (double[]) r6)
            goto L_0x007f
        L_0x0060:
            float[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzz(r7, r8)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r5, (float[]) r6)
            goto L_0x007f
        L_0x0068:
            long[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzx(r7, r8)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r5, (long[]) r6)
            goto L_0x007f
        L_0x0070:
            java.math.BigInteger[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzy(r7, r8)
        L_0x0074:
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r5, (T[]) r6)
            goto L_0x007f
        L_0x0078:
            int[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzw(r7, r8)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r5, (int[]) r6)
        L_0x007f:
            java.lang.String r6 = "]"
            goto L_0x0123
        L_0x0083:
            int r0 = r6.zzyu()
            switch(r0) {
                case 0: goto L_0x0154;
                case 1: goto L_0x014c;
                case 2: goto L_0x0144;
                case 3: goto L_0x013c;
                case 4: goto L_0x0134;
                case 5: goto L_0x012f;
                case 6: goto L_0x0127;
                case 7: goto L_0x0111;
                case 8: goto L_0x0103;
                case 9: goto L_0x00f5;
                case 10: goto L_0x00a1;
                case 11: goto L_0x0092;
                default: goto L_0x008a;
            }
        L_0x008a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Unknown field type out"
            r5.<init>(r6)
            throw r5
        L_0x0092:
            android.os.Parcel r7 = com.google.android.gms.common.internal.safeparcel.zzb.zzF(r7, r8)
            r7.setDataPosition(r1)
            java.util.Map r6 = r6.zzyC()
            r4.zza((java.lang.StringBuilder) r5, (java.util.Map<java.lang.String, com.google.android.gms.internal.zzacs.zza<?, ?>>) r6, (android.os.Parcel) r7)
            return
        L_0x00a1:
            android.os.Bundle r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzs(r7, r8)
            java.util.Set r7 = r6.keySet()
            r7.size()
            java.lang.String r8 = "{"
            r5.append(r8)
            java.util.Iterator r7 = r7.iterator()
            r8 = 1
        L_0x00b6:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x00f2
            java.lang.Object r0 = r7.next()
            java.lang.String r0 = (java.lang.String) r0
            if (r8 != 0) goto L_0x00c9
            java.lang.String r8 = ","
            r5.append(r8)
        L_0x00c9:
            java.lang.String r8 = "\""
            r5.append(r8)
            r5.append(r0)
            java.lang.String r8 = "\""
            r5.append(r8)
            java.lang.String r8 = ":"
            r5.append(r8)
            java.lang.String r8 = "\""
            r5.append(r8)
            java.lang.String r8 = r6.getString(r0)
            java.lang.String r8 = com.google.android.gms.common.util.zzq.zzdy(r8)
            r5.append(r8)
            java.lang.String r8 = "\""
            r5.append(r8)
            r8 = r1
            goto L_0x00b6
        L_0x00f2:
            java.lang.String r6 = "}"
            goto L_0x0123
        L_0x00f5:
            byte[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(r7, r8)
            java.lang.String r7 = "\""
            r5.append(r7)
            java.lang.String r6 = com.google.android.gms.common.util.zzc.zzr(r6)
            goto L_0x011e
        L_0x0103:
            byte[] r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzt(r7, r8)
            java.lang.String r7 = "\""
            r5.append(r7)
            java.lang.String r6 = com.google.android.gms.common.util.zzc.zzq(r6)
            goto L_0x011e
        L_0x0111:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(r7, r8)
            java.lang.String r7 = "\""
            r5.append(r7)
            java.lang.String r6 = com.google.android.gms.common.util.zzq.zzdy(r6)
        L_0x011e:
            r5.append(r6)
            java.lang.String r6 = "\""
        L_0x0123:
            r5.append(r6)
            return
        L_0x0127:
            boolean r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzc(r7, r8)
            r5.append(r6)
            return
        L_0x012f:
            java.math.BigDecimal r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzp(r7, r8)
            goto L_0x0150
        L_0x0134:
            double r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzn(r7, r8)
            r5.append(r6)
            return
        L_0x013c:
            float r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzl(r7, r8)
            r5.append(r6)
            return
        L_0x0144:
            long r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzi(r7, r8)
            r5.append(r6)
            return
        L_0x014c:
            java.math.BigInteger r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzk(r7, r8)
        L_0x0150:
            r5.append(r6)
            return
        L_0x0154:
            int r6 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(r7, r8)
            r5.append(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzacz.zzb(java.lang.StringBuilder, com.google.android.gms.internal.zzacs$zza, android.os.Parcel, int):void");
    }

    private void zzb(StringBuilder sb, zzacs.zza<?, ?> zza, Object obj) {
        if (zza.zzyt()) {
            zzb(sb, zza, (ArrayList<?>) (ArrayList) obj);
        } else {
            zza(sb, zza.zzys(), obj);
        }
    }

    private void zzb(StringBuilder sb, zzacs.zza<?, ?> zza, ArrayList<?> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            zza(sb, zza.zzys(), (Object) arrayList.get(i));
        }
        sb.append("]");
    }

    public static HashMap<String, String> zzr(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public int getVersionCode() {
        return this.zzaiI;
    }

    public String toString() {
        zzac.zzb(this.zzaHf, (Object) "Cannot convert to JSON on client side.");
        Parcel zzyH = zzyH();
        zzyH.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        zza(sb, this.zzaHf.zzdw(this.mClassName), zzyH);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzada.zza(this, parcel, i);
    }

    public Object zzds(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public boolean zzdt(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public Parcel zzyH() {
        switch (this.zzaHo) {
            case 0:
                this.zzaHp = com.google.android.gms.common.internal.safeparcel.zzc.zzaZ(this.zzaHm);
                break;
            case 1:
                break;
        }
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(this.zzaHm, this.zzaHp);
        this.zzaHo = 2;
        return this.zzaHm;
    }

    /* access modifiers changed from: package-private */
    public zzacw zzyI() {
        switch (this.zzaHn) {
            case 0:
                return null;
            case 1:
                return this.zzaHf;
            case 2:
                return this.zzaHf;
            default:
                int i = this.zzaHn;
                StringBuilder sb = new StringBuilder(34);
                sb.append("Invalid creation type: ");
                sb.append(i);
                throw new IllegalStateException(sb.toString());
        }
    }

    public Map<String, zzacs.zza<?, ?>> zzyr() {
        if (this.zzaHf == null) {
            return null;
        }
        return this.zzaHf.zzdw(this.mClassName);
    }
}
