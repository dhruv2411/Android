package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzatx;
import com.google.android.gms.internal.zzauu;
import com.google.android.gms.internal.zzauw;
import com.google.android.gms.measurement.AppMeasurement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

class zzatf extends zzauh {
    zzatf(zzaue zzaue) {
        super(zzaue);
    }

    private Boolean zza(zzauu.zzb zzb, zzauw.zzb zzb2, long j) {
        Boolean bool;
        String str;
        Object obj;
        if (zzb.zzbws != null) {
            Boolean zza = zza(j, zzb.zzbws);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        for (zzauu.zzc zzc : zzb.zzbwq) {
            if (TextUtils.isEmpty(zzc.zzbwx)) {
                zzKl().zzMa().zzj("null or empty param name in filter. event", zzb2.name);
                return null;
            }
            hashSet.add(zzc.zzbwx);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzauw.zzc zzc2 : zzb2.zzbwY) {
            if (hashSet.contains(zzc2.name)) {
                if (zzc2.zzbxc != null) {
                    str = zzc2.name;
                    obj = zzc2.zzbxc;
                } else if (zzc2.zzbwf != null) {
                    str = zzc2.name;
                    obj = zzc2.zzbwf;
                } else if (zzc2.zzaGV != null) {
                    str = zzc2.name;
                    obj = zzc2.zzaGV;
                } else {
                    zzKl().zzMa().zze("Unknown value for param. event, param", zzb2.name, zzc2.name);
                    return null;
                }
                arrayMap.put(str, obj);
            }
        }
        for (zzauu.zzc zzc3 : zzb.zzbwq) {
            boolean equals = Boolean.TRUE.equals(zzc3.zzbww);
            String str2 = zzc3.zzbwx;
            if (TextUtils.isEmpty(str2)) {
                zzKl().zzMa().zzj("Event has empty param name. event", zzb2.name);
                return null;
            }
            Object obj2 = arrayMap.get(str2);
            if (obj2 instanceof Long) {
                if (zzc3.zzbwv == null) {
                    zzKl().zzMa().zze("No number filter for long param. event, param", zzb2.name, str2);
                    return null;
                }
                Boolean zza2 = zza(((Long) obj2).longValue(), zzc3.zzbwv);
                if (zza2 == null) {
                    return null;
                }
                if ((true ^ zza2.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj2 instanceof Double) {
                if (zzc3.zzbwv == null) {
                    zzKl().zzMa().zze("No number filter for double param. event, param", zzb2.name, str2);
                    return null;
                }
                Boolean zza3 = zza(((Double) obj2).doubleValue(), zzc3.zzbwv);
                if (zza3 == null) {
                    return null;
                }
                if ((true ^ zza3.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj2 instanceof String) {
                if (zzc3.zzbwu != null) {
                    bool = zza((String) obj2, zzc3.zzbwu);
                } else if (zzc3.zzbwv != null) {
                    String str3 = (String) obj2;
                    if (zzaut.zzgf(str3)) {
                        bool = zza(str3, zzc3.zzbwv);
                    } else {
                        zzKl().zzMa().zze("Invalid param value for number filter. event, param", zzb2.name, str2);
                        return null;
                    }
                } else {
                    zzKl().zzMa().zze("No filter for String param. event, param", zzb2.name, str2);
                    return null;
                }
                if (bool == null) {
                    return null;
                }
                if ((true ^ bool.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj2 == null) {
                zzKl().zzMe().zze("Missing param for filter. event, param", zzb2.name, str2);
                return false;
            } else {
                zzKl().zzMa().zze("Unknown param type. event, param", zzb2.name, str2);
                return null;
            }
        }
        return true;
    }

    private Boolean zza(zzauu.zze zze, zzauw.zzg zzg) {
        zzatx.zza zzMa;
        String str;
        Boolean zza;
        zzauu.zzc zzc = zze.zzbwF;
        if (zzc == null) {
            zzMa = zzKl().zzMa();
            str = "Missing property filter. property";
        } else {
            boolean equals = Boolean.TRUE.equals(zzc.zzbww);
            if (zzg.zzbxc != null) {
                if (zzc.zzbwv == null) {
                    zzMa = zzKl().zzMa();
                    str = "No number filter for long property. property";
                } else {
                    zza = zza(zzg.zzbxc.longValue(), zzc.zzbwv);
                }
            } else if (zzg.zzbwf != null) {
                if (zzc.zzbwv == null) {
                    zzMa = zzKl().zzMa();
                    str = "No number filter for double property. property";
                } else {
                    zza = zza(zzg.zzbwf.doubleValue(), zzc.zzbwv);
                }
            } else if (zzg.zzaGV == null) {
                zzMa = zzKl().zzMa();
                str = "User property has no value, property";
            } else if (zzc.zzbwu != null) {
                zza = zza(zzg.zzaGV, zzc.zzbwu);
            } else if (zzc.zzbwv == null) {
                zzKl().zzMa().zzj("No string or number filter defined. property", zzg.name);
                return null;
            } else if (zzaut.zzgf(zzg.zzaGV)) {
                zza = zza(zzg.zzaGV, zzc.zzbwv);
            } else {
                zzKl().zzMa().zze("Invalid user property value for Numeric number filter. property, value", zzg.name, zzg.zzaGV);
                return null;
            }
            return zza(zza, equals);
        }
        zzMa.zzj(str, zzg.name);
        return null;
    }

    static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        boolean matches;
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                matches = Pattern.compile(str3, z ? 0 : 66).matcher(str).matches();
                break;
            case 2:
                matches = str.startsWith(str2);
                break;
            case 3:
                matches = str.endsWith(str2);
                break;
            case 4:
                matches = str.contains(str2);
                break;
            case 5:
                matches = str.equals(str2);
                break;
            case 6:
                matches = list.contains(str);
                break;
            default:
                return null;
        }
        return Boolean.valueOf(matches);
    }

    private Boolean zza(BigDecimal bigDecimal, int i, BigDecimal bigDecimal2, BigDecimal bigDecimal3, BigDecimal bigDecimal4, double d) {
        if (bigDecimal == null) {
            return null;
        }
        if (i == 4) {
            if (bigDecimal3 == null || bigDecimal4 == null) {
                return null;
            }
        } else if (bigDecimal2 == null) {
            return null;
        }
        boolean z = false;
        switch (i) {
            case 1:
                if (bigDecimal.compareTo(bigDecimal2) == -1) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 2:
                if (bigDecimal.compareTo(bigDecimal2) == 1) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 3:
                if (d != 0.0d) {
                    if (bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d).multiply(new BigDecimal(2)))) == -1) {
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
                if (bigDecimal.compareTo(bigDecimal2) == 0) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 4:
                if (!(bigDecimal.compareTo(bigDecimal3) == -1 || bigDecimal.compareTo(bigDecimal4) == 1)) {
                    z = true;
                }
                return Boolean.valueOf(z);
            default:
                return null;
        }
    }

    private List<String> zza(String[] strArr, boolean z) {
        if (z) {
            return Arrays.asList(strArr);
        }
        ArrayList arrayList = new ArrayList();
        for (String upperCase : strArr) {
            arrayList.add(upperCase.toUpperCase(Locale.ENGLISH));
        }
        return arrayList;
    }

    public Boolean zza(double d, zzauu.zzd zzd) {
        try {
            return zza(new BigDecimal(d), zzd, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public Boolean zza(long j, zzauu.zzd zzd) {
        try {
            return zza(new BigDecimal(j), zzd, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public Boolean zza(String str, zzauu.zzd zzd) {
        if (!zzaut.zzgf(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzd, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Boolean zza(String str, zzauu.zzf zzf) {
        zzac.zzw(zzf);
        if (str == null || zzf.zzbwG == null || zzf.zzbwG.intValue() == 0) {
            return null;
        }
        if (zzf.zzbwG.intValue() == 6) {
            if (zzf.zzbwJ == null || zzf.zzbwJ.length == 0) {
                return null;
            }
        } else if (zzf.zzbwH == null) {
            return null;
        }
        int intValue = zzf.zzbwG.intValue();
        boolean z = zzf.zzbwI != null && zzf.zzbwI.booleanValue();
        String upperCase = (z || intValue == 1 || intValue == 6) ? zzf.zzbwH : zzf.zzbwH.toUpperCase(Locale.ENGLISH);
        return zza(str, intValue, z, upperCase, zzf.zzbwJ == null ? null : zza(zzf.zzbwJ, z), intValue == 1 ? upperCase : null);
    }

    /* access modifiers changed from: package-private */
    public Boolean zza(BigDecimal bigDecimal, zzauu.zzd zzd, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        zzac.zzw(zzd);
        if (zzd.zzbwy == null || zzd.zzbwy.intValue() == 0) {
            return null;
        }
        if (zzd.zzbwy.intValue() == 4) {
            if (zzd.zzbwB == null || zzd.zzbwC == null) {
                return null;
            }
        } else if (zzd.zzbwA == null) {
            return null;
        }
        int intValue = zzd.zzbwy.intValue();
        if (zzd.zzbwy.intValue() == 4) {
            if (!zzaut.zzgf(zzd.zzbwB) || !zzaut.zzgf(zzd.zzbwC)) {
                return null;
            }
            try {
                bigDecimal3 = new BigDecimal(zzd.zzbwB);
                bigDecimal4 = null;
                bigDecimal2 = new BigDecimal(zzd.zzbwC);
            } catch (NumberFormatException unused) {
                return null;
            }
        } else if (!zzaut.zzgf(zzd.zzbwA)) {
            return null;
        } else {
            try {
                bigDecimal4 = new BigDecimal(zzd.zzbwA);
                bigDecimal3 = null;
                bigDecimal2 = null;
            } catch (NumberFormatException unused2) {
                return null;
            }
        }
        return zza(bigDecimal, intValue, bigDecimal4, bigDecimal3, bigDecimal2, d);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public void zza(String str, zzauu.zza[] zzaArr) {
        zzac.zzw(zzaArr);
        for (zzauu.zza zza : zzaArr) {
            for (zzauu.zzb zzb : zza.zzbwm) {
                String str2 = AppMeasurement.zza.zzbqd.get(zzb.zzbwp);
                if (str2 != null) {
                    zzb.zzbwp = str2;
                }
                for (zzauu.zzc zzc : zzb.zzbwq) {
                    String str3 = AppMeasurement.zze.zzbqe.get(zzc.zzbwx);
                    if (str3 != null) {
                        zzc.zzbwx = str3;
                    }
                }
            }
            for (zzauu.zze zze : zza.zzbwl) {
                String str4 = AppMeasurement.zzg.zzbqi.get(zze.zzbwE);
                if (str4 != null) {
                    zze.zzbwE = str4;
                }
            }
        }
        zzKg().zzb(str, zzaArr);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public zzauw.zza[] zza(String str, zzauw.zzb[] zzbArr, zzauw.zzg[] zzgArr) {
        ArrayMap arrayMap;
        ArrayMap arrayMap2;
        int i;
        Map map;
        Iterator it;
        char c;
        zzauu.zze zze;
        Iterator it2;
        int i2;
        int i3;
        zzauw.zzb zzb;
        int i4;
        ArrayMap arrayMap3;
        ArrayMap arrayMap4;
        zzatn zzatn;
        ArrayMap arrayMap5;
        ArrayMap arrayMap6;
        ArrayMap arrayMap7;
        Object obj;
        String str2;
        zzatx.zza zza;
        Object obj2;
        ArrayMap arrayMap8;
        Iterator<Integer> it3;
        Map<Integer, zzauw.zzf> map2;
        String str3 = str;
        zzauw.zzb[] zzbArr2 = zzbArr;
        zzauw.zzg[] zzgArr2 = zzgArr;
        zzac.zzdr(str);
        HashSet hashSet = new HashSet();
        ArrayMap arrayMap9 = new ArrayMap();
        ArrayMap arrayMap10 = new ArrayMap();
        ArrayMap arrayMap11 = new ArrayMap();
        Map<Integer, zzauw.zzf> zzfy = zzKg().zzfy(str3);
        if (zzfy != null) {
            Iterator<Integer> it4 = zzfy.keySet().iterator();
            while (it4.hasNext()) {
                int intValue = it4.next().intValue();
                zzauw.zzf zzf = zzfy.get(Integer.valueOf(intValue));
                BitSet bitSet = (BitSet) arrayMap10.get(Integer.valueOf(intValue));
                BitSet bitSet2 = (BitSet) arrayMap11.get(Integer.valueOf(intValue));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap10.put(Integer.valueOf(intValue), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap11.put(Integer.valueOf(intValue), bitSet2);
                }
                int i5 = 0;
                while (i5 < zzf.zzbxG.length * 64) {
                    if (zzaut.zza(zzf.zzbxG, i5)) {
                        map2 = zzfy;
                        it3 = it4;
                        arrayMap8 = arrayMap11;
                        zzKl().zzMe().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i5));
                        bitSet2.set(i5);
                        if (zzaut.zza(zzf.zzbxH, i5)) {
                            bitSet.set(i5);
                        }
                    } else {
                        map2 = zzfy;
                        it3 = it4;
                        arrayMap8 = arrayMap11;
                    }
                    i5++;
                    zzfy = map2;
                    it4 = it3;
                    arrayMap11 = arrayMap8;
                }
                Map<Integer, zzauw.zzf> map3 = zzfy;
                zzauw.zza zza2 = new zzauw.zza();
                arrayMap9.put(Integer.valueOf(intValue), zza2);
                zza2.zzbwW = false;
                zza2.zzbwV = zzf;
                zza2.zzbwU = new zzauw.zzf();
                zza2.zzbwU.zzbxH = zzaut.zza(bitSet);
                zza2.zzbwU.zzbxG = zzaut.zza(bitSet2);
                zzfy = map3;
                it4 = it4;
                arrayMap11 = arrayMap11;
            }
        }
        ArrayMap arrayMap12 = arrayMap11;
        int i6 = 0;
        char c2 = 256;
        if (zzbArr2 != null) {
            ArrayMap arrayMap13 = new ArrayMap();
            int length = zzbArr2.length;
            int i7 = 0;
            while (i7 < length) {
                zzauw.zzb zzb2 = zzbArr2[i7];
                zzatn zzQ = zzKg().zzQ(str3, zzb2.name);
                if (zzQ == null) {
                    zzKl().zzMa().zze("Event aggregate wasn't created during raw event logging. appId, event", zzatx.zzfE(str), zzb2.name);
                    zzb = zzb2;
                    i3 = i7;
                    i2 = length;
                    arrayMap3 = arrayMap13;
                    arrayMap4 = arrayMap12;
                    i4 = 0;
                    zzatn = new zzatn(str3, zzb2.name, 1, 1, zzb2.zzbwZ.longValue());
                } else {
                    zzb = zzb2;
                    i3 = i7;
                    arrayMap3 = arrayMap13;
                    i2 = length;
                    i4 = i6;
                    arrayMap4 = arrayMap12;
                    zzatn = zzQ.zzLV();
                }
                zzKg().zza(zzatn);
                long j = zzatn.zzbrA;
                zzauw.zzb zzb3 = zzb;
                Map map4 = (Map) arrayMap3.get(zzb3.name);
                if (map4 == null) {
                    map4 = zzKg().zzV(str3, zzb3.name);
                    if (map4 == null) {
                        map4 = new ArrayMap();
                    }
                    arrayMap3.put(zzb3.name, map4);
                }
                Iterator it5 = map4.keySet().iterator();
                while (it5.hasNext()) {
                    int intValue2 = ((Integer) it5.next()).intValue();
                    if (hashSet.contains(Integer.valueOf(intValue2))) {
                        zzKl().zzMe().zzj("Skipping failed audience ID", Integer.valueOf(intValue2));
                    } else {
                        zzauw.zza zza3 = (zzauw.zza) arrayMap9.get(Integer.valueOf(intValue2));
                        BitSet bitSet3 = (BitSet) arrayMap10.get(Integer.valueOf(intValue2));
                        BitSet bitSet4 = (BitSet) arrayMap4.get(Integer.valueOf(intValue2));
                        if (zza3 == null) {
                            zzauw.zza zza4 = new zzauw.zza();
                            arrayMap9.put(Integer.valueOf(intValue2), zza4);
                            zza4.zzbwW = true;
                            BitSet bitSet5 = new BitSet();
                            arrayMap10.put(Integer.valueOf(intValue2), bitSet5);
                            bitSet4 = new BitSet();
                            arrayMap4.put(Integer.valueOf(intValue2), bitSet4);
                            bitSet3 = bitSet5;
                        }
                        Iterator it6 = ((List) map4.get(Integer.valueOf(intValue2))).iterator();
                        while (it6.hasNext()) {
                            Map map5 = map4;
                            zzauu.zzb zzb4 = (zzauu.zzb) it6.next();
                            Iterator it7 = it5;
                            Iterator it8 = it6;
                            if (zzKl().zzak(2)) {
                                arrayMap7 = arrayMap3;
                                arrayMap6 = arrayMap4;
                                arrayMap5 = arrayMap10;
                                zzKl().zzMe().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue2), zzb4.zzbwo, zzb4.zzbwp);
                                zzKl().zzMe().zzj("Filter definition", zzaut.zza(zzb4));
                            } else {
                                arrayMap6 = arrayMap4;
                                arrayMap7 = arrayMap3;
                                arrayMap5 = arrayMap10;
                            }
                            if (zzb4.zzbwo != null) {
                                if (zzb4.zzbwo.intValue() <= 256) {
                                    if (bitSet3.get(zzb4.zzbwo.intValue())) {
                                        zza = zzKl().zzMe();
                                        str2 = "Event filter already evaluated true. audience ID, filter ID";
                                        obj = Integer.valueOf(intValue2);
                                        obj2 = zzb4.zzbwo;
                                        zza.zze(str2, obj, obj2);
                                        map4 = map5;
                                        it5 = it7;
                                        it6 = it8;
                                        arrayMap3 = arrayMap7;
                                        arrayMap4 = arrayMap6;
                                        arrayMap10 = arrayMap5;
                                    } else {
                                        Boolean zza5 = zza(zzb4, zzb3, j);
                                        zzKl().zzMe().zzj("Event filter result", zza5 == null ? "null" : zza5);
                                        if (zza5 == null) {
                                            hashSet.add(Integer.valueOf(intValue2));
                                        } else {
                                            bitSet4.set(zzb4.zzbwo.intValue());
                                            if (zza5.booleanValue()) {
                                                bitSet3.set(zzb4.zzbwo.intValue());
                                            }
                                        }
                                        map4 = map5;
                                        it5 = it7;
                                        it6 = it8;
                                        arrayMap3 = arrayMap7;
                                        arrayMap4 = arrayMap6;
                                        arrayMap10 = arrayMap5;
                                    }
                                }
                            }
                            zza = zzKl().zzMa();
                            str2 = "Invalid event filter ID. appId, id";
                            obj = zzatx.zzfE(str);
                            obj2 = String.valueOf(zzb4.zzbwo);
                            zza.zze(str2, obj, obj2);
                            map4 = map5;
                            it5 = it7;
                            it6 = it8;
                            arrayMap3 = arrayMap7;
                            arrayMap4 = arrayMap6;
                            arrayMap10 = arrayMap5;
                        }
                    }
                }
                ArrayMap arrayMap14 = arrayMap10;
                i7 = i3 + 1;
                c2 = 256;
                i6 = i4;
                length = i2;
                arrayMap13 = arrayMap3;
                arrayMap12 = arrayMap4;
                zzbArr2 = zzbArr;
                zzgArr2 = zzgArr;
            }
        }
        int i8 = i6;
        char c3 = c2;
        ArrayMap arrayMap15 = arrayMap10;
        ArrayMap arrayMap16 = arrayMap12;
        zzauw.zzg[] zzgArr3 = zzgArr2;
        if (zzgArr3 != null) {
            ArrayMap arrayMap17 = new ArrayMap();
            int length2 = zzgArr3.length;
            int i9 = i8;
            while (i9 < length2) {
                zzauw.zzg zzg = zzgArr3[i9];
                Map map6 = (Map) arrayMap17.get(zzg.name);
                if (map6 == null) {
                    map6 = zzKg().zzW(str3, zzg.name);
                    if (map6 == null) {
                        map6 = new ArrayMap();
                    }
                    arrayMap17.put(zzg.name, map6);
                }
                Iterator it9 = map6.keySet().iterator();
                while (it9.hasNext()) {
                    int intValue3 = ((Integer) it9.next()).intValue();
                    if (hashSet.contains(Integer.valueOf(intValue3))) {
                        zzKl().zzMe().zzj("Skipping failed audience ID", Integer.valueOf(intValue3));
                    } else {
                        zzauw.zza zza6 = (zzauw.zza) arrayMap9.get(Integer.valueOf(intValue3));
                        ArrayMap arrayMap18 = arrayMap15;
                        BitSet bitSet6 = (BitSet) arrayMap18.get(Integer.valueOf(intValue3));
                        ArrayMap arrayMap19 = arrayMap16;
                        BitSet bitSet7 = (BitSet) arrayMap19.get(Integer.valueOf(intValue3));
                        if (zza6 == null) {
                            zzauw.zza zza7 = new zzauw.zza();
                            arrayMap9.put(Integer.valueOf(intValue3), zza7);
                            zza7.zzbwW = true;
                            bitSet6 = new BitSet();
                            arrayMap18.put(Integer.valueOf(intValue3), bitSet6);
                            bitSet7 = new BitSet();
                            arrayMap19.put(Integer.valueOf(intValue3), bitSet7);
                        }
                        Iterator it10 = ((List) map6.get(Integer.valueOf(intValue3))).iterator();
                        while (true) {
                            if (!it10.hasNext()) {
                                arrayMap = arrayMap19;
                                arrayMap2 = arrayMap17;
                                i = length2;
                                map = map6;
                                it = it9;
                                c = 256;
                                break;
                            }
                            arrayMap2 = arrayMap17;
                            zze = (zzauu.zze) it10.next();
                            i = length2;
                            map = map6;
                            if (zzKl().zzak(2)) {
                                it = it9;
                                it2 = it10;
                                arrayMap = arrayMap19;
                                zzKl().zzMe().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue3), zze.zzbwo, zze.zzbwE);
                                zzKl().zzMe().zzj("Filter definition", zzaut.zza(zze));
                            } else {
                                arrayMap = arrayMap19;
                                it = it9;
                                it2 = it10;
                            }
                            if (zze.zzbwo == null) {
                                c = 256;
                                break;
                            }
                            c = 256;
                            if (zze.zzbwo.intValue() > 256) {
                                break;
                            }
                            if (bitSet6.get(zze.zzbwo.intValue())) {
                                zzKl().zzMe().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue3), zze.zzbwo);
                            } else {
                                Boolean zza8 = zza(zze, zzg);
                                zzKl().zzMe().zzj("Property filter result", zza8 == null ? "null" : zza8);
                                if (zza8 == null) {
                                    hashSet.add(Integer.valueOf(intValue3));
                                } else {
                                    bitSet7.set(zze.zzbwo.intValue());
                                    if (zza8.booleanValue()) {
                                        bitSet6.set(zze.zzbwo.intValue());
                                    }
                                }
                            }
                            arrayMap17 = arrayMap2;
                            length2 = i;
                            map6 = map;
                            it9 = it;
                            it10 = it2;
                            arrayMap19 = arrayMap;
                        }
                        zzKl().zzMa().zze("Invalid property filter ID. appId, id", zzatx.zzfE(str), String.valueOf(zze.zzbwo));
                        hashSet.add(Integer.valueOf(intValue3));
                        c3 = c;
                        arrayMap15 = arrayMap18;
                        arrayMap17 = arrayMap2;
                        length2 = i;
                        map6 = map;
                        it9 = it;
                        arrayMap16 = arrayMap;
                        zzauw.zzg[] zzgArr4 = zzgArr;
                    }
                }
                ArrayMap arrayMap20 = arrayMap17;
                int i10 = length2;
                char c4 = c3;
                ArrayMap arrayMap21 = arrayMap16;
                ArrayMap arrayMap22 = arrayMap15;
                i9++;
                length2 = i10;
                zzgArr3 = zzgArr;
            }
        }
        ArrayMap arrayMap23 = arrayMap16;
        ArrayMap arrayMap24 = arrayMap15;
        zzauw.zza[] zzaArr = new zzauw.zza[arrayMap24.size()];
        int i11 = i8;
        for (Integer intValue4 : arrayMap24.keySet()) {
            int intValue5 = intValue4.intValue();
            if (!hashSet.contains(Integer.valueOf(intValue5))) {
                zzauw.zza zza9 = (zzauw.zza) arrayMap9.get(Integer.valueOf(intValue5));
                if (zza9 == null) {
                    zza9 = new zzauw.zza();
                }
                zzaArr[i11] = zza9;
                zza9.zzbwk = Integer.valueOf(intValue5);
                zza9.zzbwU = new zzauw.zzf();
                zza9.zzbwU.zzbxH = zzaut.zza((BitSet) arrayMap24.get(Integer.valueOf(intValue5)));
                zza9.zzbwU.zzbxG = zzaut.zza((BitSet) arrayMap23.get(Integer.valueOf(intValue5)));
                zzKg().zza(str3, intValue5, zza9.zzbwU);
                i11++;
            }
        }
        return (zzauw.zza[]) Arrays.copyOf(zzaArr, i11);
    }

    /* access modifiers changed from: protected */
    public void zzmS() {
    }
}
