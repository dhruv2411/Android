package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    static final String[] zzbFB = "gtm.lifetime".toString().split("\\.");
    private static final Pattern zzbFC = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<zzb, Integer> zzbFD;
    private final Map<String, Object> zzbFE;
    private final ReentrantLock zzbFF;
    private final LinkedList<Map<String, Object>> zzbFG;
    private final zzc zzbFH;
    /* access modifiers changed from: private */
    public final CountDownLatch zzbFI;

    static final class zza {
        public final Object mValue;
        public final String zzAX;

        zza(String str, Object obj) {
            this.zzAX = str;
            this.mValue = obj;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return this.zzAX.equals(zza.zzAX) && this.mValue.equals(zza.mValue);
        }

        public int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.zzAX.hashCode()), Integer.valueOf(this.mValue.hashCode())});
        }

        public String toString() {
            String str = this.zzAX;
            String valueOf = String.valueOf(this.mValue.toString());
            StringBuilder sb = new StringBuilder(13 + String.valueOf(str).length() + String.valueOf(valueOf).length());
            sb.append("Key: ");
            sb.append(str);
            sb.append(" value: ");
            sb.append(valueOf);
            return sb.toString();
        }
    }

    interface zzb {
        void zzaa(Map<String, Object> map);
    }

    interface zzc {

        public interface zza {
            void zzM(List<zza> list);
        }

        void zza(zza zza2);

        void zza(List<zza> list, long j);

        void zzhc(String str);
    }

    DataLayer() {
        this(new zzc() {
            public void zza(zzc.zza zza) {
                zza.zzM(new ArrayList());
            }

            public void zza(List<zza> list, long j) {
            }

            public void zzhc(String str) {
            }
        });
    }

    DataLayer(zzc zzc2) {
        this.zzbFH = zzc2;
        this.zzbFD = new ConcurrentHashMap<>();
        this.zzbFE = new HashMap();
        this.zzbFF = new ReentrantLock();
        this.zzbFG = new LinkedList<>();
        this.zzbFI = new CountDownLatch(1);
        zzQw();
    }

    public static List<Object> listOf(Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object add : objArr) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static Map<String, Object> mapOf(Object... objArr) {
        if (objArr.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < objArr.length; i += 2) {
            if (!(objArr[i] instanceof String)) {
                String valueOf = String.valueOf(objArr[i]);
                StringBuilder sb = new StringBuilder(21 + String.valueOf(valueOf).length());
                sb.append("key is not a string: ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            }
            hashMap.put(objArr[i], objArr[i + 1]);
        }
        return hashMap;
    }

    private void zzQw() {
        this.zzbFH.zza(new zzc.zza() {
            public void zzM(List<zza> list) {
                for (zza next : list) {
                    DataLayer.this.zzac(DataLayer.this.zzo(next.zzAX, next.mValue));
                }
                DataLayer.this.zzbFI.countDown();
            }
        });
    }

    private void zzQx() {
        int i = 0;
        do {
            Map poll = this.zzbFG.poll();
            if (poll != null) {
                zzah(poll);
                i++;
            } else {
                return;
            }
        } while (i <= 500);
        this.zzbFG.clear();
        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
    }

    private void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Map.Entry next : map.entrySet()) {
            String str2 = str.length() == 0 ? "" : ".";
            String str3 = (String) next.getKey();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
            sb.append(str);
            sb.append(str2);
            sb.append(str3);
            String sb2 = sb.toString();
            if (next.getValue() instanceof Map) {
                zza((Map) next.getValue(), sb2, collection);
            } else if (!sb2.equals("gtm.lifetime")) {
                collection.add(new zza(sb2, next.getValue()));
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzac(Map<String, Object> map) {
        this.zzbFF.lock();
        try {
            this.zzbFG.offer(map);
            if (this.zzbFF.getHoldCount() == 1) {
                zzQx();
            }
            zzad(map);
        } finally {
            this.zzbFF.unlock();
        }
    }

    private void zzad(Map<String, Object> map) {
        Long zzae = zzae(map);
        if (zzae != null) {
            this.zzbFH.zza(zzag(map), zzae.longValue());
        }
    }

    private Long zzae(Map<String, Object> map) {
        Object zzaf = zzaf(map);
        if (zzaf == null) {
            return null;
        }
        return zzhb(zzaf.toString());
    }

    private Object zzaf(Map<String, Object> map) {
        String[] strArr = zzbFB;
        int i = 0;
        int length = strArr.length;
        Object obj = map;
        while (i < length) {
            String str = strArr[i];
            if (!(obj instanceof Map)) {
                return null;
            }
            i++;
            obj = ((Map) obj).get(str);
        }
        return obj;
    }

    private List<zza> zzag(Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        zza(map, "", arrayList);
        return arrayList;
    }

    private void zzah(Map<String, Object> map) {
        synchronized (this.zzbFE) {
            for (String next : map.keySet()) {
                zzd(zzo(next, map.get(next)), this.zzbFE);
            }
        }
        zzai(map);
    }

    private void zzai(Map<String, Object> map) {
        for (zzb zzaa : this.zzbFD.keySet()) {
            zzaa.zzaa(map);
        }
    }

    static Long zzhb(String str) {
        long j;
        long j2;
        long j3;
        Matcher matcher = zzbFC.matcher(str);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(str);
            zzbo.zzbg(valueOf.length() != 0 ? "unknown _lifetime: ".concat(valueOf) : new String("unknown _lifetime: "));
            return null;
        }
        try {
            j = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException unused) {
            String valueOf2 = String.valueOf(str);
            zzbo.zzbh(valueOf2.length() != 0 ? "illegal number in _lifetime value: ".concat(valueOf2) : new String("illegal number in _lifetime value: "));
            j = 0;
        }
        if (j <= 0) {
            String valueOf3 = String.valueOf(str);
            zzbo.zzbg(valueOf3.length() != 0 ? "non-positive _lifetime: ".concat(valueOf3) : new String("non-positive _lifetime: "));
            return null;
        }
        String group = matcher.group(2);
        if (group.length() == 0) {
            return Long.valueOf(j);
        }
        char charAt = group.charAt(0);
        if (charAt != 'd') {
            if (charAt == 'h') {
                j3 = j * 1000 * 60;
            } else if (charAt == 'm') {
                j3 = j * 1000;
            } else if (charAt != 's') {
                String valueOf4 = String.valueOf(str);
                zzbo.zzbh(valueOf4.length() != 0 ? "unknown units in _lifetime: ".concat(valueOf4) : new String("unknown units in _lifetime: "));
                return null;
            } else {
                j2 = j * 1000;
            }
            j2 = j3 * 60;
        } else {
            j2 = j * 1000 * 60 * 60 * 24;
        }
        return Long.valueOf(j2);
    }

    public Object get(String str) {
        synchronized (this.zzbFE) {
            Object obj = this.zzbFE;
            for (String str2 : str.split("\\.")) {
                if (!(obj instanceof Map)) {
                    return null;
                }
                obj = ((Map) obj).get(str2);
                if (obj == null) {
                    return null;
                }
            }
            return obj;
        }
    }

    public void push(String str, Object obj) {
        push(zzo(str, obj));
    }

    public void push(Map<String, Object> map) {
        try {
            this.zzbFI.await();
        } catch (InterruptedException unused) {
            zzbo.zzbh("DataLayer.push: unexpected InterruptedException");
        }
        zzac(map);
    }

    public void pushEvent(String str, Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", str);
        push(hashMap);
    }

    public String toString() {
        String sb;
        synchronized (this.zzbFE) {
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry next : this.zzbFE.entrySet()) {
                sb2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[]{next.getKey(), next.getValue()}));
            }
            sb = sb2.toString();
        }
        return sb;
    }

    /* access modifiers changed from: package-private */
    public void zza(zzb zzb2) {
        this.zzbFD.put(zzb2, 0);
    }

    /* access modifiers changed from: package-private */
    public void zzb(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add((Object) null);
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                zzb((List) obj, (List) list2.get(i));
            } else if (obj instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap());
                }
                zzd((Map) obj, (Map) list2.get(i));
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i, obj);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzd(Map<String, Object> map, Map<String, Object> map2) {
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (obj instanceof List) {
                if (!(map2.get(next) instanceof List)) {
                    map2.put(next, new ArrayList());
                }
                zzb((List) obj, (List) map2.get(next));
            } else if (obj instanceof Map) {
                if (!(map2.get(next) instanceof Map)) {
                    map2.put(next, new HashMap());
                }
                zzd((Map) obj, (Map) map2.get(next));
            } else {
                map2.put(next, obj);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzha(String str) {
        push(str, (Object) null);
        this.zzbFH.zzhc(str);
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> zzo(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        HashMap hashMap2 = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap3 = new HashMap();
            hashMap2.put(split[i], hashMap3);
            i++;
            hashMap2 = hashMap3;
        }
        hashMap2.put(split[split.length - 1], obj);
        return hashMap;
    }
}
