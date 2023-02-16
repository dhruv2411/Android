package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzatx;
import com.google.android.gms.internal.zzauu;
import com.google.android.gms.internal.zzauw;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.security.auth.x500.X500Principal;

public class zzaut extends zzauh {
    private final AtomicLong zzbwh = new AtomicLong(0);
    private int zzbwi;

    zzaut(zzaue zzaue) {
        super(zzaue);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0037 A[Catch:{ IOException | ClassNotFoundException -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003c A[Catch:{ IOException | ClassNotFoundException -> 0x0040 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object zzH(java.lang.Object r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ all -> 0x0032 }
            r2.<init>(r1)     // Catch:{ all -> 0x0032 }
            r2.writeObject(r4)     // Catch:{ all -> 0x002f }
            r2.flush()     // Catch:{ all -> 0x002f }
            java.io.ObjectInputStream r4 = new java.io.ObjectInputStream     // Catch:{ all -> 0x002f }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x002f }
            byte[] r1 = r1.toByteArray()     // Catch:{ all -> 0x002f }
            r3.<init>(r1)     // Catch:{ all -> 0x002f }
            r4.<init>(r3)     // Catch:{ all -> 0x002f }
            java.lang.Object r1 = r4.readObject()     // Catch:{ all -> 0x002d }
            r2.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
            r4.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
            return r1
        L_0x002d:
            r1 = move-exception
            goto L_0x0035
        L_0x002f:
            r1 = move-exception
            r4 = r0
            goto L_0x0035
        L_0x0032:
            r1 = move-exception
            r4 = r0
            r2 = r4
        L_0x0035:
            if (r2 == 0) goto L_0x003a
            r2.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
        L_0x003a:
            if (r4 == 0) goto L_0x003f
            r4.close()     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
        L_0x003f:
            throw r1     // Catch:{ IOException | ClassNotFoundException -> 0x0040 }
        L_0x0040:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaut.zzH(java.lang.Object):java.lang.Object");
    }

    private Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        } else if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        } else {
            if ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) {
                return zza(String.valueOf(obj), i, z);
            }
            return null;
        }
    }

    public static String zza(zzauu.zzb zzb) {
        if (zzb == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        zza(sb, 0, "filter_id", (Object) zzb.zzbwo);
        zza(sb, 0, "event_name", (Object) zzb.zzbwp);
        zza(sb, 1, "event_count_filter", zzb.zzbws);
        sb.append("  filters {\n");
        for (zzauu.zzc zza : zzb.zzbwq) {
            zza(sb, 2, zza);
        }
        zza(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    public static String zza(zzauu.zze zze) {
        if (zze == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        zza(sb, 0, "filter_id", (Object) zze.zzbwo);
        zza(sb, 0, "property_name", (Object) zze.zzbwE);
        zza(sb, 1, zze.zzbwF);
        sb.append("}\n");
        return sb.toString();
    }

    private static void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    private static void zza(StringBuilder sb, int i, zzauu.zzc zzc) {
        if (zzc != null) {
            zza(sb, i);
            sb.append("filter {\n");
            zza(sb, i, "complement", (Object) zzc.zzbww);
            zza(sb, i, "param_name", (Object) zzc.zzbwx);
            int i2 = i + 1;
            zza(sb, i2, "string_filter", zzc.zzbwu);
            zza(sb, i2, "number_filter", zzc.zzbwv);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, zzauw.zze zze) {
        if (zze != null) {
            zza(sb, i);
            sb.append("bundle {\n");
            zza(sb, i, "protocol_version", (Object) zze.zzbxf);
            zza(sb, i, "platform", (Object) zze.zzbxn);
            zza(sb, i, "gmp_version", (Object) zze.zzbxr);
            zza(sb, i, "uploading_gmp_version", (Object) zze.zzbxs);
            zza(sb, i, "config_version", (Object) zze.zzbxE);
            zza(sb, i, "gmp_app_id", (Object) zze.zzbqL);
            zza(sb, i, "app_id", (Object) zze.zzaS);
            zza(sb, i, "app_version", (Object) zze.zzbhN);
            zza(sb, i, "app_version_major", (Object) zze.zzbxA);
            zza(sb, i, "app_version_minor", (Object) zze.zzbxB);
            zza(sb, i, "app_version_release", (Object) zze.zzbxC);
            zza(sb, i, "firebase_instance_id", (Object) zze.zzbqT);
            zza(sb, i, "dev_cert_hash", (Object) zze.zzbxw);
            zza(sb, i, "app_store", (Object) zze.zzbqM);
            zza(sb, i, "upload_timestamp_millis", (Object) zze.zzbxi);
            zza(sb, i, "start_timestamp_millis", (Object) zze.zzbxj);
            zza(sb, i, "end_timestamp_millis", (Object) zze.zzbxk);
            zza(sb, i, "previous_bundle_start_timestamp_millis", (Object) zze.zzbxl);
            zza(sb, i, "previous_bundle_end_timestamp_millis", (Object) zze.zzbxm);
            zza(sb, i, "app_instance_id", (Object) zze.zzbxv);
            zza(sb, i, "resettable_device_id", (Object) zze.zzbxt);
            zza(sb, i, "device_id", (Object) zze.zzbxD);
            zza(sb, i, "limited_ad_tracking", (Object) zze.zzbxu);
            zza(sb, i, "os_version", (Object) zze.zzbb);
            zza(sb, i, "device_model", (Object) zze.zzbxo);
            zza(sb, i, "user_default_language", (Object) zze.zzbxp);
            zza(sb, i, "time_zone_offset_minutes", (Object) zze.zzbxq);
            zza(sb, i, "bundle_sequential_index", (Object) zze.zzbxx);
            zza(sb, i, "service_upload", (Object) zze.zzbxy);
            zza(sb, i, "health_monitor", (Object) zze.zzbqP);
            if (zze.zzbxF.longValue() != 0) {
                zza(sb, i, "android_id", (Object) zze.zzbxF);
            }
            zza(sb, i, zze.zzbxh);
            zza(sb, i, zze.zzbxz);
            zza(sb, i, zze.zzbxg);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzauu.zzd zzd) {
        if (zzd != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzd.zzbwy != null) {
                String str2 = "UNKNOWN_COMPARISON_TYPE";
                switch (zzd.zzbwy.intValue()) {
                    case 1:
                        str2 = "LESS_THAN";
                        break;
                    case 2:
                        str2 = "GREATER_THAN";
                        break;
                    case 3:
                        str2 = "EQUAL";
                        break;
                    case 4:
                        str2 = "BETWEEN";
                        break;
                }
                zza(sb, i, "comparison_type", (Object) str2);
            }
            zza(sb, i, "match_as_float", (Object) zzd.zzbwz);
            zza(sb, i, "comparison_value", (Object) zzd.zzbwA);
            zza(sb, i, "min_comparison_value", (Object) zzd.zzbwB);
            zza(sb, i, "max_comparison_value", (Object) zzd.zzbwC);
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzauu.zzf zzf) {
        if (zzf != null) {
            zza(sb, i);
            sb.append(str);
            sb.append(" {\n");
            if (zzf.zzbwG != null) {
                String str2 = "UNKNOWN_MATCH_TYPE";
                switch (zzf.zzbwG.intValue()) {
                    case 1:
                        str2 = "REGEXP";
                        break;
                    case 2:
                        str2 = "BEGINS_WITH";
                        break;
                    case 3:
                        str2 = "ENDS_WITH";
                        break;
                    case 4:
                        str2 = "PARTIAL";
                        break;
                    case 5:
                        str2 = "EXACT";
                        break;
                    case 6:
                        str2 = "IN_LIST";
                        break;
                }
                zza(sb, i, "match_type", (Object) str2);
            }
            zza(sb, i, "expression", (Object) zzf.zzbwH);
            zza(sb, i, "case_sensitive", (Object) zzf.zzbwI);
            if (zzf.zzbwJ.length > 0) {
                zza(sb, i + 1);
                sb.append("expression_list {\n");
                for (String append : zzf.zzbwJ) {
                    zza(sb, i + 2);
                    sb.append(append);
                    sb.append("\n");
                }
                sb.append("}\n");
            }
            zza(sb, i);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, zzauw.zzf zzf) {
        if (zzf != null) {
            int i2 = i + 1;
            zza(sb, i2);
            sb.append(str);
            sb.append(" {\n");
            int i3 = 0;
            if (zzf.zzbxH != null) {
                zza(sb, i2 + 1);
                sb.append("results: ");
                long[] jArr = zzf.zzbxH;
                int length = jArr.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length) {
                    Long valueOf = Long.valueOf(jArr[i4]);
                    int i6 = i5 + 1;
                    if (i5 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf);
                    i4++;
                    i5 = i6;
                }
                sb.append(10);
            }
            if (zzf.zzbxG != null) {
                zza(sb, i2 + 1);
                sb.append("status: ");
                long[] jArr2 = zzf.zzbxG;
                int length2 = jArr2.length;
                int i7 = 0;
                while (i3 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i3]);
                    int i8 = i7 + 1;
                    if (i7 != 0) {
                        sb.append(", ");
                    }
                    sb.append(valueOf2);
                    i3++;
                    i7 = i8;
                }
                sb.append(10);
            }
            zza(sb, i2);
            sb.append("}\n");
        }
    }

    private static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj != null) {
            zza(sb, i + 1);
            sb.append(str);
            sb.append(": ");
            sb.append(obj);
            sb.append(10);
        }
    }

    private static void zza(StringBuilder sb, int i, zzauw.zza[] zzaArr) {
        if (zzaArr != null) {
            int i2 = i + 1;
            for (zzauw.zza zza : zzaArr) {
                if (zza != null) {
                    zza(sb, i2);
                    sb.append("audience_membership {\n");
                    zza(sb, i2, "audience_id", (Object) zza.zzbwk);
                    zza(sb, i2, "new_audience", (Object) zza.zzbwW);
                    zza(sb, i2, "current_data", zza.zzbwU);
                    zza(sb, i2, "previous_data", zza.zzbwV);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, zzauw.zzb[] zzbArr) {
        if (zzbArr != null) {
            int i2 = i + 1;
            for (zzauw.zzb zzb : zzbArr) {
                if (zzb != null) {
                    zza(sb, i2);
                    sb.append("event {\n");
                    zza(sb, i2, "name", (Object) zzb.name);
                    zza(sb, i2, "timestamp_millis", (Object) zzb.zzbwZ);
                    zza(sb, i2, "previous_timestamp_millis", (Object) zzb.zzbxa);
                    zza(sb, i2, "count", (Object) zzb.count);
                    zza(sb, i2, zzb.zzbwY);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, zzauw.zzc[] zzcArr) {
        if (zzcArr != null) {
            int i2 = i + 1;
            for (zzauw.zzc zzc : zzcArr) {
                if (zzc != null) {
                    zza(sb, i2);
                    sb.append("param {\n");
                    zza(sb, i2, "name", (Object) zzc.name);
                    zza(sb, i2, "string_value", (Object) zzc.zzaGV);
                    zza(sb, i2, "int_value", (Object) zzc.zzbxc);
                    zza(sb, i2, "double_value", (Object) zzc.zzbwf);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder sb, int i, zzauw.zzg[] zzgArr) {
        if (zzgArr != null) {
            int i2 = i + 1;
            for (zzauw.zzg zzg : zzgArr) {
                if (zzg != null) {
                    zza(sb, i2);
                    sb.append("user_property {\n");
                    zza(sb, i2, "set_timestamp_millis", (Object) zzg.zzbxJ);
                    zza(sb, i2, "name", (Object) zzg.name);
                    zza(sb, i2, "string_value", (Object) zzg.zzaGV);
                    zza(sb, i2, "int_value", (Object) zzg.zzbxc);
                    zza(sb, i2, "double_value", (Object) zzg.zzbwf);
                    zza(sb, i2);
                    sb.append("}\n");
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0008, code lost:
        r3 = r1.getReceiverInfo(new android.content.ComponentName(r3, r4), 2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zza(android.content.Context r3, java.lang.String r4, boolean r5) {
        /*
            r0 = 0
            android.content.pm.PackageManager r1 = r3.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0020 }
            if (r1 != 0) goto L_0x0008
            return r0
        L_0x0008:
            android.content.ComponentName r2 = new android.content.ComponentName     // Catch:{ NameNotFoundException -> 0x0020 }
            r2.<init>(r3, r4)     // Catch:{ NameNotFoundException -> 0x0020 }
            r3 = 2
            android.content.pm.ActivityInfo r3 = r1.getReceiverInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x0020 }
            if (r3 == 0) goto L_0x0020
            boolean r4 = r3.enabled     // Catch:{ NameNotFoundException -> 0x0020 }
            if (r4 == 0) goto L_0x0020
            if (r5 == 0) goto L_0x001e
            boolean r3 = r3.exported     // Catch:{ NameNotFoundException -> 0x0020 }
            if (r3 == 0) goto L_0x0020
        L_0x001e:
            r3 = 1
            return r3
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaut.zza(android.content.Context, java.lang.String, boolean):boolean");
    }

    public static boolean zza(long[] jArr, int i) {
        return i < jArr.length * 64 && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    public static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        for (int i = 0; i < length; i++) {
            jArr[i] = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (64 * i) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
            }
        }
        return jArr;
    }

    public static boolean zzae(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null) {
            return false;
        }
        return str.equals(str2);
    }

    public static String zzb(zzauw.zzd zzd) {
        if (zzd == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        if (zzd.zzbxd != null) {
            for (zzauw.zze zze : zzd.zzbxd) {
                if (zze != null) {
                    zza(sb, 1, zze);
                }
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    static MessageDigest zzch(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    static boolean zzfT(String str) {
        zzac.zzdr(str);
        return str.charAt(0) != '_';
    }

    private int zzgc(String str) {
        return "_ldl".equals(str) ? zzKn().zzKS() : zzKn().zzKR();
    }

    public static boolean zzgd(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
    }

    static boolean zzgf(String str) {
        return str != null && str.matches("(\\+|-)?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    static long zzy(byte[] bArr) {
        zzac.zzw(bArr);
        int i = 0;
        zzac.zzaw(bArr.length > 0);
        long j = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            i += 8;
            length--;
            j += (((long) bArr[length]) & 255) << i;
        }
        return j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0008, code lost:
        r3 = r1.getServiceInfo(new android.content.ComponentName(r3, r4), 4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzy(android.content.Context r3, java.lang.String r4) {
        /*
            r0 = 0
            android.content.pm.PackageManager r1 = r3.getPackageManager()     // Catch:{ NameNotFoundException -> 0x001a }
            if (r1 != 0) goto L_0x0008
            return r0
        L_0x0008:
            android.content.ComponentName r2 = new android.content.ComponentName     // Catch:{ NameNotFoundException -> 0x001a }
            r2.<init>(r3, r4)     // Catch:{ NameNotFoundException -> 0x001a }
            r3 = 4
            android.content.pm.ServiceInfo r3 = r1.getServiceInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x001a }
            if (r3 == 0) goto L_0x001a
            boolean r3 = r3.enabled     // Catch:{ NameNotFoundException -> 0x001a }
            if (r3 == 0) goto L_0x001a
            r3 = 1
            return r3
        L_0x001a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaut.zzy(android.content.Context, java.lang.String):boolean");
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public boolean zzA(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return "android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra);
    }

    public /* bridge */ /* synthetic */ void zzJV() {
        super.zzJV();
    }

    public /* bridge */ /* synthetic */ void zzJW() {
        super.zzJW();
    }

    public /* bridge */ /* synthetic */ void zzJX() {
        super.zzJX();
    }

    public /* bridge */ /* synthetic */ zzatb zzJY() {
        return super.zzJY();
    }

    public /* bridge */ /* synthetic */ zzatf zzJZ() {
        return super.zzJZ();
    }

    public /* bridge */ /* synthetic */ zzauj zzKa() {
        return super.zzKa();
    }

    public /* bridge */ /* synthetic */ zzatu zzKb() {
        return super.zzKb();
    }

    public /* bridge */ /* synthetic */ zzatl zzKc() {
        return super.zzKc();
    }

    public /* bridge */ /* synthetic */ zzaul zzKd() {
        return super.zzKd();
    }

    public /* bridge */ /* synthetic */ zzauk zzKe() {
        return super.zzKe();
    }

    public /* bridge */ /* synthetic */ zzatv zzKf() {
        return super.zzKf();
    }

    public /* bridge */ /* synthetic */ zzatj zzKg() {
        return super.zzKg();
    }

    public /* bridge */ /* synthetic */ zzaut zzKh() {
        return super.zzKh();
    }

    public /* bridge */ /* synthetic */ zzauc zzKi() {
        return super.zzKi();
    }

    public /* bridge */ /* synthetic */ zzaun zzKj() {
        return super.zzKj();
    }

    public /* bridge */ /* synthetic */ zzaud zzKk() {
        return super.zzKk();
    }

    public /* bridge */ /* synthetic */ zzatx zzKl() {
        return super.zzKl();
    }

    public /* bridge */ /* synthetic */ zzaua zzKm() {
        return super.zzKm();
    }

    public /* bridge */ /* synthetic */ zzati zzKn() {
        return super.zzKn();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public long zzL(Context context, String str) {
        zzmR();
        zzac.zzw(context);
        zzac.zzdr(str);
        PackageManager packageManager = context.getPackageManager();
        MessageDigest zzch = zzch("MD5");
        if (zzch == null) {
            zzKl().zzLY().log("Could not get MD5 instance");
            return -1;
        }
        if (packageManager != null) {
            try {
                if (!zzM(context, str)) {
                    PackageInfo packageInfo = zzadg.zzbi(context).getPackageInfo(getContext().getPackageName(), 64);
                    if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                        return zzy(zzch.digest(packageInfo.signatures[0].toByteArray()));
                    }
                    zzKl().zzMa().log("Could not get signatures");
                    return -1;
                }
            } catch (PackageManager.NameNotFoundException e) {
                zzKl().zzLY().zzj("Package name not found", e);
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public Bundle zzM(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zzl = zzKh().zzl(str, bundle.get(str));
                if (zzl == null) {
                    zzKl().zzMa().zzj("Param value can't be null", str);
                } else {
                    zzKh().zza(bundle2, str, zzl);
                }
            }
        }
        return bundle2;
    }

    /* access modifiers changed from: package-private */
    public boolean zzM(Context context, String str) {
        zzatx.zza zza;
        String str2;
        X500Principal x500Principal = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo = zzadg.zzbi(context).getPackageInfo(str, 64);
            if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                return true;
            }
            return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(x500Principal);
        } catch (CertificateException e) {
            e = e;
            zza = zzKl().zzLY();
            str2 = "Error obtaining certificate";
            zza.zzj(str2, e);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
            zza = zzKl().zzLY();
            str2 = "Package name not found";
            zza.zzj(str2, e);
            return true;
        }
    }

    public long zzNi() {
        long andIncrement;
        long j;
        if (this.zzbwh.get() == 0) {
            synchronized (this.zzbwh) {
                long nextLong = new Random(System.nanoTime() ^ zznR().currentTimeMillis()).nextLong();
                int i = this.zzbwi + 1;
                this.zzbwi = i;
                j = nextLong + ((long) i);
            }
            return j;
        }
        synchronized (this.zzbwh) {
            this.zzbwh.compareAndSet(-1, 1);
            andIncrement = this.zzbwh.getAndIncrement();
        }
        return andIncrement;
    }

    public Bundle zza(String str, Bundle bundle, @Nullable List<String> list, boolean z) {
        int i;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        zzKn().zzKL();
        int i2 = 0;
        for (String str2 : bundle.keySet()) {
            if (list == null || !list.contains(str2)) {
                i = z ? zzfY(str2) : 0;
                if (i == 0) {
                    i = zzfZ(str2);
                }
            } else {
                i = 0;
            }
            if (i != 0) {
                if (zzd(bundle2, i)) {
                    bundle2.putString("_ev", zza(str2, zzKn().zzKO(), true));
                    if (i == 3) {
                        zzb(bundle2, (Object) str2);
                    }
                }
            } else if (zzk(str2, bundle.get(str2)) || "_ev".equals(str2)) {
                if (zzfT(str2) && (i2 = i2 + 1) > 25) {
                    StringBuilder sb = new StringBuilder(48);
                    sb.append("Event can't contain more then ");
                    sb.append(25);
                    sb.append(" params");
                    zzKl().zzLY().zze(sb.toString(), str, bundle);
                    zzd(bundle2, 5);
                }
            } else if (zzd(bundle2, 4)) {
                bundle2.putString("_ev", zza(str2, zzKn().zzKO(), true));
                zzb(bundle2, bundle.get(str2));
            }
            bundle2.remove(str2);
        }
        return bundle2;
    }

    /* access modifiers changed from: package-private */
    public zzatq zza(String str, Bundle bundle, String str2, long j, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (zzKh().zzfV(str) != 0) {
            zzKl().zzLY().zzj("Invalid conditional property event name", str);
            throw new IllegalArgumentException();
        }
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        bundle2.putString("_o", str2);
        Bundle zza = zzKh().zza(str, bundle2, (List<String>) zzf.zzx("_o"), z2);
        if (z) {
            zza = zzM(zza);
        }
        return new zzatq(str, new zzato(zza), str2, j);
    }

    public String zza(String str, int i, boolean z) {
        if (str.length() <= i) {
            return str;
        }
        if (z) {
            return String.valueOf(str.substring(0, i)).concat("...");
        }
        return null;
    }

    public void zza(int i, String str, String str2, int i2) {
        zza((String) null, i, str, str2, i2);
    }

    public void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                zzKl().zzMb().zze("Not putting event parameter. Invalid value type. name, type", str, obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public void zza(zzauw.zzc zzc, Object obj) {
        zzac.zzw(obj);
        zzc.zzaGV = null;
        zzc.zzbxc = null;
        zzc.zzbwf = null;
        if (obj instanceof String) {
            zzc.zzaGV = (String) obj;
        } else if (obj instanceof Long) {
            zzc.zzbxc = (Long) obj;
        } else if (obj instanceof Double) {
            zzc.zzbwf = (Double) obj;
        } else {
            zzKl().zzLY().zzj("Ignoring invalid (type) event param value", obj);
        }
    }

    public void zza(zzauw.zzg zzg, Object obj) {
        zzac.zzw(obj);
        zzg.zzaGV = null;
        zzg.zzbxc = null;
        zzg.zzbwf = null;
        if (obj instanceof String) {
            zzg.zzaGV = (String) obj;
        } else if (obj instanceof Long) {
            zzg.zzbxc = (Long) obj;
        } else if (obj instanceof Double) {
            zzg.zzbwf = (Double) obj;
        } else {
            zzKl().zzLY().zzj("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public void zza(String str, int i, String str2, String str3, int i2) {
        Bundle bundle = new Bundle();
        zzd(bundle, i);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString(str2, str3);
        }
        if (i == 6 || i == 7 || i == 2) {
            bundle.putLong("_el", (long) i2);
        }
        this.zzbqc.zzKn().zzLg();
        this.zzbqc.zzKa().zze("auto", "_err", bundle);
    }

    /* access modifiers changed from: package-private */
    public boolean zza(String str, String str2, int i, Object obj) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
            return false;
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.length() > i) {
            zzKl().zzMa().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public byte[] zza(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    public byte[] zza(zzauw.zzd zzd) {
        try {
            byte[] bArr = new byte[zzd.zzaeT()];
            zzbxm zzag = zzbxm.zzag(bArr);
            zzd.zza(zzag);
            zzag.zzaeG();
            return bArr;
        } catch (IOException e) {
            zzKl().zzLY().zzj("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzac(String str, String str2) {
        if (str2 == null) {
            zzKl().zzLY().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzKl().zzLY().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (!Character.isLetter(codePointAt)) {
                zzKl().zzLY().zze("Name must start with a letter. Type, name", str, str2);
                return false;
            }
            int length = str2.length();
            int charCount = Character.charCount(codePointAt);
            while (charCount < length) {
                int codePointAt2 = str2.codePointAt(charCount);
                if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                    charCount += Character.charCount(codePointAt2);
                } else {
                    zzKl().zzLY().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzad(String str, String str2) {
        if (str2 == null) {
            zzKl().zzLY().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzKl().zzLY().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            int codePointAt = str2.codePointAt(0);
            if (Character.isLetter(codePointAt) || codePointAt == 95) {
                int length = str2.length();
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = str2.codePointAt(charCount);
                    if (codePointAt2 == 95 || Character.isLetterOrDigit(codePointAt2)) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        zzKl().zzLY().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzKl().zzLY().zze("Name must start with a letter or _ (underscore). Type, name", str, str2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        zzKl().zzLY().log("Failed to load parcelable from buffer");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0030, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends android.os.Parcelable> T zzb(byte[] r5, android.os.Parcelable.Creator<T> r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.os.Parcel r1 = android.os.Parcel.obtain()
            int r2 = r5.length     // Catch:{ zza -> 0x001c }
            r3 = 0
            r1.unmarshall(r5, r3, r2)     // Catch:{ zza -> 0x001c }
            r1.setDataPosition(r3)     // Catch:{ zza -> 0x001c }
            java.lang.Object r5 = r6.createFromParcel(r1)     // Catch:{ zza -> 0x001c }
            android.os.Parcelable r5 = (android.os.Parcelable) r5     // Catch:{ zza -> 0x001c }
            r1.recycle()
            return r5
        L_0x001a:
            r5 = move-exception
            goto L_0x002d
        L_0x001c:
            com.google.android.gms.internal.zzatx r5 = r4.zzKl()     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.zzatx$zza r5 = r5.zzLY()     // Catch:{ all -> 0x001a }
            java.lang.String r6 = "Failed to load parcelable from buffer"
            r5.log(r6)     // Catch:{ all -> 0x001a }
            r1.recycle()
            return r0
        L_0x002d:
            r1.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaut.zzb(byte[], android.os.Parcelable$Creator):android.os.Parcelable");
    }

    public void zzb(Bundle bundle, Object obj) {
        zzac.zzw(bundle);
        if (obj == null) {
            return;
        }
        if ((obj instanceof String) || (obj instanceof CharSequence)) {
            bundle.putLong("_el", (long) String.valueOf(obj).length());
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzb(String str, int i, String str2) {
        if (str2 == null) {
            zzKl().zzLY().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() <= i) {
            return true;
        } else {
            zzKl().zzLY().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    @WorkerThread
    public boolean zzbW(String str) {
        zzmR();
        if (zzadg.zzbi(getContext()).checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzKl().zzMd().zzj("Permission not granted", str);
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean zzc(String str, Map<String, String> map, String str2) {
        if (str2 == null) {
            zzKl().zzLY().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.startsWith("firebase_")) {
            zzKl().zzLY().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        } else if (map == null || !map.containsKey(str2)) {
            return true;
        } else {
            zzKl().zzLY().zze("Name is reserved. Type, name", str, str2);
            return false;
        }
    }

    public boolean zzd(Bundle bundle, int i) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean zzd(zzatq zzatq, zzatd zzatd) {
        zzac.zzw(zzatq);
        zzac.zzw(zzatd);
        if (!TextUtils.isEmpty(zzatd.zzbqL)) {
            return true;
        }
        zzKn().zzLg();
        return false;
    }

    public int zzfU(String str) {
        if (!zzac("event", str)) {
            return 2;
        }
        if (!zzc("event", AppMeasurement.zza.zzbqd, str)) {
            return 13;
        }
        return !zzb("event", zzKn().zzKM(), str) ? 2 : 0;
    }

    public int zzfV(String str) {
        if (!zzad("event", str)) {
            return 2;
        }
        if (!zzc("event", AppMeasurement.zza.zzbqd, str)) {
            return 13;
        }
        return !zzb("event", zzKn().zzKM(), str) ? 2 : 0;
    }

    public int zzfW(String str) {
        if (!zzac("user property", str)) {
            return 6;
        }
        if (!zzc("user property", AppMeasurement.zzg.zzbqi, str)) {
            return 15;
        }
        return !zzb("user property", zzKn().zzKN(), str) ? 6 : 0;
    }

    public int zzfX(String str) {
        if (!zzad("user property", str)) {
            return 6;
        }
        if (!zzc("user property", AppMeasurement.zzg.zzbqi, str)) {
            return 15;
        }
        return !zzb("user property", zzKn().zzKN(), str) ? 6 : 0;
    }

    public int zzfY(String str) {
        if (!zzac("event param", str)) {
            return 3;
        }
        if (!zzc("event param", (Map<String, String>) null, str)) {
            return 14;
        }
        return !zzb("event param", zzKn().zzKO(), str) ? 3 : 0;
    }

    public int zzfZ(String str) {
        if (!zzad("event param", str)) {
            return 3;
        }
        if (!zzc("event param", (Map<String, String>) null, str)) {
            return 14;
        }
        return !zzb("event param", zzKn().zzKO(), str) ? 3 : 0;
    }

    public boolean zzga(String str) {
        if (TextUtils.isEmpty(str)) {
            zzKl().zzLY().log("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
            return false;
        } else if (zzgb(str)) {
            return true;
        } else {
            zzKl().zzLY().zzj("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", str);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzgb(String str) {
        zzac.zzw(str);
        return str.matches("^1:\\d+:android:[a-f0-9]+$");
    }

    public boolean zzge(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String zzLC = zzKn().zzLC();
        zzKn().zzLg();
        return zzLC.equals(str);
    }

    /* access modifiers changed from: package-private */
    public boolean zzgg(String str) {
        return "1".equals(zzKi().zzZ(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public boolean zzgh(String str) {
        return "1".equals(zzKi().zzZ(str, "measurement.upload.blacklist_public"));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003c A[RETURN] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zzgi(java.lang.String r5) {
        /*
            r4 = this;
            com.google.android.gms.common.internal.zzac.zzdr(r5)
            int r0 = r5.hashCode()
            r1 = 94660(0x171c4, float:1.32647E-40)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x002d
            r1 = 95025(0x17331, float:1.33158E-40)
            if (r0 == r1) goto L_0x0023
            r1 = 95027(0x17333, float:1.33161E-40)
            if (r0 == r1) goto L_0x0019
            goto L_0x0037
        L_0x0019:
            java.lang.String r0 = "_ui"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0037
            r5 = r3
            goto L_0x0038
        L_0x0023:
            java.lang.String r0 = "_ug"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0037
            r5 = 2
            goto L_0x0038
        L_0x002d:
            java.lang.String r0 = "_in"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0037
            r5 = r2
            goto L_0x0038
        L_0x0037:
            r5 = -1
        L_0x0038:
            switch(r5) {
                case 0: goto L_0x003c;
                case 1: goto L_0x003c;
                case 2: goto L_0x003c;
                default: goto L_0x003b;
            }
        L_0x003b:
            return r2
        L_0x003c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaut.zzgi(java.lang.String):boolean");
    }

    public boolean zzh(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zznR().currentTimeMillis() - j) > j2;
    }

    public boolean zzk(String str, Object obj) {
        String str2;
        int zzKP;
        if (zzgd(str)) {
            str2 = "param";
            zzKP = zzKn().zzKQ();
        } else {
            str2 = "param";
            zzKP = zzKn().zzKP();
        }
        return zza(str2, str, zzKP, obj);
    }

    public byte[] zzk(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzKl().zzLY().zzj("Failed to gzip content", e);
            throw e;
        }
    }

    public Object zzl(String str, Object obj) {
        int zzKQ;
        boolean z;
        if ("_ev".equals(str)) {
            zzKQ = zzKn().zzKQ();
            z = true;
        } else {
            zzKQ = zzgd(str) ? zzKn().zzKQ() : zzKn().zzKP();
            z = false;
        }
        return zza(zzKQ, obj, z);
    }

    public int zzm(String str, Object obj) {
        return zza("_ldl".equals(str) ? "user property referrer" : "user property", str, zzgc(str), obj) ? 0 : 7;
    }

    public /* bridge */ /* synthetic */ void zzmR() {
        super.zzmR();
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
        SecureRandom secureRandom = new SecureRandom();
        long nextLong = secureRandom.nextLong();
        if (nextLong == 0) {
            nextLong = secureRandom.nextLong();
            if (nextLong == 0) {
                zzKl().zzMa().log("Utils falling back to Random for random id");
            }
        }
        this.zzbwh.set(nextLong);
    }

    public Object zzn(String str, Object obj) {
        int zzgc;
        boolean z;
        if ("_ldl".equals(str)) {
            zzgc = zzgc(str);
            z = true;
        } else {
            zzgc = zzgc(str);
            z = false;
        }
        return zza(zzgc, obj, z);
    }

    public /* bridge */ /* synthetic */ zze zznR() {
        return super.zznR();
    }

    public Bundle zzu(@NonNull Uri uri) {
        String str;
        String str2;
        String str3;
        String str4;
        if (uri == null) {
            return null;
        }
        try {
            if (uri.isHierarchical()) {
                str4 = uri.getQueryParameter("utm_campaign");
                str3 = uri.getQueryParameter("utm_source");
                str2 = uri.getQueryParameter("utm_medium");
                str = uri.getQueryParameter("gclid");
            } else {
                str4 = null;
                str3 = null;
                str2 = null;
                str = null;
            }
            if (TextUtils.isEmpty(str4) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str)) {
                return null;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(str4)) {
                bundle.putString(FirebaseAnalytics.Param.CAMPAIGN, str4);
            }
            if (!TextUtils.isEmpty(str3)) {
                bundle.putString("source", str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                bundle.putString(FirebaseAnalytics.Param.MEDIUM, str2);
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("gclid", str);
            }
            String queryParameter = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(FirebaseAnalytics.Param.TERM, queryParameter);
            }
            String queryParameter2 = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString("content", queryParameter2);
            }
            String queryParameter3 = uri.getQueryParameter(FirebaseAnalytics.Param.ACLID);
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString(FirebaseAnalytics.Param.ACLID, queryParameter3);
            }
            String queryParameter4 = uri.getQueryParameter(FirebaseAnalytics.Param.CP1);
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString(FirebaseAnalytics.Param.CP1, queryParameter4);
            }
            String queryParameter5 = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter5)) {
                bundle.putString("anid", queryParameter5);
            }
            return bundle;
        } catch (UnsupportedOperationException e) {
            zzKl().zzMa().zzj("Install referrer url isn't a hierarchical URI", e);
            return null;
        }
    }

    public byte[] zzx(byte[] bArr) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read <= 0) {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (IOException e) {
            zzKl().zzLY().zzj("Failed to ungzip content", e);
            throw e;
        }
    }

    @WorkerThread
    public long zzz(byte[] bArr) {
        zzac.zzw(bArr);
        zzmR();
        MessageDigest zzch = zzch("MD5");
        if (zzch != null) {
            return zzy(zzch.digest(bArr));
        }
        zzKl().zzLY().log("Failed to get MD5");
        return 0;
    }
}
