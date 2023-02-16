package com.google.android.gms.tagmanager;

import android.annotation.TargetApi;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.tagmanager.DataLayer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TagManager {
    private static TagManager zzbIu;
    private final Context mContext;
    private final DataLayer zzbEV;
    private final zzt zzbHu;
    private final zza zzbIr;
    private final zzdb zzbIs;
    private final ConcurrentMap<String, zzo> zzbIt;

    public interface zza {
        zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzt zzt);
    }

    TagManager(Context context, zza zza2, DataLayer dataLayer, zzdb zzdb) {
        if (context == null) {
            throw new NullPointerException("context cannot be null");
        }
        this.mContext = context.getApplicationContext();
        this.zzbIs = zzdb;
        this.zzbIr = zza2;
        this.zzbIt = new ConcurrentHashMap();
        this.zzbEV = dataLayer;
        this.zzbEV.zza((DataLayer.zzb) new DataLayer.zzb() {
            public void zzaa(Map<String, Object> map) {
                Object obj = map.get("event");
                if (obj != null) {
                    TagManager.this.zzhs(obj.toString());
                }
            }
        });
        this.zzbEV.zza((DataLayer.zzb) new zzd(this.mContext));
        this.zzbHu = new zzt();
        zzRE();
        zzRF();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzbIu == null) {
                if (context == null) {
                    zzbo.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
                zzbIu = new TagManager(context, new zza() {
                    public zzp zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzt zzt) {
                        return new zzp(context, tagManager, looper, str, i, zzt);
                    }
                }, new DataLayer(new zzx(context)), zzdc.zzRy());
            }
            tagManager = zzbIu;
        }
        return tagManager;
    }

    @TargetApi(14)
    private void zzRE() {
        int i = Build.VERSION.SDK_INT;
        this.mContext.registerComponentCallbacks(new ComponentCallbacks2() {
            public void onConfigurationChanged(Configuration configuration) {
            }

            public void onLowMemory() {
            }

            public void onTrimMemory(int i) {
                if (i == 20) {
                    TagManager.this.dispatch();
                }
            }
        });
    }

    private void zzRF() {
        zza.zzbS(this.mContext);
    }

    /* access modifiers changed from: private */
    public void zzhs(String str) {
        for (zzo zzgU : this.zzbIt.values()) {
            zzgU.zzgU(str);
        }
    }

    public void dispatch() {
        this.zzbIs.dispatch();
    }

    public DataLayer getDataLayer() {
        return this.zzbEV;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i) {
        zzp zza2 = this.zzbIr.zza(this.mContext, this, (Looper) null, str, i, this.zzbHu);
        zza2.zzQk();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i, Handler handler) {
        zzp zza2 = this.zzbIr.zza(this.mContext, this, handler.getLooper(), str, i, this.zzbHu);
        zza2.zzQk();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i) {
        zzp zza2 = this.zzbIr.zza(this.mContext, this, (Looper) null, str, i, this.zzbHu);
        zza2.zzQm();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i, Handler handler) {
        zzp zza2 = this.zzbIr.zza(this.mContext, this, handler.getLooper(), str, i, this.zzbHu);
        zza2.zzQm();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i) {
        zzp zza2 = this.zzbIr.zza(this.mContext, this, (Looper) null, str, i, this.zzbHu);
        zza2.zzQl();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i, Handler handler) {
        zzp zza2 = this.zzbIr.zza(this.mContext, this, handler.getLooper(), str, i, this.zzbHu);
        zza2.zzQl();
        return zza2;
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzbo.setLogLevel(z ? 2 : 5);
    }

    public int zza(zzo zzo) {
        this.zzbIt.put(zzo.getContainerId(), zzo);
        return this.zzbIt.size();
    }

    public boolean zzb(zzo zzo) {
        return this.zzbIt.remove(zzo.getContainerId()) != null;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean zzv(Uri uri) {
        boolean z;
        zzcj zzRe = zzcj.zzRe();
        if (zzRe.zzv(uri)) {
            String containerId = zzRe.getContainerId();
            switch (zzRe.zzRf()) {
                case NONE:
                    zzo zzo = (zzo) this.zzbIt.get(containerId);
                    if (zzo != null) {
                        zzo.zzgW((String) null);
                        zzo.refresh();
                        break;
                    }
                    break;
                case CONTAINER:
                case CONTAINER_DEBUG:
                    for (String str : this.zzbIt.keySet()) {
                        zzo zzo2 = (zzo) this.zzbIt.get(str);
                        if (str.equals(containerId)) {
                            zzo2.zzgW(zzRe.zzRg());
                        } else if (zzo2.zzQh() != null) {
                            zzo2.zzgW((String) null);
                        }
                        zzo2.refresh();
                    }
                    break;
            }
            z = true;
        } else {
            z = false;
        }
        return z;
    }
}
