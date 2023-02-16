package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzaj;
import com.google.android.gms.internal.zzbjf;
import com.google.android.gms.tagmanager.zzcj;
import com.google.android.gms.tagmanager.zzu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Container {
    private final Context mContext;
    private final String zzbEU;
    private final DataLayer zzbEV;
    private zzcx zzbEW;
    private Map<String, FunctionCallMacroCallback> zzbEX = new HashMap();
    private Map<String, FunctionCallTagCallback> zzbEY = new HashMap();
    private volatile long zzbEZ;
    private volatile String zzbFa = "";

    public interface FunctionCallMacroCallback {
        Object getValue(String str, Map<String, Object> map);
    }

    public interface FunctionCallTagCallback {
        void execute(String str, Map<String, Object> map);
    }

    private class zza implements zzu.zza {
        private zza() {
        }

        public Object zze(String str, Map<String, Object> map) {
            FunctionCallMacroCallback zzgS = Container.this.zzgS(str);
            if (zzgS == null) {
                return null;
            }
            return zzgS.getValue(str, map);
        }
    }

    private class zzb implements zzu.zza {
        private zzb() {
        }

        public Object zze(String str, Map<String, Object> map) {
            FunctionCallTagCallback zzgT = Container.this.zzgT(str);
            if (zzgT != null) {
                zzgT.execute(str, map);
            }
            return zzdl.zzRQ();
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzaj.zzj zzj) {
        this.mContext = context;
        this.zzbEV = dataLayer;
        this.zzbEU = str;
        this.zzbEZ = j;
        zza(zzj.zzlr);
        if (zzj.zzlq != null) {
            zza(zzj.zzlq);
        }
    }

    Container(Context context, DataLayer dataLayer, String str, long j, zzbjf.zzc zzc) {
        this.mContext = context;
        this.zzbEV = dataLayer;
        this.zzbEU = str;
        this.zzbEZ = j;
        zza(zzc);
    }

    private synchronized zzcx zzQg() {
        return this.zzbEW;
    }

    private void zza(zzaj.zzf zzf) {
        if (zzf == null) {
            throw new NullPointerException();
        }
        try {
            zza(zzbjf.zzb(zzf));
        } catch (zzbjf.zzg e) {
            String valueOf = String.valueOf(zzf);
            String valueOf2 = String.valueOf(e.toString());
            StringBuilder sb = new StringBuilder(46 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append("Not loading resource: ");
            sb.append(valueOf);
            sb.append(" because it is invalid: ");
            sb.append(valueOf2);
            zzbo.e(sb.toString());
        }
    }

    private void zza(zzbjf.zzc zzc) {
        this.zzbFa = zzc.getVersion();
        zzbjf.zzc zzc2 = zzc;
        zza(new zzcx(this.mContext, zzc2, this.zzbEV, new zza(), new zzb(), zzgV(this.zzbFa)));
        if (getBoolean("_gtm.loadEventEnabled")) {
            this.zzbEV.pushEvent("gtm.load", DataLayer.mapOf("gtm.id", this.zzbEU));
        }
    }

    private synchronized void zza(zzcx zzcx) {
        this.zzbEW = zzcx;
    }

    private void zza(zzaj.zzi[] zziArr) {
        ArrayList arrayList = new ArrayList();
        for (zzaj.zzi add : zziArr) {
            arrayList.add(add);
        }
        zzQg().zzQ(arrayList);
    }

    public boolean getBoolean(String str) {
        String sb;
        zzcx zzQg = zzQg();
        if (zzQg == null) {
            sb = "getBoolean called for closed container.";
        } else {
            try {
                return zzdl.zzi(zzQg.zzhq(str).getObject()).booleanValue();
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                StringBuilder sb2 = new StringBuilder(66 + String.valueOf(valueOf).length());
                sb2.append("Calling getBoolean() threw an exception: ");
                sb2.append(valueOf);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzbo.e(sb);
        return zzdl.zzRO().booleanValue();
    }

    public String getContainerId() {
        return this.zzbEU;
    }

    public double getDouble(String str) {
        String sb;
        zzcx zzQg = zzQg();
        if (zzQg == null) {
            sb = "getDouble called for closed container.";
        } else {
            try {
                return zzdl.zzh(zzQg.zzhq(str).getObject()).doubleValue();
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                StringBuilder sb2 = new StringBuilder(65 + String.valueOf(valueOf).length());
                sb2.append("Calling getDouble() threw an exception: ");
                sb2.append(valueOf);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzbo.e(sb);
        return zzdl.zzRN().doubleValue();
    }

    public long getLastRefreshTime() {
        return this.zzbEZ;
    }

    public long getLong(String str) {
        String sb;
        zzcx zzQg = zzQg();
        if (zzQg == null) {
            sb = "getLong called for closed container.";
        } else {
            try {
                return zzdl.zzg(zzQg.zzhq(str).getObject()).longValue();
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                StringBuilder sb2 = new StringBuilder(63 + String.valueOf(valueOf).length());
                sb2.append("Calling getLong() threw an exception: ");
                sb2.append(valueOf);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzbo.e(sb);
        return zzdl.zzRM().longValue();
    }

    public String getString(String str) {
        String sb;
        zzcx zzQg = zzQg();
        if (zzQg == null) {
            sb = "getString called for closed container.";
        } else {
            try {
                return zzdl.zze(zzQg.zzhq(str).getObject());
            } catch (Exception e) {
                String valueOf = String.valueOf(e.getMessage());
                StringBuilder sb2 = new StringBuilder(65 + String.valueOf(valueOf).length());
                sb2.append("Calling getString() threw an exception: ");
                sb2.append(valueOf);
                sb2.append(" Returning default value.");
                sb = sb2.toString();
            }
        }
        zzbo.e(sb);
        return zzdl.zzRQ();
    }

    public boolean isDefault() {
        return getLastRefreshTime() == 0;
    }

    public void registerFunctionCallMacroCallback(String str, FunctionCallMacroCallback functionCallMacroCallback) {
        if (functionCallMacroCallback == null) {
            throw new NullPointerException("Macro handler must be non-null");
        }
        synchronized (this.zzbEX) {
            this.zzbEX.put(str, functionCallMacroCallback);
        }
    }

    public void registerFunctionCallTagCallback(String str, FunctionCallTagCallback functionCallTagCallback) {
        if (functionCallTagCallback == null) {
            throw new NullPointerException("Tag callback must be non-null");
        }
        synchronized (this.zzbEY) {
            this.zzbEY.put(str, functionCallTagCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public void release() {
        this.zzbEW = null;
    }

    public void unregisterFunctionCallMacroCallback(String str) {
        synchronized (this.zzbEX) {
            this.zzbEX.remove(str);
        }
    }

    public void unregisterFunctionCallTagCallback(String str) {
        synchronized (this.zzbEY) {
            this.zzbEY.remove(str);
        }
    }

    public String zzQf() {
        return this.zzbFa;
    }

    /* access modifiers changed from: package-private */
    public FunctionCallMacroCallback zzgS(String str) {
        FunctionCallMacroCallback functionCallMacroCallback;
        synchronized (this.zzbEX) {
            functionCallMacroCallback = this.zzbEX.get(str);
        }
        return functionCallMacroCallback;
    }

    public FunctionCallTagCallback zzgT(String str) {
        FunctionCallTagCallback functionCallTagCallback;
        synchronized (this.zzbEY) {
            functionCallTagCallback = this.zzbEY.get(str);
        }
        return functionCallTagCallback;
    }

    public void zzgU(String str) {
        zzQg().zzgU(str);
    }

    /* access modifiers changed from: package-private */
    public zzaj zzgV(String str) {
        zzcj.zzRe().zzRf().equals(zzcj.zza.CONTAINER_DEBUG);
        return new zzbw();
    }
}
