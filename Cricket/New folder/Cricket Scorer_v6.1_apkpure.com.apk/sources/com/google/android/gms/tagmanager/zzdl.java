package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzak;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzdl {
    private static final Object zzbIC = null;
    private static Long zzbID = new Long(0);
    private static Double zzbIE = new Double(0.0d);
    private static zzdk zzbIF = zzdk.zzaA(0);
    private static String zzbIG = new String("");
    private static Boolean zzbIH = new Boolean(false);
    private static List<Object> zzbII = new ArrayList(0);
    private static Map<Object, Object> zzbIJ = new HashMap();
    private static zzak.zza zzbIK = zzR(zzbIG);

    private static double getDouble(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        zzbo.e("getDouble received non-Number");
        return 0.0d;
    }

    public static String zzM(Object obj) {
        return obj == null ? zzbIG : obj.toString();
    }

    public static zzdk zzN(Object obj) {
        return obj instanceof zzdk ? (zzdk) obj : zzT(obj) ? zzdk.zzaA(zzU(obj)) : zzS(obj) ? zzdk.zza(Double.valueOf(getDouble(obj))) : zzhx(zzM(obj));
    }

    public static Long zzO(Object obj) {
        return zzT(obj) ? Long.valueOf(zzU(obj)) : zzhy(zzM(obj));
    }

    public static Double zzP(Object obj) {
        return zzS(obj) ? Double.valueOf(getDouble(obj)) : zzhz(zzM(obj));
    }

    public static Boolean zzQ(Object obj) {
        return obj instanceof Boolean ? (Boolean) obj : zzhA(zzM(obj));
    }

    public static zzak.zza zzR(Object obj) {
        String obj2;
        zzak.zza zza = new zzak.zza();
        if (obj instanceof zzak.zza) {
            return (zzak.zza) obj;
        }
        boolean z = false;
        if (obj instanceof String) {
            zza.type = 1;
            obj2 = (String) obj;
        } else {
            if (obj instanceof List) {
                zza.type = 2;
                List<Object> list = (List) obj;
                ArrayList arrayList = new ArrayList(list.size());
                boolean z2 = false;
                for (Object zzR : list) {
                    zzak.zza zzR2 = zzR(zzR);
                    if (zzR2 == zzbIK) {
                        return zzbIK;
                    }
                    z2 = z2 || zzR2.zzlD;
                    arrayList.add(zzR2);
                }
                zza.zzlu = (zzak.zza[]) arrayList.toArray(new zzak.zza[0]);
                z = z2;
            } else if (obj instanceof Map) {
                zza.type = 3;
                Set<Map.Entry> entrySet = ((Map) obj).entrySet();
                ArrayList arrayList2 = new ArrayList(entrySet.size());
                ArrayList arrayList3 = new ArrayList(entrySet.size());
                boolean z3 = false;
                for (Map.Entry entry : entrySet) {
                    zzak.zza zzR3 = zzR(entry.getKey());
                    zzak.zza zzR4 = zzR(entry.getValue());
                    if (zzR3 == zzbIK || zzR4 == zzbIK) {
                        return zzbIK;
                    }
                    z3 = z3 || zzR3.zzlD || zzR4.zzlD;
                    arrayList2.add(zzR3);
                    arrayList3.add(zzR4);
                }
                zza.zzlv = (zzak.zza[]) arrayList2.toArray(new zzak.zza[0]);
                zza.zzlw = (zzak.zza[]) arrayList3.toArray(new zzak.zza[0]);
                z = z3;
            } else if (zzS(obj)) {
                zza.type = 1;
                obj2 = obj.toString();
            } else if (zzT(obj)) {
                zza.type = 6;
                zza.zzlz = zzU(obj);
            } else if (obj instanceof Boolean) {
                zza.type = 8;
                zza.zzlA = ((Boolean) obj).booleanValue();
            } else {
                String valueOf = String.valueOf(obj == null ? "null" : obj.getClass().toString());
                zzbo.e(valueOf.length() != 0 ? "Converting to Value from unknown object type: ".concat(valueOf) : new String("Converting to Value from unknown object type: "));
                return zzbIK;
            }
            zza.zzlD = z;
            return zza;
        }
        zza.string = obj2;
        zza.zzlD = z;
        return zza;
    }

    public static Object zzRL() {
        return null;
    }

    public static Long zzRM() {
        return zzbID;
    }

    public static Double zzRN() {
        return zzbIE;
    }

    public static Boolean zzRO() {
        return zzbIH;
    }

    public static zzdk zzRP() {
        return zzbIF;
    }

    public static String zzRQ() {
        return zzbIG;
    }

    public static zzak.zza zzRR() {
        return zzbIK;
    }

    private static boolean zzS(Object obj) {
        if ((obj instanceof Double) || (obj instanceof Float)) {
            return true;
        }
        return (obj instanceof zzdk) && ((zzdk) obj).zzRG();
    }

    private static boolean zzT(Object obj) {
        if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
            return true;
        }
        return (obj instanceof zzdk) && ((zzdk) obj).zzRH();
    }

    private static long zzU(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzbo.e("getInt64 received non-Number");
        return 0;
    }

    public static String zze(zzak.zza zza) {
        return zzM(zzj(zza));
    }

    public static zzdk zzf(zzak.zza zza) {
        return zzN(zzj(zza));
    }

    public static Long zzg(zzak.zza zza) {
        return zzO(zzj(zza));
    }

    public static Double zzh(zzak.zza zza) {
        return zzP(zzj(zza));
    }

    private static Boolean zzhA(String str) {
        return PdfBoolean.TRUE.equalsIgnoreCase(str) ? Boolean.TRUE : PdfBoolean.FALSE.equalsIgnoreCase(str) ? Boolean.FALSE : zzbIH;
    }

    public static zzak.zza zzhw(String str) {
        zzak.zza zza = new zzak.zza();
        zza.type = 5;
        zza.zzly = str;
        return zza;
    }

    private static zzdk zzhx(String str) {
        try {
            return zzdk.zzhv(str);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(33 + String.valueOf(str).length());
            sb.append("Failed to convert '");
            sb.append(str);
            sb.append("' to a number.");
            zzbo.e(sb.toString());
            return zzbIF;
        }
    }

    private static Long zzhy(String str) {
        zzdk zzhx = zzhx(str);
        return zzhx == zzbIF ? zzbID : Long.valueOf(zzhx.longValue());
    }

    private static Double zzhz(String str) {
        zzdk zzhx = zzhx(str);
        return zzhx == zzbIF ? zzbIE : Double.valueOf(zzhx.doubleValue());
    }

    public static Boolean zzi(zzak.zza zza) {
        return zzQ(zzj(zza));
    }

    public static Object zzj(zzak.zza zza) {
        String str;
        if (zza == null) {
            return null;
        }
        int i = 0;
        switch (zza.type) {
            case 1:
                return zza.string;
            case 2:
                ArrayList arrayList = new ArrayList(zza.zzlu.length);
                zzak.zza[] zzaArr = zza.zzlu;
                int length = zzaArr.length;
                while (i < length) {
                    Object zzj = zzj(zzaArr[i]);
                    if (zzj == null) {
                        return null;
                    }
                    arrayList.add(zzj);
                    i++;
                }
                return arrayList;
            case 3:
                if (zza.zzlv.length != zza.zzlw.length) {
                    String valueOf = String.valueOf(zza.toString());
                    zzbo.e(valueOf.length() != 0 ? "Converting an invalid value to object: ".concat(valueOf) : new String("Converting an invalid value to object: "));
                    return null;
                }
                HashMap hashMap = new HashMap(zza.zzlw.length);
                while (i < zza.zzlv.length) {
                    Object zzj2 = zzj(zza.zzlv[i]);
                    Object zzj3 = zzj(zza.zzlw[i]);
                    if (zzj2 == null || zzj3 == null) {
                        return null;
                    }
                    hashMap.put(zzj2, zzj3);
                    i++;
                }
                return hashMap;
            case 4:
                str = "Trying to convert a macro reference to object";
                break;
            case 5:
                str = "Trying to convert a function id to object";
                break;
            case 6:
                return Long.valueOf(zza.zzlz);
            case 7:
                StringBuffer stringBuffer = new StringBuffer();
                zzak.zza[] zzaArr2 = zza.zzlB;
                int length2 = zzaArr2.length;
                while (i < length2) {
                    String zze = zze(zzaArr2[i]);
                    if (zze == zzbIG) {
                        return null;
                    }
                    stringBuffer.append(zze);
                    i++;
                }
                return stringBuffer.toString();
            case 8:
                return Boolean.valueOf(zza.zzlA);
            default:
                int i2 = zza.type;
                StringBuilder sb = new StringBuilder(46);
                sb.append("Failed to convert a value of type: ");
                sb.append(i2);
                str = sb.toString();
                break;
        }
        zzbo.e(str);
        return null;
    }
}
