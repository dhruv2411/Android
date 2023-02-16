package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzu;
import com.google.android.gms.internal.zzaac;
import com.google.android.gms.internal.zzbth;
import com.google.android.gms.internal.zzbti;
import com.google.android.gms.internal.zzbtj;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp {
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    private static final List<String> zzbWA = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> zzbWB = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> zzbWC = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> zzbWD = Arrays.asList(new String[0]);
    private static final Set<String> zzbWE = Collections.emptySet();
    static final Map<String, FirebaseApp> zzbhH = new ArrayMap();
    /* access modifiers changed from: private */
    public static final Object zztX = new Object();
    private final String mName;
    private final FirebaseOptions zzbWF;
    private final AtomicBoolean zzbWG = new AtomicBoolean(false);
    private final AtomicBoolean zzbWH = new AtomicBoolean();
    private final List<zza> zzbWI = new CopyOnWriteArrayList();
    private final List<zzb> zzbWJ = new CopyOnWriteArrayList();
    private final List<Object> zzbWK = new CopyOnWriteArrayList();
    private zzbti zzbWL;
    private final Context zzwi;

    public interface zza {
        void zzb(@NonNull zzbtj zzbtj);
    }

    public interface zzb {
        void zzat(boolean z);
    }

    @TargetApi(24)
    private static class zzc extends BroadcastReceiver {
        private static AtomicReference<zzc> zzbWM = new AtomicReference<>();
        private final Context zzwi;

        public zzc(Context context) {
            this.zzwi = context;
        }

        /* access modifiers changed from: private */
        public static void zzcm(Context context) {
            if (zzbWM.get() == null) {
                zzc zzc = new zzc(context);
                if (zzbWM.compareAndSet((Object) null, zzc)) {
                    context.registerReceiver(zzc, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.zztX) {
                for (FirebaseApp zza : FirebaseApp.zzbhH.values()) {
                    zza.zzUY();
                }
            }
            unregister();
        }

        public void unregister() {
            this.zzwi.unregisterReceiver(this);
        }
    }

    protected FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.zzwi = (Context) zzac.zzw(context);
        this.mName = zzac.zzdr(str);
        this.zzbWF = (FirebaseOptions) zzac.zzw(firebaseOptions);
    }

    public static List<FirebaseApp> getApps(Context context) {
        ArrayList arrayList;
        zzbth zzcw = zzbth.zzcw(context);
        synchronized (zztX) {
            arrayList = new ArrayList(zzbhH.values());
            Set<String> zzabZ = zzbth.zzabY().zzabZ();
            zzabZ.removeAll(zzbhH.keySet());
            for (String next : zzabZ) {
                zzcw.zzjC(next);
                arrayList.add(initializeApp(context, (FirebaseOptions) null, next));
            }
        }
        return arrayList;
    }

    @Nullable
    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (zztX) {
            firebaseApp = zzbhH.get(DEFAULT_APP_NAME);
            if (firebaseApp == null) {
                String valueOf = String.valueOf(zzu.zzzr());
                StringBuilder sb = new StringBuilder(116 + String.valueOf(valueOf).length());
                sb.append("Default FirebaseApp is not initialized in this process ");
                sb.append(valueOf);
                sb.append(". Make sure to call FirebaseApp.initializeApp(Context) first.");
                throw new IllegalStateException(sb.toString());
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        String str2;
        synchronized (zztX) {
            firebaseApp = zzbhH.get(zzis(str));
            if (firebaseApp == null) {
                List<String> zzUX = zzUX();
                if (zzUX.isEmpty()) {
                    str2 = "";
                } else {
                    String valueOf = String.valueOf(TextUtils.join(", ", zzUX));
                    str2 = valueOf.length() != 0 ? "Available app names: ".concat(valueOf) : new String("Available app names: ");
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{str, str2}));
            }
        }
        return firebaseApp;
    }

    @Nullable
    public static FirebaseApp initializeApp(Context context) {
        synchronized (zztX) {
            if (zzbhH.containsKey(DEFAULT_APP_NAME)) {
                FirebaseApp instance = getInstance();
                return instance;
            }
            FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
            if (fromResource == null) {
                return null;
            }
            FirebaseApp initializeApp = initializeApp(context, fromResource);
            return initializeApp;
        }
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, DEFAULT_APP_NAME);
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        zzbth zzcw = zzbth.zzcw(context);
        zzcl(context);
        String zzis = zzis(str);
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (zztX) {
            StringBuilder sb = new StringBuilder(33 + String.valueOf(zzis).length());
            sb.append("FirebaseApp name ");
            sb.append(zzis);
            sb.append(" already exists!");
            zzac.zza(!zzbhH.containsKey(zzis), (Object) sb.toString());
            zzac.zzb(context, (Object) "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, zzis, firebaseOptions);
            zzbhH.put(zzis, firebaseApp);
        }
        zzcw.zzg(firebaseApp);
        firebaseApp.zza(FirebaseApp.class, firebaseApp, zzbWA);
        if (firebaseApp.zzUV()) {
            firebaseApp.zza(FirebaseApp.class, firebaseApp, zzbWB);
            firebaseApp.zza(Context.class, firebaseApp.getApplicationContext(), zzbWC);
        }
        return firebaseApp;
    }

    private void zzUU() {
        zzac.zza(!this.zzbWH.get(), (Object) "FirebaseApp was deleted");
    }

    private static List<String> zzUX() {
        com.google.android.gms.common.util.zza zza2 = new com.google.android.gms.common.util.zza();
        synchronized (zztX) {
            for (FirebaseApp name : zzbhH.values()) {
                zza2.add(name.getName());
            }
            zzbth zzabY = zzbth.zzabY();
            if (zzabY != null) {
                zza2.addAll(zzabY.zzabZ());
            }
        }
        ArrayList arrayList = new ArrayList(zza2);
        Collections.sort(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void zzUY() {
        zza(FirebaseApp.class, this, zzbWA);
        if (zzUV()) {
            zza(FirebaseApp.class, this, zzbWB);
            zza(Context.class, this.zzwi, zzbWC);
        }
    }

    private <T> void zza(Class<T> cls, T t, Iterable<String> iterable) {
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.zzwi);
        if (isDeviceProtectedStorage) {
            zzc.zzcm(this.zzwi);
        }
        for (String next : iterable) {
            if (isDeviceProtectedStorage) {
                try {
                    if (zzbWD.contains(next)) {
                    }
                } catch (ClassNotFoundException unused) {
                    if (zzbWE.contains(next)) {
                        throw new IllegalStateException(String.valueOf(next).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                    }
                    Log.d("FirebaseApp", String.valueOf(next).concat(" is not linked. Skipping initialization."));
                } catch (NoSuchMethodException unused2) {
                    throw new IllegalStateException(String.valueOf(next).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (InvocationTargetException e) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e);
                } catch (IllegalAccessException e2) {
                    String valueOf = String.valueOf(next);
                    Log.wtf("FirebaseApp", valueOf.length() != 0 ? "Failed to initialize ".concat(valueOf) : new String("Failed to initialize "), e2);
                }
            }
            Method method = Class.forName(next).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke((Object) null, new Object[]{t});
            }
        }
    }

    private void zzaW(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (zzb zzat : this.zzbWJ) {
            zzat.zzat(z);
        }
    }

    public static void zzat(boolean z) {
        synchronized (zztX) {
            Iterator it = new ArrayList(zzbhH.values()).iterator();
            while (it.hasNext()) {
                FirebaseApp firebaseApp = (FirebaseApp) it.next();
                if (firebaseApp.zzbWG.get()) {
                    firebaseApp.zzaW(z);
                }
            }
        }
    }

    @TargetApi(14)
    private static void zzcl(Context context) {
        zzt.zzzg();
        if (context.getApplicationContext() instanceof Application) {
            zzaac.zza((Application) context.getApplicationContext());
            zzaac.zzvB().zza((zzaac.zza) new zzaac.zza() {
                public void zzat(boolean z) {
                    FirebaseApp.zzat(z);
                }
            });
        }
    }

    private static String zzis(@NonNull String str) {
        return str.trim();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseApp)) {
            return false;
        }
        return this.mName.equals(((FirebaseApp) obj).getName());
    }

    @NonNull
    public Context getApplicationContext() {
        zzUU();
        return this.zzwi;
    }

    @NonNull
    public String getName() {
        zzUU();
        return this.mName;
    }

    @NonNull
    public FirebaseOptions getOptions() {
        zzUU();
        return this.zzbWF;
    }

    public Task<GetTokenResult> getToken(boolean z) {
        zzUU();
        return this.zzbWL == null ? Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.")) : this.zzbWL.zzaX(z);
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public void setAutomaticResourceManagementEnabled(boolean z) {
        boolean z2;
        zzUU();
        if (this.zzbWG.compareAndSet(!z, z)) {
            boolean zzvC = zzaac.zzvB().zzvC();
            if (z && zzvC) {
                z2 = true;
            } else if (!z && zzvC) {
                z2 = false;
            } else {
                return;
            }
            zzaW(z2);
        }
    }

    public String toString() {
        return zzaa.zzv(this).zzg("name", this.mName).zzg("options", this.zzbWF).toString();
    }

    public boolean zzUV() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    public String zzUW() {
        String valueOf = String.valueOf(com.google.android.gms.common.util.zzc.zzs(getName().getBytes()));
        String valueOf2 = String.valueOf(com.google.android.gms.common.util.zzc.zzs(getOptions().getApplicationId().getBytes()));
        StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append("+");
        sb.append(valueOf2);
        return sb.toString();
    }

    public void zza(@NonNull zzbti zzbti) {
        this.zzbWL = (zzbti) zzac.zzw(zzbti);
    }

    @UiThread
    public void zza(@NonNull zzbtj zzbtj) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        int i = 0;
        for (zza zzb2 : this.zzbWI) {
            zzb2.zzb(zzbtj);
            i++;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[]{Integer.valueOf(i)}));
    }

    public void zza(@NonNull zza zza2) {
        zzUU();
        zzac.zzw(zza2);
        this.zzbWI.add(zza2);
    }

    public void zza(zzb zzb2) {
        zzUU();
        if (this.zzbWG.get() && zzaac.zzvB().zzvC()) {
            zzb2.zzat(true);
        }
        this.zzbWJ.add(zzb2);
    }
}
